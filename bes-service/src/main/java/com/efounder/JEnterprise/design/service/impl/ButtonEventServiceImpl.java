package com.efounder.JEnterprise.design.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.util.StringUtil;
import com.efounder.JEnterprise.design.mapper.ButtonEventMapper;
import com.efounder.JEnterprise.design.service.ButtonEventService;
import com.efounder.JEnterprise.design.util.ConstantValue;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.CrossEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LampEquipmentService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 按钮的关联事件方法
 */
@Service
public class ButtonEventServiceImpl implements ButtonEventService {

    private static final Logger log = LoggerFactory.getLogger(ButtonEventServiceImpl.class);

    @Resource
    private ButtonEventMapper buttonEventMapper;

    @Autowired
    private CrossEquipmentService crossEquipmentService;
    @Autowired
    private LampEquipmentService lampEquipmentService;

    @Resource
    private BESSbdyMapper besSbdyMapper;

    @Autowired
    private BESSbdyService besSbdyService;

    @Autowired
    private SbPzStructCache sbPzStructCache;

    /**
     * 查询关联点的信息
     *
     * @param sysname
     * @return
     */
    @Override
    public ISSPReturnObject getPointInfo(String sysname) {
        ISSPReturnObject object = new ISSPReturnObject();
        Map<String,Object> resultMap = new HashMap<>();
        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
        if (besSbPzStruct == null)
        {
            object.setStatus("0");
        }else{
            resultMap.put("F_INIT_VAL",besSbPzStruct.getF_init_val());
            resultMap.put("F_NODE_ATTRIBUTION",besSbPzStruct.getF_node_attribution());
            resultMap.put("F_SYS_NAME",besSbPzStruct.getF_sys_name());
            resultMap.put("B_TYPE",besSbPzStruct.getF_type());
            resultMap.put("F_NODE_TYPE",besSbPzStruct.getF_type());
            object.setStatus("1");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sysname",sysname);
        // 查询该节点的实体表名
        String tableName = besSbdyMapper.findNodeTable(sysname);
        map.put("tablename",tableName);


        if(Objects.isNull(map.get("tablename"))){
            object.setStatus("0");
        }else{
            Map<String, Object> pointMap = buttonEventMapper.queryRelativePointInfo(map);
//            object.setMap(pointMap);
//            object.setStatus("1");
            resultMap.put("F_WORK_MODE",pointMap.get("F_WORK_MODE"));
            resultMap.put("F_ENGINEER_UNIT",pointMap.get("F_ENGINEER_UNIT"));
        }
        object.setMap(resultMap);
        object.setData(resultMap);
        return object;
    }


    /**
     * 查询温控器模块对应点位的信息
     *
     * @param sysname
     * @return
     */
    @Override
    public ISSPReturnObject getTempModuleInfo(String sysname) {
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Object>> pointInfoList = new ArrayList<>();
        List<BESSbPzStruct>sysNmaeList = besSbdyMapper.findChildNodeInfo(sysname);
        ISSPReturnObject object = new ISSPReturnObject();
        for(int i = 0;i<sysNmaeList.size();i++){
            String tableName = besSbdyMapper.findNodeTable(sysNmaeList.get(i).getF_sys_name());
            map.put("sysname",sysNmaeList.get(i).getF_sys_name());
            map.put("tablename",tableName);
            if(Objects.isNull(tableName)){
                object.setStatus("0");
                return object;
            }else{
                Map<String, Object> pointMap = buttonEventMapper.queryRelativeTempPointInfo(map);
                pointInfoList.add(pointMap);
            }
        }
        object.setList(pointInfoList);
        object.setStatus("1");
        return object;
    }

    @Override
    public ISSPReturnObject getModuleState(String sysname) {
        ISSPReturnObject object = new ISSPReturnObject();
        String moduleStatus = buttonEventMapper.getModuleState(sysname);
        object.setMsg("查询成功");
        object.setData(moduleStatus);
        object.setStatus("1");
        return object;
    }

    /**
     * 查询温控器点位详情
     * @param sysname
     * @return
     */
    @Override
    public ISSPReturnObject getTempPointInfo(String sysname) {
        ISSPReturnObject object = new ISSPReturnObject();
        String sysNameOld = buttonEventMapper.getPointSysNameOld(sysname);
        List<Map<String, Object>> pointInfoList = buttonEventMapper.getTempPointInfo(sysNameOld);
        object.setMsg("查询成功");
        object.setList(pointInfoList);
        object.setStatus("1");
        return object;
    }

    /**
     * 获取关联点的信息
     * @param sysname 关联点的sysname值
     * @return
     */
    @Override
    public Map<String, Object> getOnePointInfo(String sysname) {
        Map<String,Object> map = new HashMap<>();

        map.put("sysname",sysname);
        // 查询该节点的实体表名
        String tableName = besSbdyMapper.findNodeTable(sysname);
        map.put("tablename",tableName);

        if(!Objects.isNull(map.get("tablename"))){
            Map<String, Object> pointMap = buttonEventMapper.queryRelativePointInfo(map);
            return pointMap;
        }

        return null;
    }



    /**
     * 查询设置下拉框内容
     *
     * @param f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject getSettingsInfo(String f_sys_name) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<Object> list = buttonEventMapper.getSettingsInfo(f_sys_name);
            returnObject.setList(list);
            returnObject.setMsg("查询成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("查询失败");
            returnObject.setStatus("0");
        }
        return returnObject;

    }


    /**
     * 查询点位置、单通道、多场景关联点位信息
     * @param sysname
     * @return
     */
    @Override
    public String  getRelatedInfo(String sysname) {
        return buttonEventMapper.getRelatedInfo(sysname);
    }


    /**
     * 查询所有文本框关联点信息
     *
     * @param sysnameArray 所有关联点sysname数组
     * @return
     */
    @Override
    public ISSPReturnObject queryTextboxListInfo(String[] sysnameArray) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("sysnameArray",sysnameArray);
        Map<String,String> valueMap = new HashMap<>();
        //缓存中查询出来的数据是小写，而原先走数据库查询出来的字段是大写的，而且字段名不完全一致，比如工程单位unit和F_ENGINEER_UNIT
        //所以缓存提供实时值，其他的字段数据还是走数据库
        for(String sysname : sysnameArray){
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
            if(besSbPzStruct == null){
                continue;
            }
            String f_init_val = besSbPzStruct.getF_init_val();
            valueMap.put(sysname,f_init_val);
        }
        List<Map> typeList = buttonEventMapper.getNodeListType(queryMap);
        List<String> typeAIList = new ArrayList<>();
        List<String> typeAOList = new ArrayList<>();
        List<String> typeDOList = new ArrayList<>();
        List<String> typeDIList = new ArrayList<>();
        List<String> typeVisualList = new ArrayList<>();
        for(Map itemMap : typeList){
            String type = String.valueOf(itemMap.get("f_type"));
            String sysnameStr = String.valueOf(itemMap.get("sysname"));
//            String[] array = sysnameStr.split(",");
            if(ConstantValue.TYPE_AI.getValue().equals(type)){
//                typeAIList.addAll(Arrays.asList(array));
                typeAIList.add(sysnameStr);
            }
            if(ConstantValue.TYPE_AO.getValue().equals(type)){
//                typeAOList.addAll(Arrays.asList(array));
                typeAOList.add(sysnameStr);
            }
            if(ConstantValue.TYPE_DI.getValue().equals(type)){
                typeDIList.add(sysnameStr);
//                typeDIList.addAll(Arrays.asList(array));
            }
            if(ConstantValue.TYPE_DO.getValue().equals(type)){
                typeDOList.add(sysnameStr);
//                typeDOList.addAll(Arrays.asList(array));
            }
            if(ConstantValue.TYPE_VISUAL.getValue().equals(type)){
                typeVisualList.add(sysnameStr);
//                typeVisualList.addAll(Arrays.asList(array));
            }
        }
        //F_SYS_NAME,F_INIT_VAL,F_ENGINEER_UNIT
        //查询各种类型的节点的信息
        List<Map> typeAIData = queryTypeNodeList(typeAIList,valueMap);
        List<Map> typeAOData = queryTypeNodeList(typeAOList,valueMap);
        List<Map> typeDIData = queryTypeNodeList(typeDIList,valueMap);
        List<Map> typeDOData = queryTypeNodeList(typeDOList,valueMap);
        List<Map> typeVisualData = queryTypeNodeList(typeVisualList,valueMap);
        //所有类型的数据集合
        List<Map> allData = new ArrayList<>();
        allData.addAll(typeAIData);
        allData.addAll(typeAOData);
        allData.addAll(typeDIData);
        allData.addAll(typeDOData);
        allData.addAll(typeVisualData);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("typeAIData",typeAIData);
        resultMap.put("typeAOData",typeAOData);
        resultMap.put("typeDIData",typeDIData);
        resultMap.put("typeDOData",typeDOData);
        resultMap.put("typeVisualData",typeVisualData);
        resultMap.put("allData",allData);
        returnObject.setStatus("1");
        returnObject.setMap(resultMap);
        return returnObject;
    }

