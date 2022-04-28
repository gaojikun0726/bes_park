package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户功能权限Mapper
 * @author tianjiangwei
 * @datetiem 2018年9月11日
 */
@Mapper
public interface ESUserGnqxQueryMapper {

	List<ESGnzdManage> loadGnzdData(@Param("f_yhbh") String f_yhbh);

	List<ESGnzdManage> loadAllGnzdData();

	List<ESGnzdManage> loadAllGnzdDatasc();

	List<ESGnzdManage> loadAllModuleData();

	
	
}
