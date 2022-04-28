package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserGroupGnqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupGnqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupGnqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户组功能权限接口实现类
 * @author gongfanfei
 */
@Service("esUserGroupGnqxManageService")
public class ESUserGroupGnqxManageServiceImpl implements ESUserGroupGnqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserGroupGnqxManageServiceImpl.class);

	@Autowired
	private ESUserGroupGnqxManageMapper userGroupgnqxMapper;

	@Override
	public List<ESUserGroupGnqxManage> findUserGroupGnqx() {
		log.info("#userGroupgnqxMapper获取全部用户组功能权限信息");
		return userGroupgnqxMapper.findUserGroupGnqx();
	}
	@Override
	public List<ESUserGroupGnqxManage> findUserGroupGnqxByThreeId(String f_zbh,String f_qxbh ,String f_sjbh) {
		log.info("#userGroupgnqxMapper获取全部用户组功能权限信息");
		return userGroupgnqxMapper.findUserGroupGnqxByThreeId(f_zbh,f_qxbh,f_sjbh);
	}

	@Override
	public List<ESUserGroupGnqxManage> findUserGroupGnqxByYhbhAndQxbh(String f_zbh, String classifygnqxTb) {
		log.info("#userGroupgnqxMapper获取对应ID用户组功能权限信息");
		return userGroupgnqxMapper.findUserGroupGnqxByYhbhAndQxbh(f_zbh,classifygnqxTb);
	}

	
	@Override
    public List<ESUserGroupGnqxManage> findUserGroupGnqxByKey(String keywords) {
		log.info("#userGroupgnqxMapper获取模糊搜索用户组功能权限信息");
        return userGroupgnqxMapper.findUserGroupGnqxByKey(keywords);
    }

	@Override
	public boolean addUserGroupGnqx(ESUserGroupGnqxManage userGroupgnqx) {
		log.info("#userGroupgnqxMapper添加用户组功能权限信息");
		return userGroupgnqxMapper.addUserGroupGnqx(userGroupgnqx);
	}

	@Override
	public boolean delUserGroupGnqx(String f_zbh,String f_qxbh ,String guid) {
		log.info("#userGroupgnqxMapper删除用户组功能权限信息");
		return userGroupgnqxMapper.delUserGroupGnqx(f_zbh,f_qxbh,guid);
	}

	@Override
	public boolean upUserGroupGnqx(ESUserGroupGnqxManage userGroupgnqx) {
		log.info("#userGroupgnqxMapper更新用户组功能权限信息");
		return userGroupgnqxMapper.upUserGroupGnqx(userGroupgnqx);
	}
	@Override
	public boolean delUserGroupGnqx(List<ESUserGroupGnqxManage> deleteGgnqxList, String tablename) {
		// TODO Auto-generated method stub
		log.info("#userRolegnqxMapper删除用户角色功能权限信息");
		String f_zbh = null;
		if(deleteGgnqxList.size()>0){
			f_zbh = deleteGgnqxList.get(0).getF_zbh();	
		}
		return userGroupgnqxMapper.delUserGroupGnqx(deleteGgnqxList,tablename,f_zbh);
	}
	@Override
	public boolean addUserGroupGnqx(List<ESUserGroupGnqxManage> gnqxList, String tablename) {
		// TODO Auto-generated method stub
		return userGroupgnqxMapper.addUserGroupGnqx(gnqxList,tablename);
	}
	@Override
	public boolean upUserGroupGnqx(List<ESUserGroupGnqxManage> gnqxList, String tablename, String f_zbh) {
		log.info("#userGroupgnqxMapper更新用户组功能权限信息");
		return userGroupgnqxMapper.upUserGroupGnqx(gnqxList,tablename,f_zbh);
	}
}