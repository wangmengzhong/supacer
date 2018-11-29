import React from 'react';
import {Icon} from 'antd';
import Global from '../common/global';
import  './styles/catalog.less';
const Catalog = React.createClass({
    getInitialState() {
        return {
            menus: []
        };
    },
    hide(e){
        e.stopPropagation();
        let self = this;
        this.refs.modal.className = "catalog_modal my_animated bounceOut";
        setTimeout(function () {
            self.refs.box.className = "box-mask_hidden";
        }, 700)
    },
    show(){
        this.refs.box.className = "box-mask";
        this.refs.modal.className = "catalog_modal animated flipInX";
    },
    close(e){
        e.stopPropagation();
        let self = this;
        let animate=Global.getHideType();
        this.refs.modal.className = "catalog_modal my_animated "+animate;
        setTimeout(function () {
            self.refs.box.className = "box-mask_hidden";
        }, 700)
    },
    linkTo(url, e){
        e.stopPropagation();
        let self = this;
        this.refs.modal.className = "catalog_modal my_animated bounceOut";
        setTimeout(function () {
            //hashHistory.push(url);
            Global.goTo(url);
            self.refs.box.className = "box-mask_hidden";
        }, 700)
    },
    shake(){
        let self = this;
        this.refs.modal.className = "catalog_modal animated swing";
        setTimeout(function () {
            self.refs.modal.className = "catalog_modal animated";
        }, 1000)
    },
    render(){
        return (
            <div className="box-mask_hidden" ref="box" onClick={()=>{this.shake()}}>
                <div className="catalog_modal" ref="modal">
                    <div style={{width:"100%",height:29,background:"rgba(16, 233, 129, 0.76)", borderRadius: "5px 5px 0px 0px"}}>
                        <Icon type="close-square" onClick={this.close} className="catalog_close"/>
                    </div>
                    <div>
                        <ul className="catalog_modal_ul">
                            <li onClick={(e)=>{this.linkTo("/home/myBlog/list?lm=0",e)}}>myBlog</li>
                            <li onClick={(e)=>{this.linkTo("/home/demo",e)}}>demo</li>
                            <li onClick={(e)=>{this.linkTo("/home/chatRoom",e)}}>chatRoom</li>
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
});

export default Catalog;