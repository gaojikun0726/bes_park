package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.dataAnalysises.BesWainingRealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 杨超
 * @version 创建时间：2018年12月3日 下午2:07:08
 * @parameter 报警监控接口
 * @version 1.0
 */
@Service("BesExportAnalysisService")
public class BesWainingRealServiceimpl implements BesWainingRealService {
    private static final Logger log = LoggerFactory.getLogger(BesWainingRealServiceimpl.class);

    @Autowired
    private BesWainingRealInfoMapper beswainingrealinfomapper;
    /**
     * Description:分页查询报警监控信息
     * @param pageNum
     * @param besWainingInfo
     * @return
     * @see com.efounder.JEnterprise.service.dataAnalysises.BesWainingRealService#searchWainingInfo(java.lang.Integer,
     *      com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo)
     */
    @Override
    public PageInfo<BesWainingInfo> getWarningRealInfoData(Integer pageNum, BesWainingInfo besWainingInfo) {
        List<BesWainingInfo> pageList;
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        if (besWainingInfo.getFType().equals("1")){//当报警类型为"全部"的时候
            pageList = beswainingrealinfomapper.getWarningRealInfoDataAll(besWainingInfo);
        }else {
            pageList = beswainingrealinfomapper.getWarningRealInfoData(besWainingInfo);
        }
        for (BesWainingInfo besWainingInfo1 : pageList) {
            besWainingInfo1.setFATime(besWainingInfo1.getFATime().replace(".0", ""));
            besWainingInfo1.setFCrdate(besWainingInfo1.getFCrdate().replace(".0",""));
        }

        // 用PageInfo对结果进行包装
        PageInfo<BesWainingInfo> page = new PageInfo<>(pageList);
        return page;
    }

