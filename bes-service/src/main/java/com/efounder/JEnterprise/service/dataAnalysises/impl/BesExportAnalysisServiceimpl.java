package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BESExportReportMapper;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESAnalysis;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.dataAnalysises.BesExportAnalysisService;
import com.framework.common.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author  杨超
* @version 创建时间：2018年11月19日 上午9:45:08
* @parameter 
* @version 1.0
*/
@Service("/BesExportAnalysisService")
public class BesExportAnalysisServiceimpl implements BesExportAnalysisService {
	private static final Logger log = LoggerFactory.getLogger(BesExportAnalysisServiceimpl.class);

    @Autowired
    private BESExportReportMapper besexportreportmapper;

    @Autowired
    private Pzlj pzlj;// 获取配置文件路径

    /**
     * 查询拼装table
     */
    @Override
    public ISSPReturnObject getExportReport(BESExportReport dto) {
        ISSPReturnObject resultObject = new ISSPReturnObject();
        try {
            List<Map<String, Object>> AllList = new ArrayList<>();
            List<Map<String, Object>> newlist = new ArrayList<>();
            List<String> timelist = new ArrayList<>();// 存放时间list
            String timeType = dto.getTimeType();// 查询时间类型
            String f_cjsj = "date_format(D.f_cjsj ,'%Y-%m')";// 月度
            String startTime = dto.getStartTime();// 开始时间
            String endTime = dto.getEndTime();// 结束时间
            if ("2".equals(timeType)) {// 月度
                f_cjsj = "date_format(D.f_cjsj ,'%Y-%m')";
            } else if ("3".equals(timeType)) {// 年度
                f_cjsj = "date_format(D.f_cjsj ,'%Y')";
                startTime = startTime.substring(0, 4);
                endTime = endTime.substring(0, 4);
            }
            dto.setStartTime(startTime);
            dto.setEndTime(endTime);
            dto.setF_cjsj(f_cjsj);
            if ("zl".equals(dto.getStatisticsType())) {// 支路
                // 支路 查询该能耗下的支路 --1.0
                List<Map<String, String>> AllZlList = besexportreportmapper.AllZlList(dto);
                // 根据所有查询出来的支路查询所有符合条件的数据
                for (int i = 0; i < AllZlList.size(); i++) {// 循环所有能源下的支路
                    if ("01".equals(AllZlList.get(i).get("ID"))) {
                        continue;
                    }
                    dto.setFZlbh(AllZlList.get(i).get("ID"));// 将支路编号存到实体类
                    AllList = besexportreportmapper.getZlExportReport(dto);// 符合条件的所有支路数据
                    double alldata = 0.00;
                    if (AllList.size() > 0) {
                        Map<String, Object> newmap = new HashMap<>();
                        newmap.put("fPzlmc", AllList.get(0).get("F_PZLMC"));// 父级支路名称
                        newmap.put("fZlmc", AllList.get(0).get("F_ZLMC"));// 支路名称
                        for (int j = 0; j < AllList.size(); j++) {// 没循环一个月份放一次数据
                            if ("3".equals(timeType)) {
                                newmap.put("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年",
                                        AllList.get(j).get("F_DATA"));
                                timelist.add("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年");
                            } else {
                                newmap.put("fTime" + AllList.get(j).get("F_TIME") + "月", AllList.get(j).get("F_DATA"));
                                timelist.add("fTime" + AllList.get(j).get("F_TIME") + "月");
                            }
                            alldata += Double.valueOf(AllList.get(j).get("F_DATA").toString());
                        }
                        newmap.put("AllFdata", String .format("%.2f",alldata));
                        newlist.add(newmap);
                    }
                }
            } else {// 分项
                // 查询该能耗下的分项
                List<Map<String, String>> AllFxList = besexportreportmapper.AllFxList(dto);
                // 根据所有查询出来的支路查询所有符合条件的数据
                for (int i = 0; i < AllFxList.size(); i++) {// 循环所有能源下的支路
                    if ("01".equals(AllFxList.get(i).get("ID"))) {
                        continue;
                    }
                    dto.setFZlbh(AllFxList.get(i).get("ID"));// 将支路编号存到实体类
                    AllList = besexportreportmapper.getFxExportReport(dto);// 符合条件的所有支路数据
                    double alldata = 0.00;
                    if (AllList.size() > 0) {
                        Map<String, Object> newmap = new HashMap<>();
                        newmap.put("fPzlmc", AllList.get(0).get("F_PFXMC"));// 父级支路名称
                        newmap.put("fZlmc", AllList.get(0).get("F_FXMC"));// 支路名称
                        for (int j = 0; j < AllList.size(); j++) {// 没循环一个月份放一次数据
                            if ("3".equals(timeType)) {
                                newmap.put("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年",
                                        AllList.get(j).get("F_DATA"));
                                timelist.add("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年");
                            } else {
                                newmap.put("fTime" + AllList.get(j).get("F_TIME") + "月", AllList.get(j).get("F_DATA"));
                                timelist.add("fTime" + AllList.get(j).get("F_TIME") + "月");
                            }
                            alldata += Double.valueOf(AllList.get(j).get("F_DATA").toString());
                        }
                        newmap.put("AllFdata", String .format("%.2f",alldata));
                        newlist.add(newmap);
                    }
                }
            }
            Set<String> set = new HashSet<>();
            set.addAll(timelist);
            List<String> newtimeList = new ArrayList<>();
            newtimeList.addAll(set);// 去重后的list
            Collections.sort(newtimeList);// 重新排序
            resultObject.setList(newlist);
            resultObject.setData(newtimeList);
            resultObject.setStatus("1");// 成功
            resultObject.setMsg("数据查询成功");
        } catch (Exception e) {
            resultObject.setStatus("0");// 失败
            resultObject.setMsg("节能诊断分析数据查询失败");
        }
        return resultObject;

    }

