package com.efounder.JEnterprise.config.shiro.vo;

import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;

import java.io.Serializable;
import java.util.List;

public class Principal implements Serializable {
    private static final long serialVersionUID = -6477583820961243636L;

    private ESUser user;
    private List<ESRole> roles;

    public ESUser getUser() {
        return user;
    }

    public void setUser(ESUser user) {
        this.user = user;
    }

    public List<ESRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ESRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return user.getF_name();
    }

}
