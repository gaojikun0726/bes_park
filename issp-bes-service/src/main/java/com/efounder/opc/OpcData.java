package com.efounder.opc;

import com.alibaba.fastjson.JSONObject;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.json.JsonHelper;
import com.core.common.metatype.Dto;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.opc.OpcDataMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.framework.common.utils.Validate_y;
import lombok.extern.slf4j.Slf4j;
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
 * OPC数据CRUD
 * 包括DDC,模块，逻辑点等所有属性
 * @author LvSihan 
 * @date 2019年3月5日  
 * @version 1.0
 */
@Component
@Slf4j
public class OpcData {
	private static OpcData opcData;
	@Autowired
	private OpcDataMapper opcDataMapper;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private MySQLDBObject mysqlDBObject;
	@PostConstruct
	public void init() {
		opcData = this;
		opcData.opcDataMapper = this.opcDataMapper;
		opcData.besSbdyMapper = this.besSbdyMapper;
	}
	/**
	 * 新增opc数据
	 * @param f_sys_name
	 */
	public void addOpcData(String f_sys_name) {
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcDataMapper.getNodeTable(f_sys_name);
		//无属性表		
		if(Validate_y.isNullOrEmpty(nodeInfo.get("F_NODE_TABLE"))) {
			Map<String,String> allData = new HashMap<>();
			for(String key : nodeInfo.keySet()) {
				if(!Validate_y.isNullOrEmpty(String.valueOf(nodeInfo.get(key))) && !key.equals("F_NODE_TABLE") && !key.equals("F_ID")) {
//					String o_key = String.valueOf(nodeInfo.get("F_ID"))+"."+nodeInfo.get("F_SYS_NAME")+"."+key;
					String o_key = nodeInfo.get("F_SYS_NAME")+"."+key;
					allData.put("o_val", String.valueOf(nodeInfo.get(key)));
					allData.put("o_key", o_key);
					allData.put("o_descripe", nodeInfo.get("F_ALLPATH"));
					//判断是否已有该条数据
					if(queryOpcData(o_key) == 0) {
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
		//有属性表
		}else {
			//获取节点
			List<Map<String,String>> allDataList = opcDataMapper.getAllData(f_sys_name, getDbName());
			//
			for(Map<String,String> allData : allDataList) {
				String o_key = "";
				//单个节点属性
				Map<String,String> nodeData = opcDataMapper.getNodeData(allData.get("F_SYS_NAME"),allData.get("F_NODE_TABLE"));
				for(String key : nodeData.keySet()) {					
					if(key.equals(allData.get("COLUMN_NAME"))) {
						o_key = allData.get("F_SYS_NAME")+"."+allData.get("COLUMN_NAME");
						allData.put("o_val", String.valueOf(nodeData.get(key)));
						allData.put("o_key", o_key);
						allData.put("o_descripe", allData.get("F_ALLPATH"));
					}
				}
				//判断是否已有该条数据
				if(queryOpcData(o_key) == 0) {
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
	}
	/**
	 * 删除该节点及所有子节点OPC数据
	 * @param f_sys_name
	 */
	public void delOpcData(String f_sys_name) {
		//查询该节点下的所有子节点
		List<BESSbPzStruct> allChildNodeList = new ArrayList<>();
		getChildNodeList(f_sys_name,allChildNodeList);
		for(BESSbPzStruct childNodeList : allChildNodeList) {
			delSingleOpc(childNodeList.getF_sys_name());
		}
		delSingleOpc(f_sys_name);		
	}
	/**
	 * 更新该节点OPC数据
	 */
	public void editOpcData(String f_sys_name) {
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcDataMapper.getNodeTable(f_sys_name);
		//无属性表		
		if(Validate_y.isNullOrEmpty(nodeInfo.get("F_NODE_TABLE"))) {
			Map<String,String> allData = new HashMap<>();
			for(String key : nodeInfo.keySet()) {
				if(!Validate_y.isNullOrEmpty(nodeInfo.get(key)) && !nodeInfo.get(key).equals("F_NODE_TABLE")) {
					String o_key = nodeInfo.get("F_SYS_NAME")+"."+key;
					allData.put("o_val", String.valueOf(nodeInfo.get(key)));
					allData.put("o_key", o_key);
					allData.put("o_descripe", nodeInfo.get("F_ALLPATH"));
					//判断是否已有该条数据
					if(queryOpcData(o_key) == 0) {
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
		//有属性表
		}else {
			//获取节点
			List<Map<String,String>> allDataList = opcDataMapper.getAllData(f_sys_name, getDbName());
			//
			for(Map<String,String> allData : allDataList) {
				String o_key = "";
				//单个节点属性
				Map<String,String> nodeData = opcDataMapper.getNodeData(allData.get("F_SYS_NAME"),allData.get("F_NODE_TABLE"));
				for(String key : nodeData.keySet()) {
					if(key.equals(allData.get("COLUMN_NAME"))) {
						o_key = allData.get("F_SYS_NAME")+"."+allData.get("COLUMN_NAME");
						allData.put("o_val", String.valueOf(nodeData.get(key)));
						allData.put("o_key", o_key);
						allData.put("o_descripe", allData.get("F_ALLPATH"));
					}
				}
				//判断是否已有该条数据
				if(queryOpcData(o_key) == 0) {
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
	}
	/**
	 * 更新OPC逻辑点值
	 * @param besSbPzStruct
	 */
	public static void editOpcInitVal(BESSbPzStruct besSbPzStruct) {
		String o_key = besSbPzStruct.getF_sys_name()+".F_INIT_VAL";
		Map<String,String> allData = new HashMap<>();
		allData.put("o_val", besSbPzStruct.getF_init_val());
		allData.put("o_key", o_key);
		allData.put("o_descripe", besSbPzStruct.getF_allpath());
		//判断是否已有该条数据
		if(queryOpcData(o_key) == 0) {
			//写入数据库
			opcData.opcDataMapper.insertData(allData);
		}else {
			//更新该条数据
			opcData.opcDataMapper.editData(allData);
		}	
		//发送opc数据
		sendOpc(allData);
	}
	/**
	 * 更新采集器的状态
	 * @param obj
	 */
	public static void editOpcOne(JSONObject obj) {
		Map<String,String>  nodeInfo= opcData.opcDataMapper.getNodeTable(obj.getString("f_sys_name"));
		String o_key = nodeInfo.get("F_SYS_NAME")+"."+"F_ONLINE_STATE";
		Map<String,String> allData = new HashMap<>();
		allData.put("o_val", obj.getString("f_status"));
		allData.put("o_key", o_key);
		allData.put("o_descripe", nodeInfo.get("F_ALLPATH"));
		//判断是否已有该条数据
		if(queryOpcData(o_key) == 0) {
			//写入数据库
			opcData.opcDataMapper.insertData(allData);
		}else {
			//更新该条数据
			opcData.opcDataMapper.editData(allData);
		}
		//发送opc数据
		sendOpc(allData);
	}
	/**
	 * 更新电表 数据
	 * @param dtoIn
	 */
	public static void editOpcAmmeter(Dto dtoIn) {
		Map<String,String>  nodeInfo= opcData.opcDataMapper.getNodeTable(dtoIn.getAsString("meteruuid"));
		String o_key = nodeInfo.get("F_SYS_NAME")+"."+"F_data";
		Map<String,String> allData = new HashMap<>();
		allData.put("o_val", dtoIn.getAsString("data"));
		allData.put("o_key", o_key);
		allData.put("o_descripe", nodeInfo.get("F_ALLPATH"));
		//判断是否已有该条数据
		if(queryOpcData(o_key) == 0) {
			//写入数据库
			opcData.opcDataMapper.insertData(allData);
		}else {
			//更新该条数据
			opcData.opcDataMapper.editData(allData);
		}
		//发送opc数据
		sendOpc(allData);
	}
	/**
	 * 根据o_key查询该条数据
	 * @param o_key
	 * @return
	 */
	private static int queryOpcData(String o_key) {
		List<Map<String,String>> opcDataList = opcData.opcDataMapper.queryOpcData(o_key);
		int num = 0;
		if(opcDataList.size() == 0) {
			return num;
		}else {
			num = opcDataList.size();
		}
		return num;	
	}
	/**
	 * 删除
	 * @param f_sys_name
	 */
	private void delSingleOpc(String f_sys_name) {
		//查询节点是否有属性表
		Map<String,String>  nodeInfo= opcDataMapper.getNodeTable(f_sys_name);
		String o_key = "";
		//无属性表
		if(Validate_y.isNullOrEmpty(nodeInfo.get("F_NODE_TABLE"))) {
			for(String key : nodeInfo.keySet()) {
				if(nodeInfo.get(key) != null) {
					o_key = nodeInfo.get("F_SYS_NAME")+"."+key;
					//删除该数据
					opcDataMapper.delOpcData(o_key);
				}					
			}
		//有属性表
		}else {
			//获取节点
			List<Map<String,String>> allDataList = opcDataMapper.getAllData(f_sys_name, getDbName());
			//
			for(Map<String,String> allData : allDataList) {
				//单个节点属性
				Map<String,String> nodeData = opcDataMapper.getNodeData(allData.get("F_SYS_NAME"),allData.get("F_NODE_TABLE"));
				if(nodeData != null) {
					for(String key : nodeData.keySet()) {
						if(key.equals(allData.get("COLUMN_NAME"))) {
							o_key = allData.get("F_SYS_NAME")+"."+allData.get("COLUMN_NAME");	
							//删除该数据
							opcDataMapper.delOpcData(o_key);
						}
					}
				}								
			}
		}
	}
	/**
	 * 向OPCServer发送数据
	 * @param allData
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private static void sendOpc(Map<String, String> allData) {
		Map<String, Object> pMap = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> opcdata = new HashMap<>();
		opcdata.put("o_type" , "11");
		opcdata.put("o_key", allData.get("o_key"));
		opcdata.put("o_val", allData.get("o_val"));
		opcdata.put("o_descripe", allData.get("o_descripe"));	
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
	 * 获取ddc下所有子节点
	 * @param f_sys_name
	 * @param allChildNodeList
	 */
	private void getChildNodeList(String f_sys_name, List<BESSbPzStruct> allChildNodeList) {
		List<BESSbPzStruct> childNodeList = besSbdyMapper.getSbChildNodeBySysName(f_sys_name);
		for(BESSbPzStruct childNode : childNodeList) {
			allChildNodeList.add(childNode);
			getChildNodeList(childNode.getF_sys_name(),allChildNodeList);			
		}
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
	/**
	 * 同步OPC数据
	 * @return
	 */
	public static void syncOpc() {
		//查询数据库中所有OPC数据
		List<Map<String,String>> opcDataList = opcData.opcDataMapper.getOpcData();
		//发送给OPCServer
		for(int i=0;i<opcDataList.size();i++) {
			//发送opc数据
			sendOpc(opcDataList.get(i));
		}
	}
}
