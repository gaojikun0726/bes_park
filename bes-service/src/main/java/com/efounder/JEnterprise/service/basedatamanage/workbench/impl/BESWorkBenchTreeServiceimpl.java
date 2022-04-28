package com.efounder.JEnterprise.service.basedatamanage.workbench.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.controller.GHEEViewController;
import com.efounder.JEnterprise.mapper.basedatamanage.workbench.BesGztzzjgTreeMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesHouseholdDataMapper;
import com.efounder.JEnterprise.model.BesSubyearData;
import com.efounder.JEnterprise.model.BesWorkbench.BESGztfhzzjg;
import com.efounder.JEnterprise.model.BesWorkbench.BesGztzzjgTree;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.workbench.BESWorkBenchTreeService;
import com.framework.common.utils.Validate_y;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
* @author  杨超
* @version 创建时间：2018年11月30日 上午10:53:17
* @parameter 
* @version 1.0
*/
@Service("/BESWorkBenchTreeService")
public class BESWorkBenchTreeServiceimpl implements BESWorkBenchTreeService {

    @Autowired
    private BesGztzzjgTreeMapper besgztzzjgtreemapper;

    @Autowired
    private BesHouseholdDataMapper besHouseholdDataMapper;

    // 刷新table
    @Override
    public ISSPReturnObject RefreshTable(String nodeId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            // 根据传过来的nodeId查询table内容 差下级
            List<Map<String, String>> table = besgztzzjgtreemapper.RefreshTable(nodeId);
            returnObject.setList(table);
            returnObject.setStatus("1");
            returnObject.setMsg("查询成功");
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
        }
        return returnObject;
    }

    /**
     * 
     * Description:同比环比数据对接
     * @param besHouseholdData
     * @return   
     * @see
     */
    @Override
    public ISSPReturnObject illumination(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<String> legend = new ArrayList<String>();// legend
        List<String> timeData = new ArrayList<String>();// data
        List<Map<String, Object>> seriesData = new ArrayList<>();// data
        /*String fFhbhmc = request.getParameter("fFhbhmc");// 分户名称
        if (Validate_y.noNull(fFhbhmc) && fFhbhmc.length() > 5) {//不同园区命名规则不同
            fFhbhmc = fFhbhmc.substring(fFhbhmc.length() - 5, fFhbhmc.length() - 3);
        }*/
        legend.add("本期");
        legend.add("同期");
        //判断是天还是月 1.天 2.月
        List<BesHouseholdData> bqData = new ArrayList<>();
        List<BesHouseholdData> tqData = new ArrayList<>();

        List<Map<String,Object>> searchHouseholdDataByParkBQ = new ArrayList<>();
        List<Map<String,Object>> searchHouseholdDataByParkTQ = new ArrayList<>();
        Map<String, String> DtoMap = new HashMap<>();

        //查询所有的能源类型
        DtoMap.put("fFhbh", request.getParameter("fId"));
        DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
//        DtoMap.put("fCjsj_start", "2020-07");
        DtoMap.put("fCjsj_end", request.getParameter("fCjsj_end"));
        DtoMap.put("fNybh", request.getParameter("nybh"));
        DtoMap.put("fType", request.getParameter("fType"));
        DtoMap.put("yqbh", request.getParameter("yqbh"));

        //根据能源编号查询能源信息
        Map<String,Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(DtoMap.get("fNybh"));

        if (request.getParameter("fId").equals("1")){//点击水,电,气节点的查询图表信息

            if ("2".equals(request.getParameter("fType"))) {//月
                //查询水,电,气下园区的数据
                searchHouseholdDataByParkBQ = besHouseholdDataMapper.searchHouseholdDataByParkMonthBQ(DtoMap.get("yqbh"),DtoMap.get("fNybh"),DtoMap.get("fCjsj_start"),DtoMap.get("fType"));//本期
                searchHouseholdDataByParkTQ = besHouseholdDataMapper.searchHouseholdDataByParkMonthTQ(DtoMap.get("yqbh"),DtoMap.get("fNybh"),besHouseholdData.getFtbhb_sametime_start(),DtoMap.get("fType"));//同期
                timeData.add(DtoMap.get("fCjsj_start").substring(5,7)+"月");
                 }else {//天
                //查询水,电,气下园区的数据
                searchHouseholdDataByParkBQ = besHouseholdDataMapper.searchHouseholdDataByParkBQ(DtoMap.get("yqbh"),DtoMap.get("fNybh"),DtoMap.get("fCjsj_start"),DtoMap.get("fType"));//本期
                searchHouseholdDataByParkTQ = besHouseholdDataMapper.searchHouseholdDataByParkTQ(DtoMap.get("yqbh"),DtoMap.get("fNybh"),besHouseholdData.getFtbhb_sametime_start(),DtoMap.get("fType"));//同期
                timeData.add(DtoMap.get("fCjsj_start").substring(8,10)+"日");
            }


            Map<String, Object> bqdataMap = new HashMap<>();

            List<Map<String,Object>> bqDataPark = new ArrayList<>();

            if (searchHouseholdDataByParkBQ.size() > 0) {

                searchHouseholdDataByParkBQ.forEach((datum) -> {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datum.get("f_cjsj"));

                    Object o = bqdataMap.get(time);

                    if (null == o) {
                        bqdataMap.put(time, datum.get("f_data"));
                    } else {
                        BigDecimal aa = new BigDecimal(Double.valueOf(String.valueOf(bqdataMap.get(time))));
                        BigDecimal bb = new BigDecimal(Double.valueOf(String.valueOf(datum.get("f_data"))));
						/*Double aa = (Double) dataMap.get(time);
						Double bb = (Double) datum.get("f_data");*/
                        Double cc = aa.add(bb).doubleValue();
                        bqdataMap.put(time, cc);
                        //1、 datum 和 o 值相加
                        // 计算好添加到 dataMap
                    }

                });

                Iterator iter = bqdataMap.entrySet().iterator();  //获得map的Iterator
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Map<String, Object> bqDataParkMap = new HashMap<>();
                    bqDataParkMap.put("time", entry.getKey());
                    bqDataParkMap.put("data", entry.getValue());
                    String aacc = (String) entry.getKey();
                    bqDataParkMap.put("timetest", aacc.substring(8, 10));
                    bqDataParkMap.put("nybh", DtoMap.get("fNybh"));
                    bqDataPark.add(bqDataParkMap);
                }
            }

                Map<String, Object> tqdataMap = new HashMap<>();

                List<Map<String,Object>> tqDataPark = new ArrayList<>();

                if (searchHouseholdDataByParkTQ.size() > 0) {

                    searchHouseholdDataByParkTQ.forEach((datum) -> {
                        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datum.get("f_cjsj"));

                        Object o = tqdataMap.get(time);

                        if (null == o) {
                            tqdataMap.put(time, datum.get("f_data"));
                        } else {
                            BigDecimal aa = new BigDecimal(Double.valueOf(String.valueOf(tqdataMap.get(time))));
                            BigDecimal bb = new BigDecimal(Double.valueOf(String.valueOf(datum.get("f_data"))));
						/*Double aa = (Double) dataMap.get(time);
						Double bb = (Double) datum.get("f_data");*/
                            Double cc = aa.add(bb).doubleValue();
                            tqdataMap.put(time, cc);
                            //1、 datum 和 o 值相加
                            // 计算好添加到 dataMap
                        }

                    });

                    Iterator iters = tqdataMap.entrySet().iterator();  //获得map的Iterator
                    while (iters.hasNext()) {
                        Map.Entry entry = (Map.Entry) iters.next();
                        Map<String, Object> tqDataParkMap = new HashMap<>();
                        tqDataParkMap.put("time", entry.getKey());
                        tqDataParkMap.put("data", entry.getValue());
                        String aacc = (String) entry.getKey();
                        tqDataParkMap.put("timetest", aacc.substring(8, 10));
                        tqDataParkMap.put("nybh", DtoMap.get("fNybh"));
                        tqDataPark.add(tqDataParkMap);
                    }

                }
                    List<Map<String, Object>> allList = new ArrayList<>();

                    List<String> bqlist = new ArrayList<>();
                    List<String> tqlist = new ArrayList<>();

                    for (int i = 0; i < legend.size(); i++) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("name", legend.get(i));
                        map.put("type", "bar");
                        if (i == 0){
                            if (bqDataPark.size() > 0){
                                BigDecimal bq = new BigDecimal((Double) bqDataPark.get(0).get("data"));
                                bqlist.add(String.valueOf(bq.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()) );
                            }
                        }else {
                            if (tqDataPark.size() >0){
                                BigDecimal tq = new BigDecimal((Double) tqDataPark.get(0).get("data"));
                                tqlist.add(String.valueOf(tq.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()) );
                            }
                        }


                        if (i == 0) {
                            map.put("data", bqlist);
                        } else {
                            map.put("data", tqlist);
                        }
                        allList.add(map);
                    }
                    seriesData.addAll(allList);
        }else {
            if ("2".equals(request.getParameter("fType"))) {//年
                bqData = besgztzzjgtreemapper.monthbqData(besHouseholdData);// 环比查询数据 本期
                tqData = besgztzzjgtreemapper.monthtqData(besHouseholdData);// 环比查询数据 同期
                String day = besHouseholdData.getfCjsj_start();
                timeData.add(day.substring(5,7)+"月");
            } else {//天
                bqData = besgztzzjgtreemapper.bqData(besHouseholdData);// 环比查询数据 本期
                tqData = besgztzzjgtreemapper.tqData(besHouseholdData);// 环比查询数据 同期
                String month = besHouseholdData.getfCjsj_start();
                timeData.add(month.substring(8,10)+"日");
            }
            List<String> bqlist = new ArrayList<>();
            List<String> tqlist = new ArrayList<>();
            for (int i = 0; i < bqData.size(); i++) {
                String bqdata = String.valueOf(bqData.get(i).getfData());
                bqlist.add(bqdata);
            }
            for (int j = 0; j < tqData.size(); j++) {
                String tqdata = String.valueOf(tqData.get(j).getfData());
                for (int t = 0; t < timeData.size(); t++) {
                    tqlist.add(tqdata);
                }
            }
            List<Map<String, Object>> allList = new ArrayList<>();
            for (int i = 0; i < legend.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", legend.get(i));
                map.put("type", "bar");
                if (i == 0) {
                    map.put("data", bqlist);
                } else {
                    map.put("data", tqlist);
                }
                allList.add(map);
            }
            seriesData.addAll(allList);
        }

        List<Map<String, Object>> ListAll = new ArrayList<>();
        Map<String, Object> mapAll = new HashMap<>();
        mapAll.put("legend", legend);
        mapAll.put("timeData", timeData);
        mapAll.put("seriesData", seriesData);
        mapAll.put("nymc",energyInformation.get("F_NYMC"));
        mapAll.put("nydw",energyInformation.get("F_UNIT"));
        ListAll.add(mapAll);
        returnObject.setData(ListAll);
        return returnObject;
    }
    /**
     * 
     * Description:工作台 能耗分析
     * @param besHouseholdData
     * @param request
     * @return   
     * @see com.efounder.JEnterprise.service.basedatamanage.workbench.BESWorkBenchTreeService#energyAnalyze(com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ISSPReturnObject energyAnalyze(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
            List<BesHouseholdData> AllData = besgztzzjgtreemapper.TodayAllData(besHouseholdData);// 环比查询数据 本期
        String data = "0.00";
        if (AllData.size() > 0) {
            data = String.valueOf(AllData.get(0).getfData());
        }
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("data", data);
        list.add(map);
        returnObject.setList(list);
        return returnObject;
    }
    
    /**
     * 删除
     * */
	@Override
	public ISSPReturnObject del_WorkbenchTree(String id) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject object = new ISSPReturnObject();
		boolean flag = false;
		List<String> nodeIds = new ArrayList<>();
		findAllSonId(id, nodeIds);
		if(nodeIds.size()>0) {
			flag = besgztzzjgtreemapper.deleteSon(nodeIds)&&besgztzzjgtreemapper.deleteSelf(id);
		}else {
			flag = besgztzzjgtreemapper.deleteSelf(id);
		}
		if(flag) {
			object.setStatus("1");
			object.setMsg("删除成功");
		}else {
			object.setStatus("0");
			object.setMsg("删除失败");
		}
		return object;
	}
	
	/**
	 * 循环获取所有子节点
	 * */
	public List<String> findAllSonId(String id, List<String> nodeIds) {
		List<Map<String, String>> nodeList = besgztzzjgtreemapper.getnodId(id);
		for(Map<String , String>map:nodeList) {
			nodeIds.add(map.get("F_ID"));
			findAllSonId(map.get("F_ID"), nodeIds);
		}
		return nodeIds;
	}
	
	/**
	 * 添加
	 * */
	@Override
	public ISSPReturnObject add_workbenchTree(BesGztzzjgTree besgztzjgtree) {
		ISSPReturnObject object = new ISSPReturnObject();
		try {
			String F_ID = UUIDUtil.getRandom32BeginTimePK();
			besgztzjgtree.setfId(F_ID);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			besgztzjgtree.setfCrdate(df.format(new Date()));
			boolean flag = besgztzzjgtreemapper.add_workbenchTree(besgztzjgtree);
			if(flag = true) {
				object.setStatus("1");
				object.setMsg("操作成功");
			}else {
				object.setStatus("0");
				object.setMsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			object.setStatus("0");
			object.setMsg("数据异常！请重新输入！");
		}
		
		return object;
	}
	/**
	 * 编辑
	 * */
	@Override
	public ISSPReturnObject edit_workbenchTree(BesGztzzjgTree besgztzjgtree) {
		ISSPReturnObject object = new ISSPReturnObject();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			besgztzjgtree.setfCrdate(df.format(new Date()));
			boolean flag = besgztzzjgtreemapper.edit_workbenchTree(besgztzjgtree);
			object.setStatus("1");
			object.setMsg("操作成功");
		} catch (Exception e) {
			object.setStatus("0");
			object.setMsg("操作失败");
		}
		return object;
	}
	
	/**
	 * 查询
	 * */
	@Override
	public ISSPReturnObject serch_workdenchTree(String bh) {
		ISSPReturnObject object = new ISSPReturnObject();
		try {
			List<BesGztzzjgTree> list = besgztzzjgtreemapper.serch_workdenchTree(bh);
			object.setList(list);
			object.setStatus("1");
		} catch (Exception e) {
			object.setStatus("0");
		}
		return object;
	}
	
	/**
	  * 切地图
	 * */
	@Override
	public ISSPReturnObject changeImage(String id) {
		ISSPReturnObject object = new ISSPReturnObject();
		try {
			List<BESGztfhzzjg> data = besgztzzjgtreemapper.changeImage(id);
			object.setList(data);
			object.setStatus("1");
			object.setMsg("成功");
		} catch (Exception e) {
			object.setStatus("0");
			object.setMsg("异常");
		}
		return object;
	}

    /**
     * 刷新工作台左侧和地图中间部分数据
     */
    @Override
    public ISSPReturnObject gztLeftRefresh(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> DtoMap = new HashMap<>();
        DtoMap.put("fId", request.getParameter("fId"));
        DtoMap.put("lastyear", request.getParameter("lastyear"));
        DtoMap.put("lastmonth", request.getParameter("lastmonth"));
        DtoMap.put("month", request.getParameter("month"));
        DtoMap.put("yesterday", request.getParameter("yesterday"));
        DtoMap.put("nhbh", request.getParameter("nhbh"));
        DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
        // 查询分户电表瞬时功率 所有电表功率的和--1.0 先查询分户下所有电表
        double InstantPower = 0.00;// 总瞬时功率
        String isControl = request.getParameter("isControl");// 前台控制 0关闭 1开启
        List<Map<String, String>> dbsysname = new ArrayList<>();
        if ("1".equals(isControl)) {
            dbsysname = besgztzzjgtreemapper.Getdbsysname(DtoMap);// 获取所有电表
        }
        if (!dbsysname.isEmpty()) {// 电表不为空 --查询电表瞬时功率
            List<String> dbsysnameList = new ArrayList<>();
            for (Map<String, String> dbsysnameMap : dbsysname) {
                dbsysnameList.add(dbsysnameMap.get("F_DBSYS_NAME"));
            }
            // 获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
            String currentTime = df.format(new Date()).substring(0, 13);// 当前时间
            // 先查询时间 最近的时间
            String timenew = besgztzzjgtreemapper.GetTimeList(currentTime,
                    request.getParameter("nhbh"), dbsysnameList);
            if (Validate_y.noNull(timenew)) {
                DtoMap.put("timenew", timenew);
                DtoMap.put("dbsysnameList", dbsysnameList);
                String fData = besgztzzjgtreemapper.Getpower(DtoMap);
                InstantPower = parse(fData);
            }
        }
        String peakEnergy = besgztzzjgtreemapper.getpeakEnergy(besHouseholdData);// 峰值能耗
        List<BesHouseholdData> FhEnergyData = besgztzzjgtreemapper.getFhEnergyData(besHouseholdData);// 分户数据
        // 查询总耗能sql
        List<BesHouseholdData> FhAllEnergyData = besgztzzjgtreemapper.getAllFhEnergyData(besHouseholdData);// 分户数据
        // 上年 上月 昨日 时间处理
        List<BesHouseholdData> lastYear = besgztzzjgtreemapper.getLastYear(DtoMap);// 分户去年总数据
        List<BesHouseholdData> lastMonth = besgztzzjgtreemapper.getLastMonth(DtoMap);// 分户上月总数据
        List<BesHouseholdData> Month = besgztzzjgtreemapper.getMonth(DtoMap);// 分户本月总数据
        List<BesHouseholdData> Yesterday = besgztzzjgtreemapper.getYesterday(DtoMap);// 昨天总数据
        String fUnitareaData = "0.00";
        String fPermanData = "0.00";
        String Alldata = "0.00";
        String LastYearData = "0.00";// 去年数据
        String LastYearContrast = "0.00";// 去年与今年对比数据
        String LastMonthData = "0.00";// 上月数据
        String MonthData = "0.00"; //本月数据
        String lastMonthContrast = "0.00"; // 上月与本月对比数据
        String todayData = "0.00";// 今日总能耗
        String yesterdayData = "0.00";// 昨日总能耗
        String yesterdayDataContrast = "0.00"; // 昨天与今天能耗对比

        if (!FhEnergyData.isEmpty()) {// list 不为空的情况
            fUnitareaData = String.valueOf(FhEnergyData.get(0).getfUnitareaData()); // 今日单位面积能耗
            isNumeric(fUnitareaData);
            fPermanData = String.valueOf(FhEnergyData.get(0).getfPermanData());// 今日人均能耗
            isNumeric(fPermanData);
            todayData = String.valueOf(FhEnergyData.get(0).getfData());// 今日总能耗
            isNumeric(todayData);
        }
        if (!FhAllEnergyData.isEmpty()) {// list 不为空的情况
            Alldata = String.valueOf(FhAllEnergyData.get(0).getfData());// 今年总能耗
            isNumeric(Alldata);
        }
        if (!lastYear.isEmpty()) {
            LastYearData = String.valueOf(lastYear.get(0).getfData());// 去年能耗
            isNumeric(LastYearData);
        }
        if (!lastMonth.isEmpty()) {
            LastMonthData = String.valueOf(lastMonth.get(0).getfData());// 上月能耗
            isNumeric(LastMonthData);
        }
        if (!Month.isEmpty()) {
            MonthData = String.valueOf(Month.get(0).getfData());// 本月能耗
            isNumeric(MonthData);
        }
        if (!Yesterday.isEmpty()) {
            yesterdayData = String.valueOf(Yesterday.get(0).getfData());// 昨天能耗
            isNumeric(yesterdayData);
        }
        // 先判断分母是否为0.00
        if ("0.00" == LastYearData) {
            LastYearContrast = transform(parse(LastYearContrast));
        } else {
            LastYearContrast = transform(parse(Alldata) / parse(LastYearData));
        }
        if ("0.00" == LastMonthData) {
            lastMonthContrast = transform(parse(lastMonthContrast));
        } else {
            lastMonthContrast = transform(parse(MonthData) / parse(LastMonthData));
        }
        if ("0.00" == yesterdayData) {
            yesterdayDataContrast = transform(parse(yesterdayDataContrast));
        } else {
            yesterdayDataContrast = transform(parse(todayData) / parse(yesterdayData));
        }
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("fUnitareaData", fUnitareaData);
        map.put("fPermanData", fPermanData);
        map.put("fAlldata", Alldata);
        list.add(map);
        returnObject.setList(list);

        // 存放对比数据list
        List<Map<String, Object>> contrastList = new ArrayList<>();
        Map<String, Object> contrastmap = new HashMap<>();
        contrastmap.put("LastYearData", LastYearData);// 去年能耗
        contrastmap.put("LastYearContrast", LastYearContrast);// 今年/去年 百分比
        contrastmap.put("LastMonthData", LastMonthData);// 上月能耗
        contrastmap.put("lastMonthContrast", lastMonthContrast);// 对比数据
        contrastmap.put("yesterdayData", yesterdayData);// 昨天能耗
        contrastmap.put("yesterdayDataContrast", yesterdayDataContrast);// 对比能耗
        contrastmap.put("peakEnergy", peakEnergy);// 峰值
        contrastmap.put("InstantPower", InstantPower);// 瞬时功率
        contrastList.add(contrastmap);
        returnObject.setData(contrastList);
        return returnObject;
    }

    /**
     * 能耗排行接口
     */
    @Override
    public ISSPReturnObject GetRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, String> DtoMap = new HashMap<>();
        List<Map<String, String>> energyList = new ArrayList<>();//能源的List
        DtoMap.put("count", request.getParameter("count"));
        DtoMap.put("fFhbh", request.getParameter("fFhbh"));
        DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
        DtoMap.put("fCjsj_end", request.getParameter("fCjsj_end"));
        DtoMap.put("month_start", request.getParameter("month_start"));
        DtoMap.put("month_end", request.getParameter("month_end"));
        DtoMap.put("fNybh", request.getParameter("nybh"));
        DtoMap.put("yqbh", request.getParameter("yqbh"));
        DtoMap.put("firstDay", request.getParameter("firstDay"));
        DtoMap.put("endDay", request.getParameter("endDay"));
        String yqbhMap = request.getParameter("yqbh");

        String nybhMap = request.getParameter("nybh");

        if (DtoMap.get("fFhbh").equals("1")){//如果没有节点 则为园区
            if (yqbhMap == null){
                energyList = besgztzzjgtreemapper.energyList(request.getParameter("nybh"));
            }else {
                energyList = besgztzzjgtreemapper.getEnergyListByYq(nybhMap,yqbhMap);
            }
            if (energyList != null && !energyList.isEmpty()){
              List<Map<String,Object>> MouDataList = new ArrayList<>();
              String fhlx = "";
               for (int i = 0;i<energyList.size();i++){
                    Map<String,Object>  MouData ;
                    fhlx = energyList.get(i).get("F_FHLX");
                    String fhbh = energyList.get(i).get("F_FHBH");
                    String nybh = energyList.get(i).get("F_NYBH");
                    String yqbh = energyList.get(i).get("F_YQBH");
                    String fhmc = energyList.get(i).get("F_FHMC");
                    String mouthMc = DtoMap.get("month_start");
                    Double personNum =besgztzzjgtreemapper.getHouseConfPerson(fhbh);
                    DtoMap.put("fNybh",nybh);
                    DtoMap.put("yqbh",yqbh);
                    MouData = besgztzzjgtreemapper.MounthParkEnergyMap(DtoMap);//根据能源编号和园区编号查询出的月能源
                    //根据能源编号查询能源信息
                    Map<String,Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(DtoMap.get("fNybh"));
                    if (MouData!=null){
                        for (String f_data : MouData.keySet()){
                            Double fdata = (double) MouData.get(f_data);

                            Double data = null;
                            //如果人数等于0或者小于0时,则对数据不进行处理,大于0时,则算出人均能耗
                            if (personNum <= 0) {
                                data = fdata;
                            } else {
                                data = fdata/personNum;
                            }

                            DecimalFormat  df = new DecimalFormat("#0.00");
                            MouData.put("personData",df.format(data));//月个人数据
                        }
                        if (!energyInformation.isEmpty() && energyInformation.size() > 0){
                            MouData.put("nymc",energyInformation.get("F_NYMC"));
                            MouData.put("nydw",energyInformation.get("F_UNIT"));
                        }
                       MouData.put("fhlx",fhlx);
                       MouData.put("fhmc",fhmc);
                       MouData.put("mouthMc",mouthMc);
                       MouDataList.add(MouData);
                    }else {
                        Map<String,Object>  MouData1 = new HashMap<>();//放置为空的数据
                        if (!energyInformation.isEmpty() && energyInformation.size() > 0){
                            MouData1.put("nymc",energyInformation.get("F_NYMC"));
                            MouData1.put("nydw",energyInformation.get("F_UNIT"));
                        }
                       MouData1.put("fhlx",fhlx);
                       MouData1.put("f_data",0);
                       MouData1.put("personData",0);
                       MouData1.put("fhmc",fhmc);
                       MouData1.put("mouthMc",mouthMc);
                       MouDataList.add(MouData1);
                    }
                 }

               List<Map<String,Object>> MouDataList1 = new ArrayList<>();

               if (MouDataList.size()!=0){
                   if (MouDataList.size()<5){
                       for (int j = 0;j<MouDataList.size();j++){
                           Map<String,Object> map = MouDataList.get(j);
                           MouDataList1.add(j,map);
                       }
                   }else {
                       for (int j = 0;j<5;j++){
                           Map<String,Object> map = MouDataList.get(j);
                           MouDataList1.add(j,map);
                       }
                   }
               }

               returnObject.setList(MouDataList1);
            }
        } else {

            //根据园区编号查询子节点
            List<Map<String, Object>> list = besgztzzjgtreemapper.getHouseHoldInfo(DtoMap.get("fNybh"), DtoMap.get("yqbh"), DtoMap.get("fFhbh"));


            if (list.size() > 0) {
                //分户单位
                String fhlx = (String) list.get(0).get("F_FHLX");

                List mounth = new ArrayList();

                Map<String, Double> PersonMap = new TreeMap<String, Double>();//个人数据key

                Map<String, Double> Totalmap = new TreeMap<String, Double>();//总数据

                for (int i = 0; i < list.size(); i++) {

                    String personNum = (String) list.get(i).get("F_PERSON_NUMS");

                    int number = Integer.parseInt(personNum);

                    String fhbh = (String) list.get(i).get("F_FHBH");

                    String yqbh = (String) list.get(i).get("F_YQBH");

                    //获取单个分户的数据
                    Map<String, Object> aloneData = besgztzzjgtreemapper.getHouseHoldPersonData(fhbh, yqbh, DtoMap.get("month_start"), DtoMap.get("month_end"));
                    //查询出某个分户的数据单人数据
                    if (aloneData != null) {
                        String fhmc = (String) list.get(i).get("F_FHMC");
                        Double initdata = (Double) aloneData.get("F_DATA");
                        BigDecimal bg = new BigDecimal(initdata);
                        double data = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        Date dateTime = (Date) aloneData.get("F_CJSJ");

                        Double initPersonDta = null;
                        //如果人数等于0或者小于0时,则对数据不进行处理,大于0时,则算出人均能耗
                        if (number <= 0) {
                            initPersonDta = data ;
                        } else {
                            initPersonDta = data / number;
                        }

                        DecimalFormat df = new DecimalFormat("#.00");//把数据保留两位小数
                        String personDta1 = df.format(initPersonDta);//个人数据
                        Double personDta = Double.parseDouble(personDta1);//String转Double
                        mounth.add(dateTime);
                        PersonMap.put(fhmc, personDta);
                        Totalmap.put(fhmc, data);
                    }
                }

                List<String> personYdata = new ArrayList();//月个人数据Y轴

                List<Object> personXdata = new ArrayList();//月个人数据X轴

                List<Object> totalXdata = new ArrayList();//月总数据X轴

                List<String> totalYdata = new ArrayList();//月总数据Y轴


                List<Map.Entry<String, Double>> personList = new ArrayList<Map.Entry<String, Double>>(PersonMap.entrySet());

                //对个人数据进行排序
                Collections.sort(personList, new Comparator<Map.Entry<String, Double>>() {

                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });

                //遍历存放
                for (Map.Entry<String, Double> m : personList) {
                    personYdata.add(m.getKey());
                    personXdata.add(m.getValue());
                }
                //对总数据进行排序
                List<Map.Entry<String, Double>> totalList = new ArrayList<Map.Entry<String, Double>>(Totalmap.entrySet());

                Collections.sort(totalList, new Comparator<Map.Entry<String, Double>>() {

                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                //遍历存放
                for (Map.Entry<String, Double> m : totalList) {
                    totalYdata.add(m.getKey());
                    totalXdata.add(m.getValue());
                }

                Map<String, Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(request.getParameter("nybh"));
                List<Map<String, Object>> RankEnergy = new ArrayList<>();
                Map<String, Object> RankEnergyMap = new HashMap<>();
                if (!energyInformation.isEmpty()) {
                    RankEnergyMap.put("nymc", energyInformation.get("F_NYMC"));
                    RankEnergyMap.put("nydw", energyInformation.get("F_UNIT"));
                }
                RankEnergyMap.put("fhlx", fhlx);//分户单位

                List<String> personYdata1 = new ArrayList();//月个人数据Y轴

                List<Object> personXdata1 = new ArrayList();//月个人数据X轴

                List<Object> totalXdata1 = new ArrayList();//月总数据X轴

                List<String> totalYdata1 = new ArrayList();//月总数据Y轴

                List mounth1 = new ArrayList();

                if (personYdata.size()!=0){
                    if (personYdata.size() < 5){
                        for (int j =0; j < personYdata.size();j++){
                            String personYDta =  personYdata.get(j);
                            personYdata1.add(j,personYDta);
                            Object personXdta =  personXdata.get(j);
                            personXdata1.add(j,personXdta);
                            Object totalXdta  =  totalXdata.get(j);
                            totalXdata1.add(j,totalXdta);
                            String totalYta   =  totalYdata.get(j);
                            totalYdata1.add(j,totalYta);
                            Object mounth2    =  mounth.get(j);
                            mounth1.add(j,mounth2);
                        }
                    }else {
                        for (int j =0; j < 5;j++){
                            String personYDta =  personYdata.get(j);
                            personYdata1.add(j,personYDta);
                            Object personXdta =  personXdata.get(j);
                            personXdata1.add(j,personXdta);
                            Object totalXdta  =  totalXdata.get(j);
                            totalXdata1.add(j,totalXdta);
                            String totalYta   =  totalYdata.get(j);
                            totalYdata1.add(j,totalYta);
                            Object mounth2    =  mounth.get(j);
                            mounth1.add(j,mounth2);
                        }
                    }
                }

                RankEnergyMap.put("personYdata", personYdata1);
                RankEnergyMap.put("personXdata", personXdata1);
                RankEnergyMap.put("totalXdata", totalXdata1);
                RankEnergyMap.put("totalYdata", totalYdata1);
                RankEnergyMap.put("Mounth", mounth1);
                RankEnergy.add(RankEnergyMap);
                returnObject.setList(RankEnergy);
            } else {
                String fhbh = besgztzzjgtreemapper.getHouseHoldFhbh(DtoMap.get("fFhbh"));
                List<Map<String, Object>> list1 = besgztzzjgtreemapper.getHouseHoldInfo(DtoMap.get("fNybh"), DtoMap.get("yqbh"),fhbh);
                //分户单位
                String fhlx = (String) list1.get(0).get("F_FHLX");

                List mounth = new ArrayList();

                Map<String, Double> PersonMap = new TreeMap<String, Double>();//个人数据key

                Map<String, Double> Totalmap = new TreeMap<String, Double>();//总数据

                for (int i = 0; i < list1.size(); i++) {

                    String personNum = (String) list1.get(i).get("F_PERSON_NUMS");

                    int number = Integer.parseInt(personNum);

                    String fhbh1 = (String) list1.get(i).get("F_FHBH");

                    String yqbh = (String) list1.get(i).get("F_YQBH");

                    //获取单个分户的数据
                    Map<String, Object> aloneData = besgztzzjgtreemapper.getHouseHoldPersonData(fhbh1, yqbh, DtoMap.get("month_start"), DtoMap.get("month_end"));
                    //查询出某个分户的数据单人数据
                    if (aloneData != null) {
                        String fhmc = (String) list1.get(i).get("F_FHMC");
                        Double initdata = (Double) aloneData.get("F_DATA");
                        BigDecimal bg = new BigDecimal(initdata);
                        double data = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        Date dateTime = (Date) aloneData.get("F_CJSJ");
                        Double initPersonDta = data / number;
                        DecimalFormat df = new DecimalFormat("#.00");//把数据保留两位小数
                        String personDta1 = df.format(initPersonDta);//个人数据
                        Double personDta = Double.parseDouble(personDta1);//String转Double
                        mounth.add(dateTime);
                        PersonMap.put(fhmc, personDta);
                        Totalmap.put(fhmc, data);
                    }
                }

                List<String> personYdata = new ArrayList();//月个人数据Y轴

                List<Object> personXdata = new ArrayList();//月个人数据X轴

                List<Object> totalXdata = new ArrayList();//月总数据X轴

                List<String> totalYdata = new ArrayList();//月总数据Y轴


                List<Map.Entry<String, Double>> personList = new ArrayList<Map.Entry<String, Double>>(PersonMap.entrySet());

                //对个人数据进行排序
                Collections.sort(personList, new Comparator<Map.Entry<String, Double>>() {

                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });

                //遍历存放
                for (Map.Entry<String, Double> m : personList) {
                    personYdata.add(m.getKey());
                    personXdata.add(m.getValue());
                }
                //对总数据进行排序
                List<Map.Entry<String, Double>> totalList = new ArrayList<Map.Entry<String, Double>>(Totalmap.entrySet());

                Collections.sort(totalList, new Comparator<Map.Entry<String, Double>>() {

                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                //遍历存放
                for (Map.Entry<String, Double> m : totalList) {
                    totalYdata.add(m.getKey());
                    totalXdata.add(m.getValue());
                }

                Map<String, Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(request.getParameter("nybh"));
                List<Map<String, Object>> RankEnergy = new ArrayList<>();
                Map<String, Object> RankEnergyMap = new HashMap<>();
                if (!energyInformation.isEmpty()) {
                    RankEnergyMap.put("nymc", energyInformation.get("F_NYMC"));
                    RankEnergyMap.put("nydw", energyInformation.get("F_UNIT"));
                }
                RankEnergyMap.put("fhlx", fhlx);//分户单位

                RankEnergyMap.put("personYdata", personYdata);
                RankEnergyMap.put("personXdata", personXdata);
                RankEnergyMap.put("totalXdata", totalXdata);
                RankEnergyMap.put("totalYdata", totalYdata);

                RankEnergyMap.put("Mounth", mounth);
                RankEnergy.add(RankEnergyMap);
                returnObject.setList(RankEnergy);
            }
        }

        return returnObject;
    }


    /**
     * 工作台报警信息查询
     */
    @Override
    public ISSPReturnObject GetWaringReal(BesWainingInfo beswaininginfo, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String[] levelArray = request.getParameter("levelArray").split(",");// 报警等级数组
        String limit = request.getParameter("limit");// 展示条数
        List<Map<String, String>> waringrealList = besgztzzjgtreemapper.GetWaringReal(levelArray, limit);
        returnObject.setList(waringrealList);
        return returnObject;
    }

    // 写方法判断返回结果是否为数字 不是数字的转换成0.00
    public String isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return "0.00";
            }
        }
        return str;
    }

    // 字符串转换成double
    public double parse(String str) {
        double b = Double.parseDouble(str);
        return b;
    }

    // 小数转百分比
    public String transform(Double rate) {
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumFractionDigits(1);// 保留两位小数
        String rates = num.format(rate);
        return rates;
    }
    /**
     * 获取园区信息
     */
	@Override
	public ISSPReturnObject getleftInfo(String yqbh, HttpServletRequest request) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,String> leftInfo = besgztzzjgtreemapper.getleftInfo(yqbh);
		String runingTime = leftInfo.get("F_EQUIPMENT_RUNTIME");// 系统运行开始时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String currentTime = df.format(new Date());// 当前时间
        String DistanceTime = GHEEViewController.getDistanceTime(runingTime, currentTime);// 两个时间相差距离多少天多少小时，以String形式返回
        String[] arr = DistanceTime.split("@");
        leftInfo.put("day", arr[0]);
        leftInfo.put("hour", arr[1]);
		returnObject.setData(leftInfo);
		return returnObject;
	}

	//今日排行
    @Override
    public ISSPReturnObject GetTodayRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, String> DtoMap = new HashMap<>();
        Map<String, String> energyInformationMap = new HashMap<>();//能源信息的Map
        List<Map<String, Object>> energyList = new ArrayList<>();//能源的List
        List<BesHouseholdData> RankEnergy = new ArrayList<>();
        List<Map<String, Object>> RankEnergy1 = new ArrayList<>();
        //查询所有的能源类型
        DtoMap.put("count", request.getParameter("count"));
        DtoMap.put("fFhbh", request.getParameter("fFhbh"));
        DtoMap.put("fCjsj_start", request.getParameter("fCjsj_start"));
