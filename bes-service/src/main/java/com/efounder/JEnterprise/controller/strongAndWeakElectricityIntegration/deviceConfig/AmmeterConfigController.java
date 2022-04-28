package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigService;
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
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig")
public class AmmeterConfigController {
	private static final Logger log = LoggerFactory.getLogger(AmmeterConfigController.class);

    @Resource
	private AmmeterConfigService ammeterConfigService;

    @Resource
    private GljlService gljlService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#ColdHeatSourceController 进入电表监控页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig";
    }

    /**
     * 添加电表配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(AmmeterConfigModel ammeterConfigModel) {
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
        gljlModel.setF_remark("添加电表名称:"+ammeterConfigModel.getName());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#添加电表配置");
        return ammeterConfigService.create(ammeterConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, AmmeterConfigModel ammeterConfigModel) {

        PageInfo<Object> page = ammeterConfigService.getPaging(pageSize, pageNum, ammeterConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfigPage";
    }

    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(AmmeterConfigModel ammeterConfigModel) {
        log.info("#查询电表配置信息");
        return ammeterConfigService.query(ammeterConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(AmmeterConfigModel ammeterConfigModel) {
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
        gljlModel.setF_remark("修改电表id:"+ammeterConfigModel.getId()+"名称:"+ammeterConfigModel.getName());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#更新电表配置信息");
        return ammeterConfigService.update(ammeterConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(AmmeterConfigModel ammeterConfigModel) {
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
        gljlModel.setF_remark("删除电表id:"+ammeterConfigModel.getId());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#删除电表配置信息");
        return ammeterConfigService.delete(ammeterConfigModel);
    }

}













