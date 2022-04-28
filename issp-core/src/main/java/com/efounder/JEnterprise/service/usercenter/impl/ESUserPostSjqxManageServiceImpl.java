package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserPostSjqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserPostSjqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESPostSjqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 岗位功能权限接口实现类
 * @author gongfanfei
 */
@Service("esUserPostSjqxManageService")
public class ESUserPostSjqxManageServiceImpl implements ESPostSjqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostSjqxManageServiceImpl.class);

	@Autowired
	private ESUserPostSjqxManageMapper userPostsjqxMapper;

	@Override
	public List<ESUserPostSjqxManage> findUserPostSjqx() {
		log.info("#userPostsjqxMapper获取全部岗位数据权限信息");
		return userPostsjqxMapper.findUserPostSjqx();
	}
	@Override
	public List<ESUserPostSjqxManage> findUserPostSjqxByThreeId(String f_zbh, String f_qxbh,String f_bhzd) {
		log.info("#userPostsjqxMapper获取全部岗位数据权限信息");
		return userPostsjqxMapper.findUserPostSjqxByThreeId(f_zbh,f_qxbh,f_bhzd);
	}


	
	@Override
    public List<ESUserPostSjqxManage> findUserPostSjqxByKey(String keywords) {
		log.info("#userPostsjqxMapper获取模糊搜索岗位数据权限信息");
        return userPostsjqxMapper.findUserPostSjqxByKey(keywords);
    }

	
	/*public boolean addUserPostSjqx(ESUserPostSjqxManage usersjqx) {
		log.info("#userPostsjqxMapper添加岗位数据权限信息");
		return userPostsjqxMapper.addUserPostSjqx(usersjqx);
	}*/

	@Transactional
	@Override
	public boolean addUserPostSjqx(List<ESUserPostSjqxManage> usersjqx,String classifysjqxTb) {
		log.info("#userRolesjqxMapper添加用户组数据权限信息");
		return userPostsjqxMapper.addUserPostSjqx(usersjqx,classifysjqxTb);
	}
	
	
	@Override
	public boolean delUserPostSjqx(String f_gwguid,String f_qxbh,String classifysjqxTb) {
		log.info("#userPostsjqxMapper删除岗位数据权限信息");
		return userPostsjqxMapper.delUserPostSjqx(f_gwguid, f_qxbh, classifysjqxTb);
	}

	@Override
	public boolean upUserPostSjqx(ESUserPostSjqxManage usersjqx) {
		log.info("#userPostsjqxMapper更新岗位数据权限信息");
		return userPostsjqxMapper.upUserPostSjqx(usersjqx);
	}

	@Override
	public List<?> getcolumnsBytableName(String columns, String tableName) {
		// TODO Auto-generated method stub
		return userPostsjqxMapper.getcolumnsBytableName(columns, tableName);
	}
	@Override
	public List<ESUserPostSjqxManage> findUserPostSjqxById(String f_gwguid, String f_qxbh, String columns,
			String tableName, String classifysjqxTb) {
		// TODO Auto-generated method stub
		return userPostsjqxMapper.findUserPostSjqxById(f_gwguid,f_qxbh,columns,tableName,classifysjqxTb);

	}
}