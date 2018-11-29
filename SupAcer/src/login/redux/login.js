//Action
export function login(name, psw) {
    return dispatch => {
        dispatch({type: 'onLogin'});
    }
}

// Reducer
export function reducer(state, action) {
    const nowUser = state.nowUser;
    switch (action.type) {
        case 'loginSuccess':
            return {...state, nowUser: {name: 'wmz'}};
        case'onLogin':
            return {...state, nowUser: {name: 'Loading'}};
        default:
            return state
    }
}
