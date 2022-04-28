package com.efounder.JEnterprise.service.collectorJob;

import com.core.common.metatype.Dto;
import com.efounder.JEnterprise.service.ESBaseService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;
@Service
public interface BuildBasicInfoService extends ESBaseService{
	public String queryBuildBasicInfoByDto(Dto dto) throws SQLException;
	public String queryDataCentreTypeByDto(Dto dto) throws SQLException;
	public String queryBuildTypeByDto(Dto dto) throws SQLException;
	public String queryAirContition(Dto dto) throws SQLException;
	public String queryHeatingSys(Dto dto) throws SQLException;
	public String queryBuildingStructure(Dto dto) throws SQLException;
	public String querywallMaterial(Dto dto) throws SQLException;
	public String queryheatInsulationForm(Dto dto) throws SQLException;
	public String queryWindowType(Dto dto) throws SQLException;
	public String queryGlassType(Dto dto) throws SQLException;
	public String queryWindowFrame(Dto dto) throws SQLException;
	public Dto regionTreeInit(Dto pDto);
	//新增建筑基本信息
	public void insertbuildBasicInfo(HttpServletRequest request,Dto pDto);
	//修改
	public void updateBuildBasicInfo(Dto pDto);
	//删除
	public void deleteBuildBasicInfo(Dto pDto);
	//通过id查询建筑基本信息
	public String selectbuildBasicInfoById(Dto dto) throws SQLException;
	//建筑基本信息接口
	public Map<String, Object> buildInfoService(String parkid);
}
