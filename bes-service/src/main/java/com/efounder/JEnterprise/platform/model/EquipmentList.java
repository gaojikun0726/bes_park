package com.efounder.JEnterprise.platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 设备列表 bes_equipment_list
 *
 * @author zs
 * @date 2020-10-26
 */
public class EquipmentList
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String fId;

    /** 设备编号 */
//    @Excel(name = "设备编号")
    private String fSbb;

    /** 设备名称 */
//    @Excel(name = "设备名称")
    private String fSbmc;

    /** 品牌编号 */
//    @Excel(name = "品牌编号")
    private String fBrandid;

    /** 品牌名称 */
//    @Excel(name = "品牌名称")
    private String fBrandmc;

    /** 线路id */
//    @Excel(name = "线路id")
    private String fXlid;

    /** 线路名称 */
//    @Excel(name = "线路名称")
    private String fXlmc;

    /** 设备标识名称 /设备真实标签名 */
//    @Excel(name = "设备标识名称 /设备真实标签名")
    private String fSn;

    /** 设备类型编号 */
//    @Excel(name = "设备类型编号")
    private String fSblxType;

    /** 设备型号编号 */
//    @Excel(name = "设备型号编号")
    private String fSbxhType;

    /** 设备类型名称 */
//    @Excel(name = "设备类型名称")
    private String fSblemc;

    /** 设备型号名称 */
//    @Excel(name = "设备型号名称")
    private String fSbxhmc;

    /** 入库单ID /FK() */
//    @Excel(name = "入库单ID /FK()")
    private String fRkdId;

    /** 所属仓库 */
//    @Excel(name = "所属仓库")
    private String fSsck;

    /** 物理位置 room前缀对应-监控室房间base_room-所属监控室base_monitorroom */
//    @Excel(name = "物理位置 room前缀对应-监控室房间base_room-所属监控室base_monitorroom")
    private String fLacation;

    /** 使用单位/组织机构表 */
//    @Excel(name = "使用单位/组织机构表")
    private String fSydw;

    /** 经办人/用户表 */
//    @Excel(name = "经办人/用户表")
    private String fJbr;

    /** 经度 */
//    @Excel(name = "经度")
    private String fLong;

    /** 纬度 */
//    @Excel(name = "纬度")
    private String fLat;

    /** 生产商 */
//    @Excel(name = "生产商")
    private String fScs;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fGmrq;

    /** 保修年限 */
//    @Excel(name = "保修年限")
    private String fBxnx;

    /** 单价 */
//    @Excel(name = "单价")
    private String fUnitPrice;

    /** 设备状态(0:正常 1:报警 2:离线) */
//    @Excel(name = "设备状态(0:正常 1:报警 2:离线)")
    private String fStatus;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fLastGxsj;

    /** 最后更新人 */
//    @Excel(name = "最后更新人")
    private String fLastGxr;

    /** 一维码编号 */
//    @Excel(name = "一维码编号")
    private String fYwmbh;

    /** 二维码编号 */
//    @Excel(name = "二维码编号")
    private String fEwmbh;

    /** 安装时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "安装时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fAzsj;

    /** 维护商 */
//    @Excel(name = "维护商")
    private String fWhs;

    /** 出库单/FK() */
//    @Excel(name = "出库单/FK()")
    private String fCkdid;

    /** 领用机构 /组织机构表 */
//    @Excel(name = "领用机构 /组织机构表")
    private String fLyjg;

    /** 领用人/用户表 */
//    @Excel(name = "领用人/用户表")
    private String fLyr;

    /** 桩号 */
//    @Excel(name = "桩号")
    private String fZh;

    /** 管理单位/组织机构表 */
//    @Excel(name = "管理单位/组织机构表")
    private String fGldw;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fScrq;

    /** 保修到期日 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "保修到期日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fBxdqr;

    /** 操作日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "操作日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCzrq;

    /** 使用年限 */
//    @Excel(name = "使用年限")
    private String fSynx;

    /** 入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fRksj;

    /** 是否监控 0不监控 1监控 */
//    @Excel(name = "是否监控 0不监控 1监控")
    private String fSfjk;

    /** 上一状态 */
//    @Excel(name = "上一状态")
    private String fLastStatus;

    /** 数量单位 */
