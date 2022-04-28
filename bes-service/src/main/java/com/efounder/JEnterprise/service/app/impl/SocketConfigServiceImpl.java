package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.SocketConfigMapper;
import com.efounder.JEnterprise.model.app.SocketConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESSocket;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.SocketConfigService;
import com.framework.common.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * describe: 插座配置
 *
 * @author wzx
 * @date 2020年9月24日14:37:46
 */
@Service
public class SocketConfigServiceImpl implements SocketConfigService {

    @Resource
    private SocketConfigMapper socketConfigMapper;

    @Autowired
    Pzlj pzlj;

    @Autowired
    AoPointCache aoPointCache;

    @Autowired
    AiPointCache aiPointCache;

    @Autowired
    DoPointCache doPointCache;

    @Autowired
    DiPointCache diPointCache;

    /**
     * 查询插座配置列表
     *
     * @param map
     * @return
     */
    @Override
    public Map querySocketConfigList(Map map,String pageSize,String pageNumber) {
        PageHelper.startPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        List<SocketConfigModel> list = socketConfigMapper.querySocketConfigList(map);
        PageInfo<SocketConfigModel> page = new PageInfo<>(list);
        Map result = new HashMap();
        result.put("rows",list);
        result.put("total",page.getTotal());
        return result;
    }

    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageNumber
     * @param map
     * @return
     */
    @Override
    public PageInfo<SocketConfigModel> getPaging(Integer pageSize, Integer pageNumber, Map map) {
        if (pageNumber == null){
            pageNumber = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNumber, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<SocketConfigModel> list = socketConfigMapper.querySocketConfigList(map);
        // 用PageInfo对结果进行包装
        PageInfo<SocketConfigModel> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean delete(String id) {
        Integer num = socketConfigMapper.delete(id);
        return num == 1;
    }

    /**
     * 修改
     *
     * @param socketConfig
     * @return
     */
    @Override
    public boolean update(SocketConfigModel socketConfig) {
        Integer num = socketConfigMapper.update(socketConfig);
        return num == 1;
    }

    /**
     * 新增
     *
     * @param socketConfig
     * @return
     */
    @Override
    public boolean insert(SocketConfigModel socketConfig) {
        Integer num = socketConfigMapper.insert(socketConfig);
        return num == 1;
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public SocketConfigModel queryOne(String id) {
        List<SocketConfigModel> list = socketConfigMapper.queryOne(id);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 文件上传后台接收方法
     */
    @Override
    public ISSPReturnObject impExcel(HttpServletRequest request,
                                     @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException {
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
                    ExcelUtil<BESSocket> util = new ExcelUtil<>(BESSocket.class);
                    List<BESSocket> list = util.importExcel("插座配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESSocket besSocket = list.get(i);
                        String errMsg = "";
                        //判断插座名称
                        if(besSocket.getName() == null || besSocket.getName().equals("")) {
                            errMsg = "插座名称为空,";
                            flag = false;
                        }
                        //判断插座地址
                        if(besSocket.getSocketAddress() == null || besSocket.getSocketAddress().equals("")) {
                            errMsg += "插座地址为空,";
                            flag = false;
                        }
                        //判断插座开关
                        if (besSocket.getSocketSwitch() != null && !"".equals(besSocket.getSocketSwitch())){
                            //插座开关必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besSocket.getSocketSwitch());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besSocket.getSocketSwitch());
                                if (besAoPoint == null){
                                    errMsg += "插座开关点位错误,";
                                    flag = false;
                                } else {
                                    besSocket.setSocketSwitch(besAoPoint.getfSysName());
                                    besSocket.setSocketSwitchDisplay(besAoPoint.getfNickName());
                                }
                            } else {
                                besSocket.setSocketSwitch(besDoPoint.getfSysName());
                                besSocket.setSocketSwitchDisplay(besDoPoint.getfNickName());
                            }
                        }
                        //判断能耗值
                        if (besSocket.getEnergyConsumptionValue() != null && !"".equals(besSocket.getEnergyConsumptionValue())){
                            //能耗值必须为AI或DI
                            //从缓存中取出该点位
                            BesDiPoint besDiPoint = diPointCache.getCachedElementBySysNameOld(besSocket.getEnergyConsumptionValue());
                            if (besDiPoint == null){
                                BesAiPoint besAiPoint = aiPointCache.getCachedElementBySysNameOld(besSocket.getEnergyConsumptionValue());
                                if (besAiPoint == null){
                                    errMsg += "能耗值点位错误,";
                                    flag = false;
                                } else {
                                    besSocket.setEnergyConsumptionValue(besAiPoint.getF_sys_name());
                                    besSocket.setEnergyConsumptionValueDisplay(besAiPoint.getF_nick_name());
                                }
                            } else {
                                besSocket.setEnergyConsumptionValue(besDiPoint.getfSysName());
                                besSocket.setEnergyConsumptionValueDisplay(besDiPoint.getfNickName());
                            }
                        }


                        if (!flag) {
                            errMsg = errMsg.substring(0, errMsg.length() - 1);
                            ExcelError excelError = new ExcelError();
                            excelError.setRow((i+2)+"");
                            excelError.setErrorMsg(errMsg);
                            excelErrors.add(excelError);
                        }
                    }
                    if(excelErrors.size() > 0)
                    {
                        returnObject.setMsg("导入数据过程中出现失误，请查看excel错误报告！");
                        returnObject.setStatus("2");
                        returnObject.setList(excelErrors);
                        return returnObject;
                    }
                    else
                    {
                        boolean inportflag = false;
                        for (BESSocket besSocket :list) {
                            try {
                                inportflag = socketConfigMapper.insertSocket(besSocket);
                            } catch (Exception e) {
                                returnObject.setMsg("导入失败！");
                                returnObject.setStatus("0");
                                e.printStackTrace();
                            }
                        }
                        if(inportflag){
                            returnObject.setMsg("导入成功！");
                            returnObject.setStatus("1");
                        }else{
                            returnObject.setMsg("导入失败！");
                            returnObject.setStatus("0");
                        }
                    }
                }  catch (FileNotFoundException e) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("模板错误！");
                    e.printStackTrace();
                }
            }
        } else {
            returnObject.setMsg("导入数据失败！");
            returnObject.setStatus("0");
        }
        return returnObject;
    }


}
