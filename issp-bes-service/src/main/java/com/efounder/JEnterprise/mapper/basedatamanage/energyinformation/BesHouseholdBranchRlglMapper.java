package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分户支路关系mapper接口
 * @author suhang
 */
@Mapper
public interface BesHouseholdBranchRlglMapper extends BaseMapper<String ,BESSubitemConf>
{
	
    
    /**
     * 查询该分户下包含的支路信息
     * @param getfFhbh
     * @return
     */
	List<BesHouseholdBranchRlgl> queryHOUList(String fFhbh);
    /**
     * 查询所有分户支路信息
     * @return
     */
	List<BesHouseholdBranchRlgl> queryAll();
	/**
	 * 添加支路
	 * @param beshous
	 * @return
	 */
	boolean beshousehold_instBranch(BesHouseholdBranchRlgl besHBR);
	/**
	 * 删除支路
	 * @param beshous
	 * @return
	 */
	boolean beshousehold_delBranch(BesHouseholdBranchRlgl besHBR);
	/**
	 * 分项中删除全部支路
	 * @param beshous
	 * @return
	 */
	boolean beshousehold_leftmoveAll(@Param("fhbh") String fhbh,@Param("keywords") String keywords);

	/**
	 *
	 * @Description: 查询是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:52 2020/11/18
	 * @param: [fFhbh]
	 * @return: java.lang.String
	 *
	 */
    String selectf_jl(String fFhbh);

    /**
     *
     * @Description: 修改是否级联
     *
     * @auther: wanghongjie
     * @date: 9:51 2020/11/18
     * @param: [fJl, fFhbh]
     * @return: java.lang.Boolean
     *
     */
    Boolean changef_jl(@Param("fJl") String fJl,@Param("fFhbh") String fFhbh);

    Map<String, Object> selectZlbh(BesHouseholdBranchRlgl fZlbh);

	boolean beshousehold_delBranch_jl_up(BesHouseholdBranchRlgl fZlbh);

    Map<String, Object> selectBesHouseholdBranchRlglZlbh(@Param("f_fhbh") String f_fhbh,@Param("fZlbh") String fZlbh);

	Boolean delFhAndZl(@Param("f_fhbh") String f_fhbh,@Param("fZlbh") String fZlbh);

	/**
	 *
	 * @Description: 查询当前分户所属的全部支路信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:56 2020/11/19
	 * @param: [getfZlbh]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl>
	 *
	 */
    List<BesHouseholdBranchRlgl> selectBranchList(@Param("fFhbh") String fFhbh,@Param("keywords") String keywords);
}