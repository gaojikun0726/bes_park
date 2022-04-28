package com.efounder.JEnterprise.util;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 10:22 2022/4/24
 * @Modified By:
 */
public class TaskScheduleModel {
    /**
     * 所选作业类型:
     * 0  -> 每分钟
     * 1  -> 每小时
     * 2  -> 每天
     * 3  -> 每周
     * 4  -> 每月
     * 5  -> 每年
     * 6  -> 指定固定日期时间
     */
    private Integer jobType;
    //启动时间
    private String startDate;

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public TaskScheduleModel(Integer jobType, String startDate) {
        this.jobType = jobType;
        this.startDate = startDate;
    }

    public TaskScheduleModel() {
    }
}
