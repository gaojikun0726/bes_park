package com.efounder.JEnterprise.service.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesBranchConfAlert;

import java.util.List;
import java.util.Map;

/**
 * 支路报警service
 * @author suhang
 * @datetime 2018-07-31
 * 
 */
public interface BesBranchConfAlertService {
	
	/**
	 * 查询
	 * @param besBranchConfAlert
	 * @return
	 */
	public ISSPReturnObject getAlertList(BesBranchConfAlert besBranchConfAlert);
	
	/**
	 * 加载园区下拉框
	 * @return
	 */
	ISSPReturnObject Park_tree();

	/**
	 * 通过园区编号和能源编号获取支路树
	 * @param fYqbh fNybh
	 * @return
	 */
	ISSPReturnObject getbranchTree(String fYqbh , String fNybh);
	ISSPReturnObject getbranchalert_ammeter(String fYqbh , String fNybh);
	ISSPReturnObject getbranchalert_ammeter_rlgl(String parentId);

	/**
	 * 通过支路编号获取支路报警信息
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject branchalert_chlildtreegrid(String fZlbh, String yqbh, String nybh, String searchInfo);

	/**
	 * 添加支路报警信息
	 * @param besBranchConfAlert
	 * @return
	 */
	public ISSPReturnObject insbranchalert(BesBranchConfAlert besBranchConfAlert);

	/**
	 * 删除支路报警信息
	 * @param fId
	 * @return
	 */
	public ISSPReturnObject del_branchalert(String fId);

	/**
	 * 查询支路报警信息
	 *
	 * @param fId
	 * @return
	 */
	ISSPReturnObject selbranchalert(String fId);

	/**
	 * 更新支路报警信息
	 *
	 * @param besBranchConfAlert
	 * @return
	 */
	ISSPReturnObject updbranchalert(BesBranchConfAlert besBranchConfAlert);

	/**
	 * 加载能源下拉框
	 * @return
	 */
	ISSPReturnObject getAllEnergyType();

	/**
	 * 获取单个节点下的所有子节点
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject loadOrganization(String fZlbh);

//	public ISSPReturnObject getDnbhbyParams(String param);

	/**
	 * 循环插入数据
	 * @param map
	 * @return
	 */
	public  ISSPReturnObject insertAlertParameter(Map<String,Object> map);

	public List<Object> selectFalertname(String name);

	public ISSPReturnObject selectAlertNameRepeat(String alertName);

	ISSPReturnObject getAlertTypeInfo();
}
