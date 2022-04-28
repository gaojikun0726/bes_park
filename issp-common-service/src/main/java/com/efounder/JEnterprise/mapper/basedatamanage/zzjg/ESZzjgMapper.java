package com.efounder.JEnterprise.mapper.basedatamanage.zzjg;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 组织机构mapper接口
 * @author gongfanfei
 */
@Mapper
public interface ESZzjgMapper  extends BaseMapper<String, ESZzjg>{

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */

	List<ESZzjg> findZzjgByPage(@Param("keywords") String keywords);
	/**
	 * 获取组织机构分公司
	 * @return
	 */
	List<ESZzjg> getBranchOffice();
	/**
	 * 子节点分页查询
	 * @param f_zzjgbh
	 * @return
	 */

	List<ESZzjg> findSonZzjgByPage(@Param("f_zzjgbh") String f_zzjgbh);

	/**
	 * 根据组织机构编号模糊查询本级和子级id
	 * @param f_zzjgbh
	 * @return
	 */
	List<ESZzjg> findZzjgListByLikeBh(@Param("f_zzjgbh") String f_zzjgbh);
	/**
	 * 数据权限——根据组织机构编号模糊查询本级和子级id
	 * @param f_zzjgbh
	 * @return
	 */
	List<ESZzjg> findZzjgListByLikeBhAndCheckDataLimit(@Param("f_zzjgbh") String f_zzjgbh,@Param("checkDataLimit") String checkDataLimit);
	/**
	 * 加载组织机构树
	 * @return
	 */
	List<ESZzjg> loadZzjgTreeJsTwo();
	/**
	 * 加载组织机构树
	 * @return
	 */
	List<IBaseTree> loadAll(@Param("checkDataLimit") String checkDataLimit);

	List<IBaseTree> loadAllZC(@Param("checkDataLimit") String checkDataLimit);

	List<ESZzjg> findChildByBh(@Param("f_zzjgbh") String f_zzjgbh);

	boolean updateMxByBh(@Param("f_zzjgbh") String f_zzjgbh,@Param("f_mx") String f_mx);

	boolean deleteByBH(@Param("f_id") String f_id);

	/**
	 * 通过组织机构编号查询组织机构
	 * @param f_zzjgbh
	 * @return
	 */
	ESZzjg getZzjgIdBybh(@Param("f_zzjgbh") String f_zzjgbh);

	List<ESZzjg> getId(@Param("f_zzjgbh") String f_zzjgbh);

	List<ESZzjg> getbh(@Param("id") String id);//根据id得到编号

	/**
	 * 查询所有的组织机构
	 * @return
	 */
	List<ESZzjg> findAll();

	Integer roleGroupIbfk(String groupId);

}
