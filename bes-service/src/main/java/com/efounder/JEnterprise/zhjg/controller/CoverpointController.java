package com.efounder.JEnterprise.zhjg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhjg.entity.CoverpointEntity;
import com.efounder.JEnterprise.zhjg.service.CoverpointService;
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
 * 设备维护
 *
 * @author YangChao
 * @date 2020-10-09 14:28:58
 */
@Controller
@RequestMapping("zhjg/coverpoint")
public class CoverpointController {
    private static final Logger log = LoggerFactory.getLogger(CoverpointController.class);
    @Autowired
    private CoverpointService service;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# CoverpointController #初始化界面");
        return "view/zhjg/Coverpoint/Coverpoint_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# CoverpointController #分页查询，关键字查询");
        PageInfo<CoverpointEntity> page = service.getSearch(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhjg/Coverpoint/Coverpoint_listPage";
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# CoverpointController #根据id查询具体参数");
        CoverpointEntity map = service.getSearchById(id);
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
    public ISSPReturnObject add(CoverpointEntity dto) {
        log.info("# CoverpointController 添加");
        return service.add(dto);
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(CoverpointEntity dto) {
        log.info("# CoverpointController 更新");
        return service.update(dto);
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# CoverpointController 删除");
        return service.delete(id);
    }

}
