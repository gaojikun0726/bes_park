package com.efounder.JEnterprise.mapper.basedatamanage.warningAndAlarm;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类描述：报警等级接口
 * 创建人：liuzhen
 *
 */
@Mapper
public interface AlarmLevelMapper extends BaseMapper<String, AlarmLevel> {

	/**
	 * 获取所有报警等级信息
	 * @return
	 */
	 List<AlarmLevel> findAlarmLevel(AlarmLevel alarmLevel);
	/**
	 * 根据搜索内容获取所有报警等级信息
	 * @return
	 */
	List<AlarmLevel> findAlarmLevelBySearch(AlarmLevel alarmLevel);

	/**
	 * 插入等级信息
	 * @param alarmLevel
	 * @return
	 */
	 boolean insertAlarmLevel(AlarmLevel alarmLevel);
	/**
	 * 删除一条报警等级信息
	 * @param alarmLevel
	 * @return
	 */
	boolean deleteAlarmLevel(AlarmLevel alarmLevel);

	/**
	 * 更新一条报警等级信息
	 * @param alarmLevel
	 * @return
	 */
	boolean updateAlarmLevel(AlarmLevel alarmLevel);


}
