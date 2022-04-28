package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.UserPostRlglMapper;
import com.efounder.JEnterprise.model.usercenter.UserPostRlgl;
import com.efounder.JEnterprise.service.usercenter.UserPostRlglService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 
 * 类名称：UserPostRlglServiceImpl
 * 类描述：岗位和用户关系接口实现类
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("UserPostRlglService")
public class UserPostRlglServiceImpl implements UserPostRlglService {
	private static final Logger log = LoggerFactory.getLogger(UserPostRlglServiceImpl.class);
	 @Autowired
	    private UserPostRlglMapper userPostRlglMapper;
	 
	/**
	  * @Description 查看用户组关系
	  * @author gongfanfei
	  * @param f_zbh
	  * @return  
	 */
	@Override
	public List<UserPostRlgl> findChildRlglByPostbh(String f_gwguid) {
		List<UserPostRlgl> userpostrlglList = userPostRlglMapper.findChildByPostbh(f_gwguid);
		log.info("#userGroupRlglMapper获取用户组关系信息",userpostrlglList.toString());
		return userpostrlglList;
	}
	/** 
      * @Description ajax保存用户组关系信息
      * @author gongfanfei
      * @param usergroup
      * @return  
      */
	@Transactional
    @Override
    public boolean addUserPostRlgl(UserPostRlgl userpostrlgl) {
        if (userpostrlgl != null) {
            int flag = userPostRlglMapper.insert(userpostrlgl);
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
	public boolean delUserPostRlgl(UserPostRlgl userpostrlgl) {
		log.info("#userGroupRlglMapper删除组织机构包含用户信息");
	    return userPostRlglMapper.deleteByYhbh(userpostrlgl.getF_yhbh());
	}
	/**
	 * @Description 删除用户组所有用户信息
	 * @author gongfanfei
	 * @param json
	 * @return
	 */
	@Override
	public boolean leftMoveAllUsers(UserPostRlgl userpostrlgl) {
		log.info("#userGroupRlglMapper删除组织机构包含用户信息");
		return userPostRlglMapper.leftMoveByPostbh(userpostrlgl.getF_gwguid());
	}
	@Override
	public UserPostRlgl findPostRlglByZbh(String f_gwguid) {
		// TODO Auto-generated method stub
		return userPostRlglMapper.findById(f_gwguid);
	}
	
}
