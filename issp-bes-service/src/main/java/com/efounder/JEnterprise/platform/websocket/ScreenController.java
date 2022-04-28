package com.efounder.JEnterprise.platform.websocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import org.apache.commons.lang.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2021/9/8
 */
@Controller
@RequestMapping("/api/public/screen")
public class ScreenController {
    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/platform/screen";

    /**
     * 设备树缓存定义
     */
    @Autowired
    private SbPzStructCache sbPzStructCache;

    private static final Logger log = LogManager.getLogger(ScreenController.class);


    @RequestMapping("/show")
    public String view(){
        return prefix + "/show";
    }


    /**
     * 获取屏上展示的数据
     * @param names 点位的系统名称
     * @return
     */
    @RequestMapping("/getScreenData")
    @ResponseBody
    public JSONObject getScreenData(String names){
        List<Map> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(names);
        for(Object name : jsonArray){
            String sysname = ObjectUtils.toString(name);
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
            if(besSbPzStruct == null){
                continue;
            }
            String value = besSbPzStruct.getF_init_val();
            String unit = besSbPzStruct.getUnit();
            Map<String,String> map = new HashMap<>();
                map.put("name",sysname);
                map.put("value",value);
                map.put("unit",unit);
                list.add(map);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        return jsonObject;
    }

}
