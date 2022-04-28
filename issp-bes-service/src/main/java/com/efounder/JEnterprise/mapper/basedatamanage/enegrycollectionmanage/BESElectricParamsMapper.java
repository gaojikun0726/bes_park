package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 采集参数mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESElectricParamsMapper extends BaseMapper<String, BESElectricParams>{

	/**
	 * 获取所有采集参数数据
	 * @return
	 */
    List<BESElectricParams> loadAll();

    BESElectricParams selectByPrimaryKey(String fDnbh);

	List<BESElectricParams> getElectricPList(@Param("keywords") String keywords,@Param("fNybh") String fNybh);

	List<BESElectricParams> getListByfNybh(String fNybh);

	boolean add_ElectricP(BESElectricParams besElectricParams);

	BESElectricParams getElectricP(String fDnbh);

	boolean del_ElectricP(String fDnbh);

	boolean edit_ElectricP(BESElectricParams besElectricParams);

	public Map<String, Object> selectInfoByEnectricId(@Param("enectric_id") String enectric_id);

    List<Map<String, String>> getfbmgz();
}