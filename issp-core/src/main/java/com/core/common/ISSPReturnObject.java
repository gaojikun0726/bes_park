package com.core.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 统一返回格式
 * 
 * @author Alvin
 * @datetiem 2018年1月22日
 *
 */
public class ISSPReturnObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;// 是否成功 1：成功 0：失败
	private String msg;// 返回信息
	private Object data;// 返回数据
	private List<?> list;
	private Map<?, ?> map;
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Map<?, ?> getMap() {
		return map;
	}

	public void setMap(Map<?, ?> map) {
		this.map = map;
	}
}
