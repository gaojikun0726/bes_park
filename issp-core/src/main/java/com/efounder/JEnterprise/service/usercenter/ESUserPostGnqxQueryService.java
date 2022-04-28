package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;

import java.util.List;

/**
 * @Description 用户岗位功能权限查询接口
 * @author tianjiangwei
 */
public interface ESUserPostGnqxQueryService {

	List<ESGnzdManage> queryPostGnzdData(String f_gwguid);
}
