package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 分项数据mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESSubitemDataMapper extends BaseMapper<String, BESSubitemData>{

	List<Map<String, Object>> getSubitemData(BESSubitemData besSubitemData);

	List<BESSubitemData> getSubitemDataTab(@Param("keywords") String keywords);

	List<BESSubitemData> getSubitemDataList(BESSubitemData subitemdata);

	BESSubitemData queryGradeExists(BESSubitemData besSubitemData);

	public void saveGradeData(BESSubitemData besSubitemData);

	public void updateGrade(BESSubitemData besSubitemData);

	Boolean updateGradeData(BESSubitemData besSubitemData);

	/**
	 *
	 * @param subitemdata
	 * @return
	 * 获取分项用能趋势统计数据
	 */
	List<BESSubitemData> getQstjSubitemData(BESSubitemData besSubitemData);

	List<BESSubitemData> getSubentryData(BESSubitemData besSubitemData);

	List<BESSubitemData> getSubitemDataByCjsjAndType(@Param("time") String time,@Param("type") String type);

}