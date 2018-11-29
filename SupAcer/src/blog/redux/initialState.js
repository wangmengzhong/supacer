const initialState = {
    blogList:[],
    listLoading:false,
    totalRecord:0,
    pageSize:8,
    currentBlog:{},
    blogInf:{},
    editBlogInf:{},
    showEdit:false,
    formType:"add",//add edit,
    currentPage:1,
    selectedRowKeys: [],
    searchValue:""
};

export default initialState;
