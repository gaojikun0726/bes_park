package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.commhandler.MsgSubPubHandler;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesceneConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesceneConfigService;
import org.ace.business.constant.DDCCmd;
import org.ace.business.constant.LDCCmd;
import org.ace.business.dto.ddc.*;
import org.ace.business.dto.ldc.*;
import org.ace.business.handler.SendMsgHandler;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunzhiyuan
 * @Date:2020/8/31 15:05
 */
@Service("BesceneConfigService")
public class BesceneConfigServiceImpl implements BesceneConfigService {

    @Autowired
    private BesceneConfigMapper besceneConfigMapper;

    //场景模式缓存
    @Autowired
    SceneModeCache sceneModeCache;

    //模式点位缓存
    @Autowired
    ScenePointCache scenePointCache;

    //设备树缓存
    @Autowired
    SbPzStructCache sbPzStructCache;

    //控制器配置定义缓存
    @Autowired
    DdcCache ddcCache;

    //计划缓存
    @Autowired
    PlanCache planCache;

    @Autowired
    private BESSbdyMapper besSbdyMapper;

    // 根据阿里巴巴代码规范，将Pattern设置为全局常量
    // 通过 -?[0-9]+(\\\\.[0-9]+)? 进行匹配是否为数字
    private static Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");

    /**
     * 获取树的数据
     * @return
     */
    @Override
    public ISSPReturnObject getSceneTree() {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<BeSceneInfo> returnList = besceneConfigMapper.getSceneTree();
        returnObject.setData(returnList);
        returnObject.setStatus("1");
        return returnObject;
    }

    /**
     * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
     * @param col 该列当前最大值
     * @return
     */
    private String getAutoIncreaseCol(String col) {
        if (col == null || "".equals(col)) {
            return "1";
        }
        String regex = "^([0]+)([\\d]*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(new StringBuffer(col));
        if (matcher.find()) {
            return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
        } else {
            return String.valueOf(Integer.parseInt(col) + 1);
        }
    }

    /**
     * 添加控制场景的数据
     * @param beSceneInfo
     * @return
     */
    @Override
    public ISSPReturnObject insertContScene(BeSceneInfo beSceneInfo) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        //获取最大ID
        String maxId = besceneConfigMapper.querySceneInfoMaxId();

        beSceneInfo.setF_id(getAutoIncreaseCol(maxId));

        Boolean flag = besceneConfigMapper.insertSceneInfo(beSceneInfo);

        int id = Integer.parseInt(maxId);

        Integer returnId = id + 1;

