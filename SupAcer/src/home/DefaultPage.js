import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Row,Col} from 'antd';
import * as actions from './redux/actions';
import HomeBlogList from './HomeBlogList'
// 配置导航
class DefaultPage extends React.Component {
    state = {};

    componentWillMount() {

    }

    render() {
        return (
            <div style={{backgroundColor:"#fff"}}>
                <Row>
                    <Col span={24}>
                        <HomeBlogList/>
                    </Col>
                    <Col span={8}>

                    </Col>
                </Row>
            </div>
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
)(DefaultPage)
