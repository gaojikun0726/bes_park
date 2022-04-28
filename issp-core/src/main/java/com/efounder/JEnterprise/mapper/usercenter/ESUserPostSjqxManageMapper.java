package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserPostSjqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位数据权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月15日
 */
@Mapper
public interface ESUserPostSjqxManageMapper extends BaseMapper<String, ESUserPostSjqxManage>{

	/**
	 * 获取所有岗位数据权限信息
	 * @return
	 */
	List<ESUserPostSjqxManage> findUserPostSjqx();

	/**
	 * 联合取所有岗位数据权限信息
	 * @param f_gwguid
	 * @param f_qxbh
	 * @param f_bhzd
	 * @return
	 */
	List<ESUserPostSjqxManage> findUserPostSjqxByThreeId(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh,@Param("f_bhzd")String f_bhzd);
	
	/**
	 * 获取岗位数据权限信息
	 * @param f_gwguid
	 * @param f_qxbh
	 * @param columns
	 * @param tableName
	 * @param classifysjqxTb
	 * @return
	 */
	List<ESUserPostSjqxManage> findUserPostSjqxById(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh,@Param("columns")String columns,@Param("tableName")String tableName,@Param("classifysjqxTb")String classifysjqxTb);

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserPostSjqxManage> findUserPostSjqxByKey(@Param("keywords") String keywords);

	/**
	 * 增加岗位数据权限
	 * @param usersjqx
	 * @param classifysjqxTb
	 * @return
	 */
	boolean addUserPostSjqx(@Param("list")List<ESUserPostSjqxManage> usersjqx,@Param("classifysjqxTb") String classifysjqxTb);

	/**
	 * 删除岗位数据权限
	 * @param f_gwguid
	 * @param f_qxbh
	 * @param classifysjqxTb
	 * @return
	 */
	boolean delUserPostSjqx(@Param("f_gwguid")String f_gwguid,@Param("f_qxbh")String f_qxbh,@Param("classifysjqxTb")String classifysjqxTb);

	/**
	 * 更新岗位数据权限
	 * @param group
	 * @return
	 */
	boolean upUserPostSjqx(ESUserPostSjqxManage userPostSjqx);
	
	/**
	 * 根据表名查询字段
	 * @param columns
	 * @param tableName
	 * @return
	 */
	List<?> getcolumnsBytableName(@Param("columns")String columns,@Param("tableName")String tableName);
	
}
