package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;

import java.util.List;

/**
 * @Description 用户角色功能权限查询接口
 * @author tianjiangwei
 */
public interface ESUserRoleGnqxQueryService {

	List<ESGnzdManage> queryRoleGnzdData(String f_rolebh);

	/*List<ESGnzdManage> loadAllRoleGnzdData();

	List<ESGnzdManage> loadAllRoleGnzdDatasc();*/

	

	
	
}
