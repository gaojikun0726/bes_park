package com.efounder.JEnterprise.mapper.applicationmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ESBmjgMapper extends BaseMapper<String, ESBmjg> {

	/**
	 * 编码结构分页查询
	 * 
	 * @param keywords
	 * @return
	 */
	List<ESBmjg> selBmjgpage(@Param("keywords") String keywords);

	/**
	 * 增加编码结构信息
	 * 
	 * @param esbmjg
	 * @return
	 */
	boolean insBmjg(ESBmjg esbmjg);

	/**
	 * 删除编码结构信息
	 * 
	 * @param id
	 * @return
	 */
	boolean delBmjg(String tableName);

	/**
	 * 查询单行数据 用于回显
	 * 
	 * @param id
	 * @return
	 */
	ESBmjg selectBmjg(String tableName);

	/**
	 * 
	 * 编辑编码结构信息
	 * 
	 * @param esbmjg
	 * @return
	 */
	boolean updBmjg(ESBmjg esbmjg);

}
