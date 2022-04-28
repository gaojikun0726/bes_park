package com.efounder.JEnterprise.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.schedule.CreateXmlToUploadMapper;
import com.efounder.JEnterprise.model.schedule.BuildBaseInfo;
import com.efounder.JEnterprise.model.schedule.CommonAndDataCenterBaseInfo;
import com.efounder.JEnterprise.model.schedule.EnergyItemHourResult;
import com.efounder.JEnterprise.service.CreateXmlToUploadService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("CreateXmlToUploadService")
public class CreateXmlToUploadServiceImpl implements CreateXmlToUploadService {

	@Resource
	private CreateXmlToUploadMapper createXmlToUploadMapper;

	@Override
	public ISSPReturnObject createBuildXml() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Document doc = DocumentHelper.createDocument();
			Element rootElement = DocumentHelper.createElement("root");
			doc.setRootElement(rootElement);
			List<CommonAndDataCenterBaseInfo> commonAndDataCenterBaseInfolist = createXmlToUploadMapper.getCommonAndDataCenterBaseInfo();
			List<BuildBaseInfo> buildBaseInfoList = createXmlToUploadMapper.getBuildBaseInfo();
			
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("YYYYMMddHH");
			String dateString= sdf.format(date);
			String dataResultId = sdf2.format(date);
			String lsh = "";//园区文件命名前部的节点id
			
