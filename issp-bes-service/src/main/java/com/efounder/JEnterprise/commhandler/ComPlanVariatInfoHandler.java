package com.efounder.JEnterprise.commhandler;

import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.PlanVariatInfoCache;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesplanConfigMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.PlanVariatInfoModel;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Data 2021/1/26 18:34
 */
@Component
public class ComPlanVariatInfoHandler {

    @Autowired
    private  PlanVariatInfoCache planVariatInfoCache;

    @Autowired
    private BesplanConfigMapper besplanConfigMapper;

    public void planVariatInfoCache(Integer id,Double value){

        String key = id.toString();

        Cache<String, PlanVariatInfoModel> variatinfo = planVariatInfoCache.getPlanVariatInfoCache();

        List<Map<String,Object>> planList = besplanConfigMapper.queryToatalCollectPlan();

        List<Object> cacheList = new ArrayList<>();

        if (variatinfo!=null){

            for (int j = 0; j< planList.size();j++){

                String planId = (String) planList.get(j).get("f_id");

                PlanVariatInfoModel planVariatInfoModel = variatinfo.get(planId);

                if (planVariatInfoModel != null){
                    cacheList.add(planVariatInfoModel);
                }
            }


            for (int g = 0; g< cacheList.size();g++){

                PlanVariatInfoModel planVariatInfoModel = (PlanVariatInfoModel) cacheList.get(g);

                String Variatvalue = planVariatInfoModel.getF_specificvalue();

                Double vavalue = Double.parseDouble(Variatvalue);//获取变化量的值

                List<Map<String,Object>> totalPointList = besplanConfigMapper.selectPointInfomationByModeId(((PlanVariatInfoModel) cacheList.get(g)).getF_modeId());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

                String F_CRDATE = simpleDateFormat.format(new Date());

                for (int i = 0;i<totalPointList.size(); i++){

                    String f_pointId = (String) totalPointList.get(i).get("f_pointId");

                    if (f_pointId.equals(key)){//证明模式里面的点位存在 存在就需要比较

                        Map<String,Object> pointType = besplanConfigMapper.queryPointTypeByPointId(f_pointId);

                        Integer type = (Integer) pointType.get("F_TYPE");

                        if (type.equals(12)) {//DI

                            Map<String,Object> typeDiPointMap = besplanConfigMapper.queryDiPointDateList(f_pointId);

                            String initVal1 = (String) typeDiPointMap.get("F_INIT_VAL");

                            Double initVal = Double.parseDouble(initVal1);

                            Double tatalValue = initVal + vavalue; //变化量 + 初始值

                            if (value >= tatalValue){ //传入值大于变化量+初始值 插入

                                typeDiPointMap.put("F_PLANID",((PlanVariatInfoModel) cacheList.get(g)).getF_planId());

                                typeDiPointMap.put("F_TIMENAME",((PlanVariatInfoModel) cacheList.get(g)).getF_timename());

                                typeDiPointMap.put("F_TASKID",((PlanVariatInfoModel) cacheList.get(g)).getF_taskId());

                                typeDiPointMap.put("F_INIT_VAL1",value);

                                typeDiPointMap.put("F_CRDATE",F_CRDATE);

                                InsertDiPointVariatHistoryInfo(typeDiPointMap);
                            }
                        }else if (type.equals(13)){//DO

                            Map<String,Object> typeDoPointMap = besplanConfigMapper.queryDoPointDateList(f_pointId);

                            String initVal1 = (String) typeDoPointMap.get("F_INIT_VAL");

                            Double initVal = Double.parseDouble(initVal1);

                            Double tatalValue = initVal + vavalue;

                            if (value >= tatalValue){

                                typeDoPointMap.put("F_PLANID",((PlanVariatInfoModel) cacheList.get(g)).getF_planId());

                                typeDoPointMap.put("F_TIMENAME",((PlanVariatInfoModel) cacheList.get(g)).getF_timename());

                                typeDoPointMap.put("F_TASKID",((PlanVariatInfoModel) cacheList.get(g)).getF_taskId());

                                typeDoPointMap.put("F_INIT_VAL1",value);

                                typeDoPointMap.put("F_CRDATE",F_CRDATE);

                                InsertDoPointVariatHistoryInfo(typeDoPointMap);
                            }
                        }else if (type.equals(10)){//AI

                            Map<String,Object> typeAiPointMap = besplanConfigMapper.queryAiPointDateList(f_pointId);

                            String initVal1 = (String) typeAiPointMap.get("F_INIT_VAL");

                            Double initVal = Double.parseDouble(initVal1);

                            Double tatalValue = initVal + vavalue;

                            if (value >= tatalValue){

                                typeAiPointMap.put("F_PLANID",((PlanVariatInfoModel) cacheList.get(g)).getF_planId());

                                typeAiPointMap.put("F_TIMENAME",((PlanVariatInfoModel) cacheList.get(g)).getF_timename());

                                typeAiPointMap.put("F_TASKID",((PlanVariatInfoModel) cacheList.get(g)).getF_taskId());

                                typeAiPointMap.put("F_INIT_VAL1",value);

                                typeAiPointMap.put("F_CRDATE",F_CRDATE);

                                InsertAiPointVariatHistoryInfo(typeAiPointMap);
                            }
                        }else if (type.equals(11)){//AO

                            Map<String,Object> typeAoPointMap = besplanConfigMapper.queryAoPointDateList(f_pointId);

                            String initVal1 = (String) typeAoPointMap.get("F_INIT_VAL");

                            Double initVal = Double.parseDouble(initVal1);

                            Double tatalValue = initVal + vavalue;

                            if (value >= tatalValue){

                                typeAoPointMap.put("F_PLANID",((PlanVariatInfoModel) cacheList.get(g)).getF_planId());

                                typeAoPointMap.put("F_TIMENAME",((PlanVariatInfoModel) cacheList.get(g)).getF_timename());

                                typeAoPointMap.put("F_TASKID",((PlanVariatInfoModel) cacheList.get(g)).getF_taskId());

                                typeAoPointMap.put("F_INIT_VAL1",value);

                                typeAoPointMap.put("F_CRDATE",F_CRDATE);

                                InsertAoPointVariatHistoryInfo(typeAoPointMap);
                            }

                        }else{//虚点

                            Map<String,Object> typeVoPointMap = besplanConfigMapper.queryVpointDateList(f_pointId);

                            String initVal1 = (String) typeVoPointMap.get("F_INIT_VAL");

                            Double initVal = Double.parseDouble(initVal1);

                            Double tatalValue = initVal + vavalue;

                            if (value >= tatalValue){

                                typeVoPointMap.put("F_PLANID",((PlanVariatInfoModel) cacheList.get(g)).getF_planId());

                                typeVoPointMap.put("F_TIMENAME",((PlanVariatInfoModel) cacheList.get(g)).getF_timename());

                                typeVoPointMap.put("F_TASKID",((PlanVariatInfoModel) cacheList.get(g)).getF_taskId());

                                typeVoPointMap.put("F_INIT_VAL1",value);

                                typeVoPointMap.put("F_CRDATE",F_CRDATE);

                                InsertVoPointVariatHistoryInfo(typeVoPointMap);
                            }
                        }
                    }
                }
            }
        }
    }

