package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserSjqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserSjqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserSjqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 用户数据权限接口实现类
 * @author gongfanfei
 */
@Service("esUserSjqxManageService")
public class ESUserSjqxManageServiceImpl implements ESUserSjqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserSjqxManageServiceImpl.class);

	@Autowired
	private ESUserSjqxManageMapper usersjqxMapper;

	@Override
	public List<ESUserSjqxManage> findUserSjqx() {
		log.info("#usersjqxMapper获取全部用户数据权限信息");
		return usersjqxMapper.findUserSjqx();
	}
	@Override
	public List<ESUserSjqxManage> findUserSjqxByThreeId(String f_yhbh, String f_qxbh,String f_bhzd) {
		log.info("#usersjqxMapper获取全部用户数据权限信息");
		return usersjqxMapper.findUserSjqxByThreeId(f_yhbh,f_qxbh,f_bhzd);
	}

	@Override
	public ESUserSjqxManage findUserSjqxById(String f_yhbh, String f_qxbh,String f_bhzd ) {
		log.info("#usersjqxMapper获取对应ID用户数据权限信息");
		return usersjqxMapper.findUserSjqxById(f_yhbh,f_qxbh,f_bhzd);
	}

	
	@Override
    public List<ESUserSjqxManage> findUserSjqxByKey(String keywords) {
		log.info("#usersjqxMapper获取模糊搜索用户数据权限信息");
        return usersjqxMapper.findUserSjqxByKey(keywords);
    }

	@Transactional
	@Override
	public boolean addUserSjqx(List<ESUserSjqxManage> esusersjqxmanage,String usersjqxTb) {
		log.info("#usersjqxMapper添加用户数据权限信息");
		return usersjqxMapper.addUserSjqx(esusersjqxmanage,usersjqxTb);
	}


	@Override
	public boolean upUserSjqx(ESUserSjqxManage usersjqx) {
		log.info("#usersjqxMapper更新用户数据权限信息");
		return usersjqxMapper.upUserSjqx(usersjqx);
	}

	@Override
	public List<?> getcolumnsBytableName(String columns, String tableName) {
		// TODO Auto-generated method stub
		return usersjqxMapper.getcolumnsBytableName(columns, tableName);
	}
	@Override
	public List<ESUserSjqxManage> findUserSjqxList(String f_yhbh, String f_qxbh, String columns, String tableName,String usersjqxTb) {
		// TODO Auto-generated method stub
		return usersjqxMapper.findUserSjqxList(f_yhbh, f_qxbh, columns, tableName,usersjqxTb);
	}
	@Override
	public boolean delPartUserSjqx(List<String> delsjList, String f_yhbh, String f_qxbh, String usersjqxTb) {
		// TODO Auto-generated method stub
		return usersjqxMapper.delPartUserSjqx(delsjList, f_yhbh, f_qxbh, usersjqxTb);
	}
	@Override
	public boolean delUserSjqx(String f_yhbh, String f_qxbh, String usersjqxTb) {
		// TODO Auto-generated method stub
		return usersjqxMapper.delUserSjqx(f_yhbh, f_qxbh, usersjqxTb);
	}
}