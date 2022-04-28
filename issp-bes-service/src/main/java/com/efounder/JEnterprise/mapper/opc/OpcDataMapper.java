package com.efounder.JEnterprise.mapper.opc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface OpcDataMapper {

	List<Map<String, String>> getAllData(@Param("f_sys_name") String f_sys_name, @Param("dbname") String dbname);

	Map<String, String> getNodeData(@Param("F_SYS_NAME")String F_SYS_NAME, @Param("F_NODE_TABLE")String F_NODE_TABLE);
	/**
	 * 新增
	 * @param allData
	 */
	void insertData(@Param("allData") Map<String, String> allData);
	/**
	 * 获取节点的属性表
	 * @param f_sys_name
	 * @return
	 */
	Map<String, String> getNodeTable(String f_sys_name);
	/**
	 * 删除该条数据
	 * @param o_key
	 */
	void delOpcData(String o_key);
	/**
	 * 编辑该条数据
	 * @param allData
	 */
	void editData(@Param("allData") Map<String, String> allData);
	/**
	 * 查询该条数据
	 * @param o_key
	 * @return
	 */
	List<Map<String, String>> queryOpcData(@Param("o_key") String o_key);
	/**
	 * 获取所有OPC数据
	 * @return
	 */
	List<Map<String, String>> getOpcData();

}
