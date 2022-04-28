package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingInfoMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.dataAnalysises.BesWainingInfoService;
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

/**
 * 报警信息统计
 * @author liuzhen
 *
 */
@Service("besWainingInfoData")
public class BesWainingInfoServiceImpl implements BesWainingInfoService{
	private static final Logger log = LoggerFactory.getLogger(BesWainingInfoServiceImpl.class);

	@Autowired
	private BesWainingInfoMapper  besWainingInfoMapper;

	@Override
	public PageInfo<BesWainingInfo> searchWainingInfo(Integer pageNum, BesWainingInfo besWainingInfo) {
		List<BesWainingInfo> pageList;
		if (pageNum == null)
		{
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);

		if (besWainingInfo.getFType().equals("1")){//当报警类型为"全部"的时候
			pageList = besWainingInfoMapper.searchWainingInfoAll(besWainingInfo);
		}else {
			pageList = besWainingInfoMapper.searchWainingInfo(besWainingInfo);
		}

		for(BesWainingInfo besWainingInfo1:pageList){
			besWainingInfo1.setFATime(besWainingInfo1.getFATime().replace(".0",""));
		}

		// 用PageInfo对结果进行包装
		PageInfo<BesWainingInfo> page = new PageInfo<>(pageList);
		return page;
	}


	/**
	 *
	 * @param besWainingInfo
	 * @return
	 * 获取报警信息统计
	 */

	@Override
	public ISSPReturnObject searchWainingInfoCount(BesWainingInfo besWainingInfo) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			// 0：bes_waring_info  1:bes_waring_real 空：从两个表中取
			List<BesWainingInfo> besWainingInfos =null;
			if("0".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

			}
			else if("1".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
			}
			else
			{
				List<BesWainingInfo> dealList = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

				besWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
				//合并相同报警类型的个数
				for (BesWainingInfo wainingInfo:dealList)
				{
					for (BesWainingInfo wainingData:besWainingInfos)
					{
						if(wainingInfo.getFType().equals(wainingData.getFType()))
						{
							wainingData.setTypeCount(Integer.parseInt(wainingData.getTypeCount())+Integer.parseInt(wainingInfo.getTypeCount())+"");
						}

					}
				}
			}

