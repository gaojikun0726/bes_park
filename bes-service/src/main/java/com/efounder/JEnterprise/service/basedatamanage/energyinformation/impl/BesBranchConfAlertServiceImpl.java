package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.initializer.BranchConfAlarmConfigurationCache;
import com.efounder.JEnterprise.initializer.BranchConfParameterCache;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESParkMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesBranchConfAlertMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.*;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.platform.util.List2Map;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesBranchConfAlertService;
import com.framework.common.utils.ScopeDataUtil;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 支路报警impl
 * @author suhang
 * @datetime 2018-07-31 
 */
@Service("besBranchConfAlertService")
public class BesBranchConfAlertServiceImpl implements BesBranchConfAlertService {
	
	@Autowired
	private BesBranchConfAlertMapper besbranchConfAlertmapper;
	
	@Autowired
	private BESParkMapper besParkMapper;
	
	@Autowired
	private BESBranchConfMapper besbranchConfmapper;
	
	@Autowired
	private BESEnergyTypeMapper besenergyTypeMapper;

	////支路报警配置缓存
	@Resource
	private BranchConfAlarmConfigurationCache besBranchConfAlarmConfigurationCache;

	//支路报警规则
	@Resource
	private BranchConfParameterCache branchConfParameterCache;
	
	//获取支路报警信息
	@Override
	public ISSPReturnObject getAlertList(BesBranchConfAlert besBranchConfAlert) {
			ISSPReturnObject returnObject = new ISSPReturnObject();
			try {
				List<BesBranchConfAlert> list = besbranchConfAlertmapper.getAlertList(besBranchConfAlert);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getfId() != null && !"".equals(list.get(i).getfId())) {
						list.get(i).setfId(list.get(i).getfId());
					} else {
						list.get(i).setfId(null);
					}
				}
				returnObject.setList(list);
				returnObject.setMsg("获取支路报警数据配置成功");
				returnObject.setStatus("1");
			} catch (Exception e) {
				returnObject.setMsg("获取支路报警数据配置失败");
				returnObject.setStatus("0");
			}
			return returnObject;
		}
	
	
	/**
	 * 生成园区类型树
	 */
	@Override
	public ISSPReturnObject Park_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			List<String> groupbh = ScopeDataUtil.getGroupIdScope();
			String userId = ScopeDataUtil.getUserIdScope();
			List<BESPark> findAllPark = besParkMapper.findAllPark(groupbh,userId);
			returnObject.setList(findAllPark);
			returnObject.setMsg("获取园区下拉数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取园区下拉数据失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 生成能源类型树
	 */
	@Override
	public ISSPReturnObject getAllEnergyType() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			List<BESEnergyType> getAllEnergyType = besenergyTypeMapper.getAllEnergyType();
			returnObject.setList(getAllEnergyType);
			returnObject.setMsg("获取能源下拉数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取能源下拉数据失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	
	/*private List<ISSPTreeNode> getTreeJson() {
		List<BESPark> list = besParkMapper.findAllPark();// 从数据库获取园区所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BESPark parkType : list) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(parkType.getF_yqbh());
			node.setId(parkType.getF_yqbh());
			node.setPid("");
			node.setText(parkType.getF_yqmc());
			// node.setJs(parkType.getfJs());
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;

	}*/
	
	/**
	 * 获取支路配置树
	 */
	@Override
	public ISSPReturnObject getbranchTree(String fYqbh , String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson(fYqbh,fNybh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getbranchalert_ammeter(String fYqbh,String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESAmmeter> tree = getextTree(fYqbh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getbranchalert_ammeter_rlgl(String parentId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESAmmeter> tree = getextTree_rlgl(parentId);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	private List<BESAmmeter> getextTree_rlgl(String parentId) {
		//
		List<BESAmmeter> AmmeterList = new ArrayList<>();
		List<BESAmmeter> newAmmeterList = new ArrayList<>();
		List deptList = new ArrayList();
		Dto deptDto = new BaseDto();
		List<BESElectric_params> BARList = besbranchConfmapper.queryBARList_rlgl(parentId);
		for (int i = 0; i < BARList.size(); i++) {
			Map<String,Object> ammeter = new HashMap<>();
			ammeter.put("id",BARList.get(i).getfDnbh());
			ammeter.put("name",BARList.get(i).getfDnmc());
			ammeter.put("pId",parentId);
			ammeter.put("isParent",true);
			ammeter.put("open",false);
			deptList.add(i, ammeter);
		}
		newAmmeterList = deptList;
		return newAmmeterList;
	}

	private List<BESAmmeter> getextTree(String SubId) {
		//通过fZlbh查询'支路与电表关系表'中支路BARList
		List<BESAmmeter> AmmeterList = new ArrayList<>();
		List<BESAmmeter> newAmmeterList = new ArrayList<>();
		List deptList = new ArrayList();
		Dto deptDto = new BaseDto();
		List<BESBranch_Ammeter_Rlgl> BARList = besbranchConfmapper.queryBARList(SubId);
		for (int i = 0; i < BARList.size(); i++) {
			Map<String,Object> ammeter =besbranchConfmapper.queryAmmeter(BARList.get(i).getfDbsysName());
//			ammeter.getfSysName();
			if (ammeter.get("wldz") == null){
				ammeter.put("wldz",0);//等于0则证明是AI点位
			}else {
				ammeter.put("wldz",1);//等于1则证明是电表
			}
//			ammeter.setfOperator(BARList.get(i).getfOperator());
			ammeter.put("leaf",new Boolean(true));
			ammeter.put("pId",0);
			ammeter.put("isParent",true);
			ammeter.put("open",false);
			deptList.add(i, ammeter);
		}
		newAmmeterList = deptList;
		return newAmmeterList;
	}

	private List<ISSPTreeNode> getTreeJson(String fYqbh , String fNybh) {
		//获取用户和组织编号
//		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
//		String userId = ScopeDataUtil.getUserIdScope();

		List<String> groupbh = null;
		String userId = null;

		List<BESBranchConf> branchlist = besbranchConfmapper.loadAllYqbh(fYqbh, fNybh,groupbh,userId);
//		List<BESBranchConf> organList = besbranchConfmapper.loadOrganization();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if(branchlist.size() > 0) {
			for (BESBranchConf branchconf : branchlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(branchconf.getfZlbh());
				node.setId(branchconf.getfZlbh());
				node.setPid(branchconf.getfPzlbh());
				node.setText(branchconf.getfZlmc());
				nodes.add(node);// 添加到节点容器
			}
		}else {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setId("");
			node.setPid("");
			node.setText("");
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

	/**
	 * 获取单个节点树
	 * @param fZlbh
	 * @return
	 */
	@Override
	public ISSPReturnObject loadOrganization(String fZlbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BesBranchConfAlert> list = new ArrayList<BesBranchConfAlert>();
		try {
			List<ISSPTreeNode> tree = getTreeJsonOrgan(fZlbh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJsonOrgan(String fZlbh) {
		List<BESBranchConf> branchlist = besbranchConfmapper.loadOrganization(fZlbh);
//		List<BESBranchConf> organList = besbranchConfmapper.loadOrganization();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if(branchlist.size() > 0) {
			for (BESBranchConf branchconf : branchlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(branchconf.getfZlbh());
				node.setId(branchconf.getfZlbh());
				node.setPid(branchconf.getfPzlbh());
				node.setText(branchconf.getfZlmc());
				nodes.add(node);// 添加到节点容器
			}
		}else {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setId("");
			node.setPid("");
			node.setText("");
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}


	@Override
	public ISSPReturnObject branchalert_chlildtreegrid(String fZlbh, String yqbh, String nybh, String searchInfo) {
		Map<String,String> map = new HashMap<>();
		map.put("fZlbh",fZlbh);
		map.put("yqbh",yqbh);
		map.put("nybh",nybh);
		map.put("searchInfo",searchInfo);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BesBranchConfAlert> list = new ArrayList<BesBranchConfAlert>();
		try {
			list = besbranchConfAlertmapper.branchalert_chlildtreegrid(map);
			List<Map<String,Object>> typeList = besbranchConfAlertmapper.getAlertTypeInfo();
			List<Map> tempList = new ArrayList<>(typeList);
			Map<String,Object> typeMap = List2Map.list2Map(tempList,"ALARM_TYPE_NUM","ALARM_TYPE_NAME");
			//避免循环访问数据库
			if (list.size()!=0){
				for (int i = 0; i < list.size(); i++) {
					BigInteger typeId = list.get(i).getfAlertypeId();
//					String typeName = besbranchConfAlertmapper.getAlertTypeName(typeId);
					String typeName = ObjectUtils.toString(typeMap.get(String.valueOf(typeId)));
					list.get(i).setfAlertTypeName(typeName);
				}
			}
			returnObject.setList(list);
			returnObject.setMsg("查找子类成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查找子类失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	
	/**
	 * 添加支路报警
	 */
	
	public ISSPReturnObject insbranchalert(BesBranchConfAlert besBranchConfAlert) {
	     ISSPReturnObject returnObject = new ISSPReturnObject();

				String name = besBranchConfAlert.getfAlertname();

				List<Object> list = besbranchConfAlertmapper.selectFalertname(name);

				if (list.size()>0){
					returnObject.setMsg("不允许添加重复报警名称！");
					returnObject.setStatus("0");
				}else {
					boolean flag = besbranchConfAlertmapper.insbranchalert(besBranchConfAlert);

					if (flag) {
						// 查找刚添加的数据，在tabulator展示
						//List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(newBh);
						returnObject.setData(besBranchConfAlert);
						returnObject.setMsg("添加支路报警成功");
						returnObject.setStatus("1");
					} else {
						returnObject.setMsg("添加支路报警失败");
						returnObject.setStatus("0");
					}
				}
		return returnObject;
	}

	/**
	 * 循环插入数据
	 * @param map
	 * @return
	 */
	@Override
	public ISSPReturnObject insertAlertParameter(Map<String, Object> map) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		boolean flag = besbranchConfAlertmapper.insertAlertParameter(map);

		if(flag){
			returnObject.setMsg("保存成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("保存失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public List<Object> selectFalertname(String name) {

		return  besbranchConfAlertmapper.selectFalertname(name);
	}

	/**
	 * 删除支路报警
	 */
	@Transactional
	@Override
	public ISSPReturnObject del_branchalert(String fId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag1 = besbranchConfAlertmapper.del_branchalert_parameter(fId);
		boolean flag = besbranchConfAlertmapper.del_branchalert(fId);
		if (flag1 && flag){
			returnObject.setMsg("删除支路报警成功");
			returnObject.setStatus("1");
			besBranchConfAlarmConfigurationCache.loadCache();
			branchConfParameterCache.loadCache();
		}else {
			returnObject.setMsg("删除支路报警失败");
			returnObject.setStatus("0");
		}
		return returnObject;
		}

	/**
	 * 查询单行支路报警
	 */
	@Override
	public ISSPReturnObject selbranchalert(String fId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BesBranchConfAlert alert = besbranchConfAlertmapper.selbranchalert(fId);
			List<Map<String,Object>> map = besbranchConfAlertmapper.selectConfparameter(fId);
			returnObject.setData(alert);
			returnObject.setList(map);
			returnObject.setMsg("查询单行支路报警成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询单行支路报警失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	
	/**
	 * 更新支路报警
	 */
	@Override
	public ISSPReturnObject updbranchalert(BesBranchConfAlert besBranchConfAlert) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String fId = besBranchConfAlert.getfId();

		String name = besBranchConfAlert.getfAlertname();

		List<Object> list = besbranchConfAlertmapper.selectFalertname(name);

		if (list.size()<=0){//证明没有这条数据 直接插入

			String alertname = null;

			for (int i =0;i<list.size();i++){
				alertname = list.get(i).toString();
			}

			if (name.equals(alertname)){
				returnObject.setMsg("不允许添加重复报警名称！");
				returnObject.setStatus("0");
			}else {

			boolean flag1 = besbranchConfAlertmapper.del_branchalert_parameter(fId);

			if (flag1){//证明删除成功
				//删除成功然后再添加
				String Operator= besBranchConfAlert.getfOperator();

				String date = DateUtil.getCurrTime();

				Boolean op = Operator.contains("$");

				if (op){

					Map<String,Object> paraMap = new HashMap<>();

					String str1 = besBranchConfAlert.getfOperator();

					String[] fsortno = besBranchConfAlert.getFsortno();

					String[] pId1 = besBranchConfAlert.getpId();

					String[] strs = str1.split("\\$");

					String fparameter = besBranchConfAlert.getFparameter();

					String[] str = fparameter.split(",");

					for (int i = 0;i <strs.length-1; i++){

						paraMap.put("F_ALERTID",fId);

						paraMap.put("F_SORTNO",i);

						if (pId1[i] == null){
							paraMap.put("F_ELEDNBH",10000);
							paraMap.put("F_AMMSYS_NAME",str[i]);//电表id
						}else {
							paraMap.put("F_ELEDNBH",fsortno[i]);
							paraMap.put("F_AMMSYS_NAME",pId1[i]);//电表id
						}


						paraMap.put("F_CRDATE",date);

						paraMap.put("F_CHDATE",date);

						besbranchConfAlertmapper.insertAlertParameter(paraMap);

						if (paraMap.size()>0){
							returnObject.setStatus("1");
							returnObject.setMsg("报警信息插入成功");


						}else {
							returnObject.setStatus("0");
							returnObject.setMsg("报警信息插入失败");
						}
					}
				}else {
					returnObject.setStatus("0");
					returnObject.setMsg("字符输入有误");
				}
			}
			boolean flag = besbranchConfAlertmapper.updbranchalert(besBranchConfAlert);
			if (flag) {
				returnObject.setData(besBranchConfAlert);
				returnObject.setMsg("更新支路报警成功");
				returnObject.setStatus("1");
				besBranchConfAlarmConfigurationCache.loadCache();
				branchConfParameterCache.loadCache();
			} else {
				returnObject.setMsg("更新支路报警失败");
				returnObject.setStatus("0");
			}
		}
	}else { //证明有这条数据

//			String alertname = null;
//
//			for (int i = 0; i < list.size(); i++) {
//				alertname = list.get(i).toString();
//			}
//
//			if (list.size()>=1) {
//				returnObject.setMsg("不允许添加重复报警名称！");
//				returnObject.setStatus("0");
//			} else {

				boolean flag1 = besbranchConfAlertmapper.del_branchalert_parameter(fId);

				if (flag1) {//证明删除成功

					boolean flag = besbranchConfAlertmapper.updbranchalert(besBranchConfAlert);

					if (flag) {
						//删除成功然后再添加
						String Operator = besBranchConfAlert.getfOperator();

						String date = DateUtil.getCurrTime();

						Boolean op = Operator.contains("$");

						if (op) {

							Map<String, Object> paraMap = new HashMap<>();

							String str1 = besBranchConfAlert.getfOperator();

							String[] fsortno = besBranchConfAlert.getFsortno();

							String[] pId1 = besBranchConfAlert.getpId();

							String[] strs = str1.split("\\$");

							String fparameter = besBranchConfAlert.getFparameter();

							String[] str = fparameter.split(",");

							for (int i = 0; i < strs.length - 1; i++) {

								paraMap.put("F_ALERTID", fId);

								paraMap.put("F_SORTNO", i);

								if (pId1[i] == null) {
									paraMap.put("F_ELEDNBH", 10000);
									paraMap.put("F_AMMSYS_NAME", str[i]);//电表id
								} else {
									paraMap.put("F_ELEDNBH", fsortno[i]);
									paraMap.put("F_AMMSYS_NAME", pId1[i]);//电表id
								}


								paraMap.put("F_CRDATE", date);

								paraMap.put("F_CHDATE", date);

								besbranchConfAlertmapper.insertAlertParameter(paraMap);

								if (paraMap.size() > 0) {
									returnObject.setData(besBranchConfAlert);
									returnObject.setStatus("1");
									returnObject.setMsg("报警信息修改成功");
								} else {
									returnObject.setStatus("0");
									returnObject.setMsg("报警信息修改失败");
								}
							}
						} else {
							returnObject.setStatus("0");
							returnObject.setMsg("字符输入有误");
						}
					} else {
						returnObject.setStatus("0");
						returnObject.setMsg("字符输入有误");
					}
				}
				boolean flag = besbranchConfAlertmapper.updbranchalert(besBranchConfAlert);
				if (flag){
					returnObject.setData(besBranchConfAlert);
					returnObject.setStatus("1");
					returnObject.setMsg("报警信息修改成功");
					besBranchConfAlarmConfigurationCache.loadCache();
					branchConfParameterCache.loadCache();
				}else {
					returnObject.setStatus("0");
					returnObject.setMsg("报警信息修改失败");
				}
		}
		return returnObject;
   }


	/**
	 * 报警名称验重
	 * @param alertName
	 * @return
	 */
	@Override
	public ISSPReturnObject selectAlertNameRepeat(String alertName) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		List<Object> list = besbranchConfAlertmapper.selectFalertname(alertName);

		if (list.size()>=1){
				returnObject.setMsg("不允许添加重复报警名称！");
				returnObject.setStatus("0");
		}else {
			returnObject.setStatus("1");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getAlertTypeInfo() {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		List<Map<String,Object>> list = besbranchConfAlertmapper.getAlertTypeInfo();

		if (list.size()>0){
			returnObject.setStatus("1");
			returnObject.setList(list);
		}else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}
}


