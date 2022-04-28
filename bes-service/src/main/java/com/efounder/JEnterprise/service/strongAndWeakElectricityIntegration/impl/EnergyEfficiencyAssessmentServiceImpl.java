package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.EnergyEfficiencyAssessmentMapper;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.EnergyEfficiencyAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

@Service("EnergyEfficiencyAssessmentService")
public class EnergyEfficiencyAssessmentServiceImpl implements EnergyEfficiencyAssessmentService {
	private static final Logger log = LoggerFactory.getLogger(EnergyEfficiencyAssessmentServiceImpl.class);
	@Autowired
	private EnergyEfficiencyAssessmentMapper energyEfficiencyAssessmentMapper;

	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

	/**
	 * 查询系统能效关联点位信息
	 * @param sysnameArray
	 * @return
	 */
	@Override
	public ISSPReturnObject queryEnergyEfficiencyListInfo(String[] sysnameArray) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String id =sysnameArray[1];//id
		String operator = sysnameArray[2];;//计算公式
		sysnameArray = explode(sysnameArray[0]);
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("sysnameArray",sysnameArray);
		List<Map> typeList = energyEfficiencyAssessmentMapper.getRelateNodeListType(queryMap);
		List<String> typeAIlList = new ArrayList<>();
		for(Map itemMap : typeList){
			String type = String.valueOf(itemMap.get("f_type"));
			String sysnameStr = String.valueOf(itemMap.get("sysname"));
			String[] array = sysnameStr.split(",");
			typeAIlList.addAll(Arrays.asList(array));
		}
		//查询各种类型的节点的信息
		Map<String,List>typeAllData = queryRelativeTypeNodeList(typeAIlList);
		List pointDataList = new ArrayList();
		List<List<Map<String,String>>> pointInfoList = new ArrayList<List<Map<String,String>>>();
		for(int i = 0;i<sysnameArray.length;i++){
			for (String key : typeAllData.keySet()) {
				if(key.equals(sysnameArray[i])){
					pointInfoList.add(typeAllData.get(key));
					//pointDataList.add(pointInfoList[0][0].F_INIT_VAL);8
				}
			}
		}
		for(int j = 0;j<pointInfoList.size();j++){
			//map遍历
			for(int i=0;i<pointInfoList.get(j).size();i++){
				for(Map.Entry<String, String> entry : pointInfoList.get(j).get(i).entrySet()){
					if(entry.getKey().equals("F_INIT_VAL")){
						pointDataList.add(entry.getValue());//获取到的值
					}
				}
			}

		}
		/**
		 * 根据公式计算值
		 */
		float nData = 0.0f;
		for(int k = pointDataList.size() ; k > 0; k--){
			String strData = (String) pointDataList.get(k - 1);
			String strOperator = "$" + k;
			operator = operator.replace(strOperator,strData);
		}
		try {
			nData = Float.parseFloat(jse.eval(operator).toString());
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("typeAllData",String.valueOf(nData));

		returnObject.setStatus("1");
		returnObject.setMap(resultMap);
		return returnObject;
	}

	/**
	 * 查询饼图点位关联及计算公式相关信息
	 * @param map
	 * @return
	 */
	@Override
	public ISSPReturnObject queryPieRelativeInfo(Map map)  {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map>list = energyEfficiencyAssessmentMapper.queryPieRelativeInfo(map);
		if(list.size()>0){
			returnObject.setList(list);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		}else{
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 新增饼图关联点位及计算公式信息
	 * @param map
	 * @return
	 */
	@Override
	public ISSPReturnObject addPieRelativeInfo(Map map) {
		ISSPReturnObject object = new ISSPReturnObject();
		Integer num = 0;
		List<Map>list = energyEfficiencyAssessmentMapper.queryPieRelativeInfo(map);
		if(list.size()>0){
			num = energyEfficiencyAssessmentMapper.updatePieRelativeInfo(map);
		}else{
			String pageId = UUID.randomUUID().toString();
			map.put("id",pageId);
			num = energyEfficiencyAssessmentMapper.addPieRelativeInfo(map);
		}
		if(num == 1){
			object.setStatus("1");
		}else{
			object.setStatus("0");
		}
		return object;
	}

	@Override
	public ISSPReturnObject queryAllPieRelativeInfo(String[] piePointArray) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("piePointArray",piePointArray);
		List<Map> typeList = energyEfficiencyAssessmentMapper.queryAllPieRelativeInfo(queryMap);
		Map<String,List> resultMap = new HashMap<>();
		resultMap.put("allPiePointData",typeList);
		returnObject.setStatus("1");
		returnObject.setMap(resultMap);
		return returnObject;
	}

	/**
	 * 查询列表关联点位信息
	 * @param map
	 * @return
	 */
	@Override
	public ISSPReturnObject queryLableRelativeInfo(Map map) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,Object> queryMap = new HashMap<>();
		//查询出各种类型节点对应的表名
		String tableName = energyEfficiencyAssessmentMapper.findRelativeNodeTable( String.valueOf(map.get("sysName")));
		queryMap.put("sysName",String.valueOf(map.get("sysName")));
		queryMap.put("type_table",tableName);
		List<Map>list = energyEfficiencyAssessmentMapper.queryLableRelativeTypeNodeList(queryMap);
		returnObject.setList(list);
		returnObject.setStatus("1");
		returnObject.setMsg("查询成功");
		return returnObject;

	}