    /**
     * 查询温控器关联模块列表对应的点位的信息
     *
     * @param sysnameArray 模块f_sys_name数组
     * @return
     */
    @Override
    public ISSPReturnObject queryModuleListChildInfo(String[] sysnameArray) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("sysnameArray",sysnameArray);
//        List<Map> typeList = buttonEventMapper.getModuleListChildrenType(queryMap);
        List<Map> typeList = buttonEventMapper.getModuleChildrenList(queryMap);
//        List<String> typeAIList = new ArrayList<>();
//        List<String> typeAOList = new ArrayList<>();
//        List<String> typeDOList = new ArrayList<>();
//        for(Map itemMap : typeList){
//            String type = String.valueOf(itemMap.get("f_type"));
//            String sysnameStr = String.valueOf(itemMap.get("sysname"));
//            String[] array = sysnameStr.split(",");
//            if(ConstantValue.TYPE_AI.getValue().equals(type)){
//                typeAIList.addAll(Arrays.asList(array));
//            }
//            if(ConstantValue.TYPE_AO.getValue().equals(type)){
//                typeAOList.addAll(Arrays.asList(array));
//            }
//            if(ConstantValue.TYPE_DO.getValue().equals(type)){
//                typeDOList.addAll(Arrays.asList(array));
//            }
//        }
        //查询各种类型的节点的信息
        List<Map> typeData = queryModuleDigitChildrenNew(typeList);
//        List<Map> typeAIData = queryModuleDigitChildren(typeAIList);
//        List<Map> typeAOData = queryModuleDigitChildren(typeAOList);
//        List<Map> typeDOData = queryModuleDigitChildren(typeDOList);
//        typeAIData.addAll(typeAOData);
//        typeAIData.addAll(typeDOData);
        Map<String,List<Map>> moduleMap = new HashMap<>();
        for(int i = 0; i < sysnameArray.length; i++){
            List<Map> pointList = new ArrayList<>();
            moduleMap.put(sysnameArray[i],pointList);
        }
        for(Map pointMap : typeData){
            String f_psys_name = pointMap.get("F_PSYS_NAME") == null ? "" : pointMap.get("F_PSYS_NAME").toString();
            moduleMap.get(f_psys_name).add(pointMap);
        }
        returnObject.setStatus("1");
        returnObject.setMap(moduleMap);
        return returnObject;
    }

    /**
     * 低档温控器开关切换
     *
     * @param sysname 开关f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject switchToggle(String sysname) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

//        Map<String,Object> pointMap = getOnePointInfo(sysname);
//        String f_init_val = String.valueOf(pointMap.getOrDefault("F_INIT_VAL",""));
        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);

        if (besSbPzStruct == null)
        {
            returnObject.setStatus("0");
            return returnObject;
        }
//        String f_work_mode = String.valueOf(pointMap.getOrDefault("F_WORK_MODE",""));
        String open = ConstantValue.STATE_OPEN.getValue();
        String stop = ConstantValue.STATE_STOP.getValue();
        String value = "";
        if(open.equals(besSbPzStruct.getF_init_val())){
            //开-》关
            value = stop;
        }else{
            value = open;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("f_init_val",value);
//        jsonObject.put("f_work_mode",f_work_mode);
        jsonObject.put("f_sys_name",sysname);
        returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
        return returnObject;
    }

    /**
     * 低档温控器模式切换
     *
     * @param sysname 模式f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject lowConditionerModeToggle(String sysname) {
        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
        if(besSbPzStruct == null){
            ISSPReturnObject returnObject = new ISSPReturnObject();
            returnObject.setStatus("0");
            return returnObject;
        }
        String f_init_val = String.valueOf(besSbPzStruct.getF_init_val());
//        Map<String,Object> pointMap = getOnePointInfo(sysname);
//        String f_init_val = String.valueOf(pointMap.getOrDefault("F_INIT_VAL",""));
//        String f_work_mode = String.valueOf(pointMap.getOrDefault("F_WORK_MODE",""));
        String heating = ConstantValue.MODE_HEATING.getValue();
        String cooling = ConstantValue.MODE_COOLING.getValue();
        String value = "";
        if(heating.equals(f_init_val)){
            value = cooling;
        }else{
            value = heating;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("f_init_val",value);
//        jsonObject.put("f_work_mode",f_work_mode);
        jsonObject.put("f_sys_name",sysname);
        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
        return returnObject;
    }

    /**
     * 低档温控器风速切换
     *
     * @param sysname 风速f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject lowConditionerSpeedToggle(String sysname) {
        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
        if(besSbPzStruct == null){
            ISSPReturnObject returnObject = new ISSPReturnObject();
            returnObject.setStatus("0");
            return returnObject;
        }
        String f_init_val = String.valueOf(besSbPzStruct.getF_init_val());
//        Map<String,Object> pointMap = getOnePointInfo(sysname);
//        String f_init_val = String.valueOf(pointMap.getOrDefault("F_INIT_VAL",""));
//        String f_work_mode = String.valueOf(pointMap.getOrDefault("F_WORK_MODE",""));
        Integer highSpeed = Integer.valueOf(ConstantValue.WIND_SPEED_HIGH.getValue());
        Integer middleSpeed = Integer.valueOf(ConstantValue.WIND_SPEED_MIDDLE.getValue());
        Integer lowSpeed = Integer.valueOf(ConstantValue.WIND_SPEED_LOW.getValue());
        Integer enery = Integer.valueOf(ConstantValue.WIND_SPEED_ENERGY.getValue());
        Integer init_val = Double.valueOf(f_init_val).intValue();
        Integer value = null;
        if(lowSpeed.equals(init_val)){
            //低速-》中速
            value = middleSpeed;
        }else if(middleSpeed.equals(init_val)){
            //中速-》高速
            value = highSpeed;
        }else if(highSpeed.equals(init_val)){
            //高速-》节能
            value = enery;
        }else if(enery.equals(init_val)){
            //节能-》低速
            value = lowSpeed;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("f_init_val",value);
//        jsonObject.put("f_work_mode",f_work_mode);
        jsonObject.put("f_sys_name",sysname);
        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
        return returnObject;
    }

    /**
     * 低档温控器调整设定温度
     *
     * @param sysname 设定温度f_sys_name
     * @param direction 设定方向
     * @return
     */
    @Override
    public ISSPReturnObject changeSetTemperature(String sysname, String direction, String setNum) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
