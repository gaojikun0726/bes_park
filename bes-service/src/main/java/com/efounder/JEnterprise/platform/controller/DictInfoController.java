package com.efounder.JEnterprise.platform.controller;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.service.DictInfoService;
import com.efounder.JEnterprise.platform.util.List2Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 字典管理
 *
 * @author zs
 * @date 2021/10/13
 */
@Controller
@RequestMapping("/view/platform/dictInfo")
public class DictInfoController {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(DictInfoController.class);


    @Resource
    private DictInfoService dictInfoService;


    /**
     * 按字典类型查询
     * @param type 字典类型
     * @return
     */
    @RequestMapping("/getDictByType")
    @ResponseBody
    public ISSPReturnObject getDictByType(String type){
        Map<String,String> map = new HashMap<>();
        map.put("type",type);
        List<Map> list = dictInfoService.getDictByType(map);
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        Map dictMap = List2Map.list2Map(list,"value","name");
        isspReturnObject.setStatus("1");
        isspReturnObject.setList(list);
        isspReturnObject.setMap(dictMap);
        return isspReturnObject;
    }

}
