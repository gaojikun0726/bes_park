package com.efounder.JEnterprise.adapterProcess.camera;

import com.alibaba.fastjson.JSONObject;
import com.core.ApplicationContextProvider;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.conn.db.MySQLDBUtil;
import com.core.common.conn.restful.ISSPURLConnection;
import com.core.common.data.ISSPDataSet;
import com.core.common.data.ISSPRowSet;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CameraProcess {

    public void getCameraState(JSONObject paramObject) {
        MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
        Connection conn = sqldbObject.getConnection();
        try {
            String sqlpz = "SELECT * FROM bes_monitor_sbxhpz WHERE F_ID = '2018080106'";
            ISSPDataSet dataSetPZ = MySQLDBUtil.executeQuerySQL(conn, sqlpz, null);
            ISSPRowSet rowSetPZ = dataSetPZ.getRowSet(0);
            String filter = rowSetPZ.getString("F_TYPE", "");
            //String sql = "SELECT * FROM ISSP_EQUIPMENTACCESS WHERE F_TYPE in (" + filter + ")";

            String sql = "SELECT * FROM bes_equipment_list WHERE F_SBLX_TYPE in (" + filter + ")" + "and F_SFJK='1'";

            ISSPDataSet dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
            ISSPRowSet rowSet = null;
            //List<Object> paramsList = null;
            //List<Object> delteList = null;

            String sbid = null;//设备编号
            String f_sbmc, f_brandid, f_brandmc, f_xlid, f_xlmc, f_road_direction, f_sn, f_sblx_type, f_sbxh_type, f_sblemc, f_sbxhmc, f_rkd_id, f_ssck, f_lacation, f_sydw, f_jbr, f_long, f_lat, f_scs, f_gmrq, f_bxnx = null;
            String f_unit_price, f_status, f_last_gxsj, f_last_gxr, f_ywmbh, f_ewmbh, f_azsj, f_whs, f_ckdid, f_lyjg, f_lyr, f_zh, f_gldw, f_scrq, f_bxdqr, f_czrq, f_synx, f_rksj, f_sfjk, f_last_status, f_unit, f_crdate, f_chdate = null;
            String runStatus = null;//运行状态
            String crDate = DateUtil.getCurrTime();// 获取系统时间
            for (int i = 0; i < dataSet.getRowCount(); i++) {
                List<Object> stateList = new ArrayList<Object>();//设备基础信息表状态
                List<Object> inspectList = new ArrayList<Object>();//巡检信息
                try {
                    rowSet = dataSet.getRowSet(i);

                    sbid = rowSet.getString("F_SBB", "");
                    f_sbmc = dataSet.getRowSet(i).getString("F_SBMC", "");
                    f_brandid = dataSet.getRowSet(i).getString("F_BRANDID", "");
                    f_brandmc = dataSet.getRowSet(i).getString("F_BRANDMC", "");
                    f_xlid = dataSet.getRowSet(i).getString("F_XLID", "");
                    f_xlmc = dataSet.getRowSet(i).getString("F_XLMC", "");
                    f_road_direction = dataSet.getRowSet(i).getString("F_ROAD_DIRECTION", "");
                    f_sn = dataSet.getRowSet(i).getString("F_SN", "");
                    f_sblx_type = dataSet.getRowSet(i).getString("F_SBLX_TYPE", "");
                    f_sbxh_type = dataSet.getRowSet(i).getString("F_SBXH_TYPE", "");
                    f_sblemc = dataSet.getRowSet(i).getString("F_SBLEMC", "");
                    f_sbxhmc = dataSet.getRowSet(i).getString("F_SBXHMC", "");
                    f_rkd_id = dataSet.getRowSet(i).getString("F_RKD_ID", "");
                    f_ssck = dataSet.getRowSet(i).getString("F_SSCK", "");
                    f_lacation = dataSet.getRowSet(i).getString("F_LACATION", "");
                    f_sydw = dataSet.getRowSet(i).getString("F_SYDW", "");
                    f_jbr = dataSet.getRowSet(i).getString("F_JBR", "");
                    f_long = dataSet.getRowSet(i).getString("F_LONG", "");
                    f_lat = dataSet.getRowSet(i).getString("F_LAT", "");
                    f_scs = dataSet.getRowSet(i).getString("F_SCS", "");
                    f_gmrq = dataSet.getRowSet(i).getString("F_GMRQ", "");
                    f_bxnx = dataSet.getRowSet(i).getString("F_BXNX", "");

                    f_unit_price = dataSet.getRowSet(i).getString("F_UNIT_PRICE", "");
                    f_last_gxsj = dataSet.getRowSet(i).getString("F_LAST_GXSJ", "");
                    f_last_gxr = dataSet.getRowSet(i).getString("F_LAST_GXR", "");
                    f_ywmbh = dataSet.getRowSet(i).getString("F_YWMBH", "");
                    f_ewmbh = dataSet.getRowSet(i).getString("F_EWMBH", "");
                    f_azsj = dataSet.getRowSet(i).getString("F_AZSJ", "");
                    f_whs = dataSet.getRowSet(i).getString("F_WHS", "");
                    f_ckdid = dataSet.getRowSet(i).getString("F_CKDID", "");
                    f_lyjg = dataSet.getRowSet(i).getString("F_LYJG", "");
                    f_lyr = dataSet.getRowSet(i).getString("F_LYR", "");
                    f_zh = dataSet.getRowSet(i).getString("F_ZH", "");
                    f_gldw = dataSet.getRowSet(i).getString("F_GLDW", "");
                    f_scrq = dataSet.getRowSet(i).getString("F_SCRQ", "");
                    f_bxdqr = dataSet.getRowSet(i).getString("F_BXDQR", "");
                    f_czrq = dataSet.getRowSet(i).getString("F_CZRQ", "");
                    f_synx = dataSet.getRowSet(i).getString("F_SYNX", "");
                    f_rksj = dataSet.getRowSet(i).getString("F_RKSJ", "");
                    f_sfjk = dataSet.getRowSet(i).getString("F_SFJK", "");
                    f_last_status = dataSet.getRowSet(i).getString("F_LAST_STATUS", "");
                    f_unit = dataSet.getRowSet(i).getString("F_UNIT", "");


                    //String url = "http://10.200.1.74:8080/com.qljtxx.uapmee.wapi/api/v1.0/json/vorxVideo/deviceStatus/" + sbid;
                    String url = "http://10.50.13.248:8011/restService/cameraStatus/" + sbid;
                    String result = ISSPURLConnection.processCameraRequest(url, "GET", null);//{"UPDATETIME":"2018-09-19 11:32:21","RESULT":"SUCCESS"}
                    if (StringUtils.hasText(result)) {
                        JSONObject parseObject = JSONObject.parseObject(result);// 从接口获取的数据转换成JSONObject
                        String statusInfo = parseObject.get("data").toString();
                        JSONObject parseStatusObject = JSONObject.parseObject(statusInfo);// 从接口获取的数据转换成JSONObject
                        if (parseStatusObject.get("online") == null) {
                            runStatus = "2";
                            toOffDel(conn, dataSet.getRowSet(i));//进行离线处理
                        } else {
                            String RESULT = parseStatusObject.get("online").toString();
                            runStatus = "1".equals(RESULT) ? "0" : "2";
                            if (runStatus.equals("0")) {
                                //在线报警处理
                                toOnUpdate(conn, dataSet.getRowSet(i));
                            } else {
                                //进行离线处理
                                toOffUpdate(conn, dataSet.getRowSet(i));
                            }
                        }
                    } else {
                        runStatus = "2";
                    }

                    stateList.add(runStatus);//运行状态
                    stateList.add(crDate);//
                    stateList.add(crDate);//
                    stateList.add(sbid);//

                    inspectList.add(UUIDUtil.getRandom32BeginTimePK());
                    inspectList.add(sbid);
                    inspectList.add(runStatus);
                    inspectList.add(f_sbmc);
                    inspectList.add(f_xlid);
                    inspectList.add(f_xlmc);
                    inspectList.add(f_road_direction);
                    inspectList.add(f_sblx_type);
                    inspectList.add(f_sbxh_type);
                    inspectList.add(f_sblemc);
                    inspectList.add(f_sbxhmc);
                    inspectList.add(f_ssck);
                    inspectList.add(f_lacation);
                    inspectList.add(f_sydw);
                    inspectList.add(f_jbr);
                    inspectList.add(f_long);
                    inspectList.add(f_lat);
                    inspectList.add(f_scs);
                    //inspectList.add("'"+f_gmrq+"'");
                    inspectList.add(f_unit_price);
                    //inspectList.add("'"+f_last_gxsj+"'");
                    inspectList.add(f_last_gxr);
                    //inspectList.add("'"+f_azsj+"'");
                    inspectList.add(f_whs);
                    inspectList.add(f_lyjg);
                    inspectList.add(f_lyr);
                    inspectList.add(f_zh);
                    inspectList.add(f_gldw);
                    //inspectList.add("'"+f_bxdqr+"'");
                    //inspectList.add("'"+f_rksj+"'");
                    inspectList.add(f_sfjk);
                    inspectList.add(f_unit);
                    inspectList.add(crDate);
                    inspectList.add(crDate);

                    //插入巡检表
                    String inSql = "INSERT INTO BES_EQUIPMENT_INSPECT(F_ID,F_SBB,F_STATUS,F_SBMC,F_XLID,F_XLMC,F_ROAD_DIRECTION,F_SBLX_TYPE,F_SBXH_TYPE,F_SBLEMC,F_SBXHMC,F_SSCK,F_LACATION,F_SYDW, "
                            + "F_JBR,F_LONG,F_LAT,F_SCS,F_UNIT_PRICE,F_LAST_GXR,F_WHS,F_LYJG,F_LYR,F_ZH,F_GLDW,F_SFJK,F_UNIT,F_INSPECT_TIME,F_CRDATE,F_CHDATE)"
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?)";
                    MySQLDBUtil.executeUpdateSQL(conn, inSql, inspectList);

                    //更新设备状态 bes_equipment_list表中 设备状态(0:正常 1:报警 2:离线 3：已移除)
                    String upSql = "UPDATE bes_equipment_list SET F_STATUS = ? ,F_LAST_GXSJ = ?,F_CHDATE = ? where F_SBB = ?";
                    MySQLDBUtil.executeUpdateSQL(conn, upSql, stateList);
                } catch (Exception e) {
                    e.printStackTrace();
                    rowSet = dataSet.getRowSet(i);

                    sbid = rowSet.getString("F_SBB", "");
                    f_sbmc = dataSet.getRowSet(i).getString("F_SBMC", "");
                    f_brandid = dataSet.getRowSet(i).getString("F_BRANDID", "");
                    f_brandmc = dataSet.getRowSet(i).getString("F_BRANDMC", "");
                    f_xlid = dataSet.getRowSet(i).getString("F_XLID", "");
                    f_xlmc = dataSet.getRowSet(i).getString("F_XLMC", "");
                    f_road_direction = dataSet.getRowSet(i).getString("F_ROAD_DIRECTION", "");
                    f_sn = dataSet.getRowSet(i).getString("F_SN", "");
                    f_sblx_type = dataSet.getRowSet(i).getString("F_SBLX_TYPE", "");
                    f_sbxh_type = dataSet.getRowSet(i).getString("F_SBXH_TYPE", "");
                    f_sblemc = dataSet.getRowSet(i).getString("F_SBLEMC", "");
                    f_sbxhmc = dataSet.getRowSet(i).getString("F_SBXHMC", "");
                    f_rkd_id = dataSet.getRowSet(i).getString("F_RKD_ID", "");
                    f_ssck = dataSet.getRowSet(i).getString("F_SSCK", "");
                    f_lacation = dataSet.getRowSet(i).getString("F_LACATION", "");
                    f_sydw = dataSet.getRowSet(i).getString("F_SYDW", "");
                    f_jbr = dataSet.getRowSet(i).getString("F_JBR", "");
                    f_long = dataSet.getRowSet(i).getString("F_LONG", "");
                    f_lat = dataSet.getRowSet(i).getString("F_LAT", "");
                    f_scs = dataSet.getRowSet(i).getString("F_SCS", "");
                    f_gmrq = dataSet.getRowSet(i).getString("F_GMRQ", "");
                    f_bxnx = dataSet.getRowSet(i).getString("F_BXNX", "");

                    f_unit_price = dataSet.getRowSet(i).getString("F_UNIT_PRICE", "");
                    f_last_gxsj = dataSet.getRowSet(i).getString("F_LAST_GXSJ", "");
                    f_last_gxr = dataSet.getRowSet(i).getString("F_LAST_GXR", "");
                    f_ywmbh = dataSet.getRowSet(i).getString("F_YWMBH", "");
                    f_ewmbh = dataSet.getRowSet(i).getString("F_EWMBH", "");
                    f_azsj = dataSet.getRowSet(i).getString("F_AZSJ", "");
                    f_whs = dataSet.getRowSet(i).getString("F_WHS", "");
                    f_ckdid = dataSet.getRowSet(i).getString("F_CKDID", "");
                    f_lyjg = dataSet.getRowSet(i).getString("F_LYJG", "");
                    f_lyr = dataSet.getRowSet(i).getString("F_LYR", "");
                    f_zh = dataSet.getRowSet(i).getString("F_ZH", "");
                    f_gldw = dataSet.getRowSet(i).getString("F_GLDW", "");
                    f_scrq = dataSet.getRowSet(i).getString("F_SCRQ", "");
                    f_bxdqr = dataSet.getRowSet(i).getString("F_BXDQR", "");
                    f_czrq = dataSet.getRowSet(i).getString("F_CZRQ", "");
                    f_synx = dataSet.getRowSet(i).getString("F_SYNX", "");
                    f_rksj = dataSet.getRowSet(i).getString("F_RKSJ", "");
                    f_sfjk = dataSet.getRowSet(i).getString("F_SFJK", "");
                    f_last_status = dataSet.getRowSet(i).getString("F_LAST_STATUS", "");
                    f_unit = dataSet.getRowSet(i).getString("F_UNIT", "");


                    stateList.add("2");//运行状态
                    stateList.add(crDate);//
                    stateList.add(crDate);//
                    stateList.add(sbid);//

                    inspectList.add(UUIDUtil.getRandom32BeginTimePK());
                    inspectList.add(sbid);
                    inspectList.add("2");
                    inspectList.add(f_sbmc);
                    inspectList.add(f_xlid);
                    inspectList.add(f_xlmc);
                    inspectList.add(f_road_direction);
                    inspectList.add(f_sblx_type);
                    inspectList.add(f_sbxh_type);
                    inspectList.add(f_sblemc);
                    inspectList.add(f_sbxhmc);
                    inspectList.add(f_ssck);
                    inspectList.add(f_lacation);
                    inspectList.add(f_sydw);
                    inspectList.add(f_jbr);
                    inspectList.add(f_long);
                    inspectList.add(f_lat);
                    inspectList.add(f_scs);
                    //inspectList.add("'"+f_gmrq+"'");
                    inspectList.add(f_unit_price);
                    //inspectList.add("'"+f_last_gxsj+"'");
                    inspectList.add(f_last_gxr);
                    //inspectList.add("'"+f_azsj+"'");
                    inspectList.add(f_whs);
                    inspectList.add(f_lyjg);
                    inspectList.add(f_lyr);
                    inspectList.add(f_zh);
                    inspectList.add(f_gldw);
                    //inspectList.add("'"+f_bxdqr+"'");
                    //inspectList.add("'"+f_rksj+"'");
                    inspectList.add(f_sfjk);
                    inspectList.add(f_unit);
                    inspectList.add(crDate);
                    inspectList.add(crDate);

                    //插入巡检表
                    String inSql = "INSERT INTO BES_EQUIPMENT_INSPECT(F_ID,F_SBB,F_STATUS,F_SBMC,F_XLID,F_XLMC,F_ROAD_DIRECTION,F_SBLX_TYPE,F_SBXH_TYPE,F_SBLEMC,F_SBXHMC,F_SSCK,F_LACATION,F_SYDW, "
                            + "F_JBR,F_LONG,F_LAT,F_SCS,F_UNIT_PRICE,F_LAST_GXR,F_WHS,F_LYJG,F_LYR,F_ZH,F_GLDW,F_SFJK,F_UNIT,F_INSPECT_TIME,F_CRDATE,F_CHDATE)"
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?)";
                    MySQLDBUtil.executeUpdateSQL(conn, inSql, inspectList);

                    //更新设备状态 bes_equipment_list表中 设备状态(0:正常 1:报警 2:离线)
                    String upSql = "UPDATE bes_equipment_list SET F_STATUS = ? ,F_LAST_GXSJ = ?,F_CHDATE = ? where F_SBB = ?";
                    MySQLDBUtil.executeUpdateSQL(conn, upSql, stateList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //当设备状态为空时，物联平台更改监控状态：不监控
    public static void toOffDel(Connection conn, ISSPRowSet isspRowSet) throws Exception {
        String crDate = DateUtil.getCurrTime();// 获取系统时间
        String F_SBBH = isspRowSet.getString("F_SBB", "");//设备编号
        //删除数据
        List<Object> delList = new ArrayList<>();
        delList.add("0");
        delList.add(crDate);
        delList.add(F_SBBH);
        String delSql = "UPDATE bes_equipment_list SET F_SFJK = ? ,F_CHDATE = ? where F_SBB = ?";
        MySQLDBUtil.executeUpdateSQL(conn, delSql, delList);
    }


    //当设备状态为离线时，进行操作
    public static void toOffUpdate(Connection conn, ISSPRowSet isspRowSet) throws Exception {
        String crDate = DateUtil.getCurrTime();// 获取系统时间
        String F_GUID = UUIDUtil.getRandom32BeginTimePK();//guid
        String F_SBBH = isspRowSet.getString("F_SBB", "");//设备编号
        String F_SBMC = isspRowSet.getString("F_SBMC", "");//设备名称
        String F_SBLX = isspRowSet.getString("F_SBLX_TYPE", "");//设备类型
        String F_ZZJGBH = isspRowSet.getString("F_SYDW", "");//使用单位
        String F_LACATION = isspRowSet.getString("F_LACATION", "");//当前位置

        //存放需要添加实时告警信息集合
        List<Object> alarmList = new ArrayList<Object>();
        alarmList.add(F_GUID);
        alarmList.add(F_SBBH);
        alarmList.add(F_SBMC);
        alarmList.add(F_SBLX);
        alarmList.add("设备已离线！"); //报警信息
        alarmList.add(crDate); //报警时间
        alarmList.add("0"); //消息类型  0=告警消息, 1=恢复通知
        alarmList.add("0"); //恢复状态  0=异常中,1=恢复正常
        alarmList.add(F_ZZJGBH);
        alarmList.add(F_LACATION);
        alarmList.add("2");    //状态： 0:异常；1：正常；2：离线
        alarmList.add(crDate);    //创建时间
        alarmList.add(crDate);    //跟新时间
        String sql = "SELECT * FROM BES_EQ_ALARM_ACTUALTIME WHERE  F_SBBH = " + "'" + F_SBBH + "'";
        ISSPDataSet dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
        if (dataSet.getRowCount() == 0) {//实时告警表中，未检测到存在此设备信息
            //插入实时告警表
            String insertSql = "INSERT INTO BES_EQ_ALARM_ACTUALTIME(F_GUID,F_SBBH,F_SBMC,F_SBLX,F_AlERT_DESC,F_ALERT_TIME,F_MSG_TYPE,F_RECOVER_STATE,F_ZZJGBH,F_LACATION,F_STATE,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            MySQLDBUtil.executeUpdateSQL(conn, insertSql, alarmList);
        } else {
            //放行。。。
        }
    }

    //当设备状态为在线时，进行操作
    public static void toOnUpdate(Connection conn, ISSPRowSet isspRowSet) throws Exception {
        String crDate = DateUtil.getCurrTime();// 获取系统时间
        String F_GUID = UUIDUtil.getRandom32BeginTimePK();//guid
        String F_SBBH = isspRowSet.getString("F_SBB", "");//设备编号
        String F_SBMC = isspRowSet.getString("F_SBMC", "");//设备名称
        String F_SBLX = isspRowSet.getString("F_SBLX_TYPE", "");//设备类型
        String F_ZZJGBH = isspRowSet.getString("F_SYDW", "");//使用单位
        String F_LACATION = isspRowSet.getString("F_LACATION", "");//当前位置

        //存放需要添加实时告警信息集合
        List<Object> alarList = new ArrayList<Object>();
        alarList.add(F_GUID);
        alarList.add(F_SBBH);
        alarList.add(F_SBMC);
        alarList.add(F_SBLX);
        alarList.add("摄像机故障");
        alarList.add(crDate);
        alarList.add(F_ZZJGBH);
        alarList.add(F_LACATION);
        alarList.add("0");    //状态： 0:异常；1：正常；2：离线
        alarList.add(crDate);    //创建时间
        alarList.add(crDate);    //跟新时间
        String sql = "SELECT * FROM BES_EQ_ALARM_ACTUALTIME WHERE  F_SBBH = " + "'" + F_SBBH + "'";
        ISSPDataSet dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
        if (dataSet.getRowCount() != 0) {//实时报警表中检测到信息
            //删除实时表中当前报警设备
            String deleteSql = "DELETE FROM BES_EQ_ALARM_ACTUALTIME WHERE  F_SBBH = " + "'" + F_SBBH + "'";
            MySQLDBUtil.executeUpdateSQL(conn, deleteSql, null);
            //插入设备报警记录表
            String insertSql = "INSERT INTO BES_EQ_ALARM_RECORD(F_GUID,F_SBBH,F_SBMC,F_SBLX,F_ALERT_DESC,F_ALERT_TIME,F_ZZJGBH,F_LACATION,F_STATE,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            MySQLDBUtil.executeUpdateSQL(conn, insertSql, alarList);
        } else {
            //放行
        }

    }


}