    public void InsertDiPointVariatHistoryInfo(Map<String,Object> typeDiPointMap){

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeDiPointMap.put("F_ID",F_ID);

        Boolean  flag = besplanConfigMapper.InsertPointVariatHistoryInfo(typeDiPointMap);
    }


    public void InsertDoPointVariatHistoryInfo(Map<String,Object> typeDoPointMap){

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeDoPointMap.put("F_ID",F_ID);

        Boolean  flag = besplanConfigMapper.InsertPointVariatHistoryInfo(typeDoPointMap);
    }


    public void InsertAiPointVariatHistoryInfo(Map<String,Object> typeAiPointMap){

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeAiPointMap.put("F_ID",F_ID);

        Boolean  flag = besplanConfigMapper.InsertPointVariatHistoryInfo(typeAiPointMap);
    }


    public void InsertAoPointVariatHistoryInfo(Map<String,Object> typeAoPointMap){

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeAoPointMap.put("F_ID",F_ID);

        Boolean  flag = besplanConfigMapper.InsertPointVariatHistoryInfo(typeAoPointMap);
    }


    public void InsertVoPointVariatHistoryInfo(Map<String,Object> typeVoPointMap){

        String F_ID = UUIDUtil.getRandom32BeginTimePK();

        typeVoPointMap.put("F_ID",F_ID);

        Boolean  flag = besplanConfigMapper.InsertPointVariatHistoryInfo(typeVoPointMap);
    }

}
