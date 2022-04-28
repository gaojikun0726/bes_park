package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESElectric_params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 支路配置mappper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESBranchConfMapper extends BaseMapper<String ,BESBranchConf>{

	List<BESBranchConf> getBranchConfList(BESBranchConf besBranchConf);

	BESBranchConf getBranchConf(String fZlbh);
	
	List<BESBranchConf> loadAll(@Param(value="fnybh")String fnybh, @Param(value="fZzjgbh")List<String> fZzjgbh, @Param(value="userId") String userId);

	List<BESBranch_Ammeter_Rlgl> loadAllBranchAmmeterRlgl();

	List<BESBranchConf> loadAllYqbh(@Param(value="fYqbh")String fYqbh ,@Param(value="fNybh")String fNybh,
									@Param(value="groupbh")List<String> groupbh,@Param(value="userId")String userId);

	List<BESBranchConf> loadOrganization(String fZlbh);

	List<BESBranchConf> findChildByZlbh(String fPzlbh);

	List<BESBranchConf> querySelfChildByZlbh(@Param("fZlbh") String fZlbh,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	//新增支路信息
	boolean addBranch(BESBranchConf besBranchConf);
	//查找支路信息
	List<BESBranchConf> queryBranch(String newBh);
	//删除支路信息
	boolean del_branch(@Param("fZlbh") String fZlbh);
	//编辑支路信息
	boolean edit_branch(BESBranchConf besBranchConf);
	//删除支路电表操作记录
	boolean delBranchAmmeterLog(@Param("fZlbh") String fZlbh);
	/**
	 * 查询'支路与电表关系表'中支路BARList
	 * @param fZlbh
	 * @return
	 */
	List<BESBranch_Ammeter_Rlgl> queryBARList(String SubId);
	List<BESElectric_params> queryBARList_rlgl(String parentId);

	//查找一条电表
	Map<String,Object> queryAmmeter(@Param("keywords") String keywords);
	//显示是否级联
    String selectf_jl(String fZlbh);
	//修改是否级联
    Boolean changef_jl(@Param("fJl") String fJl,@Param("fZlbh") String fZlbh);
	//查询当前支路编号的信息
	Map<String, Object> selectf_pzlbh(BESBranch_Ammeter_Rlgl besBAR);
	//查询父节点的信息
	Map<String, Object> selectf_pzlbhs(String pzlbh);
	//支路中移除电表 往下 级联删除,查询父支路编号为当前支路编号的所有信息
	List<Map<String, Object>> selectf_pzlbh_down(BESBranch_Ammeter_Rlgl besBAR);

	//判断支路名称是否重复
	List<BESBranchConf> selectfzlmc(String getfZlmc);

	//删除前判断分户中是否添加此支路
	List<Map<String, Object>> selectHouseholdDeploy(String fZlbh);

	//删除前判断分项中是否添加此支路
	List<Map<String, Object>> selectSubOptionDeploy(String fZlbh);

	//删除前判断支路报警配置中是否关联此支路
	List<Map<String, Object>> selectBranchAlarmDeploy(String fZlbh);

	/**
	 *
	 * @Description: 删除前判断支路与电表关系表中是否关联电表
	 *
	 * @auther: wanghongjie
	 * @date: 9:33 2021/5/22
	 * @param: [fZlbh]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
    List<Map<String, Object>> selectBranchAmmeterDeploy(String fZlbh);

    /**
     *
     * @Description: 修改支路树结构表
     *
     * @auther: wanghongjie
     * @date: 10:26 2022/4/23
     * @param: [nodeId1, nodeId]
     * @return: void
     *
     */
    Boolean updateBranch_conf(@Param("nodeId1") String nodeId1, @Param("nodeId")  String nodeId, @Param("nodeTreeId")  String nodeTreeId);

    /**
     *
     * @Description: 修改支路电表表
     *
     * @auther: wanghongjie
     * @date: 10:26 2022/4/23
     * @param: [nodeId1]
     * @return: void
     *
     */
	Boolean updateBranch_ammeter_rlgl(@Param("nodeId1") String nodeId1,@Param("nodeTreeId")  String nodeTreeId);

	/**
	 *
	 * @Description: 修改支路数据表
	 *
	 * @auther: wanghongjie
	 * @date: 10:27 2022/4/23
	 * @param: [nodeId1]
	 * @return: void
	 *
	 */
	void updateBranch_data(@Param("nodeId1") String nodeId1,@Param("nodeTreeId")  String nodeTreeId);
}