package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色功能权限Mapper
 * @author tianjiangwei
 * @datetiem 2018年9月11日
 */
@Mapper
public interface ESUserRoleGnqxQueryMapper {

	List<ESGnzdManage> loadRoleGnzdData(@Param("f_rolebh") String f_rolebh);

	/*List<ESGnzdManage> loadAllRoleGnzdData();

	List<ESGnzdManage> loadAllRoleGnzdDatasc();*/

}
