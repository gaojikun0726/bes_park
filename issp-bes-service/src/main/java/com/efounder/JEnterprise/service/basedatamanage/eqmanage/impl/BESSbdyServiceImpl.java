package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.constant.BesNodeType;
import com.core.common.metatype.Dto;
import com.core.common.ppcl.FunctionComplier;
import com.core.common.ppcl.FunctionLexer;
import com.core.common.ppcl.FunctionParser;
import com.core.common.ppcl.Instruction;
import com.core.common.security.ISSPSecurityObject;
import com.core.common.security.LimitParamObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.common.util.DataUtil;
import com.efounder.JEnterprise.collectorJob.BESPollingThread;
import com.efounder.JEnterprise.collectorJob.BESSbtreeThread;
import com.efounder.JEnterprise.collectorJob.LEMSConstants;
import com.efounder.JEnterprise.commhandler.MsgSubPubHandler;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.BESEquipmentMoldMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.model.BESEquipmentMold;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.ace.business.constant.*;
import org.ace.business.dto.ddc.ControllerDataDDC;
import org.ace.business.dto.ddc.ModuleParamDDC;
import org.ace.business.dto.ddc.PointDataDDC;
import org.ace.business.dto.ddc.PointParamDDC;
import org.ace.business.dto.edc.AmmeterCollectParamData;
import org.ace.business.dto.edc.AmmeterParam;
import org.ace.business.dto.edc.ControllerDataEDC;
import org.ace.business.dto.ldc.ControllerDataLDC;
import org.ace.business.dto.ldc.ModuleParamLDC;
import org.ace.business.dto.ldc.PointDataLDC;
import org.ace.business.dto.ldc.PointParamLDC;
import org.ace.business.handler.SendMsgHandler;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("BESSbdyService")
public class BESSbdyServiceImpl implements BESSbdyService,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESSbdyServiceImpl.class);
	private static TFTPClient tftp = new TFTPClient();
	@Value("${system.parameter.bessbtreethread}")
	private String bessbtreethread;

	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private BESEquipmentMoldMapper besEquipmentMoldMapper;
	@Autowired
	private BESJobManagerMapper besJobManagerMapper;
	@Autowired
	private BesDdcMapper besDdcMapper;
	@Autowired
	private BesCollectorMapper besCollectorMapper;
	@Autowired
	private CrossEquipmentServiceImpl crossEquipmentServiceImpl;
	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;
	@Autowired
	private LampEquipmentServiceImpl lampEquipmentServiceImpl;
	@Autowired
	private EnerEquipmentServiceImpl enerEquipmentServiceImpl;

	@Autowired
	private BESAmmeterMapper besAmmeterMapper;

	@Autowired
	private SbPzStructCache sbPzStructCache;

	@Autowired
	private SbTreeNodeTypeCache sbTreeNodeTypeCache;

	@Autowired
	private DdcCache ddcCache;

	@Autowired
	private AmmeterCache ammeterCache;

	@Autowired
	private CollectorCache collectorCache;

	@Autowired
	private BesVirtualPointMapper besVirtualPointMapper;

	@Autowired
	private VirtualPointCache virtualPointCache;

	@Resource
	private BesAiPointMapper besAiPointMapper;

	@Autowired
	private AiPointCache aiPointCache;

	@Resource
	private BesAoPointMapper besAoPointMapper;

	@Autowired
	private AoPointCache aoPointCache;

	@Autowired
	private DiPointCache diPointCache;

	@Resource
	private BesDiPointMapper besDiPointMapper;

	@Resource
	private BesDoPointMapper besDoPointMapper;

	@Autowired
	private DoPointCache doPointCache;

	/**
	 *点击DDC下面的模块的不同AO、AI、DO、DI点，进入不同页面时，查看不同表数据验证是否已同步，同步后则相应的表中有数据
	 */
	@Override
	public int getModuleNodeInfoWhenEntryPage(String f_sys_name, String tableName) {
		int returnCount = besSbdyMapper.getModuleNodeInfoWhenEntryPage(f_sys_name, tableName);
		return returnCount;
	}


	@Override
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
		String qxbh = "0008";
		String pscol = "";
		String yhbh = username;
		String pibzw = "1";
		LimitParamObject paramObject = new LimitParamObject();
		paramObject.setQxbh(qxbh);
		paramObject.setPscol(pscol);
		paramObject.setYhbh(yhbh);
		paramObject.setPibzw(pibzw);
		// String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

		try {
			// List<BESSbPzStruct> sbdylist = besSbdyMapper.loadAll(checkDataLimit);//加载设备树信息
			// 直接从缓存中获取
			Cache<String, BESSbPzStruct> sbdyCache = sbPzStructCache.getSbPzStructCache();//加载设备树信息
			if (sbdyCache == null)
			{
				returnObject.setStatus("0");
				return returnObject;
			}

			List<BESSbPzStruct> sbdyList = new ArrayList<>(sbdyCache.values());

			if (sbdyList.size() == 0) {
				returnObject.setStatus("0");
				return returnObject;
			}

			// 排序
			Collections.sort(sbdyList, Comparator.comparingInt(o -> Integer.parseInt(o.getF_id())));

			// List<BESSbTreeNodeType> nodeTypeInfoList = besSbdyMapper.findChildByTreeNoteType(null);//从数据库获取所有节点对应图片信息

			// 改为从缓存中获取
			Cache<String, BESSbTreeNodeType> nodeTypeCache = sbTreeNodeTypeCache.getSbTreeNodeTypeCache();

			if (nodeTypeCache == null)
			{
				returnObject.setStatus("0");
				return returnObject;
			}

			Collection<BESSbTreeNodeType> nodeTypeCacheList = nodeTypeCache.values();

			if (nodeTypeCacheList.size() == 0) {
				returnObject.setStatus("0");
				return returnObject;
			}


			Map<String, String> imgMap=new HashMap<>();

			for(BESSbTreeNodeType besSbTreeNodeType:nodeTypeCacheList){
				imgMap.put("on_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_online_img());
				imgMap.put("off_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_offline_img());
			}

			List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();//把所有资源转换成树模型的节点集合，此容器用于保存所有节点
			Map<String, String> idMap=new HashMap<>();

			for (BESSbPzStruct tree : sbdyList) {//获得一棵树模型的数据
				idMap.put(tree.getF_sys_name(), tree.getF_sys_name());
				ISSPTreeNode isspPTreeNode=new ISSPTreeNode();
				isspPTreeNode.setNodeTreeId(tree.getCId());
				isspPTreeNode.setPid(tree.getPId());
				//isspPTreeNode.setId(tree.getF_id());
				//isspPTreeNode.setId(tree.getF_id());
				//isspPTreeNode.setLevel(tree.getF_id());
				isspPTreeNode.setRootId(tree.getF_id());
				isspPTreeNode.setText(tree.getCMc());
				isspPTreeNode.setNodeType(tree.getF_type());
				isspPTreeNode.setNodeStatus(tree.getF_status());
				isspPTreeNode.setNodeAttribution(tree.getF_node_attribution());
				if (tree.getF_sys_name_old()!=null &&tree.getF_sys_name_old()!=""){
					isspPTreeNode.setIcon(tree.getF_sys_name_old());
				}else {
					isspPTreeNode.setIcon(tree.getF_sys_name());
				}
				if ("0".equals(tree.getF_status()))
					isspPTreeNode.setImage(imgMap.get("off_" + tree.getF_type()));
				else
					isspPTreeNode.setImage(imgMap.get("on_" + tree.getF_type()));
				isspPTreeNode.setId(tree.getCId());
				//isspPTreeNode.setLevel(tree.getF_sys_name_old());
				//isspPTreeNode.setIcon(tree.getF_id());
				nodes.add(isspPTreeNode);// 添加到节点容器
			}

//			List<ISSPTreeNode> tree=ISSPTreeBuilder.buildTree(nodes);//调用共同静态类创建树
			returnObject.setStatus("1");
			returnObject.setList(nodes);
//			returnObject.setList(tree);
			returnObject.setMap(idMap);
		} catch (Exception e) {
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 适用于Ztree设备树，从缓存中读取设备树
	 *
	 * @return
	 */
	@Override
	public ISSPReturnObject getTreeFormat() {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {
			// 直接从缓存中获取
			Cache<String, BESSbPzStruct> sbdyCache = sbPzStructCache.getSbPzStructCache();//加载设备树信息
			if (sbdyCache == null)
			{
				returnObject.setStatus("0");
				return returnObject;
			}

			List<BESSbPzStruct> sbdyList = new ArrayList<>(sbdyCache.values());

			if (sbdyList.size() == 0) {
				returnObject.setStatus("0");
				return returnObject;
			}

			// 排序
			Collections.sort(sbdyList, Comparator.comparingInt(o -> Integer.parseInt(o.getF_id())));

			returnObject.setStatus("1");
			returnObject.setList(sbdyList);
		} catch (Exception e) {
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getTreeLazyLoad(String pSysName) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (pSysName == null) {
			pSysName = "root";
		}

		// 直接从缓存中获取
		List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCachedElementByPSysName(pSysName);//加载设备树信息

		if (besSbPzStructs == null || besSbPzStructs.isEmpty())
		{
			returnObject.setStatus("0");
			return returnObject;
		}

		// 排序
		Collections.sort(besSbPzStructs, Comparator.comparingInt(o -> Integer.parseInt(o.getF_id())));

		// 改为从缓存中获取
		Cache<String, BESSbTreeNodeType> nodeTypeCache = sbTreeNodeTypeCache.getSbTreeNodeTypeCache();

		if (nodeTypeCache == null)
		{
			returnObject.setStatus("0");
			return returnObject;
		}

		Collection<BESSbTreeNodeType> nodeTypeCacheList = nodeTypeCache.values();

		if (nodeTypeCacheList.size() == 0) {
			returnObject.setStatus("0");
			return returnObject;
		}

		Map<String, String> imgMap = new HashMap<>();

		for(BESSbTreeNodeType besSbTreeNodeType: nodeTypeCacheList){
			imgMap.put("on_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_online_img());
			imgMap.put("off_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_offline_img());
		}

		Set<String> pSysNames = new HashSet<>();

		Collection<BESSbPzStruct> besSbPzStructCache = sbPzStructCache.getSbPzStructCache().values();

		besSbPzStructCache.forEach(besSbPzStruct -> {
			pSysNames.add(besSbPzStruct.getF_psys_name());
		});


		List<ISSPTreeNode> nodes = new ArrayList<>();//把所有资源转换成树模型的节点集合，此容器用于保存所有节点

		for (BESSbPzStruct tree : besSbPzStructs) {//获得一棵树模型的数据
			ISSPTreeNode isspPTreeNode=new ISSPTreeNode();
			isspPTreeNode.setNodeTreeId(tree.getCId());
			isspPTreeNode.setPid(tree.getPId());
			isspPTreeNode.setRootId(tree.getF_id());
			isspPTreeNode.setText(tree.getCMc());
			isspPTreeNode.setNodeType(tree.getF_type());
			isspPTreeNode.setNodeStatus(tree.getF_status());
			isspPTreeNode.setNodeAttribution(tree.getF_node_attribution());
			if (tree.getF_sys_name_old() != null && tree.getF_sys_name_old() !=  ""){
				isspPTreeNode.setIcon(tree.getF_sys_name_old());
			}else {
				isspPTreeNode.setIcon(tree.getF_sys_name());
			}
			if ("0".equals(tree.getF_status()))
				isspPTreeNode.setImage(imgMap.get("off_" + tree.getF_type()));
			else
				isspPTreeNode.setImage(imgMap.get("on_" + tree.getF_type()));
			isspPTreeNode.setId(tree.getCId());

			if (pSysNames.contains(tree.getF_sys_name())){
				isspPTreeNode.setIsChildNodeExist(ISSPTreeNode.CHILD_NODE_EXIST);
			}else {
				isspPTreeNode.setIsChildNodeExist(ISSPTreeNode.CHILD_NODE_NONEXIST);
			}

			nodes.add(isspPTreeNode);// 添加到节点容器
		}

		returnObject.setStatus("1");
		returnObject.setList(nodes);

		return returnObject;
	}

	@Override
	public ISSPReturnObject getTreeReload(String pSysName) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (pSysName == null) {
			pSysName = "root";
		}

		//刷新设备树缓存
		sbPzStructCache.loadCache();
		// 直接从缓存中获取
		List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCachedElementByPSysName(pSysName);//加载设备树信息

		if (besSbPzStructs == null || besSbPzStructs.isEmpty())
		{
			returnObject.setStatus("0");
			return returnObject;
		}

		// 排序
		Collections.sort(besSbPzStructs, Comparator.comparingInt(o -> Integer.parseInt(o.getF_id())));

		// 改为从缓存中获取
		Cache<String, BESSbTreeNodeType> nodeTypeCache = sbTreeNodeTypeCache.getSbTreeNodeTypeCache();

		if (nodeTypeCache == null)
		{
			returnObject.setStatus("0");
			return returnObject;
		}

		Collection<BESSbTreeNodeType> nodeTypeCacheList = nodeTypeCache.values();

		if (nodeTypeCacheList.size() == 0) {
			returnObject.setStatus("0");
			return returnObject;
		}

		Map<String, String> imgMap = new HashMap<>();

		for(BESSbTreeNodeType besSbTreeNodeType: nodeTypeCacheList){
			imgMap.put("on_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_online_img());
			imgMap.put("off_"+besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_offline_img());
		}

		Set<String> pSysNames = new HashSet<>();

		Collection<BESSbPzStruct> besSbPzStructCache = sbPzStructCache.getSbPzStructCache().values();

		besSbPzStructCache.forEach(besSbPzStruct -> {
			pSysNames.add(besSbPzStruct.getF_psys_name());
		});


		List<ISSPTreeNode> nodes = new ArrayList<>();//把所有资源转换成树模型的节点集合，此容器用于保存所有节点

		for (BESSbPzStruct tree : besSbPzStructs) {//获得一棵树模型的数据
			ISSPTreeNode isspPTreeNode=new ISSPTreeNode();
			isspPTreeNode.setNodeTreeId(tree.getCId());
			isspPTreeNode.setPid(tree.getPId());
			isspPTreeNode.setRootId(tree.getF_id());
			isspPTreeNode.setText(tree.getCMc());
			isspPTreeNode.setNodeType(tree.getF_type());
			isspPTreeNode.setNodeStatus(tree.getF_status());
			isspPTreeNode.setNodeAttribution(tree.getF_node_attribution());
			if (tree.getF_sys_name_old() != null && tree.getF_sys_name_old() !=  ""){
				isspPTreeNode.setIcon(tree.getF_sys_name_old());
			}else {
				isspPTreeNode.setIcon(tree.getF_sys_name());
			}
			if ("0".equals(tree.getF_status()))
				isspPTreeNode.setImage(imgMap.get("off_" + tree.getF_type()));
			else
				isspPTreeNode.setImage(imgMap.get("on_" + tree.getF_type()));
			isspPTreeNode.setId(tree.getCId());

			if (pSysNames.contains(tree.getF_sys_name())){
				isspPTreeNode.setIsChildNodeExist(ISSPTreeNode.CHILD_NODE_EXIST);
			}else {
				isspPTreeNode.setIsChildNodeExist(ISSPTreeNode.CHILD_NODE_NONEXIST);
			}

			nodes.add(isspPTreeNode);// 添加到节点容器
		}

		returnObject.setStatus("1");
		returnObject.setList(nodes);

		return returnObject;
	}


	@Override
	public ISSPReturnObject getAustereTree() {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
		String qxbh = "0008";
		String pscol = "";
		String yhbh = username;
		String pibzw = "1";
		LimitParamObject paramObject = new LimitParamObject();
		paramObject.setQxbh(qxbh);
		paramObject.setPscol(pscol);
		paramObject.setYhbh(yhbh);
		paramObject.setPibzw(pibzw);
		String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);


		try {
			List<BESSbPzStruct> sbdylist = besSbdyMapper.loadAll(checkDataLimit);//加载设备树信息
			returnObject.setStatus("1");
			returnObject.setList(sbdylist);
		} catch (Exception e) {
			returnObject.setStatus("0");
			e.printStackTrace();
		}

		return returnObject;
	}

	/**
	 * 初次加载获取设备树结构信息
	 * @param pageNum
	 * @param
	 * @return
	 */
	@Override
	public PageInfo<BESSbPzStruct> findSbdyInfo(Integer pageNum, String sbdy_in) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 获取设备树结构信息
		List<BESSbPzStruct> besSbdy = besSbdyMapper.findSbdyInfo(sbdy_in);
		// 用PageInfo对结果进行包装
		PageInfo<BESSbPzStruct> page = new PageInfo<BESSbPzStruct>(besSbdy);
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}

	/**
	 * 获取设备树最新状态
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public ISSPReturnObject loadAll() throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
		String qxbh = "0008";
		String pscol = "";
		String yhbh = username;
		String pibzw = "1";
		LimitParamObject paramObject = new LimitParamObject();
		paramObject.setQxbh(qxbh);
		paramObject.setPscol(pscol);
		paramObject.setYhbh(yhbh);
		paramObject.setPibzw(pibzw);
		String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
		try {
			List<BESSbPzStruct> list = besSbdyMapper.loadAll(checkDataLimit);
			returnObject.setMsg("1");
			returnObject.setList(list);
		} catch (Exception e) {
			returnObject.setMsg("0");
		}
		return returnObject;
	}
	/**
	 * 获取所有DO点用于照明监控首次加载
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public ISSPReturnObject getAllDOInfo() throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,String>> list = besSbdyMapper.loadAllDO();
			returnObject.setMsg("1");
			returnObject.setList(list);
		} catch (Exception e) {
			returnObject.setMsg("0");
		}
		return returnObject;
	}



	/**
	 * 获取设备状态
	 */
	public ISSPReturnObject loadAllForJK(List<String> f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (null != f_sys_name && f_sys_name.isEmpty())
		{
			returnObject.setMsg("0");
			return returnObject;
		}
		try {
			// TODO getSbtreeList
			// List<BESSbPzStruct> list = BESSbtreeThread.getSbtreeList();
//			List<BESSbPzStruct> list = new ArrayList<>(BESSbtreeThread.realTimeData.values());

			List<BESSbPzStruct> list = new ArrayList<>();

			// 直接从缓存中获取
			if (f_sys_name == null)
			{
				list = new ArrayList<>(sbPzStructCache.getSbPzStructCache().values());//加载设备树信息
			}else {
				List<BESSbPzStruct> finalList = list;
				f_sys_name.forEach(sysName -> {
					finalList.add(sbPzStructCache.getCachedElement(sysName));
				});
			}

			//List<Map<String, Object>> list = besSbdyMapper.getTreeInfoAndPointData(f_sys_name);
			returnObject.setMsg("1");
			returnObject.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setMsg("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject loadAllForJK(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			// TODO getSbtreeList 实时监控页面
			//List<BESSbPzStruct> list = BESSbtreeThread.getSbtreeList();
			List<BESSbPzStruct> listBes = new ArrayList<>();
			if(f_sys_name != null){
				String[] besList = f_sys_name.split(",");
				for(int i=0;i<besList.length;i++){
				/*	for(BESSbPzStruct besSbPzStruct: list){
						if(besSbPzStruct.getF_sys_name().equals(besList[i])){
							listBes.add(besSbPzStruct);
							break;
						}
					}*/

					BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(besList[i]);
					if(null != besSbPzStruct){
						listBes.add(besSbPzStruct);
					}

				}
			}
			returnObject.setMsg("1");
			returnObject.setList(listBes);
		} catch (Exception e) {
			returnObject.setMsg("0");
		}
		return returnObject;
	}

	/**
	 * 获取特定设备实时状态
	 */
	public ISSPReturnObject loadAllForgivenJK(HttpServletRequest request) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			// 得到前台传过来的id数组
			List<Object> jsonList = jsonToList(request.getParameter("treeidList"));
			//List<BESSbPzStruct> list = BESSbtreeThread.getSbtreeList();// TODO
			List<BESSbPzStruct> newlist = new ArrayList<>();
			for(int i=0;i<jsonList.size();i++){
			/*	for(int j=0;j<list.size();j++){
					String f_sys_name = list.get(j).getF_sys_name();
					if(f_sys_name.equals(jsonList.get(i))){
						newlist.set(i,list.get(j));
					}
				}*/

				BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(jsonList.get(i));

				if(null != besSbPzStruct){
					newlist.add(besSbPzStruct);
				}

			}
			returnObject.setMsg("1");
			//returnObject.setList(list);
			returnObject.setList(newlist);
		} catch (Exception e) {
			returnObject.setMsg("0");
		}
		return returnObject;
	}


	/**
	 * 获取 ddc 实时数据（监控页面）
	 * @param sysName
	 * @return
	 */
	@Override
	public ISSPReturnObject getRvscanData(String sysName) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if(!StringUtils.hasText(sysName)){
			returnObject.setStatus("0");
			return returnObject;
		}

		List<BESSbPzStruct> ddcList = new ArrayList<>();

		String[] sysNameList = sysName.split(",");

		for(int i = 0; i < sysNameList.length; i++){

			String item = sysNameList[i];

			BESSbPzStruct ddcInfo = besSbdyMapper.sbpzQueryPollStatus(item);

			if(null == ddcInfo){
				continue;
			}

			// 0:可以轮询 1:不可以轮询
			/*if("0".equals(ddcInfo.getF_poll_status())){
				continue;
			}*/

			BESPollingThread bESPollingThread = new BESPollingThread();
			List<BESSbPzStruct> ddcRealTimeDataTree = new ArrayList<>();
			List<BESSbPzStruct> ddcRealTimeData = new ArrayList<>();

			bESPollingThread.setDdcInfo(ddcInfo);
			bESPollingThread.setAllSbList(ddcRealTimeData);
			bESPollingThread.setSbAllTreeList(ddcRealTimeDataTree);

			bESPollingThread.run();

			ddcList.addAll(ddcRealTimeData);

		}

		if(ddcList.isEmpty()){
			returnObject.setStatus("0");
			return returnObject;
		}

		returnObject.setStatus("1");
		returnObject.setData(ddcList);
		return returnObject;
	}

	@Override
	public ISSPReturnObject getOpcKeyAll() {

		ISSPReturnObject isspReturnObject = new ISSPReturnObject();

		List<Map<String, Object>> opcKey = null;
		try {
			opcKey = besJobManagerMapper.getOpcKeyAll();
		/*	List opcKeyList = new ArrayList();
			for(int i = 0; i < opcKey.size(); i++){
				opcKeyList.add(opcKey.get(i).get("name") + "*" + opcKey.get(i).get("dnmc"));
			}*/
			isspReturnObject.setData(opcKey);
			isspReturnObject.setStatus("1");
		} catch (Exception e) {
			isspReturnObject.setStatus("0");
			e.printStackTrace();
		}

		return isspReturnObject;
	}

	/**
	 * @Description: 字符串转换list
	 * @param s
	 * @return
	 * @author YangChao
	 * @date 2019/8/21 14:59
	 */
	public static List<Object> jsonToList(String s){
		List<Object> list =new ArrayList<>();
		try {
			JSONArray jsonArray = JSONArray.fromObject(s);
			for (int i=0;i<jsonArray.size();i++){
				list.add(jsonArray.get(i));
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	/**
	 * 加载设备树节点类型信息
	 */
	@Override
	public ISSPReturnObject findChildByTreeNoteTypes() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {

			// List<BESSbTreeNodeType> nodeTypeInfoList = besSbdyMapper.findChildByTreeNoteType(null);//从数据库获取所有资源

			// 改为从缓存中获取
			Cache<String, BESSbTreeNodeType> nodeTypeCache = sbTreeNodeTypeCache.getSbTreeNodeTypeCache();

			if (nodeTypeCache == null)
			{
				returnObject.setStatus("0");
				return returnObject;
			}

			Collection<BESSbTreeNodeType> nodeTypeCacheList = nodeTypeCache.values();

			if (nodeTypeCacheList.size() == 0) {
				returnObject.setStatus("0");
				return returnObject;
			}

			returnObject.setStatus("1");
			returnObject.setList(new ArrayList<>(nodeTypeCacheList));
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 根据节点类型获取设备定义所有子节点
	 */
	@Override
	public ISSPReturnObject findChildByTreeNoteType(String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESSbTreeNodeType data = besSbdyMapper.findChildByTreeNoteType(f_type).get(0);
			returnObject.setData(data);
			returnObject.setStatus("1");
			returnObject.setMsg("success");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("fail");
		}
		return returnObject;
	}

	/**
	 * 模块属性信息中模块型号下拉列表获取模块类型信息
	 */
	@Override
	public ISSPReturnObject getModuleTypeInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESModuleType> moduleTypeList = besSbdyMapper.getModuleTypeInfo();
		for(BESModuleType moduleType : moduleTypeList){
			if (moduleType.getfModuleType() == null || "".equals(moduleType.getfModuleType())) {
				moduleType.setfModuleType(null);
			}
		}
		if (moduleTypeList.size() > 0 ) {
			returnObject.setStatus("1");
			returnObject.setList(moduleTypeList);
		} else
			returnObject.setStatus("0");
		return returnObject;
	}

	/**
	 * 虚点属性信息中虚点类型下拉列表获取虚点类型信息
	 */
	@Override
	public ISSPReturnObject getVpointTypeInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESVPointType> vpointTypeList = besSbdyMapper.getVpointTypeInfo();
		for(BESVPointType vpointType : vpointTypeList){
			if (vpointType.getId() == null || "".equals(vpointType.getId())) {
				vpointType.setId(null);
			}
		}
		if (vpointTypeList.size() > 0 ) {
			returnObject.setStatus("1");
			returnObject.setList(vpointTypeList);
		} else
			returnObject.setStatus("0");
		return returnObject;
	}

	/**
	 * 根据所选节点编辑对应的属性信息
	 * 王红杰修改
	 */
	@Override
	@Transactional
	public ISSPReturnObject editSbdyInfoBySelByNode(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(obj.get("f_max_val") != null){
			String mav_val =  obj.get("f_max_val").toString();
			String fMaxVal = mav_val.replace(",", "");
			obj.put("f_max_val", fMaxVal);
		}
		if (obj.getString("f_ip_addr") != null){
			String ipTxt=obj.get("f_ip_addr").toString();
			String tabName=obj.get("tabName").toString();
			String f_guid = obj.get("f_guid").toString();
			//wanghongjie ip的唯一验证  BesDdc start
			if (obj.get("f_guid")!=null){
				if (obj.getString("f_guid") != null && !("".equals(obj.getString("f_guid")))){
					String  besSbPzF_guid = besSbdyMapper.editSbdyInfoBySelByNodeByF_guid(f_guid);
					if (!besSbPzF_guid.equals(ipTxt)){
						if (obj.get("f_ip_addr")!=null){

							if(obj.getString("f_ip_addr") !=null && !("".equals(obj.getString("f_ip_addr")))) {
								List<BesDdc>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ipTxt,tabName);
								if (0!=besSbPzStruct.size()) {
									returnObject.setStatus("3");
									return returnObject;
								}
							}
						}
					}
				}
			}
		}
		//Start  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
		//输入的系统名称限制唯一
		String id = obj.get("f_sys_name").toString();
		if (id == null || "".equals(id)) {
			returnObject.setStatus("0");
			return returnObject;
		}
		//f_sys_name唯一验证
		if(obj.getString("attrTabName") !=null || !("".equals(obj.getString("attrTabName"))) || obj.getString("f_sys_name")!=null) {
			BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(id);
			if (besSbPzStruct!=null){
				String f_id=besSbPzStruct.getF_id();
				if (obj.get("f_id") != null){
					if ( !f_id.equals(obj.get("f_id"))){
						returnObject.setStatus("2");
						return returnObject;
					}
				}
			}

		}
		//End  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
		if (obj.get("f_sys_name") != null && obj.getString("tabName")!=null) {
			if (obj.get("tabName").equals("BES_SBPZ_STRUCT") ){
				int flag = besSbdyMapper.editSbTreeStructInfoBySysNamexh(obj);
				int sbFlag = 0;
				if(!"bes_sbpz_struct".equals(obj.getString("tabName").toLowerCase())) {//更新设备树信息
					String myInput_f_sys_name=obj.getString("old_f_sys_name");
					String f_sys_name = obj.getString("f_sys_name");
					String f_nick_name = obj.getString("f_nick_name");
					String f_status = obj.getString("f_status");
					obj = new JSONObject();
					obj.put("myInput_f_sys_name",myInput_f_sys_name);
					obj.put("f_sys_name", f_sys_name);
					obj.put("f_nick_name", f_nick_name);
					obj.put("f_status", f_status);
					obj.put("tabName", "BES_SBPZ_STRUCT");
					if("BES_ANALOG_INPUT".equals((String)obj.get("tabName"))){
						obj.put("f_energyStatics", "");
						obj.put("f_staticsTime", "");
					}
					//obj.put(f_allpath, f_allpath);
					sbFlag = besSbdyMapper.editSbTreeStructInfoBySysName1(obj);
				}
				if (flag + sbFlag >= 1)
					returnObject.setStatus("1");
				else
					returnObject.setStatus("0");
			}else {
				int flag = besSbdyMapper.editSbTreeStructInfoBySysNameXT(obj);
				if("10".equals(obj.getString("f_node_type"))&&"0".equals(obj.getString("f_energyStatics"))){
					String f_sbid =besSbdyMapper.isAmmeter(obj.getString("f_sys_name"));
					String fSysName = obj.getString("f_sys_name");
					if(f_sbid == null){
						obj.put("attr_f_sblxbh",obj.getString("f_node_type"));//设备类型编号  虚点能耗的为4
						obj.put("attr_f_sys_name",fSysName);//系统名称
						obj.put("attr_f_alarm_enable","");//报警使能状态
						obj.put("attr_f_alarm_type","");//报警类型
						obj.put("attr_f_init_val","");//初始值
						obj.put("attr_f_accuracy","");//精度
						obj.put("attr_f_high_limit","");//高限值
						obj.put("attr_f_close_state","");//闭合状态
						obj.put("attr_f_node_type","");//节点类型
						obj.put("attr_f_alarm_priority","");//报警优先级
						obj.put("attr_f_engineer_unit","");//单位
						obj.put("attr_f_low_limit","");//低限值
						obj.put("f_energyStatics","");//能耗采集
						obj.put("f_staticsTime","");//采集时间
						obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
						obj.put("attrTabName", "BES_AMMETER");
						flag = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
					}else{
						obj.put("tabName", "BES_AMMETER");
						obj.put("f_sblxbh",obj.getString("f_node_type"));//设备类型编号  虚点能耗用来存虚点类型
						obj.put("f_alarm_enable","");//报警使能状态
						obj.put("f_alarm_type","");//报警类型
						obj.put("f_init_val",null);//初始值
						obj.put("f_accuracy","");//精度
						obj.put("f_high_limit","");//高限值
						obj.put("f_close_state","");//闭合状态
						obj.put("f_node_type","");//节点类型
						obj.put("f_alarm_priority","");//报警优先级
						obj.put("f_engineer_unit","");//单位
						obj.put("f_low_limit","");//低限值
						obj.put("f_channel_index","");//通道索引
						obj.put("f_work_mode","");//工作模式
						obj.put("f_max_val","");//最大值
						obj.put("f_min_val","");//最小值
						obj.put("f_reversed","");//是否反向
						obj.put("f_sinnal_type","");//信号类型
						flag = besSbdyMapper.editSbTreeStructInfoBySysName(obj);
					}
				}
				int sbFlag = 0;
				if(!"bes_sbpz_struct".equals(obj.getString("tabName").toLowerCase())) {//更新设备树信息
					String myInput_f_sys_name=obj.getString("old_f_sys_name");
					String f_sys_name = obj.getString("f_sys_name");
					String f_nick_name = obj.getString("f_nick_name");
					String f_status = obj.getString("f_status");
					obj = new JSONObject();
					obj.put("myInput_f_sys_name",myInput_f_sys_name);
					obj.put("f_sys_name", f_sys_name);
					obj.put("f_nick_name", f_nick_name);
					obj.put("f_status", f_status);
					obj.put("tabName", "BES_SBPZ_STRUCT");
					//obj.put(f_allpath, f_allpath);
					sbFlag = besSbdyMapper.editSbTreeStructInfoBySysName1(obj);
				}
				if (flag + sbFlag >= 1)
					returnObject.setStatus("1");
				else
					returnObject.setStatus("0");
			}


		} else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	//Start add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题
	/**
	 * 更新BES_VPOINT虚点信息，由editSbdyInfoBySelByNode()方法改进
	 */
	@Override
	@Transactional
	public ISSPReturnObject updateTableBesVpoint(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (obj.get("f_sys_name") != null && obj.getString("tabName")!=null) {
			if("4".equals(obj.getString("f_node_type"))&&"0".equals(obj.getString("f_energyStatics"))){//统计虚点能耗  add by tianjiangwei 0415
				int vpointFlag = besSbdyMapper.updateTableBesVpoint(obj);//仅仅为将更新BES_VPOINT表所应用的上方通用sql更正为应用虚点表自己的sql，剩余逻辑不变
				String f_sbid =besSbdyMapper.isAmmeter(obj.getString("f_sys_name"));
				String fSysName = obj.getString("f_sys_name");
				int flag = 0;
				if(f_sbid == null){
					obj.put("attr_f_sblxbh",obj.getString("f_node_type"));//设备类型编号  虚点能耗的为4
					obj.put("attr_f_sys_name",fSysName);//系统名称
					obj.put("attr_f_alarm_enable","");//报警使能状态
					obj.put("attr_f_alarm_type","");//报警类型
					obj.put("attr_f_init_val","");//初始值
					obj.put("attr_f_accuracy","");//精度
					obj.put("attr_f_high_limit","");//高限值
					obj.put("attr_f_close_state","");//闭合状态
					obj.put("attr_f_node_type","");//节点类型
					obj.put("attr_f_alarm_priority","");//报警优先级
					obj.put("attr_f_engineer_unit","");//单位
					obj.put("attr_f_low_limit","");//低限值
					obj.put("f_energyStatics","");//能耗采集
					obj.put("f_staticsTime","");//采集时间
					obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
					obj.put("attrTabName", "BES_AMMETER");
					flag = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
				}else{
					obj.put("tabName", "BES_AMMETER");
					obj.put("f_sblxbh",obj.getString("f_node_type"));//设备类型编号  虚点能耗用来存虚点类型
					obj.put("f_alarm_enable","");//报警使能状态
					obj.put("f_alarm_type","");//报警类型
					obj.put("f_init_val",null);//初始值
					obj.put("f_accuracy","");//精度
					obj.put("f_high_limit","");//高限值
					obj.put("f_close_state","");//闭合状态
					obj.put("f_node_type","");//节点类型
					obj.put("f_alarm_priority","");//报警优先级
					obj.put("f_engineer_unit","");//单位
					obj.put("f_low_limit","");//低限值
					flag = besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				}

				int sbFlag = 0;
				if(!"bes_sbpz_struct".equals(obj.getString("tabName").toLowerCase())) {//更新设备树信息
					String f_sys_name = obj.getString("f_sys_name");
					String f_nick_name = obj.getString("f_nick_name");
					String f_status = obj.getString("f_status");
					//String f_allpath = (String) obj.get("f_allpath");
					obj = new JSONObject();
					obj.put("f_sys_name", f_sys_name);
					obj.put("f_nick_name", f_nick_name);
					obj.put("f_status", f_status);
					obj.put("tabName", "BES_SBPZ_STRUCT");
					//obj.put(f_allpath, f_allpath);
					sbFlag = besSbdyMapper.editSbTreeStructInfoBySysName(obj);

				}
				if (flag + sbFlag >= 1)
					returnObject.setStatus("1");
				else
					returnObject.setStatus("0");//end
			}else{
				//int flag = besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				int flag = besSbdyMapper.updateTableBesVpoint(obj);//仅仅为将更新BES_VPOINT表所应用的上方通用sql更正为应用虚点表自己的sql，剩余逻辑不变
				int sbFlag = 0;
				if(!"bes_sbpz_struct".equals(obj.getString("tabName").toLowerCase())) {//更新设备树信息
					String f_sys_name = obj.getString("f_sys_name");
					String f_nick_name = obj.getString("f_nick_name");
					String f_status = obj.getString("f_status");
					//String f_allpath = (String) obj.get("f_allpath");
					obj = new JSONObject();
					obj.put("f_sys_name", f_sys_name);
					obj.put("f_nick_name", f_nick_name);
					obj.put("f_status", f_status);
					obj.put("tabName", "BES_SBPZ_STRUCT");
					//obj.put(f_allpath, f_allpath);
					sbFlag = besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				}
				if (flag + sbFlag >= 1)
					returnObject.setStatus("1");
				else
					returnObject.setStatus("0");

			}

		} else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	//End add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题


	/**
	 * 编辑采集器信息
	 * 王红杰修改
	 */
	@Override
	@Transactional
	public ISSPReturnObject editSbdyInfoCollector(JSONObject obj) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		String attrTabName = obj.getString("attrTabName");
		String guid = obj.getString("f_guid");
		String tabName = obj.getString("tabName");

		String ip = obj.getString("attr_f_ip_addr"); // ip 地址
		String name = obj.getString("attr_f_sys_name"); // 系统名称
		String alias = obj.getString("attr_f_nick_name"); // 别名
		String description = obj.getString("attr_f_description"); // 描述
		String location = obj.getString("attr_f_azwz"); // 安装位置
		String zone = obj.getString("attr_f_ssqy"); // 安装位置
		Integer active = obj.getInteger("f_enabled"); // 使能状态
		Integer samplePeriod = obj.getInteger("attr_f_coll_cycle"); // 采样周期 单位为分钟
		Integer hisDataSavePeriod = obj.getInteger("attr_f_his_data_save_cycle"); // 历史数据保存周期，单位为小时
		Integer upDataSamplePeriod =  obj.getInteger("attr_f_loop_time"); // 上传数据采样周期 单位为分钟
		String gateway = obj.getString("f_gateway"); // 默认网关
		String mask = obj.getString("f_mask"); // 子网掩码
		String ipMaster = obj.getString("f_ip_master"); // 主机ip
		String portMaster = obj.getString("f_port_master"); // 主机端口

		if (!StringUtils.hasText(attrTabName)
				|| !StringUtils.hasText(guid)
				|| !StringUtils.hasText(tabName)
		)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请求参数错误");
			return returnObject;
		}

		if (null == ip
				|| !StringUtils.hasText(name)
				|| !StringUtils.hasText(alias)
				|| !StringUtils.hasText(description)
				|| !StringUtils.hasText(location)
				|| !StringUtils.hasText(zone)
				|| null == active
				|| null == samplePeriod
				|| null == upDataSamplePeriod
				|| null == hisDataSavePeriod
				|| !StringUtils.hasText(gateway)
				|| !StringUtils.hasText(mask)
				|| !StringUtils.hasText(ipMaster)
				|| !StringUtils.hasText(portMaster)
				)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		BesCollector besCollector = besCollectorMapper.selectByPrimaryKey(obj.getString("f_guid"));

		if (null == besCollector)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请求参数错误");
			return returnObject;
		}

		if (!ip.equals(besCollector.getfIpAddr()))
		{
			List<BesDdc>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ip, tabName);

			if (besSbPzStruct.size() > 0 && !name.equals(besSbPzStruct.get(0).getfSysName()))
			{
				returnObject.setStatus("3");
				returnObject.setMsg("ip 地址重复");
				return returnObject;
			}

		}


		besSbdyMapper.editSbTreeStructInfo(obj);// BES_SBPZ_STRUCT

		String channelId = besCollector.getfChannelId();

		// 如果是离线状态则使通道id等于ip
		if ("0".equals(besCollector.getfOnlineState()))
		{
			obj.put("f_channel_id", ip);
			channelId = ip;
		}


		obj.put("f_collector_state", "0");

		besSbdyMapper.editSbTreeAttrInfoByGuid(obj);// BES_COLLECTOR

		try
		{

			Integer sbId = Integer.parseInt(besCollector.getfSbId());

			ControllerDataEDC controllerData = new ControllerDataEDC();

			controllerData.setId(sbId);
			controllerData.setName(name);
			controllerData.setAlias(alias);
			controllerData.setDescription(description);
			controllerData.setLocation(location);
			controllerData.setZone(zone);
			controllerData.setActive(active);
			controllerData.setSamplePeriod(samplePeriod);
			controllerData.setUpDataSamplePeriod(upDataSamplePeriod);
			controllerData.setHisDataSavePeriod(hisDataSavePeriod);
			controllerData.setIp(ip);
			controllerData.setGateWay(gateway);
			controllerData.setMask(mask);
			controllerData.setServerIP(ipMaster);
			controllerData.setServerPort(Integer.parseInt(portMaster));

			boolean sendState = SendMsgHandler.setControllerEDC(channelId, controllerData);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("编辑成功，下发失败");

				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_PARAM_SET, channelId);

		}catch (Exception e)
		{
			e.printStackTrace();
		}

		returnObject.setStatus("1");
		returnObject.setMsg("编辑成功，下发成功");

		return returnObject;
	}

	/**
	 * 更新添加默认节点的属性信息--并向下位机设备发送数据(逻辑点实点)---add by LvSihan
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 *
	 * 王红杰修改
	 */
	@Override
	@Transactional
	public ISSPReturnObject editDefaultNodeInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> ddcinfo = new HashMap<>();
		Map<String, Object> pointMap = new HashMap<>();
		String fEnergyStatics = "";
		String fStaticsTime = "";
		//输入的系统名称限制唯一
		String id = obj.get("f_sys_name").toString();
		if (id == null || "".equals(id)) {
			returnObject.setStatus("0");
			return returnObject;
		}

		//f_sys_name唯一验证
		if(obj.getString("attrTabName") !=null && !("".equals(obj.getString("attrTabName")))) {
			BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysNameChange(id,obj.get("attrTabName").toString());
			if (besSbPzStruct != null) {
				returnObject.setStatus("4");
				return returnObject;
			}
		}

		/*if (obj.getString("f_status") == null) {
			obj.put("f_status", "1");//设置添加默认状态
		}*/

		if (obj.get("f_sys_name") != null) {
			pointMap=besSbdyMapper.listpoint(obj.getString("old_f_sys_name"));
			if (pointMap.get("F_SYS_NAME") != obj.getString("myInput_f_sys_name")){
				obj.put("myInput_f_sys_name",pointMap.get("F_SYS_NAME"));
				obj.put("attr_f_sys_name",pointMap.get("F_SYS_NAME"));
			}

			int addAmmeterFlag =0;//将ai点存到电表中用于统计实点能耗bes_ammeter
			if("BES_ANALOG_INPUT".equals((String) obj.get("attrTabName"))){
				fEnergyStatics = (String) obj.get("f_energyStatics");
				fStaticsTime = (String) obj.get("f_staticsTime");
				obj.put("f_energyStatics","");
				obj.put("f_staticsTime","");
			}
			int upflag = besSbdyMapper.editSbTreeStructInfoBySysName2(obj);//根据输入的系统名称来修改数据,表名为结构树表
			String fSysName = obj.getString("attr_f_sys_name");//系统名称
			String fNickName = obj.getString("attr_f_nick_name");//别名
			String fEnabled = obj.getString("attr_f_enabled");//使能状态
			//根据已有F_GUID生成
			String maxId = besSbdyMapper.queryMaxId(obj.getString("attrTabName"));
			String f_guid =  getAutoIncreaseCol(maxId);
			obj.put("attr_f_guid", f_guid);
			obj.put("f_energyStatics",fEnergyStatics);
			obj.put("f_staticsTime",fStaticsTime);
			int addflag = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表属性信息
			if (upflag + addflag > 1) {
				int reStr = 1;
				//查询逻辑点所属模块的信息
				Map<String, Object> moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint((String) obj.get("old_f_sys_name"));
				//通过节点所在级数，区分Lamp和Cross,7为Cross楼控，5为Lamp照明
				if(obj.getString("nodeLevel").equals("7")) {
					//查询逻辑点所属DDC控制器的IP地址
					ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint((String) obj.get("old_f_sys_name"));
					obj.put("DDCIPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
					obj.put("DDCPort", ddcinfo.get("F_PORT"));//端口号
					obj.put("moduleFguid", moduleinfo.get("F_SBID"));//模块信息
					//bes_sbtree_nodetype表中定义AI，AO,DO,DI分别为10，11，12，13，截取个位即可与bes_modulepoint_type表中点类型相对应，即0，1，2，3
					obj.put("attr_f_node_type", obj.getString("attr_f_node_type").substring(1));
					//向下位机逻辑点发送数据(实点)
					Dto retDto = crossEquipmentServiceImpl.operCrossController(obj, 15);
					reStr = retDto.getAsInteger("code");
				}else {
					//查询逻辑点所属模块的父节点属性
					String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
					if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
						ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME_OLD"));
					}else if(pNodeType.equals("3"))	{
						//查询逻辑点所属IP路由器的IP地址
						//wanghongjie 修改根据f_id查询逻辑点所属IP路由器的IP地址
						ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPointByFid((String)obj.get("f_id"));
					}

					obj.put("DDCIPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
					obj.put("DDCPort", ddcinfo.get("F_PORT"));//端口号
					obj.put("moduleFguid", moduleinfo.get("F_SBID"));//模块信息
					//bes_sbtree_nodetype表中定义AI，AO,DO,DI分别为10，11，12，13，截取个位即可与bes_modulepoint_type表中点类型相对应，即0，1，2，3
					obj.put("attr_f_node_type", obj.getString("attr_f_node_type").substring(1));
					//向下位机逻辑点发送数据(实点)
					Dto retDto = lampEquipmentServiceImpl.operLampController(obj, 15);
					reStr = retDto.getAsInteger("code");
				}
				if("10".equals((String)obj.get("f_type"))&&"0".equals((String)obj.get("f_energyStatics"))){  //ai点存在电表中用于统计能耗

					obj.put("attr_f_sblxbh",obj.getString("attr_f_node_type"));//设备类型编号  AI点能耗的为0
					obj.put("attr_f_alarm_enable","");//报警使能状态
					obj.put("attr_f_alarm_type","");//报警类型
					obj.put("attr_f_init_val","");//初始值
					obj.put("attr_f_accuracy","");//精度
					obj.put("attr_f_high_limit","");//高限值
					obj.put("attr_f_close_state","");//闭合状态
					obj.put("attr_f_node_type","");//节点类型
					obj.put("attr_f_alarm_priority","");//报警优先级
					obj.put("attr_f_engineer_unit","");//单位
					obj.put("attr_f_low_limit","");//低限值
					obj.put("attr_f_min_val","");//最小值
					obj.put("attr_f_max_val","");//最大值
					obj.put("attr_f_reversed","");//是否反向
					obj.put("attr_f_sinnal_type","");//信号类型
					obj.put("attr_f_work_mode","");//工作模式
					obj.put("attr_f_channel_index","");//逻辑点通道索引
					obj.put("attr_f_ip_addr","");//ip
					obj.put("attr_f_port","");//端口号
					obj.put("attr_f_sys_name",fSysName);//系统名称
					obj.put("attr_f_nick_name",fNickName);//别名
					obj.put("attr_f_enabled",fEnabled);//是否可用
					obj.put("attr_f_ssqy","");//所属区域
					obj.put("attr_f_azwz","");//安装位置
					obj.put("attr_f_type","0");//类型（电表为0）
					obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
					obj.put("f_energyStatics", "");//能耗统计标志位“”
					obj.put("f_staticsTime", "");//能耗统计标志位“”
					obj.put("attrTabName", "BES_AMMETER");
					addAmmeterFlag = besSbdyMapper.addSbTreeAttrInfo(obj);//将ai点添加到电表数据库表里面

				}

				//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
				if (reStr != 0) {
					returnObject.setMsg("数据库中添加成功，下位机同步失败");
					returnObject.setStatus("1");
					return returnObject;
				}
				returnObject.setStatus("1");
			}else {
				returnObject.setStatus("0");
			}
			return returnObject;
		} else {
			returnObject.setStatus("0");
			return returnObject;
		}
	}

	/**
	 * 通过系统名称获取设备树信息
	 */
	@Override
	public BESSbPzStruct getSbTreeInfoBySysName(String f_sys_name) {
		// 获取全路径信息
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(f_sys_name);
		return besSbPzStruct;
	}

	/**
	 * 获取所选节点获取对应的属性信息
	 */
	@Override
	public ISSPReturnObject findSbdyInfoBySelNode(String f_sys_name,String nodeTabName,String f_type, String f_psys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if(null == nodeTabName){
			returnObject.setStatus("0");
			return returnObject;
		}
		// 获取属性信息
		try {
//			Object obj = besSbdyMapper.findSbdyInfoBySelNode(f_sys_name, nodeTabName);
			//将Object改为map,便于对数据操作（电表页面的端口号，需要根据电表id去总线表里查询）by LvSihan
			Map<String, Object> map = besSbdyMapper.findSbdyInfoBySelNode(f_sys_name, nodeTabName);

			if ("BES_DDC".equals(nodeTabName))
			{
				map.put("F_ONLINE_STATE", ddcCache.getCachedElement(f_sys_name).getfDdcOnlinestate());
			}else if ("BES_COLLECTOR".equals(nodeTabName))
			{
				map.put("F_ONLINE_STATE", collectorCache.getCachedElement(f_sys_name).getfOnlineState());
			}

			if(nodeTabName.equals("BES_AMMETER")) {
				String port = besJobManagerMapper.queryBusProtByMeterId(f_sys_name);

				if (null == map)
				{
					map = new HashMap<>();
				}

				if (null == port)
				{
					port = besJobManagerMapper.queryBusPortBySysName(f_psys_name);
				}

				map.put("F_PORT", port);
			}

			if(map!=null){
				List<BESSbTreeNodeType> nodeTypeList = besSbdyMapper.findChildByTreeNoteType((String) map.get("F_NODE_TYPE"));
				if(nodeTypeList!=null && nodeTypeList.size()>0){
					map.put("F_NODE_TYPE_NAME",nodeTypeList.get(0).getF_node_name());
					map.put("F_NODE_TYPE",nodeTypeList.get(0).getF_node_type());
					/*if (nodeTypeList.get(0).getF_node_type().equals("11") || nodeTypeList.get(0).getF_node_type().equals("10")  ) {
						Double F_INIT_VAL = Double.valueOf(String.valueOf(map.get("F_INIT_VAL")));
						Double F_ACCURACY = Double.valueOf(String.valueOf( map.get("F_ACCURACY")));
						Double F_MIN_VAL = Double.valueOf(String.valueOf(map.get("F_MIN_VAL")));
						Double F_MAX_VAL = Double.valueOf(String.valueOf(map.get("F_MAX_VAL")));
						map.put("F_INIT_VAL",F_INIT_VAL / (Math.pow(10, F_ACCURACY)) );
						map.put("F_MIN_VAL",F_MIN_VAL / (Math.pow(10, F_ACCURACY)) );
						map.put("F_MAX_VAL",F_MAX_VAL / (Math.pow(10, F_ACCURACY)) );
					}*/
					/*if (nodeTabName.equals("BES_VPOINT")) {

						if (nodeTypeList.get(0).getF_node_type().equals("4") || nodeTypeList.get(0).getF_node_type().equals("5")) {
							Double F_INIT_VAL = Double.valueOf(String.valueOf(map.get("F_INIT_VAL")));
							Double F_ACCURACY = Double.valueOf(String.valueOf( map.get("F_ACCURACY")));
							map.put("F_INIT_VAL",F_INIT_VAL / (Math.pow(10, F_ACCURACY)) );
						}
					}*/


				}
				returnObject.setData(map);
				returnObject.setStatus("1");
			}else{
				BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(f_sys_name);
				if(f_type != null ) {
					List<BESSbTreeNodeType> nodeTypeList = besSbdyMapper.findChildByTreeNoteType(f_type);
					if(nodeTypeList!=null && nodeTypeList.size()>0)
					{
						besSbPzStruct.setF_type_name(nodeTypeList.get(0).getF_node_name());
						besSbPzStruct.setF_type(nodeTypeList.get(0).getF_node_type());
					}

				}
				returnObject.setData(besSbPzStruct);
				returnObject.setStatus("0");
			}


		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 根据所选节点弹出菜单添加设备树信息
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	@Transactional
	public ISSPReturnObject addSbdyInfoByTreeBtn(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int addSbdyCount = 0;
		int addAttrTabInfoCount = 0;
		int addDefaultNodeCount = 0;
		String id = obj.get("f_sys_name").toString();
		//wanghongjie ip的唯一性验证 start  BesDdc
		if (obj.get("attr_f_ip_addr")!=null){
			String ipTxt=obj.get("attr_f_ip_addr").toString();
			String tabName=obj.get("attrTabName").toString();
			if(obj.getString("attr_f_ip_addr") !=null && !("".equals(obj.getString("attr_f_ip_addr")))) {
				List<BesDdc>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ipTxt,tabName);
				int qqq=besSbPzStruct.size();
				if (0!=besSbPzStruct.size()) {
					returnObject.setStatus("3");
					return returnObject;
				}
			}
		}
		//end
		if (id == null || "".equals(id)) {
			returnObject.setStatus("0");
			return returnObject;
		}
		//f_sys_name唯一验证#
		if(obj.getString("attrTabName") !=null || !("".equals(obj.getString("attrTabName"))) || obj.getString("f_sys_name")!=null) {
			BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(id);
			if (besSbPzStruct != null) {
				returnObject.setStatus("2");
				return returnObject;
			}
		}

		if (obj.getString("f_status") == null) {
			obj.put("f_status", "1");//设置添加默认状态
		}
		//没有所属系统标识，查询父节点系统标识#
		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_psys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Dto retDto = null;
		String str = LEMSConstants.Service_Success;
		int reStr = 1;
		if(obj.getString("attrTabName") !=null && !("".equals(obj.getString("attrTabName")))) {
			if(obj.getString("attrTabName") .equals("BES_AMMETER")) {
				//查询电表所在的采集器信息
				Map<String, Object> collectorInfo = besJobManagerMapper.querycollectorInfoByAmmeter(obj.getString("f_psys_name"));
				//获取sbid
				obj.put("f_sbid", getSbid((String)collectorInfo.get("F_SYS_NAME")) + 1);

				obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
				//向下位机发送电表属性信息，返回通讯结果,index指定为11
				retDto = enerEquipmentServiceImpl.operEnergyController(obj, "11");
				str = retDto.getAsString("errmsg");
				//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
				if (!str.equals(LEMSConstants.Service_Success)) {
					obj.put("attr_f_ammeter_state", new String("1"));
					str = str + LEMSConstants.SaveAmmeterInfo;
					obj.put("attr_f_online_state", new String("1"));
				}
				//Cross系统DDC控制器需要添加F_GUID，向下位机同步数据
			}else if(obj.getString("attrTabName").equals("BES_DDC")) {
				//ddc节点id默认为1
				obj.put("f_sbid", 1);
				if(obj.getString("f_node_attribution").equals("1")) {
					// 向Cross下位机发送DDC属性信息，返回通讯结果,index指定为0
//   	   				retDto = crossEquipmentServiceImpl.operCrossController(obj, 0);
//   	   				reStr = retDto.getAsInteger("code");
				}else {
					// 向Lamp下位机发送DDC属性信息，返回通讯结果,index指定为0
//   	   				retDto = lampEquipmentServiceImpl.operLampController(obj, 0);
//   	   				reStr = retDto.getAsInteger("code");
				}
				if (reStr != 0) {
					obj.put("attr_f_ddc_state", new String("1"));
					obj.put("attr_f_online_state", new String("1"));
					str = "数据库中添加成功，下位机同步失败";
				}
			}else if(obj.getString("attrTabName") .equals("BES_MODULE")) {
				if(obj.getString("f_node_attribution").equals("1")) {
					//查询模块所属DDC控制器
					Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(obj.getString("f_psys_name"));
					//获取sbid
					obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);
					//Ip,端口号
					obj.put("IPAddr", ddcinfo.get("F_IP_ADDR"));
					obj.put("Port", ddcinfo.get("F_PORT"));
					//查询该模块位于第几条线路
					int FlnID = 0;
					List<Map<String,Object>> lines = besSbdyMapper.queryLines(obj.getString("f_psys_name"));
					for(int i=0;i<lines.size();i++) {
						if(obj.getString("f_psys_name").equals(lines.get(i).get("F_SYS_NAME"))) {
							FlnID=i+1;
						}
					}
					obj.put("FlnID", FlnID);
					//Cross向下位机发送模块属性信息，返回通讯结果,index指定为10
//   	   	   			retDto = crossEquipmentServiceImpl.operCrossController(obj, 10);
//   	   	   			reStr = retDto.getAsInteger("code");
				}else if(obj.getString("f_node_attribution").equals("2")){
					Map<String, Object> ddcinfo = new HashMap<>();
					///查询模块的父节点属性
					String pNodeType = besSbdyMapper.querySbpzStructInfo(obj.getString("f_psys_name"));
					if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型(智能照明系统ip路由器下可以新增干路、支路、模块等，此处需要判断该模块的位置)tjw331
						/*ddcinfo = crossEquipmentMapper.queryLampDDCInfoByCoupler(obj.getString("f_psys_name"));*/
						ddcinfo = crossEquipmentMapper.queryIpInfo(obj.getString("f_psys_name"));
					}else if(pNodeType.equals("3"))	{
						//查询逻辑点所属IP路由器的IP地址
						ddcinfo = crossEquipmentMapper.queryLampDDCInfoByModule(obj.getString("f_psys_name"));
					}
					//获取sbid
					obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);
					//Ip,端口号
					obj.put("IPAddr", ddcinfo.get("F_IP_ADDR"));
					obj.put("Port", ddcinfo.get("F_PORT"));
					//获取干线耦合器状态下的通信地址
					Map<String, Object> map = ModuleF_ADDR(obj.getString("f_psys_name"));
					obj.put("branchCoupler_F_ADDR",map.get("branchCoupler_F_ADDR"));
					obj.put("trunkCoupler_F_ADDR", map.get("trunkCoupler_F_ADDR"));
					//Lamp向下位机发送模块属性信息，返回通讯结果,index指定为10
//   	   	   			retDto = lampEquipmentServiceImpl.operLampController(obj, 10);
//   	   	   			reStr = retDto.getAsInteger("code");
				}
				//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
				if (reStr != 0) {
					obj.put("attr_f_module_state", new String("1"));
					obj.put("attr_f_online_state", new String("1"));
					str = "数据库中添加成功，下位机同步失败";
				}
			}else if(obj.getString("attrTabName") .equals("BES_VPOINT")) {
				//查询逻辑点所属DDC控制器
				Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint((String) obj.get("f_psys_name"));


				Map<String, Object> imPoitntInfo = crossEquipmentMapper.queryimPoitntByDDCinfo((String) obj.get("f_psys_name"));
				if(imPoitntInfo.get("F_SBID") != null){//判断子虚点的个数，，防止sbid冲突
					obj.put("f_sbid", Integer.parseInt((imPoitntInfo.get("F_SBID").toString()))+1);
				}else{
					//获取sbid
					obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);
				}

				//获取sbid
				//obj.put("f_sbid", getSbid((String)imPoitntInfo.get("F_SBID")) + 1);
				obj.put("DDCIPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
				obj.put("DDCPort", ddcinfo.get("F_PORT"));//端口号
				if(obj.getString("f_node_attribution").equals("1")) {
					//向Cross下位机发送逻辑虚点属性信息，返回通讯结果,index指定为15
//			   			retDto = crossEquipmentServiceImpl.operCrossController(obj, 15);
//			   			reStr = retDto.getAsInteger("code");
				}else {
					//向Lamp下位机发送逻辑虚点属性信息，返回通讯结果,index指定为15
//			   			retDto = lampEquipmentServiceImpl.operLampController(obj, 15);
//			   			reStr = retDto.getAsInteger("code");
				}
				//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
				if (reStr != 0) {
					//   				obj.put("attr_f_module_state", new String("1"));
					//   				obj.put("attr_f_online_state", new String("1"));
					str = "数据库中添加成功，下位机同步失败";
				}

			}else if(obj.getString("attrTabName") .equals("BES_COUPLER")) {//2020/3/31 ip路由器下新增支路  tianjiangwei
				/*Map<String, Object> IPinfo = new HashMap<String, Object>();
				if("1".equals(obj.getString("attr_f_type"))){//支线
					//查询逻辑点所属DDC控制器
					IPinfo = crossEquipmentMapper.queryDDCInfoByVPoint((String) obj.get("f_psys_name"));
				}else if("0".equals(obj.getString("attr_f_type"))){//干线
					//查询逻辑点所属ip路由器信息
					IPinfo = crossEquipmentMapper.queryIpInfoByGX((String) obj.get("f_psys_name"));

				}*/
				Map<String, Object> IPinfo = crossEquipmentMapper.queryIpInfo((String) obj.get("f_psys_name"));
				//获取sbid
				obj.put("f_sbid", getSbid((String)IPinfo.get("F_SYS_NAME")) + 1);
				//Ip,端口号
				obj.put("IPAddr", IPinfo.get("F_IP_ADDR"));
				obj.put("Port", IPinfo.get("F_PORT"));

			}
			if (obj.getString("old_f_sys_name").equals("")){
				obj.put("old_f_sys_name",obj.get("f_sys_name"));
				// BES_AMMETER
				addAttrTabInfoCount = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
			}else {
				addAttrTabInfoCount = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
			}


			if(obj.getString("attrTabName").equals("BES_VPOINT")&&"4".equals(obj.getString("attr_f_node_type"))&&"1".equals(String.valueOf(addAttrTabInfoCount))&&"0".equals(obj.getString("f_energyStatics"))){//虚点能耗
				obj.put("attr_f_sblxbh",obj.getString("attr_f_node_type"));//设备类型编号  虚点能耗的为8
				obj.put("attr_f_alarm_enable","");//报警使能状态
				obj.put("attr_f_alarm_type","");//报警类型
				obj.put("attr_f_init_val","");//初始值
				obj.put("attr_f_accuracy","");//精度
				obj.put("attr_f_high_limit","");//高限值
				obj.put("attr_f_close_state","");//闭合状态
				obj.put("attr_f_node_type","");//节点类型
				obj.put("attr_f_alarm_priority","");//报警优先级
				obj.put("attr_f_engineer_unit","");//单位
				obj.put("attr_f_low_limit","");//低限值
				obj.put("f_energyStatics","");//能耗采集
				obj.put("f_staticsTime","");//采集时间
				obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
				obj.put("attrTabName", "BES_AMMETER");
				//向下位机发送电表属性信息，返回通讯结果,index指定为11
			/*	retDto = enerEquipmentServiceImpl.operEnergyController(obj, "15");//虚点index为15
				str = retDto.getAsString("errmsg");
				//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
				if (!str.equals(LEMSConstants.Service_Success)) {
					obj.put("attr_f_ammeter_state", new String("1"));
					str = str + LEMSConstants.SaveAmmeterInfo;
					obj.put("attr_f_online_state", new String("1"));
				}*/
				addAttrTabInfoCount = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
			}
		}
		// BES_SBPZ_STRUCT
		addSbdyCount = besSbdyMapper.addSbTreeStructInfo(obj);//将属性信息存入[设备树结构表]
		if (obj.getString("other_node_types") != null && !"".equals(obj.getString("other_node_types"))) {
			addDefaultNodeCount = addDefaultNodes(obj);
		}
		if (addSbdyCount + addAttrTabInfoCount + addDefaultNodeCount >0 && str.equals(LEMSConstants.Service_Success)) {
			returnObject.setStatus("1");
			returnObject.setMsg(str);
		} else if(addSbdyCount + addAttrTabInfoCount + addDefaultNodeCount >0 && !str.equals(LEMSConstants.Service_Success)){
			returnObject.setStatus("1");
			returnObject.setMsg(str);
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("添加失败");
		}
		return returnObject;
	}
	private void getChildNodeList(String f_sys_name, List<BESSbPzStruct> allChildNodeList) {
		//List<BESSbPzStruct> childNodeList = besSbdyMapper.getSbChildNodeBySysName(f_sys_name);
		allChildNodeList= besSbdyMapper.getSbChildNodeBySysName(f_sys_name);
		//getSbChildNodeBySysName(f_sys_name);
		/*for(BESSbPzStruct childNode : childNodeList) {
			allChildNodeList.add(childNode);
			//getChildNodeList(childNode.getF_sys_name(),allChildNodeList);

		}*/
	}


	private int getSbid(String f_sys_name) {
		//List<BESSbPzStruct> allChildNodeList = new ArrayList<>();
		////获取所有子节点
		//getChildNodeList(f_sys_name,allChildNodeList);
		////取最大sbid
		int maxSbid = 1;
		int count = besSbdyMapper.getSumSbCount(f_sys_name);
		if(count>0){
			maxSbid = count;
		}
		//for(int i=1;i<allChildNodeList.size();i++) {
		//	if(allChildNodeList.get(i).getF_sbid() != "" && allChildNodeList.get(i).getF_sbid() != null
		//			&& Integer.parseInt(allChildNodeList.get(i).getF_sbid()) > maxSbid) {
		//		maxSbid = Integer.parseInt(allChildNodeList.get(i).getF_sbid());
		//	}
		//}
		return maxSbid;
	}

	/*Start delete by xiepufeng at 2020年5月9日  for reason 上位机与下位机通信升级*/
	/**
	 * 新增采集器
	 *//*
	@Override
	@Transactional
	public ISSPReturnObject addCollector(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		*//*
		 *
		 * @Description: 验证电表ip是否重复
		 *
		 * @auther: wanghongjie
		 * @date: 17:04 2020/3/13
		 *
		 *//*
		if (obj.get("attr_f_ip_addr")!=null){
			String ipTxt=obj.get("attr_f_ip_addr").toString();
			String tabName=obj.get("attrTabName").toString();
			if(obj.getString("attr_f_ip_addr") !=null && !("".equals(obj.getString("attr_f_ip_addr")))) {
				List<BESVPointType>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ipTxt,tabName);
				int qqq=besSbPzStruct.size();
				if (0!=besSbPzStruct.size()) {
					returnObject.setStatus("3");
					return returnObject;
				}
			}
		}
		int addSbdyCount = 0;
		int addAttrTabInfoCount = 0;
		if(obj.getString("tabName") !=null && !("".equals(obj.getString("tabName")))&&!("".equals(obj.getString("f_sys_name")))) {
			//没有所属系统标识，查询父节点系统标识
			if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
				Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_psys_name"));
				obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
			}
			//查询采集器中的最大id
			String maxId = besSbdyMapper.queryCollectorMaxId();
			String f_id = getAutoIncreaseCol(maxId);
			obj.put("f_sbid", f_id);
			addSbdyCount = besSbdyMapper.addSbTreeStructInfo(obj);//将属性信息存入[设备树结构表]
		}
		obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());
		Dto retDto = null;
		try {
			//向下位机发送采集器属性信息，返回通讯结果
			retDto = enerEquipmentServiceImpl.operEnergyController(obj, "0");
		} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
			e.printStackTrace();
		}
		String str = retDto.getAsString("errmsg");
		obj.put("attr_f_collector_state", new String("0"));//采集器同步状态
		obj.put("attr_f_online_state", new String("0"));//采集器在线状态
		//判断向下位机发送信息不成功，则设置‘未同步’‘不在线’
		if (!str.equals(LEMSConstants.Service_Success)) {
			obj.put("attr_f_collector_state", new String("1"));
			str = str + LEMSConstants.SaveCollectorInfo;
			obj.put("attr_f_online_state", new String("1"));
		}
		//数据库中插入
		if(obj.getString("attrTabName") !=null && !("".equals(obj.getString("attrTabName")))&&!("".equals(obj.getString("f_sys_name"))))
			if (obj.getString("old_f_sys_name").equals("")){
				obj.put("old_f_sys_name",obj.get("f_sys_name"));
				addAttrTabInfoCount = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
			}else {
				addAttrTabInfoCount = besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息
			}

		if (obj.getString("attr_f_loop_time") != null && !"".equals(obj.getString("attr_f_loop_time"))) {
			besSbdyMapper.addCollectorIsspTimerListInfo(obj.getString("attr_f_guid"), obj.getString("attr_f_loop_time"));
			besSbdyMapper.addCollectorSjcjTaskInfo(obj.getString("attr_f_guid"));
		}


		if (addSbdyCount + addAttrTabInfoCount >0 && str.equals(LEMSConstants.Service_Success)) {
			returnObject.setStatus("1");
			returnObject.setMsg(str);
		} else if(addSbdyCount + addAttrTabInfoCount >0 && !str.equals(LEMSConstants.Service_Success)){
			returnObject.setStatus("1");
			returnObject.setMsg(str);
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("保存失败");
		}
		return returnObject;
	}*/
	/*End delete by xiepufeng at 2020年5月9日  for reason 上位机与下位机通信升级*/

	/*Start replace by xiepufeng at 2020年5月9日  for reason 上位机与下位机通信升级*/
	/**
	 * 新增采集器
	 */
	@Override
	@Transactional
	public ISSPReturnObject addCollector(JSONObject obj) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		String tabName = obj.getString("attrTabName"); // 表名
		String tableName = obj.getString("tabName"); // 表名
		String pSysName = obj.getString("f_psys_name"); // 表名

		if (!StringUtils.hasText(tabName)
				|| !StringUtils.hasText(tableName)
				|| !StringUtils.hasText(pSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		String ip = obj.getString("attr_f_ip_addr"); // ip 地址
		String name = obj.getString("attr_f_sys_name"); // 系统名称
		String alias =  obj.getString("attr_f_nick_name"); // 别名
		String description = obj.getString("attr_f_description"); // 描述
		String location = obj.getString("attr_f_azwz"); // 安装位置
		String zone = obj.getString("attr_f_ssqy"); // 安装位置
		Integer active = obj.getInteger("attr_f_enabled"); // 使能状态
		Integer samplePeriod = obj.getInteger("attr_f_coll_cycle"); // 采样周期 单位为分钟
		Integer hisDataSavePeriod= obj.getInteger("attr_f_his_data_save_cycle"); // 上传数据采样周期 单位为分钟
		Integer upDataSamplePeriod = obj.getInteger("attr_f_loop_time"); // 历史数据保存周期，单位为小时

		if (null == ip
				|| !StringUtils.hasText(name)
				|| !StringUtils.hasText(alias)
				|| !StringUtils.hasText(description)
				|| !StringUtils.hasText(location)
				|| !StringUtils.hasText(zone)
				|| null == active
				|| null == samplePeriod
				|| null == upDataSamplePeriod
				|| null == hisDataSavePeriod
				)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		if (null != besSbdyMapper.getSbTreeDataBySysName(name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("系统名称不能重复");
			return returnObject;
		}

		// 查询ip地址是否已经存在
		List<BesDdc>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ip, tabName);

		if (besSbPzStruct.size() > 0)
		{
			returnObject.setStatus("3");
			returnObject.setMsg("ip 地址重复");
			return returnObject;
		}

		// 生成设备id
		String maxId = besSbdyMapper.queryCollectorMaxId();
		String id = getAutoIncreaseCol(maxId);
		obj.put("f_sbid", id);

		String nodeAttribution = obj.getString("f_node_attribution");

		if (!StringUtils.hasText(nodeAttribution))
		{
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(pSysName);
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));// 所属系统
		}

		//查询父节点的父节点名称,将园区的编号插入到采集器节点中
		String yqbh =  besSbdyMapper.selectYqbhByFsysName(obj.get("f_psys_name"));

		obj.put("f_yqbh", yqbh);// 所属园区
		obj.put("f_status", "0");// 设置设备树为不在线

		besSbdyMapper.addSbTreeStructInfo(obj);//将属性信息存入[设备树结构表]

		obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());

		obj.put("old_f_sys_name", name);

		// 设置采集器未同步
		obj.put("attr_f_collector_state", "0");

		// 设置采集器不在线
		obj.put("attr_f_online_state", "0");

		// 添加通道id
        obj.put("f_channel_id", ip);

		// "BES_COLLECTOR"
		besSbdyMapper.addSbTreeAttrInfo(obj);//根据系统名称添加其他表(属性表)属性信息

		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/


		/*采集器添加到缓存 start*/
		BesCollector besCollector = besCollectorMapper.selectBySysName(obj.getString("f_sys_name"));

		if(besCollector != null)
		{
			collectorCache.addOneCollectorCache(besCollector);
		}
		/*采集器添加到缓存 end*/

		ControllerDataEDC controllerData = new ControllerDataEDC();

		controllerData.setId(Integer.parseInt(id));
		controllerData.setName(name);
		controllerData.setAlias(alias);
		controllerData.setDescription(description);
		controllerData.setLocation(location);
		controllerData.setZone(zone);
		controllerData.setActive(active);
		controllerData.setSamplePeriod(samplePeriod);
		controllerData.setUpDataSamplePeriod(upDataSamplePeriod);
		controllerData.setHisDataSavePeriod(hisDataSavePeriod);

		// 同步数据到下位机
		boolean sendState = SendMsgHandler.addControllerEDC(ip, controllerData);

		if (!sendState)
		{
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功，下发失败");

			return returnObject;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_ADD, ip);
		returnObject.setStatus("1");
		returnObject.setMsg("保存成功，下发成功");

		return returnObject;
	}
	/*End replace by xiepufeng at 2020年5月9日  for reason 上位机与下位机通信升级*/

	/**
	 * 判断是否可删除
	 */
	public ISSPReturnObject delEnable(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();
		//电表
		if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_Ammeter)) {
			int rlglNum = delEnable_ammeter(obj.getString("f_sys_name"));
			if(rlglNum>0) {
				returnObject.setStatus("0");
				returnObject.setMsg(LEMSConstants.meter_branch);
			}else {
				returnObject.setStatus("1");
			}
			//虚点能耗电表
		}else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_VpointEne)) {//add by tianjiangwei 2020/4/16判断虚点虚点能耗配置节点是否可以删除
			String nodeType = findNodeTypeBySysName(obj.getString("f_sys_name"));
			if("4".equals(nodeType)){
				int rlglNum = delEnable_ammeter(obj.getString("f_sys_name"));
				if(rlglNum>0) {
					returnObject.setStatus("0");
					returnObject.setMsg(LEMSConstants.meter_branch);
				}else {
					returnObject.setStatus("1");
				}
			}else{
				returnObject.setStatus("1");
			}
			//ai点能耗
		}else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_AI)) {//add by tianjiangwei 2020/4/16判断虚点虚点能耗配置节点是否可以删除
				int rlglNum = delEnable_ammeter(obj.getString("f_sys_name"));
				if(rlglNum>0) {
					returnObject.setStatus("0");
					returnObject.setMsg(LEMSConstants.meter_branch);
				} else{
				returnObject.setStatus("1");
			}
			//采集器  +ddc    add by tianjiangwei 2020/4/16判断虚点虚点能耗配置节点是否可以删除
		} else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_Collector)) {
			//查询采集器（ddc）下所有电表
			List<Map<String,Object>> ammeters= besJobManagerMapper.queryAmmeter(obj.getString("f_sys_name"));
			if(ammeters.size() > 0) {
				//遍历，只要有一个电表与支路有关联，就不可删除
				for (Map<String, Object> ammeter : ammeters) {
					int rlglNum = delEnable_ammeter((String) ammeter.get("uuid"));
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelCollectorMsg);
						break;
					}else {
						returnObject.setStatus("1");
					}
				}

			} else {
				returnObject.setStatus("1");
			}
			//区域下
		}else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_DDC))//DDC控制器
		{
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));

			List<Map<String,Object>> list = new ArrayList<>();

			if (besSbPzStructs.size() > 0)
			{
				//遍历，查询所有的AI点
				for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
				{

					if (besSbPzStruct.getF_type().equals("10"))
					{
						Map<String, Object> pointMap = new HashMap<>();
						String name = besSbPzStruct.getF_sys_name_old();
						pointMap.put("F_SYS_NAME_OLD", name);
						list.add(pointMap);
					}
				}

				for (int i = 0; i < list.size(); i++)
				{
					String f_sys_name = (String) list.get(i).get("F_SYS_NAME_OLD");
					int rlglNum = delEnable_ammeter(f_sys_name);
					if (rlglNum > 0)
					{
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelAreaMsg);
						return returnObject;
					}
				}

			}

			returnObject.setStatus("1");
			return returnObject;

		}else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_IPRouter) ||//照明DDC控制器
				obj.getString("fNodeType").equals(LEMSConstants.NodeType_Ldc) || //照明控制节点
				obj.getString("fNodeType").equals(LEMSConstants.NodeType_LdcArea) || //照明区域节点
				obj.getString("fNodeType").equals(LEMSConstants.NodeType_TrunkCoupler) || //干线耦合器节点
				obj.getString("fNodeType").equals(LEMSConstants.NodeType_BranchCoupler)) //支线耦合器节点
		{
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));

			List<Map<String,Object>> list = new ArrayList<>();

			if (besSbPzStructs.size() > 0)
			{
				//遍历，查询所有的AI点
				for (BESSbPzStruct besSbPzStruct : besSbPzStructs)
				{

					if (besSbPzStruct.getF_type().equals("10"))
					{
						Map<String, Object> pointMap = new HashMap<>();
						String name = besSbPzStruct.getF_sys_name_old();
						pointMap.put("F_SYS_NAME_OLD", name);
						list.add(pointMap);
					}
				}

				for (int i = 0; i < list.size(); i++)
				{
					String f_sys_name = (String) list.get(i).get("F_SYS_NAME_OLD");
					int rlglNum = delEnable_ammeter(f_sys_name);
					if (rlglNum > 0)
					{
						returnObject.setStatus("0");
						if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_IPRouter)) {//照明IP路由器节点
							returnObject.setMsg(LEMSConstants.DelIPRouterMsg);
						}
						else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_Ldc)) {//照明控制节点
							returnObject.setMsg(LEMSConstants.DelLdcMsg);
						}
						else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_LdcArea)) {//照明区域节点
							returnObject.setMsg(LEMSConstants.DelLdcAreaMsg);
						}
						else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_TrunkCoupler)) {//干线耦合器节点
							returnObject.setMsg(LEMSConstants.DelTrunkCouplerMsg);
						}
						else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_BranchCoupler)) {//支线耦合器节点
							returnObject.setMsg(LEMSConstants.DelBranchCouplerMsg);
						}
						return returnObject;
					}
				}

			}

			returnObject.setStatus("1");
			return returnObject;

		} else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_sys))
		{//能耗采集节点
			//查询区域下所有电表
			List<Map<String,Object>> ammeters= besJobManagerMapper.queryReginAmmeter(obj.getString("f_sys_name"));
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
//			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));
			List<Map<String,Object>> list = new ArrayList<>();


			if(ammeters.size() > 0) {
				//遍历，只要有一个电表与支路有关联，就不可删除
				for (Map<String, Object> ammeter : ammeters) {
					int rlglNum = delEnable_ammeter((String) ammeter.get("uuid"));
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelCollectorMsg);
						return returnObject;
					}
				}
			}

			returnObject.setStatus("1");
			return returnObject;

		}else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_Region)) {//区域节点
			//查询区域下所有电表
			List<Map<String,Object>> ammeters= besJobManagerMapper.queryReginAmmeter(obj.getString("f_sys_name"));
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));
			List<Map<String,Object>> list = new ArrayList<>();


			if(ammeters.size() > 0) {
				//遍历，只要有一个电表与支路有关联，就不可删除
				for (Map<String, Object> ammeter : ammeters) {
					int rlglNum = delEnable_ammeter((String) ammeter.get("uuid"));
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelCollectorMsg);
						return returnObject;
					}
				}
			}else if(besSbPzStructs.size() > 0){
				//遍历，查询所有的AI点
				for (BESSbPzStruct besSbPzStruct : besSbPzStructs) {

					if (besSbPzStruct.getF_type().equals("10")){
						Map<String,Object> pointMap = new HashMap<>();
						String name = besSbPzStruct.getF_sys_name_old();
						pointMap.put("F_SYS_NAME_OLD",name);
						list.add(pointMap);
					}
				}

				for (int i = 0;i < list.size(); i++){
					String f_sys_name = (String) list.get(i).get("F_SYS_NAME_OLD");
					int rlglNum = delEnable_ammeter(f_sys_name);
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelAreaMsg);
						return returnObject;
					}
				}
			}

			returnObject.setStatus("1");
			return returnObject;

			//总线
		}else if(obj.getString("fNodeType").equals(LEMSConstants.NodeType_CollectorBus)) {
			//查询总线下所有电表
			List<Map<String,Object>> ammeters= besJobManagerMapper.queryAmmeterFromBus(obj.getString("f_sys_name"));
			if(ammeters.size() > 0) {
				for (Map<String, Object> ammeter : ammeters) {
					int rlglNum = delEnable_ammeter((String) ammeter.get("F_SYS_NAME"));
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelBusMsg);
						return returnObject;
					}
				}
			}

			returnObject.setStatus("1");
			return returnObject;

		}else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_Line)){//线路节点
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));
			List<Map<String,Object>> list = new ArrayList<>();
			if(besSbPzStructs.size() > 0){
				//遍历，查询所有的AI点
				for (BESSbPzStruct besSbPzStruct : besSbPzStructs) {

					if (besSbPzStruct.getF_type().equals("10")){
						Map<String,Object> pointMap = new HashMap<>();
						String name = besSbPzStruct.getF_sys_name_old();
						pointMap.put("F_SYS_NAME_OLD",name);
						list.add(pointMap);
					}
				}

				for (int i = 0;i < list.size(); i++){
					String f_sys_name = (String) list.get(i).get("F_SYS_NAME_OLD");
					int rlglNum = delEnable_ammeter(f_sys_name);
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelLineMsg);
						return returnObject;
					}
				}
			}

			returnObject.setStatus("1");
			return returnObject;

		}else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_Module)){//模块节点
//			List<BESSbPzStruct> besSbPzStructs = besSbdyMapper.searchChildren(obj.getString("f_sys_name"));
			List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(obj.getString("f_sys_name"));
			List<Map<String,Object>> list = new ArrayList<>();
			if(besSbPzStructs.size() > 0){
				//遍历，查询所有的AI点
				for (BESSbPzStruct besSbPzStruct : besSbPzStructs) {

					if (besSbPzStruct.getF_type().equals("10")){
						Map<String,Object> pointMap = new HashMap<>();
						String name = besSbPzStruct.getF_sys_name_old();
						pointMap.put("F_SYS_NAME_OLD",name);
						list.add(pointMap);
					}
				}

				for (int i = 0;i < list.size(); i++){
					String f_sys_name = (String) list.get(i).get("F_SYS_NAME_OLD");
					int rlglNum = delEnable_ammeter(f_sys_name);
					if(rlglNum>0) {
						returnObject.setStatus("0");
						returnObject.setMsg(LEMSConstants.DelModuleMsg);
						return returnObject;
					}
				}
			}

			returnObject.setStatus("1");
			return returnObject;
		}else if (obj.getString("fNodeType").equals(LEMSConstants.NodeType_ROOT)) {//根节点
			//查询父节点为当前根节点的节点信息
			List<BESSbPzStruct> pointType = besSbdyMapper.selectPointType(obj.getString("f_sys_name"));
			if (pointType.size() > 0) {
				for (BESSbPzStruct besSbPzStruct : pointType) {

					object.put("fNodeType",besSbPzStruct.getF_type());
					object.put("f_sys_name",besSbPzStruct.getF_sys_name());
					returnObject = delEnable(object);
					if (returnObject.getStatus().equals("0")) {//删除失败
						return returnObject;
					}
				}
			}
			returnObject.setStatus("1");
			return returnObject;
		}
		else {

			returnObject.setStatus("1");
			return returnObject;
		}
		return returnObject;
	}
	/**
	 * 查询电表是否与支路关联
	 * @param f_sys_name
	 * @return
	 */
	private int delEnable_ammeter(String f_sys_name) {
		int rlglNum = besSbdyMapper.queryBranchAmmeterRlgl(f_sys_name);
		return rlglNum;
	}
	/**
	 * 删除下位机设备信息
	 * @param obj
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ISSPReturnObject delUnderInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int code = 1;//code：0-删除成功；否则删除失败
		// 查询该节点的实体表名--add by LvSihan
		String tabName = besSbdyMapper.findNodeTable(obj.getString("f_sys_name"));
		// 查询该节点详细属性信息(用于和下位机通讯)--add by LvSihan
		Map<String, Object> ddcinfo = new HashMap<>();
		Map<String, Object> nodeInfo = new HashMap<>();
		Map<String, Object> retMap = new HashMap<>();
		if (tabName != null && tabName.equals("BES_DDC")) {
			nodeInfo = besSbdyMapper.findSbdyInfoBySelNode(obj.getString("f_sys_name"), tabName);
			if (obj.getString("f_node_attribution").equals("1")) {
				// 与Cross下位机通讯，删除DDC控制器，并删除和它相关的模块和点，index指定为5--add by LvSihan
//						retMap = crossEquipmentService.operDDCController((String)nodeInfo.get("F_IP_ADDR") ,(String) nodeInfo.get("F_PORT"), 5);
				retMap = crossEquipmentServiceImpl.operCrossController(nodeInfo, 5);
				code = (int) retMap.get("code");
			} else if (obj.getString("f_node_attribution").equals("2")) {
				// 与Lamp下位机通讯，删除DDC控制器，并删除和它相关的模块和点，index指定为5
				retMap = lampEquipmentServiceImpl.operLampController(nodeInfo, 5);
				code = (int) retMap.get("code");
			} else {
				code = 1;
			}
			if (code == 0) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
			return returnObject;
		} else if (tabName != null && tabName.equals("BES_MODULE")) {
			if (obj.getString("f_node_attribution").equals("1")) {
				// 查询模块所属DDC控制器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByModule((String) obj.get("f_psys_name"));
				nodeInfo = besSbdyMapper.findSbdyInfoBySelNode(obj.getString("f_sys_name"), tabName);
				// Cross与下位机通讯，删除模块，并删除和它相关的点，index指定为12--add by LvSihan
				Map<String, Object> pMap = new HashMap<>();
				Map<String, Object> data = new HashMap<>();
				pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));// ip地址
				pMap.put("Port", ddcinfo.get("F_PORT"));// 端口号
				data.put("ID", nodeInfo.get("F_SBID"));//
				pMap.put("data", data);
				retMap = crossEquipmentServiceImpl.operCrossController(pMap, 12);// 删除一个模块，并删除和模块相关的点，index设置为12
				code = (int) retMap.get("code");
			} else if (obj.getString("f_node_attribution").equals("2")) {
				// 查询模块的父节点属性
				String pNodeType = besSbdyMapper.querySbpzStructInfo((String) obj.get("f_psys_name"));
				if (pNodeType.equals("6")) {// "6"为支路耦合器节点类型
					ddcinfo = crossEquipmentMapper.queryLampDDCInfoByCoupler((String) obj.get("f_psys_name"));
				} else if (pNodeType.equals("3")) {
					// 查询逻辑点所属IP路由器的IP地址
					ddcinfo = crossEquipmentMapper.queryLampDDCInfoByModule((String) obj.get("f_psys_name"));
				}
				nodeInfo = besSbdyMapper.findSbdyInfoBySelNode(obj.getString("f_sys_name"), tabName);
				// Lamp与下位机通讯，删除模块，并删除和它相关的点，index指定为12--add by LvSihan
				Map<String, Object> pMap = new HashMap<>();
				Map<String, Object> data = new HashMap<>();
				pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));// ip地址
				pMap.put("Port", ddcinfo.get("F_PORT"));// 端口号
				data.put("ID", nodeInfo.get("F_SBID"));//
				pMap.put("data", data);
				retMap = lampEquipmentServiceImpl.operLampController(pMap, 12);// 删除一个模块，并删除和模块相关的点，index设置为12
				code = (int) retMap.get("code");
			} else {
				code = 0;
			}

			if (code == 0) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
			return returnObject;
		} else if (tabName != null && (tabName.equals("BES_ANALOG_INPUT") || tabName.equals("BES_ANALOG_OUPUT")
				|| tabName.equals("BES_DIGIT_INPUT") || tabName.equals("BES_DIGIT_OUPUT")
				|| tabName.equals("BES_VPOINT"))) {
			if(tabName.equals("BES_VPOINT")) {
				// 查询逻辑虚点所在控制器的信息
				ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint((String) obj.get("f_psys_name"));
			}else {
				// 查询逻辑点所属DDC控制器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint((String) obj.get("f_sys_name"));
			}
			Map<String, Object> pMap = new HashMap<>();
			Map<String, Object> data = new HashMap<>();
			pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));// ip地址
			pMap.put("Port", ddcinfo.get("F_PORT"));// 端口号
			data.put("ID", nodeInfo.get("F_SBID"));//
			pMap.put("data", data);
			Dto retDto = crossEquipmentServiceImpl.operCrossController(pMap, 19);// 删除逻辑点，index设置为19
			code = retDto.getAsInteger("code");
			if (code == 0) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
			return returnObject;
		} else if (tabName != null && tabName.equals("BES_COLLECTOR")) {
			// 通过f_sys_name查询该collector属性信息
			nodeInfo = besSbdyMapper.findSbdyInfoBySelNode(obj.getString("f_sys_name"), tabName);
			retMap = enerEquipmentServiceImpl.operEnergyController(nodeInfo, "9");
			String errmsg = (String) retMap.get("errmsg");
			if (errmsg != null && errmsg.equals(LEMSConstants.Service_Success)) {
				returnObject.setStatus("1");
				returnObject.setMsg(errmsg);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg(errmsg);
			}
		} else if (tabName != null && tabName.equals("BES_AMMETER")) {
			// 查询电表所在控制器的信息
			ddcinfo = besJobManagerMapper.querycollectorInfoByAmmeter((String) obj.get("f_psys_name"));
			// 通过f_sys_name查询该电表属性信息
			nodeInfo = besSbdyMapper.findSbdyInfoBySelNode(obj.getString("f_sys_name"), tabName);
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));// ip地址
			pMap.put("Port", ddcinfo.get("F_PORT"));// 端口号
			pMap.put("MeterID", nodeInfo.get("F_SYS_NAME"));// 电表id
			retMap = enerEquipmentServiceImpl.operEnergyController(pMap, "13");
			String errmsg = (String) retMap.get("errmsg");
			if (errmsg.equals(LEMSConstants.Service_Success)) {
				returnObject.setStatus("1");
				returnObject.setMsg(errmsg);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg(errmsg);
			}
		}else {
			returnObject.setStatus("1");
		}
		return returnObject;
	}
	/**
	 * 删除设备树信息
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public ISSPReturnObject delSbTreeBySelNode(JSONObject obj) {


	    ISSPReturnObject returnObject = new ISSPReturnObject();

		String sysName = obj.getString("f_sys_name");

		if (!StringUtils.hasText(sysName))
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

		String tabName = besSbdyMapper.findNodeTable(sysName);

        Map<String, Object> collector = null;
        Map<String, Object> ddc = null;
		Map<String, Object> module = null;
		Map<String, Object> DDC_module = null;
		if ("BES_COLLECTOR".equals(tabName))
        {
            collector = besCollectorMapper.queryCollectorInfo(sysName);
        }

		if ("BES_BUS".equals(tabName))
        {
        }
		if ("BES_DDC".equals(tabName)){
			ddc = besCollectorMapper.queryDDCInfo(sysName);
		}
		if ("BES_MODULE".equals(tabName)){
			module = besCollectorMapper.queryModuleInfo(sysName);
			DDC_module = besCollectorMapper.queryDDCModuleInfo(sysName);
		}

		Map<String, Object> ammeterData = null;
		Map<String, Object> collectorData = null;

		if ("BES_AMMETER".equals(tabName))
		{
			collectorData = besJobManagerMapper.queryCollectorByAmmeterSysName(sysName);
			ammeterData = besJobManagerMapper.queryAmmeterInfo(sysName);

		}

		Map<String, String> tabNameMap = new HashMap<>();//(key:节点类型  value：表名)
		List<BESSbTreeNodeType> nodeTypeInfoList = besSbdyMapper.findChildByTreeNoteType(null);//从数据库获取所有资源
		for (BESSbTreeNodeType besSbTreeNodeType : nodeTypeInfoList)// 将所有节点类型对应的表名放入map
			tabNameMap.put(besSbTreeNodeType.getF_node_type(), besSbTreeNodeType.getF_node_table());
		Map<String, List<String>> idsMap=new HashMap<>();//(key:节点类型  value：List<系统名称>)
		List<String> f_sys_names=new ArrayList<>();//要删除的所有节点id(f_sys_name)
		List<String> f_vpointEnes=new ArrayList<>();//要删除的所有虚点节点id(f_sys_name)
		getChildNode(sysName, f_sys_names, idsMap,f_vpointEnes);//获取节点下所有子节点id(f_sys_name)
		int childCount = 0;
		int childCounts = 0;
		int vpointCounts = 0;
		if (f_sys_names.size() > 0) {// 如果含有子节点

			for (Entry<String, List<String>> nodeType : idsMap.entrySet()) { // 根据子节点包含的表名循环删除对应属性信息（多个节点时）
				String delTabName = tabNameMap.get(nodeType.getKey());
				if (delTabName != null && !("".equals(delTabName)) && !("bes_sbpz_struct".equals(delTabName.toLowerCase())))
				{
					List<String> fSysNames =idsMap.get(nodeType.getKey());

					if ("BES_AMMETER".equals(delTabName) && null != fSysNames)
					{
						Map<String, Object> collectorData1 = besJobManagerMapper.queryCollectorByAmmeterSysName(fSysNames.get(0));

						if (null != collectorData1)
						{
							String channelId1 = (String) collectorData1.get("F_CHANNEL_ID");

							fSysNames.forEach((fSysName) -> {
								Map<String, Object> ammeterData1 = besJobManagerMapper.queryAmmeterInfo(fSysName);

								Integer meterID1 = Integer.parseInt((String) ammeterData1.get("F_SBID"));

								// 删除电表
								if (SendMsgHandler.deleteAmmeterEDC(channelId1, meterID1))
								{
									// 添加订阅消息
									MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_DELETE, channelId1);
								}


							});
						}

					}else if ("BES_DDC".equals(delTabName) && null != fSysNames){//表名为BES_DDC  DDC控制器表的时候
						if (fSysNames.size() > 0){//如果有一条或者多条DDC控制器的信息的时候
							for (int i = 0 ; i < fSysNames.size(); i++ ) {//遍历一条或者多条DDC控制器
								Map<String, Object> DDCData = besJobManagerMapper.queryDDCBySysName(fSysNames.get(i));
								if (DDCData != null) {
									String	channelId = (String) DDCData.get("F_CHANNEL_ID");
									Integer meterID1 = Integer.parseInt((String) DDCData.get("F_SBID"));
									// 删除DDC控制器以及下面的点位
									if (SendMsgHandler.deleteAmmeterEDC(channelId, meterID1))
									{
										// 添加订阅消息
										MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_DELETE, channelId);
									}
								}

							}

						}

					}else if ("BES_MODULE".equals(delTabName) && null != fSysNames){//表名为BES_MODULE  模块表的时候
						if (fSysNames.size() > 0){//如果有一条或者多条模块的信息的时候
							for (int i = 0 ; i < fSysNames.size(); i++ ) {
								//遍历一条或者多条模块
								String	channelId = besJobManagerMapper.querychannelIdByModule(fSysNames.get(0));
								Map<String, Object> ModuleData = besJobManagerMapper.queryModuleBySysName(fSysNames.get(i));
								if (ModuleData != null) {
									Integer meterID1 = Integer.parseInt((String) ModuleData.get("F_SBID"));
									// 删除模块以及下面的点位
									if (SendMsgHandler.deleteModuleDDC(channelId, meterID1))
									{
										// 添加订阅消息
										MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_DELETE, channelId);
									}
								}

							}

						}

					}
					/*else if("BES_ANALOG_OUPUT".equals(delTabName) || "BES_ANALOG_INPUT".equals(delTabName)
							|| "BES_DIGIT_INPUT".equals(delTabName) || "BES_DIGIT_OUPUT".equals(delTabName) && null != fSysNames){//删除AO,AI,AO,AI点
						Map<String, Object> DDCData = besJobManagerMapper.queryDDCByPointSysName(fSysNames.get(0));
						if (DDCData != null){
							String	channelId = (String) DDCData.get("F_CHANNEL_ID");
							fSysNames.forEach((fSysName) -> {

							});
						}
					}*/

					childCounts = besSbdyMapper.batchDelete(fSysNames, delTabName);
				}

			}

			childCount = besSbdyMapper.batchDelete(f_sys_names, "bes_sbpz_struct");// 根据节点id(f_sys_name)批量删除
			for(int i = 0; i < f_sys_names.size(); i++){

				Map<String, Object> childCollector = besCollectorMapper.queryCollectorInfo(f_sys_names.get(i));

				if (null != childCollector && !childCollector.isEmpty())
				{
					String channelId = (String) childCollector.get("F_CHANNEL_ID");

					// 删除一个控制器，并删除和它相关的电表
					if (SendMsgHandler.deleteControllerEDC(channelId))
					{
						// 添加订阅消息
						MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_DELETE, channelId);
					}

				}

				if(null != BESSbtreeThread.realTimeData.get(f_sys_names.get(i))){
					BESSbtreeThread.realTimeData.remove(f_sys_names.get(i));
				}

			}

			/*for (String nodeType : idsMap.keySet()) {// 根据子节点包含的表名循环删除对应属性信息
				System.out.println(idsMap);
				String delTabName = tabNameMap.get(nodeType);
				if("16".equals(nodeType)){
					String nodeVType = findNodeTypeBySysName("22");
				}
				if (delTabName != null && !("".equals(delTabName)) && !("bes_sbpz_struct".equals(delTabName.toLowerCase())))
					childCounts = besSbdyMapper.batchDelete(idsMap.get(nodeType), delTabName);
			}*/

		}

		if(f_vpointEnes.size() > 0){//删除虚点能耗对应的节点
			vpointCounts = besSbdyMapper.batchDelete(f_vpointEnes,"bes_ammeter");
		}
		//删除该节点对应的信息（一个节点时）
		String delTabName = tabName;

		int delChildCount = 0;
		if (delTabName != null && !("".equals(delTabName)) && !("bes_sbpz_struct".equals(delTabName.toLowerCase()))) {
			if("16".equals(obj.getString("fNodeType"))){
				String nodeType = findNodeTypeBySysName(sysName);
				if("4".equals(nodeType))
					delChildCount = besSbdyMapper.delSbTreeBySysName(sysName, "bes_ammeter");//根据所选节点删除设备树信息
			}
			delChildCount = besSbdyMapper.delSbTreeBySysName(sysName, delTabName);//根据所选节点删除设备树信息

		}
		int delCount = besSbdyMapper.delSbTreeBySysName(sysName, "BES_SBPZ_STRUCT");//根据所选节点删除设备树信息

		if(delCount == 1 && null != BESSbtreeThread.realTimeData.get(sysName)){

			BESSbtreeThread.realTimeData.remove(sysName);
		}
		if(delCount + delChildCount + childCount + childCounts + vpointCounts>=1){

			if (null != collector && !collector.isEmpty())
			{
				String channelId = (String) collector.get("F_CHANNEL_ID");

				// 删除一个控制器，并删除和它相关的电表
				if (!SendMsgHandler.deleteControllerEDC(channelId))
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功，下发失败");
					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_DELETE, channelId);
			}
			if (ddc != null && !ddc.isEmpty()){
				String channelId = (String) ddc.get("F_CHANNEL_ID");

				// 删除一个控制器，并删除和它相关的模块以及点位
				if (!SendMsgHandler.deleteControllerDDC(channelId))
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功，下发失败");
					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_DELETE, channelId);

			}
			if (module != null && !module.isEmpty()){
				String channelId = (String) DDC_module.get("F_CHANNEL_ID");
				Integer id = Integer.parseInt((String) module.get("F_SBID"));
				// 删除一个模块，并删除和它相关的点位
				if (!SendMsgHandler.deleteModuleDDC(channelId,id))
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功，下发失败");
					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_DELETE, channelId);
			}

			// 删除下位机电表
			if ("BES_AMMETER".equals(delTabName)
					&& null != ammeterData
					&& null != collectorData
					)
			{

				Integer meterID = Integer.parseInt((String) ammeterData.get("F_SBID"));
				String channelId = (String) collectorData.get("F_CHANNEL_ID");
				// 删除电表
				if (!SendMsgHandler.deleteAmmeterEDC(channelId, meterID))
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功，下发失败");
					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_DELETE, channelId);

			}

			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else{
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	/**
	 * 生成设备类型树
	 */
	@Override
	public ISSPReturnObject getEquipmentModuleTree(String ftype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, String> eTypeName = new  HashMap<>();
		try {
			List<BESEquipmentType> equipmentTypeAllList = new ArrayList<>();
			List<BESEquipmentMold> equipmentMoldList = besEquipmentMoldMapper.getIdEquipmentMold(ftype);//获取设备类型信息
			for(BESEquipmentMold equipmentMold : equipmentMoldList){
				eTypeName.put(equipmentMold.getF_type(), equipmentMold.getF_sbmc());
				BESEquipmentType equipmentType = new BESEquipmentType();
				equipmentType.setF_type(equipmentMold.getF_type());
				equipmentType.setF_sblxbh("root");
				equipmentType.setF_sbmc(equipmentMold.getF_sbmc());
				equipmentTypeAllList.add(equipmentType);
				List<BESEquipmentType> equipmentTypeList = besSbdyMapper.getEquipmentTypeById(equipmentMold.getF_type());
				for (BESEquipmentType eType : equipmentTypeList) {
					equipmentTypeAllList.add(eType);
					eTypeName.put(eType.getF_type(), eType.getF_sbmc());
				}
			}
			List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
			for(BESEquipmentType tree : equipmentTypeAllList){
				ISSPTreeNode isspPTreeNode=new ISSPTreeNode();
				isspPTreeNode.setId(tree.getCId());
				isspPTreeNode.setPid(tree.getPId());
				isspPTreeNode.setText(tree.getCMc());
				nodes.add(isspPTreeNode);// 添加到节点容器
			}
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(nodes);//调用共同静态类创建树
			returnObject.setStatus("1");
			returnObject.setList(tree);
			returnObject.setMap(eTypeName);
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 根据当前型号ID获取对应名称
	 */
	@Override
	public ISSPReturnObject getCurrentEquipmentType(String sblxbh, String type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, String> map = new HashMap<>();
			BESEquipmentMold equipmentMold = besSbdyMapper.getEquipmentMoldInfoById(sblxbh);
			BESEquipmentType equipmentType = besSbdyMapper.getEquipmentTypeInfoById(type);
			map.put(sblxbh, equipmentMold.getF_sbmc());
			map.put(type, equipmentType.getF_sbmc());
			returnObject.setMap(map);
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取根据模块型号下拉列表添加子节点时所需信息
	 */
	@Override
	public ISSPReturnObject getModuleTypeAddChidNodeInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESModulePointType> modulePointTypeList = besSbdyMapper.getModulePointTypeInfo();//获取模块点类型信
		Map<String, String > mPointTypeMap = new HashMap<>();//(key:ID value:模块点类型)
		for(BESModulePointType mPointType : modulePointTypeList){
			mPointTypeMap.put(mPointType.getfId(), mPointType.getfModulepointType());
		}
		List<BESEpModuleTypeRlgl> epNoduleTypeList = besSbdyMapper.getEpModuleTypeRlglInfo();//获取设备树节点和模块点类型关系信息
		if (modulePointTypeList.size() > 0 && modulePointTypeList.size() > 0) {
			returnObject.setStatus("1");
			returnObject.setList(epNoduleTypeList);
			returnObject.setMap(mPointTypeMap);
		} else
			returnObject.setStatus("0");

		return returnObject;
	}

	/**
	 * 添加默认节点(根据模块类型)
	 * @param obj
	 * @return
	 */
	private int addDefaultNodes(JSONObject obj){
		List<BESModulePointType> modulePointTypeList = besSbdyMapper.getModulePointTypeInfo();//获取模块点类型信息
		Map<String, String > mPointTypeMap = new HashMap<>();//(key:ID value:模块点类型)
		for(BESModulePointType mPointType : modulePointTypeList){
			mPointTypeMap.put(mPointType.getfId(), mPointType.getfModulepointType());
		}
		List<BESEpModuleTypeRlgl> epNoduleTypeList = besSbdyMapper.getEpModuleTypeRlglInfo();//获取设备树节点和模块点类型信息
		Map<String, String > epModuleTypeRlglMap = new HashMap<>();//(key:ID value:设备树节点类型)
		for(BESEpModuleTypeRlgl epModuleTypeRlgl : epNoduleTypeList){
			epModuleTypeRlglMap.put(epModuleTypeRlgl.getfModulepointId(), epModuleTypeRlgl.getfEpTreenodeType());
		}
		String pFsysName = obj.getString("attr_f_sys_name");
		char[] nodeTypes = obj.getString("other_node_types").toCharArray();
		List<BESSbPzStruct> nodeList = new ArrayList<>();
		//取模块id，加1为第一个逻辑点的id
//		int f_sbid = obj.getInteger("f_sbid") + 1;
		for(int i=0;i<nodeTypes.length;i++){
			BESSbPzStruct sbPzStruct = new BESSbPzStruct();
//			sbPzStruct.setF_sbid(String.valueOf(f_sbid));
			sbPzStruct.setF_sys_name(pFsysName+"0"+String.valueOf(i));
			sbPzStruct.setF_psys_name(pFsysName);
			String nodeName = mPointTypeMap.get(String.valueOf(nodeTypes[i]));
			sbPzStruct.setF_nick_name(nodeName);
			sbPzStruct.setF_allpath(obj.getString("f_allpath")+">"+nodeName);
			sbPzStruct.setF_type(epModuleTypeRlglMap.get(String.valueOf(nodeTypes[i])));
			sbPzStruct.setF_node_attribution(obj.getString("f_node_attribution"));
			sbPzStruct.setF_status("1");
			nodeList.add(sbPzStruct);
//			f_sbid++;
		}
		int insertCount = besSbdyMapper.batchInsert(nodeList);

		return insertCount;
	}

	/**
	 * 根据系统名称获取该节点下的所有子节点
	 * @param f_sys_name 系统名称
	 * @param f_sys_names 系统名称List
	 * @param idsMap
	 * Change by  tianjiangwei
	 */
	private void getChildNode(String f_sys_name, List<String> f_sys_names, Map<String, List<String>> idsMap,List<String> f_vpointEnes) {
		List<BESSbPzStruct> childNodeList = besSbdyMapper.getSbChildNodeBySysName(f_sys_name);//检索所选节点是否含有子节点
		List<String> list = null;
		for (BESSbPzStruct besSbPzStruct : childNodeList) {
			if (idsMap.get(besSbPzStruct.getF_type()) == null) {
				list = new ArrayList<>();
				idsMap.put(besSbPzStruct.getF_type(), list);
				if("16".equals(besSbPzStruct.getF_type())){
					f_vpointEnes.add(besSbPzStruct.getF_sys_name());
					list = idsMap.get(besSbPzStruct.getF_type());
				}
			} else if("16".equals(besSbPzStruct.getF_type())){
				f_vpointEnes.add(besSbPzStruct.getF_sys_name());
				list = idsMap.get(besSbPzStruct.getF_type());
			}else{
				list = idsMap.get(besSbPzStruct.getF_type());
			}
			list.add(besSbPzStruct.getF_sys_name());
			f_sys_names.add(besSbPzStruct.getF_sys_name());
			getChildNode(besSbPzStruct.getF_sys_name(), f_sys_names, idsMap,f_vpointEnes);
		}
	}

	/**
	 * 根据干线耦合器通信地址设置获取支线通信地址设置信息
	 */
	@Override
	public ISSPReturnObject getBranchAddrByTrunkId(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Object obj = besSbdyMapper.findSbdyInfoBySelNode(f_sys_name, "BES_COUPLER");
		if (obj != null) {
			returnObject.setData(obj);
			returnObject.setStatus("1");
		} else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取采集器轮询周期
	 */
	@Override
	public ISSPReturnObject getCollectorLoopTime(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (!"".equals(f_sys_name)) {
			Object obj = besSbdyMapper.getCollectorLoopTime(f_sys_name);
			if (obj != null) {
				returnObject.setData(obj);
				returnObject.setStatus("1");
			} else {
				returnObject.setStatus("0");
			}
		} else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 通过制定列名获取该列全部信息
	 */
	@Override
	public ISSPReturnObject getInfoByColumnName(String colIdName, String colName, String tabName) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, List<String>> map = new HashMap<>();
		List<String> colIdNames = new ArrayList<>();
		List<String> colNames = new ArrayList<>();
		try {
			List<Map<String, String>> list = besSbdyMapper.getInfoByColumnName(colIdName, colName, tabName);
			if (list.size() == 0) {
				returnObject.setStatus("0");
				return returnObject;
			}
			for (Map<String, String> m : list) {
				colIdNames.add(m.get(colIdName));
				colNames.add(m.get(colName));
			}
			map.put("ids", colIdNames);
			map.put("names", colNames);
			returnObject.setMap(map);
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
	 * @param col 该列当前最大值
	 * @return
	 */
	private String getAutoIncreaseCol(String col) {
		if (col == null || "".equals(col)) {
			return "1";
		}
		String regex = "^([0]+)([\\d]*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(new StringBuffer(col));
		if (matcher.find()) {
			return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
		} else {
			return String.valueOf(Integer.parseInt(col) + 1);
		}
	}

	/**
	 * 获取逻辑点的调试信息
	 *
	 * 王红杰修改
	 */
	@SuppressWarnings("unused")
	public ISSPReturnObject getdebugNodeInfo(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询该节点的实体表名
		//String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		String tabName = besSbdyMapper.findNodeTableByRootIdtabName(f_sys_name);
		//String tabName = besSbdyMapper.findNodeTableChange(f_sys_name);
		//Map<String, Object> map = besSbdyMapper.findSbdyInfoBySelNode(f_sys_name, tabName);
		Map<String, Object> map = besSbdyMapper.findSbdyInfoByRootId(f_sys_name,tabName);
		//Map<String, Object> map = besSbdyMapper.findSbdyInfoBySelNodeChange(f_sys_name, tabName);
		if(map != null) {
			if (map.get("F_NODE_TYPE").equals("13")) {//DO点

				map.put("tabName", tabName);
				returnObject.setData(map);
				returnObject.setStatus("1");
			} else {//AO点

				String init = (String) map.get("F_INIT_VAL");
				Integer accuracy = (Integer) map.get("F_ACCURACY");
				map.put("F_INIT_VAL",init);
				map.put("tabName", tabName);
				returnObject.setData(map);
				returnObject.setStatus("1");
			}

		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("请先配置节点详细属性");
		}
		return returnObject;
	}
	/**
	 * 获取模块的通信地址
	 */
	@Override
	@Transactional
	public ISSPReturnObject sbdy_getModuleF_ADDR(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		Map<String, Object> map = new HashMap<>();
		if (!"".equals(obj.getString("f_psys_name"))) {
			map = ModuleF_ADDR(obj.getString("f_psys_name"));
		}

		if(map != null) {
			returnObject.setData(map);
			returnObject.setStatus("1");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("查询通信地址失败");
		}
		return returnObject;
	}

	public Map<String, Object> ModuleF_ADDR(String f_psys_name){
		Map<String,Object> branchCoupler = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		//查询模块的父节点信息
		String pNodeType = besSbdyMapper.querySbpzStructInfo(f_psys_name);
		if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
			//查询该支路耦合器的信息
			branchCoupler = besSbdyMapper.findSbdyInfoBySelNode(f_psys_name,"bes_coupler");
			String branchCoupler_F_ADDR = (String) branchCoupler.get("F_ADDR");
			map.put("branchCoupler_F_ADDR", branchCoupler_F_ADDR);
			//查看支路耦合器的父节点
			Map<String, Object> branchCouplerpNode = besSbdyMapper.querybranchCouplerpNode((String) branchCoupler.get("F_SYS_NAME"));
			if(branchCouplerpNode.get("F_TYPE").equals(5)) {
				Map<String, Object> branchCouplerpNodes = besSbdyMapper.selectCouplerMap((String) branchCouplerpNode.get("F_SYS_NAME"));
				map.put("trunkCoupler_F_ADDR", branchCouplerpNodes.get("F_ADDR"));
				//设置
			} else {
				map.put("trunkCoupler_F_ADDR", "1");
			}
		}else if(pNodeType.equals("3"))	{
			map.put("branchCoupler_F_ADDR", "1");
			map.put("trunkCoupler_F_ADDR", "1");
		}
		return map;
	}
	/**
	 * 获取DO点的信息
	 */
	@Override
	public ISSPReturnObject getPointInfo(String f_sys_name,String tabname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String,Object> doPointInfo = besSbdyMapper.queryPointInfo(f_sys_name,tabname);
			returnObject.setData(doPointInfo);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}
	/**
	 * 查询所有逻辑点的信息
	 */
	@Override
	public ISSPReturnObject getAllPointInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,String>> pointList = besSbdyMapper.getAllPointInfo();
			returnObject.setData(pointList);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getAllPointInfo(String sysName) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//String[] sysNameAttr = sysName.split(",");
			//先去查询缓存
			returnObject = getCacheObject(sysName.split(","));
			if(returnObject.getData() != null){
				returnObject.setStatus("1");
				returnObject.setMsg("查询成功");
				return returnObject;
			}
			sysName = sysName.replaceAll(",","','");
			sysName = "'"+ sysName + "'";
			List<Map<String,String>> pointList = besSbdyMapper.getAllPointInfoBySysName(sysName);
			returnObject.setData(pointList);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	/**
	 * 获取缓存数据
	 * String[] sysNameAttr
	 * @return
	 */
	public ISSPReturnObject getCacheObject(String[] sysNameAttr){
		ISSPReturnObject isspReturnObject = new ISSPReturnObject();
		//List<BESSbPzStruct> besSbPzStructs = BESSbtreeThread.getSbtreeList();// TODO
		List<Map<String,String>> pointList = new ArrayList<>();
	/*	if(besSbPzStructs == null){
			isspReturnObject.setData(null);*/
		if(BESSbtreeThread.realTimeData.isEmpty()){
			isspReturnObject.setData(null);
		}else{
			for(String sysName : sysNameAttr){
				/*for(BESSbPzStruct besSbPzStruct: besSbPzStructs){
					if(sysName.equals(besSbPzStruct.getF_sys_name())){//缓存中有数据
						Map<String,String> map = new HashMap<>();
						map.put("f_sys_name", sysName);
						//map.put("f_guid", );
						map.put("f_sbid", besSbPzStruct.getF_sbid());
						map.put("f_nick_name", besSbPzStruct.getF_nick_name());
						map.put("f_node_type", besSbPzStruct.getF_type());
						//map.put("f_channel_index", sysName);
						map.put("f_init_val", besSbPzStruct.getF_init_val());
						pointList.add(map);
						break;
					}
				}*/

				BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(sysName);

				if(null != besSbPzStruct){
					Map<String,String> map = new HashMap<>();
					map.put("f_sys_name", sysName);
					//map.put("f_guid", );
					map.put("f_sbid", besSbPzStruct.getF_sbid());
					map.put("f_nick_name", besSbPzStruct.getF_nick_name());
					map.put("f_node_type", besSbPzStruct.getF_type());
					//map.put("f_channel_index", sysName);
					map.put("f_init_val", besSbPzStruct.getF_init_val());
					pointList.add(map);
				}
			}
			isspReturnObject.setData(pointList);
		}
		return isspReturnObject;
	}



	/**
	 * 获取缓存数据
	 * String sysName
	 * @return
	 */
	public ISSPReturnObject getCacheObjectBySysName(String sysName){
		ISSPReturnObject isspReturnObject = new ISSPReturnObject();
		//List<BESSbPzStruct> besSbPzStructs = BESSbtreeThread.getSbtreeList();// TODO
		List<Map<String,String>> pointList = new ArrayList<>();
	/*	if(besSbPzStructs == null){
			isspReturnObject.setData(null);*/
		if(BESSbtreeThread.realTimeData.isEmpty()){
			isspReturnObject.setData(null);
		}else{
		/*	for(BESSbPzStruct besSbPzStruct: besSbPzStructs){
				if(besSbPzStruct.getF_sys_name().indexOf(sysName) > -1){//缓存中有数据
					Map<String,String> map = new HashMap<>();
					map.put("f_sys_name", sysName);
					//map.put("f_guid", );
					map.put("f_sbid", besSbPzStruct.getF_sbid());
					map.put("f_nick_name", besSbPzStruct.getF_nick_name());
					map.put("f_node_type", besSbPzStruct.getF_type());
					//map.put("f_channel_index", sysName);
					map.put("f_init_val", besSbPzStruct.getF_init_val());
					pointList.add(map);
				}
			}*/

			BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(sysName);

			if(null != besSbPzStruct){//缓存中有数据
				Map<String,String> map = new HashMap<>();
				map.put("f_sys_name", sysName);
				//map.put("f_guid", );
				map.put("f_sbid", besSbPzStruct.getF_sbid());
				map.put("f_nick_name", besSbPzStruct.getF_nick_name());
				map.put("f_node_type", besSbPzStruct.getF_type());
				//map.put("f_channel_index", sysName);
				map.put("f_init_val", besSbPzStruct.getF_init_val());
				pointList.add(map);
			}

			isspReturnObject.setData(pointList);
		}
		return isspReturnObject;
	}


	public ISSPReturnObject dealProgrammInfo(HttpServletResponse response, HttpServletRequest request, @RequestParam (value = "file", required = false) MultipartFile file, String ddcbcInfoText, String ddcbc_sys_name, String ddcbc_f_type, Integer f_id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer id = f_id;
		String ddcbcInfoTextNext = ddcbcInfoText.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
		if(file !=null)
		{
			//String rootPath = System.getProperty("user.dir");
			String fileName = file.getOriginalFilename();
			if ("".equals(fileName) || fileName==null){//提交文本时,将文本内容储存到demo.txt中
				String filePaths= "";
				String filePath = "";
				//idea或eclipse软件起来的项目的路径
				String path = System.getProperty("user.dir");
				//tomcat起来的war路径
				filePath = request.getServletContext().getRealPath("/")  + "WEB-INF\\file\\DDCprgram\\";//为了拼接编译好的program.txt文件
				filePaths = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\DDCprgram\\demo.txt";//demo.txt的全部路径*/
				File f = null;
				OutputStream out = null;
				try {
					f = new File(filePaths);
					out = new FileOutputStream(f);

				}catch (FileNotFoundException e){
					filePaths = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\demo.txt";//demo.txt的全部路径
					filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\";//为了拼接编译好的program.txt文件
					f = new File(filePaths);
					try {
						out = new FileOutputStream(f);
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					}
					e.printStackTrace();
				}
				// 将字符串转成字节数组
				try {
					byte b[] = ddcbcInfoTextNext.getBytes();
					out.write(b);
					List<BesTxt> txtNum = besSbdyMapper.selectProgrammInfo(f_id);
					if ( txtNum.size() >= 1){//如果表里面已经有了和设备树关联的id,则修改
						besSbdyMapper.updateProgrammInfo(ddcbcInfoTextNext,f_id);
					}else {//如果没有,则添加
						besSbdyMapper.importProgrammInfo(ddcbcInfoTextNext,f_id);
					}
					ISSPReturnObject aa=run(filePaths,filePath,id,ddcbcInfoTextNext);
					returnObject.setData(aa);
				} catch (IOException e1) {
					e1.printStackTrace();
					returnObject.setStatus("编译失败");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					out.close();
				}catch (IOException e2){
					e2.printStackTrace();
				}

			}else {//上传文本时,将文本存放在DDCprgram文件夹下
				String filePath = "";
				//idea或eclipse软件起来的项目的路径
				String path = System.getProperty("user.dir");//获取项目的路径
				File dest = null;
				//tomcat起来的war路径
				filePath = request.getServletContext().getRealPath("/")  + "WEB-INF\\file\\DDCprgram\\";//获取文件的上级目录的路径,为了拼接编译好的program.txt文件
				try {
					dest = new File(filePath+fileName);//获取文件的全部路径
					file.transferTo(dest);
				} catch (IOException e) {
					filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\";//获取文件的上级目录的路径,为了拼接编译好的program.txt文件
					dest = new File(filePath+fileName);//获取文件的全部路径
					try {
						file.transferTo(dest);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				//读取编译好的txt文本中的内容
				StringBuilder result =new StringBuilder();
				try {
					//将上传的文件以utf-8的方式读取出来,防止乱码
					InputStreamReader isr = new InputStreamReader(new FileInputStream(dest), "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					String s = null;
					while((s = br.readLine())!=null){//使用readLine方法，一次读一行
						result.append(System.lineSeparator()+s);
					}
					br.close();
				}catch (Exception e){
					e.printStackTrace();
				}
				String txtend = result.toString();
				List<BesTxt> txtNum = besSbdyMapper.selectProgrammInfo(f_id);//查询bes_txt_begin表里面有没有当前id关联的txt文本
				if ( txtNum.size() >= 1){//如果表里面已经有了和设备树关联的id,则修改
					besSbdyMapper.updateProgrammInfoFile(txtend,f_id);
				}else {//如果没有,则添加
					besSbdyMapper.importProgrammInfoFile(txtend,f_id);
				}
				try {
					ISSPReturnObject aa=run(filePath+fileName,filePath,id,txtend);
					returnObject.setData(aa);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 增加编译文件的功能
	 *
	 * @auther: wanghongjie
	 * @date: 9:52 2020/4/28
	 * @param: [filePaths, filePath, id, txt]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	public ISSPReturnObject run(String filePaths,String filePath,Integer id,String txt) throws Exception {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//以文件的形式输出
		File file=new File(filePath+"program.txt");
		if(!file.exists())
			file.createNewFile();
		FileOutputStream out=new FileOutputStream(file);
		PrintStream p=new PrintStream(out);

		//文件
		ANTLRFileStream input = new ANTLRFileStream(filePaths);
		//lexer
		FunctionLexer lexer = new FunctionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		//parser
		FunctionParser parser = new FunctionParser(tokens);
		FunctionParser.prog_return ret = parser.prog();

		//AST
		CommonTree t = (CommonTree) ret.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
		nodes.setTokenStream(tokens);

		//compiler
		FunctionComplier c= new FunctionComplier(nodes);
		c.prog();

		//print compile result
		Map<String, List<Instruction>> functionMap = c.getFunctionMap();
		List<Instruction> value = new ArrayList<>();
		for (String key : functionMap.keySet()){
			value = functionMap.get(key);

		}
		if (functionMap.size()==0 || value.size() == 0){
			//编译失败,返回0
			returnObject.setData(txt);//返回未编译的文件文本内容,用于显示前端编程页面的文本框中
			returnObject.setStatus("0");
			returnObject.setMsg("编译失败");
		}else {
			Set<Map.Entry<String, List<Instruction>>> set = functionMap.entrySet();
			for (Map.Entry<String, List<Instruction>> e : set) {
				for(Instruction ins: e.getValue()){
					p.println(ins.toString());
				}
			}
			out.close();
			StringBuilder result =new StringBuilder();//读取编译好的txt文本中的内容
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String s = null;
				while((s = br.readLine())!=null){//使用readLine方法，一次读一行
					result.append(System.lineSeparator()+s);
				}
				br.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			String txtend = result.toString();
			//移除“第一个/r/n换行符”
			txtend = txtend.substring(2,txtend.length());
			List<BesTxt> txtNum = besSbdyMapper.selectProgrammInfoByEnd(id);//查询bes_txt_end表里面有没有当前id关联的txt文本
			if ( txtNum.size() >= 1){//如果表里面已经有了和设备树关联的id,则修改
				besSbdyMapper.updateProgrammInfoByEnd(txtend,id);
			}else {//如果没有,则添加
				besSbdyMapper.importProgrammInfoByEnd(txtend,id);
			}
			returnObject.setData(txt);//返回未编译的文件文本内容,用于显示前端编程页面的文本框中
			//编译成功,返回1
			returnObject.setStatus("1");
			returnObject.setMsg("编译成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 能耗采集器的远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 15:58 2020/5/6
	 * @param: [response, request, file, ddcbcInfoText, f_ip]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	public ISSPReturnObject collectorUpload(HttpServletResponse response, HttpServletRequest request, @RequestParam (value = "file", required = false) MultipartFile file,String ddcbcInfoText, String  f_ip) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(file !=null)
		{
			String fileName = file.getOriginalFilename();
			String filePath = "";
			//tomcat起来的war路径
			filePath = request.getServletContext().getRealPath("/")  + "WEB-INF\\file\\DDCprgram\\";//获取文件的上级目录的路径,为了拼接编译好的program.txt文件
			File dest = new File(filePath+fileName);//获取文件的全部路径
			try {
				file.transferTo(dest);
			} catch (IOException e) {

			}
			try {
//				Boolean a = isHostConnectable(f_ip,69);
//				if (a == true){
					ISSPReturnObject aa = uploadFile(f_ip, "D:/44.txt", filePath,fileName, 69,file);
					returnObject.setData(aa);
//				}else {
//					returnObject.setData(false);
//					returnObject.setMsg("ip不通!!");
//				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return returnObject;
	}

	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 *
	 * @Description: 能耗采集器远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 15:43 2020/5/6
	 * @param: [hostname, remoteFilename, localFilePath, port]
	 *
	 * @return
	 */
	public static ISSPReturnObject uploadFile(String hostname, String remoteFilename, String filePath, String fileName, int port, MultipartFile file) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		FileInputStream fileInput=null;
		try {
			fileInput = new  FileInputStream(filePath+fileName);
		} catch (FileNotFoundException e) {
			//idea或eclipse软件起来的项目的路径
			String path = System.getProperty("user.dir");//获取项目的路径
			filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\";//获取文件的上级目录的路径,为了拼接编译好的bin文件
			File dest = new File(filePath+fileName);//获取文件的全部路径
			try {
				file.transferTo(dest);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				fileInput = new  FileInputStream(filePath+fileName);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		// 设置超时时间为10秒
		tftp.setDefaultTimeout(10000);

		// 打开本地socket
		try {
			tftp.open();
		} catch (SocketException e) {
			System.err.println("无法打开本地 UDP socket!");
			System.err.println(e.getMessage());
		}

		boolean success,closed;
		closed = false;
		success = false;

		try {
			boolean ip = InetAddress.getByName(hostname).isReachable(3000);
			if (ip == true){
				tftp.sendFile(remoteFilename, TFTP.BINARY_MODE, fileInput, hostname, port);
				returnObject.setData(true);
				returnObject.setMsg("升级成功!");
			}else {
				returnObject.setData(false);
				returnObject.setMsg("当前ip不通!");
			}
//			success = true;
		} catch (UnknownHostException e) {
			System.err.println("无法解析主机!");
			System.err.println(e.getMessage());
			returnObject.setData(false);
			returnObject.setMsg("无法解析主机!");
		} catch (IOException e) {
			System.err.println("发送文件时有I/O异常!");
			System.err.println(e.getMessage());
			returnObject.setData(false);
			returnObject.setMsg("发送文件时有I/O异常!");
		} finally {
			// 关闭本地 socket 和输出的文件
			tftp.close();
			try {
				if (null != fileInput) {
					fileInput.close();
					File files = new File(filePath+fileName);//升级成功后获取bin文件的地址
					files.delete();//然后删除当前bin文件
				}
				closed = true;
			} catch (IOException e) {
				closed = false;
				System.err.println("关闭文件时出错!");
				System.err.println(e.getMessage());
				returnObject.setMsg("关闭文件时出错!");
				returnObject.setData(false);
			}
		}

		if (!closed)
			returnObject.setData(false);
		return returnObject;
	}
	/**
	 * 将流转化为具体内容
	 * @param inputStream
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String convertStreamToString(InputStream inputStream) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"gb2312"));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * antlr编译文件方法
	 * @param inputStr
	 * @throws Exception
	 */
	public  void complieFile(String inputStr) throws Exception {
		//以文件的形式输出
		File file=new File("src/main/java/com/core/common/ppcl/program.txt");
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		FileOutputStream out=new FileOutputStream(file);
		PrintStream p=new PrintStream(out);

		//文件
		//ANTLRFileStream input = new ANTLRFileStream(inputStr);
		//字符串
		InputStream is = new ByteArrayInputStream(inputStr.getBytes());
		ANTLRInputStream input = new ANTLRInputStream(is);
		//lexer
		FunctionLexer lexer = new FunctionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		//parser
		FunctionParser parser = new FunctionParser(tokens);
		FunctionParser.prog_return ret = parser.prog();

		//AST
		CommonTree t = ret.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
		nodes.setTokenStream(tokens);

		//compiler
		FunctionComplier c= new FunctionComplier(nodes);
		c.prog();

		//print compile result
		Map<String, List<Instruction>> functionMap = c.getFunctionMap();
		Set<Map.Entry<String, List<Instruction>>> set = functionMap.entrySet();
		for (Map.Entry<String, List<Instruction>> e : set) {
			for(Instruction ins: e.getValue()){
				p.println(ins.toString());
			}
		}
		out.close();
	}

	/**
	 * 文件下发
	 */
	public void  fileSend(String IP)
	{
		Process proc;
		String line = null;
		List<String> lines = new ArrayList<>();
		try {
			String[] args1 = new String[] { "python" ,"src/main/java/com/core/common/ppcl/client.py",IP};
//			proc = Runtime.getRuntime().exec("python src/main/java/com/core/common/ppcl/client.py");// 执行py文件
			proc = Runtime.getRuntime().exec(args1);// 执行py文件
			// 用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				lines.add(line);
			}
			in.close();
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String lineData = lines.toString();
		System.out.println("Java调Python脚本结束："+lineData);
	}

	@Override
	public ISSPReturnObject getWkjkPointInfo(String sysName) {
		// TODO Auto-generated method stub
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			returnObject = getCacheObjectBySysName(sysName);
			if(returnObject.getData() != null){
				returnObject.setStatus("1");
				returnObject.setMsg("查询成功");
				return returnObject;
			}
			sysName = "'"+ sysName + "'";
			List<Map<String,String>> pointList = besSbdyMapper.getWkjkPointInfoBySysName(sysName);
			returnObject.setData(pointList);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	/**
	 * @Description:编写f_sbid不对情况代码冲定义sbid字段值方法
	 * @author YangChao
	 * @date 2019/8/30 9:43
	 */
	@Transactional
	public void Reset(){
		List<Map<String,String>> ddclist = besSbdyMapper.getAllDdc();
		// 循环所有ddc 取出f_sys_name
		for(int i=0;i<ddclist.size();i++){
			String f_sys_name = ddclist.get(i).get("F_SYS_NAME");
			//1.0先修改bes_sbpz_struct 这个表 所有当前ddc的f_sbid
			List<Map<String,String>> sbpzList = besSbdyMapper.getSbpzList(f_sys_name);
			//1.0.1 循环当前ddc的bes_sbpz_struct表的f_sbid进行修改
			for(int j =0;j<sbpzList.size();j++){
				// 得到bes_sbpz_struct表查询的f_sys_name--然后执行update
				String fSysName = sbpzList.get(j).get("F_SYS_NAME");
				int f_sbid = j+1;
				boolean flag = besSbdyMapper.fSbidUpdate(fSysName,f_sbid);
			}
			//2.0然后根据bes_sbpz_struct这个表的f_sbid去更新所有附属表
			besSbdyMapper.ffSbidUpdate("bes_module",f_sys_name);
			besSbdyMapper.ffSbidUpdate("bes_digit_ouput",f_sys_name);
			besSbdyMapper.ffSbidUpdate("bes_digit_input",f_sys_name);
			besSbdyMapper.ffSbidUpdate("bes_analog_input",f_sys_name);
			besSbdyMapper.ffSbidUpdate("bes_analog_ouput",f_sys_name);
		}
	}

	/**
	 * 根据系统名称查询节点配置设置表数据 (提示：F_DESC、提示值:F_VALUE)
	 */
	@Override
	public ISSPReturnObject selectNodesConfigSetting(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String, String>> remsUPSDataList = besSbdyMapper.selectNodesConfigSetting(f_sys_name);
		returnObject.setList(remsUPSDataList);
		return returnObject;
	}

	/**
	 * 点击设置配置提交确定时，验证节点配置设置表是否有数据，若有，则依据系统名称，先将数据清除，再插入配置后的数据。
	 */
	@Override
	public int updateNodesConfigSetting(com.alibaba.fastjson.JSONObject obj) {
		String f_sys_name = obj.getString("f_sys_name");//取得系统名称
		//Step1.删除节点配置设置表中，此系统名称所设置存入的数据
		besSbdyMapper.deleteNodesConfigSettingBySysName(f_sys_name);

		String num = obj.getString("config_num");
		if ("0".equals(num))
		{
			return -1; // 删除成功
		}

		//Step2.将新设置的数据插入节点配置设置表
		int insertCount = insertNodesConfigSetting(obj);
		return insertCount;
	}

	/**
	 * 将数据插入节点配置设置表
	 */
	@Override
	public int insertNodesConfigSetting(JSONObject obj) {
		//step1. 对map 进行处理  key对应F_DESC,value对应：F_VALUE。注意：可能有多个key，value。
		List<BesNodeConfig> besNodeConfigList = new ArrayList<BesNodeConfig>();//节点配置设置数据
		Map<String, String> mapValue = new HashMap<String, String>();
		Date date = new Date();
		mapValue = (Map<String, String>) obj.get("map");
		int configNum = obj.getInteger("config_num");//取得配置数据组数
		String f_sys_name = obj.getString("f_sys_name");//取得系统名称
		/*SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdt.format(date);
		String createUser = obj.getString("createuser");
		for(int i=1;i<=configNum;i++) {
			BesNodeConfig besNodeConfig = new BesNodeConfig();//创建节点配置设置对象
			String keyString = mapValue.get("key"+i);
			String value = mapValue.get("val"+i);
			int valueInt = Integer.parseInt(value);
			besNodeConfig.setF_sys_name(f_sys_name);//系统名称
			besNodeConfig.setF_desc(keyString);//节点配置设置表对应的提示描述（如：开机，关机，微风）
			besNodeConfig.setF_value(valueInt);//节点配置表对应的提示数值        （如：255，0 ，1）
			besNodeConfig.setF_createuser(createUser);//配置设置人
			besNodeConfig.setF_createdate(dateString);//创建时间
			besNodeConfigList.add(besNodeConfig);//将节点配置设置表信息添加至List。
		}
		//Step2.将新设置的数据插入节点配置设置表
		int insertCount = 0;
		if (besNodeConfigList.size() > 0)
		{

			insertCount = besSbdyMapper.insertNodesConfigSetting(besNodeConfigList);
		}

		return insertCount;
	}

	/**
	 * 根据类型查询对应名称
	 */
	@Override
	public ISSPReturnObject noteNameByNoteType(String f_type) {
		// TODO Auto-generated method stub
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESSbTreeNodeType> data = besSbdyMapper.noteNameByNoteType(f_type);
			returnObject.setData(data);
			returnObject.setStatus("1");
			returnObject.setMsg("success");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("fail");
		}
		return returnObject;
	}


	@Override
	public String findNodeTypeBySysName(String f_sys_name) {
		// TODO Auto-generated method stub
		return besSbdyMapper.findNodeTypeBySysName(f_sys_name);
	}

	@Override
	public List<Map<String, Object>> judge(String type) {
		return besSbdyMapper.judge(type);
	}

	/**
	 *
	 * @Description: 获取DDC的信息以及关联的程序的txt文件
	 *
	 * @auther: wanghongjie
	 * @date: 15:01 2020/4/23
	 * @param: [f_sys_name, f_id]
	 * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct
	 *
	 */
	@Override
	public BESSbPzStruct getSbTreeAndTxtInfoBySysNameAndId(String f_sys_name, String f_id) {
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeAndTxtInfoBySysNameAndId(f_sys_name,f_id);
		return besSbPzStruct;
	}

    @Override
    public String selectSYS_NAME(String SYS_NAME) {
        Random random = new Random();
        int ends = random.nextInt(99);
        String sys_name_f= String.format("%02d",ends);
        String f_sys_name = SYS_NAME+"1"+sys_name_f;
        Map<String, Object> map= besSbdyMapper.selectSYS_NAME(f_sys_name);
		String in;
        if (map==null){
        	in="0";
		}else {
			in = map.get("F_SYS_NAME_OLD") == null ? "0" : "1";
		}
        if (in.equals("0")){
            return f_sys_name;
        }else {
            return this.selectSYS_NAME(SYS_NAME);
        }
    }


	/**
	 * 新增电表
	 * @param obj
	 * @return
	 */
	@Override
	@Transactional
	public ISSPReturnObject addAmmeter(JSONObject obj)
	{
		ISSPReturnObject result = new ISSPReturnObject();


		String sysName = obj.getString("f_sys_name"); // 系统名称

		if (!StringUtils.hasText(sysName))
		{
			result.setMsg("参数错误，无效的系统名称");
			result.setStatus("0");
			return result;
		}

		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(sysName);

		if (null != besSbPzStruct)
		{
			result.setMsg("系统名称已存在");
			result.setStatus("0");
			return result;
		}

		String nodeAttribution = obj.getString("f_node_attribution");

		if (!StringUtils.hasText(nodeAttribution))
		{
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_psys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}

		String pSysName = obj.getString("f_psys_name");

		String alias = obj.getString("attr_f_nick_name"); // 电表别名
		Integer active = obj.getInteger("attr_f_enabled"); // 使能状态
		Integer meterType = obj.getInteger("attr_f_blxbh"); // 电表类型编号
		String meterTypeName = obj.getString("attr_f_blxmc"); // 电表类型名称
		Integer comPort = obj.getInteger("attr_f_communication_port"); // 通信端口
		Integer comRate = obj.getInteger("attr_f_comm_rate"); // 通信波特率
		String comRateName = obj.getString("attr_f_comm_rate_mc"); // 通信波特率名称
		Integer comAgreementType = obj.getInteger("attr_f_protocol_type"); //通信协议
		String phyAddr = obj.getString("attr_f_wldz"); // 物理地址
		Integer percentage = obj.getInteger("attr_f_percentage"); // 变比
		String location = obj.getString("attr_f_azwz"); //安装位置
		String description = obj.getString("attr_f_description"); //描述
		Integer comDataBit = obj.getInteger("f_com_data_bit"); // 数据位
		Integer comParityBit = obj.getInteger("f_com_parity_bit"); // 校验位
		Integer comStopBit = obj.getInteger("f_com_stop_bit"); // 停止位
		Integer functionCode = obj.getInteger("f_function_code"); // 功能码


		String collectMethodID = obj.getString("attr_f_cjfabh"); // 采集方案编号
		String collectMethodName = obj.getString("attr_f_cjfamc"); // 采集方案名称

		if (!StringUtils.hasText(pSysName)
				|| !StringUtils.hasText(comRateName)
				|| !StringUtils.hasText(collectMethodName)
				)
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		if (!StringUtils.hasText(alias)
				|| null == active
				|| null == meterType
				|| !StringUtils.hasText(meterTypeName)
				|| null == comPort
				|| null == comRate
				|| null == comAgreementType
				|| !StringUtils.hasText(phyAddr)
				|| null == percentage
				|| !StringUtils.hasText(location)
				|| !StringUtils.hasText(description)
				|| null == comDataBit
				|| null == comParityBit
				|| null == comStopBit
				|| null == functionCode
				|| null == collectMethodID
				)
		{
			result.setStatus("0");
			result.setMsg("请完善输入框信息");
			return result;
		}

		obj.put("attr_f_guid", UUIDUtil.getRandom32BeginTimePK());

		// 生成设备id
		String maxId = besSbdyMapper.queryAmmeterMaxId();
		String meterID = getAutoIncreaseCol(maxId);
		obj.put("f_sbid", meterID);

		// BES_AMMETER
		besSbdyMapper.addSbTreeAttrInfo(obj); //根据系统名称添加其他表(属性表)属性信息

		// BES_SBPZ_STRUCT
		besSbdyMapper.addSbTreeStructInfo(obj); //将属性信息存入[设备树结构表]

		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*电表数据添加到缓存 start*/

		BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(obj.getString("f_sys_name"));

		if (besAmmeter != null)
		{
			ammeterCache.addOneAmmeterCache(besAmmeter);
		}
		/*电表数据添加到缓存 end*/

		//查询电表所在的采集器信息
		Map<String, Object> collectorInfo = besJobManagerMapper.querycollectorInfoByAmmeter(pSysName);

		if (null == collectorInfo || collectorInfo.isEmpty())
		{
			result.setStatus("0");
			result.setMsg("数据异常");
			return result;
		}

		String channelId = (String) collectorInfo.get("F_CHANNEL_ID");

		AmmeterCollectParamData ammeterCollectParam = new AmmeterCollectParamData();

		ammeterCollectParam.setMeterID(Integer.parseInt(meterID)); // 电表序列号

		AmmeterParam ammeterParam = new AmmeterParam();

		ammeterParam.setPhyAddr(phyAddr); // 物理地址
		ammeterParam.setComAgreementType(comAgreementType); // 通信规约类型
		ammeterParam.setActive(active); // 电表的使能状态
		ammeterParam.setDescription(description); // 表的描述信息
		ammeterParam.setComRate(comRate); // 通信速率
		ammeterParam.setComPort(comPort); // 通信端口号
		ammeterParam.setComDataBit(comDataBit); // 通信数据位
		ammeterParam.setComParityBit(comParityBit); // 通信校验位
		ammeterParam.setComStopBit(comStopBit); // 通信停止位
		ammeterParam.setMeterType(meterType); // 表类型
		ammeterParam.setAlias(alias); // 表的别名
		ammeterParam.setMeterID(Integer.parseInt(meterID)); // 表序列号
		ammeterParam.setLocation(location); // 电表的安装位置
		ammeterParam.setFunctionCode(functionCode); // 功能码

		ammeterCollectParam.setMeterParameter(ammeterParam);


		// 同步数据到下位机
		boolean sendState = SendMsgHandler.addAmmeterEDC(channelId, ammeterCollectParam);

		if (!sendState)
		{
			result.setStatus("1");
			result.setMsg("保存成功，下发失败");

			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_ADD, channelId);

		result.setStatus("1");
		result.setMsg("保存成功，下发成功");

		return result;
	}

	/**
	 * 更新能耗采集器总线
	 * @param obj
	 * @return
	 */
	@Override
	@Transactional
	public ISSPReturnObject updateEnergyBus(JSONObject obj)
	{

		ISSPReturnObject result = new ISSPReturnObject();

		if (null == obj)
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		String sysName = obj.getString("f_sys_name");
		String nickName = obj.getString("f_nick_name");
		String port = obj.getString("f_port");

		if (!StringUtils.hasText(sysName)
				|| !StringUtils.hasText(nickName)
				|| !StringUtils.hasText(port))
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		obj.put("tabName", "BES_BUS");

		if (besSbdyMapper.editSbTreeStructInfoBySysNameXT(obj) <= 0)
		{
			result.setStatus("0");
			return result;
		}

		obj.put("tabName", "BES_SBPZ_STRUCT");

		obj.remove("f_port");
		obj.put("myInput_f_sys_name", sysName);

		if (besSbdyMapper.editSbTreeStructInfoBySysName1(obj) <= 0)
		{
			result.setStatus("0");
			return result;
		}

		List<Map<String, Object>> ammeters = besJobManagerMapper.queryAmmeterByBusSysName(sysName);

		//TODO 批量同步电表数据，待完善
		for (Map<String, Object> ammeter : ammeters)
		{
			String fSysName = (String) ammeter.get("F_SYS_NAME");
			BESAmmeter besAmmeter = new BESAmmeter();
			besAmmeter.setfSysName(fSysName);
			besAmmeter.setfPort(port);

			besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);

			enerEquipmentServiceImpl.synAmmeter(fSysName);
		}

		result.setStatus("1");
		result.setMsg("保存成功");
		return result;
	}

	/**
	 * 更新电表信息
	 * @param obj
	 * @return
	 */
	@Override
	public ISSPReturnObject updateAmmeter(JSONObject obj)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		String sysName = obj.getString("f_sys_name"); // 系统名称

		if (!StringUtils.hasText(sysName))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Map<String, Object> ammeterInfo = besJobManagerMapper.queryAmmeterInfo(sysName);

		if (null == ammeterInfo || ammeterInfo.isEmpty())
		{
			result.setMsg("电表不存在");
			result.setStatus("0");
			return result;
		}

		Integer meterID = Integer.parseInt((String) ammeterInfo.get("F_SBID"));

		String alias = obj.getString("f_nick_name"); // 电表别名
		Integer active = obj.getInteger("f_enabled"); // 使能状态
		Integer meterType = obj.getInteger("f_blxbh"); // 电表类型编号
		String meterTypeName = obj.getString("f_blxmc"); // 电表类型名称
		Integer comPort = obj.getInteger("f_communication_port"); // 通信端口
		Integer comRate = obj.getInteger("f_comm_rate"); // 通信波特率
		String comRateName = obj.getString("f_comm_rate_mc"); // 通信波特率名称
		Integer comAgreementType = obj.getInteger("f_protocol_type"); //通信协议
		String phyAddr = obj.getString("f_wldz"); // 物理地址
		String location = obj.getString("f_azwz"); //安装位置
		String description = obj.getString("f_description"); //描述
		Integer comDataBit = obj.getInteger("f_com_data_bit"); // 数据位
		Integer comParityBit = obj.getInteger("f_com_parity_bit"); // 校验位
		Integer comStopBit = obj.getInteger("f_com_stop_bit"); // 停止位
		Integer functionCode = obj.getInteger("f_function_code"); // 功能码


		String collectMethodID = obj.getString("f_cjfabh"); // 采集方案编号
		String collectMethodName = obj.getString("f_cjfamc"); // 采集方案名称

		if (!StringUtils.hasText(comRateName)
				|| !StringUtils.hasText(collectMethodName)
				)
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		if (!StringUtils.hasText(alias)
				|| null == active
				|| null == meterType
				|| !StringUtils.hasText(meterTypeName)
				|| null == comPort
				|| null == comRate
				|| null == comAgreementType
				|| !StringUtils.hasText(phyAddr)
				|| !StringUtils.hasText(location)
				|| !StringUtils.hasText(description)
				|| null == comDataBit
				|| null == comParityBit
				|| null == comStopBit
				|| null == functionCode
				|| null == collectMethodID
				)
		{
			result.setStatus("0");
			result.setMsg("请完善输入框信息");
			return result;
		}


		// BES_AMMETER
		obj.put("f_ammeter_state", "0"); // 设置未同步
		besSbdyMapper.editSbTreeStructInfoBySysNameXT(obj); //根据系统名称添加其他表(属性表)属性信息

		obj = new JSONObject();

		obj.put("myInput_f_sys_name", sysName);
		obj.put("f_sys_name", sysName);
		obj.put("f_nick_name", alias);
		obj.put("tabName", "BES_SBPZ_STRUCT");

		// BES_SBPZ_STRUCT
		besSbdyMapper.editSbTreeStructInfoBySysName1(obj); //将属性信息存入[设备树结构表]

		/*更新缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*更新缓存设备结构树 end*/

		/*电表数据添加到缓存 start*/
		BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(obj.getString("f_sys_name"));

		if (besAmmeter != null)
		{
			ammeterCache.updateOneAmmeterCache(besAmmeter);
		}
		/*电表数据添加到缓存 end*/

		//查询电表所在的采集器信息
		Map<String, Object> collectorInfo = besJobManagerMapper.queryCollectorByAmmeterSysName(sysName);

		if (null == collectorInfo || collectorInfo.isEmpty())
		{
			result.setStatus("0");
			result.setMsg("数据异常");
			return result;
		}

		String channelId = (String) collectorInfo.get("F_CHANNEL_ID");

		AmmeterCollectParamData ammeterCollectParam = new AmmeterCollectParamData();

		ammeterCollectParam.setMeterID(meterID); // 电表序列号

		AmmeterParam ammeterParam = new AmmeterParam();

		ammeterParam.setPhyAddr(phyAddr); // 物理地址
		ammeterParam.setComAgreementType(comAgreementType); // 通信规约类型
		ammeterParam.setActive(active); // 电表的使能状态
		ammeterParam.setDescription(description); // 表的描述信息
		ammeterParam.setComRate(comRate); // 通信速率
		ammeterParam.setComPort(comPort); // 通信端口号
		ammeterParam.setComDataBit(comDataBit); // 通信数据位
		ammeterParam.setComParityBit(comParityBit); // 通信校验位
		ammeterParam.setComStopBit(comStopBit); // 通信停止位
		ammeterParam.setMeterType(meterType); // 表类型
		ammeterParam.setAlias(alias); // 表的别名
		ammeterParam.setMeterID(meterID); // 表序列号
		ammeterParam.setLocation(location); // 电表的安装位置
		ammeterParam.setFunctionCode(functionCode); // 功能码

		ammeterCollectParam.setMeterParameter(ammeterParam);

		// 删除电表
		if (!SendMsgHandler.deleteAmmeterEDC(channelId, meterID))
		{
			result.setStatus("1");
			result.setMsg("保存成功，下发失败");
			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_DELETE, channelId);

		// 同步数据到下位机
		if (!SendMsgHandler.addAmmeterEDC(channelId, ammeterCollectParam))
		{

			result.setStatus("1");
			result.setMsg("保存成功，下发失败");
			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_ADD, channelId);

		result.setStatus("1");
		result.setMsg("保存成功，下发成功");

		return result;
	}

	/**
	 * @Description: DDC控制器的新增
	 * @auther: wanghongjie
	 * @date: 8:34 2020/6/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_ddcInfo_Insert(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询bes_ddc表的最大的 	f_sbid
		String sbid 				= besSbdyMapper.select_f_sbid_By_Bes_Ddc();
		String str 					= LEMSConstants.Service_Success;

		String meterID 				= getAutoIncreaseCol(sbid);
		obj.put("f_sbid", meterID);
		String f_poll_status 		= "1";//是否轮训
		String f_sys_name 			= (String) obj.get("f_sys_name");//系统名称
		String f_nick_name 			= (String) obj.get("f_nick_name");//别名
		String f_allpath 			= (String) obj.get("f_allpath");//全路径
		String f_description 		= (String) obj.get("f_description");//描述
		String f_type 				= (String) obj.get("f_type");//节点类型 楼层0、区域1、DDC2、IP路由器3、采集器4、网络控制器5、干线耦合器6、支线耦合器7、总线8、模块9、AI10、AO11、DI12、DO13、UI14、UX15、虚点16、  VAI 17、 VAO 18、 VDI 19、 VDO20
		String f_psys_name 			= (String) obj.get("f_psys_name");//父节点名称
		String f_status 			= "0";//状态 0 离线 、 1 正常 默认正常
		obj.put("f_status",f_status);//状态 0 离线 、 1 正常 默认正常
		obj.put("f_online_state", f_status);//状态 0 离线 、 1 正常 默认正常
		String f_azwz 				= (String) obj.get("f_azwz");//安装位置
		String f_ssqy 				= (String) obj.get("f_ssqy");//归属区域
		String sysName 				= obj.getString("f_sys_name"); // 系统名称
		String f_enabled 			= obj.getString("f_enabled");//使能状态
		String ip 					= obj.getString("f_ip_addr");//ip地址

		if (!StringUtils.hasText(f_sys_name) ||
			!StringUtils.hasText(f_nick_name) ||
			!StringUtils.hasText(f_description) ||
			!StringUtils.hasText(f_azwz) ||
			!StringUtils.hasText(f_ssqy) )
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		if (!StringUtils.hasText(sysName))
		{
			returnObject.setMsg("参数错误，无效的系统名称");
			returnObject.setStatus("0");
			return returnObject;
		}

		//查询父节点的系统名称标识
		String f_node_attribution 	= besSbdyMapper.selectFNodeAttributionByPsysName((String) obj.get("f_psys_name"));//所属系统 1代表cross : 楼控 ,2代表照明
		obj.put("f_node_attribution",f_node_attribution);//所属系统 1代表cross : 楼控,2代表照明

		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(sysName);

		if (null != besSbPzStruct)
		{
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}
		String f_ip_addr = (String) obj.get("f_ip_addr");
		int besDDCSize = besSbdyMapper.getSizeByIpAddrBesDdc(f_ip_addr);
		int besCollectorSize = besSbdyMapper.getSizeByIpAddrBesCollector(f_ip_addr);
		if (besDDCSize == 1 || besCollectorSize == 1){
			returnObject.setMsg("ip重复");
			returnObject.setStatus("0");
			return returnObject;
		}
		besSbdyMapper.sbdy_ddcInfo_insert_bes_sbpz_struct(obj);
		besSbdyMapper.sbdy_ddcInfo_insert_bes_DCC(obj);


		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*控制器信息添加到缓存 start*/
		BesDdc besDdc = besDdcMapper.selectBySysName(obj.getString("f_sys_name"));

		if (besDdc != null)
		{
			ddcCache.addOneDdcCache(besDdc);
		}
		/*控制器信息添加到缓存 end*/

		// 添加订阅消息
		if (f_type.equals("2")){

			ControllerDataDDC controllerData = new ControllerDataDDC();
			controllerData.setName(f_sys_name);
			controllerData.setAlias(f_nick_name);
			controllerData.setDescription(f_description);
			controllerData.setLocation(f_azwz);
			controllerData.setZone(f_ssqy);
			controllerData.setId(Integer.parseInt(meterID));
			controllerData.setActive(Integer.parseInt(f_enabled));

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.addControllerDDC(ip, controllerData);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("保存成功，下发失败");

				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_ADD, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功，下发成功");
		}else {

			ControllerDataLDC controllerDataLDC = new ControllerDataLDC();
			controllerDataLDC.setName(f_sys_name);
			controllerDataLDC.setAlias(f_nick_name);
			controllerDataLDC.setDescription(f_description);
			controllerDataLDC.setLocation(f_azwz);
			controllerDataLDC.setZone(f_ssqy);
			controllerDataLDC.setId(Integer.parseInt(meterID));
			controllerDataLDC.setActive(Integer.parseInt(f_enabled));

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.addControllerLDC(ip, controllerDataLDC);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("保存成功，下发失败");

				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_ADD, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功，下发成功");
		}


		return returnObject;
	}

	/**
	 * @Description: DDC控制器的修改
	 * @auther: wanghongjie
	 * @date: 14:10 2020/6/23
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_ddcInfo_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String ip 				= obj.getString("f_ip_addr");
		String f_description 	= obj.getString("f_description");
		String f_azwz 			= obj.getString("f_azwz");
		String f_ssqy 			= obj.getString("f_ssqy");
		String tabName 			= obj.getString("tabName");
		String str 				= LEMSConstants.Service_Success;
		String f_sys_name 		= obj.getString("f_sys_name");
		String f_nick_name 		= obj.getString("f_nick_name");
		String f_enabled 		= obj.getString("f_enabled");
		String meterID 			= obj.getString("f_id");
		String f_type 			= obj.getString("f_type");
		String f_gateway		= obj.getString("f_gateway");//默认网关
		String f_mask			= obj.getString("f_mask");//子网掩码
		String f_ip_master		= obj.getString("f_ip_master");//主机ip
		Integer f_port_master	= obj.getInteger("f_port_master");//主机端口
		if (!StringUtils.hasText(ip) ||
				!StringUtils.hasText(f_description) ||
				!StringUtils.hasText(f_azwz) ||
				!StringUtils.hasText(f_ssqy) ||
				!StringUtils.hasText(f_gateway) ||
				!StringUtils.hasText(f_mask) ||
				!StringUtils.hasText(f_ip_master) ||
				f_port_master == null)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		//通过f_sys_name查询该DDC属性信息
		String  besSbPzF_guid = besSbdyMapper.editSbdyInfoBySelByNodeByF_guid(obj.getString("f_guid").replaceAll(",",""));
		String  bes_ddc_channel_id = besSbdyMapper.editSbdyInfoBySelByNodeByF_guid_channel_id(obj.getString("f_guid").replaceAll(",",""));
		if (!besSbPzF_guid.equals(ip) && !bes_ddc_channel_id.equals(ip)){

			List<BesDdc>  besSbPzStruct = besSbdyMapper.editSbdyInfoBySelByNodeByIP(ip,tabName);
			if (0!=besSbPzStruct.size()) {
				returnObject.setStatus("3");
				return returnObject;
			}
		}

		Map<String, Object> ddcMap = besDdcMapper.queryDDCMap(obj.getString("f_sys_name"));//查询当前ddc的全部信息
//		ip = (String) ddcMap.get("F_CHANNEL_ID");

		String channelId = (String) ddcMap.get("F_CHANNEL_ID");

		// 如果是离线状态则使通道id等于ip
 		if ("0".equals(ddcMap.get("F_ONLINE_STATE")))
		{
			obj.put("f_channel_id", ip);
			channelId = ip;
		}
//		int besDDCSize = besSbdyMapper.getSizeByIpAddrBesDdc(f_ip_addr);
//		int besCollectorSize = besSbdyMapper.getSizeByIpAddrBesCollector(f_ip_addr);
//		if (besDDCSize == 1 || besCollectorSize == 1){
//			returnObject.setMsg("ip重复");
//			returnObject.setStatus("0");
//			return returnObject;
//		}
		String allpath = (String) obj.get("f_allpath");
		obj.put("f_guid",obj.getString("f_guid").replaceAll(",",""));
		String  prefix = allpath.substring(0,allpath.lastIndexOf(">"));//截取别名前面的字符串

		String f_allpath = prefix + ">" + f_nick_name;
		obj.put("allpath",f_allpath);
		besSbdyMapper.sbdy_ddcInfo_update_bes_sbpz_struct(obj);
		besSbdyMapper.sbdy_ddcInfo_update_bes_DDC(obj);


		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_status(sbPzStructCache.getCachedElement(sbPzStruct.getF_sys_name()).getF_status());
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*控制器信息添加到缓存 start*/
		BesDdc besDdc = besDdcMapper.selectBySysName(obj.getString("f_sys_name"));

		if (besDdc != null)
		{
			besDdc.setfDdcOnlinestate(ddcCache.getCachedElement(besDdc.getfSysName()).getfDdcOnlinestate());
			ddcCache.updateOneDdcCache(besDdc);
		}
		/*控制器信息添加到缓存 end*/

		if (f_type.equals("2")){
			ControllerDataDDC controllerData = new ControllerDataDDC();
			controllerData.setName(f_sys_name);
			controllerData.setAlias(f_nick_name);
			controllerData.setDescription(f_description);
			controllerData.setLocation(f_azwz);
			controllerData.setZone(f_ssqy);
			controllerData.setId(Integer.parseInt(meterID));
			controllerData.setActive(Integer.parseInt(f_enabled));
			controllerData.setIp(ip);
			controllerData.setGateWay(f_gateway);
			controllerData.setMask(f_mask);
			controllerData.setServerIP(f_ip_master);
			controllerData.setServerPort(f_port_master);

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setControllerDDC(channelId, controllerData);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_PARAM_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("修改成功，下发成功");
		} else {
			ControllerDataLDC controllerDataLDC = new ControllerDataLDC();
			controllerDataLDC.setName(f_sys_name);
			controllerDataLDC.setAlias(f_nick_name);
			controllerDataLDC.setDescription(f_description);
			controllerDataLDC.setLocation(f_azwz);
			controllerDataLDC.setZone(f_ssqy);
			controllerDataLDC.setId(Integer.parseInt(meterID));
			controllerDataLDC.setActive(Integer.parseInt(f_enabled));
			controllerDataLDC.setIp(ip);
			controllerDataLDC.setGateWay(f_gateway);
			controllerDataLDC.setMask(f_mask);
			controllerDataLDC.setServerIP(f_ip_master);
			controllerDataLDC.setServerPort(f_port_master);

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setControllerLDC(channelId, controllerDataLDC);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("保存成功，下发失败");

				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_PARAM_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功，下发成功");
		}


		//如果DDC控制器下有模块点位以及下属点位和虚点,则往下位机发送同步命令

		return returnObject;
	}


	/**
	 * @Description: DDC给下位机下发指令，获取控制器参数
	 * @auther: wanghongjie
	 * @date: 17:48 2020/6/28
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject getDDCInfoParam(String fSysName,String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		Map<String,Object> DDCInfoMap = besSbdyMapper.DDCInfoMap(fSysName);
		if (null == DDCInfoMap || DDCInfoMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}
		String channelID = (String) DDCInfoMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		if (f_type.equals("2")){

			boolean sendState = SendMsgHandler.getControllerDDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("获取下位机数据失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_PARAM_GET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("获取下位机数据成功");
		}else {
			boolean sendState = SendMsgHandler.getControllerLDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("获取下位机数据失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_PARAM_GET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("获取下位机数据成功");
		}

		return returnObject;
	}


	/**
	 *
	 * @Description: 新增模块
	 * @auther: wanghongjie
	 * @date: 17:44 2020/6/29
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 * 模块同步状态 在线状态不在此添加,待返回结果再进行判断添加
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_moduleInfo_insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int addSbdyModule = 0;
		int addDefaultNodeCount = 0;
		int addbesModule = 0;

		String f_psys_name 			= obj.getString("f_psys_name");//父节点系统名称
		String ip = null;//所属ip的地址
		String name 				= (String) obj.get("f_sys_name");//名称
		String alias 				= (String) obj.get("f_nick_name");//别名
		String description 			= (String) obj.get("f_description");//模块的描述信息
		String location 			= (String) obj.get("f_azwz");//模块的安装位置
		String addr					= (String) obj.get("f_addr_module");//模块的通信地址(为了区分照明的通信地址)
		Integer slaveAddress 		= obj.getInteger("f_addr");//模块的通信地址
		Integer active 				= obj.getInteger("f_enabled");//模块的使能状态
		Integer id;//模块的识别码
		Integer modelID 			= obj.getInteger("f_module_type");//模块型号编码
		Integer flnID 				= 0;//模块挂在哪条FLN总线上，可能取值PNP,FLN1、FLN2、FLN3、FLN4 (1,2,3,4,5)

		Integer	areaNum				= 1;//模块所在干线号,默认为1
		Integer	branchNum			= 1;//模块所在支线号,默认为1

		//根据前台传的模块型号编码查询模块类型表中的F_TYPE_CODE,这个字段才是下发到下位机的模块型号编码
		modelID = crossEquipmentMapper.queryTypeCodeByModuleType(modelID);

		String sbid = besSbdyMapper.select_f_sbid_By_Bes_Module();
		String meterID = getAutoIncreaseCol(sbid);

		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				!StringUtils.hasText(location))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(name);

		if (null != besSbPzStruct)
		{
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}

		//没有所属系统标识，查询父节点系统标识#
		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_psys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}

		if (obj.get("f_node_attribution").equals("1")) {//楼控节点

			//根据上级系统名称查询别名来查询模块挂在哪条FLN总线上
			String flnName = besSbdyMapper.selectFlnName(f_psys_name);
			if (flnName.equals("PNP")){
				flnID = 1;
			}else if (flnName.equals("FLN1")){
				flnID = 2;
			}else if (flnName.equals("FLN2")){
				flnID = 3;
			}else if (flnName.equals("FLN3")){
				flnID = 4;
			}else if (flnName.equals("FLN4")){
				flnID = 5;
			}

			//查询模块所属DDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");
			//获取sbid
//			obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);
			obj.put("f_status","1");//状态 0 离线 、 1 正常 默认正常
			obj.put("attr_f_sys_name",obj.get("f_sys_name"));
			obj.put("module_guid",meterID);

			String f_addr = obj.getString("f_addr");
			String f_sys_name_old_ddc = (String) ddcinfo.get("F_SYS_NAME_OLD");
			//如果模块的通信地址在当前ddc下有相同的地址,则在页面提示通信地址重复,添加失败
			List<Map<String,Object>> f_addrList = besSbdyMapper.f_addrListByPName(f_psys_name);
			for (int i = 0; i < f_addrList.size(); i++){
				if (f_addr.equals((String) f_addrList.get(i).get("F_ADDR"))){
					returnObject.setStatus("0");
					returnObject.setMsg("新增失败，下发失败, 通信地址重复");
					return returnObject;
				}
			}
		} else {//照明节点
			//查询父节点的详细信息
			//根据父节点的名称查询父节点的详细信息
			Map<String,Object> couplerMap = besSbdyMapper.selectCouplerMap(f_psys_name);

			if (couplerMap != null && !couplerMap.isEmpty()) {

				if (couplerMap.get("F_NODE_TYPE").equals("6")) {//支线耦合器

					branchNum = Integer.parseInt((String) couplerMap.get("F_ADDR")) ;//支线

					//查询相应的支线信息
					Map<String,Object> branchMap = besSbdyMapper.listpoint((String) couplerMap.get("F_SYS_NAME"));

					//获取支线的父节点,根据父节点查询干线的信息
					Map<String,Object> branchPMap = besSbdyMapper.listpoint((String) branchMap.get("F_PSYS_NAME"));

					if (branchPMap.get("F_TYPE").equals(5)) {

						Map<String,Object> couplerMaps = besSbdyMapper.selectCouplerMap((String) branchPMap.get("F_SYS_NAME_OLD"));
						areaNum = Integer.parseInt((String) couplerMaps.get("F_ADDR")) ;//干线
					} else {
						areaNum = 1;//干线
					}
				}
			} else {//说明是在ip路由器下添加的模块
				branchNum = 1;//支线
				areaNum = 1;//干线
			}

			//查询模块所属LDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryLDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");
			//获取sbid
//			obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);
			obj.put("f_status","1");//状态 0 离线 、 1 正常 默认正常
			obj.put("attr_f_sys_name",obj.get("f_sys_name"));
			obj.put("module_guid",meterID);

			String f_addr = obj.getString("f_addr_module");
			String f_sys_name_old_ddc = (String) ddcinfo.get("F_SYS_NAME_OLD");

			//查询当前LDC下所有的模块的通信地址
			List<Map<String,Object>> LDCModuleListMap = besSbdyMapper.selectLDCModuleList(f_sys_name_old_ddc);
			if (LDCModuleListMap.size() > 0) {

				for (Map<String,Object> LDCModuleListMaps : LDCModuleListMap) {
					if (f_addr.equals(LDCModuleListMaps.get("F_ADDR"))) {

						returnObject.setStatus("0");
						returnObject.setMsg("新增失败，下发失败, 通信地址重复");
						return returnObject;
					}
				}
			}
		}

		addSbdyModule = besSbdyMapper.addSbTreeModuleInfo(obj);//将属性信息存入[设备树结构表]
		if (obj.getString("other_node_types") != null && !"".equals(obj.getString("other_node_types"))) {
			addDefaultNodeCount = addDefaultNodes(obj);//自动添加模块下的点位
		}
		String idByModule = besSbdyMapper.selectIdByModule(name);//查询当前模块的F_ID
		id = Integer.parseInt(idByModule);
		String f_struct_id = String.valueOf(id);
		obj.put("f_struct_id",f_struct_id);
		addbesModule = besSbdyMapper.addbesModule(obj);//添加模块表
		if (addSbdyModule > 0 && addDefaultNodeCount > 0 && addbesModule > 0) {//模块和模块下的点位都添加成功
			ModuleParamDDC moduleParam = new ModuleParamDDC();
			ModuleParamLDC moduleParamLDC = new ModuleParamLDC();
			if (obj.get("f_node_attribution").equals("1")) {
				moduleParam.setName(name);
				moduleParam.setAlias(alias);
				moduleParam.setDescription(description);
				moduleParam.setLocation(location);
				moduleParam.setModelID(modelID);
				moduleParam.setFlnID(flnID);
				moduleParam.setSlaveAddress(slaveAddress);
				moduleParam.setActive(active);
				moduleParam.setId(id);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addModuleDDC(ip, moduleParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");

			} else {
				moduleParamLDC.setName(name);
				moduleParamLDC.setAlias(alias);
				moduleParamLDC.setDescription(description);
				moduleParamLDC.setLocation(location);
				moduleParamLDC.setModelID(modelID);
				moduleParamLDC.setAreaNum(areaNum);
				moduleParamLDC.setBranchNum(branchNum);
				moduleParamLDC.setSlaveAddress(slaveAddress);
				moduleParamLDC.setActive(active);
				moduleParamLDC.setId(id);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addModuleLDC(ip, moduleParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.MODULE_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			}
		} else {

			returnObject.setStatus("1");
			returnObject.setMsg("新增成功，不下发");
		}

		return returnObject;
	}

	/**
	 * @Description: 模块的修改
	 * @auther: wanghongjie
	 * @date: 17:49 2020/6/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_moduleInfo_update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int updateSbdyModule = 0;
		int updatebesModule = 0;

		String f_psys_name 			= obj.getString("f_psys_name");//父节点系统名称
		String ip;//所属ip的地址
		String f_enabled 			= obj.getString("f_enabled");//使能状态

		String name 				= (String) obj.get("f_sys_name");//名称
		String alias 				= (String) obj.get("f_nick_name");//别名
		String description 			= (String) obj.get("f_description");//模块的描述信息
		String location 			= (String) obj.get("f_azwz");//模块的安装位置
		Integer slaveAddress 		= obj.getInteger("f_addr");//模块的通信地址
		Integer active 				= obj.getInteger("f_enabled");//模块的使能状态
		Integer id 					= obj.getInteger("f_id");//模块的识别码
		Integer modelID 			= obj.getInteger("f_module_type");//模块型号编码
		Integer flnID 				= 0;//模块挂在哪条FLN总线上，可能取值PNP,FLN1、FLN2、FLN3、FLN4 (1,2,3,4,5)

		Integer	areaNum				= 1;//模块所在干线号,默认为1
		Integer	branchNum			= 1;//模块所在支线号,默认为1

		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				!StringUtils.hasText(location)) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		//根据前台传的模块型号编码查询模块类型表中的F_TYPE_CODE,这个字段才是下发到下位机的模块型号编码
		modelID = crossEquipmentMapper.queryTypeCodeByModuleType(modelID);

		//根据上级系统名称查询别名来查询模块挂在哪条FLN总线上
		if (obj.get("f_node_attribution").equals("1")){
			String flnName = besSbdyMapper.selectFlnName(f_psys_name);
			if (flnName.equals("PNP")) {
				flnID = 1;
			} else if (flnName.equals("FLN1")) {
				flnID = 2;
			} else if (flnName.equals("FLN2")) {
				flnID = 3;
			} else if (flnName.equals("FLN3")) {
				flnID = 4;
			} else if (flnName.equals("FLN4")) {
				flnID = 5;
			}

			//查询模块所属DDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");
			//获取sbid
			obj.put("f_status", "1");//状态 0 离线 、 1 正常 默认正常
			obj.put("attr_f_sys_name", obj.get("f_sys_name"));

			String f_addr = obj.getString("f_addr");
			String f_sys_name_old_ddc = (String) ddcinfo.get("F_SYS_NAME_OLD");
			//如果模块的通信地址在当前ddc下有相同的地址,则在页面提示通信地址重复,添加失败
			//查询ddc下的总线的系统名称
			List<Map<String, Object>> f_addrList = besSbdyMapper.f_addrList(f_sys_name_old_ddc);
			for (int i = 0; i < f_addrList.size(); i++) {
				if (!name.equals(f_addrList.get(i).get("F_SYS_NAME_OLD"))) {
					if (f_addr.equals((String) f_addrList.get(i).get("F_ADDR"))) {
						returnObject.setStatus("0");
						returnObject.setMsg("修改失败，下发失败, 通信地址重复");
						return returnObject;
					}
				}

			}
		}else {//照明节点
			//查询父节点的详细信息
			//根据父节点的名称查询父节点的详细信息
			Map<String,Object> couplerMap = besSbdyMapper.selectCouplerMap(f_psys_name);

			if (couplerMap != null && !couplerMap.isEmpty()) {

				if (couplerMap.get("F_NODE_TYPE").equals("6")) {//支线耦合器

					branchNum = Integer.parseInt((String) couplerMap.get("F_ADDR")) ;//支线

					//查询相应的支线信息
					Map<String,Object> branchMap = besSbdyMapper.listpoint((String) couplerMap.get("F_SYS_NAME"));

					//获取支线的父节点,根据父节点查询干线的信息
					Map<String,Object> branchPMap = besSbdyMapper.listpoint((String) branchMap.get("F_PSYS_NAME"));

					if (branchPMap.get("F_TYPE").equals(5)) {

						Map<String,Object> couplerMaps = besSbdyMapper.selectCouplerMap((String) branchPMap.get("F_SYS_NAME_OLD"));
						areaNum = Integer.parseInt((String) couplerMaps.get("F_ADDR")) ;//干线
					} else {
						areaNum = 1;//干线
					}
				}
			} else {//说明是在ip路由器下添加的模块
				branchNum = 1;//支线
				areaNum = 1;//干线
			}

			//查询模块所属LDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryLDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");

			String f_addr = obj.getString("f_addr_module");
			String f_sys_name_old_ddc = (String) ddcinfo.get("F_SYS_NAME_OLD");


//			String  besSbPzF_guid = besSbdyMapper.selectLDCModuleAddrList(obj.getString("f_guid"));
//			if (!besSbPzF_guid.equals(ip)){
//
//			}

			//查询当前LDC下所有的模块的通信地址
			List<Map<String,Object>> LDCModuleListMap = besSbdyMapper.selectLDCModuleList(f_sys_name_old_ddc);
			if (LDCModuleListMap.size() > 0) {

				for (int i = 0; i < LDCModuleListMap.size(); i++) {
					if (!name.equals(LDCModuleListMap.get(i).get("F_SYS_NAME_OLD"))) {
						if (f_addr.equals((String) LDCModuleListMap.get(i).get("F_ADDR"))) {
							returnObject.setStatus("0");
							returnObject.setMsg("修改失败，下发失败, 通信地址重复");
							return returnObject;
						}
					}
				}
			}
		}

		//修改[设备树结构表]的信息
		updateSbdyModule = besSbdyMapper.updateSbdyModule(obj);
		//修改模块表的信息
		updatebesModule = besSbdyMapper.updatebesModule(obj);
		if (updatebesModule > 0 && updateSbdyModule > 0) {

			if (obj.get("f_node_attribution").equals("1")) {//楼控的模块节点
				ModuleParamDDC moduleParam = new ModuleParamDDC();
				moduleParam.setName(name);
				moduleParam.setAlias(alias);
				moduleParam.setDescription(description);
				moduleParam.setLocation(location);
				moduleParam.setModelID(modelID);
				moduleParam.setFlnID(flnID);
				moduleParam.setSlaveAddress(slaveAddress);
				moduleParam.setActive(active);
				moduleParam.setId(id);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setModuleDDC(ip, moduleParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			}else {//照明的模块节点
				ModuleParamLDC moduleParamLDC = new ModuleParamLDC();
				moduleParamLDC.setName(name);
				moduleParamLDC.setAlias(alias);
				moduleParamLDC.setDescription(description);
				moduleParamLDC.setLocation(location);
				moduleParamLDC.setModelID(modelID);
				moduleParamLDC.setAreaNum(areaNum);
				moduleParamLDC.setBranchNum(branchNum);
				moduleParamLDC.setSlaveAddress(slaveAddress);
				moduleParamLDC.setActive(active);
				moduleParamLDC.setId(id);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setModuleLDC(ip, moduleParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.MODULE_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			}

		} else {

			returnObject.setStatus("1");
			returnObject.setMsg("修改成功，不下发");
		}

		return returnObject;
	}

	/**
	 * @Description: DO点新增
	 * @auther: wanghongjie
	 * @date: 13:43 2020/7/2
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_DOInfo_Insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LDO;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		Integer initValue 		= obj.getIntValue("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer alarmTrigger 	= obj.getInteger("f_close_state");//报警触发  开或关

		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer insertDOPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		if (alarmTrigger == 2) {
			alarmTrigger = 0;
		}
		//输入的系统名称限制唯一
		//f_sys_name唯一验证
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(name);
		if (besSbPzStruct!=null){
			String f_ids = besSbPzStruct.getF_id();
			if (obj.get("f_id") != null){
				if ( !f_ids.equals(obj.get("f_id"))){
					returnObject.setMsg("系统名称已存在");
					returnObject.setStatus("0");
					return returnObject;
				}
			}
		}
		/*BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysNamePoint(name);

		if (null != besSbPzStruct) {
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}*/

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}

		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));


		}

		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}

		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//添加DO表的相应的点位信息
		insertDOPointTable = besSbdyMapper.insertDOPointTable(obj);

		addDoPointCache(obj); // 添加do点缓存

		if (updatesbdyByPoint > 0 && insertDOPointTable > 0) {

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initValue);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setAlarmTrigger(alarmTrigger);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initValue);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setAlarmTrigger(alarmTrigger);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("新增成功，不下发");
		}
		return returnObject;
	}

	/**
	 * 添加do 点缓存
	 * @param obj
	 */
	public void addDoPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加do点到缓存 start*/
		BesDoPoint besDoPoint = besDoPointMapper.queryDoPoint(obj.getString("f_sys_name"));
		if (besDoPoint != null)
		{
			doPointCache.addOneDoPointCache(besDoPoint);
		}
		/*添加do点到缓存 end*/


	}

	/**
	 * @Description: DO点的修改
	 * @auther: wanghongjie
	 * @date: 17:24 2020/7/2
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_DOInfo_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LDO;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		Integer initValue 		= obj.getIntValue("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer alarmTrigger 	= obj.getInteger("f_close_state");//报警触发  开或关

		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";


		Integer updatesbdyByPoint = 0;
		Integer updateDOPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		if (alarmTrigger == 2) {
			alarmTrigger = 0;
		}
		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("old_f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//修改DO表的相应的点位信息
		updateDOPointTable = besSbdyMapper.updateDOPointTable(obj);

		updateDoPointCache(obj);

		if (updatesbdyByPoint > 0 && updateDOPointTable > 0) {

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initValue);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setAlarmTrigger(alarmTrigger);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			} else {//照明
				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initValue);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setAlarmTrigger(alarmTrigger);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("修改成功，不下发");
		}
		return returnObject;
	}

	/**
	 * 更新do点缓存
	 * @param obj
	 */
	public void updateDoPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*更新到缓存设备结构树 end*/

		/*更新do点到缓存 start*/
		BesDoPoint besDoPoint = besDoPointMapper.queryDoPoint(obj.getString("f_sys_name"));
		if (besDoPoint != null)
		{
			doPointCache.updateOneDoPointCache(besDoPoint);
		}
		/*更新do点到缓存 end*/


	}

	/**
	 * @Description: AO点新增
	 * @auther: wanghongjie
	 * @date: 9:52 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_AOInfo_Insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LAO;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		String initValue 		= obj.getString("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer lineType 		= obj.getInteger("f_sinnal_type");//有效输入类型，包括0-10V、0-20mA、4-20mA
		String unit 			= obj.getString("f_engineer_unit");//[12]工程单位，如℉、℃、KWh
		String highRange 		= obj.getString("f_max_val");//最高阀值
		String lowRange 		= obj.getString("f_min_val");//最低阀值
		Integer precision 		= obj.getInteger("f_accuracy");//精度
		Integer alarmHighValue 	= obj.getInteger("f_high_limit");//高限报警值
		Integer alarmLowValue 	= obj.getInteger("f_low_limit");//底限报警值
		if (alarmHighValue == null || alarmLowValue == null) {
			alarmHighValue = 0;
			alarmLowValue = 0;
		}
		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer insertAOPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(unit) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				lineType == null ||
				highRange == null ||
				lowRange == null ||
				precision == null ||
				alarmHighValue == null ||
				alarmLowValue == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		//输入的系统名称限制唯一
		//f_sys_name唯一验证
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(name);
		if (besSbPzStruct!=null){
			String f_ids = besSbPzStruct.getF_id();
			if (obj.get("f_id") != null){
				if ( !f_ids.equals(obj.get("f_id"))){
					returnObject.setMsg("系统名称已存在");
					returnObject.setStatus("0");
					return returnObject;
				}
			}
		}
		/*BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysNamePoint(name);

		if (null != besSbPzStruct) {
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}*/

		Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
		String energyType = obj.getString("f_energy_type");// 能源类型

		//判断能耗统计（0:是；1:否）,统计时插入电表中
		if (energyStatics == 0)
		{
			BESAmmeter besAmmeter = new BESAmmeter();

			besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
			besAmmeter.setfSysName(obj.getString("f_sys_name"));
			besAmmeter.setfSysNameOld(name);
			besAmmeter.setfNickName(alias);
			besAmmeter.setfYqbh(obj.getString("f_yqbh"));
//				besAmmeter.setfSbid(String.valueOf(id));
			// 添加电表
			besAmmeterMapper.insertSelective(besAmmeter);
		}

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}

		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//添加AO表的相应的点位信息
		insertAOPointTable = besSbdyMapper.insertAOPointTable(obj);

		addAoPointCache(obj); // 添加ao点缓存

		if (updatesbdyByPoint > 0 && insertAOPointTable > 0) {

			Integer initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
			Integer highRangeVal = (int) Math.round(Double.parseDouble(highRange) * (Math.pow(10, (precision))));
			Integer lowRangeVal = (int) Math.round(Double.parseDouble(lowRange) * (Math.pow(10, (precision))));

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initVal);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setLineType(lineType);
				pointParam.setUnit(unit);
				pointParam.setHighRange(highRangeVal);
				pointParam.setLowRange(lowRangeVal);
				pointParam.setPrecision(precision);
				pointParam.setAlarmHighValue(alarmHighValue);
				pointParam.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initVal);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setLineType(lineType);
				pointParamLDC.setUnit(unit);
				pointParamLDC.setHighRange(highRangeVal);
				pointParamLDC.setLowRange(lowRangeVal);
				pointParamLDC.setPrecision(precision);
				pointParamLDC.setAlarmHighValue(alarmHighValue);
				pointParamLDC.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("新增成功，不下发");
		}
		return returnObject;
	}

	/**
	 * 添加ao点缓存
	 * @param obj
	 */
	public void addAoPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStruct.setF_accuracy(obj.getString("f_accuracy"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加ao点到缓存 start*/
		BesAoPoint besAoPoint = besAoPointMapper.queryAoPoint(obj.getString("f_sys_name"));
		if (besAoPoint != null)
		{
			aoPointCache.addOneAoPointCache(besAoPoint);
		}
		/*添加ao点到缓存 end*/


	}


	/**
	 * @Description: AO点修改
	 * @auther: wanghongjie
	 * @date: 11:25 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_AOInfo_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LAO;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		String initValue 		= obj.getString("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer lineType 		= obj.getInteger("f_sinnal_type");//有效输入类型，包括0-10V、0-20mA、4-20mA
		String  unit 			= obj.getString("f_engineer_unit");//[12]工程单位，如℉、℃、KWh
		String highRange 		= obj.getString("f_max_val");//最高阀值
		String lowRange 		= obj.getString("f_min_val");//最低阀值
		Integer precision 		= obj.getInteger("f_accuracy");//精度
		Integer alarmHighValue 	= obj.getInteger("f_high_limit");//高限报警值
		Integer alarmLowValue 	= obj.getInteger("f_low_limit");//底限报警值
		if (alarmHighValue == null || alarmLowValue == null) {
			alarmHighValue = 0;
			alarmLowValue = 0;
		}
		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer updateAOPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(unit) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				lineType == null ||
				highRange == null ||
				lowRange == null ||
				precision == null ||
				alarmHighValue == null ||
				alarmLowValue == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		if (Double.valueOf(highRange) < Double.valueOf(lowRange)) {
			returnObject.setStatus("0");
			returnObject.setMsg("最大值小于最小值");
			return returnObject;
		}
		if (alarmHighValue < alarmLowValue) {
			returnObject.setStatus("0");
			returnObject.setMsg("高限报警值小于底限报警值");
			return returnObject;
		}

		String tabName = "BES_ANALOG_OUPUT";
		//查询AI点修改前的信息
		Map<String,Object> pointMap = crossEquipmentMapper.selectPointMap(tabName,f_sys_name);//查询逻辑点的信息


		//如果修改之前能耗采集是采集状态的时候,先修改电表,再判断修改之后的能耗采集状态
		if (pointMap.get("F_ENERGYSTATICS").equals(0)){

			Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
			String energyType = obj.getString("f_energy_type");// 能源类型

			//原状态为开启,修改后还是开启,则只需更新电表信息
			if (energyStatics == 0)
			{
				BESAmmeter besAmmeter = new BESAmmeter();

				besAmmeter.setfSysName(obj.getString("f_sys_name"));
				besAmmeter.setfNickName(alias);
				besAmmeter.setfYqbh((String) pointMap.get("F_YQBH"));
				// 修改电表
				besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);
			}else {
			//原状态为开启,修改为关闭,则在电表中删除该点位
				//判断当前AI点是否在支路中配置过
				int count = besSbdyMapper.queryBranchAmmeterRlgl(name);

				if (count > 0)
				{
					returnObject.setMsg(LEMSConstants.meter_branch);
					returnObject.setStatus("0");
					return returnObject;
				}
				//删除电表中的此点位
				besAmmeterMapper.deleteByPrimaryKey(obj.getString("f_sys_name"));

			}
		} else {

			Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
			String energyType = obj.getString("f_energy_type");// 能源类型

			//原状态为关闭,修改为开启,则往电表中插入该点位
			if (energyStatics == 0)
			{
				BESAmmeter besAmmeter = new BESAmmeter();

				besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
				besAmmeter.setfSysName(obj.getString("f_sys_name"));
				besAmmeter.setfSysNameOld(name);
				besAmmeter.setfNickName(alias);
				besAmmeter.setfYqbh((String) pointMap.get("F_YQBH"));
//				besAmmeter.setfSbid(String.valueOf(id));

				// 添加电表
				besAmmeterMapper.insertSelective(besAmmeter);
			}
		}

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//修改AO表的相应的点位信息
		updateAOPointTable = besSbdyMapper.updateAOPointTable(obj);

		updateAoPointCache(obj); // 更新ao点缓存


		if (updatesbdyByPoint > 0 && updateAOPointTable > 0) {

			Integer initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
			Integer highRangeVal = (int) Math.round(Double.parseDouble(highRange) * (Math.pow(10, (precision))));
			Integer lowRangeVal = (int) Math.round(Double.parseDouble(lowRange) * (Math.pow(10, (precision))));

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initVal);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setLineType(lineType);
				pointParam.setUnit(unit);
				pointParam.setHighRange(highRangeVal);
				pointParam.setLowRange(lowRangeVal);
				pointParam.setPrecision(precision);
				pointParam.setAlarmHighValue(alarmHighValue);
				pointParam.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initVal);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setLineType(lineType);
				pointParamLDC.setUnit(unit);
				pointParamLDC.setHighRange(highRangeVal);
				pointParamLDC.setLowRange(lowRangeVal);
				pointParamLDC.setPrecision(precision);
				pointParamLDC.setAlarmHighValue(alarmHighValue);
				pointParamLDC.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("修改成功，不下发");
		}

		return returnObject;
	}

	/**
	 * 更ai 点缓存
	 * @param obj
	 */
	public void updateAoPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStruct.setF_accuracy(obj.getString("f_accuracy"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*更新到缓存设备结构树 end*/
		/*更新ao点到缓存 start*/
		BesAoPoint besAoPoint = besAoPointMapper.queryAoPoint(obj.getString("f_sys_name"));
		if (besAoPoint != null)
		{
			aoPointCache.updateOneAoPointCache(besAoPoint);
		}
		/*更新ao点到缓存 end*/

		/*电表添加到缓存 start*/
		ammeterCache.deleteOneAmmeterCache(obj.getString("f_sys_name")); // 清除缓存

		Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态

		if (energyStatics != null && energyStatics.equals(0))
		{
			BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(obj.getString("f_sys_name"));

			if (besAmmeter != null)
			{
				ammeterCache.addOneAmmeterCache(besAmmeter);
			}
		}
		/*电表添加到缓存 end*/

	}

	/**
	 * @Description: AI点新增
	 * @auther: wanghongjie
	 * @date: 15:19 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_AIInfo_Insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LAI;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String 	name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String 	alias 			= obj.getString("f_nick_name");//别名
		String 	description 	= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		String initValue 		= obj.getString("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer lineType 		= obj.getInteger("f_sinnal_type");//有效输入类型，包括0-10V、0-20mA、4-20mA
		String 	unit 			= obj.getString("f_engineer_unit");//[12]工程单位，如℉、℃、KWh
		String highRange 		= obj.getString("f_max_val");//最高阀值
		String lowRange 		= obj.getString("f_min_val");//最低阀值
		Integer precision 		= obj.getInteger("f_accuracy");//精度
		Integer alarmHighValue 	= obj.getInteger("f_high_limit");//高限报警值
		Integer alarmLowValue 	= obj.getInteger("f_low_limit");//底限报警值
		String 	yqbh 			= obj.getString("f_yqbh");//园区编号
		if (alarmHighValue == null || alarmLowValue == null) {
			alarmHighValue = 0;
			alarmLowValue = 0;
		}
		String f_sys_name = obj.getString("f_sys_name");//点位的树名称
		String f_id = obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer insertAIPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(unit) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				lineType == null ||
				highRange == null ||
				lowRange == null ||
				precision == null ||
				alarmHighValue == null ||
				alarmLowValue == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		//输入的系统名称限制唯一
		//f_sys_name唯一验证
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(name);
		if (besSbPzStruct!=null){
			String f_ids = besSbPzStruct.getF_id();
			if (obj.get("f_id") != null){
				if ( !f_ids.equals(obj.get("f_id"))){
					returnObject.setMsg("系统名称已存在");
					returnObject.setStatus("0");
					return returnObject;
				}
			}
		}

		/*BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysNamePoint(name);

		if (null != besSbPzStruct) {
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}*/


		Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
		String energyType = obj.getString("f_energy_type");// 能源类型

		if (energyStatics == 0)
		{
			BESAmmeter besAmmeter = new BESAmmeter();

			besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
			besAmmeter.setfSysName(obj.getString("f_sys_name"));
			besAmmeter.setfSysNameOld(name);
			besAmmeter.setfNickName(alias);
			besAmmeter.setfYqbh(yqbh);
//				besAmmeter.setfSbid(String.valueOf(id));
			// 添加电表
			besAmmeterMapper.insertSelective(besAmmeter);
		}

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		//查询当前点位所属的ddc
		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//添加AI表的相应的点位信息
		insertAIPointTable = besSbdyMapper.insertAIPointTable(obj);

		addAiPointCache(obj); // 更新ai点缓存

		if (updatesbdyByPoint > 0 && insertAIPointTable > 0) {

			Integer initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
			Integer highRangeVal = (int) Math.round(Double.parseDouble(highRange) * (Math.pow(10, (precision))));
			Integer lowRangeVal = (int) Math.round(Double.parseDouble(lowRange) * (Math.pow(10, (precision))));

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initVal);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setLineType(lineType);
				pointParam.setUnit(unit);
				pointParam.setHighRange(highRangeVal);
				pointParam.setLowRange(lowRangeVal);
				pointParam.setPrecision(precision);
				pointParam.setAlarmHighValue(alarmHighValue);
				pointParam.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initVal);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setLineType(lineType);
				pointParamLDC.setUnit(unit);
				pointParamLDC.setHighRange(highRangeVal);
				pointParamLDC.setLowRange(lowRangeVal);
				pointParamLDC.setPrecision(precision);
				pointParamLDC.setAlarmHighValue(alarmHighValue);
				pointParamLDC.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("新增成功，不下发");
		}
		return returnObject;
	}

	/**
	 * 添加 ai 点缓存
	 * @param obj
	 */
	public void addAiPointCache(JSONObject obj)
	{

		if (obj == null) return;

		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStruct.setF_accuracy(obj.getString("f_accuracy"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加ai点到缓存 start*/
		BesAiPoint besAiPoint = besAiPointMapper.queryAiPoint(obj.getString("f_sys_name"));
		if (besAiPoint != null)
		{
			aiPointCache.addOneAiPointCache(besAiPoint);
		}
		/*添加ai点到缓存 end*/

		/*添加电表到缓存 start*/
		Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态

		if (energyStatics == 0)
		{
			BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(obj.getString("f_init_val"));

			if (besAmmeter != null)
			{
				ammeterCache.addOneAmmeterCache(besAmmeter);
			}

		}
		/*添加电表到缓存 end*/
	}

	/**
	 * @Description: AI点修改
	 * @auther: wanghongjie
	 * @date: 8:52 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_AIInfo_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LAI;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		String initValue 		= obj.getString("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer lineType 		= obj.getInteger("f_sinnal_type");//有效输入类型，包括0-10V、0-20mA、4-20mA
		String 	unit 			= obj.getString("f_engineer_unit");//[12]工程单位，如℉、℃、KWh
		String highRange 		= obj.getString("f_max_val");//最高阀值
		String lowRange 		= obj.getString("f_min_val");//最低阀值
		Integer precision 		= obj.getInteger("f_accuracy");//精度
		Integer alarmHighValue 	= obj.getInteger("f_high_limit");//高限报警值
		Integer alarmLowValue	 = obj.getInteger("f_low_limit");//底限报警值
		if (alarmHighValue == null || alarmLowValue == null) {
			alarmHighValue = 0;
			alarmLowValue = 0;
		}
		String f_sys_name = obj.getString("f_sys_name");//点位的树名称
		String f_id = obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer updateAIPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(unit) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				lineType == null ||
				highRange == null ||
				lowRange == null ||
				precision == null ||
				alarmHighValue == null ||
				alarmLowValue == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		String tabName = "BES_ANALOG_INPUT";
		//查询AI点修改前的信息
		Map<String,Object> pointMap = crossEquipmentMapper.selectPointMap(tabName,f_sys_name);//查询逻辑点的信息


		//如果修改之前能耗采集是采集状态的时候,先修改电表,再判断修改之后的能耗采集状态
		if (pointMap.get("F_ENERGYSTATICS").equals(0)){

			Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
			String energyType = obj.getString("f_energy_type");// 能源类型

			if (energyStatics == 0)
			{
				BESAmmeter besAmmeter = new BESAmmeter();

				besAmmeter.setfSysName(obj.getString("f_sys_name"));
				besAmmeter.setfNickName(alias);
				besAmmeter.setfYqbh((String) pointMap.get("F_YQBH"));
				// 修改电表
				besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);
			}else {
				//判断当前AI点是否在支路中配置过
				int count = besSbdyMapper.queryBranchAmmeterRlgl(name);

				if (count > 0)
				{
					returnObject.setMsg(LEMSConstants.meter_branch);
					returnObject.setStatus("0");
					return returnObject;
				}
				besAmmeterMapper.deleteByPrimaryKey(obj.getString("f_sys_name"));

			}
		} else {

			Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态
			String energyType = obj.getString("f_energy_type");// 能源类型

			if (energyStatics == 0)
			{
				BESAmmeter besAmmeter = new BESAmmeter();

				besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
				besAmmeter.setfSysName(obj.getString("f_sys_name"));
				besAmmeter.setfSysNameOld(name);
				besAmmeter.setfNickName(alias);
				besAmmeter.setfYqbh((String) pointMap.get("F_YQBH"));
//				besAmmeter.setfSbid(String.valueOf(id));

				// 添加电表
				besAmmeterMapper.insertSelective(besAmmeter);
			}
		}
		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		//查询当前点位所属的ddc
		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//修改AI表的相应的点位信息
		updateAIPointTable = besSbdyMapper.updateAIPointTable(obj);

		updateAiPointCache(obj); // 更新ai 点缓存

		if (updatesbdyByPoint > 0 && updateAIPointTable > 0) {


			Integer initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
			Integer highRangeVal = (int) Math.round(Double.parseDouble(highRange) * (Math.pow(10, (precision))));
			Integer lowRangeVal = (int) Math.round(Double.parseDouble(lowRange) * (Math.pow(10, (precision))));

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initVal);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setLineType(lineType);
				pointParam.setUnit(unit);
				pointParam.setHighRange(highRangeVal);
				pointParam.setLowRange(lowRangeVal);
				pointParam.setPrecision(precision);
				pointParam.setAlarmHighValue(alarmHighValue);
				pointParam.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initVal);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setLineType(lineType);
				pointParamLDC.setUnit(unit);
				pointParamLDC.setHighRange(highRangeVal);
				pointParamLDC.setLowRange(lowRangeVal);
				pointParamLDC.setPrecision(precision);
				pointParamLDC.setAlarmHighValue(alarmHighValue);
				pointParamLDC.setAlarmLowValue(alarmLowValue);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("修改成功，不下发");
		}
		return returnObject;
	}

	/**
	 * 更新ai点缓存
	 * @param obj
	 */
	public void updateAiPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		String sysName = obj.getString("f_sys_name");

		/*更新到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(sysName);

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStruct.setF_accuracy(obj.getString("f_accuracy"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*更新到缓存设备结构树 end*/

		/*更新ai点到缓存 start*/
		BesAiPoint besAiPoint = besAiPointMapper.queryAiPoint(sysName);
		if (besAiPoint != null)
		{
			aiPointCache.updateOneAiPointCache(besAiPoint);
		}
		/*更新ai点到缓存 end*/

		/*电表添加到缓存 start*/
		ammeterCache.deleteOneAmmeterCache(sysName); // 清除缓存

		Integer energyStatics = obj.getInteger("f_energyStatics");// 能耗采集状态

		if (energyStatics != null && energyStatics.equals(0))
		{
			BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(sysName);

			if (besAmmeter != null)
			{
				ammeterCache.addOneAmmeterCache(besAmmeter);
			}
		}
		/*电表添加到缓存 end*/

	}

	/**
	 * @Description: DI点新增
	 * @auther: wanghongjie
	 * @date: 11:48 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_DIInfo_Insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LDI;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String 	name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String 	alias 			= obj.getString("f_nick_name");//别名
		String 	description 	= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向
		Integer initValue 		= obj.getIntValue("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer alarmTrigger 	= obj.getInteger("f_close_state");//报警触发  开或关
		Integer activePassive 	= obj.getInteger("f_sourced");//有源无源

		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer insertDIPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				activePassive == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}
		if (alarmTrigger == 2) {
			alarmTrigger = 0;
		}
		//输入的系统名称限制唯一
		//f_sys_name唯一验证
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeInfoBySysName(name);
		if (besSbPzStruct!=null){
			String f_ids = besSbPzStruct.getF_id();
			if (obj.get("f_id") != null){
				if ( !f_ids.equals(obj.get("f_id"))){
					returnObject.setMsg("系统名称已存在");
					returnObject.setStatus("0");
					return returnObject;
				}
			}
		}
		/*BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysNamePoint(name);

		if (null != besSbPzStruct) {
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}*/

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		//查询当前点位所属的ddc
		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
//		//添加DI表的相应的点位信息
		insertDIPointTable = besSbdyMapper.insertDIPointTable(obj);

		addteDiPointCache(obj); // 添加di 缓存

		if (updatesbdyByPoint > 0 && insertDIPointTable > 0) {

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initValue);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setAlarmTrigger(alarmTrigger);
				pointParam.setActivePassive(activePassive);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initValue);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setAlarmTrigger(alarmTrigger);
				pointParamLDC.setActivePassive(activePassive);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.addPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("新增成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_ADD, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("新增成功，下发成功");
			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("新增失败，不下发");
		}
		return returnObject;
	}

	/**
	 * 添加 di 缓存
	 * @param obj
	 */
	public void addteDiPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("f_init_val"));
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加di点到缓存 start*/
		BesDiPoint besDiPoint = besDiPointMapper.queryDiPoint(obj.getString("f_sys_name"));
		if (besDiPoint != null)
		{
			diPointCache.addOneDiPointCache(besDiPoint);
		}
		/*添加di点到缓存 end*/


	}

	/**
	 * @Description: DI点修改
	 * @auther: wanghongjie
	 * @date: 14:08 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject sbdy_DIInfo_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType 		= PointType.POINT_TYPE_LDI;//点类型
		Integer id 				= obj.getInteger("f_id");//点的ID
		Integer active 			= obj.getInteger("f_enabled");//是否启用
		String name 			= (String) obj.get("old_f_sys_name");//点的名字,发送到下位机名字
		String alias 			= obj.getString("f_nick_name");//别名
		String description 		= obj.getString("f_description");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= obj.getInteger("f_channel_index");//点所在通道索引
		Integer workMode 		= obj.getInteger("f_work_mode");//工作模式，手动模式或自动模式
		Integer polarity 		= obj.getInteger("f_reversed");//极性，正向或反向 0 : 正向    1:反向
		Integer initValue 		= obj.getIntValue("f_init_val");//值
		Integer alarmActive 	= obj.getInteger("f_alarm_enable");//报警是否启用
		Integer alarmType 		= obj.getInteger("f_alarm_type");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= obj.getInteger("f_alarm_priority");//报警优先级
		Integer alarmTrigger 	= obj.getInteger("f_close_state");//报警触发  开或关
		Integer activePassive 	= obj.getInteger("f_sourced");//有源无源

		String f_sys_name 		= obj.getString("f_sys_name");//点位的树名称
		String f_id 			= obj.getString("f_id");//设备配置表的id

		String ip = "";

		Integer updatesbdyByPoint = 0;
		Integer updateDIPointTable = 0;
		if (!StringUtils.hasText(name) ||
				!StringUtils.hasText(alias) ||
				!StringUtils.hasText(description) ||
				active == null ||
				workMode == null ||
				polarity == null ||
				initValue == null ||
				alarmActive == null ||
				alarmType == null ||
				alarmPriority == null ||
				activePassive == null
		) {
			returnObject.setStatus("0");
			returnObject.setMsg("请完善输入框信息");
			return returnObject;
		}

		if (alarmTrigger == 2) {
			alarmTrigger = 0;
		}


		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		//查询当前点位所属的ddc
		if (obj.get("f_node_attribution").equals("1")) {//楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}
		//修改设备配置表的相应的点位信息
		updatesbdyByPoint = besSbdyMapper.updatesbdyByPoint(obj);
		//修改DI表的相应的点位信息
		updateDIPointTable = besSbdyMapper.updateDIPointTable(obj);

		updateDiPointCache(obj); // 更新di 缓存

		if (updatesbdyByPoint > 0 && updateDIPointTable > 0) {

			if (obj.get("f_node_attribution").equals("1")) {

				PointParamDDC pointParam = new PointParamDDC();
				pointParam.setPointType(pointType);
				pointParam.setId(id);
				pointParam.setActive(active);
				pointParam.setName(name);
				pointParam.setAlias(alias);
				pointParam.setDescription(description);
				pointParam.setModuleID(moduleID);
				pointParam.setChannelIndex(channelIndex);
				pointParam.setWorkMode(workMode);
				pointParam.setPolarity(polarity);
				pointParam.setInitValue(initValue);
				pointParam.setAlarmActive(alarmActive);
				pointParam.setAlarmType(alarmType);
				pointParam.setAlarmPriority(alarmPriority);
				pointParam.setAlarmTrigger(alarmTrigger);
				pointParam.setActivePassive(activePassive);
				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointDDC(ip, pointParam);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");
			} else {

				PointParamLDC pointParamLDC = new PointParamLDC();
				pointParamLDC.setPointType(pointType);
				pointParamLDC.setId(id);
				pointParamLDC.setActive(active);
				pointParamLDC.setName(name);
				pointParamLDC.setAlias(alias);
				pointParamLDC.setDescription(description);
				pointParamLDC.setModuleID(moduleID);
				pointParamLDC.setChannelIndex(channelIndex);
				pointParamLDC.setWorkMode(workMode);
				pointParamLDC.setPolarity(polarity);
				pointParamLDC.setInitValue(initValue);
				pointParamLDC.setAlarmActive(alarmActive);
				pointParamLDC.setAlarmType(alarmType);
				pointParamLDC.setAlarmPriority(alarmPriority);
				pointParamLDC.setAlarmTrigger(alarmTrigger);

				// 同步数据到下位机
				boolean sendState = SendMsgHandler.setPointLDC(ip, pointParamLDC);

				if (!sendState) {
					returnObject.setStatus("1");
					returnObject.setMsg("修改成功，下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, ip);
				returnObject.setStatus("1");
				returnObject.setMsg("修改成功，下发成功");

			}

		} else {
			returnObject.setStatus("1");
			returnObject.setMsg("修改失败，不下发");
		}
		return returnObject;
	}

	/**
	 * 更新di 缓存
	 * @param obj
	 */
	public void updateDiPointCache(JSONObject obj)
	{
		if (obj == null)
		{
			return;
		}

		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

		if(sbPzStruct != null)
		{
			sbPzStruct.setUnit(obj.getString("f_engineer_unit"));
			sbPzStruct.setF_init_val(sbPzStructCache.getCachedElement(sbPzStruct.getF_sys_name()).getF_init_val());
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*更新到缓存设备结构树 end*/

		/*更新di点到缓存 start*/
		BesDiPoint besDiPoint = besDiPointMapper.queryDiPoint(obj.getString("f_sys_name"));
		if (besDiPoint != null)
		{
			diPointCache.updateOneDiPointCache(besDiPoint);
		}
		/*更新di点到缓存 end*/


	}

	/**
	 * @Description: 同步逻辑点
	 * @auther: wanghongjie
	 * @date: 15:34 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject synchronizePoint(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = null;

		Integer pointType = null;
		String ip = "";
		Map<String,Object> pNodeInfo;


		if (obj.getInteger("f_node_type") == 13) {
			pointType = PointType.POINT_TYPE_LDO;//点类型
		} else if (obj.getInteger("f_node_type") == 12) {
			pointType = PointType.POINT_TYPE_LDI;//点类型
		} else if (obj.getInteger("f_node_type") == 11) {
			pointType = PointType.POINT_TYPE_LAO;//点类型
		} else if (obj.getInteger("f_node_type") == 10) {
			pointType = PointType.POINT_TYPE_LAI;//点类型
		}

		if (obj.getString("old_f_sys_name") != null) {
			pointMap = crossEquipmentMapper.selectPointMap(obj.getString("tabname"), obj.getString("old_f_sys_name"));
		}
		if (pointMap == null){
			returnObject.setStatus("0");
			returnObject.setMsg("请配置点位信息");

			return returnObject;
		}
		Integer id 				= Integer.parseInt((String) pointMap.get("F_STRUCT_ID"));//点的ID
		Integer active 			= (Integer) pointMap.get("F_ENABLED");//是否启用
		String name 			= (String) pointMap.get("F_SYS_NAME_OLD");//点的名字,发送到下位机名字
		String alias 			= (String) pointMap.get("F_NICK_NAME");//别名
		String description 		= (String) pointMap.get("F_DESCRIPTION");//描述
		Integer moduleID 		= 0;//点所在模块的ID号
		Integer channelIndex 	= (Integer) pointMap.get("F_CHANNEL_INDEX");//点所在通道索引
		Integer workMode 		= (Integer) pointMap.get("F_WORK_MODE");//工作模式，手动模式或自动模式
		Integer polarity 		= (Integer) pointMap.get("F_REVERSED");//极性，正向或反向

		Integer alarmActive 	= (Integer) pointMap.get("F_ALARM_ENABLE");//报警是否启用
		Integer alarmType 		= (Integer) pointMap.get("F_ALARM_TYPE");//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority 	= (Integer) pointMap.get("F_ALARM_PRIORITY");//报警优先级
		Integer lineType 		= (Integer) pointMap.get("F_SINNAL_TYPE");//有效输入类型，包括0-10V、0-20mA、4-20mA
		String unit 			= (String) pointMap.get("F_ENGINEER_UNIT");//[12]工程单位，如℉、℃、KWh
		Integer precision 		= (Integer) pointMap.get("F_ACCURACY");//精度
		String highRange 		= (String) pointMap.get("F_MAX_VAL") ;//最高阀值
		String lowRange 		= (String) pointMap.get("F_MIN_VAL");//最低阀值
		Integer alarmHighValue 	= (Integer) pointMap.get("F_HIGH_LIMIT");//高限报警值
		Integer alarmLowValue 	= (Integer) pointMap.get("F_LOW_LIMIT");//底限报警值

		String initValue       = (String) pointMap.get("F_INIT_VAL");

		if (obj.getInteger("f_node_type") == 11 || obj.getInteger("f_node_type") == 10) {
			if (alarmHighValue == null || alarmLowValue == null) {
				alarmHighValue = 0;
				alarmLowValue = 0;
			}
			if (initValue == null) {
				initValue = "0.0";
			}
		}
		if (obj.getInteger("f_node_type") == 12) {
			if (initValue == null) {
				initValue = "0.0";
			}
		}

		Integer alarmTrigger 	= (Integer) pointMap.get("F_CLOSE_STATE");//报警触发  开或关
		Integer activePassive 	= (Integer) pointMap.get("F_SOURCED");//有源无源


		String f_id 			= obj.getString("f_id");//设备配置表的id

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {

				pNodeInfo = besSbdyMapper.listpoint((String) pointMap.get("F_SYS_NAME_OLD"));

			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}

		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));
		}

		Integer initVal;

		if (null == precision)
		{
			initVal = Integer.parseInt(initValue);
		}else
		{
			initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
		}

		Integer highRangeVal = null;

		if (highRange != null)
		{
			highRangeVal = (int) Math.round(Double.parseDouble(highRange) * (Math.pow(10, (precision))));
		}

		Integer lowRangeVal = null;

		if (lowRange != null)
		{
			lowRangeVal = (int) Math.round(Double.parseDouble(lowRange) * (Math.pow(10, (precision))));
		}


		//查询当前点位所属的ddc
		 if (obj.get("f_node_attribution").equals("1")) { //楼控

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

			 PointParamDDC pointParam = new PointParamDDC();
			 pointParam.setPointType(pointType);
			 pointParam.setId(id);
			 pointParam.setActive(active);
			 pointParam.setName(name);
			 pointParam.setAlias(alias);
			 pointParam.setDescription(description);
			 pointParam.setModuleID(moduleID);
			 pointParam.setChannelIndex(channelIndex);
			 pointParam.setWorkMode(workMode);
			 pointParam.setPolarity(polarity);
			 pointParam.setInitValue(initVal);
			 pointParam.setAlarmActive(alarmActive);
			 pointParam.setAlarmType(alarmType);
			 pointParam.setAlarmPriority(alarmPriority);
			 pointParam.setAlarmTrigger(alarmTrigger);
			 pointParam.setLineType(lineType);
			 pointParam.setUnit(unit);
			 pointParam.setHighRange(highRangeVal);
			 pointParam.setLowRange(lowRangeVal);
			 pointParam.setPrecision(precision);
			 pointParam.setAlarmHighValue(alarmHighValue);
			 pointParam.setAlarmLowValue(alarmLowValue);
			 pointParam.setActivePassive(activePassive);
			 // 同步数据到下位机
			 boolean sendState = SendMsgHandler.setPointDDC(ip, pointParam);

			 if (!sendState) {
				 returnObject.setStatus("0");
				 returnObject.setMsg("同步失败");

				 return returnObject;
			 }

			 // 添加订阅消息
			 MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, ip);
			 returnObject.setStatus("1");
			 returnObject.setMsg("同步成功");
		} else if (obj.get("f_node_attribution").equals("2")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");

			 PointParamLDC pointParamLDC = new PointParamLDC();
			 pointParamLDC.setPointType(pointType);
			 pointParamLDC.setId(id);
			 pointParamLDC.setActive(active);
			 pointParamLDC.setName(name);
			 pointParamLDC.setAlias(alias);
			 pointParamLDC.setDescription(description);
			 pointParamLDC.setModuleID(moduleID);
			 pointParamLDC.setChannelIndex(channelIndex);
			 pointParamLDC.setWorkMode(workMode);
			 pointParamLDC.setPolarity(polarity);
			 pointParamLDC.setInitValue(initVal);
			 pointParamLDC.setAlarmActive(alarmActive);
			 pointParamLDC.setAlarmType(alarmType);
			 pointParamLDC.setAlarmPriority(alarmPriority);
			 pointParamLDC.setAlarmTrigger(alarmTrigger);
			 pointParamLDC.setLineType(lineType);
			 pointParamLDC.setUnit(unit);
			 pointParamLDC.setHighRange(highRangeVal);
			 pointParamLDC.setLowRange(lowRangeVal);
			 pointParamLDC.setPrecision(precision);
			 pointParamLDC.setAlarmHighValue(alarmHighValue);
			 pointParamLDC.setAlarmLowValue(alarmLowValue);
			 pointParamLDC.setActivePassive(activePassive);
			 // 同步数据到下位机
			 boolean sendState = SendMsgHandler.setPointLDC(ip, pointParamLDC);

			 if (!sendState) {
				 returnObject.setStatus("0");
				 returnObject.setMsg("同步失败");

				 return returnObject;
			 }

			 // 添加订阅消息
			 MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, ip);
			 returnObject.setStatus("1");
			 returnObject.setMsg("同步成功");
		}


		return returnObject;
	}


	/**
	 * @Description: 同步模块点
	 * @auther: wanghongjie
	 * @date: 9:40 2020/7/6
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject synchronizeModule(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String f_psys_name = obj.getString("f_psys_name");//父节点系统名称
		String ip;//所属ip的地址
		String tabName;
		String pointName;
		JSONObject obj1 = new JSONObject();
		Map<String, Object> pointMap;

		String f_sbid_module 	= obj.getString("f_sbid_module");
		Map<String, Object> moduleMap = crossEquipmentMapper.moduleMap(f_sbid_module);
		String name 			= (String) obj.get("old_f_sys_name");//名称
		String alias 			= (String) moduleMap.get("F_NICK_NAME");//别名
		String description 		= (String) moduleMap.get("F_DESCRIPTION");//模块的描述信息
		String location 		= (String) moduleMap.get("F_AZWZ");//模块的安装位置
		Integer slaveAddress 	= null;//模块的通信地址
		Integer active 			= Integer.parseInt((String) moduleMap.get("F_ENABLED"));//模块的使能状态
		Integer id 				= Integer.parseInt((String) moduleMap.get("F_STRUCT_ID"));//模块的识别码
		Integer modelID 		= Integer.parseInt((String) moduleMap.get("F_TYPE"));//模块型号编码
		Integer flnID 			= 0;//模块挂在哪条FLN总线上，可能取值PNP,FLN1、FLN2、FLN3、FLN4 (1,2,3,4,5)
		String f_node_attribution=obj.getString("f_node_attribution");//所属系统
		String f_addr_module	= obj.getString("f_addr_module");//模块的通信地址

		String  StringslaveAddress  = "";//模块最后位的通信地址
		String  StringareaNum		= "";//模块所在干线号
		String  StringbranchNum		= "";//模块所在支线号
		Integer	areaNum				= 1;//模块所在干线号,默认为1
		Integer	branchNum			= 1;//模块所在支线号,默认为1

		modelID = crossEquipmentMapper.queryTypeCodeByModuleType(modelID);

		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {

			Map<String, Object> pNodeInfo = besSbdyMapper.listpoint(name);
			f_node_attribution = (String) pNodeInfo.get("F_NODE_ATTRIBUTION");
//			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}
		if (f_node_attribution.equals("1")) {//楼控的模块
			slaveAddress = Integer.parseInt((String) moduleMap.get("F_ADDR"));
			//根据上级系统名称查询别名来查询模块挂在哪条FLN总线上
			String flnName = besSbdyMapper.selectFlnName(f_psys_name);
			if (flnName.equals("PNP")) {
				flnID = 1;
			} else if (flnName.equals("FLN1")) {
				flnID = 2;
			} else if (flnName.equals("FLN2")) {
				flnID = 3;
			} else if (flnName.equals("FLN3")) {
				flnID = 4;
			} else if (flnName.equals("FLN4")) {
				flnID = 5;
			}

			//查询模块所属DDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");
			ModuleParamDDC moduleParam = new ModuleParamDDC();
			moduleParam.setName(name);
			moduleParam.setAlias(alias);
			moduleParam.setDescription(description);
			moduleParam.setLocation(location);
			moduleParam.setModelID(modelID);
			moduleParam.setFlnID(flnID);
			moduleParam.setSlaveAddress(slaveAddress);
			moduleParam.setActive(active);
			moduleParam.setId(id);

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setModuleDDC(ip, moduleParam);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("同步失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_PARAM_SET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("同步成功");

		}else {//照明的模块

			StringareaNum = f_addr_module.substring(0,f_addr_module.indexOf("."));

			int index1= f_addr_module.indexOf(".");
			int index2=f_addr_module.indexOf(".",index1+1);
			int index3 = f_addr_module.lastIndexOf(".");

			StringbranchNum = f_addr_module.substring(index1+1, index2);
			StringslaveAddress = f_addr_module.substring(index3+1);
			areaNum = Integer.parseInt(StringareaNum);
			branchNum = Integer.parseInt(StringbranchNum);
			slaveAddress = Integer.parseInt(StringslaveAddress);

			//查询模块所属LDC控制器
			Map<String, Object> ddcinfo = crossEquipmentMapper.queryLDCInfoByModule(obj.getString("f_psys_name"));
			ip = (String) ddcinfo.get("F_CHANNEL_ID");

			ModuleParamLDC moduleParamLDC = new ModuleParamLDC();
			moduleParamLDC.setName(name);
			moduleParamLDC.setAlias(alias);
			moduleParamLDC.setDescription(description);
			moduleParamLDC.setLocation(location);
			moduleParamLDC.setModelID(modelID);
			moduleParamLDC.setAreaNum(areaNum);
			moduleParamLDC.setBranchNum(branchNum);
			moduleParamLDC.setSlaveAddress(slaveAddress);
			moduleParamLDC.setActive(active);
			moduleParamLDC.setId(id);

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setModuleLDC(ip, moduleParamLDC);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("同步失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.MODULE_PARAM_SET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("同步成功");
		}

		//同步模块下的子节点,不管子节点是否同步成功
		/*List<Map<String, Object>> pointMapLists = crossEquipmentMapper.pointMapList(name);
		for (Map<String, Object> pointMapList : pointMapLists) {

			pointName = (String) pointMapList.get("F_SYS_NAME_OLD");
			obj1.put("f_node_type", pointMapList.get("F_TYPE"));
			obj1.put("old_f_sys_name", pointName);
			obj1.put("f_id", pointMapList.get("F_ID"));

			if (pointMapList.get("F_TYPE").equals(13)) {
				tabName = "BES_DIGIT_OUPUT";
				//查询点位的信息
				pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
				if (pointMap != null) {
					obj1.put("tabname", tabName);
					synchronizePoint(obj1);
				}

			} else if (pointMapList.get("F_TYPE").equals(12)) {
				tabName = "BES_DIGIT_INPUT";
				pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
				if (pointMap != null) {
					obj1.put("tabname", tabName);
					synchronizePoint(obj1);
				}
			} else if (pointMapList.get("F_TYPE").equals(11)) {
				tabName = "BES_ANALOG_OUPUT";
				pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
				if (pointMap != null) {
					obj1.put("tabname", tabName);
					synchronizePoint(obj1);
				}
			} else if (pointMapList.get("F_TYPE").equals(10)) {
				tabName = "BES_ANALOG_INPUT";
				pointMap = crossEquipmentMapper.pointMap(tabName, pointName);
				if (pointMap != null) {
					obj1.put("tabname", tabName);
					synchronizePoint(obj1);
				}
			}
		}*/
		return returnObject;
	}

	/*public static void main(String[] args){
		String f_addr_module= "213.513.414";
		int index= f_addr_module.indexOf(".");
		int index2=f_addr_module.indexOf(".",index+1);
		int index4 = f_addr_module.lastIndexOf(".");

		System.out.println(f_addr_module.substring(0,f_addr_module.indexOf(".")));
		System.out.println(f_addr_module.substring(index+1, index2));
		System.out.println(f_addr_module.substring(index4+1));
	}*/
	/**
	 * @Description: 同步DDC
	 * @auther: wanghongjie
	 * @date: 14:20 2020/7/6
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject synchronizeDDC(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		int flnID;
		//通过f_sys_name查询该DDC属性信息
		Map<String, Object> ddcMap = besDdcMapper.queryDDCMap(obj.getString("old_f_sys_name"));//查询当前ddc的全部信息

		String name 		= (String) ddcMap.get("F_SYS_NAME_OLD");//控制器的名字
		String alias 		= (String) ddcMap.get("F_NICK_NAME");//控制器别名
		String description 	= (String) ddcMap.get("F_DESCRIPTION");//控制器的描述信息
		String location 	= (String) ddcMap.get("F_AZWZ");//控制器的安装位置
		String zone 		= (String) ddcMap.get("F_SSQY");//区域信息
		Integer id 			= Integer.valueOf((String) ddcMap.get("F_SBID"));//控制器的ID,必须唯一
		Integer active 		= Integer.parseInt(ddcMap.get("F_ENABLED").toString());//控制器的使能状态，只有使能时才会正常工作，才会与下位机通信
		String ip 			= (String) ddcMap.get("F_IP_ADDR");
		String channelIP	= (String) ddcMap.get("F_CHANNEL_ID");
		String f_type		= obj.getString("f_type");//节点类型
		String f_gateway	= (String) ddcMap.get("F_GATEWAY");//默认网关
		String f_mask		= (String) ddcMap.get("F_MASK");//子网掩码
		String f_ip_master	= (String) ddcMap.get("F_IP_MASTER");//主机ip
		String f_port_master= (String) ddcMap.get("F_PORT_MASTER");//主机端口

		returnObject.setData(ip);

		if (       !StringUtils.hasText(f_gateway)
				|| !StringUtils.hasText(f_mask)
				|| !StringUtils.hasText(f_ip_master)
				|| !StringUtils.hasText(f_port_master)
		)
		{
			returnObject.setMsg("请完善输入框信息");
			returnObject.setStatus("0");
			return returnObject;
		}

		if (f_type.equals("2")) {
			ControllerDataDDC controllerData = new ControllerDataDDC();
			controllerData.setName(name);
			controllerData.setAlias(alias);
			controllerData.setDescription(description);
			controllerData.setLocation(location);
			controllerData.setZone(zone);
			controllerData.setId(id);
			controllerData.setActive(active);
			controllerData.setIp(ip);
			controllerData.setGateWay(f_gateway);
			controllerData.setMask(f_mask);
			controllerData.setServerIP(f_ip_master);
			controllerData.setServerPort(Integer.valueOf(f_port_master));

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setControllerDDC(channelIP, controllerData);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_PARAM_SET, channelIP);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else {

			ControllerDataLDC controllerDataLDC = new ControllerDataLDC();
			controllerDataLDC.setName(name);
			controllerDataLDC.setAlias(alias);
			controllerDataLDC.setDescription(description);
			controllerDataLDC.setLocation(location);
			controllerDataLDC.setZone(zone);
			controllerDataLDC.setId(id);
			controllerDataLDC.setActive(active);
			controllerDataLDC.setIp(ip);
			controllerDataLDC.setGateWay(f_gateway);
			controllerDataLDC.setMask(f_mask);
			controllerDataLDC.setServerIP(f_ip_master);
			controllerDataLDC.setServerPort(Integer.valueOf(f_port_master));

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.setControllerLDC(channelIP, controllerDataLDC);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_PARAM_SET, channelIP);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}


		//同步模块
		/*List<Map<String,Object>> busList   = crossEquipmentMapper.queryBusInfo(obj.getString("old_f_sys_name"));//查询当前ddc下所有总线的信息

		if (busList.size() > 0){
			for (Map<String,Object> bus : busList){
				String f_psys_name = (String) bus.get("F_SYS_NAME_OLD");
				if (bus.get("F_TYPE").equals(8)){//总线

					List<Map<String,Object>> modelList = crossEquipmentMapper.queryModelInfo(f_psys_name);//查询当前ddc下所有模块的信息

					if (modelList.size() > 0){

						for (Map<String,Object> model : modelList){
							String f_psys_nameByModule = (String) model.get("F_PSYS_NAME");
							Map<String,Object> selectBesModule = crossEquipmentMapper.selectBesModule((String) model.get("F_SYS_NAME_OLD"));
							JSONObject object = new JSONObject();
							object.put("f_psys_name",f_psys_nameByModule);
							object.put("f_sbid_module",selectBesModule.get("F_SBID"));
							object.put("old_f_sys_name",model.get("F_SYS_NAME_OLD"));
							synchronizeModule(object);

						}
					}

				} else if (bus.get("F_TYPE").equals(24)){//虚点总线

				}
			}
		}*/
		return returnObject;
	}

	/**
	 * 新增虚点
	 * @param obj
	 * @return
	 */
	@Transactional
	@Override
	public ISSPReturnObject addVirtualPoint(JSONObject obj)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		if (null == obj)
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		String parkNumber = obj.getString("parkNumber");// 园区编号
		String pSysName = obj.getString("pSysName");// 父节点系统名称
		String allPath = obj.getString("allPath");// 全路径

		if (!StringUtils.hasText(parkNumber)
				|| !StringUtils.hasText(pSysName)
				|| !StringUtils.hasText(allPath)
				)
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer pointType = obj.getInteger("pointType"); // 点类型
		Integer active = obj.getInteger("active");// 使能状态
		String name = obj.getString("name");// 点名称
		String alias = obj.getString("alias");// 点别名
		String description = obj.getString("description");// 描述
		String initValue = obj.getString("initValue");// 初始值
		Integer alarmActive = obj.getInteger("alarmActive");// 告警使能
		Integer alarmType = obj.getInteger("alarmType");// 告警类型
		Integer alarmPriority = obj.getInteger("alarmPriority");// 报警优先级

		if (null == pointType
				|| null == active
				|| !StringUtils.hasText(name)
				|| !StringUtils.hasText(alias)
				|| !StringUtils.hasText(description)
				|| !StringUtils.hasText(parkNumber)
				|| null == initValue
				|| null == alarmActive
				|| null == alarmType
				|| null == alarmPriority
				)
		{
			result.setMsg("请完善输入框信息");
			result.setStatus("0");
			return result;
		}

		String unit = null; // 单位
		Integer precision = null; // 精度
		Integer alarmHighValue = null; // 高限报警值
		Integer alarmLowValue = null; // 底限报警值
		Integer alarmTrigger = null; // 闭合状态

		PointParamDDC pointParam = new PointParamDDC();
		PointParamLDC pointParamLDC = new PointParamLDC();


		if (PointType.POINT_TYPE_VIRTUAL_DI == pointType
				|| PointType.POINT_TYPE_VIRTUAL_DO == pointType) // 虚点DI类型 虚点DO类型
		{
			alarmTrigger = obj.getInteger("alarmTrigger");// 闭合状态
			if (alarmTrigger == 2) {
				alarmTrigger = 0;
			}
		}

		// 获取 tree ddc 信息
		Map<String, Object> treeDdcInfo = besSbdyMapper.getParentNodeInfo(pSysName);
		//获取所属系统的编号
		obj.put("f_node_attribution",treeDdcInfo.get("F_NODE_ATTRIBUTION"));

		String ddcSysName = (String) treeDdcInfo.get("F_SYS_NAME");

		if (!StringUtils.hasText(ddcSysName))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		// 查看系统名称是否存在
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(name);

		if (null != besSbPzStruct)
		{
			result.setMsg("系统名称已存在");
			result.setStatus("0");
			return result;
		}

