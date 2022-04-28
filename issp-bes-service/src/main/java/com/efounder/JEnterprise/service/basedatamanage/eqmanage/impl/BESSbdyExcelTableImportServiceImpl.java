package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.constant.BesNodeType;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration.*;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyExcelTableImportService;
import com.framework.common.utils.ExcelUtil;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 8:30 2020/9/18
 * @Modified By:
 */
@Service("BESSbdyExcelTableImportService")
public class BESSbdyExcelTableImportServiceImpl implements BESSbdyExcelTableImportService {
    private static final Logger log = LoggerFactory.getLogger(BESSbdyExcelTableImportServiceImpl.class);
    @Autowired
    private Pzlj pzlj;// 获取配置文件路径

    @Autowired
    private BESSbdyMapper besSbdyMapper;
    @Autowired
    private BESSbdyExcelTableImportMapper besSbdyExcelTableImportMapper;

    @Autowired
    private SbPzStructCache sbPzStructCache;

    @Autowired
    private BESSbdyMapper besSbPzStructMapper;

    @Autowired
    private BesCollectorMapper besCollectorMapper;

    @Autowired
    private CollectorCache collectorCache;


    @Autowired
    private DdcCache ddcCache;
    @Autowired
    private BesDdcMapper besDdcMapper;

    @Autowired
    private AmmeterCache ammeterCache;

    @Autowired
    private BESAmmeterMapper besAmmeterMapper;

    @Autowired
    private AiPointCache aiPointCache;

    @Autowired
    private AoPointCache aoPointCache;

    @Autowired
    private DiPointCache diPointCache;

    @Autowired
    private DoPointCache doPointCache;

    @Autowired
    private BesAiPointMapper besAiPointMapper;

    @Autowired
    private BesAoPointMapper besAoPointMapper;

    @Autowired
    private BesDiPointMapper besDiPointMapper;

    @Autowired
    private BesDoPointMapper besDoPointMapper;

    @Autowired
    private VirtualPointCache virtualPointCache;

    @Autowired
    private BesVirtualPointMapper besVirtualPointMapper;


