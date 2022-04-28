package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.UserRoleRlglMapper;
import com.efounder.JEnterprise.model.usercenter.UserRoleRlgl;
import com.efounder.JEnterprise.service.usercenter.UserRoleRlglService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 
 * 类名称：userrolerlgl
 * 类描述：角色和用户关系接口实现类
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("UserRoleRlglService")
public class UserRoleRlglServiceImpl implements UserRoleRlglService {
	private static final Logger log = LoggerFactory.getLogger(UserRoleRlglServiceImpl.class);
	 @Autowired
	    private UserRoleRlglMapper userRoleRlglMapper;
	 
	/**
	  * @Description 查看用户组关系
	  * @author gongfanfei
	  * @param f_zbh
	  * @return  
	 */
	@Override
	public List<UserRoleRlgl> findChildRlglByRolebh(String f_roleguid) {
		List<UserRoleRlgl> userrolerlglList = userRoleRlglMapper.findChildByRolebh(f_roleguid);
		log.info("#userrolerlglMapper获取用户组关系信息",userrolerlglList.toString());
		return userrolerlglList;
	}
	/** 
      * @Description ajax保存用户组关系信息
      * @author gongfanfei
      * @param usergroup
      * @return  
      */
	@Transactional
    @Override
    public boolean addUserRoleRlgl(UserRoleRlgl userrolerlgl) {
        if (userrolerlgl != null) {
            int flag = userRoleRlglMapper.insert(userrolerlgl);
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
	public boolean delUserRoleRlgl(UserRoleRlgl userrolerlgl) {
		log.info("#userrolerlglMapper删除组织机构包含用户信息");
	    return userRoleRlglMapper.deleteByYhbh(userrolerlgl.getF_yhbh());
	}
	/**
	 * @Description 删除用户组所有用户信息
	 * @author gongfanfei
	 * @param json
	 * @return
	 */
	@Override
	public boolean leftMoveAllUsers(UserRoleRlgl userrolerlgl) {
		log.info("#userrolerlglMapper删除组织机构包含用户信息");
		return userRoleRlglMapper.leftMoveByRolebh(userrolerlgl.getF_roleguid());
	}
	@Override
	public UserRoleRlgl findRoleRlglByZbh(String f_roleguid) {
		// TODO Auto-generated method stub
		return userRoleRlglMapper.findById(f_roleguid);
	}
	
}
