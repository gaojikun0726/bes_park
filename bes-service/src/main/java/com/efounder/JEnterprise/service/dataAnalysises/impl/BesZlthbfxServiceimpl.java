package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesBranchDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchData;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;
import com.efounder.JEnterprise.service.dataAnalysises.BesZlthbfxService;
import com.framework.common.utils.Validate_y;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 杨超
 * @version 创建时间：2018年10月29日 下午3:35:39
 * @parameter
 * @version 1.0
 */
@Service("/BesZlthbfxService")
public class BesZlthbfxServiceimpl implements BesZlthbfxService {
    private static final Logger log = LoggerFactory.getLogger(BesZlthbfxServiceimpl.class);

    @Autowired
    private BesBranchDataMapper besbranchdatamapper;

    // 根据查询条件拼装table
    @Override
    public ISSPReturnObject thb_pin_table(BesQstjfxData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 获取查询条件
        String time_start = dto.getTime_start();// 本期起始时间
        String time_end = dto.getTime_end();// 本期终止时间
        String tqtime_start = dto.getTqtime_start();// 同期起始时间
        String tqtime_end = dto.getTqtime_end();// 同期终止时间
        String sjkld = dto.getSjkld();// 时间颗粒度
        String dwhs = dto.getDwhs();// 单位换算 起始就是查询的动态列
        String nhlx = dto.getNhlx();// 能耗类型
        String zlid = dto.getZlid();// 支路id 逗号隔开
        String tblx = dto.getTblx();// 图表类型
        StringBuilder str = new StringBuilder();// 拼装table
        str.append("<thead>");
        str.append(
                "<tr class='header_color'><th class='czjz' colspan='2' style='white-space: nowrap;width:20%;'>支路名称</th>");
        // 判断时 天 月 年
        StringBuilder bt = sjkld_s(zlid, dwhs, time_start, time_end, sjkld, nhlx, dto);
        str.append(bt);
        str.append("</thead>");
        str.append("<tbody>");
        // 循环遍历查询
        if (Validate_y.noNull(zlid)) {
            // 本期同期
            StringBuilder bqtq = sjkld_s_nr(zlid, time_start, time_end, sjkld, dwhs, nhlx, tblx, dto, tqtime_start,
                    tqtime_end);
            str.append(bqtq);
        }
        str.append("</tbody>");
        Map<String, Object> map = new HashMap<>();
        map.put("str", str);
        returnObject.setList(dto.getBtlist());
        returnObject.setData(dto.getNrlist());
        returnObject.setMap(map);
        return returnObject;
    }

    // 时 str pin

    // 1. 时 重新编写表头 逻辑拼装
    public StringBuilder sjkld_s(String zlid, String dwhs, String time_start, String time_end, String sjkld,
            String nhlx, BesQstjfxData dto) {
        StringBuilder str = new StringBuilder();
        // 循环遍历查询 先判断
        if (Validate_y.noNull(zlid)) {
            String arr[] = zlid.split(",");
            // 1. 查询表头数据 根据条件
            String sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
            if ("0".equals(sjkld)) {// 时
                sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
            } else if ("1".equals(sjkld)) {// 天
                sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m')";
            } else if ("2".equals(sjkld)) {// 月
                sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
            } else if ("3".equals(sjkld)) {// 年
                sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
            }
            List<Map<String, Object>> sjkld_s_bt = besbranchdatamapper.sjkld_s_bt(sjgs, arr, time_start, time_end,
                    sjkld, nhlx);
            // echars存放表头list
            List<String> btlist = new ArrayList<>();
            for (Map<String, Object> btmap : sjkld_s_bt) {
                String btsj = btmap.get("sj").toString();
                // 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
                for (Map<String, Object> ssmap : sjkld_s_bt_s) {
                    String sj = ssmap.get("sj").toString();
                    String e_sj = "";// echars 时间
                    if (Validate_y.noNull(sj)) {
                        if ("0".equals(sjkld)) {// 时
                            e_sj = sj.substring(10, 13);
                            sj = sj.substring(5, 13);
                            str.append("<th style='white-space: nowrap;text-align:center;'>" + sj + "</th>");
                            btlist.add(e_sj);
                        } else if ("1".equals(sjkld)) {// 天
                            e_sj = sj.substring(8, 11);
                            sj = sj.substring(0, 11);
                            str.append("<th style='white-space: nowrap;text-align:center;'>" + sj + "</th>");
                            btlist.add(e_sj);
                        } else if ("2".equals(sjkld)) {// 月
                            e_sj = sj.substring(5, 7);
                            str.append("<th style='white-space: nowrap;text-align:center;'>" + sj + "</th>");
                            btlist.add(e_sj);
                        } else if ("3".equals(sjkld)) {// 年
                            e_sj = sj.substring(0, 4);
                            str.append("<th style='white-space: nowrap;text-align:center;'>" + sj + "</th>");
                            btlist.add(e_sj);
                        }
                    }
                }
            }
            dto.setBtlist(btlist);
            str.append("</tr>");
        } else {
            str.append("</tr>");
        }
        return str;
    }

