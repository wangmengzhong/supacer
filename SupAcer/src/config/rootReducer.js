import { combineReducers } from 'redux'
import { routerReducer } from 'react-router-redux'
import counter from '../counter/redux/reducer'
import login from '../login/redux/reducer'
import home from '../home/redux/reducer'
import header from '../header/redux/reducer'
import blog from '../blog/redux/reducer'
import chatRoom from '../chatRoom/redux/reducer'
const rootReducer = combineReducers({
  routing: routerReducer,
  counter:counter,
  login:login,
  home:home,
  header:header,
  blog:blog,
  chatRoom:chatRoom
});

export default rootReducer;
