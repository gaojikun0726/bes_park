package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeSceneInfo;

/**
 * @author sunzhiyuan
 * @Date:2020/8/31 15:05
 */
public interface BesceneConfigService {

    /**
     * 获取主页场景配置树数据
     * @return
     */
    ISSPReturnObject getSceneTree();

    /**
     * 添加控制场景
     * @param beSceneInfo
     * @return
     */
    ISSPReturnObject insertContScene(BeSceneInfo beSceneInfo);

    /**
     * 添加采集场景
     * @param beSceneInfo
     * @return
     */
    ISSPReturnObject insertCollScene(BeSceneInfo beSceneInfo);

    /**
     * 修改场景名称
     * @param beSceneInfo
     * @return
     */
    ISSPReturnObject editSceneInfo(BeSceneInfo beSceneInfo);

    /**
     * 删除一个场景
     * @param id
     * @return
     */
    ISSPReturnObject deleteSceneInfo(String id);

    /**
     * 查询table内的指令信息
     * @param id
     * @return
     */
    ISSPReturnObject queryTableParam(String id);

    /**
     * 新增的table内的数据
     * @param obj
     * @return
     */
    ISSPReturnObject insertScenePoint(JSONObject obj);

    /**
     *
     * @param obj
     * @return
     */
    ISSPReturnObject selectValueAndUnitById(JSONObject obj);

    ISSPReturnObject insertPointValueUnitAndIssue(JSONObject obj);

    ISSPReturnObject insertCollectPointValueUnitAndIssue(JSONObject obj);


    ISSPReturnObject saveSceneInfomation(JSONObject obj);

    ISSPReturnObject selectModeInfoBySceneId(String sceneId);

    ISSPReturnObject deleteUnderModeInfo(String modeId,String sceneId);

    ISSPReturnObject queryContrastLocalInfo(String modeId);

    ISSPReturnObject queryContrastUnderInfo(String modeId,String sceneId,String type);

    ISSPReturnObject synchroPointValueUnitAndIssue(JSONObject obj);

    ISSPReturnObject deletCollectModeInfomation(String modeId,String sceneId);

    /**
     *
     * @Description: 修改场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 14:16 2021/8/9
     * @param: [modeId, sceneId]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject updateCollectMode(JSONObject obj);

    /**
     *
     * @Description: 添加场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 9:05 2021/8/12
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject addCollectMode(JSONObject obj);
}
