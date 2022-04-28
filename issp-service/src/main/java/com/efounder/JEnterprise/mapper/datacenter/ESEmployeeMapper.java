package com.efounder.JEnterprise.mapper.datacenter;

import com.efounder.JEnterprise.model.datacenter.ESEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ESEmployeeMapper {
	
	public List<ESEmployee> loadList();
	
	public List<ESEmployee> findByPage(@Param("keywords") String keywords);
	
	public ESEmployee findByZybh(@Param("f_zgbh") String f_zgbh);
	
	public boolean insert(ESEmployee vo);
	
	public boolean update(ESEmployee vo);
	
	public boolean delByZgbh(String f_zgbh);
}
