package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 点位报警维护
 * 
 * @author YangChao
 * @date 2020-09-16 10:20:05
 */
@Data
public class PointwarnEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
private Long id;
	/**
	 * 点位id--seb_point-fcode
	 */
private String pointid;
	/**
	 * 点位id数组
	 */
private String [] pointids;
	/**
	 * 点位名称
	 */
private String f_point_name;
	/**
	 * 报警id--关联seb-warntype
	 */
private Long warnid;
	/**
	 * 报警类型
	 */
private String warntype;
	/**
	 * 报警阈值类型--0.是/否类型--1.上限下限类型
	 */
private String vtype;
	/**
	 * 报警阈值类型1--0正常-1告警
	 */
private String vtype0;
	/**
	 * 报警阈值类型2-下限
	 */
private String vtype1Min;
	/**
	 * 报警阈值类型2-上限
	 */
private String vtype1Max;



}
