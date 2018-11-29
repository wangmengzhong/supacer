import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Table, Pagination, Icon} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from './redux/actions';
import Global from '../common/global';
class HomeBlogList extends React.Component {
    state = {};

    componentWillMount() {
        const {getBlogList, changeHeadMenu, currentPage} = this.props;
        changeHeadMenu({});
        getBlogList(currentPage, "all");
    }

    componentDidMount() {
        /* Global.ajax(
         {url: "diary/testPAjax.action"},
         ()=> {
         console.log("ajax-false");
         }
         ).then(
         (data) => {
         this.test1(data);
         Global.ajax(
         {url: "diary/testPAjax2.action"}
         ).then(
         (data2)=> {
         this.test1(data2);
         }
         );
         }
         ).catch(
         (err)=> {
         console.log("请求失败！");
         //console.log(err);
         }
         ).finally(
         ()=> {
         console.log("finally")
         }
         )*/
    }

    test1(data) {
        console.log(data);
    }

    componentWillUnmount() {
        const {changeHeadMenu} = this.props;
        changeHeadMenu({});

    }

    showInf(value) {
    }

    onPageChange(page) {
        const {changeBlogPage, getBlogList}=this.props;
        changeBlogPage(page);
        getBlogList(page, "all");
    }

    render() {
        const {blogList} = this.props;
        const columns = [{
            title: '标题',
            width: '54%',
            dataIndex: 'diaryName',
            key: 'iaryName',
            render: (text, record, index) => {
                return (<div onClick={()=>this.showInf(record)} >{text}</div>)
            }
        }, {
            title: '作者',
            width: '20%',
            dataIndex: 'createdName',
            key: 'createdName',
            render: (text, record, index) => {
                return (<div >{text}</div>)
            }
        }, {
            title: '时间',
            width: '18%',
            dataIndex: 'createdDt',
            key: 'createdDt',
            render: (text, record, index) => {
                return (<div >{Global.timeStamp2String(text)}</div>)
            }
        }];

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

        const blogs = [];
        for (let i = 0; i < blogList.length; i++) {
            blogs.push(
                <li>

                </li>
            );
        }

        return (
            <QueueAnim delay={600} duration={1000} leaveReverse={true}>
                <div key="demo1">
                    <div style={{borderBottom: "1px solid #e9e9e9"}}>
                        <Table
                            columns={columns}
                            rowKey="diaryId"
                            dataSource={blogList}
                            pagination={false}
                            bordered
                            size="middle"
                            loading={this.props.listLoading}
                        />
                    </div>
                    <div style={{height:40,paddingRight:10,marginTop:10,paddingBottom:3}}>
                        <div style={{float:"right"}}>
                            <Pagination
                                defaultCurrent={1}
                                pageSize={this.props.pageSize}
                                current={this.props.currentPage}
                                onChange={this.onPageChange.bind(this)}
                                total={this.props.totalRecord}/>
                        </div>
                    </div>
                </div>
            </QueueAnim>
        )
    }
}

function

mapStateToProps(state) {
    return {
        blogList: state.home.blogList,
        currentPage: state.home.currentPage,
        totalRecord: state.home.totalRecord,
        pageSize: state.home.pageSize,
        listLoading: state.home.listLoading
    }
}

function

mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export
default

connect(mapStateToProps,
    mapDispatchToProps)

(
    HomeBlogList
)