package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModulePointType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模块点类型
 *
 * @author suhang datetime 2018-7-10
 *
 */
@Mapper
public interface BESModulePointTypeMapper {

	/**
	 * 添加模块点类型
	 *
	 * @param besModulePointType
	 * @return
	 */
	boolean insModulepointType(BESModulePointType besModulePointType);

	/**
	 * 删除模块点类型
	 *
	 * @param fId
	 * @return
	 */
	boolean delModulepointType(String fId);

	/**
	 * 删除之前查询是否存在关联约束：模块点与设备树节点存在关联关系
	 * @param fId
	 * @return
	 */
	Integer queryRelationType(String fId);

	/**
	 * 更新模块点类型
	 *
	 * @param besModulePointType
	 * @return
	 */
	boolean updfModulepointType(BESModulePointType besModulePointType);

	/**
	 * 查询单行模块点类型
	 *
	 * @param fId
	 * @return
	 */
	BESModulePointType selectModulepointType(String fId);

	/**
	 * 分页模块点类型
	 *
	 * @param keywords
	 * @return
	 */
	List<BESModulePointType> selModulepointTypepage(@Param("keywords") String keywords);

}
