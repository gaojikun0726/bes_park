package com.efounder.JEnterprise.zhdg.service.impl;

import com.core.common.util.StringUtil;
import com.efounder.JEnterprise.zhdg.config.WeatherStatusEnum;
import com.efounder.JEnterprise.zhdg.dao.DynamicagreeDao;
import com.efounder.JEnterprise.zhdg.entity.*;
import com.efounder.JEnterprise.zhdg.service.HttpConnect;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.JEnterprise.zhdg.service.PointwarnService;
import com.efounder.JEnterprise.zhdg.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * 动态协议解析处理类
 *
 * @author YangChao
 * @date 2020-04-16
 */
@Service
public class SebDynamicAgreeHandleServiceImpl implements ISebDynamicAgreeHandleService {
    private static final Logger log = LoggerFactory.getLogger(SebDynamicAgreeHandleServiceImpl.class);

    @Autowired
    private PointService pointService;// 点位接口

    @Autowired
    private PointwarnService pointwarnService;//点位报警

    // 动态协议配置
    @Autowired
    private DynamicagreeDao dynamicagreeDao;

    // 下发指令
    @Autowired
    private HttpConnect httpConnect;

    /**
     * 实时数据,使用Vector代替ArrayList
     */
    private static List<Map<String, String>> realDataList = new Vector<>();//点位数据解析内存数组

    /**
     * 气象状态数据-key ip ,value 状态：在线0、报警1、离线2
     */
    public static Map<String,String> weatherStatusMap = new ConcurrentHashMap<>();

    /**
     * 实时报警,使用Vector代替ArrayList
     */
    private static List<Map<String, String>> realWarnList = new Vector<>();//实时报警数组

    // 配置包
    private final static String configCode = "151";
    // 数据包
    private final static String dataCode = "171";

    // 默认配置包协议列表
    private static List<DynamicagreeEntity> DynamicConfigAgreeList = new ArrayList<DynamicagreeEntity>();
    // 默认数据包协议列表
    private static List<DynamicagreeEntity> DynamicDataAgreeList = new ArrayList<DynamicagreeEntity>();
    // 点位报警表数据
    private static List<PointwarnEntity> PointWarnList = new ArrayList<PointwarnEntity>();

    /**
     * @Description: 初始化加载数据--执行顺序--构造方法 > @Autowired > @PostConstruct
     */
    @PostConstruct
    private void init() {
        // 1.0-- 获取默认类型配置包动态协议配置--151
        DynamicConfigAgreeList = dynamicagreeDao.GetDynamicAgreeList(configCode);
        // 2.0-- 获取默认类型数据包动态协议配置--171
        DynamicDataAgreeList = dynamicagreeDao.GetDynamicAgreeList(dataCode);
        // 3.0-- 获取点位报警表数据
        PointWarnList = pointwarnService.selectSebPointwarnList(new PointwarnEntity());

        //加载所有气象的IP、状态数据
        weatherStatusMap = pointService.getWeatherData();
    }

    /**
     * @Description: 重新加载初始化数据
     */
    @Override
    public void reload() {
        // 1.0-- 获取默认类型配置包动态协议配置--151
        DynamicConfigAgreeList = dynamicagreeDao.GetDynamicAgreeList(configCode);
        // 2.0-- 获取默认类型数据包动态协议配置--171
        DynamicDataAgreeList = dynamicagreeDao.GetDynamicAgreeList(dataCode);
        // 3.0-- 获取点位报警表数据
        PointWarnList = pointwarnService.selectSebPointwarnList(new PointwarnEntity());
    }

