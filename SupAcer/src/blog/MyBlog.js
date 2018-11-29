import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Row, Col} from 'antd';
import * as actions from './redux/actions';
// 配置导航
class MyBlog extends React.Component {
    state = {
        current: '',
        username: ''
    };

    componentWillMount() {

    }

    componentDidMount() {

    }

    componentWillUnmount() {
    }

    handleClick(e) {
        this.setState({
            current: e.key
        });
    }

    render() {
        return (
            <div>
                <div className="con_center">
                    <div style={{width:"100%",backgroundColor:"#fff",opacity:"0.85"}}>
                        <Row >
                            <Col span={24}>
                                {React.cloneElement(this.props.children, {
                                    key: this.props.location.pathname
                                })}
                            </Col>
                        </Row>
                    </div>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        editBlogInf: state.blog.editBlogInf,
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(MyBlog)
