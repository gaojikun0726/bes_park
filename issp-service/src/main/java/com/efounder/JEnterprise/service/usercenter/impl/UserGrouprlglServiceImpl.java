package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.UserGrouprlglMapper;
import com.efounder.JEnterprise.model.usercenter.UserGrouprlgl;
import com.efounder.JEnterprise.service.usercenter.UserGrouprlglService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 
 * 类名称：UserGroupRlgl
 * 类描述：用户组和用户关系接口实现类
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("UserGrouprlglService")
public class UserGrouprlglServiceImpl implements UserGrouprlglService {
	private static final Logger log = LoggerFactory.getLogger(UserGrouprlglServiceImpl.class);
	 @Autowired
	    private UserGrouprlglMapper userGroupRlglMapper;
	 
	/**
	  * @Description 查看用户组关系
	  * @author gongfanfei
	  * @param f_zbh
	  * @return  
	 */
	@Override
	public List<UserGrouprlgl> findChildRlglByZbh(String f_zbh) {
		List<UserGrouprlgl> usergrouprlglList = userGroupRlglMapper.findChildByZbh(f_zbh);
		log.info("#userGroupRlglMapper获取用户组关系信息",usergrouprlglList.toString());
		return usergrouprlglList;
	}
	/** 
      * @Description ajax保存用户组关系信息
      * @author gongfanfei
      * @param usergroup
      * @return  
      */
	@Transactional
    @Override
    public boolean addUserGroupRlgl(UserGrouprlgl usergrouprlgl) {
        if (usergrouprlgl != null) {
            int flag = userGroupRlglMapper.insert(usergrouprlgl);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }
	 /**
	   * @Description 删除单一用户和用户组关系
	   * @author gongfanfei
	   * @param json
	   * @return
	   */
	@Override
	public boolean delUserGroupRlgl(UserGrouprlgl usergrouprlgl) {
		log.info("#userGroupRlglMapper删除组织机构包含用户信息");
	    return userGroupRlglMapper.deleteByYhbh(usergrouprlgl.getF_yhbh());
	}
	/**
	 * @Description 删除用户组所有用户信息
	 * @author gongfanfei
	 * @param json
	 * @return
	 */
	@Override
	public boolean leftMoveAllUsers(UserGrouprlgl usergrouprlgl) {
		log.info("#userGroupRlglMapper删除组织机构包含用户信息");
		return userGroupRlglMapper.leftMoveByZbh(usergrouprlgl.getF_zbh());
	}
	@Override
	public UserGrouprlgl findGroupRlglByZbh(String f_zbh) {
		// TODO Auto-generated method stub
		return userGroupRlglMapper.findById(f_zbh);
	}
	
}