//		int deviceId = getSbid(ddcSysName);

		obj.put("type", 16);

		// BES_SBPZ_STRUCT
		besSbdyMapper.addSbTreeInfo(obj); // 添加虚点设备树信息

		BESSbPzStruct nodeInfo = besSbdyMapper.getSbTreeDataBySysName(name);

		if (null == nodeInfo)
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer id = Integer.parseInt(nodeInfo.getF_id());

		if (PointType.POINT_TYPE_VIRTUAL_AI == pointType
				|| PointType.POINT_TYPE_VIRTUAL_AO == pointType) // 虚点AI类型 虚点AO类型
		{
			unit = obj.getString("unit");// [12]工程单位，如℉、℃、KWh
			precision = obj.getInteger("precision");// 精度

			alarmHighValue = obj.getInteger("alarmHighValue");// 高限报警值
			alarmLowValue = obj.getInteger("alarmLowValue");// 底限报警值

			if (PointType.POINT_TYPE_VIRTUAL_AI == pointType)
			{
				Integer energyStatics = obj.getInteger("energyStatics");// 能耗采集状态
				String energyType = obj.getString("energyType");// 能源类型

				obj.put("energyStatics", energyStatics);
				obj.put("energyType", energyType);

				if (energyStatics == 0)
				{
					BESAmmeter besAmmeter = new BESAmmeter();

					besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
					besAmmeter.setfSysName(name);
					besAmmeter.setfSysNameOld(name);
					besAmmeter.setfNickName(alias);
					besAmmeter.setfYqbh(parkNumber);
//					besAmmeter.setfSbid(String.valueOf(id));

					// 添加电表
					besAmmeterMapper.insertSelective(besAmmeter);
				}

			}

			pointParam.setAlarmHighValue(alarmHighValue);
			pointParam.setAlarmLowValue(alarmLowValue);

			pointParamLDC.setAlarmHighValue(alarmHighValue);
			pointParamLDC.setAlarmLowValue(alarmLowValue);

			if (AlarmType.ALARM_TYPE_STANDARD == alarmType
					&&  (null == alarmHighValue
					|| null == alarmLowValue))
			{
				result.setMsg("请完善输入框信息");
				result.setStatus("0");
				return result;
			}else
			{
				pointParam.setAlarmHighValue(0);
				pointParam.setAlarmLowValue(0);

				pointParamLDC.setAlarmHighValue(0);
				pointParamLDC.setAlarmLowValue(0);
			}

			if (!StringUtils.hasText(unit) || null == precision)
			{
				result.setMsg("请完善输入框信息");
				result.setStatus("0");
				return result;
			}
		}
		// bes_vpoint
		obj.put("syncState" , 0); // 设置未同步
		obj.put("deviceId" , nodeInfo.getF_id()); // 设置未同步
		besSbdyMapper.addVirtualPointInfo(obj); // 添加虚点信息

		addVirtualPointCache(obj); // 同步虚点缓存

		Map<String, Object>  ddcInfo = besDdcMapper.queryDDCInfo(ddcSysName);

		String channelId = (String) ddcInfo.get("F_CHANNEL_ID");

		if (null == channelId)
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer initVal;

		if (null == precision)
        {
			initVal = Integer.parseInt(initValue);
        }else
		{
			initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
		}


		if (nodeInfo.getF_node_attribution().equals("2")) {//照明

			pointParamLDC.setPointType(pointType);
			pointParamLDC.setId(id);
			pointParamLDC.setActive(active);
			pointParamLDC.setName(name);
			pointParamLDC.setAlias(alias);
			pointParamLDC.setDescription(description);
			pointParamLDC.setInitValue(initVal);
			pointParamLDC.setAlarmActive(alarmActive);
			pointParamLDC.setAlarmType(alarmType);
			pointParamLDC.setAlarmPriority(alarmPriority);
			pointParamLDC.setUnit(unit);
			pointParamLDC.setPrecision(precision);
			pointParamLDC.setAlarmTrigger(alarmTrigger);


			boolean sendState = SendMsgHandler.addPointLDC(channelId, pointParamLDC);

			if (!sendState)
			{
				result.setStatus("1");
				result.setMsg("保存成功，下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_ADD, channelId);

			result.setStatus("1");
			result.setMsg("保存成功，下发成功");

		} else if (nodeInfo.getF_node_attribution().equals("1")) {//楼控

			pointParam.setPointType(pointType);
			pointParam.setId(id);
			pointParam.setActive(active);
			pointParam.setName(name);
			pointParam.setAlias(alias);
			pointParam.setDescription(description);
			pointParam.setInitValue(initVal);
			pointParam.setAlarmActive(alarmActive);
			pointParam.setAlarmType(alarmType);
			pointParam.setAlarmPriority(alarmPriority);
			pointParam.setUnit(unit);
			pointParam.setPrecision(precision);
			pointParam.setAlarmTrigger(alarmTrigger);


			boolean sendState = SendMsgHandler.addPointDDC(channelId, pointParam);

			if (!sendState)
			{
				result.setStatus("1");
				result.setMsg("保存成功，下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_ADD, channelId);

			result.setStatus("1");
			result.setMsg("保存成功，下发成功");
		}


		return result;
	}

	/**
	 * 添加虚点缓存
	 */
	public void addVirtualPointCache(JSONObject obj)
	{

		if (obj == null)
		{
			return;
		}

		String name = obj.getString("name");// 点名称

		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(name);

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("initValue"));
			sbPzStruct.setUnit(obj.getString("unit"));
			sbPzStruct.setF_accuracy(obj.getString("precision"));
			sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加虚点到缓存 start*/
		BesVirtualPoint besVirtualPoint = besVirtualPointMapper.queryVirtualPoint(name);

		if (besVirtualPoint != null)
		{
			virtualPointCache.addOneVirtualPointCache(besVirtualPoint);
		}
		/*添加虚点到缓存 end*/

		/*电表添加到缓存 start*/
		Integer pointType = obj.getInteger("pointType"); // 点类型
		Integer energyStatics = obj.getInteger("energyStatics");// 能耗采集状态

		if (PointType.POINT_TYPE_VIRTUAL_AI == pointType)
		{
			if (energyStatics != null && energyStatics.equals(0))
			{
				BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(name);

				if (besAmmeter != null)
				{
					ammeterCache.addOneAmmeterCache(besAmmeter);
				}
			}

		}
		/*电表添加到缓存 end*/
	}

	/**
	 * 更新虚点缓存
	 */
	public void updateVirtualPointCache(JSONObject obj)
	{

		if (obj == null)
		{
			return;
		}

		String name = obj.getString("name");// 点名称

		String sysName = obj.getString("name");

		/*添加到缓存设备结构树 start*/
		BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(sysName);

		if(sbPzStruct != null)
		{
			sbPzStruct.setF_init_val(obj.getString("initValue"));
			sbPzStruct.setUnit(obj.getString("unit"));
			sbPzStruct.setF_accuracy(obj.getString("precision"));
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
		}
		/*添加到缓存设备结构树 end*/

		/*添加虚点到缓存 start*/
		BesVirtualPoint besVirtualPoint = besVirtualPointMapper.queryVirtualPoint(sysName);

		if (besVirtualPoint != null)
		{
			virtualPointCache.updateOneVirtualPointCache(besVirtualPoint);
		}
		/*添加虚点到缓存 end*/

		/*电表添加到缓存 start*/
		ammeterCache.deleteOneAmmeterCache(sysName); // 清除缓存

		Integer pointType = obj.getInteger("pointType"); // 点类型
		Integer energyStatics = obj.getInteger("energyStatics");// 能耗采集状态

		if (PointType.POINT_TYPE_VIRTUAL_AI == pointType)
		{
			if (energyStatics != null && energyStatics.equals(0))
			{
				BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(sysName);

				if (besAmmeter != null)
				{
					ammeterCache.addOneAmmeterCache(besAmmeter);
				}
			}

		}
		/*电表添加到缓存 end*/

	}

	/**
	 * @Description: 获取上位机逻辑点信息
	 * @auther: wanghongjie
	 * @date: 14:31 2020/7/7
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject getPointInfoFillLocal(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		Map<String,Object> map = new HashMap<>();;

		String tabName = obj.getString("tabName");//表名
		String f_psys_name = obj.getString("f_psys_name");//父节点的名称
		String f_sys_name = obj.getString("f_sys_name");//当前点位的名称
		String f_node_attribution = obj.getString("f_node_attribution");//所属系统
		String f_id = obj.getString("f_id");//设备配置表的id

		String pointType = null;//点类型
		Integer id = null;//点的ID
		String active = null;//是否启用
		String name = null;//名称
		String alias = null;//别名
		String description = null;//描述
		String moduleID = null;//点所在模块的名称
		Integer channelIndex = null;//点所在模块的通道索引
		String workMode = null;//工作模式
		String polarity = null;//极性
		Integer initValue = null;//值

		String alarmActive = null;//报警是否启用
		String alarmType = null;//报警类型
		String alarmPriority = null;//报警优先级
		String lineType = null;//有效输入类型
		Integer highRange = null;//最高阈值
		Integer lowRange = null;//最低阈值

		String alarmTrigger = null;//报警触发
		String activePassive = null;//有源无源
		String unit = null;//工程单位
		Integer precision = null;//精度
		Integer alarmHighValue = null;//高限报警值
		Integer alarmLowValue = null;//底限报警值


		if (tabName.equals("BES_DIGIT_OUPUT")){
			map.put("pointType","DO");
		}else if (tabName.equals("BES_DIGIT_INPUT")){
			map.put("pointType","DI");
		}else if (tabName.equals("BES_ANALOG_OUPUT")){
			map.put("pointType","AO");
		}else {
			map.put("pointType","AI");
		}

			map.put("id",obj.get("f_id"));//点的id

		//查询父节点的详细信息
		Map<String, Object> moduleMap = crossEquipmentMapper.selectModuleMapById(f_id);
		if (moduleMap.size() > 0) {
			//查询点所在模块的ID号
			moduleID = (String) moduleMap.get("F_SYS_NAME_OLD");
		}
		map.put("moduleID",moduleID);

		map.put("channelIndex",obj.get("f_channel_index"));
		//查询当前点位的详细信息

		Map<String,Object> pointMap = crossEquipmentMapper.selectPointMap(tabName,f_sys_name);
		if (pointMap == null){
			returnObject.setStatus("0");
			returnObject.setMsg("请配置信息");
			return returnObject;
		}
		if(pointMap.size() > 0){
			if (pointMap.get("F_ENABLED") != null){//使能状态
				if (pointMap.get("F_ENABLED").equals(1)){
					map.put("active","使能");
				}else {
					map.put("active","不使能");
				}
			}
			if (pointMap.get("F_SYS_NAME_OLD") != null){//名称
				map.put("name",pointMap.get("F_SYS_NAME_OLD"));
			}
			if (pointMap.get("F_NICK_NAME") != null){//别名
				map.put("alias",pointMap.get("F_NICK_NAME"));
			}
			if (pointMap.get("F_DESCRIPTION") != null){//描述
				map.put("description",pointMap.get("F_DESCRIPTION"));
			}
			if (pointMap.get("F_WORK_MODE") != null){//工作模式
				if (pointMap.get("F_WORK_MODE").equals(1)){
					map.put("workMode","手动");
				}else {
					map.put("workMode","自动");
				}
			}
			if (pointMap.get("F_REVERSED") != null){//正反向
				if (pointMap.get("F_REVERSED").equals(1)){
					map.put("polarity","反向");
				}else {
					map.put("polarity","正向");
				}
			}
			if (pointMap.get("F_INIT_VAL") != null){//值
				map.put("initValue",pointMap.get("F_INIT_VAL"));
			}
			if (pointMap.get("F_ALARM_ENABLE") != null){//报警使能
				if (pointMap.get("F_ALARM_ENABLE").equals(1)){
					map.put("alarmActive","启用");
				}else {
					map.put("alarmActive","禁用");
				}
			}
			if (pointMap.get("F_ALARM_TYPE") != null){//报警类型
				if (pointMap.get("F_ALARM_TYPE").equals(0)){
					map.put("alarmType","不报警");
				}else if(pointMap.get("F_ALARM_TYPE").equals(1)){
					map.put("alarmType","标准报警");
				}else {
					map.put("alarmType","增强报警");
				}
			}
			if (pointMap.get("F_ALARM_PRIORITY") != null){//报警优先级
				if (pointMap.get("F_ALARM_PRIORITY").equals(0)){
					map.put("alarmPriority","一般");
				}else if(pointMap.get("F_ALARM_PRIORITY").equals(1)){
					map.put("alarmPriority","较大");
				}else {
					map.put("alarmPriority","重大");
				}
			}
			if (pointMap.get("F_CLOSE_STATE") != null){//报警触发  开和关
				if (pointMap.get("F_CLOSE_STATE").equals(1)){
					map.put("alarmTrigger","断开");
				}else if (pointMap.get("F_CLOSE_STATE").equals(0)){
					map.put("alarmTrigger","闭合");
				}else if (pointMap.get("F_CLOSE_STATE").equals(2)){
					map.put("alarmTrigger","闭合");
				}
			}

			if (pointMap.get("F_SOURCED") != null){//是否有源
				if (pointMap.get("F_SOURCED").equals(1)){
					map.put("activePassive","有源");
				}else {
					map.put("activePassive","无源");
				}
			}
			if (pointMap.get("F_SINNAL_TYPE") != null){//信号类型
				if (pointMap.get("F_SINNAL_TYPE").equals(0)){
					map.put("lineType","0-10V");
				}else if(pointMap.get("F_SINNAL_TYPE").equals(1)){
					map.put("lineType","4-20mA");
				}else if(pointMap.get("F_SINNAL_TYPE").equals(2)){
					map.put("lineType","4-20mA");
				}else {
					map.put("lineType","pt100");
				}
			}
			if (pointMap.get("F_ENGINEER_UNIT") != null){//工程单位
				map.put("unit",pointMap.get("F_ENGINEER_UNIT"));
			}
			if (pointMap.get("F_MAX_VAL") != null){//最大值
				map.put("highRange",pointMap.get("F_MAX_VAL"));
			}
			if (pointMap.get("F_MIN_VAL") != null){//最小值
				map.put("lowRange",pointMap.get("F_MIN_VAL"));
			}
			if (pointMap.get("F_ACCURACY") != null){//精度
				map.put("precision",pointMap.get("F_ACCURACY"));
			}
			if (pointMap.get("F_HIGH_LIMIT") != null){//高限报警
				map.put("alarmHighValue",pointMap.get("F_HIGH_LIMIT"));
			}
			if (pointMap.get("F_LOW_LIMIT") != null){//底限报警
				map.put("alarmLowValue",pointMap.get("F_LOW_LIMIT"));
			}
			returnObject.setStatus("1");
			returnObject.setData(map);
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("获取上位机数据失败");
		}

		return returnObject;
	}


	/**
	 *
	 * @Description: 数据对比的时候,给下位机发送指令
	 *
	 * @auther: wanghongjie
	 * @date: 8:58 2020/7/8
	 * @param: [fSysName, f_node_attribution]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject getPointParam(String tabName,String fSysName, String f_node_attribution,String f_id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String ip = null;


		if(f_node_attribution ==null || "".equals(f_node_attribution)) {
			Map<String,Object> pNodeInfo = besSbdyMapper.listpoint(fSysName);
			f_node_attribution = (String) pNodeInfo.get("F_NODE_ATTRIBUTION");
		}
		if (f_node_attribution.equals("1")) {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint(f_id);
			if (queryDDCMapByPoint.size() > 0){
				ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
			}
		}else {

			//查询当前点位所属的ddc
			Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryLDCMapByPoint(f_id);
			ip = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
		}


		//查询当前点位的信息
		List<Map<String,Object>> selectPointMap =  besSbdyMapper.selectPointMap(tabName,fSysName);
		if (selectPointMap.size() == 0){
			returnObject.setStatus("0");
			returnObject.setMsg("请配置点位信息");

			return returnObject;
		}
		Integer id = Integer.parseInt(f_id);

		if (f_node_attribution.equals("1")) {

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.getPointParamDDC(ip, id);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机数据获取失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_GET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("下位机数据获取成功");
		} else {

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.getPointParamLDC(ip, id);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机数据获取失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_GET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("下位机数据获取成功");

		}


		return returnObject;
	}
	/**
	 * 更新虚点信息
	 * @param obj
	 * @return
	 */
	@Transactional
	@Override
	public ISSPReturnObject updateVirtualPoint(JSONObject obj)
	{

		ISSPReturnObject result = new ISSPReturnObject();

		if (null == obj)
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		String name = obj.getString("name");// 点名称

		if (!StringUtils.hasText(name))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer pointType = obj.getInteger("pointType"); // 点类型
		Integer energyStatics = obj.getInteger("energyStatics"); // 点类型

		Integer active = obj.getInteger("active");// 使能状态
		String alias = obj.getString("alias");// 点别名
		String description = obj.getString("description");// 描述
		String initValue = obj.getString("initValue");// 初始值
		Integer alarmActive = obj.getInteger("alarmActive");// 告警使能
		Integer alarmType = obj.getInteger("alarmType");// 告警类型
		Integer alarmPriority = obj.getInteger("alarmPriority");// 报警优先级

		if (null == pointType
				|| null == active
				|| !StringUtils.hasText(name)
				|| !StringUtils.hasText(alias)
				|| !StringUtils.hasText(description)
				|| null == initValue
				|| null == alarmActive
				|| null == alarmType
				|| null == alarmPriority
				)
		{
			result.setMsg("请完善输入框信息");
			result.setStatus("0");
			return result;
		}

		// 查看系统名称是否存在
		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(name);

		if(null == besSbPzStruct )
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer id = Integer.parseInt(besSbPzStruct.getF_id());

		// 查询当前虚点信息
		Map<String, Object> virtualPoint = crossEquipmentMapper.findVpointBySysName(name);

		if (null == virtualPoint || virtualPoint.isEmpty())
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer f_node_type = Integer.parseInt((String)virtualPoint.get("F_NODE_TYPE"));
		Integer f_energystatics = (Integer) virtualPoint.get("F_ENERGYSTATICS");
		String f_nick_name = (String) virtualPoint.get("F_NICK_NAME");

		if (PointType.POINT_TYPE_VIRTUAL_AI == f_node_type && 0 == f_energystatics
				&& (pointType != PointType.POINT_TYPE_VIRTUAL_AI || energyStatics != 0))
		{

			int count = besSbdyMapper.queryBranchAmmeterRlgl(name);

			if (count > 0)
			{
				result.setMsg(LEMSConstants.meter_branch);
				result.setStatus("0");
				return result;
			}

			besAmmeterMapper.deleteByPrimaryKey(name);
		}

		if (PointType.POINT_TYPE_VIRTUAL_AI == f_node_type
				&& 0 == f_energystatics
				&& pointType == PointType.POINT_TYPE_VIRTUAL_AI
				&& energyStatics == 0
				&& !alias.equals(f_nick_name))
		{
			BESAmmeter besAmmeter = new BESAmmeter();
			besAmmeter.setfSysName(name);
			besAmmeter.setfNickName(alias);
			besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);
		}

		String unit = null; // 单位
		Integer precision = null; // 精度
		Integer alarmHighValue = null; // 高限报警值
		Integer alarmLowValue = null; // 底限报警值
		Integer alarmTrigger = null; // 闭合状态

		PointParamDDC pointParam = new PointParamDDC();
		PointParamLDC pointParamLDC = new PointParamLDC();

		if (PointType.POINT_TYPE_VIRTUAL_AI == pointType
				|| PointType.POINT_TYPE_VIRTUAL_AO == pointType) // 虚点AI类型 虚点AO类型
		{
			unit = obj.getString("unit");// [12]工程单位，如℉、℃、KWh
			precision = obj.getInteger("precision");// 精度

			alarmHighValue = obj.getInteger("alarmHighValue");// 高限报警值
			alarmLowValue = obj.getInteger("alarmLowValue");// 底限报警值

			if (PointType.POINT_TYPE_VIRTUAL_AI == pointType)
			{
				String energyType = obj.getString("energyType");// 能源类型

				obj.put("energyStatics", energyStatics);
				obj.put("energyType", energyType);

				if ((PointType.POINT_TYPE_VIRTUAL_AI != f_node_type || 0 != f_energystatics) && energyStatics == 0)
				{
					BESAmmeter besAmmeter = new BESAmmeter();

					besAmmeter.setfGuid(UUIDUtil.getRandom32BeginTimePK());
					besAmmeter.setfSysName(name);
					besAmmeter.setfSysNameOld(name);
					besAmmeter.setfNickName(alias);
					besAmmeter.setfYqbh((String) virtualPoint.get("F_YQBH"));
//					besAmmeter.setfSbid(String.valueOf(id));

					// 添加电表
					besAmmeterMapper.insertSelective(besAmmeter);
				}

			}

			pointParam.setAlarmHighValue(alarmHighValue);
			pointParam.setAlarmLowValue(alarmLowValue);

			if (AlarmType.ALARM_TYPE_STANDARD == alarmType
					&&  (null == alarmHighValue
					|| null == alarmLowValue))
			{
				result.setMsg("请完善输入框信息");
				result.setStatus("0");
				return result;
			}else
			{
				pointParam.setAlarmHighValue(0);
				pointParam.setAlarmLowValue(0);
			}

			if (!StringUtils.hasText(unit) || null == precision)
			{
				result.setMsg("请完善输入框信息");
				result.setStatus("0");
				return result;
			}
		}


		if (PointType.POINT_TYPE_VIRTUAL_DI == pointType
				|| PointType.POINT_TYPE_VIRTUAL_DO == pointType) // 虚点DI类型 虚点DO类型
		{
			alarmTrigger = obj.getInteger("alarmTrigger");// 闭合状态
			if (alarmTrigger == 2) {
				alarmTrigger = 0;
			}
		}

		// 获取 tree ddc 信息
		Map<String, Object> treeDdcInfo = besSbdyMapper.getParentNodeInfo(besSbPzStruct.getF_psys_name());

		String ddcSysName = (String) treeDdcInfo.get("F_SYS_NAME");

		String f_node_attribution = (String) treeDdcInfo.get("F_NODE_ATTRIBUTION");

		if (!StringUtils.hasText(ddcSysName))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		// BES_SBPZ_STRUCT
		besSbdyMapper.updateSbTreeInfo(obj); // 更新虚点设备树信息

		// bes_vpoint
		obj.put("syncState" , 0); // 设置未同步
		besSbdyMapper.updateVirtualPointInfo(obj); // 更新虚点信息

		updateVirtualPointCache(obj); // 更新虚点缓存

		Integer initVal;

		if (null == precision)
		{
			initVal = Integer.parseInt(initValue);
		}else
		{
			initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
		}

		if (f_node_attribution.equals("2")) {//照明

			pointParamLDC.setPointType(pointType);
			pointParamLDC.setId(id);
			pointParamLDC.setActive(active);
			pointParamLDC.setName(name);
			pointParamLDC.setAlias(alias);
			pointParamLDC.setDescription(description);
			pointParamLDC.setInitValue(initVal);
			pointParamLDC.setAlarmActive(alarmActive);
			pointParamLDC.setAlarmType(alarmType);
			pointParamLDC.setAlarmPriority(alarmPriority);
			pointParamLDC.setUnit(unit);
			pointParamLDC.setPrecision(precision);
			pointParamLDC.setAlarmTrigger(alarmTrigger);

			Map<String, Object>  ddcInfo = besDdcMapper.queryDDCInfo(ddcSysName);

			String channelId = (String) ddcInfo.get("F_CHANNEL_ID");

			if (null == channelId)
			{
				result.setMsg("参数错误");
				result.setStatus("0");
				return result;
			}

			boolean sendState = SendMsgHandler.setPointLDC(channelId, pointParamLDC);

			if (!sendState)
			{
				result.setStatus("1");
				result.setMsg("保存成功，下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, channelId);

			result.setStatus("1");
			result.setMsg("保存成功，下发成功");

		}else if (f_node_attribution.equals("1")){//楼控

			pointParam.setPointType(pointType);
			pointParam.setId(id);
			pointParam.setActive(active);
			pointParam.setName(name);
			pointParam.setAlias(alias);
			pointParam.setDescription(description);
			pointParam.setInitValue(initVal);
			pointParam.setAlarmActive(alarmActive);
			pointParam.setAlarmType(alarmType);
			pointParam.setAlarmPriority(alarmPriority);
			pointParam.setUnit(unit);
			pointParam.setPrecision(precision);
			pointParam.setAlarmTrigger(alarmTrigger);

			Map<String, Object>  ddcInfo = besDdcMapper.queryDDCInfo(ddcSysName);

			String channelId = (String) ddcInfo.get("F_CHANNEL_ID");

			if (null == channelId)
			{
				result.setMsg("参数错误");
				result.setStatus("0");
				return result;
			}

			boolean sendState = SendMsgHandler.setPointDDC(channelId, pointParam);

			if (!sendState)
			{
				result.setStatus("1");
				result.setMsg("保存成功，下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, channelId);

			result.setStatus("1");
			result.setMsg("保存成功，下发成功");
		}

		return result;
	}

	/**
	 *
	 * @Description: 根据id查询模块表的当前点位的全部信息
	 *
	 * @auther: wanghongjie
	 * @date: 13:45 2020/7/8
	 * @param: [moduleID]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject selectModuleByModuleID(String moduleID) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,Object> moduleMap = besSbdyMapper.selectModuleMap(moduleID);
		String moduleName = (String) moduleMap.get("F_SYS_NAME_OLD");
		returnObject.setData(moduleName);

		return returnObject;
	}

	/**
	 *
	 * @Description: 数据对比的时候,给下位机发送获取一个模块的所有配置信息的命令
	 *
	 * @auther: wanghongjie
	 * @date: 17:13 2020/7/8
	 * @param: [f_id, fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject getModuleParam(String f_id, String fSysName,String f_node_attribution) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String ip = null;
		Map<String, Object> ddcinfo = null;
		//查询当前模块所属的ddc
		if (f_node_attribution.equals("1")) {

			ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(fSysName);
		} else {

			ddcinfo = crossEquipmentMapper.queryLDCInfoByModuleByfSysName(fSysName);
		}

		if (ddcinfo == null) {
			returnObject.setStatus("0");
			returnObject.setMsg("请配置点位信息");

			return returnObject;
		}
		ip = (String) ddcinfo.get("F_CHANNEL_ID");
		Integer id = Integer.parseInt(f_id);

		if(f_node_attribution ==null || "".equals(f_node_attribution)) {
			Map<String,Object> pNodeInfo = besSbdyMapper.listpoint(fSysName);
			f_node_attribution = (String) pNodeInfo.get("F_NODE_ATTRIBUTION");
		}

		if (f_node_attribution.equals("1")) {

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.getModuleDDC(ip, id);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机数据获取失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_PARAM_GET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("下位机数据获取成功");

			return returnObject;
		} else {

			// 同步数据到下位机
			boolean sendState = SendMsgHandler.getModuleLDC(ip, id);

			if (!sendState) {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机数据获取失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.MODULE_PARAM_GET, ip);
			returnObject.setStatus("1");
			returnObject.setMsg("下位机数据获取成功");

			return returnObject;
		}

	}

	/**
	 *
	 * @Description: DDC设置时间
	 *
	 * @auther: wanghongjie
	 * @date: 8:42 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject setTimeDDC(String fSysName,String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//获取当前DDC的属性信息
		Map<String,Object> DDCInfoMap = besSbdyMapper.DDCInfoMap(fSysName);
		if (DDCInfoMap == null || DDCInfoMap.isEmpty()){
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}

		String channelID = (String) DDCInfoMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		if (f_type.equals("2")) {

			boolean sendState = SendMsgHandler.setControllerTimeDDC(channelID, DataUtil.getTimeDataObject());

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_TIME_SET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else {

			boolean sendState = SendMsgHandler.setControllerTimeLDC(channelID, DataUtil.getTimeDataObject());

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_TIME_SET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: DDC获取时间
	 *
	 * @auther: wanghongjie
	 * @date: 9:00 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject getTimeDDC(String fSysName,String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//获取当前DDC的属性信息
		Map<String,Object> DDCInfoMap = besSbdyMapper.DDCInfoMap(fSysName);
		if (DDCInfoMap == null || DDCInfoMap.isEmpty()){
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}

		String channelID = (String) DDCInfoMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		if (f_type.equals("2")) {

			boolean sendState = SendMsgHandler.getControllerTimeDDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_TIME_GET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else {

			boolean sendState = SendMsgHandler.getControllerTimeLDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_TIME_GET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 重启DDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 9:11 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject restartDDC(String fSysName,String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//获取当前DDC的属性信息
		Map<String,Object> DDCInfoMap = besSbdyMapper.DDCInfoMap(fSysName);
		if (DDCInfoMap == null || DDCInfoMap.isEmpty()){
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}

		String channelID = (String) DDCInfoMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		if (f_type.equals("2")) {

			boolean sendState = SendMsgHandler.restartControllerDDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_RESTART, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else {

			boolean sendState = SendMsgHandler.restartControllerLDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_RESTART, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 重置DDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 9:14 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject resetDDC(String fSysName,String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//获取当前DDC的属性信息
		Map<String,Object> DDCInfoMap = besSbdyMapper.DDCInfoMap(fSysName);
		if (DDCInfoMap == null || DDCInfoMap.isEmpty()){
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}

		String channelID = (String) DDCInfoMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		if (f_type.equals("2")) {

			boolean sendState = SendMsgHandler.resetControllerDDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_RESET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else {

			boolean sendState = SendMsgHandler.resetControllerLDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.CONTROLLER_RESET, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 删除楼控区域的节点
	 *
	 * @auther: wanghongjie
	 * @date: 11:20 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyArea(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();
		int deleteSbdyArea = 0;
		//查询当前区域下所有的子节点
		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);
		//删除当前区域

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.AREA))){//楼控区域的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyArea(object);

				}else {//DDC控制器的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyDDC(object);
				}
			}
		}

		deleteSbdyArea = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		if (deleteSbdyArea > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description:DDC控制器的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:06 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyDDC(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String tabName = "BES_DDC";

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前区域下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){
			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.VPOINT_NOPAGE))){//虚点总线的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyVBus(object);

				}else {//总线的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyBus(object);
				}
			}
		}
		//查询当前ddc在ddc表中的信息
		Map<String,Object> ddcPointMapByDDCTable =	besSbdyMapper.ddcPointMapByDDCTable(f_sys_name);
		String channelId = (String) ddcPointMapByDDCTable.get("F_CHANNEL_ID");
		//删除当前DDC在设备配置表
		int deleteSbdyArea = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		//删除当前DDC在DDC表
		int deleteSbdyDDC = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyArea > 0 && deleteSbdyDDC > 0){
			boolean sendState = SendMsgHandler.deleteControllerDDC(channelId);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_DELETE, channelId);

			returnObject.setStatus("1");
			returnObject.setMsg("删除成功,下发成功");
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description: 总线的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:52 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	public ISSPReturnObject delectSbdyBus(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();


		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前总线下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap == null || nodeMap.isEmpty()){
			besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
			return returnObject;
		}else {

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.LINE))){//线路的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyLine(object);

				}
			}
		}

		//删除当前总线在设备配置表
		int deleteSbdyBus = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		if (deleteSbdyBus > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description: 虚点总线的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:52 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	public ISSPReturnObject delectSbdyVBus(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();


		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前虚点总线下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.VPOINT))){//虚点的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyVpoint(object);
				}
			}
		}

		//删除当前虚点总线在设备配置表
		int deleteSbdyVBus = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		if (deleteSbdyVBus > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}
	/**
	 *
	 * @Description: 线路的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:44 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyLine(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();


		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前线路下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.MODULE))){//模块的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyModule(object);

				}
			}
		}

		//删除当前线路在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		if (deleteSbdyLine > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description: 模块的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:04 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyModule(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String tabName = "BES_MODULE";
		String channelId = "";

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前模块下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		Map<String,Object> pointMap =  besSbdyMapper.listpoint(f_sys_name);//查询点位在设备配置表的信息
		String f_node_attribution = (String) pointMap.get("F_NODE_ATTRIBUTION");
		if (f_node_attribution.equals("1")) {//楼控
			//查询当前模块所属的ip
			Map<String,Object> selectDDCByModuleFSysName = besSbdyMapper.selectDDCByModuleFSysName(f_sys_name);
			channelId = (String) selectDDCByModuleFSysName.get("F_CHANNEL_ID");

		}else if (f_node_attribution.equals("2")) {//照明
			//查询当前模块所属的ip(照明)
			Map<String,Object> selectDDCByModuleFSysName = besSbdyMapper.selectLDCByModuleFSysName(f_sys_name);
			channelId = (String) selectDDCByModuleFSysName.get("F_CHANNEL_ID");
		}




		List<Map<String,Object>> point = besSbdyMapper.selectPointMap(tabName,f_sys_name);
		String id = (String) point.get(0).get("F_STRUCT_ID");

		if (nodeMap != null || !nodeMap.isEmpty()){
			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.AI)) || node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.AO)) ||
						node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.DI)) || node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.DO)) ||
						node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.UI))
				){//模块下的点位节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyPoint(object);

				}
			}
		}

		//删除当前模块在设备配置表
		int deleteSbdyArea = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		//删除当前模块在模块表
		int deleteSbdyModule = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);

		if (deleteSbdyArea > 0 && deleteSbdyModule > 0){

			if (f_node_attribution.equals("1")) {//楼控

				boolean sendState = SendMsgHandler.deleteModuleDDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.MODULE_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			} else if (f_node_attribution.equals("2")){//照明

				boolean sendState = SendMsgHandler.deleteModuleLDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.MODULE_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			}


		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description: 模块下点位的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:47 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyPoint(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();
		List<Map<String,Object>> point = new ArrayList<Map<String,Object>>();
		String tabName = null;
		int deleteSbdyPointTable = 0;
		String channelId = "";

		if (obj.get("f_type").equals(Integer.valueOf(BesNodeType.AI))){
			 tabName = "BES_ANALOG_INPUT";
		}else if (obj.get("f_type").equals(Integer.valueOf(BesNodeType.AO))){
			 tabName = "BES_ANALOG_OUPUT";
		}else if (obj.get("f_type").equals(Integer.valueOf(BesNodeType.DI))){
			 tabName = "BES_DIGIT_INPUT";
		}else if (obj.get("f_type").equals(Integer.valueOf(BesNodeType.DO))){
			 tabName = "BES_DIGIT_OUPUT";
		}

		String f_sys_name_old = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name_old))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.listpoint(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}

		//查询该逻辑点所在的控制器的ip
		if (obj.get("f_node_attribution").equals("1")) {

			Map<String,Object> controllerPoint = besSbdyMapper.selectcontrollerPoint(f_sys_name_old);
			channelId = (String) controllerPoint.get("F_CHANNEL_ID");
		} else {

			Map<String,Object> controllerPoint = besSbdyMapper.selectcontrollerPointByDelete(f_sys_name_old);
			channelId = (String) controllerPoint.get("F_CHANNEL_ID");
		}


		//删除当前点位在设备配置表
		int deleteSbdyPoint = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name_old);
		//查询点前点位在相应的表中是否存在
		if (!obj.get("f_type").equals(Integer.valueOf(BesNodeType.UI))){
			point = besSbdyMapper.selectPointMap(tabName,f_sys_name_old);
		}

		if (point.size() != 0 && point != null){

			String id = (String) point.get(0).get("F_STRUCT_ID");
			//删除当前点位在相应的点位表
			deleteSbdyPointTable = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name_old);

			besSbdyMapper.delNodeCconfigSettingByFSysName(f_sys_name_old);//根据名称和表名删除设置配置表中相关的数据

			// 删除关联的电表
			if (obj.get("f_type").equals(Integer.valueOf(BesNodeType.AI))||obj.get("f_type").equals(Integer.valueOf(BesNodeType.AO))){


				//判断当前AI点是否在支路中配置过
				int count = besSbdyMapper.queryBranchAmmeterRlgl(f_sys_name_old);

				if (count > 0)
				{

					returnObject.setMsg(LEMSConstants.meter_branch);
					returnObject.setStatus("0");
					return returnObject;

				} else if (BesNodeType.AI.equals(point.get(0).get("F_NODE_TYPE"))
						&& 0 == (Integer) point.get(0).get("F_ENERGYSTATICS"))
				{
					besAmmeterMapper.deleteByPrimaryKey(f_sys_name_old);
				} else if (BesNodeType.AO.equals(point.get(0).get("F_NODE_TYPE"))
						&& 0 == (Integer) point.get(0).get("F_ENERGYSTATICS"))
				{
					besAmmeterMapper.deleteByPrimaryKey(f_sys_name_old);
				}
			}


			if (deleteSbdyPoint > 0 && deleteSbdyPointTable > 0){

				if (obj.get("f_node_attribution").equals("1")) {

					boolean sendState = SendMsgHandler.deletePointDDC(channelId, Integer.parseInt(id));

					if (!sendState)
					{
						returnObject.setStatus("1");
						returnObject.setMsg("删除成功,下发失败");

						return returnObject;
					}

					// 添加订阅消息
					MsgSubPubHandler.addSubMsg(DDCCmd.POINT_DELETE, channelId);

					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发成功");
				} else {

					boolean sendState = SendMsgHandler.deletePointLDC(channelId, Integer.parseInt(id));

					if (!sendState)
					{
						returnObject.setStatus("1");
						returnObject.setMsg("删除成功,下发失败");

						return returnObject;
					}

					// 添加订阅消息
					MsgSubPubHandler.addSubMsg(LDCCmd.POINT_DELETE, channelId);

					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发成功");
				}

			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
		}else {
			if (deleteSbdyPoint > 0){
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 虚点点位的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:48 2020/7/9
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyVpoint(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String tabName = "BES_VPOINT";

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}


		if(obj.getString("f_node_attribution") ==null || ("".equals(obj.getString("f_node_attribution")))) {
			Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(obj.getString("f_sys_name"));
			obj.put("f_node_attribution", pNodeInfo.get("F_NODE_ATTRIBUTION"));
		}


		//获取当前虚点的详细信息
		Map<String,Object> VPointTableMap = besSbdyMapper.VPointTableMap(f_sys_name);
		String id = (String) VPointTableMap.get("F_SBID");
		//获取当前虚点所在的ip详细信息
		Map<String,Object> VPointMap =besSbdyMapper.VPointMap(f_sys_name);
		String channelId = (String) VPointMap.get("F_CHANNEL_ID");

		//删除当前虚点在设备配置表
		int deleteSbdyVBus = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		int deleteSbdyVPoint = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);


		// 删除关联的电表
		if (PointType.POINT_TYPE_VIRTUAL_AI
				== Integer.parseInt((String)VPointTableMap.get("F_NODE_TYPE"))
				&& 0 == (Integer) VPointTableMap.get("F_ENERGYSTATICS"))
		{
			besAmmeterMapper.deleteByPrimaryKey(f_sys_name);
		}

		// 删除关联的调试配置参数
		besSbdyMapper.deleteNodesConfigSettingBySysName(f_sys_name);

		if (deleteSbdyVBus > 0 && deleteSbdyVPoint > 0){

			if (obj.getString("f_node_attribution").equals("1")) {//楼控

				boolean sendState = SendMsgHandler.deletePointDDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			} else if (obj.getString("f_node_attribution").equals("2")) {//照明

				boolean sendState = SendMsgHandler.deletePointLDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			}
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}

	/**
	 * 同步虚点信息
	 * @param f_sys_name
	 * @return
	 */
	@Override
	public ISSPReturnObject synVirtualPoint(String f_sys_name)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		if (!StringUtils.hasText(f_sys_name))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Map<String, Object> virtualPoint = crossEquipmentMapper.findVpointBySysName(f_sys_name);


		if(null == virtualPoint || virtualPoint.isEmpty())
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Integer pointType = Integer.parseInt((String) virtualPoint.get("F_NODE_TYPE"));

		Map<String, Object> treeNodeInfo = crossEquipmentMapper.getPointInfoSBDY(f_sys_name);

		String pSysName = (String) treeNodeInfo.get("F_PSYS_NAME");

		// 获取 tree ddc 信息
		Map<String, Object> treeDdcInfo = besSbdyMapper.getParentNodeInfo(pSysName);

		Map<String, Object>  ddcInfo = besDdcMapper.queryDDCInfo((String) treeDdcInfo.get("F_SYS_NAME"));

		String channelId = (String) ddcInfo.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelId))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		String id = (String)virtualPoint.get("F_SBID");


		Integer active = (Integer) virtualPoint.get("F_ENABLED");
		String name = (String)virtualPoint.get("F_SYS_NAME");
		String alias = (String)virtualPoint.get("F_NICK_NAME");
		String description = (String)virtualPoint.get("F_DESCRIPTION");
		String initValue = (String) virtualPoint.get("F_INIT_VAL");
		Integer alarmActive = (Integer) virtualPoint.get("F_ALARM_ENABLE");
		Integer alarmType = (Integer) virtualPoint.get("F_ALARM_TYPE");
		Integer alarmPriority = (Integer) virtualPoint.get("F_ALARM_PRIORITY");
		Integer precision = (Integer) virtualPoint.get("F_ACCURACY");

		Integer initVal;

		if (null == precision)
		{
			initVal = Integer.parseInt(initValue);
		}else
		{
			initVal = (int) Math.round(Double.parseDouble(initValue) * (Math.pow(10, (precision))));
		}

		if (treeDdcInfo.get("F_NODE_ATTRIBUTION").equals("1")) { //楼控

			PointParamDDC pointParam = new PointParamDDC();

			pointParam.setPointType(pointType);
			pointParam.setId(Integer.parseInt(id));
			pointParam.setActive(active);
			pointParam.setName(name);
			pointParam.setAlias(alias);
			pointParam.setDescription(description);
			pointParam.setInitValue(initVal);
			pointParam.setAlarmActive(alarmActive);
			pointParam.setAlarmType(alarmType);
			pointParam.setAlarmPriority(alarmPriority);

			if (PointType.POINT_TYPE_VIRTUAL_AI == pointType || PointType.POINT_TYPE_VIRTUAL_AO == pointType)
			{

				pointParam.setUnit((String) virtualPoint.get("F_ENGINEER_UNIT"));
				pointParam.setPrecision(precision);
				if (virtualPoint.get("F_ALARM_TYPE").equals("1")) {
					pointParam.setAlarmHighValue((Integer) virtualPoint.get("F_HIGH_LIMIT"));
					pointParam.setAlarmLowValue((Integer) virtualPoint.get("F_LOW_LIMIT"));
				} else {
					pointParam.setAlarmHighValue(0);
					pointParam.setAlarmLowValue(0);
				}


			}else
			{
				pointParam.setAlarmTrigger((Integer) virtualPoint.get("F_CLOSE_STATE"));
			}

			boolean sendState = SendMsgHandler.setPointDDC(channelId, pointParam);

			if (!sendState)
			{
				result.setStatus("0");
				result.setMsg("下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_SET, channelId);

			result.setStatus("1");
			result.setMsg("下发成功");
			return result;

		}


		if (treeDdcInfo.get("F_NODE_ATTRIBUTION").equals("2")) {//照明

			PointParamLDC pointParamLDC = new PointParamLDC();

			pointParamLDC.setPointType(pointType);
			pointParamLDC.setId(Integer.parseInt(id));
			pointParamLDC.setActive(active);
			pointParamLDC.setName(name);
			pointParamLDC.setAlias(alias);
			pointParamLDC.setDescription(description);
			pointParamLDC.setInitValue(initVal);
			pointParamLDC.setAlarmActive(alarmActive);
			pointParamLDC.setAlarmType(alarmType);
			pointParamLDC.setAlarmPriority(alarmPriority);

			if (PointType.POINT_TYPE_VIRTUAL_AI == pointType || PointType.POINT_TYPE_VIRTUAL_AO == pointType)
			{

				pointParamLDC.setUnit((String) virtualPoint.get("F_ENGINEER_UNIT"));
				pointParamLDC.setPrecision(precision);
				if (virtualPoint.get("F_ALARM_TYPE").equals("1")) {
					pointParamLDC.setAlarmHighValue((Integer) virtualPoint.get("F_HIGH_LIMIT"));
					pointParamLDC.setAlarmLowValue((Integer) virtualPoint.get("F_LOW_LIMIT"));
				} else {
					pointParamLDC.setAlarmHighValue(0);
					pointParamLDC.setAlarmLowValue(0);
				}


			}else
			{
				pointParamLDC.setAlarmTrigger((Integer) virtualPoint.get("F_CLOSE_STATE"));
			}

			boolean sendState = SendMsgHandler.setPointLDC(channelId, pointParamLDC);

			if (!sendState)
			{
				result.setStatus("0");
				result.setMsg("下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_SET, channelId);

			result.setStatus("1");
			result.setMsg("下发成功");
		}

		return result;
	}

	/**
	 *
	 * @Description: 逻辑点的删除操作
	 *
	 * @auther: wanghongjie
	 * @date: 9:45 2020/7/10
	 * @param: [f_sys_name, f_sbid, f_psys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject deletePointByF_sys_name(String f_sys_name, String f_sbid, String f_psys_name,String f_channel_index) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String f_nick_name 		= null;
		String path 			= null;
		String f_type 			= null;
		String f_descruption 	= "";
		String id 				= null;
		String channelId		= null;

		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}
		if (f_sys_name == null || f_sys_name.isEmpty() ||
				f_sbid == null || f_sbid.isEmpty()){
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}
		//根据父节点的名称查询模块类型表的信息
		Map<String,Object> selectModelTypeByPsysName = crossEquipmentMapper.selectModelTypeByPsysName(f_psys_name);

		String f_point_type_cl = (String) selectModelTypeByPsysName.get("F_POINT_TYPE_CL");

		String pathModle = (String) selectModelTypeByPsysName.get("F_ALLPATH");

		String pointType = f_point_type_cl.substring(Integer.parseInt(f_channel_index),Integer.parseInt(f_channel_index) + 1);

		//根据父节点的模块名称查询模块点的信息

		String tabName = besSbdyMapper.findNodeTableByFSysName(f_sys_name);//查询节点的表名

		Map<String,Object> pointMap = crossEquipmentMapper.selectPointMap(tabName,f_sys_name);//查询逻辑点的信息

		if (pointMap == null || pointMap.isEmpty())
		{
			returnObject.setMsg("删除失败");
			returnObject.setStatus("0");
			return returnObject;
		}

				id 				= (String) pointMap.get("F_STRUCT_ID");

		String f_sys_name_old	= (String) pointMap.get("F_SYS_NAME_OLD");

		// 删除关联的电表
		if (tabName.equals("BES_ANALOG_INPUT")||tabName.equals("BES_ANALOG_OUPUT")){//AI点或AO点的时候

			//判断当前AI点是否在支路中配置过
			int countBranch = besSbdyMapper.queryBranchAmmeterRlgl(f_sys_name_old);

			if (countBranch > 0)
			{
				returnObject.setMsg(LEMSConstants.meter_branch);
				returnObject.setStatus("0");
				return returnObject;

			}else if (pointMap.get("F_ENERGYSTATICS").equals(0)){//能耗统计为 0:是  的时候

				besAmmeterMapper.deleteByPrimaryKey(f_sys_name_old);//删除关联的电表
			}
		}

		String f_sys_name_sbdy = (String) pointMap.get("F_SYS_NAME");

		if (pointType.equals("0")) {
			f_nick_name = "AI类型";
			path = pathModle + ">AI类型";
		} else if (pointType.equals("1")) {
			f_nick_name = "AO类型";
			path = pathModle + ">AO类型";
		}else if (pointType.equals("2")) {
			f_nick_name = "DI类型";
			path = pathModle + ">DI类型";
		} else if (pointType.equals("3")) {
			f_nick_name = "DO类型";
			path = pathModle + ">DO类型";
		}else if (pointType.equals("4") || pointType.equals("5")) {

			if (tabName.equals("BES_DIGIT_OUPUT")){
				f_nick_name = "DO类型";
				path = pathModle + ">DO类型";
			}else if (tabName.equals("BES_DIGIT_INPUT")){
				f_nick_name = "UI类型";
				path = pathModle + ">UI类型";
				f_type = "14";
			}else if (tabName.equals("BES_ANALOG_OUPUT")){
				f_nick_name = "AO类型";
				path = pathModle + ">AO类型";
			}else if (tabName.equals("BES_ANALOG_INPUT")){
				f_nick_name = "UI类型";
				path = pathModle + ">UI类型";
				f_type = "14";
			}
		}

		Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(f_sys_name_sbdy);
		String f_node_attribution = (String) pNodeInfo.get("F_NODE_ATTRIBUTION");

		//查询该逻辑点所在的控制器的ip
		if (f_node_attribution.equals("1")) {//楼控

			Map<String,Object> controllerPoint = besSbdyMapper.selectcontrollerPoint(f_sys_name_old);
			channelId = (String) controllerPoint.get("F_CHANNEL_ID");
		} else if (f_node_attribution.equals("2")) {

			Map<String,Object> controllerPoint = besSbdyMapper.selectLDCByModuleFSysName(f_sys_name_old);
			channelId = (String) controllerPoint.get("F_CHANNEL_ID");
		}


		if (!StringUtils.hasText(channelId))
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}

		Map<String, String> nodeTypeMap = new HashMap<>();

		nodeTypeMap.put("f_type", f_type);
		returnObject.setData(nodeTypeMap);

		int count = besSbdyMapper.delSbTreeByFSysName(f_sys_name_old, tabName);//根据名称和表名删除节点的数据

		besSbdyMapper.delNodeCconfigSettingByFSysName(f_sys_name_old);//根据名称和表名删除设置配置表中相关的数据

		//修改设备配置表的点位信息
		int countTree = besSbdyMapper.updateSbTreeByFSysName(f_sys_name_sbdy,f_nick_name,path,f_type,f_descruption);//修改设备配置表的点位信息

		if (count > 0 && countTree > 0){

			if (f_node_attribution.equals("1")) {//楼控

				boolean sendState = SendMsgHandler.deletePointDDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(DDCCmd.POINT_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			} else if (f_node_attribution.equals("2")) {//照明

				boolean sendState = SendMsgHandler.deletePointLDC(channelId, Integer.parseInt(id));

				if (!sendState)
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功,下发失败");

					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(LDCCmd.POINT_DELETE, channelId);

				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");
			}

		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 电表的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 11:29 2020/7/13
	 * @param: [obj]insertNodesConfigSetting
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyAmmeter(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String sysName = obj.getString("f_sys_name");
		String tabName = "bes_ammeter";

		//删除之前先保存到电表删除记录表中
		Map<String,Object> map = new HashMap<>();
		map.put("sysName",sysName);
		Integer num = besAmmeterMapper.insertAmmeterDelete(map);

		Map<String, Object> ammeterData = null;
		Map<String, Object> collectorData = null;
		collectorData = besJobManagerMapper.queryCollectorByAmmeterSysName(sysName);
		ammeterData = besJobManagerMapper.queryAmmeterInfo(sysName);

		//删除上位机电表的信息
		//删除当前虚点在设备配置表
		int deleteSbdyVBus = besSbdyMapper.deleteNodeByFSysNameOld(sysName);
		int deleteSbdyVPoint = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,sysName);
		// 删除下位机电表
		if (null != ammeterData && null != collectorData && deleteSbdyVBus > 0 && deleteSbdyVPoint > 0) {

				Integer meterID = Integer.parseInt((String) ammeterData.get("F_SBID"));
				String channelId = (String) collectorData.get("F_CHANNEL_ID");
				// 删除电表
				if (!SendMsgHandler.deleteAmmeterEDC(channelId, meterID))
				{
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功，下发失败");
					return returnObject;
				}

				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_DELETE, channelId);
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");


		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败，下发失败");
			return returnObject;
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 17:26 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyBranchCoupler(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String tabName = "bes_coupler";
		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前线路下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.MODULE))){//模块的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyModule(object);

				}
			}
		}

		//删除当前线路在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		//删除支线耦合器当前在耦合器表
		int deleteSbdyCoupler = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyLine > 0 && deleteSbdyCoupler > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}


		return returnObject;
	}

	/**
	 *
	 * @Description: 干线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 17:26 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyTrunkCoupler(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String tabName = "bes_coupler";
		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前线路下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.SPUR_COUPLER))){//支线耦合器的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyBranchCoupler(object);

				}
			}
		}

		//删除当前线路在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		//删除支线耦合器当前在耦合器表
		int deleteSbdyCoupler = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyLine > 0 && deleteSbdyCoupler > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: IP路由器节点
	 *
	 * @auther: wanghongjie
	 * @date: 17:26 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyLDC(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String tabName = "bes_ddc";
		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前线路下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.TRUNK_COUPLER))){//干线耦合器的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyTrunkCoupler(object);

				}
				else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.SPUR_COUPLER))){//支线耦合器的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyBranchCoupler(object);

				}
				else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.MODULE))){//模块的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyModule(object);

				}else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.VPOINT_NOPAGE))){//虚点的节点(无属性页面)
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyVBus(object);

				}
			}
		}
		//查询ip路由器在DDC表中的信息
		List<Map<String,Object>> DDCMap = besSbdyMapper.selectPointMap(tabName,f_sys_name);
		String channelId = (String) DDCMap.get(0).get("F_CHANNEL_ID");

		//删除当前ip路由器在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		//删除ip路由器当前在DDC表
		int deleteSbdyCoupler = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyLine > 0 && deleteSbdyCoupler > 0){

			boolean sendState = SendMsgHandler.deleteControllerLDC(channelId);

			if (!sendState)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_DELETE, channelId);

			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 照明区域节点
	 *
	 * @auther: wanghongjie
	 * @date: 17:27 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyLAMP(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前线路下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.LAMP))){//照明区域节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyLAMP(object);
				} else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.ILLUMINATION))){//照明控制节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyLAMP(object);
				}
				else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.IP_ROUTER))){//IP路由器节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyLDC(object);
				}
			}
		}

		//删除当前ip路由器在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
//		//删除ip路由器当前在DDC表
//		int deleteSbdyCoupler = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyLine > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 根节点
	 *
	 * @auther: wanghongjie
	 * @date: 10:06 2020/9/23
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyPark(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前根节点下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.AREA))){//楼宇自控节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyArea(object);
				}
				else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.ILLUMINATION))){//照明控制节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyLAMP(object);
				}
				else if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.EDC))){//能耗采集节点

					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyEdc(object);
				}
			}
		}

		//删除当前根节点在设备配置表
		int deleteSbdyLine = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);

		if (deleteSbdyLine > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 能耗总线的删除
	 *
	 * @auther: wanghongjie
	 * @date: 14:52 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyEDCBus(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String f_sys_name = obj.getString("f_sys_name");
		String tabName = "bes_bus";
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前总线下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){
			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.AMMETER))){//线路的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyAmmeter(object);
				}
			}
		}

		//删除当前总线在设备配置表
		int deleteSbdyBus = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		int deleteSbdyVPoint = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (deleteSbdyBus > 0 && deleteSbdyVPoint > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}


	/**
	 *
	 * @Description: 采集器的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 14:02 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyCollector(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();
		String f_sys_name = obj.getString("f_sys_name");
		String tabName = "bes_collector";
		Map<String, Object> childCollector = besCollectorMapper.queryCollectorInfo(f_sys_name);

		//查询当前采集器下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){
			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.BUS_EDC))){//能耗总线的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyEDCBus(object);
				}
			}
		}

		//删除当前采集器在设备配置表
		int deleteSbdyPoint = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		int deleteSbdyCollector = besSbdyMapper.deleteTableNodeByFSysNameOld(tabName,f_sys_name);
		if (null != childCollector && !childCollector.isEmpty() && deleteSbdyPoint > 0 && deleteSbdyCollector > 0)
		{
			String channelId = (String) childCollector.get("F_CHANNEL_ID");

			// 删除一个控制器，并删除和它相关的电表
			if (SendMsgHandler.deleteControllerEDC(channelId))
			{
				// 添加订阅消息
				MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_DELETE, channelId);
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发成功");

			}else {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功,下发失败");
			}

		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败,下发失败");
		}
		return returnObject;
	}

	/**
	 * @Description: 能耗采集区域的删除逻辑
	 * @auther: wanghongjie
	 * @date: 14:55 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Transactional
	@Override
	public ISSPReturnObject delectSbdyEdc(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject object = new JSONObject();

		String f_sys_name = obj.getString("f_sys_name");
		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}
		//查询当前能耗采集区域下所有的子节点
		List<Map<String,Object>> nodeMap = besSbdyMapper.selectNodeMap(f_sys_name);

		if (nodeMap != null || !nodeMap.isEmpty()){

			for (Map<String,Object> node : nodeMap){
				if (node.get("F_TYPE").equals(Integer.valueOf(BesNodeType.COLLECTOR))){//采集器的节点
					object.put("f_sys_name",node.get("F_SYS_NAME_OLD"));
					object.put("f_type",node.get("F_TYPE"));
					delectSbdyCollector(object);
				}
			}
		}

		//删除当前能耗采集区域在设备配置表
		int deleteSbdyBus = besSbdyMapper.deleteNodeByFSysNameOld(f_sys_name);
		if (deleteSbdyBus > 0){
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}

		return returnObject;
	}


	/**
	 *
	 * @Description: 设置逻辑点的值，根据点的id
	 *
	 * @auther: wanghongjie
	 * @date: 13:54 2020/7/14
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject sbdy_put_up_point(JSONObject obj)  {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String f_node_attribution = "";
		Integer f_accuracy = null;

		String f_sys_name_old 	= obj.getString("f_sys_name");
		Integer f_struct_id		= obj.getInteger("f_struct_id");
		Double value 			= obj.getDouble("f_init_val");
		String id = null;
		Integer f_work_mode 	= obj.getInteger("f_work_mode");
		String tabName = null;
		Double accuracy;

		Map<String,Object> controllerPoint = null;

		if (f_sys_name_old == null)
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}

		Map<String,Object> pointMap = besSbdyMapper.sbpzStructPointMapByName(f_sys_name_old);

		if (pointMap == null || pointMap.isEmpty())
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}

		Map<String,Object> pointMaps = besSbdyMapper.sbpzStructPointMapsByName((String) pointMap.get("F_NODE_TABLE"),f_sys_name_old);//查询点位的信息
		if(pointMaps == null){
			returnObject.setMsg("当前点位未配置");
			returnObject.setStatus("0");
			return returnObject;
		}

		if (f_work_mode == null)
		{
			f_work_mode = (Integer) pointMaps.get("F_WORK_MODE");
		}

		f_node_attribution = (String) pointMap.get("F_NODE_ATTRIBUTION");
		id = String.valueOf(pointMap.get("F_ID"));
		tabName = (String) pointMap.get("F_NODE_TABLE");

		if (!"BES_VPOINT".equals(tabName))
		{
			//如果工作模式为空,则将查询的点位信息的工作模式
			if (f_work_mode == null) {
				f_work_mode = (Integer) pointMaps.get("F_WORK_MODE");
			}
			//查询该逻辑点所在的控制器的ip
			if (f_node_attribution.equals("1")) {//楼控节点

				controllerPoint = besSbdyMapper.selectcontrollerPoint(f_sys_name_old);

			} else if (f_node_attribution.equals("2")) {//照明节点

				controllerPoint = besSbdyMapper.selectcontrollerPointByDelete(f_sys_name_old);

			}

			if (pointMap.get("F_TYPE").equals(11)) {
				accuracy = Double.valueOf(String.valueOf(pointMaps.get("F_ACCURACY")));
				value = value * Math.pow(10,accuracy);
			}


		}else
		{
			if (f_node_attribution.equals("1")) {//楼控节点

				controllerPoint = besSbdyMapper.VPointMap(f_sys_name_old);

			} else if (f_node_attribution.equals("2")) {//照明节点

				controllerPoint = besSbdyMapper.selectcontrollerByLDCVpoint(f_sys_name_old);
			}

			if (pointMaps.get("F_NODE_TYPE").equals("5")) {
				accuracy = Double.valueOf(String.valueOf(pointMaps.get("F_ACCURACY")));
				value = value * Math.pow(10,accuracy);
			}

		}
		BigDecimal b = new BigDecimal(value);
		double   f1   =   b.setScale(0,   BigDecimal.ROUND_HALF_UP).doubleValue();
		String aa = String.valueOf(f1);
		Integer v = Integer.parseInt(aa.substring(0,aa.indexOf(".")));

		if (null == controllerPoint || controllerPoint.isEmpty())
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}

		String channelId = (String) controllerPoint.get("F_CHANNEL_ID");

		if(v == null){
			returnObject.setMsg("请输入值");
			returnObject.setStatus("0");
			return returnObject;
		}
		if (!StringUtils.hasText(channelId))
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}
		/*if ("0".equals(f_work_mode)){//自动状态,更改数据库的当前模式为手动

			besSbdyMapper.updatePointByWorkMode(tabName,updateWorkMode,id);
		}*/
		if ("BES_VPOINT".equals(tabName)) {
			f_work_mode = 0;
		}

		if (f_node_attribution.equals("1")) {//楼控

			PointDataDDC pointData = new PointDataDDC();
			pointData.setId(Integer.valueOf(id));
			pointData.setValue(v);
			pointData.setWorkMode(f_work_mode);
			boolean sendState = SendMsgHandler.setPointValueDDC(channelId, pointData);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_VALUE_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");

		} else if (f_node_attribution.equals("2")) {//照明

			PointDataLDC pointDataLDC = new PointDataLDC();
			pointDataLDC.setId(Integer.valueOf(id));
			pointDataLDC.setValue(v);
			pointDataLDC.setWorkMode(f_work_mode);
			boolean sendState = SendMsgHandler.setPointValueLDC(channelId, pointDataLDC);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_VALUE_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 设置逻辑点的值(根据名字)
	 *
	 * @auther: wanghongjie
	 * @date: 14:51 2020/8/7
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject sbdy_put_up_pointByName(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String f_node_attribution = "";
		Integer f_accuracy = null;

		String f_sys_name_old 	= obj.getString("f_sys_name");
		Integer value 			= obj.getInteger("f_init_val");
		String id = null;
		String f_work_mode 		= obj.getString("f_work_mode");
		String updateWorkMode 	= "1";
		String tabName = null;

		Map<String,Object> controllerPoint = null;
		Map<String,Object> pointMap = besSbdyMapper.sbpzStructPointMapByName(f_sys_name_old);

		if (pointMap != null && !pointMap.isEmpty()) {

			f_node_attribution = (String) pointMap.get("F_NODE_ATTRIBUTION");
			id = String.valueOf(pointMap.get("F_ID"));
			tabName = (String) pointMap.get("F_NODE_TABLE");
		}

		if (!"BES_VPOINT".equals(tabName))
		{
			//查询该逻辑点所在的控制器的ip
			if (f_node_attribution.equals("1")) {//楼控节点

				controllerPoint = besSbdyMapper.selectcontrollerPoint(f_sys_name_old);

			} else if (f_node_attribution.equals("2")) {//照明节点

				controllerPoint = besSbdyMapper.selectcontrollerPointByDelete(f_sys_name_old);

			}


		}else
		{
			if (f_node_attribution.equals("1")) {//楼控节点

				controllerPoint = besSbdyMapper.VPointMap(f_sys_name_old);

			} else if (f_node_attribution.equals("2")) {//照明节点

				controllerPoint = besSbdyMapper.selectcontrollerByLDCVpoint(f_sys_name_old);
			}

		}


		if (null == controllerPoint || controllerPoint.isEmpty())
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}

		String channelId = (String) controllerPoint.get("F_CHANNEL_ID");

		if(value == null){
			returnObject.setMsg("请输入值");
			returnObject.setStatus("0");
			return returnObject;
		}
		if (!StringUtils.hasText(channelId))
		{
			returnObject.setMsg("参数错误");
			returnObject.setStatus("0");
			return returnObject;
		}
		/*if ("0".equals(f_work_mode)){//自动状态,更改数据库的当前模式为手动

			besSbdyMapper.updatePointByWorkMode(tabName,updateWorkMode,id);
		}*/
		if (!"BES_VPOINT".equals(tabName)) {
			//更改实点点位的实时值
			besSbdyMapper.updatePointByid(tabName,id,value);
		} else {
			//更改虚点点位的实时值
			besSbdyMapper.updateVPointByid(tabName,id,value);
		}

		if (f_node_attribution.equals("1")) {

			PointDataDDC pointData = new PointDataDDC();
			pointData.setId(Integer.valueOf(id));
			pointData.setValue(value);
			boolean sendState = SendMsgHandler.setPointValueDDC(channelId, pointData);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_VALUE_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");

		} else if (f_node_attribution.equals("2")) {

			PointDataLDC pointDataLDC = new PointDataLDC();
			pointDataLDC.setId(Integer.valueOf(id));
			pointDataLDC.setValue(value);
			boolean sendState = SendMsgHandler.setPointValueLDC(channelId, pointDataLDC);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");

				return returnObject;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_VALUE_SET, channelId);
			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}

	/**
	 * 查询关联模块型号类型
	 * @param sysname
	 * @return
	 */
	@Override
	public ISSPReturnObject getRelativeModuleTypeInfo(String sysname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_moduleType = besSbdyMapper.getRelativeModuleTypeInfo(sysname);
		returnObject.setData(f_moduleType);
		returnObject.setMsg("查询成功");
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 * 查询关联点位类型
	 * @param sysname
	 * @return
	 */
	@Override
	public ISSPReturnObject getPointTypeInfo(String sysname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String, Object>> nodeTypeInfo = besSbdyMapper.getPointTypeInfo(sysname);
		returnObject.setList(nodeTypeInfo);
		returnObject.setMsg("查询成功");
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 * 查询关模块的点位信息
	 * @param sysname
	 * @return
	 */
	@Override
	public ISSPReturnObject getChildPointInfo(String sysname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESSbPzStruct>childNodeTypeInfo = besSbdyMapper.getChildPointInfo(sysname);
		returnObject.setList(childNodeTypeInfo);
		returnObject.setMsg("查询成功");
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 *
	 * @Description: 新增干线或者新增支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 11:49 2020/7/28
	 * @param:
	 * @return:
	 *
	 */
	@Override
	@Transactional
	public ISSPReturnObject sbdy_coupler_Insert(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		//查询bes_ddc表的最大的 	f_sbid
		String sbid 				= besSbdyMapper.select_f_sbid_By_Bes_Ddc();
		String str 					= LEMSConstants.Service_Success;

		String meterID 				= getAutoIncreaseCol(sbid);
		obj.put("f_sbid", meterID);
		String f_poll_status 		= "1";//是否轮训
		obj.put("f_poll_status",f_poll_status);
		String f_status 			= "1";//状态 0 离线 、 1 正常 默认正常
		obj.put("f_status",f_status);//状态 0 离线 、 1 正常 默认正常

		String f_sys_name 	= obj.getString("f_sys_name");
		String f_addr		= obj.getString("f_addr");
		if (!StringUtils.hasText(f_sys_name) || !StringUtils.hasText(f_addr))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		BESSbPzStruct besSbPzStruct = besSbdyMapper.getSbTreeDataBySysName(f_sys_name);

		if (null != besSbPzStruct)
		{
			returnObject.setMsg("系统名称已存在");
			returnObject.setStatus("0");
			return returnObject;
		}

		//判断通信地址是否重复
		List<Map<String,Object>> couplerLists = besSbdyMapper.selectCouplerList((String) obj.get("f_psys_name"));

		for (Map<String,Object> couplerList : couplerLists) {
			if (f_addr.equals(couplerList.get("F_ADDR"))) {

				returnObject.setMsg("通信地址重复");
				returnObject.setStatus("0");
				return returnObject;
			}
		}
		//查询父节点的系统名称标识
		String f_node_attribution 	= besSbdyMapper.selectFNodeAttributionByPsysName((String) obj.get("f_psys_name"));//所属系统 1代表cross : 楼控 ,2代表照明
		obj.put("f_node_attribution",f_node_attribution);//所属系统 1代表cross : 楼控,2代表照明
		//将数据保存到bes_sbdy_struct中
		besSbdyMapper.sbdy_ddcInfo_insert_bes_sbpz_struct(obj);
		int insertcoupler = besSbdyMapper.sbdy_couplerInfo_insert_bes_coupler(obj);
		if (insertcoupler > 0) {
			returnObject.setStatus("1");
			returnObject.setMsg("新增成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("新增失败");
		}
		return returnObject;
	}


	/**
	 *
	 * @Description: 修改干线或者新增支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 8:48 2020/7/29
	 * @param:
	 * @return:
	 *
	 */
	@Override
	@Transactional
	public ISSPReturnObject sbdy_coupler_Update(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String f_nick_name 	= obj.getString("f_nick_name");
		String f_azwz 		= obj.getString("f_azwz");
		String f_addr 		= obj.getString("f_addr");
		String f_description= obj.getString("f_description");//归属系统必填

		if (!StringUtils.hasText(f_description)) {
			returnObject.setStatus("0");
			returnObject.setMsg("归属系统未填写!");
			return returnObject;
		}

		// 查询当前节点在设备配置中的信息
		Map<String, Object> listpoint = besSbdyMapper.listpoint((String) obj.get("f_sys_name"));
		//修改的时候判断通信地址是否重复
		Map<String,Object> couplerMaps = besSbdyMapper.selectCouplerMap((String) obj.get("f_sys_name"));

		if (!f_addr.equals(couplerMaps.get("F_ADDR"))){
			List<Map<String,Object>> couplerChildList = besSbdyMapper.selectCouplerList((String) listpoint.get("F_PSYS_NAME"));

			for (Map<String,Object> couplerList : couplerChildList) {
				if (f_addr.equals(couplerList.get("F_ADDR"))) {

					returnObject.setMsg("通信地址重复");
					returnObject.setStatus("0");
					return returnObject;
				}
			}
		}

		String allpath = (String) obj.get("f_allpath");
		String  prefix = allpath.substring(0,allpath.lastIndexOf(">"));//截取别名前面的字符串

		String f_allpath = prefix + ">" + f_nick_name;
		obj.put("allpath",f_allpath);

		//查询当前支路或者干线下是否有子节点,如果有的话,则提示不能修改
		List<Map<String,Object>> couplerChildLists = besSbdyMapper.selectCouplerChildList((String) obj.get("f_sys_name"));

		if (couplerChildLists.size() > 0) {
			//查询当前通信地址是否修改
			Map<String,Object> couplerMap = besSbdyMapper.selectCouplerMap((String) obj.get("f_sys_name"));
			if (!couplerMap.get("F_ADDR").equals(obj.getString("f_addr"))) {

				returnObject.setMsg("有子节点,通信地址不可修改");
				returnObject.setStatus("0");
				return returnObject;
			}

		}

		//修改数据库节点信息
		int updateCouplerSbdyStruct = besSbdyMapper.updateCouplerSbdyStruct(obj);
		int updateCoupler = besSbdyMapper.updateCoupler(obj);

		if (updateCoupler > 0 && updateCouplerSbdyStruct > 0) {

			returnObject.setMsg("修改成功");
			returnObject.setStatus("1");
			return returnObject;
		} else {
			returnObject.setMsg("修改失败");
			returnObject.setStatus("0");
			return returnObject;
		}

	}

	/**
	 *
	 * @Description: 查询支线耦合器上一级的通信地址以及模块上两级的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 14:12 2020/7/29
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject select_branchCoupler(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String trunk_left = "";//干线耦合器的通信地址
		String branch_left = "";//支路耦合器的通信地址
		Map<String, Object> moduleMap = null;//模块
		Map<String, Object> trunkMap = null;//干线
		Map<String, Object> branchMap = null;//支线
		Map<String, Object> couplerMap = null;//耦合器表的详细信息
		//查询当前节点在设备配置表中的详细信息
		Map<String, Object> pointMap = besSbdyMapper.listpoint(f_sys_name);
		if (pointMap.get("F_TYPE").equals(6)) {//支线耦合器
			//根据父节点的名称查询父节点的详细信息
			trunkMap = besSbdyMapper.listpoint((String) pointMap.get("F_PSYS_NAME"));
			if (trunkMap.get("F_TYPE").equals(5)) {//干线耦合器
				//根据系统名称查询耦合器表中的点位详细数据
				couplerMap = besSbdyMapper.selectCouplerMap((String) trunkMap.get("F_SYS_NAME_OLD"));

				trunk_left = (String) couplerMap.get("F_ADDR");
			} else {
				trunk_left = "1";
			}

		} else if (pointMap.get("F_TYPE").equals(9)) {//模块
			//根据父节点的名称查询父节点的详细信息
			moduleMap = besSbdyMapper.listpoint((String) pointMap.get("F_PSYS_NAME"));
			if (moduleMap.get("F_TYPE").equals(6)) {//支线耦合器
				//根据系统名称查询耦合器表中的点位详细数据
				couplerMap = besSbdyMapper.selectCouplerMap((String) moduleMap.get("F_SYS_NAME_OLD"));

				branch_left = (String) couplerMap.get("F_ADDR");

				//根据父节点的名称查询父节点的详细信息
				branchMap = besSbdyMapper.listpoint((String) couplerMap.get("F_PSYS_NAME"));
				if (branchMap.get("F_TYPE").equals(5)) {//干线耦合器
					//根据系统名称查询耦合器表中的点位详细数据
					couplerMap = besSbdyMapper.selectCouplerMap((String) branchMap.get("F_SYS_NAME_OLD"));

					trunk_left = (String) couplerMap.get("F_ADDR");
				} else {
					trunk_left = "1";
				}
			} else {
				branch_left = "1";
			}

		}
		Map<String,Object> addrLeft = new HashMap<>();
		addrLeft.put("trunk_left",trunk_left);
		addrLeft.put("branch_left",branch_left);
		returnObject.setData(addrLeft);
		return returnObject;
	}

	/**
	 *
	 * @Description: 刚进入新增页面的时候,查出上级节点的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 15:46 2020/7/29
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject select_pre_branchCoupler(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String trunk_left = "";//干线耦合器的通信地址
		String branch_left = "";//支路耦合器的通信地址
		Map<String, Object> couplerMap = null;//耦合器表的详细信息
		Map<String, Object> couplerMaps = null;//耦合器表的详细信息
		Map<String, Object> branchMap = null;//支线
		couplerMap = besSbdyMapper.selectCouplerMap(f_sys_name);

		if (couplerMap == null ||  couplerMap.isEmpty()) {

			returnObject.setStatus("0");
		}else {

			if (couplerMap.get("F_NODE_TYPE").equals("5")){

				trunk_left = (String) couplerMap.get("F_ADDR");

			} else if (couplerMap.get("F_NODE_TYPE").equals("6")){

				//根据系统名称查询耦合器表中的点位详细数据
				couplerMaps = besSbdyMapper.selectCouplerMap((String) couplerMap.get("F_SYS_NAME_OLD"));

				branch_left = (String) couplerMaps.get("F_ADDR");

				//根据父节点的名称查询父节点的详细信息
				branchMap = besSbdyMapper.listpoint((String) couplerMaps.get("F_PSYS_NAME"));
				if (branchMap.get("F_TYPE").equals(5)) {//干线耦合器
					//根据系统名称查询耦合器表中的点位详细数据
					couplerMap = besSbdyMapper.selectCouplerMap((String) branchMap.get("F_SYS_NAME_OLD"));

					trunk_left = (String) couplerMap.get("F_ADDR");
				} else {
					trunk_left = "1";
				}
			} else {
				trunk_left = "1";
				branch_left = "1";
			}
			Map<String,Object> addrLeft = new HashMap<>();
			addrLeft.put("trunk_left",trunk_left);
			addrLeft.put("branch_left",branch_left);
			returnObject.setStatus("1");
			returnObject.setData(addrLeft);
		}

		return returnObject;
	}

	/**
	 *
	 * @Description: 根据父节点的名称,查询父节点的所属系统
	 *
	 * @auther: wanghongjie
	 * @date: 18:34 2020/7/29
	 * @param:
	 * @return:
	 *
	 */
	@Override
	public ISSPReturnObject selectPSysNameNodeAttribution(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据父节点的名称查询父节点的详细信息
		Map<String,Object> moduleMap = besSbdyMapper.listpoint(obj.getString("f_psys_name"));
		String f_node_attribution = (String) moduleMap.get("F_NODE_ATTRIBUTION");
		returnObject.setData(f_node_attribution);
		return returnObject;
	}

	/**
	 *
	 * @Description: 模块数据对比获取上位机的模块型号
	 *
	 * @auther: wanghongjie
	 * @date: 10:28 2020/8/12
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject getf_module_type(JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer modelID 			= obj.getInteger("f_module_type");//模块型号编码

		//根据前台传的模块型号编码查询模块类型表中的F_TYPE_CODE,这个字段才是下发到下位机的模块型号编码
		modelID = crossEquipmentMapper.queryTypeCodeByModuleType(modelID);

		if (modelID != null) {
			returnObject.setStatus("1");
			returnObject.setData(modelID);
		} else {

			returnObject.setStatus("0");
			returnObject.setMsg("当前模块的类型代码未定义");
		}
		return returnObject;
	}

	/**
	 *
	 * @Description: 查询园区的信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:10 2020/9/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject select_park() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESPark> besParks = besSbdyMapper.select_park();
		returnObject.setList(besParks);
		return returnObject;
	}

	/**
	 *
	 * @Description: 添加设备配置的根节点
	 *
	 * @auther: wanghongjie
	 * @date: 16:07 2020/9/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject insert_sbdy_node_first(String f_yqbh,String f_descrption,String f_nick_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String f_poll_status = "0";
		String f_allpath = "root>Cross工程";
		String f_type = "0";
		String f_issyn = "1";
		String f_psys_name = "root";
		String f_status = "1";

		//根据类型为0查询设备配置表的信息
		List<Map<String,Object>> pointList = besSbdyMapper.selectPointListByF_type(f_type);
		for (Map<String,Object> point : pointList) {
			if (point.get("f_sys_name").equals(f_yqbh)) {
				returnObject.setStatus("0");
				returnObject.setMsg("园区编号已添加");
				return  returnObject;
			}
		}

		//添加根节点信息
		Boolean insert_sbdy_node_first = besSbdyMapper.insert_sbdy_node_first(f_yqbh,f_descrption,f_nick_name,f_poll_status,f_allpath,f_type,f_issyn,f_psys_name,f_status);
		if (insert_sbdy_node_first) {

			// 添加到缓存
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(f_yqbh);
			if(sbPzStruct != null)
			{
				sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
			}

			returnObject.setMsg("添加成功");
			returnObject.setStatus("1");
		}
		return returnObject;
	}
}
