package com.efounder.JEnterprise.zhjg.controller;

import com.core.common.ISSPReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-10-10 8:36:45
 */
@Controller
@RequestMapping("zhjg/realCoverPoint")
public class realCoverPointController {
    private static final Logger log = LoggerFactory.getLogger(realCoverPointController.class);

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# realCoverPointController #初始化界面");
        return "view/zhjg/realCoverPoint/realCoverPoint_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearch(@RequestParam(value = "keywords", required = false) String keywords) throws Exception {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointController #列表数据查询");
        List<Map<String, Object>> TableRealDataList = new LinkedList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("sbkh","2020070811100");
        map1.put("dqdl","15.6%");
        map1.put("xhqd","79%");
        map1.put("yjwd","21");
        map1.put("ssjd","116.6422211");
        map1.put("sswd","40.11");
        map1.put("spjj","176");
        TableRealDataList.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("sbkh","2020070811101");
        map2.put("dqdl","16.6%");
        map2.put("xhqd","69%");
        map2.put("yjwd","22");
        map2.put("ssjd","116.6422221");
        map2.put("sswd","40.31");
        map2.put("spjj","179");
        TableRealDataList.add(map2);
        Map<String, Object> RealDataTable = new HashMap<>();
        RealDataTable.put("TableRealDataList", TableRealDataList);
        res.setMap(RealDataTable);
        return res;
    }
}
