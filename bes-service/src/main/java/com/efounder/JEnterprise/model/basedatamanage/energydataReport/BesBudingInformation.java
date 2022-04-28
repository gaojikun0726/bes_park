package com.efounder.JEnterprise.model.basedatamanage.energydataReport;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 
 * 
 * @author ycc
 * 
 * @date 2018-08-29
 */
public class BesBudingInformation implements BaseEntity<String>{
    /**
     * 建筑信息主键
     */
    private String fBudingId;

    /**
     * 建筑代码
     */
    @ExcelVOAttribute(name = "建筑代码", column = "A")
    private String fBudingCode;

    /**
     * 数据中心id 关联数据中心表
     */
    @ExcelVOAttribute(name = " 数据中心id", column = "B", combo = { "数据中心", "数据中转站"})
    private String fCenterId;

    /**
     * 建筑名称
     */
    @ExcelVOAttribute(name = "建筑名称", column = "C")
    private String fBudingName;



    /**
     * 建筑别名
     */
    @ExcelVOAttribute(name = "建筑别名", column = "D")
    private String fBudingLetter;

    /**
     * 建筑业主
     */
    @ExcelVOAttribute(name = "建筑业主", column = "E")
    private String fBudingOwner;

    /**
     * 建筑检测状态
     */
    @ExcelVOAttribute(name = "建筑检测状态", column = "F",combo= {"启用检测","停用检测"})
    private String fBudingDetection;

    /**
     * 节点编码（行政代码）
     */
    @ExcelVOAttribute(name = "行政区代码", column = "G")
    private String fBudingNode;

    /**
     * 建筑地址
     */
    @ExcelVOAttribute(name = "建筑地址", column = "H")
    private String fBudingAddress;

    /**
     * 建筑坐标 经度
     */
    @ExcelVOAttribute(name = "建筑坐标经度", column = "I")
    private String fBudingLongitude;

    /**
     * 建筑坐标 纬度
     */
    @ExcelVOAttribute(name = "建筑坐标纬度", column = "J")
    private String fBudingLatitude;

    /**
     * 地上建筑层数
     */
    @ExcelVOAttribute(name = "地上建筑层数", column = "K")
    private String fBudingLayerson;

    /**
     * 地下建筑层数
     */
    @ExcelVOAttribute(name = "地下建筑层数", column = "L")
    private String fBudingLayersunder;

    /**
     * 建筑物类型
     */
    @ExcelVOAttribute(name = "建筑物类型", column = "M",combo= {"办公建筑","商场建筑","宾馆饭店建筑","文化教育建筑","医疗卫生建筑"
            ,"体育建筑","综合建筑","其他建筑"})
    private String fBudingFunction;

    /**
     * 建筑面积
     */
    @ExcelVOAttribute(name = "建筑面积", column = "N")
    private String fBudingArea;

    /**
     * 空调面积
     */
    @ExcelVOAttribute(name = "空调面积", column = "O")
    private String fAirconditionerArea;

    /**
     * 采暖面积
     */
    @ExcelVOAttribute(name = "采暖面积", column = "P")
    private String fHeatingArea;

    /**
     * 空调系统形式
     */
    @ExcelVOAttribute(name = "空调系统形式", column = "Q",combo= {"集中式全空气系统","风机盘管＋新风系统","分体式空调或VRV的局部式机组系统","其他"})

    private String fAirconditionerSystem;
    /**
     * 采暖系统形式
     */
    @ExcelVOAttribute(name = "采暖系统形式", column = "R",combo= {"散热器采暖","地板辐射采暖","电辐射采暖","空调系统集中供暖","其他"})
    private String fHeatingSystem;

    /**
     * 建筑体形系数
     */
    @ExcelVOAttribute(name = "建筑体形系数", column = "S")
    private String fBudingCoefficient;

    /**
     * 建筑结构形式
     */
    @ExcelVOAttribute(name = "建筑结构形式", column = "T",combo= {"框架结构","框-剪结构","剪力墙结构","砖混结构","钢结构","筒体结构","木结构","其他"})
    private String fArchitecturalForm;

