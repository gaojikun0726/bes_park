package com.efounder.JEnterprise.design.controller;


import com.core.common.ISSPReturnObject;
import com.core.common.util.StringUtil;
import com.efounder.JEnterprise.design.service.ButtonEventService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.CrossEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LampEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按钮的关联事件方法
 */
@Controller
@RequestMapping("/btnEventController")
public class ButtonEventController {

    private static final String tableName = "bes_sbpz_struct";

    private static final Logger log = LoggerFactory.getLogger(ButtonEventController.class);
    @Autowired
    private CrossEquipmentService crossEquipmentService;
    @Autowired
    private LampEquipmentService lampEquipmentService;

    @Autowired
    private BESSbdyService besSbdyService;

    @Resource
    private ButtonEventService buttonEventService;

    /**
     * 查询关联点的信息
     * @return
     */
    @RequestMapping(value = "/getPointInfo")
    @ResponseBody
        public ISSPReturnObject getPointInfo(String sysname){
        return buttonEventService.getPointInfo(sysname);
    }

    /**
     * 查询温控器关联模块对应的点位的信息
     * @return
     */
    @RequestMapping(value = "/getTempModuleInfo")
    @ResponseBody
    public ISSPReturnObject getTempModuleInfo(String sysname){
        return buttonEventService.getTempModuleInfo(sysname);
    }

    /**
     * 查询温控器关联模块列表对应的点位的信息
     * @return
     */
    @RequestMapping(value = "/queryModuleListChildInfo")
    @ResponseBody
    public ISSPReturnObject queryModuleListChildInfo(@RequestBody String[] sysnameArray){
        ISSPReturnObject isspReturnObject = null;
        if(sysnameArray != null && sysnameArray.length > 0){
            isspReturnObject = buttonEventService.queryModuleListChildInfo(sysnameArray);
        }
        return isspReturnObject;
    }

    /**
     * 低档温控器开关切换
     * @return
     */
    @RequestMapping(value = "/switchToggle")
    @ResponseBody
    public ISSPReturnObject switchToggle(String sysname){
        return buttonEventService.switchToggle(sysname);
    }

    /**
     * 低档温控器模式切换
     * @return
     */
    @RequestMapping(value = "/lowConditionerModeToggle")
    @ResponseBody
    public ISSPReturnObject lowConditionerModeToggle(String sysname){
        return buttonEventService.lowConditionerModeToggle(sysname);
    }
    /**
     * 低档温控器风速切换
     * @return
     */
    @RequestMapping(value = "/lowConditionerSpeedToggle")
    @ResponseBody
    public ISSPReturnObject lowConditionerSpeedToggle(String sysname){
        return buttonEventService.lowConditionerSpeedToggle(sysname);
    }

    /**
     * 低档温控器调整设定温度
     * @return
     */
    @RequestMapping(value = "/changeSetTemperature")
    @ResponseBody
    public ISSPReturnObject changeSetTemperature(String sysname,String direction,String setNum){
        return buttonEventService.changeSetTemperature(sysname,direction,setNum);
    }

    /**
     * 低档温控器锁定切换
     * @return
     */
    @RequestMapping(value = "/lowConditionerSdToggle")
    @ResponseBody
    public ISSPReturnObject lowConditionerSdToggle(String sysname, String initVal){
        return buttonEventService.lowConditionerSdToggle(sysname,initVal);
    }

    /**
     * 查询温控器关联点位详情
     * @return
     */
    @RequestMapping(value = "/getTempPointInfo")
    @ResponseBody
    public ISSPReturnObject getTempPointInfo(String sysname){
        return buttonEventService.getTempPointInfo(sysname);
    }

    /**
     * 查询温控器关联模块在线情况
     * @return
     */
    @RequestMapping(value = "/getModuleState")
    @ResponseBody
    public ISSPReturnObject getModuleState(String sysname){
        return buttonEventService.getModuleState(sysname);
    }


    /**
     * 查询关联点的信息
     * @return
     */
    @RequestMapping(value = "/getScenePointInfo")
    @ResponseBody
    public String getScenePointInfo(String sysname){
        return buttonEventService.getScenePointInfo(sysname);
    }


