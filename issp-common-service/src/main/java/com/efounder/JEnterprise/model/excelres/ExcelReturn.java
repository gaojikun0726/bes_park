package com.efounder.JEnterprise.model.excelres;

/**
 * @author 杨超--YangChao
 * @version 创建时间：2018年12月10日 下午7:47:17
 * @parameter excel返回实体类
 * @version 1.0
 */
public class ExcelReturn {

    private String msg;// 返回信息
    private String status;// 是否成功 1.成功 0.失败

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
