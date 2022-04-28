package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/** 
 * 类名称：BESParkMapper
 * 类描述：园区表mapper接口
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年7月3日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface BESParkMapper extends BaseMapper<String, BESPark> {
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
    List<BESPark> findParkByPage(@Param("keywords") String keywords,@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	/**
	 * 查询全部信息
	 * @author sunhao
	 * @param 
	 * @return
	 */
	public List<BESPark> findAllPark(@Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	/**
	 * 根据编号查询信息
	 * @author sunhao
	 * @param f_yqbh
	 * @return
	 */
	public BESPark findParkByYqbh(@Param("f_yqbh") String f_yqbh);
	/**
	 * 根据名称查询
	 * @author sunhao
	 * @param f_yqmc
	 * @return
	 */
	public List<BESPark> findParkByYqmc(@Param("f_yqmc") String f_yqmc);
	/**
	 * 模糊查询
	 * @author sunhao
	 * @param keywords
	 * 
	 * @return
	 */
	public List<BESPark> findParkByKeywords(@Param("keywords") String keywords);
	/**
	 * 根据编号查询信息
	 * @author yujieying
	 * @param f_yqbh
	 * @return
	 */
	//public List<BESPark> findParkinfoListByYqbh(@Param("f_yqbh") String f_yqbh);
	public List<BESPark> findParkinfoListByYqbh();
	
	/**
	 * @author sunhao
	 * 加载信息
	 * @return
	 */
	public List<BESPark> loadAll();
	/**
	 * 增加信息
	 * @return
	 */
	public boolean addPark(BESPark park);
	/**
	 * 删除信息
	 * @param f_yqbh
	 * @return
	 */
	public boolean deleteByYqbh(BESPark park);
	/**
	 * 修改信息
	 * @param 
	 * @return
	 */
	public boolean upDatePark(BESPark park);
	/**
	 * 查询编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxYqbh();

	//判断分项配置中是否在此园区下新增分项
	List<Map<String, Object>> subOptionDeploy(String yqbh);

	//判断分户配置中是否在此园区下新增分户
	List<Map<String, Object>> householdDeploy(String yqbh);

	//判断支路配置中是否在此园区下新增支路
	List<Map<String, Object>> branchRoadDeploy(String yqbh);

	//判断采集方案中是否在此园区下配置采集方案
//	List<Map<String, Object>> collectionPlanDeploy(String yqbh);

	//判断设备配置中是否在此园区下配置设备
	List<Map<String, Object>> deviceConfigurationDeploy(String yqbh);

	//判断能耗配置中是否在此园区下配置
    List<Map<String, Object>> energyTypeDeploy(String yqbh);
}
