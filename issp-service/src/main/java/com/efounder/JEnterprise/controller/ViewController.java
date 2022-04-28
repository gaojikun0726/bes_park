package com.efounder.JEnterprise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private static final Logger log = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value = "view/sysconfig/setconfig", method = RequestMethod.GET)
    String setconfig() {
        log.info("# loding view/sysconfig/setconfig ");
        return "view/sysconfig/setconfig";
    }


    @RequestMapping(value = "view/city/add2", method = RequestMethod.GET)
    String city_add2() {
        log.info("# loding view/city/add2 ");
        return "view/city/add2";
    }

    @RequestMapping(value = "view/city/list", method = RequestMethod.GET)
    String city_list() {
        log.info("# loding view/city/list ");
        return "view/city/list";
    }

    @RequestMapping(value = "view/user/user_list", method = RequestMethod.GET)
    String user_list() {
        log.info("# loding view/user/user_list");
        return "view/user/user_list";
    }

    @RequestMapping(value = "view/data/statIncome_data", method = RequestMethod.GET)
    String statIncome_data() {
        log.info("# loding view/data/statIncome_data");
        return "view/data/statIncome_data";
    }
 
}
