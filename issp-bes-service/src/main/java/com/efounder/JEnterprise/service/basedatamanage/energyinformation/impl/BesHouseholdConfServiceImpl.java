package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.initializer.HouseholdBranchRlglCache;
import com.efounder.JEnterprise.initializer.HouseholdConfigCache;
import com.efounder.JEnterprise.mapper.applicationmanage.ESBmjgMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESParkMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesHouseholdBranchRlglMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesHouseholdConfMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdConf;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesHouseholdConfService;
import com.framework.common.utils.ScopeDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 分户配置树Impl
 * 
 * @author suhang
 * @date 2018-07-19
 */
@Service("besHouseholdConfService")
public class BesHouseholdConfServiceImpl implements BesHouseholdConfService {
	private static final Logger log = LoggerFactory.getLogger(BesHouseholdConfServiceImpl.class);

	@Autowired
	private ESBmjgMapper esBmjgMapper;
	@Autowired

	private BESBranchConfMapper besBranchConfMapper;
	@Autowired
	private BesHouseholdBranchRlglMapper besHouseholdBranchRlglMapper;
	@Autowired
	private BesHouseholdConfMapper besHouseholdConfMapper;
	@Autowired
	private BESParkMapper besParkMapper;
	@Autowired
	private BESEnergyTypeMapper besenergyTypeMapper;
	@Autowired
	private HouseholdConfigCache householdConfigCache;
	@Autowired
	private HouseholdBranchRlglCache householdBranchRlglCache;


	/**
	 * 查询全部分户配置信息
	 * @return
	 */
	@Override
	public List<BesHouseholdConf> findAll()
	{
		return besHouseholdConfMapper.findAll();
	}

