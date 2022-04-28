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
public class BesHouseholdConf implements BaseEntity<Serializable>{
	/**
	 * 分户编号
	 */
	private String fFhbh;

	/**
	 * 分户名称
	 */
	private String fFhmc;

	/**
	 * 父分户编号
	 */
	private String fPfhbh;

	/**
	 * 人数
	 */
	private String fPersonNums;

	/**
	 * 面积
	 */
	private String fArea;

	/**
	 * 级数
	 */
	private String fJs;

	/**
	 * 明细
	 */
	private String fMx;

	/**
	 * 所属位置
	 */
	private String fLocation;

	/**
	 * 能源编号
	 */
	private String fNybh;
	private String fNymc;

	/**
	 * 园区编号
	 */
	private String fYqbh;

	/**
	 * 分户单位(分户的单位)
	 */
	private String fFhlx;

	/**
	 *
	 * 是否级联
	 *
	 */
	private String fJl;

	/**
	 * 创建日期
	 */
	private String fCrdate;

	/**
	 * 修改日期
	 */
	private String fChdate;
	
	/**
	 *
	 * @Description: 用户名称
	 * 
	 * @auther: wanghongjie
	 * @date: 14:25 2021/3/15
	 * @param: []
	 * @return: java.lang.String
	 *
	 */
	private String fUserName;

	/**
	 *
	 * @Description: 组织机构id
	 *
	 * @auther: wanghongjie
	 * @date: 14:25 2021/3/15
	 * @param: []
	 * @return: java.lang.String
	 *
	 */
	private String fZzjgId;

	public String getfFhbh() {
		return fFhbh;
	}

	public void setfFhbh(String fFhbh) {
		this.fFhbh = fFhbh == null ? null : fFhbh.trim();
	}

	public String getfFhmc() {
		return fFhmc;
	}

	public void setfFhmc(String fFhmc) {
		this.fFhmc = fFhmc == null ? null : fFhmc.trim();
	}

	public String getfPfhbh() {
		return fPfhbh;
	}

	public void setfPfhbh(String fPfhbh) {
		this.fPfhbh = fPfhbh == null ? null : fPfhbh.trim();
	}

	public String getfPersonNums() {
		return fPersonNums;
	}

	public void setfPersonNums(String fPersonNums) {
		this.fPersonNums = fPersonNums == null ? null : fPersonNums.trim();
	}

	public String getfArea() {
		return fArea;
	}

	public void setfArea(String fArea) {
		this.fArea = fArea == null ? null : fArea.trim();
	}

	public String getfJs() {
		return fJs;
	}

	public void setfJs(String fJs) {
		this.fJs = fJs == null ? null : fJs.trim();
	}

	public String getfMx() {
		return fMx;
	}

	public void setfMx(String fMx) {
		this.fMx = fMx == null ? null : fMx.trim();
	}

	public String getfLocation() {
		return fLocation;
	}

	public void setfLocation(String fLocation) {
		this.fLocation = fLocation == null ? null : fLocation.trim();
	}

	public String getfNybh() {
		return fNybh;
	}

	public void setfNybh(String fNybh) {
		this.fNybh = fNybh == null ? null : fNybh.trim();
	}

	public String getfYqbh() {
		return fYqbh;
	}

	public void setfYqbh(String fYqbh) {
		this.fYqbh = fYqbh == null ? null : fYqbh.trim();
	}

	public String getfCrdate() {
		return fCrdate;
	}

	public void setfCrdate(String fCrdate) {
		this.fCrdate = fCrdate;
	}

	public String getfChdate() {
		return fChdate;
	}

	public void setfChdate(String fChdate) {
		this.fChdate = fChdate;
	}

	public String getfFhlx() {
		return fFhlx;
	}

	public void setfFhlx(String fFhlx) {
		this.fFhlx = fFhlx;
	}

	public String getfJl() {
		return fJl;
	}

	public void setfJl(String fJl) {
		this.fJl = fJl;
	}
	public String getfUserName() {
		return fUserName;
	}

	public void setfUserName(String fUserName) {
		this.fUserName = fUserName;
	}

	public String getfZzjgId() {
		return fZzjgId;
	}

	public void setfZzjgId(String fZzjgId) {
		this.fZzjgId = fZzjgId;
	}

	public String getfNymc() {
		return fNymc;
	}

	public void setfNymc(String fNymc) {
		this.fNymc = fNymc;
	}
}