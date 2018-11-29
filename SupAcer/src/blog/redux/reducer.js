import initialState from './initialState';
import {reducer as blogForm} from './blogForm';
import {reducer as blogList} from './blogList';

const reducers = [
    blogForm,blogList
];

export default function reducer(state = initialState, action) {
    let newState;
    switch (action.type) {
        // Handle cross-topic actions here
        default:
            newState = state;
            break;
    }
    return reducers.reduce((s, r) => r(s, action), newState);
}
