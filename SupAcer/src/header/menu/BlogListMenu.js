'use strict';
import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Icon, message, Popconfirm, Input} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from '../redux/actions';
const Search = Input.Search;

class BlogListMenu extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {

    }

    showAddBlog() {
        const {showAdd}=this.props;
        showAdd();
    }

    deleteBlog() {
        const {deleteBlog, selectedRowKeys}=this.props;
        if (selectedRowKeys.length > 0) {
            deleteBlog(selectedRowKeys);
        } else {
            message.warning("没有选择数据！");
        }
    }

    onSearch(value){
        const {getBlogList}=this.props;
        getBlogList(1,"my")
    }
    onSearchValueChange(e){
        const {onSearchValueChange}=this.props;
        onSearchValueChange(e.target.value);
    }
    render() {
        return (
            <ul>
                <li>
                    <QueueAnim type="top" delay={300}>
                        <div key="1">
                            <Icon onClick={this.showAddBlog.bind(this)} className="headerIcon" type="plus-square"/>
                        </div>
                    </QueueAnim>
                </li>
                <li>
                    <QueueAnim type="top" delay={500}>
                        <div key="1">
                            <Popconfirm title="确定要删除吗？" onConfirm={() => this.deleteBlog()}>
                                <Icon className="headerIcon" type="minus-square"/>
                            </Popconfirm>
                        </div>
                    </QueueAnim>
                </li>
                <li>
                    <QueueAnim type="top" delay={700}>
                        <div key="1">
                            <Search
                                size="small"
                                placeholder="input search text"
                                style={{ width: 250,marginTop:7}}
                                value={this.props.searchValue}
                                onSearch={value => this.onSearch(value)}
                                onChange={this.onSearchValueChange.bind(this)}
                            />
                        </div>
                    </QueueAnim>
                </li>
            </ul>
        )
    }
}

function mapStateToProps(state) {
    return {
        selectedRowKeys: state.blog.selectedRowKeys,
        searchValue: state.blog.searchValue,
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BlogListMenu)
