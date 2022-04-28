package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigService;
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
 * 传感器配置
 * @author wzx
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig")
public class CgqConfigController {
	private static final Logger log = LoggerFactory.getLogger(CgqConfigController.class);

    @Resource
	private CgqConfigService cgqConfigService;
    @Resource
    private GljlService gljlService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#ColdHeatSourceController 进入传感器配置页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig";
    }

    /**
     * 添加传感器配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(CgqConfigModel cgqConfigModel) {
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
        gljlModel.setF_remark("添加传感器名称:"+cgqConfigModel.getName());
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end

        log.info("#添加传感器配置");
        return cgqConfigService.create(cgqConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, CgqConfigModel cgqConfigModel) {

        PageInfo<Object> page = cgqConfigService.getPaging(pageSize, pageNum, cgqConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfigPage";
    }

    /**
     *  查询传感器配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(CgqConfigModel cgqConfigModel) {
        log.info("#查询传感器配置信息");
        return cgqConfigService.query(cgqConfigModel);
    }
    /**
     *  根据传感器类型查询传感器配置信息
     * */
    @RequestMapping(value = "/queryByType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryByType(String cgqType,CgqConfigModel cgqConfigModel) {
        log.info("#查询传感器配置信息");
        return cgqConfigService.queryByType(cgqType,cgqConfigModel);
    }
    /**
     * 更新传感器配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(CgqConfigModel cgqConfigModel) {
        //将操作记录到管理记录中 start
        GljlModel gljlModel =new GljlModel();
        String user = SecurityUtils.getSubject().getPrincipal().toString();//获取当前登录用户
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
        String currentTime = dateFormat.format(now);
        gljlModel.setF_id(UUIDUtil.getRandom32BeginTimePK());
        gljlModel.setF_username(user);
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark("添加传感器id:"+cgqConfigModel.getId()+",名称:"+cgqConfigModel.getName());
        gljlService.addGljlxx(gljlModel);
        log.info("#更新传感器配置信息");
        return cgqConfigService.update(cgqConfigModel);
    }

    /**
     * 更新传感器配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(CgqConfigModel cgqConfigModel) {
        //将操作记录到管理记录中 start
        GljlModel gljlModel =new GljlModel();
        String user = SecurityUtils.getSubject().getPrincipal().toString();//获取当前登录用户
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
        String currentTime = dateFormat.format(now);
        gljlModel.setF_id(UUIDUtil.getRandom32BeginTimePK());
        gljlModel.setF_username(user);
        gljlModel.setF_type("0");
        gljlModel.setF_gltime(currentTime);
        gljlModel.setF_remark("删除传感器id:"+cgqConfigModel.getId());
        gljlService.addGljlxx(gljlModel);
        log.info("#删除传感器配置信息");
        return cgqConfigService.delete(cgqConfigModel);
    }

}
