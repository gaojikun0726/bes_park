package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组功能权限Mapper
 * @author tianjiangwei
 * @datetiem 2018年9月30日
 */
@Mapper
public interface ESUserGroupGnqxQueryMapper {

	List<ESGnzdManage> loadGroupGnzdData(@Param("f_zbh") String f_zbh);

}
