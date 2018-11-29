import {createStore, applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
import rootReducer from './rootReducer'

//全局唯一的store,挂在window上方便控制台查看
window.store = createStore(
    rootReducer,applyMiddleware(thunk)
);
export default window.store;
