package com.efounder.JEnterprise.zhjg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 设备维护
 * 
 * @author YangChao
 * @date 2020-10-09 14:28:58
 */
@Data
public class CoverpointEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 终端ID
	 */
private Long fGuid;
	/**
	 * 唯一编码
	 */
private String fCode;
	/**
	 * 点位名称
	 */
private String fPointName;
	/**
	 * 终端IP
	 */
private String fPointIp;
	/**
	 * 经度
	 */
private String fLongitude;
	/**
	 * 纬度
	 */
private String fLatitude;
	/**
	 * 所属区域
	 */
private Integer fRegionId;
	/**
	 * 详细地址
	 */
private String fRemark;
	/**
	 * 是否启用1是0否
	 */
private String fIsUse;
	/**
	 * 0正常、1警告、2掉线
	 */
private Integer fStatus;
	/**
	 * 添加时间
	 */
private Date fCrdate;
	/**
	 * 修改时间
	 */
private Date fChdate;

}
