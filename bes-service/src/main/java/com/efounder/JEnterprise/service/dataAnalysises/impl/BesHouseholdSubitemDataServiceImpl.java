package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesHouseholdSubitemDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdSubitemData;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdSubitemDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 趋势分析接口实现类
 * @author LvSihan
 *
 */
@Service("getHouseholdSubitemData")
public class BesHouseholdSubitemDataServiceImpl implements BesHouseholdSubitemDataService{
	private static final Logger log = LoggerFactory.getLogger(BesHouseholdSubitemDataServiceImpl.class);

	@Autowired
	private BesHouseholdSubitemDataMapper besHouseholdSubitemDataMapper;

	
	/**
	 *
	 * @param besHouseholdSubitemData
	 * @return
	 * 获取分户分项同比环比统计数据
	 */
	@Override
	public ISSPReturnObject getHouseholdSubitemData(BesHouseholdSubitemData besHouseholdSubitemData){
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesHouseholdSubitemData> list =besHouseholdSubitemDataMapper.searchHouseholdSubitemData(besHouseholdSubitemData);//获取所有数据
			List<BesHouseholdSubitemData> timeList = new ArrayList<>();//所有时间list
			List<BesHouseholdSubitemData> nameList = new ArrayList<>();//所有分项list
			List<BesHouseholdSubitemData> housueNameList = new ArrayList<>();//所有分项list

			Map<String,List<BesHouseholdSubitemData>> map = new HashMap<>();//时间map
			//获取所有时间list
			if(list != null&&list.size() != 0)
			{
				for(int i =0;i<list.size();i++) 
				{
					if ((i == 0 || !list.get(i).getfCjsj().equals(list.get(i - 1).getfCjsj().trim())) && list.get(i).getfFxmc() != null)
					{
						timeList.add(list.get(i));
					}
					
				}
			}
			
			//获取所有分户名称list
			if(list != null&&list.size() != 0)
			{
				for(int i =0;i<list.size();i++) 
				{					
					nameList.add(list.get(i));						
				}
			}
			//分户名称去重
			for (int i = 0; i < nameList.size()-1; i++) {
				for (int b = nameList.size()-1; b > i; b--) {
					if (nameList.get(b).getfFhbh().equals(nameList.get(i).getfFhbh())) {
						nameList.remove(b);
					}
				}
			}
			
			//获取所有分户分项名称list
			if(list != null&&list.size() != 0)
			{
				for(BesHouseholdSubitemData besHousehold:list) 
				{

					if(besHousehold.getfFxbh()!=null && besHousehold.getfFxmc()!=null)
					{
						besHousehold.setfFhmc(besHousehold.getfFhmc()+"-"+besHousehold.getfFxmc());
						housueNameList.add(besHousehold);
					}



				}
			}
			//分户名称去重
			for (int i = 0; i < housueNameList.size()-1; i++) {
				for (int b = housueNameList.size()-1; b > i; b--) {
					if(housueNameList.get(b).getfFxbh()!=null && housueNameList.get(i).getfFxbh() !=null )
					{
						if (housueNameList.get(b).getfFhbh().equals(housueNameList.get(i).getfFhbh())
								&& housueNameList.get(b).getfFxbh().equals(housueNameList.get(i).getfFxbh())) {
							housueNameList.remove(b);
						}
					}

				}
			}
			
			
			List<BesHouseholdSubitemData> besHouseholdSubitemFxDatas = null;
			//组装分户分项map
			for(int j = 0;j < besHouseholdSubitemData.getfFhbhs().size(); j++)
			{
				besHouseholdSubitemFxDatas = new ArrayList<>();
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++) 
					{
						if(list.get(i).getfFhbh().equals(besHouseholdSubitemData.getfFhbhs().get(j)) && list.get(i).getfFxbh()!=null && list.get(i).getfFxmc()!=null)
						{
							besHouseholdSubitemFxDatas.add(list.get(i));
						}
						
					}
					if(besHouseholdSubitemFxDatas.size()>0)
					{

						for (int i = 0; i < besHouseholdSubitemFxDatas.size()-1; i++) {
							for (int b = besHouseholdSubitemFxDatas.size()-1; b > i; b--) {
								if(besHouseholdSubitemFxDatas.get(b).getfFxbh()!=null && besHouseholdSubitemFxDatas.get(i).getfFxbh() !=null )
								{
									if (besHouseholdSubitemFxDatas.get(b).getfFhbh().equals(besHouseholdSubitemFxDatas.get(i).getfFhbh())
											&& besHouseholdSubitemFxDatas.get(b).getfFxbh().equals(besHouseholdSubitemFxDatas.get(i).getfFxbh())) {
										besHouseholdSubitemFxDatas.remove(b);
									}
								}

							}
						}

						map.put(besHouseholdSubitemData.getfFhbhs().get(j)+"_fhfx", besHouseholdSubitemFxDatas);
					}
				}
				
			}
			
			
			
			
			List<BesHouseholdSubitemData> besHouseholdSubitemDatas = null;
			//获取本期数据
			for(int j = 0;j < besHouseholdSubitemData.getfFhbhs().size(); j++)
			{
				besHouseholdSubitemDatas = new ArrayList<>();
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++) 
					{
						if(list.get(i).getfFhbh().equals(besHouseholdSubitemData.getfFhbhs().get(j))) 

						{
							besHouseholdSubitemDatas.add(list.get(i));	
						}
						
					}
					if(besHouseholdSubitemDatas.size()>0)
					{

						for (int i = 0; i < besHouseholdSubitemDatas.size()-1; i++) {
							for (int b = besHouseholdSubitemDatas.size()-1; b > i; b--) {
								if(besHouseholdSubitemDatas.get(b).getfFxbh()!=null && besHouseholdSubitemDatas.get(i).getfFxbh() !=null )

									if (besHouseholdSubitemDatas.get(b).getfCjsj().equals(besHouseholdSubitemDatas.get(i).getfCjsj()) &&
										besHouseholdSubitemDatas.get(b).getfFxbh().equals(besHouseholdSubitemDatas.get(i).getfFxbh())) {
									besHouseholdSubitemDatas.remove(b);
								}
							}
						}


						map.put(besHouseholdSubitemData.getfFhbhs().get(j), besHouseholdSubitemDatas);
					}
				}
				
			}
			
			
			//获取同期数据并处理
			besHouseholdSubitemData.setfCjsj_start(besHouseholdSubitemData.getFtbhb_sametime_start());
			besHouseholdSubitemData.setfCjsj_end(besHouseholdSubitemData.getFtbhb_sametime_end());
			List<BesHouseholdSubitemData> sameTimelist =besHouseholdSubitemDataMapper.searchHouseholdSubitemData(besHouseholdSubitemData);
			
			List<BesHouseholdSubitemData> sameTimeDatas =null;
			for(int j = 0;j < besHouseholdSubitemData.getfFhbhs().size(); j++)
			{
				sameTimeDatas = new ArrayList<>();
				if(sameTimelist != null&&sameTimelist.size() != 0)
				{
					for(int i =0;i<sameTimelist.size();i++) 
					{
						if(sameTimelist.get(i).getfFhbh().equals(besHouseholdSubitemData.getfFhbhs().get(j)))
						{
							sameTimeDatas.add(sameTimelist.get(i));	
						}
						
						
					}
					if(sameTimeDatas.size()>0)
					{
						//同期数据去除相同分项且相同时间的数据
						for (int i = 0; i < sameTimeDatas.size()-1; i++) {
							for (int b = sameTimeDatas.size()-1; b > i; b--) {
								if (sameTimeDatas.get(b).getfCjsj().equals(sameTimeDatas.get(i).getfCjsj())
										&& sameTimeDatas.get(b).getfFxbh().equals(sameTimeDatas.get(i).getfFxbh())) {
									sameTimeDatas.remove(b);
								}
							}
						}
						
						map.put(besHouseholdSubitemData.getfFhbhs().get(j)+"_sametime", sameTimeDatas);	
					}
				}
				
				
			}
			//对同期数据进行处理
			for(int j = 0;j < besHouseholdSubitemData.getfFhbhs().size(); j++)
			{
				List<BesHouseholdSubitemData> timeDatas = new ArrayList<>();
				List<BesHouseholdSubitemData> nowList=null;
				List<BesHouseholdSubitemData> sameList=null;
				if(map.get(besHouseholdSubitemData.getfFhbhs().get(j))!=null)
				{
					nowList = map.get(besHouseholdSubitemData.getfFhbhs().get(j));
					sameList = map.get(besHouseholdSubitemData.getfFhbhs().get(j)+"_sametime");
				}
				
				if(nowList !=null&&nowList.size()!=0&&sameList !=null&&sameList.size()!=0) {
					for(int c=0;c<sameList.size();c++)
					{
						BesHouseholdSubitemData houseHold = sameList.get(c);
						timeDatas.add(houseHold);
					}
				}
				else if(nowList !=null&&nowList.size()!=0&&(sameList == null || sameList.size()==0))
				{
					for(int i=0;i<nowList.size();i++) {
						BesHouseholdSubitemData houseHoldData =new BesHouseholdSubitemData();
						houseHoldData.setfData(0.00);
						houseHoldData.setfAllMoney("0.00");
						houseHoldData.setfCo2("0.00");
						houseHoldData.setfCoalAmount("0.00");
						houseHoldData.setfPermanData("0.00");
						houseHoldData.setfPermanMoney("0.00");
						houseHoldData.setfUnitareaData("0.00");
						houseHoldData.setfUnitareaMoney("0.00");
						timeDatas.add(houseHoldData);
					}
				}
				
				if(timeDatas!=null && timeDatas.size()!=0)
				{
					map.put(besHouseholdSubitemData.getfFhbhs().get(j)+"_same", timeDatas);
					
				}
			}
			
			
			map.put("time", timeList);
			map.put("houseName", nameList);
			map.put("houseSubName", housueNameList);
			returnObject.setMap(map);
			returnObject.setMsg("获取分户分项同比等比数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分户分项同比等比数据数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}



}
