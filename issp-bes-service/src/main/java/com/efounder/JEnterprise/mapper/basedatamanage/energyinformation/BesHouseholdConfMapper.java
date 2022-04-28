package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdConf;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分户配置mappper接口
 * @author suhang
 *
 */
@Mapper
public interface BesHouseholdConfMapper extends BaseMapper<String ,BESSubitemConf> {
	
    /*int deleteByPrimaryKey(String fFhbh);

    int insert(BesHouseholdConf record);

    int insertSelective(BesHouseholdConf record);

    BesHouseholdConf selectByPrimaryKey(String fFhbh);

    int updateByPrimaryKeySelective(BesHouseholdConf record);

    int updateByPrimaryKey(BesHouseholdConf record);*/
    
    /**
     * 查询分户配置数据
     * @param besHouseholdConf
     * @return
     */
	List<BesHouseholdConf> getHouseholdList(BesHouseholdConf besHouseholdConf);

    /**
     * 查询全部分户配置数据
     * @return
     */
	List<BesHouseholdConf> findAll();

	/**
	 * 分户配置树
	 * @param fYqbh 
	 * @return
	 */
	List<BesHouseholdConf> loadAll(
			@Param(value="fYqbh")String fYqbh,
			@Param(value="fNybh")String fNybh ,
			@Param(value="groupbh") List<String> groupbh,
			@Param(value="userId") String userId
	);
	
	/**
	 * 查找子类
	 * @param fFhbh
	 * @return
	 */
	List<BesHouseholdConf> household_chlildtreegrid(@Param("fhbh") String fhbh,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	
	/**
	 * 添加分户
	 * @param besHouseholdConf
	 * @return
	 */
	boolean add_household(BesHouseholdConf besHouseholdConf);
	
	/**
	 * 查询分户
	 * @param newBh
	 * @return
	 */
	List<BesHouseholdConf> queryhousehold(String newBh);
	/**
	 * 删除分户
	 * @param fFhbh
	 * @return
	 */
	boolean del_household(String fFhbh);
	
	/**
	 * 编辑分户
	 */
	boolean edit_household(BesHouseholdConf besHouseholdConf);

	/**
	 * 获取能源类型 sunzhiyuan
	 * @param f_yqbh
	 * @return
	 */
	List<Map<String,Object>> getenergylist(@Param("f_yqbh")String f_yqbh);

	/**
	 * 分户配置树孙志远
	 * @param fNybh1
	 * @param fYqbh1
	 * @return
	 */
	List<BesHouseholdConf> loadAll1(@Param(value="fNybh1")String fNybh1,@Param(value="fYqbh1")String fYqbh1);

	/**
	 * 查询能源编号(去重)
	 * @return
	 * 孙志远
	 */
	List<Map<String,Object>> removeYqbhCf();

	/**
	 * 查询能源编号(去重)
	 * @return
	 * 孙志远
	 */
	List<Map<String,Object>> removeNybhCf(@Param(value="fYqbh1")String fYqbh1);

	List<Map<String,Object>> selectAlleneryType();

	/**
	 * 查询能源编号(去重)
	 * @return
	 * 孙志远
	 */
	List<Map<String,Object>> selectAllPark(@Param(value="nybh")String nybh);

	/**
	 * 三表联查出的数据
	 * @return
	 */
	List<Map<String,Object>>  selectAll();

	/**
	 * 根据园区编号查询园区名称
	 * @param yqbh
	 * @return
	 */
	String	selectYqmc(@Param(value="yqbh")String yqbh);

	/**
	 * 获取树组织机构
	 * @param fYqbh
	 * @param fNybh
	 * @return
	 */
    List<Map<String, Object>> getHomePageTreeData(@Param("fYqbh")String fYqbh,@Param("fNybh") String fNybh,
												  @Param("groupbh") List<String> groupbh,@Param("userId") String userId);

	/**
	 * 获取分项数据
	 * @return
	 */
	List<Map<String,Object>> getSubitemTreeData();

	/**
	 *
	 * @Description: 查询当前分户编号的信息
	 *
	 * @auther: wanghongjie
	 * @date: 18:05 2020/11/18
	 * @param: [fZlbh]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectf_pfhbh(BesHouseholdBranchRlgl fZlbh);

	/**
	 *
	 * @Description: 查询父节点的信息
	 *
	 * @auther: wanghongjie
	 * @date: 18:04 2020/11/18
	 * @param: [pfhbh]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectf_pfhbhs(String pfhbh);

	/**
	 *
	 * @Description: 分户中移除支路 往下 级联删除,查询父分户编号为当前分户编号的所有信息
	 *
	 * @auther: wanghongjie
	 * @date: 18:02 2020/11/18
	 * @param: [besHBR]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	List<Map<String, Object>> selectf_pfhbh_down(BesHouseholdBranchRlgl besHBR);

	//删除分户的时候,先判断分户中是否关联的支路,如果关联,则将分户支路配置表中的相应的数据一起删除
	List<Map<String, Object>> selectBes_household_branch_rlglList(String fFhbh);

	//删除分户的时候,先判断分户中是否关联的支路,如果关联,则将分户支路配置表中的相应的数据一起删除
	Boolean deleteHousehold_branch_rlgl(String fFhbh);

	//判断支路名称是否重复
	List<BesHouseholdConf> selectffhmc(String getfFhmc);

	/**
	 *
	 * @Description: 获取用户列表
	 *
	 * @auther: wanghongjie
	 * @date: 10:32 2020/12/28
	 * @param: []
	 * @return: java.util.List<com.efounder.JEnterprise.model.usercenter.ESUser>
	 *
	 */
	List<ESUser> getuserNameList(@Param("groupbh") List<String> groupbh);

	/**
	 *
	 * @Description: 获取组织机构列表
	 *
	 * @auther: wanghongjie
	 * @date: 14:04 2021/1/5
	 * @param: []
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> getZZJGList(@Param("groupbh") List<String> groupbh);
	
	/**
	 *
	 * @Description: 获取建筑信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:26 2021/5/25
	 * @param: []
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
    List<Map<String, Object>> getBuilding();
}