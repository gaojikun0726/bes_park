package com.efounder.JEnterprise.service.usercenter.impl;

import com.alibaba.fastjson.JSONArray;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.usercenter.ESPostMapper;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESPostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 岗位自定义接口实现类
 * @author gongfanfei
 */
@Service("esPostService")
public class ESPostServiceImpl implements ESPostService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESPostServiceImpl.class);

	@Autowired
	private ESPostMapper postMapper;

	@Override
	public List<ESPost> findPosts() {
		log.info("#postMapper获取用户岗位信息");
		return postMapper.findPosts();
	}

	@Override
	public ESPost findPostById(String id) {
		log.info("#postMapper获取用户岗位信息");
		return postMapper.findPostById(id);
	}
	@Override
	public List<ESPost> findPostByZzjgId(String zzjgbh) {
		log.info("#postMapper获取用户岗位信息");
		return postMapper.findPostByZzjgId(zzjgbh);
	}

	@Override
	public List<ESUser> findUserByPostId(String id) {
		log.info("#postMapper通过岗位id加载用户信息");
		return postMapper.findUserByPostId(id);
	}

	@Override
	public PageInfo<ESPost> findUserPostByPage(Integer pageNum, String keywords) {
		log.info("#postMapper分页获取用户岗位信息");
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESPost> userPost = postMapper.findUserPostByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESPost> page = new PageInfo<ESPost>(userPost);
		return page;
	}
	@Override
	public List<ESPost> getUserPostList(String keywords) {
		// TODO Auto-generated method stub
	        List<ESPost> list = postMapper.findUserPost(keywords);
	        log.info("# 查询默认数据库 page.toString()={}", list.toString());
	        return list;
	}
	@Override
    public List<ESPost> findRoleByUserId(String userId) {
        return postMapper.findPostByUserId(userId);
    }

	@Transactional
	@Override
	public boolean addPost(ESPost post) {
		log.info("#postMapper添加用户岗位信息");
		return postMapper.addPost(post);
	}

	@Override
	public boolean delPost(ESPost post) {
		log.info("#postMapper删除用户岗位信息");
		return postMapper.delPost(post);
	}

	@Override
	public boolean upPost(ESPost post) {
		log.info("#postMapper更新用户岗位信息");
		return postMapper.upPost(post);
	}

	@Override
	public List<ESPost> findPostByZzjgbh(String zzjgbh) {
		// TODO Auto-generated method stub
		log.info("#postMapper获取用户岗位信息");
		return postMapper.findPostByZzjgbh(zzjgbh);
	}

	@Override
	public ESPost findPostByPostBh(String f_gwguid) {
		// TODO Auto-generated method stub
		return postMapper.findPostByPostBh(f_gwguid);
	}

	/**
	 * 根据组织机构bh查询岗位信息
	 *
	 * @param zzjgbh
	 * @return
	 */
	@Override
	public List<ESPost> findPostByZzjgbhArray(JSONArray zzjgbh) {
		Map<String,Object> map = new HashMap<>();
		map.put("array",zzjgbh);
		return postMapper.findPostByZzjgbhArray(map);
	}
}
