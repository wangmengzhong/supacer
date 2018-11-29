import React from 'react'
import {Router} from 'react-router'
import Global from '../common/global'
const ReactCreateClass =require('create-react-class');
const RootRoute =ReactCreateClass({
    getInitialState() {
        return {
          
        };
    },

    render() {
        const rootRoute =
            {
                path: '/',
                onEnter: Global.requireLogin,
                childRoutes: [
                    require('../login/router'),
                    require('../home/router')
                ]
            };
        return (
            <Router history={this.props.history} routes={rootRoute}/>
        )
    }
})

export default RootRoute;