        if (flag){
            returnObject.setMsg("添加控制场景成功");
            returnObject.setStatus("1");
            returnObject.setData(returnId);
        }else {
            returnObject.setMsg("添加控制场景失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 添加采集场景
     * @param beSceneInfo
     * @return
     */
    public ISSPReturnObject insertCollScene(BeSceneInfo beSceneInfo){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        //获取最大ID
        String maxId = besceneConfigMapper.querySceneInfoMaxId();

        beSceneInfo.setF_id(getAutoIncreaseCol(maxId));

        Boolean flag = besceneConfigMapper.insertSceneInfo(beSceneInfo);

        int id = Integer.parseInt(maxId);

        Integer returnId = id + 1;

        if (flag){
            returnObject.setMsg("添加采集场景成功");
            returnObject.setStatus("1");
            returnObject.setData(returnId);
        }else {
            returnObject.setMsg("添加采集场景失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 修改场景名称
     * @param beSceneInfo
     * @return
     */
    @Override
    public ISSPReturnObject editSceneInfo(BeSceneInfo beSceneInfo) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        Boolean flag = besceneConfigMapper.editSceneInfo(beSceneInfo);

        if (flag){
            returnObject.setMsg("修改场景成功");
            returnObject.setStatus("1");
        }else {
            returnObject.setMsg("修改场景失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 删除一个场景
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject deleteSceneInfo(String id) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_node_attribution = null;//所属系统，cross1、lamp2、energy3

        String channelID = null;

        Integer id1 = Integer.parseInt(id);

        //获取当前场景下所有的模式
        List<BeSceneMode> beSceneModeList = sceneModeCache.getCachedElementById(id);
        if (beSceneModeList != null && beSceneModeList.size() > 0) {
            for (BeSceneMode beSceneMode : beSceneModeList) {
                List<BesPlan> besPlanList = planCache.selectPlanBySceneIdCache(beSceneMode.getF_sceneInfoId());
                if (besPlanList != null && besPlanList.size() > 0) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("当前场景下的模式已配置到计划中,请删除相关计划后操作!");
                    return returnObject;
                }
            }
        }

        List<Map<String,Object>> list = besceneConfigMapper.selectScenePointBySceneId(id);

        if (list.size()!=0){
           String pointId = (String) list.get(0).get("f_pointId");

            //根据点位查询所属系统类型
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(pointId);
            if (besSbPzStruct != null) {
                f_node_attribution = besSbPzStruct.getF_node_attribution();
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("模式下的点位不存在,请确认!");
                return returnObject;
            }

            if ("1".equals(f_node_attribution)) {//楼控

                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                    channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
                } else {
                    Map<String,Object> queryDDCMapByPoint = besceneConfigMapper.queryChannelByIdDDC(pointId);
                    channelID = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
                }

            } else if ("2".equals(f_node_attribution)) {//照明

                Map<String,Object> queryLDCMapByPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                channelID = (String) queryLDCMapByPoint.get("F_CHANNEL_ID");
            }
        }

        try {
            returnObject = deleteSceneModeValue(id);

        } catch (NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败！");
            e.printStackTrace();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败！");
            e.printStackTrace();
        }


        if (f_node_attribution == null) {
            return returnObject;
        }
        if ("1".equals(returnObject.getStatus())) {

            switch (f_node_attribution) {

                case "1"://楼控
                    Boolean flagDDC = SendMsgHandler.deleteSceneDDC(channelID,id1);
                    if (!flagDDC) {
                        returnObject.setStatus("1");
                        returnObject.setMsg("删除成功，下发失败");

                        return returnObject;
                    }

                    // 添加订阅消息
                    MsgSubPubHandler.addSubMsg(DDCCmd.SCENE_DELETE, channelID);
                    returnObject.setStatus("1");
                    returnObject.setMsg("删除成功，下发成功");
                    break;

                case "2"://照明
                    Boolean flagLDC = SendMsgHandler.deleteSceneLDC(channelID,id1);
                    if (!flagLDC) {
                        returnObject.setStatus("1");
                        returnObject.setMsg("删除成功，下发失败");

                        return returnObject;
                    }

                    // 添加订阅消息
                    MsgSubPubHandler.addSubMsg(LDCCmd.SCENE_DELETE, channelID);
                    returnObject.setStatus("1");
                    returnObject.setMsg("删除成功，下发成功");
                    break;
                default:
                    break;
            }

        }
        return returnObject;
    }

    /**
     *
     * @Description: 删除模式点位
     *
     * @auther: wanghongjie
     * @date: 8:39 2021/8/26
     * @param: [id]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject deleteSceneModeValue(String id){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        try {
            Boolean flag = besceneConfigMapper.deleteSceneInfo(id);

            if (flag){
                returnObject.setMsg("删除场景成功");
                returnObject.setStatus("1");
                //判断当前场景下是否有模式
                List<BeSceneMode>  beSceneModes = sceneModeCache.getCachedElementById(id);
                if (beSceneModes == null) {
                    return returnObject;
                }
                Boolean flag1 = besceneConfigMapper.deleteSceneModeBySceneId(id);
                if(!flag1) {
                    throw new Exception("删除场景失败");
                }
                //判断当前场景下是否有点位
                List<BeScenePoint>  beScenePoints = scenePointCache.getCachedElementBySceneInfoId(id);
                if (beScenePoints == null) {
                    return returnObject;
                }
                Boolean flag2 = besceneConfigMapper.deleteScenePointBySceneId(id);
                if(!flag2) {
                    throw new Exception("删除场景失败");
                }
                //删除场景模式的缓存
                for (BeSceneMode beSceneMode : beSceneModes) {
                    sceneModeCache.deleteOneSceneModeCache(beSceneMode.getF_id());
                }
                //删除模式点位的缓存
                for (BeScenePoint beScenePoint: beScenePoints) {
                    scenePointCache.deleteOneScenePointCache(String.valueOf(beScenePoint.getF_id()));
                }

            }else {
                throw new Exception("删除场景失败");
            }
        } catch (NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除场景失败");
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
        }catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除场景失败");
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }


        return returnObject;
    }

    /**
     * 查询table内的指令信息
     * @param id
     * @return
     */
    @Override
    public ISSPReturnObject queryTableParam(String id) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        Map<String,Object> map = besceneConfigMapper.queryTableParam(id); //查询场景信息

        if ("1".equals(map.get("f_type"))) {//控制

        } else {//采集

        }
        List<Map<String,Object>> list = besceneConfigMapper.queryModeParamById(id); //查询Table内的数据(两种情况 1 根节点没有数据 2子节点没有数据)

        //通过判断模式Id是否为null判断是否为子节点
        if (list.size() == 0){ //证明没有数据
            map.put("f_modename","默认模式");
            //String f_id =  besceneConfigMapper.selectSceneModeMaxId(id);
            returnObject.setMap(map);
            returnObject.setList(list);
            returnObject.setStatus("0");
        }else {
            returnObject.setMap(map);
            returnObject.setList(list);
            returnObject.setStatus("1");
        }
        return returnObject;
    }

    /**
     * 新增的table内的数据
     * @param obj
     * @return
     */
    @Override
    @Transactional
    public ISSPReturnObject insertScenePoint(JSONObject obj) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        List<String> pointIdList = (List<String>) obj.get("f_pointId"); //点位Id 获取最大长度 重新组装

        List<String> pointNameList = (List<String>) obj.get("f_pointname"); //点位名称

        String f_sceneInfoId = obj.getString("f_sceneInfoId"); //场景信息Id

        String f_pointType = obj.getString("f_type"); //点位类型

        //判断点位List是否已经在别的采集场景下添加
        //首先获取别的场景下所有的点位
        List<Map<String,Object>> pointMap = besceneConfigMapper.selectPointMap(f_sceneInfoId,f_pointType);
        for (int a = 0; a < pointIdList.size(); a++) {



            for (Map<String,Object> map : pointMap) {
                if (map.get("f_pointId").equals(pointIdList.get(a))) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("点位:" + pointNameList.get(a) + "在" + map.get("sceneInfoName") + "采集场景下重复,请重新选择点位");
                    return returnObject;
                }
            }
        }


        List<Object> modeList = besceneConfigMapper.selectBeSceneModeByInfoId(f_sceneInfoId);

        String nick_name = null;
        String psys_name = null;

        for (int i = 0; i<modeList.size(); i++){

            //首先清空当前控制场景下所有的模式关联的点位以及添加的点位值
            besceneConfigMapper.deleteScenepointByScenemodeId(String.valueOf(modeList.get(i)));
//            besceneConfigMapper.deleteValueUnitbyModeId(String.valueOf(modeList.get(i)));
            //从缓存中获取控制场景下模块所关联的点信息
            List<BeScenePoint>  beScenePointList = scenePointCache.getCachedElementByScenemodeId(String.valueOf(modeList.get(i)));
            if (beScenePointList != null && beScenePointList.size() > 0) {
                for (BeScenePoint beScenePoint : beScenePointList) {
                    scenePointCache.deleteOneScenePointCache(String.valueOf(beScenePoint.getF_id()));
                }
            }

            for (int j =0 ;j < pointIdList.size(); j++){

                BeScenePoint beScenePoint = new BeScenePoint();
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(pointIdList.get(i));
                if (besSbPzStruct != null) {
                    nick_name = besSbPzStruct.getF_nick_name();//别名
                    psys_name = besSbPzStruct.getF_psys_name();//父节点名称

                }

                //通过父级名称查询缓存中的父级信息
                BESSbPzStruct psysNameStruct = sbPzStructCache.getCachedElement(psys_name);
                String psysName = psysNameStruct.getF_psys_name();
                //节点类型,2楼控3照明
                String fType = psysNameStruct.getF_type();
                //循环查询父级,当父节点类型为2楼控,3照明时停止循环
                while(psysName != null && !fType.equals("3") && !fType.equals("2")){
                    psysNameStruct = sbPzStructCache.getCachedElement(psysName);
                    psysName = psysNameStruct.getF_psys_name();
                    fType = psysNameStruct.getF_type();

                };
                //组织点位信息
                String DDCName = psysNameStruct.getF_nick_name();
                beScenePoint.setF_pointnameDetailed("点位:" +besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                        "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                );

                beScenePoint.setF_pointId(pointIdList.get(j));
                beScenePoint.setF_sceneInfoId(f_sceneInfoId);
                beScenePoint.setF_pointype(f_pointType);
                beScenePoint.setF_pointname(pointNameList.get(j));
                beScenePoint.setF_scenemodeId(String.valueOf(modeList.get(i)) );
                Boolean flag = besceneConfigMapper.insertSceneValue(beScenePoint);

                if (flag){
                    returnObject.setStatus("1"); //插入成功
                    returnObject.setMsg("添加点位成功");
                }else {
                    returnObject.setStatus("0"); //插入失败
                    returnObject.setMsg("添加点位失败");
                }
            }
        }
        //刷新模块点位缓存
        scenePointCache.loadCache();

        return returnObject;
    }

    /**
     * 根据Id查询该模式下的值和单位
     * @param obj
     * @return
     */
    @Override
    public ISSPReturnObject selectValueAndUnitById(JSONObject obj) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String nick_name = null;
        String psys_name = null;

        String sceneId = String.valueOf(obj.get("sceneId"));

        String modeId =  String.valueOf(obj.get("modeId"));

        //根据模式id查询模式点位表中的数据
        List<Map<String,Object>> allModeType = besceneConfigMapper.scenePointList(modeId);

        //如果为null 插入空 防止 前台layui不可编辑为null的数据
        for (int i = 0; i< allModeType.size(); i++){


            String value = (String) allModeType.get(i).get("f_value");
            if (value == null){
                allModeType.get(i).put("f_value","无数据");
            }

            String f_unit = (String) allModeType.get(i).get("f_unit");
            if (f_unit == null){
                allModeType.get(i).put("f_unit","无数据");
            }

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById((String) allModeType.get(i).get("f_pointId"));
            if (besSbPzStruct != null) {
                nick_name = besSbPzStruct.getF_nick_name();//别名
                psys_name = besSbPzStruct.getF_psys_name();//父节点名称
                BESSbPzStruct psys_nameStruct = sbPzStructCache.getCachedElement(psys_name);
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("查询失败!");
                return returnObject;
            }

            //通过父级名称查询缓存中的父级信息
            BESSbPzStruct psysNameStruct = sbPzStructCache.getCachedElement(psys_name);
            String psysName = psysNameStruct.getF_psys_name();
            //节点类型,2楼控3照明
            String fType = psysNameStruct.getF_type();
            //循环查询父级,当父节点类型为2楼控,3照明时停止循环
            while(psysName != null && !fType.equals("3") && !fType.equals("2")){
                psysNameStruct = sbPzStructCache.getCachedElement(psysName);
                psysName = psysNameStruct.getF_psys_name();
                fType = psysNameStruct.getF_type();

            };
            //组织点位信息
            String DDCName = psysNameStruct.getF_nick_name();
            allModeType.get(i).put("f_pointname","点位:" +allModeType.get(i).get("f_pointname") + "(" + nick_name + ")" +
                    "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
            );

            /*String f_node_attribution = besSbPzStruct.getF_node_attribution();//所属系统*/
            /*if ("1".equals(f_node_attribution)) {//楼控
                //查询当前点位所属的ddc
                if ("16".equals(besSbPzStruct.getF_type())) {//虚点

                    Map<String, Object> queryDDCMapByVPoint = besSbdyMapper.selectcontrollerByLDCVpoint(besSbPzStruct.getF_sys_name_old());
                    String DDCName = (String) queryDDCMapByVPoint.get("F_NICK_NAME");
                    allModeType.get(i).put("f_pointname","点位:" +allModeType.get(i).get("f_pointname") + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                } else {
                    Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint((String) allModeType.get(i).get("f_pointId"));
                    String DDCName = (String) queryDDCMapByPoint.get("F_NICK_NAME");
                    allModeType.get(i).put("f_pointname","点位:" +allModeType.get(i).get("f_pointname") + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                }

            } else {
                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryLDCMapByVPoint = besSbdyMapper.selectcontrollerByLDCVpoint(besSbPzStruct.getF_sys_name_old());
                    String LDCName = (String) queryLDCMapByVPoint.get("F_NICK_NAME");
                    allModeType.get(i).put("f_pointname","点位:" +allModeType.get(i).get("f_pointname") + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + LDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                } else {
                    Map<String, Object> queryLDCMapByPoint = besSbdyMapper.queryLDCMapByPoint((String) allModeType.get(i).get("f_pointId"));
                    String LDCName = (String) queryLDCMapByPoint.get("F_NICK_NAME");
                    allModeType.get(i).put("f_pointname","点位:" +allModeType.get(i).get("f_pointname") + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + LDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                }

            }*/
        }

        if (allModeType.size()!=0){//证明查出的数据不为O
            returnObject.setStatus("1");
            returnObject.setList(allModeType);
        }else { //证明查出的数据为0
            //如果为0 则查出该场景所有点位
//            List<Map<String,Object>> allModePoint = besceneConfigMapper.selectAllModePoint(sceneId,modeId);
//            returnObject.setList(allModePoint);
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**控制场景
     * 下发指令同时保存到数据库
     * @param obj
     * @return
     */
    @Override
    public ISSPReturnObject insertPointValueUnitAndIssue(JSONObject obj) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        String channelID = null;
        String f_node_attribution = null;//所属系统，cross1、lamp2、energy3

        Integer Type = null; //场景类型

        Map<String,Object> modeData = (Map<String, Object>) obj.get("modeData"); //模式信息

        Map<String,Object> sceneDataMap = (Map<String, Object>) obj.get("sceneData"); //场景信息

        String f_type = (String) sceneDataMap.get("f_type");//场景类型

        List<Map<String,Object>> valueData = (List<Map<String,Object>>) obj.get("pointData"); //点位数据信息

        //根据场景Id查模式信息 因为可能还不存在这条数据
        Map<String, Object> modeMap = besceneConfigMapper.selectRepeatModeId(String.valueOf(modeData.get("f_id"))); //根据场景ID查询是否有重复名称
        if (modeMap == null){//新增
            returnObject = insertNewModeInfo(obj);
        }else {//修改
            returnObject = updateNewModeInfo(obj);
        }
        if (valueData == null || valueData.size() == 0) {
            return returnObject;
        }

        for (Map<String,Object> map : valueData) {

            String f_value = (String) map.get("f_value");
            if ("无数据".equals(f_value)) {
                returnObject.setStatus("0");
                returnObject.setMsg("请添加全部的点位值");
                return returnObject;
            } else {
                // 通过Matcher进行字符串匹配
                Matcher m = pattern.matcher(f_value);
                // 如果正则匹配通过 m.matches() 方法返回 true ，反之 false
                if (!m.matches()) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("请确定点位值是整数");
                    return returnObject;
                };
            }
        }

        for (int i = 0; i< valueData.size(); i++){

            String f_id = (String) valueData.get(i).get("f_pointId");

            //根据点位查询所属系统类型
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(f_id);
            if (besSbPzStruct != null) {
                f_node_attribution = besSbPzStruct.getF_node_attribution();
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("模式名称配置成功,点位配置失败,设备树缓存中未查到当前点位");
                return returnObject;
            }
            //获取父级名称
            String psys_name = besSbPzStruct.getF_psys_name();
            //通过父级名称查询缓存中的父级信息
            BESSbPzStruct psysNameStruct = sbPzStructCache.getCachedElement(psys_name);
            String psysName = psysNameStruct.getF_psys_name();
            //节点类型,2楼控3照明
            String fType = psysNameStruct.getF_type();
            //循环查询父级,当父节点类型为2楼控,3照明时停止循环
            while(psysName != null && !fType.equals("3") && !fType.equals("2")){
                psysNameStruct = sbPzStructCache.getCachedElement(psysName);
                psysName = psysNameStruct.getF_psys_name();
                fType = psysNameStruct.getF_type();
            };
            //获取系统名称
            String sysName = psysNameStruct.getF_sys_name();
            //通过系统名称查询缓存中的通道id
            BesDdc besDdc = ddcCache.getCachedElement(sysName);
            channelID = besDdc.getfChannelId();

            /*if ("1".equals(f_node_attribution)) {//楼控
                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(f_id);
                    channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
                } else {
                    Map<String,Object> queryDDCMapByPoint = besceneConfigMapper.queryChannelByIdDDC(f_id);
                    channelID = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
                }

            } else if ("2".equals(f_node_attribution)) {//照明

                Map<String,Object> queryLDCMapByPoint = besceneConfigMapper.queryChannelByIdLDC(f_id);
                channelID = (String) queryLDCMapByPoint.get("F_CHANNEL_ID");
            }*/

            //根据模式点位表的id修改点位的值和单位
            boolean updateScenepointValue = besceneConfigMapper.updateScenepointValue(valueData.get(i));
            if (updateScenepointValue){
                    //修改模式点位缓存数据
                    scenePointCache.updateOneScenePointValueCache(valueData.get(i));
                    returnObject.setStatus("1");
                    returnObject.setMsg("保存值/单位成功!");
                }else {
                    returnObject.setStatus("0");
                    returnObject.setMsg("保存值/单位失败!");
                }
        }

        switch (f_node_attribution){
            case "1"://楼控

                //控制场景
                ControlParamDDC controlParamDDC = new ControlParamDDC(); //控制场景参数定义

                ControlModeDDC controlModeDDC = new ControlModeDDC(); //控制场景模式定义

                List<ControlPointDDC> controlPoint = new ArrayList<>(); //控制场景点位值/单位定义

                if (f_type.equals("0")){//证明是根节点
                    return null;
                }else if (f_type.equals("1")){//证明是控制场景
                    //场景参数
                    Type = 0 ; //控制场景要求为0

                    controlParamDDC.setSceneType(Type);

                    controlParamDDC.setId(Integer.parseInt((String) sceneDataMap.get("f_id")));

                    controlParamDDC.setName((String) sceneDataMap.get("f_scenename"));

                    controlParamDDC.setAlias((String) sceneDataMap.get("f_scenenickname"));

                    controlParamDDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));
                    //模式参数
                    controlModeDDC.setId((Integer) modeData.get("f_id"));

                    controlModeDDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));

