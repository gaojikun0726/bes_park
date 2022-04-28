package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESStrategyMapper extends BaseMapper<String, BESStrategy> {

    /**
     * 获取场景配置树机构
     * @return
     */
    List<BESStrategy> getStrategyTree(@Param("userCode") String userCode);

    Map<String,Object> queryTableParam(String id);

    boolean updateStrategyName(@Param("id") String id, @Param("name") String name);

    boolean deleteStrategy(@Param("id") String id);

    /*List<Map<String,String>> loadBranchTree(@Param(value="fZzjgbh")String fZzjgbh,@Param(value="fnybh")String fnybh,
                                     @Param("groupbh") List<String> groupbh,@Param("userId") String userId);*/

    int insertStrategy(BESStrategy besStrategy);

    boolean updateStrategy(BESStrategy besStrategy);

    void deleteBranch(@Param("fStrategyId") String fStrategyId);

    void insertBranch(@Param("fStrategyId") String fStrategyId,@Param("f_zlbh") String f_zlbh,@Param("f_level") String f_level,@Param("f_zlmc") String f_zlmc);

    void deleteDepartment(@Param("fStrategyId") String fStrategyId);

    void insertDepartment(@Param("fStrategyId") String fStrategyId,@Param("f_department_id") String f_department_id,@Param("f_level") String f_level,@Param("f_zlmc") String f_zlmc);

    List<String> queryStrategyBranch(@Param("id") String id);

    List<String> queryStrategyDepartment(@Param("id") String id);

    int insertSysJobSyncInfo(SysJob sysJob);

    String queryStrategyJobId(@Param("f_id") String f_id);

    void deleteJobInfo(@Param("jobId") String jobId);

    void updateStrategyJobId(@Param("fJobId") String fJobId,@Param("f_id") String f_id);

    Map<String,Object> queryStrategyInfo(@Param("strategyId") String strategyId);

    List<Map<String,Object>> queryBranchData(@Param("strategyId") String strategyId,@Param("nowStart") String nowStart,@Param("nowEnd") String nowEnd,@Param("lastStart") String lastStart,@Param("lastEnd") String lastEnd);

    List<Map<String,Object>> queryChildBranchData(@Param("strategyId") String strategyId,@Param("nowStart") String nowStart,@Param("nowEnd") String nowEnd,@Param("lastStart") String lastStart,@Param("lastEnd") String lastEnd);

}
