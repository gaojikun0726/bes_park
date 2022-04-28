package com.efounder.opc;

import com.core.common.conn.db.MySQLDBObject;
import com.core.common.json.JsonHelper;
import com.core.common.metatype.Dto;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.collectorJob.CollectorJob;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.opc.OpcDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.framework.common.utils.Validate_y;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testGsoap.LemsService;

import javax.annotation.PostConstruct;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * OPC数据
 *
 * @author LvSihan
 * @date 2019年4月22日
 * @version 1.0
 */

@Component
@Slf4j
public class OpcData_cross {
	private static final Logger logger = LoggerFactory.getLogger(OpcData_cross.class);

	private static OpcData_cross opcData_cross;
	@Autowired
	private OpcDataMapper opcDataMapper;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private MySQLDBObject mysqlDBObject;
	@PostConstruct
	public void init() {
		opcData_cross = this;
		opcData_cross.opcDataMapper = this.opcDataMapper;
		opcData_cross.besSbdyMapper = this.besSbdyMapper;
	}

	/**
	 * 新增opc数据
	 * @param f_sys_name
	 */
	public void addOpcData(String f_sys_name) {
		Map<String,String> allData = new HashMap<>();
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcDataMapper.getNodeTable(f_sys_name);
		//单个节点属性
		Map<String,String> nodeData = opcDataMapper.getNodeData(nodeInfo.get("F_SYS_NAME"),nodeInfo.get("F_NODE_TABLE"));
		if(Validate_y.noNull(nodeData)) {
			allData.put("o_key", nodeData.get("F_SYS_NAME"));
			allData.put("o_val", String.valueOf(nodeData.get("F_INIT_VAL")));
			allData.put("o_unit", nodeData.get("F_ENGINEER_UNIT"));
			allData.put("o_descripe", nodeData.get("F_DESCRIPTION"));
			//判断是否已有该条数据
			if(queryOpcData(nodeData.get("F_SYS_NAME")) == 0) {
				//写入数据库
				opcDataMapper.insertData(allData);
			}else {
				//更新该条数据
				opcDataMapper.editData(allData);
			}
			//发送opc数据
			sendOpc(allData);
		}
	}
	/**
	 * 更新OPC逻辑点值
	 * @param besSbPzStruct
	 */
	public static void editOpcInitVal(BESSbPzStruct besSbPzStruct) {
		Map<String,String> allData = new HashMap<>();
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcData_cross.opcDataMapper.getNodeTable(besSbPzStruct.getF_sys_name());
		//单个节点属性
		Map<String,String> nodeData = opcData_cross.opcDataMapper.getNodeData(nodeInfo.get("F_SYS_NAME"),nodeInfo.get("F_NODE_TABLE"));
		if(Validate_y.noNull(nodeData)) {
			allData.put("o_val", besSbPzStruct.getF_init_val());
			//allData.put("o_key", besSbPzStruct.getF_nick_name());
			allData.put("o_key", besSbPzStruct.getF_sys_name()+"*"+besSbPzStruct.getF_nick_name());
			allData.put("o_unit", nodeData.get("F_ENGINEER_UNIT"));
			allData.put("o_descripe", nodeData.get("F_DESCRIPTION"));
			//判断是否已有该条数据
//			if(queryOpcData(besSbPzStruct.getF_sys_name()) == 0) {
//				//写入数据库
//				opcData_cross.opcDataMapper.insertData(allData);
//			}else {
//				//更新该条数据
//				opcData_cross.opcDataMapper.editData(allData);
//			}
			//发送opc数据
			sendOpc(allData);
		}
	}

