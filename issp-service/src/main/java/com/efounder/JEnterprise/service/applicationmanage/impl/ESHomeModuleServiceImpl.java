package com.efounder.JEnterprise.service.applicationmanage.impl;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.applicationmanage.ESHomeModuleMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESHomeModule;
import com.efounder.JEnterprise.service.applicationmanage.ESHomeModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ESHomeModuleService")
public class ESHomeModuleServiceImpl implements ESHomeModuleService{
	
	@Autowired
	private ESHomeModuleMapper esHomeModuleMapper;
	
	@Value("${JEnterprise.ApplicationId}")
	private String ApplicationId;

	
	public String getApplicationId() {
		return ApplicationId;
	}

	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}


	@Override
	public PageInfo<ESHomeModule> findByPage(Integer bars,Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		
		List<ESHomeModule> pageList = esHomeModuleMapper.findByPage(keywords);
		
		// 用PageInfo对结果进行包装
		PageInfo<ESHomeModule> page = new PageInfo<ESHomeModule>(pageList);
		return page;
	}

	@Override
	public ISSPReturnObject addESHomeModule(ESHomeModule esHomeModule) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		String f_guid =  UUIDUtil.getRandom32BeginTimePK();
		esHomeModule.setGuid(f_guid);
		esHomeModule.setF_xtbh(ApplicationId);
		boolean flag = esHomeModuleMapper.insert(esHomeModule);
//		插入之后插入
		try {
			//OperationLog.insert(esHomeModule.getGuid(), "es_home_module");
		if (flag) {	
			returnData.setStatus("1");
			returnData.setMsg("添加成功！");
		} else {
			returnData.setStatus("0");
			returnData.setMsg("添加失败！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnData;
	}

	@Override
	public ISSPReturnObject delESHomeModule(String guid) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		//删除之前删除
		try {
			//OperationLog.delete(guid, "es_home_module");
		boolean flag = esHomeModuleMapper.delByGuid(guid);
		if (flag) {
			returnData.setStatus("1");
			returnData.setMsg("删除成功！");
		} else {
			returnData.setStatus("0");
			returnData.setMsg("删除失败！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnData;
	}

	@Override
	public ISSPReturnObject updESHomeModule(ESHomeModule esHomeModule) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		Map<String, Object> startMap;
		try {
			//startMap = OperationLog.updateStart(esHomeModule.getGuid(), "es_home_module");
		boolean flag = esHomeModuleMapper.update(esHomeModule);
		//OperationLog.updateEnd(esHomeModule.getGuid(), "es_home_module", startMap);
		if (flag) {
			returnData.setStatus("1");
			returnData.setMsg("修改成功！");
		} else {
			returnData.setStatus("0");
			returnData.setMsg("修改失败！");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnData;
	}

	@Override
	public ISSPReturnObject loadESHomeModuleByGuid(String guid) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		try {
			ESHomeModule esHomeModule = esHomeModuleMapper.findByGuid(guid);
			returnData.setStatus("1");
			returnData.setData(esHomeModule);
		} catch (Exception e) {
			returnData.setStatus("0");
		}
		return returnData;
	}

}
