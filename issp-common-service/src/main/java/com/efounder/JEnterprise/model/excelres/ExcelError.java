package com.efounder.JEnterprise.model.excelres;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;


/**
* @author  liuzhen
* @version 创建时间：2019年1月22日 上午8:44:29
* @parameter 
* @version 1.0
*/
public class ExcelError implements BaseEntity<String>{
	@ExcelVOAttribute(name = "行数", column = "A")
	private String row;
	@ExcelVOAttribute(name = "错误信息", column = "B")
	private String errorMsg;

	public String getRow() {
		return row;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setRow(String row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "ExcelError{" +
				"row='" + row + '\'' +
				", errorMsg='" + errorMsg + '\'' +
				'}';
	}
}