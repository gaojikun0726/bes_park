package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.IntegrationCommonConfigMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.*;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.IntegrationCommonConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IntegrationCommonConfigService")
public class IntegrationCommonConfigServiceImpl implements IntegrationCommonConfigService{
	private static final Logger log = LoggerFactory.getLogger(IntegrationCommonConfigServiceImpl.class);
	
	@Autowired
	private IntegrationCommonConfigMapper integrationCommonConfigMapper;
	
	
	@Override
	public List<CommonPointLocationConfigVo> queryDDCInfos() {
		List<CommonPointLocationConfigVo> ddcInfos = integrationCommonConfigMapper.findDDCInfos();
		return ddcInfos;
	}


	@Override
	public List<CommonPointLocationConfigVo> queryPointLocationInfos(String f_sys_name) {
		List<CommonPointLocationConfigVo> pointLocationInfos = integrationCommonConfigMapper.queryPointLocationInfos(f_sys_name);
		return pointLocationInfos;
	}


	@Override
	public JsonResult insertPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.insertPageCommonConfig(integrationPageCommonConfigVo);
		if (insertResult > 0) {
			result.setData(integrationPageCommonConfigVo);
			result.setCode("0");
			result.setMsg("添加成功");
		} else {
			result.setCode("1");
			result.setMsg("添加失败");
		}
		return result;	
	}

	@Override
	public JsonResult insertPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.insertPageCommonConfigtop(integrationPageCommonConfigtop);
		if (insertResult > 0) {
			result.setData(integrationPageCommonConfigtop);
			result.setCode("0");
			result.setMsg("添加成功");
		} else {
			result.setCode("1");
			result.setMsg("添加失败");
		}
		return result;
	}


	@Override
	public List<IntegrationPageCommonConfigVo> searchPageConfigData(String f_equipment_id,String f_type_id) {
		List<IntegrationPageCommonConfigVo> pointLocationInfos = integrationCommonConfigMapper.searchPageConfigData(f_equipment_id,f_type_id);
		return pointLocationInfos;
	}

	@Override
	public List<IntegrationPageCommonConfigtop> searchPageConfigDatatop(String f_equipment_id,String f_type_id) {
		List<IntegrationPageCommonConfigtop> pointLocationInfos = integrationCommonConfigMapper.searchPageConfigDatatop(f_equipment_id,f_type_id);
		return pointLocationInfos;
	}


	@Override
	public JsonResult confirmIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.confirmIntegrationDivNumConfig(integrationPageDivNumConfigVo);
		if (insertResult > 0) {
			result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("添加成功");
		} else {
			result.setCode("1");
			result.setMsg("添加失败");
		}
		return result;	
	}

	@Override
	public JsonResult confirmIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.confirmIntegrationDivNumConfigByLeft(integrationPageDivNumConfigVo);
		if (insertResult > 0) {
			result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("添加成功");
		} else {
			result.setCode("1");
			result.setMsg("添加失败");
		}
		return result;
	}


	@Override
	public JsonResult checkDivNumDataInfo(String f_equipment_id,String f_type_id) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkDivNumDataInfo(f_equipment_id,f_type_id);
		if (insertResult > 0) {
			result.setCode("0");
			result.setMsg("已配置此id数据");
		} else {
			result.setCode("1");
			result.setMsg("查询配置展示div数目出错");
		}
		return result;	
	}

	@Override
	public JsonResult checkDivNumDataInfoByLeft(String f_equipment_id,String f_type_id) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkDivNumDataInfoByLeft(f_equipment_id,f_type_id);
		if (insertResult > 0) {
			result.setCode("0");
			result.setMsg("已配置此id数据");
		} else {
			result.setCode("1");
			result.setMsg("查询配置展示div数目出错");
		}
		return result;
	}


	@Override
	public JsonResult updateIntegrationDivNumConfig(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateIntegrationDivNumConfig(integrationPageDivNumConfigVo);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("更新成功");
		} else {
			result.setCode("1");
			result.setMsg("更新失败");
		}
		return result;	
	}

	@Override
	public JsonResult updateIntegrationDivNumConfigByLeft(IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateIntegrationDivNumConfigByLeft(integrationPageDivNumConfigVo);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("更新成功");
		} else {
			result.setCode("1");
			result.setMsg("更新失败");
		}
		return result;
	}


	@Override
	public int searchIntegrationPageDivConfigNum(String f_equipment_id,String f_type_id) {
		Integer integer = integrationCommonConfigMapper.searchIntegrationPageDivConfigNum(f_equipment_id,f_type_id);
		if(integer==null) {
			integer=0;
		}
		return integer.intValue();
	}

	@Override
	public int searchIntegrationPageDivConfigNumByLeft(String f_equipment_id,String f_type_id) {
		Integer integer = integrationCommonConfigMapper.searchIntegrationPageDivConfigNumByLeft(f_equipment_id,f_type_id);
		if(integer==null) {
			integer=0;
		}
		return integer.intValue();
	}


	@Override
	public List<IntegrationInitValAndEngineerUnitVo> loadInitValAndEngineerUnit(String f_sysName_list) {
		List<IntegrationInitValAndEngineerUnitVo> pointLocationInfos = integrationCommonConfigMapper.loadInitValAndEngineerUnit(f_sysName_list);
		return pointLocationInfos;
	}


	@Override
	public JsonResult checkPageCommonConfig(String f_equipment_id, String f_seq,String f_type_id) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkPageCommonConfig(f_equipment_id,f_seq,f_type_id);
		if (insertResult > 0) {
			result.setCode("0");
			result.setMsg("已配置此id及div下的数据");
		} else if(insertResult==0){
			result.setCode("2");
			result.setMsg("未配置此id及div下的数据");
		}else {
			result.setCode("1");
			result.setMsg("查询配置展示div出错");
		}
		return result;	
	}

	@Override
	public JsonResult checkPageCommonConfigRepetition(String f_equipment_id,String f_seq,String f_sys_name,String f_type_id,String f_desc,String f_formula) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkPageCommonConfigRepetition(f_equipment_id,f_seq,f_sys_name,f_type_id,f_desc,f_formula);
		if (insertResult >= 1) {
			result.setCode("0");
			result.setMsg("已配置此id及div下的数据多条");
		}else {
			result.setCode("1");
			result.setMsg("查询配置");
		}
		return result;
	}

	@Override
	public JsonResult checkPageCommonConfigtop(String f_equipment_id, String f_seq,String f_type_id) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkPageCommonConfigtop(f_equipment_id,f_seq,f_type_id);
		if (insertResult > 0) {
			result.setCode("0");
			result.setMsg("已配置此id及div下的数据");
		} else if(insertResult==0){
			result.setCode("2");
			result.setMsg("未配置此id及div下的数据");
		}else {
			result.setCode("1");
			result.setMsg("查询配置展示div出错");
		}
		return result;
	}


	@Override
	public JsonResult updateIntegrationPageCommonConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateIntegrationPageCommonConfig(integrationPageCommonConfigVo);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("更新成功");
		} else {
			result.setCode("1");
			result.setMsg("更新失败");
		}
		return result;	
	}

	@Override
	public JsonResult updateIntegrationPageCommonConfigtop(IntegrationPageCommonConfigtop integrationPageCommonConfigtop) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateIntegrationPageCommonConfigtop(integrationPageCommonConfigtop);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("更新成功");
		} else {
			result.setCode("1");
			result.setMsg("更新失败");
		}
		return result;
	}


	@Override
	public List<AliveDivConfigVo> searchIntegrationAliveDivPageConfig() {
		List<AliveDivConfigVo> aliveDivInfos = integrationCommonConfigMapper.searchIntegrationAliveDivPageConfig();
		return aliveDivInfos;
	}


	@Override
	public JsonResult insertAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.insertAliveDivCommonConfig(aliveDivConfigVo);
		if (insertResult > 0) {
			result.setData(aliveDivConfigVo);
			result.setCode("0");
			result.setMsg("添加成功");
		} else {
			result.setCode("1");
			result.setMsg("添加失败");
		}
		return result;	
	}


	@Override
	public JsonResult checkConfigDivSequenceInfo(String f_div_seq) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkConfigDivSequenceInfo(f_div_seq);
		if (insertResult > 0) {
			result.setCode("2");
			result.setMsg("此序号已配置活动div");
		} else if(insertResult==0){
			result.setCode("0");
			result.setMsg("此序号未配置活动div");
		}else {
			result.setCode("1");
			result.setMsg("查询配置活动div信息出错");
		}
		return result;	
	}


	@Override
	public JsonResult updateAliveDivCommonConfig(AliveDivConfigVo aliveDivConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateAliveDivCommonConfig(aliveDivConfigVo);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("0");
			result.setMsg("更新成功");
		} else {
			result.setCode("1");
			result.setMsg("更新失败");
		}
		return result;	
	}


	@Override
	public JsonResult deleteAliveCommonConfigInfo(String f_div_seq) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.deleteAliveCommonConfigInfo(f_div_seq);
		if (insertResult > 0) {
			result.setCode("0");
			result.setMsg("删除活动DIV配置信息成功");
		} else if(insertResult==0){
			result.setCode("2");
			result.setMsg("此序号无配置活动div数据");
		}else {
			result.setCode("1");
			result.setMsg("删除活动DIV配置信息出错");
		}
		return result;	
	}


	@Override
	public List<TableDataConfigVo> searchIntegrationTableDataConfig(String f_page_name,String f_device_id) {
		List<TableDataConfigVo> tableDataConfigInfos = integrationCommonConfigMapper.searchIntegrationTableDataConfig(f_page_name,f_device_id);
		return tableDataConfigInfos;
	}


	@Override
	public JsonResult insertTableDataCommonConfig(TableDataConfigVo tableDataConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.insertTableDataCommonConfig(tableDataConfigVo);
		if (insertResult > 0) {
			result.setData(tableDataConfigVo);
			result.setCode("1");
			result.setMsg("添加成功");
		} else {
			result.setCode("0");
			result.setMsg("添加失败");
		}
		return result;	
	}


	@Override
	public JsonResult updateTableDataCommonConfig(TableDataConfigVo tableDataConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateTableDataCommonConfig(tableDataConfigVo);
		if (updateResult > 0) {
			//result.setData(integrationPageDivNumConfigVo);
			result.setCode("1");
			result.setMsg("更新成功");
		} else {
			result.setCode("0");
			result.setMsg("更新失败");
		}
		return result;	
	}


	@Override
	public JsonResult deleteTableDataCommonConfig(String f_id) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.deleteTableDataCommonConfig(f_id);
		if (insertResult > 0) {
			result.setCode("1");
			result.setMsg("删除活动DIV配置信息成功");
		}else {
			result.setCode("0");
			result.setMsg("删除活动DIV配置信息出错");
		}
		return result;	
	}


	@Override
	public JsonResult checkEnergyEfficiencyConfig(String f_seq) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.checkEnergyEfficiencyConfig(f_seq);
		if (insertResult > 0) {
			result.setCode("1");
			result.setMsg("已配置此div下的数据");
		} else if(insertResult==0){
			result.setCode("2");
			result.setMsg("未配置此div下的数据");
		}else {
			result.setCode("0");
			result.setMsg("查询配置展示div出错");
		}
		return result;	
	}


	@Override
	public JsonResult insertEnergyEfficiencyConfig(IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		JsonResult result = new JsonResult();
		int insertResult = integrationCommonConfigMapper.insertEnergyEfficiencyConfig(integrationPageCommonConfigVo);
		if (insertResult > 0) {
			result.setData(integrationPageCommonConfigVo);
			result.setCode("1");
			result.setMsg("添加成功");
		} else {
			result.setCode("0");
			result.setMsg("添加失败");
		}
		return result;	
	}


	@Override
	public JsonResult updateIntegrationEnergyEfficiencyConfig(
			IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		JsonResult result = new JsonResult();
		int updateResult = integrationCommonConfigMapper.updateIntegrationEnergyEfficiencyConfig(integrationPageCommonConfigVo);
		if (updateResult > 0) {
			result.setCode("1");
			result.setMsg("更新成功");
		} else {
			result.setCode("0");
			result.setMsg("更新失败");
		}
		return result;	
	}


	@Override
	public List<IntegrationPageCommonConfigVo> searchEnergyEfficiencyPageConfigData() {
		List<IntegrationPageCommonConfigVo> pointLocationInfos = integrationCommonConfigMapper.searchEnergyEfficiencyPageConfigData();
		return pointLocationInfos;
	}

	@Override
	public JsonResult insertAssessment(IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {

		JsonResult result = new JsonResult();
		int flag = integrationCommonConfigMapper.deleteAssessment();
		if (flag >=0){
			int insertResult = integrationCommonConfigMapper.insertAssessment(integrationPageCommonConfigVo);
			if (insertResult > 0) {
				result.setData(integrationPageCommonConfigVo);
				result.setCode("1");
				result.setMsg("添加成功");
			} else {
				result.setCode("0");
				result.setMsg("添加失败");
			}
		}else {
			result.setCode("0");
			result.setMsg("添加失败");
		}
		return result;
	}

	@Override
	public List<IntegrationPageCommonConfigVo> selectAssessment() {
		List<IntegrationPageCommonConfigVo> asData = integrationCommonConfigMapper.selectAssessment();
		return asData;
	}


}
