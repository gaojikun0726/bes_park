package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.UserGrouprlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 类名称：UserGrouprlglMapper
 * 类描述：用户组和用户关系mapper接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 下午1:44:15
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface UserGrouprlglMapper extends BaseMapper<String, UserGrouprlgl>{
	
	/**
	 * 查询子类
	 * @author Gongfanfei
	 * @param f_zbh
	 * @return
	 */
	List<UserGrouprlgl> findChildByZbh(@Param("f_zbh") String f_zbh);
	/**
	 * @author gongfanfei
	 * 加载用户组关系表
	 * @return
	 */
	List<UserGrouprlgl> loadAll();
	/**
	 * 删除用户
	 * @param f_yhbh
	 * @return
	 */
	boolean deleteByYhbh(@Param("f_yhbh") String f_yhbh);
	/**
	 * 删除用户组关系表对应下的所有用户（左移）
	 * @param f_zbh
	 * @return
	 */
	boolean leftMoveByZbh(@Param("f_zbh") String f_zbh);
}
