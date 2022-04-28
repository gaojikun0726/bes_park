package com.efounder.JEnterprise.controller.superdog.server;

import com.core.common.ISSPReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
@RequestMapping(value = "/SuperDog")
public class SuperDogController {

    private static final Logger log = LoggerFactory.getLogger(SuperDogController.class);
    // 是否开启加密狗加密登陆--默认0不启动--1启动
    @Value("${system.parameter.SuperDogState}")
    private String SuperDogState;

    @RequestMapping(value = "/superdog", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject superdog() {
        ISSPReturnObject res = new ISSPReturnObject();
        res.setStatus(SuperDogState);
        return res;
    }
}

	