package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.usercenter.ESSjqxMapper;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 数据字典维护接口实现类
 * @author gongfanfei
 */
@Service("esSjqxService")
public class ESSjqxServiceImpl implements ESSjqxService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESSjqxServiceImpl.class);

	@Autowired
	private ESSjqxMapper sjqxMapper;
	/**
	 * ApplicationName:应用ID
	 * 
	 * @since 1.0.0
	 */
	@Value("${JEnterprise.ApplicationId}")
	private String ApplicationId;

	public String getApplicationId() {
		return ApplicationId;
	}
	@Override
	public List<ESSjqx> findSjqxs() {
		log.info("#sjqxMapper获取数据权限信息");
		return sjqxMapper.findSjqxs();
	}
	//根据子系统编号查询数据权限信息
	@Override
	public List<ESSjqx> findSjqxByXtbh(String id) {
		log.info("#sjqxMapper获取数据权限信息");
		return sjqxMapper.findSjqxByXtbh(id);
	}

	@Override
	public ESSjqx findSjqxById(String id) {
		log.info("#sjqxMapper获取数据权限信息");
		return sjqxMapper.findSjqxById(id);
	}

	
	@Override
	public PageInfo<ESSjqx> findSjqxByPage(Integer pageNum, String keywords) {
		log.info("#sjqxMapper分页获取数据权限信息");
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESSjqx> sjqx = sjqxMapper.findSjqxByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESSjqx> page = new PageInfo<ESSjqx>(sjqx);
		return page;
	}
	@Override
	public List<ESSjqx> getSjqxListByKeywords(String keywords) {
		// TODO Auto-generated method stub
	        List<ESSjqx> list = sjqxMapper.findSjqxBykeywords(keywords);
	        log.info("# 查询默认数据库 page.toString()={}", list.toString());
	        return list;
	}
	@Override
	public List<ESSjqx> getSjqxListByKey(String keywords) {
		// TODO Auto-generated method stub
		List<ESSjqx> list = sjqxMapper.findSjqxBykeywords(keywords);
		log.info("# 查询默认数据库 page.toString()={}", list.toString());
		return list;
	}
	
	@Transactional
	@Override
	public boolean addSjqx(ESSjqx sjqx) {
		log.info("#sjqxMapper添加数据权限信息");
		sjqx.setF_xtbh(ApplicationId);
		return sjqxMapper.addSjqx(sjqx);
	}

	@Override
	public boolean delSjqx(ESSjqx sjqx) {
		log.info("#sjqxMapper删除数据权限信息");
		return sjqxMapper.delSjqx(sjqx);
	}

	@Override
	public boolean upSjqx(ESSjqx sjqx) {
		log.info("#sjqxMapper更新数据权限信息");
		return sjqxMapper.upSjqx(sjqx);
	}
	@Override
	public String findMaxBmbh() {
		// TODO Auto-generated method stub
		return sjqxMapper.findMaxBmbh();
	}
	@Override
	public ESSjqx checkQxbhExist(String qxbh) {
		// TODO Auto-generated method stub
		return sjqxMapper.checkQxbhExist(qxbh);
	}
}