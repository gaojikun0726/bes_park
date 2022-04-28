package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:消息监听（接口管理模块）
 * @Date: Created in 14:56 2020/12/10
 * @Modified By:
 */
public class MessageMonitoringModel implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static final Integer CONTROL_STATE = 1; // 控制状态反馈
    public static final Integer REALTIME_STATE = 2; // 实时状态反馈
    public static final Integer CONTROLLER_STATE = 3; // 控制器状态反馈
    public static final Integer AMMETER_DATA = 4; // 电表数据

    /**
     * 主键
     */
    private Integer id;

    /**
     * HTTPS回调地址链接
     */
    private String httpCallback;

    /**
     * 事件类型 1:控制状态反馈      2:实时状态反馈
     */
    private Integer eventType;

    /**
     * 备注
     */
    private String comments;

    private String createTime; // 创建时间

    private String updateTime; // 更新时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHttpCallback() {
        return httpCallback;
    }

    public void setHttpCallback(String httpCallback) {
        this.httpCallback = httpCallback;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