    // 1.1 时 重新编写内容 逻辑拼装 zlid支路id 为每一次循环的id zlids为所有的支路id
    public StringBuilder sjkld_s_nr(String zlids, String time_start, String time_end, String sjkld, String dwhs,
            String nhlx, String tblx, BesQstjfxData dto, String tqtime_start, String tqtime_end) {
        StringBuilder str = new StringBuilder();
        StringBuilder thstr = new StringBuilder();
        String arr[] = zlids.split(",");
        List<Map<String, Object>> bqlist = new ArrayList<>();// 拼echars 本期和同期 合并
        // 1. 查询表头数据 根据条件
        String sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
        if ("0".equals(sjkld)) {// 时
            sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
        } else if ("1".equals(sjkld)) {// 天
            sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m')";
        } else if ("2".equals(sjkld)) {// 月
            sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
        } else if ("3".equals(sjkld)) {// 年
            sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
        }
        for (int i = 0; i < arr.length; i++) {
            // 根据zlid查询支路名称
            String zlmc = besbranchdatamapper.getzlmc(arr[i]);
            Map<String, String> table_xmap = besbranchdatamapper.pin_xmap(arr[i]);
            // 1.0 本期
            List<String> sjlist = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            str.append("<tr>");
            str.append("<th rowspan='2' class='czjz' style='white-space: nowrap;'>" + table_xmap.get("NAME") + "</th>");
            str.append("<th id=" + table_xmap.get("ID") + " style='white-space: nowrap;text-align:center;'>本期</th>");
            List<Map<String, Object>> sjkld_s_bt = besbranchdatamapper.sjkld_s_bt(sjgs, arr, time_start, time_end,
                    sjkld, nhlx);
            for (Map<String, Object> btmap : sjkld_s_bt) {
                String btsj = btmap.get("sj").toString();
                // 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
                for (Map<String, Object> ssmap : sjkld_s_bt_s) {
                    List<String> lista = new ArrayList<>();
                    String sj = ssmap.get("sj").toString();
                    // 查询数据 根据zlid和循环查询的时间
                    Map<String, String> datamap = besbranchdatamapper.sjkld_s_bt_nr(arr[i], sj, sjkld, nhlx, dwhs);
                    if (Validate_y.isNull(datamap)) {
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + 0.00 + "</th>");
                        thstr.append("<th style='white-space: nowrap;text-align:center;'>" + 0.00 + "</th>");
                        lista.add("0.00");
                    } else {
                        String data = datamap.get("data");
                        String id = datamap.get("id");
                        str.append(
                                "<th id=" + id + " style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        thstr.append("<th style='white-space: nowrap;text-align:center;'>" + 0.00 + "</th>");
                        lista.add(data);
                    }
                    sjlist.addAll(lista);
                }
            }
            map.put("name", zlmc + "本期");
            map.put("type", tblx);
            map.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
            map.put("data", sjlist);
            bqlist.add(map);
            str.append("</tr>");

            // 2.0同期
            List<String> tqsjlist = new ArrayList<>();
            Map<String, Object> tqmap = new HashMap<>();
            str.append("<tr>");
            str.append("<th id=" + table_xmap.get("ID") + " style='white-space: nowrap;text-align:center;'>同期</th>");
            List<Map<String, Object>> tqsjkld_s_bt = besbranchdatamapper.sjkld_s_bt(sjgs, arr, tqtime_start, tqtime_end,
                    sjkld, nhlx);
            for (Map<String, Object> tqbtmap : tqsjkld_s_bt) {
                String btsj = tqbtmap.get("sj").toString();
                // 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
                for (Map<String, Object> ssmap : sjkld_s_bt_s) {
                    List<String> lista = new ArrayList<>();
                    String sj = ssmap.get("sj").toString();
                    // 查询数据 根据zlid和循环查询的时间
                    Map<String, String> datamap = besbranchdatamapper.sjkld_s_bt_nr(arr[i], sj, sjkld, nhlx, dwhs);
                    if (Validate_y.isNull(datamap)) {
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + 0.00 + "</th>");
                        lista.add("0.00");
                    } else {
                        String data = datamap.get("data");
                        String id = datamap.get("id");
                        str.append(
                                "<th id=" + id + " style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        lista.add(data);
                    }
                    tqsjlist.addAll(lista);
                }
            }
            if (tqsjkld_s_bt.size() > 0) {

            } else {
                str.append(thstr);
                thstr.delete(0, thstr.length());// 清空
            }
            tqmap.put("name", zlmc + "同期");
            tqmap.put("type", tblx);
            tqmap.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
            tqmap.put("data", tqsjlist);
            bqlist.add(tqmap);
            str.append("</tr>");
        }
        dto.setNrlist(bqlist);
        return str;
    }

