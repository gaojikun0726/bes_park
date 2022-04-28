package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesDepartmentConf;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleSjqxManage;
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
public interface BesDepartmentConfMapper extends BaseMapper<String ,BESSubitemConf> {
	
    /*int deleteByPrimaryKey(String fFhbh);

    int insert(BesDepartmentConf record);

    int insertSelective(BesDepartmentConf record);

    BesDepartmentConf selectByPrimaryKey(String fFhbh);

    int updateByPrimaryKeySelective(BesDepartmentConf record);

    int updateByPrimaryKey(BesDepartmentConf record);*/
    
    /**
     * 查询分户配置数据
     * @param fDepId
     * @return
     */
	List<Map<String,Object>> getDepartmentList(@Param("fDepId") String fDepId);

	List<Map<String,Object>> getDepartmentAllList(@Param("fDepId") String fDepId,@Param("zzjginfo") String zzjginfo);

    /**
     * 查询全部分户配置数据
     * @return
     */
	List<BesDepartmentConf> findAllDepartment();

//	/**
//	 * 分户配置树
//	 * @param fYqbh
//	 * @return
//	 */
//	List<BesDepartmentConf> loadAll(
//			@Param(value="fYqbh")String fYqbh,
//			@Param(value="fNybh")String fNybh ,
//			@Param(value="groupbh") List<String> groupbh,
//			@Param(value="userId") String userId
//	);
	
//	/**
//	 * 查找子类
//	 * @param fFhbh
//	 * @return
//	 */
//	List<BesDepartmentConf> Department_chlildtreegrid(@Param("fhbh") String fhbh,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	
	/**
	 * 添加分户
	 * @param list
	 * @return
	 */
//	boolean add_Department(BesDepartmentConf besDepartmentConf);
	boolean add_Department(@Param("list") List<Map<String, Object>> list);
	
	/**
	 * 查询分户
	 * @param newBh
	 * @return
	 */
	List<BesDepartmentConf> queryDepartment(String newBh);
	/**
	 * 删除分户
	 * @param id
	 * @return
	 */
	boolean del_Department(@Param("id") String id);

	List<Map<String,Object>> queryDepartmentCount(@Param("id") String id);

	void updaeDepartmentCount (@Param("list")List<Map<String,Object>> list,@Param("dou") Double dou);

	void updaeDepartmentCount1 (@Param("list")List<Map<String,Object>> list,@Param("dou") Double dou);

	boolean deleteDepartment(@Param("F_DEP_ID") String F_DEP_ID);
	