    /**
     *
     * Description:报警处理
     * @param request
     * @return
     * @see com.efounder.JEnterprise.service.dataAnalysises.BesWainingRealService#WarningDsipose(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public ISSPReturnObject WarningDsipose(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String fGuid = request.getParameter("fguid");// 获取guid
        String f_operation = "0";// 默认已处理
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        String f_yhbh = user.getF_yhbh();// 获取登录人的名称
        try {
            // 逻辑操作 将实时表(bes_warning_real)数据删除,新增到查询表(bes_warning_info)--先新增后删除
            Boolean flag = beswainingrealinfomapper.WarningDsipose(fGuid, f_yhbh, f_operation);
//            Boolean updateflag = beswainingrealinfomapper.updateWarningDsipose(fGuid,f_operation);
            // 删除操作
            Boolean delflag = beswainingrealinfomapper.DelWarningInfo(fGuid);
            if (flag & delflag) {
                returnObject.setStatus("1");// 成功
                returnObject.setMsg("报警信息处理完成");
                return returnObject;
            } else {
                returnObject.setStatus("0");// 成功
                returnObject.setMsg("报警信息处理失败");
                return returnObject;
            }
        } catch (Exception e) {
            returnObject.setStatus("0");//成功
            returnObject.setMsg("报警信息处理失败");
            return returnObject;
        }
    }

    /**
     *
     * @Description: 批量处理报警消息
     *
     * @auther: wanghongjie
     * @date: 11:23 2020/6/6
     * @param:
     * @return:
     *
     */
    @Override
    public ISSPReturnObject WarningDsiposeList(List<BesWainingInfo> besWainingInfos) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String f_operation = "0";// 默认已处理
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        String f_yhbh = user.getF_yhbh();// 获取登录人的名称
        for (BesWainingInfo besWainingInfo : besWainingInfos){
            String fGuid = besWainingInfo.getFGuid();
            //批量插入查询表(bes_warning_info)
            Boolean flag = beswainingrealinfomapper.WarningDsipose(fGuid, f_yhbh, f_operation);
            Boolean delflag = beswainingrealinfomapper.DelWarningInfo(fGuid);
            //批量修改报警实时表的处理状态
//            Boolean updateflag = beswainingrealinfomapper.updateWarningDsipose(fGuid,f_operation);
            if (flag == true && delflag == true){
                returnObject.setMsg("操作成功");
                returnObject.setStatus("1");
            }else {
                returnObject.setMsg("操作失败");
                returnObject.setStatus("0");
            }
        }
        return returnObject;
    }

    /**
     *
     * @Description: 首页告警图标--查询实时报警数据
     *
     * @auther: wanghongjie
     * @date: 10:30 2020/6/4
     * @param: [bars, pageNum, f_operation]
     * @return: com.github.pagehelper.PageInfo<java.lang.Object>
     *
     */
    @Override
    public PageInfo<BesWainingInfo> getAlarmWarnInfoByRecoverState(Integer bars, Integer pageNum,String f_operation,String type) {
        List<BesWainingInfo> list;
        if (pageNum == null)
            pageNum = 1;
        if (bars == null) {
            bars = Constants.PAGE_SIZE;
        }
        PageHelper.startPage(pageNum, bars);
        // 紧跟着的第一个select方法会被分页
        if ("1".equals(type)){//当报警类型为"全部"的时候
            list = beswainingrealinfomapper.getAlarmWarnInfoByRecoverStateAll(f_operation);
        }else {
            list = beswainingrealinfomapper.getAlarmWarnInfoByRecoverState(f_operation,type);
        }

        for(BesWainingInfo besWainingInfo1:list){
            besWainingInfo1.setFATime(besWainingInfo1.getFATime().replace(".0",""));
            besWainingInfo1.setFCrdate(besWainingInfo1.getFCrdate().replace(".0",""));
        }
        // 用PageInfo对结果进行包装
        PageInfo<BesWainingInfo> page = new PageInfo<BesWainingInfo>(list, 4);
        return page;
    }

    /**
     *
     * @Description: 获取未恢复的信息数
     *
     * @auther: wanghongjie
     * @date: 15:39 2020/5/27
     * @param: [f_recover_state, f_zzjgid, sblxList, sbList]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject getNoRecoverCount(String f_operation) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            Map<String, String> map = beswainingrealinfomapper.getNoRecoverCount(f_operation);
            returnObject.setData(map.get("F_RECOVER_COUNT"));
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     *
     * @Description:   全部处理报警信息
     *
     * @auther: wanghongjie
     * @date: 10:54 2020/7/1
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject WarningDsiposeAll(JSONObject obj) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        String f_operation = "0";// 默认已处理
        Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
        ESUser user = principa.getUser();
        String f_yhbh = user.getF_yhbh();// 获取登录人的名称
        obj.put("user",user.getF_yhbh());

        //园区编号
        String fYqbh = obj.getString("fYqbh");
        String startTime = obj.getString("startTime");
        String endTime = obj.getString("endTime");
        String fType = obj.getString("fType");
        String fLevel = obj.getString("fLevel");
        if (fYqbh == "" || fYqbh == null) {
            returnObject.setMsg("请选择具体的园区再操作!");
            returnObject.setStatus("0");
            return returnObject;
        }
            //批量插入查询表(bes_warning_info)
            Boolean flag = beswainingrealinfomapper.warningDsiposeAllByFtype(obj);

            //批量修改报警实时表的处理状态
//            Boolean updateflag = beswainingrealinfomapper.updateWarningDsipose(fGuid,f_operation);
        if (flag){
            Boolean delflag = beswainingrealinfomapper.warningDsiposeAll(obj);
            if (delflag) {
                returnObject.setMsg("操作成功");
                returnObject.setStatus("1");
            }

        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }


//        int warningDsiposeAll = 0;
//        if (obj.get("fType").equals("0")){//当报警类型为"全部"的时候
//            warningDsiposeAll = beswainingrealinfomapper.warningDsiposeAllByFtype(obj);
//        }else {
//            warningDsiposeAll = beswainingrealinfomapper.warningDsiposeAll(obj);
//        }
//        if (warningDsiposeAll > 0){
//            returnObject.setMsg("操作成功");
//            returnObject.setStatus("1");
//        }else {
//            returnObject.setMsg("操作失败");
//            returnObject.setStatus("0");
//        }
        return returnObject;
    }
}
