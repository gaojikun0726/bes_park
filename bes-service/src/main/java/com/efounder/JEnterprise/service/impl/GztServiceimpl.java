package com.efounder.JEnterprise.service.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.mapper.basedatamanage.workbench.BesGztzzjgTreeMapper;
import com.efounder.JEnterprise.service.GztService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 杨超
 * @version 创建时间：2018年9月13日 上午10:18:39
 * @parameter
 * @version 1.0
 */
@Service("GztServiceimpl")
public class GztServiceimpl implements GztService {

	@Autowired
	private BesGztzzjgTreeMapper besgztzzjgtreemapper;

	/**
	 * 工作台组织机构树配置
	 */
	@Override
	public ISSPReturnObject gztzzjg_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("获取工作台组织机构树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取工作台组织机构树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJson() {
		List<Map<String, String>> subitemlist = besgztzzjgtreemapper.getzzjg_tree();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if (subitemlist.size() > 0) {
			for (Map<String, String> map : subitemlist) {
				ISSPTreeNode node = new ISSPTreeNode();
                node.setNodeTreeId(map.get("fhbh"));
				node.setId(map.get("id"));
				node.setPid(map.get("pid"));
				node.setText(map.get("text"));
				node.setImage(map.get("url"));// 图片地址
				node.setNodeType(map.get("lx"));// 布局类型
				nodes.add(node);// 添加到节点容器
			}
		} else {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setId("");
			node.setPid("");
			node.setText("");
			node.setImage("");
			node.setNodeType("");// 布局类型
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree =null;
		try{
			buildTree = ISSPTreeBuilder.buildTree(nodes);

		}
		catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}
		return buildTree;
	}
}
