module.exports = {
    path: 'home',
    indexRoute:{
        getComponent(nextState, cb) {
            require.ensure([], (require) => {
                cb(null, require('./DefaultPage').default)
            }, 'home/index')
        }
    },
    getComponent(nextState, cb) {
        require.ensure([], (require) => {
            cb(null, require('./Home').default)
        }, 'home/home')
    },
    childRoutes: [
        require('../blog/router'),
        require('../blog/blogInf/router')
    ],
};
