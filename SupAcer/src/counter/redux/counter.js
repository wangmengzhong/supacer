import {INCREASE} from './constants'

//Action
export function increment() {
    return {
        type: INCREASE
    }
}

export function increment2() {
    return dispatch => {
        setTimeout(() => {
            dispatch(increment())
        }, 1000)
    }
}

// Reducer
export function reducer(state , action) {
    const count = state.count;
    switch (action.type) {
        case INCREASE:
            return { count: count + 1 };
        default:
            return state
    }
}