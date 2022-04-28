package com.efounder.JEnterprise.service.datacenter;

import com.efounder.JEnterprise.model.datacenter.ESDepartment;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/** 
 * 类名称：ESDepartmentService
 * 类描述：部门定义service接口
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年5月31日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface ESDepartmentService extends ESBaseService{
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
    List<ESUser> findESDepartmentByPage(@Param("keywords") String keywords);
	/**
	 * 查询全部部门信息
	 * @author sunhao
	 * @param 
	 * @return
	 */
	public List<ESDepartment> findAllESDepartment();
	/**
	 * 根据编号查询部门信息
	 * @author sunhao
	 * @param f_bmbh
	 * @return
	 */
	public ESDepartment findESDepartmentByBmbh(@Param("f_bmbh") String f_bmbh);
	/**
	 * 根据组织机构编号查询部门信息
	 * @author sunhao
	 * @param f_bmbh
	 * @return
	 */
	public List<ESDepartment> findESDepartmentByZzbh(@Param("f_zzjgbh") String f_zzjgbh);
	/**
	 * 根据名称查询
	 * @author sunhao
	 * @param f_bmmc
	 * @return
	 */
	public List<ESDepartment> findChildByBmmc(@Param("f_bmmc") String f_bmmc);
	/**
	 * 模糊查询
	 * @author sunhao
	 * @param keywords
	 * 
	 * @return
	 */
	public List<ESDepartment> findESDepartmentByKeywords(@Param("keywords") String keywords);
	/**
	 * @author sunhao
	 * 加载部门信息
	 * @return
	 */
	public List<ESDepartment> loadAll();
	/**
	 * 增加部门信息
	 * @return
	 */
	public boolean addESDepartment(ESDepartment esdEpartment);
	/**
	 * 删除部门信息
	 * @param f_bmbh
	 * @return
	 */
	public boolean deleteByBmbh(ESDepartment esdEpartment);
	/**
	 * 修改部门信息
	 * @param 
	 * @return
	 */
	public boolean upDateESDepartment(ESDepartment esdEpartment);
	/**
	 * 查询部门编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxBmbh();
}
