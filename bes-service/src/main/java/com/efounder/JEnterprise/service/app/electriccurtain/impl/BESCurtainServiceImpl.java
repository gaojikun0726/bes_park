package com.efounder.JEnterprise.service.app.electriccurtain.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.electriccurtain.BESCurtainMapper;
import com.efounder.JEnterprise.model.app.electriccurtain.BESCurain;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCurtain;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAoPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDoPoint;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.electriccurtain.BESCurtainService;
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
public class  BESCurtainServiceImpl implements BESCurtainService {

    @Resource
    private BESCurtainMapper bescurtainMapper;

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
     * @param bescurain
     * @return
     */
    @Override
    public ISSPReturnObject create(BESCurain bescurain) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == bescurain) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String  name        = bescurain.getName();
        String  electriccurtain_address = bescurain.getElectriccurtain_address();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(electriccurtain_address)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        try {
            bescurtainMapper.insert(bescurain);

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
     * @param bescurain
     * @return
     */
    @Override
    public PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, BESCurain bescurain) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<Object> list = bescurtainMapper.findList(bescurain);
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询电表配置信息
     * @param bescurain
     * @return
     */
    @Override
    public ISSPReturnObject query(BESCurain bescurain) {

        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<Object> list = bescurtainMapper.findList(bescurain);
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
    public ISSPReturnObject update(BESCurain bescurain) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == bescurain) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id          = bescurain.getId();
        String  name        = bescurain.getName();
        String  clAddress = bescurain.getElectriccurtain_address();

        if (null == id
                || !StringUtils.hasText(name)
                || !StringUtils.hasText(clAddress)
        ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        BESCurain acm = new BESCurain();
        acm.setId(id);

        try {
            List<Object> list = bescurtainMapper.findList(acm);

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
            bescurtainMapper.update(bescurain);
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    /**
     * 删除电表配置信息
     * @param bescurain
     * @return
     */
    @Override
    public ISSPReturnObject delete(BESCurain bescurain) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == bescurain) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = bescurain.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        BESCurain chum = new BESCurain();
        chum.setId(id);

        try {
            List<Object> list = bescurtainMapper.findList(chum);

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
            bescurtainMapper.delete(id);
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
                    ExcelUtil<BESCurtain> util = new ExcelUtil<>(BESCurtain.class);
                    List<BESCurtain> list = util.importExcel("电动窗帘配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESCurtain besCurtain = list.get(i);
                        String errMsg = "";
                        //判断窗帘名称
                        if(besCurtain.getName() == null || besCurtain.getName().equals(""))
                        {
                            errMsg = "窗帘名称为空,";
                            flag = false;
                        }
                        //判断窗帘位置
                        if(besCurtain.getCurtainAddress() == null || besCurtain.getCurtainAddress().equals("")) {
                            errMsg += "窗帘位置为空,";
                            flag = false;
                        }
                        //判断开关
                        if (besCurtain.getCurtainSwitchSystem() != null && !"".equals(besCurtain.getCurtainSwitchSystem())){
                            //开关必须为AO或DO
                            //从设备缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainSwitchSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainSwitchSystem());
                                if (besAoPoint == null){
                                    errMsg += "开关点位错误,";
                                    flag = false;
                                } else {
                                    besCurtain.setCurtainSwitchSystem(besAoPoint.getfSysName());
                                    besCurtain.setCurtainSwitch(besAoPoint.getfNickName());
                                }
                            } else {
                                besCurtain.setCurtainSwitchSystem(besDoPoint.getfSysName());
                                besCurtain.setCurtainSwitch(besDoPoint.getfNickName());
                            }
                        }
                        //判断开度控制
                        if (besCurtain.getCurtainKdkzSystem() != null && !"".equals(besCurtain.getCurtainKdkzSystem())){
                            //开度控制必须为AO或DO
                            //从设备缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainKdkzSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainKdkzSystem());
                                if (besAoPoint == null){
                                    errMsg += "开度控制点位错误,";
                                    flag = false;
                                } else {
                                    besCurtain.setCurtainKdkzSystem(besAoPoint.getfSysName());
                                    besCurtain.setCurtainKdkz(besAoPoint.getfNickName());
                                }
                            } else {
                                besCurtain.setCurtainKdkzSystem(besDoPoint.getfSysName());
                                besCurtain.setCurtainKdkz(besDoPoint.getfNickName());
                            }
                        }
                        //判断暂停功能
                        if (besCurtain.getCurtainStopSystem() != null && !"".equals(besCurtain.getCurtainStopSystem())){
                            //暂停功能必须为AO或DO
                            //从设备缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainStopSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besCurtain.getCurtainStopSystem());
                                if (besAoPoint == null){
                                    errMsg += "暂停功能点位错误,";
                                    flag = false;
                                } else {
                                    besCurtain.setCurtainStopSystem(besAoPoint.getfSysName());
                                    besCurtain.setCurtainStop(besAoPoint.getfNickName());
                                }
                            } else {
                                besCurtain.setCurtainStopSystem(besDoPoint.getfSysName());
                                besCurtain.setCurtainStop(besDoPoint.getfNickName());
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
                        for (BESCurtain besCurtain :list) {
                            try {
                                inportflag = bescurtainMapper.insertCurtain(besCurtain);
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
