package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * 建筑节点信息
 * @author xiepufeng
 * @date 2020/11/9 16:13
 */
public class BuildBaseInfoModel
{
    @AttrXml(name = "operation", isProperty = true)
    private String operation;

    /**
     * 数据中心代码
     */
    //
    @AttrXml(name = "F_DataCenterID")
    private String dataCenterID;

    /**
     * 建筑物名称
     */
    @AttrXml(name = "F_BuildName")
    private String buildName;

    /**
     * 建筑物别名
     */
    @AttrXml(name = "F_AliasName")
    private String aliasName;

    /**
     * 建筑物业主
     */
    @AttrXml(name = "F_BuildOwner")
    private String buildOwner;

    /**
     * 建筑物监测状态，1-启用监测  2-停用监测
     */
    @AttrXml(name = "F_State")
    private Integer state;

    /**
     * 建筑物的6位行政区代码
     */
    @AttrXml(name = "F_DistrictCode")
    private String districtCode;

    /**
     * 建筑物地址
     */
    @AttrXml(name = "F_BuildAddr")
    private String buildAddr;

    /**
     * 建筑物所在经度
     */
    @AttrXml(name = "F_BuildLong")
    private String buildLong;

    /**
     * 建筑物所在纬度
     */
    @AttrXml(name = "F_BuildLat")
    private String buildLat;

    /**
     * 建筑物建设时间
     */
    @AttrXml(name = "F_BuildYear")
    private Integer buildYear;

    /**
     * 建筑物地上楼层数
     */
    @AttrXml(name = "F_UpFloor")
    private Integer upFloor;

    /**
     * 建筑物地下楼层数
     */
    @AttrXml(name = "F_DownFloor")
    private Integer downFloor;



    /**
     * 建筑物类型代码 (
     * 为申请节点的第7位数，也就是建筑物节点中的那个字母
     * 					A-办公建筑        B-商场建筑       C-宾馆饭店建筑    D-文化教育建筑
     * 					E-医疗卫生建筑    F-体育建筑       G-综合建筑        H-其他建筑)
     */
    @AttrXml(name = "F_BuildFunc")
    private String buildFunc;

    /**
     * 建筑物总面积
     */
    @AttrXml(name = "F_TotalArea")
    private String totalArea;

    /**
     * 空调面积
     */
    @AttrXml(name = "F_AirArea")
    private String airArea;

    /**
     * 取暖面积
     */
    @AttrXml(name = "F_HeatArea")
    private String heatArea;

    /**
     * 空调系统形式
     * A-集中式全空气系统
     * B-风机盘管+风机系统
     * C-分体式空调或VRV的局部式机组系统
     * D-其他
     */
    @AttrXml(name = "F_AirType")
    private String airType;

    /**
     * 采暖系统形式
     * 	A-散热器采暖
     * 	B-地板辐射采暖
     * 	C-电辐射采暖
     * 	C-空调系统集中采暖
     * 	D-其他
     */
    @AttrXml(name = "F_HeatType")
    private String heatType;

    /**
     * 建筑物体形系数，非必填项
     */
    @AttrXml(name = "F_BodyCoef")
    private String bodyCoef;

    /**
     * 建筑结构形式
     * 	A-框架结构    B-框-剪结构    C-剪力墙结构   D-砖-混结构
     * 	E-钢结构      F-简体结构     G-木结构       H-其他
     */
    @AttrXml(name = "F_StruType")
    private String struType;

    /**
     * 外墙材料形式
     * 	A-砖         B-建筑砌块      C-板材墙体
     * 	D-复合墙板和墙体     E-玻璃幕墙      F-其他
     */
    @AttrXml(name = "F_WallMatType")
    private String wallMatType;

    /**
     * 外墙保温形式
     * 	A-内保温      B-外保温      C-夹心保温      D-其他
     */
    @AttrXml(name = "F_WallWarmType")
    private String wallWarmType;

    /**
     * 建筑外窗类型
     * 	A-单玻单层窗        B-单玻双层窗        C-单玻单层窗+单玻双层窗
     *  D-中空双层玻璃窗    E-中空三层玻璃窗    F-中空充惰性气体         G-其他
     */
    @AttrXml(name = "F_WallWinType")
    private String wallWinType;

    /**
     * 建筑玻璃类型
     * 	A-普通玻璃     B-镀膜玻璃      C-Low-e玻璃      D-其他
     */
    @AttrXml(name = "F_GlassType")
    private String glassType;

    /**
     * 窗框材料类型
     * 	A-钢窗      B-铝合金      C-木窗      D-断热窗框      E-塑窗      F-其他
     */
    @AttrXml(name = "F_WinFrameType")
    private String winFrameType;

