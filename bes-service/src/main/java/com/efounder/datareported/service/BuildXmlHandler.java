package com.efounder.datareported.service;

import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.datareported.model.build.*;
import com.framework.common.utils.xmlprocessor.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 建筑构建处理
 *
 * @author xiepufeng
 * @date 2020/11/11 11:15
 */
@Service
public class BuildXmlHandler
{
    /**
     * 文件存放位置
     */
    @Value("${data-centre.file-storage-location}")
    private String fileStorageLocation;

    public boolean buildBuildXmlHandle(BESDatecenterType besDatecenterType, List<BesBudingInformation> besBuildingInfoList, Date date)
    {
        if (besDatecenterType == null || besBuildingInfoList == null || besBuildingInfoList.isEmpty() || date == null)
        {
            return false;
        }

        BuildXmlModel buildXmlModel = new BuildXmlModel();

        // <common>
        CommonModel commonModel = new CommonModel();

        commonModel.setUploadDataCenterID(besDatecenterType.getF_DATACENTER_CODE()); // 数据中心代码

        String dateStr = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);

        commonModel.setCreateTime(dateStr); // 创建时间

        buildXmlModel.setCommon(commonModel);
        // </common>

        // <data>
        DataModel dataModel = new DataModel();

        // <DataCenterBaseInfo>
        DataCenterBaseInfoModel dataCenterBaseInfo = new DataCenterBaseInfoModel();

        dataCenterBaseInfo.setDataCenterID(besDatecenterType.getF_DATACENTER_CODE()); // 数据中心代码
        dataCenterBaseInfo.setDataCenterName(besDatecenterType.getF_DATACENTER_NAME()); // 数据中心名称
        dataCenterBaseInfo.setDataCenterType(Integer.parseInt(besDatecenterType.getF_DATACENTER_TYPE())); // 数据中心类型 1-数据中心 2-数据中心中转站
        dataCenterBaseInfo.setDataCenterManager(besDatecenterType.getF_GOVERNING_BODY()); // 数据中心管理单位名称
        dataCenterBaseInfo.setDataCenterDesc(besDatecenterType.getF_DATACENTER_DESCRIBE()); // 数据中心描述
        dataCenterBaseInfo.setDataCenterTel(besDatecenterType.getF_PHONE()); // 数据中心联系方式
        dataCenterBaseInfo.setDataCenterContract(besDatecenterType.getF_CONTACTPERSON()); // 业主联系人

        dataModel.setDataCenterBaseInfo(dataCenterBaseInfo);
        // </DataCenterBaseInfo>

        // <Build>

        List<BuildModel> buildModels = new ArrayList<>();

        List<String> buildIDList = new ArrayList<>();

