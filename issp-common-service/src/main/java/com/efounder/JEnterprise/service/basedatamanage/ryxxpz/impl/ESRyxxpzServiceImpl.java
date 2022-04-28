package com.efounder.JEnterprise.service.basedatamanage.ryxxpz.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.exception.BusinessException;
import com.efounder.JEnterprise.common.util.salt.Digests;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.mapper.basedatamanage.ryxxpz.ESRyxxpzMapper;
import com.efounder.JEnterprise.model.basedatamanage.ryxxpz.ESRyxxpz;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.ryxxpz.ESRyxxpzService;
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
@Service("esRyxxpzService")
public class ESRyxxpzServiceImpl implements ESRyxxpzService,ESBaseService{

	private static final Logger log = LoggerFactory.getLogger(ESRyxxpzServiceImpl.class);
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	@Autowired
	private ESRyxxpzMapper esRyxxpzMapper;

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(ESRyxxpz user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
		user.setF_pass(Encodes.encodeHex(hashPassword));
	}

	/* (non-Javadoc)
	 * @see com.efounder.JEnterprise.service.simple.ESRyxxpzService#addUser(com.efounder.JEnterprise.entity.ESRyxxpz, com.efounder.JEnterprise.entity.ESRole)
	 */
	@Transactional
	@Override
	public boolean addUser(ESRyxxpz user) {
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

		ESRyxxpz u = esRyxxpzMapper.findUserByName(user.getF_name());
		if(u!=null){
			throw new BusinessException("user.registr.error", "用户账号已经存在,username="+user.getF_name());
		}
		entryptPassword(user);
		//user.setStatus(Constants.STATUS_VALID);
		//user.setF_crdate(Calendar.getInstance().getTime());
		//  user.setF_yhbh(FactoryAboutKey.getPK(TablesPKEnum.ESUSER));
		return esRyxxpzMapper.addUser(user);
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
	public PageInfo<ESRyxxpz> findUserByPage(Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESRyxxpz> user = esRyxxpzMapper.findUserByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESRyxxpz> page = new PageInfo<ESRyxxpz>(user);
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	public List<ESRyxxpz> findUserByKeywords(String keywords){
		log.info("# 查询默认数据库");
		return esRyxxpzMapper.findByKeywords(keywords);
	}

	@Override
	public List<ESRyxxpz> findUsers() {
		log.info("#ryxxpzMapper获取用户信息" );
		return esRyxxpzMapper.findUsers();
	}
	@Override
	public List<ESRyxxpz> findUsersBykey(String keywords) {
		log.info("#ryxxpzMapper获取用户信息" );
		return esRyxxpzMapper.findUsersByKeywords(keywords);
	}

	@Override
	public boolean delUser(ESRyxxpz user) {
		log.info("#ryxxpzMapper删除用户信息");
		return esRyxxpzMapper.delUser(user);
	}

	/**
	 * 更新修改用户(用户页面)
	 * @param user
	 * @return
	 */
	@Override
	public boolean upUser(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户信息");
		return esRyxxpzMapper.upUser(user);
	}

	/**
	 * 更新用户密码(用户页面)
	 * @param user
	 * @return
	 */
	@Override
	public boolean upUserPass(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户信息");
		entryptPassword(user);
		return esRyxxpzMapper.resetPass(user);
	}

	/**
	 * 修改用户头像(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterUserhead(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户头像");
		return esRyxxpzMapper.updatePersonalCenterUserhead(user);
	}

	/**
	 * 修改用户信息(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterUserInfo(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户信息");
		return esRyxxpzMapper.updatePersonalCenterUserInfo(user);
	}

	/**
	 * 修改密码(个人中心)
	 * @param user
	 * @return
	 */
	@Override
	public boolean updatePersonalCenterPassword(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户信息");
		entryptPassword(user);
		return esRyxxpzMapper.updatePersonalCenterPassword(user);
	}

	@Override
	public ESRyxxpz findUserById(String id) {
		log.info("#ryxxpzMapper获取用户信息");
		return esRyxxpzMapper.findUserById(id);
	}

	@Override
	public List<ESRyxxpz> findRyxxByDwxxId(String zzjgbh) {
		log.info("#ryxxpzMapper获取用户信息");
		return esRyxxpzMapper.findRyxxByDwxxId(zzjgbh);
	}

	@Override
	public List<ESRyxxpz> findUserBykeywords(String euserkeywords) {
		return esRyxxpzMapper.findUserBykey(euserkeywords);
	}

	@Override
	public List<ESRyxxpz> findUserByrlglId(String yhbh) {
		log.info("#ryxxpzMapper获取用户信息");
		return esRyxxpzMapper.findUserByRlglId(yhbh);
	}

	public List<ESRyxxpz> findUserByusername(String username){
		log.info("# 查询用户编号与角色信息");
		return esRyxxpzMapper.findUserByusername(username);
	}

	@Override
	public boolean exUserPass(ESRyxxpz user) {
		log.info("#ryxxpzMapper更新修改用户密码");
		entryptPassword(user);
		return esRyxxpzMapper.upUser(user);
	}

	@Override
	public ESRyxxpz findUserByBH(String id) {
		log.info("#ryxxpzMapper获取用户头像图片路径信息");
		return esRyxxpzMapper.findUserByBH(id);
	}

	@Override
	public boolean interceptIP(String fip) {
		log.info("#ryxxpzMapper判断IP是否存在");
		int ip=esRyxxpzMapper.interceptIP(fip);
		if(ip==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String username(String fip) {
		log.info("#ryxxpzMapper获取username");
		return esRyxxpzMapper.username(fip);
	}

}
