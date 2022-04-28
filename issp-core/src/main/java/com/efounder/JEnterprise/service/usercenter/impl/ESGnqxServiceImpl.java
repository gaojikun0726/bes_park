package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.usercenter.ESGnqxMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnqx;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESGnqxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 功能权限字典维护接口实现类
 * @author gongfanfei
 */
@Service("esGnqxService")
public class ESGnqxServiceImpl implements ESGnqxService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESGnqxServiceImpl.class);

	@Autowired
	private ESGnqxMapper gnqxMapper;

	@Override
	public List<ESGnqx> findGnqxs() {
		log.info("#gnqxMapper获取功能权限信息");
		return gnqxMapper.findGnqxs();
	}
	//根据子系统编号查询功能权限信息
	@Override
	public List<ESGnqx> findGnqxByXtbh(String id) {
		log.info("#gnqxMapper获取功能权限信息");
		return gnqxMapper.findGnqxByXtbh(id);
	}

	@Override
	public ESGnqx findGnqxById(String id) {
		log.info("#gnqxMapper获取功能权限信息");
		return gnqxMapper.findGnqxById(id);
	}

	
	@Override
	public PageInfo<ESGnqx> findGnqxByPage(Integer pageNum, String keywords) {
		log.info("#gnqxMapper分页获取功能权限信息");
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESGnqx> gnqx = gnqxMapper.findGnqxByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESGnqx> page = new PageInfo<ESGnqx>(gnqx);
		return page;
	}
	@Override
	public List<ESGnqx> getGnqxListByKeywords(String f_xtbh,String keywords) {
		// TODO Auto-generated method stub
	        List<ESGnqx> list = gnqxMapper.findGnqxBykeywords(f_xtbh,keywords);
	        log.info("# 查询默认数据库 page.toString()={}", list.toString());
	        return list;
	}
	
	@Transactional
	@Override
	public boolean addGnqx(ESGnqx gnqx) {
		log.info("#gnqxMapper添加功能权限信息");
		return gnqxMapper.addGnqx(gnqx);
	}

	@Override
	public boolean delGnqx(ESGnqx gnqx) {
		log.info("#gnqxMapper删除功能权限信息");
		return gnqxMapper.delGnqx(gnqx);
	}

	@Override
	public boolean upGnqx(ESGnqx gnqx) {
		log.info("#gnqxMapper更新功能权限信息");
		return gnqxMapper.upGnqx(gnqx);
	}
}