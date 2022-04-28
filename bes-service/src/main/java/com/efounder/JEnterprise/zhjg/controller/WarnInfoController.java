package com.efounder.JEnterprise.zhjg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhjg.entity.WarnInfoEntity;
import com.efounder.JEnterprise.zhjg.service.WarnInfoService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * describe: 井盖报警记录
 *
 * @author zs
 * @date 2021/3/26
 */
@Controller
@RequestMapping("zhjg/warn")
public class WarnInfoController {

    private static final Logger log = LoggerFactory.getLogger(WarnInfoController.class);


    @Resource
    private WarnInfoService warnInfoService;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# WarnInfoController #初始化界面");
        return "view/zhjg/warnInfo/warnInfo_list";
    }


    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "warnid", required = false)String warnid,
                             @RequestParam(value = "beginTime", required = false)String beginTime,
                             @RequestParam(value = "endTime", required = false)String endTime,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# WarnInfoController #分页查询，关键字查询");
        PageInfo<WarnInfoEntity> page = warnInfoService.getSearch(warnid,beginTime,endTime,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("warnid",warnid);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("pageList", jsonString);
        return "view/zhjg/warnInfo/warnInfo_listPage";
    }

    /**
     * 根据id查询具体参数
     */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# WarnInfoController #根据id查询具体参数");
        WarnInfoEntity map = warnInfoService.getSearchById(id);
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
    public ISSPReturnObject add(WarnInfoEntity dto) {
        log.info("# WarnInfoController 添加");
        return warnInfoService.add(dto);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(WarnInfoEntity dto) {
        log.info("# WarnInfoController 更新");
        return warnInfoService.update(dto);
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# WarnInfoController 删除");
        return warnInfoService.delete(id);
    }

    /**
     * 清空历史数据
     * @return
     */
    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject clean() {
        log.info("# WarnInfoController 删除");
        return warnInfoService.clean();
    }
}
