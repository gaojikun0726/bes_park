package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;
/**
 * 运行模式
 * @author xiepufeng
 */
public class HostLinkageRunningModeModel
{

    public static final Integer RUN_MODEL_TYPE = 1;

    public static final Integer START_STOP_TYPE = 2;

    public static final Integer START_STOP_STATE_TYPE = 3;

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer machineSetId; // 所属主机

    private String joinPoint; // 关联点（系统名称）

    private Integer type; // 关联点类型（1: 模式关联 2：启停关联 3：启停状态关联）

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
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

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getJoinPoint()
    {
        return joinPoint;
    }

    public void setJoinPoint(String joinPoint)
    {
        this.joinPoint = joinPoint;
    }
}
