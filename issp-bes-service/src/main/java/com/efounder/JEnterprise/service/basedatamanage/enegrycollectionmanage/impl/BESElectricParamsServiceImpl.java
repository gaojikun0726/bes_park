package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.initializer.ElectricParamsCache;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectricParamsMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESElectricParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 采集参数接口实现类
 * @author LvSihan
 *
 */
@Service("BESElectricParamsService")
public class BESElectricParamsServiceImpl implements BESElectricParamsService,ESBaseService{
	
	@Autowired
	private BESElectricParamsMapper besElectricParamsMapper;
	@Autowired
	private BESEnergyTypeMapper besEnergyTypeMapper;
	@Autowired
	private ElectricParamsCache electricParamsCache;

	/**
	 * 获取采集参数
	 */
	@Override
	public ISSPReturnObject getElectricPList(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESElectricParams> list = besElectricParamsMapper.getElectricPList(keywords,null);
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).getfDnbh() != null && !"" .equals(list.get(i).getfDnbh())) {
					list.get(i).setfDnbh(list.get(i).getfDnbh());
				}else {
					list.get(i).setfDnbh(null);
				}
			returnObject.setList(list);
			returnObject.setMsg("获取采集参数成功");
			returnObject.setStatus("1");
			}
		} catch (Exception e) {
			returnObject.setMsg("获取采集参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 生成能源类型树
	 */
	@Override
	public ISSPReturnObject ElectricP_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取能源类型树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取能源类型树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	private List<ISSPTreeNode> getTreeJson() {
		List<BESEnergyType> energyTypelist = besEnergyTypeMapper.getEnergyTypeList(null);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BESEnergyType energyType : energyTypelist) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(energyType.getfNybh());
			node.setId(energyType.getfNybh());
			node.setPid("");
			node.setText(energyType.getfNymc());
			//node.setJs(subitemconf.getfJs());
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

	/**
	 * 获取子节点
	 */
	@Override
	public ISSPReturnObject ElectricP_chlildtreegrid(String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESElectricParams> list = besElectricParamsMapper.getListByfNybh(fNybh);
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
	 * 新增
	 */
	@Override
	public ISSPReturnObject add_ElectricP(BESElectricParams besElectricParams) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fNybh = besElectricParams.getfNybh();
		List<BESElectricParams> list = besElectricParamsMapper.getListByfNybh(fNybh);
		String fDnbh = null;
		if(list.size()>0) {
			//获取最大编号
			int maxBh = getMaxBh(list);
			//定义 编号长度
			int bhLength = 7;
			//生成新的编号			
			fDnbh = String.format("%0" + bhLength + "d", maxBh + 1);
		}else {
			fDnbh = fNybh + "001";
		}
		besElectricParams.setfDnbh(fDnbh);
		boolean flag = besElectricParamsMapper.add_ElectricP(besElectricParams);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besElectricParams.setfCrdate(sdf.format(new Date()));
		besElectricParams.setfChdate(sdf.format(new Date()));
		if (flag) {

			// 根据电能编号查询出采取参数信息
			BESElectricParams returnData = besElectricParamsMapper.getElectricP(fDnbh);

			if (returnData != null)
			{
				// 添加到缓存
				electricParamsCache.addOneElectricParamsCache(returnData);
			}

			returnObject.setData(besElectricParams);
			returnObject.setMsg("添加采集参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加采集参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	// 获取最大编号
	private int getMaxBh(List<BESElectricParams> list) {
		int maxnybh = 0;
		for (int i = 0; i < list.size(); i++) {
			String sBh = list.get(i).getfDnbh();
			int iBh = Integer.parseInt(sBh);
			if (maxnybh < iBh) {
				maxnybh = iBh;
			}
		}
		return maxnybh;
	}
	/**
	 * 删除采集参数
	 */
	@Override
	public ISSPReturnObject del_ElectricP(String fDnbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besElectricParamsMapper.del_ElectricP(fDnbh);
		if (flag) {

			// 删除缓存数据
			electricParamsCache.deleteOneElectricParamsCache(fDnbh);

			returnObject.setMsg("删除采集参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("删除采集参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getElectricP(String fDnbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESElectricParams returnData = besElectricParamsMapper.getElectricP(fDnbh);
			returnObject.setData(returnData);
			returnObject.setMsg("fDnbh获取采集参数成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("fDnbh获取采集参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 编辑采集参数
	 */
	@Override
	public ISSPReturnObject edit_ElectricP(BESElectricParams besElectricParams) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besElectricParamsMapper.edit_ElectricP(besElectricParams);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besElectricParams.setfChdate(sdf.format(new Date()));
		if (flag) {

			// 根据电能编号查询出采取参数信息
			BESElectricParams returnData = besElectricParamsMapper.getElectricP(besElectricParams.getfDnbh());


			if (returnData != null)
			{
				// 更新缓存
				electricParamsCache.updateOneElectricParamsCache(returnData);
			}

			returnObject.setData(besElectricParams);
			returnObject.setMsg("编辑采集参数成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("编辑采集参数失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取编码规则
	 * @return
	 */
	@Override
	public ISSPReturnObject getfbmgz() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,String>> returnData = besElectricParamsMapper.getfbmgz();
			returnObject.setData(returnData);
			returnObject.setMsg("获取编码规则成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取编码规则失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

}
