import Global from '../../common/global'
import reqwest from 'reqwest';
const reducerName = 'home/';
export function getBlogList(page) {
    return (dispatch, getState) => {
        dispatch({type: reducerName + 'listLoading'});
        let pageSize = getState().blog.pageSize;
        let titleFilter = getState().blog.searchValue;

        Global.ajax(
            {
                url: "blog/getBlogList",
                data: {page: page, limit: pageSize, filter: {diaryName: titleFilter}},
            },
        ).then(
            (data) => {
                dispatch({
                    type: reducerName + 'getBlogList',
                    payload: {blogList: data.data.list, totalRecord: data.data.total, listLoading: false}
                })
            }
        );
    }
}

export function changeBlogPage(page) {
    return dispatch => {
        dispatch({type: reducerName + 'changeBlogPage', page: page});
    }
}

// Reducer
export function reducer(state, action) {
    switch (action.type) {
        case reducerName + 'listLoading':
            return {...state, listLoading: true};
        case reducerName + 'getBlogList':
            return {...state, ...action.payload};
        case reducerName + 'changeBlogPage':
            return {...state, currentPage: action.page};
        default:
            return state
    }
}
