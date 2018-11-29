import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import reqwest from 'reqwest';
import {Table, Icon, Button} from 'antd';
import QueueAnim from 'rc-queue-anim';
import * as actions from '../redux/actions';
import Global from '../../common/global';
import './styles/blogInf.less';
class BlogInf extends React.Component {
    state = {
        show: true,
        isSaved: true,
        blogInf: {},
        blogId: ""
    };

    componentWillMount() {
        let blogId = this.props.location.query.bId;
        const {changeHeadMenu} = this.props;
        changeHeadMenu({shareBlog: true});
        this.setState({
            blogId: blogId
        });
    }

    componentWillUnmount() {
        const {changeHeadMenu, changeFixed} = this.props;
        //取消顶部固定
        window.onscroll = null;
        changeFixed(false);
        //切换菜单
        changeHeadMenu({});
    }

    getBlogInf(bId) {
        Global.ajax(
            {
                url: "blog/getBlogInf",
                data: {"diaryId": bId},
            }
        ).then(
            (data) => {
                this.setState({
                    blogInf: data.data
                });
                this.refs.content.innerHTML = data.data.content;
            }
        )
    }

    componentDidMount() {
        let self = this;
        self.props.router.setRouteLeaveHook(
            self.props.route,
            self.routerWillLeave.bind(self)
        )
        this.getBlogInf(this.state.blogId);

        window.onscroll = this.onScroll.bind(this);
        //document.body.className = "bg-blogInfo";
        //document.body.className = "bg-blog";
    }

    onScroll() {
        const {changeFixed}=this.props;
        var t = window.scrollY;
        if (t > 40) {
            changeFixed(true);
        } else if (t == 0) {
            changeFixed(false);
        }
    }

    routerWillLeave(nextLocation) {
        if (!this.state.isSaved)
            return '确认要离开？';
    }

    onEnd() {
        let self = this;
    }

    render() {
        return (
            <div className="blogInf">
                <QueueAnim leaveReverse={true} type="top" onEnd={this.onEnd.bind(this)}>
                    <div key="1" className="blog_title">
                        <h2>{this.state.blogInf.diaryName}</h2>
                    </div>
                </QueueAnim>
                <div ref="content" className="blog_content">

                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        blogInf: state.blog.blogInf
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BlogInf)