package com.efounder.JEnterprise.mapper.sysmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.sysmanage.BesSchedule;
import com.efounder.JEnterprise.model.sysmanage.BesScheduleinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesScheduleConfigMapper extends BaseMapper<String, BesScheduleinfo>{

	public List<BesScheduleinfo> getScheduleTreeList();

	public List<BesSchedule> getScheduleList(@Param(value="keywords")String keywords, @Param(value="fId")String fId);
	/**
	 * 新增计划类型
	 * @param besScheduleinfo
	 * @return
	 */
	public boolean add_schedule(BesScheduleinfo besScheduleinfo);

	public String queryMaxId(@Param("TabName") String TabName);
	/**
	 * 删除计划类型
	 * @param fId
	 * @return
	 */
	public boolean del_schedule(@Param(value="fId")String fId);
	/**
	 * 新增计划详情
	 * @param besSchedule
	 * @return
	 */
	public boolean add_scheduleTask(BesSchedule besSchedule);
	/**
	 * 通过fSysname查询一条计划详情
	 * @param fSysname
	 * @return
	 */
	//public BesSchedule get_scheduleTask(String fSysname);
	/**
	 * 更新计划详情
	 * @param besSchedule
	 * @return
	 */
	public boolean editscheduleTask(BesSchedule besSchedule);
	/**
	 * 删除计划详情
	 * @param fSysName
	 * @return
	 */
	public boolean del_scheduletask(String fSysname);
	/**
	 * 查询场景关联的DDC信息
	 * @param getfZoneid
	 * @return
	 */
	public Map<String, Object> queryDDCInfo(String fZoneid);
	/**
	 * 查询场景模式
	 * @param fZoneid
	 * @return
	 */
	public Map<String, String> loadZoneMode(String fZoneid);
	/**
	 * 查询场景类型
	 * @param getfZoneid
	 * @return
	 */
	public String queryZoneType(String getfZoneid);

	BesSchedule get_scheduleTask(String fSysName);
}