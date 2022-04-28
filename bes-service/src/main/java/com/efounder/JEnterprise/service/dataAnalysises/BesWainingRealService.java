package com.efounder.JEnterprise.service.dataAnalysises;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author  杨超
* @version 创建时间：2018年12月3日 下午2:06:41
* @parameter 
* @version 1.0
*/
public interface BesWainingRealService {

    /**
     * @Title: searchWainingInfo
     * @Description:分页查询报警监控信息
     * @return: PageInfo<BesWainingInfo>
     * @throws
     */
    PageInfo<BesWainingInfo> getWarningRealInfoData(Integer pageNum, BesWainingInfo besWainingInfo);

    ISSPReturnObject WarningDsipose(HttpServletRequest request);
    /**
     *
     * @Description: 批量处理报警信息
     * 
     * @auther: wanghongjie
     * @date: 15:38 2020/6/6
     * @param: [besWainingInfos]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject WarningDsiposeList(List<BesWainingInfo> besWainingInfos);

    /**
     *
     * @Description:首页告警图标--查询实时报警数据
     *
     * @auther: wanghongjie
     * @date: 11:18 2020/5/27
     * @param:
     * @return:
     *
     */
    public PageInfo<BesWainingInfo> getAlarmWarnInfoByRecoverState(Integer bars, Integer pageNum,String f_operation,String type);
    /**
     *
     * @Description: 首页告警图标--获取未恢复的信息数
     * 
     * @auther: wanghongjie
     * @date: 15:39 2020/5/27
     * @param: [f_recover_state, f_zzjgid, sblxList, sbList]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    public ISSPReturnObject getNoRecoverCount(String f_operation);

    /**
     *
     * @Description: 全部处理报警信息
     *
     * @auther: wanghongjie
     * @date: 10:53 2020/7/1
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject WarningDsiposeAll(JSONObject obj);
}
