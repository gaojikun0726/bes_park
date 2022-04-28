package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.initializer.BranchAmmeteRrlglCache;
import com.efounder.JEnterprise.initializer.BranchConfAlarmConfigurationCache;
import com.efounder.JEnterprise.initializer.BranchConfParameterCache;
import com.efounder.JEnterprise.initializer.BranchConfigCache;
import com.efounder.JEnterprise.mapper.applicationmanage.ESBmjgMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranch_Ammeter_RlglMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesBranchConfAlertMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchAmmeterLog;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfAlarmConfiguration;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfParameter;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESBranchConfService;
import com.framework.common.utils.ScopeDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 支路配置实现类
 * @author LvSiHan
 *
 */
@Service("BESBranchConfService")
public class BESBranchConfServiceImpl implements BESBranchConfService ,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESBranchConfServiceImpl.class);

	@Autowired
	private BESBranchConfMapper besBranchConfMapper;
	@Autowired
	private ESBmjgMapper esBmjgMapper;
	@Autowired
	private BESAmmeterMapper besAmmeterMapper;
	@Autowired
	private BESBranch_Ammeter_RlglMapper besBranch_Ammeter_RlglMapper;

	@Autowired
	private BranchConfigCache branchConfigCache;

	@Autowired
	private BranchAmmeteRrlglCache branchAmmeteRrlglCache;

	@Resource
	private BranchConfParameterCache branchConfParameterCache;

	@Resource
	private BranchConfAlarmConfigurationCache branchConfAlarmConfigurationCache;

	@Autowired
	private BesBranchConfAlertMapper besbranchConfAlertmapper;

	/**
	 * 查询全部填充tabulator
	 */
	@Override
	public ISSPReturnObject getBranchConfList(BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESBranchConf> list = besBranchConfMapper.getBranchConfList(besBranchConf);
			returnObject.setList(list);
			returnObject.setMsg("搜索支路配置成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("搜索支路配置失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 获取支路配置树
	 */
	@Override
	public ISSPReturnObject getTree(String fnybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson(fnybh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}



	private List<ISSPTreeNode> getTreeJson(String fnybh) {

		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();

		List<BESBranchConf> branchlist = besBranchConfMapper.loadAll(fnybh, groupbh, userId);// 从数据库获取所有资源
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
	 * 查找子类
	 */
	@Override
	public ISSPReturnObject findChildByZlbh(String fZlbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESBranchConf> list = new ArrayList<BESBranchConf>();
		try {
//			list = besBranchConfMapper.findChildByZlbh(fZlbh);

			//获取用户和组织编号
//			List<String> groupbh = ScopeDataUtil.getGroupIdScope();
//			String userId = ScopeDataUtil.getUserIdScope();

			List<String> groupbh = null;
			String userId = null;

			list = besBranchConfMapper.querySelfChildByZlbh(fZlbh,groupbh,userId);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getfPzlbh() == null || "".equals(list.get(i).getfPzlbh())) {
					list.get(i).setfPzlbh(null);
				}
			}
			System.out.println(list);
			returnObject.setList(list);
			returnObject.setMsg("获取用户组树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取用户组树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 添加支路
	 */
	public ISSPReturnObject add_branch(BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		besBranchConf.setfJl("0");//默认级联
		ESBmjg bmjg = esBmjgMapper.findById("bes_branch_conf");
		// 级数其实是父节点的级数，所以将级数+1
		int branchJs = Integer.parseInt(besBranchConf.getfJs()) + 1;
		besBranchConf.setfJs(Integer.toString(branchJs));
		if (branchJs > bmjg.getBmjg().length()) {
			returnObject.setStatus("1");
			returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
			return returnObject;
		}

		// 根据级数 从编码结构中获取当前级位数
		String sbm = bmjg.getBmjg().substring(branchJs - 1, branchJs);
		int ibm = Integer.parseInt(sbm);

		// 获取父节点下所有子节点
		List<BESBranchConf> branchList = besBranchConfMapper.findChildByZlbh(besBranchConf.getfPzlbh());

		String newBh = null;

		//生成支路编号
		if (besBranchConf.getfPzlbh() != null && "06".equals(besBranchConf.getfPzlbh())){
			/*//父节点是06产业园时,则格式为1_one, 2_two, 3_three...
			if (branchList.size() > 0){
				String maxBh = toEnglish(branchList.size() + 1);
				newBh = branchList.size()+1 + "_" + maxBh;
				besBranchConf.setfZlbh(newBh);
			} else {
				besBranchConf.setfZlbh("1_one");
			}*/

			//父节点是06产业园时,则格式为061, 062, 063...
			if (branchList.size() > 0){
				// //获取楼号的最大编号
				long maxBh = getMaxBh2(branchList);

				// //生成组织机构编号 06+最大编号
				newBh = besBranchConf.getfPzlbh() + (maxBh + 1);
				besBranchConf.setfZlbh(newBh);
			} else {
				besBranchConf.setfZlbh("061");
			}

		} else {
			if (branchList.size() > 0) {
				// //获取子节点最大编号
				long maxBh = getMaxBh(branchList);

				// //生成组织机构编号 父级编号+2位最大编号
				newBh = besBranchConf.getfPzlbh() + String.format("%0" + ibm + "d", maxBh + 1);
				besBranchConf.setfZlbh(newBh);
			} else {
				// //生成支路编号 补齐编号
				newBh = besBranchConf.getfPzlbh() + String.format("%0" + ibm + "d", 1);
				besBranchConf.setfZlbh(newBh);
			}
		}


		if (besBranchConf != null) {

//			//判断支路名称是否重复
//			List<BESBranchConf> fzlmc = besBranchConfMapper.selectfzlmc(besBranchConf.getfZlmc());
//			if (fzlmc.size() > 0) {
//				returnObject.setStatus("0");
//				returnObject.setMsg("新增失败，支路名称重复!");
//				return returnObject;
//			}

			boolean flag = besBranchConfMapper.addBranch(besBranchConf);
			if (flag) {
				//查找刚添加的数据，在tabulator展示
				List<BESBranchConf> returnList = besBranchConfMapper.queryBranch(newBh);
				// 添加到缓存
				branchConfigCache.addOneBranchConfCache(returnList.get(0));
				returnObject.setList(returnList);
				returnObject.setMsg("添加支路成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("添加支路失败");
				returnObject.setStatus("0");
			}
		}
		return returnObject;
	}

	// 获取最大编号
	private long getMaxBh(List<BESBranchConf> branchList) {
		long maxugbh = 0;
		for (int i = 0; i < branchList.size(); i++) {
			String sBh = branchList.get(i).getfZlbh();
			long iBh = Long.parseLong(sBh.substring(sBh.length()-2));
			if (maxugbh < iBh) {
				maxugbh = iBh;
			}
		}
		return maxugbh;
	}
	// 获取楼号最大编号
	private long getMaxBh2(List<BESBranchConf> branchList) {
		long maxugbh = 0;
		for (int i = 0; i < branchList.size(); i++) {
			String sBh = branchList.get(i).getfZlbh();
			long iBh = Long.parseLong(sBh.substring(sBh.length()-1));
			if (maxugbh < iBh) {
				maxugbh = iBh;
			}
		}
		return maxugbh;
	}

	private static final String[] BITS = {"one", "two", "three", "four", "five",
			"six", "seven", "eight,", "nine", "ten"};
	private static final String[] TEENS = {"eleven", "twelve", "thirteen",
			"fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nighteen"};
	private static final String[] TIES = {"twenty", "thirty", "forty", "fifty",
			"sixty", "seventy", "eighty", "ninety"};

	private static String toEnglish(int num) {
		if(num == 0) {
			return "zero";
		}
		StringBuffer buffer = new StringBuffer();
		if(num >= 100) {
			buffer.append(pickHunder(num));
			if(num % 100 != 0) {
				buffer.append("_and");
			}
			num -= (num / 100) * 100;
		}
		boolean largerThan20 = false;
		if(num >= 20) {
			largerThan20 = true;
			buffer.append(pickTies(num));
			num -= (num / 10) * 10;
		}
		if(!largerThan20 && num > 10) {
			buffer.append(pickTeens(num));
			num = 0;
		}
		if(num > 0) {
			String bit = pickBits(num);
			if(largerThan20) {
				buffer.append("_");
			}
			buffer.append(bit);
		}
		return buffer.toString();
	}

	private static String pickHunder(int num) {
		int hunder = num / 100;
		return BITS[hunder - 1] + "_hundred";
	}

	private static String pickTies(int num) {
		int ties = num / 10;
		return TIES[ties - 2];
	}

	private static String pickTeens(int num) {
		return TEENS[num - 11];
	}

	private static String pickBits(int num) {
		return BITS[num - 1];
	}
	/**
	 * 删除支路
	 */
	@Override
	public ISSPReturnObject del_branch(String fZlbh) throws DataAccessException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
//		try {

		//删除前判断分户中是否添加此支路
		List<Map<String,Object>> householdDeploy = besBranchConfMapper.selectHouseholdDeploy(fZlbh);
		if (householdDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("分户计量拓扑配置中已配置此支路,请删除分户中关联的支路再操作");
			return returnObject;
		}

		//删除前判断分项中是否添加此支路
		List<Map<String,Object>> subOptionDeploy = besBranchConfMapper.selectSubOptionDeploy(fZlbh);
		if (subOptionDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("分项计量拓扑配置中已配置此支路,请删除分项中关联的支路再操作");
			return returnObject;
		}

		//删除前判断支路报警配置中是否关联此支路
		List<Map<String,Object>> branchAlarmDeploy = besBranchConfMapper.selectBranchAlarmDeploy(fZlbh);
		if (branchAlarmDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("支路报警配置中已配置此支路的报警,请删除支路报警配置中的报警配置再操作");
			return returnObject;
		}
		//删除前判断支路与电表关系表中是否关联电表
		List<Map<String,Object>> branchAmmeterDeploy = besBranchConfMapper.selectBranchAmmeterDeploy(fZlbh);
		if (branchAmmeterDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("当前支路已配置电表,请删除当前支路关联的电表再操作");
			return returnObject;
		}
			boolean flag = besBranchConfMapper.del_branch(fZlbh);
			if (flag) {

				// 删除缓存
				branchConfigCache.deleteOneBranchConfCache(fZlbh);

				//删除相关电表操作记录
				besBranchConfMapper.delBranchAmmeterLog(fZlbh);

				returnObject.setMsg("删除支路成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("删除支路失败");
				returnObject.setStatus("0");
			}

//		} catch (DataAccessException  e) {
//			returnObject.setMsg("删除支路失败,当前支路已存入数据");
//			returnObject.setStatus("0");
//		}



		return returnObject;
	}

	@Override
	public ISSPReturnObject queryBranch(String fZlbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESBranchConf> returnList = besBranchConfMapper.queryBranch(fZlbh);
			returnObject.setList(returnList);
			returnObject.setMsg("查询编辑支路信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询编辑支路信息失败");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}

	/**
	 * 编辑支路信息
	 */
	@Override
	public ISSPReturnObject edit_branch(BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		//判断支路名称是否重复
//		List<BESBranchConf> fzlmc = besBranchConfMapper.selectfzlmc(besBranchConf.getfZlmc());
//		if (fzlmc.size() > 0) {
//			if (!fzlmc.get(0).getfZlbh().equals(besBranchConf.getfZlbh())) {
//				returnObject.setStatus("0");
//				returnObject.setMsg("更新失败，支路名称重复!");
//				return returnObject;
//			}
//		}

		boolean flag = besBranchConfMapper.edit_branch(besBranchConf);

		if (flag) {
			List<BESBranchConf> returnList = besBranchConfMapper.queryBranch(besBranchConf.getfZlbh());
			// 更新缓存
			branchConfigCache.updateOneBranchConfCache(returnList.get(0));
			returnObject.setList(returnList);
			returnObject.setMsg("更新支路成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新支路失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 查询支路未包含的电表信息
	 */
	@Override
	public ISSPReturnObject loadNoIncludeAmt(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESAmmeter> AmmeterList = getNoIncludeAmt(besBAR,keywords);
			returnObject.setList(AmmeterList);
			returnObject.setMsg("查询支路未包含电表信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询支路未包含电表信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	public List<BESAmmeter> getNoIncludeAmt(BESBranch_Ammeter_Rlgl besBAR, String keywords) {
		// 通过fZlbh查询'支路与电表关系表'中支路BARList
		List<BESBranch_Ammeter_Rlgl> BARList = besBranch_Ammeter_RlglMapper.queryBARList(besBAR.getfZlbh());
		// 查询'电表信息表'中电表信息
		List<BESAmmeter> AmmeterList = besAmmeterMapper.queryAmmeterList(keywords);

		for (int i = 0; i < BARList.size(); i++) {
			for (int j = 0; j < AmmeterList.size(); j++) {
				if (AmmeterList.get(j).getfSysNameOld().equals(BARList.get(i).getfDbsysName())) {
					AmmeterList.remove(j);
				}
			}
		}
		return AmmeterList;
	}
	/**
	 * 查询支路包含的电表信息
	 */
	@Override
	public ISSPReturnObject loadIncludeAmt(BESBranch_Ammeter_Rlgl besBAR, String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		List<BESAmmeter> newAmmeterList = getIncludeAmt(besBAR,keywords);
		returnObject.setList(newAmmeterList);
		returnObject.setMsg("查询支路未包含电表信息成功");
		returnObject.setStatus("1");
	} catch (Exception e) {
		returnObject.setMsg("查询支路未包含电表信息失败");
		returnObject.setStatus("0");
	}
		return returnObject;
	}
	public List<BESAmmeter> getIncludeAmt(BESBranch_Ammeter_Rlgl besBAR, String keywords) {
		List<BESAmmeter> AmmeterList = new ArrayList<>();
		List<BESAmmeter> newAmmeterList = new ArrayList<>();
		//通过fZlbh查询'支路与电表关系表'中支路BARList
		List<BESBranch_Ammeter_Rlgl> BARList = besBranch_Ammeter_RlglMapper.queryBARList(besBAR.getfZlbh());
		for (int i = 0; i < BARList.size(); i++) {
			BESAmmeter ammeter = besAmmeterMapper.queryAmmeter(BARList.get(i).getfDbsysName());
			ammeter.setfOperator(BARList.get(i).getfOperator());
			AmmeterList.add(i, ammeter);
		}
		if(keywords != null){
			List<BESAmmeter> AmmeterListResult = besAmmeterMapper.queryAmmeterList(keywords);
			int index = 0;
			for (int i = 0; i < AmmeterList.size(); i++) {
				for (int j = 0; j < AmmeterListResult.size(); j++) {
					if(AmmeterListResult.get(j).getfSysNameOld().equals(AmmeterList.get(i).getfSysNameOld())){
						AmmeterListResult.get(j).setfOperator(AmmeterList.get(i).getfOperator());
						newAmmeterList.add(index++, AmmeterListResult.get(j));
					}
				}
			}
		}else{
			newAmmeterList = AmmeterList;
		}		
		return newAmmeterList;
	}
	/**
	 *
	 * @Description: 显示是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:19 2020/11/19
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject loadShowCascade(BESBranch_Ammeter_Rlgl besHBR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fZlbh = besHBR.getfZlbh();
		String f_jl = besBranchConfMapper.selectf_jl(fZlbh);
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
	 * @date: 16:56 2020/11/19
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject changef_jl(String fJl, String fZlbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Boolean changef_jl = besBranchConfMapper.changef_jl(fJl,fZlbh);
		if (changef_jl) {

			List<BESBranchConf> returnList = besBranchConfMapper.queryBranch(fZlbh);
			// 更新缓存
			branchConfigCache.updateOneBranchConfCache(returnList.get(0));

			returnObject.setMsg("修改成功");
			returnObject.setStatus("0");
		} else {
			returnObject.setMsg("修改失败");
			returnObject.setStatus("1");

		}
		return returnObject;
	}


    /**
	 * 支路中添加电表
	 */
	@Override
	public ISSPReturnObject branchconf_insertAmmeter(BESBranch_Ammeter_Rlgl besBAR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besBranch_Ammeter_RlglMapper.branchconf_insertAmmeter(besBAR);

		// 更新缓存
		if (flag)
		{
			BESBranch_Ammeter_Rlgl branchAmmeterRlgl = new BESBranch_Ammeter_Rlgl();

			branchAmmeterRlgl.setfOperator(besBAR.getfOperator());
			branchAmmeterRlgl.setfChdate(besBAR.getfChdate());
			branchAmmeterRlgl.setfJl(besBAR.getfJl());
			branchAmmeterRlgl.setfZlbh(besBAR.getfZlbh());
			branchAmmeterRlgl.setfCrdate(besBAR.getfCrdate());
			branchAmmeterRlgl.setfDbsysName(besBAR.getfDbsysName());

			branchAmmeteRrlglCache.putOneCache(branchAmmeterRlgl);

			//添加电表操作记录
			BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
			besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
			besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
			besBranchAmmeterLog.setfOperator(besBAR.getfOperator());
			besBranchAmmeterLog.setfType("1");

			besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);
		}
		String fJl = besBAR.getfJl();

		//父级级联添加
		branchconf_instF_jl(besBAR);

		if (flag) {
			BESAmmeter ammeter = besAmmeterMapper.queryAmmeter(besBAR.getfDbsysName());

			returnObject.setData(ammeter);
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
	private void branchconf_instF_jl(BESBranch_Ammeter_Rlgl besBAR) {
		//查询当前支路编号的信息
		Map<String,Object> f_zlhbh = besBranchConfMapper.selectf_pzlbh(besBAR);
		boolean flag = false;
		String pzlbh = (String) f_zlhbh.get("F_PZLBH");

		if (!pzlbh.isEmpty() && pzlbh != null) {

			Map<String,Object> f_pzlbhs = besBranchConfMapper.selectf_pzlbhs(pzlbh);//查询父节点的信息
			besBAR.setfJl((String) f_pzlbhs.get("F_JL"));
			besBAR.setfZlbh(pzlbh);
			if ("0".equals(besBAR.getfJl())) {
				//根据支路编号查询关联的电表
				Map<String,Object> zlbh = besBranch_Ammeter_RlglMapper.selectZlbh(besBAR);
				if (zlbh == null) {
					flag = besBranch_Ammeter_RlglMapper.branchconf_insertAmmeter(besBAR);

					// 更新缓存
					if (flag)
					{
						BESBranch_Ammeter_Rlgl branchAmmeterRlgl = new BESBranch_Ammeter_Rlgl();

						branchAmmeterRlgl.setfOperator(besBAR.getfOperator());
						branchAmmeterRlgl.setfChdate(besBAR.getfChdate());
						branchAmmeterRlgl.setfJl(besBAR.getfJl());
						branchAmmeterRlgl.setfZlbh(besBAR.getfZlbh());
						branchAmmeterRlgl.setfCrdate(besBAR.getfCrdate());
						branchAmmeterRlgl.setfDbsysName(besBAR.getfDbsysName());

						branchAmmeteRrlglCache.putOneCache(branchAmmeterRlgl);

						//添加电表操作记录
						BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
						besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
						besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
						besBranchAmmeterLog.setfOperator(besBAR.getfOperator());
						besBranchAmmeterLog.setfType("1");

						besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);
					}
				}
				branchconf_instF_jl(besBAR);
			}

		}
	}

	/**
	 * 支路中移除电表
	 */
	@Override
	public ISSPReturnObject branchconf_deleteAmmeter(BESBranch_Ammeter_Rlgl besBAR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//获取支路编号
		String zlbh = besBAR.getfZlbh();

		boolean flag = besBranch_Ammeter_RlglMapper.branchconf_deleteAmmeter(besBAR);


		if (flag) {
			// 更新缓存
			branchAmmeteRrlglCache.deleteOneCache(besBAR.getfZlbh(), besBAR.getfDbsysName());

			//添加电表操作记录
			BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
			besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
			besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
			besBranchAmmeterLog.setfOperator(besBAR.getfOperator());
			besBranchAmmeterLog.setfType("0");

			besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);

			//往上 级联删除
			branchconf_deleteAmmeter_jl_up(besBAR);
				//往下 级联删除
//				branchconf_deleteAmmeter_jl_down(besBAR);

			BESAmmeter ammeter = besAmmeterMapper.queryAmmeter(besBAR.getfDbsysName());

			//获取电表系统名称
			String fDbsysName = besBAR.getfDbsysName();

			//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
			delect(fDbsysName,zlbh);

			returnObject.setData(ammeter);
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
	 * @Description: 支路中移除电表 往上 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 11:44 2020/11/20
	 * @param: [besBAR]
	 * @return: void
	 *
	 */
	private void branchconf_deleteAmmeter_jl_up(BESBranch_Ammeter_Rlgl besBAR) {

		//查询当前支路编号的信息
		Map<String, Object> f_pzlbh = besBranchConfMapper.selectf_pzlbh(besBAR);
		boolean flag = false;
		String pzlbh = (String) f_pzlbh.get("F_PZLBH");

		if (!pzlbh.isEmpty() && pzlbh != null) {

			Map<String, Object> f_pfhbhs = besBranchConfMapper.selectf_pzlbhs(pzlbh);//查询父节点的信息
			BESBranch_Ammeter_Rlgl BranchRlgl = new BESBranch_Ammeter_Rlgl();
			BranchRlgl.setfJl((String) f_pfhbhs.get("F_JL"));
			BranchRlgl.setfZlbh(pzlbh);
			BranchRlgl.setfDbsysName(besBAR.getfDbsysName());
			if ("0".equals(BranchRlgl.getfJl())) {
				//根据支路编号查询关联的电表
				Map<String, Object> zlbh = besBranch_Ammeter_RlglMapper.selectZlbh(BranchRlgl);
				if (zlbh != null) {
					flag = besBranch_Ammeter_RlglMapper.beshousehold_delBranch_jl_up(BranchRlgl);

					if (flag)
					{
						// 更新缓存
						branchAmmeteRrlglCache.deleteOneCache(BranchRlgl.getfZlbh(), BranchRlgl.getfDbsysName());

						BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
						besBranchAmmeterLog.setfZlbh(BranchRlgl.getfZlbh());
						besBranchAmmeterLog.setfDbsysName(BranchRlgl.getfDbsysName());
						besBranchAmmeterLog.setfOperator(BranchRlgl.getfOperator());
						besBranchAmmeterLog.setfType("0");

						besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);

						//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
						delect(BranchRlgl.getfDbsysName(),(String) zlbh.get("F_ZLBH"));
					}
				}
				branchconf_deleteAmmeter_jl_up(BranchRlgl);
			}
		}
	}

	/**
	 *
	 * @Description: 支路中移除电表 往下 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 11:44 2020/11/20
	 * @param: [besBAR]
	 * @return: void
	 *
	 */
	private void branchconf_deleteAmmeter_jl_down(BESBranch_Ammeter_Rlgl besBAR) {

		List<Map<String, Object>> f_pzlbh = besBranchConfMapper.selectf_pzlbh_down(besBAR);
		if (f_pzlbh.size() > 0) {
			for (Map<String,Object> f_pzlbhs : f_pzlbh) {
				if (f_pzlbhs.get("F_JL").equals("0")) {
					//查询支路电表关系表中是否存在当前支路关联的电表
					Map<String,Object> besHouseholdBranchRlglzlbh = besBranch_Ammeter_RlglMapper.selectbesBranch_Ammeter_RlglDbbh((String) f_pzlbhs.get("F_ZLBH"),besBAR.getfDbsysName());
					if (besHouseholdBranchRlglzlbh != null) {
						//删除当前支路电表的点位信息
						Boolean delFhAndZl = besBranch_Ammeter_RlglMapper.delZlAndDb((String) f_pzlbhs.get("F_ZLBH"),besBAR.getfDbsysName());

						if (delFhAndZl)
						{
							// 更新缓存
							branchAmmeteRrlglCache.deleteOneCache((String) f_pzlbhs.get("F_ZLBH"), besBAR.getfDbsysName());

							BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
							besBranchAmmeterLog.setfZlbh((String) f_pzlbhs.get("F_ZLBH"));
							besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
							besBranchAmmeterLog.setfOperator(besBAR.getfOperator());
							besBranchAmmeterLog.setfType("0");

							besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);

							//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
							delect(besBAR.getfDbsysName(),(String) f_pzlbhs.get("F_ZLBH"));
						}
					}
				}
				besBAR.setfZlbh((String) f_pzlbhs.get("F_ZLBH"));
				branchconf_deleteAmmeter_jl_down(besBAR);
			}

		}
	}

	/**
	 * 支路添加全部电表
	 */
	@Override
	public ISSPReturnObject branchconf_rightmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
    	log.info("#BESBranchConfServiceImpl支路中添加全部电表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询支路中未包含的电表（关键字查询出的电表）
		List<BESAmmeter> AmmeterList = getNoIncludeAmt(besBAR,keywords);
//		//查询所有电表信息
//		List<BESAmmeter> AmmeterList = besAmmeterMapper.queryAmmeterList(null);
		//查询支路编号对应的所有电表信息
		List<BESBranch_Ammeter_Rlgl> BARList = besBranch_Ammeter_RlglMapper.queryBARList(besBAR.getfZlbh());
		
    	for (int i = 0; i < BARList.size(); i++) {
			for (int j = 0; j < AmmeterList.size(); j++) {
				if(AmmeterList.get(j).getfSysNameOld().equals(BARList.get(i).getfDbsysName())){
					AmmeterList.remove(j);
				}
			}
		}
    	boolean flag = false;
    	for (int i = 0; i < AmmeterList.size(); i++) {
    		besBAR.setfDbsysName(AmmeterList.get(i).getfSysNameOld());
    		besBAR.setfOperator("0");
    		flag = besBranch_Ammeter_RlglMapper.branchconf_insertAmmeter(besBAR);

    		//添加电表操作记录
    		BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
    		besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
			besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
			besBranchAmmeterLog.setfOperator("0");
			besBranchAmmeterLog.setfType("1");
			besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);
    	}
		if (flag) {
			/*if (besBAR.getfJl().equals("0")) {//如果级联选择的是"是",则往父节点添加电表点位*/
			branchconf_rightmoveAll_jl(besBAR, keywords);


			// 更新缓存
			branchAmmeteRrlglCache.updateAll();

			returnObject.setList(AmmeterList);
			returnObject.setMsg("添加所有电表成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加所有电表失败");
			returnObject.setStatus("0");
		}
    	
		return returnObject;
	}

	private void branchconf_rightmoveAll_jl(BESBranch_Ammeter_Rlgl besBAR,String keywords) {

		//查询当前支路编号的信息
		Map<String, Object> f_pzlbh = besBranchConfMapper.selectf_pzlbh(besBAR);
		String pzlbh = (String) f_pzlbh.get("F_PZLBH");



		if (!pzlbh.isEmpty() && pzlbh != null) {

			//父级节点信息
			Map<String,Object> f_pzlbhs = besBranchConfMapper.selectf_pzlbhs(pzlbh);//查询父节点的信息
			besBAR.setfJl((String) f_pzlbhs.get("F_JL"));
			besBAR.setfZlbh(pzlbh);
			if ("0".equals(besBAR.getfJl())) {

				//查询支路中未包含的电表（关键字查询出的电表）
				List<BESAmmeter> AmmeterList = getNoIncludeAmt(besBAR, keywords);
				//查询支路编号对应的所有电表信息
				List<BESBranch_Ammeter_Rlgl> BARList = besBranch_Ammeter_RlglMapper.queryBARList(pzlbh);

				for (int i = 0; i < BARList.size(); i++) {
					for (int j = 0; j < AmmeterList.size(); j++) {
						if (AmmeterList.get(j).getfSysNameOld().equals(BARList.get(i).getfDbsysName())) {
							AmmeterList.remove(j);
						}
					}
				}
				boolean flag = false;
				for (int i = 0; i < AmmeterList.size(); i++) {
					besBAR.setfDbsysName(AmmeterList.get(i).getfSysNameOld());
					besBAR.setfOperator("0");
					flag = besBranch_Ammeter_RlglMapper.branchconf_insertAmmeter(besBAR);

					//添加电表操作记录
					BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
					besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
					besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
					besBranchAmmeterLog.setfOperator("0");
					besBranchAmmeterLog.setfType("1");
					besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);
				}

				branchconf_rightmoveAll_jl(besBAR, keywords);
			}
		}

	}

	/**
	 * 支路移除全部电表
	 */
	@Override
	public ISSPReturnObject branchconf_leftmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
    	log.info("#BESBranchConfServiceImpl支路中移除全部电表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询支路中包含的电表（关键字查询出的电表）
		List<BESAmmeter> AmmeterList = getIncludeAmt(besBAR,keywords);
		boolean flag = false;
		//往上 级联删除
		branchconf_leftmoveAll_up(besBAR,AmmeterList);
		/*//往下 级联删除
		Set<String> vf = new HashSet<>();
		for (BESAmmeter dd : AmmeterList) {
			vf.add(dd.getfSysNameOld());
		}
		branchconf_leftmoveAll_down(besBAR,vf);*/

    	for (int i = 0; i < AmmeterList.size(); i++) {
    		besBAR.setfDbsysName(AmmeterList.get(i).getfSysNameOld());
    		flag = besBranch_Ammeter_RlglMapper.branchconf_leftmoveAll(besBAR);

    		if (flag)
			{
				branchAmmeteRrlglCache.deleteOneCache(besBAR.getfZlbh(), besBAR.getfDbsysName());
				//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
				delect(besBAR.getfDbsysName(),besBAR.getfZlbh());

				//添加电表操作记录
				BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
				besBranchAmmeterLog.setfZlbh(besBAR.getfZlbh());
				besBranchAmmeterLog.setfDbsysName(besBAR.getfDbsysName());
				besBranchAmmeterLog.setfOperator("0");
				besBranchAmmeterLog.setfType("0");
				besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);

			}
    	}
		if (flag) {

			returnObject.setMsg("移除全部成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("无数据移动");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}

	/**
	 *
	 * @Description: 支路移除全部电表 往上 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 15:56 2020/11/20
	 * @param: [besBAR, ammeterList]
	 * @return: void
	 *
	 */
	private void branchconf_leftmoveAll_up(BESBranch_Ammeter_Rlgl besBAR, List<BESAmmeter> ammeterList) {
		//查询当前支路编号的信息
		Map<String, Object> f_pzlbh = besBranchConfMapper.selectf_pzlbh(besBAR);
		String pzlbh = (String) f_pzlbh.get("F_PZLBH");
		//查询父节点的支路信息
		BESBranch_Ammeter_Rlgl fatherBESBranch = new BESBranch_Ammeter_Rlgl();
		fatherBESBranch.setfZlbh(pzlbh);
		Map<String, Object> f_pzlbhMap = besBranchConfMapper.selectf_pzlbh(fatherBESBranch);


		if (!pzlbh.isEmpty() && pzlbh != null) {
			BESBranch_Ammeter_Rlgl BESBranch_Ammeter_Rlgl_this = new BESBranch_Ammeter_Rlgl();
			BESBranch_Ammeter_Rlgl_this.setfZlbh(pzlbh);
			if (f_pzlbhMap.get("F_JL").equals("0")) {

				for (int i = 0; i < ammeterList.size(); i++) {
					BESBranch_Ammeter_Rlgl_this.setfDbsysName(ammeterList.get(i).getfSysNameOld());
					Boolean flag = besBranch_Ammeter_RlglMapper.branchconf_leftmoveAll(BESBranch_Ammeter_Rlgl_this);
					if (flag) {
						branchAmmeteRrlglCache.deleteOneCache(BESBranch_Ammeter_Rlgl_this.getfZlbh(), BESBranch_Ammeter_Rlgl_this.getfDbsysName());
						//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
						delect(BESBranch_Ammeter_Rlgl_this.getfDbsysName(),BESBranch_Ammeter_Rlgl_this.getfZlbh());

						//添加支路电表操作记录
						BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
						besBranchAmmeterLog.setfZlbh(pzlbh);
						besBranchAmmeterLog.setfDbsysName(ammeterList.get(i).getfSysNameOld());
						besBranchAmmeterLog.setfType("0");
						besBranchAmmeterLog.setfOperator(ammeterList.get(i).getfOperator());
						besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);
					}
				}
				branchconf_leftmoveAll_up(BESBranch_Ammeter_Rlgl_this,ammeterList);
			}

		}

	}
	/**
	 *
	 * @Description: 支路移除全部电表 往下 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 15:56 2020/11/20
	 * @param: [besBAR, ammeterList]
	 * @return: void
	 *
	 */
	private void branchconf_leftmoveAll_down(BESBranch_Ammeter_Rlgl besBAR, Set<String> ammeterList) {
		List<Map<String, Object> > f_pzlbh = besBranchConfMapper.selectf_pzlbh_down(besBAR);
		if (f_pzlbh.size() > 0) {
			for (Map<String, Object> f_pzlbhs : f_pzlbh) {
				if (f_pzlbhs.get("F_JL").equals("0")) {
					//查询当前支路所属的全部电表信息
					List<BESBranch_Ammeter_Rlgl> BranchList_pzlbh = besBranch_Ammeter_RlglMapper.queryBARList((String) f_pzlbhs.get("F_ZLBH"));

					for (BESBranch_Ammeter_Rlgl zlAnd_ammeterp : BranchList_pzlbh) {

						if (ammeterList.contains(zlAnd_ammeterp.getfDbsysName())) {

							//删除父节点的支路信息
							BESBranch_Ammeter_Rlgl besHBR_p = new BESBranch_Ammeter_Rlgl();
							besHBR_p.setfZlbh((String) f_pzlbhs.get("F_ZLBH"));
							branchconf_leftmoveAll_down(besHBR_p,ammeterList);
							Boolean delBesHouseholdBranchRlgl = besBranch_Ammeter_RlglMapper.delZlAndDb((String) f_pzlbhs.get("F_ZLBH"),zlAnd_ammeterp.getfDbsysName());
							if (delBesHouseholdBranchRlgl)
							{
								branchAmmeteRrlglCache.deleteOneCache((String) f_pzlbhs.get("F_ZLBH"), zlAnd_ammeterp.getfDbsysName());
								//删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
								delect(zlAnd_ammeterp.getfDbsysName(),(String) f_pzlbhs.get("F_ZLBH"));
							}
						}
					}
				} else {
					//删除父节点的支路信息
					BESBranch_Ammeter_Rlgl besHBR_p = new BESBranch_Ammeter_Rlgl();
					besHBR_p.setfZlbh((String) f_pzlbhs.get("F_ZLBH"));
					branchconf_leftmoveAll_down(besHBR_p,ammeterList);
				}
			}
		}
	}

	@Override
	public ISSPReturnObject update_inclu_fOperator(String fZlbh, String fSysName, String fOperator) {
    	log.info("#BESBranchConfServiceImpl更新支路包含电表的运算符");

		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besBranch_Ammeter_RlglMapper.update_inclu_fOperator( fZlbh,  fSysName,  fOperator);
		if (flag) {

			// 更新缓存
			branchAmmeteRrlglCache.getCachedElement(fZlbh).get(fSysName).setfOperator(fOperator);

			//添加电表操作记录
			BESBranchAmmeterLog besBranchAmmeterLog = new BESBranchAmmeterLog();
			besBranchAmmeterLog.setfZlbh(fZlbh);
			besBranchAmmeterLog.setfDbsysName(fSysName);
			besBranchAmmeterLog.setfOperator(fOperator);
			besBranchAmmeterLog.setfType("2");
			besBranch_Ammeter_RlglMapper.insertBranchAmmeterLog(besBranchAmmeterLog);

			returnObject.setMsg("更新运算符成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新运算符失败");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}

		/**
		 * * 孙志远
	 * 生成单个节点下所有的子节点
	 * @param fZlbh
	 * @return
	 */
	@Override
	public ISSPReturnObject loadOrganization(String fZlbh) {

		List<BESBranchConf> branchlist = besBranchConfMapper.loadOrganization(fZlbh);// 从数据库获取所有资源

		ISSPReturnObject returnObject = new ISSPReturnObject();

		returnObject.setList(branchlist);

		return returnObject;
	}

	/**
	 *
	 * @Description: 删除支路报警配置以及规则表中的数据,以及删除相应的缓存的数据
	 *
	 * @auther: wanghongjie
	 * @date: 18:02 2021/6/5
	 * @param: []
	 * @return: void
	 *
	 */
	public void delect(String fDbsysName,String zlbh) {
		//获取当前支路报警配置所属的支路编号
		List<BesBranchConfAlarmConfiguration> branchConfAlarmConfList = branchConfAlarmConfigurationCache.getCachedElement(fDbsysName);

		if (branchConfAlarmConfList == null || branchConfAlarmConfList.size() == 0) {
			return;
		}

		for (BesBranchConfAlarmConfiguration configuration : branchConfAlarmConfList) {

			if (!configuration.getfZlbh().equals(zlbh)) {
				continue;
			}

			//删除支路配置报警规则缓存的数据
			branchConfParameterCache.deleteOneBranchConfParamCache(configuration.getfAlertid());

			//删除支路报警规则表中的数据
			besbranchConfAlertmapper.del_branchalert_parameter(configuration.getfAlertid());
			//删除支路报警配置表中的数据
			besbranchConfAlertmapper.del_branchalert(configuration.getfAlertid());
		}
		//删除支路配置报警配置缓存的数据
		branchConfAlarmConfigurationCache.deleteOneBranchConfAlarmConfCache(fDbsysName,zlbh);

	}

	/**
	 *
	 * @Description: 批量整理支路树
	 *
	 * @auther: wanghongjie
	 * @date: 9:12 2022/4/23
	 * @param: []
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject tidyTree() throws Exception {
		ISSPReturnObject isspReturnObject = new ISSPReturnObject();
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();

		groupbh = null;
		userId = null;

		List<BESBranchConf> branchlist = besBranchConfMapper.loadAll("01000", groupbh, userId);// 从数据库获取所有资源
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

		String nodeId = "";
		//递归判断
		recursionTidyTree(buildTree,nodeId);

		isspReturnObject.setList(buildTree);

		return isspReturnObject;
	}


	//递归判断
	@Transactional(propagation = Propagation.NESTED)
	public void recursionTidyTree (List<ISSPTreeNode> buildTree,String nodeId) throws IOException,Exception{

		try {
			int i = 1;
			for (ISSPTreeNode isspTreeNode1 : buildTree) {
				String nodeTreeId = isspTreeNode1.getNodeTreeId();
				String nodeId1 = "";
				if (i < 10) {
					nodeId1 = nodeId + "0" + String.valueOf(i);
				} else {
					nodeId1 = nodeId + String.valueOf(i);
				}
				isspTreeNode1.setNodeTreeId(nodeId1);
				isspTreeNode1.setId(nodeId1);
				isspTreeNode1.setPid(nodeId);

				//修改支路树结构表
				Boolean a = besBranchConfMapper.updateBranch_conf(nodeId1, nodeId, nodeTreeId);
				if (a) {
					//修改支路电表表
					Boolean b = besBranchConfMapper.updateBranch_ammeter_rlgl(nodeId1, nodeTreeId);
					if (b) {
						//修改支路数据表
						besBranchConfMapper.updateBranch_data(nodeId1,nodeTreeId);
					}
				}

				i++;
				if (isspTreeNode1.getNodes().size() != 0) {

					recursionTidyTree(isspTreeNode1.getNodes(), nodeId1);
				}
			}

		}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 *
	 * @Description: 获取所有的电表
	 *
	 * @auther: wanghongjie
	 * @date: 9:54 2022/4/25
	 * @param: []
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject loadAllAmmeter() {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		List<BESAmmeter>  ammeterMap = besAmmeterMapper.loadAll();

		returnObject.setList(ammeterMap);
		return returnObject;
	}

	/**
	 *
	 * @Description:根据支路编号查询所有的电表
	 *
	 * @auther: wanghongjie
	 * @date: 10:16 2022/4/25
	 * @param: []
	 * @return: com.core.common.ISSPReturnObject
	 *
	 * @param besBAR
	 */
	@Override
	public ISSPReturnObject loadAmmeterByBranchId(BESBranch_Ammeter_Rlgl besBAR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (besBAR.getfZlbh() != null || besBAR.getfZlbh() != "") {
			List<BESBranch_Ammeter_Rlgl> BARList = besBranch_Ammeter_RlglMapper.queryBARList(besBAR.getfZlbh());
			returnObject.setList(BARList);
		}

		return returnObject;
	}

}
