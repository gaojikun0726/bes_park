package com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm;

import com.core.common.BaseEntity;
import lombok.Data;


/**
 * 报警等级
* @author  liuzhen
* @version 创建时间：2018年11月27日
*/
@Data
public class AlarmLevel implements BaseEntity<String>{


	/**
	 * 等级编号
	 */
	private int alarmLevelNum;
	/**
	 * 等级名称
	 */
	private String alarmLevelName;

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