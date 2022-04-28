package com.efounder.JEnterprise.service.sysmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.metatype.Dto;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesZoneinfoMapper;
import com.efounder.JEnterprise.mapper.sysmanage.BesScheduleConfigMapper;
import com.efounder.JEnterprise.model.sysmanage.BesSchedule;
import com.efounder.JEnterprise.model.sysmanage.BesScheduleinfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl.CrossEquipmentServiceImpl;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl.LampEquipmentServiceImpl;
import com.efounder.JEnterprise.service.sysmanage.BESScheduleConfigService;
import com.framework.common.utils.Validate_y;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("BESScheduleConfigService")
public class BESScheduleConfigServiceImpl implements BESScheduleConfigService {

	@Autowired
	private BesScheduleConfigMapper besScheduleConfigMapper;
	@Autowired
	private LampEquipmentServiceImpl lampEquipmentServiceImpl;

	@Autowired
	private CrossEquipmentServiceImpl crossequipmentservice;

	@Autowired
	private BesZoneinfoMapper beszoneinfomapper;

	/**
	 * 生成计划任务树
	 */
	@Override
	public ISSPReturnObject getScheduleTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取计划任务树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取计划任务树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJson() {
		List<BesScheduleinfo> scheduleinfolist = besScheduleConfigMapper.getScheduleTreeList();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BesScheduleinfo scheduleinfo : scheduleinfolist) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(scheduleinfo.getfId());
			node.setId(scheduleinfo.getfId());
			node.setPid(scheduleinfo.getfParentid());
			node.setText(scheduleinfo.getfName());
			node.setNodeType(scheduleinfo.getfType());
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

	/**
	 * 查询计划任务分页列表
	 */
	@Override
	public PageInfo<BesSchedule> getScheduleList(String keywords, String fId, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BesSchedule> list = besScheduleConfigMapper.getScheduleList(keywords, fId);
		PageInfo<BesSchedule> page = new PageInfo<BesSchedule>(list);
		return page;
	}

	/**
	 * 添加计划任务节点
	 */
	@Override
	public ISSPReturnObject add_schedule(BesScheduleinfo besScheduleinfo) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		// 根据已有F_GUID生成
		String maxId = besScheduleConfigMapper.queryMaxId("bes_scheduleinfo");
		String f_guid = getAutoIncreaseCol(maxId);
		besScheduleinfo.setfId(f_guid);
		if (besScheduleinfo.getfType().equals("0")) {
			int ftype = Integer.parseInt(besScheduleinfo.getfType()) + 1;
			besScheduleinfo.setfType(String.valueOf(ftype));
		}
		boolean flag = besScheduleConfigMapper.add_schedule(besScheduleinfo);
		if (flag) {
			returnObject.setData(besScheduleinfo);
			returnObject.setStatus("1");
			returnObject.setMsg("添加成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("添加失败");
		}
		return returnObject;
	}

