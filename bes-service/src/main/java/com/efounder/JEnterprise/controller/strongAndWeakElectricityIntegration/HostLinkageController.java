package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageDeviceModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageRunningModeModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.HostLinkageService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名称：HostLinkageController
 *  类描述：主机联动页面
 *   创建人：xiepufeng
 *   创建时间：2019/9/07
 *   修改人：
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/hostLinkage")
public class HostLinkageController {
	private static final Logger log = LoggerFactory.getLogger(HostLinkageController.class);

    @Resource
    private HostLinkageService hostLinkageService;
    @Resource
    private GljlService gljlService;

   @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#HostLinkageController 进入主机联动页面");
        return "view/strongAndWeakElectricityIntegration/hostLinkage";
    }

    /**
     * 添加主机
     * */
    @RequestMapping(value = "/createHost", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createHost(HostLinkageModel hostLinkageModel) {

        log.info("#添加主机");
        return hostLinkageService.createHost(hostLinkageModel);
    }

    /**
     *  查询主机信息
     * */
    @RequestMapping(value = "/queryHost", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject queryHost(HostLinkageModel hostLinkageModel) {
        log.info("#查询主机信息");
        return hostLinkageService.queryHost(hostLinkageModel);
    }

    /**
     * 更新主机
     * */
    @RequestMapping(value = "/updateHost", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateHost(HostLinkageModel hostLinkageModel) {

        log.info("#更新主机");
        return hostLinkageService.updateHost(hostLinkageModel);
    }

    /**
     * 删除主机信息
     *
     * */
    @RequestMapping(value = "/deleteHost", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject deleteHost(HostLinkageModel hostLinkageModel) {

        log.info("#删除主机信息");
        return hostLinkageService.deleteHost(hostLinkageModel);
    }

    /**
     * 添加设备
     * */
    @RequestMapping(value = "/createDevice", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        log.info("#添加设备");
        return hostLinkageService.createDevice(hostLinkageDeviceModel);
    }

    /**
     *  查询设备信息
     * */
    @RequestMapping(value = "/queryDevice", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject queryDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {
        log.info("#查询设备信息");
        return hostLinkageService.queryDevice(hostLinkageDeviceModel);
    }

    /**
     * 更新设备
     * */
    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        log.info("#更新设备");
        return hostLinkageService.updateDevice(hostLinkageDeviceModel);
    }


    /**
     * 删除设备信息
     *
     * */
    @RequestMapping(value = "/deleteDevice", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject deleteDevice(HostLinkageDeviceModel hostLinkageDeviceModel) {

        log.info("#删除设备信息");
        return hostLinkageService.deleteDevice(hostLinkageDeviceModel);
    }

    /**
     *  查询关联点信息
     * */
    @RequestMapping(value = "/queryJoinPoint", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject queryJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel) {
        log.info("#查询关联点信息");
        return hostLinkageService.queryJoinPoint(hostLinkageRunningModeModel);
    }
    /**
     *  添加关联点
     * */
    @RequestMapping(value = "/createJoinPoint", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel) {

        log.info("#添加关联点");
        return hostLinkageService.createJoinPoint(hostLinkageRunningModeModel);
    }
    /**
     *  更新关联点
     * */
    @RequestMapping(value = "/updateJoinPoint", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel) {

        log.info("#更新关联点");
        return hostLinkageService.updateJoinPoint(hostLinkageRunningModeModel);
    }

    /**
     *  添加管理记录
     * */
    @RequestMapping(value = "/addGljl", method = RequestMethod.POST)
    @ResponseBody
    public void addGljl(String status) {

        //将操作记录到管理记录中 start
        GljlModel gljlModel =new GljlModel();
        String user = SecurityUtils.getSubject().getPrincipal().toString();//获取当前登录用户
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
        String currentTime = dateFormat.format(now);
        // 自动生成guid
        gljlModel.setF_id(UUIDUtil.getRandom32BeginTimePK());
        gljlModel.setF_username(user);
        //0是维护，1是通信
        gljlModel.setF_type("1");
        gljlModel.setF_gltime(currentTime);
        if(status.equals("1")){
            gljlModel.setF_remark("主机联动-下发通信成功");
        }else{
            gljlModel.setF_remark("主机联动-下发通信失败");
        }
        gljlService.addGljlxx(gljlModel);
    }

}













