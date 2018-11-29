import React from 'react';
import {Input, Button, Icon, Row, Col, message} from 'antd';
import reqwest from 'reqwest';
import Global from '../common/global';
import  './styles/chatRoom.less';
const ChatRoom = React.createClass({
    getInitialState() {
        return {
            menus: [],
            message: "",
            history: [],
            friends: []
        };
    },
    componentWillMount() {
        //this.getFriends();
    },
    componentDidMount() {
        let url = "ws://" + Global.wsUrl + "webSocketServer";
        window.myws = new WebSocket(url);
        window.myws.onerror = function (event) {
            console.log("出错了！");
        };
        window.myws.onopen = function (event) {
            console.log("Opened");
        };
        window.myws.onmessage = function (e) {
            console.log("Opened");
            console.log("收到：" + e.data);
        };
    },
    onReceive(e){
        //转成json
        console.log("收到：" + e.data);
    },
    getFriends(){
        //获取好友列表
        reqwest({
            url: Global.baseUrl + "system/userlist.action",
            method: 'post',
            data: JSON.stringify({}),
            contentType: 'application/json',
            withCredentials: true,
        }).then((data) => {
            if (data.successful) {
                this.setState({
                    friends: data.items
                });
            }
        }).catch(function (err) {
            message.error("请求失败！");
        });
    },
    hide(e){
        e.stopPropagation();
        let self = this;
        this.refs.modal.className = "chatRoom_modal my_animated bounceOut";
        setTimeout(function () {
            self.refs.box.className = "box-mask_hidden";
        }, 700)
    },
    show(){
        this.refs.box.className = "box-mask";
        this.refs.modal.className = "chatRoom_modal animated bounceInDown";
    },
    close(e){
        e.stopPropagation();
        let self = this;
        this.refs.modal.className = "chatRoom_modal my_animated bounceOutUp";
        setTimeout(function () {
            self.refs.box.className = "box-mask_hidden";
        }, 700)
    },
    shake(){
        let self = this;
        this.refs.modal.className = "chatRoom_modal animated swing";
        setTimeout(function () {
            self.refs.modal.className = "chatRoom_modal animated";
        }, 1000)
    },
    onChange(e){
        this.setState({message: e.target.value});
    },
    onMessage(msg){

    },
    send(){
        let message = this.state.message;
        window.myws.send(message);
        let msg = {userName: "wmz", content: message, type: "send", dateTime: "11:20"};
        let his = this.state.history;
        debugger;
        his.push(msg);
        //this.setState({message: "", history: his});
    },
    render(){
        let history = [];
        for (let i = 0; i < this.state.history.length; i++) {
            let item = this.state.history[i];
            if (item.type == "send") {
                history.push(
                    <div key={i} style={{clear:"both",height:45,marginTop:10}}>
                        <div style={{float:"left"}}>
                            <div style={{float:"left"}}><img style={{width:35,height:35}} src={Global.imgUrl+"face.jpg"}/></div>
                            <div
                                style={{borderRadius:"4px",float:"left",height:35,border:"solid 1px",fontSize:15,marginLeft:10,lineHeight:"22px",padding:5,background:"#49eed4"}}>
                                {item.content}
                            </div>
                        </div>
                    </div>
                )
            } else if (item.type == "receive") {
                history.push(
                    <div key={i} style={{clear:"both",height:45,marginTop:10}}>
                        <div style={{float:"left"}}>
                            <div
                                style={{borderRadius:"4px",float:"left",height:35,border:"solid 1px",fontSize:15,marginLeft:10,lineHeight:"22px",padding:5,background:"#49eed4"}}>
                                {item.content}
                            </div>
                            <div style={{float:"left"}}><img style={{width:35,height:35}} src={Global.imgUrl+"images/face.jpg"}/></div>
                        </div>
                    </div>
                )
            }
        }
        return (
            <div className="box-mask_hidden" ref="box">
                <div className="chatRoom_modal" ref="modal">
                    <div style={{width:"100%",height:29,background:"rgba(16, 233, 129, 0.76)", borderRadius: "5px 5px 0px 0px"}}>
                        <Icon type="close-square" onClick={this.close} className="chatRoom_close"/>
                    </div>
                    <div>

                        <Row className="chat_con">
                            <Row>
                                <div style={{width:800,margin:"0 auto"}}>
                                    <Col span={16} style={{minHeight:300}}>
                                        <div style={{width:"100%",height:300,padding:5}}>
                                            {history}
                                        </div>

                                        <div style={{width:"100%",height:30}}>
                                            his
                                        </div>

                                        <div style={{width:"100%",paddingRight:10}}>
                                            <Input
                                                type="textarea"
                                                value={this.state.message}
                                                onChange={this.onChange}
                                                style={{width:"100%",backgroundColor:"rgba(225,225,225,0.2)",color:"#fff"}}
                                            />
                                        </div>

                                        <div style={{width:"100%",marginTop:5}}>
                                            <Button style={{width:"20%",float:"right",marginRight:10}} ghost type="primary" onClick={this.send}>确定</Button>
                                        </div>
                                    </Col>
                                    <Col span={8} style={{minHeight:422,backgroundColor:"#ccc"}}>

                                    </Col>
                                </div>
                            </Row>
                        </Row>

                    </div>
                </div>
            </div>
        );
    }
});

export default ChatRoom;