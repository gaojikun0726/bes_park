package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.LightConfigMapper;
import com.efounder.JEnterprise.model.app.LightConfig;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESLight;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.LightConfigService;
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
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Service
public class LightConfigServiceImpl implements LightConfigService {

    @Resource
    private LightConfigMapper lightConfigMapper;

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
     * 查询灯光配置列表
     *
     * @param map
     * @return
     */
    @Override
    public Map queryLightConfigList(Map map,String pageSize,String pageNumber) {
        PageHelper.startPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        List<LightConfig> list = lightConfigMapper.queryLightConfigList(map);
        PageInfo<LightConfig> page = new PageInfo<>(list);
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
    public PageInfo<LightConfig> getPaging(Integer pageSize, Integer pageNumber, Map map) {
        if (pageNumber == null){
            pageNumber = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNumber, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<LightConfig> list = lightConfigMapper.queryLightConfigList(map);
        // 用PageInfo对结果进行包装
        PageInfo<LightConfig> page = new PageInfo<>(list);
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
        Integer num = lightConfigMapper.delete(id);
        return num == 1;
    }

    /**
     * 修改
     *
     * @param lightConfig
     * @return
     */
    @Override
    public boolean update(LightConfig lightConfig) {
        Integer num = lightConfigMapper.update(lightConfig);
        return num == 1;
    }

    /**
     * 新增
     *
     * @param lightConfig
     * @return
     */
    @Override
    public boolean insert(LightConfig lightConfig) {
        Integer num = lightConfigMapper.insert(lightConfig);
        return num == 1;
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public LightConfig queryOne(String id) {
        List<LightConfig> list = lightConfigMapper.queryOne(id);
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
                    ExcelUtil<BESLight> util = new ExcelUtil<>(BESLight.class);
                    List<BESLight> list = util.importExcel("灯光配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESLight besLight = list.get(i);
                        String errMsg = "";
                        //判断灯光名称
                        if(besLight.getName() == null || besLight.getName().equals("")) {
                            errMsg = "灯光名称为空,";
                            flag = false;
                        }
                        //判断灯光地址
                        if(besLight.getLightAddress() == null || besLight.getLightAddress().equals("")) {
                            errMsg += "灯光地址为空,";
                            flag = false;
                        }
                        //判断灯光开关
                        if (besLight.getLightSwitchSystem() != null && !"".equals(besLight.getLightSwitchSystem())){
                            //灯光开关必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besLight.getLightSwitchSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besLight.getLightSwitchSystem());
                                if (besAoPoint == null){
                                    errMsg += "灯光开关点位错误,";
                                    flag = false;
                                } else {
                                    besLight.setLightSwitchSystem(besAoPoint.getfSysName());
                                    besLight.setLightSwitch(besAoPoint.getfNickName());
                                }
                            } else {
                                besLight.setLightSwitchSystem(besDoPoint.getfSysName());
                                besLight.setLightSwitch(besDoPoint.getfNickName());
                            }
                        }
                        //判断灯光状态
                        if (besLight.getLightStatusSystem() != null && !"".equals(besLight.getLightStatusSystem())){
                            //灯光状态必须为AI或DI
                            //从缓存中取出该点位
                            BesDiPoint besDiPoint = diPointCache.getCachedElementBySysNameOld(besLight.getLightStatusSystem());
                            if (besDiPoint == null){
                                BesAiPoint besAiPoint = aiPointCache.getCachedElementBySysNameOld(besLight.getLightStatusSystem());
                                if (besAiPoint == null){
                                    errMsg += "灯光状态点位错误,";
                                    flag = false;
                                } else {
                                    besLight.setLightStatusSystem(besAiPoint.getF_sys_name());
                                    besLight.setLightStatus(besAiPoint.getF_nick_name());
                                }
                            } else {
                                besLight.setLightStatusSystem(besDiPoint.getfSysName());
                                besLight.setLightStatus(besDiPoint.getfNickName());
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
                        for (BESLight besLight :list) {
                            try {
                                inportflag = lightConfigMapper.insertLight(besLight);
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
