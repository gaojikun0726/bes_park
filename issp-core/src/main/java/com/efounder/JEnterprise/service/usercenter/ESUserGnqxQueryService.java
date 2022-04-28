package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;

import java.util.List;

/**
 * @Description 用户功能权限查询接口
 * @author tianjiangwei
 */
public interface ESUserGnqxQueryService {

	List<ESGnzdManage> queryGnzdData(String f_yhbh);

	List<ESGnzdManage> loadAllGnzdData();

	List<ESGnzdManage> loadAllGnzdDatasc();

	List<ESGnzdManage> loadAllModuleData();

	
	
}
