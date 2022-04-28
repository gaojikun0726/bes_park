package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 设备
 * @author xiepufeng
 * @date 2020/2/11 15:32
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class HostLinkageDeviceModel {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer hostId;

    private String name;

    private String pointDisplay;

    private String point;

    private Integer isFaultPoint;

    private String faultFineValue;

    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointDisplay() {
        return pointDisplay;
    }

    public void setPointDisplay(String pointDisplay) {
        this.pointDisplay = pointDisplay;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Integer getIsFaultPoint()
    {
        return isFaultPoint;
    }

    public void setIsFaultPoint(Integer isFaultPoint)
    {
        this.isFaultPoint = isFaultPoint;
    }

    public String getFaultFineValue()
    {
        return faultFineValue;
    }

    public void setFaultFineValue(String faultFineValue)
    {
        this.faultFineValue = faultFineValue;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
