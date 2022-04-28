package com.efounder.JEnterprise.service.dataAnalysises.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesOriginalDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesOriginalData;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.service.dataAnalysises.BESZlynjcService;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.Validate_y;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.springframework.util.StringUtils;

/**
 * @author 杨超
 * @version 创建时间：2018年11月1日 上午10:45:43
 * @parameter
 * @version 1.0
 */
@Service("/BESZlynjcService")
public class BESZlynjcServiceimpl implements BESZlynjcService {
    private static final Logger log = LoggerFactory.getLogger(BESZlynjcServiceimpl.class);

    @Autowired
    private BesOriginalDataMapper besoriginaldatamapper;

    // 根据条件拼table
    /*@Override
    public ISSPReturnObject pin_table1(BesOriginalData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String start = dto.getTime_start();// 起始时间
        String end = dto.getTime_end();// 终止时间
        String starttime = "";// 有效起始时间
        String endtime = "";// 有效终止时间
        String zlids = dto.getThree_zlids();
        String[] zlidsArray = dto.getThree_zlids().split(",");// 三级支路ids
        if("".equals(zlids) || null == zlids) {
        	zlidsArray = dto.getTwo_zlids().split(",");// 二级支路ids
        }
        // 三级支路id 查询传过来支路id所有的电表
        List<Map<String, String>> amtterlist = besoriginaldatamapper.getamtterlist(zlidsArray);
        List<Map<String, Object>> list = new ArrayList<>();
        // 循环电表 重新拼装数据list
        if (Validate_y.noNull(amtterlist)) {
            for (Map<String, String> ammtermap : amtterlist) {
                // 根据电表id查询相关信息
                String dbid = ammtermap.get("AMTTERIDS");
                String zlmc = besoriginaldatamapper.getzlmc(ammtermap.get("ZLBH"));
                Map<String, String> dbxxmap = besoriginaldatamapper.getdbxx(dbid);
                Map<String, Object> map = new HashMap<>();
                map.put("zlmc", zlmc);// 支路名称
                map.put("dbmc", dbxxmap.get("DBMC"));// 电表名称
                map.put("dbxlh", dbxxmap.get("DBXLH"));// 电表序列号
                // 先查询时间段内有数据的起始时间
                List<Map<String, String>> qslist = besoriginaldatamapper.getqslist(start, end, dbid);
                if (qslist.size() > 0) {
                    starttime = qslist.get(0).get("SJ");
                    endtime = qslist.get(qslist.size() - 1).get("SJ");
                } else {
                    starttime = start;
                    endtime = end;
                }
                // 根据电表id查询起始数据
                String start_data = besoriginaldatamapper.getStrat_data(starttime, dbid);
                String end_data = besoriginaldatamapper.getEnd_data(endtime, dbid);
                if (Validate_y.isNull(start_data)) {
                    start_data = "0.00";
                }
                if (Validate_y.isNull(end_data)) {
                    end_data = "0.00";
                }
                float s = Float.valueOf(start_data);
                float e = Float.valueOf(end_data);
                float cha = e - s;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
                String p = decimalFormat.format(cha);// format 返回的是字符串
                map.put("qssj", s);// 起始数据
                map.put("jzsj", e);// 截止数据
                map.put("sjcz", p);// 差值
                list.add(map);
            }
        }
        returnObject.setList(list);
        return returnObject;
    }*/

    // 根据条件拼table
    @Override
    public ISSPReturnObject pin_table(BesOriginalData dto) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String timeStart = dto.getTime_start(); // 开始时间
        String timeEnd = dto.getTime_end(); // 结束时间
        String branch = dto.getZlids(); // 所有支路编号

        if (!StringUtils.hasText(timeStart)
                || !StringUtils.hasText(timeEnd)
                || !StringUtils.hasText(branch) )
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        String[] branchArray = branch.split(",");

        List<Map<String, String>> ammeterList = besoriginaldatamapper.getamtterlist(branchArray);

        if (ammeterList == null || ammeterList.isEmpty())
        {
            returnObject.setStatus("0");
            return returnObject;
        }

        Set<String> sysName = new HashSet<>();