    /**
     * 外墙材料形式
     */
    @ExcelVOAttribute(name = "外墙材料形式", column = "U",combo= {"砖","建筑砌块","板材墙体","复合墙板和墙体","玻璃幕墙","其它"})
    private String fExteriorwallForm;

    /**
     * 外墙保温形式
     */
    @ExcelVOAttribute(name = "外墙保温形式", column = "V",combo= {"内保温","外保温","夹芯保温","其它"})
    private String fExteriorwallInsulationForm;

    /**
     * 建筑外窗类型
     */
    @ExcelVOAttribute(name = "建筑外窗类型", column = "W",combo= {"单玻单层窗","单玻双层窗","单玻单层窗+单玻双层窗","中空双层玻璃窗","中空三层玻璃窗","中空充惰性气体","其他"})
    private String fBudingExteriorType;

    /**
     * 建筑玻璃类型
     */
    @ExcelVOAttribute(name = "建筑玻璃类型", column = "X",combo= {"普通玻璃","镀膜玻璃","Low-e玻璃","其它"})
    private String fBudingGlassType;

    /**
     * 窗框材料类型
     */
    @ExcelVOAttribute(name = "窗框材料类型", column = "Y",combo= {"钢窗","铝合金","木窗","断热窗框","塑钢","其它"})
    private String fMaterialType;

    /**
     * 是否标杆建筑
     */
    @ExcelVOAttribute(name = "是否标杆建筑", column = "Z",combo= {"是","否"})
    private String fBenchmarking;

    /**
     * 监测方案设计单位
     */
    @ExcelVOAttribute(name = "监测方案设计单位", column = "AA")
    private String fSchemeDesign;

    /**
     * 监测工程实施单位
     */
    @ExcelVOAttribute(name = "监测工程实施单位", column = "AB")
    private String fEngineeringConstruction;

    /**
     * 录入时间
     */
    @ExcelVOAttribute(name = "录入时间", column = "AC")
    private String fEntryTime;

    /**
     * 开始检测日期
     */
    @ExcelVOAttribute(name = "开始检测日期", column = "AD")
    private String fDetectionTime;

    /**
     * 建筑物建设时间
     */
    @ExcelVOAttribute(name = "建筑物建设时间", column = "AE")
    private String fBudingDate;

    /**
     * 设计单位
     * @return
     */
    @ExcelVOAttribute(name = "设计单位", column = "AF")
    private String fDesignOrganization;
    /**
     * 业主单位
     */
    @ExcelVOAttribute(name = "业主单位", column = "AG")
    private String fBudingOwnerCompany;

    /**
     * 建筑 联系人
     */
    @ExcelVOAttribute(name = "建筑 联系人", column = "AH")
    private String fBudingContact;

    /**
     * 联系方式
     */
    @ExcelVOAttribute(name = "联系方式", column = "AI")
    private String fContactInformation;

    /**
     * 用能人数
     */
    @ExcelVOAttribute(name = "用能人数", column = "AJ")
    private String fBudingYnrs;

    /**
     * 供热方式
     */
    @ExcelVOAttribute(name = "供热方式", column = "AK",combo= {"集中供热","分户采暖","无供热"})
    private String fHeatingMode;

    /**
     * 建筑高度
     */
    @ExcelVOAttribute(name = "建筑高度", column = "AL")
    private String fBudingHeight;

    /**
     * 供冷方式
     */
    @ExcelVOAttribute(name = "供冷方式", column = "AM",combo= {"集中供冷","分户采冷","无供冷"})
    private String fCoolingMode;

    /**
     * 执行节能标准
     */
    @ExcelVOAttribute(name = "执行节能标准", column = "AN",combo= {"未执行节能标准","节能50%标准","节能60%标准","节能65%以上标准"})
    private String fSavingStandard;

    /**
     * 是否节能改造
     */
    @ExcelVOAttribute(name = "是否节能改造", column = "AO",combo= {"是","否"})
    private String fSavingTransformation;

