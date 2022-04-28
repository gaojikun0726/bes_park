package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.usercenter.ESUserGroupMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 
 * 类名称：UserGroup
 * 类描述：用户组接口实现类
 * 创建人：yanyonghui
 * 修改人：liuhoujun
 * 修改时间：2018年12月19日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("ESuserGroupService")
public class ESUserGroupServiceImpl implements ESUserGroupService {
	private static final Logger log = LoggerFactory.getLogger(ESUserGroupServiceImpl.class);
	 @Autowired
	    private ESUserGroupMapper userGroupMapper;
	 /** 
	    * @Description 分页查询
	    * @author yanyonghui
	    * @param pageNum
	    * @return  
	    */
	@Override
	public PageInfo<ESUserGroup> findUserGroupByPage(Integer pageNum, String f_zmc) {
		// TODO Auto-generated method stub
		 if (pageNum == null)
	            pageNum = 1;
	        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
	        // 紧跟着的第一个select方法会被分页
	        List<ESUserGroup> userGroup = userGroupMapper.findUserGroupByPage(f_zmc);
	        // 用PageInfo对结果进行包装
	        PageInfo<ESUserGroup> page = new PageInfo<ESUserGroup>(userGroup);
	        // 测试PageInfo全部属性
	        // PageInfo包含了非常全面的分页属性
	        log.info("# 查询默认数据库 page.toString()={}", page.toString());
	        return page;
	}
	/**
	  * @Description 查询子类
	  * @author gongfanfei
	  * @param f_zbh
	  * @return  
	 */
	@Override
	public List<ESUserGroup> findChildByZbh(String f_zbh) {
		List<ESUserGroup> usergroupList = userGroupMapper.findChildByZbh(f_zbh);
		log.info("#ESZzjgMapper获取子节点",usergroupList.toString());
		return usergroupList;
	}
	/**
	 * 用户组树
	 */
	@Override
	public List<ESUserGroup> loadAll(){
		log.info("#userGroupMapper加载组织机构树");
		 List<ESUserGroup> list = userGroupMapper.loadAll();
		return list;
		
	}
	/** 
      * @Description ajax保存用户组信息
      * @author yanyonghui
      * @param usergroup
      * @return  
      */
	@Transactional
    @Override
    public boolean addUserGroup(ESUserGroup usergroup) {
        if (usergroup != null) {
            int flag = userGroupMapper.insert(usergroup);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }
	 /**
	   * @Description 删除用户组信息
	   * @author yanyonghui
	   * @param json
	   * @return
	   */
	@Override
	public boolean delUserGroup(ESUserGroup usergroup) {
		 if (usergroup != null) {
			 int flag = userGroupMapper.delete(usergroup.getF_zbh());
			 if (flag == 1)
	                return true;
	            else
	                return false;
	        } else
	            return false;
	}
	/** 
      * @Description ajax加载编辑对象
      * @author yanyonghui
      * @param 主键
      * @return  
      */
	@Override
    public ESUserGroup findById(String id) {
        if (StringUtils.isBlank(id))
            return null;
        else
            return userGroupMapper.findById(id);
    }
	 /**
       * @Description ajax更新用户组信息
       * @author yanyonghui
       * @param usergroup
       * @return
       */
	@Override
    public boolean editUserGroup(ESUserGroup usergroup) {
        if (usergroup != null && StringUtils.isNotBlank(usergroup.getF_zbh())) {
            int flag = userGroupMapper.update(usergroup);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }
	
	/** 
	    * @Description 分页查询
	    * @author yanyonghui
	    * @param pageNum
	    * @return  
	    */
	@Override
	public List<ESUserGroup> getUserGroupList(String f_zmc) {
		// TODO Auto-generated method stub
	        List<ESUserGroup> list = userGroupMapper.findUserGroupByPage(f_zmc);
	        return list;
	}
	@Override
	public List<ESUserGroup> findChildByZbh_all(String f_zbh) {
		// TODO Auto-generated method stub
		List<ESUserGroup> usergroupList = userGroupMapper.findChildByZbh_all(f_zbh);
		return usergroupList;
	}
	@Override
	public ESUserGroup findGroupByGroupBh(String f_zbh) {
		// TODO Auto-generated method stub
		return userGroupMapper.findGroupByGroupBh(f_zbh);
	}
	
}
