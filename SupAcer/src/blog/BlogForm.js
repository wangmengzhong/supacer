import React, {Component}from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {message, Input, Form, Select, Row, Col, Button, Modal} from 'antd';
import QueueAnim from 'rc-queue-anim';
import Global from '../common/global';
import CheckUtil from '../common/util/checkUtil';
import Weditor from '../common/Weditor';
import * as actions from './redux/actions';

const FormItem = Form.Item;
const Option = Select.Option;
class BlogForm extends Component {

    state = {
        baseUrl: Global.baseUrl,
        loading: false
    };

    componentWillUnmount() {
        console.log("modal 销毁");
    }

    check(rule, value, callback) {
       /*if (!CheckUtil.isSpecialChar(value)) {
           callback('不能输入特殊字符!');
            return;
        }else{*/
           callback();
       //}

    }

    onSave() {
        const {addBlog, updateBlog, formType, editBlogInf}=this.props;

        let html = this.refs.editor.getContent();
        const {validateFields, getFieldsValue}= this.props.form;
        let values = {...getFieldsValue()};
        values.content=html;
        if(!html||html.trim()==""){
            message.warning("内容不可为空！")
        }
        validateFields((errors) => {
            if (errors) {
                return;
            }
            if (formType == "edit") {
                values.diaryId = editBlogInf.diaryId;
                values.version = editBlogInf.version;
                updateBlog(values, this.saved.bind(this))
            } else {
                values.version = 0;
                addBlog(values, this.saved.bind(this));
            }
        })
    }

    saved() {
        const {resetFields}= this.props.form;
        resetFields();
        setTimeout(
            ()=> {
                message.success("保存成功！")
            },
            300
        );
    }

    handleCancel() {
        const {hideEdit}= this.props;
        hideEdit();
    }

    render() {
        const {getFieldDecorator}= this.props.form;
        const formItemLayout = {
            labelCol: {span: 0},
            wrapperCol: {span: 24},
        };
        return (
            <Modal
                visible={this.props.showEdit}
                title="编辑"
                width="1150px"
                maskClosable={false}
                onOk={this.onSave.bind(this)}
                onCancel={this.handleCancel.bind(this)}
                footer={[
                <Button key="back" size="large" onClick={this.handleCancel.bind(this)}>取消</Button>,
                <Button key="submit" type="primary" size="large" loading={this.state.loading} onClick={this.onSave.bind(this)}>
                    保存
                </Button>,
                ]}
            >
                <div className="ani-box">
                    <Form>
                        <FormItem
                            hasFeedback
                            {...formItemLayout}
                        >
                            {getFieldDecorator('diaryName', {
                                initialValue: this.props.editBlogInf.diaryName,
                                rules: [
                                    {required: true, message: '请输入名称！'},
                                    {validator: this.check.bind(this)}
                                ],
                            })(
                                <Input type="text" size="large"/>
                            )}
                        </FormItem>
                        <FormItem
                            hasFeedback
                            {...formItemLayout}
                        >
                            {getFieldDecorator('type', {
                                initialValue: this.props.editBlogInf.type ? this.props.editBlogInf.type + "" : "1",
                                rules: [
                                    {required: true, message: '请选择分类！'},
                                ],
                            })(
                                <Select>
                                    <Select.Option value="0">technology</Select.Option>
                                    <Select.Option value="1">diary</Select.Option>
                                </Select>
                            )}
                        </FormItem>
                        <FormItem
                            hasFeedback
                            {...formItemLayout}
                        >
                            {getFieldDecorator('ext1', {
                                initialValue: this.props.editBlogInf.ext1 ? this.props.editBlogInf.ext1 + "" : "1",
                                rules: [
                                    {required: true, message: '请选择可见范围！'},
                                ],
                            })(
                                <Select>
                                    <Select.Option value="1">public</Select.Option>
                                    <Select.Option value="0">private</Select.Option>
                                </Select>
                            )}
                        </FormItem>
                        <FormItem
                            hasFeedback
                            {...formItemLayout}
                        >
                            {getFieldDecorator('subhead', {
                                initialValue: this.props.editBlogInf.subhead,
                                rules: [
                                    {required: true, message: '请输入副标题！'},
                                ],
                            })(
                                <Input type="text" size="large"/>
                            )}
                        </FormItem>
                    </Form>
                    <Row>
                        <Col span={24}>
                            <Weditor
                                type={this.props.formType}
                                blog={this.props.editBlogInf}
                                bId={this.props.editBlogInf.diaryId} ref="editor"
                            />
                        </Col>
                    </Row>
                </div>
            </Modal>
        )
    }
}

function mapStateToProps(state) {
    return {
        editBlogInf: state.blog.editBlogInf,
        showEdit: state.blog.showEdit,
        formType: state.blog.formType,
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators(actions, dispatch)
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Form.create()(BlogForm))