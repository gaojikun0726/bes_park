package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.AirconditioningConfigMapper;
import com.efounder.JEnterprise.model.app.AirconditioningConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAirconditioning;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.AirconditioningConfigService;
import com.framework.common.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 13:48:58
 */

@Service
public class AirconditioningConfigServiceImpl implements AirconditioningConfigService {

    @Resource
    private AirconditioningConfigMapper airconditioningConfigMapper;

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
     * 添加电表配置
     * @param airconditioningConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(AirconditioningConfigModel airconditioningConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = airconditioningConfigModel.getName();
        String  ktAddress = airconditioningConfigModel.getKtAddress();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(ktAddress)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            airconditioningConfigMapper.insert(airconditioningConfigModel);

            returnObject.setStatus("1");
            returnObject.setMsg("保存成功");

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("保存失败");

            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param airconditioningConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, AirconditioningConfigModel airconditioningConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = airconditioningConfigMapper.findList(airconditioningConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询电表配置信息
     * @param airconditioningConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(AirconditioningConfigModel airconditioningConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = airconditioningConfigMapper.findList(airconditioningConfigModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     * 更新电表配置信息
     * */
    @Override
    public ISSPReturnObject update(AirconditioningConfigModel airconditioningConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = airconditioningConfigModel.getId();
        String  name        = airconditioningConfigModel.getName();
        String  ktAddress = airconditioningConfigModel.getKtAddress();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(ktAddress)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        AirconditioningConfigModel acm = new AirconditioningConfigModel();
        acm.setId(id);

        try {
            List<Object> list = airconditioningConfigMapper.findList(acm);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            airconditioningConfigMapper.update(airconditioningConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除电表配置信息
     * @param airconditioningConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(AirconditioningConfigModel airconditioningConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == airconditioningConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = airconditioningConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        AirconditioningConfigModel chum = new AirconditioningConfigModel();
        chum.setId(id);

        try {
            List<Object> list = airconditioningConfigMapper.findList(chum);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }


        try {
            airconditioningConfigMapper.delete(id);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
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
                    ExcelUtil<BESAirconditioning> util = new ExcelUtil<>(BESAirconditioning.class);
                    List<BESAirconditioning> list = util.importExcel("空调接口配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESAirconditioning besAirconditioning = list.get(i);
                        String errMsg = "";
                        //判断空调名称
                        if(besAirconditioning.getName() == null || besAirconditioning.getName().equals(""))
                        {
                            errMsg = "空调名称为空,";
                            flag = false;
                        }
                        //判断空调位置
                        if(besAirconditioning.getAirconditioningAddress() == null
                                || besAirconditioning.getAirconditioningAddress().equals("")) {
                            errMsg += "空调位置为空,";
                            flag = false;
                        }
                        //判断空调模式
                        if (besAirconditioning.getAirconditioningModeSystem() != null && !"".equals(besAirconditioning.getAirconditioningModeSystem())){
                            //模式必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningModeSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningModeSystem());
                                if (besAoPoint == null){
                                    errMsg += "空调模式点位错误,";
                                    flag = false;
                                } else {
                                    besAirconditioning.setAirconditioningModeSystem(besAoPoint.getfSysName());
                                    besAirconditioning.setAirconditioningMode(besAoPoint.getfNickName());
                                }
                            } else {
                                besAirconditioning.setAirconditioningModeSystem(besDoPoint.getfSysName());
                                besAirconditioning.setAirconditioningMode(besDoPoint.getfNickName());
                            }
                        }
                        //判断空调风速
                        if (besAirconditioning.getAirconditioningWindspeedSystem() != null && !"".equals(besAirconditioning.getAirconditioningWindspeedSystem())){
                            //风速必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningWindspeedSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningModeSystem());
                                if (besAoPoint == null){
                                    errMsg += "空调风速点位错误,";
                                    flag = false;
                                } else {
                                    besAirconditioning.setAirconditioningWindspeedSystem(besAoPoint.getfSysName());
                                    besAirconditioning.setAirconditioningWindspeed(besAoPoint.getfNickName());
                                }
                            } else {
                                besAirconditioning.setAirconditioningWindspeedSystem(besDoPoint.getfSysName());
                                besAirconditioning.setAirconditioningWindspeed(besDoPoint.getfNickName());
                            }
                        }
                        //判断空调开关
                        if (besAirconditioning.getAirconditioningSwitchSystem() != null && !"".equals(besAirconditioning.getAirconditioningSwitchSystem())){
                            //开关必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningSwitchSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningSwitchSystem());
                                if (besAoPoint == null){
                                    errMsg += "空调开关点位错误,";
                                    flag = false;
                                } else {
                                    besAirconditioning.setAirconditioningSwitchSystem(besAoPoint.getfSysName());
                                    besAirconditioning.setAirconditioningSwitch(besAoPoint.getfNickName());
                                }
                            } else {
                                besAirconditioning.setAirconditioningSwitchSystem(besDoPoint.getfSysName());
                                besAirconditioning.setAirconditioningSwitch(besDoPoint.getfNickName());
                            }
                        }
                        //判断空调温度
                        if (besAirconditioning.getAirconditioningTemperatureSystem() != null && !"".equals(besAirconditioning.getAirconditioningTemperatureSystem())){
                            //空调温度必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningTemperatureSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningTemperatureSystem());
                                if (besAoPoint == null){
                                    errMsg += "空调温度点位错误,";
                                    flag = false;
                                } else {
                                    besAirconditioning.setAirconditioningTemperatureSystem(besAoPoint.getfSysName());
                                    besAirconditioning.setAirconditioningTemperature(besAoPoint.getfNickName());
                                }
                            } else {
                                besAirconditioning.setAirconditioningTemperatureSystem(besDoPoint.getfSysName());
                                besAirconditioning.setAirconditioningTemperature(besDoPoint.getfNickName());
                            }
                        }
                        //判断空调显示温度
                        if (besAirconditioning.getAirconditioningDisplaytemperatureSystem() != null && !"".equals(besAirconditioning.getAirconditioningDisplaytemperatureSystem())){
                            //显示温度必须为AI或DI
                            //从缓存中取出该点位
                            BesDiPoint besDiPoint = diPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningDisplaytemperatureSystem());
                            if (besDiPoint == null){
                                BesAiPoint besAiPoint = aiPointCache.getCachedElementBySysNameOld(besAirconditioning.getAirconditioningDisplaytemperatureSystem());
                                if (besAiPoint == null){
                                    errMsg += "空调显示温度点位错误,";
                                    flag = false;
                                } else {
                                    besAirconditioning.setAirconditioningDisplaytemperatureSystem(besAiPoint.getF_sys_name());
                                    besAirconditioning.setAirconditioningDisplaytemperature(besAiPoint.getF_nick_name());
                                }
                            } else {
                                besAirconditioning.setAirconditioningDisplaytemperatureSystem(besDiPoint.getfSysName());
                                besAirconditioning.setAirconditioningDisplaytemperature(besDiPoint.getfNickName());
                            }
                        }

                        //错误报告信息生成
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
                        for (BESAirconditioning besAirconditioning :list) {
                            try {
                                inportflag = airconditioningConfigMapper.insertAirconditioning(besAirconditioning);
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