			//封装饼状图所需数据
			List<Map<String,String>> countList = new ArrayList<>();
			for(BesWainingInfo besWainingInfoCount:besWainingInfos)
			{
				Map<String,String> countMap = new HashMap<>();
				countMap.put("value",besWainingInfoCount.getTypeCount());
				countMap.put("name",besWainingInfoCount.getTypeName());
				countMap.put("typeId",besWainingInfoCount.getFType());

				countList.add(countMap);
			}
			returnObject.setList(countList);
			returnObject.setMsg("获取报警信息统计成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取报警信息统计失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject searchWainingData(BesWainingInfo besWainingInfo) {

		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			// 0：bes_waring_info  1:bes_waring_real 空：从两个表中取
			List<Map> besWainingInfos =null;
			if("0".equals(besWainingInfo.getFOpearation())) {
				besWainingInfos = besWainingInfoMapper.searchWainingData(besWainingInfo);
			}
			else if("1".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingReal(besWainingInfo);
			}
			else
			{
				List<Map> mapList = besWainingInfoMapper.searchWainingData(besWainingInfo);
				besWainingInfos = besWainingInfoMapper.searchWainingReal(besWainingInfo);
				for(Map map:besWainingInfos)
				{
					for(Map map1:mapList)
					{
						if(map1.get("F_ALERT_TIME").equals((map.get("F_ALERT_TIME"))))
						{
							int realCount = Integer.parseInt(map.get("count").toString()) ;
							int  count = Integer.parseInt(map1.get("count").toString());
							map.put("count",realCount+count+"");
						}
					}
				}
			}

			if(besWainingInfos != null)
			{
				for(Map map:besWainingInfos){
					map.put("normolCount", "0");
				}
			}

			returnObject.setList(besWainingInfos);
			returnObject.setMsg("获取报警信息统计成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取报警信息统计失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject searchHbWainingPieData(BesWainingInfo besWainingInfo) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			// 0：bes_waring_info  1:bes_waring_real 空：从两个表中取
			List<BesWainingInfo> besWainingInfos =null;
			if("0".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

			}
			else if("1".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
			}
			else
			{
				List<BesWainingInfo> dealList = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

				besWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
				//合并相同报警类型的个数
				for (BesWainingInfo wainingInfo:dealList)
				{
					for (BesWainingInfo wainingData:besWainingInfos)
					{
						if(wainingInfo.getFType().equals(wainingData.getFType()))
						{
							wainingData.setTypeCount(Integer.parseInt(wainingData.getTypeCount())+Integer.parseInt(wainingInfo.getTypeCount())+"");
						}

					}
				}
			}
			//封装环比饼状图所需数据
			List<Map<String,String>> countList = new ArrayList<>();
			Map<String,String> countMap = new HashMap<>();
			int errNum =0;
			//循环获取本期报警个数
			for(BesWainingInfo wainingInfo:besWainingInfos)
			{
				errNum= errNum+ Integer.parseInt(wainingInfo.getTypeCount());
			}
			countMap.put("value",errNum+"");
			countMap.put("name","本期报警");
			countList.add(countMap);

			//封装同期饼状图所需数据
			besWainingInfo.setStartTime(besWainingInfo.getSameStartTime());
			besWainingInfo.setEndTime(besWainingInfo.getSameEndTime());

			if(!"".equals(besWainingInfo.getSameStartTime()) && besWainingInfo.getSameStartTime() !=null &&
					!"".equals(besWainingInfo.getSameEndTime()) && besWainingInfo.getSameEndTime() !=null)
			{
				// 0：bes_waring_info  1:bes_waring_real 空：从两个表中取
				List<BesWainingInfo> besHbWainingInfos =null;
				if("0".equals(besWainingInfo.getFOpearation()))
				{
					besHbWainingInfos = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

				}
				else if("1".equals(besWainingInfo.getFOpearation()))
				{
					besHbWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
				}
				else
				{
					List<BesWainingInfo> dealList = besWainingInfoMapper.searchWainingInfoCount(besWainingInfo);

					besHbWainingInfos = besWainingInfoMapper.searchWainingRealCount(besWainingInfo);
					//合并相同报警类型的个数
					for (BesWainingInfo wainingInfo:dealList)
					{
						for (BesWainingInfo wainingData:besHbWainingInfos)
						{
							if(wainingInfo.getFType().equals(wainingData.getFType()))
							{
								wainingData.setTypeCount(Integer.parseInt(wainingData.getTypeCount())+Integer.parseInt(wainingInfo.getTypeCount())+"");
							}

						}
					}
				}
				Map<String,String> countSameTimeMap = new HashMap<>();
				int errSameNum =0;
                //循环获取同期报警个数
				for(BesWainingInfo wainingInfo:besHbWainingInfos)
				{
					errSameNum= errSameNum+ Integer.parseInt(wainingInfo.getTypeCount());
				}
				countSameTimeMap.put("value",errSameNum+"");
				countSameTimeMap.put("name","同期报警");
				countList.add(countSameTimeMap);
			}
			returnObject.setList(countList);
			returnObject.setMsg("获取环比报警信息统计成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取环比报警信息统计失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 报警同比环比柱状图数据
	 * @param besWainingInfo
	 * @return
	 */
	@Override
	public ISSPReturnObject searchHbWainingBarData(BesWainingInfo besWainingInfo) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String,List<String>> map =new HashMap<>();//返回的map
			List<Map<String,String>> list = new ArrayList();//返回报警数据队列
			List<String> legendData = new ArrayList();//legend数据队列
			List<String> legendCount = new ArrayList();//legend数据队列
			//首先获取本期数据并拼装数据
			// 0：bes_waring_info  1:bes_waring_real 空：从两个表中取
			List<BesWainingInfo> besWainingInfos =null;
			if("0".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingBarData(besWainingInfo);
			}
			else if("1".equals(besWainingInfo.getFOpearation()))
			{
				besWainingInfos = besWainingInfoMapper.searchWainingRealBarData(besWainingInfo);
			}
			else
			{
				List<BesWainingInfo> dealList = besWainingInfoMapper.searchWainingBarData(besWainingInfo);

				besWainingInfos = besWainingInfoMapper.searchWainingRealBarData(besWainingInfo);
				//合并相同报警类型的个数
				for (BesWainingInfo wainingInfo:besWainingInfos)
				{
					for (BesWainingInfo wainingData:dealList)
					{
						if(wainingInfo.getFType().equals(wainingData.getFType()))
						{
							wainingData.setTypeCount(Integer.parseInt(wainingData.getTypeCount())+Integer.parseInt(wainingInfo.getTypeCount())+"");
						}

					}
				}
			}
			for(BesWainingInfo wainingInfo:besWainingInfos)
			{
				Map<String,String> alarmMap = new HashMap();
				alarmMap.put("type",wainingInfo.getFType());
				alarmMap.put("alrmTime",wainingInfo.getFATime().split(" ")[0]);
				alarmMap.put(wainingInfo.getFType()+"count",wainingInfo.getTypeCount());
				legendData.add(wainingInfo.getTypeName());
				legendCount.add(wainingInfo.getFType()+"count");
				list.add(alarmMap);
			}
			for(int i = 0; i < legendCount.size(); i++)
			{
				for(int j = 0; j < list.size(); j++){
					if(!legendCount.get(i).equals(list.get(j).get("type")+"count")){
						list.get(j).put(legendCount.get(i),"0");
					}
				}

			}
			//获取同期数据
			besWainingInfo.setStartTime(besWainingInfo.getSameStartTime());
			besWainingInfo.setEndTime(besWainingInfo.getSameEndTime());
			List<BesWainingInfo> besSameWainingInfos =null;
			if("0".equals(besWainingInfo.getFOpearation()))
			{
				besSameWainingInfos = besWainingInfoMapper.searchWainingBarData(besWainingInfo);

			}
			else if("1".equals(besWainingInfo.getFOpearation()))
			{
				besSameWainingInfos = besWainingInfoMapper.searchWainingRealBarData(besWainingInfo);
			}
			else
			{
				List<BesWainingInfo> dealList = besWainingInfoMapper.searchWainingBarData(besWainingInfo);

				besSameWainingInfos = besWainingInfoMapper.searchWainingRealBarData(besWainingInfo);
				//合并相同报警类型的个数
				for (BesWainingInfo wainingInfo:besSameWainingInfos)
				{
					for (BesWainingInfo wainingData:dealList)
					{
						if(wainingInfo.getFType().equals(wainingData.getFType()))
						{
							wainingData.setTypeCount(Integer.parseInt(wainingData.getTypeCount())+Integer.parseInt(wainingInfo.getTypeCount())+"");
						}

					}
				}
			}
			if(besSameWainingInfos.size()>0){
				for(int i =0;i<list.size();i++)
				{
					for(BesWainingInfo wainingInfo:besSameWainingInfos){
						if("0".equals(besWainingInfo.getComType()))//同比环比比计较情况不同
						{
							if( list.get(i).get("alrmTime").substring(8,10).equals(wainingInfo.getFATime().substring(8,10)) &&list.get(i).get("type").equals(wainingInfo.getFType()))
							{
								list.get(i).put(wainingInfo.getFType()+"samecount",wainingInfo.getTypeCount());
								legendData.add(wainingInfo.getTypeName()+"同期");
								legendCount.add(wainingInfo.getFType()+"samecount");

							}else
							{
								list.get(i).put(wainingInfo.getFType()+"samecount","0");
							}
						}
						else
						{
							if( list.get(i).get("type").equals(wainingInfo.getFType()))
							{
								list.get(i).put(wainingInfo.getFType()+"samecount",wainingInfo.getTypeCount());
								legendData.add(wainingInfo.getTypeName()+"同期");
								legendCount.add(wainingInfo.getFType()+"samecount");

							}else
							{
								list.get(i).put(wainingInfo.getFType()+"samecount","0");
							}
						}

					}

				}

			}

			//legend数据合并
			for (int i = 0; i < list.size()-1; i++) {
				for (int b = list.size()-1; b > i; b--) {
					if (list.get(b).get("alrmTime").equals(list.get(i).get("alrmTime"))) {
						list.get(i).put(list.get(b).get("type")+"count",list.get(b).get(list.get(b).get("type")+"count"));
						list.get(i).put(list.get(b).get("type")+"samecount",list.get(b).get(list.get(b).get("type")+"samecount"));
						list.remove(b);
					}
				}
			}

			//legend数据去重
			for (int i = 0; i < legendData.size()-1; i++) {
				for (int b = legendData.size()-1; b > i; b--) {
					if (legendData.get(b)!=null&&legendData.get(i)!=null&&legendData.get(b).equals(legendData.get(i))) {
						legendData.remove(b);
					}
				}
			}

			//legend对应值去重
			for (int i = 0; i < legendCount.size()-1; i++) {
				for (int b = legendCount.size()-1; b > i; b--) {
					if (legendCount.get(b)!=null&&legendCount.get(i)!=null&&legendCount.get(b).equals(legendCount.get(i))) {
						legendCount.remove(b);
					}
				}
			}
			map.put("legendData",legendData);
			map.put("legendCount",legendCount);

			returnObject.setList(list);
			returnObject.setMap(map);
			returnObject.setMsg("获取环比报警柱状图信息统计成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取环比报警柱状图信息统计失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}


}
