package com.efounder.JEnterprise.service.applicationmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.applicationmanage.ESXtListMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("esXtService")
public class ESXtListServiceImpl implements ESXtListService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESXtListServiceImpl.class);
	@Autowired
	private OperationConfig operationConfig;
	/**
	 * ApplicationName:应用ID
	 * 
	 * @since 1.0.0
	 */
	@Value("${JEnterprise.ApplicationId}")
	private String ApplicationId;

	public String getApplicationId() {
		return ApplicationId;
	}

	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}
	@Autowired
	private ESXtListMapper xtMapper;

	@Override
	public List<ESXtList> findCurrentXt() {
		log.info("#xtMapper获取子系统信息");
		return xtMapper.findCurrentXt(ApplicationId);
	}
	@Override
	public List<ESXtList> findXts() {
		log.info("#xtMapper获取子系统信息");
		return xtMapper.findXts();
	}

	@Override
	public ESXtList findXtById(String id) {
		log.info("#xtMapper获取子系统信息");
		return xtMapper.findXtById(id);
	}

	
	@Override
	public PageInfo<ESXtList> findXtByPage(Integer pageNum, String keywords) {
		log.info("#xtMapper分页获取子系统信息");
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESXtList> xt = xtMapper.findXtByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESXtList> page = new PageInfo<ESXtList>(xt);
		return page;
	}
	@Override
	public List<ESXtList> getXtListByKeywords(String keywords) {
		// TODO Auto-generated method stub
	        List<ESXtList> list = xtMapper.findXtBykeywords(keywords);
	        log.info("# 查询默认数据库 page.toString()={}", list.toString());
	        return list;
	}
	
	@Transactional
	@Override
	public boolean addXt(ESXtList xt) {
		log.info("#xtMapper系统信息");
		return xtMapper.addXt(xt);
	}
	
	public ESXtList add_EsXt(ESXtList xt) {
		String esXTBh = null;
		 List<ESXtList> list = findXts();
		 if(list != null ) {
			 if(list.size() > 0) {
				 //获取最大编号
				 int maxNum = getMaxNum(list);
				 log.info("最大编号为==================="+maxNum);
				 
				//定义 编号长度
				int bhLength = 4;
				
				//生成新的编号
				esXTBh =String.format("%0" + bhLength + "d", maxNum + 1);
				log.info("编号为==================="+esXTBh);
			 }else {
				 esXTBh = String.valueOf(0001);
			 }
		 }
		 xt.setF_xtbh(esXTBh);
		 xtMapper.addXt(xt);
		 return xt;
	}
	
	private int getMaxNum(List<ESXtList> list) {
		int maxnybh = 0;
		for (int i = 0; i < list.size(); i++) {
			String sBh = list.get(i).getF_xtbh();
			int iBh = Integer.parseInt(sBh);
			if (maxnybh < iBh) {
				maxnybh = iBh;
			}
		}
		return maxnybh;
	}

	@Override
	public boolean delXt(ESXtList xt) {
		log.info("#xtMapper删除子系统信息");
		return xtMapper.delXt(xt);
	}

	@Override
	public boolean upXt(ESXtList xt) {
		log.info("#xtMapper更新子系统信息");
		return xtMapper.upXt(xt);
	}

	@Override
	public ISSPReturnObject delEsxt(ESXtList xt) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = xtMapper.delXt(xt);
		if (flag) {
			returnObject.setMsg("删除成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("删除失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject editEsxt(ESXtList xt) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> startMap = null;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(xt.getF_xtbh(), "es_xt_list");
				flag = xtMapper.upXt(xt);
				OperationLog.updateEnd(xt.getF_xtbh(), "es_xt_list", startMap);
			}else{
				flag = xtMapper.upXt(xt);
			}
			if (flag) {
				returnObject.setMsg("修改成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("修改失败");
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
}