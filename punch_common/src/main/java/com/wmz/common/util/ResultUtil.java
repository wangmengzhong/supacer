package com.wmz.common.util;

import com.wmz.common.entity.Result;

public class ResultUtil {

	/**
	 * 成功编码: 0
	 */
	public static Integer SUCCESS_CODE = 0;

	/**
	 * 失败编码: 1
	 */
	public static Integer FAILED_CODE = 1;

	/**
	 * 返回处理的结果 code=0 msg="成功"
	 * 
	 * @param data
	 *            返回的数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Result success(Object data) {
		Result result = new Result();
		result.setSuccessful(true);
		result.setCode(SUCCESS_CODE);
		result.setMsg("成功");
		result.setData(data);
		return result;
	}

	/**
	 * 返回处理的结果 code=0
	 * 
	 * @param data
	 *            返回的数据
	 * @param msg
	 *            结果说明
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Result success(Object data, String msg) {
		Result result = new Result();
		result.setSuccessful(true);
		result.setCode(SUCCESS_CODE);
		if (null != msg) {
			result.setMsg(msg);
		}
		result.setData(data);
		return result;
	}

	/**
	 * 返回处理的结果
	 * 
	 * @param code
	 *            结果代码 0:成功,1:失败
	 * @param data
	 *            返回的数据
	 * @param msg
	 *            结果说明
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result success(Integer code, Object data, String msg) {
		Result result = new Result();
		result.setSuccessful(true);
		result.setCode(code);
		if (null != msg) {
			result.setMsg(msg);
		}
		if (null != data) {
			result.setData(data);
		}
		return result;
	}

	/**
	 * 返回业务失败的结果
	 * 
	 * @param code
	 *            结果代码 0:成功,1:失败
	 * @param msg
	 *            结果说明
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Result failed(Integer code, String msg) {
		Result result = new Result();
		result.setSuccessful(true);
		result.setCode(code);
		if (null != msg) {
			result.setMsg(msg);
		}
		return result;
	}

	/**
	 * 返回业务失败的结果 code:1
	 * 
	 * @param msg
	 *            结果说明
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Result failed(String msg) {
		Result result = new Result();
		result.setSuccessful(true);
		result.setCode(FAILED_CODE);
		result.setMsg(msg);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setSuccessful(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
