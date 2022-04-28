package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 数据权限维护接口
 * @author gongfanfei
 */
public interface ESSjqxService {

	/**
	 * 查询组织下所有数据权限信息
	 *
	 * @return
	 */
	public List<ESSjqx> findSjqxs();

	/**
	 * 根据ID查询数据权限信息
	 *
	 * @return
	 */
	public ESSjqx findSjqxById(String id);
	/**
	 * 根据系统编号查询数据权限信息
	 *
	 * @return
	 */
	public List<ESSjqx> findSjqxByXtbh(String id);
	
	/**
	 * 搜索(关键字+系统编号)
	 */
	 public List<ESSjqx> getSjqxListByKeywords(String keywords);
	 /**
	  * 搜索（关键字）
	  */
	 public List<ESSjqx> getSjqxListByKey(String keywords);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESSjqx> findSjqxByPage(Integer pageNum, String keywords);

	/**
	 * 添加数据权限
	 * 
	 * @return
	 */
	public boolean addSjqx(ESSjqx sjqx);

	/**
	 * 删除数据权限信息
	 * 
	 * @param sjqx
	 * @return
	 */
	public boolean delSjqx(ESSjqx sjqx);

	/**
	 * 更新数据权限信息
	 * 
	 * @param sjqx
	 * @return
	 */
	public boolean upSjqx(ESSjqx sjqx);
	/**
	 * 更新数据权限信息
	 * 
	 * @param sjqx
	 * @return
	 */
	public String findMaxBmbh();
	
	/**
	 * 查询数据的权限编号是否存在
	 * @param qxbh
	 * @return
	 */
	public ESSjqx  checkQxbhExist(String qxbh);
}
