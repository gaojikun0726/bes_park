package com.efounder.JEnterprise.platform.test;

import com.efounder.JEnterprise.platform.controller.InterfaceTimer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * describe: 定时任务--接口手动调用
 *
 * @author zs
 * @date 2020/11/25
 */
@Controller
@RequestMapping("/test")
public class TimerTest {

    @Resource
   public InterfaceTimer interfaceTimer;


    @RequestMapping("/position")
    public void testPosition(){
        interfaceTimer.getPositionInfoList();
    }


    @RequestMapping("/assert")
    public void testAsset(){
        interfaceTimer.getAllAssetsList();
    }

//    @RequestMapping("/user")
//    public void testUser(){
//        interfaceTimer.getUserList();
//    }

//    @RequestMapping("/role")
//    public void testRole(){
//        interfaceTimer.getRoleList();
//    }
//
    @RequestMapping("/dept")
    public void testDept(){
        interfaceTimer.getDeptList();
    }
//
//
//    @RequestMapping("/rolemenu")
//    public void testRoleMenu(){
//        interfaceTimer.getRoleMenuList();
//    }


//    @RequestMapping("/code_asset")
//    public void testAssetByCode(){
//        interfaceTimer.getConditionerList();
//    }
//
    @RequestMapping("/device")
    public void testAllDevice(){
        interfaceTimer.getAllDeviceList();
    }
}
