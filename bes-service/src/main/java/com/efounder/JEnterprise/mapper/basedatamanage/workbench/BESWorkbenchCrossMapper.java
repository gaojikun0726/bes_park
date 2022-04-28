package com.efounder.JEnterprise.mapper.basedatamanage.workbench;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.BesWorkbench.BESWorkbenchCrossType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * workbench数据库接口
 * @author liwenjie
 * */
@Mapper
public interface BESWorkbenchCrossMapper extends BaseMapper<String, BESWorkbenchCrossType>{
	
    List<BESWorkbenchCrossType> getWorkbenchCrossList(@Param(value = "keywords")String keywords);
    
    List<BESWorkbenchCrossType> getWorkbenchTableList(@Param(value = "f_gztzzjg_id")String f_gztzzjg_id);
	
	boolean add_WorkbenchCross(BESWorkbenchCrossType besdatecentertype);

	BESWorkbenchCrossType getWorkbench(String bh);

	boolean del_WorkbenchCross(String id);

	boolean edit_WorkbenchCross(BESWorkbenchCrossType besdatecentertype);
}
