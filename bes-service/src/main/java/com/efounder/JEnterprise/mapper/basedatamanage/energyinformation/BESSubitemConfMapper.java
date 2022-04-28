package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 分项配置mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESSubitemConfMapper extends BaseMapper<String ,BESSubitemConf>{
    int deleteByPrimaryKey(String fFxbh);

    int insert(BESSubitemConf record);

    int insertSelective(BESSubitemConf record);

    BESSubitemConf selectByPrimaryKey(String fFxbh);

    int updateByPrimaryKeySelective(BESSubitemConf record);

    int updateByPrimaryKey(BESSubitemConf record);
    /**
     * 查询分项配置数据
     * @param besSubitemConf
     * @return
     */
	List<BESSubitemConf> getSubitemConfList(BESSubitemConf besSubitemConf);

	/**
     * 根据父类编号查询分项配置数据
     * @param besSubitemConf
     * @return
     */
	List<BESSubitemConf> getSubitemConfGridList(@Param("fNybh") String fNybh,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);


	/**
	 * 根据能源编号和与前去编号查询分项配置数据
	 * @param besSubitemConf
	 * @return
	 */
	List<BESSubitemConf> getSubitemConfByYqAndNy(@Param("fNybh") String fNybh,@Param("fYqbh") String fYqbh,
												 @Param("fBudingId") String fBudingId,
												 @Param("groupbh") List<String> groupbh,
												 @Param("userId") String userId);

	/**
     * 查询分项配置数据
     * @param besSubitemConf
     * @return
     */
	List<BESSubitemConf> getSubitemConfData(BESSubitemConf besSubitemConf);
	
	/**
	 * 分项配置树
	 * @param fZzjgbh 
	 * @return
	 */
	List<BESSubitemConf> loadAll(@Param(value="fZzjgbh")String fZzjgbh,@Param("groupbh") List<String> groupbh,@Param("fFxbh") String fFxbh);
	/**
	 * 查找子类
	 * @param fFxbh
	 * @return
	 */
	List<BESSubitemConf> subitem_chlildtreegrid(@Param("fxbh") String fxbh,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	
	
	/**
	 * 添加分项
	 * @param besSubitemConf
	 * @return
	 */
	boolean addSubitem(BESSubitemConf besSubitemConf);
	
	

	List<BESSubitemConf> querySubitem(String fGuid);
	/**
	 * 删除分项
	 * @param fFxbh
	 * @return
	 */
	boolean del_subitem(String fGuid);
	/**
	 * 编辑分项
	 */
	boolean edit_subitem(BESSubitemConf besSubitemConf);

	List<BESSubitemConf> querySubitemByFxbh(String fFxbh);

	/**
	 * 获取分项全部数据
	 * @return
	 */
    List<BESSubitemConf> findAll();

	/**
	 * 根据建筑能耗代码查询分项配置信息
	 * @return
	 */
	List<BESSubitemConf> findBySubitemCode(String subitemCode);

    /**
     *
     * @Description: 查询当前分项是否级联
     *
     * @auther: wanghongjie
     * @date: 9:46 2020/11/21
     * @param: [fFxbh]
     * @return: java.lang.String
     *
     */
    String selectf_jl(String fFxbh);

    /**
     *
     * @Description: 修改是否级联-分项
     *
     * @auther: wanghongjie
     * @date: 9:56 2020/11/21
     * @param: [getfJl, getfFxbh]
     * @return: java.lang.Boolean
     *
     */
    Boolean changef_jl(@Param("fJl") String fJl,@Param("fFxbh") String fFxbh);

    /**
     *
     * @Description: 查询当前分项编号的信息
     *
     * @auther: wanghongjie
     * @date: 10:24 2020/11/21
     * @param: [besSBR]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectf_pfxbh(BESSubitem_Branch_Rlgl besSBR);

    /**
     *
     * @Description: 查询父节点的信息
     *
     * @auther: wanghongjie
     * @date: 10:26 2020/11/21
     * @param: [pfxbh]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
	Map<String, Object> selectf_pfxbhs(String pfxbh);

	/**
	 *
	 * @Description:查找子节点的信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:26 2020/11/21
	 * @param: [besSBR]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectf_pfxbh_down(BESSubitem_Branch_Rlgl besSBR);

	/**
	 *
	 * @Description: 查询当前父分项的信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:42 2020/11/21
	 * @param: [f_pfxbh]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf>
	 *
	 */
	List<BESSubitemConf> querySubitemhold(String f_pfxbh);

	/**
	 *
	 * @Description:分项中移除支路 往下 级联删除,查询父分项编号为当前分项编号的所有信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:51 2020/11/21
	 * @param: [besSBR]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectf_pfXbh_down(BESSubitem_Branch_Rlgl besSBR);

	/**
	 *
	 * @Description: 查询分项支路关系表中,当前分项是否关联支路
	 *
	 * @auther: wanghongjie
	 * @date: 9:03 2021/5/22
	 * @param: [getfFxbh]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> select_bes_subitem_branch_rlgl_data(String fFxbh);

	/**
	 *
	 * @Description:判断分项配置表中当前建筑下,当前园区,能源类型是否有数据
	 *
	 * @auther: wanghongjie
	 * @date: 10:18 2021/5/26
	 * @param: [fNybh, fYqbh, buildingbh]
	 * @return: java.lang.Boolean
	 *
	 */
	List<BESSubitemConf> selectSubitemTableData(@Param("fNybh") String fNybh,@Param("fYqbh")  String fYqbh,@Param("buildingbh")  String buildingbh);

    /**
     *
     * @Description: 批量删除
     *
     * @auther: wanghongjie
     * @date: 10:29 2021/5/26
     * @param: [fNybh, fYqbh, buildingbh]
     * @return: java.lang.Boolean
     *
     */
	Boolean delectSubitemData(@Param("fNybh") String fNybh,@Param("fYqbh")  String fYqbh,@Param("buildingbh")  String buildingbh);
}