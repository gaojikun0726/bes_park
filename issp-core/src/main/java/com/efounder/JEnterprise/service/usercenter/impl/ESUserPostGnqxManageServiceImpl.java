package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserPostGnqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserPostGnqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESPostGnqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 岗位功能权限接口实现类
 * @author gongfanfei
 */
@Service("esUserPostGnqxManageService")
public class ESUserPostGnqxManageServiceImpl implements ESPostGnqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostGnqxManageServiceImpl.class);

	@Autowired
	private ESUserPostGnqxManageMapper userPostgnqxMapper;

	@Override
	public List<ESUserPostGnqxManage> findUserPostGnqx() {
		log.info("#userPostgnqxMapper获取全部岗位功能权限信息");
		return userPostgnqxMapper.findUserPostGnqx();
	}
	@Override
	public List<ESUserPostGnqxManage> findUserPostGnqxByThreeId(String f_gwguid, String f_qxbh, String f_sjbh) {
		log.info("#userPostgnqxMapper获取全部岗位功能权限信息");
		return userPostgnqxMapper.findUserPostGnqxByThreeId(f_gwguid,f_qxbh,f_sjbh);
	}

	@Override
	public List<ESUserPostGnqxManage> findUserPostGnqxByGwbhAndQxbh(String f_gwguid, String f_qxbh) {
		log.info("#userPostgnqxMapper获取对应ID岗位功能权限信息");
		return userPostgnqxMapper.findUserPostGnqxByGwbhAndQxbh(f_gwguid,f_qxbh);
	}

	
	@Override
    public List<ESUserPostGnqxManage> findUserPostGnqxByKey(String keywords) {
		log.info("#userPostgnqxMapper获取模糊搜索岗位功能权限信息");
        return userPostgnqxMapper.findUserPostGnqxByKey(keywords);
    }

	@Override
	public boolean addUserPostGnqx(ESUserPostGnqxManage userPostgnqx) {
		log.info("#userPostgnqxMapper添加岗位功能权限信息");
		return userPostgnqxMapper.addUserPostGnqx(userPostgnqx);
	}

/*	@Override
	public boolean delUserPostGnqx(String f_roleguid,String f_qxbh,String guid) {
		log.info("#userPostgnqxMapper删除岗位功能权限信息");
		return userPostgnqxMapper.delUserPostGnqx(f_roleguid,f_qxbh,guid);
	}*/

	@Override
	public boolean upUserPostGnqx(ESUserPostGnqxManage userPostgnqx) {
		log.info("#userPostgnqxMapper更新岗位功能权限信息");
		return userPostgnqxMapper.upUserPostGnqx(userPostgnqx);
	}
	@Override
	public List<ESUserPostGnqxManage> findUserPostGnqxByYhbhAndQxbh(String f_gwguid, String classifygnqxTb) {
		// TODO Auto-generated method stub
		return userPostgnqxMapper.findUserPostGnqxByYhbhAndQxbh(f_gwguid,classifygnqxTb);
	}
	@Override
	public boolean delUserPostGnqx(List<ESUserPostGnqxManage> deleteGgnqxList, String tablename) {
		// TODO Auto-generated method stub
		log.info("#userRolegnqxMapper删除用户角色功能权限信息");
		String f_gwguid = null;
		if(deleteGgnqxList.size()>0){
			f_gwguid = deleteGgnqxList.get(0).getF_gwguid();	
		}
		return userPostgnqxMapper.delUserPostGnqx(deleteGgnqxList,tablename,f_gwguid);
	}
	@Override
	public boolean addUserPostGnqx(List<ESUserPostGnqxManage> gnqxList, String tablename) {
		// TODO Auto-generated method stub
		return userPostgnqxMapper.addUserPostGnqx(gnqxList,tablename);
	}
	@Override
	public boolean upUserPostGnqx(List<ESUserPostGnqxManage> gnqxList, String tablename, String f_gwguid) {
		// TODO Auto-generated method stub
		log.info("#userPostgnqxMapper更新用户组功能权限信息");
		return userPostgnqxMapper.upUserPostGnqx(gnqxList,tablename,f_gwguid);
	}
}