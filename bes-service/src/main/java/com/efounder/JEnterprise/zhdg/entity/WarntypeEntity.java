package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 报警类型维护表
 * 
 * @author YangChao
 * @date 2020-09-14 11:20:24
 */
@Data
public class WarntypeEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
private Long id;
	/**
	 * 报警类型
	 */
private String warntype;

}
