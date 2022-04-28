package com.efounder.JEnterprise.service.basedatamanage.workbench;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.BesSubyearData;
import com.efounder.JEnterprise.model.BesWorkbench.BesGztzzjgTree;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;

/**
* @author  杨超
* @version 创建时间：2018年11月30日 上午10:53:06
* @parameter 
* @version 1.0
*/
public interface BESWorkBenchTreeService {

    // 刷新table
    ISSPReturnObject RefreshTable(String nodeId);
    
    /**
     * 删除
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws UnsupportedEncodingException 
     */
    ISSPReturnObject del_WorkbenchTree(String id) throws UnsupportedEncodingException, RemoteException, ServiceException;
    
    
    /**
     *添加
     * */
    ISSPReturnObject add_workbenchTree(BesGztzzjgTree besgztzjgtree);
    
    /**
     * 编辑
     * */
    ISSPReturnObject edit_workbenchTree(BesGztzzjgTree besgztzzjgtree);
    
    /**
     * 查询
     * */
    ISSPReturnObject serch_workdenchTree(String bh);
    
    /**
         * 切地图
     * */
	ISSPReturnObject changeImage(String id);
	
    /**
     * 
     * @Title: illumination
     * @Description:同比环比数据对接
     * @return: ISSPReturnObject
     * @throws
     */
    ISSPReturnObject illumination(BesHouseholdData besHouseholdData, HttpServletRequest request);

    /**
     * 
     * @Title: energyAnalyze
     * @Description:工作台 能耗分析
     * @return: ISSPReturnObject
     * @throws
     */
    ISSPReturnObject energyAnalyze(BesHouseholdData besHouseholdData, HttpServletRequest request);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月18日 下午2:35:41
     * @Description:工作台刷新左侧和地图中间部分数据
     * @param besHouseholdData
     * @param request
     * @return ISSPReturnObject
     */
    ISSPReturnObject gztLeftRefresh(BesHouseholdData besHouseholdData, HttpServletRequest request);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月25日 下午2:27:31
     * @Description:能耗排行接口
     * @param besHouseholdData
     * @param request
     * @return ISSPReturnObject
     */
    ISSPReturnObject GetRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月26日 下午2:16:28
     * @Description:报警信息查询
     * @param beswaininginfo
     * @param request
     * @return ISSPReturnObject
     */
    ISSPReturnObject GetWaringReal(BesWainingInfo beswaininginfo, HttpServletRequest request);
    /**
     * 园区信息
     * @param yqbh
     * @param request
     * @return
     */
	ISSPReturnObject getleftInfo(String yqbh, HttpServletRequest request);

    ISSPReturnObject GetTodayRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request);

    /**
     * 获取本年分项数据
     * @param request
     * @return
     */
    ISSPReturnObject getEnergyYearData(HttpServletRequest request);

    /**
     * 插入本年分项数据
     * @param
     * @param
     * @return
     */
    ISSPReturnObject insertSubyearData(List<BesSubyearData> list,String firstDay,String endDay);

    /**
     * 首页加载年数据
     * @param request
     * @return
     */
    ISSPReturnObject selectEnergyYearData(HttpServletRequest request);

  
    /**
     *
     * @Description: 今日能耗排行(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 14:14 2021/4/26
     * @param: [besHouseholdData, request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject GetTodayRankEnergyNew(HttpServletRequest request);

    /**
     *
     * @Description: 今日能耗趋势(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 17:39 2021/4/26
     * @param: [request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject getHouseHoldDataNew(HttpServletRequest request);

    /**
     *
     * @Description: 今日同环比,本月同环比(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 13:57 2021/4/27
     * @param: [request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject illuminationNew(HttpServletRequest request);

    /**
     *
     * @Description: 本年能耗以及本年能耗峰值
     *
     * @auther: wanghongjie
     * @date: 18:11 2021/4/27
     * @param: [request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject getEnergyYearDataNew(HttpServletRequest request);
}
