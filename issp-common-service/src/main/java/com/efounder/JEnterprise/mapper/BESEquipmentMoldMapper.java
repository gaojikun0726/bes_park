package com.efounder.JEnterprise.mapper;

import com.efounder.JEnterprise.model.BESEquipmentMold;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BESEquipmentMoldMapper extends BaseMapper<String, BESEquipmentMold> {
	
	/**
	 * 获取设备类型信息
	 * @return
	 */
	List<BESEquipmentMold> getAllEquipmentMold();
	
	/**根据ID
	 * 获取设备类型信息
	 * @return
	 */
	List<BESEquipmentMold> getIdEquipmentMold(@Param("ftype")String ftype);
	
	/**
	 * 加载设备类型树
	 * @return
	 */
	List<BESEquipmentMold> loadAll();
	
	/**
	 * 查询子类
	 * @author tianjiangwei
	 * @param f_zbh
	 * @return
	 */
	List<BESEquipmentMold> findChildByZtype(@Param("f_type") String f_type);
	
	/**
	 * 模糊查询所有设备类型及子类
	 * @param f_type
	 * @return
	 */
	List<BESEquipmentMold> findAllLikeTp(@Param("f_type") String f_type);
	/**
	 * 根据条件查询设备类型
	 * @param f_sbmc
	 * @return
	 */
	List<BESEquipmentMold> getEqTypeList(@Param("f_sbmc") String f_sbmc);
	
	/**
     * 根据设备类型查询设备信息
     *
     * 
     */
    public BESEquipmentMold findeqTypeByBh(String f_type);
    
    /**
     * 
     * 新增设备类型信息
     */
    boolean add_eqType(BESEquipmentMold besEquipmentMold);
    
    /**
     * 更新设备类型
     * @param 
     * @return
     */
    boolean upeqType(BESEquipmentMold besEquipmentMold);

    /**
     * 根据设备类型编号查询设备名称
     */
    List<BESEquipmentMold> getEquipmentMoldById(@Param("f_type") String f_type);
	/**
	 *
	 * @Description: 查询bes_txt_end表里面有没有当前id关联的txt文本
	 *
	 * @auther: wanghongjie
	 * @date: 17:22 2020/4/29
	 * @param: [f_id]
	 * @return: java.lang.String
	 *
	 */
    String selectProgrammInfo(Integer f_id);
    
}