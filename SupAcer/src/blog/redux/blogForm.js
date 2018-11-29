import Global from '../../common/global'
const reducerName = 'blog/';
//Action
export function addBlog(diary, callback) {
    return (dispatch, getState)=> {
        let state = getState();
        Global.ajax(
            {url: "blog/addBlog", data: diary}
        ).then(
            (data) => {
                if (data.successful) {
                    let list = Object.assign([], state.blog.blogList);
                    list.push(data.data);
                    dispatch({type: reducerName + 'addBlog', changed: {blogList: list}});
                    callback();
                }
            }
        )
    }
}

// Reducer
export function reducer(state, action) {
    switch (action.type) {
        case reducerName + 'addBlog':
            return {...state, ...action.changed, showEdit: false};
        default:
            return state
    }
}