    /**
     * 
     * Description:重写同环比后台逻辑  
     * @param request
     * @return   
     * @see com.efounder.JEnterprise.service.dataAnalysises.BesZlthbfxService#thbPinTable(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ISSPReturnObject thbPinTable(BesQstjfxData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 1.0 先根据条件将所有数据都查询出来
        dto.setZlbh(dto.getZlid().split(","));// 将对应格式
        dto.setZlmc(dto.getZlname().split(","));
        try {
            List<Map<String, Object>> bqAllData = besbranchdatamapper.bqAllData(dto);// 本期 按时间正序排列
            List<String> AlltimeList = new ArrayList<>();// 时间list 去重前
            List<String> timeList = new ArrayList<>();// 时间list 去重后
            // 对本期数据进行处理
            for (int i = 0; i < bqAllData.size(); i++) {
                AlltimeList.add(bqAllData.get(i).get("f_cjsj").toString());// 将本期时间存入timeList中
            }
            Set<String> set = new HashSet<>();
            set.addAll(AlltimeList);
            timeList.addAll(set);// 去重后的list
            Collections.sort(timeList);// 重新排序
            List<Map<String, Object>> tqAllData = besbranchdatamapper.tqAllData(dto);// 同期
            // 拼table 先拼表头(thead) 再拼内容(tbody)
            // 1.0 拼表头
            StringBuilder str = new StringBuilder();
            str.append("<thead>");
            str.append(
                    "<tr class='header_color'><th class='czjz' colspan='2' style='white-space: nowrap;width:20%;'>支路名称</th>");
            // 判断时 天 月 年
            StringBuilder title = pin_thead(dto, timeList);
            str.append(title);
            str.append("</thead>");

            // 2.0 拼内容
            str.append("<tbody>");
            StringBuilder content = pin_tbody(dto, timeList, bqAllData, tqAllData);
            str.append(content);
            str.append("</tbody>");
            Map<String, Object> map = new HashMap<>();
            map.put("str", str);
            if (bqAllData.size() > 0) {
                returnObject.setStatus("1");
                returnObject.setList(dto.getBtlist());
                returnObject.setData(dto.getNrlist());
                returnObject.setMap(map);
            } else {
                returnObject.setStatus("0");// 返回失敗
            }
        } catch (Exception e) {
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    
    /**
     * 
     * @Title: pin_thead
     * @Description:拼装表头
     * @return: StringBuilder
     * @throws
     */
    public StringBuilder pin_thead(BesQstjfxData dto, List<String> timeList) {
        StringBuilder str = new StringBuilder();
        //先判断时间颗粒度是什么类型 来确定表头如何展示
        String timeGranularity = dto.getSjkld();//时间颗粒度
        // 时间截取格式
        int subStart = 5, subEnd = 13;// table
        int echarsStart = 5, echarsEnd = 13;// echars
        if ("0".equals(timeGranularity)) {// 0 时 11-24 00
            subStart = 5;
            subEnd = 13;
            echarsStart = 11;
            echarsEnd = 13;
        } else if ("1".equals(timeGranularity)) {// 1 天
            subStart = 0;
            subEnd = 10;
            echarsStart = 8;
            echarsEnd = 10;
        } else if ("2".equals(timeGranularity)) {// 2 月
            subStart = 0;
            subEnd = 7;
            echarsStart = 5;
            echarsEnd = 7;
        } else if ("3".equals(timeGranularity)) {// 3 年
            subStart = 0;
            subEnd = 4;
            echarsStart = 0;
            echarsEnd = 4;
        }
        // 循环时间
        List<String> echarsBt = new ArrayList<>();
        String time = "";
        for (int i = 0; i < timeList.size(); i++) {
            time = timeList.get(i);
            str.append(
                    "<th style='white-space: nowrap;text-align:center;'>" + time.substring(subStart, subEnd) + "</th>");
            echarsBt.add(time.substring(echarsStart, echarsEnd));
        }
        str.append("</tr>");
        dto.setBtlist(echarsBt);
        return str;
    }

