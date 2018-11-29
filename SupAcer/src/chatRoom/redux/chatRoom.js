import Global from '../../common/global'
import reqwest from 'reqwest';
const reducerName = 'chatRoom/';
//Action
export function showChatRoom() {
    return dispatch => {
        dispatch({
            type: reducerName + 'changeStore', payload: {
                show: true
            }
        })
    }
}

export function closeChatRoom() {
    return dispatch => {
        dispatch({
            type: reducerName + 'changeStore', payload: {
                show: false
            }
        })
    }
}

export function sendMessage(message) {
    return (dispatch, getState) => {
        let state = getState();
        let histories = Object.assign([], state.chatRoom.histories);
        debugger;
        histories.push(message)
        dispatch({
            type: reducerName + 'changeStore', payload: {
                histories: histories,
                message:""
            }
        })
    }
}

export function onMsgChange(message) {
    return {
        type: reducerName + 'changeStore', payload: {
            message: message
        }
    }
}

export function changeHeadMenu(menu) {
    /* 调用另一个reducer type不要加reducerName */
    return dispatch => {
        dispatch({type: reducerName + 'changeHeadMenu', headMenu: menu});
    }
}

// Reducer
export function reducer(state, action) {
    const nowUser = state.nowUser;
    switch (action.type) {
        case reducerName + 'getLoginUser':
            return {...state, nowUser: action.nowUser};
        case reducerName + 'changeHeadMenu':
            return {...state, headMenu: action.headMenu};
        case reducerName + 'changeFixed':
            return {...state, fixed: action.fix};
        case reducerName + 'changeStore':
            return {...state, ...action.payload};
        default:
            return state
    }
}
