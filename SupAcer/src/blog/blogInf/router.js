module.exports = {
    path: 'blogInf',
    getComponent(nextState, cb) {
        require.ensure([], (require) => {
            cb(null, require('./BlogInf').default)
        }, 'modules/blog/blogInf')
    }
}
