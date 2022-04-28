package com.efounder.JEnterprise.common.exception;

/**
 * 
 *   
 * 类名称：BusinessException
 * 类描述：
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:27:56
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1144969267587138347L;

	String code = "-1";

	String message;

	Exception cause;

	public BusinessException(String code, String message, Exception cause) {
		super();
		this.code = code;
		this.message = message;
		this.cause = cause;
	}

	public BusinessException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getCause() {
		return cause;
	}

	public void setCause(Exception cause) {
		this.cause = cause;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