    /**
     * 文件上传后台接收方法
     */
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject impExcel(HttpServletRequest request,
                                     @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException,Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String fold = request.getParameter("fold");
        String dayFold = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String realPath = pzlj.getUploadPath();// 文件保存时候的根路径
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
                    ExcelUtil<sbdyStruct> util = new ExcelUtil<>(sbdyStruct.class);
                    List<sbdyStruct> list = util.importExcel("节点表", fis);// 导入excel,处理后生成list

                    if (list.size() >0) {

                        if (list.get(0).getfNodeattribution().equals("3")) {//能耗

                            returnObject = energy(list,fileUrl);

                        } else if (list.get(0).getfNodeattribution().equals("2")) {//照明控制

                            returnObject = lightingControl(list,fileUrl);

                        } else if (list.get(0).getfNodeattribution().equals("1")) {//楼宇自控

                            returnObject = buildingAutomation(list,fileUrl);

                        }
                    }


                    // 如果不需要验证 则可直接将list插入到数据库
                }  catch (FileNotFoundException e) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                } catch (Exception e) {
                    returnObject.setStatus(returnObject.getStatus());
                    returnObject.setMsg(returnObject.getMsg());
//					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    e.printStackTrace();
                }
            }
        } else {
            returnObject.setMsg("导入数据失败！");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 点位导出
     */
    @Override
    public ISSPReturnObject exportPoint(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 创建工具类.
        ExcelUtil<sbdyStructExport> util = new ExcelUtil<>(sbdyStructExport.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
        String fPointName = request.getParameter("f_pointName");

        //从缓存中获取所有下级
        List<BESSbPzStruct> cacheSbPzStruct = sbPzStructCache.getCascadeSubordinate(fPointName);
        //重组数据,由List<BESSbPzStruct>转为List<sbdyStruct>
        List<sbdyStructExport> sbdyStructList = cacheSbPzStruct.stream().map(item->
                {
                    sbdyStructExport map = new sbdyStructExport();
                    map.setfId(item.getF_id());
                    map.setfType(item.getF_type());
                    map.setfSysname(item.getF_sys_name());
                    map.setfNickname(item.getF_nick_name());
                    map.setfDescription(item.getF_description());
                    map.setfNodeattribution(item.getF_node_attribution());
                    map.setfPsysname(item.getF_psys_name());
                    return map;
                }
        ).collect(Collectors.toList());
        //实体类导出
        ExcelReturn res = util.resList(FileName, FilePath, sbdyStructList);
        Map<String, String> map = new HashMap<>();
        map.put("status", res.getStatus());// 1.成功 0.失败
        map.put("file", file);
        returnObject.setMap(map);
        return returnObject;
    }

    /**
     * 同步数据到缓存
     */
    @Override
    public void syncCache() {

        List<BESSbPzStruct> besSbPzStructs = besSbPzStructMapper.findAll();

        if (besSbPzStructs == null || besSbPzStructs.isEmpty())
        {
            return;
        }

        besSbPzStructs.forEach(besSbPzStruct -> {

            String sysName = besSbPzStruct.getF_sys_name();

            BESSbPzStruct besSbPzStruct1 = sbPzStructCache.getCachedElement(sysName);

            String type = besSbPzStruct.getF_type();


            if (besSbPzStruct1 != null)
            {
                if (StringUtils.hasText(besSbPzStruct1.getF_init_val()))
                {
                    return;
                }


                // 判断是否为ai点
                if (BesNodeType.AI.equals(type))
                {
                    BesAiPoint besAiPoint = besAiPointMapper.queryAiPoint(sysName);
                    aiPointCache.updateOneAiPointCache(besAiPoint);
                    // 更新到设备树配置定义缓存
                    sbPzStructCache.updateOneSbPzStructCache(besSbPzStruct);

                    return;
                }
                // 判断是否为ao点
                if (BesNodeType.AO.equals(type))
                {
                    BesAoPoint besAoPoint = besAoPointMapper.queryAoPoint(sysName);
                    aoPointCache.updateOneAoPointCache(besAoPoint);
                    // 更新到设备树配置定义缓存
                    sbPzStructCache.updateOneSbPzStructCache(besSbPzStruct);

                    return;
                }
                // 判断是否为di点
                if (BesNodeType.DI.equals(type))
                {
                    BesDiPoint besDiPoint = besDiPointMapper.queryDiPoint(sysName);
                    diPointCache.updateOneDiPointCache(besDiPoint);
                    // 更新到设备树配置定义缓存
                    sbPzStructCache.updateOneSbPzStructCache(besSbPzStruct);

                    return;
                }
                // 判断是否为do点
                if (BesNodeType.DO.equals(type))
                {
                    BesDoPoint besDoPoint = besDoPointMapper.queryDoPoint(sysName);
                    doPointCache.updateOneDoPointCache(besDoPoint);
                    // 更新到设备树配置定义缓存
                    sbPzStructCache.updateOneSbPzStructCache(besSbPzStruct);

                }

                return;
            }

            // 添加到设备树配置定义缓存
            sbPzStructCache.addOneSbPzStructCache(besSbPzStruct);

            // 判断是否是能耗采集器
            if (BesNodeType.COLLECTOR.equals(type))
            {
                BesCollector besCollector = besCollectorMapper.selectBySysName(sysName);

                // 能耗采集器添加到缓存
                collectorCache.addOneCollectorCache(besCollector);

                return;
            }

            // 判断是否DDC 控制器
            if (BesNodeType.DDC.equals(type) || BesNodeType.IP_ROUTER.equals(type))
            {
                BesDdc besDdc = besDdcMapper.selectBySysName(sysName);
                ddcCache.addOneDdcCache(besDdc);

                return;
            }

            // 判断是否为电表
            if (BesNodeType.AMMETER.equals(type))
            {
                BESAmmeter besAmmeter = besAmmeterMapper.queryAmmeter(sysName);
                ammeterCache.addOneAmmeterCache(besAmmeter);

                return;
            }

            // 判断是否为ai点
            if (BesNodeType.AI.equals(type))
            {
                BesAiPoint besAiPoint = besAiPointMapper.queryAiPoint(sysName);
                aiPointCache.addOneAiPointCache(besAiPoint);

                return;
            }
            // 判断是否为ao点
            if (BesNodeType.AO.equals(type))
            {
                BesAoPoint besAoPoint = besAoPointMapper.queryAoPoint(sysName);
                aoPointCache.addOneAoPointCache(besAoPoint);

                return;
            }
            // 判断是否为di点
            if (BesNodeType.DI.equals(type))
            {
                BesDiPoint besDiPoint = besDiPointMapper.queryDiPoint(sysName);
                diPointCache.addOneDiPointCache(besDiPoint);

                return;
            }
            // 判断是否为do点
            if (BesNodeType.DO.equals(type))
            {
                BesDoPoint besDoPoint = besDoPointMapper.queryDoPoint(sysName);
                doPointCache.addOneDoPointCache(besDoPoint);

                return;
            }

            // 判断是否为虚点
            if (BesNodeType.VPOINT.equals(type))
            {
                BesVirtualPoint besVirtualPoint = besVirtualPointMapper.queryVirtualPoint(sysName);
                virtualPointCache.addOneVirtualPointCache(besVirtualPoint);
            }


        });

    }


    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject energy(List<sbdyStruct> list,String fileUrl){
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 获取要导出的数据
        List<ExcelError> excelErrors = new ArrayList<>();
        boolean inportflag = false;
        Map<String, Object> psysName = null;
        // 初始化导入工具类
        FileInputStream fis = null;

        String nodeTabName = "能耗节点表";


        try {

            Set<String> point = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {

                //判断总线表中系统名称数据是否重复
                Boolean success = point.add(list.get(i).getfSysname());
                if (!success) {
                    throw new Exception(list.get(i).getfSysname() +":系统名称在能耗节点表中重复");
                }
                boolean flag = true; //插入数据成功的标志
                sbdyStruct sbdy = list.get(i);
                String date = DateUtil.getCurrTime();
                String errMsg = "";
                if (sbdy.getfType() == null || sbdy.getfType().equals("")) {
                    errMsg = "节点类型为空";
                    flag = false;
                } else if (sbdy.getfSysname() == null || sbdy.getfSysname().equals("")) {
                    if ("".equals(errMsg)) {
                        errMsg = "系统名称为空";
                        flag = false;
                    } else {
                        errMsg = errMsg + ",电表类型名称为空";
                        flag = false;
                    }
                } else if (sbdy.getfDescription() == null || sbdy.getfDescription().equals("")) {
                    if ("".equals(errMsg)) {
                        errMsg = "描述为空";
                        flag = false;
                    } else {
                        errMsg = errMsg + ",描述为空";
                        flag = false;
                    }

                } else if (sbdy.getfPsysname() == null || sbdy.getfPsysname().equals("")) {

                    if ("".equals(errMsg)) {
                        errMsg = "父节点系统名称为空";
                        flag = false;
                    } else {
                        errMsg = errMsg + ",父节点系统名称为空";
                        flag = false;
                    }
                } else if (sbdy.getfNickname() == null || sbdy.getfNickname().equals("")) {

                    if ("".equals(errMsg)) {
                        errMsg = "别名为空";
                        flag = false;
                    } else {
                        errMsg = errMsg + ",别名为空";
                        flag = false;
                    }
                }else if (sbdy.getfNodeattribution() == null || sbdy.getfNodeattribution().equals("")) {

                    if ("".equals(errMsg)) {
                        errMsg = "所属系统为空";
                        flag = false;
                    } else {
                        errMsg = errMsg + ",所属系统为空";
                        flag = false;
                    }
                }

                if (!flag) {
                    ExcelError excelError = new ExcelError();
                    excelError.setRow((i + 2) + "");
                    excelError.setErrorMsg(errMsg);
                    excelErrors.add(excelError);
                }
            }

            /*if (excelErrors.size() > 0) {
                returnObject.setMsg("导入数据过程中出现失误，请查看excel错误报告！");
                returnObject.setStatus("2");
                returnObject.setList(excelErrors);
//						return returnObject;
                throw new Exception((Throwable) returnObject.getList());
            }*/

            if (excelErrors.size() > 0) {
                throw new Exception(nodeTabName+"第" + excelErrors.get(0).getRow() + "行" +excelErrors.get(0).getErrorMsg());
            }

            for (sbdyStruct sbdyStructs : list) {

                if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                    return returnObject;
                }
                switch (sbdyStructs.getfType()) {

                    case "31": {//能耗采集节点


                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;
                    }
                    case "26": {//能耗控制器节点

                        fis = new FileInputStream(fileUrl);
                        ExcelUtil<besCollectorExcel> util = new ExcelUtil<>(besCollectorExcel.class);
                        List<besCollectorExcel> collectorlist = util.importExcel("能耗控制器节点", fis);// 导入excel,处理后生成list

                        List lists=new ArrayList();

                        Set<String> point_collector = new HashSet<>();
                        for (besCollectorExcel besCollectorExcel : collectorlist) {

                            //判断能耗控制器节点表中系统名称数据是否重复
                            Boolean success = point_collector.add(besCollectorExcel.getfSysName());
                            if (!success) {
                                throw new Exception(besCollectorExcel.getfSysName() +":系统名称在能耗控制器节点表中重复");
                            }
                            lists.add(besCollectorExcel.getfSysName());
                        }
                        Boolean aa = lists.contains(sbdyStructs.getfSysname());

                        if (!aa) {
                            throw new Exception(sbdyStructs.getfSysname() +"系统名称在能耗控制器节点表中不存在");
                        }
                        nodeTabName = "能耗控制器节点表";
                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        for (besCollectorExcel collectorExcel : collectorlist) {

                            String f_guid 					= UUIDUtil.getRandom32BeginTimePK();
                            String f_sys_name 				= collectorExcel.getfSysName();
                            String f_nick_name 				= collectorExcel.getfNickName();
                            String f_ssqy 					= collectorExcel.getfSsqy();
                            String f_azwz 					= collectorExcel.getfAzwz();
                            String f_description 			= collectorExcel.getfDescription();
                            String collectorIp 				= collectorExcel.getfIpAddr();
                            String f_coll_cycle 			= collectorExcel.getfCollCycle();
                            String f_node_type 				= collectorExcel.getfNodeType();
                            String f_his_data_save_cycle 	= collectorExcel.getfHisDataSaveCycle();
                            String yqbh 					= collectorExcel.getfYqbh();
                            String f_upload_cycle 			= collectorExcel.getfUploadCycle();
                            String f_gateway 				= collectorExcel.getfGateway();
                            String f_mask 					= collectorExcel.getfMask();
                            String f_ip_master 				= collectorExcel.getfIpMaster();
                            String f_port_master 			= collectorExcel.getfPortMaster();

                            if (       !StringUtils.hasText(f_sys_name)
                                    || !StringUtils.hasText(f_nick_name)
                                    || !StringUtils.hasText(f_ssqy)
                                    || !StringUtils.hasText(f_azwz)
                                    || !StringUtils.hasText(f_description)
                                    || !StringUtils.hasText(collectorIp)
                                    || !StringUtils.hasText(f_coll_cycle)
                                    || !StringUtils.hasText(f_node_type)
                                    || !StringUtils.hasText(f_his_data_save_cycle)
                                    || !StringUtils.hasText(yqbh)
                                    || !StringUtils.hasText(f_upload_cycle)
                                    || !StringUtils.hasText(f_gateway)
                                    || !StringUtils.hasText(f_mask)
                                    || !StringUtils.hasText(f_ip_master)
                                    || !StringUtils.hasText(f_port_master)
                            )
                            {
                                throw new Exception(f_sys_name + ":能耗控制器节点表格内容不完整");
                            }


                            String collectorTableName = "bes_collector";

                            if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                                //查询系统名称在设备配置表中是否存在
                                if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                                    throw new Exception(f_sys_name + ":系统名称在节点表中不存在");
                                }

                                // 查询ip地址是否已经存在
                                int besDDCSize = besSbdyExcelTableImportMapper.getSizeByIpAddrBesDdc(collectorIp);
                                int besCollectorSize = besSbdyExcelTableImportMapper.getSizeByIpAddrBesCollector(collectorIp);
                                if (besDDCSize == 1 || besCollectorSize == 1){

                                    throw new Exception(collectorIp +":ip 地址重复");
                                }

                                // 生成设备id
                                String maxId = besSbdyExcelTableImportMapper.queryCollectorMaxId();
                                String id = getAutoIncreaseCol(maxId);
                                String f_sbid = id;

                                BesCollector besCollector = new BesCollector();

                                besCollector.setfGuid(f_guid);
                                besCollector.setfSbId(f_sbid);
                                besCollector.setfSysName(f_sys_name);
                                besCollector.setfNickName(f_nick_name);
                                besCollector.setfEnabled(1);
                                besCollector.setfSsqy(f_ssqy);
                                besCollector.setfAzwz(f_azwz);
                                besCollector.setfDescription(f_description);
                                besCollector.setfIpAddr(collectorIp);
                                besCollector.setfCollCycle(Integer.parseInt(f_coll_cycle));
                                besCollector.setfNodeType(f_node_type);
                                besCollector.setfHisDataSaveCycle(Integer.parseInt(f_his_data_save_cycle));
                                besCollector.setfParkYqbh(yqbh);
                                besCollector.setfCollectorState("0");
                                besCollector.setfOnlineState("0");
                                besCollector.setfChannelId(collectorIp);
                                besCollector.setfUploadCycle(f_upload_cycle);
                                besCollector.setfGateway(f_gateway);
                                besCollector.setfMask(f_mask);
                                besCollector.setfIpMaster(f_ip_master);
                                besCollector.setfPortMaster(f_port_master);

                                inportflag = besSbdyExcelTableImportMapper.add_sbdyStructCollector(besCollector);
                            }
                        }
                        if (inportflag) {
                            returnObject.setMsg("导入成功！");
                            returnObject.setStatus("1");
                        } else {
                            throw new Exception("能耗控制器节点表的系统名称在节点表中不存在");
//						returnObject.setMsg("能耗控制器节点表的系统名称在节点表中不存在！");
                        }

                        break;

                    }
                    case "27": {//能耗总线节点
                        fis = new FileInputStream(fileUrl);
                        ExcelUtil<besBusExcel> util = new ExcelUtil<>(besBusExcel.class);
                        List<besBusExcel> buslist = util.importExcel("能耗总线节点", fis);// 导入excel,处理后生成list

                        List lists=new ArrayList();

                        Set<String> point_bus = new HashSet<>();
                        for (besBusExcel besBusExcel : buslist) {
                            //判断总线表中系统名称数据是否重复
                            Boolean success = point_bus.add(besBusExcel.getfSysName());
                            if (!success) {
                                throw new Exception(besBusExcel.getfSysName() +":系统名称在能耗总线节点表中重复");
                            }

                            lists.add(besBusExcel.getfSysName());
                        }



                        Boolean aa = lists.contains(sbdyStructs.getfSysname());

                        if (!aa) {
                            throw new Exception(sbdyStructs.getfSysname() +":系统名称在能耗总线节点表中不存在");
                        }

                        nodeTabName = "总线节点表";
                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        for (besBusExcel besBusExcel : buslist) {

                            String f_sys_name = besBusExcel.getfSysName();
                            String f_nick_name = besBusExcel.getfNickName();
                            String f_port = besBusExcel.getfPort();

                            if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                                if (!StringUtils.hasText(f_sys_name)
                                        || !StringUtils.hasText(f_nick_name)
                                        || !StringUtils.hasText(f_port)
                                ) {
                                    throw new Exception(f_sys_name + "能耗总线节点表格内容不完整");
                                }


                                //查询系统名称在设备配置表中是否存在
                                if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                                    throw new Exception(f_sys_name + "系统名称在节点表中不存在");
                                }

                                inportflag = besSbdyExcelTableImportMapper.add_sbdyStructBus(besBusExcel);
                            }
                        }
                        if (inportflag) {
                            returnObject.setMsg("导入成功！");
                            returnObject.setStatus("1");
                        } else {
                            throw new Exception("导入失败");
//						returnObject.setMsg("导入失败！");
//						returnObject.setStatus("0");
//						return returnObject;
                        }
                        break;
                    }
                    case "28": {//能耗电表节点
                        fis = new FileInputStream(fileUrl);
                        ExcelUtil<besAmmeterExcel> util = new ExcelUtil<>(besAmmeterExcel.class);
                        List<besAmmeterExcel> ammeterlist = util.importExcel("能耗电表节点", fis);// 导入excel,处理后生成list

                        List lists=new ArrayList();

                        Set<String> point_ammeter = new HashSet<>();
                        for (besAmmeterExcel besAmmeterExcel : ammeterlist) {
                            //判断能耗节点表中系统名称数据是否重复
                            Boolean success = point_ammeter.add(besAmmeterExcel.getfSysName());
                            if (!success) {
                                throw new Exception(besAmmeterExcel.getfSysName() +":系统名称在能耗节点表中重复");
                            }

                            lists.add(besAmmeterExcel.getfSysName());
                        }
                        Boolean aa = lists.contains(sbdyStructs.getfSysname());

                        if (!aa) {
                            throw new Exception(sbdyStructs.getfSysname() +":系统名称在能耗电表节点表中不存在");
                        }
                        nodeTabName = "能耗电表节点表";
                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);
                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }


                        for (besAmmeterExcel besAmmeterExcel : ammeterlist) {

                            String f_sys_name = besAmmeterExcel.getfSysName();            //系统名称
                            String f_nick_name = besAmmeterExcel.getfNickName();            //别名
                            String f_azwz = besAmmeterExcel.getfAzwz();                //安装位置
                            String f_wldz = besAmmeterExcel.getfWldz();                //物理地址
                            String f_description = besAmmeterExcel.getfDescription();        //描述
                            String f_blxbh = besAmmeterExcel.getfBlxbh();				//电表类型编号
                            String f_comm_rate = besAmmeterExcel.getfCommRate();            //通信波特率编号
                            String f_protocol_type = besAmmeterExcel.getfProtocolType();        //通信协议类型
                            String f_cjfabh = besAmmeterExcel.getfCjfabh();                //采集方案编号
                            String f_communication_port = besAmmeterExcel.getfCommunicationPort();    //通信端口
                            String f_yqbh = besAmmeterExcel.getfYqbh();                //园区编号
                            String f_percentage = besAmmeterExcel.getfPercentage();            //变比
                            String f_com_data_bit = besAmmeterExcel.getfComDataBit();            //数据位
                            String f_com_parity_bit = besAmmeterExcel.getfComParityBit();        //校验位
                            String f_com_stop_bit = besAmmeterExcel.getfComStopBit();            //停止位
                            String f_function_code = besAmmeterExcel.getfFunctionCode();        //功能码

                            if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                                if (!StringUtils.hasText(f_sys_name)
                                        || !StringUtils.hasText(f_nick_name)
                                        || !StringUtils.hasText(f_azwz)
                                        || !StringUtils.hasText(f_wldz)
                                        || !StringUtils.hasText(f_description)
                                        || !StringUtils.hasText(f_blxbh)
                                        || !StringUtils.hasText(f_comm_rate)
                                        || !StringUtils.hasText(f_protocol_type)
                                        || !StringUtils.hasText(f_cjfabh)
                                        || !StringUtils.hasText(f_communication_port)
                                        || !StringUtils.hasText(f_yqbh)
                                        || !StringUtils.hasText(f_percentage)
                                        || !StringUtils.hasText(f_com_data_bit)
                                        || !StringUtils.hasText(f_com_parity_bit)
                                        || !StringUtils.hasText(f_com_stop_bit)
                                        || !StringUtils.hasText(f_function_code)

                                ) {
                                    throw new Exception(f_sys_name + ":能耗电表节点表格内容不完整");
                                }

                                //查询系统名称在设备配置表中是否存在
                                if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                                    throw new Exception(f_sys_name + ":系统名称在节点表中不存在");
                                }

                                //根据通信波特率编号查询通信波特率名称
                                String f_comm_rate_mc = besSbdyExcelTableImportMapper.selectF_comm_rate_mc(f_comm_rate);
                                if (f_comm_rate_mc == null) {
                                    throw new Exception(f_sys_name + ":通信波特率编号在能耗电表节点表中填写错误,请检查");
                                }
                                //根据通信协议类型编号查询通信协议名称
                                String f_protocol_type_mc = besSbdyExcelTableImportMapper.selectF_protocol_type_mc(f_protocol_type);
                                if (f_protocol_type_mc == null) {
                                    throw new Exception(f_sys_name + ":通信协议类型编号在能耗电表节点表中填写错误,请检查");
                                }
                                //根据采集方案编号查询采集方案名称
                                String f_cjfamc = besSbdyExcelTableImportMapper.selectF_cjfabh_mc(f_cjfabh);
                                if (f_cjfamc == null) {
                                    throw new Exception(f_sys_name + ":采集方案编号在能耗电表节点表中填写错误,请检查");
                                }
                                //根据电表类型编号查询电表类型名称
                                String f_blxmc = besSbdyExcelTableImportMapper.selectF_blxbh_mc(f_blxbh);
                                if (f_blxmc == null) {
                                    throw new Exception(f_sys_name + ":电表类型编号在能耗电表节点表中填写错误,请检查");
                                }

                                String f_guid = UUIDUtil.getRandom32BeginTimePK();

                                // 生成设备id
                                String maxId = besSbdyExcelTableImportMapper.queryAmmeterMaxId();
                                String meterID = getAutoIncreaseCol(maxId);
                                String f_sbid = meterID;

                                BESAmmeter besAmmeter = new BESAmmeter();
                                besAmmeter.setfGuid(f_guid);
                                besAmmeter.setfSbid(f_sbid);
                                besAmmeter.setfSysName(f_sys_name);
                                besAmmeter.setfNickName(f_nick_name);
                                besAmmeter.setfEnabled(1);
                                besAmmeter.setfAzwz(f_azwz);
                                ;
                                besAmmeter.setfWldz(f_wldz);
                                besAmmeter.setfDescription(f_description);
                                besAmmeter.setfType("0");
                                besAmmeter.setfCommRate(f_comm_rate);
                                besAmmeter.setfCommRateMc(f_comm_rate_mc);
                                besAmmeter.setfProtocolType(f_protocol_type);
                                besAmmeter.setfProtocolTypeMc(f_protocol_type_mc);
                                besAmmeter.setfCjfabh(f_cjfabh);
                                besAmmeter.setfCjfamc(f_cjfamc);
                                besAmmeter.setfBlxbh(f_blxbh);
                                besAmmeter.setfBlxmc(f_blxmc);
                                besAmmeter.setfCommunicationPort(f_communication_port);
                                besAmmeter.setfYqbh(f_yqbh);
                                besAmmeter.setfPercentage(f_percentage);
                                besAmmeter.setfAmmeterState("0");
                                besAmmeter.setfComDataBit(f_com_data_bit);
                                besAmmeter.setfComParityBit(f_com_parity_bit);
                                besAmmeter.setfComStopBit(f_com_stop_bit);
                                besAmmeter.setfFunctionCode(f_function_code);

                                inportflag = besSbdyExcelTableImportMapper.add_sbdyStructAmmeter(besAmmeter);
                            }
                        }
                        if (inportflag) {
                            returnObject.setMsg("导入成功！");
                            returnObject.setStatus("1");
                        } else {
                            throw new Exception("导入失败");
//								returnObject.setMsg("导入失败！");
//								returnObject.setStatus("0");
//								return returnObject;
                        }

                        break;
                    }

                    default:
                        throw new Exception("节点类型不存在");
