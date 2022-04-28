package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.initializer.CollMethodCache;
import com.efounder.JEnterprise.initializer.ElectricCollRlglCache;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESCollMethodMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectricParamsMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectric_Coll_RlglMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCollMethod;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESCollMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 采集方案接口实现类
 * @author LvSihan
 *
 */
@Service("BESCollMethodService")
public class BESCollMethodServiceImpl implements BESCollMethodService,ESBaseService{
	
	@Autowired
	private BESCollMethodMapper besCollMethodMapper;
	@Autowired
	private BESElectric_Coll_RlglMapper besElectric_Coll_RlglMapper;
	@Autowired
	private BESElectricParamsMapper besElectricParamsMapper;
	@Autowired
	private CollMethodCache collMethodCache;
	@Autowired
	private ElectricCollRlglCache electricCollRlglCache;

	/**
	 * 获取采集方案列表
	 */
	@Override
	public ISSPReturnObject getCollMethodList(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESCollMethod> list = besCollMethodMapper.getCollMethodList(keywords);
			returnObject.setList(list);
			returnObject.setMsg("获取采集方案成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取采集方案失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 通过组织机构查询采集方案列表
	 */
	@Override
	public ISSPReturnObject getCMbyZzjgbh(String fyqbh,String fnybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESCollMethod> list = besCollMethodMapper.getCMbyZzjgbh(fyqbh,fnybh);
			returnObject.setList(list);
			returnObject.setMsg("获取采集方案成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取采集方案失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}
	/**
	 * 新增采集方案
	 */
	@Override
	public ISSPReturnObject add_CollM(BESCollMethod besCollMethod) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESCollMethod> list = besCollMethodMapper.getCollMethodList(null);
		String fCjfabh = null;
		if(list.size()>0) {
			//获取最大编号
			int maxBh = getMaxBh(list);
			//定义 编号长度
			int bhLength = 4;
			//生成新的编号			
			fCjfabh = String.format("%0" + bhLength + "d", maxBh + 1);
		}else {
			fCjfabh = "0001";
		}
		besCollMethod.setfCjfabh(fCjfabh);
		boolean flag = besCollMethodMapper.add_CollM(besCollMethod);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besCollMethod.setfCrdate(sdf.format(new Date()));
		besCollMethod.setfChdate(sdf.format(new Date()));		
		if (flag) {

			BESCollMethod besCollMethod1 = besCollMethodMapper.getCMbyCjfabh(fCjfabh);

			if (besCollMethod1 != null)
			{
				// 添加到缓存
				collMethodCache.addOneCollMethodCache(besCollMethod1);
			}

			returnObject.setData(besCollMethod);
			returnObject.setMsg("添加采集方案成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加采集方案失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}	
	// 获取最大编号
	private int getMaxBh(List<BESCollMethod> list) {
		int maxnybh = 0;
		for (int i = 0; i < list.size(); i++) {
			String sBh = list.get(i).getfCjfabh();
			int iBh = Integer.parseInt(sBh);
			if (maxnybh < iBh) {
				maxnybh = iBh;
			}
		}
		return maxnybh;
	}
	/**
	 * 编辑采集方案
	 */
	@Override
	public ISSPReturnObject edit_CollM(BESCollMethod besCollMethod) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besCollMethodMapper.edit_CollM(besCollMethod);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besCollMethod.setfChdate(sdf.format(new Date()));
		if (flag) {

			BESCollMethod besCollMethod1 = besCollMethodMapper.getCMbyCjfabh(besCollMethod.getfCjfabh());

			if (besCollMethod1 != null)
			{
				// 更新缓存
				collMethodCache.updateOneCollMethodCache(besCollMethod1);
			}

			returnObject.setData(besCollMethod);
			returnObject.setMsg("修改采集方案成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("修改采集方案失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 删除采集方案
	 */
	@Override
	public ISSPReturnObject del_CollM(BESCollMethod besCollMethod) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besCollMethodMapper.del_CollM(besCollMethod);
		if (flag) {

			// 删除缓存数据
			collMethodCache.deleteOneCollMethodCache(besCollMethod.getfCjfabh());

			returnObject.setMsg("删除采集方案成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("删除采集方案失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 通过采集方案编号获取采集方案
	 */
	@Override
	public ISSPReturnObject getCMbyCjfabh(String fCjfabh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESCollMethod returnData = besCollMethodMapper.getCMbyCjfabh(fCjfabh);
			returnObject.setData(returnData);
			returnObject.setMsg("获取采集方案成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取采集方案失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 查询未包含的能耗参数
	 */
	@Override
	public ISSPReturnObject loadNoIncludeCollM(BESElectric_Coll_Rlgl besECR, String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询采集方案对应的能耗参数
		List<BESElectric_Coll_Rlgl> ECRList = besElectric_Coll_RlglMapper.queryECRList(besECR.getfCjfabh());
		//通过采集方案编号，获取该条数据
		BESCollMethod CollM = besCollMethodMapper.getCMbyCjfabh(besECR.getfCjfabh());
		String fNybh = CollM.getfNybh();
		//查询能耗参数列表
		List<BESElectricParams> EPList = besElectricParamsMapper.getElectricPList(keywords,fNybh);
		try {
			for (int i = 0; i < ECRList.size(); i++) {
				for (int j = 0; j < EPList.size(); j++) {
					if(EPList.get(j).getfDnbh().equals(ECRList.get(i).getfNhbh())){
						EPList.remove(j);
					}					
				}
			}
			returnObject.setList(EPList);
			returnObject.setMsg("查询采集方案未包含的能耗参数成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询采集方案未包含的能耗参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 查询已包含的能耗参数
	 */
	@Override
	public ISSPReturnObject loadIncludeCollM(BESElectric_Coll_Rlgl besECR, String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESElectricParams> EpList = new ArrayList<>();
		List<BESElectricParams> newEpList = new ArrayList<>();
		//通过采集方案编号，获取该条数据
		BESCollMethod CollM = besCollMethodMapper.getCMbyCjfabh(besECR.getfCjfabh());
		String fNybh = CollM.getfNybh();
		
		try {
			List<BESElectric_Coll_Rlgl> ECRList = besElectric_Coll_RlglMapper.queryECRList(besECR.getfCjfabh());
			for (int i = 0; i < ECRList.size(); i++) {
				BESElectricParams EP = besElectricParamsMapper.getElectricP(ECRList.get(i).getfNhbh());
				EP.setfStatisticalParam(ECRList.get(i).getfStatisticalParam());
				EP.setfIsRate(ECRList.get(i).getfIsRate());
				EpList.add(i, EP);
			}
			if(keywords != null){
				//查询能耗参数列表
				List<BESElectricParams> EpListResult = besElectricParamsMapper.getElectricPList(keywords,fNybh);
				int index = 0;
				for (int i = 0; i < EpList.size(); i++) {
					for (int j = 0; j < EpListResult.size(); j++) {
						if(EpListResult.get(j).getfDnbh().equals(EpList.get(i).getfDnbh())){
							EpListResult.get(j).setfStatisticalParam(EpList.get(i).getfStatisticalParam());
							newEpList.add(index++, EpListResult.get(j));
						}
					}
				}
			}else{
				newEpList = EpList;
			}
			returnObject.setList(newEpList);
			returnObject.setMsg("查询方案包含的能耗参数成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询方案包含的能耗参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 新增
	 */
	@Override
	public ISSPReturnObject CollMethod_insertElectricP(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besElectric_Coll_RlglMapper.CollMethod_insertElectricP(besECR);
		if (flag) {
			BESElectricParams EP = besElectricParamsMapper.getElectricP(besECR.getfNhbh());

			BESElectric_Coll_Rlgl besElectricCollRlgl = besElectric_Coll_RlglMapper.queryByDnbhAndCjfabh(besECR);

			if (besElectricCollRlgl != null)
			{
				// 添加到缓存
				electricCollRlglCache.addOneCollMethodCache(besElectricCollRlgl);
			}

			returnObject.setData(EP);
			returnObject.setMsg("添加成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 移除
	 */
	@Override
	public ISSPReturnObject CollMethod_deleteElectricP(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besElectric_Coll_RlglMapper.CollMethod_deleteElectricP(besECR);
		if (flag) {

			// 删除缓存数据
			electricCollRlglCache.deleteOneCollMethodCache(besECR.getfCjfabh(), besECR.getfNhbh());

			BESElectricParams EP = besElectricParamsMapper.getElectricP(besECR.getfNhbh());
			returnObject.setData(EP);
			returnObject.setMsg("删除成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("删除失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 添加全部
	 */
	@Override
	public ISSPReturnObject CollMethod_rightmoveAll(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESElectric_Coll_Rlgl> ECRList = besElectric_Coll_RlglMapper.queryECRList(besECR.getfCjfabh());
		//通过采集方案编号，获取该条数据
		BESCollMethod CollM = besCollMethodMapper.getCMbyCjfabh(besECR.getfCjfabh());
		String fNybh = CollM.getfNybh();
		//查询所有能耗参数
		List<BESElectricParams> EPList = besElectricParamsMapper.getElectricPList(null,fNybh);
		
    	for (int i = 0; i < ECRList.size(); i++) {
			for (int j = 0; j < EPList.size(); j++) {
				if(EPList.get(j).getfDnbh().equals(ECRList.get(i).getfNhbh())){
					EPList.remove(j);
				}
			}
		}
    	boolean flag = false;
    	for (int i = 0; i < EPList.size(); i++) {
    		besECR.setfNhbh(EPList.get(i).getfDnbh());
    		if(i == 0) {
    			besECR.setfStatisticalParam("1");
    		}else {
    			besECR.setfStatisticalParam("0");
    		}
    		 flag = besElectric_Coll_RlglMapper.CollMethod_insertElectricP(besECR);

    		if (flag)
			{

				BESElectric_Coll_Rlgl besElectricCollRlgls = besElectric_Coll_RlglMapper.queryByDnbhAndCjfabh(besECR);
				if (besElectricCollRlgls != null)
				{
					// 添加到缓存
					electricCollRlglCache.addOneCollMethodCache(besElectricCollRlgls);
				}
			}
    	}
		if (flag) {
			returnObject.setList(EPList);
			returnObject.setMsg("添加所有能耗参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加所有能耗参数失败");
			returnObject.setStatus("0");
		}
    	
		return returnObject;
	}
	/**
	 * 移除全部
	 */
	@Override
	public ISSPReturnObject CollMethod_leftmoveAll(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besElectric_Coll_RlglMapper.CollMethod_leftmoveAll(besECR);
		if (flag) {

			// 删除缓存根据采集方案编号
			electricCollRlglCache.deleteCollMethodCacheByCjfabh(besECR.getfCjfabh());

			returnObject.setMsg("移除全部成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("移除全部失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 更新统计参数
	 */
	@Override
	public ISSPReturnObject update_inclu_fStatisticalParam(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();		
		//更新本条数据
		boolean flag = besElectric_Coll_RlglMapper.update_inclu_fStatisticalParam(besECR);
		//将相同采集方案下的其他参数设置为 否
		List<BESElectric_Coll_Rlgl> rlglLists = besElectric_Coll_RlglMapper.queryrlglList(besECR);
		for(BESElectric_Coll_Rlgl rlglList : rlglLists) {
			rlglList.setfStatisticalParam("0");
			besElectric_Coll_RlglMapper.update_inclu_fStatisticalParam(rlglList);
		}
		
		if (flag) {

			List<BESElectric_Coll_Rlgl>  besElectricCollRlgls = besElectric_Coll_RlglMapper.queryECRList(besECR.getfCjfabh());

			if (besElectricCollRlgls != null && !besElectricCollRlgls.isEmpty())
			{
				besElectricCollRlgls.forEach(item -> electricCollRlglCache.updateOneAmmeterCache(item));
			}

			returnObject.setMsg("更新统计参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新统计参数失败");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}
	/**
	 * 更新是否变比
	 */
	@Override
	public ISSPReturnObject update_inclu_fIsRate(BESElectric_Coll_Rlgl besECR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//更新本条数据
		boolean flag = besElectric_Coll_RlglMapper.update_inclu_fIsRate(besECR);

		if (flag) {

			BESElectric_Coll_Rlgl besElectricCollRlgls = besElectric_Coll_RlglMapper.queryByDnbhAndCjfabh(besECR);
			if (besElectricCollRlgls != null)
			{
				// 更新缓存
				electricCollRlglCache.updateOneAmmeterCache(besElectricCollRlgls);
			}

			returnObject.setMsg("更新变比参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新变比参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 生成能源类型树
	 */
	@Override
	public ISSPReturnObject getParkEnergytree(String yqbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson(yqbh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取园区能源类型树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取园区能源类型树失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJson(String yqbh) {
		List<Map<String,String>> energyTypelist = besCollMethodMapper.findPark_EnergyType(yqbh);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点

		//添加园区为父节点
		if(energyTypelist != null && energyTypelist.size() > 0)
		{
			Map<String,String> map = energyTypelist.get(0);
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId("yq_"+map.get("F_YQBH"));
			node.setId("yq_"+map.get("F_YQBH"));
			node.setPid("");
			node.setText(map.get("F_YQMC"));
			//node.setJs(subitemconf.getfJs());
			nodes.add(node);// 添加到节点容器
		}
		//添加能源类型为子节点
		for (Map<String,String> energyType : energyTypelist) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(energyType.get("F_NYBH"));
			node.setId(energyType.get("F_NYBH"));
			node.setPid("yq_"+energyType.get("F_YQBH"));
			node.setText(energyType.get("F_NYMC"));
			//node.setJs(subitemconf.getfJs());
			nodes.add(node);// 添加到节点容器
		}

		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}




}
