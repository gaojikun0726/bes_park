package com.efounder.JEnterprise.service.realtimemonitoring.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesOriginalDataMapper;
import com.efounder.JEnterprise.mapper.realtimemonitoring.BESEnergyMonitoringMapper;
import com.efounder.JEnterprise.service.realtimemonitoring.BESEnergyMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 能效监控
 * 
 * @author LvSihan 
 * @date 2018年12月8日  
 * @version 1.0
 */
@Service("BESEnergyMonitoringService")
public class BESEnergyMonitoringServiceImpl implements BESEnergyMonitoringService{
	private static final Logger log = LoggerFactory.getLogger(BESEnergyMonitoringServiceImpl.class);
	@Autowired
	private  BESEnergyMonitoringMapper besEnergyMonitoringMapper;
	@Autowired
	private BesOriginalDataMapper besOriginalDataMapper;
	
	/**
	 * 过支路ID查询电表数据
	 */	
	public ISSPReturnObject queryAmmeterData(String f_zlbh) {
		log.info("通过支路ID查询电表数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//获取今日0点
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayZero = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(todayZero);
		try {
			List<Map<String,String>> ammeterdata = besEnergyMonitoringMapper.queryAmmeterData(f_zlbh,dateString);
			returnObject.setList(ammeterdata);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
			e.printStackTrace();
		}
		return returnObject;
	}
	/**
	 * 加载echarts所需数据
	 */
	@Override
	public ISSPReturnObject loadAmmeterlinechart(String f_zlbh) {
		log.info("加载echarts所需数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,List<String>> map =new HashMap<>();//返回的map


		List<Map<String,String>> retammeterDataList = new ArrayList<>();
		//获取今日0点
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date todayZero = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(todayZero);
        
		//查询支路包含的电表
		List<Map<String,String>> ammeters = besEnergyMonitoringMapper.queryAmmeterList(f_zlbh);
		List<String> timeList =  null;//横轴时间队列;
		Map retammeterData = null;

		List<Map<String,String>> ammeterDataList =null;
		List<String> newTimeList =null;
        List<String> ammeterList =new ArrayList<>();
		for(int i  =0;i<ammeters.size();i++)
		{
			ammeterList.add(ammeters.get(i).get("F_SYS_NAME"));
		}
		String dnbh = "";
		//查询电表对应的采集参数id
		List<Map<String, String>> electricParams = besOriginalDataMapper.zl_cscx_Select_dncs(ammeterList.get(0));
		for(int i=0;i<electricParams.size();i++){
			if("kwh".equals(electricParams.get(i).get("BS").toLowerCase())){
				dnbh = electricParams.get(i).get("ID");
			}
		}

		if(ammeterList.size()>0)
		{
			ammeterDataList = besEnergyMonitoringMapper.queryAmmeterDataList(ammeterList,dateString,dnbh);
		}
		 //查询该电表今日数据

		List<String> dataList = null;
		String nowdate =dateString.split(" ")[0];
		for(int i  =0;i<ammeters.size();i++)
		{
			timeList =  new ArrayList();
			dataList =new ArrayList<>();
			retammeterData = new HashMap();
		    for(int j =0;j<ammeterDataList.size();j++) {
			    if (ammeters.get(i).get("F_SYS_NAME") != null && ammeterDataList.get(j).get("F_DBSYS_NAME") != null
					  && ammeters.get(i).get("F_SYS_NAME").equals(ammeterDataList.get(j).get("F_DBSYS_NAME"))) {
				    dataList.add(ammeterDataList.get(j).get("F_DATA"));
					timeList.add(DateUtil.dateToStamp(ammeterDataList.get(j).get("F_CJSJ")));
			    }
		    }
			dataList.add("");
			retammeterData.put("name",ammeters.get(i).get("F_NICK_NAME"));
			retammeterData.put("data",dataList);
			retammeterDataList.add(retammeterData);
			if(timeList.size()!=0){
				newTimeList=timeList;
			}
		}

		if(ammeterDataList!=null&&nowdate!=null && ammeterDataList.size()>0 && newTimeList!=null){
			newTimeList.add(DateUtil.dateToStamp(nowdate+" 23:59:59"));
		}

		map.put("data",newTimeList);

		returnObject.setList(retammeterDataList);
		returnObject.setMap(map);
		return returnObject;
	}

}
