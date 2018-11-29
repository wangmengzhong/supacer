import initialState from './initialState';
import {reducer as counter} from './counter';

const reducers = [
  counter,
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
