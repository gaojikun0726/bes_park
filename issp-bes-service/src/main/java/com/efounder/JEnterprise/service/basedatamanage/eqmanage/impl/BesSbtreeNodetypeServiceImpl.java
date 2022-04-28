package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.SbTreeNodeTypeCache;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesSbtreeNodetypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesSbtreeNodetypeService;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("besSbtreeNodetypeService")
public class BesSbtreeNodetypeServiceImpl implements BesSbtreeNodetypeService {
    @Autowired
    private OperationConfig operationConfig;
    @Autowired
    private BesSbtreeNodetypeMapper besSbtreeNodetypeMapper;
    @Autowired
    private Pzlj pzlj;
    @Autowired
    private SbTreeNodeTypeCache sbTreeNodeTypeCache;

    private static final Logger log = LoggerFactory.getLogger(BesSbtreeNodetypeServiceImpl.class);


    @Override
    public PageInfo<BESSbTreeNodeType> selBesSbtreeNodetypepage(Integer pageNum, Integer bars, String keywords) {
        log.info("节点类型分页查询");
        if (pageNum == null)
            pageNum = 1;
        if (bars == null)
            bars = Constants.PAGE_SIZE;
        PageHelper.startPage(pageNum, bars);
        // 紧跟着的第一个select方法会被分页
        List<BESSbTreeNodeType> pageList = besSbtreeNodetypeMapper.selBesSbtreeNodetypepage(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<BESSbTreeNodeType> page = new PageInfo<BESSbTreeNodeType>(pageList);
        // 测试PageInfo全部属性
        // PageInfo包含了非常全面的分页属性
        log.info("# 查询默认数据库 page.toString()={}", page.toString());
        return page;
    }

    @Override
    public ISSPReturnObject insBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType) {
        log.info("添加节点类型信息");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            boolean isSucc = besSbtreeNodetypeMapper.insBesSbtreeNodetype(besSbTreeNodeType);
            if ("1".equals(operationConfig.getSysDataBaseUseable())) {
                OperationLog.insert(besSbTreeNodeType.getF_node_type(), "bes_sbtree_nodetype");
            }
            if (isSucc) {

                // 添加到缓存
                sbTreeNodeTypeCache.addOneSbTreeNodeTypeCache(besSbTreeNodeType);

                returnObject.setStatus("1");
                returnObject.setData(besSbTreeNodeType);
            } else {
                returnObject.setStatus("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus("0");
            returnObject.setMsg("该节点类型已存在，请重新输入");
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject delBesSbtreeNodetype(String f_node_type) {
        log.info("#删除节点类型信息");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            if ("1".equals(operationConfig.getSysDataBaseUseable())) {
                OperationLog.delete(f_node_type, "bes_sbtree_nodetype");
            }
            boolean flag = besSbtreeNodetypeMapper.delBesSbtreeNodetype(f_node_type);
            if (flag) {
                // 删除缓存
                sbTreeNodeTypeCache.deleteOneSbTreeNodeTypeCache(f_node_type);
                returnObject.setStatus("1");
                returnObject.setMsg("删除成功！");
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject selectBesSbtreeNodetype(String f_node_type) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            BESSbTreeNodeType selmo = besSbtreeNodetypeMapper.selectBesSbtreeNodetype(f_node_type);
            returnObject.setData(selmo);
            returnObject.setMsg("查询树节点类型成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("查询树节点类型失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject updateBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType) {
        log.info("更新树节点类型信息");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            Map<String, Object> startMap = null;
            boolean flag = besSbtreeNodetypeMapper.updateBesSbtreeNodetype(besSbTreeNodeType);
            if ("1".equals(operationConfig.getSysDataBaseUseable())) {
                startMap = OperationLog.updateStart(besSbTreeNodeType.getF_node_type(), "bes_sbtree_nodetype");
                OperationLog.updateEnd(besSbTreeNodeType.getF_node_type(), "bes_sbtree_nodetype", startMap);
            }
            if (flag) {
                // 更新缓存
                sbTreeNodeTypeCache.updateOneSbTreeNodeTypeCache(besSbTreeNodeType);
                returnObject.setData(besSbTreeNodeType);
                returnObject.setStatus("1");
                returnObject.setMsg("更新成功！");
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("修改失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnObject;
    }

    @Override
    public ISSPReturnObject impExcel(HttpServletRequest request, MultipartFile multipartFile) throws Exception {
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
                // 初始化存放数据list
                List<BESSbTreeNodeType> list = new ArrayList<>();
                List<ExcelError> errorList = new ArrayList<>();
                try {
                    fis = new FileInputStream(fileUrl);
                    ExcelUtil<BESSbTreeNodeType> util = new ExcelUtil<BESSbTreeNodeType>(BESSbTreeNodeType.class);
                    // 导入excel,处理后生成list
                    list = util.importExcel("sheet1", fis);
                    // 处理导入数据 判断验证
                    log.debug("\r 打印导入数据:" + list);
                    //实例化实体类list
                    List<BESSbTreeNodeType> BESSbTreeNodeTypeList = new ArrayList<>();
                    // 如果不需要验证 则可直接将list插入到数据库
                    for (int i = 0; i < list.size(); i++) {
                        // 初始化实体类
                        BESSbTreeNodeType bessbtreenodetype = list.get(i);
                        // 初始化存放错误信息实体类
                        ExcelError err = new ExcelError();
                        // 时间
                        String date = DateUtil.getCurrTime();
                        String fCrdate = date;
                        String fChdate = date;
                        bessbtreenodetype.setF_crdate(fCrdate);
                        bessbtreenodetype.setF_chdate(fChdate);
                        BESSbTreeNodeTypeList.add(bessbtreenodetype);
                        int count = besSbtreeNodetypeMapper.getCount(bessbtreenodetype.getF_node_type());
                        if (count > 0) {
                            err.setRow((i + 2) + "");
                            err.setErrorMsg("节点类型重复！");
                            errorList.add(err);
                        }
                    }
                    log.debug("newlist:" + BESSbTreeNodeTypeList);
                    log.debug("错误信息打印:" + errorList);
                    //判断错误信息
                    boolean flag = false;
                    if (errorList.isEmpty()) {
                        for (BESSbTreeNodeType map : BESSbTreeNodeTypeList) {
                            flag = besSbtreeNodetypeMapper.insBesSbtreeNodetype(map);

                            // 添加到缓存
                            if (flag)
                            {
                                sbTreeNodeTypeCache.addOneSbTreeNodeTypeCache(map);
                            }

                        }
                        if (flag) {
                            returnObject.setMsg("导入成功！");
                            returnObject.setStatus("1");
                        } else {
                            returnObject.setMsg("导入失败！");
                            returnObject.setStatus("0");
                        }
                    } else {
                        //将错误信息放到list返回到前台--只有status==2 才导出错误报告
                        returnObject.setStatus("2");
                        returnObject.setMsg("导入数据过程中出现错误，请查看excel错误报告！");
                        returnObject.setList(errorList);
                    }

                } catch (FileNotFoundException e) {
                    log.debug("模板错误！");
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    log.debug("模板错误！");
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                }
            }
        } else {
            returnObject.setMsg("文件上传失败！");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

}
