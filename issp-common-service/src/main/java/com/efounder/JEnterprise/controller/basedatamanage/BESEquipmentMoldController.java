package com.efounder.JEnterprise.controller.basedatamanage;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.model.BESEquipmentMold;
import com.efounder.JEnterprise.service.BESEquipmentMoldService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类名称：BESEquipmentMold 类描述：设备类型 创建人：tianjiangwei 修改人：tianjiangwei
 * 修改时间：2018年7月3日
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/equipmentmold")
public class BESEquipmentMoldController {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentMoldController.class);
	@Autowired
	private BESEquipmentMoldService besEquipmentMoldService;
	@Autowired
	private OperationConfig operationConfig;

	/**
	 * @Description 初始化设备类型界面
	 * @author tianjiangwei
	 * @param 未传参
	 * @return
	 */
	@RequestMapping(value = "/eq_type", method = RequestMethod.GET)
	public String geteq_type() {
		log.info("#BESEquipmentMold loding view/equipmentmold/eq_type");
		return "view/basedatamanage/eqmanage/equipmentMold";
	}

	/**
	 * 生成设备类型树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/eqType_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("NodeController获取信息");
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
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
		List<BESEquipmentMold> eqtypelist = besEquipmentMoldService.loadAll();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BESEquipmentMold eqtype : eqtypelist) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(eqtype.getF_type());
			node.setId(eqtype.getF_type());
			node.setPid(eqtype.getF_ptype());
			node.setText(eqtype.getF_sbmc());
			nodes.add(node);// 添加到节点容器
		}
		return ISSPTreeBuilder.buildTree(nodes);
	}

	/**
	 * 查找子类
	 * 
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/eqType_chlildtreegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentMold> list = new ArrayList<BESEquipmentMold>();
		list = besEquipmentMoldService.findChildByZtype(f_type);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_ptype() == null || "".equals(list.get(i).getF_ptype())) {
				list.get(i).setF_ptype(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 根据设备名称、类型查询设备类型信息
	 * 
	 * @param f_sbmc
	 * @return
	 */
	@RequestMapping(value = "/searcheqType", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getEqTypeList(String f_sbmc) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentMold> list = besEquipmentMoldService.getEqTypeList(f_sbmc);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_ptype() != null && !"".equals(list.get(i).getF_ptype())) {
				list.get(i).setF_ptype(list.get(i).getF_ptype());
			} else {
				list.get(i).setF_ptype(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * @Description ajax保存设备类型信息
	 * @author tianjiangwei
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/add_eqType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_eqType(BESEquipmentMold besEquipmentMold) {
		log.info("#BESEquipmentMoldController添加设备类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean isSucc = besEquipmentMoldService.add_eqType(besEquipmentMold);
			if ("1".equals(operationConfig.getSysDataBaseUseable())) {
				OperationLog.insert(besEquipmentMold.getF_type(), "bes_equipment_mold");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("添加设备类型成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				besEquipmentMold.setF_crdate(sdf.format(new Date()));
				besEquipmentMold.setF_chdate(sdf.format(new Date()));
				returnObject.setData(besEquipmentMold);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加设备类型失败");
			}
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("添加设备类型失败");
		}
		return returnObject;
	}

	/**
	 * ajax加载编辑对象
	 * 
	 * @param f_type
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadediteqType", method = RequestMethod.POST)
	@ResponseBody
	public BESEquipmentMold loadedit(@RequestBody BESEquipmentMold besEquipmentMold) {
		log.info("# ajax加载编辑设备类型信息");
		return besEquipmentMoldService.findeqTypeById(besEquipmentMold.getF_type());
	}

	/**
	 * 更新设备类型信息
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/edit_eqType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upeqType(BESEquipmentMold besEquipmentMold) {
		log.info("#BESEquipmentMoldControllers更新设备类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean isSucc = true;
			if ("1".equals(operationConfig.getSysDataBaseUseable())) {
				Map<String, Object> startMap = OperationLog.updateStart(besEquipmentMold.getF_type(), "bes_equipment_mold");
				isSucc = besEquipmentMoldService.upeqType(besEquipmentMold);
				OperationLog.updateEnd(besEquipmentMold.getF_type(), "bes_equipment_mold", startMap);
			} else {
				isSucc = besEquipmentMoldService.upeqType(besEquipmentMold);
			}
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				besEquipmentMold.setF_chdate(sdf.format(new Date()));
				returnObject.setStatus("1");
				returnObject.setMsg("更新设备类型成功");
				returnObject.setData(besEquipmentMold);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新设备类型失败");
			}
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("更新设备类型失败");
		}
		return returnObject;
	}

	/**
	 * @Description 删除设备类型信息
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "eqType_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject deleqType(@RequestBody BESEquipmentMold besEquipmentMold) {
		log.info("#BESEquipmentMoldControllers删除设备类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			if ("1".equals(operationConfig.getSysDataBaseUseable())) {
				OperationLog.delete(besEquipmentMold.getF_type(), "bes_equipment_mold");
			}
			boolean flag = besEquipmentMoldService.deleqType(besEquipmentMold);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	// 根据设备类型编号查询设备类型名称
	@RequestMapping(value = "/getEquipmentMoldById1", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getEquipmentMoldById(String f_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentMold> list = besEquipmentMoldService.getEquipmentMoldById(f_type);
		returnObject.setList(list);
		return returnObject;
	}
}
