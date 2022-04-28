package com.efounder.JEnterprise.timer.manage;

/**
 * describe: 开关状态类型
 *
 * @author zs
 * @date 2021/11/19
 */
public enum SwitchStatusType {

    /**
     * 开关状态：关
     */
    switchOFF("0"),

    /**
     * 开关状态：开
     */
    switchOn("1");

    /**
     * 状态对应的指令
     */
    private String code;

    SwitchStatusType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
