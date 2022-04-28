package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnqx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 功能权限mapper接口
 * @author gongfanfei
 */
@Mapper
public interface ESGnqxMapper extends BaseMapper<String, ESGnqx> {
	/**
	 * 查询所有功能权限信息 
	 * 
	 * @return
	 */
	List<ESGnqx> findGnqxs();
	/**
	 * 根据关键字高级搜索功能权限 
	 * 
	 * @param keywords
	 * @return
	 */
	List<ESGnqx> findGnqxBykeywords(@Param("f_xtbh") String f_xtbh,@Param("keywords") String keywords);
	/**
	 * 根据系统编号查询功能权限信息
	 * 
	 * @param id
	 * @return
	 */
	
	List<ESGnqx> findGnqxByXtbh(String id);
	/**
	 * 根据功能权限编号查询信息
	 * 
	 * @param id
	 * @return
	 */
	ESGnqx findGnqxById(String id);


	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESGnqx> findGnqxByPage(@Param("keywords") String keywords);
	
	/**
	 * 增加功能权限
	 * 
	 * @param gnqx
	 * @return
	 */
	boolean addGnqx(ESGnqx gnqx);

	/**
	 * 删除功能权限
	 * 
	 * @param gnqx
	 * @return
	 */
	boolean delGnqx(ESGnqx gnqx);

	/**
	 * 更新功能权限
	 * 
	 * @param gnqx
	 * @return
	 */
	boolean upGnqx(ESGnqx gnqx);
}
