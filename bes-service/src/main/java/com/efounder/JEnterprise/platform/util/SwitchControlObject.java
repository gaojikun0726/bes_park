package com.efounder.JEnterprise.platform.util;

/**
 * describe: PDU开关控制对象
 *
 * @author zs
 * @date 2021/11/19
 */
public enum SwitchControlObject {


    /**
     * 大灯
     */
    big_light("1"),
    /**
     * 小灯
     */
    small_light("2"),

    /**
     * 显示屏
     */
    screen("3"),

    /**
     * 音柱
     */
    sound_post("4"),

    /**
     * 摄像头
     */
    camera("5"),

    /**
     * wifi
     */
    wifi("6"),
    /**
     * 气象
     */
    weather("7"),
    /**
     * 一键报警
     */
    alarm("8");

    private String code;

    SwitchControlObject(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String getName(String code) {
        for (SwitchControlObject ele : values()) {
            if(ele.getCode().equals(code)) {
                return ele.name();
            }
        }
        return null;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
