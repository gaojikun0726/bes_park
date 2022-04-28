package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 数据权限mapper接口
 * @author gongfanfei
 */
@Mapper
public interface ESSjqxMapper extends BaseMapper<String, ESSjqx> {
	/**
	 * 查询所有数据权限信息 
	 * @return
	 */
	List<ESSjqx> findSjqxs();
	
	/**
	 * 根据关键字高级搜索数据权限 
	 * @param keywords
	 * @return
	 */
	List<ESSjqx> findSjqxBykeywords(@Param("keywords") String keywords);
	
	/**
	 * 根据系统编号查询数据权限信息
	 * @param id
	 * @return
	 */
	
	List<ESSjqx> findSjqxByXtbh(String id);
	
	/**
	 * 根据数据权限编号查询信息
	 * @param id
	 * @return
	 */
	ESSjqx findSjqxById(String id);

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESSjqx> findSjqxByPage(@Param("keywords") String keywords);
	
	/**
	 * 增加数据权限
	 * @param sjqx
	 * @return
	 */
	boolean addSjqx(ESSjqx sjqx);

	/**
	 * 删除数据权限
	 * @param sjqx
	 * @return
	 */
	boolean delSjqx(ESSjqx sjqx);

	/**
	 * 更新数据权限
	 * @param sjqx
	 * @return
	 */
	boolean upSjqx(ESSjqx sjqx);
	
	/**
	 * 获取当前表信息中最大编号
	 * @return
	 */
	public String findMaxBmbh();
	
	/**
	 * 查询数据的权限编号是否存在
	 * @param qxbh
	 * @return
	 */
	public ESSjqx  checkQxbhExist(@Param("qxbh") String qxbh);
}
