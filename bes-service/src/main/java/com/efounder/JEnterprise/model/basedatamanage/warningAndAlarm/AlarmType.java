package com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm;

import com.core.common.BaseEntity;
import lombok.Data;


/**
* @author  liuzhen
* @version 创建时间：2018年11月27日
*/
@Data
public class AlarmType implements BaseEntity<String>{


	/**
	 * 类型编号
	 */
	private int alarmNum;
	/**
	 * 类型名称
	 */
	private String alarmTypeName;

	/**
	 * 序号
	 */
	private String id;

	/**
	 * 创建时间
	 */
	private String fCrdate;

	/**
	 * 修改时间
	 */
	private String fChdate;




}