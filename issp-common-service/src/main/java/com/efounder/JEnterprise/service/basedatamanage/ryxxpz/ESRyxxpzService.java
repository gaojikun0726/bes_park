package com.efounder.JEnterprise.service.basedatamanage.ryxxpz;


import com.efounder.JEnterprise.model.basedatamanage.ryxxpz.ESRyxxpz;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description  用户定义接口
 * @author guzhirui
 * @date 2018年1月17日下午2:50:48
 *
 */

/**
 * @Description  个人中心接口 因所用数据表相同，故再次添加接口
 * @author liuhoujun
 * @date 2018/11/26
 *
 */
public interface ESRyxxpzService {
	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESRyxxpz> findUserByPage(Integer pageNum, String keywords);

	/**
	 * 查询所有的用户
	 * @return
	 */
	public  List<ESRyxxpz> findUsers();

	public  List<ESRyxxpz> findUsersBykey(String keywords);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public ESRyxxpz findUserById(String id);
	/**
	 * 获取头像的url
	 */
	public ESRyxxpz findUserByBH(String id);
	/**
	 * 根据组织机构id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESRyxxpz> findUserBykeywords(String euserkeywords);

	/**
	 * 根据组织机构id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESRyxxpz> findRyxxByDwxxId(String zzjgbh);
	/**
	 * 根据用户组关系id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESRyxxpz> findUserByrlglId(String yhbh);

	/**
     * 新增用户(用户页面)
     *
     * @param user
     *            用户
     * @param organize
     *            组织
     * @param role
     *            角色
     */
    public boolean addUser(ESRyxxpz user);

	/**
	 * 删除用户(用户页面)
	 * @param user
	 * @return
	 */
	public boolean delUser(ESRyxxpz user);

	/**
	 * 更新修改用户(用户页面)
	 * @param user
	 * @return
	 */
	public boolean upUser(ESRyxxpz user);

	/**
	 * 重置密码(用户页面)
	 * @param user
	 * @return
	 */
	public boolean upUserPass(ESRyxxpz user);
	/**
	 * 修改密码(用户页面)
	 * @param user
	 * @return
	 */
	public boolean exUserPass(ESRyxxpz user);


	public List<ESRyxxpz> findUserByusername(String username);

	/**
	 * 修改用户信息(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterUserInfo(ESRyxxpz user);

	/**
	 * 修改用户头像(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterUserhead(ESRyxxpz user);

	/**
	 * 修改密码(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterPassword(ESRyxxpz user);

	/**
	 * 拦截IP
	 */
	public boolean interceptIP(String ip);

	/**
	 * 根据用户名拦截
	 */
	public String username(String fip);


}
