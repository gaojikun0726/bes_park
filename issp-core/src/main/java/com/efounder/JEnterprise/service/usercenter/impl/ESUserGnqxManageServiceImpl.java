package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserGnqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGnqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserGnqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户功能权限接口实现类
 * @author gongfanfei
 */
@Service("esUserGnqxManageService")
public class ESUserGnqxManageServiceImpl implements ESUserGnqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserGnqxManageServiceImpl.class);

	@Autowired
	private ESUserGnqxManageMapper usergnqxMapper;

	@Override
	public List<ESUserGnqxManage> findUserGnqx() {
		log.info("#usergnqxMapper获取全部用户功能权限信息");
		return usergnqxMapper.findUserGnqx();
	}
	@Override
	public List<ESUserGnqxManage> findUserGnqxByYhbh(String f_yhbh,String usergnqxTb) {
		log.info("#usergnqxMapper根据用户编号和权限编号获取对应的用户功能权限信息");
		return usergnqxMapper.findUserGnqxByYhbh(f_yhbh,usergnqxTb);
	}
	@Override
	public boolean addUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename) {
		log.info("#usergnqxMapper添加用户功能权限信息");
		return usergnqxMapper.addUserGnqx(gnqxList,tablename);
	}

	@Override
	public boolean delUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename) {
		log.info("#usergnqxMapper删除用户功能权限信息");
		String f_yhbh = null;
		if(gnqxList.size()>0){
			f_yhbh = gnqxList.get(0).getF_yhbh();
		}
		return usergnqxMapper.delUserGnqx(gnqxList,tablename,f_yhbh);
	}

	@Override
	public boolean upUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename,String f_yhbh) {
		log.info("#usergnqxMapper更新用户功能权限信息");
		return usergnqxMapper.upUserGnqx(gnqxList,tablename,f_yhbh);
	}
}