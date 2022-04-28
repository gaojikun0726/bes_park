package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 设备树节点类型mapper
 * @author suhang
 * @datetime 2018-7-11
 *
 */
@Mapper
public interface BesSbtreeNodetypeMapper {
	
	boolean delBesSbtreeNodetype(String f_node_type);

	boolean insBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType);

	BESSbTreeNodeType selectBesSbtreeNodetype(String f_node_type);

	boolean updateBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType);
	
	List<BESSbTreeNodeType> selBesSbtreeNodetypepage(@Param("keywords") String keywords);

	int getCount(@Param("f_node_type") String f_node_type);


	/**
	 * 获取全部设备树节点类型数据
	 * @return
	 */
    List<BESSbTreeNodeType> findAll();
}
