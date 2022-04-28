package com.efounder.JEnterprise.zhdg.controller;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-09-08 14:36:45
 */
@Controller
@RequestMapping("zhdg/realPoint")
public class realPointController {
    private static final Logger log = LoggerFactory.getLogger(realPointController.class);
    /** 动态协议接口 */
    @Autowired
    private ISebDynamicAgreeHandleService dynamicAgreeHandleService;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# realPointController #初始化界面");
        return "view/zhdg/realPoint/realPoint_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearch (@RequestParam(value = "keywords", required = false)String keywords , ModelMap map) throws Exception{
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #列表数据查询");
        Map<String,Object> RealDataTable = dynamicAgreeHandleService.GetRealPoint();
        res.setMap(RealDataTable);
        return res;
    }

}
