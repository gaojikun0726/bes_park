package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.zhdg.entity.CameraManageHk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
* 海康摄像头配置
* add by gaoguangchao 2021-12-1
* */
@Mapper
public interface CameraManageDao {
    /*查询列表+模糊查询*/
    List<CameraManageHk> selectList(@Param(value = "keywords")String keywords);
    /*根据id查询*/
    public CameraManageHk selectById(long id);
    /*根据编号查询*/
    public CameraManageHk selectByNum(String cameraNum);
    /*新增摄像头*/
    public int add(CameraManageHk cameraManageHk);
    /*修改摄像头*/
    public int update(CameraManageHk cameraManageHk);
    /*删除摄像头*/
    public int delete(long id);
}
