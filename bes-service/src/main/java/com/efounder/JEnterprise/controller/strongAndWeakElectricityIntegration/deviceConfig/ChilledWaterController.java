package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterService;
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
import java.util.*;

/**
 *冷冻水配置
 *王红杰
 *
* */

@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater")
public class ChilledWaterController {
    private static final Logger log = LoggerFactory.getLogger(CoolingHeatingUnitController.class);
    @Resource
    private ChilledWaterService ChilledWaterService;
    @Resource
    private GljlService gljlService;

    @GetMapping("/page")
    public String showPage() {
        log.info("#ChilledWaterController 进入冷冻水监控页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater";
    }

/**
     *
     * 筛选查询，分页查询
     */

    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, ChilledWaterModel chilledWaterModel) {

        PageInfo<Object> page = ChilledWaterService.getPaging(pageSize, pageNum, chilledWaterModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWaterPage";
    }

    /**
     * 添加冷冻水配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(ChilledWaterModel chilledWaterModel) {
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
        if(chilledWaterModel.getF_TYPE_ID().equals("1")){
            gljlModel.setF_remark("添加冷冻水配置id:"+chilledWaterModel.getF_ID());
        }else{
            gljlModel.setF_remark("添加冷却水配置id:"+chilledWaterModel.getF_ID());
        }
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#添加冷冻水配置");
        return ChilledWaterService.creat(chilledWaterModel);
    }

    /**
     *  查询冷冻水配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(ChilledWaterModel chilledWaterModel) {
        log.info("#查询冷冻水配置信息");
        return ChilledWaterService.query(chilledWaterModel);
    }

    /**
     *  查询冷冻水冷却水关联电表的配置信息
     * */
    @RequestMapping(value = "/queryColdCooling", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryColdCooling(ChilledWaterModel chilledWaterModel) {
        log.info("#查询冷冻水配置信息");
        return ChilledWaterService.queryColdCooling(chilledWaterModel);
    }

    /**
     * 更新冷冻水配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(ChilledWaterModel chilledWaterModel) {
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
        if(chilledWaterModel.getF_TYPE_ID().equals("1")){
            gljlModel.setF_remark("修改冷冻水配置id:"+chilledWaterModel.getF_ID()+",名称："+chilledWaterModel.getF_NAME());
        }else{
            gljlModel.setF_remark("修改冷却水配置id:"+chilledWaterModel.getF_ID()+",名称："+chilledWaterModel.getF_NAME());
        }

        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#更新冷冻水配置信息");
        return ChilledWaterService.update(chilledWaterModel);
    }

    /**
     * 删除冷冻水配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(ChilledWaterModel chilledWaterModel) {
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
        if(chilledWaterModel.getF_TYPE_ID().equals("1")){
            gljlModel.setF_remark("删除冷冻水配置id:"+chilledWaterModel.getF_ID());
        }else{
            gljlModel.setF_remark("删除冷却水配置id:"+chilledWaterModel.getF_ID());
        }
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#删除冷冻水配置信息");
        return ChilledWaterService.delete(chilledWaterModel);
    }

    /**
     * 删除冷冻水配置信息
     * */
    @RequestMapping(value = "/detailedinformation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject detailedinformation(String f_equipment_id) {
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
        gljlModel.setF_remark("删除冷冻水配置id:"+f_equipment_id);
        gljlService.addGljlxx(gljlModel);
        //将操作记录到管理记录中 end
        log.info("#更新冷冻水配置信息");
        return ChilledWaterService.detailedinformation(f_equipment_id);
    }

    /**
     * 查询冷冻水配置列表
     * @return
     */
    @RequestMapping(value = "/electricMeterNnumber", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject electricMeterNnumber() {
        log.info("# ChilledWaterController 获取冷冻水配置列表");
        ISSPReturnObject returnObject = ChilledWaterService.electricMeterNnumber();
        return returnObject;
    }

    /**
     *
     * @Description: 根据冷冻水冷却水的id查询变频器运行状态
     *
     * @auther: wanghongjie
     * @date: 14:37 2020/11/11
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/query_F_BPYXZT",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryColdWarmWaterWithF_BPYXZT(String id){
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> f_bpqyxztList = new ArrayList<>();
        Map<String,Object> f_bpqyxztMap = new HashMap<>();


        String f_bpqyxzt = ChilledWaterService.queryColdWarmWaterWithF_BPYXZT(id);

        f_bpqyxztMap.put("f_bpqyxzt",f_bpqyxzt);
        f_bpqyxztList.add(f_bpqyxztMap);
        returnObject.setData(f_bpqyxztList);
        return returnObject;
    }

}

