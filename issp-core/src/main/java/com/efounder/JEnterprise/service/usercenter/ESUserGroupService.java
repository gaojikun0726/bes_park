package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * 
 * 类名称：UserGroup
 * 类描述：用户组接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei 
 * 修改人：liuhoujun
 * 修改时间2018/12/19
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface ESUserGroupService {
	/**
	  * 新增用户组
	  * @param usergroup
	  * @return
	  */
	 public boolean addUserGroup(ESUserGroup usergroup);
	 
	 public boolean delUserGroup(ESUserGroup usergroup);
	 
	 public ESUserGroup findById(String id);
	 
	 public boolean editUserGroup(ESUserGroup usergroup);
	 
	 public PageInfo<ESUserGroup> findUserGroupByPage(Integer pageNum, String f_zmc);
	 
	 public List<ESUserGroup> getUserGroupList(String f_zmc);
	 
	 /**
	  * 查找子类
	  * @param f_zbh
	  * @return
	  */
	public List<ESUserGroup> findChildByZbh(String f_zbh);
		
	/**
	 * 加载用户组树
	 * @return
	*/
	List<ESUserGroup> loadAll();

	public List<ESUserGroup> findChildByZbh_all(String f_zbh);

	public ESUserGroup findGroupByGroupBh(String f_zbh);
	 
}