    /**
     * 建筑状态
     */
    @ExcelVOAttribute(name = "建筑状态", column = "AP",combo= {"建成","封顶","主体结构施工","基础施工","基坑施工"})
    private String fBudingState;

    /**
     * 是否政府办公楼
     */
    @ExcelVOAttribute(name = "是否政府办公楼", column = "AQ",combo= {"是","否"})
    private String fBudingGovernment;

    /**
     * 工程联系人
     */
    @ExcelVOAttribute(name = "工程联系人", column = "AR")
    private String fEngineeringContacts;

    /**
     * 工程联系人 办公电话
     */
    @ExcelVOAttribute(name = "工程联系人 办公电话", column = "AS")
    private String fEngineeringContactsBgdh;

    /**
     * 工程联系人 移动电话
     */
    @ExcelVOAttribute(name = "工程联系人 移动电话", column = "AT")
    private String fEngineeringContactsYddh;

    /**
     * 建筑总人口
     */
    @ExcelVOAttribute(name = "建筑总人口", column = "AU")
    private String fBudingPopulation;

    /**
     * 监测面积
     */
    @ExcelVOAttribute(name = "监测面积", column = "AV")
    private String fMonitoringArea;

    /**
     * 工程验收日期
     */
    @ExcelVOAttribute(name = "工程验收日期", column = "AW")
    private String fAcceptanceTime;

    /**
     * 描述
     * @return
     */
    @ExcelVOAttribute(name = "描述", column = "AX")
    private String fDescribe;//Add by yangjx at 20200421 for 能耗上传生成建筑信息中的 F_GroupDesc标签


    /**
     * 系统软件
     */
//    @ExcelVOAttribute(name = "系统软件", column = "AW")
    private String fSystemSoftware;

    /**
     * 采集器型号
     */
//    @ExcelVOAttribute(name = "采集器型号", column = "AX")
    private String fCollectorType;

    /**
     * 所属县区
     */
//    @ExcelVOAttribute(name = "所属县区", column = "H", combo = { "历下区", "市中区", "槐荫区", "天桥区"
//    		, "历城区", "长清区", "平阴县", "济阳县", "商河县", "章丘区"})
    private String fCountyArea;


    /**
     * 竣工年份
     */
//    @ExcelVOAttribute(name = "竣工年份", column = "N")
    private String fDateCompletion;

    /**
     * 创建操作人名称
     */
//    @ExcelVOAttribute(name = "创建操作人名称", column = "AP")
    private String fOperatorName;

    /**
     * 修改时间
     */
//    @ExcelVOAttribute(name = "修改时间", column = "BB")
    private String fUpdateTime;
    
    /**
     * 检测状态名称
     * @return
     */
//    @ExcelVOAttribute(name = "检测状态名称", column = "BC")
    private String JCZTMC;

    public String getfBudingId() {
        return fBudingId;
    }

    public void setfBudingId(String fBudingId) {
        this.fBudingId = fBudingId == null ? null : fBudingId.trim();
    }

    public String getfBudingCode() {
        return fBudingCode;
    }

    public void setfBudingCode(String fBudingCode) {
        this.fBudingCode = fBudingCode == null ? null : fBudingCode.trim();
    }

    public String getfCenterId() {
        return fCenterId;
    }

    public void setfCenterId(String fCenterId) {
        this.fCenterId = fCenterId == null ? null : fCenterId.trim();
    }

    public String getfBudingName() {
        return fBudingName;
    }

    public void setfBudingName(String fBudingName) {
        this.fBudingName = fBudingName == null ? null : fBudingName.trim();
    }

    public String getfBudingNode() {
        return fBudingNode;
    }

    public void setfBudingNode(String fBudingNode) {
        this.fBudingNode = fBudingNode == null ? null : fBudingNode.trim();
    }

    public String getfBudingLetter() {
        return fBudingLetter;
    }

    public void setfBudingLetter(String fBudingLetter) {
        this.fBudingLetter = fBudingLetter == null ? null : fBudingLetter.trim();
    }

