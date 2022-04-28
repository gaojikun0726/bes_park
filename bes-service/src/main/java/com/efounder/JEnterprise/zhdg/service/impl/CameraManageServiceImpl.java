package com.efounder.JEnterprise.zhdg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.zhdg.dao.CameraManageDao;
import com.efounder.JEnterprise.zhdg.entity.CameraManageHk;
import com.efounder.JEnterprise.zhdg.entity.StreamEntity;
import com.efounder.JEnterprise.zhdg.service.CameraManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ace.httpclient.InterfaceUtils.InterfaceCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraManageServiceImpl implements CameraManageService {
    @Autowired
    private CameraManageDao cameraManageDao;
    @Value("${mediaStream.getStreamUrl}")
    private String getStreamUrl;
    /**
     * 查询摄像头列表+模糊查询
     * */
    @Override
    public PageInfo<CameraManageHk> selectList(String keywords, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<CameraManageHk> list = cameraManageDao.selectList(keywords);
        PageInfo<CameraManageHk> page = new PageInfo<CameraManageHk>(list);
        return page;
    }
    /**
     * 根据id查询摄像头
     * */
    @Override
    public ISSPReturnObject selectById(long id) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        CameraManageHk cameraManageHk=cameraManageDao.selectById(id);
        if(cameraManageHk!=null){
            returnObject.setData(cameraManageHk);
            returnObject.setMsg("操作成功");
            returnObject.setStatus("1");
        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    /**
     * 根据编号查询摄像头
     * */
    @Override
    public ISSPReturnObject selectByNum(String cameraNum) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        CameraManageHk cameraManageHk=cameraManageDao.selectByNum(cameraNum);
        if(cameraManageHk!=null){
            returnObject.setData(cameraManageHk);
        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    /**
     * 新增摄像头
     * */
    @Override
    public ISSPReturnObject add(CameraManageHk cameraManageHk) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        int count=cameraManageDao.add(cameraManageHk);
        if(count>0){
            returnObject.setMsg("操作成功");
            returnObject.setStatus("1");
        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    /**
     * 修改摄像头
     * */
    @Override
    public ISSPReturnObject update(CameraManageHk cameraManageHk) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        int count=cameraManageDao.update(cameraManageHk);
        if(count>0){
            returnObject.setMsg("操作成功");
            returnObject.setStatus("1");
        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    /**
     * 删除摄像头
     * */
    @Override
    public ISSPReturnObject delete(long id) {
        ISSPReturnObject returnObject=new ISSPReturnObject();
        int count=cameraManageDao.delete(id);
        if(count>0){
            returnObject.setMsg("操作成功");
            returnObject.setStatus("1");
        }else {
            returnObject.setMsg("操作失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }
    /**
     * 视频预览
     * */
    public ISSPReturnObject preView(CameraManageHk cameraManageHk){
        ISSPReturnObject returnObject=new ISSPReturnObject();
        String cameraUser=cameraManageHk.getCameraUser();
        String password=cameraManageHk.getCameraPassword();
        String ip=cameraManageHk.getCameraIp();
        String cameraNum=cameraManageHk.getCameraNum();
        //拼接RTSP
        String rtsp="rtsp://"+cameraUser+":"+password+"@"+ip+":554/h264/ch1/sub/"+cameraNum;
        //调用接口获取httpFlv格式的流
        StreamEntity streamEntity=new StreamEntity();
        streamEntity.setRtspUrl(rtsp);
        String res = null;
        try {
            res=InterfaceCall.doPost(getStreamUrl, JSONObject.toJSONString(streamEntity));
            JSONObject obj=JSONObject.parseObject(res);
            String httpFlvUrl=String.valueOf(obj.getJSONObject("data").get("httpFlvUrl"));
            if(httpFlvUrl!=null){
                returnObject.setData(httpFlvUrl);
                returnObject.setStatus("1");
            }else {
                returnObject.setStatus("0");
            }
        } catch (org.apache.commons.httpclient.HttpException e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }
        return returnObject;
    }
}
