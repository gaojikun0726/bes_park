package com.efounder.JEnterprise.service.collectorJob;

import com.core.common.metatype.Dto;
import com.efounder.JEnterprise.service.ESBaseService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
@Service
public interface AlarmInfoService extends ESBaseService{
	//查询支路名称
		public String selectHouseName(Dto pDto)throws SQLException ;
		//查询分户能耗计划配置
		public String selectHouseConfPlan(Dto pDto,HttpServletRequest request)throws SQLException ;
		//查询分户能耗计划配置
		public String selectAlerttype(Dto pDto)throws SQLException ;
		//查询分户能耗计划配置
		public String selectNumAndSize(Dto pDto)throws SQLException ;
		//新增或者修改报警方式
		public void updOrInseAlarmWay(Dto pDto,HttpServletRequest request);
		//新增或者修改人数和面积 按钮
		public void updOrInseNumAndSize(Dto pDto,HttpServletRequest request);
		//查询分户能耗计划配置
		public String selectNumAndSizeByHouseid(Dto pDto)throws SQLException;
		//查询分户能耗计划配置
		public void updOrInseMonthStrInfo(Dto pDto,HttpServletRequest request)throws SQLException;
		//分户报警接口
		public void houseAlarm(Dto dto);	
		//日志接口
		public void saveEvent(HttpServletRequest request, BigDecimal costTime,String description) ;
		//查询年份
		public String selectYear(Dto pDto)throws SQLException ;
//		//查询年份 通过点击的那个年份
		public String selectPlanByYear(Dto pDto)throws SQLException ;
		public 	void  alarmHouseWays(String id,Dto dto);//分户报警方式
		public 	void alarmWaysSystem(Dto dto);//系统报警
		public void updateAlarmState(String parkid);
}
