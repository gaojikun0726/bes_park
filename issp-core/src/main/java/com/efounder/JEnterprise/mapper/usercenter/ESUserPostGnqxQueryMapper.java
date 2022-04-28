package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位功能权限Mapper
 * @author tianjiangwei
 * @datetiem 2018年9月30日
 */
@Mapper
public interface ESUserPostGnqxQueryMapper {

	List<ESGnzdManage> loadPostGnzdData(@Param("f_gwguid") String f_gwguid);


}
