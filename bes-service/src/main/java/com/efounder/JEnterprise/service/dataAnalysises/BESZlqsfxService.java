package com.efounder.JEnterprise.service.dataAnalysises;
/**
* @author  杨超
* @version 创建时间：2018年10月17日 上午11:03:10
* @parameter 
* @version 1.0
*/

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;

public interface BESZlqsfxService {
	
	//查询支路tab-list
	ISSPReturnObject getZl_tablist();
	
	//加载支路树
	public ISSPReturnObject getTree(String fZzjgbh,String fnybh);

    public ISSPReturnObject getTreeDep(String fZzjgbh,String fnybh);
	
	//拼装table根据查询条件
    ISSPReturnObject pin_table(BesQstjfxData dto);

    // 重写拼装table
    ISSPReturnObject pinTable(BesQstjfxData dto);

    // 单位
    ISSPReturnObject pinTabledw(BesQstjfxData dto);

}
