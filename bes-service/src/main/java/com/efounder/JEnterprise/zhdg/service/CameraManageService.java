package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.CameraManageHk;
import com.github.pagehelper.PageInfo;
/*
* 海康摄像头配置
* add by gaoguangchao 2021-12-01
* */
public interface CameraManageService {
    /*查询列表+模糊查询*/
    PageInfo<CameraManageHk> selectList(String keywords, Integer pageNum);
    /*根据id查询*/
    public ISSPReturnObject selectById(long id);
    /*根据编号查询*/
    public ISSPReturnObject selectByNum(String cameraNum);
    /*新增摄像头*/
    public ISSPReturnObject add(CameraManageHk cameraManageHk);
    /*修改摄像头*/
    public ISSPReturnObject update(CameraManageHk cameraManageHk);
    /*删除摄像头*/
    public ISSPReturnObject delete(long id);
    /*视频预览*/
    public ISSPReturnObject preView(CameraManageHk cameraManageHk);
}
