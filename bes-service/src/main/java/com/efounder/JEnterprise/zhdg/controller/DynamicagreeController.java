package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.DynamicagreeEntity;
import com.efounder.JEnterprise.zhdg.service.AgreehandleService;
import com.efounder.JEnterprise.zhdg.service.DynamicagreeService;
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


/**
 * 动态协议配置
 *
 * @author YangChao
 * @date 2020-09-03 09:40:12
 */
@Controller
@RequestMapping("zhdg/dynamicagree")
public class DynamicagreeController {
    private static final Logger log = LoggerFactory.getLogger(DynamicagreeController.class);
    @Autowired
    private DynamicagreeService service;
    /**协议处理接口*/
    @Autowired
    private AgreehandleService agreehandleService;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# DynamicagreeController #初始化界面");
        return "view/zhdg/Dynamicagree/Dynamicagree_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# DynamicagreeController #分页查询，关键字查询");
        PageInfo<DynamicagreeEntity> page = service.getSearch(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhdg/Dynamicagree/Dynamicagree_listPage";
    }

    /**
     * 协议处理类型查询
     */
    @RequestMapping(value = "/getHandleSearch" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getHandleSearch () {
        return agreehandleService.getHandleSearch();
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# DynamicagreeController #根据id查询具体参数");
        DynamicagreeEntity map = service.getSearchById(id);
        String json = JSONObject.toJSONString(map);
        res.setData(json);
        return res;
    }

    /**
	 * 添加
	 * @param
	 * @return
	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add(DynamicagreeEntity dto) {
        log.info("# DynamicagreeController 添加");
        return service.add(dto);
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(DynamicagreeEntity dto) {
        log.info("# DynamicagreeController 更新");
        return service.update(dto);
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# DynamicagreeController 删除");
        return service.delete(id);
    }

}