    /**
     * 
     * @Title: pin_tbody
     * @Description:拼内容
     * @return: StringBuilder
     * @throws
     */
    public StringBuilder pin_tbody(BesQstjfxData dto, List<String> timeList, List<Map<String, Object>> bqAllData,
            List<Map<String, Object>> tqAllData) {
        StringBuilder str = new StringBuilder(); // 本期str
        StringBuilder tqstr = new StringBuilder(); // 同期tqstr
        String[] zlbh = dto.getZlbh(); // 所有的支路编号
        List<Map<String, Object>> echarsContentList = new ArrayList<>();
        for (int i = 0; i < zlbh.length; i++) {
            List<String> echarsBqData = new ArrayList<>();// echars数据 本期
            List<String> echarsTqData = new ArrayList<>();// echars数据 同期
            String zlmc = dto.getZlmc()[i]; // 支路名称
            str.append("<tr>");
            str.append("<th rowspan='2' class='czjz' style='white-space: nowrap;'>" + zlmc + "</th>");
            str.append("<th style='white-space: nowrap;text-align:center;'>本期</th>");
            // 然后循环时间 将本期数据拼装
            for (int j = 0; j < timeList.size(); j++) {
                // 循环同期数据 将时间相同的数据取出来
                for (int d = 0; d < bqAllData.size(); d++) {
                    String time = timeList.get(j); // 循环时间
                    String dataTime = bqAllData.get(d).get("f_cjsj").toString(); // 本期数据中的时间
                    String dataZlbh = bqAllData.get(d).get("F_ZLBH").toString(); // 本期数据中的支路编号
                    if (time.equals(dataTime) && zlbh[i].equals(dataZlbh)) { // 时间相同并且支路编号相同才拼装 以确定唯一
                        String data = bqAllData.get(d).get("data").toString(); // 本期数据
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        echarsBqData.add(data);
                        if (tqAllData.size() > 0) {
                            String tqdata = tqAllData.get(d).get("data").toString(); // 同期数据
                            echarsTqData.add(tqdata);
                            if (Validate_y.noNull(tqdata)) {
                                tqstr.append("<th style='white-space: nowrap;text-align:center;'>" + tqdata + "</th>");
                            } else {
                                tqstr.append("<th style='white-space: nowrap;text-align:center;'>0.00</th>");
                            }
                        } else {
                            tqstr.append("<th style='white-space: nowrap;text-align:center;'>0.00</th>");
                        }
                    }
                }
            }
            // 拼装echars 本期
            Map<String, Object> echarsBqContent = new HashMap<>();
            echarsBqContent.put("name", zlmc + "本期");
            echarsBqContent.put("type", dto.getTblx());
            echarsBqContent.put("smooth", "true"); // 平滑曲线
            echarsBqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
            echarsBqContent.put("data", echarsBqData);
            echarsContentList.add(echarsBqContent);
            // 同期
            Map<String, Object> echarsTqContent = new HashMap<>();
            echarsTqContent.put("name", zlmc + "同期");
            echarsTqContent.put("type", dto.getTblx());
            echarsTqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
            echarsTqContent.put("data", echarsTqData);
            echarsContentList.add(echarsTqContent);
            str.append("</tr>");
            str.append("<tr>");
            str.append("<th style='white-space: nowrap;text-align:center;'>同期</th>");
            str.append(tqstr);
            tqstr.delete(0, tqstr.length()); // 清空
            str.append("</tr>");
        }
        dto.setNrlist(echarsContentList);
        return str;
    }

