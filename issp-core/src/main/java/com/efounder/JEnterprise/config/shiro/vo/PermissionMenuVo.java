package com.efounder.JEnterprise.config.shiro.vo;

import com.efounder.JEnterprise.model.auth.PermissionMenu;

import java.util.List;

public class PermissionMenuVo extends PermissionMenu {

    private static final long serialVersionUID = -2051933842290600230L;

    private List<PermissionMenuVo> children;

    public List<PermissionMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionMenuVo> children) {
        this.children = children;
    }

}
