package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEqMaintainer;
import com.github.pagehelper.PageInfo;

/**
 * 维护商字典
 * @author liujoujun
 *
 */

public interface BESEqMaintainerService {
	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<BESEqMaintainer> findByPage(Integer bars,Integer pageNum, String keywords);
	/**
	 * 主键查询
	 * @param f_id
	 * @return
	 */
	public ISSPReturnObject findBESEqMaintainerByFid(String f_id);
	
	/**
	 * 添加维护商
	 * @param vo
	 * @return
	 */
	public ISSPReturnObject addBESEqMaintainer(BESEqMaintainer vo);
	
	/**
	 * 修改维护商
	 * @param vo
	 * @return
	 */
	public ISSPReturnObject updBESEqMaintainer(BESEqMaintainer vo);
	
	/**
	 * 删除维护商
	 * @param f_id
	 * @return
	 */
	public ISSPReturnObject delBESEqMaintainer(String f_id);
	


}
