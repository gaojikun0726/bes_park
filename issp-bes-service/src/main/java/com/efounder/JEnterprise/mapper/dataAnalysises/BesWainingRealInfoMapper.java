package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 杨超
 * @version 创建时间：2018年12月3日 下午2:16:33
 * @parameter 报警监控
 * @version 1.0
 */
@Mapper
public interface BesWainingRealInfoMapper extends BaseMapper<String, BesWainingInfo> {

    /**
     * 
     * @Title: searchWainingInfo
     * @Description:
     * @return: List<BesWainingInfo>
     * @throws
     */
    List<BesWainingInfo> getWarningRealInfoData(BesWainingInfo besWainingInfo);
    
    /**
     * 
     * @Title: WarningDsipose
     * @Description:处理报警信息
     * @return: Boolean
     * @throws
     */
    Boolean WarningDsipose(@Param(value = "fGuid") String fGuid, @Param(value = "f_yhbh") String f_yhbh,
            @Param(value = "f_operation") String f_operation);
	/**
	 *
	 * @Description: 批量处理报警信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:47 2020/6/6
	 * @param: [fGuid, f_yhbh, f_operation]
	 * @return: java.lang.Boolean
	 *
	 */
	Boolean updateWarningDsipose(@Param(value = "fGuid") String fGuid,@Param(value = "f_operation") String f_operation);
    
    /**
     * 
     * @Title: DelWarningInfo
     * @Description:删除实时表数据
     * @return: Boolean
     * @throws
     */
    Boolean DelWarningInfo(@Param(value = "fGuid") String fGuid);
    /**
     * 插入报警信息
     * @param besWaringInfo
     */
	void insertWarningInfo(BesWainingInfo besWaringInfo);
	/**
	 * 查询所有报警位置相同，且未处理的信息
	 * @param fLoction
	 * @param fTipInfo 
	 * @return
	 */
	List<BesWainingInfo> queryWarningRealList(@Param(value = "fLoction")String fLoction, @Param(value = "fTipInfo")String fTipInfo);
	/**
	 *
	 * @Description: 首页告警图标--查询实时报警数据
	 *
	 * @auther: wanghongjie
	 * @date: 8:56 2020/6/4
	 * @param: [f_operation]
	 * @return: java.util.List<java.lang.Object>
	 *
	 */
	List<BesWainingInfo> getAlarmWarnInfoByRecoverState(@Param(value = "f_operation") String f_operation,@Param(value = "type") String type);

	List<BesWainingInfo> getAlarmWarnInfoByRecoverStateAll(@Param(value = "f_operation") String f_operation);
	/**
	 *
	 * @Description: 获取未恢复的信息数
	 *
	 * @auther: wanghongjie
	 * @date: 8:56 2020/6/4
	 * @param: [f_operation]
	 * @return: java.util.Map<java.lang.String,java.lang.String>
	 *
	 */
	Map<String, String> getNoRecoverCount(@Param("f_operation") String f_operation);
	/**
	 *
	 * @Description: 插入bes_waring_real_data表,实时报警表
	 *
	 * @auther: wanghongjie
	 * @date: 16:13 2020/6/3
	 * @param: [besWaringInfo]
	 * @return: void
	 *
	 */
	void insertWarningInfoData(BesWainingInfo besWaringInfo);

	/**
	 *
	 * @Description: 如果有重复的报警消息,跟新实时报警表的值
	 *
	 * @auther: wanghongjie
	 * @date: 16:15 2020/6/3
	 * @param:
	 * @return:
	 *
	 */
	void updateWarningInfoData(BesWainingInfo besWaringInfo);

	/**
	 *
	 * @Description: 全部处理报警信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:26 2020/7/1
	 * @param: [obj]
	 * @return: int
	 *
	 */
	Boolean warningDsiposeAll(@Param("obj") JSONObject obj);

	List<BesWainingInfo> getWarningRealInfoDataAll(BesWainingInfo besWainingInfo);

	Boolean warningDsiposeAllByFtype(@Param("obj") JSONObject obj);
}
