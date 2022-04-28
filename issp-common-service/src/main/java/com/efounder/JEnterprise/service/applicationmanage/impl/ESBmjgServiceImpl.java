package com.efounder.JEnterprise.service.applicationmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.applicationmanage.ESBmjgMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.service.applicationmanage.ESBmjgService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类描述：功能字典接口实现类 创建人：suhang 修改时间：2018年7月5日
 *
 */
@Service("esBmjgService")
public class ESBmjgServiceImpl implements ESBmjgService {
	private static final Logger log = LoggerFactory.getLogger(ESBmjgServiceImpl.class);

	@Autowired
	private ESBmjgMapper esBmjgMapper;
	@Autowired
	private OperationConfig operationConfig;
	
	public ESBmjg findBmjg(String tabName) {
		log.info("#ESBmjgServiceImpl通过表名获取表编码结构");
		return (ESBmjg) esBmjgMapper.findById(tabName);
	}

	/**
	 * 编码结构分页查询
	 * 
	 * @param pageNum
	 *            , keywords
	 * @returnpage
	 */
	@Override
	public PageInfo<ESBmjg> selBmjgpage(Integer bars,Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		List<ESBmjg> pageList = esBmjgMapper.selBmjgpage(keywords);
		PageInfo<ESBmjg> page = new PageInfo<ESBmjg>(pageList);
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	/**
	 * 添加编码结构信息
	 * 
	 * @param esbmjg
	 * @return returnObject
	 */
	@Override
	public ISSPReturnObject insBmjg(ESBmjg esbmjg) {
		log.info("添加编码结构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			
			boolean flag = esBmjgMapper.insBmjg(esbmjg);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(esbmjg.getTableName(), "es_xt_bmjg");
			}
			if (flag) {
				returnObject.setData(esbmjg);
				returnObject.setStatus("1");
				returnObject.setMsg("添加成功");
			} else {
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("表名已存在，请重新添加！");
		}
		return returnObject;
	}

	/**
	 * 删除编码结构信息
	 * 
	 * @param tableName
	 * @return returnObject
	 */
	@Override
	public ISSPReturnObject delBmjg(String tableName) {
		log.info("impl删除编码结构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(tableName, "es_xt_bmjg");
			}
			boolean flag = esBmjgMapper.delBmjg(tableName);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 查询单行编码结构信息
	 * 
	 * @param tableName
	 * @return returnObject
	 */
	@Override
	public ISSPReturnObject selectBmjg(String tableName) {
		log.info("impl查询单行编码结构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			ESBmjg selBmjgg = esBmjgMapper.selectBmjg(tableName);
			returnObject.setStatus("1");
			returnObject.setMsg("查询成功");
			returnObject.setData(selBmjgg);
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	/**
	 * 更新编码结构信息
	 * 
	 * @param esbmjg
	 * @return returnObject
	 */
	@Override
	public ISSPReturnObject updBmjg(ESBmjg esbmjg) {
		log.info("impl更新编码结构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> startMap = null;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.updateStart(esbmjg.getTableName(), "es_xt_bmjg");
				flag = esBmjgMapper.updBmjg(esbmjg);
				OperationLog.updateEnd(esbmjg.getTableName(), "es_xt_bmjg", startMap);
			}else{
				flag = esBmjgMapper.updBmjg(esbmjg);
			}
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("更新成功");
				returnObject.setData(esbmjg);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新失败");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnObject;
	}

}
