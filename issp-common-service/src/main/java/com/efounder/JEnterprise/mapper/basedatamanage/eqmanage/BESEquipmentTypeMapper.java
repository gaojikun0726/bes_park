package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备类型mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESEquipmentTypeMapper {
    /**
     * 增加设备信息
     * @param equipmenttype
     * @return
     */
	boolean addEquipmentType(BESEquipmentType equipmenttype);
	
    /**
     * 删除设备信息
     * @param equipmenttype
     * @return
     */
    boolean delEquipmentType(BESEquipmentType equipmenttype);
    
    /**
     * 根据ID获取设备信息
     * @param id
     * @return
     */
    public BESEquipmentType findEmtById(@Param("f_type") String f_type);
    
    /**
     * 更新设备型号信息
     * @param equipmenttype
     * @return
     */
    boolean upEmt(BESEquipmentType equipmenttype);
	
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<BESEquipmentType> findemTByPage(@Param("f_sblxbh") String f_sblxbh,@Param("keywords") String keywords);
	
	/**
	 * 
	 * @param f_sblxbh
	 * @return
	 */
	List<BESEquipmentType> findSblxPage(@Param("f_sblxbh") String f_sblxbh,@Param("treeId") String treeId);
	
	/**
	 * 设备类型维护树
	 * @return
	 */
	List<Map<String,Object>> loadAll();
	
	/**
	 * 通过品牌id获取设备类型列表
	 * @param f_brandid 
	 * @return
	 */
	List<BESEquipmentType> getEquipmentTypeList(String f_brandid);
	
	/**
	 * 通过设备类型编号获取设备类型列表对应信息
	 * @param f_sblxbh
	 * @return
	 */
	List<BESEquipmentType> seleqtypechildren (@Param(value = "f_sblxbh") String f_sblxbh);
	
	/**
	 * 通过设备类型编号获取设备类型编号
	 * @param f_type
	 * @return
	 */
	List<BESEquipmentType> getbh(@Param("f_type") String f_type);
	//获取数据库最大ID
		String getMaxId();
	//树table
	List<BESEquipmentType> tree_table(String treeId);

	List<BESEquipmentType> findemTByPage(String keywords);
}