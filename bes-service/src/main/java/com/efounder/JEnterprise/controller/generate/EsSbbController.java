package com.efounder.JEnterprise.controller.generate;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.generator.EsSbb;
import com.efounder.JEnterprise.service.generate.EsSbbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/view/EsSbbController")
@Controller
public class EsSbbController {
    private static final Logger log = LoggerFactory.getLogger(EsSbbController.class);
    @Autowired
    private EsSbbService esSbbService;

    /**
     * @description 初始化到界面
     * @return
     */
    @RequestMapping(value = "/getview", method = RequestMethod.GET)
    public String getview() {
        log.info("# ESUserController跳转用户页面");
        return "/view/generate/essbb";
    }

    @RequestMapping(value = "/selectByPrimarykeywords", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject selectByPrimarykeywords(String zzjgbh) {
        log.info("#EsSbbController 根据组织机构编号加载对应用户设备列表");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<EsSbb> list = new ArrayList<EsSbb>();
        list = esSbbService.selectByPrimarykeywords(zzjgbh);
        returnObject.setList(list);
        return returnObject;
    }


    /**
     * 添加sheb信息
     * @param user
     * @return returnObject
     */
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertSelective(@RequestBody EsSbb esSbb) {
        log.info("#EsSbbController添加设备信息");
        ISSPReturnObject returnObject = new ISSPReturnObject();
            boolean isSucc = esSbbService.insertSelective(esSbb);
            if (isSucc) {
                returnObject.setStatus("1");
                returnObject.setMsg("添加用户成功");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                esSbb.setfCrdate(sdf.format(new Date()));
                esSbb.setfChdate(sdf.format(new Date()));
                returnObject.setData(esSbb);
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("添加用户失败");
            }
        return returnObject;

    }

    /**
     * 删除设备信息
     * @return returnObject
     */
    @RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteByPrimaryKey(@RequestBody EsSbb esSbb, ModelMap model) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        log.info("#EsSbbController删除设备信息");

            boolean isSucc = esSbbService.deleteByPrimaryKey(esSbb);

            if (isSucc) {
                returnObject.setStatus("1");
                returnObject.setMsg("删除设备成功");
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("删除设备失败");
            }
        return returnObject;
    }

    /**
     * ajax加载编辑对象
     */
    @RequestMapping(value = "/findsbblId", method = RequestMethod.POST)
    @ResponseBody
    public EsSbb findsbblId(@RequestBody EsSbb esSbb) {
        log.info("#ESUserControllers ajax加载编辑用户对象");
        EsSbb esSbb1 =  esSbbService.findsbblId(esSbb.getId());
        return esSbb1;
    }

    /**
     * 搜索设备信息
     * @param sbbkeywords
     * @return returnObject
     */
    @RequestMapping(value = "/selectbuyjiji", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject selectbuyjiji(@RequestParam(value = "sbbkeywords", required = false) String sbbkeywords) {
        log.info("#selectbuyjiji 搜索设备信息");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<EsSbb> list = new ArrayList<EsSbb>();
        list = esSbbService.selectbuyjiji(sbbkeywords);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == null
                    || "".equals(list.get(i).getId())) {
                list.get(i).getId(null);
            }
        }
        returnObject.setList(list);
        return returnObject;
    }


    /**
     * 修改设备信息
     * @return returnObject
     */
    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateByPrimaryKey(@RequestBody EsSbb esSbb) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        log.info("#EsSbbController修改设备信息");

        boolean isSucc = esSbbService.updateByPrimaryKey(esSbb);

        if (isSucc) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            esSbb.setfChdate(sdf.format(new Date()));
            returnObject.setStatus("1");
            returnObject.setMsg("修改设备成功");
            returnObject.setData(esSbb);
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("修改设备失败");
        }
        return returnObject;
    }


}
