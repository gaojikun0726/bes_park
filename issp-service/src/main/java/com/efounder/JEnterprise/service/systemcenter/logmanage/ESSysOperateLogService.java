package com.efounder.JEnterprise.service.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 系统操作日志维护
 * @author liuhoujun
 *
 */
public interface ESSysOperateLogService {

	public PageInfo<ESSysOperateLog> getlmcxList(Integer bars,Integer pageNum, @Param("fcomment") String fcomment, @Param("fyhbh") String fyhbh, @Param("ftype") String ftype, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	/**
	 *
	 * @Description: 操作日志分页加载
	 *
	 * @auther: wanghongjie
	 * @date: 9:54 2020/9/25
	 * @param: [bars, pageNum, keywords]
	 * @return: com.github.pagehelper.PageInfo<com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog>
	 *
	 */
    PageInfo<ESSysLoginLog> loadsysOperateloglistss(Integer bars, Integer pageNum, String keywords);
}
