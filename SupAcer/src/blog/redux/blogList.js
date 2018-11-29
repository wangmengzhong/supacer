import Global from '../../common/global'
import reqwest from 'reqwest';
const reducerName = 'blog/';
//Action
export function updateBlog(diary, callback) {
    return dispatch => {
        Global.ajax(
            {
                url: "blog/updateBlog",
                data: diary,
            }
        ).then(
            (data) => {
                if (data.data) {
                    dispatch({type: reducerName + 'updateBlogSuccess', diary: diary});
                    callback();
                } else {
                    //更新失败
                }
            }
        )
    }
}

export function getBlogList(page, type = "") {
    return (dispatch, getState) => {
        dispatch({type: reducerName + 'listLoading'});
        let pageSize = getState().blog.pageSize;
        let titleFilter = getState().blog.searchValue;
        console.log("titleFilter:"+titleFilter)
        Global.ajax(
            {
                url: "blog/getBlogList",
                data: {page: page, limit: pageSize, diaryName: titleFilter},
            },
        ).then(
            (data) => {
                dispatch({
                    type: reducerName + 'getBlogList',
                    payload: {blogList: data.data.list, totalRecord: data.data.total, listLoading: false}
                })
            }
        )
    }
}

export function getBlogInf(bId) {
    return dispatch => {
        Global.ajax(
            {
                url: "blog/getBlogInf",
                data: {"diaryId": bId},
            }
        ).then(
            (data) => {
                dispatch({type: reducerName + 'getBlogInf', blogInf: data.data});
            }
        )
    }
}

export function showInf(blogInf) {
    return dispatch => {
        dispatch({type: reducerName + 'showInf', blogInf: blogInf});
        Global.goTo("/home/blogInf?bId=" + blogInf.diaryId);
    }
}

export function showAdd() {
    return dispatch => {
        dispatch({type: reducerName + 'showAdd'});
    }
}

export function editInf(blogInf) {
    return dispatch => {
        Global.ajax(
            {
                url: "blog/getBlogInf",
                data: {"diaryId": blogInf.diaryId},
            }
        ).then(
            (json)=> {
                dispatch({type: reducerName + 'editInf', blogInf: json.data});
            }
        );
    }
}

export function hideEdit() {
    return dispatch => {
        dispatch({type: reducerName + 'hideEdit'});
    }
}

export function changePage(page) {
    return dispatch => {
        dispatch({type: reducerName + 'changePage', page: page});
    }
}

export function onSelectChange(selectedRowKeys) {
    return dispatch => {
        dispatch({type: reducerName + 'blogListSelectedChange', selectedRowKeys: selectedRowKeys});
    }
}

export function onSearchValueChange(value) {
    return dispatch => {
        dispatch({type: reducerName + 'onSearchValueChange', payload: {searchValue: value}});
    }
}


export function deleteBlog(selectedRowKeys) {
    return (dispatch, getState) => {
        reqwest({
            url: Global.baseUrl + "diary/delBlog.action",
            method: 'post',
            data: JSON.stringify(selectedRowKeys),
            contentType: 'application/json',
            withCredentials: true,
        }).then((data) => {
            if (data.successful) {
                let state = getState();
                let list = Object.assign([], state.blog.blogList);
                for (let j = 0; j < selectedRowKeys.length; j++) {
                    let child = selectedRowKeys[j];
                    list = list.filter(i=>i.diaryId != child);
                }
                dispatch({type: reducerName + 'deleteBlogSuccess', blogList: list})
            }
        }).catch(function (err) {
            console.log(err);
        });
    }
}

// Reducer
export function reducer(state, action) {
    switch (action.type) {
        case reducerName + 'listLoading':
            return {...state, listLoading: true};
        case reducerName + 'getBlogList':
            return {...state, ...action.payload};
        case reducerName + 'showInf':
            return {...state, blogInf: action.blogInf};
        case reducerName + 'editInf':
            return {...state, showEdit: true, formType: "edit", editBlogInf: action.blogInf};
        case reducerName + 'hideEdit':
            return {...state, showEdit: false};
        case reducerName + 'showAdd':
            return {...state, showEdit: true, formType: "add", editBlogInf: {}};
        case reducerName + 'getBlogInf':
            return {...state, blogInf: action.blogInf};
        case reducerName + 'changePage':
            return {...state, currentPage: action.page};
        case reducerName + 'blogListSelectedChange':
            return {...state, ...action.selectedRowKeys};
        case reducerName + 'deleteBlogSuccess':
            return {...state, selectedRowKeys: [], blogList: action.blogList};
        case reducerName + 'onSearchValueChange':
            return {...state, ...action.payload};
        case reducerName + 'updateBlogSuccess':
            let oldList = Object.assign([], state.blogList);
            let newBlog = action.diary;
            let newList = [];
            for (let i = 0; i < oldList.length; i++) {
                let item = oldList[i];
                if (item.diaryId == newBlog.diaryId) {
                    item.diaryName = newBlog.diaryName;
                    item.content = newBlog.content;
                    item.subhead = newBlog.subhead;
                    newList.push(item);
                } else {
                    newList.push(item);
                }
            }
            return {...state, showEdit: false, blogList: newList};
        default:
            return state
    }
}