//    @Excel(name = "数量单位")
    private String fUnit;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCrdate;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fChdate;

    public void setfId(String fId)
    {
        this.fId = fId;
    }

    public String getfId()
    {
        return fId;
    }
    public void setfSbb(String fSbb)
    {
        this.fSbb = fSbb;
    }

    public String getfSbb()
    {
        return fSbb;
    }
    public void setfSbmc(String fSbmc)
    {
        this.fSbmc = fSbmc;
    }

    public String getfSbmc()
    {
        return fSbmc;
    }
    public void setfBrandid(String fBrandid)
    {
        this.fBrandid = fBrandid;
    }

    public String getfBrandid()
    {
        return fBrandid;
    }
    public void setfBrandmc(String fBrandmc)
    {
        this.fBrandmc = fBrandmc;
    }

    public String getfBrandmc()
    {
        return fBrandmc;
    }
    public void setfXlid(String fXlid)
    {
        this.fXlid = fXlid;
    }

    public String getfXlid()
    {
        return fXlid;
    }
    public void setfXlmc(String fXlmc)
    {
        this.fXlmc = fXlmc;
    }

    public String getfXlmc()
    {
        return fXlmc;
    }
    public void setfSn(String fSn)
    {
        this.fSn = fSn;
    }

    public String getfSn()
    {
        return fSn;
    }
    public void setfSblxType(String fSblxType)
    {
        this.fSblxType = fSblxType;
    }

    public String getfSblxType()
    {
        return fSblxType;
    }
    public void setfSbxhType(String fSbxhType)
    {
        this.fSbxhType = fSbxhType;
    }

    public String getfSbxhType()
    {
        return fSbxhType;
    }
    public void setfSblemc(String fSblemc)
    {
        this.fSblemc = fSblemc;
    }

    public String getfSblemc()
    {
        return fSblemc;
    }
    public void setfSbxhmc(String fSbxhmc)
    {
        this.fSbxhmc = fSbxhmc;
    }

    public String getfSbxhmc()
    {
        return fSbxhmc;
    }
    public void setfRkdId(String fRkdId)
    {
        this.fRkdId = fRkdId;
    }

    public String getfRkdId()
    {
        return fRkdId;
    }
    public void setfSsck(String fSsck)
    {
        this.fSsck = fSsck;
    }

    public String getfSsck()
    {
        return fSsck;
    }
    public void setfLacation(String fLacation)
    {
        this.fLacation = fLacation;
    }

    public String getfLacation()
    {
        return fLacation;
    }
    public void setfSydw(String fSydw)
    {
        this.fSydw = fSydw;
    }

    public String getfSydw()
    {
        return fSydw;
    }
    public void setfJbr(String fJbr)
    {
        this.fJbr = fJbr;
    }

    public String getfJbr()
    {
        return fJbr;
    }
    public void setfLong(String fLong)
    {
        this.fLong = fLong;
    }

    public String getfLong()
    {
        return fLong;
    }
    public void setfLat(String fLat)
    {
        this.fLat = fLat;
    }

    public String getfLat()
    {
        return fLat;
    }
    public void setfScs(String fScs)
    {
        this.fScs = fScs;
    }

    public String getfScs()
    {
        return fScs;
    }
    public void setfGmrq(Date fGmrq)
    {
        this.fGmrq = fGmrq;
    }

    public Date getfGmrq()
    {
        return fGmrq;
    }
    public void setfBxnx(String fBxnx)
    {
        this.fBxnx = fBxnx;
    }

    public String getfBxnx()
    {
        return fBxnx;
    }
    public void setfUnitPrice(String fUnitPrice)
    {
        this.fUnitPrice = fUnitPrice;
    }

    public String getfUnitPrice()
    {
        return fUnitPrice;
    }
    public void setfStatus(String fStatus)
    {
        this.fStatus = fStatus;
    }

    public String getfStatus()
    {
        return fStatus;
    }
    public void setfLastGxsj(Date fLastGxsj)
    {
        this.fLastGxsj = fLastGxsj;
    }

    public Date getfLastGxsj()
    {
        return fLastGxsj;
    }
    public void setfLastGxr(String fLastGxr)
    {
        this.fLastGxr = fLastGxr;
    }

    public String getfLastGxr()
    {
        return fLastGxr;
    }
    public void setfYwmbh(String fYwmbh)
    {
        this.fYwmbh = fYwmbh;
    }

    public String getfYwmbh()
    {
        return fYwmbh;
    }
    public void setfEwmbh(String fEwmbh)
    {
        this.fEwmbh = fEwmbh;
    }

    public String getfEwmbh()
    {
        return fEwmbh;
    }
    public void setfAzsj(Date fAzsj)
    {
        this.fAzsj = fAzsj;
    }

    public Date getfAzsj()
    {
        return fAzsj;
    }
    public void setfWhs(String fWhs)
    {
        this.fWhs = fWhs;
    }

    public String getfWhs()
    {
        return fWhs;
    }
    public void setfCkdid(String fCkdid)
    {
        this.fCkdid = fCkdid;
    }

    public String getfCkdid()
    {
        return fCkdid;
    }
    public void setfLyjg(String fLyjg)
    {
        this.fLyjg = fLyjg;
    }

    public String getfLyjg()
    {
        return fLyjg;
    }
    public void setfLyr(String fLyr)
    {
        this.fLyr = fLyr;
    }

    public String getfLyr()
    {
        return fLyr;
    }
    public void setfZh(String fZh)
    {
        this.fZh = fZh;
    }

    public String getfZh()
    {
        return fZh;
    }
    public void setfGldw(String fGldw)
    {
        this.fGldw = fGldw;
    }

    public String getfGldw()
    {
        return fGldw;
    }
    public void setfScrq(Date fScrq)
    {
        this.fScrq = fScrq;
    }

    public Date getfScrq()
    {
        return fScrq;
    }
    public void setfBxdqr(Date fBxdqr)
    {
        this.fBxdqr = fBxdqr;
    }

    public Date getfBxdqr()
    {
        return fBxdqr;
    }
    public void setfCzrq(Date fCzrq)
    {
        this.fCzrq = fCzrq;
    }

    public Date getfCzrq()
    {
        return fCzrq;
    }
    public void setfSynx(String fSynx)
    {
        this.fSynx = fSynx;
    }

    public String getfSynx()
    {
        return fSynx;
    }
    public void setfRksj(Date fRksj)
    {
        this.fRksj = fRksj;
    }

    public Date getfRksj()
    {
        return fRksj;
    }
    public void setfSfjk(String fSfjk)
    {
        this.fSfjk = fSfjk;
    }

    public String getfSfjk()
    {
        return fSfjk;
    }
    public void setfLastStatus(String fLastStatus)
    {
        this.fLastStatus = fLastStatus;
    }

    public String getfLastStatus()
    {
        return fLastStatus;
    }
    public void setfUnit(String fUnit)
    {
        this.fUnit = fUnit;
    }

    public String getfUnit()
    {
        return fUnit;
    }
    public void setfCrdate(Date fCrdate)
    {
        this.fCrdate = fCrdate;
    }

    public Date getfCrdate()
    {
        return fCrdate;
    }
    public void setfChdate(Date fChdate)
    {
        this.fChdate = fChdate;
    }

    public Date getfChdate()
    {
        return fChdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fId", getfId())
            .append("fSbb", getfSbb())
            .append("fSbmc", getfSbmc())
            .append("fBrandid", getfBrandid())
            .append("fBrandmc", getfBrandmc())
            .append("fXlid", getfXlid())
            .append("fXlmc", getfXlmc())
            .append("fSn", getfSn())
            .append("fSblxType", getfSblxType())
            .append("fSbxhType", getfSbxhType())
            .append("fSblemc", getfSblemc())
            .append("fSbxhmc", getfSbxhmc())
            .append("fRkdId", getfRkdId())
            .append("fSsck", getfSsck())
            .append("fLacation", getfLacation())
            .append("fSydw", getfSydw())
            .append("fJbr", getfJbr())
            .append("fLong", getfLong())
            .append("fLat", getfLat())
            .append("fScs", getfScs())
            .append("fGmrq", getfGmrq())
            .append("fBxnx", getfBxnx())
            .append("fUnitPrice", getfUnitPrice())
            .append("fStatus", getfStatus())
            .append("fLastGxsj", getfLastGxsj())
            .append("fLastGxr", getfLastGxr())
            .append("fYwmbh", getfYwmbh())
            .append("fEwmbh", getfEwmbh())
            .append("fAzsj", getfAzsj())
            .append("fWhs", getfWhs())
            .append("fCkdid", getfCkdid())
            .append("fLyjg", getfLyjg())
            .append("fLyr", getfLyr())
            .append("fZh", getfZh())
            .append("fGldw", getfGldw())
            .append("fScrq", getfScrq())
            .append("fBxdqr", getfBxdqr())
            .append("fCzrq", getfCzrq())
            .append("fSynx", getfSynx())
            .append("fRksj", getfRksj())
            .append("fSfjk", getfSfjk())
            .append("fLastStatus", getfLastStatus())
            .append("fUnit", getfUnit())
            .append("fCrdate", getfCrdate())
            .append("fChdate", getfChdate())
            .toString();
    }
}
