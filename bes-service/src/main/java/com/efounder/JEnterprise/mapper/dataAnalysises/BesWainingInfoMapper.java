package com.efounder.JEnterprise.mapper.dataAnalysises;


import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 报警信息统计
 * @author liuzhen
 * time:2018/11/29
 */
@Mapper
public interface BesWainingInfoMapper extends BaseMapper<String, BesWainingInfo>{

     /**
      * 查询报警信息
      * @param besWainingInfo
      * @return
      */
     List<BesWainingInfo> searchWainingInfo(BesWainingInfo besWainingInfo);

     /**
      * 报警类型统计
      * @param besWainingInfo
      * @return
      */
     List<BesWainingInfo> searchWainingInfoCount(BesWainingInfo besWainingInfo);

     /**
      * 报警类型统计未处理
      * @param besWainingInfo
      * @return
      */
     List<BesWainingInfo> searchWainingRealCount(BesWainingInfo besWainingInfo);

     /**
      * 报警类型柱状图信息统计
      * @param besWainingInfo
      * @return
      */
     List<BesWainingInfo> searchWainingBarData(BesWainingInfo besWainingInfo);

     /**
      * 报警类型柱状图未处理信息统计
      * @param besWainingInfo
      * @return
      */
     List<BesWainingInfo> searchWainingRealBarData(BesWainingInfo besWainingInfo);
     /**
      * 报警类型统计-折线图显示
      * @param besWainingInfo
      * @return
      */
     List<Map> searchWainingData(BesWainingInfo besWainingInfo);

     /**
      * 报警类型统计-折线图显示未处理
      * @param besWainingInfo
      * @return
      */
     List<Map> searchWainingReal(BesWainingInfo besWainingInfo);


     List<BesWainingInfo> searchWainingInfoAll(BesWainingInfo besWainingInfo);
}