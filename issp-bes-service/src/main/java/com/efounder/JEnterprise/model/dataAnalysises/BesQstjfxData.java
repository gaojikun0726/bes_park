package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 杨超
 * @version 创建时间：2018年10月25日 上午9:43:41
 * @parameter 返回动态拼装table/echars--bean类
 * @version 1.0
 */
public class BesQstjfxData implements BaseEntity<String>{

	private List<String> btlist;//标题list
	
	private List<Map<String, Object>> nrlist;// 内容list

    private String time_start;// 本期起始时间
    private String time_end;// 本期终止时间
    private String tqtime_start;// 同期起始时间
    private String tqtime_end;// 同期终止时间
    private String sjkld;// 时间颗粒度
    private String dwhs;// 单位换算 起始就是查询的动态列
    private String nhlx;// 能耗类型
    private String zlid;// 支路id 逗号隔开
    private String zlname;// 支路名称 字符串
    private String[] zlmc;// 支路名称 逗号隔开
    private String tblx;// 图表类型
    private String[] zlbh;// 多个zhid分割成数组

    /**
     *
     * @Description: 增加支路编号集合
     *
     * @auther: wanghongjie
     * @date: 14:20 2021/5/11
     * @param:
     * @return:
     *
     */
    private List<String> zlbhs;//支路编号集合
    /**
     *
     * @Description: 增加统计类型  分:0、时：1、日：2、月：3、年
     *
     * @auther: wanghongjie
     * @date: 14:08 2021/5/11
     * @param: []
     * @return: java.util.List<java.lang.String>
     *
     */
    private String fType;

	public List<String> getBtlist() {
		return btlist;
	}

	public void setBtlist(List<String> btlist) {
		this.btlist = btlist;
	}

	public List<Map<String, Object>> getNrlist() {
		return nrlist;
	}

	public void setNrlist(List<Map<String, Object>> nrlist) {
		this.nrlist = nrlist;
	}

    public String getTqtime_start() {
        return tqtime_start;
    }

    public void setTqtime_start(String tqtime_start) {
        this.tqtime_start = tqtime_start;
    }

    public String getTqtime_end() {
        return tqtime_end;
    }

    public void setTqtime_end(String tqtime_end) {
        this.tqtime_end = tqtime_end;
    }

    public String getSjkld() {
        return sjkld;
    }

    public void setSjkld(String sjkld) {
        this.sjkld = sjkld;
    }

    public String getDwhs() {
        return dwhs;
    }

    public void setDwhs(String dwhs) {
        this.dwhs = dwhs;
    }

    public String getNhlx() {
        return nhlx;
    }

    public void setNhlx(String nhlx) {
        this.nhlx = nhlx;
    }

    public String getZlid() {
        return zlid;
    }

    public void setZlid(String zlid) {
        this.zlid = zlid;
    }

    public String getTblx() {
        return tblx;
    }

    public void setTblx(String tblx) {
        this.tblx = tblx;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String[] getZlbh() {
        return zlbh;
    }

    public void setZlbh(String[] zlbh) {
        this.zlbh = zlbh;
    }

    public String[] getZlmc() {
        return zlmc;
    }

    public void setZlmc(String[] zlmc) {
        this.zlmc = zlmc;
    }

    public String getZlname() {
        return zlname;
    }

    public void setZlname(String zlname) {
        this.zlname = zlname;
    }

    public List<String> getZlbhs() {
        return zlbhs;
    }

    public void setZlbhs(List<String> zlbhs) {
        this.zlbhs = zlbhs;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
