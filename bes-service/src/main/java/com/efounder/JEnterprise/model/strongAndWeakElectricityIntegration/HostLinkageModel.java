package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

/**
 * 主机
 * @author xiepufeng
 * @date 2020/2/11 15:32
 */
public class HostLinkageModel {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer machineSetId; // 所属机组

    private String name; // 主机名称

    private String pointDisplay; // 控制关联点显示名称（别名）

    private String point; // 控制关联点（系统名称）

    private String pointStateDisplay; // 状态关联点显示名称（别名）

    private String pointState; // 状态关联点（系统名称）

    private String comments; // 备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMachineSetId()
    {
        return machineSetId;
    }

    public void setMachineSetId(Integer machineSetId)
    {
        this.machineSetId = machineSetId;
    }

    public String getName() {
        return name;
    }

    public String getPointDisplay()
    {
        return pointDisplay;
    }

    public void setPointDisplay(String pointDisplay)
    {
        this.pointDisplay = pointDisplay;
    }

    public String getPoint()
    {
        return point;
    }

    public void setPoint(String point)
    {
        this.point = point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPointStateDisplay()
    {
        return pointStateDisplay;
    }

    public void setPointStateDisplay(String pointStateDisplay)
    {
        this.pointStateDisplay = pointStateDisplay;
    }

    public String getPointState()
    {
        return pointState;
    }

    public void setPointState(String pointState)
    {
        this.pointState = pointState;
    }
}
