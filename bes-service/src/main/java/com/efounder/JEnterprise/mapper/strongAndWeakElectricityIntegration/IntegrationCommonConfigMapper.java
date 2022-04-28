package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 类名称：IntegrationCommonConfigMapper
 * 类描述：强弱电一体化通用配置Mapper
 * 创建人：yangjx
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface IntegrationCommonConfigMapper{
	 
	 
	 /**
	  * 查询全部DDC信息
	  * @return
	  */
	public List<CommonPointLocationConfigVo> findDDCInfos();

	/**
	 * 查询DDC所属的点位信息
	 * @return
	 */
	public List<CommonPointLocationConfigVo> queryPointLocationInfos(String f_sys_name);

	/**
	 * 添加页面配置点位展示信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public int insertPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);
	
	/**
	 *
	 * @Description: 添加页面配置点位展示信息 top
	 * 
	 * @auther: wanghongjie
	 * @date: 11:23 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: int
	 *
	 */
	public int insertPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop);

	/**
	 * 查询配置的页面点位展示配置信息
	 * @param f_equipment_id
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> searchPageConfigData(@Param("f_equipment_id")String f_equipment_id,@Param("f_type_id")String f_type_id);

	public List<IntegrationPageCommonConfigtop> searchPageConfigDatatop(@Param("f_equipment_id")String f_equipment_id,@Param("f_type_id")String f_type_id);


	/**
	 * 添加页面配置div展示数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	public int confirmIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);
	/*/*
	 *
	 * @Description: 添加页面配置div展示数目信息,z左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 16:32 2020/2/26
	 * @param: [integrationPageDivNumConfigVo]
	 * @return: int
	 *
	 */
	public int confirmIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);
	/**
	 * 查找页面配置div展示数目信息
	 * @param f_equipment_id
	 * @return
	 */
	public int checkDivNumDataInfo(@Param(value = "f_equipment_id") String f_equipment_id,@Param(value = "f_type_id") String f_type_id);

	/**
	 * 查找页面配置div展示数目信息左侧的div配置
	 * @param f_equipment_id
	 *wanghongjie
	 * @return
	 */
	public int checkDivNumDataInfoByLeft(@Param(value = "f_equipment_id") String f_equipment_id,@Param(value = "f_type_id") String f_type_id);

	/**
	 * 更新页面配置div展示数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	public int updateIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);
	/*/*
	 *
	 * @Description: 更新页面配置div展示数目信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:58 2020/2/26
	 * @param: [integrationPageDivNumConfigVo]
	 * @return: int
	 *
	 */
	public int updateIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);

	/**
	 * 查询页面配置div展示数目信息
	 * @param f_equipment_id  设备id
	 * @param f_type_id       页面类型
	 * @return
	 */
	public Integer searchIntegrationPageDivConfigNum(@Param(value = "f_equipment_id") String f_equipment_id,@Param(value = "f_type_id")String f_type_id);
	/*/*
	 *
	 * @Description: 查询页面配置div展示数目信息  左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 17:34 2020/2/26
	 * @param: [f_equipment_id]
	 * @return: java.lang.Integer
	 *
	 */
	public Integer searchIntegrationPageDivConfigNumByLeft(@Param(value = "f_equipment_id") String f_equipment_id,@Param(value = "f_type_id") String f_type_id);

	/**
	 * 查询加载页面配置显示数值及单位
	 * @param f_sysName_list
	 * @return
	 */
	public List<IntegrationInitValAndEngineerUnitVo> loadInitValAndEngineerUnit(@Param(value = "f_sysName_list")String f_sysName_list);

	/**
	 * 查找页面配置div信息
	 * @param f_equipment_id
	 * @param f_seq
	 * @return
	 */
	public int checkPageCommonConfig(@Param("f_equipment_id")String f_equipment_id, @Param("f_seq")String f_seq,@Param("f_type_id")String f_type_id);

	public int checkPageCommonConfigRepetition(@Param("f_equipment_id")String f_equipment_id, @Param("f_seq")String f_seq,@Param("f_sys_name")String f_sys_name,@Param("f_type_id")String f_type_id,@Param("f_desc")String f_desc,@Param("f_formula") String f_formula);

	public int checkPageCommonConfigtop(@Param("f_equipment_id")String f_equipment_id, @Param("f_seq")String f_seq,@Param("f_type_id")String f_type_id);

	/**
	 * 更新页面配置div展示信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public int updateIntegrationPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 *
	 * @Description: 更新页面配置div展示信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 10:57 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: int
	 *
	 */
	public int updateIntegrationPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop);

	/**
	 * 查询活动div配置数据信息
	 * @return
	 */
	public List<AliveDivConfigVo> searchIntegrationAliveDivPageConfig();

	/**
	 * 添加通用页面活动DIV配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	public int insertAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo);

	/**
	 * 查询校验此序号是否已配置活动div信息
	 * @param f_div_seq
	 * @return
	 */
	public int checkConfigDivSequenceInfo(String f_div_seq);

	/**
	 * 更新页面活动DIV配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	public int updateAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo);

	/**
	 * 删除活动DIV配置信息
	 * @param f_div_seq
	 * @return
	 */
	public int deleteAliveCommonConfigInfo(String f_div_seq);

	/**
	 * 查询表数据配置展示信息
	 * @return
	 */
	public List<TableDataConfigVo> searchIntegrationTableDataConfig(@Param("f_page_name")String f_page_name,@Param("f_device_id")String f_device_id);

	/**
	 * 添加强弱电一体化表数据配置信息
	 * @param tableDataConfigVo
	 * @return
	 */
	public int insertTableDataCommonConfig(TableDataConfigVo tableDataConfigVo);

	/**
	 * 更新页面展示表数据配置信息
	 * @param tableDataConfigVo
	 * @return
	 */
	public int updateTableDataCommonConfig(TableDataConfigVo tableDataConfigVo);

	/**
	 * 删除页面展示表数据配置信息
	 * @param f_id
	 * @return
	 */
	public int deleteTableDataCommonConfig(String f_id);

	/**
	 * 查找系统能效页面配置div信息
	 * @param f_seq
	 * @return
	 */
	public int checkEnergyEfficiencyConfig(String f_seq);

	/**
	 * 添加系统能效页面配置点位展示信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public int insertEnergyEfficiencyConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 * 
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public int updateIntegrationEnergyEfficiencyConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);
	
	/**
	 * 查找系统能效页面配置div展示信息
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> searchEnergyEfficiencyPageConfigData();

	/**
	 * 添加能耗评估页面配置点位信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	 public int insertAssessment(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 * 在添加之前删除之前添加的数据
	 * @return
	 */
	public int deleteAssessment();

	/**
	 * 初始化查询
	 * @param
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> selectAssessment();
}
