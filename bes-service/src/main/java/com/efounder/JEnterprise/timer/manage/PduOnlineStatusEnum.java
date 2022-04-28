package com.efounder.JEnterprise.timer.manage;

/**
 * describe: pdu在线状态
 *
 * @author zs
 * @date 2022/1/4
 */
public enum PduOnlineStatusEnum {

    /**
     * 在线
     */
    on("1"),

    /**
     * 离线
     */
    off("0");

    private String code;

    PduOnlineStatusEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
