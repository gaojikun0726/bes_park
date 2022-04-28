package com.efounder.JEnterprise.service.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesDepartmentConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BesDepartmentConfService {
	
	/**
	 * 查询
	 * @param f_zzjgbh
	 * @return
	 */
	public ISSPReturnObject getDepartmentList(String f_zzjgbh);

	public ISSPReturnObject getDepartmentAllList(String f_zzjgbh,String zzjginfo );

//	/**
//	 * 生成分项配置树
//	 * @param fYqbh
//	 * @return
//	 */
//	public ISSPReturnObject loadAll(String fYqbh,String fNybh);
//
//	/**
//	 * 加载组织机构树
//	 * @return
//	 */
//	ISSPReturnObject Park_tree();
//
//	/**
//	 * 查找子类
//	 * @param fFhbh
//	 * @return
//	 */
//	public ISSPReturnObject Department_chlildtreegrid(String fFhbh);
	
	/**
	 * 添加分户
	 * @param list
	 * @return
	 */
//	public ISSPReturnObject add_Department (BesDepartmentConf besDepartmentConf);
	public ISSPReturnObject add_Department(List<Map<String, Object>> list);
	
	/**
	 * 删除分户
	 * @param id
	 * @return
	 */
	public ISSPReturnObject del_Department(String id) throws Exception;
	
	/**
	 * 查询需要编辑的分户配置信息
	 * @param fFhbh
	 * @return
	 */
	public ISSPReturnObject queryDepartment(String fFhbh);
	
	/**
	 * 编辑分户配置信息
	 * @param besDepartmentConf
	 * @return
	 */
	public ISSPReturnObject edit_Department(BesDepartmentConf besDepartmentConf);
	
//	/**
//	 * 分户未包含的支路信息
//	 * @param besHBR
//	 * @param besBranchConf
//	 * @return
//	 */
//	public ISSPReturnObject loadNoBrc(BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf);
//
//	/**
//	 * 分户包含的支路信息
//	 * @param besHBR
//	 * @param besBranchConf
//	 * @return
//	 */
//	public ISSPReturnObject loadInBrc(BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf);
//
//	/**
//	 * 分户中添加一条支路
//	 * @param besHBR
//	 * @return
//	 */
//	public ISSPReturnObject besDepartment_instBranch(BesDepartmentBranchRlgl besHBR);
//
//	/**
//	 * 分户中删除一条支路
//	 * @param besSBR
//	 * @return
//	 */
//	public ISSPReturnObject besDepartment_delBranch(BesDepartmentBranchRlgl besHBR);
//
//	/**
//	 * 分户中移除全部支路
//	 * @param besHBR
//	 * @return
//	 */
//	public ISSPReturnObject besDepartment_leftmoveAll(BesDepartmentBranchRlgl besHBR,BESBranchConf besBranchConf);
//
//	/**
//	 * 分户中添加全部支路
//	 * @param besHBR besBranchConf
//	 * @return
//	 */
//	public ISSPReturnObject besDepartment_rightmoveAll(BesDepartmentBranchRlgl besHBR,BESBranchConf besBranchConf);
//
//	/**
//	 * 生成能源类型
//	 * @param
//	 * @return
//	 */
//	ISSPReturnObject getAllEnergyType();
//
//	/**
//	 *
//	 * add  by liuzhen 20181018 根据能源编号生成分项配置表树
//	 * @param yqbh  园区编号
//	 * @param fNybh 能源编号
//	 * @return
//	 */
//	public ISSPReturnObject Department_treegrid(String yqbh,String fNybh);
//
//	/**
//	 *
//	 * add  页面的能源类型 孙志远 根据能源编号生成分项配置表树
//	 * @param fNybh1  园区编号
//	 * @param fYqbh1 能源编号
//	 * @return
//	 */
//	public ISSPReturnObject Department_treegrid1(String fNybh1,String fYqbh1);



	/**
	 * 查询全部分户配置数据
	 * @return
	 */
	List<BesDepartmentConf> findAll();

//	/**
//	 * 获取所有能源类型
//	 * @param f_yqbh
//	 * @return
//	 */
//	public List<Map<String,Object>> getenrrgylist(String f_yqbh);
//
//	/**
//	 * 查询园区编号去重
//	 * @return
//	 */
//   public ISSPReturnObject removeYqbhCf();
//
//	/**
//	 * 查询能源编号去重
//	 * @return
//	 */
//	public ISSPReturnObject removeNybhCf(String fYqbh1);
//
//	/**
//	 * 查询所有能源类型
//	 * @return
//	 */
//	public  ISSPReturnObject selectAlleneryType();
//
//	/**
//	 * 根据能源类型查询园区编号
//	 * @return
//	 */
//	public  ISSPReturnObject selectAllPark(String nybh);
//
//	/**
//	 * 三表联查
//	 * @return
//	 */
//	public  ISSPReturnObject selectAll();
//
//	/**
//	 * 根据园区编号查询园区名称
//	 * @return
//	 */
//	public  String selectYqmc(String yqbh);
//
//	/**
//	 * 获取主页树数据
//	 * @return
//	 */
//    ISSPReturnObject getHomePageTreeData(String fYqbh, String fNybh);
//
//
//    ISSPReturnObject getSubitemTreeData();
//
//    /**
//     *
//     * @Description: 显示是否级联
//     *
//     * @auther: wanghongjie
//     * @date: 9:18 2020/11/18
//     * @param: [besHBR]
//     * @return: com.core.common.ISSPReturnObject
//     *
//     */
//    ISSPReturnObject loadShowCascade(BesDepartmentBranchRlgl besHBR);
//
//    /**
//     *
//     * @Description: 修改是否级联
//     *
//     * @auther: wanghongjie
//     * @date: 9:50 2020/11/18
//     * @param: [fJl, fFhbh]
//     * @return: com.core.common.ISSPReturnObject
//     *
//     */
//    ISSPReturnObject changef_jl(String fJl, String fFhbh);
//
//	/**
//	 *
//	 * @Description: 获取用户列表
//	 *
//	 * @auther: wanghongjie
//	 * @date: 10:31 2020/12/28
//	 * @param: []
//	 * @return: java.util.List<com.efounder.JEnterprise.model.usercenter.ESUser>
//	 *
//	 */
//	List<ESUser> getuserNameList();
//
//	/**
//	 *
//	 * @Description: 获取组织机构列表
//	 *
//	 * @auther: wanghongjie
//	 * @date: 14:03 2021/1/5
//	 * @param: []
//	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//	 *
//	 */
//	List<Map<String, Object>> getZZJGList();
//
//    /**
//     *
//     * @Description: 获取建筑信息
//     *
//     * @auther: wanghongjie
//     * @date: 14:26 2021/5/25
//     * @param: []
//     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//     *
//     */
//	List<Map<String, Object>> getBuilding();

	List<Map<String, Object>> getSelectBranchInfoList(String bmbh,String keywords);

	List<Map<String, Object>> getSelectBranchInfoChoose(String bmbh);

	List<Map<String, Object>> getSelectBranchInfoById(String fZlbh);

	List<Map<String, Object>> getSelectBranchCount(String F_DEP_ID,String fZlbh);

	List<Map<String, Object>> getSelectElectricityMeterInfoList(String bmbh,String keywords);

	List<Map<String, Object>> getSelectElectricityMeterInfoChoose(String bmbh);

	List<Map<String, Object>> getSelectElectricityMeterInfoById(String fZlbh);

	List<Map<String, Object>> getSelectElectricityMeterCount(String F_DEP_ID,String fZlbh);

	Map<String, Object> getDepPeopleNumber(String F_DEP_ID);

	ISSPReturnObject  setDepPeopleNumber(String F_DEP_ID,int number);
}