    /**
     * 查询设置下拉框的信息
     * @return
     */
    @RequestMapping(value = "/getSettingsInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSettingsInfo(String f_sys_name){

        return buttonEventService.getSettingsInfo(f_sys_name);
    }

    /**
     * 查询设置下拉框的信息
     * @return
     */
    @RequestMapping(value = "/getSceneSettingsInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSceneSettingsInfo(@RequestBody List<String> sysNameList){
        return buttonEventService.getSceneSettingsInfo(sysNameList);
    }



    /**
     * 查询调试按钮关联点信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDebugInfo")
    public ISSPReturnObject getDebugInfo(String sysname){
        //F_NODE_TYPE,F_ENGINEER_UNIT
        ISSPReturnObject returnObject2 = besSbdyService.getdebugNodeInfo(sysname);
        ISSPReturnObject returnObject = buttonEventService.getPointInfo(sysname);
        return returnObject;
    }


    /**
     * 查询点位置、单通道、多场景按钮关联点信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRelatedInfo")
    public ISSPReturnObject  getRelatedInfo(String sysname){
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_guid = buttonEventService.getRelatedInfo(sysname);
        if(f_guid!=null){
            returnObject.setMsg("查询点位信息成功");
            returnObject.setStatus("1");
        }else{
            returnObject.setMsg("请先配置点位信息");
            returnObject.setStatus("0");
        }
        return returnObject;
    }


    /**
     * 查询所有文本框关联点信息
     * @param sysnameArray 关联点sysname数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryTextboxListInfo")
    public ISSPReturnObject queryTextboxListInfo(@RequestBody String[] sysnameArray){
        ISSPReturnObject isspReturnObject = null;
        if(sysnameArray != null && sysnameArray.length > 0){
            isspReturnObject = buttonEventService.queryTextboxListInfo(sysnameArray);
        }

        return isspReturnObject;
    }


    /**
     * 查询所有流程节点关联点信息
     * @param sysnameArray 关联点sysname数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryFlowPointListInfo")
    public ISSPReturnObject queryFlowPointListInfo(@RequestBody String[] sysnameArray){
        ISSPReturnObject isspReturnObject = null;
        if(sysnameArray != null && sysnameArray.length > 0){
            isspReturnObject = buttonEventService.queryFlowPointListInfo(sysnameArray);
        }

        return isspReturnObject;
    }
//    /**
//     * 查询多个点的配置数据
//     * @param sysnameArray 关联点sysname数组
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/queryConfigByArray")
//    public ISSPReturnObject queryConfigByArray(@RequestBody String[] sysnameArray){
//        ISSPReturnObject isspReturnObject = null;
//        if(sysnameArray != null && sysnameArray.length > 0){
//            isspReturnObject = buttonEventService.queryConfigByArray(sysnameArray);
//        }
//        return isspReturnObject;
//    }


    /**
     * 场景按钮切换命令
     * @return
     */
    @RequestMapping(value = "/sceneBtnToggle")
    @ResponseBody
    public ISSPReturnObject sceneBtnToggle(String sceneData) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
         if(StringUtil.isNotEmpty(sceneData)){
             returnObject = buttonEventService.sceneBtnToggle(sceneData);
         }else{
             returnObject.setStatus("0");
         }

        return returnObject;
    }


    /**
     * 获取下位机数据
     * @return
     */
    @RequestMapping(value = "/getLowerData")
    @ResponseBody
    public ISSPReturnObject getLowerData(String sysname){
        ISSPReturnObject returnObject = buttonEventService.getLowerData(sysname);
        return returnObject;
    }

    /**
     * 获取下位机数据
     * @return
     */
    @RequestMapping(value = "/queryModuleType")
    @ResponseBody
    public ISSPReturnObject queryModuleType(String sysname){
        ISSPReturnObject returnObject = buttonEventService.queryModuleType(sysname);
        return returnObject;
    }

    /**
     * 查询虚点的具体类型
     * @param sysname 点位的系统名称
     * @return
     */
    @RequestMapping(value = "/getVisualType")
    @ResponseBody
    public ISSPReturnObject getVisualType(String sysname){
        ISSPReturnObject returnObject = buttonEventService.getVisualType(sysname);
        return returnObject;
    }
}
