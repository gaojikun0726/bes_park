package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 数据权限管理
 * @author gongfanfei
 * @datetime 2018年5月10日
 */
public class ESUserSjqxManage implements BaseEntity<Serializable>{

	private static final long serialVersionUID = 1213123L;

	
	private String f_yhbh;// 用户编号
	private String f_qxbh;// 权限编号
	private String f_sjbh;// 数据编号
	private String f_sjmc;// 数据名称（此处不与数据表同步）
	private String f_qxbz;// 权限标志，作为15个标志位使用，每一位代表一种权限，同数据权限字典中的权限内容对应',
	private String f_g0;// 权限标志0
	private String f_g1;// 权限标志1
	private String f_g2;//
	private String f_g3;//
	private String f_g4;//
	private String f_g5;//
	private String f_g6;//
	private String f_g7;//
	private String f_g8;//
	private String f_crdate;// 创建日期
	private String f_chdate;// 修改日期
	public String getF_yhbh() {
		return f_yhbh;
	}
	public void setF_yhbh(String f_yhbh) {
		this.f_yhbh = f_yhbh;
	}
	public String getF_qxbh() {
		return f_qxbh;
	}
	public void setF_qxbh(String f_qxbh) {
		this.f_qxbh = f_qxbh;
	}
	
	public String getF_sjmc() {
		return f_sjmc;
	}
	public void setF_sjmc(String f_sjmc) {
		this.f_sjmc = f_sjmc;
	}
	public String getF_sjbh() {
		return f_sjbh;
	}
	public void setF_sjbh(String f_sjbh) {
		this.f_sjbh = f_sjbh;
	}
	public String getF_qxbz() {
		return f_qxbz;
	}
	public void setF_qxbz(String f_qxbz) {
		this.f_qxbz = f_qxbz;
	}
	
	public String getF_g0() {
		return f_g0;
	}
	public void setF_g0(String f_g0) {
		this.f_g0 = f_g0;
	}
	public String getF_g1() {
		return f_g1;
	}
	public void setF_g1(String f_g1) {
		this.f_g1 = f_g1;
	}
	public String getF_g2() {
		return f_g2;
	}
	public void setF_g2(String f_g2) {
		this.f_g2 = f_g2;
	}
	public String getF_g3() {
		return f_g3;
	}
	public void setF_g3(String f_g3) {
		this.f_g3 = f_g3;
	}
	public String getF_g4() {
		return f_g4;
	}
	public void setF_g4(String f_g4) {
		this.f_g4 = f_g4;
	}
	public String getF_g5() {
		return f_g5;
	}
	public void setF_g5(String f_g5) {
		this.f_g5 = f_g5;
	}
	public String getF_g6() {
		return f_g6;
	}
	public void setF_g6(String f_g6) {
		this.f_g6 = f_g6;
	}
	public String getF_g7() {
		return f_g7;
	}
	public void setF_g7(String f_g7) {
		this.f_g7 = f_g7;
	}
	public String getF_g8() {
		return f_g8;
	}
	public void setF_g8(String f_g8) {
		this.f_g8 = f_g8;
	}
	public String getF_crdate() {
		return f_crdate;
	}
	public void setF_crdate(String f_crdate) {
		this.f_crdate = f_crdate;
	}
	public String getF_chdate() {
		return f_chdate;
	}
	public void setF_chdate(String f_chdate) {
		this.f_chdate = f_chdate;
	}


}
