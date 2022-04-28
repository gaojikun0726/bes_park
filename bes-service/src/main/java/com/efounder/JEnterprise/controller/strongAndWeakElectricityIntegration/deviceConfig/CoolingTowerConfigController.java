package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 电表配置
 * @author xiepufeng
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig")
public class CoolingTowerConfigController {
	private static final Logger log = LoggerFactory.getLogger(CoolingTowerConfigController.class);

    @Resource
	private CoolingTowerConfigService coolingTowerConfigService;

    @Resource
    private GljlService gljlService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#ColdHeatSourceController 进入电表监控页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig";
    }

    /**
	 *wanghongjie
     *  查询冷却塔关联电表的配置信息
     * */
    @RequestMapping(value = "/queryTownCooling", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryTownCooling(CoolingTowerConfigModel coolingTowerConfigModel) {
        log.info("#查询冷冻水配置信息");
        return coolingTowerConfigService.queryTownCooling(coolingTowerConfigModel);
    }

    /**
     * 添加冷却塔配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(CoolingTowerConfigModel coolingTowerConfigModel) {
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
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark("添加冷却塔配置名称:"+coolingTowerConfigModel.getName());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#添加冷却塔配置");
        return coolingTowerConfigService.create(coolingTowerConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, CoolingTowerConfigModel coolingTowerConfigModel) {

        PageInfo<Object> page = coolingTowerConfigService.getPaging(pageSize, pageNum, coolingTowerConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfigPage";
    }

    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(CoolingTowerConfigModel coolingTowerConfigModel) {
        log.info("#查询电表配置信息");
        return coolingTowerConfigService.query(coolingTowerConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(CoolingTowerConfigModel coolingTowerConfigModel) {
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
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark("修改冷却塔配置id:"+coolingTowerConfigModel.getId());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#更新冷却塔配置信息");
        return coolingTowerConfigService.update(coolingTowerConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(CoolingTowerConfigModel coolingTowerConfigModel) {
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
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark("删除冷却塔配置id:"+coolingTowerConfigModel.getId());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#删除冷却塔配置信息");
        return coolingTowerConfigService.delete(coolingTowerConfigModel);
    }

}













