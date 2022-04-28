package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.UserPostRlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 类名称：UserPostRlglMapper
 * 类描述：岗位和用户关系mapper接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 下午1:44:15
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface UserPostRlglMapper extends BaseMapper<String, UserPostRlgl>{
	/**
	 * 查询岗位
	 * @author Gongfanfei
	 * @param f_zbh
	 * @return
	 */
	List<UserPostRlgl> findUserPostRlglByPage(@Param("f_zmc") String f_zmc);
	
	/**
	 * 查询子类
	 * @author Gongfanfei
	 * @param f_gwguid
	 * @return
	 */
	List<UserPostRlgl> findChildByPostbh(@Param("f_gwguid") String f_gwguid);
	/**
	 * @author gongfanfei
	 * 加载岗位
	 * @return
	 */
	List<UserPostRlgl> loadAll();
	/**
	 * 删除用户
	 * @param f_yhbh
	 * @return
	 */
	boolean deleteByYhbh(@Param("f_yhbh") String f_yhbh);//f_yhbh
	/**
	 * 删除岗位对应下的所有用户（左移）
	 * @param f_gwguid
	 * @return
	 */
	boolean leftMoveByPostbh(@Param("f_gwguid") String f_gwguid);
}
