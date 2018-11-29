import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Table, Pagination, Icon} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from './redux/actions';
import Global from '../common/global';
import './styles/blog.less';
import BlogForm from './BlogForm';
class BlogList extends React.Component {
    state = {
        show: true,
        isSaved: true,
    }

    componentWillMount() {
        const {getBlogList, changeHeadMenu, currentPage} = this.props;
        changeHeadMenu({addBlog: true});
        getBlogList(currentPage);
    }

    componentDidMount() {
        this.props.router.setRouteLeaveHook(
            this.props.route,
            this.routerWillLeave.bind(this)
        )
        //document.body.className = "bg-blog";
    }

    componentWillUnmount() {
        const {changeHeadMenu} = this.props;
        changeHeadMenu({});
    }

    routerWillLeave(nextLocation) {
        if (!this.state.isSaved)
            return '确认要离开？';
    }

    showInf(value) {
        const {showInf}=this.props;
        showInf(value);
    }

    editInf(value) {
        const {editInf}=this.props;
        editInf(value);
    }

    // checkbox状态
    onSelectChange(selectedRowKeys) {
        const {onSelectChange}=this.props;
        onSelectChange({selectedRowKeys})
    }

    onPageChange(page) {
        console.log(page);
        const {changePage, getBlogList}=this.props;
        changePage(page);
        getBlogList(page);
    }

    showAdd() {
        console.log("showAdd");
        const {showAdd}=this.props;
        showAdd();
    }

    render() {
        const {blogList} = this.props;
        const columns = [{
            title: '标题',
            width: '54%',
            dataIndex: 'diaryName',
            key: 'iaryName',
            render: (text, record, index) => {
                return (<div onClick={()=>this.showInf(record)} className="table_td">{text}</div>)
            }
        }, {
            title: '作者',
            width: '20%',
            dataIndex: 'createdName',
            key: 'createdName',
            render: (text, record, index) => {
                return (<div className="table_td">{text}</div>)
            }
        }, {
            title: '时间',
            width: '18%',
            dataIndex: 'createdDt',
            key: 'createdDt',
            render: (text, record, index) => {
                return (<div className="table_td">{Global.timeStamp2String(text)}</div>)
            }
        }, {
            title: '操作',
            width: '8%',
            dataIndex: 'operate',
            render: (text, record, index) => {
                return (<div><a onClick={()=>this.editInf(record)}>编辑</a></div>)
            }
        }];

        const {selectedRowKeys} = this.props;
        //console.log(selectedRowKeys);
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange.bind(this)
        };

        const pagination = {
            total: this.props.blogList.length,
            showSizeChanger: true,
            onShowSizeChange(current, pageSize) {
                console.log('Current: ', current, '; PageSize: ', pageSize)
            },
            onChange(current) {
                console.log('Current: ', current)
            }
        };

        return (
            <QueueAnim delay={0} duration={800} leaveReverse={true}>
                <div  key="demo1">
                    <div style={{borderBottom: "1px solid #e9e9e9"}}>
                        <Table rowSelection={rowSelection}
                               columns={columns}
                               rowKey="diaryId"
                               dataSource={blogList}
                               pagination={false}
                               bordered
                               size="large"
                               loading={this.props.listLoading}
                               expandedRowRender={record =>
                               <p style={{ marginLeft: 64 }}>
                                    {record.subhead}
                               </p>
                               }
                        />
                    </div>
                    <div style={{height:40,paddingRight:10,marginTop:10,paddingBottom:3}}>
                        <div style={{float:"right"}}>
                            <Pagination defaultCurrent={1} pageSize={this.props.pageSize} current={this.props.currentPage} onChange={this.onPageChange.bind(this)} total={this.props.totalRecord}/>
                        </div>
                    </div>
                </div>
                <BlogForm/>
            </QueueAnim>
        )
    }
}

function mapStateToProps(state) {
    return {
        blogList: state.blog.blogList,
        editBlogInf: state.blog.editBlogInf,
        currentPage: state.blog.currentPage,
        selectedRowKeys: state.blog.selectedRowKeys,
        totalRecord: state.blog.totalRecord,
        pageSize: state.blog.pageSize,
        listLoading: state.blog.listLoading
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BlogList)