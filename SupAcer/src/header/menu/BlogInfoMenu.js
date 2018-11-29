'use strict';
import React from 'react';
import { hashHistory } from 'react-router';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Icon, message, Popconfirm} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from '../redux/actions';

class BlogInfoMenu extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {

    }

    showAddBlog() {
        const {showAdd}=this.props;
        showAdd();
    }

    rollback() {
        hashHistory.go(-1)
    }

    render() {
        return (
            <ul>
                <li>
                    <QueueAnim type="top" delay={300}>
                        <div key="1">
                            <Icon onClick={this.showAddBlog.bind(this)} className="headerIcon" type="star-o"/>
                        </div>
                    </QueueAnim>
                </li>
                <li>
                    <QueueAnim type="top" delay={500}>
                        <div key="1">
                            <Icon onClick={this.showAddBlog.bind(this)} className="headerIcon" type="download"/>
                        </div>
                    </QueueAnim>
                </li>
                <li>
                    <QueueAnim type="top" delay={500}>
                        <div key="1">
                            <Icon onClick={this.rollback.bind(this)} className="headerIcon" type="rollback"/>
                        </div>
                    </QueueAnim>
                </li>
            </ul>
        )
    }
}

function mapStateToProps(state) {
    return {}
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BlogInfoMenu)
