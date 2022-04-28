package org.ace.business.dto.ldc;

/**
 * @author sunzhiyuan
 * @Data 2020/12/22 16:40
 */
public class ControlPointLDC {

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private Integer pointID; // 点的ID号

    private Integer runValue; // 运行值,数字量0或255，模拟量是个数值

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public Integer getPointID()
    {
        return pointID;
    }

    public void setPointID(Integer pointID)
    {
        this.pointID = pointID;
    }

    public Integer getRunValue()
    {
        return runValue;
    }

    public void setRunValue(Integer runValue)
    {
        this.runValue = runValue;
    }

}