	/**
	 * 编辑分户
	 */
	boolean edit_Department(BesDepartmentConf besDepartmentConf);

//	/**
//	 * 获取能源类型 sunzhiyuan
//	 * @param f_yqbh
//	 * @return
//	 */
//	List<Map<String,Object>> getenergylist(@Param("f_yqbh")String f_yqbh);
//
//	/**
//	 * 分户配置树孙志远
//	 * @param fNybh1
//	 * @param fYqbh1
//	 * @return
//	 */
//	List<BesDepartmentConf> loadAll1(@Param(value="fNybh1")String fNybh1,@Param(value="fYqbh1")String fYqbh1);
//
//	/**
//	 * 查询能源编号(去重)
//	 * @return
//	 * 孙志远
//	 */
//	List<Map<String,Object>> removeYqbhCf();
//
//	/**
//	 * 查询能源编号(去重)
//	 * @return
//	 * 孙志远
//	 */
//	List<Map<String,Object>> removeNybhCf(@Param(value="fYqbh1")String fYqbh1);
//
//	List<Map<String,Object>> selectAlleneryType();
//
//	/**
//	 * 查询能源编号(去重)
//	 * @return
//	 * 孙志远
//	 */
//	List<Map<String,Object>> selectAllPark(@Param(value="nybh")String nybh);

//	/**
//	 * 三表联查出的数据
//	 * @return
//	 */
//	List<Map<String,Object>>  selectAll();
//
//	/**
//	 * 根据园区编号查询园区名称
//	 * @param yqbh
//	 * @return
//	 */
//	String	selectYqmc(@Param(value="yqbh")String yqbh);
//
//	/**
//	 * 获取树组织机构
//	 * @param fYqbh
//	 * @param fNybh
//	 * @return
//	 */
//    List<Map<String, Object>> getHomePageTreeData(@Param("fYqbh")String fYqbh,@Param("fNybh") String fNybh,
//												  @Param("groupbh") List<String> groupbh,@Param("userId") String userId);
//
//	/**
//	 * 获取分项数据
//	 * @return
//	 */
//	List<Map<String,Object>> getSubitemTreeData();
//
//	/**
//	 *
//	 * @Description: 查询当前分户编号的信息
//	 *
//	 * @auther: wanghongjie
//	 * @date: 18:05 2020/11/18
//	 * @param: [fZlbh]
//	 * @return: java.util.Map<java.lang.String,java.lang.Object>
//	 *
//	 */
//	Map<String, Object> selectf_pfhbh(BesDepartmentBranchRlgl fZlbh);
//
//	/**
//	 *
//	 * @Description: 查询父节点的信息
//	 *
//	 * @auther: wanghongjie
//	 * @date: 18:04 2020/11/18
//	 * @param: [pfhbh]
//	 * @return: java.util.Map<java.lang.String,java.lang.Object>
//	 *
//	 */
//	Map<String, Object> selectf_pfhbhs(String pfhbh);
//
//	/**
//	 *
//	 * @Description: 分户中移除支路 往下 级联删除,查询父分户编号为当前分户编号的所有信息
//	 *
//	 * @auther: wanghongjie
//	 * @date: 18:02 2020/11/18
//	 * @param: [besHBR]
//	 * @return: java.util.Map<java.lang.String,java.lang.Object>
//	 *
//	 */
//	List<Map<String, Object>> selectf_pfhbh_down(BesDepartmentBranchRlgl besHBR);
//
//	//删除分户的时候,先判断分户中是否关联的支路,如果关联,则将分户支路配置表中的相应的数据一起删除
//	List<Map<String, Object>> selectBes_Department_branch_rlglList(String fFhbh);
//
//	//删除分户的时候,先判断分户中是否关联的支路,如果关联,则将分户支路配置表中的相应的数据一起删除
//	Boolean deleteDepartment_branch_rlgl(String fFhbh);
//
//	//判断支路名称是否重复
//	List<BesDepartmentConf> selectffhmc(String getfFhmc);
//
//	/**
//	 *
//	 * @Description: 获取用户列表
//	 *
//	 * @auther: wanghongjie
//	 * @date: 10:32 2020/12/28
//	 * @param: []
//	 * @return: java.util.List<com.efounder.JEnterprise.model.usercenter.ESUser>
//	 *
//	 */
//	List<ESUser> getuserNameList(@Param("groupbh") List<String> groupbh);
//
//	/**
//	 *
//	 * @Description: 获取组织机构列表
//	 *
//	 * @auther: wanghongjie
//	 * @date: 14:04 2021/1/5
//	 * @param: []
//	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//	 *
//	 */
//	List<Map<String, Object>> getZZJGList(@Param("groupbh") List<String> groupbh);
//
//	/**
//	 *
//	 * @Description: 获取建筑信息
//	 *
//	 * @auther: wanghongjie
//	 * @date: 14:26 2021/5/25
//	 * @param: []
//	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//	 *
//	 */
//    List<Map<String, Object>> getBuilding();

	List<Map<String,Object>> getSelectBranchInfoList(@Param("bmbh") String bmbh,@Param("keywords") String keywords);

	List<Map<String,Object>> getSelectBranchInfoChoose(@Param("bmbh") String bmbh);

	List<Map<String,Object>> getSelectBranchInfoById(@Param("fZlbh") String fZlbh);

	List<Map<String,Object>> getSelectBranchCount(@Param("F_DEP_ID") String F_DEP_ID,@Param("fZlbh") String fZlbh);

	List<Map<String,Object>> getSelectElectricityMeterInfoList(@Param("bmbh") String bmbh,@Param("keywords") String keywords);

	List<Map<String,Object>> getSelectElectricityMeterInfoChoose(@Param("bmbh") String bmbh);

	List<Map<String,Object>> getSelectElectricityMeterInfoById(@Param("fZlbh") String fZlbh);

	List<Map<String,Object>> getSelectElectricityMeterCount(@Param("F_DEP_ID") String F_DEP_ID,@Param("fZlbh") String fZlbh);

	void updateBranchCoefficient(@Param("F_ZLXS") Double F_ZLXS,@Param("F_ZLBH") String F_ZLBH);

	void updateAmmeterCoefficient(@Param("F_DBXS") Double F_DBXS,@Param("F_DBSYS_NAME") String F_DBSYS_NAME);

	Map<String,Object> getDepPeopleNumber(@Param("F_DEP_ID") String F_DEP_ID);

	boolean setDepPeopleNumber(@Param("F_DEP_ID") String F_DEP_ID,@Param("number") int number);

	boolean setDepPeopleNumberUpdate(@Param("F_DEP_ID") String F_DEP_ID,@Param("number") int number);
}