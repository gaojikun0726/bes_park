package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/** 
 * 类名称：BESEquipmentBrandMapper
 * 类描述：品牌字典mapper接口
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年6月26日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface BESEquipmentBrandMapper extends BaseMapper<String, BESEquipmentBrand> {
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
    List<BESEquipmentBrand> findEquipmentBrandByPage(@Param("keywords") String keywords);
	/**
	 * 查询全部信息
	 * @author sunhao
	 * @param 
	 * @return
	 */
	public List<BESEquipmentBrand> findAllEquipmentBrand();
	/**
	 * 根据编号查询信息
	 * @author sunhao
	 * @param f_brandbh
	 * @return
	 */
	public BESEquipmentBrand findEquipmentBrandByBrandbh(@Param("id") String id);
	/**
	 * 根据名称查询
	 * @author sunhao
	 * @param f_brandmc
	 * @return
	 */
	public List<BESEquipmentBrand> findEquipmentBrandByBrandmc(@Param("f_brandmc") String f_brandmc);
	/**
	 * 模糊查询
	 * @author sunhao
	 * @param keywords
	 * 
	 * @return
	 */
	public List<BESEquipmentBrand> findEquipmentBrandByKeywords(@Param("keywords") String keywords);
	/**
	 * @author sunhao
	 * 加载信息
	 * @return
	 */
	public List<BESEquipmentBrand> loadAll();
	/**
	 * 增加信息
	 * @return
	 */
	public boolean addEquipmentBrand(BESEquipmentBrand equipmentBrand);
	/**
	 * 删除信息
	 * @param f_bmbh
	 * @return
	 */
	public boolean deleteByBrandbh(BESEquipmentBrand equipmentBrand);
	/**
	 * 修改信息
	 * @param 
	 * @return
	 */
	public boolean upDateEquipmentBrand(BESEquipmentBrand equipmentBrand);
	/**
	 * 查询编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxBrandbh();
    /**
     * 获取设备品牌列表
     * @return
     */
	List<BESEquipmentBrand> getEquipmentBrandList();
	/**
	 * 获取品牌名称
	 * @return
	 */
	List<BESEquipmentBrand> getBrand();
}
