import {hashHistory} from 'react-router'
import reqwest from 'reqwest'
import pAjax from '../common/pAjax';
const Global = {
    history:hashHistory,
    baseUrl: "/web/",
    //wsUrl:"192.168.58.131:8080/sup/",
    wsUrl: "localhost:80/web/",
    imgUrl: "/web/images/",
    strModulus: "abf9ce9c3f6dc3fa472552a56477d89fa5948b042ba4f0fd9ed972701c8880d70e9865465696ec30036ce4fef7772e638b87118bd565982aed334629e71445e5",
    pukExponent: "10001",
    ajax:pAjax,
    getRandom(){
        //获取1-10随机数
        const chars = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
        let res = "";
        let id = Math.ceil(Math.random() * 10) - 1;
        res += chars[id];
        return res;
    },
    timeStamp2String (time){
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = (datetime.getMonth() + 1)>9?datetime.getMonth() + 1:"0"+(datetime.getMonth() + 1);
        var date = datetime.getDate()>9?datetime.getDate():"0"+datetime.getDate();
        var hour = datetime.getHours()>9? datetime.getHours():"0"+ datetime.getHours();
        var minute = datetime.getMinutes()>9?datetime.getMinutes():"0"+datetime.getMinutes();
        var second = datetime.getSeconds()>9?datetime.getSeconds():"0"+datetime.getSeconds();
        var mseconds = datetime.getMilliseconds();
        return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
    },
    requireLogin(nextState, replace){
        if (nextState.location.pathname != "/login") {
            Global.ajax(
                {
                    url: "system/isLogined",
                }
            ).then(
                (data) => {
                    if (data.code==0) {
                        if (nextState.location.pathname == "/") {
                            replace({pathname: '/home'});
                        }
                    } else {
                        hashHistory.push('/login');
                        window.location.reload();
                    }
                }
            );
            if (window.console) {
                console.log("--onEnter---requireLogin--pathname:" + nextState.location.pathname);
            }
        }
    },
    getHideType(){
        //获取随机隐藏动画样式
        let r = this.getRandom();
        let animate = "";
        if (r == '1') {
            animate = "rotateOutUpLeft";
        } else if (r == '2') {
            animate = "fadeOutLeft";
        } else if (r == '3') {
            animate = "fadeOutLeftBig";
        } else if (r == '4') {
            animate = "fadeOutRightBig";
        } else if (r == '5') {
            animate = "flipOutX";
        } else if (r == '6') {
            animate = "flipOutY";
        } else if (r == '7') {
            animate = "rotateOutUpLeft";
        } else if (r == '8') {
            animate = "bounceOutUp";
        } else if (r == '9') {
            animate = "rotateOut";
        } else {
            animate = "bounceOutLeft";
        }
        return animate;
    },
    goTo(path, parameter)
    {
        hashHistory.push(path);
    },
    ajaxFalse(json){
        if(json.sessionOver){
            hashHistory.push("/login");
        }
        if(console){
            console.log("ajaxFalse:"+json)
        }
    }
};
export default Global;