package com.efounder.JEnterprise.service.basedatamanage.energyinformation;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import org.springframework.dao.DataAccessException;

/**
 * 支路配置接口
 * @author LvSihan
 *
 */
public interface BESBranchConfService {

	/**
	 * 查询全部，tabulator填充数据
	 * @param f_zlmc
	 * @return
	 */
	public ISSPReturnObject getBranchConfList(BESBranchConf besBranchConf);

	/**
	 * 加载支路树
	 * @param fZzjgbh 
	 * @return
	 */
	public ISSPReturnObject getTree(String fnybh);
	/**
	 * 查询支路子节点
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject findChildByZlbh(String fZlbh);
	/**
	 * 添加支路信息
	 * @param besBranchConf
	 * @return
	 */
	public ISSPReturnObject add_branch(BESBranchConf besBranchConf);
	/**
	 * 删除支路信息
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject del_branch(String fZlbh) throws DataAccessException;
	/**
	 * 查询单条支路信息
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject queryBranch(String fZlbh);
	/**
	 * 编辑支路信息
	 * @param besBranchConf
	 * @return
	 */
	public ISSPReturnObject edit_branch(BESBranchConf besBranchConf);
	/**
	 * 查询支路未包含的仪表信息
	 * @return
	 */
	public ISSPReturnObject loadNoIncludeAmt(BESBranch_Ammeter_Rlgl besBAR,String keywords);
	/**
	 * 支路中添加电表
	 * @param besBAR
	 * @return
	 */
	public ISSPReturnObject branchconf_insertAmmeter(BESBranch_Ammeter_Rlgl besBAR);
	/**
	 * 查询支路包含的电表
	 * @param besBAR
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject loadIncludeAmt(BESBranch_Ammeter_Rlgl besBAR, String keywords);
	/**
	 * 移除支路中的电表
	 * @param besBAR
	 * @return
	 */
	public ISSPReturnObject branchconf_deleteAmmeter(BESBranch_Ammeter_Rlgl besBAR);
	/**
	 * 支路中添加全部电表
	 * @param besBAR
	 * @return
	 */
	public ISSPReturnObject branchconf_rightmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords);
	/**
	 * 支路中移除全部电表
	 * @param besBAR
	 * @return
	 */
	public ISSPReturnObject branchconf_leftmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords);

	public ISSPReturnObject update_inclu_fOperator(String fZlbh, String fSysName, String fOperator);

	/**
	 * 获取单个节点下的所有子节点
	 * @param fZlbh
	 * @return
	 */
	public ISSPReturnObject loadOrganization(String fZlbh);

	/**
	 *
	 * @Description: 显示是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 15:35 2020/11/19
	 * @param: [besHBR]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject loadShowCascade(BESBranch_Ammeter_Rlgl besHBR);

	/**
	 *
	 * @Description: 修改是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 16:56 2020/11/19
	 * @param: [fJl, fZlbh]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject changef_jl(String fJl, String fZlbh);

	/**
	 *
	 * @Description: 批量整理支路树
	 *
	 * @auther: wanghongjie
	 * @date: 9:12 2022/4/23
	 * @param: []
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject tidyTree() throws Exception;

    /**
     *
     * @Description: 获取所有的电表
     *
     * @auther: wanghongjie
     * @date: 9:54 2022/4/25
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject loadAllAmmeter();

    /**
     *
     * @Description: 根据支路编号查询所有的电表
     *
     * @auther: wanghongjie
     * @date: 10:16 2022/4/25
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
	 * @param besBAR
	 */
    ISSPReturnObject loadAmmeterByBranchId(BESBranch_Ammeter_Rlgl besBAR);
}
