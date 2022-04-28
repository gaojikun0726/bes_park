package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 分户配置表
 * 
 * @author suhang
 * 
 * @date 2018-07-18
 */
public class BesDepartmentConf implements BaseEntity<Serializable>{
	/**
	 * 部门编号
	 */
	private String fDepId;

	/**
	 * 支路编号
	 */
	private String fZlbh;

	/**
	 * 支路ID
	 */
	private String fZlId;

	/**
	 * 支路系数
	 */
	private Double fZlxs;

	/**
	 * 电表ID
	 */
	private String fDbId;

	/**
	 * 电表系统名称
	 */
	private String fDbSysName;

	/**
	 * 电表系数
	 */
	private Double fDbxs;

	/**
	 * 创建日期
	 */
	private String createTime;

	/**
	 * 修改日期
	 */
	private String updateTime;

	public String getfDepId() {
		return fDepId;
	}

	public void setfDepId(String fDepId) {
		this.fDepId = fDepId;
	}

	public String getfZlbh() {
		return fZlbh;
	}

	public void setfZlbh(String fZlbh) {
		this.fZlbh = fZlbh;
	}

	public String getfZlId() {
		return fZlId;
	}

	public void setfZlId(String fZlId) {
		this.fZlId = fZlId;
	}

	public Double getfZlxs() {
		return fZlxs;
	}

	public void setfZlxs(Double fZlxs) {
		this.fZlxs = fZlxs;
	}

	public String getfDbId() {
		return fDbId;
	}

	public void setfDbId(String fDbId) {
		this.fDbId = fDbId;
	}

	public String getfDbSysName() {
		return fDbSysName;
	}

	public void setfDbSysName(String fDbSysName) {
		this.fDbSysName = fDbSysName;
	}

	public Double getfDbxs() {
		return fDbxs;
	}

	public void setfDbxs(Double fDbxs) {
		this.fDbxs = fDbxs;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}