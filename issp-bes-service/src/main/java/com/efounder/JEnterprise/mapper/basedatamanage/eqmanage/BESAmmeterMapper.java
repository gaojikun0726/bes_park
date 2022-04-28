package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 电表基础信息mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESAmmeterMapper extends BaseMapper<String ,BESAmmeter>{

	  //查找一条电表
		BESAmmeter queryAmmeter(@Param("keywords") String keywords);
	  //查找一条电表
		BESAmmeter queryAmmeterBySbId(@Param("fSbid") String fSbid);
		int deleteByPrimaryKey(@Param("fSysName") String fSysName);
		//查找指定电表
		List<BESAmmeter> queryAmmeterList(@Param("keywords") String keywords);
		void updateByPrimaryKeySelective(BESAmmeter besAmmeter);
		void updateByPrimaryKeySelectiveZt(BESAmmeter besAmmeter);
		void updateBySbid(BESAmmeter besAmmeter);
		int insertSelective(BESAmmeter besAmmeter);


	/**
	 * 查询全部或新增的仪表数据列表
	 * @param map 上次请求时间（可选）
	 * @return
	 */
	List<Map<String,Object>> getAmmeterList(Map<String,Object> map);

	/**
	 * 保存一条电表删除的记录
	 * @param map
	 * @return
	 */
	Integer insertAmmeterDelete(Map<String, Object> map);

	/**
	 * 获取所有电表数据
	 * @return
	 */
	List<BESAmmeter> loadAll();
}