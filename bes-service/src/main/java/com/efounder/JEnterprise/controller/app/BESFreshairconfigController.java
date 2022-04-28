package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.service.app.BESFreshairconfigService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/view/BESFreshairconfigController")
public class BESFreshairconfigController {
    private static final Logger log = LoggerFactory.getLogger(BESFreshairconfigController.class);

    @Autowired
    private BESFreshairconfigService besFreshairconfigService;

    /**
     * 初始化新风配置界面
     * @return
     */
    @RequestMapping(value = "/getFreshairconfig" ,method = RequestMethod.GET)
    public String getFreshairconfig () {
        log.info("# BESFreshairconfigController #初始化‘新风配置’界面");
        return "view/app/freshairconfig";
    }

    /**
     * 查询采集方案
     * @return
     */
    @RequestMapping(value = "/selectFreshair", method = RequestMethod.POST)
    public String selectFreshair (ModelMap map, Integer pageSize, Integer pageNum, Map<String,Object> map1) {
        log.info("# BESFreshairconfigController 获取采集方案");
        PageInfo<Object> page = besFreshairconfigService.selectFreshair(pageSize,pageNum,map1);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "view/app/freshairconfigPage";
    }


    /**
     *  查询新风配置信息
     * */
    @RequestMapping(value = "/selectFreshairByID", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectFreshairByID(String id) {
        log.info("#查询新风配置信息");
        ISSPReturnObject returnObject = new  ISSPReturnObject();
        Map<String,Object>  map =  besFreshairconfigService.selectFreshairByID(id);
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
         returnObject.setData(list);
        return returnObject;
    }

    /**
     * 修改新风配置
     * */
    @RequestMapping(value = "/updateFreshair", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateFreshair(@RequestParam Map<String,Object> map) {
        log.info("#修改新风配置");
        ISSPReturnObject returnObject = new  ISSPReturnObject();
        boolean flag =  besFreshairconfigService.updateFreshair(map);
        if (flag){
            returnObject.setStatus("1");
            returnObject.setMsg("修改成功！");
            return returnObject;
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("修改失败！");
            return  returnObject;
        }
    }


    /**
     * 添加新风配置
     * */
    @RequestMapping(value = "/insertFreshair", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertFreshair(@RequestParam Map<String,Object> map) {
        log.info("#添加新风配置");
        ISSPReturnObject returnObject = new  ISSPReturnObject();
        boolean flag =  besFreshairconfigService.insertFreshair(map);
        if (flag){
            returnObject.setStatus("1");
            returnObject.setMsg("添加成功！");
            return returnObject;
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败！");
            return  returnObject;
        }
    }


    /**
     * 删除新风配置信息
     * */
    @RequestMapping(value = "/deleteFreshairByID", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteFreshairByID(String id) {
        log.info("#删除新风配置信息");
        ISSPReturnObject returnObject = new  ISSPReturnObject();
        boolean flag = besFreshairconfigService.deleteFreshairByID(id);
        if (flag){
            returnObject.setStatus("1");
            returnObject.setMsg("添加成功！");
            return returnObject;
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败！");
            return  returnObject;
        }
    }



}
