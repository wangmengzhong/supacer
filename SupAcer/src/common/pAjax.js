import reqwest from 'reqwest';
import Global from './global'
!function (name, context, definition) {
    if (typeof module != 'undefined' && module.exports) module.exports = definition();
    else if (typeof define == 'function' && define.amd) define(definition);
    else context[name] = definition()
}('pAjax', this, function () {

    function pAjax(o, failed) {
        o.url = Global.baseUrl + "rest/" + o.url;
        if (!o.withCredentials) {
            o.withCredentials = true;//携带session信息
        }
        if (!o.method) {
            o.method = 'post'
        }
        if (!o.contentType) {
            o.contentType = 'application/json'
        }
        if (localStorage.getItem("sessionId")) {
            if (o.data) {
                o.data.sessionId = localStorage.getItem("sessionId");
            } else {
                o.data = {sessionId: localStorage.getItem("sessionId")}
            }
            if (o.url.indexOf("?") != -1) {
                o.url = o.url + "&sessionId=" + localStorage.getItem("sessionId")
            } else {
                o.url = o.url + "?sessionId=" + localStorage.getItem("sessionId")
            }
        }
        if (o.data) {
            o.data = JSON.stringify(o.data);
        }
        return new PAjax(o, failed)
    }

    function PAjax(o, failed) {
        return new Promise(
            (resolve, reject) => {
                reqwest(o).then(
                    (json) => {
                        if (isSuccessful(json)) {
                            return resolve(json)
                        } else {
                            if (failed) {
                                return failed(json)
                            } else {
                                return Global.ajaxFalse(json)
                            }
                        }
                    }
                ).catch(
                    (err)=> {
                        if (err.status == "504") {
                            //网关超时
                            if (console) {
                                console.log(o.url + ":" + err.status);
                            }
                        } else if (err.status == "404") {
                            //页面不存在
                            if (console) {
                                console.log(o.url + ":" + err.status);
                            }
                        } else {
                            if (console) {
                                console.log(o.url + ":" + err.status);
                            }
                        }
                        return reject(err);
                    }
                )
            }
        );
    }

    function isSuccessful(data) {
        if (data.successful) {
            return true
        } else {
            return false
        }
    }

    return pAjax;
});
