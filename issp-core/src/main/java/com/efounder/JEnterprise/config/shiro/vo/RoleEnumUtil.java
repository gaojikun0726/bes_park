package com.efounder.JEnterprise.config.shiro.vo;

import com.efounder.JEnterprise.model.usercenter.ESRole;

public enum RoleEnumUtil {
    
    超级管理员("超级管理员","admin_role","超级管理员"),
    普通用户("普通用户","common_role","普通用户");

    private RoleEnumUtil(String name, String roleCode, String remark) {
        this.name = name;
        this.roleCode = roleCode;
        this.remark = remark;
    }

    private String name;
    private String roleCode;
    private String remark;
    
    public ESRole getRole(){
        ESRole role = new ESRole();
        role.setF_rolemc(this.name());
        role.setF_rolebh(this.roleCode);
//        role.setRemark(this.remark); //备注
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
