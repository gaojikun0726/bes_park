package com.efounder.JEnterprise.service.collectorJob.impl;

import com.core.common.metatype.Dto;
import com.efounder.JEnterprise.service.collectorJob.AlarmInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
@Service("AlarmInfoService")
public class AlarmInfoServiceImpl implements AlarmInfoService{
	private Log log = LogFactory.getLog(AlarmInfoServiceImpl.class);

	@Override
	public String selectHouseName(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectHouseConfPlan(Dto pDto, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectAlerttype(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectNumAndSize(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updOrInseAlarmWay(Dto pDto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updOrInseNumAndSize(Dto pDto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String selectNumAndSizeByHouseid(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updOrInseMonthStrInfo(Dto pDto, HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseAlarm(Dto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveEvent(HttpServletRequest request, BigDecimal costTime, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String selectYear(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectPlanByYear(Dto pDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alarmHouseWays(String id, Dto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alarmWaysSystem(Dto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAlarmState(String parkid) {
		// TODO Auto-generated method stub
		
	}

}