    public String getfBudingOwner() {
        return fBudingOwner;
    }

    public void setfBudingOwner(String fBudingOwner) {
        this.fBudingOwner = fBudingOwner == null ? null : fBudingOwner.trim();
    }

    public String getfBudingOwnerCompany() {
        return fBudingOwnerCompany;
    }

    public void setfBudingOwnerCompany(String fBudingOwnerCompany) {
        this.fBudingOwnerCompany = fBudingOwnerCompany == null ? null : fBudingOwnerCompany.trim();
    }


    public String getfBudingContact() {
        return fBudingContact;
    }

    public void setfBudingContact(String fBudingContact) {
        this.fBudingContact = fBudingContact == null ? null : fBudingContact.trim();
    }

    public String getfContactInformation() {
        return fContactInformation;
    }

    public void setfContactInformation(String fContactInformation) {
        this.fContactInformation = fContactInformation == null ? null : fContactInformation.trim();
    }

    public String getfBudingDetection() {
        return fBudingDetection;
    }

    public void setfBudingDetection(String fBudingDetection) {
        this.fBudingDetection = fBudingDetection == null ? null : fBudingDetection.trim();
    }

    public String getfBudingAddress() {
        return fBudingAddress;
    }

    public void setfBudingAddress(String fBudingAddress) {
        this.fBudingAddress = fBudingAddress == null ? null : fBudingAddress.trim();
    }

    public String getfBudingState() {
        return fBudingState;
    }

    public void setfBudingState(String fBudingState) {
        this.fBudingState = fBudingState == null ? null : fBudingState.trim();
    }


    public String getfBudingDate() {
        return fBudingDate;
    }

    public void setfBudingDate(String fBudingDate) {
        this.fBudingDate = fBudingDate == null ? null : fBudingDate.trim();
    }

    public String getfBudingLongitude() {
        return fBudingLongitude;
    }

    public void setfBudingLongitude(String fBudingLongitude) {
        this.fBudingLongitude = fBudingLongitude == null ? null : fBudingLongitude.trim();
    }

    public String getfBudingLatitude() {
        return fBudingLatitude;
    }

    public void setfBudingLatitude(String fBudingLatitude) {
        this.fBudingLatitude = fBudingLatitude == null ? null : fBudingLatitude.trim();
    }

    public String getfBudingLayerson() {
        return fBudingLayerson;
    }

    public void setfBudingLayerson(String fBudingLayerson) {
        this.fBudingLayerson = fBudingLayerson == null ? null : fBudingLayerson.trim();
    }

    public String getfBudingLayersunder() {
        return fBudingLayersunder;
    }

    public void setfBudingLayersunder(String fBudingLayersunder) {
        this.fBudingLayersunder = fBudingLayersunder == null ? null : fBudingLayersunder.trim();
    }

    public String getfBudingHeight() {
        return fBudingHeight;
    }

    public void setfBudingHeight(String fBudingHeight) {
        this.fBudingHeight = fBudingHeight == null ? null : fBudingHeight.trim();
    }

    public String getfBudingFunction() {
        return fBudingFunction;
    }

    public void setfBudingFunction(String fBudingFunction) {
        this.fBudingFunction = fBudingFunction == null ? null : fBudingFunction.trim();
    }

    public String getfBudingArea() {
        return fBudingArea;
    }

    public void setfBudingArea(String fBudingArea) {
        this.fBudingArea = fBudingArea == null ? null : fBudingArea.trim();
    }

    public String getfAirconditionerArea() {
        return fAirconditionerArea;
    }

    public void setfAirconditionerArea(String fAirconditionerArea) {
        this.fAirconditionerArea = fAirconditionerArea == null ? null : fAirconditionerArea.trim();
    }

    public String getfHeatingArea() {
        return fHeatingArea;
    }

    public void setfHeatingArea(String fHeatingArea) {
        this.fHeatingArea = fHeatingArea == null ? null : fHeatingArea.trim();
    }

    public String getfBudingYnrs() {
        return fBudingYnrs;
    }

