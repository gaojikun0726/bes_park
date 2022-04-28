package com.efounder.JEnterprise.controller.sysmanage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @CkassName: SystemSettingController
 * @Author: YangChao
 * @Date: 2019/2/13 16:25
 * @Descruotuib:系统一般设置,数据备份设置,平台升级设置共用controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping(value = "/SystemSetting")
public class SystemSettingController {
    private static final Logger log = LoggerFactory.getLogger(SystemSettingController.class);


    /**
     * 
     * @author: YangChao
     * @createTime: 2019年7月30日 上午10:50:56
     * @Description:加密狗设置
     * @return String
     */
    @RequestMapping(value = "/SuperDogSetting", method = RequestMethod.GET)
    public String SuperDogSetting() {
        log.info("# SystemSettingController系统参数设置");
        return "view/sysmanage/superDog/SuperDogSetting";
    }

}
