package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserGroupSjqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupSjqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupSjqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户组数据权限接口实现类
 * @author gongfanfei
 */
@Service("esUserGroupSjqxManageService")
public class ESUserGroupSjqxManageServiceImpl implements ESUserGroupSjqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserGroupSjqxManageServiceImpl.class);

	@Autowired
	private ESUserGroupSjqxManageMapper userGroupsjqxMapper;

	@Override
	public List<ESUserGroupSjqxManage> findUserGroupSjqx() {
		log.info("#userGroupsjqxMapper获取全部用户组数据权限信息");
		return userGroupsjqxMapper.findUserGroupSjqx();
	}

	
	@Override
    public List<ESUserGroupSjqxManage> findUserGroupSjqxByKey(String keywords) {
		log.info("#userGroupsjqxMapper获取模糊搜索用户组数据权限信息");
        return userGroupsjqxMapper.findUserGroupSjqxByKey(keywords);
    }


	@Override
	public boolean upUserGroupSjqx(ESUserGroupSjqxManage usersjqx) {
		log.info("#userGroupsjqxMapper更新用户组数据权限信息");
		return userGroupsjqxMapper.upUserGroupSjqx(usersjqx);
	}

	@Override
	public List<?> getcolumnsBytableName(String columns, String tableName) {
		// TODO Auto-generated method stub
		return userGroupsjqxMapper.getcolumnsBytableName(columns, tableName);
	}
	@Override
	public List<ESUserGroupSjqxManage> findUserGroupSjqxById(String f_zbh, String f_qxbh, String columns,
			String tableName, String classifysjqxTb) {
		// TODO Auto-generated method stub
		return userGroupsjqxMapper.findUserGroupSjqxById(f_zbh,f_qxbh,columns,tableName,classifysjqxTb);
	}
	@Override
	public boolean addUserGroupSjqx(List<ESUserGroupSjqxManage> esuserGroupsjqxmanage,String classifysjqxTb) {
		// TODO Auto-generated method stub
		return userGroupsjqxMapper.addUserGroupSjqx(esuserGroupsjqxmanage,classifysjqxTb);
	}


	@Override
	public List<ESUserGroupSjqxManage> findUserGroupSjqxByThreeId(String f_zbh, String f_qxbh, String f_bhzd) {
		// TODO Auto-generated method stub
		return userGroupsjqxMapper.findUserGroupSjqxByThreeId(f_zbh, f_qxbh, f_bhzd);
	}


	@Override
	public boolean delUserGroupSjqx(String f_zbh, String f_qxbh, String classifysjqxTb) {
		// TODO Auto-generated method stub
		return userGroupsjqxMapper.delUserGroupSjqx(f_zbh, f_qxbh, classifysjqxTb);
	}

}