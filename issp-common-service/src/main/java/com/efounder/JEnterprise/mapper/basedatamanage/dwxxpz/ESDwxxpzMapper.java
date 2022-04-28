package com.efounder.JEnterprise.mapper.basedatamanage.dwxxpz;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.dwxxpz.ESDwxxpz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 组织机构mapper接口
 * @author gongfanfei
 */
@Mapper
public interface ESDwxxpzMapper extends BaseMapper<String, ESDwxxpz>{

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */

	List<ESDwxxpz> findDwxxByPage(@Param("keywords") String keywords);
	/**
	 * 获取组织机构分公司
	 * @return
	 */
	List<ESDwxxpz> getBranchOffice();
	/**
	 * 子节点分页查询
	 * @param f_dwxxbh
	 * @return
	 */

	List<ESDwxxpz> findSonDwxxByPage(@Param("f_dwxxbh") String f_dwxxbh);

	/**
	 * 根据组织机构编号模糊查询本级和子级id
	 * @param f_dwxxbh
	 * @return
	 */
	List<ESDwxxpz> findDwxxListByLikeBh(@Param("f_dwxxbh") String f_dwxxbh);
	/**
	 * 数据权限——根据组织机构编号模糊查询本级和子级id
	 * @param f_dwxxbh
	 * @return
	 */
	List<ESDwxxpz> findDwxxListByLikeBhAndCheckDataLimit(@Param("f_dwxxbh") String f_dwxxbh,@Param("checkDataLimit") String checkDataLimit);
	/**
	 * 加载组织机构树
	 * @return
	 */
	List<ESDwxxpz> loadDwxxTreeJsTwo();
	/**
	 * 加载组织机构树
	 * @return
	 */
	List<IBaseTree> loadAll(@Param("checkDataLimit") String checkDataLimit);

	List<ESDwxxpz> findChildByBh(@Param("f_dwxxbh") String f_dwxxbh);

	boolean updateMxByBh(@Param("f_dwxxbh") String f_dwxxbh,@Param("f_mx") String f_mx);

	boolean deleteByBH(@Param("f_id") String f_id);

	/**
	 * 通过组织机构编号查询组织机构
	 * @param f_dwxxbh
	 * @return
	 */
	ESDwxxpz getDwxxIdBybh(@Param("f_dwxxbh") String f_dwxxbh);

	List<ESDwxxpz> getId(@Param("f_dwxxbh") String f_dwxxbh);

	List<ESDwxxpz> getbh(@Param("id") String id);//根据id得到编号
}