    public void setfBudingYnrs(String fBudingYnrs) {
        this.fBudingYnrs = fBudingYnrs == null ? null : fBudingYnrs.trim();
    }

    public String getfHeatingMode() {
        return fHeatingMode;
    }

    public void setfHeatingMode(String fHeatingMode) {
        this.fHeatingMode = fHeatingMode == null ? null : fHeatingMode.trim();
    }

    public String getfCoolingMode() {
        return fCoolingMode;
    }

    public void setfCoolingMode(String fCoolingMode) {
        this.fCoolingMode = fCoolingMode == null ? null : fCoolingMode.trim();
    }

    public String getfAirconditionerSystem() {
        return fAirconditionerSystem;
    }

    public void setfAirconditionerSystem(String fAirconditionerSystem) {
        this.fAirconditionerSystem = fAirconditionerSystem == null ? null : fAirconditionerSystem.trim();
    }

    public String getfHeatingSystem() {
        return fHeatingSystem;
    }

    public void setfHeatingSystem(String fHeatingSystem) {
        this.fHeatingSystem = fHeatingSystem == null ? null : fHeatingSystem.trim();
    }

    public String getfBudingCoefficient() {
        return fBudingCoefficient;
    }

    public void setfBudingCoefficient(String fBudingCoefficient) {
        this.fBudingCoefficient = fBudingCoefficient == null ? null : fBudingCoefficient.trim();
    }

    public String getfArchitecturalForm() {
        return fArchitecturalForm;
    }

    public void setfArchitecturalForm(String fArchitecturalForm) {
        this.fArchitecturalForm = fArchitecturalForm == null ? null : fArchitecturalForm.trim();
    }

    public String getfExteriorwallForm() {
        return fExteriorwallForm;
    }

    public void setfExteriorwallForm(String fExteriorwallForm) {
        this.fExteriorwallForm = fExteriorwallForm == null ? null : fExteriorwallForm.trim();
    }

    public String getfExteriorwallInsulationForm() {
        return fExteriorwallInsulationForm;
    }

    public void setfExteriorwallInsulationForm(String fExteriorwallInsulationForm) {
        this.fExteriorwallInsulationForm = fExteriorwallInsulationForm == null ? null : fExteriorwallInsulationForm.trim();
    }

    public String getfBudingExteriorType() {
        return fBudingExteriorType;
    }

    public void setfBudingExteriorType(String fBudingExteriorType) {
        this.fBudingExteriorType = fBudingExteriorType == null ? null : fBudingExteriorType.trim();
    }

    public String getfBudingGlassType() {
        return fBudingGlassType;
    }

    public void setfBudingGlassType(String fBudingGlassType) {
        this.fBudingGlassType = fBudingGlassType == null ? null : fBudingGlassType.trim();
    }

    public String getfMaterialType() {
        return fMaterialType;
    }

    public void setfMaterialType(String fMaterialType) {
        this.fMaterialType = fMaterialType == null ? null : fMaterialType.trim();
    }

    public String getfSavingStandard() {
        return fSavingStandard;
    }

    public void setfSavingStandard(String fSavingStandard) {
        this.fSavingStandard = fSavingStandard == null ? null : fSavingStandard.trim();
    }

    public String getfSavingTransformation() {
        return fSavingTransformation;
    }

    public void setfSavingTransformation(String fSavingTransformation) {
        this.fSavingTransformation = fSavingTransformation == null ? null : fSavingTransformation.trim();
    }

    public String getfBudingGovernment() {
        return fBudingGovernment;
    }

    public void setfBudingGovernment(String fBudingGovernment) {
        this.fBudingGovernment = fBudingGovernment == null ? null : fBudingGovernment.trim();
    }

    public String getfBenchmarking() {
        return fBenchmarking;
    }

    public void setfBenchmarking(String fBenchmarking) {
        this.fBenchmarking = fBenchmarking == null ? null : fBenchmarking.trim();
    }

    public String getfSchemeDesign() {
        return fSchemeDesign;
    }

