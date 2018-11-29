'use strict';
import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import QueueAnim from 'rc-queue-anim';
import * as actions from './redux/actions';
import BlogListMenu from './menu/BlogListMenu';
import BlogInfoMenu from './menu/BlogInfoMenu';

class HeadMenu extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <QueueAnim type="top" delay={200}>
                    <div className="blog_bar">
                        {this.props.headMenu.addBlog ?
                            <BlogListMenu/>
                            : null}
                        {this.props.headMenu.shareBlog ?
                            <BlogInfoMenu/>
                            : null}
                    </div>
                </QueueAnim>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        headMenu: state.header.headMenu
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(HeadMenu)
