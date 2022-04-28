package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 协议处理类型维护
 * 
 * @author YangChao
 * @date 2020-08-31 17:41:28
 */
@Data
public class AgreehandleEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
private Long id;
	/**
	 * 处理类型名称
	 */
private String name;
	/**
	 * 处理类型说明
	 */
private String remarks;

}