	/**
	 * 查询实时能耗配置信息
	 * @param sysnameArray
	 * @return
	 */
	@Override
	public ISSPReturnObject queryRealTimeEnergyEfficiencyBtnInfo(String[] sysnameArray) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String id =sysnameArray[1];//id
		String operator = sysnameArray[2];;//计算公式
		String changePointName = sysnameArray[3];//name改变的点位名称
		String changePointVal = sysnameArray[4];//f_init_val改变的点位值
		sysnameArray = explode(sysnameArray[0]);
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("sysnameArray",sysnameArray);
		List<Map> typeList = energyEfficiencyAssessmentMapper.getRelateNodeListType(queryMap);
		List<String> typeAIlList = new ArrayList<>();
		for(Map itemMap : typeList){
			String type = String.valueOf(itemMap.get("f_type"));
			String sysnameStr = String.valueOf(itemMap.get("sysname"));
			String[] array = sysnameStr.split(",");
			typeAIlList.addAll(Arrays.asList(array));
		}
		//查询各种类型的节点的信息
		Map<String,List>typeAllData = queryRelativeTypeNodeList(typeAIlList);
		List pointDataList = new ArrayList();
		List<List<Map<String,String>>> pointInfoList = new ArrayList<List<Map<String,String>>>();
		for(int i = 0;i<sysnameArray.length;i++){
			for (String key : typeAllData.keySet()) {
				if(key.equals(sysnameArray[i])){
					pointInfoList.add(typeAllData.get(key));
				}
			}
		}
		for(int j = 0;j<pointInfoList.size();j++){
			//map遍历
			for(int i=0;i<pointInfoList.get(j).size();i++){
				for(Map.Entry<String, String> entry : pointInfoList.get(j).get(i).entrySet()){
					if(entry.getKey().equals("F_SYS_NAME")){
						if(entry.getValue().equals(changePointName)){
							pointDataList.add(changePointVal);//获取到的值
							//j++;
							break;
						}
					}
						if(entry.getKey().equals("F_INIT_VAL")){
							pointDataList.add(entry.getValue());//获取到的值
						}

				}
			}

		}
		/**
		 * 根据公式计算值
		 */
		float nData = 0.0f;
		for(int k = pointDataList.size() ; k > 0; k--){
			String strData = (String) pointDataList.get(k - 1);
			String strOperator = "$" + k;
			operator = operator.replace(strOperator,strData);
		}
		try {
			nData = Float.parseFloat(jse.eval(operator).toString());
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("typeAllData",String.valueOf(nData));

		returnObject.setStatus("1");
		returnObject.setMap(resultMap);
		return returnObject;
	}


	/**
	 *以li分割str字符串，返回字符串数组
	 */
	public static String[] explode(String str) {
		List rtn = new ArrayList();
		if (str != null) {
			String temp = str;
			for (int i = temp.indexOf(","); i > -1; i = temp.indexOf(",")) {
				rtn.add(temp.substring(0, i));
				temp = temp.substring(i + ",".length());
			}
			rtn.add(temp);
		}
		return (String[]) rtn.toArray(new String[rtn.size()]);
	}

	private Map<String,List> queryRelativeTypeNodeList(List<String> typeList) {
		List<Map> list = new ArrayList<>();
		Map<String,Object> queryMap = new HashMap<>();
		Map<String,List> queryList = new HashMap<>();
		if(typeList != null && typeList.size() > 0){
			for(int i = 0;i<typeList.size();i++){
				String sysname = typeList.get(i);
				//查询出各种类型节点对应的表名
				String tableName = energyEfficiencyAssessmentMapper.findRelativeNodeTable(sysname);
				queryMap.put("type_table",tableName);
				queryMap.put("typeList",typeList);
				list = energyEfficiencyAssessmentMapper.queryRelativeTypeNodeList(queryMap);
				queryList.put(sysname,list);
			}

		}
		return queryList;

	}
}


