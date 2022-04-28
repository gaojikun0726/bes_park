package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-08-27 18:36:45
 */
@Data
public class PointEntity implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 终端ID
	 */
private Long fGuid;
	/**
	 * 唯一编码--12位-由硬件提供
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
	 * 所属区域名称
	 */
private String fRegionName;
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

	/**
	 * 关联的大屏的硬件编码标识
	 */
	private String screenCode;


	/**
	 * PDU IP地址
	 */
	private String pduIp;


	/**
	 * 音柱 IP地址
	 */
	private String soundIp;

	/**
	 * 动环主板 IP地址
	 */
	private String mainboardIp;


	/**
	 * 摄像头 IP地址
	 */
	private String cameraIp;

	/**
	 * 一键报警 IP地址
	 */
	private String alarmIp;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getFGuid() {
		return fGuid;
	}

	public void setFGuid(Long fGuid) {
		this.fGuid = fGuid;
	}

	public String getFCode() {
		return fCode;
	}

	public void setFCode(String fCode) {
		this.fCode = fCode;
	}

	public String getFPointName() {
		return fPointName;
	}

	public void setFPointName(String fPointName) {
		this.fPointName = fPointName;
	}

	public String getFPointIp() {
		return fPointIp;
	}

	public void setFPointIp(String fPointIp) {
		this.fPointIp = fPointIp;
	}

	public String getFLongitude() {
		return fLongitude;
	}

	public void setFLongitude(String fLongitude) {
		this.fLongitude = fLongitude;
	}

	public String getFLatitude() {
		return fLatitude;
	}

	public void setFLatitude(String fLatitude) {
		this.fLatitude = fLatitude;
	}

	public Integer getFRegionId() {
		return fRegionId;
	}

	public void setFRegionId(Integer fRegionId) {
		this.fRegionId = fRegionId;
	}

	public String getFRegionName() {
		return fRegionName;
	}

	public void setFRegionName(String fRegionName) {
		this.fRegionName = fRegionName;
	}

	public String getFRemark() {
		return fRemark;
	}

	public void setFRemark(String fRemark) {
		this.fRemark = fRemark;
	}

	public String getFIsUse() {
		return fIsUse;
	}

	public void setFIsUse(String fIsUse) {
		this.fIsUse = fIsUse;
	}

	public Integer getFStatus() {
		return fStatus;
	}

	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}

	public Date getFCrdate() {
		return fCrdate;
	}

	public void setFCrdate(Date fCrdate) {
		this.fCrdate = fCrdate;
	}

	public Date getFChdate() {
		return fChdate;
	}

	public void setFChdate(Date fChdate) {
		this.fChdate = fChdate;
	}

	public String getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(String screenCode) {
		this.screenCode = screenCode;
	}

	public String getPduIp() {
		return pduIp;
	}

	public void setPduIp(String pduIp) {
		this.pduIp = pduIp;
	}

	public String getSoundIp() {
		return soundIp;
	}

	public void setSoundIp(String soundIp) {
		this.soundIp = soundIp;
	}

	public String getMainboardIp() {
		return mainboardIp;
	}

	public void setMainboardIp(String mainboardIp) {
		this.mainboardIp = mainboardIp;
	}

	public String getCameraIp() {
		return cameraIp;
	}

	public void setCameraIp(String cameraIp) {
		this.cameraIp = cameraIp;
	}

	public String getAlarmIp() {
		return alarmIp;
	}

	public void setAlarmIp(String alarmIp) {
		this.alarmIp = alarmIp;
	}
}
