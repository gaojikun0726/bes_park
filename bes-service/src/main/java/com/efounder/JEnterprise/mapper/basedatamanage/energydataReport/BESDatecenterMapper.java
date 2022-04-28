package com.efounder.JEnterprise.mapper.basedatamanage.energydataReport;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据中心mapper接口
 * @author 杨超
 * @modify ycc
 */
@Mapper
public interface BESDatecenterMapper extends BaseMapper<String, BESDatecenterType>{

	List<BESDatecenterType> getDatecenterList(@Param(value = "keywords")String keywords);

	/**
	 * 获取全部数据中心基本信息
	 * @return
	 */
	List<BESDatecenterType> getDateCenterList();

	boolean add_Datecenter(BESDatecenterType besdatecentertype);

	BESDatecenterType getDatecenter(String bh);

	boolean del_Datecenter(String id);

	boolean edit_Datecenter(BESDatecenterType besdatecentertype);
	
	List<Map<String, Object>> getDatecenterTypeList(@Param(value = "keywords")String keywords);
	/**
	 * 批量上传
	 * @param list
	 * @return
	 */
	boolean addDatecenterList(List<BESDatecenterType> list);

}
