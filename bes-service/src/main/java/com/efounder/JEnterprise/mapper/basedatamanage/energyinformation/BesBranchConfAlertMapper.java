package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesBranchConfAlert;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


/**
 * 支路报警mapper
 * @author suhang
 * @datetime 2018-7-31
 *
 */
@Mapper
public interface BesBranchConfAlertMapper {
    int insert(BesBranchConfAlert record);

    int insertSelective(BesBranchConfAlert record);
    
    /**
     * 查询支路报警数据
     * @param besBranchConfAlert
     * @return
     */
    
	List<BesBranchConfAlert> getAlertList(BesBranchConfAlert besBranchConfAlert);
    
	/**
	 * 查找子类支路报警信息
	 * @param map
	 * @return
	 */
	List<BesBranchConfAlert> branchalert_chlildtreegrid(Map<String, String> map);

	/**
	 * 添加支路报警信息
	 * @param besBranchConfAlert
	 * @return
	 */
	boolean insbranchalert(BesBranchConfAlert besBranchConfAlert);

	/**
	 * 删除支路报警信息
	 * @param fId
	 * @return
	 */
	boolean del_branchalert(String fId);

	/**
	 * 删除父表parameter数据
	 * @param fId
	 * @return
	 */
	boolean del_branchalert_parameter(String fId);
	/**
	 * 查询单行支路报警信息
	 *
	 * @param fId
	 * @return
	 */
	BesBranchConfAlert selbranchalert(String fId);

	List<Map<String,Object>> selectConfparameter(String fId);

	/**
	 * 更新支路报警信息
	 *
	 * @param besBranchConfAlert
	 * @return
	 */
	boolean updbranchalert(BesBranchConfAlert besBranchConfAlert);

	/**
	 * 插入数据
	 * @param map
	 * @return
	 */
	 Boolean insertAlertParameter(Map<String,Object> map);

	 List<Object> selectFalertname(String name);

	 List<Object> selectFalertnameById(String fId);

	 List<Map<String,Object>> getAlertTypeInfo();
//	 Boolean del_branchalert_parameterByName(String name);
	 String  getAlertTypeName(BigInteger typeId);
}