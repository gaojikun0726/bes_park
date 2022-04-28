package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户岗位ESPostMapper
 * @author gongfanfei
 * @datetiem 2018年1月16日
 */
@Mapper
public interface ESPostMapper extends BaseMapper<String, ESPost>  {

	/**
	 * 获取所有岗位信息
	 * @return
	 */
	List<ESPost> findPosts();

	/**
	 * 根据岗位ID获取岗位信息
	 * @param id
	 * @return
	 */
	ESPost findPostById(String id);

	/**
	 * 根据组织机构编码获取岗位信息
	 * @param id
	 * @return
	 */
	List<ESPost> findPostByZzjgId(@Param("zzjgbh") String zzjgbh);

	/**
	 * 根据岗位ID获取该岗位下的所有用户
	 * @param id
	 * @return
	 */
	List<ESUser> findUserByPostId(@Param("id") String id);

	/**
     * 根据用户查询对应所有岗位
     * @param userId
     * @return roles 所有岗位
     */
    public List<ESPost> findPostByUserId(String userId);

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESPost> findUserPostByPage(@Param("keywords") String keywords);
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	List<ESPost> findUserPost(@Param("keywords") String keywords);

	/**
	 * 增加岗位
	 * @param post
	 * @return
	 */
	boolean addPost(ESPost post);

	/**
	 * 删除岗位
	 * @param post
	 * @return
	 */
	boolean delPost(ESPost post);

	/**
	 * 更新岗位
	 * @param post
	 * @return
	 */
	boolean upPost(ESPost post);

	/**
	 * 根据组织机构编号查询岗位列表
	 * @param zzjgbh
	 * @return
	 */
	List<ESPost> findPostByZzjgbh(@Param("zzjgbh") String zzjgbh);


	/**
	 * 根据岗位编号获取岗位信息
	 * @param f_gwguid
	 * @return
	 */
	ESPost findPostByPostBh(@Param("f_gwguid") String f_gwguid);


	/**
	 * 根据组织机构bh查询岗位信息
	 * @param map
	 *
	 * @return
	 */
	List<ESPost> findPostByZzjgbhArray(Map<String,Object> map);


}
