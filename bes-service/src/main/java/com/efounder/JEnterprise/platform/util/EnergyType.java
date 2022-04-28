package com.efounder.JEnterprise.platform.util;

/**
 * describe: 能源类型
 *
 * @author zs
 * @date 2021/9/6
 */
public enum EnergyType {

    /**
     * 电
     */
    electricity("01000"),
    /**
     * 水
     */
    water("02000"),
    /**
     * 管道燃气
     */
    fuel_gas("03000"),
    /**
     * 集中供热
     */
    central_heating("04000"),
    /**
     * 集中供冷
     */
    central_cooling("05000");

    /**
     * 能源类型编码
     */
    private String code;

    EnergyType(String s) {
        this.code = s;
    }

    public String getCode() {
        return code;
    }
}