        besBuildingInfoList.forEach((besBuildingInfo) -> {

            BuildModel buildModel = new BuildModel();

            buildModel.setId(besBuildingInfo.getfBudingCode()); // 节点代码
            buildIDList.add(besBuildingInfo.getfBudingCode());

            BuildBaseInfoModel buildBaseInfo = new BuildBaseInfoModel();// 建筑节点信息

            buildBaseInfo.setOperation("N");
            buildBaseInfo.setDataCenterID(besDatecenterType.getF_DATACENTER_CODE()); // 数据中心代码
            buildBaseInfo.setBuildName(besBuildingInfo.getfBudingName()); // 建筑物名称
            buildBaseInfo.setAliasName(besBuildingInfo.getfBudingLetter()); // 建筑物别名
            buildBaseInfo.setBuildOwner(besBuildingInfo.getfBudingOwner()); // 建筑物业主
            buildBaseInfo.setState(Integer.parseInt(besBuildingInfo.getfBudingDetection())); // 建筑物监测状态，1-启用监测  2-停用监测
            buildBaseInfo.setDistrictCode(besBuildingInfo.getfBudingNode()); // 建筑物的6位行政区代码
            buildBaseInfo.setBuildAddr(besBuildingInfo.getfBudingAddress()); // 建筑物地址
            buildBaseInfo.setBuildLong(besBuildingInfo.getfBudingLongitude()); // 建筑物所在经度
            buildBaseInfo.setBuildLat(besBuildingInfo.getfBudingLatitude()); // 建筑物所在纬度
            buildBaseInfo.setBuildYear(Integer.parseInt(besBuildingInfo.getfBudingDate())); // 建筑物建设时间
            buildBaseInfo.setUpFloor(Integer.parseInt(besBuildingInfo.getfBudingLayerson())); // 建筑物地上楼层数
            buildBaseInfo.setDownFloor(Integer.parseInt(besBuildingInfo.getfBudingLayersunder())); // 建筑物地下楼层数
            buildBaseInfo.setBuildFunc(besBuildingInfo.getfBudingFunction()); // 建筑物类型代码
            buildBaseInfo.setTotalArea(besBuildingInfo.getfBudingArea()); // 建筑物总面积
            buildBaseInfo.setAirArea(besBuildingInfo.getfAirconditionerArea()); // 空调面积
            buildBaseInfo.setHeatArea(besBuildingInfo.getfHeatingArea()); // 取暖面积
            buildBaseInfo.setAirType(besBuildingInfo.getfAirconditionerSystem()); // 空调系统形式
            buildBaseInfo.setHeatType(besBuildingInfo.getfHeatingSystem()); // 采暖系统形式
            buildBaseInfo.setBodyCoef(besBuildingInfo.getfBudingCoefficient()); // 建筑物体形系数
            buildBaseInfo.setStruType(besBuildingInfo.getfArchitecturalForm()); // 建筑结构形式
            buildBaseInfo.setWallMatType(besBuildingInfo.getfExteriorwallForm()); // 外墙材料形式
            buildBaseInfo.setWallWarmType(besBuildingInfo.getfExteriorwallInsulationForm()); // 外墙保温形式
            buildBaseInfo.setWallWinType(besBuildingInfo.getfBudingExteriorType()); // 建筑外窗类型
            buildBaseInfo.setGlassType(besBuildingInfo.getfBudingGlassType()); // 建筑玻璃类型
            buildBaseInfo.setWinFrameType(besBuildingInfo.getfMaterialType()); // 窗框材料类型
            buildBaseInfo.setIsStandard(besBuildingInfo.getfBenchmarking()); // 是否标杆建筑
            buildBaseInfo.setDesignDept(besBuildingInfo.getfSchemeDesign()); // 监测方案设计单位
            buildBaseInfo.setWorkDept(besBuildingInfo.getfEngineeringConstruction()); // 检测工程实施单位
            buildBaseInfo.setCreateTime(besBuildingInfo.getfEntryTime()); // 录入时间
            buildBaseInfo.setAcceptDate(dateStr); // 开始上传能耗监测数据的日期

            buildModel.setBuildBaseInfo(buildBaseInfo);

            buildModels.add(buildModel);

        });

        dataModel.setBuild(buildModels);
        // </Build>

        // <BuildGroup>
        BuildGroupModel buildGroupModel = new BuildGroupModel();

        buildGroupModel.setId(besDatecenterType.getF_DATACENTER_CODE());

        // <BuildGroupBaseInfo>

        BuildGroupBaseInfoModel buildGroupBaseInfoModel = new BuildGroupBaseInfoModel(); // 建筑群信息

        buildGroupBaseInfoModel.setOperation("N");
        buildGroupBaseInfoModel.setBuildGroupName(besDatecenterType.getF_BUILDGROUPNAME()); // 建筑群名称
        buildGroupBaseInfoModel.setGroupAliasName(besDatecenterType.getF_GROUPALIASNAME()); // 建筑群别名
        buildGroupBaseInfoModel.setGroupDesc(besDatecenterType.getF_GROUPDESC());

        buildGroupModel.setBuildGroupBaseInfo(buildGroupBaseInfoModel);

        // </BuildGroupBaseInfo>

        // <BuildGroupRelaInfo>

        BuildGroupRelateInfoModel buildGroupRelateInfoModel = new BuildGroupRelateInfoModel(); // 建筑群关联的建筑信息

        buildGroupRelateInfoModel.setOperation("N");
        buildGroupRelateInfoModel.setBuildID(buildIDList);

        buildGroupModel.setBuildGroupRelateInfo(buildGroupRelateInfoModel);
        // </BuildGroupRelaInfo>

        dataModel.setBuildGroup(buildGroupModel);

        // </BuildGroup>

        buildXmlModel.setData(dataModel);

        // </data>

        // 命名规则 上传文件目录名+build.xml

        return XmlUtils.createXmlFile(buildXmlModel, fileStorageLocation, DataRARHandler.getRarName(date, besDatecenterType.getF_DATACENTER_CODE()) + "Build");

    }

}
