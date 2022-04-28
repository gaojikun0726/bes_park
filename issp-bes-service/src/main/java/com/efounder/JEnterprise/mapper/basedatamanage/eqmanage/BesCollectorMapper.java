package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesCollectorMapper extends BaseMapper<String, BesCollector>{
    int deleteByPrimaryKey(String fGuid);

    int insert(BesCollector record);

    int insertSelective(BesCollector record);

    BesCollector selectByPrimaryKey(String fGuid);

    BesCollector selectBySysName(String sysName);

    int updateByPrimaryKeySelective(BesCollector record);

    int updateByPrimaryKey(BesCollector record);
    /**
     * 更新采集器信息
     * @param besCollector
     */
	public void updateCollector(BesCollector besCollector);
	/**
	 * 查询采集器信息
	 * @param f_sys_name
	 * @return
	 */
	Map<String, Object> queryCollectorInfo(@Param("f_sys_name") String f_sys_name);

	/**
	 *
	 * @Description: 查询ddc控制器的信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:48 2020/6/29
	 * @param: [sysName]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    Map<String, Object> queryDDCInfo(@Param("sysName") String sysName);

	/**
	 *
	 * @Description: 查询模块的信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:17 2020/7/2
	 * @param: [sysName]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryModuleInfo(@Param("sysName") String sysName);

	/**
	 *
	 * @Description: 根据模块名称查询当前ddc的channelId
	 *
	 * @auther: wanghongjie
	 * @date: 9:22 2020/7/2
	 * @param: [sysName]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryDDCModuleInfo(@Param("sysName") String sysName);

	/**
	 *
	 * @Description: 更新bes_ddc表的在线离线,是否同步的状态
	 *
	 * @auther: wanghongjie
	 * @date: 17:02 2020/7/6
	 * @param: [besDdc]
	 * @return: void
	 *
	 */
	void updateDDC(BesDdc besDdc);

	/**
	 * 获取全部采集器数据
	 * @return
	 */
	List<BesCollector> loadAll();
}