package com.efounder.JEnterprise.platform.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 区域位置信息对象 opm_position_info
 * 
 * @author ruoyi
 * @date 2020-10-29
 */
public class OpmPositionInfo
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 上级ID */
    private String pid;

    /** 地址描述 */
    private String addressDesc;

    /** 最高层数 */
    private Long maxFloor;

    /** 层数 */
    private Long floorNum;

    /** 类型，0：园区、1：场地:2：单元、3：楼层、4：房间、5：出入口、6：停车场、7：其它 8：楼宇 9.开放区 */
    private String type;

    /** 区域用途 */
    private String areaUses;

    /** 使用面积 */
    private String usableArea;

    /** 	建组面积 */
    private String builtArea;

    /** 公摊面积 */
    private String poolArea;

    /** 排序 */
    private String sort;

    /** 删除标志(0代表存在 1代表删除  默认值0） */
    private Integer hasDelete;

    /** 创建者 */
    private String creator;

    /** 创建时间 */
    private Date createDate;

    /** 更新者 */
    private String updater;

    /** 更新时间 */
    private Date updateDate;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPid(String pid) 
    {
        this.pid = pid;
    }

    public String getPid() 
    {
        return pid;
    }
    public void setAddressDesc(String addressDesc) 
    {
        this.addressDesc = addressDesc;
    }

    public String getAddressDesc() 
    {
        return addressDesc;
    }
    public void setMaxFloor(Long maxFloor) 
    {
        this.maxFloor = maxFloor;
    }

    public Long getMaxFloor() 
    {
        return maxFloor;
    }
    public void setFloorNum(Long floorNum) 
    {
        this.floorNum = floorNum;
    }

    public Long getFloorNum() 
    {
        return floorNum;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAreaUses(String areaUses) 
    {
        this.areaUses = areaUses;
    }

    public String getAreaUses() 
    {
        return areaUses;
    }
    public void setUsableArea(String usableArea) 
    {
        this.usableArea = usableArea;
    }

    public String getUsableArea() 
    {
        return usableArea;
    }
    public void setBuiltArea(String builtArea) 
    {
        this.builtArea = builtArea;
    }

    public String getBuiltArea() 
    {
        return builtArea;
    }
    public void setPoolArea(String poolArea) 
    {
        this.poolArea = poolArea;
    }

    public String getPoolArea() 
    {
        return poolArea;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
    {
        return sort;
    }
    public void setHasDelete(Integer hasDelete) 
    {
        this.hasDelete = hasDelete;
    }

    public Integer getHasDelete() 
    {
        return hasDelete;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdater(String updater) 
    {
        this.updater = updater;
    }

    public String getUpdater() 
    {
        return updater;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("pid", getPid())
            .append("addressDesc", getAddressDesc())
            .append("maxFloor", getMaxFloor())
            .append("floorNum", getFloorNum())
            .append("type", getType())
            .append("areaUses", getAreaUses())
            .append("usableArea", getUsableArea())
            .append("builtArea", getBuiltArea())
            .append("poolArea", getPoolArea())
            .append("sort", getSort())
            .append("hasDelete", getHasDelete())
            .append("creator", getCreator())
            .append("createDate", getCreateDate())
            .append("updater", getUpdater())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
