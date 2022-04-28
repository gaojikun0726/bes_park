package com.efounder.JEnterprise.mapper.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志分页显示数据以及联名查询
 * 
 * @author zc_liuhoujun
 *
 */
@Mapper
public interface ESSysOperateLogMapper {
	// 分页显示系统操作日志信息
	List<ESSysOperateLog> loadsysOperatelogByKey(@Param("keywords") String keywords);

	// 联名查询
	List<ESSysOperateLog> getlmcxList(@Param("fcomment") String fcomment, @Param("fname") String fname,
			@Param("ftype") String ftype, @Param("startTime") String startTime, @Param("endTime") String endTime);

	/**
	 *
	 * @Description: 根据条件加载操作日志信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:55 2020/9/25
	 * @param: [keywords]
	 * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog>
	 *
	 */
	List<ESSysLoginLog> loadsysOperateloglistss(@Param("keywords") String keywords);
}
