package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESGnqx;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 功能权限维护接口
 * @author gongfanfei
 */
public interface ESGnqxService {

	/**
	 * 查询所有功能权限信息
	 *
	 * @return
	 */
	public List<ESGnqx> findGnqxs();

	/**
	 * 根据权限编号查询功能权限信息
	 *
	 * @return
	 */
	public ESGnqx findGnqxById(String id);
	/**
	 * 根据系统编号查询功能权限信息
	 *
	 * @return
	 */
	public List<ESGnqx> findGnqxByXtbh(String id);
	
	/**
	 * 搜索
	 */
	 public List<ESGnqx> getGnqxListByKeywords(String f_xtbh,String keywords);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESGnqx> findGnqxByPage(Integer pageNum, String keywords);

	/**
	 * 添加功能权限
	 * 
	 * @return
	 */
	public boolean addGnqx(ESGnqx gnqx);

	/**
	 * 删除功能权限信息
	 * 
	 * @param gnqx
	 * @return
	 */
	public boolean delGnqx(ESGnqx gnqx);

	/**
	 * 更新功能权限信息
	 * 
	 * @param gnqx
	 * @return
	 */
	public boolean upGnqx(ESGnqx gnqx);

}
