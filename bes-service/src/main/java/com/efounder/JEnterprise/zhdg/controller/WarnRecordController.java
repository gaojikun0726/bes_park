package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.WarnRecordEntity;
import com.efounder.JEnterprise.zhdg.service.WarnRecordService;
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
 * 报警历史记录表
 *
 * @author YangChao
 * @date 2020-09-14 11:54:54
 */
@Controller
@RequestMapping("zhdg/warnrecord")
public class WarnRecordController {
    private static final Logger log = LoggerFactory.getLogger(WarnRecordController.class);
    @Autowired
    private WarnRecordService service;

    /**
     * 初始化頁面
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList() {
        log.info("# WarnRecordController #初始化界面");
        return "view/zhdg/WarnRecord/WarnRecord_list";
    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/getSearch" ,method = RequestMethod.POST)
    public String getSearch (@RequestParam(value = "warnid", required = false)String warnid,
                             @RequestParam(value = "beginTime", required = false)String beginTime,
                             @RequestParam(value = "endTime", required = false)String endTime,
                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("# WarnRecordController #分页查询，关键字查询");
        PageInfo<WarnRecordEntity> page = service.getSearch(warnid,beginTime,endTime,pageNum);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("page", page);
        map.put("warnid",warnid);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("pageList", jsonString);
        return "view/zhdg/WarnRecord/WarnRecord_listPage";
    }

    /**
    * 根据id查询具体参数
    */
    @RequestMapping(value = "/getSearchById" ,method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSearchById (@RequestParam(value = "id", required = true)String id) {
        ISSPReturnObject res = new ISSPReturnObject();
        log.info("# WarnRecordController #根据id查询具体参数");
        WarnRecordEntity map = service.getSearchById(id);
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
    public ISSPReturnObject add(WarnRecordEntity dto) {
        log.info("# WarnRecordController 添加");
        return service.add(dto);
    }

    /**
	 * 更新
	 * @return
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(WarnRecordEntity dto) {
        log.info("# WarnRecordController 更新");
        return service.update(dto);
    }

    /**
	 * 删除
	 * @return
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(String id) {
        log.info("# WarnRecordController 删除");
        return service.delete(id);
    }

    /**
     * 清空历史数据
     * @return
     */
    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject clean() {
        log.info("# WarnRecordController 删除");
        return service.clean();
    }

}
