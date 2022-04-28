package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 策略与部门关系表
 * @author sunhao
 *创建时间  2018-7-3
 */
public class BESStrategyDepartment implements BaseEntity<Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041705335918578563L;

	private String f_strategy_id;  //策略id

	private String f_department_id;  //支路编号

	private String f_level;  //支路等级

	private String f_zlmc;  //支路名称

	public String getF_strategy_id() {
		return f_strategy_id;
	}

	public void setF_strategy_id(String f_strategy_id) {
		this.f_strategy_id = f_strategy_id;
	}

	public String getF_department_id() {
		return f_department_id;
	}

	public void setF_department_id(String f_department_id) {
		this.f_department_id = f_department_id;
	}

	public String getF_level() {
		return f_level;
	}

	public void setF_level(String f_level) {
		this.f_level = f_level;
	}

	public String getF_zlmc() {
		return f_zlmc;
	}

	public void setF_zlmc(String f_zlmc) {
		this.f_zlmc = f_zlmc;
	}
}