package com.efounder.JEnterprise.mapper.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ESSysLoginLogMapper {
    //分页显示系统登录日志信息
	List<ESSysLoginLog> loadsysLoginlogByKey(@Param("keywords") String keywords);
    //用户登录时插入日志信息
	public boolean addSysLoginLogid(ESSysLoginLog esSysLoginLog);



}