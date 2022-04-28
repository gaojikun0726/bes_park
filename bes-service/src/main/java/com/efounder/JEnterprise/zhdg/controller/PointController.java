package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.service.AssetsInfoService;
import com.efounder.JEnterprise.zhdg.entity.PointEntity;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.JEnterprise.zhdg.service.RegionService;
import com.efounder.JEnterprise.zhdg.util.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-08-27 18:36:45
 */
@Controller
@RequestMapping("zhdg/point")
public class PointController {
    private static final Logger log = LoggerFactory.getLogger(PointController.class);
    @Autowired
    private PointService service;
    @Autowired
    private RegionService regionService;
    /** 动态协议接口 */
    @Autowired
    private ISebDynamicAgreeHandleService dynamicAgreeHandleService;

    @Resource
    private AssetsInfoService assetsInfoService;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# PointController #初始化界面");
        return "view/zhdg/Point/Point_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# PointController #分页查询，关键字查询");
        PageInfo<PointEntity> page = service.getSearchWithRealStatus(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhdg/Point/Point_listPage";
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #根据id查询具体参数");
        PointEntity point = service.getSearchById(id);
        String json = JSONObject.toJSONString(point);
        res.setData(json);
        return res;
    }

    /**
     * 根据fCode查询具体参数
     */
    @RequestMapping(value = "/getSearchByFcode" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchByFcode (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #根据fCode查询具体参数");
        PointEntity point = service.getSearchByFcode(id);
        String json = JSONObject.toJSONString(point);
        res.setData(json);
        return res;
    }

    /**
     * 所属区域下拉列表查询
     */
    @RequestMapping(value = "/getRegionSearch" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getRegionSearch(){
        return regionService.getRegionSearch();
    }

    /**
	 * 添加
	 * @param
	 * @return
	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add(PointEntity dto) {
        log.info("# PointController 添加");
        Map<String,Object> map = new HashMap<>();
        map.put("screenCode",dto.getScreenCode());
        //查询唯一编码是否重复
        Integer codeNum = service.querySameCode(dto);
        //查询显示屏编码是否重复
        Integer num = assetsInfoService.querySameSerial(map);
        //查询IP地址是否重复
        Integer ipNum = service.querySameIp(dto);
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        if(codeNum == 0 && num == 0 && ipNum == 0){
            isspReturnObject = service.add(dto);
        }else if(codeNum != 0){
            isspReturnObject.setMsg("唯一编码重复");
            isspReturnObject.setStatus("0");
        } else if(ipNum != 0){
            isspReturnObject.setMsg("IP字段存在重复");
            isspReturnObject.setStatus("0");
        }else{
            isspReturnObject.setMsg("显示屏编码重复");
            isspReturnObject.setStatus("0");
        }
        return isspReturnObject;
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(PointEntity dto) {
        log.info("# PointController 更新");
        Map<String,Object> map = new HashMap<>();
        map.put("fGuid",dto.getFGuid());
        map.put("screenCode",dto.getScreenCode());
        //查询唯一编码是否重复
        Integer codeNum = service.querySameCode(dto);
        //查询显示屏编码是否重复
        Integer num = assetsInfoService.querySameSerial(map);
        //查询IP地址是否重复
        Integer ipNum = service.querySameIp(dto);
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        if(codeNum == 0 && num == 0 && ipNum == 0){
            isspReturnObject = service.update(dto);
        }else if(codeNum != 0){
            isspReturnObject.setMsg("唯一编码重复");
            isspReturnObject.setStatus("0");
        } else if(ipNum != 0){
            isspReturnObject.setMsg("IP字段存在重复");
            isspReturnObject.setStatus("0");
        }else{
            isspReturnObject.setMsg("显示屏编码重复");
            isspReturnObject.setStatus("0");
        }
        return isspReturnObject;
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# PointController 删除");
        return service.delete(id);
    }

    /**
     * 查询所有设备
     */
    @RequestMapping(value = "/GetAllPoint" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetAllPoint () {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #查询所有设备");
        List<PointEntity> PointList = service.selectSebPointList();
        res.setList(PointList);
        return res;
    }

    /**
     * 列表实时数据获取
     */
    @RequestMapping(value = "/GetTableRealDataList" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetTableRealDataList () throws Exception{
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #实时数据获取");
        Map<String,Object> TableRealDataList = dynamicAgreeHandleService.GetRealPoint();
        res.setMap(TableRealDataList);
        return res;
    }

    /**
     * 实时数据获取
     */
    @RequestMapping(value = "/GetRealDataList" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject GetRealDataList () {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #实时数据获取");
        List<Map<String,String>> RealDataList = dynamicAgreeHandleService.GetRealDataList();
        res.setList(RealDataList);
        return res;
    }

    /**
     * 根据id查询设备实时数据
     */
    @RequestMapping(value = "/getPointRealData" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getPointRealData (String deviceId) throws Exception{
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #根据id获取设备实时数据");
        Map<String,String> PointRealData = dynamicAgreeHandleService.getPointInfo(deviceId);
        res.setMap(PointRealData);
        return res;
    }

    /**
     * @Description: 下发指令
     */
    @PostMapping(value = "/sendMsg")
    @ResponseBody
    public AjaxResult sendMsg(String DeviceId, String datas, String orderCode, String digits) throws Exception {
        return dynamicAgreeHandleService.sendMsg(DeviceId, datas, orderCode, digits);
    }


    /**
     * 获取气象状态实时数据
     */
    @RequestMapping(value = "/getWeatherStatusData" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getWeatherStatusData () {
        ISSPReturnObject res = new ISSPReturnObject();
//        log.info("# PointController #获取气象状态实时数据");
        Map<String,String> weatherStatusData = dynamicAgreeHandleService.getWeatherStatusData();
        res.setMap(weatherStatusData);
        return res;
    }

}
