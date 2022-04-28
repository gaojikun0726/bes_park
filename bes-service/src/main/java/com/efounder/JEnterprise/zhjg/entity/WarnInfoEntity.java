package com.efounder.JEnterprise.zhjg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 报警历史记录表
 * 
 * @author YangChao
 * @date 2020-09-14 11:54:54
 */
@Data
public class WarnInfoEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
private Long id;
	/**
	 * 点位id--关联seb_coverpoint-f_code
	 */
private String coverid;
	/**
	 * 报警id
	 */
private Long warnid;
	/**
	 * 报警类型
	 */
private String warntype;
	/**
	 * 报警说明
	 */
private String warnmsg;
	/**
	 * 创建时间
	 */
private String creattime;

}
