package com.efounder.JEnterprise.service.auth.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESRoleMapper;
import com.efounder.JEnterprise.mapper.usercenter.ESUserMapper;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authService")
public class AuthServiceImpl implements AuthService,ESBaseService {

    @Autowired
    private ESUserMapper userMapper;

    @Autowired
    private ESRoleMapper roleMapper;

    @Override
    public ESUser findUserByName(String username) {
        return userMapper.findUserById(username);
    }

    @Override
    public ESRole findRoleByRoleCode(String roleCode) {
        return roleMapper.findRoleById(roleCode);
    }

    @Override
    public List<ESUser> findUserByRoleCode(String roleCode) {
        return roleMapper.findUserByRoleId(roleCode);
    }

}
