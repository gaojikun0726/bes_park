package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.dataAnalysises.BESSubitemDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;
import com.efounder.JEnterprise.service.dataAnalysises.BESSubitemDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 趋势分析接口实现类
 * @author LvSihan
 *
 */
@Service("getSubitemData")
public class BESSubitemDataServiceImpl implements BESSubitemDataService{
	private static final Logger log = LoggerFactory.getLogger(BESSubitemDataServiceImpl.class);

	@Autowired
	private BESSubitemDataMapper subitemdatamapper;
	
	@Override
	public ISSPReturnObject getSubitemData(BESSubitemData besSubitemData) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> arr = subitemdatamapper.getSubitemData(besSubitemData);
			returnObject.setData(arr);
			returnObject.setMsg("success");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("fail");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public PageInfo<BESSubitemData> getSubitemDataTab(Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<BESSubitemData> moduletype = subitemdatamapper.getSubitemDataTab(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<BESSubitemData> page = new PageInfo<BESSubitemData>(moduletype);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	@Override
	public List<BESSubitemData> getSubitemDataList(BESSubitemData besSubitemData) {
		List<BESSubitemData> list =subitemdatamapper.getSubitemDataList(besSubitemData);
		return list;
	}
	
	/**
	 *
	 * @param besSubitemData
	 * @return
	 * 获取分项用能趋势统计数据
	 */
	@Override
	public ISSPReturnObject getQstjSubitemData(BESSubitemData besSubitemData){
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESSubitemData> list =subitemdatamapper.getQstjSubitemData(besSubitemData);
			Map<String,List<BESSubitemData>> subNameDataMap = new HashMap<>();//存取分项名称与map
			list = list.stream().distinct().collect(Collectors.toList()); // 去重
			//合并数据
			for (BESSubitemData besSum : list){
				if (subNameDataMap.containsKey(besSum.getfFxbh())){
					subNameDataMap.get(besSum.getfFxbh()).add(besSum);
				}else{

					List<BESSubitemData> subitemdatas = new ArrayList<>();//存取数据list
					subitemdatas.add(besSum);
					subNameDataMap.put(besSum.getfFxbh(), subitemdatas);
				}
			}
			returnObject.setList(list);
			returnObject.setMap(subNameDataMap);
			returnObject.setMsg("获取分项用能趋势统计数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分项用能趋势统计数据失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	
	/**
	 *
	 * @param besSubitemData
	 * @return
	 * 获取分项用能趋势统计数据
	 */
	@Override
	public ISSPReturnObject getTbhbSubitemData(BESSubitemData besSubitemData){
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESSubitemData> list =subitemdatamapper.getQstjSubitemData(besSubitemData);
			List<BESSubitemData> timeList = new ArrayList<>();//所有时间list
			List<BESSubitemData> nameList = new ArrayList<>();//所有分项list

			Map<String,List<BESSubitemData>> map = new HashMap<>();//时间map
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
					if (nameList.get(b).getfFxbh().equals(nameList.get(i).getfFxbh())) {
						nameList.remove(b);
					}
				}
			}
			
			List<BESSubitemData> bedSubitemDatas = null;
			//获取本期数据
			for(int j = 0;j < besSubitemData.getfFxbhs().size(); j++)
			{
				bedSubitemDatas = new ArrayList<>();
				if(list != null&&list.size() != 0)
				{
					for(int i =0;i<list.size();i++) 
					{
						if(list.get(i).getfFxbh().equals(besSubitemData.getfFxbhs().get(j)) 
)
						{
							bedSubitemDatas.add(list.get(i));	
						}

					}
					if(bedSubitemDatas.size()>0)
					{
						
						for (int i = 0; i < bedSubitemDatas.size()-1; i++) {
							for (int b = bedSubitemDatas.size()-1; b > i; b--) {
								if (bedSubitemDatas.get(b).getfCjsj().equals(bedSubitemDatas.get(i).getfCjsj())) {
									bedSubitemDatas.remove(b);
								}
							}
						}

						map.put(besSubitemData.getfFxbhs().get(j), bedSubitemDatas);	
					}
				}
				
			}
			
			//获取同期数据并处理
			besSubitemData.setfCjsj_start(besSubitemData.getFtbhb_sametime_start());
			besSubitemData.setfCjsj_end(besSubitemData.getFtbhb_sametime_end());
			List<BESSubitemData> sameTimelist =subitemdatamapper.getQstjSubitemData(besSubitemData);
			
			List<BESSubitemData> sameTimeDatas =null;
			for(int j = 0;j < besSubitemData.getfFxbhs().size(); j++)
			{
				sameTimeDatas = new ArrayList<>();
				if(sameTimelist != null&&sameTimelist.size() != 0)
				{
					for(int i =0;i<sameTimelist.size();i++) 
					{
						if(sameTimelist.get(i).getfFxbh().equals(besSubitemData.getfFxbhs().get(j)))
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
			
						map.put(besSubitemData.getfFxbhs().get(j)+"_sametime", sameTimeDatas);	
					}
				}
			}
			//对同期数据进行处理
			for(int j = 0;j < besSubitemData.getfFxbhs().size(); j++)
			{
				List<BESSubitemData> timeDatas = new ArrayList<>();
				List<BESSubitemData> nowList=null;
				List<BESSubitemData> sameList=null;
				if(map.get(besSubitemData.getfFxbhs().get(j))!=null)
				{
					nowList = map.get(besSubitemData.getfFxbhs().get(j));
					sameList = map.get(besSubitemData.getfFxbhs().get(j)+"_sametime");
				}
				
				if(nowList !=null&&nowList.size()!=0&&sameList !=null&&sameList.size()!=0) {
					for (int c = 0; c < sameList.size(); c++) {
						BESSubitemData subitem = sameList.get(c);
						timeDatas.add(subitem);
					}

				}
				else if(nowList !=null&&nowList.size()!=0&&(sameList == null || sameList.size()==0))
				{
					for(int i=0;i<nowList.size();i++) {
						BESSubitemData besSubitem =new BESSubitemData();
						besSubitem.setfData(0.00);
						besSubitem.setfAllMoney("0.00");
						besSubitem.setfCo2("0.00");
						besSubitem.setfCoalAmount("0.00");
						besSubitem.setfPercapitaEnergy("0.00");
						besSubitem.setfPercapitaMoney("0.00");
						besSubitem.setfUnitareaData("0.00");
						besSubitem.setfUnitareaMoney("0.00");
						timeDatas.add(besSubitem);
					}
				}
				
				if(timeDatas!=null && timeDatas.size()!=0)
				{
					map.put(besSubitemData.getfFxbhs().get(j)+"_same", timeDatas);
					
				}
			}


			map.put("time", timeList);
			map.put("brach", nameList);
			returnObject.setMap(map);
			returnObject.setMsg("获取分项用能同比环比数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分项用能同比环比数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getSubentryData(BESSubitemData besSubitemData) {

		ISSPReturnObject isspReturnObject = new ISSPReturnObject();

		if(null == besSubitemData){
			isspReturnObject.setStatus("0");
			return isspReturnObject;
		}

		try {

			List<BESSubitemData>  list = subitemdatamapper.getSubentryData(besSubitemData);

			isspReturnObject.setStatus("1");
			isspReturnObject.setData(list);

		} catch (Exception e) {
			isspReturnObject.setStatus("0");
			e.printStackTrace();
		}

		return isspReturnObject;
	}

}
