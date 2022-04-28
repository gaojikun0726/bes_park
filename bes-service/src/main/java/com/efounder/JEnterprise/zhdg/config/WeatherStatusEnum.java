package com.efounder.JEnterprise.zhdg.config;

/**
 * describe: 气象状态枚举类
 *
 * @author zs
 * @date 2022/1/10
 */
public enum  WeatherStatusEnum {
    /**
     * 在线
     */
    online("0"),

    /**
     * 报警
     */
    warning("1"),

    /**
     * 离线
     */
    offline("2");

    private String code;

    WeatherStatusEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
