package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModuleType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 模块类型mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESModuleTypeMapper {
	/**
	 * 删除模块型号
	 * @param fModuleType
	 * @return
	 */
	boolean delModuleType(BESModuleType moduletype);
	
	/**
	 * 新增模块型号
	 * @param record
	 * @return
	 */
	boolean addModuleType(BESModuleType moduletype);

	/**
	 * 更新模块型号
	 * @param record
	 * @return
	 */
    boolean upModuleType(BESModuleType moduletype);

    /**
     * 根据模块型号查询
     * @param fModuleType
     * @return
     */
    BESModuleType selectByPrimaryKey(@Param("fModuleType") String fModuleType);
    
    /**
     * 分页查询
     * @param keywords
     * @return
     */
	List<BESModuleType> findmtByPage(@Param("keywords") String keywords);

	List<BESModuleType> selectList(BESModuleType moduletype);

	List<BESModuleType> selectPointTypeCl(BESModuleType moduletype);

	String queryMaxId();

	/**
	 *
	 * @Description: 查找当前id所对应的模块名称
	 *
	 * @auther: wanghongjie
	 * @date: 15:10 2020/8/12
	 * @param: [id]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    Map<String, Object> selectModuleMap(String id);
}