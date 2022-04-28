package com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmLevel;

/**
 * 报警等级
 * @author lizuhen
 */
public interface AlarmLevelService {

	/**
	 * 获取警报等级信息
	 * @return
	 */
	ISSPReturnObject getAlarmLevelList(AlarmLevel alarmLevel);

	/**
	 * 根据搜索内容获取警报等级信息
	 * @return
	 */
	ISSPReturnObject getAlarmLevelListBySearch(AlarmLevel alarmLevel);
	/**
	 * 新增警报等级信息
	 * @param alarmLevel
	 * @return
	 */
	ISSPReturnObject insertAlarmLevel(AlarmLevel alarmLevel);

	/**
	 * 新增警报等级信息
	 * @param alarmLevel
	 * @return
	 */
	ISSPReturnObject deleteAlarmLevel(AlarmLevel alarmLevel);
	/**
	 * 新增警报等级信息
	 * @param alarmLevel
	 * @return
	 */
	ISSPReturnObject updateAlarmLevel(AlarmLevel alarmLevel);


}
