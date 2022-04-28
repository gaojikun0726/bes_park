package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdSubitemData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesHouseholdSubitemDataMapper extends BaseMapper<String, BesHouseholdSubitemData>{
    int deleteByPrimaryKey(String fId);

    int insert(BesHouseholdSubitemData record);

    int insertSelective(BesHouseholdSubitemData record);

    BesHouseholdSubitemData selectByPrimaryKey(String fId);

    int updateByPrimaryKeySelective(BesHouseholdSubitemData record);

    int updateByPrimaryKey(BesHouseholdSubitemData record);
    
    /**
     * 获取分户分项同比环比数据
     * @param besHouseholdSubitemData
     * @return
     */
	List<BesHouseholdSubitemData> searchHouseholdSubitemData(BesHouseholdSubitemData besHouseholdSubitemData);

    /**
     * 查询分户分项数据
     * @param besHouseholdSubitemData
     * @return
     */
	List<BesHouseholdSubitemData> selectHouseholdSubitemData(BesHouseholdSubitemData besHouseholdSubitemData);

	List<Map<String, String>> queryHouseholdGradeExists(@Param("fFhbh") String fFhbh,@Param("fCjsj") String fCjsj,
			@Param("fType") String fType,@Param("fFxbh") String fFxbh);


	public void saveHouseholdGradeData(BesHouseholdSubitemData besHouseholdSubitemData);

	public void updateHouseholdGrade(BesHouseholdSubitemData besHouseholdSubitemData);

	Boolean updateHouseholdGradeData(BesHouseholdSubitemData besHouseholdSubitemData);
}