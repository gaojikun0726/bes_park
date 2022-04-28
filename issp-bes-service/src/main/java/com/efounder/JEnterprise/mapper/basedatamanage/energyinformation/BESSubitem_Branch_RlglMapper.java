package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分项支路关系mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESSubitem_Branch_RlglMapper extends BaseMapper<BESSubitem_Branch_Rlgl,String>{
    int deleteByPrimaryKey(BESSubitem_Branch_Rlgl key);

    int insert(BESSubitem_Branch_Rlgl record);

    int insertSelective(BESSubitem_Branch_Rlgl record);

    BESSubitem_Branch_Rlgl selectByPrimaryKey(BESSubitem_Branch_Rlgl key);

    int updateByPrimaryKeySelective(BESSubitem_Branch_Rlgl record);

    int updateByPrimaryKey(BESSubitem_Branch_Rlgl record);

    /**
     * 查询该分项下包含的支路信息
     * @param getfFxbh
     * @return
     */
	List<BESSubitem_Branch_Rlgl> querySBRList(String fFxbh);

	/**
	 * 获取所有的分项支路关系信息
	 * @return
	 */
	List<BESSubitem_Branch_Rlgl> queryAll();

	/**
	 * 添加支路
	 * @param besSBR
	 * @return
	 */
	boolean subitemconf_insertBranch(BESSubitem_Branch_Rlgl besSBR);
	/**
	 * 删除支路
	 * @param besSBR
	 * @return
	 */
	boolean subitemconf_deleteBranch(BESSubitem_Branch_Rlgl besSBR);
	/**
	 * 分项中删除全部支路
	 * @param besSBR
	 * @return
	 */
	boolean subitemconf_leftmoveAll(BESSubitem_Branch_Rlgl besSBR);

	/**
	 *
	 * @Description: 根据分项编号查询关联的支路
	 *
	 * @auther: wanghongjie
	 * @date: 10:28 2020/11/21
	 * @param: [besSBR]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    Map<String, Object> selectZlbh(BESSubitem_Branch_Rlgl besSBR);

    /**
     *
     * @Description: 分项 往上级联删除
     *
     * @auther: wanghongjie
     * @date: 11:16 2020/11/21
     * @param: [branch_rlgl]
     * @return: boolean
     *
     */
	boolean besSubitem_delBranch_jl_up(BESSubitem_Branch_Rlgl branch_rlgl);

	/**
	 *
	 * @Description: 查询分项支路关系表中是否存在当前分项关联的支路
	 *
	 * @auther: wanghongjie
	 * @date: 11:28 2020/11/21
	 * @param: [f_fxbh, getfZlbh]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectsubitemconfBranchRlglZlbh(@Param("f_fxbh") String f_fxbh,@Param("fZlbh") String fZlbh);

	/**
	 *
	 * @Description: 删除当前分项支路的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:30 2020/11/21
	 * @param: [f_fxbh, getfZlbh]
	 * @return: java.lang.Boolean
	 *
	 */
	Boolean delFxAndZl(@Param("f_fxbh") String f_fxbh,@Param("fZlbh") String fZlbh);

	/**
	 *
	 * @Description: 查询当前分项所属的全部支路信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:31 2020/11/21
	 * @param: [getfFxbh, keywords]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl>
	 *
	 */
	List<BESSubitem_Branch_Rlgl> selectBranchList(@Param("fFxbh") String fFxbh,@Param("keywords") String keywords);
}