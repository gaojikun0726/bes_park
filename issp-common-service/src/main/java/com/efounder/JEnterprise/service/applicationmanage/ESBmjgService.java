package com.efounder.JEnterprise.service.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;

public interface ESBmjgService extends ESBaseService {
	
	public ESBmjg findBmjg(String tabName);

	/**
	 * 分页搜索
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	PageInfo<ESBmjg> selBmjgpage(Integer bars,Integer pageNum, String keywords);

	/**
	 * 增加
	 * 
	 * @param esbmjg
	 * @return
	 */
	ISSPReturnObject insBmjg(ESBmjg esbmjg);

	/**
	 * 删除
	 * 
	 * @param tableName
	 * @return
	 */
	ISSPReturnObject delBmjg(String tableName);

	/**
	 * 单行查询
	 * 
	 * @param tableName
	 * @return
	 */
	ISSPReturnObject selectBmjg(String tableName);

	/**
	 * 修改
	 * 
	 * @param esbmjg
	 * @return
	 */
	ISSPReturnObject updBmjg(ESBmjg esbmjg);
}
