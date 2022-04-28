package com.efounder.JEnterprise.controller.basedatamanage.energydataReport;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.datareported.DataUploadRecord;
import com.efounder.JEnterprise.service.datareported.DataUploadRecordService;
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
 * 历史数据管理
 *
 * @author xiepufeng
 * @date 2021/4/10 10:46
 */
@Controller
@RequestMapping(value = "/view/energydataReport/archivemanage")
public class ArchiveManageController
{

    private static final Logger log = LoggerFactory.getLogger(ArchiveManageController.class);

    @Autowired
    private DataUploadRecordService dataUploadRecordService;

    /**
     * 初始化历史数据页面
     *
     * @return
     */
    @RequestMapping(value = "/getArchiveManage", method = RequestMethod.GET)
    public String getArchiveManage()
    {
        log.info("# ArchiveManageController #初始化‘历史数据页面’界面");
        return "/view/basedatamanage/energydataReport/archivemanage/archiveManage";
    }

    /**
     * 搜索
     *
     * @param keywords
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/getArchiveManageListPage", method = RequestMethod.POST)
    public String getArchiveManageListPage(@RequestParam(value = "keywords", required = false) String keywords,
                                    @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map)
    {
        log.info("# BESBudingController #分页查询，关键字查询");
        PageInfo<DataUploadRecord> page = dataUploadRecordService.getArchiveManageListPage(keywords, pageNum);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", keywords);
        return "view/basedatamanage/energydataReport/archivemanage/archiveManage_page";
    }
    
    /**
     * 生成能耗数据包
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/createDataPackage", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createDataPackage(@RequestParam(value = "startTime", required = false) String startTime,
                                              @RequestParam(value = "endTime", required = false) String endTime)
    {
        log.info("# ArchiveManageController #生成能耗数据包");

        return dataUploadRecordService.createDataPackage(startTime, endTime);
    }
}
