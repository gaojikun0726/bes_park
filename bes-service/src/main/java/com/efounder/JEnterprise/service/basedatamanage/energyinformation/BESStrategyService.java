package com.efounder.JEnterprise.service.basedatamanage.energyinformation;


import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 策略配置接口
 * @author LiuWenGe
 *
 */
public interface BESStrategyService {

    /**
     * 获取主页策略配置树数据
     * @return
     */
    ISSPReturnObject getStrategyTree();

    /**
     * 查询该策略的配置信息
     * @param id
     * @return
     */
    ISSPReturnObject queryTableParam(String id);

    ISSPReturnObject updateStrategyName(String id, String name);

    ISSPReturnObject deleteStrategy(String id);

    /*ISSPReturnObject getTree(String fZzjgbh,String fnybh);*/

    ISSPReturnObject add(BESStrategy besStrategy);

    ISSPReturnObject update(JSONObject object);

}