//					returnObject.setMsg("节点类型不存在");
//					returnObject.setStatus("0");
//					return returnObject;
                }

            }
        }catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return returnObject;
    }

    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertBesStruct(sbdyStruct sbdy,String nodeTabName){
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String, Object> psysName = null;
        boolean  inportflag = false;

        try {

            //根据父节点名称查询数据库中是否有这个名称
            psysName = besSbdyExcelTableImportMapper.selectSbdyByPsysName(sbdy.getfPsysname());

            if (psysName == null) {
                throw new Exception(nodeTabName+ "→" + sbdy.getfSysname() + ":父节点不存在");
            }

            if (null != besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(sbdy.getfSysname())) {
                throw new Exception(nodeTabName+ "→" + sbdy.getfSysname() + ":系统名称不能重复");
            }

            String allpath = (String) psysName.get("F_ALLPATH");//父节点的全路径
            allpath = allpath + ">" + sbdy.getfSysname();
            String f_status = "0";//状态

            BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
            besSbPzStruct.setF_type(sbdy.getfType());
            besSbPzStruct.setF_sys_name(sbdy.getfSysname());
            besSbPzStruct.setF_node_attribution(sbdy.getfNodeattribution());
            besSbPzStruct.setF_nick_name(sbdy.getfNickname());
            besSbPzStruct.setF_psys_name(sbdy.getfPsysname());
            besSbPzStruct.setF_description(sbdy.getfDescription());
            besSbPzStruct.setF_allpath(allpath);
            besSbPzStruct.setF_status(f_status);


            inportflag = besSbdyExcelTableImportMapper.add_sbdyStruct(besSbPzStruct);

            //新增临时表,导入完成后,替换掉bes_sbdy_struct表中的数据
            Boolean aa =  besSbdyExcelTableImportMapper.add_sbdyStructCopy(besSbPzStruct);

            if (inportflag) {
               if (aa) {
                   returnObject.setMsg("导入成功！");
                   returnObject.setStatus("1");
                   returnObject.setData(allpath);
               }

            } else {
                throw new Exception("导入失败");
//				returnObject.setMsg("导入失败！");
//				returnObject.setStatus("0");
//				return returnObject;
            }

        } catch (FileNotFoundException | NullPointerException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return returnObject;
    }

    /**
     *
     * @Description: 楼宇自控
     *
     * @auther: wanghongjie
     * @date: 9:33 2020/10/11
     * @param: [list, fileUrl]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject buildingAutomation(List<sbdyStruct> list, String fileUrl) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        // 获取要导出的数据
        List<ExcelError> excelErrors = new ArrayList<>();
        boolean inportflag = false;
        Map<String, Object> psysName = null;
        // 初始化导入工具类
        FileInputStream fis = null;
        //将ecxcel查出来的数据放到list里面,统一使用
        List<besPointExcel> pointList = null;
        List<besModuleExcel> Modulelist = null;
        List<besDDCExcel> DDClist= null;

        String nodeTabName = "楼宇自控节点表";

        try {

            excelErrors = nodeTable(list);

            /*if (excelErrors.size() > 0) {
                returnObject.setMsg("导入数据过程中出现失误，请查看excel错误报告！");
                returnObject.setStatus("2");
                returnObject.setList(excelErrors);
//						return returnObject;
                throw new Exception((Throwable) returnObject.getList());
            }*/

            if (excelErrors.size() > 0) {
                throw new Exception(nodeTabName + "第" + excelErrors.get(0).getRow() + "行" +excelErrors.get(0).getErrorMsg());
            }

            Set<String> point = new HashSet<>();

            for (sbdyStruct sbdyStructs : list) {
                //判断楼宇自控节点表中系统名称数据是否重复
                Boolean success = point.add(sbdyStructs.getfSysname());
                if (!success) {
                    throw new Exception(sbdyStructs.getfSysname() +":系统名称在楼宇自控节点表中重复");
                }
            }
            for (sbdyStruct sbdyStructs : list) {


                switch (sbdyStructs.getfType()) {
                    case "1":
                    case "24":
                    case "8":
                    case "23":	{//楼宇自控节点,虚点(无属性页面),总线,线路

                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);
                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }


                        break;
                    }
                    case "2": {//楼宇控制器节点

                        if (DDClist == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besDDCExcel> util = new ExcelUtil<>(besDDCExcel.class);
                            DDClist = util.importExcel("楼宇控制器节点", fis);// 导入excel,处理后生成list
                        }

                        returnObject = insertDDCByExcel(DDClist,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;

                    }
                    case "9":{//模块

                        if (Modulelist == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besModuleExcel> util = new ExcelUtil<>(besModuleExcel.class);
                            Modulelist = util.importExcel("模块节点", fis);// 导入excel,处理后生成list
                        }

                        returnObject = insertModuleByExcel(Modulelist,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;
                    }
                    case "DO":
                    case "DI":
                    case "AO":
                    case "AI": {

                        if (pointList == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besPointExcel> util = new ExcelUtil<>(besPointExcel.class);
                            pointList = util.importExcel("点位节点", fis);// 导入excel,处理后生成list
                        }

                        List lists=new ArrayList();

                        Set<String> point_DODIAOAI = new HashSet<>();
                        for (besPointExcel besPointExcel : pointList) {
                            //判断点位节点表中系统名称数据是否重复
                            Boolean success1 = point_DODIAOAI.add(besPointExcel.getfSysNameOld());
                            if (!success1) {
                                throw new Exception(besPointExcel.getfSysNameOld() +":系统名称在点位节点表中重复");
                            }
                            lists.add(besPointExcel.getfSysNameOld());
                        }
                        Boolean aa = lists.contains(sbdyStructs.getfSysname());

                        if (!aa) {
                            throw new Exception(sbdyStructs.getfSysname() +"系统名称在点位节点表中不存在");
                        }

                        returnObject = pointInsertByExcel(pointList,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return  returnObject;
                        }
                        break;
                    }
                    default:
                        throw new Exception("节点类型不存在");
                }
            }

        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     *
     * @Description: 判断节点表信息是否完整
     *
     * @auther: wanghongjie
     * @date: 10:36 2020/9/14
     * @param: [list]
     * @return: java.util.List<com.efounder.JEnterprise.model.excelres.ExcelError>
     *
     */
    private List<ExcelError> nodeTable(List<sbdyStruct> list) {
        List<ExcelError> excelErrors = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            boolean flag = true; //插入数据成功的标志
            sbdyStruct sbdy = list.get(i);
            String date = DateUtil.getCurrTime();
            String errMsg = "";
            if (sbdy.getfType() == null || sbdy.getfType().equals("")) {
                errMsg = "节点类型为空";
                flag = false;
            } else if (sbdy.getfSysname() == null || sbdy.getfSysname().equals("")) {
                if ("".equals(errMsg)) {
                    errMsg = "系统名称为空";
                    flag = false;
                } else {
                    errMsg = errMsg + ",类型名称为空";
                    flag = false;
                }
            } else if (sbdy.getfDescription() == null || sbdy.getfDescription().equals("")) {
                if ("".equals(errMsg)) {
                    errMsg = "描述为空";
                    flag = false;
                } else {
                    errMsg = errMsg + ",描述为空";
                    flag = false;
                }

            } else if (sbdy.getfPsysname() == null || sbdy.getfPsysname().equals("")) {

                if ("".equals(errMsg)) {
                    errMsg = "父节点系统名称为空";
                    flag = false;
                } else {
                    errMsg = errMsg + ",父节点系统名称为空";
                    flag = false;
                }
            }else if (sbdy.getfNodeattribution() == null || sbdy.getfNodeattribution().equals("")) {

                if ("".equals(errMsg)) {
                    errMsg = "所属系统为空";
                    flag = false;
                } else {
                    errMsg = errMsg + ",所属系统为空";
                    flag = false;
                }
            }
            else if (sbdy.getfNickname() == null || sbdy.getfNickname().equals("")) {

                if ("".equals(errMsg)) {
                    errMsg = "别名为空";
                    flag = false;
                } else {
                    errMsg = errMsg + ",别名为空";
                    flag = false;
                }
            }

            if (!flag) {
                ExcelError excelError = new ExcelError();
                excelError.setRow((i + 2) + "");
                excelError.setErrorMsg(errMsg);
                excelErrors.add(excelError);
            }
        }

        return excelErrors;
    }

    /**
     *
     * @Description: excel导入添加DDC控制器的信息
     *
     * @auther: wanghongjie
     * @date: 17:13 2020/9/16
     * @param: [ddClist, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertDDCByExcel(List<besDDCExcel> ddClist, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Boolean inportflag = null;

        String nodeTabName = "楼宇控制器节点表";
        try {

            List lists=new ArrayList();

            Set<String> point = new HashSet<>();
            for (besDDCExcel besDDCExcel : ddClist) {

                //判断总线表中系统名称数据是否重复
                Boolean success = point.add(besDDCExcel.getfSysName());
                if (!success) {
                    throw new Exception(besDDCExcel.getfSysName() +":系统名称在控制器节点表中重复");
                }
                lists.add(besDDCExcel.getfSysName());
            }
            Boolean aa = lists.contains(sbdyStructs.getfSysname());

            if (!aa) {
                throw new Exception(sbdyStructs.getfSysname() +"系统名称在控制器节点表中不存在");
            }

            returnObject = insertBesStruct(sbdyStructs,nodeTabName);

            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                return returnObject;
            }

            String allPath = (String) returnObject.getData();

            for (besDDCExcel besDDCExcel : ddClist) {

                String f_sys_name 		= besDDCExcel.getfSysName();
                String f_nick_name 		= besDDCExcel.getfNickName();
                String f_ssqy 			= besDDCExcel.getfSsqy();
                String f_azwz 			= besDDCExcel.getfAzwz();
                String f_description 	= besDDCExcel.getfDescription();
                String DDCIp 			= besDDCExcel.getfIpAddr();
                String f_node_type 		= besDDCExcel.getfNodeType();
                String yqbh 			= besDDCExcel.getfYqbh();
                String f_gateway 		= besDDCExcel.getfGateway();
                String f_mask 			= besDDCExcel.getfMask();
                String f_ip_master 		= besDDCExcel.getfIpMaster();
                String f_port_master 	= besDDCExcel.getfPortMaster();
                String f_enabled		= besDDCExcel.getfEnabled();

                if (       !StringUtils.hasText(f_sys_name)
                        || !StringUtils.hasText(f_nick_name)
                        || !StringUtils.hasText(f_ssqy)
                        || !StringUtils.hasText(f_azwz)
                        || !StringUtils.hasText(f_description)
                        || !StringUtils.hasText(DDCIp)
                        || !StringUtils.hasText(f_node_type)
                        || !StringUtils.hasText(yqbh)
                        || !StringUtils.hasText(f_gateway)
                        || !StringUtils.hasText(f_mask)
                        || !StringUtils.hasText(f_ip_master)
                        || !StringUtils.hasText(f_port_master)
                        || !StringUtils.hasText(f_enabled)
                )
                {
                    throw new Exception(f_sys_name + "控制器节点表格内容不完整");
                }

                if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                    //查询系统名称在设备配置表中是否存在
                    if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                        throw new Exception(f_sys_name + "系统名称在节点表中不存在");
                    }

                    // 查询ip地址是否已经存在
                    int besDDCSize = besSbdyExcelTableImportMapper.getSizeByIpAddrBesDdc(DDCIp);
                    int besCollectorSize = besSbdyExcelTableImportMapper.getSizeByIpAddrBesCollector(DDCIp);
                    if (besDDCSize == 1 || besCollectorSize == 1){

                        throw new Exception(DDCIp +"ip 地址重复");
                    }

                    // 生成设备id

                    String sbid 	= besSbdyExcelTableImportMapper.select_f_sbid_By_Bes_Ddc();
                    String meterID 	= getAutoIncreaseCol(sbid);
                    String f_sbid 	= meterID;

//								besDDCExcel.setfGuid(f_guid);
                    besDDCExcel.setfSbid(f_sbid);
                    besDDCExcel.setfPollStatus("1");
                    besDDCExcel.setfEnabled(f_enabled);
                    besDDCExcel.setfDdcState("0");
                    besDDCExcel.setfOnlineState("0");
                    besDDCExcel.setfChannelId(DDCIp);

                    inportflag = besSbdyExcelTableImportMapper.add_sbdyStructDDC(besDDCExcel);

                    //DDC控制器添加完毕后默认添加虚点
								/*if (inportflag) {
									returnObject = insertVirtualPoint_bus_line(f_sys_name,allPath,f_node_type);
								}*/

                }
            }
						/*if (returnObject.getMsg().equals("导入成功")) {
							returnObject.setMsg("导入成功！");
							returnObject.setStatus("1");
						} else {
							throw new Exception("能耗控制器节点表的系统名称在节点表中不存在");
//						returnObject.setMsg("能耗控制器节点表的系统名称在节点表中不存在！");
						}*/

            if (inportflag) {
                returnObject.setMsg("导入成功！");
                returnObject.setStatus("1");
            } else {
                throw new Exception("导入失败");
            }

        }catch (NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return returnObject;
    }
    /**
     *
     * @Description: 添加模块信息
     *
     * @auther: wanghongjie
     * @date: 17:09 2020/9/16
     * @param: [modulelist, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertModuleByExcel(List<besModuleExcel> modulelist, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Boolean inportflag = null;

        String nodeTabName = "楼宇模块节点表";
        try {
            JSONObject obj = new JSONObject();
            List lists=new ArrayList();

            Set<String> point = new HashSet<>();
            for (besModuleExcel besModuleExcel : modulelist) {

                //判断楼宇模块节点表中系统名称数据是否重复
                Boolean success = point.add(besModuleExcel.getfSysName());
                if (!success) {
                    throw new Exception(besModuleExcel.getfSysName() +":系统名称在楼宇模块节点表中重复");
                }
                lists.add(besModuleExcel.getfSysName());
            }
            Boolean aa = lists.contains(sbdyStructs.getfSysname());

            if (!aa) {
                throw new Exception(sbdyStructs.getfSysname() +"系统名称在模块节点表中不存在");
            }

            returnObject = insertBesStruct(sbdyStructs,nodeTabName);

            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                return returnObject;
            }

            String allPath = (String) returnObject.getData();

            for (besModuleExcel besModuleExcel : modulelist) {

                String f_sys_name 		= besModuleExcel.getfSysName();
                String f_nick_name 		= besModuleExcel.getfNickName();
                String f_azwz 			= besModuleExcel.getfAzwz();
                String f_description 	= besModuleExcel.getfDescription();
                String f_node_type 		= besModuleExcel.getfNodeType();
                String yqbh 			= besModuleExcel.getfYqbh();
                String f_module_type	= besModuleExcel.getfModuleType();
                String f_addr 			= besModuleExcel.getfAddr();
                String f_enabled		= besModuleExcel.getfEnabled();

                if (       !StringUtils.hasText(f_sys_name)
                        || !StringUtils.hasText(f_nick_name)
                        || !StringUtils.hasText(f_azwz)
                        || !StringUtils.hasText(f_description)
                        || !StringUtils.hasText(f_node_type)
                        || !StringUtils.hasText(yqbh)
                        || !StringUtils.hasText(f_module_type)
                        || !StringUtils.hasText(f_addr)
                        || !StringUtils.hasText(f_enabled)
                )
                {
                    throw new Exception(f_sys_name+":模块节点表格内容不完整");
                }

                if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                    //查询系统名称在设备配置表中是否存在
                    if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                        throw new Exception(f_sys_name + "系统名称在节点表中不存在");
                    }

                    //如果模块的通信地址在当前ddc下有相同的地址,则在页面提示通信地址重复,添加失败
                    List<Map<String,Object>> f_addrList = besSbdyExcelTableImportMapper.f_addrListByPName(sbdyStructs.getfPsysname());
                    for (int i = 0; i < f_addrList.size(); i++){
                        if (f_addr.equals((String) f_addrList.get(i).get("F_ADDR"))){
                            throw new Exception(f_addr + ":通信地址重复");
                        }
                    }

                    Map<String, Object> ddcinfo = null;
                    if (sbdyStructs.getfNodeattribution().equals("1")) {//楼宇自控
                        ddcinfo = besSbdyExcelTableImportMapper.queryDDCInfoByModule(sbdyStructs.getfPsysname());
                    } else if (sbdyStructs.getfNodeattribution().equals("2")) {//照明
                        ddcinfo = besSbdyExcelTableImportMapper.queryLDCInfoByModule(sbdyStructs.getfPsysname());
                    }
                    //获取sbid
//                    obj.put("f_sbid", getSbid((String)ddcinfo.get("F_SYS_NAME")) + 1);

                    String f_point_type_cl = besSbdyExcelTableImportMapper.selectPointTypeClByModule(f_module_type);//查询当前模块的点集合
                    if (f_point_type_cl == null) {
                        throw new Exception(f_module_type + ":请配置这个模块型号");
                    }
                    obj.put("attr_f_sys_name",f_sys_name);
                    obj.put("other_node_types",f_point_type_cl);
                    obj.put("f_allpath",allPath);
                    obj.put("f_node_attribution",sbdyStructs.getfNodeattribution());
                    Integer addDefaultNodeCount = addDefaultNodes(obj);//自动添加模块下的点位

                    String idByModule = besSbdyExcelTableImportMapper.selectIdByModule(f_sys_name);//查询当前模块的F_ID
                    // 生成设备id
                    String sbid = besSbdyExcelTableImportMapper.select_f_sbid_By_Bes_Module();
                    String meterID = getAutoIncreaseCol(sbid);
                    String f_sbid  = meterID;

                    //根据模块型号查询模块型号表的F_ID
                    String moduleTypeId = besSbdyExcelTableImportMapper.selectModuleTypeId(f_module_type);
//								besDDCExcel.setfGuid(f_guid);
                    besModuleExcel.setfSbid(f_sbid);
                    besModuleExcel.setfStructId(idByModule);
                    besModuleExcel.setfEnabled(f_enabled);
                    besModuleExcel.setfType(moduleTypeId);

                    inportflag = besSbdyExcelTableImportMapper.add_sbdyStructModule(besModuleExcel);

                    if (inportflag) {
                        //20210802  当excel导入插座和低档温控器时,只需要添加值模块,下面的点位自动生成
                        if ("WKQ_MOD".equals(f_module_type) || "计量插座".equals(f_module_type) || "新产业园人体光照度".equals(f_module_type)) {

                            returnObject = insertPointAutomatic(besModuleExcel,f_module_type);

                            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                                return  returnObject;
                            }
                        }
                    }
                }
            }

            if (inportflag) {
                returnObject.setMsg("导入成功！");
                returnObject.setStatus("1");
            } else {
                throw new Exception("导入失败");
            }
        }catch (NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return returnObject;
    }

    /**
     *
     * @Description: 自动添加点位
     *
     * @auther: wanghongjie
     * @date: 18:21 2021/8/2
     * @param:
     * @return:
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertPointAutomatic(besModuleExcel besModuleExcel, String  f_module_type) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        besPointExcel besPointExcel = new besPointExcel();
        sbdyStruct sbdyStruct = new sbdyStruct();
        String nodeTabName = "模块下点位自动生成逻辑";

        if ("WKQ_MOD".equals(f_module_type)) {
            for (int i = 0; i < 12; i++) {
                String f_Psys_name = besModuleExcel.getfSysName();
                String f_sys_name = f_Psys_name + "0" + String.valueOf(i);

                sbdyStruct.setfDescription("中档温控器");
                sbdyStruct.setfNickname(f_sys_name);
                sbdyStruct.setfSysname(f_sys_name);
                sbdyStruct.setfNodeattribution("1");
                sbdyStruct.setfPsysname(f_Psys_name);

                besPointExcel.setfEnabled("1");
                besPointExcel.setfWorkMode("1");
                besPointExcel.setfReversed("0");
                besPointExcel.setfAlarmEnable("0");
                besPointExcel.setfYqbh("0000");
                besPointExcel.setfEnergystatics("0");


                if (i <= 1) {

                    sbdyStruct.setfType("DO");
                    besPointExcel.setfSysNameOld(f_sys_name);
                    besPointExcel.setPointTypeName("DO");
                    besPointExcel.setfChannelIndex(String.valueOf(i));

                    if (i == 0) {
                        besPointExcel.setfNickName("开关机");
                        besPointExcel.setfDescription("开关机");
                        besPointExcel.setfInitVal("255");
                    } else {
                        besPointExcel.setfNickName("模式");
                        besPointExcel.setfDescription("模式");
                        besPointExcel.setfInitVal("0");
                    }

                    returnObject = insertDO_DIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }

                } else if ((i > 1 && i <= 3) || i > 4) {

                    sbdyStruct.setfType("AO");
                    besPointExcel.setfSysNameOld(f_sys_name);
                    besPointExcel.setPointTypeName("AO");
                    besPointExcel.setfChannelIndex(String.valueOf(i));
                    besPointExcel.setfSinnalType("0");
                    besPointExcel.setfMinVal("0");

                    if (i == 2) {
                        besPointExcel.setfNickName("风速");
                        besPointExcel.setfDescription("风速");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("#");
                        besPointExcel.setfMaxVal("10");
                        besPointExcel.setfAccuracy("0");
                    } else if (i == 3){
                        besPointExcel.setfNickName("锁定");
                        besPointExcel.setfDescription("锁定");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("#");
                        besPointExcel.setfMaxVal("10");
                        besPointExcel.setfAccuracy("0");
                    } else if (i == 5){
                        besPointExcel.setfNickName("功率");
                        besPointExcel.setfDescription("功率");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("W");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("0");
                    }else if (i == 6){
                        besPointExcel.setfNickName("时间计量");
                        besPointExcel.setfDescription("时间计量");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("H");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("0");
                    }else if (i == 7){
                        besPointExcel.setfNickName("设定温度");
                        besPointExcel.setfDescription("设定温度");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("℃");
                        besPointExcel.setfMaxVal("100");
                        besPointExcel.setfAccuracy("1");
                    }else if (i == 8){
                        besPointExcel.setfNickName("温度补偿");
                        besPointExcel.setfDescription("温度补偿");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("℃");
                        besPointExcel.setfMaxVal("100");
                        besPointExcel.setfAccuracy("1");
                    }else if (i == 9){
                        besPointExcel.setfNickName("累计能耗");
                        besPointExcel.setfDescription("累计能耗");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("KW.H");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("0");
                    }else if (i == 10){
                        besPointExcel.setfNickName("定时开");
                        besPointExcel.setfDescription("定时开");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("H");
                        besPointExcel.setfMaxVal("10");
                        besPointExcel.setfAccuracy("0");
                    }else if (i == 11){
                        besPointExcel.setfNickName("定时关");
                        besPointExcel.setfDescription("定时关");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("H");
                        besPointExcel.setfMaxVal("10");
                        besPointExcel.setfAccuracy("0");
                    }

                    returnObject = insertAO_AIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }

                } else if (i == 4) {
                    sbdyStruct.setfType("AI");
                    besPointExcel.setfSysNameOld(f_sys_name);
                    besPointExcel.setPointTypeName("AI");
                    besPointExcel.setfChannelIndex(String.valueOf(i));

                    besPointExcel.setfNickName("室内温度");
                    besPointExcel.setfDescription("室内温度");
                    besPointExcel.setfInitVal("0");
                    besPointExcel.setfEngineerUnit("℃");
                    besPointExcel.setfMaxVal("100");
                    besPointExcel.setfAccuracy("1");

                    returnObject = insertAO_AIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }
                }
            }
        }
        else if ("计量插座".equals(f_module_type)){

            for (int i = 0; i < 5; i++) {
                String f_Psys_name = besModuleExcel.getfSysName();
                String f_sys_name = f_Psys_name + "0" + String.valueOf(i);

                sbdyStruct.setfDescription("计量插座");
                sbdyStruct.setfNickname(f_sys_name);
                sbdyStruct.setfSysname(f_sys_name);
                sbdyStruct.setfNodeattribution("2");
                sbdyStruct.setfPsysname(f_Psys_name);

                besPointExcel.setfEnabled("1");
                besPointExcel.setfWorkMode("1");
                besPointExcel.setfReversed("0");
                besPointExcel.setfAlarmEnable("0");
                besPointExcel.setfYqbh("0000");
                besPointExcel.setfEnergystatics("0");


                if (i == 0) {

                    sbdyStruct.setfType("DO");
                    besPointExcel.setfSysNameOld(f_sys_name);
                    besPointExcel.setPointTypeName("DO");
                    besPointExcel.setfChannelIndex(String.valueOf(i));

                    besPointExcel.setfNickName("开关");
                    besPointExcel.setfDescription("开关");
                    besPointExcel.setfInitVal("255");

                    returnObject = insertDO_DIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }

                } else {

                    sbdyStruct.setfType("AI");
                    besPointExcel.setfSysNameOld(f_sys_name);
                    besPointExcel.setPointTypeName("AI");
                    besPointExcel.setfChannelIndex(String.valueOf(i));
                    besPointExcel.setfSinnalType("0");
                    besPointExcel.setfMinVal("0");


                    if (i == 1) {
                        besPointExcel.setfNickName("能耗");
                        besPointExcel.setfDescription("能耗");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("kwh");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("3");
                    } else if (i == 2){
                        besPointExcel.setfNickName("功率");
                        besPointExcel.setfDescription("功率");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("W");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("1");
                    } else if (i == 3){
                        besPointExcel.setfNickName("电流");
                        besPointExcel.setfDescription("电流");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("A");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("2");
                    }else if (i == 4){
                        besPointExcel.setfNickName("电压");
                        besPointExcel.setfDescription("电压");
                        besPointExcel.setfInitVal("0");
                        besPointExcel.setfEngineerUnit("V");
                        besPointExcel.setfMaxVal("1000000");
                        besPointExcel.setfAccuracy("2");
                    }

                    returnObject = insertAO_AIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }
                }
            }
        }
        else if ("新产业园人体光照度".equals(f_module_type)) {
            for (int i = 1; i < 4; i++) {

                String f_Psys_name = besModuleExcel.getfSysName();
                String f_sys_name = f_Psys_name + "0" + String.valueOf(i);

                sbdyStruct.setfDescription("人体光照度");
                sbdyStruct.setfNickname(f_sys_name);
                sbdyStruct.setfSysname(f_sys_name);
                sbdyStruct.setfNodeattribution("2");
                sbdyStruct.setfPsysname(f_Psys_name);

                besPointExcel.setfSysNameOld(f_sys_name);
                besPointExcel.setfMinVal("0");
                besPointExcel.setfEnabled("1");
                besPointExcel.setfWorkMode("1");
                besPointExcel.setfReversed("0");
                besPointExcel.setfAlarmEnable("0");
                besPointExcel.setfYqbh("0000");
                besPointExcel.setfEnergystatics("0");
                besPointExcel.setfChannelIndex(String.valueOf(i));

                if (i == 1) {
                    sbdyStruct.setfType("DI");
                    besPointExcel.setPointTypeName("DI");
                    besPointExcel.setfNickName("人员状态");
                    besPointExcel.setfDescription("人员状态");
                    besPointExcel.setfInitVal("0");
                    besPointExcel.setfSourced("0");

                    returnObject = insertDO_DIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }

                } else if (i == 3) {
                    sbdyStruct.setfType("AI");
                    besPointExcel.setPointTypeName("AI");
                    besPointExcel.setfSinnalType("0");
                    besPointExcel.setfNickName("光照度值");
                    besPointExcel.setfDescription("光照度值");
                    besPointExcel.setfInitVal("0");
                    besPointExcel.setfEngineerUnit("LUX");
                    besPointExcel.setfMaxVal("1000000");
                    besPointExcel.setfAccuracy("2");

                    returnObject = insertAO_AIPoint(besPointExcel,sbdyStruct);

                    if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                        return returnObject;
                    }
                }
            }
        }
        return returnObject;
    }

    /**
     *
     * @Description: Excel导入点位节点
     *
     * @auther: wanghongjie
     * @date: 10:35 2020/9/14
     * @param: [pointList, sbdyStructs, allPath]
     * @return: void
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject pointInsertByExcel(List<besPointExcel> pointList, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        try {

            for (besPointExcel besPoint : pointList) {

                if (besPoint.getPointTypeName() == null) {
                    throw new Exception(besPoint.getfSysNameOld() + "点位节点表的节点类型不存在");
                }
                if (besPoint.getfSysNameOld().equals(sbdyStructs.getfSysname())) {
                    //查询该节点的父节点的类型
                    String pSysNameType = besSbdyExcelTableImportMapper.selectPSysNameType(sbdyStructs.getfPsysname());

                    if (pSysNameType == null) {
                        throw new Exception(besPoint.getfSysNameOld() + "节点表的父节点输入错误");
                    }
                    if (pSysNameType.equals("9")) {//模块节点

                        if (!besPoint.getPointTypeName().equals("DO") && !besPoint.getPointTypeName().equals("DI")
                                && !besPoint.getPointTypeName().equals("AO") && !besPoint.getPointTypeName().equals("AI")) {

                            throw new Exception(besPoint.getfSysNameOld() + "点位节点表的节点类型不存在");
                        }
                        if (besPoint.getPointTypeName().equals("DO") || besPoint.getPointTypeName().equals("DI")) {

                            returnObject = insertDO_DIPoint(besPoint,sbdyStructs);

                            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                                return returnObject;
                            }
                            if (returnObject.getMsg() == "导入成功！") {
                                return returnObject;
                            }


                        } else if (besPoint.getPointTypeName().equals("AO") || besPoint.getPointTypeName().equals("AI")) {

                            returnObject = insertAO_AIPoint(besPoint,sbdyStructs);
                            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                                return returnObject;
                            }
                            if (returnObject.getMsg() == "导入成功！") {
                                return returnObject;
                            }

                        }
                    } else if (pSysNameType.equals("24")) {//虚点无属性页面节点

                        if (!besPoint.getPointTypeName().equals("DO")  && !besPoint.getPointTypeName().equals("DI")
                                && !besPoint.getPointTypeName().equals("AO") && !besPoint.getPointTypeName().equals("AI")) {

                            throw new Exception(besPoint.getfSysNameOld() + "点位节点表的节点类型不存在");
                        }

                        if (besPoint.getPointTypeName().equals("DO") || besPoint.getPointTypeName().equals("DI")) {

                            returnObject = insertDO_DIVPoint(besPoint,sbdyStructs);
                            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                                return returnObject;
                            }
                            if (returnObject.getMsg() == "导入成功！") {
                                return returnObject;
                            }

                        }else if (besPoint.getPointTypeName().equals("AO") || besPoint.getPointTypeName().equals("AI")) {

                            returnObject = insertAO_AIVPoint(besPoint,sbdyStructs);
                            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                                return returnObject;
                            }
                            if (returnObject.getMsg() == "导入成功！") {
                                return returnObject;
                            }
                        }
                    }
                }
            }


        }catch (NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return returnObject;
    }

    /**
     *
     * @Description: Excel DO,DI点位新增
     *
     * @auther: wanghongjie
     * @date: 14:03 2020/9/15
     * @param: [besPoint, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertDO_DIPoint(besPointExcel besPoint, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Integer f_idBySbdyStruct ;
        String allpath;
        String f_sys_name;
        String f_type = null;
        String tabName = null;
        Boolean Has_it_been_added = true;//此点位是否已添加,默认已添加

        try {

            //判断当前点位的通道索引是否符合模块的模块型号,首先查出模块的点集合
            Map<String,Object> moduleType = besSbdyExcelTableImportMapper.selectF_POINT_TYPE_CL(sbdyStructs.getfPsysname());

            String F_POINT_TYPE_CL = (String) moduleType.get("F_POINT_TYPE_CL");

            //模块型号
            String moduleName = (String) moduleType.get("F_MODULE_TYPE");

            if ("3路计量模块".equals(moduleName) || "7路计量模块".equals(moduleName) || "11路计量模块".equals(moduleName)) {
                besPoint.setfEnabled("1");
                besPoint.setfWorkMode("1");
                besPoint.setfReversed("0");
                besPoint.setfInitVal("255");
                besPoint.setfAlarmEnable("0");
                besPoint.setfYqbh("0000");
            }


            String f_alarm_type;
            String f_close_state;
            String f_alarm_priority;
            String f_fault_state;

            String f_sys_name_old 	= besPoint.getfSysNameOld();
            String f_nick_name 		= besPoint.getfNickName();
            String f_enabled 		= besPoint.getfEnabled();
            String f_channel_index 	= besPoint.getfChannelIndex();
            String f_reversed 		= besPoint.getfReversed();

            String f_work_mode 		= besPoint.getfWorkMode();
            String f_init_val 		= besPoint.getfInitVal();
            String f_description 	= besPoint.getfDescription();
            String yqbh				= besPoint.getfYqbh();
            String f_alarm_enable 	= besPoint.getfAlarmEnable();

            if (!f_alarm_enable.equals("0")) {
                f_alarm_type 	= besPoint.getfAlarmType();
                f_close_state 	= besPoint.getfCloseState();
                f_alarm_priority = besPoint.getfAlarmPriority();
                f_fault_state    = besPoint.getfFaultState();
            } else {//如果excel导入的DO点位报警不使能,则将报警类型,闭合状态,报警等级,故障状态设置一个默认值
                f_alarm_type 	= "0";//0:	不报警  1:	标准报警  2:	增强报警
                besPoint.setfAlarmType(f_alarm_type);
                f_close_state 	= "0";//0:	闭合报警 1:	断开报警
                besPoint.setfCloseState(f_close_state);
                f_alarm_priority = "0";//0:	一般 1:	较大 2:	重大
                besPoint.setfAlarmPriority(f_alarm_priority);
                f_fault_state    = "0";//0:	不启用 1:	启用
                besPoint.setfFaultState(f_fault_state);

            }

            if (besPoint.getfFaultState() == null) {//如果报警是使能状态,并且故障状态未设置,则将故障状态设置成不启用
                f_fault_state    = "0";//0:	不启用 1:	启用
                besPoint.setfFaultState(f_fault_state);
            }

            if (       !StringUtils.hasText(f_sys_name_old)
                    || !StringUtils.hasText(f_nick_name)
                    || !StringUtils.hasText(f_enabled)
                    || !StringUtils.hasText(f_channel_index)
                    || !StringUtils.hasText(f_reversed)
                    || !StringUtils.hasText(f_work_mode)
                    || !StringUtils.hasText(f_init_val)
                    || !StringUtils.hasText(f_description)
                    || !StringUtils.hasText(yqbh)
                    || !StringUtils.hasText(f_alarm_enable)
                    || !StringUtils.hasText(f_alarm_type)
                    || !StringUtils.hasText(f_close_state)
                    || !StringUtils.hasText(f_alarm_priority)
                    || !StringUtils.hasText(f_fault_state)
            )
            {
                throw new Exception(f_sys_name_old+":点位节点表格内容不完整");
            }


            //修改自动生成的点位的系统名称以及别名和点类型,首先查询当前模块下所有的点位
            List<Map<String,Object>> module_pointList = besSbdyExcelTableImportMapper.selectModule_pointList(sbdyStructs.getfPsysname());

            String name = (String) module_pointList.get(Integer.parseInt(f_channel_index)).get("f_sys_name_old");

//			for (int i = 0;i < module_pointList.size(); i++) {


//				String name = (String) module_pointList.get(i).get("f_sys_name_old");

            if (name.contains(sbdyStructs.getfPsysname())){

                if (Integer.valueOf(name.replace(sbdyStructs.getfPsysname(),"")).equals(Integer.valueOf(f_channel_index))) {

                    Has_it_been_added = false;//此点位未添加

                    //修改设备配置的点位信息,首先查询当前点位在设备配置表的信息,目的是查出F_ID以及F_ALLPATH
//                    String tabNameSbdy =  "bes_sbpz_struct";
                    String tabNameSbdy =  "bes_sbpz_struct_copy";
                    Map<String,Object> pointMap = besSbdyExcelTableImportMapper.selectPointMap(tabNameSbdy,name);
                    f_idBySbdyStruct = (Integer) pointMap.get("F_ID");
                    allpath = (String) pointMap.get("F_ALLPATH");

//                    //判断当前点位的通道索引是否符合模块的模块型号,首先查出模块的点集合
//                    Map<String,Object> moduleType = besSbdyExcelTableImportMapper.selectF_POINT_TYPE_CL(sbdyStructs.getfPsysname());
//
//                    String F_POINT_TYPE_CL = (String) moduleType.get("F_POINT_TYPE_CL");
//
//                    //模块型号
//                    String moduleName = (String) moduleType.get("F_MODULE_TYPE");

                    final String substring = F_POINT_TYPE_CL.substring(Integer.parseInt(f_channel_index), Integer.parseInt(f_channel_index) + 1);
                    if (sbdyStructs.getfType().equals("DO")) {
                        if (!substring.equals("3")) {//DO点
                            throw new Exception(f_sys_name_old+"点位的通道索引不符合模块的模块型号");
                        }
                    }
                    if (sbdyStructs.getfType().equals("DI")) {
                        if (!substring.equals("4") && !substring.equals("2")) {//UI点,DI点
                            throw new Exception(f_sys_name_old+"点位的通道索引不符合模块的模块型号");
                        }
                    }

                    if (besPoint.getPointTypeName().equals("DO")) {
                        f_type = "13";
                        tabName = "BES_DIGIT_OUPUT";
                        allpath = allpath.replace(">DO类型",">"+f_sys_name_old);

                    } else if (besPoint.getPointTypeName().equals("DI")) {

                        f_type = "12";
                        tabName = "BES_DIGIT_INPUT";

                        if (pointMap.get("F_TYPE").equals(12)) {
                            allpath = allpath.replace(">DI类型",">"+f_sys_name_old);
                        } else if (pointMap.get("F_TYPE").equals(14)) {
                            allpath = allpath.replace(">UI类型",">"+f_sys_name_old);
                        }


                        String f_sourced = besPoint.getfSourced();
                        if (!StringUtils.hasText(f_sourced)) {
                            throw new Exception(f_sys_name_old+"点位节点表格内容不完整");
                        }
                    }

                    f_sys_name = (String) pointMap.get("F_SYS_NAME");
                    String f_sbid = (String) pointMap.get("F_SBID");
                    besPoint.setfSysName(f_sys_name);
                    besPoint.setfNodeType(f_type);

//                    Boolean updateStruct = besSbdyExcelTableImportMapper.updateStructPoint(f_idBySbdyStruct,f_sys_name_old,f_nick_name,allpath,f_description,f_type);


//                    if (updateStruct) {

                        //产业园临时用
                        Boolean aaa = besSbdyExcelTableImportMapper.updateStructPointCopy(f_idBySbdyStruct,f_sys_name_old,
                                f_nick_name,allpath,f_description,f_type);

                        if (aaa) {
                            besPoint.setfPointState("0");
                            besPoint.setfSbid(f_sbid);
                            besPoint.setfStructId(String.valueOf(f_idBySbdyStruct));
                            //添加点位信息到相应的节点表中
                            Boolean insertPointMapToNodeTable = besSbdyExcelTableImportMapper.insertPointMapToNodeTable_DO_DI(tabName,besPoint);

                            if (insertPointMapToNodeTable) {
                                returnObject.setMsg("导入成功！");
                                returnObject.setStatus("1");
                            } else {
                                throw new Exception("导入失败");
                            }
                        }

//                    }
                }
            }
//			}
            if (Has_it_been_added) {
                throw new Exception(f_sys_name_old +":此点位已添加");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }

    /**
     *
     * @Description: Excel AO,AI点位新增
     *
     * @auther: wanghongjie
     * @date: 14:26 2020/9/15
     * @param: [besPoint, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertAO_AIPoint(besPointExcel besPoint, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Integer f_idBySbdyStruct ;
        String allpath;
        String f_sys_name;
        String f_type = null;
        String tabName = null;
        String f_energystatics = null;
        Boolean Has_it_been_added = true;//此点位是否已添加,默认已添加

        try {
            //判断当前点位的通道索引是否符合模块的模块型号,首先查出模块的点集合
            Map<String,Object> moduleType = besSbdyExcelTableImportMapper.selectF_POINT_TYPE_CL(sbdyStructs.getfPsysname());

            //模块的点数
            String F_POINT_TYPE_CL = (String) moduleType.get("F_POINT_TYPE_CL");

            //模块型号
            String moduleName = (String) moduleType.get("F_MODULE_TYPE");

            if ("3路计量模块".equals(moduleName) || "7路计量模块".equals(moduleName) || "11路计量模块".equals(moduleName)) {
                besPoint.setfEnabled("1");
                besPoint.setfWorkMode("1");
                besPoint.setfReversed("0");
                besPoint.setfInitVal("0");
                besPoint.setfSinnalType("0");
                besPoint.setfEngineerUnit("K");
                besPoint.setfMaxVal("1000000");
                besPoint.setfMinVal("0");
                besPoint.setfAccuracy("2");
                besPoint.setfAlarmEnable("0");
                besPoint.setfYqbh("0000");
            }

            String f_alarm_type;
            String f_close_state;
            String f_alarm_priority;
            String f_high_limit;
            String f_low_limit;


            String f_sys_name_old 	= besPoint.getfSysNameOld();
            String f_nick_name 		= besPoint.getfNickName();
            String f_enabled 		= besPoint.getfEnabled();
            String f_channel_index 	= besPoint.getfChannelIndex();
            String f_engineer_unit 	= besPoint.getfEngineerUnit();
            String f_sinnal_type	= besPoint.getfSinnalType();
            String f_max_val		= besPoint.getfMaxVal();
            String f_min_val		= besPoint.getfMinVal();
            String f_accuracy		= besPoint.getfAccuracy();
            String f_reversed 		= besPoint.getfReversed();
            String f_work_mode 		= besPoint.getfWorkMode();
            String f_init_val 		= besPoint.getfInitVal();
            String f_description 	= besPoint.getfDescription();
            String yqbh				= besPoint.getfYqbh();
            String f_alarm_enable 	= besPoint.getfAlarmEnable();

            if (!f_alarm_enable.equals("0")) {
                f_alarm_type 	= besPoint.getfAlarmType();
                f_close_state 	= besPoint.getfCloseState();
                f_alarm_priority = besPoint.getfAlarmPriority();
                f_high_limit    = besPoint.getfHighLimit();
                f_low_limit		= besPoint.getfLowLimit();
            } else {//如果excel导入的DO点位报警不使能,则将报警类型,闭合状态,报警等级,故障状态设置一个默认值
                f_alarm_type 	= "0";//0:	不报警  1:	标准报警  2:	增强报警
                besPoint.setfAlarmType(f_alarm_type);
                f_close_state 	= "0";//0:	闭合报警 1:	断开报警
                besPoint.setfCloseState(f_close_state);
                f_alarm_priority = "0";//0:	一般 1:	较大 2:	重大
                besPoint.setfAlarmPriority(f_alarm_priority);
                f_high_limit = "100";
                besPoint.setfHighLimit(f_high_limit);
                f_low_limit = "0";
                besPoint.setfLowLimit(f_low_limit);

            }

            if (       !StringUtils.hasText(f_sys_name_old)
                    || !StringUtils.hasText(f_nick_name)
                    || !StringUtils.hasText(f_enabled)
                    || !StringUtils.hasText(f_channel_index)
                    || !StringUtils.hasText(f_reversed)
                    || !StringUtils.hasText(f_engineer_unit)
                    || !StringUtils.hasText(f_work_mode)
                    || !StringUtils.hasText(f_init_val)
                    || !StringUtils.hasText(f_description)
                    || !StringUtils.hasText(yqbh)
                    || !StringUtils.hasText(f_alarm_enable)
                    || !StringUtils.hasText(f_alarm_type)
                    || !StringUtils.hasText(f_close_state)
                    || !StringUtils.hasText(f_alarm_priority)
                    || !StringUtils.hasText(f_high_limit)
                    || !StringUtils.hasText(f_low_limit)
                    || !StringUtils.hasText(f_sinnal_type)
                    || !StringUtils.hasText(f_max_val)
                    || !StringUtils.hasText(f_min_val)
                    || !StringUtils.hasText(f_accuracy)
            )
            {
                throw new Exception(f_sys_name_old+":点位节点表格内容不完整");
            }

            //修改自动生成的点位的系统名称以及别名和点类型,首先查询当前模块下所有的点位
            List<Map<String,Object>> module_pointList = besSbdyExcelTableImportMapper.selectModule_pointList(sbdyStructs.getfPsysname());

            String name = (String) module_pointList.get(Integer.parseInt(f_channel_index)).get("f_sys_name_old");
//			for (int i = 0;i < module_pointList.size(); i++) {

//				String name = (String) module_pointList.get(i).get("f_sys_name_old");

            if (name.contains(sbdyStructs.getfPsysname())){

                if (Integer.valueOf(name.replace(sbdyStructs.getfPsysname(),"")).equals(Integer.valueOf(f_channel_index))) {

                    Has_it_been_added = false;//此点位未添加

                    //修改设备配置的点位信息,首先查询当前点位在设备配置表的信息,目的是查出F_ID以及F_ALLPATH
//                    String tabNameSbdy =  "bes_sbpz_struct";
                    String tabNameSbdy =  "bes_sbpz_struct_copy";
                    Map<String,Object> pointMap = besSbdyExcelTableImportMapper.selectPointMap(tabNameSbdy,name);
                    f_idBySbdyStruct = (Integer) pointMap.get("F_ID");
                    allpath = (String) pointMap.get("F_ALLPATH");

//                    //判断当前点位的通道索引是否符合模块的模块型号,首先查出模块的点集合
//                    Map<String,Object> moduleType = besSbdyExcelTableImportMapper.selectF_POINT_TYPE_CL(sbdyStructs.getfPsysname());
//
//                    //模块的点数
//                    String F_POINT_TYPE_CL = (String) moduleType.get("F_POINT_TYPE_CL");
//
//                    //模块型号
//                    String moduleName = (String) moduleType.get("F_MODULE_TYPE");

                    //通道索引
                    Integer channel_index = Integer.parseInt(f_channel_index);

                    if ("3路计量模块".equals(moduleName)) {

                        if (channel_index >= 3 && channel_index <= 5) {
                            f_nick_name = f_nick_name + "累计能耗";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("kwh");
                        } else if (channel_index >= 6 && channel_index <= 8) {
                            f_nick_name = f_nick_name + "功率";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("W");

                        }
                    } else if ("7路计量模块".equals(moduleName)) {

                        if (channel_index >= 7 && channel_index <= 13) {
                            f_nick_name = f_nick_name + "累计能耗";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("kwh");
                        } else if (channel_index >= 14 && channel_index <= 20) {
                            f_nick_name = f_nick_name + "功率";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("W");
                        }

                    } else if ("11路计量模块".equals(moduleName)) {

                        if (channel_index >= 11 && channel_index <= 21) {
                            f_nick_name = f_nick_name + "累计能耗";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("kwh");
                        } else if (channel_index >= 22 && channel_index <= 32) {
                            f_nick_name = f_nick_name + "功率";
                            besPoint.setfNickName(f_nick_name);
                            besPoint.setfDescription(f_nick_name);
                            besPoint.setfEngineerUnit("W");
                        }
                    }

                    final String substring = F_POINT_TYPE_CL.substring(Integer.parseInt(f_channel_index), Integer.parseInt(f_channel_index) + 1);
                    if (sbdyStructs.getfType().equals("AO")) {
                        if (!substring.equals("1")) {//DO点
                            throw new Exception(f_sys_name_old+"点位的通道索引不符合模块的模块型号");
                        }
                    }
                    if (sbdyStructs.getfType().equals("AI")) {
                        if (!substring.equals("4") && !substring.equals("0")) {//UI点
                            throw new Exception(f_sys_name_old+"点位的通道索引不符合模块的模块型号");
                        }
                    }

                    if (besPoint.getPointTypeName().equals("AO")) {
                        f_type = "11";
                        tabName = "BES_ANALOG_OUPUT";
                        allpath = allpath.replace(">AO类型",">"+f_sys_name_old);

                    } else if (besPoint.getPointTypeName().equals("AI")) {

                        f_type = "10";
                        tabName = "BES_ANALOG_INPUT";
                        if (pointMap.get("F_TYPE").equals(10)) {
                            allpath = allpath.replace(">AI类型",">"+f_sys_name_old);
                        } else if (pointMap.get("F_TYPE").equals(14)) {
                            allpath = allpath.replace(">UI类型",">"+f_sys_name_old);
                        }

                        String fEnergystatics = besPoint.getfEnergystatics();//获取能耗统计字段值

                        if (StringUtils.hasText(fEnergystatics)) {
                            if ("1".equals(fEnergystatics)){//是
                                f_energystatics = "0";
                            } else if ("0".equals(fEnergystatics)) {//否
                                f_energystatics = "1";
                            }
                        } else {
                            f_energystatics = "1";//能耗统计（0:是；1:否）
                        }

                        besPoint.setfEnergystatics(f_energystatics);

                    }

                    f_sys_name = (String) pointMap.get("F_SYS_NAME");
                    String f_sbid = (String) pointMap.get("F_SBID");
                    besPoint.setfSysName(f_sys_name);
                    besPoint.setfNodeType(f_type);

//                    Boolean updateStruct = besSbdyExcelTableImportMapper.updateStructPoint(f_idBySbdyStruct,f_sys_name_old,f_nick_name,allpath,f_description,f_type);

//                    if (updateStruct) {

                        //产业园临时用
                        Boolean aaa = besSbdyExcelTableImportMapper.updateStructPointCopy(f_idBySbdyStruct,f_sys_name_old,
                                f_nick_name,allpath,f_description,f_type);
                        if (aaa) {
                            besPoint.setfPointState("0");
                            besPoint.setfSbid(f_sbid);
                            besPoint.setfStructId(String.valueOf(f_idBySbdyStruct));
                            //添加点位信息到相应的节点表中
                            Boolean insertPointMapToNodeTable = besSbdyExcelTableImportMapper.insertPointMapToNodeTable_AO_AI(tabName,besPoint);

                            if (insertPointMapToNodeTable) {
                                returnObject.setMsg("导入成功！");
                                returnObject.setStatus("1");
                            } else {
                                throw new Exception("导入失败");
                            }
                        }


//                    }
                }
            }
//			}

            if (Has_it_been_added) {
                throw new Exception(f_sys_name_old +":此点位已添加");
            }

        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }


    /**
     *
     * @Description: Excel 虚点DO,DI点位新增
     *
     * @auther: wanghongjie
     * @date: 11:54 2020/9/16
     * @param: [besPoint, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertDO_DIVPoint(besPointExcel besPoint, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        String nodeTabName = "点位节点表";

        try {
            String f_alarm_type;
            String f_close_state;
            String f_alarm_priority;
            String f_fault_state;

            String f_sys_name_old 	= besPoint.getfSysNameOld();
            String f_nick_name 		= besPoint.getfNickName();
            String f_enabled 		= besPoint.getfEnabled();
            String f_init_val 		= besPoint.getfInitVal();
            String f_description 	= besPoint.getfDescription();
            String yqbh				= besPoint.getfYqbh();
            String f_alarm_enable 	= besPoint.getfAlarmEnable();

            if (!f_alarm_enable.equals("0")) {
                f_alarm_type 	= besPoint.getfAlarmType();
                f_close_state 	= besPoint.getfCloseState();
                f_alarm_priority = besPoint.getfAlarmPriority();
                f_fault_state    = besPoint.getfFaultState();
            } else {//如果excel导入的DO点位报警不使能,则将报警类型,闭合状态,报警等级,故障状态设置一个默认值
                f_alarm_type 	= "0";//0:	不报警  1:	标准报警  2:	增强报警
                besPoint.setfAlarmType(f_alarm_type);
                f_close_state 	= "0";//0:	闭合报警 1:	断开报警
                besPoint.setfCloseState(f_close_state);
                f_alarm_priority = "0";//0:	一般 1:	较大 2:	重大
                besPoint.setfAlarmPriority(f_alarm_priority);
                f_fault_state    = "0";//0:	不启用 1:	启用
                besPoint.setfFaultState(f_fault_state);

            }

            if (besPoint.getfFaultState() == null) {//如果报警是使能状态,并且故障状态未设置,则将故障状态设置成不启用
                f_fault_state    = "0";//0:	不启用 1:	启用
                besPoint.setfFaultState(f_fault_state);
            }

            if (       !StringUtils.hasText(f_sys_name_old)
                    || !StringUtils.hasText(f_nick_name)
                    || !StringUtils.hasText(f_enabled)
                    || !StringUtils.hasText(f_init_val)
                    || !StringUtils.hasText(f_description)
                    || !StringUtils.hasText(yqbh)
                    || !StringUtils.hasText(f_alarm_enable)
                    || !StringUtils.hasText(f_alarm_type)
                    || !StringUtils.hasText(f_close_state)
                    || !StringUtils.hasText(f_alarm_priority)
                    || !StringUtils.hasText(f_fault_state)
            )
            {
                throw new Exception(f_sys_name_old +":点位节点表格内容不完整");
            }

            if (sbdyStructs.getfType().equals("DO") || sbdyStructs.getfType().equals("DI")) {
                sbdyStructs.setfType("16");
            }
            returnObject = insertBesStruct(sbdyStructs,nodeTabName);
            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                return returnObject;
            }


            if (sbdyStructs.getfSysname().equals(f_sys_name_old)) {

                //查询系统名称在设备配置表中是否存在
                if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name_old)) {
                    throw new Exception(f_sys_name_old + ":系统名称在节点表中不存在");
                }

            }
            //查询当前点位在设备配置表的信息,目的是查出F_ID
//            String tabNameSbdy =  "bes_sbpz_struct";
            String tabNameSbdy =  "bes_sbpz_struct_copy";
            Map<String,Object> pointMap = besSbdyExcelTableImportMapper.selectPointMap(tabNameSbdy,f_sys_name_old);
            besPoint.setfSbid(String.valueOf(pointMap.get("F_ID")));
            besPoint.setfPointState("0");
            if (besPoint.getPointTypeName().equals("DO")) {
                besPoint.setfNodeType("7");
            } else if (besPoint.getPointTypeName().equals("DI")) {
                besPoint.setfNodeType("6");
            }

            //添加虚点DO,DI点位信息到虚点表
            Boolean insertVPointMapToNodeTable = besSbdyExcelTableImportMapper.insertVPointMapToNodeTable_DO_DI(besPoint);
            if (insertVPointMapToNodeTable) {
                returnObject.setMsg("导入成功！");
                returnObject.setStatus("1");
            } else {
                throw new Exception("导入失败");
            }


        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }

    /**
     *
     * @Description: Excel 虚点AO,AI点位新增
     *
     * @auther: wanghongjie
     * @date: 11:54 2020/9/16
     * @param: [besPoint, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject insertAO_AIVPoint(besPointExcel besPoint, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        String nodeTabName = "点位节点表";
        try {
            String f_alarm_type;
            String f_close_state;
            String f_alarm_priority;
            String f_high_limit;
            String f_low_limit;

            String f_sys_name_old 	= besPoint.getfSysNameOld();
            String f_nick_name 		= besPoint.getfNickName();
            String f_enabled 		= besPoint.getfEnabled();
            String f_init_val 		= besPoint.getfInitVal();
            String f_description 	= besPoint.getfDescription();
            String yqbh				= besPoint.getfYqbh();
            String f_alarm_enable 	= besPoint.getfAlarmEnable();
            String f_engineer_unit 	= besPoint.getfEngineerUnit();
            String f_accuracy		= besPoint.getfAccuracy();

            if (!f_alarm_enable.equals("0")) {
                f_alarm_type 	= besPoint.getfAlarmType();
                f_close_state 	= besPoint.getfCloseState();
                f_alarm_priority = besPoint.getfAlarmPriority();
                f_high_limit    = besPoint.getfHighLimit();
                f_low_limit		= besPoint.getfLowLimit();
            } else {//如果excel导入的DO点位报警不使能,则将报警类型,闭合状态,报警等级,故障状态设置一个默认值
                f_alarm_type 	= "0";//0:	不报警  1:	标准报警  2:	增强报警
                besPoint.setfAlarmType(f_alarm_type);
                f_close_state 	= "0";//0:	闭合报警 1:	断开报警
                besPoint.setfCloseState(f_close_state);
                f_alarm_priority = "0";//0:	一般 1:	较大 2:	重大
                besPoint.setfAlarmPriority(f_alarm_priority);
            }

            if (       !StringUtils.hasText(f_sys_name_old)
                    || !StringUtils.hasText(f_nick_name)
                    || !StringUtils.hasText(f_enabled)
                    || !StringUtils.hasText(f_init_val)
                    || !StringUtils.hasText(f_description)
                    || !StringUtils.hasText(yqbh)
                    || !StringUtils.hasText(f_alarm_enable)
                    || !StringUtils.hasText(f_alarm_type)
                    || !StringUtils.hasText(f_close_state)
                    || !StringUtils.hasText(f_alarm_priority)
                    || !StringUtils.hasText(f_engineer_unit)
                    || !StringUtils.hasText(f_accuracy)
            )
            {
                throw new Exception(f_sys_name_old + ":点位节点表格内容不完整");
            }

            if (sbdyStructs.getfType().equals("AO") || sbdyStructs.getfType().equals("AI")) {
                sbdyStructs.setfType("16");
            }
            returnObject = insertBesStruct(sbdyStructs,nodeTabName);

            if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                return returnObject;
            }

            if (sbdyStructs.getfSysname().equals(f_sys_name_old)) {

                //查询系统名称在设备配置表中是否存在
                if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name_old)) {
                    throw new Exception(f_sys_name_old + "系统名称在节点表中不存在");
                }

            }
            //查询当前点位在设备配置表的信息,目的是查出F_ID
//            String tabNameSbdy =  "bes_sbpz_struct";
            String tabNameSbdy =  "bes_sbpz_struct_copy";
            Map<String,Object> pointMap = besSbdyExcelTableImportMapper.selectPointMap(tabNameSbdy,f_sys_name_old);
            besPoint.setfSbid(String.valueOf(pointMap.get("F_ID")));
            besPoint.setfPointState("0");
            if (besPoint.getPointTypeName().equals("AO")) {
                besPoint.setfNodeType("5");
            } else if (besPoint.getPointTypeName().equals("AI")) {
                besPoint.setfNodeType("4");

                String fEnergystatics = besPoint.getfEnergystatics();//获取能耗统计字段值

                if (StringUtils.hasText(fEnergystatics)) {
                    if ("1".equals(fEnergystatics)){//是
                        besPoint.setfEnergystatics("0");
                    } else if ("0".equals(fEnergystatics)) {//否
                        besPoint.setfEnergystatics("1");
                    }
                } else {
                    besPoint.setfEnergystatics("1");//能耗统计（0:是；1:否）
                }
            }

            //添加虚点AO,AI点位信息到虚点表
            Boolean insertVPointMapToNodeTable = besSbdyExcelTableImportMapper.insertVPointMapToNodeTable_AO_AI(besPoint);
            if (insertVPointMapToNodeTable) {
                returnObject.setMsg("导入成功！");
                returnObject.setStatus("1");
            } else {
                throw new Exception("导入失败");
            }


        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }



    @Transactional(propagation = Propagation.NESTED)
    public ISSPReturnObject lightingControl(List<sbdyStruct> list, String fileUrl) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        // 获取要导出的数据
        List<ExcelError> excelErrors = new ArrayList<>();
        boolean inportflag = false;
        Map<String, Object> psysName = null;
        // 初始化导入工具类
        FileInputStream fis = null;
        //将ecxcel查出来的数据放到list里面,统一使用
        List<besPointExcel> pointList = null;
        List<besModuleExcel> Modulelist = null;
        List<besDDCExcel> DDClist= null;
        List<besCouplerExcel> couplerList = null;

        String nodeTabName = "照明节点表";

        try {

            excelErrors = nodeTable(list);

            /*if (excelErrors.size() > 0) {
                returnObject.setMsg("导入数据过程中出现失误，请查看excel错误报告！");
                returnObject.setStatus("2");
                returnObject.setList(excelErrors);
//						return returnObject;
                throw new Exception((Throwable) returnObject.getList());
            }*/

            if (excelErrors.size() > 0) {
                throw new Exception(nodeTabName + "第" + excelErrors.get(0).getRow() + "行" +excelErrors.get(0).getErrorMsg());
            }

            Set<String> point = new HashSet<>();
            for (sbdyStruct sbdyStructs : list) {
                //判断照明节点表中系统名称数据是否重复
                Boolean success = point.add(sbdyStructs.getfSysname());
                if (!success) {
                    throw new Exception(sbdyStructs.getfSysname() +":系统名称在节点表中重复");
                }
            }
            for (sbdyStruct sbdyStructs : list) {
                //判断照明控制表中系统名称数据是否重复
//                Boolean success = point.add(sbdyStructs.getfSysname());
//                if (!success) {
//                    throw new Exception(sbdyStructs.getfSysname() +":系统名称在节点表中重复");
//                }

                if (sbdyStructs.getfType() == null) {
                    throw new Exception(sbdyStructs.getfSysname() + ":节点表的节点类型不存在,请检查");
                }
                switch (sbdyStructs.getfType()) {
                    case "21":
                    case "24": {//楼宇控制节点,虚点(无属性页面)

                        returnObject = insertBesStruct(sbdyStructs,nodeTabName);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;
                    }
                    case "3": {//照明控制器节点

                        if (DDClist == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besDDCExcel> util = new ExcelUtil<>(besDDCExcel.class);
                            DDClist = util.importExcel("照明控制器节点", fis);// 导入excel,处理后生成list
                        }

                        returnObject = insertDDCByExcel(DDClist,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;

                    }
                    case "5" :
                    case "6" : {

                        if (couplerList == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besCouplerExcel> util = new ExcelUtil<>(besCouplerExcel.class);
                            couplerList = util.importExcel("耦合器节点", fis);// 导入excel,处理后生成list
                        }
                        returnObject = insertCouplerByExcel(couplerList,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }
                        break;
                    }
                    case "9":{//模块

                        if (Modulelist == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besModuleExcel> util = new ExcelUtil<>(besModuleExcel.class);
                            Modulelist = util.importExcel("模块节点", fis);// 导入excel,处理后生成list
                        }

                        returnObject = insertModuleByExcel(Modulelist,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return returnObject;
                        }

                        break;
                    }
                    case "DO":
                    case "DI":
                    case "AO":
                    case "AI": {

                        if (pointList == null) {
                            fis = new FileInputStream(fileUrl);
                            ExcelUtil<besPointExcel> util = new ExcelUtil<>(besPointExcel.class);
                            pointList = util.importExcel("点位节点", fis);// 导入excel,处理后生成list
                        }

                        List lists=new ArrayList();

                        Set<String> point_DODIAOAI = new HashSet<>();
                        for (besPointExcel besPointExcel : pointList) {
                            //判断总线表中系统名称数据是否重复
                            Boolean success1 = point_DODIAOAI.add(besPointExcel.getfSysNameOld());
                            if (!success1) {
                                throw new Exception(besPointExcel.getfSysNameOld() +":系统名称在点位节点表中重复");
                            }
                            lists.add(besPointExcel.getfSysNameOld());
                        }
                        Boolean aa = lists.contains(sbdyStructs.getfSysname());

                        if (!aa) {
                            throw new Exception(sbdyStructs.getfSysname() +"系统名称在点位节点表中不存在");
                        }

                        returnObject = pointInsertByExcel(pointList,sbdyStructs);

                        if (returnObject.getMsg() != null && returnObject.getMsg() != "导入成功！") {
                            return  returnObject;
                        }
                        break;
                    }
                    default:
                        throw new Exception("节点类型不存在");
                }
            }

        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }

    /**
     *
     * @Description: 添加耦合器节点
     *
     * @auther: wanghongjie
     * @date: 10:35 2020/9/17
     * @param: [couplerList, sbdyStructs]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    public ISSPReturnObject insertCouplerByExcel(List<besCouplerExcel> couplerList, sbdyStruct sbdyStructs) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Boolean inportflag = false;
        Map<String,Object> psysNameMap;

        String nodeTabName = "耦合器节点表";

        try {

            JSONObject obj = new JSONObject();
            List lists=new ArrayList();

            Set<String> point = new HashSet<>();
            for (besCouplerExcel besCouplerExcel : couplerList) {
                //判断总线表中系统名称数据是否重复
                Boolean success = point.add(besCouplerExcel.getfSysName());
                if (!success) {
                    throw new Exception(besCouplerExcel.getfSysName() +":系统名称在能耗节点表中重复");
                }
                lists.add(besCouplerExcel.getfSysName());
            }
            Boolean aa = lists.contains(sbdyStructs.getfSysname());

            if (!aa) {
                throw new Exception(sbdyStructs.getfSysname() +"系统名称在耦合器节点表中不存在");
            }

            returnObject = insertBesStruct(sbdyStructs,nodeTabName);

            for (besCouplerExcel besCouplerExcel : couplerList) {

                String f_sys_name 		= besCouplerExcel.getfSysName();
                String f_nick_name 		= besCouplerExcel.getfNickName();
                String f_azwz 			= besCouplerExcel.getfAzwz();
                String f_description 	= besCouplerExcel.getfDescription();
                String f_node_type 		= besCouplerExcel.getfNodeType();
                String f_addr 			= besCouplerExcel.getfAddr();
                String f_belong_iprouter= besCouplerExcel.getfBelongIprouter();

                if (       !StringUtils.hasText(f_sys_name)
                        || !StringUtils.hasText(f_nick_name)
                        || !StringUtils.hasText(f_azwz)
                        || !StringUtils.hasText(f_description)
                        || !StringUtils.hasText(f_node_type)
                        || !StringUtils.hasText(f_addr)
                        || !StringUtils.hasText(f_belong_iprouter)
                )
                {
                    throw new Exception(f_sys_name + ":耦合器节点表格内容不完整");
                }

                if (sbdyStructs.getfSysname().equals(f_sys_name)) {

                    //查询系统名称在设备配置表中是否存在
                    if (null == besSbdyExcelTableImportMapper.getSbTreeInfoBySysName(f_sys_name)) {
                        throw new Exception(f_sys_name + "系统名称在节点表中不存在");
                    }

                    //根据耦合器节点点位的父节点名称查询父节点下所有的点位
                    List<Map<String,Object>> lightCoupler= besSbdyExcelTableImportMapper.selectLightCouplerList(sbdyStructs.getfPsysname());

                    psysNameMap = besSbdyExcelTableImportMapper.selectSbdyByPsysName(sbdyStructs.getfPsysname());
                    //如果当前节点是干线耦合器,则父节点只能是DDC控制器
                    if (sbdyStructs.getfType().equals("5")) {
                        if (!psysNameMap.get("F_TYPE").equals(3)) {
                            throw new Exception(f_sys_name + ":干线耦合器不在DDC控制器下");
                        }

                        if (lightCoupler.size() >0) {//如果父节点下有点位
                            if (sbdyStructs.getfType() != String.valueOf(lightCoupler.get(0).get("F_TYPE"))) {//如果当前点位的类型和父节点下点位的类型不同
                                if (lightCoupler.get(0).get("F_TYPE").equals(6)) {//支线耦合器
                                    throw new Exception(f_sys_name + ":节点所属的DDC控制器已添加支线耦合器,不能再添加干线耦合器");
                                }else if (lightCoupler.get(0).get("F_TYPE").equals(9)) {//模块
                                    throw new Exception(f_sys_name + ":节点所属的DDC控制器已添加模块节点,不能再添加干线耦合器");
                                }
                            }
                        }
                    }
                    //如果当前节点是支线耦合器,则父节点可以是干线耦合器或者DDC控制器节点
                    if (sbdyStructs.getfType().equals("6")) {
                        if (!psysNameMap.get("F_TYPE").equals(3) && !psysNameMap.get("F_TYPE").equals(5)) {
                            throw new Exception(f_sys_name + ":支线耦合器不在DDC控制器下或者干线耦合器下");
                        }

                        if (lightCoupler.size() >0) {//如果父节点下有点位
                            if (sbdyStructs.getfType() != String.valueOf(lightCoupler.get(0).get("F_TYPE"))) {//如果当前点位的类型和父节点下点位的类型不同
                                if (lightCoupler.get(0).get("F_TYPE").equals(5)) {//干线耦合器
                                    throw new Exception(f_sys_name + ":节点所属的DDC控制器已添加干线耦合器,不能再添加支线耦合器");
                                }else if (lightCoupler.get(0).get("F_TYPE").equals(9)) {//模块
                                    throw new Exception(f_sys_name + ":节点所属的DDC控制器已添加模块节点,不能再添加支线耦合器");
                                }
                            }
                        }
                    }

                    besCouplerExcel.setfType(sbdyStructs.getfType());

                    inportflag = besSbdyExcelTableImportMapper.add_sbdyStructCoupler(besCouplerExcel);
                }
            }

            if (inportflag) {
                returnObject.setMsg("导入成功！");
                returnObject.setStatus("1");
            } else {
                throw new Exception("导入失败");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
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
     * 添加默认节点(根据模块类型)
     * @param obj
     * @return
     */
    private int addDefaultNodes(JSONObject obj){
        List<BESModulePointType> modulePointTypeList = besSbdyMapper.getModulePointTypeInfo();//获取模块点类型信息
        Map<String, String > mPointTypeMap = new HashMap<>();//(key:ID value:模块点类型)
        for(BESModulePointType mPointType : modulePointTypeList){
            mPointTypeMap.put(mPointType.getfId(), mPointType.getfModulepointType());
        }
        List<BESEpModuleTypeRlgl> epNoduleTypeList = besSbdyMapper.getEpModuleTypeRlglInfo();//获取设备树节点和模块点类型信息
        Map<String, String > epModuleTypeRlglMap = new HashMap<>();//(key:ID value:设备树节点类型)
        for(BESEpModuleTypeRlgl epModuleTypeRlgl : epNoduleTypeList){
            epModuleTypeRlglMap.put(epModuleTypeRlgl.getfModulepointId(), epModuleTypeRlgl.getfEpTreenodeType());
        }
        String pFsysName = obj.getString("attr_f_sys_name");
        char[] nodeTypes = obj.getString("other_node_types").toCharArray();
        List<BESSbPzStruct> nodeList = new ArrayList<>();
        List<BESSbPzStruct> nodeList1 = new ArrayList<>();
        //取模块id，加1为第一个逻辑点的id
//        int f_sbid = obj.getInteger("f_sbid") + 1;
        for(int i=0;i<nodeTypes.length;i++){

            String nodeName = mPointTypeMap.get(String.valueOf(nodeTypes[i]));

            BESSbPzStruct sbPzStruct = new BESSbPzStruct();
            BESSbPzStruct sbPzStruct1 = new BESSbPzStruct();
//            sbPzStruct.setF_sbid(String.valueOf(f_sbid));
            sbPzStruct.setF_sys_name(pFsysName+"0"+String.valueOf(i));
            sbPzStruct.setF_psys_name(pFsysName);
            sbPzStruct.setF_nick_name(nodeName);
            sbPzStruct.setF_allpath(obj.getString("f_allpath")+">"+nodeName);
            sbPzStruct.setF_type(epModuleTypeRlglMap.get(String.valueOf(nodeTypes[i])));
            sbPzStruct.setF_node_attribution(obj.getString("f_node_attribution"));
            sbPzStruct.setF_status("0");

            sbPzStruct1.setF_sys_name(pFsysName+"0"+String.valueOf(i));
            sbPzStruct1.setF_psys_name(pFsysName);
            sbPzStruct1.setF_nick_name(nodeName);
            sbPzStruct1.setF_allpath(obj.getString("f_allpath")+">"+nodeName);
            sbPzStruct1.setF_type(epModuleTypeRlglMap.get(String.valueOf(nodeTypes[i])));
            sbPzStruct1.setF_node_attribution(obj.getString("f_node_attribution"));
            sbPzStruct1.setF_status("0");
            nodeList.add(sbPzStruct);
            nodeList1.add(sbPzStruct1);
//            f_sbid++;
        }

        int insertCount = besSbdyMapper.batchInsert(nodeList);
        if (insertCount > 0) {
            besSbdyMapper.batchInsertCopy(nodeList1);
        }


        return insertCount;
    }

    private int getSbid(String f_sys_name) {
        int maxSbid = 1;
        int count = besSbdyMapper.getSumSbCount(f_sys_name);
        if(count>0){
            maxSbid = count;
        }
        return maxSbid;
    }

    /**
     *
     * @Description: DDC控制器添加完毕后默认添加虚点,总线和线路
     *
     * @auther: wanghongjie
     * @date: 9:59 2020/9/12
     * @param: [f_sys_name]
     * @return: void
     *
     */
    /*@Transactional(propagation = Propagation.NESTED)
    public  ISSPReturnObject insertVirtualPoint_bus_line(String fSysName, String path, String f_node_type) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Boolean addVPoint ;
        Boolean addBus ;
        Boolean addLine = false;
        try {

            BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
            //虚点节点
            String vPointNodeNoPage = fSysName + "01";
            //总线节点
            String busNode = fSysName + "02";
            //线路节点
            String pnp = busNode + "01";
            String fln1 = busNode + "02";
            String fln2 = busNode + "03";
            String fln3 = busNode + "04";
            String fln4 = busNode + "05";

            List lineList = new ArrayList();
            lineList.add(pnp);
            lineList.add(fln1);
            lineList.add(fln2);
            lineList.add(fln3);
            lineList.add(fln4);


            besSbPzStruct.setF_poll_status("1");
            besSbPzStruct.setF_status("0");
            besSbPzStruct.setF_node_attribution(f_node_type);

            //首先添加虚点无属性页面
            besSbPzStruct.setF_sys_name(vPointNodeNoPage);
            besSbPzStruct.setF_nick_name("虚点");
            besSbPzStruct.setF_allpath(path + ">虚点");
            besSbPzStruct.setF_type("24");
            besSbPzStruct.setF_psys_name(fSysName);

            //查询系统名称在设备配置表中是否存在
            if (null != besSbdyMapper.getSbTreeInfoBySysName(vPointNodeNoPage)) {
                throw new Exception("默认添加的虚点节点系统名称已存在");
            }

            addVPoint = besSbdyMapper.add_sbdyStruct(besSbPzStruct);
            if (addVPoint) {
                besSbPzStruct.setF_sys_name(busNode);
                besSbPzStruct.setF_nick_name("总线");
                besSbPzStruct.setF_allpath(path + ">总线");
                besSbPzStruct.setF_type("8");
                besSbPzStruct.setF_psys_name(fSysName);
                //查询系统名称在设备配置表中是否存在
                if (null != besSbdyMapper.getSbTreeInfoBySysName(busNode)) {
                    throw new Exception("默认添加的总线节点系统名称已存在");
                }

                addBus = besSbdyMapper.add_sbdyStruct(besSbPzStruct);

                if (addBus) {
                    besSbPzStruct.setF_type("23");
                    besSbPzStruct.setF_psys_name(busNode);
                    for (int i = 0;i < lineList.size(); i++) {
                        if (i == 0) {
                            besSbPzStruct.setF_sys_name(pnp);
                            besSbPzStruct.setF_nick_name("PNP");
                            besSbPzStruct.setF_allpath(path + ">PNP");
                            //查询系统名称在设备配置表中是否存在
                            if (null != besSbdyMapper.getSbTreeInfoBySysName(pnp)) {
                                throw new Exception("默认添加的PNP节点系统名称已存在");
                            }
                            addLine = besSbdyMapper.add_sbdyStruct(besSbPzStruct);
                        }else if (i == 1) {
                            besSbPzStruct.setF_sys_name(fln1);
                            besSbPzStruct.setF_nick_name("FLN1");
                            besSbPzStruct.setF_allpath(path + ">FLN1");
                            //查询系统名称在设备配置表中是否存在
                            if (null != besSbdyMapper.getSbTreeInfoBySysName(fln1)) {
                                throw new Exception("默认添加的FLN1节点系统名称已存在");
                            }
                            addLine = besSbdyMapper.add_sbdyStruct(besSbPzStruct);
                        }else if (i == 2) {
                            besSbPzStruct.setF_sys_name(fln2);
                            besSbPzStruct.setF_nick_name("FLN2");
                            besSbPzStruct.setF_allpath(path + ">FLN2");
                            //查询系统名称在设备配置表中是否存在
                            if (null != besSbdyMapper.getSbTreeInfoBySysName(fln2)) {
                                throw new Exception("默认添加的FLN2节点系统名称已存在");
                            }
                            addLine = besSbdyMapper.add_sbdyStruct(besSbPzStruct);
                        }else if (i == 3) {
                            besSbPzStruct.setF_sys_name(fln3);
                            besSbPzStruct.setF_nick_name("FLN3");
                            besSbPzStruct.setF_allpath(path + ">FLN3");
                            //查询系统名称在设备配置表中是否存在
                            if (null != besSbdyMapper.getSbTreeInfoBySysName(fln3)) {
                                throw new Exception("默认添加的FLN3节点系统名称已存在");
                            }
                            addLine = besSbdyMapper.add_sbdyStruct(besSbPzStruct);
                        }else if (i == 4) {
                            besSbPzStruct.setF_sys_name(fln4);
                            besSbPzStruct.setF_nick_name("FLN4");
                            besSbPzStruct.setF_allpath(path + ">FLN4");
                            //查询系统名称在设备配置表中是否存在
                            if (null != besSbdyMapper.getSbTreeInfoBySysName(fln4)) {
                                throw new Exception("默认添加的FLN4节点系统名称已存在");
                            }
                            addLine = besSbdyMapper.add_sbdyStruct(besSbPzStruct);


                        }
                    }

                    if (addLine) {
                        returnObject.setMsg("导入成功");
                    }
                }
            }


        } catch (FileNotFoundException | NullPointerException e) {
            returnObject.setStatus("0");
            returnObject.setMsg("模板错误！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return returnObject;
    }*/
}
