package com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmType;

/**
 * 报警类型
 * @author lizuhen
 */
public interface AlarmTypeService {

	/**
	 * 获取警报类型信息
	 * @return
	 */
	ISSPReturnObject getAlarmTypeList(AlarmType alarmType);

	/**
	 * 根据搜索内容获取警报类型信息
	 * @return
	 */
	ISSPReturnObject getAlarmTypeListBySearch(AlarmType alarmType);
	/**
	 * 新增警报信息
	 * @param alarmType
	 * @return
	 */
	ISSPReturnObject insertAlarmType(AlarmType alarmType);

	/**
	 * 新增警报信息
	 * @param alarmType
	 * @return
	 */
	ISSPReturnObject deleteAlarmType(AlarmType alarmType);
	/**
	 * 新增警报信息
	 * @param alarmType
	 * @return
	 */
	ISSPReturnObject updateAlarmType(AlarmType alarmType);


}
