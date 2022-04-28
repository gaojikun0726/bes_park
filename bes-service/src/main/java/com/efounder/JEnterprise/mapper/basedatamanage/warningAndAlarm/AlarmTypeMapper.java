package com.efounder.JEnterprise.mapper.basedatamanage.warningAndAlarm;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类描述：报警类型接口
 * 创建人：liuzhen
 *
 */
@Mapper
public interface AlarmTypeMapper extends BaseMapper<String, AlarmType> {

	/**
	 * 获取所有报警类型信息
	 * @return
	 */
	 List<AlarmType> findAlarmType(AlarmType alarmType);
	/**
	 * 根据搜索内容获取所有报警类型信息
	 * @return
	 */
	List<AlarmType> findAlarmTypeBySearch(AlarmType alarmType);

	/**
	 * 插入警报信息
	 * @param alarmType
	 * @return
	 */
	 boolean insertAlarmType(AlarmType alarmType);
	/**
	 * 删除一条报警信息
	 * @param alarmType
	 * @return
	 */
	boolean deleteAlarmType(AlarmType alarmType);

	/**
	 * 更新一条报警信息
	 * @param alarmType
	 * @return
	 */
	boolean updateAlarmType(AlarmType alarmType);


}
