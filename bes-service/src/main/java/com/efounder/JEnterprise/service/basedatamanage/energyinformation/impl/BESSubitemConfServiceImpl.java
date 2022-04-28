package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.SubitemBranchRlglCache;
import com.efounder.JEnterprise.initializer.SubitemConfigCache;
import com.efounder.JEnterprise.mapper.applicationmanage.ESBmjgMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESSubitemConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESSubitemConfTemplateMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESSubitem_Branch_RlglMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConfTemplate;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESSubitemConfService;
import com.framework.common.utils.ScopeDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 分项配置接口实现类
 * @author LvSihan
 *
 */
@Service("BESSubitemConfService")
public class BESSubitemConfServiceImpl implements BESSubitemConfService,ESBaseService{
	@Autowired
	private BESSubitemConfMapper besSubitemConfMapper;
	@Autowired
	private ESBmjgMapper esBmjgMapper;
	@Autowired
	private BESSubitem_Branch_RlglMapper besSubitem_Branch_RlglMapper;
	@Autowired
	private BESBranchConfMapper besBranchConfMapper;
	@Autowired
	private SubitemConfigCache subitemConfigCache;

	@Autowired
	private BESSubitemConfTemplateMapper besSubitemConfTemplateMapper;

	@Autowired
	private SubitemBranchRlglCache subitemBranchRlglCache;