	/**
	 * 廉政楼控发送给OPC的数据
	 * @param besSbPzStruct
	 */
	public static void editOpcHWInitVal(BESSbPzStruct besSbPzStruct) {// TODO
		Map<String,String> allData = new HashMap<>();
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcData_cross.opcDataMapper.getNodeTable(besSbPzStruct.getF_sys_name());
		//单个节点属性
		Map<String,String> nodeData = opcData_cross.opcDataMapper.getNodeData(nodeInfo.get("F_SYS_NAME"),nodeInfo.get("F_NODE_TABLE"));
		if(Validate_y.noNull(nodeData) && (besSbPzStruct.getF_status().equals("0") || besSbPzStruct.getF_status().equals("1"))) {
			//allData.put("o_val", besSbPzStruct.getF_status().equals("0")?"离线":(besSbPzStruct.getF_status().equals("2"))?"报警":"");
			/*if(besSbPzStruct.getF_status().equals("0")){
				besSbPzStruct.setF_status("1");
			}*/
			// 数据库  0 离线   1 在线
			// OPC数据 0 在线   1 离线
			allData.put("o_val", besSbPzStruct.getF_status().equals("0")?"1": "0");
			//allData.put("o_key", besSbPzStruct.getF_nick_name());
			allData.put("o_key", besSbPzStruct.getF_sys_name()+"--"+besSbPzStruct.getF_nick_name()+"**");
			allData.put("o_unit", nodeData.get("F_ENGINEER_UNIT"));
			allData.put("o_descripe", nodeData.get("F_DESCRIPTION"));
			//判断是否已有该条数据
//			if(queryOpcData(besSbPzStruct.getF_sys_name()) == 0) {
//				//写入数据库
//				opcData_cross.opcDataMapper.insertData(allData);
//			}else {
//				//更新该条数据
//				opcData_cross.opcDataMapper.editData(allData);
//			}
			//发送opc数据
			sendOpc(allData);
		}
	}
	public static void editOpcInitVal_energy(Dto dtoIn, String[] dataArray, String[] unitArray, String[] descArray) {
		Map<String,String> allData = new HashMap<>();
		//单个节点属性
		Map<String,String> nodeData = opcData_cross.opcDataMapper.getNodeData(dtoIn.getAsString("meteruuid"),"bes_ammeter");
		if(Validate_y.noNull(nodeData)) {
//			allData.put("o_val", dtoIn.getAsString("data"));
//			allData.put("o_key", dtoIn.getAsString("meteruuid"));
//			allData.put("o_unit",  dtoIn.getAsString("unit"));
//			allData.put("o_descripe", nodeData.get("F_ENGINEER_UNIT"));

			allData.put("o_val", StringUtils.join(dataArray, ","));
			allData.put("o_key", dtoIn.getAsString("meteruuid"));
			allData.put("o_unit",  StringUtils.join(unitArray, ","));
			allData.put("o_descripe", StringUtils.join(descArray, ","));
			//发送opc数据
			sendOpc(allData);
		}
	}

