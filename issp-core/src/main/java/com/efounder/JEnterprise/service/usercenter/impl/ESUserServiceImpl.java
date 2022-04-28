package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.exception.BusinessException;
import com.efounder.JEnterprise.common.util.salt.Digests;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.mapper.usercenter.ESUserGnqxManageMapper;
import com.efounder.JEnterprise.mapper.usercenter.ESUserMapper;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description  用户定义接口实现类
 * @author guzhirui
 * @date 2018年1月17日下午3:02:03
 *
 */

/**
 * @Description  个人中心接口 因所用数据表相同，故再次添加接口
 * @author liuhoujun
 * @date 2018/11/26
 *
 */
@Service("esUserService")
public class ESUserServiceImpl implements ESUserService,ESBaseService{

	private static final Logger log = LoggerFactory.getLogger(ESUserServiceImpl.class);
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	@Autowired
	private ESUserMapper esUserMapper;

	@Autowired
	private ESUserGnqxManageMapper esUserGnqxManageMapper;

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(ESUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
		user.setF_pass(Encodes.encodeHex(hashPassword));
	}

	/* (non-Javadoc)
	 * @see com.efounder.JEnterprise.service.simple.ESUserService#addUser(com.efounder.JEnterprise.entity.ESUser, com.efounder.JEnterprise.entity.ESRole)
	 */
	@Transactional
	@Override
	public boolean addUser(ESUser user) {
		if (user == null ) {
			throw new BusinessException("user.registr.error", "注册信息错误");
		}
		if (StringUtils.isBlank(user.getF_name()) || StringUtils.isBlank(user.getF_pass())) {
			throw new BusinessException("user.registr.error", "注册信息错误");
		}
		//	        if (StringUtils.isBlank(role.getF_rolebh())) {
		//	            throw new BusinessException("user.registr.error", "用户未指定所属角色");
		//	        }

		// Role r = daoService.getByPrimaryKey(Role.class, role.getId());
		//	        ESRole r = esuserRoleMapper.findRoleById(role.getF_rolebh());
		//	        if (r == null) {
		//	            throw new BusinessException("user.registr.error", "用户未指定所属组织或角色");
		//	        }

		ESUser u = esUserMapper.findUserByName(user.getF_name());
		if(u!=null){
			throw new BusinessException("user.registr.error", "用户账号已经存在,username="+user.getF_name());
		}
		entryptPassword(user);
		//user.setStatus(Constants.STATUS_VALID);
		//user.setF_crdate(Calendar.getInstance().getTime());
		//  user.setF_yhbh(FactoryAboutKey.getPK(TablesPKEnum.ESUSER));
		return esUserMapper.addUser(user);
		//	        ESRole er = new ESRole();
		//	        er.setF_rolebh(r.getF_rolebh());
		//	        er.set(user.getId());
		//	        ur.setId(FactoryAboutKey.getPK(TablesPKEnum.T_SYS_USER_ROLE));
		//	        // daoService.save(ur);
		//	        userRoleMapper.insert(ur);
	}

	/**
	 * 分页查询
	 */
	@Override
	@Cacheable(value="users")
	//@Cacheable(value="users",key="#p0") @CachePut(key = "#p0") @Cacheable(value={"users"}, key="#user.id", condition="#user.id%2==0")
	//@CacheEvict(value="books", allEntries=true， beforeInvocation=true)
	//@Caching(evict = { @CacheEvict("primary"), @CacheEvict(cacheNames="secondary", key="#p0") })
	//可以包含上面介绍的三个注解，key-value分别对应(cachable=[@Cacheable], put=[@CachePut], evict=[@CacheEvict])
	public PageInfo<ESUser> findUserByPage(Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESUser> user = esUserMapper.findUserByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESUser> page = new PageInfo<ESUser>(user);
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	public List<ESUser> findUserByKeywords(String keywords){
		log.info("# 查询默认数据库");
		//return esUserMapper.findByKeywords(keywords);
		return null;
	}

	@Override
	public List<ESUser> findUsers() {
		log.info("#userMapper获取用户信息" );
		return esUserMapper.findUsers();
	}
	@Override
	public List<ESUser> findUsersBykey(String keywords) {
		log.info("#userMapper获取用户信息" );
		return esUserMapper.findUsersByKeywords(keywords);
	}

	@Override
	public boolean delUser(ESUser user) {
		log.info("#userMapper删除用户信息");
		//没有删除gnqx代码
		return esUserMapper.delUser(user);
	}

	/**
	 * 删除用户及其关联的权限
	 *
	 * @param user
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUser(ESUser user) {
		esUserGnqxManageMapper.delGnqxByUser(user);
		esUserMapper.delUser(user);
		return true;
	}

	/**
	 * 更新修改用户(用户页面)
	 * @param user
	 * @return
	 */
	@Override
	public boolean upUser(ESUser user) {
		log.info("#userMapper更新修改用户信息");
		return esUserMapper.upUser(user);
	}

	/**
	 * 更新用户密码(用户页面)
	 * @param user
	 * @return
	 */
	@Override
	public boolean upUserPass(ESUser user) {
		log.info("#userMapper更新修改用户信息");
		entryptPassword(user);
		return esUserMapper.resetPass(user);
	}

	/**
	 * 修改用户头像(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterUserhead(ESUser user) {
		log.info("#userMapper更新修改用户头像");
		return esUserMapper.updatePersonalCenterUserhead(user);
	}

	/**
	 * 修改用户信息(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterUserInfo(ESUser user) {
		log.info("#userMapper更新修改用户信息");
		return esUserMapper.updatePersonalCenterUserInfo(user);
	}

	/**
	 * 修改密码(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterPassword(ESUser user) {
		log.info("#userMapper更新修改用户信息");
		entryptPassword(user);
		return esUserMapper.updatePersonalCenterPassword(user);
	}

	@Override
	public ESUser findUserById(String id) {
		log.info("#userMapper获取用户信息");
		return esUserMapper.findUserById(id);
	}

	@Override
	public List<ESUser> findUserByzzjgId(String zzjgbh) {
		log.info("#userMapper获取用户信息");
		return esUserMapper.findUserByZzjgId(zzjgbh);
	}

	/**
	 * 根据组织机构查询用户信息
	 *
	 * @param map 组织机构编号列表
	 * @return
	 */
	@Override
	public List<ESUser> getUserByZzjg(Map<String,Object> map) {
		return esUserMapper.getUserByZzjg(map);
	}

	@Override
	public List<ESUser> findUserBykeywords(String euserkeywords) {
		return esUserMapper.findUserBykey(euserkeywords);
	}

	@Override
	public List<ESUser> findUserByrlglId(String yhbh) {
		log.info("#userMapper获取用户信息");
		return esUserMapper.findUserByRlglId(yhbh);
	}

	public List<ESUser> findUserByusername(String username){
		log.info("# 查询用户编号与角色信息");
		return esUserMapper.findUserByusername(username);
	}

	@Override
	public boolean exUserPass(ESUser user) {
		log.info("#userMapper更新修改用户密码");
		entryptPassword(user);
		return esUserMapper.upUser(user);
	}

	@Override
	public ESUser findUserByBH(String id) {
		log.info("#userMapper获取用户头像图片路径信息");
		return esUserMapper.findUserByBH(id);
	}

	@Override
	public boolean interceptIP(String fip) {
		log.info("#userMapper判断IP是否存在");
		int ip=esUserMapper.interceptIP(fip);
		if(ip==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String username(String fip) {
		log.info("#userMapper获取username");
		return esUserMapper.username(fip);
	}

}