	/**
	 * 查询分项数据配置数据
	 */
	@Override
	public ISSPReturnObject getSubitemConfList(BESSubitemConf besSubitemConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESSubitemConf> list = besSubitemConfMapper.getSubitemConfList(besSubitemConf);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getfFxbh() != null && !"".equals(list.get(i).getfFxbh())) {
					list.get(i).setfFxbh(list.get(i).getfFxbh());
				} else {
					list.get(i).setfFxbh(null);
				}
			}			
			returnObject.setList(list);
			returnObject.setMsg("获取分项数据配置成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分项数据配置失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 生成分项配置树
	 */
	@Override
	public ISSPReturnObject subitem_tree(String fZzjgbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson(fZzjgbh, 0);// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("获取分项配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取分项配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	
	/**
	 * 根据能源类型生成分项配置树
	 */
	@Override
	public ISSPReturnObject subitem_treegrid(String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson(fNybh,1);// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("根据能源类型获取分项配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("根据能源类型配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	/**
	 * 根据能源类型生成分项配置树
	 */
	@Override
	public ISSPReturnObject subitemConfTree(String fNybh,String fYqbh,String fBudingId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String params = fNybh+"-"+fYqbh + "-" + fBudingId;
		List<ISSPTreeNode> tree = getTreeJson(params,2);// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("根据能源类型和园区编号获取分项配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("根据能源类型和园区编号获取分项配置树成功");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取分项全部数据
	 * @return
	 */
	@Override
	public List<BESSubitemConf> findAll()
	{
		return besSubitemConfMapper.findAll();
	}

	/**
	 * 
	 * @param fZzjgbh
	 * @param flag
	 * @return
	 * update by liuzhen 20181018 增加标志flag，使方法可以共用
	 */
	private List<ISSPTreeNode> getTreeJson(String fZzjgbh, int flag) {
		List<BESSubitemConf> subitemlist = null;
		BESSubitemConf besSubitemConf =new BESSubitemConf();
		//获取用户和组织编号
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		if(flag == 0) 
		{
			subitemlist = besSubitemConfMapper.loadAll(fZzjgbh,groupbh,userId);// 从数据库获取所有资源
		}
		else if(flag == 1)
		{
			besSubitemConf.setfNybh(fZzjgbh);
			subitemlist = besSubitemConfMapper.getSubitemConfGridList(fZzjgbh,groupbh,userId);// 从数据库获取所有资源
		}
		else{
			besSubitemConf.setfNybh(fZzjgbh.split("-")[0]);
			besSubitemConf.setfYqbh(fZzjgbh.split("-")[1]);
			String fNybh = fZzjgbh.split("-")[0];
			String fYqbh = fZzjgbh.split("-")[1];
			String fBudingId = fZzjgbh.split("-")[2];

			groupbh = null;
			userId = null;
			subitemlist = besSubitemConfMapper.getSubitemConfByYqAndNy(fNybh,fYqbh,fBudingId,groupbh,userId);
		}

		// 排序
//		Collections.sort(subitemlist, Comparator.comparingInt(o -> Integer.parseInt(o.getfBudingCode())));
		Collections.sort(subitemlist, Comparator.comparing(o -> String.valueOf(o.getfSubitemCode())));
		 
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if(subitemlist.size() > 0) {
			for (BESSubitemConf subitemconf : subitemlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(subitemconf.getfFxbh());
				node.setId(subitemconf.getfFxbh());
				node.setPid(subitemconf.getfPfxbh());
				node.setText(subitemconf.getfFxmc());
				node.setNodeStatus(subitemconf.getfJs());
				node.setNodeType(subitemconf.getfFxmc());// 暂时存的PID的name
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
	public ISSPReturnObject subitem_chlildtreegrid(String fFxbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESSubitemConf> list = new ArrayList<BESSubitemConf>();
		//获取用户和组织编号
//		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
//		String userId = ScopeDataUtil.getUserIdScope();

		List<String> groupbh = null;
		String userId = null;
		try {
			list = besSubitemConfMapper.subitem_chlildtreegrid(fFxbh,groupbh,userId);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getfPfxbh() == null || "".equals(list.get(i).getfPfxbh())) {
					list.get(i).setfPfxbh(null);
				}
			}
			Collections.sort(list, Comparator.comparing(o -> String.valueOf(o.getfSubitemCode())));
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
	 * 添加分项
	 */
	public ISSPReturnObject add_subitem(BESSubitemConf besSubitemConf) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		besSubitemConf.setfJl("0");//默认级联
		String fFxbh = besSubitemConf.getfFxbh();
		String fNybh = besSubitemConf.getfNybh();
		String fFxmc = besSubitemConf.getfFxmc();

		if (!StringUtils.hasText(fNybh)
				|| !StringUtils.hasText(fFxmc))
		{
			returnObject.setMsg("添加分项失败");
			returnObject.setStatus("0");
			return returnObject;
		}

//		List<BESSubitemConf> subitemInfo =  besSubitemConfMapper.querySubitemByFxbh(fFxbh);
//
//		if (null != subitemInfo && !subitemInfo.isEmpty())
//		{
//			returnObject.setMsg("分项编号不能重复");
//			returnObject.setStatus("0");
//			return returnObject;
//		}

		ESBmjg bmjg = esBmjgMapper.findById("bes_subitem_conf");
		// 级数其实是父节点的级数，所以将级数+1
		int subitemJs = Integer.parseInt(besSubitemConf.getfJs()) + 1;
		besSubitemConf.setfJs(Integer.toString(subitemJs));
		if (subitemJs > bmjg.getBmjg().length()) {
			returnObject.setStatus("1");
			returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
			return returnObject;
		}

//		// 根据级数 从编码结构中获取当前级位数
//		String sbm = bmjg.getBmjg().substring(subitemJs - 1, subitemJs);
//		int ibm = Integer.parseInt(sbm);
//
//		// 获取父节点下所有子节点
//		List<BESSubitemConf> subitemList = besSubitemConfMapper.subitem_chlildtreegrid(besSubitemConf.getfPfxbh());

//		String newBh = null;
//		if (subitemList.size() > 0) {
//			// //获取子节点最大编号
//			int maxBh = getMaxBh(subitemList);
//			// //编号长度
//			int bhLen = besSubitemConf.getfPfxbh().length() + ibm;
//			// //生成组织机构编号 补齐编号
//			newBh = String.format("%0" + bhLen + "d", maxBh + 1);
//			besSubitemConf.setfFxbh(newBh);
//		} else {
//			// //生成支路编号 补齐编号
//			newBh = besSubitemConf.getfPfxbh() + String.format("%0" + ibm + "d", 1);
//			besSubitemConf.setfFxbh(newBh);
//		}
		if (besSubitemConf != null) {
			besSubitemConf.setfGuid(UUIDUtil.getRandom32BeginTimePK());
			besSubitemConf.setfFxbh(UUIDUtil.getRandom32BeginTimePK());
			boolean flag = besSubitemConfMapper.addSubitem(besSubitemConf);
			if (flag) {
				//查找刚添加的数据，在tabulator展示
				List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitem(besSubitemConf.getfGuid());
				// 添加到缓存
				subitemConfigCache.addOneSubitemConfCache(returnList.get(0));
				returnObject.setList(returnList);
				returnObject.setMsg("添加分项成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("添加分项失败");
				returnObject.setStatus("0");
			}
		}
		return returnObject;
	}

	// 获取最大编号
	private int getMaxBh(List<BESSubitemConf> subitemList) {
		int maxugbh = 0;
		for (int i = 0; i < subitemList.size(); i++) {
			String sBh = subitemList.get(i).getfFxbh();
			int iBh = Integer.parseInt(sBh);
			if (maxugbh < iBh) {
				maxugbh = iBh;
			}
		}
		return maxugbh;
	}
	
	/**
	 * 删除分项
	 */
	@Override
	public ISSPReturnObject del_subitem(String fGuid) throws Exception {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {

			List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitem(fGuid);

			if (returnList == null || returnList.isEmpty())
			{
				returnObject.setMsg("删除分项失败");
				returnObject.setStatus("0");
				return returnObject;
			}
			//查询分项支路关系表中,当前分项是否关联支路
			List<Map<String,Object>> bes_subitem_branch_rlgl_data = besSubitemConfMapper.select_bes_subitem_branch_rlgl_data(returnList.get(0).getfFxbh());

			if (bes_subitem_branch_rlgl_data.size() > 0) {
				returnObject.setMsg("删除分项失败,当前分项已配置支路");
				returnObject.setStatus("0");
				return returnObject;
			}

			boolean flag = besSubitemConfMapper.del_subitem(fGuid);

			if (flag) {

				// 删除缓存数据
				subitemConfigCache.deleteOneSubitemConfCache(returnList.get(0).getfFxbh());

				returnObject.setMsg("删除分项成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("删除分项失败");
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			returnObject.setMsg("删除分项失败,当前分项已有数据");
			returnObject.setStatus("0");
		}

		return returnObject;
	}
	/**
	 * 查询需要编辑的分项配置信息
	 */
	@Override
	public ISSPReturnObject querySubitem(String fGuid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		try {
			List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitem(fGuid);
			returnObject.setList(returnList);
			returnObject.setMsg("查询编辑分项信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询编辑分项信息失败");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}

	/**
	 * 编辑分项配置信息
	 */
	@Override
	public ISSPReturnObject edit_subitem(BESSubitemConf besSubitemConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		boolean flag = besSubitemConfMapper.edit_subitem(besSubitemConf);

		if (flag) {
			List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitem(besSubitemConf.getfGuid());

			// 更新缓存数据
			subitemConfigCache.updateOneSubitemConfCache(returnList.get(0));
			returnObject.setList(returnList);
			returnObject.setMsg("更新分项配置成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("更新分项配置失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 查询当前分项是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:46 2020/11/21
	 * @param: [besSubitemConf]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject loadShowCascade_subitem(BESSubitemConf besSubitemConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fFxbh = besSubitemConf.getfFxbh();
		String f_jl = besSubitemConfMapper.selectf_jl(fFxbh);
		if (f_jl == null) {
			returnObject.setMsg("分项是否级联查询失败");
			returnObject.setStatus("0");
		} else {
			returnObject.setMsg("分项是否级联查询成功");
			returnObject.setData(f_jl);
			returnObject.setStatus("1");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 修改是否级联-分项
	 *
	 * @auther: wanghongjie
	 * @date: 9:54 2020/11/21
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject changef_jl_subitem(BESSubitemConf besSubitemConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Boolean changef_jl = besSubitemConfMapper.changef_jl(besSubitemConf.getfJl(),besSubitemConf.getfFxbh());
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
	 * 查询分项未包含支路信息
	 */
	@Override
	public ISSPReturnObject loadNoIncludeBrc(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		//通过fFxbh查询'分项与支路关系表'中支路SBRList
		List<BESSubitem_Branch_Rlgl> SBRList = besSubitem_Branch_RlglMapper.querySBRList(besSBR.getfFxbh());
		//通过分项编号获取该条数据
		List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitemByFxbh(besSBR.getfFxbh());
		//String fZzjgbh = returnList.get(0).getfZzjgbh();
		String fNybh = returnList.get(0).getfNybh();
		String fYqbh = returnList.get(0).getfYqbh();
		//besBranchConf.setfZzjgbh(fZzjgbh);
		besBranchConf.setfNybh(fNybh);
		besBranchConf.setfYqbh(fYqbh);
		//查询与分项配置相同fZzjgbh和fNybh的支路信息
		List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);
		
		try {
			for (int i = 0; i < SBRList.size(); i++) {
				for (int j = 0; j < BranchList.size(); j++) {
					if(BranchList.get(j).getfZlbh().equals(SBRList.get(i).getfZlbh())){
						BranchList.remove(j);
					}					
				}
			}
			returnObject.setList(BranchList);
			returnObject.setMsg("查询分项未包含支路信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询分项未包含支路信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 查询分项包含的支路信息
	 */
	@Override
	public ISSPReturnObject loadIncludeBrc(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		List<BESBranchConf> BranchList = new ArrayList<>();
		List<BESBranchConf> newBranchList = new ArrayList<>();

		try {
		//通过fFxbh查询'分项与支路关系表'中支路SBRList
		List<BESSubitem_Branch_Rlgl> SBRList = besSubitem_Branch_RlglMapper.querySBRList(besSBR.getfFxbh());
		for (int i = 0; i < SBRList.size(); i++) {
			BESBranchConf branch = besBranchConfMapper.getBranchConf(SBRList.get(i).getfZlbh());
			BranchList.add(i, branch);
		}
		if(besBranchConf.getKeywords() != null){
			//通过分项编号获取该条数据
			List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitemByFxbh(besSBR.getfFxbh());
			//String fZzjgbh = returnList.get(0).getfZzjgbh();
			String fNybh = returnList.get(0).getfNybh();
			String fYqbh = returnList.get(0).getfYqbh();
			//besBranchConf.setfZzjgbh(fZzjgbh);
			besBranchConf.setfNybh(fNybh);
			besBranchConf.setfYqbh(fYqbh);
			//查询与分项配置相同fZzjgbh和fNybh的支路信息
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
		returnObject.setMsg("查询分项包含的支路信息成功");
		returnObject.setStatus("1");
	} catch (Exception e) {
		returnObject.setMsg("查询分项包含的支路信息失败");
		returnObject.setStatus("0");
	}
		return returnObject;
	}


	/**
	 * 分项中添加支路
	 */
	@Override
	public ISSPReturnObject subitemconf_insertBranch(BESSubitem_Branch_Rlgl besSBR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		boolean flag = besSubitem_Branch_RlglMapper.subitemconf_insertBranch(besSBR);
		if (flag)
		{
			// 更新缓存
			BESSubitem_Branch_Rlgl besSubitemBranchRlgl = new BESSubitem_Branch_Rlgl();
			besSubitemBranchRlgl.setfFxbh(besSBR.getfFxbh());
			besSubitemBranchRlgl.setfJl(besSBR.getfJl());
			besSubitemBranchRlgl.setfZlbh(besSBR.getfZlbh());
			besSubitemBranchRlgl.setKeywords(besSBR.getKeywords());
			besSubitemBranchRlgl.setfChdate(besSBR.getfChdate());
			besSubitemBranchRlgl.setfCrdate(besSBR.getfCrdate());
			subitemBranchRlglCache.putOneCache(besSubitemBranchRlgl);
		}
		String fJl = besSBR.getfJl();
		if (fJl.equals("0")) {
			subitemconf_instF_jl(besSBR);
		}
		if (flag) {
			BESBranchConf branch = besBranchConfMapper.getBranchConf(besSBR.getfZlbh());
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
	 * @Description: 往上 级联添加支路点位
	 *
	 * @auther: wanghongjie
	 * @date: 10:16 2020/11/21
	 * @param: [besSBR]
	 * @return: void
	 *
	 */
	private void subitemconf_instF_jl(BESSubitem_Branch_Rlgl besSBR) {
		//查询当前分项编号的信息
		Map<String,Object> f_pfxbh = besSubitemConfMapper.selectf_pfxbh(besSBR);
		boolean flag = false;
		String pfxbh = (String) f_pfxbh.get("F_PFXBH");

		if (!pfxbh.isEmpty() && pfxbh != null && !pfxbh.equals("01") && !pfxbh.equals("00")) {

			Map<String,Object> f_pfhbhs = besSubitemConfMapper.selectf_pfxbhs(pfxbh);//查询父节点的信息
			besSBR.setfJl((String) f_pfhbhs.get("F_JL"));
			besSBR.setfFxbh(pfxbh);
			if (besSBR.getfJl().equals("0")) {
				//根据分项编号查询关联的支路
				Map<String,Object> zlbh = besSubitem_Branch_RlglMapper.selectZlbh(besSBR);
				if (zlbh == null) {
					flag = besSubitem_Branch_RlglMapper.subitemconf_insertBranch(besSBR);

					if (flag)
					{
						// 更新缓存
						BESSubitem_Branch_Rlgl besSubitemBranchRlgl = new BESSubitem_Branch_Rlgl();
						besSubitemBranchRlgl.setfFxbh(besSBR.getfFxbh());
						besSubitemBranchRlgl.setfJl(besSBR.getfJl());
						besSubitemBranchRlgl.setfZlbh(besSBR.getfZlbh());
						besSubitemBranchRlgl.setKeywords(besSBR.getKeywords());
						besSubitemBranchRlgl.setfChdate(besSBR.getfChdate());
						besSubitemBranchRlgl.setfCrdate(besSBR.getfCrdate());
						subitemBranchRlglCache.putOneCache(besSubitemBranchRlgl);
					}
				}
			}
			subitemconf_instF_jl(besSBR);
		}
	}

	/**
	 * 分项中移除支路
	 */
	@Override
	public ISSPReturnObject subitemconf_deleteBranch(BESSubitem_Branch_Rlgl besSBR) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		boolean flag = besSubitem_Branch_RlglMapper.subitemconf_deleteBranch(besSBR);
		if (flag) {

			// 更新缓存
			subitemBranchRlglCache.deleteOneCache(besSBR.getfFxbh(), besSBR.getfZlbh());

			if (besSBR.getfJl().equals("0")) {
				//往上 级联删除
				subitemconf_delBranch_jl_up(besSBR);
				//往下 级联删除
				subitemconf_delBranch_jl_down(besSBR);
			}
			BESBranchConf branch = besBranchConfMapper.getBranchConf(besSBR.getfZlbh());

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
	 * @Description: 分项中移除支路  往上 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 11:07 2020/11/21
	 * @param: [besSBR]
	 * @return: void
	 *
	 */
	private void subitemconf_delBranch_jl_up(BESSubitem_Branch_Rlgl besSBR) {
		//查询当前分项编号的信息
		Map<String, Object> f_pfxbh = besSubitemConfMapper.selectf_pfxbh(besSBR);
		boolean flag = false;
		String pfxbh = (String) f_pfxbh.get("F_PFXBH");

		if (!pfxbh.isEmpty() && pfxbh != null && !pfxbh.equals("01") && !pfxbh.equals("00")) {

			Map<String, Object> f_pfxbhs = besSubitemConfMapper.selectf_pfxbhs(pfxbh);//查询父节点的信息
			BESSubitem_Branch_Rlgl branch_rlgl = new BESSubitem_Branch_Rlgl();
			branch_rlgl.setfJl((String) f_pfxbhs.get("F_JL"));
			branch_rlgl.setfFxbh(pfxbh);
			branch_rlgl.setfZlbh(besSBR.getfZlbh());
			if (branch_rlgl.getfJl().equals("0")) {
				//根据分项编号查询关联的支路
				Map<String, Object> zlbh = besSubitem_Branch_RlglMapper.selectZlbh(branch_rlgl);
				if (zlbh != null) {
					flag = besSubitem_Branch_RlglMapper.besSubitem_delBranch_jl_up(branch_rlgl);

					if (flag)
					{
						// 更新缓存
						subitemBranchRlglCache.deleteOneCache(branch_rlgl.getfFxbh(), branch_rlgl.getfZlbh());
					}
				}
			}
			subitemconf_delBranch_jl_up(branch_rlgl);
		}
	}

	/**
	 *
	 * @Description: 分项中移除支路  往下 级联删除
	 *
	 * @auther: wanghongjie
	 * @date: 11:07 2020/11/21
	 * @param: [besSBR]
	 * @return: void
	 *
	 */
	private void subitemconf_delBranch_jl_down(BESSubitem_Branch_Rlgl besSBR) {
		//查找子节点的信息
		List<Map<String, Object>> f_pfxbh = besSubitemConfMapper.selectf_pfxbh_down(besSBR);
		if (f_pfxbh.size() > 0) {
			for (Map<String,Object> f_pfxbhs : f_pfxbh) {
				if (f_pfxbhs.get("F_JL").equals("0")) {
					//查询分项支路关系表中是否存在当前分项关联的支路
					Map<String,Object> besHouseholdBranchRlglzlbh = besSubitem_Branch_RlglMapper.selectsubitemconfBranchRlglZlbh((String) f_pfxbhs.get("F_FXBH"),besSBR.getfZlbh());
					if (besHouseholdBranchRlglzlbh != null) {
						//删除当前分项支路的点位信息
						Boolean delFhAndZl = besSubitem_Branch_RlglMapper.delFxAndZl((String) f_pfxbhs.get("F_FXBH"),besSBR.getfZlbh());
						if (delFhAndZl)
						{
							// 更新缓存
							subitemBranchRlglCache.deleteOneCache((String) f_pfxbhs.get("F_FXBH"), besSBR.getfZlbh());
						}

					}
				}
				besSBR.setfFxbh((String) f_pfxbhs.get("F_FXBH"));
				subitemconf_delBranch_jl_down(besSBR);
			}

		}
	}

	/**
	 * 分项中添加全部支路
	 */
	@Override
	public ISSPReturnObject subitemconf_rightmoveAll(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
    	//log.info("#BESBranchConfServiceImpl支路中添加全部电表");

		ISSPReturnObject returnObject = new ISSPReturnObject();	
		
		//通过分项编号获取该条数据
		List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitemByFxbh(besSBR.getfFxbh());
		//String fZzjgbh = returnList.get(0).getfZzjgbh();
		String fNybh = returnList.get(0).getfNybh();
		//besBranchConf.setfZzjgbh(fZzjgbh);
		besBranchConf.setfNybh(fNybh);
		//查询与分项配置相同fZzjgbh和fNybh的支路信息

		List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);
		//查询分项编号对用的所有支路
		List<BESSubitem_Branch_Rlgl> SBRList = besSubitem_Branch_RlglMapper.querySBRList(besSBR.getfFxbh());
		
    	for (int i = 0; i < SBRList.size(); i++) {
			for (int j = 0; j < BranchList.size(); j++) {
				if(BranchList.get(j).getfZlbh().equals(SBRList.get(i).getfZlbh())){
					BranchList.remove(j);
				}
			}
		}
    	boolean flag = false;
    	for (int i = 0; i < BranchList.size(); i++) {
    		besSBR.setfZlbh(BranchList.get(i).getfZlbh());
    		 flag = besSubitem_Branch_RlglMapper.subitemconf_insertBranch(besSBR);

    		 if (flag)
			 {
				 // 更新缓存
				 BESSubitem_Branch_Rlgl besSubitemBranchRlgl = new BESSubitem_Branch_Rlgl();
				 besSubitemBranchRlgl.setfFxbh(besSBR.getfFxbh());
				 besSubitemBranchRlgl.setfJl(besSBR.getfJl());
				 besSubitemBranchRlgl.setfZlbh(besSBR.getfZlbh());
				 besSubitemBranchRlgl.setKeywords(besSBR.getKeywords());
				 besSubitemBranchRlgl.setfChdate(besSBR.getfChdate());
				 besSubitemBranchRlgl.setfCrdate(besSBR.getfCrdate());
				 subitemBranchRlglCache.putOneCache(besSubitemBranchRlgl);
			 }
    	}
		if (flag) {
			if (besSBR.getfJl().equals("0")) {//如果级联选择的是"是",则往父节点添加支路点位
				subitemconf_rightmoveAll_jl(besSBR,besBranchConf);
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
	 * @date: 13:53 2020/11/21
	 * @param: [besSBR, besBranchConf]
	 * @return: void
	 *
	 */
	private void subitemconf_rightmoveAll_jl(BESSubitem_Branch_Rlgl besSBR, BESBranchConf besBranchConf) {
		//查询当前分项编号的信息
		Map<String,Object> f_pfxbh = besSubitemConfMapper.selectf_pfxbh(besSBR);
		String pfxbh = (String) f_pfxbh.get("F_PFXBH");//获取父节点的编号
		if (!pfxbh.isEmpty() && pfxbh != null && !pfxbh.equals("01") && !pfxbh.equals("00")) {

			//通过分项编号获取该条数据
			List<BESSubitemConf> returnList = besSubitemConfMapper.querySubitemByFxbh(besSBR.getfFxbh());
			//String fZzjgbh = returnList.get(0).getfZzjgbh();
			String fNybh = returnList.get(0).getfNybh();
			//besBranchConf.setfZzjgbh(fZzjgbh);
			besBranchConf.setfNybh(fNybh);
			//查询与分项配置相同fZzjgbh和fNybh的支路信息

			List<BESBranchConf> BranchList = besBranchConfMapper.getBranchConfList(besBranchConf);
			//查询分项编号对用的所有支路
			List<BESSubitem_Branch_Rlgl> SBRList = besSubitem_Branch_RlglMapper.querySBRList(pfxbh);

			for (int i = 0; i < SBRList.size(); i++) {
				for (int j = 0; j < BranchList.size(); j++) {
					if(BranchList.get(j).getfZlbh().equals(SBRList.get(i).getfZlbh())){
						BranchList.remove(j);
					}
				}
			}
			boolean flag = false;



			Map<String,Object> f_pfxbhs = besSubitemConfMapper.selectf_pfxbhs(pfxbh);//查询父节点的信息
			besSBR.setfJl((String) f_pfxbhs.get("F_JL"));
			besSBR.setfFxbh(pfxbh);
			if (besSBR.getfJl().equals("0")) {
				for (int i = 0; i < BranchList.size(); i++) {
					besSBR.setfZlbh(BranchList.get(i).getfZlbh());
					flag = besSubitem_Branch_RlglMapper.subitemconf_insertBranch(besSBR);

					if (flag)
					{
						// 更新缓存
						BESSubitem_Branch_Rlgl besSubitemBranchRlgl = new BESSubitem_Branch_Rlgl();
						besSubitemBranchRlgl.setfFxbh(besSBR.getfFxbh());
						besSubitemBranchRlgl.setfJl(besSBR.getfJl());
						besSubitemBranchRlgl.setfZlbh(besSBR.getfZlbh());
						besSubitemBranchRlgl.setKeywords(besSBR.getKeywords());
						besSubitemBranchRlgl.setfChdate(besSBR.getfChdate());
						besSubitemBranchRlgl.setfCrdate(besSBR.getfCrdate());
						subitemBranchRlglCache.putOneCache(besSubitemBranchRlgl);
					}
				}
			}
			BESSubitem_Branch_Rlgl besHBRs = new BESSubitem_Branch_Rlgl();
			BESBranchConf besBranchConfs = new BESBranchConf();
			besHBRs.setfFxbh(pfxbh);
			besBranchConfs.setKeywords(besBranchConf.getKeywords());
			subitemconf_rightmoveAll_jl(besHBRs,besBranchConfs);
		}
	}

	/**
	 * 支路移除全部电表
	 */
	@Override
	public ISSPReturnObject subitemconf_leftmoveAll (BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
    	//log.info("#BESBranchConfServiceImpl支路中移除全部电表");

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (besSBR.getfJl().equals("0")) {
			//查询当前分项所属的全部支路信息
			List<BESSubitem_Branch_Rlgl> BranchList = besSubitem_Branch_RlglMapper.selectBranchList(besSBR.getfFxbh(),besBranchConf.getKeywords());
			//往上 级联删除
			bessubitem_delBranch_jl_up_all(besSBR,BranchList,besBranchConf.getKeywords());
			//往下 级联删除

			Set<String> vf = new HashSet<>();
			for (BESSubitem_Branch_Rlgl dd : BranchList) {
				vf.add(dd.getfZlbh());
			}
			bessubitem_delBranch_jl_down_all(besSBR,vf,besBranchConf.getKeywords());
		}

		boolean flag = besSubitem_Branch_RlglMapper.subitemconf_leftmoveAll(besSBR);
		if (flag) {
			// 更新缓存
			subitemBranchRlglCache.deleteCacheByfFxbh(besSBR.getfFxbh());

			returnObject.setMsg("移除全部成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("无数据移动");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}


	private void bessubitem_delBranch_jl_up_all(BESSubitem_Branch_Rlgl besSBR, List<BESSubitem_Branch_Rlgl> branchList, String keywords) {
		//查询当前分项的信息
		List<BESSubitemConf> besSubitemConfs = besSubitemConfMapper.querySubitemByFxbh(besSBR.getfFxbh());
		//获取父节点的名称
		String f_pfxbh = besSubitemConfs.get(0).getfPfxbh();
		if (f_pfxbh != null && !f_pfxbh.isEmpty() && !f_pfxbh.equals("01") && !f_pfxbh.equals("00")) {
			//查询当前父分项的信息
			List<BESSubitemConf> besSubitemConfList = besSubitemConfMapper.querySubitemhold(f_pfxbh);
			if (besSubitemConfList.get(0).getfJl().equals("0")) {
				//查询当前分项所属的全部支路信息
				List<BESSubitem_Branch_Rlgl> BranchList_pfxbh = besSubitem_Branch_RlglMapper.selectBranchList(f_pfxbh,keywords);
				for (BESSubitem_Branch_Rlgl fxAndZl : branchList) {
					for (BESSubitem_Branch_Rlgl fhAndZl_p : BranchList_pfxbh) {
						if (fxAndZl.getfZlbh().equals(fhAndZl_p.getfZlbh())) {
							//删除父节点的支路信息
							Boolean delBesHouseholdBranchRlgl = besSubitem_Branch_RlglMapper.delFxAndZl(f_pfxbh,fxAndZl.getfZlbh());

							// 更新缓存
							if (delBesHouseholdBranchRlgl)
							{
								subitemBranchRlglCache.deleteOneCache(f_pfxbh, fxAndZl.getfZlbh());
							}
						}
					}
				}
			}
			BESSubitem_Branch_Rlgl branch_rlgl = new BESSubitem_Branch_Rlgl();
			branch_rlgl.setfFxbh(f_pfxbh);
			bessubitem_delBranch_jl_up_all(branch_rlgl,branchList,keywords);
		}
	}

	private void bessubitem_delBranch_jl_down_all(BESSubitem_Branch_Rlgl besSBR, Set<String> vf, String keywords) {
	//分项中移除支路 往下 级联删除,查询父分项编号为当前分项编号的所有信息
		List<Map<String, Object> > f_pfxbh = besSubitemConfMapper.selectf_pfXbh_down(besSBR);
		if (f_pfxbh.size() > 0) {
			for (Map<String, Object> f_pfxbhs : f_pfxbh) {

				if (f_pfxbhs.get("F_JL").equals("0")) {
					//查询当前分户所属的全部支路信息
					List<BESSubitem_Branch_Rlgl> BranchList_pfxbh = besSubitem_Branch_RlglMapper.selectBranchList((String) f_pfxbhs.get("F_FXBH"),keywords);

					for (BESSubitem_Branch_Rlgl fxAndZl_p : BranchList_pfxbh) {

						if (vf.contains(fxAndZl_p.getfZlbh())) {

							//删除父节点的支路信息
							BESSubitem_Branch_Rlgl besHBR_p = new BESSubitem_Branch_Rlgl();
							besHBR_p.setfFxbh((String) f_pfxbhs.get("F_FXBH"));
							bessubitem_delBranch_jl_down_all(besHBR_p,vf,keywords);
							Boolean delBesHouseholdBranchRlgl = besSubitem_Branch_RlglMapper.delFxAndZl((String) f_pfxbhs.get("F_FXBH"),fxAndZl_p.getfZlbh());

							// 更新缓存
							if (delBesHouseholdBranchRlgl)
							{
								subitemBranchRlglCache.deleteOneCache((String) f_pfxbhs.get("F_FXBH"), fxAndZl_p.getfZlbh());
							}
						}
					}
				} else {
					//删除父节点的支路信息
					BESSubitem_Branch_Rlgl besHBR_p = new BESSubitem_Branch_Rlgl();
					besHBR_p.setfFxbh((String) f_pfxbhs.get("F_FXBH"));
					bessubitem_delBranch_jl_down_all(besHBR_p,vf,keywords);
				}
			}
		}
	}

	/**
	 *
	 * @Description: 一键新增分项配置
	 *
	 * @auther: wanghongjie
	 * @date: 17:36 2021/5/25
	 * @param: [list]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject addsubitem_OneClickAdd(String fNybh,String fYqbh,String buildingbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		BESSubitemConf besSubitemConf = new BESSubitemConf();
		boolean flag = false;
		try {
		//获取分项配置模板的数据
		List<BESSubitemConfTemplate>  besSubitemConfTemplates = besSubitemConfTemplateMapper.besSubitemConfTemplates(fNybh,fYqbh,buildingbh);


//		//首先删除分项配置表中当前建筑下,当前园区,能源类型所有的数据
//		//判断分项配置表中当前建筑下,当前园区,能源类型是否有数据
		List<BESSubitemConf> besSubitemConfList = besSubitemConfMapper.selectSubitemTableData(fNybh,fYqbh,buildingbh);
		if (besSubitemConfList.size() > 0) {
			for (BESSubitemConf besSubitemConf1 : besSubitemConfList) {
				//判断分项是否关联支路
				//查询分项支路关系表中,当前分项是否关联支路
				List<Map<String,Object>> bes_subitem_branch_rlgl_data = besSubitemConfMapper.select_bes_subitem_branch_rlgl_data(besSubitemConf1.getfFxbh());
				if (bes_subitem_branch_rlgl_data.size() > 0) {
					returnObject.setMsg("删除分项失败,当前分项已配置支路");
					returnObject.setStatus("0");
					return returnObject;
				}
			}

			Boolean delectSubitemData = besSubitemConfMapper.delectSubitemData(fNybh,fYqbh,buildingbh);

			for (BESSubitemConf besSubitemConf2 : besSubitemConfList) {
				// 删除缓存数据
				subitemConfigCache.deleteOneSubitemConfCache(besSubitemConf2.getfFxbh());
			}

		}

		for (int i = 0; i < besSubitemConfTemplates.size(); i++) {
			//获取父节点的分项编号
			String fPfxbh = besSubitemConfTemplates.get(i).getfPfxbh();
			if (fPfxbh.equals("")) {
				//直接插入数据库
				besSubitemConf.setfGuid(UUIDUtil.getRandom32BeginTimePK());
				besSubitemConf.setfFxbh(UUIDUtil.getRandom32BeginTimePK());
				besSubitemConf.setfFxmc(besSubitemConfTemplates.get(i).getfFxmc());
				besSubitemConf.setfPfxbh("");
				besSubitemConf.setfJs(besSubitemConfTemplates.get(i).getfJs());
				besSubitemConf.setfJl("0");
				besSubitemConf.setfNybh(fNybh);
				besSubitemConf.setfYqbh(fYqbh);
				besSubitemConf.setfSubitemCode(besSubitemConfTemplates.get(i).getfSubitemCode());
				besSubitemConf.setfBudingId(buildingbh);

				//插入数据
				flag = besSubitemConfMapper.addSubitem(besSubitemConf);
				//添加到缓存
				subitemConfigCache.addOneSubitemConfCache(besSubitemConf);

			} else {
				for (BESSubitemConfTemplate besSubitemConfTemplate : besSubitemConfTemplates) {
					if (besSubitemConfTemplate.getfFxbh().equals(fPfxbh)) {
						//获取父节点的建筑代码
						String fSubitemCode = besSubitemConfTemplate.getfSubitemCode();
						//查询父节点的分项编号
						String fxbh = besSubitemConfTemplateMapper.selectFxbh(fNybh,fYqbh,buildingbh,fSubitemCode);
						//直接插入数据库
						besSubitemConf.setfGuid(UUIDUtil.getRandom32BeginTimePK());
						besSubitemConf.setfFxbh(UUIDUtil.getRandom32BeginTimePK());
						besSubitemConf.setfFxmc(besSubitemConfTemplates.get(i).getfFxmc());
						besSubitemConf.setfPfxbh(fxbh);
						besSubitemConf.setfJs(besSubitemConfTemplates.get(i).getfJs());
						besSubitemConf.setfJl("0");
						besSubitemConf.setfNybh(fNybh);
						besSubitemConf.setfYqbh(fYqbh);
						besSubitemConf.setfSubitemCode(besSubitemConfTemplates.get(i).getfSubitemCode());
						besSubitemConf.setfBudingId(buildingbh);

						//插入数据
						flag = besSubitemConfMapper.addSubitem(besSubitemConf);
						//添加到缓存
						subitemConfigCache.addOneSubitemConfCache(besSubitemConf);
					}
				}
			}
		}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		if (flag) {
			returnObject.setMsg("批量添加分项成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("批量添加分项失败");
			returnObject.setStatus("0");
		}

		return returnObject;
	}
}
