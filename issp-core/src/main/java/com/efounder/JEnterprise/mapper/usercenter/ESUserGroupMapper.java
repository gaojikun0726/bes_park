package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 类名称：UserGroupMapper
 * 类描述：用户组mapper接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年1月3日 下午4:24:15
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface ESUserGroupMapper extends BaseMapper<String, ESUserGroup>{
	/**
	 * 查询用户组
	 * @author Gongfanfei
	 * @param f_zbh
	 * @return
	 */
	List<ESUserGroup> findUserGroupByPage(@Param("f_zmc") String f_zmc);
	
	/**
	 * 查询子类
	 * @author Gongfanfei
	 * @param f_zbh
	 * @return
	 */
	List<ESUserGroup> findChildByZbh(@Param("f_zbh") String f_zbh);
	/**
	 * 加载用户组树
	 * @return
	 */
	List<ESUserGroup> loadAll();

	List<ESUserGroup> findChildByZbh_all(@Param("f_zbh") String f_zbh);

	ESUserGroup findGroupByGroupBh(@Param("f_zbh") String f_zbh);
}
