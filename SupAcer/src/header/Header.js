'use strict';
import React from 'react';
import {hashHistory} from 'react-router';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Icon, message} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from './redux/actions';
import Global from '../common/global';
import Catalog from './catalog';
import ChatRoom from '../chatRoom/ChatRoom';
import HeadMenu from './HeadMenu';
import './styles/header.less';

// 配置导航
class Header extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            current: '',
            nowUser: {},
            catalogDisplay: false,
            chatRoomDisplay: false
        };
        this.closeCatalog = this.closeCatalog.bind(this);
        this.onReceive = this.onReceive.bind(this);
    }

    componentWillMount() {
        console.log("Header:componentWillMount");
        const {getLoginUser}=this.props;
        getLoginUser();
        /*reqwest({
         url:this.state.baseUrl + "system/getloginUser.action",
         method: 'post',
         data: JSON.stringify({}),
         contentType: 'application/json',
         withCredentials: true,
         }).then((data) => {
         if (data.success) {
         console.log(data.resultValue.username);
         this.setState({
         nowUser: data.resultValue
         });
         } else {
         this.setState({
         nowUser: {}
         });
         }
         }).catch(function(err){
         message.error("请求失败！");
         });


         this.setState({
         current: routeMap[route] || '0'
         });

         if(window.myws){
         if(window.myws.readyState=='OPEN'){
         return;
         }
         }
         this.connWebsocket(this);*/

    }

    componentDidMount() {

    }

    connWebsocket(self) {
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
            self.onReceive(e);
        };
    }

    onReceive(e) {
        //转成json
        console.log("收到：" + e.data);
    }

    handleClick(e) {
        this.setState({
            current: e.key
        });
    }

    showAddBlog() {
        const {showAdd}=this.props;
        showAdd();
    }

    showCatalog() {
        this.refs.catalog.show();
    }

    showChatRoom() {
        const {showChatRoom}=this.props;
        showChatRoom()
    }

    closeCatalog() {
        const {closeChatRoom}=this.props;
        closeChatRoom()
    }

    closeChatRoom() {
        this.setState({
            chatRoomDisplay: false
        });
    }

    loginOut() {
        localStorage.removeItem("cuap-login-user");
        localStorage.removeItem("cuap-login-pwd");
        hashHistory.push("/login");
        const {LoginOut}=this.props;
        LoginOut();
    }

    render() {
        const {fixed}=this.props;
        let fixStyle = fixed ? "nav animated fadeInDown" : "";
        return (
            <div className={fixStyle}>
                <div className="headerMenuConWrapper">
                    <div className="headerMenuCon">
                        <QueueAnim type="top" leaveReverse={true}>
                            <div key="d1" className="h_left">
                                <p onClick={()=>Global.goTo("/home")} style={{cursor:"pointer",fontSize:18,fontWeight:"bold",paddingTop:6,color:"#d9d9d9"}}>SupAcer</p>
                            </div>
                            <div key="d2" className="h_right">
                                <ul>
                                    <li style={{color:"#d9d9d9"}}>{this.props.nowUser.userName ? this.props.nowUser.userName : <a>登录</a>}</li>
                                    <li>
                                        <div style={{height:40}}>
                                            <a onClick={this.loginOut.bind(this)}>
                                                <Icon style={{fontSize:20,color:"#f50"}} type="close-square-o" className="headerIcon"/>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div key="d3" style={{float:"left",width:40,marginLeft:20}}>
                                <Icon style={{marginLeft:10,padding:3}} onClick={this.showCatalog.bind(this)} type="appstore" className="headerIcon"/>
                            </div>
                            <div key="d4" style={{float:"left",width:40}}>
                                <Icon style={{marginLeft:10,padding:3}} onClick={this.showChatRoom.bind(this)} type="message" className="headerIcon"/>
                            </div>
                        </QueueAnim>
                        <div>
                            <HeadMenu/>
                        </div>
                    </div>
                </div>
                <Catalog
                    ref="catalog"
                    display={this.state.catalogDisplay}
                    close={this.closeCatalog}
                />
                <ChatRoom />
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        nowUser: state.header.nowUser,
        headMenu: state.header.headMenu,
        fixed: state.header.fixed
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Header)
