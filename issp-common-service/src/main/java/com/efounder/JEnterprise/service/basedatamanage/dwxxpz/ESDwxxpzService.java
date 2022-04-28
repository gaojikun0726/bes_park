package com.efounder.JEnterprise.service.basedatamanage.dwxxpz;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.model.basedatamanage.dwxxpz.ESDwxxpz;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 单位接口
 * @author gongfanfei
 */
public interface ESDwxxpzService extends ESBaseService{

	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESDwxxpz> findDwxxByPage(Integer pageNum,String keywords);
	/**
	 * 子节点分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESDwxxpz> findSonDwxxByPage(Integer pageNum,String f_dwxxbh);

	public List<ESDwxxpz> getDwxxList(String dwxx_in);

	/**
	 * 获取单位分公司
	 * @return
	 */
	public List<ESDwxxpz> getBranchOffice();

	/**
	 * 增加单位
	 * @param dwxx
	 * @return
	 */
	public boolean addDwxx(ESDwxxpz dwxx);

	/**
	 * ajax加载编辑对象
	 * @param id
	 * @return
	 */
	public ESDwxxpz findById(String f_id);

	/**
	 * 根据 数据权限以及单位编号 获取数据
	 * @param f_dwxxbh
	 * @param checkDataLimit
	 * @return
	 */
	public List<ESDwxxpz> findDwxxListByLikeBhAndCheckDataLimit(String f_dwxxbh,String checkDataLimit);

	/**
	 * 根据dwxxbh查询对象    yujieying
	 * @param f_dwxxbh
	 * @return
	 */
	public ESDwxxpz findBybh(String f_dwxxbh);

	/**
	 * 编辑单位信息
	 * @param dwxx
	 * @return
	 */
	public boolean editDwxx(ESDwxxpz dwxx);

	/**
	 * 删除单位信息
	 * @param dwxx
	 * @return
	 */
	public boolean delDwxx(ESDwxxpz dwxx);

	/**
	 * 加载单位树
	 * @return
	 */
	List<IBaseTree> loadAll(String checkDataLimit);

	/**
	 * 根据单位编号模糊查询本级和子级id
	 * @param f_dwxxbh
	 * @return
	 */
	public List<ESDwxxpz> findDwxxListByLikeBh(String f_dwxxbh);
	/**
	 *
	 * @param f_dwxxbh
	 * @return
	 */
	public List<ESDwxxpz> findChildByBh(String f_dwxxbh);
	/**
	 * 根据编号更新明细
	 * @param f_dwxxbh
	 * @return
	 */
	public boolean updateMxByBh(String f_dwxxbh,String f_mx);
	/**
	 * 根据单位编号查询id      tianjiangwei
	 * @param f_dwxxbh
	 * @return
	 */

	/**
	 * 根据单位编号查询id
	 * @param f_dwxxbh
	 * @return
	 */
	public ESDwxxpz getDwxxIdBybh(String f_dwxxbh);
	public List<ESDwxxpz> getId(String f_dwxxbh);
	public List<ESDwxxpz> findDwxxidByBh(String f_dwxxbh);
	public List<ESDwxxpz> getbh(String id);//根据id查询编号


	/**
	 * 摄像机报表统计分析
	 * 加载单位树
	 * @return
	 */
	List<ESDwxxpz> loadDwxxTreeJsTwo();
}
