package com.efounder.JEnterprise.zhdg.entity;

import com.core.common.BaseEntity;

import java.util.Date;

/**
 * 海康威视配置实体类
 * */
public class CameraManageHk implements BaseEntity<String> {
    private static final long serialVersionUID = 1L;
    /*主键*/
    private Long id;
    /*摄像头编号*/
    private String cameraNum;
    /*点位id*/
    private String coverId;
    /*摄像头名称*/
    private String cameraName;
    /*登录用户*/
    private String cameraUser;
    /*登陆密码*/
    private String cameraPassword;
    /*ip*/
    private String cameraIp;
    /*端口*/
    private Integer cameraPort;
    /*备注*/
    private String remark;
    /*创建时间*/
    private Date creatTime;
    /*更新时间*/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraNum() {
        return cameraNum;
    }

    public void setCameraNum(String cameraNum) {
        this.cameraNum = cameraNum;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraUser() {
        return cameraUser;
    }

    public void setCameraUser(String cameraUser) {
        this.cameraUser = cameraUser;
    }

    public String getCameraPassword() {
        return cameraPassword;
    }

    public void setCameraPassword(String cameraPassword) {
        this.cameraPassword = cameraPassword;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }

    public Integer getCameraPort() {
        return cameraPort;
    }

    public void setCameraPort(Integer cameraPort) {
        this.cameraPort = cameraPort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
