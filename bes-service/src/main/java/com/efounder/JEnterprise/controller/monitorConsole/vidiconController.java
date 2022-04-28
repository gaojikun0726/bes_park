package com.efounder.JEnterprise.controller.monitorConsole;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.monitorConsole.vidiconService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @CkassName: vidiconController
 * @Author: YangChao
 * @Date: 2019/3/28 10:01
 * @Descruotuib: 监控台 摄像机 控制类
 * @Version: 1.0
 **/
@Controller
@RequestMapping(value = "/vidicon")
public class vidiconController {
    private static final Logger log = LoggerFactory.getLogger(vidiconController.class);

    @Autowired
    private vidiconService service ;

    /**
     * @Author: YangChao
     * @CreateDate: 2019/3/28 10:03
     * @Description: 初始化-控制台 -摄像机页面
     */
    @RequestMapping(value = "/getVidiconPage", method = RequestMethod.GET)
    public String getVidiconPage() {
        log.info("#vidiconController初始化-控制台-摄像机页面");
        return "view/vidicon/vidicon_list";
    }


    /**
    * @Author:         YangChao
    * @CreateDate:     2019/3/28 15:20
    * @Description:    组织机构拼装
    */
    @RequestMapping(value = "/leftTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getleftTree() {
        log.info("#vidiconController-左侧组织机构拼装");
        return service.getLeftTree();
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/4/2 11:02
    * @Description:    得到赋值
    */
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getData(HttpServletRequest request) {
        return service.getData(request);
    }

    /**
     *
     * @author: YangChao
     * @createTime: 2019年6月25日 下午3:39:10
     * @Description: 得到所有摄像机List
     * @param request
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/getVidiconList", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getVidiconList(HttpServletRequest request) {
        return service.getVidiconList(request);
    }

}
