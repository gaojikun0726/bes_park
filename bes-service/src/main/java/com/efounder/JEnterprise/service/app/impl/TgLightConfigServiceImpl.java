package com.efounder.JEnterprise.service.app.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.AiPointCache;
import com.efounder.JEnterprise.initializer.AoPointCache;
import com.efounder.JEnterprise.initializer.DiPointCache;
import com.efounder.JEnterprise.initializer.DoPointCache;
import com.efounder.JEnterprise.mapper.app.TgLightConfigMapper;
import com.efounder.JEnterprise.model.app.TgLightConfig;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESTgLight;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.app.TgLightConfigService;
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
 * describe: 可调光灯光配置
 *
 * @author wzx
 * @date 2020-8-28 17:44:12
 */
@Service
public class TgLightConfigServiceImpl implements TgLightConfigService {

    @Resource
    private TgLightConfigMapper tglightConfigMapper;

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
    public Map queryTgLightConfigList(Map map,String pageSize,String pageNumber) {
        PageHelper.startPage(Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
        List<TgLightConfig> list = tglightConfigMapper.queryTgLightConfigList(map);
        PageInfo<TgLightConfig> page = new PageInfo<>(list);
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
    public PageInfo<TgLightConfig> getPaging(Integer pageSize, Integer pageNumber, Map map) {
        if (pageNumber == null){
            pageNumber = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNumber, pageSize);
        // 紧跟着的第一个select方法会被分页
        List<TgLightConfig> list = tglightConfigMapper.queryTgLightConfigList(map);
        // 用PageInfo对结果进行包装
        PageInfo<TgLightConfig> page = new PageInfo<>(list);
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
        Integer num = tglightConfigMapper.delete(id);
        return num == 1;
    }

    /**
     * 修改
     *
     * @param tglightConfig
     * @return
     */
    @Override
    public boolean update(TgLightConfig tglightConfig) {
        Integer num = tglightConfigMapper.update(tglightConfig);
        return num == 1;
    }

    /**
     * 新增
     *
     * @param tglightConfig
     * @return
     */
    @Override
    public boolean insert(TgLightConfig tglightConfig) {
        Integer num = tglightConfigMapper.insert(tglightConfig);
        return num == 1;
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public TgLightConfig queryOne(String id) {
        List<TgLightConfig> list = tglightConfigMapper.queryOne(id);
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
                    ExcelUtil<BESTgLight> util = new ExcelUtil<>(BESTgLight.class);
                    List<BESTgLight> list = util.importExcel("可调光灯光配置", fis);// 导入excel,处理后生成list
                    // 获取要导出的数据
                    List<ExcelError> excelErrors = new ArrayList<>();

                    for(int i = 0 ;i < list.size(); i++)
                    {
                        boolean flag = true; //插入数据成功的标志
                        BESTgLight besTgLight = list.get(i);
                        String errMsg = "";
                        //判断灯光名称
                        if(besTgLight.getName() == null || besTgLight.getName().equals("")) {
                            errMsg = "灯光名称为空,";
                            flag = false;
                        }
                        //判断灯光位置
                        if(besTgLight.getLightAddress() == null || besTgLight.getLightAddress().equals("")) {
                            errMsg += "灯光位置为空,";
                            flag = false;
                        }
                        //判断灯光调光
                        if (besTgLight.getLightTgSystem() != null && !"".equals(besTgLight.getLightTgSystem())){
                            //灯光调光必须为AO或DO
                            //从缓存中取出该点位
                            BesDoPoint besDoPoint = doPointCache.getCachedElementBySysNameOld(besTgLight.getLightTgSystem());
                            if (besDoPoint == null){
                                BesAoPoint besAoPoint = aoPointCache.getCachedElementBySysNameOld(besTgLight.getLightTgSystem());
                                if (besAoPoint == null){
                                    errMsg += "灯光调光点位错误,";
                                    flag = false;
                                } else {
                                    besTgLight.setLightTgSystem(besAoPoint.getfSysName());
                                    besTgLight.setLightTg(besAoPoint.getfNickName());
                                }
                            } else {
                                besTgLight.setLightTgSystem(besDoPoint.getfSysName());
                                besTgLight.setLightTg(besDoPoint.getfNickName());
                            }
                        }
                        //判断灯光状态
                        if (besTgLight.getLightStatusSystem() != null && !"".equals(besTgLight.getLightStatusSystem())){
                            //灯光状态必须为AI或DI
                            //从缓存中取出该点位
                            BesDiPoint besDiPoint = diPointCache.getCachedElementBySysNameOld(besTgLight.getLightStatusSystem());
                            if (besDiPoint == null){
                                BesAiPoint besAiPoint = aiPointCache.getCachedElementBySysNameOld(besTgLight.getLightStatusSystem());
                                if (besAiPoint == null){
                                    errMsg += "灯光状态点位错误,";
                                    flag = false;
                                } else {
                                    besTgLight.setLightStatusSystem(besAiPoint.getF_sys_name());
                                    besTgLight.setLightStatus(besAiPoint.getF_nick_name());
                                }
                            } else {
                                besTgLight.setLightStatusSystem(besDiPoint.getfSysName());
                                besTgLight.setLightStatus(besDiPoint.getfNickName());
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
                        for (BESTgLight besTgLight :list) {
                            try {
                                inportflag = tglightConfigMapper.insertTgLight(besTgLight);
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
