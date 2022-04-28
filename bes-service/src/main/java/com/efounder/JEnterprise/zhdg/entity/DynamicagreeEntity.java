package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 动态协议配置
 * 
 * @author YangChao
 * @date 2020-09-03 09:40:12
 */
@Data
public class DynamicagreeEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
private Long id;
	/**
	 * 指令码
	 */
private Integer ordercode;
	/**
	 * 协议类型id
	 */
private Integer agreeid;
	/**
	 * 名称
	 */
private String name;
	/**
	 * 对象名称
	 */
private String oname;
	/**
	 * 协议起始位
	 */
private Integer start;
	/**
	 * 协议终止位
	 */
private Integer end;
	/**
	 * 0-是/解析 1-否/不解析 
	 */
private Integer analysisposition;
	/**
	 * 字节位 位置  1个字节8位
	 */
private Integer position;
	/**
	 * 报警类型id--关联seb_warnType
	 */
private Long warnid;
	/**
	 * 报警类型
	 */
private String warnName;
	/**
	 * 协议处理类型id---关联seb_agreeHandle
	 */
private Integer handleid;
	/**
	 * 协议处理类型
	 */
private String handleidname;
	/**
	 * 0.启用--1.停用
	 */
private Integer state;

}
