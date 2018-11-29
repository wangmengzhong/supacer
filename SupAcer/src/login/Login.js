import React, {Component} from 'react'
import {hashHistory} from 'react-router';
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import {Form, Input, Button, Checkbox, Icon} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from './redux/actions'
import Global from '../common/global';
import './style/login.css';
// login
class Login extends Component {
    state = {
        baseUrl: Global.baseUrl,
        me: false,
        user: "wmz",
        pwd: "123",
        validate: "",
        logining: false
    };

    componentDidMount() {
        const {dispatch, selectedPost} = this.props;
        if (localStorage.getItem("cuap-login-pwd")) {
            this.setState({
                user: localStorage.getItem("cuap-login-user"),
                pwd: localStorage.getItem("cuap-login-pwd"),
                validate: "123456",
                me: true,
            }, ()=> {
                this.login();
            })
        }
        //var random = Math.floor(Math.random() * 5 + 1);
        document.body.className = "bodybg1";
        document.onkeydown = (e)=> {
            var keycode = e.keyCode ? e.keyCode : e.which;
            if (keycode == 13) {
                this.login();
            }
        }
    }

    componentWillUnmount() {
        document.body.className = "";
        document.onkeydown = undefined;
    }

    loginSucceed(o) {
        console.log(o);
    }

    glogin(data) {
        const {login}= this.props;
        login(data);
    }

    validateField() {
        var user = this.state.user;
        var pwd = this.state.pwd;
        if (user == "" || user == undefined || pwd == "" || pwd == undefined) {
            this.setState({msg: "账号或密码不能为空！"});
            return false;
        }
        return true;
    }

    handleReset() {
        this.refs.message.style.display = "none";
        this.setState({user: "", pwd: "", validate: "", me: false});
    }

    handleFieldChange(e, field) {
        var value = e.target.value;
        if (field == "me") {
            value = e.target.checked;
        }
        var param = {};
        param[field] = value;
        if (field == "user") {
            param.pwd = "";
        }
        this.setState(param);
    }

    login() {
        this.setState({
            logining: true
        });
        if (!this.validateField()) {
            this.refs.message.style.display = "block";
            this.setState({
                logining: false
            });
            return;
        } else {
            this.refs.message.style.display = "none";
        }
        if (this.state.me) {
            localStorage.setItem("cuap-login-user", this.state.user);
            localStorage.setItem("cuap-login-pwd", this.state.pwd);
        } else {
            localStorage.removeItem("cuap-login-user");
            localStorage.removeItem("cuap-login-pwd");
        }
        Global.ajax(
            {url: "system/userLogin" + "?name=" + this.state.user + "&password=" + this.state.pwd},
        ).then(
            (json)=> {
                debugger
                if (json.code == "0"&&json.data.userInfo!=null) {
                    localStorage.setItem("sessionId",json.data.sessionId);
                    hashHistory.push("/home/myBlog/list?lm=0");
                    this.loginSucceed("成功了！");
                } else {
                    this.setState({msg: "账号或密码不正确！", logining: false});
                    this.refs.message.style.display = "block";
                    this.setState({
                        logining: false
                    });
                }
            }
        )
    }

