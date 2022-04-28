package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.*;

import java.util.List;

public interface IntegrationCommonConfigService {

	/**
	 * 查询DDC信息
	 * @return
	 */
	public List<CommonPointLocationConfigVo> queryDDCInfos();

	/**
	 * 查找DDC所属的点位信息
	 * @return
	 */
	public List<CommonPointLocationConfigVo> queryPointLocationInfos(String f_sys_name);
	
	/**
	 * 添加一体化页面公共配置信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public JsonResult insertPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 *
	 * @Description: 添加一体化页面公共配置信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 11:22 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	public JsonResult insertPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop);

	/**
	 * 查找页面配置所需展示的点位信息
	 * @param f_equipment_id
	 * @param f_type_id
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> searchPageConfigData(String f_equipment_id,String f_type_id);

	public List<IntegrationPageCommonConfigtop> searchPageConfigDatatop(String f_equipment_id,String f_type_id);


	/**
	 * 添加页面配置div展示最大数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	public JsonResult confirmIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);

	/**
	 * 添加页面配置div展示最大数目信息,左侧的div数量
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	public JsonResult confirmIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);

	/**
	 * 查找检验页面配置div展示最大数目信息
	 * @param f_equipment_id
	 * @return
	 */
	public JsonResult checkDivNumDataInfo(String f_equipment_id,String f_type_id);

	/**
	 * 查找检验页面配置div展示最大数目信息
	 * @param f_equipment_id
	 *wanghongjie
	 * @return
	 */
	public JsonResult checkDivNumDataInfoByLeft(String f_equipment_id,	String f_type_id);

	/**
	 * 更新页面配置div展示最大数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	public JsonResult updateIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);

	/*
	 *
	 * @Description: 更新页面配置div展示最大数目信息  左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 16:51 2020/2/26
	 * @param: [integrationPageDivNumConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	public JsonResult updateIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo);

	/**
	 * 查找页面配置div展示最大数目信息
	 * @param f_equipment_id
	 * @param f_type_id
	 * @return
	 */
	public int searchIntegrationPageDivConfigNum(String f_equipment_id,String f_type_id);
	/*/*
	 *
	 * @Description: 查找页面配置div展示最大数目信息 左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 17:33 2020/2/26
	 * @param: [f_equipment_id]
	 * @return: int
	 *
	 */
	public int searchIntegrationPageDivConfigNumByLeft(String f_equipment_id,String f_type_id);


	/**
	 * 查询加载页面配置设备展示点位数据及单位信息
	 * @param f_sysName_list
	 * @return
	 */
	public List<IntegrationInitValAndEngineerUnitVo> loadInitValAndEngineerUnit(String f_sysName_list);

	/**
	 * 查找检验页面配置div展示信息
	 * @param f_equipment_id
	 * @param f_seq
	 * @return
	 */
	public JsonResult checkPageCommonConfig(String f_equipment_id, String f_seq,String f_type_id);
	/*
	 *
	 * @Description: 配置点位的时候,做判断,如果数据库中有重复的点位名称,则不进行添加
	 *
	 * @auther: wanghongjie
	 * @date: 18:43 2020/3/10
	 * @param: [f_equipment_id, f_seq, f_sys_name, f_type_id]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	public JsonResult checkPageCommonConfigRepetition(String f_equipment_id, String f_seq,String f_sys_name,String f_type_id,String f_desc,String f_formula);

	/**
	 *
	 * @Description: 查找检验页面配置div展示信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 10:24 2020/2/27
	 * @param: [f_equipment_id, f_seq, f_type_id]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	public JsonResult checkPageCommonConfigtop(String f_equipment_id, String f_seq,String f_type_id);
	
	/**
	 * 更新页面配置div展示信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public JsonResult updateIntegrationPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 *
	 * @Description: 更新页面配置div展示信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 10:53 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	public JsonResult updateIntegrationPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop);

	/**
	 * 查询页面活动div配置信息
	 * @return
	 */
	public List<AliveDivConfigVo> searchIntegrationAliveDivPageConfig();

	/**
	 * 添加页面通用活动DIV配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	public JsonResult insertAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo);

	/**
	 * 查找检验页面配置活动div信息有无
	 * @param f_div_seq
	 * @return
	 */
	public JsonResult checkConfigDivSequenceInfo(String f_div_seq);

	/**
	 * 更新页面配置活动div配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	public JsonResult updateAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo);

	/**
	 * 删除活动DIV配置信息
	 * @param f_div_seq
	 * @return
	 */
	public JsonResult deleteAliveCommonConfigInfo(String f_div_seq);

	/**
	 * 查询表数据配置信息
	 * @return
	 */
	public List<TableDataConfigVo> searchIntegrationTableDataConfig(String f_page_name,String f_device_id);

	/**
	 * 添加强弱电一体化表数据配置信息
	 * @param tableDataConfigVo
	 * @return
	 */
	public JsonResult insertTableDataCommonConfig(TableDataConfigVo tableDataConfigVo);

	/**
	 * 更新通用展示表数据配置信息
	 * @param tableDataConfigVo
	 * @return
	 */
	public JsonResult updateTableDataCommonConfig(TableDataConfigVo tableDataConfigVo);

	/**
	 * 删除通用展示表数据配置信息
	 * @param f_id
	 * @return
	 */
	public JsonResult deleteTableDataCommonConfig(String f_id);

	/**
	 * 查找检验系统能效页面配置div展示信息
	 * @param f_seq
	 * @return
	 */
	public JsonResult checkEnergyEfficiencyConfig(String f_seq);

	/**
	 * 添加一体化系统能效页面配置信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public JsonResult insertEnergyEfficiencyConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 * 更新系统能效配置div展示信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public JsonResult updateIntegrationEnergyEfficiencyConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 * 查找系统能效页面配置div展示信息
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> searchEnergyEfficiencyPageConfigData();

	/**
	 * 添加能效评估页面配置信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	public JsonResult insertAssessment(IntegrationPageCommonConfigVo integrationPageCommonConfigVo);

	/**
	 * 初始化查询参数
	 * @return
	 */
	public List<IntegrationPageCommonConfigVo> selectAssessment();
}
