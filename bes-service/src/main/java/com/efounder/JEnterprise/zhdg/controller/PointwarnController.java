package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.PointEntity;
import com.efounder.JEnterprise.zhdg.entity.PointwarnEntity;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.JEnterprise.zhdg.service.PointwarnService;
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
 * 点位报警维护
 *
 * @author YangChao
 * @date 2020-09-16 10:20:05
 */
@Controller
@RequestMapping("zhdg/pointwarn")
public class PointwarnController {
    private static final Logger log = LoggerFactory.getLogger(PointwarnController.class);
    @Autowired
    private PointwarnService service;
    @Autowired
    private PointService pointService;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# PointwarnController #初始化界面");
        return "view/zhdg/Pointwarn/Pointwarn_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "pointid", required = false)String pointid,
                             @RequestParam(value = "warnid", required = false)String warnid,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# PointwarnController #分页查询，关键字查询");
        PageInfo<PointwarnEntity> page = service.getSearch(pointid,warnid,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pointid", pointid);
        map.put("warnid", warnid);
        map.put("pageList", jsonString);
        return "view/zhdg/Pointwarn/Pointwarn_listPage";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/point/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# PointController #分页查询，关键字查询");
        PageInfo<PointEntity> page = pointService.getSearch(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhdg/Pointwarn/PointwarnList_Page";
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# PointwarnController #根据id查询具体参数");
        PointwarnEntity map = service.getSearchById(id);
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
    public ISSPReturnObject add(PointwarnEntity dto) {
        log.info("# PointwarnController 添加");
        return service.add(dto);
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(PointwarnEntity dto) {
        log.info("# PointwarnController 更新");
        return service.update(dto);
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# PointwarnController 删除");
        return service.delete(id);
    }

}
