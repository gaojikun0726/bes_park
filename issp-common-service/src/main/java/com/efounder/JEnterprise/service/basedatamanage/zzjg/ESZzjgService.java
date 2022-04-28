package com.efounder.JEnterprise.service.basedatamanage.zzjg;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 组织机构接口
 * @author gongfanfei
 */
public interface ESZzjgService extends ESBaseService{

	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESZzjg> findZzjgByPage(Integer pageNum,String keywords);
	/**
	 * 子节点分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESZzjg> findSonZzjgByPage(Integer pageNum,String f_zzjgbh);

	public List<ESZzjg> getZzjgList(String zzjg_in);

	/**
	 * 获取组织机构分公司
	 * @return
	 */
	public List<ESZzjg> getBranchOffice();

	/**
	 * 增加组织机构
	 * @param zzjg
	 * @return
	 */
	public boolean addZzjg(ESZzjg zzjg);

	/**
	 * ajax加载编辑对象
	 * @param id
	 * @return
	 */
	public ESZzjg findById(String f_id);

	/**
	 * 根据 数据权限以及组织机构编号 获取数据
	 * @param f_zzjgbh
	 * @param checkDataLimit
	 * @return
	 */
	public List<ESZzjg> findZzjgListByLikeBhAndCheckDataLimit(String f_zzjgbh,String checkDataLimit);

	/**
	 * 根据zzjgbh查询对象    yujieying
	 * @param f_zzjgbh
	 * @return
	 */
	public ESZzjg findBybh(String f_zzjgbh);

	/**
	 * 编辑组织机构信息
	 * @param zzjg
	 * @return
	 */
	public boolean editZzjg(ESZzjg zzjg);

	/**
	 * 删除组织机构信息
	 * @param zzjg
	 * @return
	 */
	public boolean delZzjg(ESZzjg zzjg);

	/**
	 * 加载组织机构树
	 * @return
	 */
	List<IBaseTree> loadAll(String checkDataLimit);

	List<IBaseTree> loadAllZC(String checkDataLimit);

	/**
	 * 根据组织机构编号模糊查询本级和子级id
	 * @param f_zzjgbh
	 * @return
	 */
	public List<ESZzjg> findZzjgListByLikeBh(String f_zzjgbh);
	/**
	 *
	 * @param f_zzjgbh
	 * @return
	 */
	public List<ESZzjg> findChildByBh(String f_zzjgbh);
	/**
	 * 根据编号更新明细
	 * @param f_zzjgbh
	 * @return
	 */
	public boolean updateMxByBh(String f_zzjgbh,String f_mx);
	/**
	 * 根据组织机构编号查询id      tianjiangwei
	 * @param f_zzjgbh
	 * @return
	 */

	/**
	 * 根据组织机构编号查询id
	 * @param f_zzjgbh
	 * @return
	 */
	public ESZzjg getZzjgIdBybh(String f_zzjgbh);
	public List<ESZzjg> getId(String f_zzjgbh);
	public List<ESZzjg> findZzjgidByBh(String f_zzjgbh);
	public List<ESZzjg> getbh(String id);//根据id查询编号


	/**
	 * 摄像机报表统计分析
	 * 加载组织机构树
	 * @return
	 */
	List<ESZzjg> loadZzjgTreeJsTwo();

	/**
	 * 获取所有的组织机构
	 * @return
	 */
	List<ESZzjg> findAll();


	/**
	 * 查询所有子节点（递归+循环效率太低，弃用，改为前台树递归获取子节点）
	 * @param zzjgbh 组织机构
	 * @return
	 */
	@Deprecated
	List<String> getChildList(String zzjgbh);

}
