package com.efounder.JEnterprise.adapterProcess.camera;

import com.alibaba.fastjson.JSONObject;
import com.core.ApplicationContextProvider;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.conn.db.MySQLDBUtil;
import com.core.common.conn.restful.ISSPURLConnection;
import com.core.common.data.ISSPDataSet;
import com.core.common.data.ISSPRowSet;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新摄像机设备状态处理类
 * author huangxianbo
 * date 2018-10-31
 */


public class CameraStatusProcess {

	public void getCameraState(JSONObject paramObject) {
		MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
		Connection conn = sqldbObject.getConnection();
		try {
			Map<String, List<Object>> stateMap = new HashMap<String, List<Object>>();
			String sql = "SELECT * FROM BES_EQUIPMENT_LIST WHERE F_SBLX_TYPE in ('00000000014','00000000016','00000000059','00000000060','00000000061','00000000100','00000000178','00000000185','00000000200','00000000204','00000000206','00000000212');";
			ISSPDataSet dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
			ISSPRowSet rowSet = null;
			List<Object> stateList = null;
			String sbid = null;
			String sblxType = null;
			String upSql = "UPDATE bes_equipment_list SET F_STATUS = ? ,F_LAST_GXSJ = current_timestamp(),F_CHDATE = current_timestamp() where F_SBB = ? and F_SBLX_TYPE = ? ";
			for (int i = 0; i < dataSet.getRowCount(); i++) {
				rowSet = dataSet.getRowSet(i);
				sbid = rowSet.getString("F_SBB", "");
				sblxType = rowSet.getString("F_SBLX_TYPE", "");
				String url = "http://10.50.13.248:8011/restService/cameraStatus/" + sbid;
				JSONObject reqObj = new JSONObject();
				reqObj.put("Accept", "text/plain, */*; q=0.01"); //设置接口请求方式
				String result = ISSPURLConnection.processRequest(url, "GET", null, reqObj);
				if (StringUtils.hasText(result)) {
					JSONObject parseObject = JSONObject.parseObject(result);// 从接口获取的数据转换成JSONObject
					if (parseObject.get("data") == null) { //当前设备请求异常时
						continue;
					}
					JSONObject obj = (JSONObject) parseObject.get("data");
					String onlineStaus = obj.getString("online");
					if (!"1".equals(onlineStaus)) {
						String alarmCode = obj.getString("alarmCode");
						if ("001".equals(alarmCode)) {// 接口中找不到对应设备
							continue;
						}
					}
					stateList = new ArrayList<Object>();
					stateList.add("1".equals(onlineStaus)?"0":"2");//运行状态 (0:正常,2:离线)
					stateList.add(sbid);// 设备编号
					stateList.add(sblxType);// 设备类型编号
					stateMap.put(sbid + sblxType, stateList);
					if (stateMap.size() >= 500) { // 更新设备状态(分批更新)
						boolean upRes = MySQLDBUtil.executeBatchSQL(conn, upSql, stateMap);
						if (upRes == true) {
							stateMap = new HashMap<String, List<Object>>();
						}
					}
				}
			}
			if (stateMap.size() > 0) { // 设备分批更新,最后一批数量小于500条时,也要更新
				MySQLDBUtil.executeBatchSQL(conn, upSql, stateMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
	}
}
