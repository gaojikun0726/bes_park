package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 区域表
 * 
 * @author YangChao
 * @date 2020-08-28 11:48:44
 */
@Data
public class RegionEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
private Integer fId;
	/**
	 * 区域名称
	 */
private String fRegionName;
	/**
	 * 备注
	 */
private String fRemark;
	/**
	 * 创建时间
	 */
private Date fCreateTime;

}
