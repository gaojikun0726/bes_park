package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESEquipmentTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESEquipmentTypeService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 设备型号接口实现类
 * @author LvSihan
 *
 */
@Service("BESEquipmentTypeService")
public class BESEquipmentTypeServiceImpl implements BESEquipmentTypeService, ESBaseService {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentTypeServiceImpl.class);

	@Autowired
	private BESEquipmentTypeMapper equipmentTypeMapper;
	@Autowired
	private OperationConfig operationConfig;

	/**
	 * 增加设备信息
	 */
	@Override
	public ISSPReturnObject addEquipmentType(BESEquipmentType equipmenttype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#EquipmentTypeMapper新增设备信息");
		try {
			String maxId = equipmentTypeMapper.getMaxId();
			String fId =  getAutoIncreaseCol(maxId);
			equipmenttype.setF_type(fId);
			boolean flag = equipmentTypeMapper.addEquipmentType(equipmenttype);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(equipmenttype.getF_type(), "bes_equipment_type");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			equipmenttype.setF_crdate(sdf.format(new Date()));
			equipmenttype.setF_chdate(sdf.format(new Date()));
			if (flag) {
				returnObject.setData(equipmenttype);
				returnObject.setStatus("1");
				returnObject.setMsg("添加设备型号信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加设备型号信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;

	}

	/**
	 * 删除设备信息
	 */
	@Override
	public ISSPReturnObject delEquipmentType(BESEquipmentType equipmenttype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#EquipmentTypeMapper删除设备信息");		
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(equipmenttype.getF_type(), "bes_equipment_type");
			}
			boolean flag = equipmentTypeMapper.delEquipmentType(equipmenttype);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除设备型号信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除设备型号信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 设备类型树
	 */
	@Override
	public ISSPReturnObject sblx_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据

		try {
			returnObject.setList(tree);
			returnObject.setMsg("获取设备类型树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取设备类型树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	private List<ISSPTreeNode> getTreeJson() {
		List<Map<String,Object>> subitemlist = equipmentTypeMapper.loadAll();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if(subitemlist.size() > 0) {
			for (Map map : subitemlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId((String)map.get("id"));
				node.setId((String)map.get("id"));
				node.setPid((String)map.get("pid"));
//				node.setPid("");
				node.setText((String)map.get("text"));
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
	@Cacheable(value = "users")
	public PageInfo<BESEquipmentType> findemTByPage(Integer bars,Integer pageNum, String f_sblxbh,String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		// 紧跟着的第一个select方法会被分页
		List<BESEquipmentType> equipmenttype = equipmentTypeMapper.findemTByPage(f_sblxbh,keywords);
		// 用PageInfo对结果进行包装
		PageInfo<BESEquipmentType> page = new PageInfo<BESEquipmentType>(equipmenttype);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}

	@Override
	public PageInfo<BESEquipmentType> findSblxPage(Integer pageNum, String f_sblxbh,String treeId) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<BESEquipmentType> equipmenttype = equipmentTypeMapper.findSblxPage(f_sblxbh,treeId);
		// 用PageInfo对结果进行包装
		PageInfo<BESEquipmentType> page = new PageInfo<BESEquipmentType>(equipmenttype);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}
	
	@Override
	public ISSPReturnObject findEmtById(String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#equipmentTypeMapper查询设备型号信息");
		try {
			BESEquipmentType returnData = equipmentTypeMapper.findEmtById(f_type);
			returnObject.setData(returnData);
			returnObject.setMsg("f_type 查询设备型号成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("f_type 查询设备型号失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject upEmt(BESEquipmentType equipmenttype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#equipmentTypeMapper更新设备型号信息");
		try {
			Map<String, Object> startMap = null;
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap= OperationLog.updateStart(equipmenttype.getF_type(), "bes_equipment_type");
				flag = equipmentTypeMapper.upEmt(equipmenttype);
				OperationLog.updateEnd(equipmenttype.getF_type(), "bes_equipment_type", startMap);
			}else{
				flag = equipmentTypeMapper.upEmt(equipmenttype);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			equipmenttype.setF_chdate(sdf.format(new Date()));
			if (flag) {
				returnObject.setData(equipmenttype);
				returnObject.setStatus("1");
				returnObject.setMsg("更新修改设备类型成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新修改设备类型失败");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 查询
	 */
	@Override
	public List<BESEquipmentType> getEmtList(Integer pageNum, String keywords) {
		// TODO Auto-generated method stub
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BESEquipmentType> equipmenttype = equipmentTypeMapper.findemTByPage(keywords);
		return equipmenttype;
	}
	
	/**
	 * 
	 */

	@Override
	public ISSPReturnObject seleqtypechildren(String f_sblxbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentType> list = new ArrayList<BESEquipmentType>();
		try {
			//String f_type = findAllAcess(fType);
			list = equipmentTypeMapper.seleqtypechildren(f_sblxbh);
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
	 * 根据设备型号编号查询设备类型编号
	 */
	@Override
	public List<BESEquipmentType> getbh(String f_type) {
		List<BESEquipmentType> list = equipmentTypeMapper.getbh(f_type);
		return list;
	}
	/**
	 * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
	 * @param col 该列当前最大值
	 * @return
	 */
	private String getAutoIncreaseCol(String col) {
		if (col == null || "".equals(col)) {
			return "00000000000";
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

	//树table
	@Override
	public ISSPReturnObject tree_table(String treeId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESEquipmentType> list = equipmentTypeMapper.tree_table(treeId);
			returnObject.setList(list);
			returnObject.setMsg("查找子类成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查找子类失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


}
