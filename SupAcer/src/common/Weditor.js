/**
 * Created by Administrator on 2017/4/21 0021.
 */
import React from 'react';
import Global from './global'
var wangEditor = require('wangeditor');

const Weditor = React.createClass({
    getInitialState() {
        return {
            editor: {},
            html: "",
            bId: ""
        }
    },
    componentDidMount(){
        this.state.editor = new wangEditor('wEditor');

        // 上传图片（举例）
        this.state.editor.config.uploadImgUrl = Global.baseUrl + 'rest/blog/uploadImages';

        // 配置自定义参数（举例）
        this.state.editor.config.uploadParams = {
            token: 'abcdefg',
            user: 'wangfupeng1988',
            sessionId: localStorage.getItem("sessionId")
        };
        this.state.editor.config.uploadImgFileName = 'file';

        // 设置 headers（举例）
        this.state.editor.config.uploadHeaders = {
        };

        this.state.editor.config.menus = [
            'source',
            '|',
            'bold',
            'underline',
            'italic',
            'strikethrough',
            'eraser',
            'forecolor',
            'bgcolor',
            '|',
            'quote',
            'fontfamily',
            'fontsize',
            'head',
            'unorderlist',
            'orderlist',
            'alignleft',
            'aligncenter',
            'alignright',
            '|',
            'link',
            'unlink',
            'table',
            'emotion',
            '|',
            'img',
            'video',
            'insertcode',
            '|',
            'undo',
            'redo',
            'fullscreen'
        ];
        this.state.editor.create();
        if (this.props.blog) {
            if (this.props.type == "edit") {
                this.setState(
                    {
                        bId: this.props.blog.diaryId
                    }
                )
                this.state.editor.$txt.html(this.props.blog.content);
            }
        }
    },
    componentWillMount() {

    },
    componentWillUnmount() {
        console.log("weditor 销毁");
        this.state.editor.destroy();
        let cont = this.refs.content;
        cont.outerHTML = "<div></div>";
    },
    componentDidUpdate(){
        if (this.props.blog) {
            if (this.props.type == "edit") {
                if (this.state.bId != this.props.blog.diaryId) {
                    this.setState(
                        {
                            bId: this.props.blog.diaryId,
                            html: this.props.blog.content,
                        }
                    )
                    this.state.editor.$txt.html(this.props.blog.content);
                }
            } else {
                this.state.editor.$txt.html("");
            }
        }
    },
    getContent(){
        let html = this.state.editor.$txt.html();
        return html;
    },
    render: function () {
        return (
            <div ref="content" id="wEditor" style={{minHeight:200}}>

            </div>
        )
    }
});
export default Weditor;