        ammeterList.forEach(ammeterMap ->
        {
            sysName.add(ammeterMap.get("AMTTERIDS"));
        });

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(timeEnd);
            Calendar calendar= Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);

            timeEnd = dateFormat.format(calendar.getTime());

        } catch (ParseException e) {
            returnObject.setStatus("0");
            e.printStackTrace();
            return returnObject;
        }

        List<Map<String, String>> dataList = besoriginaldatamapper.getByStartTimeAndEndTimeAndSysName(timeStart, timeEnd, new ArrayList<>(sysName));

        returnObject.setStatus("1");
        returnObject.setData(ammeterList);
        returnObject.setList(dataList);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--实时参数查询 动态查询展示数据
     * 
     * @param
     * @return
     */
    @Override
    public ISSPReturnObject zl_sscscx_sssj(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String zlid = request.getParameter("zlid");// 获取selected 支路id
        String level = request.getParameter("level");// 获取selected 几级节点
        String fnybh = request.getParameter("fnybh");// 获取能源编号
        List<Map<String, String>> sssjlist = new ArrayList<>();// 电表实时数据
        List<Map<String, String>> alllist = new ArrayList<>();// 所有
        // 1. 先查询表头 动态拼装
        List<Map<String, String>> headerList = new ArrayList<>();
        // 2.循环表头
//        if (headerList.size() > 0) {
        // 再循环电表id 如果是二级节点 则获取节点下所有的支路,然后获取所有支路下所有的电表 如果是三级节点 则获取节点下所有的电表
        if ("2".equals(level)) {// 2级节点
            // 查询2级节点下所有的支路 group_concat()
            String[] zlids = besoriginaldatamapper.getzlids(zlid);
            if (zlids == null) {

                return returnObject;
            }
            String[] amtterids = besoriginaldatamapper.getamterids(zlids);// 所有支路下所有的电表ids(用的F_DBSYS_NAME关联)
            if (amtterids.length > 0) {
                String[] amtterid = removeRepeat(amtterids);
                //根据电表系统名称查询当前电表的电能参数
                List<Map<String,Object>> electric_paramsList = besoriginaldatamapper.selectElectric_paramsList(amtterid);

                if (electric_paramsList == null || electric_paramsList.size() == 0){
                    return returnObject;
                }

                for (Map<String,Object> electric_param : electric_paramsList){
                    Map<String, String> electric_params = new HashMap<>();
                    electric_params.put("DNBH", (String) electric_param.get("F_DNBH"));
                    electric_params.put("UNIT", (String) electric_param.get("F_UNIT"));
                    electric_params.put("NAME", (String) electric_param.get("F_DNMC"));
                    headerList.add(electric_params);
                }
                for (int j = 0; j < amtterid.length; j++) {// 循环电表
                    String amttername = besoriginaldatamapper.getdbxx(amtterid[j]).get("NICKNAME");
                    Map<String, String> map = new HashMap<>();
                    String data = "0.00";
                    map.put("a00", amttername);
                    for (int a = 0; a < headerList.size(); a++) {
                        String dnbh = headerList.get(a).get("DNBH");// 获取电能编号
                        sssjlist = besoriginaldatamapper.getsssjlist_two(amtterid[j], dnbh);
                        if (sssjlist.size() > 0) {
                            data = String.valueOf(sssjlist.get(0).get("DATA"));
                        } else {
                            data = "0.00";
                        }
                        map.put("a" + dnbh, data);
                    }
                    alllist.add(map);
                }
            }
        } else if ("3".equals(level)) {// 3级节点
            String[] zlidsz1 = zlid.split(",");
            String[] amtterids = besoriginaldatamapper.getamterids(zlidsz1);
            if (amtterids.length > 0) {
                String[] amtterid1 = removeRepeat(amtterids);
                //根据电表系统名称查询当前电表的电能参数
                List<Map<String,Object>> electric_paramsList = besoriginaldatamapper.selectElectric_paramsList(amtterid1);
                if (electric_paramsList == null || electric_paramsList.size() == 0){
                    return returnObject;
                }

                for (Map<String,Object> electric_param : electric_paramsList){
                    Map<String, String> electric_params = new HashMap<>();
                    electric_params.put("DNBH", (String) electric_param.get("F_DNBH"));
                    electric_params.put("UNIT", (String) electric_param.get("F_UNIT"));
                    electric_params.put("NAME", (String) electric_param.get("F_DNMC"));
                    headerList.add(electric_params);
                }

                for (int z = 0; z < amtterid1.length; z++) {
                    String amttername = besoriginaldatamapper.getdbxx(amtterid1[z]).get("NICKNAME");
                    Map<String, String> map = new HashMap<>();
                    String data = "0.00";
                    map.put("a00", amttername);
                    for (int a = 0; a < headerList.size(); a++) {
                        String dnbh = headerList.get(a).get("DNBH");// 获取电能编号
                        sssjlist = besoriginaldatamapper.getsssjlist_two(amtterid1[z], dnbh);
                        if (sssjlist.size() > 0) {
                            data = String.valueOf(sssjlist.get(0).get("DATA"));
                        } else {
                            data = "0.00";
                        }
                        map.put("a" + dnbh, data);
                    }
                    alllist.add(map);
                }
            }
        }
//        }
        returnObject.setData(alllist);
        returnObject.setList(headerList);
        return returnObject;
    }

    //String数组去重
    public static String[] removeRepeat(String[] arr){
        Set<String> set=new TreeSet<>();
        for (int i=0;i<arr.length;++i){
            set.add(arr[i]);
        }
        String[] need=new String[set.size()];
        int i=0;
        for (String str:set){
            need[i]=str;
            ++i;
        }
        return  need;
    }

    /**
     * 能源数据集抄--支路用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @Override
    public ISSPReturnObject zl_fsdtj_jcsj(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 获取selected 支路id
        String zlids = request.getParameter("zlid");
        // 起始时间
        String start = request.getParameter("time_start");
        // 终止时间
        String end = request.getParameter("time_end");
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> zlmap = new HashMap<>();
        if (Validate_y.noNull(zlids)) {
            String[] zlid = zlids.split(",");
            // 循环支路 查询
            for (int i = 0; i < zlid.length; i++) {
                String zlmc = besoriginaldatamapper.getzlmc(zlid[i]);
                String jss = besoriginaldatamapper.getjs(zlid[i]);
                if ("2".equals(jss)) {
                    zlmap = besoriginaldatamapper.Select_fsdtj(zlid[i], start, end);
                } else {
                    zlmap = besoriginaldatamapper.Select_fsdtj_notwo(zlid[i], start, end);
                }
                zlmap.put("ZLMC", zlmc);
                list.add(zlmap);
            }
        }
        returnObject.setList(list);
        return returnObject;
    }

    /**
     * 支路id查询选择电表下拉列表
     */
    @Override
    public ISSPReturnObject zl_cscx_Select(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String zlid = request.getParameter("zlid");// 获取支路id
        // 根据支路id查询电表
        String[] zlidList = zlid.split(",");
        List<Map<String, String>> ammeter_list = besoriginaldatamapper.Select_ammeterlist(zlidList);
        returnObject.setList(ammeter_list);
        return returnObject;
    }

    /**
     * 电能参数查询 根据电表id
     */
    @Override
    public ISSPReturnObject zl_cscx_Select_dncs(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String ammeterid = request.getParameter("ammeterid");
        List<Map<String, String>> dncs_list = besoriginaldatamapper.zl_cscx_Select_dncs(ammeterid);
        returnObject.setList(dncs_list);
        returnObject.setData(dncs_list);
        return returnObject;
    }

    /**
     * 电能参数统计分析
     */
    @Override
    public ISSPReturnObject zl_cscx_dncs_tjfx(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String ammeterid = request.getParameter("ammeterid");// 电表id
        String time = request.getParameter("time");// 时间
        String dncs = request.getParameter("dncs");// 电能参数id
        String[] dncsid = dncs.split(",");
        Map<String, Object> map = new HashMap<>();
        map.put("ammeterid", ammeterid);
        map.put("time", time);
        map.put("dncsid", dncsid);
        // 查询总数据
        List<Map<String, String>> allList = besoriginaldatamapper.dncsList(map);
        // 定义时间存储list
        List<String> timeList = new ArrayList<>();
        // 定义去重时间存储list
        List<String> newTimeList = new ArrayList<>();
        for (Map<String, String> tmap : allList) {
            timeList.add(tmap.get("f_cjsj").toString());
        }
        Set<String> set = new HashSet<>();
        set.addAll(timeList);
        newTimeList.addAll(set);// 去重后的list
        Collections.sort(newTimeList);// 重新排序
        // 初始化拼装list
        List<Map<String, String>> AllList = new ArrayList<>();
        // 开始拼装list 写逻辑处理 先写小循环 大循环写在里层
        for (int i = 0; i < newTimeList.size(); i++) {
            Map<String, String> newmap = new HashMap<>();
            newmap.put("time", newTimeList.get(i));
            for (int j = 0; j < allList.size(); j++) {
                for (int z = 0; z < dncsid.length; z++) {
                    if (allList.get(j).get("f_dnbh").equals(dncsid[z])
                            && allList.get(j).get("f_cjsj").equals(newTimeList.get(i))) {
                        newmap.put("a" + dncsid[z], allList.get(j).get("f_data"));
                    }
                }
            }
            AllList.add(newmap);
        }
        returnObject.setList(AllList);
        return returnObject;
    }

    /**
     * 能源数据集抄--分户用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @Override
    public ISSPReturnObject fh_fsdtj_jcsj(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String zlids = request.getParameter("zlid");// 获取selected 支路id
        String start = request.getParameter("time_start");// 起始时间
        String end = request.getParameter("time_end");// 终止时间
        List<Map<String, String>> list = new ArrayList<>();
        if (Validate_y.noNull(zlids)) {
            String[] zlid = zlids.split(",");
            // 循环支路 查询
            for (int i = 0; i < zlid.length; i++) {
                String zlmc = besoriginaldatamapper.getfhzlmc(zlid[i]);
                Map<String, String> zlmap = besoriginaldatamapper.fh_Select_fsdtj(zlid[i], start, end);
                zlmap.put("ZLMC", zlmc);
                list.add(zlmap);
            }
        }
        returnObject.setList(list);
        return returnObject;
    }

    /**
     * 能源数据集抄--分项用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @Override
    public ISSPReturnObject fx_fsdtj_jcsj(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String zlids = request.getParameter("zlid");// 获取selected 支路id
        String start = request.getParameter("time_start");// 起始时间
        String end = request.getParameter("time_end");// 终止时间
        List<Map<String, String>> list = new ArrayList<>();
        if (Validate_y.noNull(zlids)) {
            String[] zlid = zlids.split(",");
            // 循环支路 查询
            for (int i = 0; i < zlid.length; i++) {
                String zlmc = besoriginaldatamapper.getfxzlmc(zlid[i]);
                Map<String, String> zlmap = besoriginaldatamapper.fx_Select_fsdtj(zlid[i], start, end);
                zlmap.put("ZLMC", zlmc);
                list.add(zlmap);
            }
        }
        returnObject.setList(list);
        return returnObject;
    }

    @Override
    public ISSPReturnObject expExcel(HttpServletRequest request) throws Exception {

        ISSPReturnObject returnObject = new ISSPReturnObject();
        // excel的列头
        List<Object> alias = jsonToList(request.getParameter("alias"));
        // 数据List中的Map的key值.
        List<Object> names = jsonToList(request.getParameter("names"));
        // 表名
        String tablename = request.getParameter("tablename");
        // 数据list
        List<Object> jsonList = jsonToList(request.getParameter("jsonList"));
        log.debug("通用导出列头:" + alias);
        log.debug("通用导出key:" + names);
        log.debug("通用导出数据list:" + jsonList);
        // 新建list
        List<Map<String, Object>> list = new ArrayList<>();
        // 传数据
        for (int i = 0; i < jsonList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < names.size(); j++) {
                String key = (String) names.get(j);
                Map<String, Object> newMap = (Map<String, Object>) jsonList.get(i);
                Object val = newMap.get(key);
                map.put(key, val);
            }
            list.add(map);
        }
        // 创建工具类.
        ExcelUtil<Object> util = new ExcelUtil<Object>(Object.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        // sheet页名称
        String FileName = "sheet";
        // 导出excel地址
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
        // 导出方法
        ExcelReturn res = util.resListDynamic(FileName, FilePath, list, alias, names);
        Map<String, String> map = new HashMap<>();
        // 1.成功 0.失败
        map.put("status", res.getStatus());
        map.put("file", file);
        returnObject.setMap(map);
        return returnObject;

    }

    public static List<Object> jsonToList(String s) {
        List<Object> list = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.fromObject(s);
            for (int i = 0; i < jsonArray.size(); i++) {
                list.add(jsonArray.get(i));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @Description: 根据能源类型查询能源单位
     *
     * @auther: wanghongjie
     * @date: 9:52 2021/1/11
     * @param: [energyType]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject getUnitByEnergyType(String energyType) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (energyType.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("当前能源类型不存在");
            return returnObject;
        }
        String energyUnit = besoriginaldatamapper.getUnitByEnergyType(energyType);
        if(energyUnit == null || "".equals(energyUnit)) {
            returnObject.setData("");
            returnObject.setStatus("1");
        }else {
            returnObject.setData(energyUnit);
            returnObject.setStatus("1");
        }
        return returnObject;
    }
}
