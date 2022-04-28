package com.efounder.JEnterprise.service.systemcenter.logmanage.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.systemcenter.logmanage.ESSysQxOperateLogMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysQxOperateLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description 系统操作日志维护的实现类
 * @author liuhoujun
 */
@Service("ESSysQxOperateLogService")
public class ESSysQxOperateLogServiceImpl implements ESSysQxOperateLogService, ESBaseService {

	@Autowired
	private ESSysQxOperateLogMapper  esSysQxOperateLogMapper;
	
	@Override
	public PageInfo<ESSysQxOperateLog> getqxlmcxList(Integer bars,Integer pageNum, String fOpcontent, String fOpname, String fOptype,
			String startTime, String endTime) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum, bars);
		List<ESSysQxOperateLog> list = esSysQxOperateLogMapper.getqxlmcxList(fOpcontent, fOpname, fOptype, startTime, endTime);
		// 用PageInfo对结果进行包装
		return new PageInfo<ESSysQxOperateLog>(list);
	}
	@Override
	public ISSPReturnObject getlookqxopcontent(String fid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		ESSysQxOperateLog qxoperatelog = esSysQxOperateLogMapper.lookqxopcontent(fid);
		returnObject.setData(qxoperatelog);
		return returnObject;
	}
	@Override
	public int insertOperateLog(ESSysQxOperateLog record) {
		return esSysQxOperateLogMapper.insertOperateLog(record);
	}

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
	@Override
	public PageInfo<ESSysLoginLog> loadsysQxOperateloglists(Integer bars, Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		List<ESSysLoginLog> list = esSysQxOperateLogMapper.loadsysQxOperateloglists(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESSysLoginLog> page = new PageInfo<ESSysLoginLog>(list);
		return page;
	}
}
