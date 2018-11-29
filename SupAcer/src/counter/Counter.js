import React, {Component} from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import * as actions from './redux/actions'
import PropTypes from 'prop-types'
import memobind from 'memobind'
import {hashHistory, Router} from 'react-router'
class Counter extends Component {
    state = {
        count: 2,
    };

    add(e) {
        console.log(e);
        this.setState({
            count: this.state.count + 1,
        })
    }

    login() {
        hashHistory.push("/login");
    }

    render() {
        const {value, increment, increment2} = this.props;
        return (
            <div>
                <div>
                    <span>{value}</span>{' '}{this.state.count}{' '}
                    <button onClick={increment}>Increase</button>
                    {' '}
                    <button onClick={increment2}>Increase2</button>
                    {' '}
                    <button onClick={memobind(this, 'add')}>add</button>
                    <button onClick={memobind(this, 'login')}>login</button>
                </div>
                <div>
                    {this.props.children}
                </div>
            </div>

        )
    }
}

Counter.propTypes = {
    value: PropTypes.number.isRequired,
    increment: PropTypes.func.isRequired
};


// Map Redux state to component props
function mapStateToProps(state) {
    return {
        value: state.counter.count
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
)(Counter)
