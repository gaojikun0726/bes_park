package com.efounder.JEnterprise.platform.util;

/**
 * describe:
 *
 * @author zs
 * @date 2021/9/6
 */
public enum  EnergyQueryType {


    /**
     * 年能耗统计
     */
    year,
    /**
     * 月能耗统计
     */
    month,
    /**
     * 日能耗统计
     */
    day,
    /**
     * 分时能耗统计
     */
    hour,
    /**
     * 月环比能耗数据统计
     */
    month_link_ratio,
    /**
     * 月同比能耗数据统计
     */
    month_same_ratio,
    /**
     * 年度能耗趋势
     */
    year_trend,


    /**
     * 各单位月排行统计
     */
    company_month_rank,


    /**
     * 获取各楼宇的按日能耗统计
     */
    building_by_day,

    /**
     * 本周的每日能耗数据统计
     */
    week_by_day



//    /**
//     * 年能耗统计
//     */
//    year("year"),
//    /**
//     * 月能耗统计
//     */
//    month("month"),
//    /**
//     * 日能耗统计
//     */
//    day("day"),
//    /**
//     * 分时能耗统计
//     */
//    hour("hour"),
//    /**
//     * 月环比能耗数据统计
//     */
//    month_link_ratio("month_link_ratio"),
//    /**
//     * 月同比能耗数据统计
//     */
//    month_same_ratio("month_same_ratio"),
//    /**
//     * 年度能耗趋势
//     */
//    year_trend("year_trend");
//
//    EnergyQueryType(String s) {
//    }
}