    render() {
        let one = 200;
        let two = 300;
        let three = 400;
        let hr1 = 500;
        let hr2 = 800;
        let hr3 = 1100;
        let but = 1300;
        let checkBox = 1500;
        let lock = 1700;
        return (
            <div>
                <QueueAnim delay={700} duration={600} type="top">
                    <div key="1" className="cuap-header">
                        <div className="cuap-logo">
                            <ul>
                                <QueueAnim delay={400} duration={800} type="left">
                                    <li key="1">
                                        <a href="http://localhost:9001/cuap/cpc-portal/index.html#/home"><img style={{width:40,height:40}} src={Global.imgUrl + 'hornet.png'}/></a>
                                    </li>
                                </QueueAnim>
                                <QueueAnim delay={400} duration={800} type="right">
                                    <li key="1" className="supacer">
                                        SupAcer
                                    </li>
                                </QueueAnim>
                            </ul>
                        </div>
                        <div className="cuap-dlzc">
                            <ul>

                            </ul>
                        </div>
                    </div>
                </QueueAnim>
                <QueueAnim delay={one} animConfig={{opacity:[0.8, 0],translateY:[0, -50]}} duration={800} type="top">
                    <div key="1" className="login">
                        <QueueAnim delay={one} duration={1000} type="top">
                            <div key="1" className="login-badge">
                                <span >用户登录</span>
                            </div>
                        </QueueAnim>
                        <QueueAnim delay={two} type="top">
                            <div key="1" className="login-wraper">
                                <div className="login-title">
                                    <QueueAnim delay={lock} duration={800} type="top">
                                        <Icon key="1" type="lock"/>
                                    </QueueAnim>
                                </div>
                                <div style={{height:1}}>
                                    <QueueAnim delay={hr1} type="left">
                                        <div key="1" className="hr"></div>
                                    </QueueAnim>
                                </div>
                                <div className="login-field">
                                    <div>
                                        <QueueAnim delay={three} type="left">
                                            <div key="1" style={{float:"left",paddingTop:2}}>
                                                <label>用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户:</label>
                                            </div>
                                        </QueueAnim>
                                        <QueueAnim delay={three} duration={800} animConfig={{opacity:[1, 0],translateX:[0, 100]}} type="right">
                                            <div key="1" style={{float:"left",width:"77%"}}>
                                                <Input ref="user" placeholder="请输入用户名" tabIndex="1" value={this.state.user} onChange={e=>{this.handleFieldChange(e,"user")}}/>
                                            </div>
                                        </QueueAnim>
                                    </div>
                                </div>
                                <div style={{height:1}}>
                                    <QueueAnim delay={hr2} type="right">
                                        <div key="1" className="hr"></div>
                                    </QueueAnim>
                                </div>
                                <div className="login-field">
                                    <div>
                                        <QueueAnim delay={three} type="left">
                                            <div key="1" style={{float:"left",paddingTop:2}}>
                                                <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                                            </div>
                                        </QueueAnim>
                                        <QueueAnim delay={three} duration={800} animConfig={{opacity:[1, 0],translateX:[0, 100]}} type="right">
                                            <div key="1" style={{float:"left",width:"77%"}}>
                                                <Input ref="pwd" placeholder="请输入密码" tabIndex="2" type="password" value={this.state.pwd} onChange={e=>{this.handleFieldChange(e,"pwd")}}/>
                                            </div>
                                        </QueueAnim>
                                    </div>
                                </div>
                                <div style={{height:1}}>
                                    <QueueAnim delay={hr3} type="right">
                                        <div key="1" className="hr"></div>
                                    </QueueAnim>
                                </div>
                                <div className="login-field">
                                    <QueueAnim delay={checkBox} duration={600} type="top">
                                        <Checkbox key="1" ref="me" checked={this.state.me} tabIndex="4" onChange={e=>{this.handleFieldChange(e,"me")}}>
                                            <span style={{position:"absolute",top:6,width:50}}>记住我</span>
                                        </Checkbox>
                                    </QueueAnim>
                                </div>

                                <div className="login-button">
                                    <QueueAnim delay={but} duration={800} type="right">
                                        <div key="1">
                                            <Button loading={this.state.logining} onClick={this.login.bind(this)} tabIndex="5">登录</Button>
                                        </div>
                                    </QueueAnim>
                                </div>
                                <div className="login-link">
                                    <div style={{clear:"both"}}/>
                                </div>
                            </div>
                            <div className="login-message" ref="message">
                                <div className="login-message-text">
                                    <Icon type="close-circle"/>
                                    {this.state.msg}
                                </div>
                            </div>
                        </QueueAnim>
                    </div>
                </QueueAnim>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        nowUser: state.login.nowUser
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Form.create()(Login))