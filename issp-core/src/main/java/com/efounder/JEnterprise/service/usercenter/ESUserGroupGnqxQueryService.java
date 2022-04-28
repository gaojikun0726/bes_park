package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;

import java.util.List;

/**
 * @Description 用户组功能权限查询接口
 * @author tianjiangwei
 */
public interface ESUserGroupGnqxQueryService {

	List<ESGnzdManage> queryGroupGnzdData(String f_zbh);

	
}
