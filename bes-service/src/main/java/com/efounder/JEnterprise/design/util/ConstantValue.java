package com.efounder.JEnterprise.design.util;

public enum ConstantValue {
    /**
     * 成功，失败标识
     */
    SUCCESS("1"),
    FAIL("0"),


    /**
     * 设定温度最大值/最小值
     */
    SET_TEMPERATURE_MIN("18"),
    SET_TEMPERATURE_MAX("35"),

    /**
     * DO点状态：开机，关机
     */
    STATE_OPEN("255"),
    STATE_STOP("0"),
    STATE_BREAKDOWN("100"),

    /**
     * 模式：制冷255 制热0
     */
    MODE_HEATING("0"),
    MODE_COOLING("255"),

    /**
     * DO点状态：风速（高3 中2 低1 节能4）
     */
    WIND_SPEED_HIGH("3"),
    WIND_SPEED_MIDDLE("2"),
    WIND_SPEED_LOW("1"),
    WIND_SPEED_ENERGY("4"),

    /**
     * 温度增加或者温度减小的标志区分
     */
    TEMPERATURE_UP("up"),
    TEMPERATURE_DOWN("down"),

    /**
     * 表名
     */
    /**
     * 设备表
     */
    TABLE_NAME_DEVICE("bes_sbpz_struct"),


    /**
     * 节点所属系统，cross1、lamp2、energy3
     */
    NODE_ATTRIBUTION_CROSS("1"),
    NODE_ATTRIBUTION_LAMP("2"),
    NODE_ATTRIBUTION_ENERGY("3"),
    /**
     * 节点类型
     */
    TYPE_AI("10"),
    TYPE_AO("11"),
    TYPE_DI("12"),
    TYPE_DO("13"),
    TYPE_VISUAL("16"),


    /**
     * 隐藏状态--隐藏
     */
    HIDE_STATE_TRUE("00"),
    /**
     * 隐藏状态--显示
     */
    HIDE_STATE_FALSE("11"),

    /**
     * 设计器图形类型：1线段，2圆形，3矩形
     */
    GRAPH_TYPE_LINE("1"),
    GRAPH_TYPE_CIRCLE("2"),
    GRAPH_TYPE_RECT("3")
    ;

    private String value;
    ConstantValue(String s) {
        this.value = s;
    }

    public String getValue(){
        return this.value;
    }


}
