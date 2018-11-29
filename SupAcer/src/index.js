import React from 'react'
import ReactDom from 'react-dom';
import { Provider } from 'react-redux'
import { hashHistory } from 'react-router'
import { syncHistoryWithStore } from 'react-router-redux'
import RootRoute from './config/rootRoute'
import store from './config/configStore'
import 'babel-polyfill'
const history = syncHistoryWithStore(hashHistory, store);
ReactDom.render(
    <Provider store={store}>
        <RootRoute history={history}/>
    </Provider>,
    document.getElementById('app')
);
