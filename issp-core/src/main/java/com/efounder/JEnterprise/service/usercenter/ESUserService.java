package com.efounder.JEnterprise.service.usercenter;


import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
public interface ESUserService  {
	/**
	 * 分页查询
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESUser> findUserByPage(Integer pageNum, String keywords);

	/**
	 * 查询所有的用户
	 * @return
	 */
	public  List<ESUser> findUsers();

	public  List<ESUser> findUsersBykey(String keywords);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public ESUser findUserById(String id);
	/**
	 * 获取头像的url
	 */
	public ESUser findUserByBH(String id);
	/**
	 * 根据组织机构id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESUser> findUserBykeywords(String euserkeywords);

	/**
	 * 根据组织机构id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESUser> findUserByzzjgId(String zzjgbh);

	/**
	 * 根据组织机构查询用户信息
	 * @param map 组织机构编号列表
	 * @return
	 */
	List<ESUser> getUserByZzjg(Map<String,Object> map);
	/**
	 * 根据用户组关系id查询用户列表
	 * @param id
	 * @return
	 */
	public List<ESUser> findUserByrlglId(String yhbh);

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
    public boolean addUser(ESUser user);

	/**
	 * 删除用户(用户页面)
	 * @param user
	 * @return
	 */
	public boolean delUser(ESUser user);

	/**
	 * 删除用户及其关联的权限
	 * @param user
	 * @return
	 */
	boolean deleteUser(ESUser user);

	/**
	 * 更新修改用户(用户页面)
	 * @param user
	 * @return
	 */
	public boolean upUser(ESUser user);

	/**
	 * 重置密码(用户页面)
	 * @param user
	 * @return
	 */
	public boolean upUserPass(ESUser user);
	/**
	 * 修改密码(用户页面)
	 * @param user
	 * @return
	 */
	public boolean exUserPass(ESUser user);


	public List<ESUser> findUserByusername(String username);

	/**
	 * 修改用户信息(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterUserInfo(ESUser user);

	/**
	 * 修改用户头像(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterUserhead(ESUser user);

	/**
	 * 修改密码(个人中心)
	 * @param user
	 * @return
	 */
	public boolean updatePersonalCenterPassword(ESUser user);

	/**
	 * 拦截IP
	 */
	public boolean interceptIP(String ip);

	/**
	 * 根据用户名拦截
	 */
	public String username(String fip);


}
