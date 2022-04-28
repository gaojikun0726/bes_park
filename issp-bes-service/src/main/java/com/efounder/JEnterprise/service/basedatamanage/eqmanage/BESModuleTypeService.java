package com.efounder.JEnterprise.service.basedatamanage.eqmanage;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModuleType;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * 模块型号接口
 * @author LvSihan
 *
 */
public interface BESModuleTypeService {

	/**
	 * 删除模块型号
	 * @param moduletype
	 * @return
	 */
	public ISSPReturnObject delModuleType(BESModuleType moduletype);
	
	/**
	 * 新增模块型号
	 * @param moduletype
	 * @return
	 */
	public ISSPReturnObject addModuleType(BESModuleType moduletype);
	
	/**
	 * 更新模块型号
	 * @param moduletype
	 * @return
	 */
	public ISSPReturnObject upModuleType(BESModuleType moduletype);

	/**
	 * 根据模块型号查询
	 * @param fModuleType
	 * @return
	 */
	public ISSPReturnObject selectByPrimaryKey(String fModuleType);
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<BESModuleType> findmtByPage(Integer bars, Integer pageNum, String keywords);

	public ISSPReturnObject selectList(BESModuleType moduletype);

	public List<BESModuleType> selectPointTypeCl(BESModuleType moduletype);




}
