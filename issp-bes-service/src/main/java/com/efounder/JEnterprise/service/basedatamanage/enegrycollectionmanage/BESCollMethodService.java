package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCollMethod;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
/**
 * 采集方案接口
 * @author LvSihan
 *
 */
public interface BESCollMethodService {

	/**
	 * 查询采集方案列表
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject getCollMethodList(String keywords);
	/**
	 * 通过园区和编号查询采集方案
	 * @param fZzjgbh
	 * @return
	 */
	public ISSPReturnObject getCMbyZzjgbh(String fyqbh,String fnybh);
	/**
	 * 新增采集方案
	 * @param besCollMethod
	 * @return
	 */
	public ISSPReturnObject add_CollM(BESCollMethod besCollMethod);
	/**
	 * 编辑采集方案
	 * @param besCollMethod
	 * @return
	 */
	public ISSPReturnObject edit_CollM(BESCollMethod besCollMethod);
	/**
	 * 删除采集方案
	 * @param besCollMethod
	 * @return
	 */
	public ISSPReturnObject del_CollM(BESCollMethod besCollMethod);
	
	public ISSPReturnObject getCMbyCjfabh(String fCjfabh);
	/**
	 * 查询未包含的能耗参数
	 * @param besECR
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject loadNoIncludeCollM(BESElectric_Coll_Rlgl besECR, String keywords);
	/**
	 * 查询已包含的能耗参数
	 * @param besECR
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject loadIncludeCollM(BESElectric_Coll_Rlgl besECR, String keywords);
	/**
	 * 新增
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject CollMethod_insertElectricP(BESElectric_Coll_Rlgl besECR);
	/**
	 * 删除
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject CollMethod_deleteElectricP(BESElectric_Coll_Rlgl besECR);
	/**
	 * 移除全部
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject CollMethod_leftmoveAll(BESElectric_Coll_Rlgl besECR);
	/**
	 * 添加全部
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject CollMethod_rightmoveAll(BESElectric_Coll_Rlgl besECR);
	/**
	 * 更新是否作为统计参数的值
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject update_inclu_fStatisticalParam(BESElectric_Coll_Rlgl besECR);

	/**
	 * 更新采集方案包含采集参数中 是否变比
	 * @param besECR
	 * @return
	 */
	public ISSPReturnObject update_inclu_fIsRate(BESElectric_Coll_Rlgl besECR);

	/**
	 *生成园区能源类型树
	 * @return
	 */
	public ISSPReturnObject getParkEnergytree(String yqbh);

}