    /**
     * @Description: 协议解析，数据处理
     */
    @Override
    public void DynamicAgreeHandle(String clientIp, String deviceId, String data) throws Exception {
        // 来数据先开线程去实时报警数据里面找有没有报警的 有 清空它
        ThreadPool.executor.execute(() -> {
            try {
                // 离线报警id默认0
                Map<String, Object> map = warnJudge(RadixUtil.hexStringToString(deviceId), "0");
                if ((Boolean) map.get("flag")) {
                    realWarnList.remove((int) map.get("index"));
                    log.info(Thread.currentThread().getName() + "线程池处理-剔除" + RadixUtil.hexStringToString(deviceId) + "的离线报警");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 截取指令码 判断是什么类型的包数据--10进制指令码
        String orderCode = AgreeHandleUtil.revSubstrTurn(data, 30, 32);
        // 创建Map<String,String>
        Map<String, String> realMap = new HashMap<>();
        // 存储原始数据
        realMap.put("OriginalData", data);
        // 存储id
        realMap.put("DeviceId", data.substring(6, 30));

        String dId = RadixUtil.hexStringToString(data.substring(6, 30));
        //
        realMap.put("deviceid", dId);

//        //存储在线状态数据
//        SebDynamicAgreeHandleServiceImpl.weatherStatusMap.put(dId, WeatherStatusEnum.online.getCode());

        // 存储指令码
        realMap.put("orderCode", orderCode);
        switch (orderCode) {
            //realMap 负责储存解析完后数据
            case "151":// 配置包
                //configAnalysis(data, realMap);
                break;

            case "171":// 数据包
                dataAnalysis(data, realMap, clientIp);
                SaveData(deviceId, realMap, clientIp);
                break;

            case "62":
                log.error("直流控制回应");
                break;

            case "64":
                log.error("继电器控制回应");
                break;

            case "8":
                log.error("时间校准回应");
                break;

            case "74":
                log.error("八入八出控制回应");
                break;

            case "76":
                log.error("灯具亮度控制回应");
                break;

            default:
                log.error("不明识别码:" + orderCode);
                break;
        }

    }

    /**
     * @Description: 保存数据包数据
     */
    public void SaveData(String deviceId, Map<String, String> realMap, String clientIp) throws Exception {
        // 先根据设备ip判断点位数据在内存中是否存在
        Map<String, Object> map = ipJudge(deviceId);
        boolean flag = (boolean) map.get("flag");
        if (flag) {// 如果存在
            int index = (int) map.get("index");// ip相同的索引
            realMap.put("Ipadress", clientIp);
            realDataList.set(index, realMap);
        } else {// 如果不存在
            realMap.put("Ipadress", clientIp);
            realDataList.add(realMap);
        }
    }

    /**
     * @Description: semList中设备id判断是否存在
     * @author YangChao
     * @date 2020/3/26 16:52
     */
    public Map<String, Object> ipJudge(String DeviceId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        int index = 0;
        Map<String, String> realMap = new HashMap<>();
        for (int i = 0; i < realDataList.size(); i++) {
            if (DeviceId.equals(realDataList.get(i).get("DeviceId"))) {
                if(index == 0){
                    flag = true;
                    index = i;
                    realMap = realDataList.get(i);
                }else{
                    realDataList.remove(i);
                }
            }
        }
        map.put("flag", flag);
        map.put("index", index);
        map.put("realMap", realMap);
        return map;
    }

    /**
     * @Description: 数据包动态解析
     */
    public void dataAnalysis(String data, Map<String, String> realMap, String clientIp) throws Exception {
        // 开始解析数据包--1.循环所有数据协议配置
        for (DynamicagreeEntity SebDynamicagree : DynamicDataAgreeList) {
            // 获取oname-存放到map
            String oname = SebDynamicagree.getOname();
            // 处理方法名称
            String handlename = SebDynamicagree.getHandleidname();
            // 起始位
            int start = SebDynamicagree.getStart();
            // 截止位
            int end = SebDynamicagree.getEnd();
            // 报警id
            Long warnid = SebDynamicagree.getWarnid();


            /** 协议处理 */
            switch (handlename) {
                // 默认不作处理,返回十六进制
                case "Transformation0":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation0(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 精度0.1，高低转换，十六转十
                case "Transformation1":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation1(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 精度0.01，高低转换，十六转十
                case "Transformation2":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation2(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 精度0.001，高低转换，十六转十
                case "Transformation3":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation3(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 精度0.0001，高低转换，十六转十
                case "Transformation4":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation4(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 精度0.0001，高低转换，十六转十
                case "Transformation5":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation5(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 十六转二，不足八位补足八位
                case "Transformation6":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation6(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 高低转换,十六转十
                case "Transformation7":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation7(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 协议位数据截取
                case "Transformation8":
                    try {
                        // 是否解析
                        int analysisPosition = SebDynamicagree.getAnalysisposition();
                        if (analysisPosition == 1) {
                            continue;
                        }
                        // 字节位位置
                        int position = SebDynamicagree.getPosition();
                        realMap.put(oname, AgreeHandleUtil.Transformation8(data, start, end, position));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 十六转二,不足十六位补足十六位
                case "Transformation9":
                    try {
                        // 风向单独处理
                        String odata = AgreeHandleUtil.Transformation9(data, start, end);
                        if ("windDirection".equals(oname)) {
                            odata = odata.substring(4, odata.length());
                            StringBuffer sb = new StringBuffer(odata);
                            realMap.put(oname, String.valueOf(Integer.parseInt(sb.reverse().toString(), 2)));
                        } else {
                            realMap.put(oname, odata);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                // 高低转换,十六转float
                case "Transformation10":
                    try {
                        realMap.put(oname, AgreeHandleUtil.Transformation10(data, start, end));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(oname + "处理:" + handlename);
                        log.error("start:" + start + "-end:" + end);
                    }
                    break;
                default:
                    log.error("未知处理方法");
                    break;
            }

            if("outdoorTemperature".equals(oname)){
                //如果是室外温度，需要减去40,避免配置报警之后后台误报，比如超过30°就报警
                String outdoor = realMap.get("outdoorTemperature");
                BigDecimal num = new BigDecimal(outdoor);
                num = num.subtract(new BigDecimal(40));
                realMap.put("outdoorTemperature", String.valueOf(num));
            }

            /*** 报警处理 **/
            if (warnid != null) {
                String DeviceId = RadixUtil.hexStringToString(realMap.get("DeviceId"));// 字符串设备id
                if (PointWarnList.size() > 0) {
                    for (PointwarnEntity warn : PointWarnList) {
                        // 设备id和报警id对应上 查出唯一值--处理报警逻辑
                        if (warn.getWarnid().equals(warnid) && warn.getPointid().equals(DeviceId)) {
                            Map<String, String> realWarnMap = new HashMap<>();
                            String vtype = warn.getVtype();//0.定值  1.浮动(min,max)
                            // 插入报警
                            realWarnMap.put("DeviceId", DeviceId);//点位id
                            realWarnMap.put("warnid", String.valueOf(warnid));//报警id
                            realWarnMap.put("warntype", warn.getWarntype());//报警类型
                            if ("0".equals(vtype)) {// 定值
                                String vtype0 = warn.getVtype0();// 定值 值
                                // 0正常 1报警--不相同 报警
                                if (!vtype0.equals(realMap.get(oname))) {
                                    realWarnMap.put("warnMsg", warn.getWarntype() + "状态报警");//报警说明
                                }
                            } else if ("1".equals(vtype)) {// 浮动上下限--逻辑判断 低于下限 高于上限
                                double min = Double.parseDouble(warn.getVtype1Min());//下限
                                double max = Double.parseDouble(warn.getVtype1Max());// 上限
                                if (Double.parseDouble(realMap.get(oname)) < min) {
                                    realWarnMap.put("warnMsg", warn.getWarntype() + "数值低于报警阈值下限(" + realMap.get(oname) + "<" + min + ")");//报警说明
                                } else if (Double.parseDouble(realMap.get(oname)) > max) {
                                    realWarnMap.put("warnMsg", warn.getWarntype() + "数值高于报警阈值上限(" + realMap.get(oname) + ">" + max + ")");//报警说明
                                }
                            }
                            if (realWarnMap.containsKey("warnMsg")) {
                                realWarnMap.put("Ipadress", clientIp);//将ip放到缓存中
                                // 保存报警信息
                                SaveWarn(realWarnMap, clientIp);
                            }else{
                                //当不满足报警条件时，清除报警信息
                                clearWarnInfo(DeviceId,warnid);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @Description: 保存实时报警
     */
    private void SaveWarn(Map<String, String> realWarnMap, String clientIp) throws Exception {
        // 先根据设备id判断此id此类报警数据在内存中是否存在
        Map<String, Object> map = warnJudge(realWarnMap.get("DeviceId"), realWarnMap.get("warnid"));
        boolean flag = (boolean) map.get("flag");
        // 如果存在
        if (flag) {
            // 相同报警的索引
            int index = (int) map.get("index");
            realWarnList.set(index, realWarnMap);
        } else {// 如果不存在
            realWarnList.add(realWarnMap);
        }
    }


    /**
     * 清除报警信息
     * @param deviceId
     * @param warnId
     */
    private void clearWarnInfo(String deviceId,Long warnId){
        String warnIdStr = String.valueOf(warnId);
        Iterator<Map<String,String>> iterator = realWarnList.iterator();
        while(iterator.hasNext()){
            Map<String,String> map = iterator.next();
            String dId = map.get("DeviceId");
            String wId = map.get("warnid");
            if(deviceId.equals(dId) && warnIdStr.equals(wId)){
                iterator.remove();
            }
        }
    }

    /**
     * @Description: 根据设备id判断此id此类报警数据在内存中是否存在
     */
    public Map<String, Object> warnJudge(String DeviceId, String warnid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        int index = 0;
        Map<String, String> realMap = new HashMap<>();
        for (int i = 0; i < realWarnList.size(); i++) {
            String dId = realWarnList.get(i).get("DeviceId");
            String wId = realWarnList.get(i).get("warnid");
            if (DeviceId.equals(dId) && warnid.equals(wId)) {
                flag = true;
                index = i;
                realMap = realWarnList.get(i);
            }
        }
        map.put("flag", flag);
        map.put("index", index);
        map.put("realMap", realMap);
        return map;
    }

    /**
     * @return
     * @Description: 获取实时数据
     */
    @Override
    public Map<String, Object> GetRealPoint() {
        Map<String, Object> map = new HashMap<>();
        /**返回实时数据--并且拼装表头*/
        List<Map<String, Object>> HeadList = new LinkedList<>();
        Map<String, Object> mrMap = new HashMap<>();
        mrMap.put("oname", "DeviceId");
        mrMap.put("name", "设备id");
        HeadList.add(mrMap);
        Map<String, Object> mripMap = new HashMap<>();
        mripMap.put("oname", "Ipadress");
        mripMap.put("name", "设备ip");
        HeadList.add(mripMap);
        List<DynamicagreeEntity> list = DynamicDataAgreeList;
        for (DynamicagreeEntity agree : list) {
            Map<String, Object> agreeMap = new HashMap<>();
            agreeMap.put("oname", agree.getOname());
            agreeMap.put("name", agree.getName());
            HeadList.add(agreeMap);
        }
        List<Map<String, Object>> TableRealDataList = new LinkedList<>();
        for (int i = 0; i < realDataList.size(); i++) {
            Map<String, Object> TableRealMap = new HashMap<>();
            TableRealMap.putAll(realDataList.get(i));
            TableRealMap.replace("deviceid", String.valueOf(TableRealMap.get("deviceid")));
            TableRealDataList.add(TableRealMap);
        }
        map.put("HeadList", HeadList);
        map.put("TableRealDataList", TableRealDataList);
        return map;
    }

    /**
    * @Description: 获取实时数据
    *
    */
    public List<Map<String, String>> GetRealDataList(){
        return realDataList;
    }

    /**
     * 获取气象状态实时数据
     */
    @Override
    public Map<String, String> getWeatherStatusData() {
        return weatherStatusMap;
    }

    /**
     * @Description: 获取实时报警
     */
    @Override
    public List<Map<String, String>> GetRealWarn(SebWarnRecord warn) {
        // 两个查询条件
        String pointid = warn.getPointid();
        Long warnid = warn.getWarnid();

        List<Map<String, String>> resList = new ArrayList<>();
        // 遍历realWarnList--取出符合条件的数据

        if (StringUtils.isNotEmpty(pointid) && warnid != null) {// 都不为空
            resList = realWarnList.stream().filter((Map<String, String> a) ->
                    a.get("DeviceId").equals(pointid) && a.get("warnid").equals(warnid)).collect(Collectors.toList());

        } else if (StringUtils.isNotEmpty(pointid) && warnid == null) {// 点位id不为空,报警id为空
            resList = realWarnList.stream().filter((Map<String, String> a) ->
                    a.get("DeviceId").equals(pointid)).collect(Collectors.toList());

        } else if (StringUtils.isEmpty(pointid) && warnid != null) {// 点位id为空,报警id不为空
            resList = realWarnList.stream().filter((Map<String, String> a) ->
                    a.get("warnid").equals(String.valueOf(warnid))).collect(Collectors.toList());

        } else if (StringUtils.isEmpty(pointid) && warnid == null) {// 都为空

            resList = realWarnList;

        } else {

            resList = realWarnList;
        }

        return resList;
    }

    /**
     * @Description: 根据设备id获取实时数据
     */
    @Override
    public Map<String, String> getPointInfo(String DeviceId) throws Exception {
        Map<String, String> pointMap;
        Map<String, Object> map = ipJudge(RadixUtil.str2HexStr(DeviceId));
        pointMap = (Map<String, String>) map.get("realMap");
        return pointMap;
    }

    /**
     * @Description: 下发指令
     */
    @Override
    public AjaxResult sendMsg(String DeviceId, String datas, String orderCode, String digits) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            Map<String, String> pointMap;
            Map<String, Object> map = ipJudge(RadixUtil.str2HexStr(DeviceId));
            pointMap = (Map<String, String>) map.get("realMap");
            // 1 获取原始数据
            String OriginalData = pointMap.get("OriginalData");
            // 2 数据拼装 数据组成  头到指令码 为前15位 + 指令码 +数据长度 +数据段 +和校验 +尾
            String Head = OriginalData.substring(0, 30);
            String dataLength = "";// 数据长度
            String sendData = "";// 数据段
            String Tail = OriginalData.substring(OriginalData.length() - 4, OriginalData.length());
            // 3 根据指令码处理数据段和数据长度
            switch (orderCode) {
                case "61":// 直流可控控制指令
                    StringBuilder ZlKkZt = new StringBuilder(pointMap.get("ZlKkZt"));
                    sendData = RadixUtil.binary2Hex(ZlKkZt.replace(7 - Integer.parseInt(digits), 8 - Integer.parseInt(digits), datas).toString());
                    break;
                case "7":// 时间校准
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(DateUtils.getTime());
                    Long utcTime = date.getTime() / 1000;
                    // 4字节
                    sendData = AgreeHandleUtil.add32forstr(String.valueOf(utcTime));
                    break;
                case "73":// 八入八出控制指令
                    StringBuilder JdqJkZt = new StringBuilder(pointMap.get("relayControl"));
                    sendData = RadixUtil.binary2Hex(JdqJkZt.replace(7 - Integer.parseInt(digits), 8 - Integer.parseInt(digits), datas).toString());
                    break;
                case "75":// 灯具亮度控制指令
                    sendData = RadixUtil.int2Hex(Integer.parseInt(datas));
                    break;
                default:
                    break;
            }
            if (sendData != "") {
                if (sendData.length() == 1) {
                    sendData = "0" + sendData;
                }
                dataLength = AgreeHandleUtil.add20forstr(String.valueOf(RadixUtil.int2Hex(new Double(Math.ceil(sendData.length() / 2)).intValue())));
            } else {
                dataLength = "00";
            }
            // 4 拼装
            sb.append(Head).append(AgreeHandleUtil.add20forstr(Integer.toHexString(Integer.parseInt(orderCode)))).append(dataLength).append(sendData).append(Tail);
            ThreadPool.executor.execute(() -> {
                try {
                    httpConnect.sendMsg(RadixUtil.str2HexStr(DeviceId), sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            log.info("指令码:" + orderCode + "--下发指令：" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("下发失败", sb.toString());
        } finally {

        }
        return AjaxResult.success("下发成功", sb.toString());
    }

    /**
     * @Description: 定时任务, 清空缓存, 并且更改数据库点位状态--只更改离线设备
     */
    @Override
    public void cleanRealPointData() throws Exception {
        // 1.0 清空所有实时数据缓存
        realDataList = new Vector<>();
//        // 2.0 修改数据库点位状态
//        pointService.updateAllPointsStatus();

        //修改缓存气象数据状态
        setOfflineData();
    }

    /**
     * @Description: 修改点位在线离线报警状态
     */
    @Override
    public void updatePointStatus() {

        Map<String,String> ipMap = new HashMap<>();

        // 获取所有实时数据
        List<Map<String, String>> realData = realDataList;
        if (realData.size() > 0) {
            List<String> fCode = new ArrayList<>();
            for (Map<String, String> map : realData) {
                String deviceId = RadixUtil.hexStringToString(map.get("DeviceId"));
                String ip = map.get("Ipadress");
                fCode.add(deviceId);
                ipMap.put(deviceId,ip);
            }
            // 查询报警id 存放到WarnList
            List<String> WarnList = new ArrayList<>();
            for (Map<String, String> warnMap : realWarnList) {
                if (!warnMap.get("warnid").equals("0")) {
                    //报警类型不是离线
                    String deviceId = warnMap.get("DeviceId");
                    WarnList.add(deviceId);
                    String ip = warnMap.get("Ipadress");
                    //修改缓存：将不是离线类型的气象数据设置为状态：报警
                    weatherStatusMap.put(ip,WeatherStatusEnum.warning.getCode());
                }
            }
            List<String> newWarnList = new ArrayList<String>(new TreeSet<String>(WarnList));
//            // 批量修改报警状态，将不是离线类型的数据设置为状态：报警
//            if (newWarnList.size() > 0) {
//                pointService.updatePointsWarnStatus(newWarnList);
//            }
            fCode.removeAll(newWarnList);
            // 批量修改在线状态
            if (fCode.size() > 0) {
//                pointService.updatePointsStatus(fCode);
                fCode.forEach(deviceId ->{
                    //存储在线状态数据
                    String ip = ipMap.get(deviceId);
                    SebDynamicAgreeHandleServiceImpl.weatherStatusMap.put(ip, WeatherStatusEnum.online.getCode());
                });
            }
        }else{
            // 如果没有实时数据--全部清空状态--修改为离线
//            pointService.updateAllPointsStatus();

            //修改缓存气象数据状态
            setOfflineData();
        }
    }

    /**
     * 将所有气象状态修改为--离线
     */
    public void setOfflineData(){
        weatherStatusMap.forEach((ip,status) -> {
            String offlineStatus = WeatherStatusEnum.offline.getCode();
            if(!offlineStatus.equals(status)){
                //如果不是离线数据
                weatherStatusMap.put(ip,offlineStatus);
            }
        });
    }

    /**
     * @Description: 根据id清空缓存数据
     */
    @Override
    public void CleanRealData(String deviceId, String clientIp) throws Exception {
        String dId = "";
        if(StringUtil.isNotEmpty(deviceId)){
             dId = RadixUtil.hexStringToString(deviceId);
        }
        //存储离线状态数据
        weatherStatusMap.put(clientIp, WeatherStatusEnum.offline.getCode());

//        List<String> offlineList = new ArrayList<>();
//        offlineList.add(dId);
        // 1.清空相同id缓存
        Map<String, Object> map = ipJudge(deviceId);
        // 清空实时数据
        if ((boolean) map.get("flag")) {
            realDataList.remove((int) map.get("index"));
        }
//        // 2.修改数据库点位状态,设置为离线
//        pointService.updatePointStatus(offlineList);
        // 3.添加离线报警
        Map<String, String> realWarnMap = new HashMap<>();
        realWarnMap.put("DeviceId", dId);//点位id
        realWarnMap.put("Ipadress", clientIp);
        realWarnMap.put("warnid", "0");//默认0是离线报警
        realWarnMap.put("warntype", "离线");//报警类型
        realWarnMap.put("warnMsg", "设备网络通讯中断");//报警说明
        realWarnList.add(realWarnMap);

    }

    /**
     * @Description: 清空缓存报警
     */
    @Override
    public void cleanWarn() {
        // 清空报警缓存
        realWarnList.clear();
        // 将离线设备插入报警缓存
        List<PointEntity> offlinePointList = pointService.GetofflinePointList();
        for (PointEntity seb : offlinePointList) {
            Map<String, String> realWarnMap = new HashMap<>();
            realWarnMap.put("DeviceId", seb.getFCode());//点位id
            realWarnMap.put("Ipadress", seb.getFPointIp());
            realWarnMap.put("warnid", "0");//默认0是离线报警
            realWarnMap.put("warntype", "离线");//报警类型
            realWarnMap.put("warnMsg", "设备网络通讯中断");//报警说明
            realWarnList.add(realWarnMap);
        }
    }

    /**
     * @Description: 时间校准指令下发
     */
    @Override
    public void TimeCalibration() throws Exception {
        // 1.0 查询所有点位
        List<PointEntity> list = pointService.selectSebPointList();
        // 2.0 循环点位下发
        for (PointEntity seb : list) {
            sendMsg(seb.getFCode(), "", "7", "");
        }
    }


}
