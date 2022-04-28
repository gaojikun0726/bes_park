package com.efounder.JEnterprise.service.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;

import java.util.List;

/**
 * 分项配置接口
 * @author LvSihan
 *
 */
public interface BESSubitemConfService {

	public ISSPReturnObject getSubitemConfList(BESSubitemConf besSubitemConf);

	/**
	 * 生成分项配置树
	 * @param fZzjgbh 
	 * @return
	 */
	public ISSPReturnObject subitem_tree(String fZzjgbh);
	/**
	 * 查找子类
	 * @param fFxbh
	 * @return
	 */
	public ISSPReturnObject subitem_chlildtreegrid(String fFxbh);
	/**
	 * 添加分项
	 * @param besSubitemConf
	 * @return
	 */
	public ISSPReturnObject add_subitem(BESSubitemConf besSubitemConf);
	/**
	 * 删除分项
	 * @param fFxbh
	 * @return
	 */
	public ISSPReturnObject del_subitem(String fGuid) throws Exception;
	/**
	 * 查询需要编辑的分项配置信息
	 * @param fFxbh
	 * @return
	 */
	public ISSPReturnObject querySubitem(String fGuid);
	/**
	 * 编辑分项配置信息
	 * @param besSubitemConf
	 * @return
	 */
	public ISSPReturnObject edit_subitem(BESSubitemConf besSubitemConf);
	/**
	 * 分项未包含的支路信息
	 * @param besSBR
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject loadNoIncludeBrc(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf);
	/**
	 * 分项包含的支路信息
	 * @param besSBR
	 * @param keywords
	 * @return
	 */
	public ISSPReturnObject loadIncludeBrc(BESSubitem_Branch_Rlgl besSBR, BESBranchConf besBranchConf);
	/**
	 * 分项中添加一条支路
	 * @param besSBR
	 * @return
	 */
	public ISSPReturnObject subitemconf_insertBranch(BESSubitem_Branch_Rlgl besSBR);
	/**
	 * 分项中删除一条支路
	 * @param besSBR
	 * @return
	 */
	public ISSPReturnObject subitemconf_deleteBranch(BESSubitem_Branch_Rlgl besSBR);
	/**
	 * 分项中添加全部支路
	 * @param besSBR
	 * @return
	 */
	public ISSPReturnObject subitemconf_rightmoveAll(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf);
	/**
	 * 分项中移除全部支路
	 * @param besSBR
	 * @return
	 */
	public ISSPReturnObject subitemconf_leftmoveAll(BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf);
	
	/**
	 * 
	 * add  by liuzhen 20181018 根据能源编号生成分项配置表树
	 * @return
	 */
	public ISSPReturnObject subitem_treegrid(String fNybh);
	/**
	 *
	 * add  by liuzhen 20181018 根据能源编号和园区编号生成分项配置表树
	 * @return
	 */
	public ISSPReturnObject subitemConfTree(String fNybh,String fYqbh,String fBudingId);

	/**
	 * 获取分项全部数据
	 * @return
	 */
	List<BESSubitemConf> findAll();

    ISSPReturnObject loadShowCascade_subitem(BESSubitemConf besSubitemConf);

    ISSPReturnObject changef_jl_subitem(BESSubitemConf besSubitemConf);

    /**
     *
     * @Description: 一键新增分项配置
     *
     * @auther: wanghongjie
     * @date: 17:35 2021/5/25
     * @param: [list]
     * @return: com.core.common.ISSPReturnObject
     *
     */
	ISSPReturnObject addsubitem_OneClickAdd(String fNybh,String fYqbh,String buildingbh);
}
