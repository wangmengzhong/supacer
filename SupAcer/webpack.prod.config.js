'use strict';
var ExtractTextPlugin = require("extract-text-webpack-plugin");  //css单独打包
var CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin");
var webpack = require("webpack");
let commonExtractLESS = new ExtractTextPlugin('main.css');
let extractLESS = new ExtractTextPlugin('my.css');
var path = require('path');
module.exports = {
    //devtool: 'eval-source-map',

    entry: {
        main: './src/index.js', //唯一入口文件
        common: ['react', 'react-dom', 'reqwest']
    },
    output: {
        path: path.join(__dirname, './build'),
        filename: '[name].js', //打包后输出文件的文件名
        chunkFilename: '[name].js',
        publicPath: '/zull/'  //启动本地服务后的根目录
    },

    module: {
        loaders: [
            {test: /\.json$/,loader: 'json'},
            {test: /\.js$/, loader: "jsx!babel", include: /src/},
            {test: /\.css$/, loader: commonExtractLESS.extract("style", "css!postcss")},
            {
                test: /\.less$/,
                include: /node_modules/,
                loader: commonExtractLESS.extract("style-loader", "css-loader!less-loader")
            },
            {
                test: /\.less$/,
                include: /src/,
                loader: extractLESS.extract("style-loader", "css-loader!less-loader")
            },
            {test: /\.(png|jpg|gif|ico)$/, include: /sup/,loader: 'url-loader?limit=8192&name=images/[hash:8].[name].[ext]'},
            {test: /\.woff|\.woff2|\.svg|.eot|\.ttf/, loader: 'url?prefix=font/&limit>10'}
        ]
    },

    babel: {
        presets: ['es2015', 'stage-0', 'react'],
        plugins: ['transform-runtime','transform-object-assign', ['import', {
            libraryName: 'antd',
            style: true
        }]]
    },

    postcss: [
        require('autoprefixer')    //调用autoprefixer插件,css3自动补全
    ],

    devServer: {
        // contentBase: './src/views'  //本地服务器所加载的页面所在的目录
        port: 8989,
        colors: true,  //终端中输出结果为彩色
        historyApiFallback: true,  //不跳转
        inline: true,  //实时刷新
        proxy: {
            "/web/*": {
                target: "http://47.93.33.74:8765",
                secure: false,
                changeOrigin: true
            },
        }
    },

    plugins: [
        extractLESS,
        commonExtractLESS,
        new CommonsChunkPlugin({
            name: 'common',
            filename: 'common.js'
        }),
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            // 最紧凑的输出
            beautify: false,
            // 删除所有的注释
            comments: false,
            compress: {
                // 在UglifyJs删除没有用到的代码时不输出警告
                warnings: false,
                // 删除所有的 `console` 语句
                // 还可以兼容ie浏览器
                drop_console: true,
                // 内嵌定义了但是只用到一次的变量
                collapse_vars: true,
                // 提取出出现多次但是没有定义成变量去引用的静态值
                reduce_vars: true,
            }
        })
    ]

};