//        Map<String,Object> pointMap = getOnePointInfo(sysname);
//        String fInitVal = String.valueOf(pointMap.getOrDefault("F_INIT_VAL",""));
//        String fWorkMode = String.valueOf(pointMap.getOrDefault("F_WORK_MODE",""));

        String fMinVal = ConstantValue.SET_TEMPERATURE_MIN.getValue();
        String fMaxVal = ConstantValue.SET_TEMPERATURE_MAX.getValue();
        if(setNum != null && !"".equals(setNum)){
            BigDecimal value = new BigDecimal(setNum);
            BigDecimal min = new BigDecimal(fMinVal);
            BigDecimal max = new BigDecimal(fMaxVal);
            String up = ConstantValue.TEMPERATURE_UP.getValue();
            String down = ConstantValue.TEMPERATURE_DOWN.getValue();
            BigDecimal num = BigDecimal.valueOf(0.5);
            if(up.equalsIgnoreCase(direction)){
                //加
                BigDecimal sum = value.add(num);
                if(sum.compareTo(max) < 1){
                    //小于等于
                    value = sum;
                }else{
                    value = max;
                }
            }else{
                //减
                BigDecimal difference = value.subtract(num);
                if(difference.compareTo(min) > -1){
                    //大于等于
                    value = difference;
                }else{
                    value = min;
                }
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("f_init_val",value);
//            jsonObject.put("f_work_mode",fWorkMode);
            jsonObject.put("f_sys_name",sysname);
            returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
        }else{
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 获取下位机数据
     *
     * @param sysname 模块f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject getLowerData(String sysname) {
        // 查询该节点的实体表名
        String tableName = besSbdyMapper.findNodeTable(sysname);
        Map<String,Object> map = getOnePointInfo(sysname);
        String fId = map.get("F_ID") == null ? "" : map.get("F_ID").toString();
        String fNodeAttribution = String.valueOf(map.getOrDefault("F_NODE_ATTRIBUTION",""));
        ISSPReturnObject returnObject = besSbdyService.getPointParam(tableName,sysname,fNodeAttribution,fId);
        return returnObject;
    }

    /**
     * 查询模块类型
     *
     * @param sysname f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject queryModuleType(String sysname) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_moduleType = buttonEventMapper.queryModuleType(sysname);
        returnObject.setData(f_moduleType);
        returnObject.setMsg("查询成功");
        returnObject.setStatus("1");
        return returnObject;
    }

    /**
     * 查询虚点的具体类型
     *
     * @param sysname f_sys_name
     * @return
     */
    @Override
    public ISSPReturnObject getVisualType(String sysname) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_moduleType = buttonEventMapper.getVisualType(sysname);
        returnObject.setData(f_moduleType);
        returnObject.setMsg("查询成功");
        returnObject.setStatus("1");
        return returnObject;
    }

    /**
     * 低档温控器锁定功能切换
     * @param sysname
     * @param initVal
     * @return
     */
    @Override
    public ISSPReturnObject lowConditionerSdToggle(String sysname, String initVal) {
//        Map<String,Object> pointMap = getOnePointInfo(sysname);
//        BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
//        String f_init_val = String.valueOf(besSbPzStruct.getF_init_val());
//        String f_work_mode = String.valueOf(pointMap.getOrDefault("F_WORK_MODE",""));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("f_init_val",initVal);
//        jsonObject.put("f_work_mode",f_work_mode);
        jsonObject.put("f_sys_name",sysname);
        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
        return returnObject;
    }

//    /**
//     * 查询多个点的配置数据
//     *
//     * @param sysnameArray 多个点的sysname数组
//     * @return
//     */
//    @Override
//    public ISSPReturnObject queryConfigByArray(String[] sysnameArray) {
//        Map<String,Object> queryMap = new HashMap<>();
//        queryMap.put("sysnameArray",sysnameArray);
//        List<Map> list = buttonEventMapper.queryConfigByList(queryMap);
//        ISSPReturnObject returnObject = new ISSPReturnObject();
//        returnObject.setStatus("1");
//        returnObject.setList(list);
//        return returnObject;
//    }
    @Override
    public String  getScenePointInfo(String sysname) {
        String re;
        Map<String,Object> map = new HashMap<>();
        List<String> listSysName = new ArrayList<>();
        List<Map> resultList = new ArrayList<Map>();
        if(sysname.length()>1)
        sysname = sysname.substring(0, sysname.length() - 1);
        List<String> result = Arrays.asList(sysname.split(","));
        for(String item: result){
            Map<String,Object> strMap = new HashMap();
            String tableName = besSbdyMapper.findNodeTable(item);
            map.put("tablename",tableName);
            if(Objects.isNull(map.get("tablename"))){
                strMap.put("status","0");
                resultList.add(strMap);
            }else{
                map.put("sysname",item);
                Map<String, Object> pointMap = buttonEventMapper.queryRelativePointInfo(map);
                //Map<String, Object> pointMap = buttonEventMapper.queryRelativePointInfo(map);
                strMap.put("pointMap",pointMap);
                strMap.put("status","1");
                resultList.add(strMap);

            }
        }
        JSONArray jsonArray = JSONArray.fromObject(resultList);
        re = jsonArray.toString();
        return re;
    }

    /**
     * 查询多场景状态下拉框值
     * @param sysNameList
     * @return
     */
    @Override
    public ISSPReturnObject getSceneSettingsInfo(List<String> sysNameList) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Object>allList =new ArrayList<>();
        for(int i=0;i<sysNameList.size();i++){
            String f_sys_name = sysNameList.get(i);
            try {
                List<Object> list = buttonEventMapper.getSettingsInfo(f_sys_name);
                allList.add(list);
            } catch (Exception e) {
                returnObject.setMsg("查询失败");
                returnObject.setStatus("0");
                return returnObject;
            }
        }
        returnObject.setList(allList);
        returnObject.setStatus("1");
        return returnObject;
    }


    /**
     * 场景按钮切换命令
     *
     * @param sceneData 场景按钮关联的点的配置数据
     * @return
     */
    @Override
    public ISSPReturnObject sceneBtnToggle(String sceneData) {
        //解析配置字符串
        List<Map<String,String>> mapList = handleSceneConfigData(sceneData);
        //点位信息列表
        List<JSONObject> jsonList = new ArrayList<>();
        //点位初始值与配置值相同的数量
        int same = 0;
        //点位初始值与配置值不同的数量
        int diff = 0;
        //执行成功的通道数
        int success = 0;
        //执行失败的通道数
        int fail = 0;
        //故障的通道数
        int error = 0;

        //判断点位初始值与配置值是否相同，组装JSONObject格式的数据
        for(Map<String,String> itemMap : mapList){
            String state = itemMap.get("state");
            String sysname = itemMap.get("name");
            if(StringUtil.isNotEmpty(sysname)){
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
//                Map<String,Object> pointMap =  getOnePointInfo(sysname);
                if(besSbPzStruct != null) {
                    String f_init_val = String.valueOf(besSbPzStruct.getF_init_val());
                    JSONObject obj = new JSONObject();
                    obj.put("f_node_attribution", besSbPzStruct.getF_node_attribution());
                    obj.put("f_sys_name", besSbPzStruct.getF_sys_name());
//                    obj.put("f_work_mode",pointMap.get("F_WORK_MODE"));
                    obj.put("f_init_val", f_init_val);
                    obj.put("config_val", state);
                    jsonList.add(obj);

//                if(MapUtils.isNotEmpty(pointMap)){
//                    String f_init_val = String.valueOf(pointMap.getOrDefault("F_INIT_VAL",""));
//                    JSONObject obj = new JSONObject();
//                    obj.put("f_node_attribution",pointMap.get("F_NODE_ATTRIBUTION"));
//                    obj.put("f_sys_name",pointMap.get("F_SYS_NAME"));
//                    obj.put("f_work_mode",pointMap.get("F_WORK_MODE"));
//                    obj.put("f_init_val",f_init_val);
//                    obj.put("config_val",state);
//                    jsonList.add(obj);

                    if(StringUtil.isNotEmpty(state) && state.equals(f_init_val)){
                        itemMap.put("judge","same");
                        same++;
                    }else{
                        itemMap.put("judge","diff");
                        diff++;
                    }
                }
            }
        }

        String successResult = ConstantValue.SUCCESS.getValue();
        if(same == mapList.size()){
            String open = ConstantValue.STATE_OPEN.getValue();
            String stop = ConstantValue.STATE_STOP.getValue();
            //全部的点位配置值与数据库值都相同，那么就全部置为关
            for(JSONObject jsonObject : jsonList){
                jsonObject.put("f_init_val",stop);
                ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
                if(successResult.equals(returnObject.getStatus())){
                    success++;
                }else{
                    fail++;
                }
            }

        }else{
            String open = ConstantValue.STATE_OPEN.getValue();
            String stop = ConstantValue.STATE_STOP.getValue();
            Boolean flag = false;
            for(JSONObject jsonObject : jsonList){
                //初始值
                String f_init_val = String.valueOf(jsonObject.getOrDefault("f_init_val",""));
                if(f_init_val.equals(stop)){
                    flag = true;
                    break;
                }

            }
            //点位配置值与数据库值不完全相同，那么就全部置为配置值
            for(JSONObject jsonObject : jsonList){
                //初始值
                String f_init_val = String.valueOf(jsonObject.getOrDefault("f_init_val",""));
                //配置值
                String config_val = String.valueOf(jsonObject.getOrDefault("config_val",""));
                //如果初始值和配置值相同，不必执行方法
                if(StringUtil.isNotEmpty(config_val) && config_val.equals(f_init_val)&&f_init_val.equals(stop)){
                    success++;
                    continue;
                }
                if(StringUtil.isNotEmpty(config_val) ){
                    if(flag){//有一个为0的情况
                        jsonObject.put("f_init_val",open);
                        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
                        if(successResult.equals(returnObject.getStatus())){
                            success++;
                        }else{
                            fail++;
                        }
                    }else{
                        jsonObject.put("f_init_val",stop);
                        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
                        if(successResult.equals(returnObject.getStatus())){
                            success++;
                        }else{
                            fail++;
                        }
                    }
                   /* if(f_init_val.equals(breakdown)||f_init_val.equals(open)){
                        //初始值和配置值不同，执行方法
                        jsonObject.put("f_init_val",stop);
                        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
                        if(successResult.equals(returnObject.getStatus())){
                            success++;
                        }else{
                            fail++;
                        }
                    }else{
                        //初始值和配置值不同，执行方法
                        jsonObject.put("f_init_val",config_val);
                        ISSPReturnObject returnObject = besSbdyService.sbdy_put_up_point(jsonObject);
                        if(successResult.equals(returnObject.getStatus())){
                            success++;
                        }else{
                            fail++;
                        }
                    }*/

                }
            }
        }

        Map map = new HashMap();
        map.put("success",success);
        map.put("fail",fail);
        map.put("error",error);
        ISSPReturnObject result = new ISSPReturnObject();
        result.setMap(map);
        result.setStatus("1");
        return result;
    }



    /**
     * 解析场景按钮配置字符串
     * @param sceneData
     * @return
     */
    public List<Map<String,String>> handleSceneConfigData(String sceneData){
        List<Map<String,String>> mapList = new ArrayList<>();
        //解析配置字符串
        if(sceneData != null && !"".equals(sceneData)){
            String[] array = sceneData.split(";");
            for(String item : array){
                if(StringUtil.isNotEmpty(item)){
                    Map<String,String> itemMap = new HashMap<>();
                    String[] itemArray = item.split(",");
                    if(itemArray.length == 2){
                        String nameStr = itemArray[0];
                        String stateStr = itemArray[1];
                        if(StringUtil.isNotEmpty(nameStr)){
                            String[] nameArray = nameStr.split(":");
                            if(nameArray.length == 2){
                                String name = nameArray[1];
                                itemMap.put("name",name);
                            }
                        }
                        if(StringUtil.isNotEmpty(stateStr)){
                            String[] stateArray = stateStr.split(":");
                            if(stateArray.length == 2){
                                String state = stateArray[1];
                                itemMap.put("state",state);
                            }
                        }
                    }
                    mapList.add(itemMap);
                }
            }
        }
        return mapList;
    }

    /**
     * 查询各种类型的节点的信息
     * @param typeList 节点sysname集合
     * @return
     */
    public List<Map> queryTypeNodeList(List<String> typeList,Map<String,String> valueMap){
        List<Map> list = new ArrayList<>();
        Map<String,Object> queryMap = new HashMap<>();
//        queryMap.put("tree_table",ConstantValue.TABLE_NAME_DEVICE.getValue());
        if(typeList != null && typeList.size() > 0){
            String sysname = typeList.get(0);
            //查询出各种类型节点对应的表名
            String tableName = besSbdyMapper.findNodeTable(sysname);
            queryMap.put("type_table",tableName);
            queryMap.put("typeList",typeList);
            list = buttonEventMapper.queryTypeNodeList(queryMap);
        }
        for(Map map : list){
            String sysname = map.get("F_SYS_NAME") == null ? "" : map.get("F_SYS_NAME").toString();
            map.put("F_INIT_VAL",valueMap.get(sysname));
        }
        return list;
    }

    /**
     * 查询模块的(AI/AO)子节点的信息，和模块名
     * @param typeList 节点sysname集合
     * @return
     */
    public List<Map> queryModuleAnalogChildren(List<String> typeList){
        List<Map> list = new ArrayList<>();
        Map<String,Object> queryMap = new HashMap<>();
        if(typeList != null && typeList.size() > 0){
            String sysname = typeList.get(0);
            //查询出各种类型节点对应的表名
            String tableName = besSbdyMapper.findNodeTable(sysname);
            queryMap.put("type_table",tableName);
            queryMap.put("typeList",typeList);
            list = buttonEventMapper.queryModuleAnalogChildren(queryMap);
        }
        return list;
    }


    /**
     * 查询模块的(DI/DO)子节点的信息，和模块名
     * @param typeList 节点sysname集合
     * @return
     */
    public List<Map> queryModuleDigitChildrenNew(List<Map> typeList){
        List<Map> list = new ArrayList<>();
        for(Map itemMap : typeList){
            if(itemMap == null || itemMap.isEmpty()){
                continue;
            }
//            Object sysnameObject = itemMap.get("sysname");
            String sysname = ObjectUtils.toString(itemMap.get("sysname"));
//            String sysnameStr = sysnameObject == null ? "" : sysnameObject.toString();
//            String[] array = sysnameStr.split(",");
//            List<String> sList = Arrays.asList(array);
//            for(String sysname : sList){
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
                if(besSbPzStruct == null){
//                    log.error("缓存中besSbPzStruct为空的sysname="+sysname);
                    continue;
                }
                Map map = new HashMap();
                map.put("F_TYPE",besSbPzStruct.getF_type());
                map.put("F_SYS_NAME",besSbPzStruct.getF_sys_name());
                map.put("F_INIT_VAL",besSbPzStruct.getF_init_val());
                map.put("F_PSYS_NAME",besSbPzStruct.getF_psys_name());
                map.put("F_STATUS",besSbPzStruct.getF_status());
                list.add(map);
//            }
    }
        return list;
    }
    /**
     * 查询模块的(DI/DO)子节点的信息，和模块名
     * @param typeList 节点sysname集合
     * @return
     */
    public List<Map> queryModuleDigitChildren(List<String> typeList){
        List<Map> list = new ArrayList<>();
        Map<String,Object> queryMap = new HashMap<>();
        if(typeList != null && typeList.size() > 0){
            String sysname = typeList.get(0);
            //查询出各种类型节点对应的表名
            String tableName = besSbdyMapper.findNodeTable(sysname);
            queryMap.put("type_table",tableName);
            queryMap.put("typeList",typeList);
            list = buttonEventMapper.queryModuleDigitChildren(queryMap);
        }
        return list;
    }

    /**
     * 查询所有流程节点关联点信息
     *
     * @param sysnameArray 所有关联点sysname数组
     * @return
     */
    @Override
    public ISSPReturnObject queryFlowPointListInfo(String[] sysnameArray) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("sysnameArray",sysnameArray);
        List<Map> typeList = buttonEventMapper.getFlowNodeListType(queryMap);
        List<String> typeAIList = new ArrayList<>();
        List<String> typeDOList = new ArrayList<>();
        List<String> typeDIList = new ArrayList<>();
        List<String> typeAOList = new ArrayList<>();
        for(Map itemMap : typeList){
            String type = String.valueOf(itemMap.get("f_type"));
            String sysnameStr = String.valueOf(itemMap.get("sysname"));
            String[] array = sysnameStr.split(",");
            if(ConstantValue.TYPE_AI.getValue().equals(type)){
                typeAIList.addAll(Arrays.asList(array));
            }
            if(ConstantValue.TYPE_AO.getValue().equals(type)){
                typeAOList.addAll(Arrays.asList(array));
            }
            if(ConstantValue.TYPE_DI.getValue().equals(type)){
                typeDIList.addAll(Arrays.asList(array));
            }
            if(ConstantValue.TYPE_DO.getValue().equals(type)){
                typeDOList.addAll(Arrays.asList(array));
            }
        }
        //查询各种类型的节点的信息
        List<Map> typeAIData = queryFlowTypeNodeList(typeAIList);
        List<Map> typeAOData = queryFlowTypeNodeList(typeAOList);
        List<Map> typeDIData = queryFlowTypeNodeList(typeDIList);
        List<Map> typeDOData = queryFlowTypeNodeList(typeDOList);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("typeAIData",typeAIData);
        resultMap.put("typeAOData",typeAOData);
        resultMap.put("typeDIData",typeDIData);
        resultMap.put("typeDOData",typeDOData);

        returnObject.setStatus("1");
        returnObject.setMap(resultMap);
        return returnObject;
    }

    /**
     * 查询流程图各种类型的节点的信息
     * @param typeList 节点sysname集合
     * @return
     */
    public List<Map> queryFlowTypeNodeList(List<String> typeList){
        List<Map> list = new ArrayList<>();
        Map<String,Object> queryMap = new HashMap<>();
//        queryMap.put("tree_table",ConstantValue.TABLE_NAME_DEVICE.getValue());
        if(typeList != null && typeList.size() > 0){
            String sysname = typeList.get(0);
            //查询出各种类型节点对应的表名
            String tableName = besSbdyMapper.findFlowNodeTable(sysname);
            queryMap.put("type_table",tableName);
            queryMap.put("typeList",typeList);
            list = buttonEventMapper.queryFlowTypeNodeList(queryMap);
        }
        return list;
    }


}