	public static void editOpcInitVal_energySingl(Dto dtoIn, String dataArray, String unitArray, String descArray) {
		Map<String,String> allData = new HashMap<>();
		//单个节点属性
		Map<String,String> nodeData = opcData_cross.opcDataMapper.getNodeData(dtoIn.getAsString("meteruuid"),"bes_ammeter");
		if(Validate_y.noNull(nodeData)) {
//			allData.put("o_val", dtoIn.getAsString("data"));
//			allData.put("o_key", dtoIn.getAsString("meteruuid"));
//			allData.put("o_unit",  dtoIn.getAsString("unit"));
//			allData.put("o_descripe", nodeData.get("F_ENGINEER_UNIT"));

			allData.put("o_val", dtoIn.getAsString("data"));
			//allData.put("o_key", dtoIn.getAsString("meteruuid"));
			allData.put("o_key", dtoIn.getAsString("meteruuid")+"*"+dtoIn.getAsString("dnmc"));
			allData.put("o_unit",  dtoIn.getAsString("unit"));
			if(Validate_y.noNull(nodeData.get("F_DESCRIPTION"))) {
				allData.put("o_descripe", nodeData.get("F_DESCRIPTION"));
			}else {
				allData.put("o_descripe", "");
			}
			//发送opc数据
			// sendOpc(allData);// 修改为批量发送
			CollectorJob.opcData.add(allData);
		}
	}
	/**
	 * 向OPCServer发送数据
	 * @param allData
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private static void sendOpc(Map<String, String> allData) { // TODO sendOpc
		Map<String, Object> pMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> opcdata = new HashMap<>();
		opcdata.put("o_type" , "11");
		opcdata.put("o_key", allData.get("o_key"));
		opcdata.put("o_val", allData.get("o_val"));
		opcdata.put("o_unit", String.valueOf(allData.get("o_unit")));
		opcdata.put("o_descripe", allData.get("o_descripe"));
		dataList.add(opcdata);
		pMap.put("data", dataList);
		pMap.put("key", "setopctag");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		LemsService  lemService = new LemsService();
		try {
			String	reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getZCOpcServer(), LEMSUtil.getServiceTimeOut());
//			logger.info("*******************************"+new String(jsonStr.getBytes("GBK"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "static-access", "unused" })
	public static void sendOpcList(List<Map<String, String>> allDataList) { // TODO sendOpc

		Map<String, Object> pMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();

		for(Map<String, String> allData: allDataList){
			Map<String, Object> opcdata = new HashMap<>();
			opcdata.put("o_type" , "11");
			opcdata.put("o_key", allData.get("o_key"));
			opcdata.put("o_val", allData.get("o_val"));
			opcdata.put("o_unit", String.valueOf(allData.get("o_unit")));
			opcdata.put("o_descripe", allData.get("o_descripe"));
			dataList.add(opcdata);
		}

		pMap.put("data", dataList);
		pMap.put("key", "setopctag");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		LemsService  lemService = new LemsService();
		try {
			String	reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getZCOpcServer(), LEMSUtil.getServiceTimeOut());
//			logger.info("*******************************"+new String(jsonStr.getBytes("GBK"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 向OPCServer发送数据
	 * @param allData
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private static void sendOpct(Map<String, Object> allData) {
		Map<String, Object> pMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> opcdata = new HashMap<>();
		opcdata.put("o_type" , "11");
		opcdata.put("o_key", allData.get("o_key"));
		opcdata.put("o_val", String.valueOf(allData.get("o_val")));
		opcdata.put("o_unit", String.valueOf(allData.get("o_unit")));
		opcdata.put("o_descripe", String.valueOf(allData.get("o_descripe")));
		dataList.add(opcdata);
		pMap.put("data", dataList);
		pMap.put("key", "setopctag");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		LemsService  lemService = new LemsService();
		try {
			String	reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getZCOpcServer(), LEMSUtil.getServiceTimeOut());
		} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//接收到tcp连接时自动发送数据
	@SuppressWarnings("unchecked")
	public static void dataAnalysis(Object msg) {
		Map<String, String> receMap = new HashMap<>();
		try {
			receMap = parseReturnStr((String) msg);
		} catch (Exception e) {
			log.info(String.valueOf(msg));
		}
		String key = receMap.get("key");
		if(key.equals("connect")) {
			//发送所有opc数据
			syncOpc();
		}
	}
	/**
	 * 同步OPC数据
	 * @return
	 */
	public static void syncOpc() {
		//查询数据库中所有OPC数据
		List<Map<String,String>> opcDataList = opcData_cross.opcDataMapper.getOpcData();
		//发送给OPCServer
		for(int i=0;i<opcDataList.size();i++) {
			//发送opc数据
			sendOpc(opcDataList.get(i));
		}
	}
	/**
	 * string转map
	 * @param reStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map parseReturnStr(String reStr) {
		Map<String,String> pMap= new HashMap<>();
		if (!Validate_y.isNullOrEmpty(reStr) && !reStr.isEmpty()) {
			pMap = getOjectFromJson(reStr);
		} else {
			pMap.put("type", "errmsg");
		}
		return pMap;
	}
	@SuppressWarnings({ "rawtypes" })
	public static Map getOjectFromJson(String jsonStr) {
		Map<String,Object> pMap= new HashMap<>();
		try {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonStr);
			for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				pMap.put(key, jsonObject.get(key));
			}
		} catch (Exception e) {
			pMap.put("type", "errmsg");
		}
		return pMap;
	}
	/**
	 * 根据o_key查询该条数据
	 * @param o_key
	 * @return
	 */
	private static int queryOpcData(String o_key) {
		List<Map<String,String>> opcDataList = opcData_cross.opcDataMapper.queryOpcData(o_key);
		int num = 0;
		if(opcDataList.size() == 0) {
			return num;
		}else {
			num = opcDataList.size();
		}
		return num;
	}
	/**
	 * 获取当前连接的数据库名
	 * @return
	 */
	private String getDbName() {
		Connection conn = mysqlDBObject.getConnection();
		String dbname = "";
		try {
			dbname = conn.getCatalog();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			dbname = "bes";
		}
		return dbname;
	}
}
