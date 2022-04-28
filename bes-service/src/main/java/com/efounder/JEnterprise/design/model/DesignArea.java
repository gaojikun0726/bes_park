package com.efounder.JEnterprise.design.model;

import lombok.Data;


/**
 * 区域实体类
 */
@Data
public class DesignArea {


    /**
     * 主键
     */
    private String id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域上级节点id
     */
    private String parent;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 隐藏状态
     */
    private String hideState;

    /**
     * 排序顺序
     */
    private String sortOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public String getHideState() {
        return hideState;
    }

    public void setHideState(String hideState) {
        this.hideState = hideState;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
