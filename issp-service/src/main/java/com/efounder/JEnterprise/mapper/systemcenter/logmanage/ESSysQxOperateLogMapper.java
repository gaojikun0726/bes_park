package com.efounder.JEnterprise.mapper.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限日志分页显示数据
 * 
 * @author zc_liuhoujun
 *
 */
@Mapper
public interface ESSysQxOperateLogMapper {

	//分页显示系统权限日志信息
	List<ESSysQxOperateLog> loadsysQxOperatelogByKey(@Param("keywords") String keywords);
	
	// 联名查询
	List<ESSysQxOperateLog> getqxlmcxList(@Param("fOpcontent") String fOpcontent, @Param("fOpname") String fOpname,
			@Param("fOptype") String fOptype, @Param("startTime") String startTime, @Param("endTime") String endTime);
	
	//根据id获取content的值
	ESSysQxOperateLog lookqxopcontent(String fid);
	
	//添加权限日志
	int insertOperateLog(ESSysQxOperateLog record);

	/**
	 *
	 * @Description: 权限日志信息查询
	 *
	 * @auther: wanghongjie
	 * @date: 10:00 2020/9/25
	 * @param: [keywords]
	 * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog>
	 *
	 */
    List<ESSysLoginLog> loadsysQxOperateloglists(@Param("keywords") String keywords);
}
