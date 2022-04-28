package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.UserPostRlgl;

import java.util.List;
/**
 * 
 * 类名称：UserPostRlgl
 * 类描述：岗位-用户关系接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface UserPostRlglService {
	/**
	  * 新增岗位
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean addUserPostRlgl(UserPostRlgl userpostrlgl);
	 /**
	  * 删除岗位
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean delUserPostRlgl(UserPostRlgl userpostrlgl);
	 /**
	  * 删除岗位对应所有用户
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean leftMoveAllUsers(UserPostRlgl userpostrlgl);
	 /**
	  * 根据岗位编号查看岗位关系列表
	  * @param f_roleguiid
	  * @return
	  */
	 public List<UserPostRlgl> findChildRlglByPostbh(String f_gwguid);
	 /**
	  * 根据岗位编号查看岗位成员对象
	  * @param f_roleguiid
	  * @return
	  */
	 public UserPostRlgl findPostRlglByZbh(String f_gwguid);
}
