package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesHouseholdDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 趋势分析接口实现类
 * @author LvSihan
 *
 */
@Service("getHouseholdData")
public class BesHouseholdDataServiceImpl implements BesHouseholdDataService{
	private static final Logger log = LoggerFactory.getLogger(BesHouseholdDataServiceImpl.class);

	@Autowired
	private BesHouseholdDataMapper besHouseholdDataMapper;

	/**
	 *		今日用能
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能趋势统计数据
	 */
	@Override
	public ISSPReturnObject getHouseholdData(BesHouseholdData besHouseholdData, HttpServletRequest request){

		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, String> DtoMap = new HashMap<>();
			Map<String,Object> energyInformation = new HashMap<>();
			Map<String, String> energyInformationMap = new HashMap<>();//能源信息的Map
			//查询所有的能源类型
			DtoMap.put("fFhbh", request.getParameter("fFhbhs"));
			DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
//			DtoMap.put("fCjsj_start", "2020-07-13");
			DtoMap.put("fCjsj_end", request.getParameter("fCjsj_end"));
			DtoMap.put("fNybh", request.getParameter("nybh"));
			DtoMap.put("fType", request.getParameter("fType"));
			DtoMap.put("yqbh", request.getParameter("yqbh"));

			if (DtoMap.get("fNybh") != null) {

				//根据能源编号查询能源信息
				energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(DtoMap.get("fNybh"));
				if (!energyInformation.isEmpty() && energyInformation.size() > 0){
					energyInformationMap.put("nymc", (String) energyInformation.get("F_NYMC"));
					energyInformationMap.put("nydw", (String) energyInformation.get("F_UNIT"));
				}

				if (DtoMap.get("fFhbh").equals("1") && energyInformation != null ){//水,电,气等节点
					//查询水,电,气下园区的数据
					List<Map<String,Object>> searchHouseholdDataByPark = besHouseholdDataMapper.searchHouseholdDataByPark(DtoMap.get("yqbh"),DtoMap.get("fNybh"),DtoMap.get("fCjsj_start"));

					Map<String, Object> dataMap = new HashMap<>();

					List<Map<String,Object>> eded = new ArrayList<>();

					if (searchHouseholdDataByPark.size() > 0){

						searchHouseholdDataByPark.forEach((datum) -> {
							String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datum.get("f_cjsj"));

							Object o = dataMap.get(time);

							if (null == o)
							{
								BigDecimal data = new BigDecimal((Double) datum.get("f_data"));
								dataMap.put(time, data.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
							}else
							{
								BigDecimal aa = new BigDecimal(Double.valueOf(String.valueOf(dataMap.get(time))));
								BigDecimal bb = new BigDecimal(Double.valueOf(String.valueOf(datum.get("f_data"))));
						/*Double aa = (Double) dataMap.get(time);
						Double bb = (Double) datum.get("f_data");*/
								Double cc = aa.add(bb).doubleValue();

								BigDecimal data = new BigDecimal((Double) cc);
								dataMap.put(time,data.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
								//1、 datum 和 o 值相加
								// 计算好添加到 dataMap
							}

						});

						Iterator iter = dataMap.entrySet().iterator();  //获得map的Iterator
						while(iter.hasNext()) {
							Map.Entry entry = (Map.Entry)iter.next();
							Map<String,Object> hhf = new HashMap<>();
							hhf.put("time",entry.getKey());
							hhf.put("date",entry.getValue());
							String aacc = (String) entry.getKey();
							hhf.put("timetest",aacc.substring(11,13));
							hhf.put("nybh",DtoMap.get("fNybh"));
							eded.add(hhf);
						}

						//匿名实现Comparator接口进行排序
			/*	Collections.sort(eded, new Comparator<Map<String,Object>>() {
					@Override
					public int compare(Map<String,Object> o1, Map<String,Object> o2) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
						Double aa = Double.parseDouble((String) o1.get("timetest")) ;
						Double bb = Double.parseDouble((String) o2.get("timetest"));
						//进行判断
						return aa.compareTo(bb);
					}
				});*/

						Collections.sort(eded, (o1, o2) ->{
							Double aa = Double.parseDouble((String) o1.get("timetest")) ;
							Double bb = Double.parseDouble((String) o2.get("timetest"));
							//进行判断
							return aa.compareTo(bb);
						});

						returnObject.setList(eded);
						returnObject.setMsg("获取分户用能趋势统计分析数据成功");
						returnObject.setStatus("1");

						returnObject.setData(energyInformationMap);
						return returnObject;
					} else {
						Map<String,Object> hhf = new HashMap<>();
						hhf.put("nybh",DtoMap.get("fNybh"));
						eded.add(hhf);
						returnObject.setList(eded);
						returnObject.setMsg("获取分户用能趋势统计分析数据失败");
						returnObject.setStatus("0");

						returnObject.setData(energyInformationMap);
						return returnObject;
					}
				}else {
					List<BesHouseholdData> list =besHouseholdDataMapper.searchHouseholdData(besHouseholdData);
					List<BesHouseholdData> timeList = new ArrayList<>();//所有时间list
					List<BesHouseholdData> nameList = new ArrayList<>();//所有分项list

					Map<String,List<BesHouseholdData>> map = new HashMap<>();//时间map
					//获取所有时间list
					if(list != null&&list.size() != 0)
					{
						for(int i =0;i<list.size();i++)
						{
							if(i==0 || !list.get(i).getfCjsj().equals(list.get(i-1).getfCjsj().trim()) )
							{
								timeList.add(list.get(i));
							}

						}
					}

					//获取所有分项名称list
					if(list != null&&list.size() != 0)
					{
						for(int i =0;i<list.size();i++)
						{
							nameList.add(list.get(i));
						}
					}
					//分项名称去重
					for (int i = 0; i < nameList.size()-1; i++) {
						for (int b = nameList.size()-1; b > i; b--) {
							if (nameList.get(b).getfFhbh().equals(nameList.get(i).getfFhbh())) {
								nameList.remove(b);
							}
						}
					}

					List<BesHouseholdData> besHouseholdDatas = null;
					//获取本期数据
					for(int j = 0;j < besHouseholdData.getfFhbhs().size(); j++)
					{
						besHouseholdDatas = new ArrayList<>();
						if(list != null&&list.size() != 0)
						{
							for(int i =0;i<list.size();i++)
							{
								if(list.get(i).getfFhbh().equals(besHouseholdData.getfFhbhs().get(j)))

								{
									besHouseholdDatas.add(list.get(i));
								}

							}
							if(besHouseholdDatas.size()>0)
							{

								for (int i = 0; i < besHouseholdDatas.size()-1; i++) {
									for (int b = besHouseholdDatas.size()-1; b > i; b--) {
										if (besHouseholdDatas.get(b).getfCjsj().equals(besHouseholdDatas.get(i).getfCjsj())) {
											besHouseholdDatas.remove(b);
										}
									}
								}

								map.put(besHouseholdData.getfFhbhs().get(j), besHouseholdDatas);
							}
						}

					}

					map.put("time", timeList);
					map.put("houseName", nameList);

					returnObject.setData(energyInformationMap);
					returnObject.setMap(map);
					returnObject.setMsg("获取分户用能趋势统计分析数据成功");
					returnObject.setStatus("1");
				}
			} else {

				List<BesHouseholdData> list =besHouseholdDataMapper.searchHouseholdData(besHouseholdData);
				List<BesHouseholdData> timeList = new ArrayList<>();//所有时间list
				List<BesHouseholdData> nameList = new ArrayList<>();//所有分项list

				Map<String,List<BesHouseholdData>> map = new HashMap<>();//时间map
				//获取所有时间list
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++)
					{
						if(i==0 || !list.get(i).getfCjsj().equals(list.get(i-1).getfCjsj().trim()) )
						{
							timeList.add(list.get(i));
						}

					}
				}

				//获取所有分项名称list
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++)
					{
						nameList.add(list.get(i));
					}
				}
				//分项名称去重
				for (int i = 0; i < nameList.size()-1; i++) {
					for (int b = nameList.size()-1; b > i; b--) {
						if (nameList.get(b).getfFhbh().equals(nameList.get(i).getfFhbh())) {
							nameList.remove(b);
						}
					}
				}

				List<BesHouseholdData> besHouseholdDatas = null;
				//获取本期数据
				for(int j = 0;j < besHouseholdData.getfFhbhs().size(); j++)
				{
					besHouseholdDatas = new ArrayList<>();
					if(list != null&&list.size() != 0)
					{
						for(int i =0;i<list.size();i++)
						{
							if(list.get(i).getfFhbh().equals(besHouseholdData.getfFhbhs().get(j)))

							{
								besHouseholdDatas.add(list.get(i));
							}

						}
						if(besHouseholdDatas.size()>0)
						{

							for (int i = 0; i < besHouseholdDatas.size()-1; i++) {
								for (int b = besHouseholdDatas.size()-1; b > i; b--) {
									if (besHouseholdDatas.get(b).getfCjsj().equals(besHouseholdDatas.get(i).getfCjsj())) {
										besHouseholdDatas.remove(b);
									}
								}
							}

							map.put(besHouseholdData.getfFhbhs().get(j), besHouseholdDatas);
						}
					}

				}

				map.put("time", timeList);
				map.put("houseName", nameList);

				returnObject.setData(energyInformationMap);
				returnObject.setMap(map);
				returnObject.setMsg("获取分户用能趋势统计分析数据成功");
				returnObject.setStatus("1");
			}

		} catch (Exception e) {
			returnObject.setMsg("获取分户用能趋势统计分析数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 *
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能同比环比统计数据
	 */
	@Override
	public ISSPReturnObject getTbhbHouseholdData(BesHouseholdData besHouseholdData){
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesHouseholdData> list =besHouseholdDataMapper.searchHouseholdData(besHouseholdData);
			List<BesHouseholdData> timeList = new ArrayList<>();//所有时间list
			List<BesHouseholdData> nameList = new ArrayList<>();//所有分项list

			Map<String,List<BesHouseholdData>> map = new HashMap<>();//时间map
			//获取所有时间list
			if(list != null&&list.size() != 0)
			{
				for(int i =0;i<list.size();i++) 
				{
					if(i==0 || !list.get(i).getfCjsj().equals(list.get(i-1).getfCjsj().trim()) )
					{
						timeList.add(list.get(i));	
					}
					
				}
			}
			
			//获取所有分项名称list
			if(list != null&&list.size() != 0)
			{
				for(int i =0;i<list.size();i++) 
				{					
					nameList.add(list.get(i));						
				}
			}
			//分项名称去重
			for (int i = 0; i < nameList.size()-1; i++) {
				for (int b = nameList.size()-1; b > i; b--) {
					if (nameList.get(b).getfFhbh().equals(nameList.get(i).getfFhbh())) {
						nameList.remove(b);
					}
				}
			}
			
			List<BesHouseholdData> besHouseholdDatas = null;
			//获取本期数据
			for(int j = 0;j < besHouseholdData.getfFhbhs().size(); j++)
			{
				besHouseholdDatas = new ArrayList<>();
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++) 
					{
						if(list.get(i).getfFhbh().equals(besHouseholdData.getfFhbhs().get(j))) 

						{
							besHouseholdDatas.add(list.get(i));	
						}
						
					}
					if(besHouseholdDatas.size()>0)
					{
						
						for (int i = 0; i < besHouseholdDatas.size()-1; i++) {
							for (int b = besHouseholdDatas.size()-1; b > i; b--) {
								if (besHouseholdDatas.get(b).getfCjsj().equals(besHouseholdDatas.get(i).getfCjsj())) {
									besHouseholdDatas.remove(b);
								}
							}
						}
						
						map.put(besHouseholdData.getfFhbhs().get(j), besHouseholdDatas);	
					}
				}
				
			}
			
			//获取同期数据并处理
			besHouseholdData.setfCjsj_start(besHouseholdData.getFtbhb_sametime_start());
			besHouseholdData.setfCjsj_end(besHouseholdData.getFtbhb_sametime_end());
			List<BesHouseholdData> sameTimelist =besHouseholdDataMapper.searchHouseholdData(besHouseholdData);
			
			List<BesHouseholdData> sameTimeDatas =null;
			for(int j = 0;j < besHouseholdData.getfFhbhs().size(); j++)
			{
				sameTimeDatas = new ArrayList<>();
				if(sameTimelist != null&&sameTimelist.size() != 0)
				{
					for(int i =0;i<sameTimelist.size();i++) 
					{
						if(sameTimelist.get(i).getfFhbh().equals(besHouseholdData.getfFhbhs().get(j)))
						{
							sameTimeDatas.add(sameTimelist.get(i));	
						}
						
						
					}
					if(sameTimeDatas.size()>0)
					{
						//同期数据去除相同分项且相同时间的数据
						for (int i = 0; i < sameTimeDatas.size()-1; i++) {
							for (int b = sameTimeDatas.size()-1; b > i; b--) {
								if (sameTimeDatas.get(b).getfCjsj().equals(sameTimeDatas.get(i).getfCjsj())) {
									sameTimeDatas.remove(b);
								}
							}
						}
			
						map.put(besHouseholdData.getfFhbhs().get(j)+"_sametime", sameTimeDatas);	
					}
				}
				
				
			}
			//对同期数据进行处理
			for(int j = 0;j < besHouseholdData.getfFhbhs().size(); j++)
			{
				List<BesHouseholdData> timeDatas = new ArrayList<>();
				List<BesHouseholdData> nowList=null;
				List<BesHouseholdData> sameList=null;
				if(map.get(besHouseholdData.getfFhbhs().get(j))!=null)
				{
					nowList = map.get(besHouseholdData.getfFhbhs().get(j));//获取所有同期数据
					sameList = map.get(besHouseholdData.getfFhbhs().get(j)+"_sametime");//获取所有同期数据
				}
				
				if(nowList !=null&&nowList.size()!=0&&sameList !=null&&sameList.size()!=0) {
					for(int c=0;c<sameList.size();c++)
					{
						BesHouseholdData houseHold = sameList.get(c);
						timeDatas.add(houseHold);
					}

				}
				else if(nowList !=null&&nowList.size()!=0&&(sameList == null || sameList.size()==0))
				{
					for(int i=0;i<nowList.size();i++) {
						BesHouseholdData houseHoldData =new BesHouseholdData();
						houseHoldData.setfData(0.00);
						houseHoldData.setfAllMoney(0.00);
						houseHoldData.setfCo2(0.00);
						houseHoldData.setfCoalAmount(0.00);
						houseHoldData.setfPermanData(0.00);
						houseHoldData.setfPermanMoney(0.00);
						houseHoldData.setfUnitareaData(0.00);
						houseHoldData.setfUnitareaMoney(0.00);
						timeDatas.add(houseHoldData);
					}
				}
				
				if(timeDatas!=null && timeDatas.size()!=0)
				{
					map.put(besHouseholdData.getfFhbhs().get(j)+"_same", timeDatas);
					
				}
			}
			map.put("time", timeList);
			map.put("houseName", nameList);
			returnObject.setMap(map);
			returnObject.setMsg("获取分户用能同比环比统计数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分户用能同比环比统计数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 重新改造每月数据
	 * @param besHouseholdData
	 * @param request
	 * @return
	 */
	@Override
	public ISSPReturnObject getHouseholdEveryData(BesHouseholdData besHouseholdData, HttpServletRequest request) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {
			Map<String, String> DtoMap = new HashMap<>();
			Map<String, String> energyInformationMap = new HashMap<>();//能源信息的Map
			//查询所有的能源类型
			DtoMap.put("fFhbh", request.getParameter("fFhbhs"));
			DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
//			DtoMap.put("fCjsj_start", "2020-07-13");
			DtoMap.put("fCjsj_end", request.getParameter("fCjsj_end"));
			DtoMap.put("fNybh", request.getParameter("nybh"));
			DtoMap.put("fType", request.getParameter("fType"));
			DtoMap.put("yqbh", request.getParameter("yqbh"));


			if (DtoMap.get("fFhbh").equals("1")  && DtoMap.get("fFhbh") != null) {//水,电,气等节点

				//根据能源编号查询能源信息
				Map<String, Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(DtoMap.get("fNybh"));
				if (!energyInformation.isEmpty()) {
					energyInformationMap.put("nymc", (String) energyInformation.get("F_NYMC"));
					energyInformationMap.put("nydw", (String) energyInformation.get("F_UNIT"));
				}

				//获取本月第一天和最后一天 让获取的值在本月内
				SimpleDateFormat myFormater = new SimpleDateFormat("yy-MM--dd HH:mm:ss");//格式化时间

				Calendar calendar = Calendar.getInstance();//得到日历

				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);


				Calendar cal = Calendar.getInstance();

				calendar.set(Calendar.DAY_OF_MONTH, 1);

				cal.set(Calendar.DATE, 1);

				cal.roll(Calendar.DATE, -1);
				cal.set(Calendar.HOUR, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);

				//本月月初
				String cjsj_start = myFormater.format(calendar.getTimeInMillis());

				String cjsj_end = myFormater.format(cal.getTime());

				List<Map<String, Object>> searchHouseholdmouthData = besHouseholdDataMapper.GetHouseMouthData(DtoMap.get("fNybh"), cjsj_start, cjsj_end);

				List<Map<String, Object>> mouthData = new ArrayList<>();

				Map<String, Object> map = new HashMap<>();
				List sj = new ArrayList();//放时间的list
				List dt = new ArrayList();//放数据的list
				List bh = new ArrayList();//放能源编号的list
				if (searchHouseholdmouthData.size() > 0) {
					Double Data = 0.0;
					Double time2 = 0.0;
					String nybh = null;
					Double fdata = 0.0;
					map.put("Xtime", sj);
					map.put("Ydata", dt);
					map.put("nymc", energyInformationMap.get("nymc"));
					map.put("nydw", energyInformationMap.get("nydw"));
					String timeEnd = null;
					Double DataEnd = 0.0;
					Double time5 = 0.0;
					for (int i = 0; i < searchHouseholdmouthData.size(); i++) {

						String fcjsj = (String) searchHouseholdmouthData.get(i).get("f_cjsj1");

						fdata = (double) searchHouseholdmouthData.get(i).get("fdata1");

						nybh = (String) searchHouseholdmouthData.get(i).get("f_nybh");

						sj.add(fcjsj);

						dt.add(fdata);

						bh.add(nybh);

					}

					//没有经历for循环 则判断是否只有一条数据 如果只有一条数据证明是每个月的第一天
					if (searchHouseholdmouthData.size() == 1){
						Double data1 = (Double) searchHouseholdmouthData.get(0).get("f_data");
						String day1  = (String) searchHouseholdmouthData.get(0).get("f_cjsj");
						String nybh1  = (String) searchHouseholdmouthData.get(0).get("f_nybh");
						sj.add(day1);
						dt.add(data1);
						bh.add(nybh1);
					}
					if (time5 != 0 && DataEnd != 0) {
						sj.add(time5);
						dt.add(DataEnd);
					}
					if (time2 != 0 && Data != 0) {
						sj.add(time2);
						dt.add(Data);
					}
					bh.add(nybh);
					mouthData.add(map);
					returnObject.setList(mouthData);
				}else {
					List list1 = new ArrayList();
					List list = new ArrayList();
					Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
					int day = aCalendar.getActualMaximum(Calendar.DATE);
					for (int i = 1; i <= day; i++) {
						String days="0";
						if(i<10){
							days="0"+i;
						}else {
							days=String.valueOf(i);
						}
						list.add(days);
						list1.add(0);
					}
					map.put("Xtime", list);
					map.put("Ydata", list1);
					map.put("nymc", energyInformationMap.get("nymc"));
					map.put("nydw", energyInformationMap.get("nydw"));
					mouthData.add(map);
					returnObject.setList(mouthData);
				}
			} else {
				List<BesHouseholdData> list = besHouseholdDataMapper.searchHouseholdData(besHouseholdData);
				List<BesHouseholdData> timeList = new ArrayList<>();//所有时间list
				List<BesHouseholdData> nameList = new ArrayList<>();//所有分项list

				Map<String, List<BesHouseholdData>> map = new HashMap<>();//时间map

				//获取所有时间list
				if (list != null && list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						if (i == 0 || !list.get(i).getfCjsj().equals(list.get(i - 1).getfCjsj().trim())) {
							timeList.add(list.get(i));
						}

					}
				}

				//获取所有分项名称list
				if (list != null && list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						nameList.add(list.get(i));
					}
				}
				//分项名称去重
				for (int i = 0; i < nameList.size() - 1; i++) {
					for (int b = nameList.size() - 1; b > i; b--) {
						if (nameList.get(b).getfFhbh().equals(nameList.get(i).getfFhbh())) {
							nameList.remove(b);
						}
					}
				}

				List<BesHouseholdData> besHouseholdDatas = null;
				//获取本期数据
				for (int j = 0; j < besHouseholdData.getfFhbhs().size(); j++) {
					besHouseholdDatas = new ArrayList<>();
					if (list != null && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getfFhbh().equals(besHouseholdData.getfFhbhs().get(j))) {
								besHouseholdDatas.add(list.get(i));
							}

						}
						if (besHouseholdDatas.size() > 0) {

							for (int i = 0; i < besHouseholdDatas.size() - 1; i++) {
								for (int b = besHouseholdDatas.size() - 1; b > i; b--) {
									if (besHouseholdDatas.get(b).getfCjsj().equals(besHouseholdDatas.get(i).getfCjsj())) {
										besHouseholdDatas.remove(b);
									}
								}
							}

							map.put(besHouseholdData.getfFhbhs().get(j), besHouseholdDatas);
						}
					}

				}


				Map<String, Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(request.getParameter("nybh"));

				List Nymc = new ArrayList();
				List Nydw = new ArrayList();
				if (!energyInformation.isEmpty()) {
					Nymc.add(energyInformation.get("F_NYMC"));
					Nydw.add(energyInformation.get("F_UNIT"));
				}
				map.put("time", timeList);
				map.put("houseName", nameList);
				map.put("nymc",Nymc);
				map.put("nydw",Nydw);
				returnObject.setData(energyInformationMap);
				returnObject.setMap(map);
				returnObject.setMsg("获取分户用能趋势统计分析数据成功");
				returnObject.setStatus("1");
			}
		} catch (Exception e) {
			returnObject.setMsg("获取分户用能趋势统计分析数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 获取年数据和月峰值
	 * @param request
	 * @return
	 */
	@Override
	public ISSPReturnObject getHouseDataYearData(HttpServletRequest request) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		try{
			String firstDay = request.getParameter("firstDay");//年第一天
			String endData = request.getParameter("endData");
			String yqbh = request.getParameter("yqbh");
			String fhbh = request.getParameter("fhbh");
			String secondDay = request.getParameter("secondDay");//年第二天
			Map<String,Object> map = new HashMap<>();
			String fdata = besHouseholdDataMapper.selectHouseHoldDataByYear(fhbh,yqbh,firstDay);//获取本年年数据
			String hdata = besHouseholdDataMapper.selectHouseHoldByHighMonth(fhbh,yqbh,firstDay,endData);//获取本年年峰值
			//分户年数据
			map.put("fdata",fdata);
			//分户月峰值
			map.put("hdata",hdata);
			returnObject.setMap(map);

		} catch (Exception e) {

		returnObject.setMsg("获取分户用能趋势统计分析数据失败");
		returnObject.setStatus("0");
		e.printStackTrace();

		}
		return returnObject;
	}



	/**
	 * @Description: 获取分户用能趋势统计分析数据
	 * @param:
	 * @return:
	 */
	@Override
	public ISSPReturnObject queryHouseHoldData(BesHouseholdData besHouseholdData) {

		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {

			List<BesHouseholdData> list =besHouseholdDataMapper.searchHouseholdData(besHouseholdData);

			returnObject.setData(list);

			returnObject.setMsg("获取分户用能趋势统计分析数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分户用能趋势统计分析数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}
}
