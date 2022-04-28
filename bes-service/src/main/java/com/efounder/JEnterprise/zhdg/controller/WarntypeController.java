package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.WarntypeEntity;
import com.efounder.JEnterprise.zhdg.service.WarntypeService;
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
 * 报警类型维护表
 *
 * @author YangChao
 * @date 2020-09-14 11:20:24
 */
@Controller
@RequestMapping("zhdg/warntype")
public class WarntypeController {
    private static final Logger log = LoggerFactory.getLogger(WarntypeController.class);
    @Autowired
    private WarntypeService service;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# WarntypeController #初始化界面");
        return "view/zhdg/Warntype/Warntype_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# WarntypeController #分页查询，关键字查询");
        PageInfo<WarntypeEntity> page = service.getSearch(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhdg/Warntype/Warntype_listPage";
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# WarntypeController #根据id查询具体参数");
        WarntypeEntity map = service.getSearchById(id);
        String json = JSONObject.toJSONString(map);
        res.setData(json);
        return res;
    }

    /**
     * 下拉列表查询所有报警类型
     */
    @RequestMapping(value = "/getAllWarnType" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getAllWarnType () {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# WarntypeController #下拉列表查询所有报警类型");
        return service.getAllWarnType();
    }

    /**
	 * 添加
	 * @param
	 * @return
	 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add(WarntypeEntity dto) {
        log.info("# WarntypeController 添加");
        return service.add(dto);
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(WarntypeEntity dto) {
        log.info("# WarntypeController 更新");
        return service.update(dto);
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# WarntypeController 删除");
        return service.delete(id);
    }

}
