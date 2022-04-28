package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.CameraManageHk;
import com.efounder.JEnterprise.zhdg.service.CameraManageService;
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

@Controller
@RequestMapping("zhdg/cameraManage")
public class CameraManageController {
    private static final Logger log = LoggerFactory.getLogger(CameraManageController.class);
    @Autowired
    private CameraManageService cameraManageService;
    /**
     * 初始化页面
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String pageInit() {
        log.info("# CameraManageController #初始化界面");
        return "view/zhdg/camera/CameraManage";
    }
    /**
     * 搜索
     */
    @RequestMapping(value = "/selectList" ,method = RequestMethod.POST)
    public String selectList (@RequestParam(value = "keywords", required = false)String keywords,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# CameraManageController #分页查询，关键字查询");
        PageInfo<CameraManageHk> page = cameraManageService.selectList(keywords,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("pageList", jsonString);
        return "view/zhdg/camera/CameraManage_page";
    }

    /**
     * 根据id查询具体参数
     */
    @RequestMapping(value = "/selectById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectById (@RequestParam(value = "id", required = true)long id) {
        log.info("# CameraManageController #根据id查询具体参数");
        return cameraManageService.selectById(id);
    }

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add(CameraManageHk cameraManageHk) {
        log.info("# CameraManageController 添加");
        return cameraManageService.add(cameraManageHk);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(CameraManageHk cameraManageHk) {
        log.info("# CameraManageController 更新");
        return cameraManageService.update(cameraManageHk);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(long id) {
        log.info("# CameraManageController 删除");
        return cameraManageService.delete(id);
    }
    /**
     * 视频预览
     * */
    @RequestMapping("/preView")
    @ResponseBody
    public ISSPReturnObject preView(CameraManageHk cameraManageHk){
        log.info("# CameraManageController 视频预览");
        return cameraManageService.preView(cameraManageHk);
    }
}
