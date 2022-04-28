package com.efounder.JEnterprise.collectorJob;

import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.util.AlarmUtil;
import com.core.common.util.G4Utils;
import com.core.common.util.LEMSUtil;
import com.core.common.util.MailUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BESSubAlarmMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BesNoteAlarmMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BesSysConfMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesEnergyDataMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.model.collectorJob.BesNoteAlarm;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyData;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 计算当前周期功率线程类
 * @Description:
 * @author LvSihan
 * @version: 
 * @date:2018年8月28日
 */
@Component
public class CalculateCurrentEnergyThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(CalculateCurrentEnergyThread.class);

	private String parkId;//园区id
	private String parkName;//园区名字
	private Calendar time;
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript"); 
	
	private static CalculateCurrentEnergyThread calculateCurrentEnergyThread;
	@Autowired
	private BESCalculateDataMapper besCalculateDataMapper;
	@Autowired
	private BESEnergyTypeMapper besEnergyTypeMapper;
	@Autowired
	private BesEnergyDataMapper besEnergyDataMapper;
	@Autowired
	private BESSubAlarmMapper besSubAlarmMapper;
	@Autowired
	private BesWainingRealInfoMapper besWainingRealInfoMapper;
	@Autowired
	private BesSysConfMapper besSysConfMapper;
	@Autowired
	private BesNoteAlarmMapper besNoteAlarmMapper;
	@PostConstruct
	public void init() {
		calculateCurrentEnergyThread = this;
		calculateCurrentEnergyThread.besCalculateDataMapper = this.besCalculateDataMapper;
		calculateCurrentEnergyThread.besEnergyTypeMapper = this.besEnergyTypeMapper;
		calculateCurrentEnergyThread.besEnergyDataMapper = this.besEnergyDataMapper;
		calculateCurrentEnergyThread.besSubAlarmMapper = this.besSubAlarmMapper;
		calculateCurrentEnergyThread.besWainingRealInfoMapper = this.besWainingRealInfoMapper;
		calculateCurrentEnergyThread.besSysConfMapper = this.besSysConfMapper;
		calculateCurrentEnergyThread.besNoteAlarmMapper = this.besNoteAlarmMapper;
	}
	public CalculateCurrentEnergyThread () {

	}


	
	@SuppressWarnings("unchecked")
	public void run() {
		//所有能源下
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dto dto = new BaseDto();
		dto.put("parkid", parkId);
		List<Map<String,Object>> lstEnergys = calculateCurrentEnergyThread.besCalculateDataMapper.queryEnergyTypeByParkid(parkId);
		Map<String,Object> dtoLastTime = calculateCurrentEnergyThread.besCalculateDataMapper.queryLastTime();
		
		if (dtoLastTime == null){
			return ;
		}
		
		for (Map<String, Object> energy : lstEnergys){
			//查找每种能源下的时间点
			dto.put("energyid", energy.get("id"));
			BESEnergyType returnData = calculateCurrentEnergyThread.besEnergyTypeMapper.getEnergyType((String) energy.get("id")); 
			String energyname = returnData.getfNymc();
			float power = 0.0f;
			log.info("开始计算园区=【"+parkName+"】，能源=【"+energyname+"】，能源id=【" + energy.get("id")+"】");
			//如果和上次时间相差太大,不作为一个轮询周期
			try{
				Date lastTime = fmt.parse((String) dtoLastTime.get("F_CJSJ"));
				long diff = time.getTime().getTime() - lastTime.getTime();
				long minutes = diff/(1000* 60);
				if (minutes < 120){
					//取出来这两次的数据
					dto.put("parentid", parkId);
					List<Map<String,Object>> lstCurData = calculateCurrentEnergyThread.besCalculateDataMapper.queryCurData((String)energy.get("id"),parkId);
					List<Map<String,Object>> lstLastData = calculateCurrentEnergyThread.besCalculateDataMapper.queryLastData((String)energy.get("id"),parkId);
					
					if (lstCurData == null || lstCurData.isEmpty() || lstLastData == null || lstLastData.isEmpty()){
						continue;
					}
					//计算两个时间点数据差之和
					for (Map<String, Object> cur : lstCurData){
						String id = (String) cur.get("meteruuid");
						
						for (Map<String, Object> last : lstLastData){
							if (last.get("meteruuid").equals(id)){
								float lastData = mapToFloat(last, "data");
								float curData = mapToFloat(cur, "data");
								
								if (curData > lastData){
									power += (curData - lastData);
								}
								break;
							}
						}
					}
				}
				//保存当前功率
				saveEnergy((String)energy.get("id"), GetTimeString(time), "-1", floatToString(power));
			}
			catch(Exception e){
			}
			log.info("结束计算园区=【"+parkName+"】，能源=【"+energyname+"】，能源id=【" + energy.get("id")+"】");
		}
		log.info("结束计算系统内所有园区当前采集周期功率");
		log.info("开始检测系统内所有园区支路报警");
//		subAlarm();
		log.info("结束检测系统内所有园区的支路报警");
	}
	
	/**
	 * dto转float，含异常处理
	 * @param dto
	 * @param key
	 * @return
	 */
	public float dtoToFloat(Dto dto, String key){
		if (dto == null){
			return 0.0f;
		}
		try{
			float data = Float.parseFloat(dto.getAsString(key));
			return data;
		}
		catch(Exception e){
			return 0.0f;
		}
	}

	/**
	 * map转float，含异常处理
	 * @param last
	 * @param key
	 * @return
	 */
	public float mapToFloat(Map<String, Object> last, String key){
		if (last == null){
			return 0.0f;
		}
		try{
			float data = Float.parseFloat((String) last.get(key));
			return data;
		}
		catch(Exception e){
			return 0.0f;
		}
	}
	/**
	 * 获取时间字符串
	 * @param time
	 * @return
	 */
	public String GetTimeString(Calendar time){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(time.getTime());
	}
	
	/**
	 * float转string，含精确小数点
	 * @param data
	 * @return
	 */
	public String floatToString(float data){
		return String.format("%.02f", data);
	}
	
	/**
	 * 保存总能源数据
	 * @param energyid
	 * @param time
	 * @param type
	 * @param data
	 */
	public void saveEnergy(String energyid, String time, String type, String data){		
		BesEnergyData besEnergyData = new BesEnergyData();
		besEnergyData.setfNybh(energyid);
		besEnergyData.setfCjsj(time);
		besEnergyData.setfType(type);
		besEnergyData.setfData(Double.parseDouble(data));
		besEnergyData.setfYqbh(parkId);
		
		//根据园区id、时间、能源id、时间类型查询是否存在
		BesEnergyData queryres = calculateCurrentEnergyThread.besEnergyDataMapper.queryEnergyExists(besEnergyData);
		if (queryres.getRows().equals("0")){
			//新建记录并写入
			besEnergyData.setfId(UUIDUtil.getRandom32BeginTimePK());
			calculateCurrentEnergyThread.besEnergyDataMapper.saveEnergyData(besEnergyData);
		} else {
			//更新记录
			calculateCurrentEnergyThread.besEnergyDataMapper.updateEnergy(besEnergyData);
		}
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void subAlarm() {
		log.info("开始检测园区=【"+parkName+"】的支路报警");
		Dto pDto = new BaseDto();
		pDto.put("parkid", parkId);
		//查询支路信息及报警规则
		List<Map<String,Object>> deptList = calculateCurrentEnergyThread.besSubAlarmMapper.SelectAlarmSub(parkId);
		for(Map<String, Object> dto : deptList){//遍历报警规则
			List listData = new ArrayList();
			String fZlbh = (String)dto.get("F_ZLBH");
			//查询能源类型
			String energyTypeName = calculateCurrentEnergyThread.besSubAlarmMapper.selectEnergyTypeName((String)dto.get("F_NYBH"));
			List<Map<String,Object>> list = calculateCurrentEnergyThread.besSubAlarmMapper.SelectAlarmParmeSub((String)dto.get("F_ID"));
			String url="";
			for(int j=0;j<list.size();j++){//遍历报警规则用到的电能参数
				Map<String,Object> dtoParmeId = list.get(j);
				//查询出电能参数保存到list
				Map<String, Object> dtoData = calculateCurrentEnergyThread.besSubAlarmMapper
						.SelectAlarmParmeData((String)dtoParmeId.get("F_AMMSYS_NAME"),(String) dtoParmeId.get("F_ELEDNBH"));
				if (G4Utils.isNotEmpty(dtoData)) {
					listData.add(dtoData.get("F_DATA"));
				}
				/*//获取报警具体位置
				url = dtoSubName.getAsString("branch_name");
				//判断这条记录是否还有父节点，有，就将其查询出来
				Dto dtoName = new BaseDto();
				dtoName.put("parentid", dtoSubName.getAsString("parentid"));
				while(((Dto)g4Dao.queryForObject("SubAlarm.selectAllId",dtoName))!=null){
					Dto dtoLinShi = (Dto)g4Dao.queryForObject("SubAlarm.selectAllId",dtoName);
					url = dtoLinShi.getAsString("branch_name")+"/"+url;
					dtoName.put("parentid", dtoLinShi.getAsString("parentid"));
				}*/
				
			}
			float nData = 0.0f;
			String operator = (String)dto.get("F_OPERATOR");
			for(int k = listData.size(); k > 0; k--){
				String strData = (String) listData.get(k - 1);
				String strOperator = "$" + k;
				operator = operator.replace(strOperator,strData);
			}
			try {
				nData = Float.parseFloat(jse.eval(operator).toString());
			} catch (ScriptException e) {
				//e.printStackTrace();
			}
						
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
			String strTime = dateFormat.format(now); 
			String id = UUIDUtil.getRandom32BeginTimePK();
			//要添加的报警信息
			BesWainingInfo besWaringInfo = new BesWainingInfo();
			besWaringInfo.setFYqbh((String) dto.get("F_YQBH"));
			besWaringInfo.setFGuid(id);
			besWaringInfo.setFLoction(energyTypeName + "/" + url + "(" + fZlbh + ")");//报警位置
			besWaringInfo.setFAlarmName((String) dto.get("F_ALERTNAME"));//报警名称
			besWaringInfo.setFActualValue( String.valueOf(nData));//实际值
			besWaringInfo.setFATime(strTime);//报警时间
			besWaringInfo.setFOpearation("1");//是否处理
			besWaringInfo.setFType("0");//信息类型

			if (dto.get("F_RANGETYPE").equals("0")) {
				float nomore = Float.parseFloat((String) dto.get("F_NOMORE"));
				if (nData > nomore) {										
					besWaringInfo.setFPlanValue(String.valueOf(nomore));//计划值
					besWaringInfo.setFTipInfo("实际参数大于计划值");//提示信息
//					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);		
					//判断是否存在相同的未处理报警
					AlarmUtil.insertWarningReal(besWaringInfo);
					LEMSUtil.pushAlarm((String) dto.get("F_YQBH"));
					alarmSubWays(fZlbh, besWaringInfo);
				}
			} else if (dto.get("F_RANGETYPE").equals("1")) {
				float noless = Float.parseFloat((String) dto.get("F_NOLESS"));
				if(nData<noless){
					besWaringInfo.setFPlanValue(String.valueOf(noless));//计划值
					besWaringInfo.setFTipInfo("实际参数小于计划值");//提示信息
//					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
					//判断是否存在相同的未处理报警
					AlarmUtil.insertWarningReal(besWaringInfo);
					LEMSUtil.pushAlarm((String)dto.get("F_YQBH"));
					alarmSubWays(fZlbh, besWaringInfo);
				}
			} else if (dto.get("F_RANGETYPE").equals("2")) {
				float nomore = Float.parseFloat((String)dto.get("F_NOMORE"));//最大值
				float noless = Float.parseFloat((String)dto.get("F_NOLESS"));//最小
				if(nData>nomore||nData<noless){
					besWaringInfo.setFPlanValue(String.valueOf(noless+","+nomore));//计划值
					besWaringInfo.setFTipInfo("实际参数不在计划值范围");//提示信息
//					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
					//判断是否存在相同的未处理报警
					AlarmUtil.insertWarningReal(besWaringInfo);
					LEMSUtil.pushAlarm((String)dto.get("F_YQBH"));
					alarmSubWays(fZlbh, besWaringInfo);
				}
			} else {
				float equal = Float.parseFloat((String)dto.get("F_EQUAL"));//最小
				if(equal != nData){
					besWaringInfo.setFPlanValue( (String) dto.get("F_EQUAL"));//计划值
					besWaringInfo.setFTipInfo("实际参数不等于计划值");//提示信息
//					calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
					//判断是否存在相同的未处理报警
					AlarmUtil.insertWarningReal(besWaringInfo);
					LEMSUtil.pushAlarm((String)dto.get("F_YQBH"));
					alarmSubWays(fZlbh, besWaringInfo);
				}
			}
		}
		LEMSUtil.log("结束检测园区=【"+parkName+"】的支路报警");
	}
	
	// 支路报警方式
	public void alarmSubWays(String id, BesWainingInfo besWaringInfo) {
		Dto pDto = LEMSUtil.getMailAddByParkid(parkId);
		String mails = pDto.getAsString("F_EMAIL");
		String phones = pDto.getAsString("F_PHONE");
		String alarmOperation = calculateCurrentEnergyThread.besSysConfMapper.selectSysConfAlarm();
		String alerttype = calculateCurrentEnergyThread.besSubAlarmMapper.selectSubAlarm(id);//查询支路报警类型
		if (alerttype == null) {
			return;
		}
		BesWainingInfo besWaringInfoError = new BesWainingInfo();
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
		String strTime = dateFormat.format(now);
		// 支路邮件报警
		if (alarmOperation.equals("1")) {
			if (!alerttype.equals("")) {
				if (alerttype.equals("1")) {
					try {
						if (mails.indexOf("$$$$") > -1) {
							String[] rervice_host = mails.split("$$$$");
							for (int i = 0; i < rervice_host.length; i++) {
								MailUtil.sendMail(LEMSUtil.lemService_host(), LEMSUtil.lemService_host(),
										LEMSUtil.lemService_pass(), rervice_host[i], "报警信息",
										besWaringInfo.getFTipInfo());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						String idd = UUIDUtil.getRandom32BeginTimePK();
						besWaringInfoError.setFYqbh(besWaringInfo.getFYqbh());
						besWaringInfoError.setFGuid(idd);
						besWaringInfoError.setFLoction("系统报警");//报警位置
						besWaringInfoError.setFAlarmName("发送报警邮件失败");//报警名称
						besWaringInfoError.setFActualValue("");//实际值
						besWaringInfoError.setFATime(strTime);//报警时间
						besWaringInfoError.setFOpearation("1");//是否处理
						besWaringInfoError.setFType("3");//信息类型
//						calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfoError);
						//判断是否存在相同的未处理报警
						AlarmUtil.insertWarningReal(besWaringInfo);
					}
				}
			}
		}
		// 支路短信报警
		if (alarmOperation.equals("0")) {
			if (!alerttype.equals("")) {
				if (alerttype.equals("0")) {
					// 短信接口
					if (phones.indexOf("$$$$") > -1) {
						BesNoteAlarm besNoteAlarm = new BesNoteAlarm();
						String[] phone = phones.split("$$$$");
						for (int i = 0; i < phone.length; i++) {
							String id1 = UUIDUtil.getRandom32BeginTimePK();
							besNoteAlarm.setfGuid(id1);
							besNoteAlarm.setfState("1");
							besNoteAlarm.setfPhone(phone[i]);
							besNoteAlarm.setfText(besWaringInfo.getFTipInfo());
							calculateCurrentEnergyThread.besNoteAlarmMapper.insertDuanXinInfo(besNoteAlarm);							
						}
					}
				}
			}
		}
		// 支路邮件和短信报警
		if (alarmOperation.equals("2")) {
			if (!alerttype.equals("")) {
				if (alerttype.equals("2")) {
					try {
						String[] rervice_host = LEMSUtil.lemRervice_host().split(",");
						for (int i = 0; i < rervice_host.length; i++) {
							MailUtil.sendMail(LEMSUtil.lemService_host(), LEMSUtil.lemService_host(),
									LEMSUtil.lemService_pass(), rervice_host[i], "报警信息", besWaringInfo.getFTipInfo());
						}
					} catch (Exception e) {
						e.printStackTrace();
						String idd = UUIDUtil.getRandom32BeginTimePK();
						besWaringInfoError.setFYqbh(besWaringInfo.getFYqbh());
						besWaringInfoError.setFGuid(idd);
						besWaringInfoError.setFLoction("系统报警");//报警位置
						besWaringInfoError.setFAlarmName("发送报警邮件失败");//报警名称
						besWaringInfoError.setFActualValue("");//实际值
						besWaringInfoError.setFATime(strTime);//报警时间
						besWaringInfoError.setFOpearation("1");//是否处理
						besWaringInfoError.setFType("3");//信息类型
						
						besWaringInfoError.setFYqbh(besWaringInfo.getFYqbh());
						besWaringInfoError.setFGuid(idd);
						besWaringInfoError.setFLoction("系统报警");//报警位置
						besWaringInfoError.setFAlarmName("发送报警邮件失败");//报警名称
						besWaringInfoError.setFTipInfo("报警邮件发送数目超过当天发送最高限制");//提示信息
						besWaringInfoError.setFActualValue( "");//实际值
						besWaringInfoError.setFPlanValue("");//计划值
						besWaringInfoError.setFATime(strTime);//报警时间
						besWaringInfoError.setFOpearation("1");//是否处理
						besWaringInfoError.setFType("3");//信息类型
//						calculateCurrentEnergyThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfoError);
						//判断是否存在相同的未处理报警
						AlarmUtil.insertWarningReal(besWaringInfo);
					}
					// 短信接口					
					BesNoteAlarm besNoteAlarm = new BesNoteAlarm();
					String[] phone = LEMSUtil.lemPhone().split(",");
					for (int i = 0; i < phone.length; i++) {
						String id1 = UUIDUtil.getRandom32BeginTimePK();
						besNoteAlarm.setfGuid(id1);
						besNoteAlarm.setfState("1");
						besNoteAlarm.setfPhone(phone[i]);
						besNoteAlarm.setfText(besWaringInfo.getFTipInfo());
						calculateCurrentEnergyThread.besNoteAlarmMapper.insertDuanXinInfo(besNoteAlarm);
					}
				}
			}
		}
	}
	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}
}