	/**
	 * 删除
	 */
	@Override
	public ISSPReturnObject del_schedule(String fId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besScheduleConfigMapper.del_schedule(fId);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	/**
	 * 新增计划任务详情
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public ISSPReturnObject add_scheduleTask(BesSchedule besSchedule) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		// 根据已有F_GUID生成
		String maxId = besScheduleConfigMapper.queryMaxId("bes_schedule");
		String f_guid = getAutoIncreaseCol(maxId);
		besSchedule.setfId(f_guid);
		//根据场景id查询场景类型
		String zoneType = besScheduleConfigMapper.queryZoneType(besSchedule.getfZoneid());
		besSchedule.setfZoneType(zoneType);
		boolean flag = besScheduleConfigMapper.add_scheduleTask(besSchedule);
		int fWeekmask = Integer.valueOf(besSchedule.getfWeekmask(),2);

		if (flag) {
			// 向下位机发送新增的信息
			// 获取场景所属的IP路由器信息
			Map<String, Object> DDCMap = besScheduleConfigMapper.queryDDCInfo(besSchedule.getfZoneid());
			data.put("ID", Integer.parseInt(besSchedule.getfId()));// 系统名称
			if(Validate_y.isNull(besSchedule.getfEnabled())){
				besSchedule.setfEnabled("1");
			}
			data.put("Active", Integer.parseInt(besSchedule.getfEnabled()));
			data.put("Name", besSchedule.getfSysName());
			data.put("Alias", besSchedule.getfNickName());// 别名
			data.put("PlanType", Integer.parseInt(besSchedule.getfIsholidays()));// 计划类型 常规计划或节假日计划

			data.put("StartDateYear", Integer.parseInt(besSchedule.getfStartDate().substring(2, 4).trim()));// 开始日期年 从"2018-10-09 19:17:20"中截取
			data.put("StartDateMonth", Integer.parseInt(besSchedule.getfStartDate().substring(5, 7).trim()));// 开始日期月
			data.put("StartDateDay", Integer.parseInt(besSchedule.getfStartDate().substring(8, 10).trim()));// 开始日期日
			data.put("EndDateYear", Integer.parseInt(besSchedule.getfEndDate().substring(2, 4).trim()));// 结束日期年
			data.put("EndDateMonth", Integer.parseInt(besSchedule.getfEndDate().substring(5, 7).trim()));// 结束日期月
			data.put("EndDateDay", Integer.parseInt(besSchedule.getfEndDate().substring(8, 10).trim()));// 结束日期日

			data.put("StartTimeHour", Integer.parseInt(besSchedule.getfStartDate().substring(10, 13).trim()));// 开始时间时
			data.put("StartTimeMinute", Integer.parseInt(besSchedule.getfStartDate().substring(14, 16).trim()));// 开始时间分
			data.put("StartTimeSecond", Integer.parseInt(besSchedule.getfStartDate().substring(17, 19).trim()));// 开始时间秒
			data.put("EndTimeHour", Integer.parseInt(besSchedule.getfEndDate().substring(10, 13).trim()));// 结束时间时
			data.put("EndTimeMinute", Integer.parseInt(besSchedule.getfEndDate().substring(14, 16).trim()));// 结束时间分
			data.put("EndTimeSecond", Integer.parseInt(besSchedule.getfEndDate().substring(17, 19).trim()));// 结束时间秒

			data.put("ExecutionWay", Integer.parseInt(besSchedule.getfIsspan()));// 执行方式（每天执行、持续执行）
			data.put("WeekMask", fWeekmask);// 周掩码
			data.put("SceneType", Integer.parseInt(besSchedule.getfZoneType()));// 场景类型，控制场景或采集场景
			data.put("SceneID", Integer.parseInt(besSchedule.getfZoneid()));// 场景ID号
			data.put("ModeID", Integer.parseInt(besSchedule.getfZonemodeId()));// 模式ID号
			//data.put("ModeID",2);// 模式ID号
			data.put("DefaultModeID", 1);// 默认模式ID

			pMap.put("IPAddr", DDCMap.get("F_IP_ADDR"));// ip地址
			pMap.put("Port", DDCMap.get("F_PORT"));// 端口号
			pMap.put("data", data);

			Dto retDto = crossequipmentservice.operCrossController(pMap, 26);// 同步逻辑点信息，index设置为26
			int reStr = retDto.getAsInteger("code");

			if (reStr == 0) {//向下位机发送成功
				returnObject.setData(besSchedule);
				returnObject.setStatus("1");
				returnObject.setMsg("操作成功");
			} else {
				returnObject.setData(besSchedule);
				returnObject.setStatus("0");
				returnObject.setMsg("下位机添加失败");
			}
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("新增失败");
		}
		return returnObject;
	}

	public static void main(String[] args) {
		String aaa = "2018-10-09 19:17:20";
		System.err.println("年:==>"+aaa.substring(2,4));
		System.err.println("月:==>"+aaa.substring(5,7));
		System.err.println("日:==>"+aaa.substring(8,10));
		System.err.println("时:==>"+aaa.substring(10,13));
		System.err.println("分:==>"+aaa.substring(14,16));
		System.err.println("秒:==>"+aaa.substring(17,19));

		// 2进制转10进制
		String ss = Integer.valueOf("00001100",2).toString();
		// 10进制转16进制
		Integer.toHexString(Integer.parseInt(ss));
		System.err.println("2进制转10进制-10进制转16进制==>"+Integer.toHexString(Integer.parseInt(ss)));

		String a = " 133";
		System.err.println("a==>"+a);
		System.err.println("a==>"+a.trim());
	}
	/**
	 * 查询计划详情
	 */
	@Override
	public ISSPReturnObject get_scheduleTask(String fSysname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BesSchedule besSchedule = besScheduleConfigMapper.get_scheduleTask(fSysname);
			returnObject.setData(besSchedule);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	/**
	 * 更新一条计划详情
	 */
	@Override
	public ISSPReturnObject editscheduleTask(BesSchedule besSchedule) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据场景id查询场景类型
		String zoneType = besScheduleConfigMapper.queryZoneType(besSchedule.getfZoneid());
		besSchedule.setfZoneType(zoneType);
		boolean flag = besScheduleConfigMapper.editscheduleTask(besSchedule);
		if (flag) {
			returnObject.setData(besSchedule);
			returnObject.setStatus("1");
			returnObject.setMsg("更新成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新失败");
		}
		return returnObject;
	}
	/**
	 * 删除计划详情
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public ISSPReturnObject del_scheduletask(String fSysname) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询该计划属性信息
		BesSchedule scheduletask = besScheduleConfigMapper.get_scheduleTask(fSysname);
		// 获取场景所属的IP路由器信息
		Map<String, Object> DDCMap = besScheduleConfigMapper.queryDDCInfo(scheduletask.getfZoneid());
		boolean flag = besScheduleConfigMapper.del_scheduletask(fSysname);
		if (flag) {
			data.put("ID", Integer.parseInt(scheduletask.getfId()));// 计划的ID
			pMap.put("IPAddr", DDCMap.get("F_IP_ADDR"));// ip地址
			pMap.put("Port", DDCMap.get("F_PORT"));// 端口号
			pMap.put("data", data);
			Dto retDto = crossequipmentservice.operCrossController(pMap, 28);// 同步逻辑点信息，index设置为16
			int reStr = retDto.getAsInteger("code");
			if (reStr == 0) {//向下位机发送成功
				returnObject.setStatus("1");
				returnObject.setMsg("操作成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机删除失败");
			}
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	/**
	 * 同步计划详情
	 */
	@Override
	public ISSPReturnObject syn_scheduletask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询该计划属性信息
		BesSchedule besSchedule = besScheduleConfigMapper.get_scheduleTask(fSysName);
		// 获取场景所属的IP路由器信息
		Map<String, Object> DDCMap = besScheduleConfigMapper.queryDDCInfo(besSchedule.getfZoneid());

		int fWeekmask = Integer.valueOf(besSchedule.getfWeekmask(),2);
		data.put("ID", Integer.parseInt(besSchedule.getfId()));// 系统名称
		data.put("Active", Integer.parseInt(besSchedule.getfEnabled()));
		data.put("Name", besSchedule.getfSysName());
		data.put("Alias", besSchedule.getfNickName());// 别名
		data.put("PlanType", Integer.parseInt(besSchedule.getfIsholidays()));// 计划类型 常规计划或节假日计划

		data.put("StartDateYear", Integer.parseInt(besSchedule.getfStartDate().substring(2, 4).trim()));// 开始日期年 从"2018-10-09 19:17:20"中截取
		data.put("StartDateMonth", Integer.parseInt(besSchedule.getfStartDate().substring(5, 7).trim()));// 开始日期月
		data.put("StartDateDay", Integer.parseInt(besSchedule.getfStartDate().substring(8, 10).trim()));// 开始日期日
		data.put("EndDateYear", Integer.parseInt(besSchedule.getfEndDate().substring(2, 4).trim()));// 结束日期年
		data.put("EndDateMonth", Integer.parseInt(besSchedule.getfEndDate().substring(5, 7).trim()));// 结束日期月
		data.put("EndDateDay", Integer.parseInt(besSchedule.getfEndDate().substring(8, 10).trim()));// 结束日期日

		data.put("StartTimeHour", Integer.parseInt(besSchedule.getfStartDate().substring(10, 13).trim()));// 开始时间时
		data.put("StartTimeMinute", Integer.parseInt(besSchedule.getfStartDate().substring(14, 16).trim()));// 开始时间分
		data.put("StartTimeSecond", Integer.parseInt(besSchedule.getfStartDate().substring(17, 19).trim()));// 开始时间秒
		data.put("EndTimeHour", Integer.parseInt(besSchedule.getfEndDate().substring(10, 13).trim()));// 结束时间时
		data.put("EndTimeMinute", Integer.parseInt(besSchedule.getfEndDate().substring(14, 16).trim()));// 结束时间分
		data.put("EndTimeSecond", Integer.parseInt(besSchedule.getfEndDate().substring(17, 19).trim()));// 结束时间秒

		data.put("ExecutionWay", Integer.parseInt(besSchedule.getfIsspan()));// 执行方式（每天执行、持续执行）
		data.put("WeekMask", fWeekmask);// 周掩码
		data.put("SceneType", Integer.parseInt(besSchedule.getfZoneType()));// 场景类型，控制场景或采集场景
		data.put("SceneID", Integer.parseInt(besSchedule.getfZoneid()));// 场景ID号
		data.put("ModeID", Integer.parseInt(besSchedule.getfZonemodeId()));// 模式ID号
		//data.put("ModeID", 2);// 模式ID号
		data.put("DefaultModeID", 1);// 默认模式ID

		pMap.put("IPAddr", DDCMap.get("F_IP_ADDR"));// ip地址
		pMap.put("Port", DDCMap.get("F_PORT"));// 端口号
		pMap.put("data", data);
		
		Dto retDto = crossequipmentservice.operCrossController(pMap, 27);// 同步计划详情，index设置为27
		int reStr = retDto.getAsInteger("code");

		if (reStr == 0) {//向下位机发送成功
			returnObject.setStatus("1");
			returnObject.setMsg("同步成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("同步失败");
		}
		return returnObject;
	}
	/**
	 * 获取下位机计划详情
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject get_underscheduleTask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询该计划属性信息
		BesSchedule besSchedule = besScheduleConfigMapper.get_scheduleTask(fSysName);
		// 获取场景所属的IP路由器信息
		Map<String, Object> DDCMap = besScheduleConfigMapper.queryDDCInfo(besSchedule.getfZoneid());
		data.put("ID", Integer.parseInt(besSchedule.getfId()));// 计划的ID
		pMap.put("IPAddr", DDCMap.get("F_IP_ADDR"));// ip地址
		pMap.put("Port", DDCMap.get("F_PORT"));// 端口号
		pMap.put("data", data);
		Dto retDto = crossequipmentservice.operCrossController(pMap, 29);// 获取计划详情，index设置为29
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			Map<String, Object> xDto = (Map<String, Object>) retDto.get("data");
			System.err.println("xDto==>"+xDto);
			xDto.put("Active", xDto.get("Active").equals(1) ? "是" : "否");
			returnObject.setData(xDto);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("下位机获取失败");
		}
		return returnObject;
	}

	/**
	 * 场景模式下拉框
	 */
	@Override
	public ISSPReturnObject loadZoneMode(String fZoneid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String, Object>> zoneModeList = new ArrayList<>();
		try {
			//Map<String, String> zoneMode = besScheduleConfigMapper.loadZoneMode(fZoneid);
			Map<String,String> zoneMode = beszoneinfomapper.interface_modeMap(fZoneid);
			//"0"为控制场景
			String F_PARAM = zoneMode.get("F_PARAM");
			if(Validate_y.noNull(F_PARAM)){
				F_PARAM = F_PARAM.substring(F_PARAM.indexOf("#")+1,F_PARAM.length());
				String ModeArray [] = F_PARAM.split("#");
				for(int i=0;i<ModeArray.length;i++){
					Map<String,Object> CollectModeMap=new HashMap<>();
					Map<String, Object> modemap = new HashMap<>();
					modemap.put("modeID", (i+1));
					modemap.put("modeval", ModeArray[i]);
					zoneModeList.add(i, modemap);
				}
			}
			returnObject.setList(zoneModeList);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}


	/**
	 * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
	 * 
	 * @param col
	 *            该列当前最大值
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
}
