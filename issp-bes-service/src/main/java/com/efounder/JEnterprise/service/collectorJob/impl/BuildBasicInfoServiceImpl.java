package com.efounder.JEnterprise.service.collectorJob.impl;

import com.core.common.metatype.Dto;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.service.collectorJob.BuildBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

@Component
@Service("BuildBasicInfoService")
public class BuildBasicInfoServiceImpl implements BuildBasicInfoService{
	private static BuildBasicInfoServiceImpl buildBasicInfoServiceImpl;

	@Autowired
	private BESCalculateDataMapper bESCalculateDataMapper;
	
	@PostConstruct
	public void init() {
		buildBasicInfoServiceImpl = this;
		buildBasicInfoServiceImpl.bESCalculateDataMapper = this.bESCalculateDataMapper;
	}

	//建筑基本信息接口 年代，面积，人口，用途
	public Map<String, Object> buildInfoService(String parkid){
		//查询园区名称和园区建筑总面积
		Map<String,Object> parkInfo = buildBasicInfoServiceImpl.bESCalculateDataMapper.queryParkById(parkid);
		return parkInfo;
		
	}

	@Override
	public String queryBuildBasicInfoByDto(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryDataCentreTypeByDto(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryBuildTypeByDto(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryAirContition(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryHeatingSys(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryBuildingStructure(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String querywallMaterial(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryheatInsulationForm(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryWindowType(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryGlassType(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryWindowFrame(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dto regionTreeInit(Dto pDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertbuildBasicInfo(HttpServletRequest request, Dto pDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBuildBasicInfo(Dto pDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBuildBasicInfo(Dto pDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String selectbuildBasicInfoById(Dto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
