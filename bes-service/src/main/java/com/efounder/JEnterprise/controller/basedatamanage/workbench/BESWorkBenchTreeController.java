package com.efounder.JEnterprise.controller.basedatamanage.workbench;

import com.alibaba.fastjson.JSON;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.BesSubyearData;
import com.efounder.JEnterprise.model.BesWorkbench.BesGztzzjgTree;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.workbench.BESWorkBenchTreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;

/**
* @author  杨超
* @version 创建时间：2018年11月30日 上午10:47:32
* @parameter 
* @version 1.0
*/
@Controller
@RequestMapping(value = "/view/basedatamanage/workbench")
public class BESWorkBenchTreeController {
    private static final Logger log = LoggerFactory.getLogger(BESWorkBenchTreeController.class);

    @Autowired
    private BESWorkBenchTreeService besworkbenchtreeservice;
    
    /**
     * @Title: getWorkBenchTree
     * @Description:
     */
    @RequestMapping(value = "/getWorkBenchTree", method = RequestMethod.GET)
    public String getWorkBenchTree() {
        log.info("BESWorkBenchTreeController工作台组织机构");
        return "view/basedatamanage/workbench/workbenchtree/workbench_tree";
    }

    @RequestMapping(value = "/RefreshTable", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject RefreshTable(String nodeId) {
        log.info("BESWorkBenchTreeController--刷新table");
        return besworkbenchtreeservice.RefreshTable(nodeId);
    }
    
    /**
     * 删除
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws UnsupportedEncodingException 
     * */
    @RequestMapping(value = "/del_WorkbenchTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject del_WorkbenchTree(String id) throws UnsupportedEncodingException, RemoteException, ServiceException {
    	log.info("#删除Tree");
    	return besworkbenchtreeservice.del_WorkbenchTree(id);
    }
    
    /**添加
     * */
    @RequestMapping(value = "/add_workbenchTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add_workbenchTree(BesGztzzjgTree besgztzjgtree) {
    	log.info("#添加Tree");
    	return besworkbenchtreeservice.add_workbenchTree(besgztzjgtree);
    }
    
    /**
     * 编辑
     * */
    @RequestMapping(value = "/edit_workbenchTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit_workbenchTree(BesGztzzjgTree besgztzjgtree) {
    	log.info("#编辑tree");
    	return besworkbenchtreeservice.edit_workbenchTree(besgztzjgtree);
    }
    
    /**
         * 查询
     * */
    @RequestMapping(value = "/serch_workdenchTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject serch_workdenchTree(String bh) {
    	log.info("#查询");
    	return besworkbenchtreeservice.serch_workdenchTree(bh);
    }
    
    
    /**
     * 
     * @Title: illumination
     * @Description:同比环比数据对接
     * @return: ISSPReturnObject
     * @throws
     */
    @RequestMapping(value = "/illumination", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject illumination(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#同比环比数据对接");
        ISSPReturnObject returnObject = besworkbenchtreeservice.illumination(besHouseholdData, request);
        return returnObject;
    }
    
    /**
     * 
     * @Title: energyAnalyze
     * @Description:能耗分析 工作台
     * @return: ISSPReturnObject
     * @throws
     */
    @RequestMapping(value = "/energyAnalyze", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject energyAnalyze(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#环比照明数据对接");
        ISSPReturnObject returnObject = besworkbenchtreeservice.energyAnalyze(besHouseholdData, request);
        return returnObject;
    }
    
    /**
         * 切地图
     * */
    @RequestMapping(value = "/changeImage", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject changeImage(String id) {
    	log.info("#切换地图");
    	ISSPReturnObject object = besworkbenchtreeservice.changeImage(id);
		return object;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月18日 下午2:34:36
     * @Description:工作台刷新左侧和地图中间部分数据
     * @param besHouseholdData
     * @param request
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/gztLeftRefresh", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject gztLeftRefresh(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#环比照明数据对接");
        ISSPReturnObject returnObject = besworkbenchtreeservice.gztLeftRefresh(besHouseholdData, request);
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月25日 下午2:26:43
     * @Description:能耗排行接口
     * @param besHouseholdData
     * @param request
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/GetRankEnergy", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#能耗排行接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.GetRankEnergy(besHouseholdData, request);
        return returnObject;
    }

/*    *//**whj  本月能耗排行
     *
     * @param besHouseholdData
     * @param request
     * @return
     *//*
    @RequestMapping(value = "/GetRankEnergy_new", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetRankEnergy_new(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#能耗排行接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.GetRankEnergy_new(besHouseholdData, request);
        return returnObject;
    }*/

    /**
    * @Description: 今日能耗单独
    * @param
    * @return
    * @author YangChao
    * @date 2019/8/27 14:55
    */
    @RequestMapping(value = "/GetTodayRankEnergy", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetTodayRankEnergy(BesHouseholdData besHouseholdData, HttpServletRequest request) {
        log.info("#今日能耗排行接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.GetTodayRankEnergy(besHouseholdData, request);
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月26日 上午11:16:16
     * @Description:报警信息查询
     * @param
     * @param request
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/GetWaringReal", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetWaringReal(BesWainingInfo beswaininginfo, HttpServletRequest request) {
        log.info("#工作台报警信息");
        ISSPReturnObject returnObject = besworkbenchtreeservice.GetWaringReal(beswaininginfo, request);
        return returnObject;
    }
/**
     * 园区信息
     * @param yqbh
     * @param request
     * @return
     */
    @RequestMapping(value = "/getleftInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getleftInfo(String yqbh, HttpServletRequest request) {
        log.info("#园区信息");
        ISSPReturnObject returnObject = besworkbenchtreeservice.getleftInfo(yqbh, request);
        return returnObject;
    }

    /**
     * 园区信息
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEnergyYearData", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getEnergyYearData(HttpServletRequest request) {
        log.info("#园区信息");
        ISSPReturnObject returnObject = besworkbenchtreeservice.getEnergyYearData(request);
        return returnObject;
    }

    /**
     * 添加年数据
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/insertSubyearData", method = RequestMethod.POST)
    @ResponseBody
//    ,@RequestParam("firstDay") String firstDay,@RequestParam("endDay") String endDay
    public ISSPReturnObject insertSubyearData(@RequestParam("nodeObj") String arr,String firstDay,String endDay)  {
        log.info("#节点信息");
        //json数据转javabean
        List<BesSubyearData> list = JSON.parseArray(arr, BesSubyearData.class);
        ISSPReturnObject returnObject = besworkbenchtreeservice.insertSubyearData(list,firstDay,endDay);
        return returnObject;
    }

    /**
     * 首页加载查询数据
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectEnergyYearData", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectEnergyYearData(HttpServletRequest request) {
        log.info("#园区信息");
        ISSPReturnObject returnObject = besworkbenchtreeservice.selectEnergyYearData(request);
        return returnObject;
    }


    /**
     *
     * @Description: 今日能耗排行(权限控制后的排行逻辑)
     * 
     * @auther: wanghongjie
     * @date: 14:13 2021/4/26
     * @param: [besHouseholdData, request]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/GetTodayRankEnergyNew", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetTodayRankEnergyNew(HttpServletRequest request) {
        log.info("#今日能耗排行接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.GetTodayRankEnergyNew(request);
        return returnObject;
    }
    
    /**
     *
     * @Description: 今日能耗趋势(权限控制后的排行逻辑)
     * 
     * @auther: wanghongjie
     * @date: 17:38 2021/4/26
     * @param: 
     * @return: 
     *
     */
    @RequestMapping(value = "/getHouseHoldDataNew", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getHouseHoldDataNew(HttpServletRequest request) {
        log.info("#今日能耗趋势接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.getHouseHoldDataNew(request);
        return returnObject;
    }

    /**
     *
     * @Description: 今日同环比,本月同环比(权限控制后的排行逻辑)
     *
     * @auther: wanghongjie
     * @date: 13:56 2021/4/27
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/illuminationNew", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject illuminationNew(HttpServletRequest request) {
        log.info("#今日同环比,本月同环比接口");
        ISSPReturnObject returnObject = besworkbenchtreeservice.illuminationNew(request);
        return returnObject;
    }


    /**
     *
     * @Description: 本年能耗以及本年能耗峰值
     * 
     * @auther: wanghongjie
     * @date: 18:10 2021/4/27 
     * @param: 
     * @return: 
     *
     */
    @RequestMapping(value = "/getEnergyYearDataNew", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getEnergyYearDataNew(HttpServletRequest request) {
        log.info("#本年能耗以及本年能耗峰值");
        ISSPReturnObject returnObject = besworkbenchtreeservice.getEnergyYearDataNew(request);
        return returnObject;
    }
}
