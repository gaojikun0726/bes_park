package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.MessageMonitoringService;
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
 * @Author: wanghongjie
 * @Description:消息监听接口（接口管理模块）
 * @Date: Created in 14:18 2020/12/14
 * @Modified By:消息监听接口  MessageMonitoring
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/messageMonitoring")
@Controller
public class MessageMonitoringController {
    private static final Logger log = LoggerFactory.getLogger(MessageMonitoringController.class);

    @Autowired
    private MessageMonitoringService messageMonitoringService;
    /**
     * 初始化消息监听页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#MessageMonitoringController 初始化消息监听页面");
        return "view/sysmanage/interfaceconfig/messageMonitoring";
    }

    /**
     *
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String getMessageMonitoringList(@RequestParam(value = "param", required = false)String param,
                                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：消息监听");
        PageInfo<MessageMonitoringModel> page = messageMonitoringService.queryPage(pageNum,param);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        return "view/sysmanage/interfaceconfig/messageMonitoringPage";

    }

    /**
     * 添加消息监听
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createHost(MessageMonitoringModel messageMonitoringModel) {

        log.info("#添加设备类型");
        return messageMonitoringService.create(messageMonitoringModel);
    }


    /**
     *  查询消息监听信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(MessageMonitoringModel messageMonitoringModel) {
        log.info("#查询设备类型信息");
        return messageMonitoringService.query(messageMonitoringModel);
    }

    /**
     *
     * @Description: 修改消息监听信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(MessageMonitoringModel messageMonitoringModel) {
        log.info("#修改消息监听信息");
        return messageMonitoringService.update(messageMonitoringModel);
    }

    /**
     *
     * @Description: 删除消息监听信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject delete(MessageMonitoringModel messageMonitoringModel) {
        log.info("#删除消息监听信息");
        return messageMonitoringService.delete(messageMonitoringModel);
    }
}