                    controlModeDDC.setName((String) modeData.get("f_modename"));

                    for (int i = 0;i<valueData.size();i++){

                        ControlPointDDC controlPointDDC = new ControlPointDDC(); //模式里点信息 100个数据组

                        Map<String,Object> map = valueData.get(i);

                        controlPointDDC.setPointID(Integer.parseInt((String) map.get("f_pointId")));//别名

                        try {
                            int f_value = Integer.parseInt((String) map.get("f_value"));
                            controlPointDDC.setRunValue(f_value);//数据无
                        }catch (Exception e) {
                            e.printStackTrace();
                            returnObject.setStatus("0");
                            returnObject.setMsg("指令值必须为数字！");
                            return returnObject;
                        }

                        controlPointDDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));

                        controlPoint.add(controlPointDDC);
                    }

                    //组装
                    controlModeDDC.setControlPoint(controlPoint);

                    controlParamDDC.setControlMode(controlModeDDC);

                    boolean sendState = SendMsgHandler.setSceneDDC(channelID,controlParamDDC);

                    if (sendState){
                        returnObject.setStatus("1");
                        returnObject.setMsg("下发成功!修改成功!");
                    }else {
                        returnObject.setStatus("0");
                        returnObject.setMsg("下发失败!修改成功!");
                    }

                }
                // 添加订阅消息
                MsgSubPubHandler.addSubMsg(DDCCmd.SCENE_PARAM_SET, channelID);
                break;
            case"2"://照明

                //控制场景
                ControlParamLDC controlParamLDC = new ControlParamLDC(); //控制场景参数定义

                ControlModeLDC controlModeLDC = new ControlModeLDC(); //控制场景模式定义

                List<ControlPointLDC> controlPointLDCs = new ArrayList<>(); //控制场景点位值/单位定义

                if (f_type.equals("0")){//证明是根节点
                    return null;
                }else if (f_type.equals("1")){//证明是控制场景
                    //场景参数
                    Type = 0 ; //控制场景要求为0

                    controlParamLDC.setSceneType(Type);

                    controlParamLDC.setId(Integer.parseInt((String) sceneDataMap.get("f_id")));

                    controlParamLDC.setName((String) sceneDataMap.get("f_scenename"));

                    controlParamLDC.setAlias((String) sceneDataMap.get("f_scenenickname"));

                    controlParamLDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));
                    //模式参数
                    controlModeLDC.setId((Integer) modeData.get("f_id"));

                    controlModeLDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));

                    controlModeLDC.setName((String) modeData.get("f_modename"));

                    for (int i = 0;i<valueData.size();i++){

                        ControlPointLDC controlPointLDC = new ControlPointLDC(); //模式里点信息 100个数据组

                        Map<String,Object> map = valueData.get(i);

                        controlPointLDC.setPointID(Integer.parseInt((String) map.get("f_pointId")));//别名

                        try {
                            int f_value = Integer.parseInt((String) map.get("f_value"));
                            controlPointLDC.setRunValue(f_value);//数据无
                        }catch (Exception e) {
                            e.printStackTrace();
                            returnObject.setStatus("0");
                            returnObject.setMsg("指令值必须为数字！");
                            return returnObject;
                        }

                        controlPointLDC.setActive(Integer.parseInt((String) sceneDataMap.get("f_active")));

                        controlPointLDCs.add(controlPointLDC);
                    }

                    //组装
                    controlParamLDC.setControlMode(controlModeLDC);

                    controlModeLDC.setControlPoint(controlPointLDCs);

                    boolean sendState = SendMsgHandler.setControlSceneLDC(channelID,controlParamLDC);

                    if (sendState){
                        returnObject.setStatus("1");
                        returnObject.setMsg("下发成功!修改成功!");
                    }else {
                        returnObject.setStatus("0");
                        returnObject.setMsg("下发失败!修改成功!");
                    }

                }
                // 添加订阅消息
                MsgSubPubHandler.addSubMsg(LDCCmd.SCENE_PARAM_SET, channelID);
                break;
            default:
                break;
        }

        return returnObject;
    }

    /**采集场景
     * 下发指令同时保存到数据库
     * @param obj
     * @return
     */
    @Override
    public ISSPReturnObject insertCollectPointValueUnitAndIssue(JSONObject obj) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        BeSceneMode beSceneMode = new BeSceneMode();
        boolean insertOrUpdate = false;

        String sceneInfoType = obj.getString("sceneInfoType");//场景类型
        String f_modename = obj.getString("f_modename");//模式名称
        String sceneInfoId = obj.getString("sceneInfoId");//场景id
        String scenemodeId = obj.getString("scenemodeId");//模式id


        if (!StringUtils.hasText(sceneInfoType) || !StringUtils.hasText(f_modename) || !StringUtils.hasText(sceneInfoId)) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        beSceneMode.setF_modename(f_modename);
        beSceneMode.setF_enable("1");
        beSceneMode.setF_modetype(sceneInfoType);
        beSceneMode.setF_sceneInfoId(sceneInfoId);
        beSceneMode.setF_synchro("0");

        if (scenemodeId == null) {//新增采集场景下模式信息

            //直接添加当前模式到场景模式表
            insertOrUpdate = besceneConfigMapper.insertSceneMode(beSceneMode);
            if (insertOrUpdate) {
                sceneModeCache.loadCache();
            }
        } else {//修改采集场景下模式信息
            beSceneMode.setF_id(scenemodeId);
            insertOrUpdate = besceneConfigMapper.updateSceneMode(beSceneMode);
            sceneModeCache.updateOneSceneModeCache(beSceneMode);
        }

        if (insertOrUpdate) {
            returnObject.setStatus("1");
            returnObject.setMsg("保存成功");
        } else {
            returnObject.setMsg("0");
            returnObject.setStatus("保存失败");
        }


        return returnObject;
    }

    //模式新增
    public ISSPReturnObject insertNewModeInfo(JSONObject object){
        ISSPReturnObject returnObject = new ISSPReturnObject();

        Map<String,Object> modeData = (Map<String, Object>) object.get("modeData"); //模式信息

        Map<String,Object> sceneDataMap = (Map<String, Object>) object.get("sceneData"); //场景信息

        BeSceneMode beSceneMode = new BeSceneMode();//场景模式

//
//        beSceneMode.setF_id(autoId);

//        String f_sceneInfoId = (String) sceneDataMap.get("f_id");
        String f_sceneInfoId = String.valueOf(object.get("sceneInfoId"));

        beSceneMode.setF_modename((String) modeData.get("f_modename"));

        beSceneMode.setF_modetype((String) sceneDataMap.get("f_type"));

        beSceneMode.setF_sceneInfoId(f_sceneInfoId);

        beSceneMode.setF_enable("1");

        beSceneMode.setF_synchro("0");

        //走到这一步证明没有这个模式需要新增上
        Boolean flag = besceneConfigMapper.insertSceneMode(beSceneMode);//添加完模式之后需要给这个点位表里面添加这个模式 保证每个点位都要有这个模式Id

        //先查询出点位表里面的公共数据 每条公共数据插入一条模式Id
        if (flag){

            //更新模式缓存
            sceneModeCache.loadCache();

            List<Map<String,Object>> publicPointValue = besceneConfigMapper.selectPublicScenePoint(f_sceneInfoId);

            if (publicPointValue.size() == 0){
                returnObject.setStatus("0");
                returnObject.setMsg("请先添加公共点位!");
            }else {

                String sceneModeMaxId = besceneConfigMapper.querySceneModeMaxId();

//                String autoId = getAutoIncreaseCol(sceneModeMaxId);

                for (int i = 0;i<publicPointValue.size();i++){

                    BeScenePoint beScenePoint = new BeScenePoint();


                    beScenePoint.setF_pointname((String) publicPointValue.get(i).get("f_pointname"));

                    beScenePoint.setF_scenemodeId(sceneModeMaxId);

                    beScenePoint.setF_pointId((String) publicPointValue.get(i).get("f_pointId"));

                    beScenePoint.setF_sceneInfoId((String) publicPointValue.get(i).get("f_sceneInfoId"));

                    beScenePoint.setF_pointype((String) publicPointValue.get(i).get("f_pointype"));

                    Boolean insertPoint = besceneConfigMapper.insertBeScenePoint(beScenePoint);

                    //更新缓存
                    scenePointCache.loadCache();

                    if (insertPoint){
                        returnObject.setStatus("1");
                        returnObject.setMsg("新增公共点位成功");
                    }else {
                        returnObject.setStatus("0");
                        returnObject.setMsg("新增公共点位成功");
                        return returnObject;
                    }
                }
            }
            returnObject.setStatus("1");
            returnObject.setMsg("新增模式成功");
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("新增模式失败");
        }
        return returnObject;
    }

    //模式修改
    public ISSPReturnObject updateNewModeInfo(JSONObject object){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        Map<String,Object> modeData = (Map<String, Object>) object.get("modeData"); //模式信息

        //可能需要修改的场景模式
        BeSceneMode rebeSceneMode = new BeSceneMode();//场景模式

        rebeSceneMode.setF_id(String.valueOf( modeData.get("f_id")));
        rebeSceneMode.setF_modename((String) modeData.get("f_modename"));
        rebeSceneMode.setF_sceneInfoId((String) modeData.get("f_sceneInfoId"));
        rebeSceneMode.setF_modetype((String) modeData.get("f_modetype"));
        rebeSceneMode.setF_enable((String) modeData.get("f_enable"));
        rebeSceneMode.setF_synchro((String) modeData.get("f_synchro"));

        Boolean flag1 = besceneConfigMapper.updateSceneMode(rebeSceneMode);

        if (flag1){
            sceneModeCache.updateOneSceneModeCache(rebeSceneMode);
            returnObject.setStatus("1");
            returnObject.setMsg("模式修改成功");
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("模式修改失败");
        }
        return returnObject;
    }

    /**
     * 新增场景内的其他信息
     * @param obj
     * @return
     */
    @Override
    public ISSPReturnObject saveSceneInfomation(JSONObject obj){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        Boolean flag = besceneConfigMapper.saveSceneInfomation(obj);

        if (flag){
            returnObject.setStatus("1");
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 查询所有模式
     * @param sceneId
     * @return
     */
    @Override
    public ISSPReturnObject selectModeInfoBySceneId(String sceneId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        List<Map<String,Object>> list = besceneConfigMapper.selectModeInfoBySceneId(sceneId);

        if (list.isEmpty()){
            returnObject.setStatus("0");
            returnObject.setMsg("没有添加模式! 请先添加模式!");
        }else {
            returnObject.setStatus("1");
            returnObject.setList(list);
        }
        return returnObject;
    }

    /**
     * 删除该模式及其下面的点位信息
     * @param modeId
     * @return
     */
    public ISSPReturnObject deleteUnderModeInfo(String modeId, String sceneId){

        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_node_attribution = null;//所属系统，cross1、lamp2、energy3

        String channelID = null;

        //获取当前场景下所有的模式
        List<BesPlan> besPlanList = planCache.getCachedElementByModeId(modeId);
        if (besPlanList != null) {
            returnObject.setStatus("0");
            returnObject.setMsg("模式已配置到计划中,请修改相应计划后操作!");
            return returnObject;
        }

        List<Map<String,Object>> list = besceneConfigMapper.selectScenePointBySceneAndPointId(modeId,sceneId);

        if (list.size()!=0){

            String pointId = (String) list.get(0).get("f_pointId");

            //根据点位查询所属系统类型
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(pointId);
            if (besSbPzStruct != null) {
                f_node_attribution = besSbPzStruct.getF_node_attribution();
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("当前模式下的点位不存在,请确认!");
                return returnObject;
            }

            if ("1".equals(f_node_attribution)) {//楼控

                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                    channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
                } else {
                    Map<String,Object> queryDDCMapByPoint = besceneConfigMapper.queryChannelByIdDDC(pointId);
                    channelID = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
                }

            } else if ("2".equals(f_node_attribution)) {//照明
                Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
            }
        }

        returnObject = deletModeInfomation(modeId,sceneId);

        if ("1".equals(returnObject.getStatus())) {//上位机删除成功
            Integer modeid = Integer.parseInt(modeId);

            Integer sceneid = Integer.parseInt(sceneId);

            if (f_node_attribution == null) {
                returnObject.setMsg("删除成功，不下发");
                return returnObject;
            }
            switch (f_node_attribution){
                case "1"://楼控
                    Boolean deleteModeInfoDDC = SendMsgHandler.deleteSceneAndModeDDC(channelID,sceneid,modeid);
                    if (!deleteModeInfoDDC) {
                        returnObject.setStatus("1");
                        returnObject.setMsg("删除成功，下发失败");

                        return returnObject;
                    }

                    // 添加订阅消息
                    MsgSubPubHandler.addSubMsg(DDCCmd.SCENE_MODE_PARAM_DELETE, channelID);
                    returnObject.setStatus("1");
                    returnObject.setMsg("删除成功，下发成功");
                    break;
                case "2"://照明
                    Boolean deleteModeInfoLDC = SendMsgHandler.deleteSceneAndModeLDC(channelID,sceneid,modeid);
                    if (!deleteModeInfoLDC) {
                        returnObject.setStatus("1");
                        returnObject.setMsg("删除成功，下发失败");

                        return returnObject;
                    }

                    // 添加订阅消息
                    MsgSubPubHandler.addSubMsg(LDCCmd.SCENE_MODE_PARAM_DELETE, channelID);
                    returnObject.setStatus("1");
                    returnObject.setMsg("删除成功，下发成功");
                    break;
                default:
                    break;
            }


        }
        return returnObject;
    }


    /**
     *
     * @Description: 删除上位机模式以及关联的点位信息
     *
     * @auther: wanghongjie
     * @date: 10:38 2021/8/18
     * @param: [modeId, sceneId]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    public ISSPReturnObject deletModeInfomation(String modeId,String sceneId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        //判断当前模式是否在计划中配置
        List<BesPlan> besPlanList = planCache.getCachedElementByModeId(modeId);
        if (besPlanList != null) {
            returnObject.setStatus("0");
            returnObject.setMsg("当前模式已在计划中配置,请删除相应计划后再操作");
            return returnObject;
        }
        //删除模式
        Boolean flag = besceneConfigMapper.deleteScenemodeInfomation(modeId);

        if (flag){
            //删除模式信息
            sceneModeCache.deleteOneSceneModeCache(modeId);

            Boolean flag1 = besceneConfigMapper.deleteScenepointInfomation(modeId);
            if (flag1){
                //删除模式点位相关信息
                List<BeScenePoint> scenePointList  = scenePointCache.getCachedElementByScenemodeId(modeId);
                if (scenePointList != null && scenePointList.size() > 0) {
                    for (BeScenePoint beScenePoint : scenePointList) {
                        String id = String.valueOf(beScenePoint.getF_id());
                        //删除模式点位相关信息
                        scenePointCache.deleteOneScenePointCache(id);
                    }
                }
                returnObject.setStatus("1");
            } else {
                returnObject.setStatus("1");
            }
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }


    @Override
    public ISSPReturnObject queryContrastLocalInfo(String modeId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (modeId == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("请先保存当前模式,再操作!");
            return returnObject;
        }
        List<Map<String,Object>> list = besceneConfigMapper.scenePointList(modeId);

        Map<String,Object> Totalmap = new HashMap<>();

        List<String> pointId = new ArrayList<>();

        List<String> value = new ArrayList<>();

        List<String> pointname = new ArrayList<>();

        List<String> unit = new ArrayList<>();

        for (int i = 0;i<list.size(); i++){
            Map<String,Object> map = list.get(i);
            pointId.add((String) map.get("f_pointId"));
            value.add((String) map.get("f_value"));
            pointname.add((String) map.get("f_pointname"));
            unit.add((String) map.get("f_unit"));
        }
        Totalmap.put("pointId",pointId);
        Totalmap.put("value",value);
        Totalmap.put("pointname",pointname);
        Totalmap.put("unit",unit);
        if (Totalmap.size() !=  0){
            returnObject.setStatus("1");
            returnObject.setMap(Totalmap);
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("请先添加数据再同步!");
        }
        return returnObject;
    }


    /**
     *
     * @Description: 控制模式获取下位机数据
     *
     * @auther: wanghongjie
     * @date: 13:53 2021/8/16
     * @param: [modeId, sceneId, type]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject queryContrastUnderInfo(String modeId, String sceneId, String type) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_node_attribution = null;//所属系统，cross1、lamp2、energy3

        String channelID = null;

        if (modeId == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("请先添加当前模式后,再操作!");
            return returnObject;
        }
        Integer sceneid  =  Integer.parseInt(sceneId);

        Integer modeid  =  Integer.parseInt(modeId);

        if (modeId!=null && sceneId!=null){

            List<Map<String,Object>> list = besceneConfigMapper.selectScenePointBySceneAndPointId(modeId,sceneId);

            if (list.size()!=0){

                String pointId = (String) list.get(0).get("f_pointId");

                //根据点位查询所属系统类型
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(pointId);
                if (besSbPzStruct != null) {
                    f_node_attribution = besSbPzStruct.getF_node_attribution();
                } else {
                    returnObject.setStatus("0");
                    returnObject.setMsg("参数错误!");
                    return returnObject;
                }

                if ("1".equals(f_node_attribution)) {//楼控
                    if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                        Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                        channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
                    } else {
                        Map<String,Object> queryDDCMapByPoint = besceneConfigMapper.queryChannelByIdDDC(pointId);
                        channelID = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
                    }

                } else if ("2".equals(f_node_attribution)) {//照明
                    Map<String,Object> queryLDCMapByPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                    channelID = (String) queryLDCMapByPoint.get("F_CHANNEL_ID");

                }
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("请先关联点位,再操作!");
                return returnObject;
            }
        }

        switch (f_node_attribution) {
            case "1"://楼控

                Boolean getSceneUnderInfoDDC = SendMsgHandler.getSceneAndModeDDC(channelID,sceneid,modeid);
                if (!getSceneUnderInfoDDC) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("下位机数据获取失败!");
                    return returnObject;
                }
                // 添加订阅消息
                MsgSubPubHandler.addSubMsg(DDCCmd.SCENE_MODE_PARAM_GET, channelID);
                returnObject.setStatus("1");
                returnObject.setMsg("下位机数据获取成功!");
                break;
            case "2"://照明
                Boolean getSceneUnderInfoLDC = SendMsgHandler.getSceneAndModeLDC(channelID,sceneid,modeid);
                if (!getSceneUnderInfoLDC) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("下位机数据获取失败!");
                    return returnObject;
                }
                // 添加订阅消息
                MsgSubPubHandler.addSubMsg(LDCCmd.SCENE_MODE_PARAM_GET, channelID);
                returnObject.setStatus("1");
                returnObject.setMsg("下位机数据获取成功!");

                break;
            default:
                break;
        }
        return returnObject;
    }

    /**
     * 同步下位机数据，返回状态是否链接下位机 如果链接下位机直接设置 没有链接返回前台未同步状态
     * @param obj
     * @return
     */
    @Override
    public ISSPReturnObject synchroPointValueUnitAndIssue(JSONObject obj) {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_node_attribution = null;//所属系统，cross1、lamp2、energy3

        String modeId = String.valueOf(obj.get("modeId"));

        String sceneId = String.valueOf(obj.get("sceneId"));

        String channelID = null;

        //控制场景
        ControlParamLDC controlParamLDC = new ControlParamLDC(); //照明:控制场景参数定义
        ControlParamDDC controlParamDDC = new ControlParamDDC(); //楼控:控制场景参数定义

        ControlModeLDC controlModeLDC = new ControlModeLDC(); //照明:控制场景模式定义
        ControlModeDDC collectModeDDC = new ControlModeDDC(); //楼控:控制场景模式定义

        List<ControlPointLDC> controlPoint = new ArrayList<>(); //照明:控制场景点位值/单位定义
        List<ControlPointDDC> collectPoint = new ArrayList<>(); //楼控:控制场景点位值/单位定义

        Map<String,Object> sceneInfo = besceneConfigMapper.selectSceneInfoBySceneId(sceneId);

        Map<String,Object> modeInfo = besceneConfigMapper.selectModeInfoByModeId(modeId);

        List<Map<String,Object>> valueList = besceneConfigMapper.scenePointList(modeId);

        for (Map<String,Object> map: valueList) {
            if (map.get("f_value") == null) {
                returnObject.setStatus("0");
                returnObject.setMsg("请确定模式下点位是否配置值!");
                return returnObject;
            }
        }

        if (sceneInfo == null || modeInfo == null || valueList.size() == 0){
            returnObject.setStatus("0");
            returnObject.setMsg("同步失败, 参数错误!");
            return returnObject;
        }

        if (modeId!=null && sceneId!=null){

            List<Map<String,Object>> list = besceneConfigMapper.selectScenePointBySceneAndPointId(modeId,sceneId);

            if (list.size()!=0){

                String pointId = (String) list.get(0).get("f_pointId");

                //根据点位查询所属系统类型
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById(pointId);
                if (besSbPzStruct != null) {
                    f_node_attribution = besSbPzStruct.getF_node_attribution();
                } else {
                    returnObject.setStatus("0");
                    returnObject.setMsg("同步失败, 点位不存在!");
                    return returnObject;
                }

                if ("1".equals(f_node_attribution)) {//楼控
                    if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                        Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                        channelID = (String) queryDDCMapByVPoint.get("F_CHANNEL_ID");
                    } else {
                        Map<String,Object> queryDDCMapByPoint = besceneConfigMapper.queryChannelByIdDDC(pointId);
                        channelID = (String) queryDDCMapByPoint.get("F_CHANNEL_ID");
                    }
                } else if ("2".equals(f_node_attribution)) {//照明

                    Map<String,Object> queryLDCMapByPoint = besceneConfigMapper.queryChannelByIdLDC(pointId);
                    channelID = (String) queryLDCMapByPoint.get("F_CHANNEL_ID");
                }
            }
        }

        Integer Type = null; //控制场景

        String type = (String) obj.get("sceneType");

        switch (f_node_attribution){
            case "1"://楼控
                if (type.equals("1")){//控制场景

                    Type = 0;


                    String sceneid = (String) sceneInfo.get("f_id");

                    controlParamDDC.setId(Integer.parseInt(sceneid));

                    controlParamDDC.setSceneType(Type);

                    controlParamDDC.setName((String) sceneInfo.get("f_scenename"));

                    controlParamDDC.setAlias((String) sceneInfo.get("f_scenenickname"));

                    controlParamDDC.setActive(Integer.parseInt((String) sceneInfo.get("f_active")));

                    collectModeDDC.setId((Integer) modeInfo.get("f_id"));

                    collectModeDDC.setActive(Integer.parseInt((String) modeInfo.get("f_enable")));

                    collectModeDDC.setName((String) modeInfo.get("f_modename"));

                    for (int i = 0;i<valueList.size();i++){

                        ControlPointDDC controlPointDDC = new ControlPointDDC(); //模式里点信息 100个数据组

                        Map<String,Object> map = valueList.get(i);

                        controlPointDDC.setPointID(Integer.parseInt((String) map.get("f_pointId")));//别名

                        controlPointDDC.setRunValue(Integer.parseInt((String) map.get("f_value")));//数据无

                        controlPointDDC.setActive(Integer.parseInt((String) sceneInfo.get("f_active")));

                        collectPoint.add(controlPointDDC);
                    }

                    //组装
                    controlParamDDC.setControlMode(collectModeDDC);

                    collectModeDDC.setControlPoint(collectPoint);

                    boolean sendState = SendMsgHandler.setSceneDDC(channelID,controlParamDDC);

                    String f_synchro = null;

                    if (sendState){

                        returnObject.setStatus("1");

                        returnObject.setMsg("同步成功!");

                        f_synchro = "1";

                        Boolean flag = besceneConfigMapper.insertIntoSynchroState(f_synchro,modeId);

                        // 添加订阅消息
                        MsgSubPubHandler.addSubMsg(DDCCmd.SCENE_PARAM_SET, channelID);

                    }else {//同步成功失败给模式表添加同步状态

                        f_synchro = "0";

                        Boolean flag = besceneConfigMapper.insertIntoSynchroState(f_synchro,modeId);

                        returnObject.setStatus("0");

                        returnObject.setMsg("同步失败!");
                    }
                }
                break;
            case "2"://照明
                if (type.equals("1")){//控制场景

                    Type = 0;

                    String sceneid = (String) sceneInfo.get("f_id");

                    controlParamLDC.setId(Integer.parseInt(sceneid));

                    controlParamLDC.setSceneType(Type);

                    controlParamLDC.setName((String) sceneInfo.get("f_scenename"));

                    controlParamLDC.setAlias((String) sceneInfo.get("f_scenenickname"));

                    controlParamLDC.setActive(Integer.parseInt((String) sceneInfo.get("f_active")));

                    controlModeLDC.setId((Integer) modeInfo.get("f_id"));

                    controlModeLDC.setActive(Integer.parseInt((String) modeInfo.get("f_enable")));

                    controlModeLDC.setName((String) modeInfo.get("f_modename"));

                    for (int i = 0;i<valueList.size();i++){

                        ControlPointLDC controlPointLDC = new ControlPointLDC(); //模式里点信息 100个数据组

                        Map<String,Object> map = valueList.get(i);

                        controlPointLDC.setPointID(Integer.parseInt((String) map.get("f_pointId")));//别名

                        controlPointLDC.setRunValue(Integer.parseInt((String) map.get("f_value")));//数据无

                        controlPointLDC.setActive(Integer.parseInt((String) sceneInfo.get("f_active")));

                        controlPoint.add(controlPointLDC);
                    }

                    //组装
                    controlParamLDC.setControlMode(controlModeLDC);

                    controlModeLDC.setControlPoint(controlPoint);

                    boolean sendState = SendMsgHandler.setControlSceneLDC(channelID,controlParamLDC);

                    String f_synchro = null;

                    if (sendState){ //同步成功失败给模式表添加同步状态

                        returnObject.setStatus("1");

                        returnObject.setMsg("同步成功!");

                        f_synchro = "1";

                        Boolean flag = besceneConfigMapper.insertIntoSynchroState(f_synchro,modeId);

                        // 添加订阅消息
                        MsgSubPubHandler.addSubMsg(LDCCmd.SCENE_PARAM_SET, channelID);

                    }else {

                        f_synchro = "0";

                        Boolean flag = besceneConfigMapper.insertIntoSynchroState(f_synchro,modeId);

                        returnObject.setStatus("0");

                        returnObject.setMsg("同步失败!");
                    }
                }
                break;
            default:
                break;
        }

        return returnObject;
    }


    /**
     *
     * @Description: 删除采集模式
     *
     * @auther: wanghongjie 修改
     * @date: 15:32 2021/8/9
     * @param: [modeId, sceneId]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject deletCollectModeInfomation(String modeId, String sceneId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        //判断当前模式是否在计划中配置
        List<BesPlan> besPlanList = planCache.getCachedElementByModeId(modeId);
        if (besPlanList != null) {
            returnObject.setStatus("0");
            returnObject.setMsg("当前模式已在计划中配置,请删除相应计划后再操作");
            return returnObject;
        }
        //删除模式
        Boolean flag = besceneConfigMapper.deleteScenemodeInfomation(modeId);

        if (flag){
            //删除模式信息
            sceneModeCache.deleteOneSceneModeCache(modeId);

            Boolean flag1 = besceneConfigMapper.deleteScenepointInfomation(modeId);
            if (flag1){
                //删除模式点位相关信息
                List<BeScenePoint> scenePointList  = scenePointCache.getCachedElementByScenemodeId(modeId);
                if (scenePointList != null && scenePointList.size() > 0) {
                    for (BeScenePoint beScenePoint : scenePointList) {
                        String id = String.valueOf(beScenePoint.getF_id());
                        //删除模式点位相关信息
                        scenePointCache.deleteOneScenePointCache(id);
                    }
                }
                returnObject.setStatus("1");
                returnObject.setMsg("删除成功");
            } else {
                returnObject.setStatus("1");
                returnObject.setMsg("删除成功");
            }
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;

    }

    /**
     *
     * @Description: 修改场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 14:16 2021/8/9
     * @param: [modeId, sceneId]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject updateCollectMode(JSONObject obj) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String nick_name = null;
        String psys_name = null;

        //获取模式id
        String modeId = obj.getString("modeId");
        //获取场景id
        String sceneId = obj.getString("sceneId");
        //获取点位名称
        JSONArray f_pointnameList = obj.getJSONArray("f_pointname");
        //获取点位id
        JSONArray f_pointIdList = obj.getJSONArray("f_pointId");
        //点位类型(1:控制场景,2:采集场景)
        String f_type = obj.getString("f_type");

        if (f_pointIdList == null || f_pointIdList.size() == 0) {//点击"修改指令"按钮时,进行的判断
            //查询当前模式是否存在(默认模式说明模式没有添加)
            BeSceneMode beSceneMode = sceneModeCache.getCachedElement(modeId);
            if (beSceneMode == null) {
                returnObject.setStatus("0");
                returnObject.setMsg("当前模式未配置点位,请配置点位");
            } else {

                returnObject.setStatus("1");
                return returnObject;
            }
            return returnObject;
        }
        //首先删除 bes_scenepoint 点位关联模式表中的相关点位信息
        boolean booleanDeleteScenepoint = besceneConfigMapper.deleteScenepointByScenemodeId(modeId);
        if (booleanDeleteScenepoint) {
            //删除模式点位缓存中的相关数据
            List<BeScenePoint> beScenePointList = scenePointCache.getCachedElementByScenemodeId(modeId);
            if (beScenePointList != null && beScenePointList.size() > 0) {
                for (BeScenePoint beScenePoint : beScenePointList) {
                    scenePointCache.deleteOneScenePointCache(String.valueOf(beScenePoint.getF_id()));
                }
            }

            //添加相关点位到点位关联模式表
            for (int i = 0; i < f_pointIdList.size(); i++) {

                BeScenePoint beScenePoint = new BeScenePoint();
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById((String) f_pointIdList.get(i));
                if (besSbPzStruct != null) {
                    nick_name = besSbPzStruct.getF_nick_name();//别名
                    psys_name = besSbPzStruct.getF_psys_name();//父节点名称
                }

                //通过父级名称查询缓存中的父级信息
                BESSbPzStruct psysNameStruct = sbPzStructCache.getCachedElement(psys_name);
                String psysName = psysNameStruct.getF_psys_name();
                //节点类型,2楼控3照明
                String fType = psysNameStruct.getF_type();
                //循环查询父级,当父节点类型为2楼控,3照明时停止循环
                while(psysName != null && !fType.equals("3") && !fType.equals("2")){
                    psysNameStruct = sbPzStructCache.getCachedElement(psysName);
                    psysName = psysNameStruct.getF_psys_name();
                    fType = psysNameStruct.getF_type();

                };
                //组织点位信息
                String DDCName = psysNameStruct.getF_nick_name();
                beScenePoint.setF_pointnameDetailed("点位:" +besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                        "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                );
                /*String f_node_attribution = besSbPzStruct.getF_node_attribution();//所属系统
                if ("1".equals(f_node_attribution)) {//楼控
                    if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                        Map<String, Object> queryDDCMapByVPoint = besceneConfigMapper.queryChannelByIdLDC((String) f_pointIdList.get(i));
                        String DDCName = (String) queryDDCMapByVPoint.get("F_NICK_NAME");
                        beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                                "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                        );
                    } else {
                        //查询当前点位所属的ddc
                        Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint((String) f_pointIdList.get(i));
                        String DDCName = (String) queryDDCMapByPoint.get("F_NICK_NAME");
                        beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                                "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                        );
                    }

                } else {
                    Map<String, Object> queryLDCMapByPoint = besSbdyMapper.queryLDCMapByPoint((String) f_pointIdList.get(i));
                    String LDCName = (String) queryLDCMapByPoint.get("F_NICK_NAME");
                    beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + LDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                }*/

                beScenePoint.setF_pointId(f_pointIdList.getString(i));
                beScenePoint.setF_pointname(f_pointnameList.getString(i));
                beScenePoint.setF_pointype(f_type);
                beScenePoint.setF_sceneInfoId(sceneId);
                beScenePoint.setF_scenemodeId(modeId);
                besceneConfigMapper.insertBeScenePoint(beScenePoint);
            }

            //更新点位缓存
            scenePointCache.loadCache();
            returnObject.setStatus("1");
        } else {
            returnObject.setStatus("0");
        }

        return returnObject;
    }

    /**
     *
     * @Description: 添加场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 9:06 2021/8/12
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject addCollectMode(JSONObject obj) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String nick_name = null;
        String psys_name = null;

        //获取模式id
        String modeId = obj.getString("modeId");
        //获取场景id
        String sceneId = obj.getString("sceneId");
        //获取点位名称
        JSONArray f_pointnameList = obj.getJSONArray("f_pointname");
        //获取点位id
        JSONArray f_pointIdList = obj.getJSONArray("f_pointId");
        //点位类型(1:控制场景,2:采集场景)
        String f_type = obj.getString("f_type");

        if (f_pointIdList == null || f_pointIdList.size() == 0) {//点击"修改指令"按钮时,进行的判断
            //查询当前模式是否存在(默认模式说明模式没有添加)
            BeSceneMode beSceneMode = sceneModeCache.getCachedElement(modeId);
            if (beSceneMode == null) {
                returnObject.setStatus("0");
                returnObject.setMsg("当前模式未配置点位,请配置点位");
            } else {

                returnObject.setStatus("1");
                return returnObject;
            }
            return returnObject;
        }

        //添加相关点位到点位关联模式表
        for (int i = 0; i < f_pointIdList.size(); i++) {
            BeScenePoint beScenePoint = new BeScenePoint();
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementById((String) f_pointIdList.get(i));
            if (besSbPzStruct != null) {
                nick_name = besSbPzStruct.getF_nick_name();//别名
                psys_name = besSbPzStruct.getF_psys_name();//父节点名称

            }

            //通过父级名称查询缓存中的父级信息
            BESSbPzStruct psysNameStruct = sbPzStructCache.getCachedElement(psys_name);
            String psysName = psysNameStruct.getF_psys_name();
            //节点类型,2楼控3照明
            String fType = psysNameStruct.getF_type();
            //循环查询父级,当父节点类型为2楼控,3照明时停止循环
            while(psysName != null && !fType.equals("3") && !fType.equals("2")){
                psysNameStruct = sbPzStructCache.getCachedElement(psysName);
                psysName = psysNameStruct.getF_psys_name();
                fType = psysNameStruct.getF_type();

            };
            //组织点位信息
            String DDCName = psysNameStruct.getF_nick_name();
            beScenePoint.setF_pointnameDetailed("点位:" +besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                    "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
            );

            /*String f_node_attribution = besSbPzStruct.getF_node_attribution();//所属系统
            if ("1".equals(f_node_attribution)) {//楼控
                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryDDCMapByVPoint = besSbdyMapper.selectcontrollerByLDCVpoint(besSbPzStruct.getF_sys_name_old());
                    String DDCName = (String) queryDDCMapByVPoint.get("F_NICK_NAME");
                    beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                } else {
                    //查询当前点位所属的ddc
                    Map<String, Object> queryDDCMapByPoint = besSbdyMapper.queryDDCMapByPoint((String) f_pointIdList.get(i));
                    String DDCName = (String) queryDDCMapByPoint.get("F_NICK_NAME");
                    beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                }

            } else {
                if ("16".equals(besSbPzStruct.getF_type())) {//虚点
                    Map<String, Object> queryDDCMapByVPoint = besSbdyMapper.selectcontrollerByLDCVpoint(besSbPzStruct.getF_sys_name_old());
                    String DDCName = (String) queryDDCMapByVPoint.get("F_NICK_NAME");
                    beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + DDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                } else {
                    Map<String, Object> queryLDCMapByPoint = besSbdyMapper.queryLDCMapByPoint((String) f_pointIdList.get(i));
                    String LDCName = (String) queryLDCMapByPoint.get("F_NICK_NAME");
                    beScenePoint.setF_pointnameDetailed("点位:" + besSbPzStruct.getF_sys_name_old() + "(" + nick_name + ")" +
                            "\u3000" + "所属控制器名称:" + LDCName + "\u3000" + "所属模块名称:" + psys_name
                    );
                }
            }*/


            beScenePoint.setF_pointId(f_pointIdList.getString(i));
            beScenePoint.setF_pointname(f_pointnameList.getString(i));
            beScenePoint.setF_pointype(f_type);
            beScenePoint.setF_sceneInfoId(sceneId);
            beScenePoint.setF_scenemodeId(modeId);
            besceneConfigMapper.insertBeScenePoint(beScenePoint);
        }

        //更新点位缓存
        scenePointCache.loadCache();
        returnObject.setStatus("1");

        return returnObject;
    }
}
