package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchAmmeterLog;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 支路与电表关系mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESBranch_Ammeter_RlglMapper extends BaseMapper<String ,BESBranch_Ammeter_Rlgl>{

    /**
     * 查询'支路与电表关系表'中支路BARList
     * @param fZlbh
     * @return
     */
	List<BESBranch_Ammeter_Rlgl> queryBARList(String fZlbh);

	//新增支路包含电表
	boolean branchconf_insertAmmeter(BESBranch_Ammeter_Rlgl besBAR);
	//删除支路包含电表
	boolean branchconf_deleteAmmeter(BESBranch_Ammeter_Rlgl besBAR);
	//移除全部支路包含电表
	boolean branchconf_leftmoveAll(BESBranch_Ammeter_Rlgl besBAR);

	//支路电表操作记录
	Boolean insertBranchAmmeterLog(BESBranchAmmeterLog besBranchAmmeterLog);
	

	boolean update_inclu_fOperator(@Param(value = "fZlbh") String fZlbh,@Param(value = "fSysName") String fSysName,@Param(value = "fOperator") String fOperator);
	//根据分户编号查询关联的支路
    Map<String, Object> selectZlbh(BESBranch_Ammeter_Rlgl besBAR);
	//支路中移除电表 往上 级联删除
	boolean beshousehold_delBranch_jl_up(BESBranch_Ammeter_Rlgl branchRlgl);
	//查询支路电表关系表中是否存在当前支路关联的电表
	Map<String, Object> selectbesBranch_Ammeter_RlglDbbh(@Param("f_zlbh") String f_zlbh,@Param("fDbsysName") String fDbsysName);
	//删除当前支路电表的点位信息
	Boolean delZlAndDb(@Param("f_zlbh") String f_zlbh,@Param("fDbsysName") String fDbsysName);
}