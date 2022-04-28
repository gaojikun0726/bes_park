package com.efounder.datareported.service;

import com.efounder.JEnterprise.initializer.EnergyTypeCache;
import com.efounder.JEnterprise.initializer.SubitemConfigCache;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.datareported.model.build.CommonModel;
import com.efounder.datareported.model.energy.BuildEnergyModel;
import com.efounder.datareported.model.energy.EnergyDataModel;
import com.efounder.datareported.model.energy.EnergyItemHourResultModel;
import com.efounder.datareported.model.energy.EnergyXmlModel;
import com.framework.common.utils.xmlprocessor.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 能耗数据构建处理
 *
 * @author xiepufeng
 * @date 2020/11/13 8:49
 */
@Service
public class EnergyXmlHandler
{
    /**
     * 文件存放位置
     */
    @Value("${data-centre.file-storage-location}")
    private String fileStorageLocation;

    @Resource
    private SubitemConfigCache subitemConfigCache;

    @Resource
    private EnergyTypeCache energyTypeCache;

    /**
     * 构建 Energy.xml 文件
     * @param besDatecenterType 数据中心信息
     * @param energyData 能耗数据
     * @param date 日期
     * @return
     */
    public boolean buildEnergyXmlHandle(BESDatecenterType besDatecenterType, List<BesBudingInformation> besBuildingInfoList, Map<String, Map<String, Double>> energyData, Date date)
    {
        if (besDatecenterType == null
                || energyData == null
                || energyData.isEmpty()
                || date == null
                || besBuildingInfoList == null
                || besBuildingInfoList.isEmpty())
        {
            return false;
        }

        Map<String, String> budingCodeBudingIdMap = new HashMap<>();

        for (BesBudingInformation besBudingInformation : besBuildingInfoList) {
            budingCodeBudingIdMap.put(besBudingInformation.getfBudingCode(), besBudingInformation.getfBudingId());
        }

        EnergyXmlModel energyXmlModel = new EnergyXmlModel();

        // <common>
        CommonModel commonModel = new CommonModel();

        commonModel.setUploadDataCenterID(besDatecenterType.getF_DATACENTER_CODE()); // 数据中心代码

        String dateStr = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);

        commonModel.setCreateTime(dateStr); // 创建时间

        energyXmlModel.setCommon(commonModel);
        // </common>

        // <data>
        EnergyDataModel energyDataModel = new EnergyDataModel();

        // <Build>
        List<BuildEnergyModel> buildList = new ArrayList<>();

        String endHour = new SimpleDateFormat("YYYY-MM-dd HH:00:00").format(date);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, - 1);

        String startHour = new SimpleDateFormat("YYYY-MM-dd HH:00:00").format(calendar.getTime());

        energyData.forEach((budingCode, subitemDataMap) ->
        {
            BuildEnergyModel buildEnergyModel = new BuildEnergyModel();

            buildEnergyModel.setId(budingCode);

            String hourResultID = budingCode + new SimpleDateFormat("YYYYMMddHH").format(date);

            // <EnergyItemHourResult>
            List<EnergyItemHourResultModel> energyItemHourResultModelList = new ArrayList<>();

            subitemDataMap.forEach((fSubitemCode, value) ->
            {
                EnergyItemHourResultModel energyItemHourResultModel = new EnergyItemHourResultModel();

                BESSubitemConf besSubitemConf = subitemConfigCache.getCachedElementByBudingIdAndSubitemCode(budingCodeBudingIdMap.get(budingCode), fSubitemCode);

                if (besSubitemConf == null)
                {
                    return;
                }

                String fNybh =  besSubitemConf.getfNybh();
                String subitemCode =  besSubitemConf.getfSubitemCode();

                String fCoalAmount = energyTypeCache.getCachedElement(fNybh).getfCoalAmount();

                Double hourEquValue = Double.parseDouble(fCoalAmount) * value;

                energyItemHourResultModel.setHourResultID(hourResultID);
                energyItemHourResultModel.setEnergyItemCode(subitemCode);
                energyItemHourResultModel.setStartHour(startHour);
                energyItemHourResultModel.setEndHour(endHour);
                energyItemHourResultModel.setHourValue(String.format("%.4f", value));
                energyItemHourResultModel.setHourEquValue(String.format("%.4f", hourEquValue));
                energyItemHourResultModel.setState(1);

                energyItemHourResultModelList.add(energyItemHourResultModel);

            });

            buildEnergyModel.setEnergyItemHourResult(energyItemHourResultModelList);
            // </EnergyItemHourResult>

            buildList.add(buildEnergyModel);

        });
        // </Build>
        energyDataModel.setBuild(buildList);
        // </data>

        energyXmlModel.setData(energyDataModel);

        return XmlUtils.createXmlFile(energyXmlModel, fileStorageLocation, DataRARHandler.getRarName(date, besDatecenterType.getF_DATACENTER_CODE()) + "Energy");

    }
}
