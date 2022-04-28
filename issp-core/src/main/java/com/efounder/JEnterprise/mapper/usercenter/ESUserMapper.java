package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Description 用户自定义
 * @author gongfanfei
 * @date 2018年4月17日下午1:56:48
 */

/**
 * @Description  个人中心接口 因所用数据表相同，故再次添加接口
 * @author liuhoujun
 * @date 2018/11/26
 *
 */
@Mapper
public interface ESUserMapper extends BaseMapper<String, ESUser>{

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
    List<ESUser> findUserByPage(@Param("keywords") String keywords);

    /**
     * 查询所有用户
     * @return
     */
    List<ESUser> findUsers();
    /**
     *
     * @param keywords
     * @return
     */
    List<ESUser> findUsersByKeywords(@Param("keywords") String keywords);
    /**
     * @param euserkeywords
     * 根据组织机构ID查询所有用户
     * @return
     */
    List<ESUser> findUserBykey(@Param("euserkeywords") String euserkeywords);

    /**
     * 根据组织机构ID查询所有用户
     * @return
     */
    List<ESUser> findUserByZzjgId(String zzjgbh);

    /**
     * 根据组织机构查询用户信息
     * @param map 组织机构编号列表
     * @return
     */
    List<ESUser> getUserByZzjg(Map<String,Object> map);

    /**
     * 根据组织机构ID查询所有用户
     * @return
     */
    List<ESUser> findUserByRlglId(String yhbh);

    /**
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    ESUser findUserById(String id);
    /**
     * 根据用户ID获取图片路径信息
     * @param id
     * @return
     */
    ESUser findUserByBH(String id);
    /**
     * 增加用户
     * @param user
     * @return
     */
    boolean addUser(ESUser user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    boolean delUser(ESUser user);

    /**
	 * 更新修改用户(用户页面)
	 * @param user
	 * @return
	 */
    boolean upUser(ESUser user);
    /**
     * 重置密码(用户页面)
     * @param user
     * @return
     */
    boolean resetPass(ESUser user);

    /**
	 * 修改用户信息(个人中心)
	 * @param user
	 * @return
	 */
	boolean updatePersonalCenterUserInfo(ESUser user);

    /**
     * 更新修改用户头像(个人中心)
     * @param user
     * @return
     */
    boolean updatePersonalCenterUserhead(ESUser user);

    /**
     * 更新用户密码(个人中心)
     * @param user
     * @return
     */
    boolean updatePersonalCenterPassword(ESUser user);

    /**
     * 根据关键字模糊查询用户列表
     * @param keywords
     * @return
     */
	//List<ESUser> findByKeywords(@Param("keywords") String keywords);

	/**
     * 根据用户名查询用户
     * @return user 用户
     */
    public ESUser findUserByName(String f_name);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public List<ESUser> findUserByusername(@Param("username") String username);


    /**
     * 根据IP进行拦截
     */
	public int interceptIP(String fip);

	/**
	 * 根据用户名拦截
	 */
	public String username(String fip);
	}
