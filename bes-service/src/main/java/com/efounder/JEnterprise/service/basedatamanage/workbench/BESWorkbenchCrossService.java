package com.efounder.JEnterprise.service.basedatamanage.workbench;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.BesWorkbench.BESWorkbenchCrossType;

/**
 * Workbench接口
 * @author liwenjie
 */

public interface BESWorkbenchCrossService {
	/**
	 * 右侧表格
	 */
	public ISSPReturnObject getWorkbenchTable(String f_gztzzjg_id);
	
	/**
	 * 添加信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject add_WorkbenchCross (BESWorkbenchCrossType besworkbenchcrosstype);
	
	/**
	 * 删除信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject del_WorkbenchCross(String id);
	
	/**
	 * 编辑信息
	 * @param 
	 * @return
	 */	
	public ISSPReturnObject edit_WorkbenchCross(BESWorkbenchCrossType besworkbenchcrosstype);

	/**
	 * 回显查询
	 * */
	public ISSPReturnObject getWorkbench(String bh);

}