    public void setfSchemeDesign(String fSchemeDesign) {
        this.fSchemeDesign = fSchemeDesign == null ? null : fSchemeDesign.trim();
    }

    public String getfEngineeringConstruction() {
        return fEngineeringConstruction;
    }

    public void setfEngineeringConstruction(String fEngineeringConstruction) {
        this.fEngineeringConstruction = fEngineeringConstruction == null ? null : fEngineeringConstruction.trim();
    }

    public String getfEngineeringContacts() {
        return fEngineeringContacts;
    }

    public void setfEngineeringContacts(String fEngineeringContacts) {
        this.fEngineeringContacts = fEngineeringContacts == null ? null : fEngineeringContacts.trim();
    }

    public String getfEngineeringContactsBgdh() {
        return fEngineeringContactsBgdh;
    }

    public void setfEngineeringContactsBgdh(String fEngineeringContactsBgdh) {
        this.fEngineeringContactsBgdh = fEngineeringContactsBgdh == null ? null : fEngineeringContactsBgdh.trim();
    }

    public String getfEngineeringContactsYddh() {
        return fEngineeringContactsYddh;
    }

    public void setfEngineeringContactsYddh(String fEngineeringContactsYddh) {
        this.fEngineeringContactsYddh = fEngineeringContactsYddh == null ? null : fEngineeringContactsYddh.trim();
    }

    public String getfBudingPopulation() {
        return fBudingPopulation;
    }

    public void setfBudingPopulation(String fBudingPopulation) {
        this.fBudingPopulation = fBudingPopulation == null ? null : fBudingPopulation.trim();
    }

    public String getfMonitoringArea() {
        return fMonitoringArea;
    }

    public void setfMonitoringArea(String fMonitoringArea) {
        this.fMonitoringArea = fMonitoringArea == null ? null : fMonitoringArea.trim();
    }



    public String getfEntryTime() {
        return fEntryTime;
    }

    public void setfEntryTime(String fEntryTime) {
        this.fEntryTime = fEntryTime == null ? null : fEntryTime.trim();
    }

    public String getfDetectionTime() {
        return fDetectionTime;
    }

    public void setfDetectionTime(String fDetectionTime) {
        this.fDetectionTime = fDetectionTime;
    }


	public String getJCZTMC() {
		return JCZTMC;
	}

	public void setJCZTMC(String jCZTMC) {
		JCZTMC = jCZTMC;
	}

    public String getfDesignOrganization() {
        return fDesignOrganization;
    }

    public void setfDesignOrganization(String fDesignOrganization) {
        this.fDesignOrganization = fDesignOrganization;
    }

    public String getfDescribe() {
        return fDescribe;
    }

    public void setfDescribe(String fDescribe) {
        this.fDescribe = fDescribe == null ? null : fDescribe.trim();
    }

    public String getfAcceptanceTime() {
        return fAcceptanceTime;
    }

    public void setfAcceptanceTime(String fAcceptanceTime) {
        this.fAcceptanceTime = fAcceptanceTime == null ? null : fAcceptanceTime.trim();
    }

    public String getfSystemSoftware() {
        return fSystemSoftware;
    }

    public void setfSystemSoftware(String fSystemSoftware) {
        this.fSystemSoftware = fSystemSoftware;
    }

    public String getfCollectorType() {
        return fCollectorType;
    }

    public void setfCollectorType(String fCollectorType) {
        this.fCollectorType = fCollectorType;
    }

    public String getfCountyArea() {
        return fCountyArea;
    }

    public void setfCountyArea(String fCountyArea) {
        this.fCountyArea = fCountyArea;
    }

    public String getfDateCompletion() {
        return fDateCompletion;
    }

    public void setfDateCompletion(String fDateCompletion) {
        this.fDateCompletion = fDateCompletion;
    }

    public String getfOperatorName() {
        return fOperatorName;
    }

    public void setfOperatorName(String fOperatorName) {
        this.fOperatorName = fOperatorName;
    }

    public String getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(String fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}