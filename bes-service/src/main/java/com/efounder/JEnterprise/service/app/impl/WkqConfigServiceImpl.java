package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.WkqConfigMapper;
import com.efounder.JEnterprise.model.app.WkqConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESWkq;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.WkqConfigService;
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
public class WkqConfigServiceImpl implements WkqConfigService {

    @Resource
    private WkqConfigMapper wkqConfigMapper;

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
     * 添加温控器配置
     * @param wkqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject create(WkqConfigModel wkqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == wkqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = wkqConfigModel.getName();
        String  wkqAddress = wkqConfigModel.getWkqAddress();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(wkqAddress)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            wkqConfigMapper.insert(wkqConfigModel);

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
     * @param wkqConfigModel
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, WkqConfigModel wkqConfigModel) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = wkqConfigMapper.findList(wkqConfigModel);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询温控器配置信息
     * @param wkqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject query(WkqConfigModel wkqConfigModel) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = wkqConfigMapper.findList(wkqConfigModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    /**
     * 更新温控器配置信息
     * */
    @Override
    public ISSPReturnObject update(WkqConfigModel wkqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == wkqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = wkqConfigModel.getId();
        String  name        = wkqConfigModel.getName();
        String  wkqAddress = wkqConfigModel.getWkqAddress();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(wkqAddress)
                ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        WkqConfigModel acm = new WkqConfigModel();
        acm.setId(id);

        try {
            List<Object> list = wkqConfigMapper.findList(acm);

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
            wkqConfigMapper.update(wkqConfigModel);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除温控器配置信息
     * @param wkqConfigModel
     * @return
     */
    @Override
    public ISSPReturnObject delete(WkqConfigModel wkqConfigModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == wkqConfigModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = wkqConfigModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        WkqConfigModel chum = new WkqConfigModel();
        chum.setId(id);

        try {
            List<Object> list = wkqConfigMapper.findList(chum);

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
            wkqConfigMapper.delete(id);
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
                    ExcelUtil<BESWkq> util = new ExcelUtil<>(BESWkq.class);
                    List<BESWkq> list = util.importExcel("温控器配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESWkq besWkq = list.get(i);
                        String errMsg = "";
                        //判断温控器名称
                        if(besWkq.getName() == null || besWkq.getName().equals(""))
                        {
                            errMsg = "温控器名称为空,";
                            flag = false;
                        }
                        //判断温控器位置
                        if(besWkq.getWkqAddress() == null || besWkq.getWkqAddress().equals("")) {
                            errMsg += "温控器位置为空,";
                            flag = false;
                        }
                        //判断温控器模式
                        if (besWkq.getWkqModeSystem() != null && !"".equals(besWkq.getWkqModeSystem())){
                            //温控器模式必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besWkq.getWkqModeSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besWkq.getWkqModeSystem());
                                if (besAoPoint == null){
                                    errMsg += "温控器模式点位错误,";
                                    flag = false;
                                } else {
                                    besWkq.setWkqModeSystem(besAoPoint.getfSysName());
                                    besWkq.setWkqMode(besAoPoint.getfNickName());
                                }
                            } else {
                                besWkq.setWkqModeSystem(besDoPoint.getfSysName());
                                besWkq.setWkqMode(besDoPoint.getfNickName());
                            }
                        }
                        //判断温控器风速
                        if (besWkq.getWkqWindspeedSystem() != null && !"".equals(besWkq.getWkqWindspeedSystem())){
                            //温控器风速必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besWkq.getWkqWindspeedSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besWkq.getWkqWindspeedSystem());
                                if (besAoPoint == null){
                                    errMsg += "温控器风速点位错误,";
                                    flag = false;
                                } else {
                                    besWkq.setWkqWindspeedSystem(besAoPoint.getfSysName());
                                    besWkq.setWkqWindspeed(besAoPoint.getfNickName());
                                }
                            } else {
                                besWkq.setWkqWindspeedSystem(besDoPoint.getfSysName());
                                besWkq.setWkqWindspeed(besDoPoint.getfNickName());
                            }
                        }
                        //判断温控器开关
                        if (besWkq.getWkqSwitchSystem() != null && !"".equals(besWkq.getWkqSwitchSystem())){
                            //温控器开关必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besWkq.getWkqSwitchSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besWkq.getWkqSwitchSystem());
                                if (besAoPoint == null){
                                    errMsg += "温控器开关点位错误,";
                                    flag = false;
                                } else {
                                    besWkq.setWkqSwitchSystem(besAoPoint.getfSysName());
                                    besWkq.setWkqSwitch(besAoPoint.getfNickName());
                                }
                            } else {
                                besWkq.setWkqSwitchSystem(besDoPoint.getfSysName());
                                besWkq.setWkqSwitch(besDoPoint.getfNickName());
                            }
                        }
                        //判断温控器设定温度
                        if (besWkq.getWkqTemperatureSystem() != null && !"".equals(besWkq.getWkqTemperatureSystem())){
                            //温控器设定温度必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besWkq.getWkqTemperatureSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besWkq.getWkqTemperatureSystem());
                                if (besAoPoint == null){
                                    errMsg += "温控器设定温度点位错误,";
                                    flag = false;
                                } else {
                                    besWkq.setWkqTemperatureSystem(besAoPoint.getfSysName());
                                    besWkq.setWkqTemperature(besAoPoint.getfNickName());
                                }
                            } else {
                                besWkq.setWkqTemperatureSystem(besDoPoint.getfSysName());
                                besWkq.setWkqTemperature(besDoPoint.getfNickName());
                            }
                        }
                        //判断温控器显示温度
                        if (besWkq.getWkqDisplaytemperatureSystem() != null && !"".equals(besWkq.getWkqDisplaytemperatureSystem())){
                            //温控器显示温度必须为AI或DI
                            //从缓存中取出该点位
                            BesDiPoint besDiPoint = diPointCache.getCachedElementBySysNameOld(besWkq.getWkqDisplaytemperatureSystem());
                            if (besDiPoint == null){
                                BesAiPoint besAiPoint = aiPointCache.getCachedElementBySysNameOld(besWkq.getWkqDisplaytemperatureSystem());
                                if (besAiPoint == null){
                                    errMsg += "温控器显示温度点位错误,";
                                    flag = false;
                                } else {
                                    besWkq.setWkqDisplaytemperatureSystem(besAiPoint.getF_sys_name());
                                    besWkq.setWkqDisplaytemperature(besAiPoint.getF_nick_name());
                                }
                            } else {
                                besWkq.setWkqDisplaytemperatureSystem(besDiPoint.getfSysName());
                                besWkq.setWkqDisplaytemperature(besDiPoint.getfNickName());
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
                        for (BESWkq besWkq :list) {
                            try {
                                inportflag = wkqConfigMapper.insertWkq(besWkq);
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