	/**
	 * 获取分户配置树
	 * 
	 * @param besHouseholdConf
	 * @return
	 */
	@Override
	public ISSPReturnObject getHouseholdList(BesHouseholdConf besHouseholdConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesHouseholdConf> list = besHouseholdConfMapper.getHouseholdList(besHouseholdConf);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getfFhbh() != null && !"".equals(list.get(i).getfFhbh())) {
					list.get(i).setfFhbh(list.get(i).getfFhbh());
				} else {
					list.get(i).setfFhbh(null);
				}
			}
			returnObject.setList(list);
			returnObject.setMsg("获取分户数据配置成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分户数据配置失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 生成分户配置树
	 * 
	 * @param fYqbh
	 * @return
	 */
	@Override
	public ISSPReturnObject loadAll(String fYqbh,String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJsonDeploy(fYqbh,fNybh);// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("获取分户配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分户配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJsonDeploy(String fYqbh,String fNybh) {

//		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
//		String userId = ScopeDataUtil.getUserIdScope();

		List<String> groupbh = null;
		String userId = null;

		List<BesHouseholdConf> beshouselist = besHouseholdConfMapper.loadAll(fYqbh,fNybh, groupbh, userId);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if (beshouselist.size() > 0) {
			for (BesHouseholdConf beshouseconf : beshouselist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(beshouseconf.getfFhbh());
				node.setId(beshouseconf.getfFhbh());
				node.setPid(beshouseconf.getfPfhbh());
				node.setText(beshouseconf.getfFhmc());
				node.setNodeStatus(beshouseconf.getfJs());
				node.setNodeType(beshouseconf.getfFhbh());
				nodes.add(node);// 添加到节点容器
			}
		} else {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setId("");
			node.setPid("");
			node.setText("");
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

	private List<ISSPTreeNode> getTreeJson(String fYqbh,String fNybh) {

		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();

		List<BesHouseholdConf> beshouselist = besHouseholdConfMapper.loadAll(fYqbh,fNybh, groupbh, userId);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if (beshouselist.size() > 0) {
			for (BesHouseholdConf beshouseconf : beshouselist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(beshouseconf.getfFhbh());
				node.setId(beshouseconf.getfFhbh());
				node.setPid(beshouseconf.getfPfhbh());
				node.setText(beshouseconf.getfFhmc());
				node.setNodeStatus(beshouseconf.getfJs());
				node.setNodeType(beshouseconf.getfFhbh());
				nodes.add(node);// 添加到节点容器
			}
		} else {
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
	 * 生成园区类型树
	 */
	@Override
	public ISSPReturnObject Park_tree() {
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
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		List<BESPark> list = besParkMapper.findAllPark(groupbh,userId);// 从数据库获取园区所有资源
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

	}

	/**
	 * 查找子类
	 */
	@Override
	public ISSPReturnObject household_chlildtreegrid(String fFhbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BesHouseholdConf> list = new ArrayList<BesHouseholdConf>();
		try {
			//获取用户和组织编号
//			List<String> groupbh = ScopeDataUtil.getGroupIdScope();
//			String userId = ScopeDataUtil.getUserIdScope();

			List<String> groupbh = null;
			String userId = null;

			list = besHouseholdConfMapper.household_chlildtreegrid(fFhbh,groupbh,userId);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getfPfhbh() == null || "".equals(list.get(i).getfPfhbh())) {
					list.get(i).setfPfhbh(null);
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
	 * 删除分户
	 */
	@Override
	public ISSPReturnObject del_household(String fFhbh) throws Exception {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {

			//删除分户的时候,先判断分户中是否关联的支路,如果关联,则将分户支路配置表中的相应的数据一起删除
			List<Map<String,Object>> bes_household_branch_rlglList = besHouseholdConfMapper.selectBes_household_branch_rlglList(fFhbh);
			if (bes_household_branch_rlglList.size() > 0) {
				returnObject.setMsg("当前分户已关联支路,当请删除当前分户的支路再操作");
				returnObject.setStatus("0");
				return returnObject;
			}
			/*if (bes_household_branch_rlglList.size() > 0) {
				Boolean deleteHousehold_branch_rlgl = besHouseholdConfMapper.deleteHousehold_branch_rlgl(fFhbh);
				if (!deleteHousehold_branch_rlgl) {
					returnObject.setMsg("删除分户失败,当前分户包含的支路删除失败");
					returnObject.setStatus("0");
					return returnObject;
				}
			}*/

			boolean flag = besHouseholdConfMapper.del_household(fFhbh);

			if (flag) {
				// 删除缓存中的数据
				householdConfigCache.deleteOneHouseholdConfCache(fFhbh);
				returnObject.setMsg("删除分户成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("删除分户失败");
				returnObject.setStatus("0");
			}

		} catch (Exception e) {
			returnObject.setMsg("删除分户失败,当前分户已有数据");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 添加分户
	 */
	@Override
	public ISSPReturnObject add_household(BesHouseholdConf besHouseholdConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String  userName          = besHouseholdConf.getfUserName();
		String  zzjg_id             = besHouseholdConf.getfZzjgId();
		if (!StringUtils.hasText(userName) ||
				!StringUtils.hasText(zzjg_id)) {
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误！");
			return returnObject;
		}

		besHouseholdConf.setfJl("0");//是否级联,默认为0,级联
		ESBmjg bmjg = esBmjgMapper.findById("bes_household_conf");
		// 级数其实是父节点的级数，所以将级数+1
		int householdJs = Integer.parseInt(besHouseholdConf.getfJs()) + 1;
		besHouseholdConf.setfJs(Integer.toString(householdJs));
		if (householdJs > bmjg.getBmjg().length()) {
			returnObject.setStatus("0");
			returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
			return returnObject;
		}
		// 根据级数 从编码结构中获取当前级位数
		String sbm = bmjg.getBmjg().substring(householdJs - 1, householdJs);
		int ibm = Integer.parseInt(sbm);

		// 获取父节点下所有子节点
		String pfhbh = besHouseholdConf.getfPfhbh();
		//获取用户和组织编号
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		List<BesHouseholdConf> householdList = besHouseholdConfMapper.household_chlildtreegrid(pfhbh, groupbh, userId);
		//update by liuzhen 20181121 解决分户无法添加bug
		List<BesHouseholdConf> householdOneList = new ArrayList<>();
		for(BesHouseholdConf householdConf: householdList)
		{
			if( "2".equals(householdConf.getfJs()) &&  "".equals(pfhbh))
			{
				householdOneList.add(householdConf);
			}
		}
		String newBh = null;
		if(householdList.size() == 1 || householdList.size() == 0){
			int maxBh = getMaxBh(householdOneList);
			// //编号长度
			int bhLen = besHouseholdConf.getfPfhbh().length() + ibm;
			// //生成组织机构编号 补齐编号
			newBh = String.format("%0" + bhLen + "d", maxBh + 1);
			besHouseholdConf.setfFhbh(newBh);
		}
		if (householdList.size() > 1) {
			// //获取子节点最大编号
			int maxBh = getMaxBh(householdList);
			// //编号长度
			int bhLen = besHouseholdConf.getfPfhbh().length() + ibm;
			// //生成组织机构编号 补齐编号
			newBh = String.format("%0" + bhLen + "d", maxBh + 1);
			besHouseholdConf.setfFhbh(newBh);
		}
		else {
			if (StringUtils.hasText(pfhbh))
			{
				// //生成支路编号 补齐编号
				newBh = besHouseholdConf.getfPfhbh() + String.format("%0" + ibm + "d", 1);
				besHouseholdConf.setfFhbh(newBh);
			}

		}
		if (besHouseholdConf != null) {

			//判断分户名称是否重复
//			List<BesHouseholdConf> fzlmc = besHouseholdConfMapper.selectffhmc(besHouseholdConf.getfFhmc());
//			if (fzlmc.size() > 0) {
//				returnObject.setStatus("0");
//				returnObject.setMsg("新增失败，分户名称重复!");
//				return returnObject;
//			}

			boolean flag = besHouseholdConfMapper.add_household(besHouseholdConf);
			if (flag) {

				// 保存到缓存中
				householdConfigCache.addOneHouseholdConfCache(besHouseholdConf);

				// 查找刚添加的数据，在tabulator展示
				List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(newBh);
				returnObject.setList(returnList);
				returnObject.setMsg("添加分户成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("添加分户失败");
				returnObject.setStatus("0");
			}
		}
		return returnObject;	
	}

	/**
	 * 获取最大编号
	 */
	private int getMaxBh(List<BesHouseholdConf> householdList) {
		int maxugbh = 0;
		for (int i = 0; i < householdList.size(); i++) {
			String sBh = householdList.get(i).getfFhbh();
			int iBh = Integer.parseInt(sBh);
			if (maxugbh < iBh) {
				maxugbh = iBh;
			}
		}
		return maxugbh;
	}

	/**
	 * 查询分户
	 */
	@Override
	public ISSPReturnObject queryhousehold(String fFhbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(fFhbh);
			returnObject.setList(returnList);
			returnObject.setMsg("查询编辑分户信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询编辑分户信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 编辑分户
	 */
	@Override
	public ISSPReturnObject edit_household(BesHouseholdConf besHouseholdConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		//判断分户名称是否重复
//		List<BesHouseholdConf> ffhmc = besHouseholdConfMapper.selectffhmc(besHouseholdConf.getfFhmc());
//		if (ffhmc.size() > 0) {
//			if (!ffhmc.get(0).getfFhbh().equals(besHouseholdConf.getfFhbh())) {
//				returnObject.setStatus("0");
//				returnObject.setMsg("更新失败，分户名称重复!");
//				return returnObject;
//			}
//		}

		String  userName          = besHouseholdConf.getfUserName();
		String  zzjg_id             = besHouseholdConf.getfZzjgId();
		if (!StringUtils.hasText(userName) ||
				!StringUtils.hasText(zzjg_id)) {
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误！");
			return returnObject;
		}

		boolean flag = besHouseholdConfMapper.edit_household(besHouseholdConf);

		if (flag) {
			List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(besHouseholdConf.getfFhbh());
			// 更新缓存中的数据
			householdConfigCache.updateOneHouseholdConfCache(returnList.get(0));
			returnObject.setList(returnList);
			returnObject.setMsg("更新分户配置成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新分户配置失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 查询分户未包含支路信息
	 */
	@Override
	public ISSPReturnObject loadNoBrc(BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		//通过fFxbh查询'分户与支路关系表'中支路HBRList
		List<BesHouseholdBranchRlgl> HBRList = besHouseholdBranchRlglMapper.queryHOUList(besHBR.getfFhbh());
		//通过分户编号获取该条数据
		List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(besHBR.getfFhbh());
		String fYqbh = returnList.get(0).getfYqbh();
		String fNybh = returnList.get(0).getfNybh();
		besBranchConf.setfYqbh(fYqbh);
		besBranchConf.setfNybh(fNybh);
		//查询与分户配置相同fYqbh和fNybh的支路信息
		List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);
		
		try {
			for (int i = 0; i < HBRList.size(); i++) {
				for (int j = 0; j < BranchList.size(); j++) {
					if(BranchList.get(j).getfZlbh().equals(HBRList.get(i).getfZlbh())){
						BranchList.remove(j);
					}					
				}
			}
			returnObject.setList(BranchList);
			returnObject.setMsg("查询分户未包含支路信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询分户未包含支路信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 查询分户包含支路信息
	 */
	@Override
	public ISSPReturnObject loadInBrc(BesHouseholdBranchRlgl besHBR, BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		List<BESBranchConf> BranchList = new ArrayList<>();
		List<BESBranchConf> newBranchList = new ArrayList<>();

		try {
		//通过fFxbh查询'分户与支路关系表'中支路HBRList
		List<BesHouseholdBranchRlgl> HBRList = besHouseholdBranchRlglMapper.queryHOUList(besHBR.getfFhbh());
		for (int i = 0; i < HBRList.size(); i++) {
			BESBranchConf branch = besBranchConfMapper.getBranchConf(HBRList.get(i).getfZlbh());
			BranchList.add(i, branch);
		}
		if(besBranchConf.getKeywords() != null){
			//通过分户编号获取该条数据
			List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(besHBR.getfFhbh());
			String fYqbh = returnList.get(0).getfYqbh();
			String fNybh = returnList.get(0).getfNybh();
			besBranchConf.setfYqbh(fYqbh);
			besBranchConf.setfNybh(fNybh);
			//查询与分户配置相同fYqbh和fNybh的支路信息
			List<BESBranchConf> BranchListResult = besBranchConfMapper.getBranchConfList(besBranchConf);
			int index = 0;
			for (int i = 0; i < BranchList.size(); i++) {
				for (int j = 0; j < BranchListResult.size(); j++) {
					if(BranchListResult.get(j).getfZlbh().equals(BranchList.get(i).getfZlbh())){
						newBranchList.add(index++, BranchListResult.get(j));
					}
				}
			}
		}else{
			newBranchList = BranchList;
		}		
		returnObject.setList(newBranchList);
		returnObject.setMsg("查询分户包含的支路信息成功");
		returnObject.setStatus("1");
	} catch (Exception e) {
		returnObject.setMsg("查询分户包含的支路信息失败");
		returnObject.setStatus("0");
	}
		return returnObject;
	}

	/**
	 *
	 * @Description: 显示是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:19 2020/11/18
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject loadShowCascade(BesHouseholdBranchRlgl besHBR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fFhbh = besHBR.getfFhbh();
		String f_jl = besHouseholdBranchRlglMapper.selectf_jl(fFhbh);
		if (f_jl == null) {
			returnObject.setMsg("是否级联查询失败");
			returnObject.setStatus("0");
		} else {
			returnObject.setMsg("是否级联查询成功");
			returnObject.setData(f_jl);
			returnObject.setStatus("1");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 修改是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:51 2020/11/18
	 * @param: [fJl, fFhbh]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject changef_jl(String fJl, String fFhbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Boolean changef_jl = besHouseholdBranchRlglMapper.changef_jl(fJl,fFhbh);
		if (changef_jl) {
			returnObject.setMsg("修改成功");
			returnObject.setStatus("0");
		} else {
			returnObject.setMsg("修改失败");
			returnObject.setStatus("1");

		}
		return returnObject;
	}

	/**
	 * 分户中添加支路信息
	 */
	@Override
	public ISSPReturnObject beshousehold_instBranch(BesHouseholdBranchRlgl besHBR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		boolean flag = false;
		String fJl = besHBR.getfJl();
		flag = besHouseholdBranchRlglMapper.beshousehold_instBranch(besHBR);

		// 更新缓存
		if (flag)
		{
			BesHouseholdBranchRlgl besHouseholdBranchRlgl = new BesHouseholdBranchRlgl();
			besHouseholdBranchRlgl.setfFhbh(besHBR.getfFhbh());
			besHouseholdBranchRlgl.setfJl(besHBR.getfJl());
			besHouseholdBranchRlgl.setfZlbh(besHBR.getfZlbh());
			householdBranchRlglCache.putOneCache(besHouseholdBranchRlgl);

		}

		if (fJl.equals("0")) {//级联
			beshousehold_instF_jl(besHBR);
		}
		if (flag) {
			BESBranchConf branch = besBranchConfMapper.getBranchConf(besHBR.getfZlbh());
			returnObject.setData(branch);
			returnObject.setMsg("添加成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 递归添加单个支路点位
	 *
	 * @auther: wanghongjie
	 * @date: 16:08 2020/11/18
	 * @param: [fZlbh]
	 * @return: void
	 *
	 */
	private void beshousehold_instF_jl(BesHouseholdBranchRlgl fZlbh) {
		//查询当前分户编号的信息
		Map<String,Object> f_pfhbh = besHouseholdConfMapper.selectf_pfhbh(fZlbh);
		boolean flag = false;
		String pfhbh = (String) f_pfhbh.get("F_PFHBH");

		if (!pfhbh.isEmpty() && pfhbh != null) {

			Map<String,Object> f_pfhbhs = besHouseholdConfMapper.selectf_pfhbhs(pfhbh);//查询父节点的信息
			fZlbh.setfJl((String) f_pfhbhs.get("F_JL"));
			fZlbh.setfFhbh(pfhbh);
			if (fZlbh.getfJl().equals("0")) {
				//根据分户编号查询关联的支路
				Map<String,Object> zlbh = besHouseholdBranchRlglMapper.selectZlbh(fZlbh);
				if (zlbh == null) {
					flag = besHouseholdBranchRlglMapper.beshousehold_instBranch(fZlbh);

					// 更新缓存
					if (flag)
					{
						BesHouseholdBranchRlgl besHouseholdBranchRlgl = new BesHouseholdBranchRlgl();
						besHouseholdBranchRlgl.setfFhbh(fZlbh.getfFhbh());
						besHouseholdBranchRlgl.setfJl(fZlbh.getfJl());
						besHouseholdBranchRlgl.setfZlbh(fZlbh.getfZlbh());
						householdBranchRlglCache.putOneCache(besHouseholdBranchRlgl);

					}
				}
			}
			beshousehold_instF_jl(fZlbh);
		}
	}

	/**
	 * 分户中移除支路
	 */	
	@Override
	public ISSPReturnObject beshousehold_delBranch(BesHouseholdBranchRlgl besHBR) {
			ISSPReturnObject returnObject = new ISSPReturnObject();	
			boolean flag = besHouseholdBranchRlglMapper.beshousehold_delBranch(besHBR);
			if (flag) {

				// 更新缓存
				householdBranchRlglCache.deleteOneCache(besHBR.getfFhbh(), besHBR.getfZlbh());

				if (besHBR.getfJl().equals("0")) {
					//往上 级联删除
					beshousehold_delBranch_jl_up(besHBR);
					//往下 级联删除
					beshousehold_delBranch_jl_down(besHBR);
				}

				BESBranchConf branch = besBranchConfMapper.getBranchConf(besHBR.getfZlbh());

				returnObject.setData(branch);
				returnObject.setMsg("删除成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("删除失败");
				returnObject.setStatus("0");
			}
			return returnObject;
		}


	/**
		 *
		 * @Description: 分户中移除支路 往上 级联删除
		 *
		 * @auther: wanghongjie
		 * @date: 16:41 2020/11/18
		 * @param: [besHBR]
		 * @return: void
		 *
		 */
	private void beshousehold_delBranch_jl_up(BesHouseholdBranchRlgl fZlbh) {
		//查询当前分户编号的信息
		Map<String, Object> f_pfhbh = besHouseholdConfMapper.selectf_pfhbh(fZlbh);
		boolean flag = false;
		String pfhbh = (String) f_pfhbh.get("F_PFHBH");

		if (!pfhbh.isEmpty() && pfhbh != null) {

			Map<String, Object> f_pfhbhs = besHouseholdConfMapper.selectf_pfhbhs(pfhbh);//查询父节点的信息
			BesHouseholdBranchRlgl BranchRlgl = new BesHouseholdBranchRlgl();
			BranchRlgl.setfJl((String) f_pfhbhs.get("F_JL"));
			BranchRlgl.setfFhbh(pfhbh);
			BranchRlgl.setfZlbh(fZlbh.getfZlbh());
//			fZlbh.setfJl((String) f_pfhbhs.get("F_JL"));
//			fZlbh.setfFhbh(pfhbh);
			if (BranchRlgl.getfJl().equals("0")) {
				//根据分户编号查询关联的支路
				Map<String, Object> zlbh = besHouseholdBranchRlglMapper.selectZlbh(BranchRlgl);
				if (zlbh != null) {
					flag = besHouseholdBranchRlglMapper.beshousehold_delBranch_jl_up(BranchRlgl);

					if (flag)
					{
						// 更新缓存
						householdBranchRlglCache.deleteOneCache(BranchRlgl.getfFhbh(), BranchRlgl.getfZlbh());
					}
				}
			}
			beshousehold_delBranch_jl_up(BranchRlgl);
		}
	}

	/**
	 *
	 * @Description: 分户中移除支路 往下 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 17:23 2020/11/18
	 * @param: [besHBR]
	 * @return: void
	 *
	 */
	private void beshousehold_delBranch_jl_down(BesHouseholdBranchRlgl besHBR) {
		List<Map<String, Object>> f_pfhbh = besHouseholdConfMapper.selectf_pfhbh_down(besHBR);
		if (f_pfhbh.size() > 0) {
			for (Map<String,Object> f_pfhbhs : f_pfhbh) {
				if (f_pfhbhs.get("F_JL").equals("0")) {
					//查询分户支路关系表中是否存在当前分户关联的支路
					Map<String,Object> besHouseholdBranchRlglzlbh = besHouseholdBranchRlglMapper.selectBesHouseholdBranchRlglZlbh((String) f_pfhbhs.get("F_FHBH"),besHBR.getfZlbh());
					if (besHouseholdBranchRlglzlbh != null) {
						//删除当前分户支路的点位信息
						Boolean delFhAndZl = besHouseholdBranchRlglMapper.delFhAndZl((String) f_pfhbhs.get("F_FHBH"),besHBR.getfZlbh());

						if (delFhAndZl)
						{
							// 更新缓存
							householdBranchRlglCache.deleteOneCache((String) f_pfhbhs.get("F_FHBH"), besHBR.getfZlbh());
						}
					}
				}
				besHBR.setfFhbh((String) f_pfhbhs.get("F_FHBH"));
				beshousehold_delBranch_jl_down(besHBR);
			}

		}
	}

	/**
	 * 分户中移除全部支路
	 * @param besHBR
	 * @return
	 */
	@Override
	public ISSPReturnObject beshousehold_leftmoveAll(BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (besHBR.getfJl().equals("0")) {
			//查询当前分户所属的全部支路信息
			List<BesHouseholdBranchRlgl> BranchList = besHouseholdBranchRlglMapper.selectBranchList(besHBR.getfFhbh(),besBranchConf.getKeywords());
			//往上 级联删除
			beshousehold_delBranch_jl_up_all(besHBR,BranchList,besBranchConf.getKeywords());
			//往下 级联删除

			Set<String> vf = new HashSet<>();
			for (BesHouseholdBranchRlgl dd : BranchList) {
				vf.add(dd.getfZlbh());
			}
			beshousehold_delBranch_jl_down_all(besHBR,vf,besBranchConf.getKeywords());
		}
		boolean flag = besHouseholdBranchRlglMapper.beshousehold_leftmoveAll(besHBR.getfFhbh(),besBranchConf.getKeywords());
		if (flag) {
			// 更新缓存
			householdBranchRlglCache.deleteCacheByfFhbh(besHBR.getfFhbh());
			returnObject.setMsg("移除全部成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("无数据移动");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}

	private void beshousehold_delBranch_jl_up_all(BesHouseholdBranchRlgl besHBR,List<BesHouseholdBranchRlgl> BranchList,String Keywords) {
		//查询当前分户的信息
		List<BesHouseholdConf> besHouseholdConf = besHouseholdConfMapper.queryhousehold(besHBR.getfFhbh());
		//获取父节点的名称
		String f_pfhbh = besHouseholdConf.get(0).getfPfhbh();
		if (f_pfhbh != null && !f_pfhbh.isEmpty()) {
			//查询当前父分户的信息
			List<BesHouseholdConf> besHouseholdConf_pfhbh = besHouseholdConfMapper.queryhousehold(f_pfhbh);
			if (besHouseholdConf_pfhbh.get(0).getfJl().equals("0")) {
				//查询当前分户所属的全部支路信息
				List<BesHouseholdBranchRlgl> BranchList_pfhbh = besHouseholdBranchRlglMapper.selectBranchList(f_pfhbh,Keywords);
				for (BesHouseholdBranchRlgl fhAndZl : BranchList) {
					for (BesHouseholdBranchRlgl fhAndZl_p : BranchList_pfhbh) {
						if (fhAndZl.getfZlbh().equals(fhAndZl_p.getfZlbh())) {
							//删除父节点的支路信息
							Boolean delBesHouseholdBranchRlgl = besHouseholdBranchRlglMapper.delFhAndZl(f_pfhbh,fhAndZl.getfZlbh());

							if (delBesHouseholdBranchRlgl)
							{
								householdBranchRlglCache.deleteOneCache(f_pfhbh, fhAndZl.getfZlbh());
							}
						}
					}
				}
			}
			BesHouseholdBranchRlgl besHBR_p = new BesHouseholdBranchRlgl();
			besHBR_p.setfFhbh(f_pfhbh);
			beshousehold_delBranch_jl_up_all(besHBR_p,BranchList,Keywords);
		}

	}

	private void beshousehold_delBranch_jl_down_all(BesHouseholdBranchRlgl besHBR,Set<String> vf,String Keywords) {
	//分户中移除支路 往下 级联删除,查询父分户编号为当前分户编号的所有信息
		List<Map<String, Object> > f_pfhbh = besHouseholdConfMapper.selectf_pfhbh_down(besHBR);
		if (f_pfhbh.size() > 0) {
			for (Map<String, Object> f_pfhbhs : f_pfhbh) {

				if (f_pfhbhs.get("F_JL").equals("0")) {
					//查询当前分户所属的全部支路信息
					List<BesHouseholdBranchRlgl> BranchList_pfhbh = besHouseholdBranchRlglMapper.selectBranchList((String) f_pfhbhs.get("F_FHBH"),Keywords);

					for (BesHouseholdBranchRlgl fhAndZl_p : BranchList_pfhbh) {

						if (vf.contains(fhAndZl_p.getfZlbh())) {

							//删除父节点的支路信息
							BesHouseholdBranchRlgl besHBR_p = new BesHouseholdBranchRlgl();
							besHBR_p.setfFhbh((String) f_pfhbhs.get("F_FHBH"));
							beshousehold_delBranch_jl_down_all(besHBR_p,vf,Keywords);
							Boolean delBesHouseholdBranchRlgl = besHouseholdBranchRlglMapper.delFhAndZl((String) f_pfhbhs.get("F_FHBH"),fhAndZl_p.getfZlbh());

							if (delBesHouseholdBranchRlgl)
							{
								householdBranchRlglCache.deleteOneCache((String) f_pfhbhs.get("F_FHBH"), fhAndZl_p.getfZlbh());
							}

						}
					}
				} else {
					//删除父节点的支路信息
					BesHouseholdBranchRlgl besHBR_p = new BesHouseholdBranchRlgl();
					besHBR_p.setfFhbh((String) f_pfhbhs.get("F_FHBH"));
					beshousehold_delBranch_jl_down_all(besHBR_p,vf,Keywords);
				}
			}
		}
	}

	/**
	 * 分户中添加全部支路
	 * @param besHBR besBranchConf
	 * @return
	 */
	@Override
	public ISSPReturnObject beshousehold_rightmoveAll(BesHouseholdBranchRlgl besHBR,BESBranchConf  besBranchConf) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		//通过分户编号获取该条数据
		List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(besHBR.getfFhbh());
		String fYqbh = returnList.get(0).getfYqbh();
		String fNybh = returnList.get(0).getfNybh();
		besBranchConf.setfYqbh(fYqbh);
		besBranchConf.setfNybh(fNybh);
		//查询与分户配置相同fYqbh和fNybh的支路信息

		List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);

		//查询分户编号对用的所有支路
		List<BesHouseholdBranchRlgl> SBRList = besHouseholdBranchRlglMapper.queryHOUList(besHBR.getfFhbh());

    	for (int i = 0; i < SBRList.size(); i++) {
			for (int j = 0; j < BranchList.size(); j++) {
				if(BranchList.get(j).getfZlbh().equals(SBRList.get(i).getfZlbh())){
					BranchList.remove(j);
				}
			}
		}
    	boolean flag = false;
    	for (int i = 0; i < BranchList.size(); i++) {
    		besHBR.setfZlbh(BranchList.get(i).getfZlbh());
    		 flag = besHouseholdBranchRlglMapper.beshousehold_instBranch(besHBR);

			// 更新缓存
			if (flag)
			{
				BesHouseholdBranchRlgl besHouseholdBranchRlgl = new BesHouseholdBranchRlgl();
				besHouseholdBranchRlgl.setfFhbh(besHBR.getfFhbh());
				besHouseholdBranchRlgl.setfJl(besHBR.getfJl());
				besHouseholdBranchRlgl.setfZlbh(besHBR.getfZlbh());
				householdBranchRlglCache.putOneCache(besHouseholdBranchRlgl);

			}
    	}

		if (flag) {
			if (besHBR.getfJl().equals("0")) {//如果级联选择的是"是",则往父节点添加支路点位
				beshousehold_rightmoveAll_jl(besHBR,besBranchConf);
			}

			returnObject.setList(BranchList);
			returnObject.setMsg("添加所有支路成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加所有支路失败");
			returnObject.setStatus("0");
		}
    	
		return returnObject;
	}

	/**
	 *
	 * @Description: 递归添加支路点位
	 *
	 * @auther: wanghongjie
	 * @date: 16:07 2020/11/18
	 * @param: [besHBR, besBranchConf]
	 * @return: void
	 *
	 */
	private void beshousehold_rightmoveAll_jl(BesHouseholdBranchRlgl besHBR, BESBranchConf besBranchConf) {
		//查询当前分户编号的信息
		Map<String,Object> f_pfhbh = besHouseholdConfMapper.selectf_pfhbh(besHBR);
		String pfhbh = (String) f_pfhbh.get("F_PFHBH");//获取父节点的编号
		if (!pfhbh.isEmpty() && pfhbh != null) {
		//通过父节点的分户编号获取该条数据
		List<BesHouseholdConf> returnList = besHouseholdConfMapper.queryhousehold(pfhbh);
		String fYqbh = returnList.get(0).getfYqbh();
		String fNybh = returnList.get(0).getfNybh();
		besBranchConf.setfYqbh(fYqbh);
		besBranchConf.setfNybh(fNybh);
		//查询与分户配置相同fYqbh和fNybh的支路信息
		List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);
		//查询分户编号对用的所有支路
		List<BesHouseholdBranchRlgl> SBRList = besHouseholdBranchRlglMapper.queryHOUList(pfhbh);

		for (int i = 0; i < SBRList.size(); i++) {
			for (int j = 0; j < BranchList.size(); j++) {
				if(BranchList.get(j).getfZlbh().equals(SBRList.get(i).getfZlbh())){
					BranchList.remove(j);
				}
			}
		}

		Map<String,Object> f_pfhbhs = besHouseholdConfMapper.selectf_pfhbhs(pfhbh);//查询父节点的信息
		besHBR.setfJl((String) f_pfhbhs.get("F_JL"));
		besHBR.setfFhbh(pfhbh);
		if (besHBR.getfJl().equals("0")) {

			for (int i = 0; i < BranchList.size(); i++) {

				besHBR.setfZlbh(BranchList.get(i).getfZlbh());
				//根据分户编号查询关联的支路
				Map<String,Object> zlbh = besHouseholdBranchRlglMapper.selectZlbh(besHBR);
				if (zlbh == null) {
					boolean flag = besHouseholdBranchRlglMapper.beshousehold_instBranch(besHBR);

					// 更新缓存
					if (flag)
					{
						BesHouseholdBranchRlgl besHouseholdBranchRlgl = new BesHouseholdBranchRlgl();
						besHouseholdBranchRlgl.setfFhbh(besHBR.getfFhbh());
						besHouseholdBranchRlgl.setfJl(besHBR.getfJl());
						besHouseholdBranchRlgl.setfZlbh(besHBR.getfZlbh());
						householdBranchRlglCache.putOneCache(besHouseholdBranchRlgl);

					}
				}
			}
		}
		BesHouseholdBranchRlgl besHBRs = new BesHouseholdBranchRlgl();
		BESBranchConf besBranchConfs = new BESBranchConf();
		besHBRs.setfFhbh(pfhbh);
		besBranchConfs.setKeywords(besBranchConf.getKeywords());
		beshousehold_rightmoveAll_jl(besHBRs,besBranchConfs);
	}

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

	/**
	 * 生成分户配置树
	 */
	@Override
	public ISSPReturnObject houseHold_treegrid(String fYqbh,String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson(fYqbh,fNybh);// 获得一棵树模型的数据
		try {
			returnObject.setList(tree);
			returnObject.setMsg("根据能源类型获取分户配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("根据能源类型获取分户配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;		
	}


	/**
	 * 生成分户配置树
	 */
	@Override
	public ISSPReturnObject houseHold_treegrid1(String fNybh1,String fYqbh1) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson1(fNybh1,fYqbh1);// 获得一棵树模型的数据
		for (ISSPTreeNode  nodes:tree){
			if (("nodes").equals(nodes)){
				System.out.println(nodes);
			}
		}
		try {
			returnObject.setList(tree);
			returnObject.setMsg("根据能源类型获取分户配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("根据能源类型获取分户配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	/**
	 * 获取能源类型 sunzhiyuan
	 * @param f_yqbh
	 * @return
	 */
	@Override
	public List<Map<String,Object>> getenrrgylist(String f_yqbh) {
		log.info("#park_energytypeMapper获取个别园区与能耗类型关系信息");
		return besHouseholdConfMapper.getenergylist(f_yqbh);
	}

	private List<ISSPTreeNode> getTreeJson1(String fNybh1,String fYqbh1) {
		List<BesHouseholdConf> beshouselist = besHouseholdConfMapper.loadAll1(fNybh1,fYqbh1);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if (beshouselist.size() > 0) {
			for (BesHouseholdConf beshouseconf : beshouselist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(beshouseconf.getfFhbh());
				node.setId(beshouseconf.getfFhbh());
				node.setPid(beshouseconf.getfPfhbh());
				node.setText(beshouseconf.getfFhmc());
				node.setFnybh(fNybh1);
				nodes.add(node);// 添加到节点容器
			}
		} else {
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
	 * 查询园区编号(去重)
	 * @return
	 * 孙志远
	 */
	@Override
	public ISSPReturnObject removeYqbhCf() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> returnList = besHouseholdConfMapper.removeYqbhCf();
			returnObject.setList(returnList);
			returnObject.setMsg("查询园区信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询园区信息失败");
			returnObject.setStatus("0");
		}
		return  returnObject;
	}

	/**
	 * 查询能源编号(去重)
	 * @return
	 * 孙志远
	 */
	@Override
	public ISSPReturnObject removeNybhCf(String fYqbh1) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> returnList = besHouseholdConfMapper.removeNybhCf(fYqbh1);
			returnObject.setList(returnList);
			returnObject.setMsg("查询园区信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询园区信息失败");
			returnObject.setStatus("0");
		}
		return  returnObject;
	}

	/**
	 *查询所有能源类型
	 * @return
	 */
	public ISSPReturnObject selectAlleneryType(){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> returnList = besHouseholdConfMapper.selectAlleneryType();
			returnObject.setList(returnList);
			returnObject.setMsg("查询园区信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询园区信息失败");
			returnObject.setStatus("0");
		}
		return  returnObject;
	}


	/**
	 *根据能源类型查询园区编号
	 * @return
	 */
	public ISSPReturnObject selectAllPark(String nybh){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> returnList = besHouseholdConfMapper.selectAllPark(nybh);
			returnObject.setList(returnList);
			returnObject.setMsg("查询园区信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询园区信息失败");
			returnObject.setStatus("0");
		}
		return  returnObject;
	}

	/**
	 *三表联查的数据
	 * @return
	 */
	public  ISSPReturnObject selectAll(){

		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> returnList = besHouseholdConfMapper.selectAll();
			returnObject.setList(returnList);
			returnObject.setMsg("查询园区信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询园区信息失败");
			returnObject.setStatus("0");
		}
		return  returnObject;
	}

	/**
	 * 根据园区编号查询园区名称
	 * @param yqbh
	 * @return
	 */
	public  String selectYqmc(String yqbh){

			String yqmc = besHouseholdConfMapper.selectYqmc(yqbh);
		return yqmc ;
	}

	/**
	 * 获取主页树数据
	 * @return
	 */
	@Override
	public ISSPReturnObject getHomePageTreeData(String fYqbh, String fNybh)
	{

		ISSPReturnObject result = new ISSPReturnObject();
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		List<Map<String, Object>> returnList = besHouseholdConfMapper.getHomePageTreeData(fYqbh, fNybh,groupbh,userId);
		result.setData(returnList);
		result.setStatus("1");
		return result;

//		ISSPReturnObject result = new ISSPReturnObject();
//		List<Map<String, Object>> returnList = besHouseholdConfMapper.getHomePageTreeData(fYqbh, fNybh);
//		result.setData(returnList);
//		result.setStatus("1");
//		return result;
	}

	/**
	 * 获取分项数据
	 * @return
	 */
	@Override
	public ISSPReturnObject getSubitemTreeData() {
		ISSPReturnObject result = new ISSPReturnObject();
		List<Map<String,Object>> returnList = besHouseholdConfMapper.getSubitemTreeData();
		result.setData(returnList);
		result.setStatus("1");
		return result;
	}

	/**
	 *
	 * @Description: 获取用户列表
	 *
	 * @auther: wanghongjie
	 * @date: 10:31 2020/12/28
	 * @param: []
	 * @return: java.util.List<com.efounder.JEnterprise.model.usercenter.ESUser>
	 *
	 */
	@Override
	public List<ESUser> getuserNameList() {
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		List<ESUser> list = besHouseholdConfMapper.getuserNameList(groupbh);
		return list;
	}

	/**
	 *
	 * @Description: 获取组织机构列表
	 *
	 * @auther: wanghongjie
	 * @date: 14:04 2021/1/5
	 * @param: []
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	@Override
	public List<Map<String, Object>> getZZJGList() {
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		List<Map<String, Object>> list = besHouseholdConfMapper.getZZJGList(groupbh);
		return list;
	}


	/**
	 *
	 * @Description: 获取建筑信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:26 2021/5/25
	 * @param: []
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
    @Override
    public List<Map<String, Object>> getBuilding() {
		List<Map<String, Object>> list = besHouseholdConfMapper.getBuilding();
		return list;
    }

}

