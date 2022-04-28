package com.efounder.JEnterprise.platform.controller;

import com.efounder.JEnterprise.platform.config.OpmConfig;
import com.efounder.JEnterprise.platform.service.AssetsInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 资产数据控制器
 *
 * @author zs
 * @date 2021/6/23
 */
@Controller
@RequestMapping("/assetInfo")
public class AssetInfoController {

    @Resource
    private OpmConfig opmConfig;


    @Resource
    private AssetsInfoService assetsInfoService;


    /**
     * 查询灯杆设备序列号列表数据
     * @return
     */
    @RequestMapping(value = "/getLightPoleSerialList")
    @ResponseBody
    List<String> getLightPoleSeialList(){
        Map<String,Object> map = new HashMap<>();
        map.put("typeCode", opmConfig.lightPoleCode);
        List<String> list = assetsInfoService.getDeviceSerialList(map);
        return list;
    }

}