//        DtoMap.put("fCjsj_start", "2020-07-13");
        DtoMap.put("fCjsj_end", request.getParameter("fCjsj_end"));
        DtoMap.put("fNybh", request.getParameter("nybh"));
        DtoMap.put("yqbh", request.getParameter("yqbh"));

        //根据能源编号查询能源信息
        Map<String,Object> energyInformation = besHouseholdDataMapper.selectEnergyInformationByNybh(DtoMap.get("fNybh"));
        if (!energyInformation.isEmpty() && energyInformation.size() > 0){
            energyInformationMap.put("nymc", (String) energyInformation.get("F_NYMC"));
            energyInformationMap.put("nydw", (String) energyInformation.get("F_UNIT"));
        }

        if (DtoMap.get("fFhbh").equals("1")){//水.电.气节点

            //根据能源编号和园区编号查询当前分户园区的信息
            energyList = besgztzzjgtreemapper.selectBesHouseholdByYqbh(DtoMap.get("yqbh"),request.getParameter("nybh"));
            if (energyList != null && !energyList.isEmpty()){
                String fhlx = (String) energyList.get(0).get("F_FHLX");//园区的单位
                energyInformationMap.put("fhlx",fhlx);
                for (Map<String, Object> energyLists : energyList){
                    //查询当前园区的今日能耗
                    //                Map<String, Object> BesHouseholdData = new HashMap<>();
                    List<BesHouseholdData> parkEnergyMap = besgztzzjgtreemapper.parkEnergyMap((String) energyLists.get("F_FHBH"),DtoMap.get("fCjsj_start"));


                    if (parkEnergyMap.size() < 0){
                        continue;
                    }else if (parkEnergyMap.size() >= 1){
                        RankEnergy.add(parkEnergyMap.get(0));
                    }
                }

                //匿名实现Comparator接口进行排序
                Collections.sort(RankEnergy, new Comparator<BesHouseholdData>() {
                    @Override
                    public int compare(BesHouseholdData o1, BesHouseholdData o2) {
                        //进行判断
                        Double key1 = Double.valueOf((Double) o1.getfData());
                        Double key2 = Double.valueOf((Double) o2.getfData());
                        return key1.compareTo(key2);
                    }
                });
                if (RankEnergy.size() > 0){

                    returnObject.setData(energyInformationMap);
                    returnObject.setStatus("1");
                    returnObject.setList(RankEnergy);
                    return returnObject;
                }else {
                    Map<String,Object> nybhMap = new HashMap<>();
                    nybhMap.put("nybh",DtoMap.get("fNybh"));
                    RankEnergy1.add(nybhMap);
                    returnObject.setStatus("0");
                    returnObject.setList(RankEnergy1);
                    returnObject.setData(energyInformationMap);
                    return returnObject;
                }

            }else {//当前能源类型下没有园区
                Map<String,Object> nybhMap = new HashMap<>();
                nybhMap.put("nybh",DtoMap.get("fNybh"));
                RankEnergy1.add(nybhMap);
                returnObject.setStatus("0");
                returnObject.setList(RankEnergy1);
                returnObject.setData(energyInformationMap);
                return returnObject;
            }

        }else {
            //查询当前点击的分户下的子节点
            List<Map<String, Object>> selectBesHouseholdChild = besgztzzjgtreemapper.selectBesHouseholdChild(DtoMap.get("fFhbh"));
            if (selectBesHouseholdChild.size() > 0 && !selectBesHouseholdChild.isEmpty()){
                String fhlx = (String) selectBesHouseholdChild.get(0).get("F_FHLX");
                energyInformationMap.put("fhlx",fhlx);

                // 分户本日能耗排行
                List<BesHouseholdData> TodayRankEnergy = besgztzzjgtreemapper.GetTodayRankEnergy(DtoMap);
                Map<String, Object> RankEnergyMap = new HashMap<>();
                RankEnergyMap.put("TodayRankEnergy", TodayRankEnergy);
                RankEnergy1.add(RankEnergyMap);
                returnObject.setData(energyInformationMap);
                returnObject.setList(RankEnergy1);
                returnObject.setStatus("1");
            }else {
                //如果当前分户编号下没有子节点,则显示父节点下的子节点
                String pfhbh = besgztzzjgtreemapper.selectPfhbh(DtoMap.get("fFhbh"));
                List<Map<String, Object>> selectBesHouseholdChilds = besgztzzjgtreemapper.selectBesHouseholdChild(pfhbh);
                if (selectBesHouseholdChilds.size() > 0 && !selectBesHouseholdChilds.isEmpty()) {
                    String fhlx = (String) selectBesHouseholdChilds.get(0).get("F_FHLX");
                    energyInformationMap.put("fhlx", fhlx);

                    DtoMap.put("fFhbh",pfhbh);
                    // 分户本日能耗排行
                    List<BesHouseholdData> TodayRankEnergy = besgztzzjgtreemapper.GetTodayRankEnergy(DtoMap);
                    Map<String, Object> RankEnergyMap = new HashMap<>();
                    RankEnergyMap.put("TodayRankEnergy", TodayRankEnergy);
                    RankEnergy1.add(RankEnergyMap);
                    returnObject.setData(energyInformationMap);
                    returnObject.setList(RankEnergy1);
                    returnObject.setStatus("1");
                }else {
                    returnObject.setStatus("0");
                }
            }


        }

        return returnObject;
    }

    public static void main(String[] args){

        Map<String, Object> map = new HashMap<>();
        map.put("name","a");
        map.put("age",5.1);
        map.put("排名","第一");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name","b");
        map1.put("age",3.6);
        map.put("排名","第二");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name","c");
        map2.put("age",7.6);
        map.put("排名","第三");
        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(map);
        maps.add(map1);
        maps.add(map2);
        String s = JSONObject.toJSONString(maps);
        //重写比较器
        Collections.sort(maps, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double key1 = Double.valueOf((Double) o1.get("age"));
                Double key2 = Double.valueOf((Double) o2.get("age"));
                return key2.compareTo(key1);
            }
        });
        System.out.println(maps);
    }


    //获取年数据
    @Override
    public ISSPReturnObject getEnergyYearData(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        try{
	    String nybh = request.getParameter("nybh");
        String type = request.getParameter("fType");
        String firstDay = request.getParameter("firstDay");//年第一天
        String endYearData = request.getParameter("endData");
        String secondDay = request.getParameter("secondDay");//年第二天
        List<Map<String,Object>> yearData1 = besgztzzjgtreemapper.selectEnergyDataByYear(nybh,type,firstDay);//本年能耗

        double yearData = 0;

        for (int i = 0;i<yearData1.size();i++){
            yearData += (double) yearData1.get(i).get("f_data");
        }

        String monthHighData = besgztzzjgtreemapper.selectEnergyDataMaxByMonth(nybh,firstDay,endYearData);//年巅峰能耗
        Map<String,Object> map = new HashMap<>();
        map.put("yearData",yearData);
        map.put("monthHighData",monthHighData);
        returnObject.setMap(map);

        } catch (Exception e) {

            returnObject.setMsg("获取分户用能趋势统计分析数据失败");
            returnObject.setStatus("0");
            e.printStackTrace();

        }
        return returnObject;
    }


    /**
     * 分项年数据表添加数据
     * @param
     * @param
     * @return
     */
    @Override
    public ISSPReturnObject insertSubyearData(List<BesSubyearData> list,String firstDay,String endDay) {

	    ISSPReturnObject returnObject = new ISSPReturnObject();

	    if (list.size()>0){
            for (int i = 0;i<list.size();i++){
                String F_ID =UUIDUtil.getRandom32BeginTimePK();
                list.get(i).setF_ID(F_ID);
                String fxbh = list.get(i).getF_FXBH();
                String F_YDATA =  besgztzzjgtreemapper.selectSubitemData(fxbh,firstDay,endDay);

                if (F_YDATA == null){
                    list.get(i).setF_YDATA("0");
                }else {
                    list.get(i).setF_YDATA(F_YDATA);
                }
            }
        }

        List selectDataList= besgztzzjgtreemapper.selectSubyearData();//查询年数据

        if (selectDataList != null && selectDataList.size() != 0){//如果查询出有数据就清除所有数据

            Integer deleteData = besgztzzjgtreemapper.deleteSubyearData();//清除所有数据

            if (deleteData >= 0){
                if (list.size()>0){
                    Boolean   flag = besgztzzjgtreemapper.insertSubyearData(list);//插入数据
                    if(flag){//插入数据成功
                        List<Map<String,Object>> selectData = besgztzzjgtreemapper.selectSubyearData();//查询数据
                        for (int i = 0;i<selectData.size(); i++){
                            String fxbh = (String) selectData.get(i).get("f_fxbh");
                            String f_ydata =  besgztzzjgtreemapper.selectSubitemData(fxbh,firstDay,endDay);
                            Boolean flag1 = besgztzzjgtreemapper.updateSubyearDataYdata(fxbh,f_ydata);
                            if (flag1){
                                selectData.get(i).put("f_ydata",f_ydata);
                            }else {
                                returnObject.setMsg("查询年数据失败");
                                returnObject.setStatus("0");
                            }
                        }
                        returnObject.setMsg("添加年数据成功");
                        returnObject.setStatus("1");
                        returnObject.setList(selectData);
                    }else {
                        returnObject.setMsg("添加年数据失败");
                        returnObject.setStatus("0");
                    }
                }else {
                    returnObject.setMsg("清空年数据成功");
                    returnObject.setStatus("1");
                }
            }else {
                returnObject.setMsg("清除年数据失败");
                returnObject.setStatus("0");
            }
        }else{//如果查询出没有数据，就插入数据
            Boolean flag = besgztzzjgtreemapper.insertSubyearData(list);//插入数据
            if(flag){//插入数据成功
                   List<Map<String,Object>> selectData = besgztzzjgtreemapper.selectSubyearData();
                    for (int i = 0;i<selectData.size(); i++){
                        String fxbh = (String) selectData.get(i).get("f_fxbh");
                        String f_ydata =  besgztzzjgtreemapper.selectSubitemData(fxbh,firstDay,endDay);
                        Boolean flag1 = besgztzzjgtreemapper.updateSubyearDataYdata(fxbh,f_ydata);
                        if (flag1){
                            selectData.get(i).put("f_ydata",f_ydata);
                        }else {
                            returnObject.setMsg("查询年数据失败");
                            returnObject.setStatus("0");
                        }
                    }
                   returnObject.setMsg("添加年数据成功");
                   returnObject.setStatus("1");
                   returnObject.setList(selectData);
                }else {
                   returnObject.setMsg("添加年数据失败");
                   returnObject.setStatus("0");
                }
        }
        return returnObject;
    }

    /**
     * 首页加载年数据
     * @param request
     * @return
     */
    @Override
    public ISSPReturnObject selectEnergyYearData(HttpServletRequest request) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String firstDay = request.getParameter("firstDay");//当年第一天

        String endDay = request.getParameter("endDay");//当年最后一天

        List<Map<String,Object>> selectData = besgztzzjgtreemapper.selectSubyearData();

        for (int i = 0;i<selectData.size(); i++){
            String fxbh = (String) selectData.get(i).get("f_fxbh");
            String f_ydata =  besgztzzjgtreemapper.selectSubitemData(fxbh,firstDay,endDay);
            Boolean flag = besgztzzjgtreemapper.updateSubyearDataYdata(fxbh,f_ydata);
            if (flag){
                selectData.get(i).put("f_ydata",f_ydata);
            }else {
                returnObject.setMsg("查询年数据失败");
                returnObject.setStatus("0");
            }
        }
        if (selectData.size()!=0 && selectData!=null){
            returnObject.setMsg("查询年数据成功");
            returnObject.setStatus("1");
            returnObject.setList(selectData);
        }else {
            returnObject.setMsg("查询年数据失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

  

    /**
     *
     * @Description: 今日能耗排行(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 17:11 2021/4/26
     * @param: [request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject GetTodayRankEnergyNew(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> DtoMapNew = new HashMap<>();
        Map<String, String> energyInformationMapNew = new HashMap<>();//能源信息的Map
        List<BesHouseholdData> RankEnergyNew = new ArrayList<>();
        List<Map<String, Object>> RankEnergy1New = new ArrayList<>();

        DtoMapNew.put("fFhbhs", request.getParameterValues("fFhbh"));
        DtoMapNew.put("fCjsj_start", request.getParameter("fCjsj_start"));
        DtoMapNew.put("fCjsj_end", request.getParameter("fCjsj_end"));
        DtoMapNew.put("fNybh", request.getParameter("nybh"));

        String[] fFhbhs = (String[]) DtoMapNew.get("fFhbhs");

        //根据能源编号查询能源信息
        Map<String,Object> energyInformationNew = besHouseholdDataMapper.selectEnergyInformationByNybh((String) DtoMapNew.get("fNybh"));
        if (!energyInformationNew.isEmpty() && energyInformationNew.size() > 0){
            energyInformationMapNew.put("nymc", (String) energyInformationNew.get("F_NYMC"));
            energyInformationMapNew.put("nydw", (String) energyInformationNew.get("F_UNIT"));
        }
        //查询第一个分户的单位
        String unit = besgztzzjgtreemapper.selectUnitByFHBH(fFhbhs[0]);
        energyInformationMapNew.put("fhlx",unit);
        for (int i = 0; i < fFhbhs.length; i++) {
            //查询当前园区的今日能耗
            List<BesHouseholdData> parkEnergyMap = besgztzzjgtreemapper.parkEnergyMap(fFhbhs[i], (String) DtoMapNew.get("fCjsj_start"));
            if (parkEnergyMap.size() == 0){
                continue;
            }else if (parkEnergyMap.size() > 0){
                RankEnergyNew.add(parkEnergyMap.get(0));
            }
        }
        //匿名实现Comparator接口进行排序
        Collections.sort(RankEnergyNew, new Comparator<BesHouseholdData>() {
            @Override
            public int compare(BesHouseholdData o1, BesHouseholdData o2) {
                //进行判断
                Double key1 = Double.valueOf((Double) o1.getfData());
                Double key2 = Double.valueOf((Double) o2.getfData());
                return key1.compareTo(key2);
            }
        });
        if (RankEnergyNew.size() > 0){

            returnObject.setData(energyInformationMapNew);
            returnObject.setStatus("1");
            returnObject.setList(RankEnergyNew);
            return returnObject;
        }else {
            Map<String,Object> nybhMap = new HashMap<>();
            nybhMap.put("nybh",DtoMapNew.get("fNybh"));
            RankEnergy1New.add(nybhMap);
            returnObject.setStatus("0");
            returnObject.setList(RankEnergy1New);
            returnObject.setData(energyInformationMapNew);
            return returnObject;
        }
    }

    /**
     *
     * @Description: 今日能耗趋势(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 17:39 2021/4/26
     * @param: [request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject getHouseHoldDataNew(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> DtoMapNew = new HashMap<>();
        Map<String, String> energyInformationMapNew = new HashMap<>();//能源信息的Map

        DtoMapNew.put("fFhbhs", request.getParameterValues("fFhbhs"));
        DtoMapNew.put("fCjsj_start", request.getParameter("fCjsj_start"));
        DtoMapNew.put("fCjsj_end", request.getParameter("fCjsj_end"));
        DtoMapNew.put("fNybh", request.getParameter("nybh"));

        String[] fFhbhs = (String[]) DtoMapNew.get("fFhbhs");
        String f_type = "0";//小时

        //根据能源编号查询能源信息
        Map<String,Object> energyInformationNew = besHouseholdDataMapper.selectEnergyInformationByNybh((String) DtoMapNew.get("fNybh"));
        if (!energyInformationNew.isEmpty() && energyInformationNew.size() > 0){
            energyInformationMapNew.put("nymc", (String) energyInformationNew.get("F_NYMC"));
            energyInformationMapNew.put("nydw", (String) energyInformationNew.get("F_UNIT"));
        }
        //首先根据分户编号,查询当天的时间和值
        List<Map<String,Object>> RankEnergyList = besHouseholdDataMapper.selectRankEnergyList(fFhbhs,(String) DtoMapNew.get("fCjsj_start"),f_type);
        List<Map<String,Object>> TodaySEnergyConsumptionTrendsList = new ArrayList<>();
        RankEnergyList.forEach((e) -> {
            Map<String,Object> map = new HashedMap();
            String time = String.valueOf(e.get("f_cjsj")) ;
            Double data = Double.valueOf((Double) e.get("f_data"));
            map.put("time",time.substring(11,13));
            map.put("data",data);
            TodaySEnergyConsumptionTrendsList.add(map);
        });
        if (RankEnergyList.size() > 0) {
            returnObject.setData(energyInformationMapNew);
            returnObject.setList(TodaySEnergyConsumptionTrendsList);
            returnObject.setStatus("0");//查询成功
        } else {
            returnObject.setData(energyInformationMapNew);
            returnObject.setStatus("1");//查询失败
        }
        return returnObject;
    }

    /**
     *
     * @Description: 今日同环比,本月同环比(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 13:57 2021/4/27
     * @param:
     * @return:
     *
     */
    @Override
    public ISSPReturnObject illuminationNew(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> DtoMapNew = new HashMap<>();
        Map<String, String> energyInformationMapNew = new HashMap<>();//能源信息的Map
        String[] legend = new String[2];
        List<Map<String,Object>> seriesData = new ArrayList<>();
        List<Map<String,Object>> returnObjectData = new ArrayList<>();
        //本期值
        String thisPeriod = null;
        //同期值
        String corrTimePeriod = null;


        DtoMapNew.put("fFhbhs", request.getParameterValues("fFhbhs"));
        DtoMapNew.put("firstTime", request.getParameter("firstTime"));
        DtoMapNew.put("secondTime", request.getParameter("secondTime"));
        DtoMapNew.put("fNybh", request.getParameter("nybh"));
        DtoMapNew.put("type",request.getParameter("type"));

        String[] fFhbhs = (String[]) DtoMapNew.get("fFhbhs");
        String f_type = (String) DtoMapNew.get("type");
        String timeType = null;//时间类型
        String[] timeData = new String[1];//x轴时间

        //根据能源编号查询能源信息
        Map<String,Object> energyInformationNew = besHouseholdDataMapper.selectEnergyInformationByNybh((String) DtoMapNew.get("fNybh"));
        if (!energyInformationNew.isEmpty() && energyInformationNew.size() > 0){
            energyInformationMapNew.put("nymc", (String) energyInformationNew.get("F_NYMC"));
            energyInformationMapNew.put("nydw", (String) energyInformationNew.get("F_UNIT"));
        }
        legend[0] = "本期";
        legend[1] = "同期";


        //获取本期,同期数据
        if (f_type.equals("0") || f_type.equals("1")) {//0:今日同比 1:今日环比 2:本月同比 3:本月环比
            //本期
            thisPeriod = besHouseholdDataMapper.selectThisPeriod(fFhbhs,"1", (String) DtoMapNew.get("firstTime"));
            //同期
            corrTimePeriod = besHouseholdDataMapper.selectCorrTimePeriod(fFhbhs,"1",(String) DtoMapNew.get("secondTime"));

            timeData[0] = (String) ((String) DtoMapNew.get("firstTime")).substring(8,10) + "日";
        } else if (f_type.equals("2") || f_type.equals("3")) {
            //本期
            thisPeriod = besHouseholdDataMapper.selectThisPeriod(fFhbhs,"2",(String) DtoMapNew.get("firstTime"));
            //同期
            corrTimePeriod = besHouseholdDataMapper.selectCorrTimePeriod(fFhbhs,"2",(String) DtoMapNew.get("secondTime"));

            timeData[0] = (String) ((String) DtoMapNew.get("firstTime")).substring(5,7) + "月";
        }

        Map<String,Object> seriesThisPeriodMap = new HashedMap();
        Map<String,Object> seriesCorrTimePeriodMap = new HashedMap();
        String[] thisPeriodData = new String[1];
        String[] corrTimePeriodData = new String[1];

        DecimalFormat format = new DecimalFormat("0.00");
        if (thisPeriod == null) {
//            thisPeriodData[0] = "";
        } else {
            thisPeriodData[0] = format.format(new BigDecimal(thisPeriod)) ;
        }
        if (corrTimePeriod == null) {
//            corrTimePeriodData[0] = "";
        } else {
            corrTimePeriodData[0] = format.format(new BigDecimal(corrTimePeriod)) ;
        }


        seriesThisPeriodMap.put("data",thisPeriodData);
        seriesThisPeriodMap.put("name","本期");
        seriesThisPeriodMap.put("type","bar");
        seriesData.add(seriesThisPeriodMap);

        seriesCorrTimePeriodMap.put("data",corrTimePeriodData);
        seriesCorrTimePeriodMap.put("name","同期");
        seriesCorrTimePeriodMap.put("type","bar");
        seriesData.add(seriesCorrTimePeriodMap);

        Map<String, Object> mapAll = new HashMap<>();
        mapAll.put("legend",legend);
        mapAll.put("seriesData",seriesData);
        mapAll.put("timeData",timeData);
        mapAll.put("nymc",energyInformationMapNew.get("nymc"));
        mapAll.put("nydw",energyInformationMapNew.get("nydw"));
        returnObject.setData(mapAll);

        return returnObject;
    }

/**
 *
 * @Description: 本年能耗以及本年能耗峰值
 *
 * @auther: wanghongjie
 * @date: 18:11 2021/4/27
 * @param:
 * @return:
 *
 */
    @Override
    public ISSPReturnObject getEnergyYearDataNew(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> DtoMapNew = new HashMap<>();
        List<Map<String,Object>> dataList = new ArrayList<>();
        Map<String, String> energyInformationMapNew = new HashMap<>();//能源信息的Map

        DtoMapNew.put("fhbhs", request.getParameterValues("fhbhs"));
        DtoMapNew.put("secondDay", request.getParameter("secondDay"));
        DtoMapNew.put("endData", request.getParameter("endData"));
        DtoMapNew.put("firstDay", request.getParameter("firstDay"));
        DtoMapNew.put("nybh", request.getParameter("nybh"));

        //根据能源编号查询能源信息
        Map<String,Object> energyInformationNew = besHouseholdDataMapper.selectEnergyInformationByNybh((String) DtoMapNew.get("nybh"));
        if (!energyInformationNew.isEmpty() && energyInformationNew.size() > 0){
            energyInformationMapNew.put("nymc", (String) energyInformationNew.get("F_NYMC"));
            energyInformationMapNew.put("nydw", (String) energyInformationNew.get("F_UNIT"));
        }

        String[] fFhbhs = (String[]) DtoMapNew.get("fhbhs");

        DecimalFormat format = new DecimalFormat("0.00");

        //本年能耗
        String energyConsumptionThisYear = besHouseholdDataMapper.
                selectEnergyConsumptionThisYear(fFhbhs, (String) DtoMapNew.get("firstDay"),"3");

        if (energyConsumptionThisYear == null) {
            energyConsumptionThisYear = "0";
        } else {
            energyConsumptionThisYear = format.format(new BigDecimal(energyConsumptionThisYear));
        }
        //本年峰值能耗
        String peakEnergyConsumptionThisYear = besHouseholdDataMapper.
                peakEnergyConsumptionThisYear(fFhbhs,(String) DtoMapNew.get("firstDay"),(String) DtoMapNew.get("endData"),"2");
        if (peakEnergyConsumptionThisYear == null) {
            peakEnergyConsumptionThisYear = "0";
        } else {
            peakEnergyConsumptionThisYear = format.format(new BigDecimal(peakEnergyConsumptionThisYear));
        }
        Map<String,Object> dataMap = new HashedMap();
        dataMap.put("energyConsumptionThisYear",energyConsumptionThisYear);
        dataMap.put("peakEnergyConsumptionThisYear",peakEnergyConsumptionThisYear);
        dataList.add(dataMap);
        returnObject.setList(dataList);
        returnObject.setData(energyInformationMapNew);
        return returnObject;
    }
}
