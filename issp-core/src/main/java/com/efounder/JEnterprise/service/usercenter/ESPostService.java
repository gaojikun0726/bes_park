package com.efounder.JEnterprise.service.usercenter;

import com.alibaba.fastjson.JSONArray;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 用户岗位维护接口
 * @author gongfanfei
 *
 */
public interface ESPostService {

	/**
	 * 查询组织下所有岗位信息
	 *
	 * @return
	 */
	public List<ESPost> findPosts();

	/**
	 * 根据ID查询岗位信息
	 *
	 * @return
	 */
	public ESPost findPostById(String id);
	/**
	 * 根据组织机构ID查询岗位信息
	 *
	 * @return
	 */
	public List<ESPost> findPostByZzjgId(String zzjgbh);
	/**
	 * 搜索
	 */
	 public List<ESPost> getUserPostList(String keywords);
	/**
	 * 通过岗位id加载用户信息
	 *
	 * @return
	 */
	public List<ESUser> findUserByPostId(String id);

	/**
     * 根据用户查询对应所有岗位
     *
     * @param userId
     *            用户Id
     * @return roles 所有岗位
     */
    public List<ESPost> findRoleByUserId(String userId);

	/**
	 * 分页查询
	 *
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESPost> findUserPostByPage(Integer pageNum, String keywords);

	/**
	 * 添加岗位
	 *
	 * @return
	 */
	public boolean addPost(ESPost post);

	/**
	 * 删除岗位信息
	 *
	 * @param role
	 * @return
	 */
	public boolean delPost(ESPost post);

	/**
	 * 更新岗位信息
	 *
	 * @param role
	 * @return
	 */
	public boolean upPost(ESPost post);
	/**
	 * 根据组织机构bh查询岗位信息
	 *
	 * @return
	 */
	public List<ESPost> findPostByZzjgbh(String zzjgbh);

	public ESPost findPostByPostBh(String f_gwguid);


	/**
	 * 根据组织机构bh查询岗位信息
	 * @param zzjgbh
	 *
	 * @return
	 */
	List<ESPost> findPostByZzjgbhArray(JSONArray zzjgbh);

}
