import Global from '../../common/global'
import reqwest from 'reqwest';
const reducerName = 'header/';
//Action
export function getLoginUser() {
    return dispatch => {
        Global.ajax(
            {
                url: "system/isLogined"
            }
        ).then(
            (json) => {
                console.log("当前用户信息：" + json.data.userName);
                dispatch({type: reducerName + 'getLoginUser', nowUser: json.data})
            }
        )
    }
}

export function LoginOut() {
    return dispatch => {
        Global.ajax(
            {
                url: "system/loginOut"
            }
        ).then(
            (json) => {
                if (json.data) {
                    if(localStorage.getItem("sessionId")){
                        localStorage.removeItem("sessionId");
                        console.log("localStorage.remove")
                    }
                }
            }
        )
    }
}

export function changeFixed(fixed) {
    return {type: reducerName + 'changeFixed', fix: fixed}
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
        default:
            return state
    }
}
