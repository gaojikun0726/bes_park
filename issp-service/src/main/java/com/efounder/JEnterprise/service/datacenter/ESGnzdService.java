package com.efounder.JEnterprise.service.datacenter;

import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import com.efounder.JEnterprise.service.ESBaseService;

import java.util.List;
/**
 * @Description 功能字典接口
 * @author gongfanfei
 */
public interface ESGnzdService extends ESBaseService{
	/**
	 * 根据模块guid加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdListByMkuuid(String mkbh);
	/**
	 * 根据模块guid和表名加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndSh(String tablename,String mkbh,String fYhbh);
	/**
	 * 根据模块guid和表名加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndRole(String mkbh,String tablename,String fRolebh);
	/**
	 * 根据模块编号和父节点为空作为条件查询功能字典列表
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> getGnzdByMkbhAndNull(String mkbh);
	/**
	 * 根据gnbh加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdListByGnbh(String gnbh);
	/**
	 * 根据pgnbh加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdListByPgnbh(String gnbh);
	/**
	 * 加载所有数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> loadGnzdList();
	/**
	 * 根据功能名称过滤加载数据
	 * @param gnmc
	 * @return
	 */
	public List<ESGnzd> getGnzdList(String gnmc);
	/**
	 * 新增一条菜单数据
	 * @param gnzd
	 * @return
	 */
	public boolean addGnzd(ESGnzd gnzd);
	/**
	 * 修改一条菜单数据（全部）
	 * @param gnzd
	 * @return
	 */
	public boolean editGnzdByGnbh(ESGnzd gnzd);
	/**
	 * 修改一条菜单数据(url、gnmc)
	 * @param gnzd
	 * @return
	 */
	public boolean editGnzd(ESGnzd gnzd);
	/**
	 * 删除一条功能菜单
	 * @param gnbh
	 * @return
	 */
	public boolean deleteGnzd(String gnbh, String mkbh);
	/**
	 * 根据编号获取所有子节点
	 * @param gnbh
	 * @return
	 */
	public List<ESGnzd> findChildrenByBH(String pgnbh, String mkbh);
	/**
	 * 根据功能编号更新明细
	 * @param gnbh
	 * @return
	 */
	public boolean updateMxByBh(String gnbh, String mkbh, String mx);
	
	
	public List<ESGnzd> getGnzdListBygnzdlist(List<String> gnzds);
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndGroup(String guid, String classifygnqxTb, String f_zbh);
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndPost(String guid, String classifygnqxTb, String f_gwguid);
	
}
