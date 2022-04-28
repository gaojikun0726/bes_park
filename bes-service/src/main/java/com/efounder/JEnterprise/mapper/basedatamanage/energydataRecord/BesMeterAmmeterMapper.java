package com.efounder.JEnterprise.mapper.basedatamanage.energydataRecord;

import com.efounder.JEnterprise.model.basedatamanage.energydataRecord.BesMeterAmmeter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface BesMeterAmmeterMapper {
	
	List<BesMeterAmmeter> getMeterTypeList();
	
	List<BesMeterAmmeter> TreeTable(String treeId);
	
	//新增 静态电表信息
	boolean add_MeterInformation(BesMeterAmmeter dto);
	
	//新增 静态电表数据信息
	boolean add_MeterInfo(BesMeterAmmeter dto);
	
	//修改 静态电表信息
	boolean edit_MeterInformation(BesMeterAmmeter dto);
	
	//修改 静态电表数据信息
	boolean edit_MeterInfo(BesMeterAmmeter dto);
	
	//删除静态电表
	boolean del_MeterInformation(String fGuid);
	//删除静态电表数据
	boolean del_MeterInfo(String fGuid);
	//删除静态电表数据 单个
	boolean del_MeterInfo_one(String fJtguid);
	
	//修改回显 
	BesMeterAmmeter editSelect_MeterInformation(String fGuid);
	
	//修改回显 
	BesMeterAmmeter editSelect_MeterInfo(String fJtguid);
	
	//电表类型 
	List<Map<String, Object>> selectMeterType();


}