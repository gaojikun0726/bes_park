package com.efounder.JEnterprise.collectorJob;

import com.core.common.util.AlarmUtil;
import com.core.common.util.G4Utils;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.collectorJob.BESHouseAlarmMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分户报警线程
 * author liuzhen
 * time:2018/12/29
 */
@Component
public class HouseAlarmThread extends Thread{

	private String parkId ;//园区id
	private String parkName ;//园区名字
	@Autowired
	private BESHouseAlarmMapper besHouseAlarmMapper;
	@Autowired
	private BesWainingRealInfoMapper besWainingRealInfoMapper;

	private static HouseAlarmThread houseAlarmThread;


	private static final Logger log = LoggerFactory.getLogger(CollectorThread.class);

	@PostConstruct
	public void init() {
		houseAlarmThread = this;
		houseAlarmThread.besHouseAlarmMapper = this.besHouseAlarmMapper;
		houseAlarmThread.besWainingRealInfoMapper = this.besWainingRealInfoMapper;
	}

	public HouseAlarmThread () {

	}
	
	public void run() {
		houseAlarm();
	}
	
	//分户报警接口
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void houseAlarm() {
		log.info("开始轮训园区:【"+this.parkName+"】的分户报警，园区按编号：" + this.parkId);
		//获取当前时间
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String strTime = dateFormat.format(now); 
		//只获取当前月份
		 Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		List<Map<String,Object>> realList = houseAlarmThread.besHouseAlarmMapper.selectDateBytypeAndId(strTime.substring(0,8 )+"01 00:00:00",strTime.substring(0,8 )+"31 23:59:59",this.parkId);
		List<Map<String,Object>> planList = houseAlarmThread.besHouseAlarmMapper.selectPlanInfoByMon(month+"",year+"",this.parkId);
		if(realList.size()==0){
			return;
		}else if(planList.size()==0){
			return;
		} else {
			for(int i=0;i<realList.size();i++){
				Map<String,Object> realDto = realList.get(i);
				String houseid = (String) realDto.get("F_FHBH");
				for(int j=0;j<planList.size();j++){
					Map<String,Object> planDto = planList.get(j);
					if(realDto.get("F_FHBH").equals(planDto.get("F_FHBH"))){
						//判断收到的数据是否为空
						float real = 0.0f;
						float plan = 0.0f;
						try{
							real = Float.parseFloat((String) realDto.get("F_DATA"));
							plan = Float.parseFloat((String) planDto.get("F_ALLENERGY"));
						} catch (Exception e){									
						}
						//获取报警具体位置
						String url="";
						url = (String) planDto.get("F_EFFICIENTNAME");
						//判断这条记录是否还有父节点，有，就将其查询出来
						String  parentid = (String) planDto.get("F_PFHBH");
						while((houseAlarmThread.besHouseAlarmMapper.selectAllId(parentid))!=null){
							Map<String,Object> dtoLinShi = houseAlarmThread.besHouseAlarmMapper.selectAllId(parentid);
							url = dtoLinShi.get("F_FHMC")+"/"+url;
							parentid = (String) dtoLinShi.get("F_PFHBH");
						}
						//判断能耗值是否超出，否则就讲形成一条报警记录
						if(real > plan){
							BesWainingInfo besWaringInfo = new BesWainingInfo();
							besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
							String energyTypeName = (String) houseAlarmThread.besHouseAlarmMapper.selectEnergyTypeNameByDto(houseid);
							besWaringInfo.setFLoction(energyTypeName+"/"+url+"("+planDto.get("F_FHMC")+")");//报警位置
							besWaringInfo.setFTipInfo("能耗 值超出范围");//提示信息
							besWaringInfo.setFActualValue((String) realDto.get("F_DATA"));//实际值
							besWaringInfo.setFPlanValue((String) planDto.get("F_ALLENERGY"));//计划值
							besWaringInfo.setFAlarmName((String) planDto.get("F_EFFICIENTNAME"));//报警名称
							besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
							besWaringInfo.setFType("1");//信息类型
							besWaringInfo.setFLevel(LEMSConstants.LEVELIMPORTENT);//信息类型
							besWaringInfo.setFYqbh(this.parkId);//园区id
							besWaringInfo.setFATime(strTime);//报警时间
							String planData = (String) planDto.get("F_ALLENERGY");
							if(planData !=null && !"".equals(planData))
							{
//								houseAlarmThread.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
								//判断是否存在相同的未处理报警
								AlarmUtil.insertWarningReal(besWaringInfo);
							}
							log.info("结束轮训园区:【"+this.parkName+"】的分户报警，园区id：" + this.parkId);
						}
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
}
