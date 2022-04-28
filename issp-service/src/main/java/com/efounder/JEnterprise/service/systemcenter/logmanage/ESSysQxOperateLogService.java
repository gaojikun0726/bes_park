package com.efounder.JEnterprise.service.systemcenter.logmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 系统权限日志维护
 * @author liuhoujun
 *
 */
public interface ESSysQxOperateLogService {
		
		// 联动查询操作
		public PageInfo<ESSysQxOperateLog> getqxlmcxList(Integer bars,Integer pageNum, @Param("fOpcontent") String fOpcontent,
				@Param("fOpname") String fOpname, @Param("fOptype") String fOptype, @Param("startTime") String startTime,
				@Param("endTime") String endTime);
		
		//根据id查询权限日志详情
		ISSPReturnObject getlookqxopcontent(String fid);
		/**
		 * 添加权限日志
		 * @param record
		 * @return
		 */
	    int insertOperateLog(ESSysQxOperateLog record);


	    /**
	     *
	     * @Description: 权限日志信息查询
	     *
	     * @auther: wanghongjie
	     * @date: 9:59 2020/9/25
	     * @param: [bars, pageNum, keywords]
	     * @return: com.github.pagehelper.PageInfo<com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog>
	     *
	     */
	PageInfo<ESSysLoginLog> loadsysQxOperateloglists(Integer bars, Integer pageNum, String keywords);
}
