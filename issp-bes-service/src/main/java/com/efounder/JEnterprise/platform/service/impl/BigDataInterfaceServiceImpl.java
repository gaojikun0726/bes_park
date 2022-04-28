package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.efounder.JEnterprise.platform.service.BigDataInterfaceService;
import com.efounder.JEnterprise.service.opm.InterfaceUtil;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * describe: 大数据系统接口
 *
 * @author zs
 * @date 2021/6/24
 */
@Service
public class BigDataInterfaceServiceImpl implements BigDataInterfaceService {

    @Value("${opm.push_electric_data.path}")
    private String path;

    @Value("${opm.push_electric_data.table}")
    private String table;

    @Value("${opm.push_electric_data.appid}")
    private String appId;

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(BigDataInterfaceServiceImpl.class);


    /**
     * 向大数据系统推送用电量数据
     *
     * @param list 用电量数据
     * @return 结果
     */
    @Override
    public String pushElectricData(List<Map<String, Object>> list) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("table",table);
        jsonObject.put("appid",appId);
        jsonObject.put("timestamp",System.currentTimeMillis());
        jsonObject.put("data",list);

        String json = JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
        StringEntity stringEntity = new StringEntity(json,"UTF-8");
        stringEntity.setContentType("application/json");

        String result = InterfaceUtil.doHttpEntity(path,stringEntity);
        logger.info("向大数据系统推送用电量数据，返回结果：" + result);
        return result;
    }
}
