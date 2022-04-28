package com.core.common.security;

import com.core.ApplicationContextProvider;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.conn.db.MySQLDBUtil;
import com.core.common.data.ISSPDataSet;
import com.core.common.data.ISSPRowSet;
import com.core.config.qxpz.QxConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class ISSPSecurityObject {
	
	/**
	 * 检查数据权限
	 * 
	 * @param conn
	 * @param paramObject
	 * @return
	 */
	public static String CheckDataLimit(LimitParamObject paramObject) {
		QxConfig qxConfig = (QxConfig) ApplicationContextProvider.getBean("qxConfig");
		String TRUE = "(1=1)", FALSE = "(1=2)";
		String sjqxable = qxConfig.getSjqxUseable();
		if(sjqxable.equals("0")){//如果等于1 则启用,0不启用
			return TRUE;
		}
		MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
		Connection conn = sqldbObject.getConnection();
		
		String sfsy = null, USER_QXB = null, YHZ_QXB = null, ROLE_QXB = null, GW_QXB = null;
		String strGranTable = "ES_SJQX";
		StringBuffer limitSQL = new StringBuffer();

		String qxbh = paramObject.getQxbh();
		String pscol = paramObject.getPscol();
		String pszgbh = paramObject.getYhbh();
		String pibzw = paramObject.getPibzw();

		String vssql = "select F_SFSY,F_USER_QXB,F_YHZ_QXB,F_ROLE_QXB,F_GW_QXB,F_TABN,F_BHZD from "
				+ strGranTable + " where F_QXBH='" + qxbh + "'";

		try {
			ISSPDataSet dataSet = MySQLDBUtil.executeQuerySQL(conn, vssql, null);
			
			int rowCount = dataSet.getRowCount();
			ISSPRowSet rowset = null;
			for (int i = 0; i < rowCount; i++) {
				rowset = dataSet.getRowSet(i);
				sfsy = rowset.getString("F_SFSY", "");
				USER_QXB = rowset.getString("F_USER_QXB", "");// 用户权限表
				YHZ_QXB = rowset.getString("F_YHZ_QXB", "");// 用户组权限表
				ROLE_QXB = rowset.getString("F_ROLE_QXB", "");// 角色权限表
				GW_QXB = rowset.getString("F_GW_QXB", "");// 岗位权限表
				if (pscol == null || "".equals(pscol)) {
					pscol = rowset.getString("F_TABN", "") + "." + rowset.getString("F_BHZD", "");
				}
			}

			if (sfsy == null || sfsy.length() == 0) {
				return TRUE;
			}
			if (sfsy.equals("0"))
				return TRUE;

			String userLimit = CheckUserLimit(USER_QXB, qxbh, pibzw, pscol, pszgbh);
			String userGroupLimit = CheckUserGroupLimit(YHZ_QXB, qxbh, pibzw, pscol, pszgbh);
			String roleLimit = CheckRoleLimit(ROLE_QXB, qxbh, pibzw, pscol, pszgbh);
			String gwLimit = CheckGWLimit(GW_QXB, qxbh, pibzw, pscol, pszgbh);

			limitSQL.append(userLimit).append(userGroupLimit).append(roleLimit).append(gwLimit);
		} catch (Exception e) {
			e.printStackTrace();
			return FALSE;
		} finally{
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return limitSQL.toString().toUpperCase();
	}

	/**
	 * 检查用户数据权限
	 * 
	 * @param USER_QXB
	 * @param qxbh
	 * @param pibzw
	 * @param pscol
	 * @param pszgbh
	 * @return
	 */
	private static String CheckUserLimit(String USER_QXB, String qxbh,
			String pibzw, String pscol, String pszgbh) {
		StringBuffer vssql = new StringBuffer();
		// 用户权限表
		if (USER_QXB != null && !"".equals(USER_QXB)) {
			vssql.append(" EXISTS (SELECT 1 FROM " + USER_QXB);
			vssql.append(" SJQX WHERE SJQX.F_QXBH='" + qxbh + "' AND " + pscol);
			vssql.append("=SJQX.F_SJBH");
			vssql.append(" AND ( SJQX.F_YHBH='" + pszgbh + "')");
			vssql.append(" AND SJQX.F_G" + pibzw + "='1'");
			vssql.append(")");
		}
		return vssql.toString();
	}

	/**
	 * 检查用户组数据权限
	 * 
	 * @param YHZ_QXB
	 * @param qxbh
	 * @param pibzw
	 * @param pscol
	 * @param pszgbh
	 * @return
	 */
	private static String CheckUserGroupLimit(String YHZ_QXB, String qxbh,
			String pibzw, String pscol, String pszgbh) {
		StringBuffer vssql = new StringBuffer();
		// 用户组权限表
		if (YHZ_QXB != null && !"".equals(YHZ_QXB)) {
			vssql.append(" OR EXISTS (SELECT 1 FROM " + YHZ_QXB);
			vssql.append(" YHZSJQX WHERE YHZSJQX.F_QXBH='" + qxbh + "' AND ");
			vssql.append(pscol + "=YHZSJQX.F_SJBH");
			vssql.append(" AND EXISTS (SELECT 1 FROM ES_USER_GROUP_RLGL YHZRLGL WHERE YHZSJQX.F_ZBH = YHZRLGL.F_ZBH");
			vssql.append(" AND YHZRLGL.F_YHBH='" + pszgbh + "')");
			vssql.append(" AND YHZSJQX.F_G" + pibzw + "='1'");
			vssql.append(")");
		}
		return vssql.toString();
	}

	/**
	 * 检查角色数据权限
	 * 
	 * @param ROLE_QXB
	 * @param qxbh
	 * @param pibzw
	 * @param pscol
	 * @param pszgbh
	 * @return
	 */
	private static String CheckRoleLimit(String ROLE_QXB, String qxbh,
			String pibzw, String pscol, String pszgbh) {
		StringBuffer vssql = new StringBuffer();
		// 角色权限表
		if (ROLE_QXB != null && !"".equals(ROLE_QXB)) {
			vssql.append(" OR EXISTS (SELECT 1 FROM " + ROLE_QXB);
			vssql.append(" ROLESJQX WHERE ROLESJQX.F_QXBH='" + qxbh);
			vssql.append("' AND " + pscol + "=ROLESJQX.F_SJBH");
			vssql.append(" AND EXISTS (SELECT 1 FROM ES_USER_ROLE_RLGL ROLERLGL WHERE ROLESJQX.F_ROLEGUID = ROLERLGL.F_ROLEGUID");
			vssql.append(" AND ROLERLGL.F_YHBH='" + pszgbh + "')");
			vssql.append(" AND ROLESJQX.F_G" + pibzw + "='1'");
			vssql.append(")");
		}
		return vssql.toString();
	}

	/**
	 * 检查岗位数据权限
	 * 
	 * @param GW_QXB
	 * @param qxbh
	 * @param pibzw
	 * @param pscol
	 * @param pszgbh
	 * @return
	 */
	private static String CheckGWLimit(String GW_QXB, String qxbh, String pibzw,
			String pscol, String pszgbh) {
		StringBuffer vssql = new StringBuffer();
		// 岗位权限表
		if (GW_QXB != null && !"".equals(GW_QXB)) {
			vssql.append(" OR EXISTS (SELECT 1 FROM " + GW_QXB);
			vssql.append(" GWSJQX WHERE GWSJQX.F_QXBH='" + qxbh + "' AND ");
			vssql.append(pscol + "=GWSJQX.F_SJBH");
			vssql.append(" AND EXISTS (SELECT 1 FROM ES_USER_POST_RLGL GWRLGL WHERE GWSJQX.F_GWGUID = GWRLGL.F_GWGUID");
			vssql.append(" AND GWRLGL.F_YHBH='" + pszgbh + "')");
			vssql.append(" AND GWSJQX.F_G" + pibzw + "='1'");
			vssql.append(")");
		}
		return vssql.toString();
	}
}