    /**
     * 单位--重写
     */
    @Override
    public ISSPReturnObject thbPinTabledw(BesQstjfxData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 1.0 先根据条件将所有数据都查询出来
        dto.setZlbh(dto.getZlid().split(","));// 将对应格式
        dto.setZlmc(dto.getZlname().split(","));
        String[] zlmc = dto.getZlname().split(",");
        List<String> dwmc = new ArrayList<>();
        for (int z = 0; z < zlmc.length; z++) {
            dwmc.add(zlmc[z]);
        }
        dto.setBtlist(dwmc);
        try {
            List<Map<String, Object>> bqAllData = besbranchdatamapper.bqAllData(dto);// 本期 按时间正序排列
            List<String> AlltimeList = new ArrayList<>();// 时间list 去重前
            List<String> timeList = new ArrayList<>();// 时间list 去重后
            // 对本期数据进行处理
            for (int i = 0; i < bqAllData.size(); i++) {
                AlltimeList.add(bqAllData.get(i).get("f_cjsj").toString());// 将本期时间存入timeList中
            }
            Set<String> set = new HashSet<>();
            set.addAll(AlltimeList);
            timeList.addAll(set);// 去重后的list
            Collections.sort(timeList);// 重新排序
            List<Map<String, Object>> tqAllData = besbranchdatamapper.tqAllData(dto);// 同期
            // 拼table 先拼表头(thead) 再拼内容(tbody)
            // 1.0 拼表头
            StringBuilder str = new StringBuilder();
            str.append("<thead>");
            str.append(
                    "<tr class='header_color'><th class='czjz' colspan='2' style='white-space: nowrap;width:20%;'>支路名称</th>");
            // 判断时 天 月 年
            StringBuilder title = pin_theaddw(dto, timeList);
            str.append(title);
            str.append("</thead>");

            // 2.0 拼内容
            str.append("<tbody>");
            StringBuilder content = pin_tbodydw(dto, timeList, bqAllData, tqAllData);
            str.append(content);
            str.append("</tbody>");
            Map<String, Object> map = new HashMap<>();
            map.put("str", str);
            if (bqAllData.size() > 0) {
                returnObject.setStatus("1");
                returnObject.setList(dto.getBtlist());
                returnObject.setData(dto.getNrlist());
                returnObject.setMap(map);
            } else {
                returnObject.setStatus("0");// 返回失敗
            }
        } catch (Exception e) {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月27日 下午8:21:54
     * @Description:单位
     * @param dto
     * @param timeList
     * @return StringBuilder
     */
    public StringBuilder pin_theaddw(BesQstjfxData dto, List<String> timeList) {
        StringBuilder str = new StringBuilder();
        // 先判断时间颗粒度是什么类型 来确定表头如何展示
        String timeGranularity = dto.getSjkld();// 时间颗粒度
        // 时间截取格式
        int subStart = 5, subEnd = 13;// table
        int echarsStart = 5, echarsEnd = 13;// echars
        if ("0".equals(timeGranularity)) {// 0 时 11-24 00
            subStart = 5;
            subEnd = 13;
            echarsStart = 11;
            echarsEnd = 13;
        } else if ("1".equals(timeGranularity)) {// 1 天
            subStart = 0;
            subEnd = 10;
            echarsStart = 8;
            echarsEnd = 10;
        } else if ("2".equals(timeGranularity)) {// 2 月
            subStart = 0;
            subEnd = 7;
            echarsStart = 5;
            echarsEnd = 7;
        } else if ("3".equals(timeGranularity)) {// 3 年
            subStart = 0;
            subEnd = 4;
            echarsStart = 0;
            echarsEnd = 4;
        }
        // 循环时间
        List<String> echarsBt = new ArrayList<>();
        String time = "";
        for (int i = 0; i < timeList.size(); i++) {
            time = timeList.get(i);
            str.append(
                    "<th style='white-space: nowrap;text-align:center;'>" + time.substring(subStart, subEnd) + "</th>");
            echarsBt.add(time.substring(echarsStart, echarsEnd));
        }
        str.append("</tr>");
//        dto.setBtlist(echarsBt);
        return str;
    }

    /**
     * 
     * @Title: pin_tbody @Description:拼内容 @return: StringBuilder @throws
     */
    public StringBuilder pin_tbodydw(BesQstjfxData dto, List<String> timeList, List<Map<String, Object>> bqAllData,
            List<Map<String, Object>> tqAllData) {
        StringBuilder str = new StringBuilder(); // 本期str
        StringBuilder tqstr = new StringBuilder(); // 同期tqstr
        String[] zlbh = dto.getZlbh(); // 所有的支路编号
        List<Map<String, Object>> echarsContentList = new ArrayList<>();
        List<Object> echarsBqData = new ArrayList<>();// echars数据 本期
        List<Object> echarsTqData = new ArrayList<>();// echars数据 同期

        String f_type = dto.getSjkld();

        for (int d = 0; d < zlbh.length; d++) {
            for (int a = 0; a < timeList.size(); a++) {
                //根据支路编号和时间查询数据库bes_branch_data表中是否有数据
                List<Map<String,Object>> whetherFdata = besbranchdatamapper.selectWhetherFdata(zlbh[d],timeList.get(a).replace(".0",""),f_type);

                Timestamp ts = new Timestamp(System.currentTimeMillis());
                if (whetherFdata.size() == 0) {
                    Map<String,Object> map = new HashMap();
                    map.put("f_cjsj", Timestamp.valueOf(timeList.get(a)));
                    map.put("data",0.0);
                    map.put("F_ZLBH",zlbh[d]);
                    bqAllData.add(map);
                }

            }
        }

        for (int i = 0; i < zlbh.length; i++) {
            double bqsum = 0.00;
            double tqsum = 0.00;
            String zlmc = dto.getZlmc()[i]; // 支路名称
            str.append("<tr>");
            str.append("<th rowspan='2' class='czjz' style='white-space: nowrap;'>" + zlmc + "</th>");
            str.append("<th style='white-space: nowrap;text-align:center;'>本期</th>");
            // 然后循环时间 将本期数据拼装
            for (int j = 0; j < timeList.size(); j++) {
                // 循环同期数据 将时间相同的数据取出来
                for (int d = 0; d < bqAllData.size(); d++) {
                    String time = timeList.get(j); // 循环时间
                    String dataTime = bqAllData.get(d).get("f_cjsj").toString(); // 本期数据中的时间
                    String dataZlbh = bqAllData.get(d).get("F_ZLBH").toString(); // 本期数据中的支路编号
                    if (time.equals(dataTime) && zlbh[i].equals(dataZlbh)) { // 时间相同并且支路编号相同才拼装 以确定唯一
                        String data = bqAllData.get(d).get("data").toString(); // 本期数据
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        bqsum += parse(data);
                        if (tqAllData.size() > 0) {
                            String tqdata = tqAllData.get(d).get("data").toString(); // 同期数据
                            tqsum += parse(tqdata);
                            if (Validate_y.noNull(tqdata)) {
                                tqstr.append("<th style='white-space: nowrap;text-align:center;'>" + tqdata + "</th>");
                            } else {
                                tqstr.append("<th style='white-space: nowrap;text-align:center;'>0.00</th>");
                            }
                        } else {
                            tqstr.append("<th style='white-space: nowrap;text-align:center;'>0.00</th>");
                        }
                    }
                }
            }
            echarsBqData.add(String.format("%.2f", bqsum));
            echarsTqData.add(String.format("%.2f", tqsum));
            str.append("</tr>");
            str.append("<tr>");
            str.append("<th style='white-space: nowrap;text-align:center;'>同期</th>");
            str.append(tqstr);
            tqstr.delete(0, tqstr.length()); // 清空
            str.append("</tr>");
        }

        // 拼装echars 本期
        Map<String, Object> echarsBqContent = new HashMap<>();
        echarsBqContent.put("name", "本期数据");
        echarsBqContent.put("type", dto.getTblx());
        echarsBqContent.put("smooth", "true"); // 平滑曲线
        echarsBqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
        echarsBqContent.put("data", echarsBqData);
        echarsContentList.add(echarsBqContent);
        // 同期
        Map<String, Object> echarsTqContent = new HashMap<>();
        echarsTqContent.put("name", "同期数据");
        echarsTqContent.put("type", dto.getTblx());
        echarsTqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
        echarsTqContent.put("data", echarsTqData);
        echarsContentList.add(echarsTqContent);
        dto.setNrlist(echarsContentList);
        return str;
    }

    // 字符串转换成double
    public double parse(String str) {
        double b = Double.parseDouble(str);
        return b;
    }

    /**
     *
     * @Description: 加载能源统计分析--支路用能--用能统计分析
     *
     * @auther: wanghongjie
     * @date: 11:50 2021/5/11
     * @param:
     * @return:
     *
     */
    @Override
    public ISSPReturnObject statisAnalyOfEnergyConsumption(BesQstjfxData besQstjfxData) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {

            List<BesHouseholdData> list = besbranchdatamapper.searchstatisAnalyOfEnergyConsumptionData(besQstjfxData);

            returnObject.setData(list);

            returnObject.setMsg("获取支路用能趋势统计分析数据成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取支路用能趋势统计分析数据失败");
            returnObject.setStatus("0");
            e.printStackTrace();
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject statisAnalyOfEnergyConsumptionDep(BesQstjfxData besQstjfxData) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            //取包含的所有部门
            List<String> bmList = besQstjfxData.getZlbhs();

            List<BesBranchData> returnList = new ArrayList<>();
            //根据部门查询单个部门下的数据
            for(String str : bmList){
                List<Map<String,Object>> branchList = besbranchdatamapper.queryAllBranchByDep(str);
                List<Map<String,Object>> ammeterList = besbranchdatamapper.queryAllAmmeterByDep(str);
                List<Map<String,Object>> allList = new ArrayList<>();
                if(ammeterList.size()>0 || branchList.size()>0){
                    if(branchList.size()>0){
                        allList.addAll(branchList);
                    }
                    if(ammeterList.size()>0){
                        allList.addAll(ammeterList);
                    }
                    String fType = besQstjfxData.getfType();
                    String nhlx = besQstjfxData.getNhlx();
                    nhlx = "01000";
                    String time_start = besQstjfxData.getTime_start();
                    String time_end = besQstjfxData.getTime_end();
                    //根据所有支路，电表取该部门总数居
                    /*List<BesBranchData> list*/
                    List<BesBranchData> list = besbranchdatamapper.searchstatisAnalyOfEnergyConsumptionDataDep(str,fType,nhlx,time_start,time_end,branchList,ammeterList);

                    a: for(BesBranchData b : list){
                        b: for(Map m : allList){
                            if(m.get("bh").toString().equals(b.getfZlbh())){
                                b.setfData(b.getfData()*Double.parseDouble(m.get("xs").toString()));
                                b.setfZlbh(str);
                            }
                            b.setfZlbh(str);
                        }
                        if("0".equals(fType)){
                            String FCJSJ = b.getfCjsj().substring(0,13)+":00:00";
                            b.setfCjsj(FCJSJ);
                        }else if("1".equals(fType)){
                            String FCJSJ = b.getfCjsj().substring(0,10)+" 00:00:00";
                            b.setfCjsj(FCJSJ);
                        }else if("2".equals(fType)){
                            String FCJSJ = b.getfCjsj().substring(0,7)+"-01 00:00:00";
                            b.setfCjsj(FCJSJ);
                        }else{
                            String FCJSJ = b.getfCjsj().substring(0,4)+"-01-01 00:00:00";
                            b.setfCjsj(FCJSJ);
                        }
                    }


                    Map<String, List<BesBranchData>> collect =
                            list.stream().collect(Collectors.groupingBy(BesBranchData::getfCjsj, LinkedHashMap::new, Collectors.toList()));

                    List<BesBranchData> resultList = new ArrayList<>();
                    for(String key:collect.keySet()){
                        List<BesBranchData> mapList = collect.get(key);
                        Double dou = 0.00;
                        for(BesBranchData bes: mapList){
                            dou = dou+bes.getfData();
                        }
                        mapList.get(0).setfData(dou);
                        resultList.add(mapList.get(0));
                    }

                    returnList.addAll(resultList);
                }

            }
            //根据部门取所有支路、电表
            returnObject.setData(returnList);

            returnObject.setMsg("获取支路用能趋势统计分析数据成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取支路用能趋势统计分析数据失败");
            returnObject.setStatus("0");
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     *
     * @Description: 加载能源统计分析--支路用能--同比环比分析
     *
     * @auther: wanghongjie
     * @date: 16:21 2021/5/12
     * @param: [besQstjfxData]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject yoyAndMoMAnalysis(BesQstjfxData besQstjfxData) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            //获取本期数据
            List<BesHouseholdData> listBQ = besbranchdatamapper.yoyAndMoMAnalysisBQ(besQstjfxData);
            //获得同期数据
            List<BesHouseholdData> listTQ = besbranchdatamapper.yoyAndMoMAnalysisTQ(besQstjfxData);

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("本期",listBQ);
            dataMap.put("同期",listTQ);
            returnObject.setData(dataMap);

            returnObject.setMsg("获取支路用能趋势统计分析数据成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取支路用能趋势统计分析数据失败");
            returnObject.setStatus("0");
            e.printStackTrace();
        }
        return returnObject;
    }
}
