package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserPostGnqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位功能权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月21日
 */
@Mapper
public interface ESUserPostGnqxManageMapper extends BaseMapper<String, ESUserPostGnqxManage>{

	/**
	 * 获取所有岗位功能权限信息
	 * @return
	 */
	List<ESUserPostGnqxManage> findUserPostGnqx();
	
	/**
	 * 联合获取所有岗位功能权限信息
	 * @param f_gwguid
	 * @param f_qxbh
	 * @param f_sjbh
	 * @return
	 */
	List<ESUserPostGnqxManage> findUserPostGnqxByThreeId(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh,@Param("f_sjbh")String f_sjbh);
	
	/**
	 * 根据岗位功能权限ID和权限编号获取岗位功能权限信息
	 * @param f_gwguid
	 * @param f_qxbh
	 * @return
	 */
	List<ESUserPostGnqxManage> findUserPostGnqxByGwbhAndQxbh(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh);
	
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserPostGnqxManage> findUserPostGnqxByKey(@Param("keywords") String keywords);

	/**
	 * 增加岗位功能权限
	 * @param role
	 * @return
	 */
	boolean addUserPostGnqx(ESUserPostGnqxManage userPostGnqx);

	/**
	 * 删除岗位功能权限
	 * @param role
	 * @return
	 */
	boolean delUserPostGnqx(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh,@Param("guid")String guid);

	/**
	 * 更新岗位功能权限
	 * @param role
	 * @return
	 */   
	boolean upUserPostGnqx(ESUserPostGnqxManage userPostGnqx);
	
	/**
	 * 根据岗位guid和存储表名查询岗位功能权限
	 * @param f_gwguid
	 * @param classifygnqxTb
	 * @return
	 */
	List<ESUserPostGnqxManage> findUserPostGnqxByYhbhAndQxbh(@Param("f_gwguid")String f_gwguid,@Param("classifygnqxTb")String classifygnqxTb);
	//boolean delUserPostGnqx(List<ESUserPostGnqxManage> deleteGgnqxList, String tablename, String f_gwguid);
	
	/**
	 * 批量删除岗位功能权限
	 * @param deleteGgnqxList
	 * @param tablename
	 * @param f_gwguid
	 * @return
	 */
	boolean delUserPostGnqx(@Param("list")List<ESUserPostGnqxManage> deleteGgnqxList,@Param("tablename")String tablename,@Param("f_gwguid")String f_gwguid);
	
	/**
	 * 批量添加岗位功能权限
	 * @param gnqxList
	 * @param tablename
	 * @return
	 */
	boolean addUserPostGnqx(@Param("list")List<ESUserPostGnqxManage> gnqxList, @Param("tablename")String tablename);
	
	/**
	 * 批量更新岗位功能权限
	 * @param gnqxList
	 * @param tablename
	 * @param f_gwguid
	 * @return
	 */
	boolean upUserPostGnqx(@Param("list")List<ESUserPostGnqxManage> gnqxList, @Param("tablename")String tablename, @Param("f_gwguid")String f_gwguid);
	

}