			for(int i=0;i<commonAndDataCenterBaseInfolist.size();i++) {
				CommonAndDataCenterBaseInfo cbi= commonAndDataCenterBaseInfolist.get(i);
				String dataCenterIdstr = cbi.getF_buding_code();
				lsh= dataCenterIdstr;
				/*Start通用信息段*/
				Element common=rootElement.addElement("common");
				Element dataCenterId=common.addElement("UploadDataCenterID");//项目节点代码
				Element createTime=common.addElement("CreateTime");//文件创建时间
				dataCenterId.addText(cbi.getF_buding_code());
				createTime.addText(dateString);
				/*End通用信息段*/
				/*Start数据段*/
				//Start <DataCenterBaseInfo>
				Element data = rootElement.addElement("data");
				Element dataCenterBaseInfo = data.addElement("DataCenterBaseInfo");
				Element f_DataCenterID = dataCenterBaseInfo.addElement("F_DataCenterID");//项目节点ID
				Element f_DataCenterName = dataCenterBaseInfo.addElement("F_DataCenterName");//项目名称
				Element f_DataCenterType = dataCenterBaseInfo.addElement("F_DataCenterType");//项目类型
				Element f_DataCenterManager = dataCenterBaseInfo.addElement("F_DataCenterManager");//项目业主
				Element f_DataCenterDesc = dataCenterBaseInfo.addElement("F_DataCenterDesc");//项目描述
				Element f_DataCenterContact = dataCenterBaseInfo.addElement("F_DataCenterContact");//业主联系人
				Element f_DataCenterTel = dataCenterBaseInfo.addElement("F_DataCenterTel");//业主联系电话
				f_DataCenterID.addText(cbi.getF_buding_code());
				f_DataCenterName.addText(cbi.getF_datacenter_name());
				f_DataCenterType.addText(cbi.getF_datacenter_type());
				f_DataCenterManager.addText(cbi.getF_governing_body());
				f_DataCenterDesc.addText(cbi.getF_datacenter_describe());
				f_DataCenterContact.addText(cbi.getF_contactperson());
				f_DataCenterTel.addText(cbi.getF_phone());
				//End <DataCenterBaseInfo>
				//Start <Build>
				for(int j=0;j<buildBaseInfoList.size();j++) {
					BuildBaseInfo bbi = buildBaseInfoList.get(j);
					Element build = data.addElement("Build");
					build.addAttribute("id", bbi.getF_buding_code());
					/*建筑物编码，此处id由省数据中心统一分配 */
					Element buildBaseInfo = build.addElement("BuildBaseInfo");
					buildBaseInfo.addAttribute("operation", "U");
					Element baseInfo_DataCenterID = buildBaseInfo.addElement("F_DataCenterID");
					Element f_BuildName = buildBaseInfo.addElement("F_BuildName");
					Element f_AliasName = buildBaseInfo.addElement("F_AliasName");
					Element f_BuildOwner = buildBaseInfo.addElement("F_BuildOwner");
					Element f_State = buildBaseInfo.addElement("F_State");
					Element f_DistrictCode = buildBaseInfo.addElement("F_DistrictCode");
					Element f_BuildAddr = buildBaseInfo.addElement("F_BuildAddr");
					Element f_BuildLong = buildBaseInfo.addElement("F_BuildLong");
					Element f_BuildLat = buildBaseInfo.addElement("F_BuildLat");
					Element f_BuildYear = buildBaseInfo.addElement("F_BuildYear");
					Element f_UpFloor = buildBaseInfo.addElement("F_UpFloor");
					Element f_DownFloor = buildBaseInfo.addElement("F_DownFloor");
					Element f_BuildFunc = buildBaseInfo.addElement("F_BuildFunc");
					Element f_TotalArea = buildBaseInfo.addElement("F_TotalArea");
					Element f_AirArea = buildBaseInfo.addElement("F_AirArea");
					Element f_HeatArea = buildBaseInfo.addElement("F_HeatArea");
					Element f_AirType = buildBaseInfo.addElement("F_AirType");
					Element f_HeatType = buildBaseInfo.addElement("F_HeatType");
					Element f_BodyCoef = buildBaseInfo.addElement("F_BodyCoef");
					Element f_StruType = buildBaseInfo.addElement("F_StruType");
					Element f_WallMatType = buildBaseInfo.addElement("F_WallMatType");
					Element f_WallWarmType = buildBaseInfo.addElement("F_WallWarmType");
					Element f_WallWinType = buildBaseInfo.addElement("F_WallWinType");
					Element f_GlassType = buildBaseInfo.addElement("F_GlassType");
					Element f_WinFrameType = buildBaseInfo.addElement("F_WinFrameType");
					Element f_IsStandard = buildBaseInfo.addElement("F_IsStandard");
					Element f_DesignDept = buildBaseInfo.addElement("F_DesignDept");
					Element f_WorkDept = buildBaseInfo.addElement("F_WorkDept");
					Element f_CreateTime = buildBaseInfo.addElement("F_CreateTime");
					Element f_AcceptDate = buildBaseInfo.addElement("F_AcceptDate");
					baseInfo_DataCenterID.addText(cbi.getF_buding_code());//注意，此处用的cbi的查出的当作的节点编码
					f_BuildName.addText(bbi.getF_buding_name());
					f_AliasName.addText(bbi.getF_buding_letter());
					f_BuildOwner.addText(bbi.getF_buding_owner());
					f_State.addText(bbi.getF_state());
					f_DistrictCode.addText(bbi.getF_districtcode());
					f_BuildAddr.addText(bbi.getF_buding_address());
					f_BuildLong.addText(bbi.getF_buding_longitude());
					f_BuildLat.addText(bbi.getF_buding_latitude());
					f_BuildYear.addText(bbi.getF_buding_date());
					f_UpFloor.addText(bbi.getF_buding_layerson());
					f_DownFloor.addText(bbi.getF_buding_layersunder());
					f_BuildFunc.addText(bbi.getF_buding_function());//建筑功能 demo示例为ABCDEFGZ  数据库字段为2,1 不符合要求
					f_TotalArea.addText(bbi.getF_buding_area());
					f_AirArea.addText(bbi.getF_airconditioner_area());
					f_HeatArea.addText(bbi.getF_heating_area());
					f_AirType.addText(bbi.getF_airtype());
					f_HeatType.addText(bbi.getF_heattype());
					f_BodyCoef.addText(bbi.getF_bodycoef());
					f_StruType.addText(bbi.getF_strutype());
					f_WallMatType.addText(bbi.getF_wallmattype());
					f_WallWarmType.addText(bbi.getF_wallwarmtype());
					f_WallWinType.addText(bbi.getF_wallwintype());
					f_GlassType.addText(bbi.getF_glasstype());
					f_WinFrameType.addText(bbi.getF_winframetype());
					f_IsStandard.addText(bbi.getF_isstandard());
					f_DesignDept.addText(bbi.getF_design_organization());//监测方案设计单位
					f_WorkDept.addText(bbi.getF_engineering_construction());
					f_CreateTime.addText(bbi.getF_entry_time());
					if(bbi.getF_acceptance_time() == null ) {
						f_AcceptDate.addText("");
					}else {
						f_AcceptDate.addText(bbi.getF_acceptance_time());
					}
				}
				//end <Build>
				//Start <BuildGroup>   
				Element buildGroup = data.addElement("BuildGroup");
				buildGroup.addAttribute("id", cbi.getF_buding_code());
				//Start <BuildGroupBaseInfo>
				Element buildGroupBaseInfo = buildGroup.addElement("BuildGroupBaseInfo");
				buildGroupBaseInfo.addAttribute("operation", "U");
				Element f_BuildGroupName = buildGroupBaseInfo.addElement("F_BuildGroupName");//建筑群名称
				Element f_GroupAliasName = buildGroupBaseInfo.addElement("F_GroupAliasName");//建筑群字母别称
				Element f_GroupDesc = buildGroupBaseInfo.addElement("F_GroupDesc");//建筑群描述
				f_BuildGroupName.addText(cbi.getF_buding_name());
				f_GroupAliasName.addText(cbi.getF_buding_letter());
				f_GroupDesc.addText(cbi.getF_describe());
				//End <BuildGroupBaseInfo>
				//Start <BuildGroupRelaInfo>
				Element buildGroupRelaInfo = buildGroup.addElement("BuildGroupRelaInfo");
				buildGroupRelaInfo.addAttribute("operation", "U");
				for(int k=0;k<buildBaseInfoList.size();k++) {
					BuildBaseInfo bbi = buildBaseInfoList.get(k);
					Element f_BuildID = buildGroupRelaInfo.addElement("F_BuildID");
					f_BuildID.addText(bbi.getF_buding_code());
				}
				//End <BuildGroupRelaInfo>
				//End <BuildGroup>
				/*End数据段*/
			}
			File fileDir =new File("D:/UploadXml/"+lsh+dataResultId);
			//如果文件夹不存在则创建
			if  (!fileDir .exists()  && !fileDir .isDirectory())
			{
				fileDir .mkdirs();
			}
			File file=new File("D:/UploadXml/"+lsh+dataResultId+"/"+lsh+dataResultId+"Build.xml");
			FileOutputStream fos = new FileOutputStream(file);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(fos,format);
			writer.write(doc);
			returnObject.setMsg("生成建筑XML文件信息成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setMsg("生成建筑XML文件信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 *生成能耗XML文件信息 
	 */
	@Override
	public ISSPReturnObject createEnergyXml() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try{
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd HH:00:00");
			SimpleDateFormat sdf2 = new SimpleDateFormat("YYYYMMddHH");
			Calendar calendar = Calendar.getInstance();
			/* HOUR_OF_DAY 指示一天中小时 */
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
			String dateString= sdf.format(date);//当前时间
			String startHour = sdf1.format(calendar.getTime());//采集开始时间
			String endHour = sdf1.format(date);//采集结束时间
			String dateResultId = sdf2.format(date);//流水号所用时间串
			Document doc = DocumentHelper.createDocument();
			Element rootElement = DocumentHelper.createElement("root");
			doc.setRootElement(rootElement);
			//1.查出所处主园区节点编号  370112A027   select F_BUDING_CODE from bes_buding_information LIMIT 1
			String budingCode = createXmlToUploadMapper.getBudingCode();
			String lsh="";//文件命名前部的园区节点id
			Element common=rootElement.addElement("common");
			Element dataCenterId=common.addElement("UploadDataCenterID");//项目节点代码
			Element createTime=common.addElement("CreateTime");//文件创建时间
			lsh = budingCode;
			dataCenterId.addText(budingCode);
			createTime.addText(dateString);
			File fileDir =new File("D:/UploadXml/"+lsh+dateResultId);
			//如果文件夹不存在则创建
			if  (!fileDir .exists()  && !fileDir .isDirectory())
			{
				fileDir.mkdirs();
			}
			File file=new File("D:/UploadXml/"+lsh+dateResultId+"/"+lsh+dateResultId+"Energy.xml");
			FileOutputStream fos = new FileOutputStream(file);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(fos,format);
			//2.查出所有园区节点编号
			/*SELECT F_NODE_CODE FROM BES_PARK_COPY WHERE F_YQBH <>'0000' AND F_YQBH <>'0006' ORDER BY F_NODE_CODE
			 * 370112A023	370112A024	370112A025	370112A026	370112A027*/
			List<String> listNodeCode = createXmlToUploadMapper.getNodeCodeList();//注意：此sql暂时只应用于廉政
			
			//3.查出能耗分类分项编码统帅
			/*0101:电,0102:水,0103:天然气*/
			List<String> listZlbhTS = createXmlToUploadMapper.getZlbhList();//
			
			//循环内：
			List<String> listElectricity = new ArrayList<String>();//电所对应的楼号
			List<String> listWater = new ArrayList<String>();//水所对应的楼号
			List<String> listGas = new ArrayList<String>();//天然气锁对应的楼号
			int flag = 1;
			for(int i=0;i<listZlbhTS.size();i++) {
				String zlbh = listZlbhTS.get(i);
				//电：010101:1号楼	010102：2号楼	010103：3号楼	010104：4号楼	010105：5号楼
				//水：010201:1号楼	010202：2号楼	010203：3号楼	010204：4号楼	010205：5号楼
				//气：010301:1号楼	010302：2号楼	010303：3号楼	010304：4号楼	010305：5号楼
				List<String> listZlbh = createXmlToUploadMapper.getZlbhListByZlbhTs(zlbh);
				for(int j=0;j<listZlbh.size();j++) {
					if(flag<6) {
						listElectricity.add(listZlbh.get(j));
					}
					if(flag<11&&flag>5) {
						listWater.add(listZlbh.get(j));
					}
					if(flag>10) {
						listGas.add(listZlbh.get(j));
					}
					flag++;
				}
			}
			Element data = rootElement.addElement("data");
			//根据电、水、天然气查询出来的楼号信息，查询出相应的数据
			/*select bd.F_DATA,bd.F_ZLBH,bd.f_cjsj,bc.F_EQUIPMENT_SET from bes_branch_data bd ,(SELECT  BC.F_ZLBH,BC.F_ZLMC,BC.F_EQUIPMENT_SET FROM( 
				SELECT @ids as _ids, ( SELECT @ids := GROUP_CONCAT(F_ZLBH) FROM BES_BRANCH_CONF  WHERE FIND_IN_SET(F_PZLBH, @ids) ) as cids, @l := @l+1 as level 
				FROM BES_BRANCH_CONF, (SELECT @ids :='010101', @l := 0 ) b WHERE @ids IS NOT NULL ) F_ZLBH, BES_BRANCH_CONF BC 
				WHERE FIND_IN_SET(BC.F_ZLBH, F_ZLBH._ids) and f_js<>'6'
				ORDER BY  F_ZLBH,F_EQUIPMENT_SET) bc where bd.F_ZLBH = bc.F_ZLBH and bd.F_type='0' and f_cjsj = '2019-11-20 18:00:00'
				union
				SELECT BBD.F_DATA,BBD.F_ZLBH,BBD.F_CJSJ,BBC.F_EQUIPMENT_SET FROM BES_BRANCH_DATA BBD,BES_BRANCH_CONF BBC
				 WHERE BBD.F_ZLBH=BBC.F_ZLBH AND BBD.F_ZLBH = '010201' AND BBD.F_TYPE='0' AND F_CJSJ = '2019-11-20 18:00:00'
				union
				SELECT BBD.F_DATA,BBD.F_ZLBH,BBD.F_CJSJ,BBC.F_EQUIPMENT_SET FROM BES_BRANCH_DATA BBD,BES_BRANCH_CONF BBC
				 WHERE BBD.F_ZLBH=BBC.F_ZLBH AND BBD.F_ZLBH = '010301' AND BBD.F_TYPE='0' AND F_CJSJ = '2019-11-20 18:00:00'
			*/
			for(int i=0;i<5;i++) {
				String electricityZlbh = listElectricity.get(i);
				String waterZlbh = listWater.get(i);
				String gasZlbh = listGas.get(i);
				//根据电、水、天然气查询出来的楼号信息，查询出相应的数据
				List<EnergyItemHourResult>listEnergyItemHourResult = createXmlToUploadMapper.getEnergyItemHourResultList(electricityZlbh,waterZlbh,gasZlbh,startHour);
				
				Element build = data.addElement("Build");
				String nodeCode1 = listNodeCode.get(i);
				String hourResultId = nodeCode1+dateResultId;
				build.addAttribute("id", listNodeCode.get(i));
				for(int k=0;k<listEnergyItemHourResult.size();k++) {
					Element energyItemHourResult = build.addElement("EnergyItemHourResult");
					Element f_HourResultID = energyItemHourResult.addElement("F_HourResultID");
					Element f_EnergyItemCode = energyItemHourResult.addElement("F_EnergyItemCode");
					Element f_StartHour = energyItemHourResult.addElement("F_StartHour");
					Element f_EndHour = energyItemHourResult.addElement("F_EndHour");
					Element f_HourValue = energyItemHourResult.addElement("F_HourValue");
					Element f_HourEquValue = energyItemHourResult.addElement("F_HourEquValue");
					Element f_State = energyItemHourResult.addElement("F_State");
					f_HourResultID.addText(hourResultId);
					f_EnergyItemCode.addText(listEnergyItemHourResult.get(k).getF_equipment_set());
					f_StartHour.addText(startHour);
					f_EndHour.addText(endHour);
					f_HourValue.addText(listEnergyItemHourResult.get(k).getF_data());
					DecimalFormat df2 = new DecimalFormat("0.0000");//格式化小数
			        String hourEquValue= df2.format(Double.parseDouble(listEnergyItemHourResult.get(k).getF_data())*0.1229);//返回的是String类型
					//String hourEquValue = String.valueOf(Double.parseDouble(rs7.getString("F_DATA"))*0.1229);
					f_HourEquValue.addText(hourEquValue);
					f_State.addText("1");
				}
			}
			writer.write(doc);
			
		}catch(Exception e) {
			e.printStackTrace();
			returnObject.setMsg("生成能耗XML文件信息失败");
			returnObject.setStatus("0");
		}
		return returnObject;
		
	}
	
}
