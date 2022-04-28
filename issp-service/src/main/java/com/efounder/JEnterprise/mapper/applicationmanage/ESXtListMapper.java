package com.efounder.JEnterprise.mapper.applicationmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ESXtListMapper extends BaseMapper<String, ESXtList> {
	/**
	 * 查询当前系统信息 
	 * 
	 * @return
	 */
	List<ESXtList> findCurrentXt(@Param("f_xtbh") String f_xtbh);
	/**
	 * 查询所有系统信息 
	 * 
	 * @return
	 */
	List<ESXtList> findXts();
	/**
	 * 根据关键字高级搜索系统 
	 * 
	 * @param keywords
	 * @return
	 */
	List<ESXtList> findXtBykeywords(@Param("keywords") String keywords);
	/**
	 * 根据系统编号查询信息
	 * 
	 * @param id
	 * @return
	 */
	ESXtList findXtById(String id);


	/**
	 * 分页查询
	 * 
	 * @param keywords
	 * @return
	 */
	List<ESXtList> findXtByPage(@Param("keywords") String keywords);
	
	/**
	 * 增加系统
	 * 
	 * @param xilist
	 * @return
	 */
	boolean addXt(ESXtList xilist);

	/**
	 * 删除系统
	 * 
	 * @param xilist
	 * @return
	 */
	boolean delXt(ESXtList xilist);

	/**
	 * 更新系统
	 * 
	 * @param xilist
	 * @return
	 */
	boolean upXt(ESXtList xilist);
	
	List<ESXtList> getEsxtList(@Param("keywords") String keywords);
}
