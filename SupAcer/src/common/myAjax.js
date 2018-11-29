/**
 * Created by Administrator on 2017/4/26 0026.
 */
import reqwest from 'reqwest';
import Global from './global'
!function (name, context, definition) {
    if (typeof module != 'undefined' && module.exports) module.exports = definition();
    else if (typeof define == 'function' && define.amd) define(definition);
    else context[name] = definition()
}('myAjax', this, function () {

    function myAjax(o, success, fail) {
        o.url = Global.baseUrl +"rest/"+ o.url;
        if (!o.withCredentials) {
            o.withCredentials = true;//携带session信息
        }
        if (!o.method) {
            o.method = 'post'
        }
        if (!o.contentType) {
            o.contentType = 'application/json'
        }
        if(o.data){
            o.data=JSON.stringify(o.data);
        }
        return new MyAjax(o, success, fail)
    }

    function MyAjax(o, success, fail) {
        reqwest(o).then((data) => {
            return success(data);
        }).fail(function (err, msg) {
            if (fail) {
                return fail(err, msg);
            }
        });
    }

    return myAjax;
});