    /**
     * 生成excel 并返回地址url
     */
    @Override
    public ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 创建工具类.
        ExcelUtil<BESAnalysis> util = new ExcelUtil<BESAnalysis>(BESAnalysis.class);
//        实体类导出示例
        /*List<BESAnalysis> analysis = new ArrayList<BESAnalysis>();
        BESAnalysis vo = new BESAnalysis();
        vo.setId(1);
        vo.setName("李坤");
        vo.setAge(26);
        vo.setClazz("五期提高班");
        vo.setCompany("天融信");
        analysis.add(vo);*/
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
        // 初始化数据
        List<Map<String, Object>> AllList = new ArrayList<>();
        List<Map<String, Object>> newlist = new ArrayList<>();
        List<String> timelist = new ArrayList<>();// 存放时间list
        // 合并单元格list
        List<List<Map<String, Integer>>> MergedRegionList = new ArrayList<>();

        String timeType = dto.getTimeType();// 查询时间类型
        String f_cjsj = "date_format(D.f_cjsj ,'%Y-%m')";// 月度
        String startTime = dto.getStartTime();// 开始时间
        String endTime = dto.getEndTime();// 结束时间
        if ("2".equals(timeType)) {// 月度
            f_cjsj = "date_format(D.f_cjsj ,'%Y-%m')";
        } else if ("3".equals(timeType)) {// 年度
            f_cjsj = "date_format(D.f_cjsj ,'%Y')";
            startTime = startTime.substring(0, 4);
            endTime = endTime.substring(0, 4);
        }
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        dto.setF_cjsj(f_cjsj);
        if ("zl".equals(dto.getStatisticsType())) {// 支路
            // 支路 查询该能耗下的支路 --1.0
            List<Map<String, String>> AllZlList = besexportreportmapper.AllZlList(dto);
            // 根据所有查询出来的支路查询所有符合条件的数据
            for (int i = 0; i < AllZlList.size(); i++) {// 循环所有能源下的支路
                // 级数
                String JS = AllZlList.get(i).get("JS");
                // 支路id
                String ZLID = AllZlList.get(i).get("ID");
                if ("01".equals(ZLID)) {
                    continue;
                }
                dto.setFZlbh(ZLID);// 将支路编号存到实体类
                AllList = besexportreportmapper.getZlExportReport(dto);// 符合条件的所有支路数据
                double alldata = 0.00;
                if (AllList.size() > 0) {
                    Map<String, Object> newmap = new HashMap<>();
                    newmap.put("fPzlmc", AllList.get(0).get("F_PZLMC"));// 父级支路名称
                    newmap.put("fZlmc", AllList.get(0).get("F_ZLMC"));// 支路名称
                    for (int j = 0; j < AllList.size(); j++) {// 没循环一个月份放一次数据
                        if ("3".equals(timeType)) {
                            newmap.put("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年",
                                    AllList.get(j).get("F_DATA"));
                            timelist.add("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年");
                        } else {
                            newmap.put("fTime" + AllList.get(j).get("F_TIME") + "月", AllList.get(j).get("F_DATA"));
                            timelist.add("fTime" + AllList.get(j).get("F_TIME") + "月");
                        }
                        if(AllList.get(j).get("F_DATA") != null){
                            alldata += Double.valueOf(AllList.get(j).get("F_DATA").toString());
                        }
                    }
                    newmap.put("AllFdata", String.format("%.2f", alldata));
                    newlist.add(newmap);
                }
            }
        } else {// 分项
            // 查询该能耗下的分项
            List<Map<String, String>> AllFxList = besexportreportmapper.AllFxList(dto);
            // 根据所有查询出来的支路查询所有符合条件的数据  循环所有能源下的支路
            for (int i = 0; i < AllFxList.size(); i++) {
                if ("01".equals(AllFxList.get(i).get("ID"))) {
                    continue;
                }
                // 将支路编号存到实体类
                dto.setFZlbh(AllFxList.get(i).get("ID"));
                // 符合条件的所有支路数据
                AllList = besexportreportmapper.getFxExportReport(dto);
                double alldata = 0.00;
                if (AllList.size() > 0) {
                    Map<String, Object> newmap = new HashMap<>();
                    newmap.put("fPzlmc", AllList.get(0).get("F_PFXMC"));// 父级支路名称
                    newmap.put("fZlmc", AllList.get(0).get("F_FXMC"));// 支路名称
                    for (int j = 0; j < AllList.size(); j++) {// 没循环一个月份放一次数据
                        if ("3".equals(timeType)) {
                            newmap.put("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年",
                                    AllList.get(j).get("F_DATA"));
                            timelist.add("fTime" + AllList.get(j).get("F_TIME").toString().substring(0, 4) + "年");
                        } else {
                            newmap.put("fTime" + AllList.get(j).get("F_TIME") + "月", AllList.get(j).get("F_DATA"));
                            timelist.add("fTime" + AllList.get(j).get("F_TIME") + "月");
                        }
                        alldata += Double.valueOf(AllList.get(j).get("F_DATA").toString());
                    }
                    newmap.put("AllFdata", String.format("%.2f", alldata));
                    newlist.add(newmap);
                }
            }
        }
        Set<String> set = new HashSet<>();
        set.addAll(timelist);
        List<String> newtimeList = new ArrayList<>();
        // 去重后的list
        newtimeList.addAll(set);
        // 重新排序
        Collections.sort(newtimeList);
        // excel的列头
        List<Object> alias = new ArrayList<>();
        // 数据List中的Map的key值.
        List<Object> names = new ArrayList<>();
        alias.add("父级支路");
        alias.add("支路名称");
        for (String str : newtimeList) {
            alias.add(str.substring(5, str.length()));
        }
        alias.add("总计");
        names.add("fPzlmc");
        names.add("fZlmc");
        for (String str : newtimeList) {
            names.add(str);
        }
        names.add("AllFdata");
        // 合并单元格--MergedRegionList 可写可不写
//        List<Map<String, Integer>> MergedRegionList = new ArrayList<>();
//        Map<String, Integer> MergedRegionMap = new HashMap<>();
//        MergedRegionMap.put("firstRow", 2);
//        MergedRegionMap.put("endRow", 11);
//        MergedRegionMap.put("firstCol", 0);
//        MergedRegionMap.put("endCol", 0);
//        MergedRegionList.add(MergedRegionMap);
        // 计算合并分页
        double sheetNo = Math.ceil(newlist.size() / 65535.00);
        for (int index = 0; index < sheetNo; index++) {

        }
        ExcelReturn res = util.resListDynamic(FileName, FilePath, newlist, alias, names, MergedRegionList);
//        ExcelReturn res = util.resList(FileName, FilePath, analysis);//实体类导出
        Map<String, String> map = new HashMap<>();
        // 1.成功 0.失败
        map.put("status", res.getStatus());
        map.put("file", file);
        returnObject.setMap(map);
        return returnObject;
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/19 17:38
    * @Description:    文件上传后台接收方法
    */
    @Override
    public ISSPReturnObject impExcel(HttpServletRequest request,
            @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String fold = request.getParameter("fold");
        String dayFold = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 文件保存时候的根路径
        String realPath = pzlj.getUploadPath();
        // 转换为文件类型的request
        // 获取对应file对象
        if (multipartFile != null) {
            if (multipartFile.getSize() != 0L) {
                // 原始文件名称
                String pictureFile_name = multipartFile.getOriginalFilename();
                // uuid
                String UUID = UUIDUtil.getRandom32BeginTimePK();
                // 新文件名称
                String wjmc_url = UUID + pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
                // 文件路径
                String fileUrl = realPath + "/" + fold + "/" + dayFold + "/" + wjmc_url;
                // 上传文件
                File uploadPic = new File(fileUrl);
                if (!uploadPic.getParentFile().exists()) {
                    uploadPic.getParentFile().mkdirs();// 创建父级目录
                    uploadPic.createNewFile();// 创建文件
                }
                // 向磁盘写文件
//                multipartFile.transferTo(uploadPic);
                //写入指定文件夹
                OutputStream out = new FileOutputStream(uploadPic);
                out.write(multipartFile.getBytes());
                // 初始化导入工具类
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(fileUrl);
                    ExcelUtil<BESAnalysis> util = new ExcelUtil<BESAnalysis>(BESAnalysis.class);
                    // 导入excel,处理后生成list
                    List<BESAnalysis> list = util.importExcel("sheet1", fis);
                    log.debug("\r 打印导入数据:" + list);
                    // 如果不需要验证 则可直接将list插入到数据库
                } catch (FileNotFoundException e) {
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                }
                returnObject.setMsg("文件上传成功！");
                returnObject.setStatus("1");
            }
        } else {
            returnObject.setMsg("文件上传失败！");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

}
