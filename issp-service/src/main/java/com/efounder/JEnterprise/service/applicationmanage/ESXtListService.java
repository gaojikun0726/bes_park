package com.efounder.JEnterprise.service.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 子系统维护
 * 
 * @author gongfanfei
 *
 */
public interface ESXtListService {

	/**
	 * 查询当前系统信息
	 *
	 * @return
	 */
	public List<ESXtList> findCurrentXt();
	/**
	 * 查询组织下所有系统信息
	 *
	 * @return
	 */
	public List<ESXtList> findXts();

	/**
	 * 根据ID查询系统信息
	 *
	 * @return
	 */
	public ESXtList findXtById(String id);
	
	/**
	 * 搜索
	 */
	 public List<ESXtList> getXtListByKeywords(String keywords);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESXtList> findXtByPage(Integer pageNum, String keywords);

	/**
	 * 添加系统
	 * 
	 * @return
	 */
	public boolean addXt(ESXtList xt);
	
	public ESXtList add_EsXt(ESXtList xt);

	/**
	 * 删除系统信息
	 * 
	 * @param xt
	 * @return
	 */
	public boolean delXt(ESXtList xt);

	/**
	 * 更新系统信息
	 * 
	 * @param xt
	 * @return
	 */
	public boolean upXt(ESXtList xt);
	
	public ISSPReturnObject delEsxt(ESXtList xt);
	
	public ISSPReturnObject editEsxt(ESXtList xt);
}
