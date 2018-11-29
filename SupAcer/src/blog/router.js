module.exports = {
    path: 'myBlog',
    getComponent(nextState, cb) {
        require.ensure([], (require) => {
            cb(null, require('./MyBlog').default)
        }, 'modules/blog/myBlog')
    },
    childRoutes: [
        {
            path: 'list',
            getComponent(nextState, cb) {
                require.ensure([], (require) => {
                    cb(null, require('./BlogList').default)
                }, 'modules/blog/list')
            },
        }
    ]
}
