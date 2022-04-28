package com.efounder.JEnterprise.mapper.applicationmanage;

import com.efounder.JEnterprise.model.applicationmanage.ESHomeModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ESHomeModuleMapper {
	
	public List<ESHomeModule> loadList();
	
	public boolean insert(ESHomeModule vo);
	
	public boolean update(ESHomeModule vo);
	
	public boolean delByGuid(String guid);
	
	public List<ESHomeModule> findByPage(@Param("keywords") String keywords);
	
	public ESHomeModule findByGuid (@Param("guid") String guid);
}
