import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import * as actions from './redux/actions';
import Header from '../header/Header'
import  './styles/home.less';
import  '../../src/styles/animate.css';
// 配置导航
class Home extends React.Component {
    state = {};

    componentWillMount() {
        this.p().then(
            (data) => {
                console.log(data)
            }
        ).catch(
            (err)=>{
                console.log("出错了")
            }
        )
    }
    p(){
        return new Promise(function (resolve) {
            console.log("Promise");
            resolve("success")
        });
    }

    render() {
        return (
            <div>
                <Header/>
                <div className="homeCon">
                    {this.props.children}
                </div>
            </div>
        )
    }
}

// Map Redux state to component props
function mapStateToProps(state) {
    return {
        nowUser: state.home.nowUser
    }
}

//将action的所有方法绑定到props上
function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

// Connected Component
export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Home)
