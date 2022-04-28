package com.efounder.JEnterprise.zhdg.entity;

import lombok.Data;

import java.util.Date;

/**
 * 报警历史记录对象 seb_warn_record
 * 
 * @author YangChao
 * @date 2020-05-22
 */
@Data
public class SebWarnRecord
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 点位id */
    private String pointid;

    /** 报警id */
    private Long warnid;

    /** 报警类型 */
    private String warntype;

    /** 报警说明 */
    private String warnmsg;

    /** 创建时间 */
    private Date creattime;

}