    /**
     * 是否标杆建筑
     *  true（勾选为True）    false（不勾选为False）
     */
    @AttrXml(name = "F_IsStandard")
    private String isStandard;

    /**
     * 监测方案设计单位
     */
    @AttrXml(name = "F_DesignDept")
    private String designDept;

    /**
     * 检测工程实施单位
     */
    @AttrXml(name = "F_WorkDept")
    private String workDept;

    /**
     * 录入时间
     */
    @AttrXml(name = "F_CreateTime")
    private String createTime;

    /**
     * 开始上传能耗监测数据的日期
     */
    @AttrXml(name = "F_AcceptDate")
    private String acceptDate;

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getDataCenterID()
    {
        return dataCenterID;
    }

    public void setDataCenterID(String dataCenterID)
    {
        this.dataCenterID = dataCenterID;
    }

    public String getBuildName()
    {
        return buildName;
    }

    public void setBuildName(String buildName)
    {
        this.buildName = buildName;
    }

    public String getAliasName()
    {
        return aliasName;
    }

    public void setAliasName(String aliasName)
    {
        this.aliasName = aliasName;
    }

    public String getBuildOwner()
    {
        return buildOwner;
    }

    public void setBuildOwner(String buildOwner)
    {
        this.buildOwner = buildOwner;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(String districtCode)
    {
        this.districtCode = districtCode;
    }

    public String getBuildAddr()
    {
        return buildAddr;
    }

    public void setBuildAddr(String buildAddr)
    {
        this.buildAddr = buildAddr;
    }

    public String getBuildLong()
    {
        return buildLong;
    }

    public void setBuildLong(String buildLong)
    {
        this.buildLong = buildLong;
    }

    public String getBuildLat()
    {
        return buildLat;
    }

    public void setBuildLat(String buildLat)
    {
        this.buildLat = buildLat;
    }

    public Integer getBuildYear()
    {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear)
    {
        this.buildYear = buildYear;
    }

    public Integer getUpFloor()
    {
        return upFloor;
    }

    public void setUpFloor(Integer upFloor)
    {
        this.upFloor = upFloor;
    }

    public Integer getDownFloor()
    {
        return downFloor;
    }

    public void setDownFloor(Integer downFloor)
    {
        this.downFloor = downFloor;
    }

    public String getBuildFunc()
    {
        return buildFunc;
    }

    public void setBuildFunc(String buildFunc)
    {
        this.buildFunc = buildFunc;
    }

    public String getTotalArea()
    {
        return totalArea;
    }

    public void setTotalArea(String totalArea)
    {
        this.totalArea = totalArea;
    }

    public String getAirArea()
    {
        return airArea;
    }

    public void setAirArea(String airArea)
    {
        this.airArea = airArea;
    }

    public String getHeatArea()
    {
        return heatArea;
    }

    public void setHeatArea(String heatArea)
    {
        this.heatArea = heatArea;
    }

    public String getAirType()
    {
        return airType;
    }

    public void setAirType(String airType)
    {
        this.airType = airType;
    }

    public String getHeatType()
    {
        return heatType;
    }

    public void setHeatType(String heatType)
    {
        this.heatType = heatType;
    }

    public String getStruType()
    {
        return struType;
    }

    public void setStruType(String struType)
    {
        this.struType = struType;
    }

    public String getWallMatType()
    {
        return wallMatType;
    }

    public void setWallMatType(String wallMatType)
    {
        this.wallMatType = wallMatType;
    }

    public String getWallWinType()
    {
        return wallWinType;
    }

    public void setWallWinType(String wallWinType)
    {
        this.wallWinType = wallWinType;
    }

    public String getGlassType()
    {
        return glassType;
    }

    public void setGlassType(String glassType)
    {
        this.glassType = glassType;
    }

    public String getWinFrameType()
    {
        return winFrameType;
    }

    public void setWinFrameType(String winFrameType)
    {
        this.winFrameType = winFrameType;
    }

    public String getIsStandard()
    {
        return isStandard;
    }

    public void setIsStandard(String isStandard)
    {
        this.isStandard = isStandard;
    }

    public String getDesignDept()
    {
        return designDept;
    }

    public void setDesignDept(String designDept)
    {
        this.designDept = designDept;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getAcceptDate()
    {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate)
    {
        this.acceptDate = acceptDate;
    }

    public String getWallWarmType()
    {
        return wallWarmType;
    }

    public void setWallWarmType(String wallWarmType)
    {
        this.wallWarmType = wallWarmType;
    }

    public String getWorkDept()
    {
        return workDept;
    }

    public void setWorkDept(String workDept)
    {
        this.workDept = workDept;
    }

    public String getBodyCoef()
    {
        return bodyCoef;
    }

    public void setBodyCoef(String bodyCoef)
    {
        this.bodyCoef = bodyCoef;
    }
}
