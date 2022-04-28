package com.efounder.JEnterprise.collectorJob;

import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.util.G4Utils;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.*;
import com.efounder.JEnterprise.model.dataAnalysises.*;
import com.efounder.JEnterprise.service.collectorJob.BuildBasicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计算采集器数据线程类
 * @Description:分别以小时、天、月、年为周期，按照支路、分项、分户、总能耗、分户下的能源分项5类计算
 * @author LvSihan
 * @version: 
 * @date:2018年8月28日
 */
@Component
public class CalculateThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(CalculateThread.class);
	private String parkId;//园区id
	private String parkName;//园区名字
	private Dto params = new BaseDto();// 能源的个项参数
	private enum TYPE{HOUR, DAY, MONTH, YEAR};
	
	private static CalculateThread calculateThread;
	@Autowired
	private BESCalculateDataMapper besCalculateDataMapper;
	@Autowired
	private BuildBasicInfoService buildBasicInfoService;
	@Autowired
	private BesBranchDataMapper besBranchDataMapper;
	@Autowired
	private BesEnergyDataMapper besEnergyDataMapper;
	@Autowired
	private BESSubitemDataMapper besSubitemDataMapper;
	@Autowired
	private BesHouseholdDataMapper besHouseholdDataMapper;
	@Autowired
	private BesHouseholdSubitemDataMapper besHouseholdSubitemDataMapper;
	@PostConstruct
	public void init() {
		calculateThread = this;
		calculateThread.besCalculateDataMapper = this.besCalculateDataMapper;
		calculateThread.buildBasicInfoService = this.buildBasicInfoService;
		calculateThread.besBranchDataMapper = this.besBranchDataMapper;
		calculateThread.besEnergyDataMapper = this.besEnergyDataMapper;
		calculateThread.besSubitemDataMapper = this.besSubitemDataMapper;
		calculateThread.besHouseholdDataMapper = this.besHouseholdDataMapper;
		calculateThread.besHouseholdSubitemDataMapper = this.besHouseholdSubitemDataMapper;
	}

	public CalculateThread () {

	}
	
	@Override
	public void run() {
		//计算前加载有关单位的参数信息
		loadParams(parkId);
		
		Calendar hourTime = Calendar.getInstance();
		Calendar dayTime = Calendar.getInstance();
		Calendar monthTime = Calendar.getInstance();
		Calendar yearTime = Calendar.getInstance();
		log.info("开始计算园区=【"+parkName+"】的数据!");
		//依次计算当前小时\日\月\年数据
		CalculateDataByHour(hourTime);
		CalculateDataByDay(dayTime);
		CalculateDataByMonth(monthTime);
		CalculateDataByYear(yearTime);
	}
	
	/**
	 * 加载所有能源的各项参数
	 */
	@SuppressWarnings("unchecked")
	public void loadParams(String parkid){
		//查询所有能源类型
		List<Map<String,Object>> energys = calculateThread.besCalculateDataMapper.queryAllEnergyType();
		//查询分户参数
		List<Map<String,Object>> household = calculateThread.besCalculateDataMapper.queryHouseParam(parkid);
		String number = "1";
		String area = "1";
		
		Dto houseParam = new BaseDto();		
		for (Map<String, Object> house : household){
			houseParam.put(house.get("F_FHBH"), house);
		}
		
		if (calculateThread.buildBasicInfoService != null){
			Map<String, Object> info = calculateThread.buildBasicInfoService.buildInfoService(parkid);
			
			if (info != null){
				number = (String) info.get("F_PERSON_NUMS"); // 总人口
				area = (String) info.get("F_ALL_AREA");	 // 总面积
			}
		}
		
		for (Map<String, Object> dto : energys){
			params.put("number", number);
			params.put("area", area);
			params.put(dto.get("F_NYBH"), dto);
		}
		params.put("houseParams", houseParam);
	}

	/**
	 * 取出指定能源下的参数
	 * 顺序:价钱,耗煤量,二氧化碳,人数,面积
	 * @param energyid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Float[] getParams(String energyid){
		float cost = 1.0f;
		float coal = 1.0f;
		float co2 = 1.0f;
		float number = 1.0f;
		float unitarea = 1.0f;
		
		Map<String,Object> dto= (Map<String, Object>) params.get(energyid);
		
		if (dto != null){
			cost = mapToFloat(dto, "F_PRICE");
			coal = mapToFloat(dto, "F_COAL_AMOUNT");
			co2 = mapToFloat(dto, "F_CO2");
			number = dtoToFloat(params, "number");
			unitarea = dtoToFloat(params, "area");
		}
		number = (number == 0) ? 1 : number;
		unitarea = (unitarea == 0) ? 1 : unitarea;
		return new Float[]{cost, coal, co2, number, unitarea};
	}
	
	/**
	 * 计算单位数据
	 * @param dto
	 */
	@SuppressWarnings("unchecked")
	public void calculateDataWithUnit(Dto dto, String energyid, String householdid){
		Float[] param = getParams(energyid);

		if (householdid != null && householdid.length() > 0){
			Dto houseParam = (Dto) params.get("houseParams");
			
			if (houseParam != null){
				Dto config = (Dto) houseParam.get(householdid);
				
				if (config != null){
					param[3] = dtoToFloat(config, "number");
					param[4] = dtoToFloat(config, "size");
				}
			}
		}
		
		float energy = dtoToFloat(dto, "data");
		float allmoney = energy*param[0];
		float coalamount = energy*param[1];
		float dioxide = energy*param[2];
		float percapenergy = energy/param[3];
		float percapmoney = allmoney/param[3];
		float unitareaenergy = energy/param[4];
		float unitareamoney = allmoney/param[4];
		
		dto.put("allmoney", floatToString(allmoney));
		dto.put("coalamount", floatToString(coalamount));
		dto.put("dioxide", floatToString(dioxide));
		dto.put("percapenergy", floatToString(percapenergy));
		dto.put("percapmoney", floatToString(percapmoney));
		dto.put("unitareaenergy", floatToString(unitareaenergy));
		dto.put("unitareamoney", floatToString(unitareamoney));
	}

	/**
	 * 按月计算
	 */
	public void CalculateDataByMonth(Calendar t) {
		Calendar time = (Calendar) t.clone();
		time.add(Calendar.MONTH, 1);
		//格式化时间
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.HOUR_OF_DAY, 0);
		time.set(Calendar.DAY_OF_MONTH, 1);

		CalculateBranch(time, TYPE.MONTH);
		CalculateEnergy(time, TYPE.MONTH);
		CalculateGrade(time, TYPE.MONTH);
		CalculateHousehold(time, TYPE.MONTH);
		CalculateHouseholdGrade(time, TYPE.MONTH);
	}

	/**
	 * 按年计算
	 */
	public void CalculateDataByYear(Calendar t) {
		Calendar time = (Calendar) t.clone();
		time.add(Calendar.YEAR, 1);
		//格式化时间
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.HOUR_OF_DAY, 0);
		time.set(Calendar.DAY_OF_MONTH, 1);
		time.set(Calendar.MONTH, 0);

		CalculateBranch(time, TYPE.YEAR);
		CalculateEnergy(time, TYPE.YEAR);
		CalculateGrade(time, TYPE.YEAR);
		CalculateHousehold(time, TYPE.YEAR);
		CalculateHouseholdGrade(time, TYPE.YEAR);
	}

	/**
	 * 计算单个小时
	 * @param time
	 */
	public void CalculateDataByHour(Calendar t) {
		Calendar time = (Calendar) t.clone();
		time.add(Calendar.HOUR_OF_DAY, 1);
		//格式化开始时间
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
		
		CalculateBranchByHour(time); // 计算支路数据，按小时
		CalculateEnergy(time, TYPE.HOUR);// 计算总能耗(从支路表取数据)，小时，天，月，年
		CalculateGrade(time, TYPE.HOUR); //计算分项数据，小时，天，月，年
		CalculateHousehold(time, TYPE.HOUR); // 计算分户数据，小时，天，月，年
		CalculateHouseholdGrade(time, TYPE.HOUR); // 分户数据按能源分项计算，小时，天，月，年
	}
	
	/**
	 * 计算一天
	 * @param time
	 */
	public void CalculateDataByDay(Calendar t) {
		Calendar time = (Calendar) t.clone();
		time.add(Calendar.DAY_OF_MONTH, 1);
		//格式化开始时间
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.HOUR_OF_DAY, 0);

		CalculateBranch(time, TYPE.DAY);
		CalculateEnergy(time, TYPE.DAY);
		CalculateGrade(time, TYPE.DAY);
		CalculateHousehold(time, TYPE.DAY);
		CalculateHouseholdGrade(time, TYPE.DAY);
	}
	
	/**
	 * 计算支路，天，月，年
	 * @param time
	 * @param type
	 */
	@SuppressWarnings("unchecked")
	public void CalculateBranch(Calendar time, TYPE type){
		//查询园区下所有的支路
		List<Map<String,Object>> lstBranch = calculateThread.besCalculateDataMapper.queryBranch(null,null,null);
		log.info("开始按" + type + "计算园区=【"+parkName+"】的支路");
		String endTime = GetTimeString(time);
		String startTime = GetLastTimeString(time, type);
		
		//整理能源信息
		Dto dtoEnergy = new BaseDto();
		
		for (Map<String, Object> branch : lstBranch){
			dtoEnergy.put(branch.get("F_ZLBH"), branch.get("F_NYBH"));
		}

		//查询所有支路数据
		Dto dto = new BaseDto();
		dto.put("start", startTime);
		dto.put("end", endTime);
		dto.put("type", String.valueOf(type.ordinal() - 1));
		
		List<Map<String, Object>> lstData = calculateThread.besBranchDataMapper.queryBranchData(startTime, endTime,
				String.valueOf(type.ordinal() - 1));
		//遍历所有支路
		for (Map<String, Object> branch : lstData){
			String id = (String) branch.get("F_ZLBH");
			saveBranch(dtoEnergy.getAsString(id), id, startTime, GetType(type), floatToString(mapToFloat(branch, "F_DATA")));
		}
		log.info("按" + type + "计算园区=【"+parkName+"】的支路结束");
	}
	
	/**
	 * 计算支路数据，按小时
	 * @param time
	 * @param type
	 */
	public void CalculateBranchByHour(Calendar time){
		TYPE type = TYPE.HOUR;
		//查询园区parkName所有的支路
		List<Map<String,Object>> lstBranch = calculateThread.besCalculateDataMapper.queryBranch(null,null,parkId);
		log.info("开始按" + type + "计算园区=【"+parkName+"】的支路");
		//相同时间段内保证每个电表只计算一次
		Map<String, Float> ammeterData = new HashMap<String, Float>();
		
		//遍历所有支路
		for (Map<String, Object> branch : lstBranch) {
			//查询该支路下所有电表
			float sum = 0.0f;
			List<Map<String,Object>> lstAmmeter = calculateThread.besCalculateDataMapper.queryAmmeter((String)branch.get("F_ZLBH"));
			
			//遍历该支路下所有电表
			for (Map<String, Object> ammeter : lstAmmeter){
				float data = 0.0f;
				String uuid = (String) ammeter.get("F_SYS_NAME");
				
				//如果该电表已计算完毕，直接使用计算的数据
				if (ammeterData.containsKey(uuid)) {
					data = ammeterData.get(uuid);
				} else {
					//查询该电表在指定时间段内的起始值
					String endTime = GetTimeString(time);
					String startTime = GetLastTimeString(time, type);
//					log.info("开始查询端点数据");
					Map<String,Object> min = calculateThread.besCalculateDataMapper.queryAmmeterStartData(startTime,uuid);
					Map<String,Object> max = calculateThread.besCalculateDataMapper.queryAmmeterEndData(startTime,endTime,uuid);
					if(min == null){
						min = calculateThread.besCalculateDataMapper.queryAmmeterStartData2(startTime,uuid);
					}
//					log.info("结束查询端点数据");
					
					//如果max也为null，则为异常
					if (max != null){
						float maxData = mapToFloat(max, "data");
						float minData = mapToFloat(min, "data");
						data = maxData - minData;
						
						if (data < 0){
							LEMSUtil.pushAlarm((String) branch.get("F_YQBH"), "数据计算", "负数异常", "计算出现负数", "能耗差值为正数",
									"支路" + (String) branch.get("F_ZLBH") + "电表" + (String) ammeter.get("F_SYS_NAME") + "计算出现负数!",
									"4");
							log.info("园区=【"+parkName+"】下的支路" + branch.get("F_ZLBH") + "电表" +ammeter.get("F_SYS_NAME") + "计算出现负数!");
						}
					}
										
					//乘于电表变比--原始数据保存时已处理 Add By LvSihan 2019.1.14
//					try{
//						float percent = Float.parseFloat((String) ammeter.get("F_PERCENTAGE"));
//						data *= percent;
//					} catch(Exception e) {
//					}
					if (data < 0.0f){
						data = 0.0f;
					}
					// 电表id，当前时间较之前时间的能耗值，比如现在2点，时间类型时小时，那就是1点~2点的能耗值
					ammeterData.put(uuid, data);
				}
				if (ammeter.get("F_OPERATOR").equals("0")){
					sum += data;
				} else {
					sum -= data;					
				}
			}
			if (sum < 0.0f){
    			LEMSUtil.pushAlarm((String)branch.get("F_YQBH"),"数据计算", "负数异常", "计算出现负数", "能耗差值为正数", "支路" + (String)branch.get("F_ZLBH") + "计算出现负数!", "3");
				log.info("园区=【"+parkName+"】下的支路" + (String)branch.get("F_ZLBH") + "计算出现负数!");
				sum = 0.0f;
			}
//			log.info("开始写入结果");
			//计算完毕一条支路，写入数据库
			saveBranch((String)branch.get("F_NYBH"), (String)branch.get("F_ZLBH"), GetLastTimeString(time, type), GetType(type), floatToString(sum));
//			log.info("结束写入结果");
		}
		log.info("按" + type + "计算园区=【"+parkName+"】的支路计算结束");
	}
	
	/**
	 * 计算总能耗(从支路表取数据)，小时，天，月，年
	 * @param time
	 * @param type
	 */
	public void CalculateEnergy(Calendar time, TYPE type){
		log.info("开始按" + type + "计算总能耗。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		
		List<Map<String,Object>> lstEnergys = calculateThread.besCalculateDataMapper.queryEnergyTypeByParkid(parkId);
		for (Map<String, Object> energy : lstEnergys){
			//查询该能源下支路总数据
			Map<String, Object> dataDto = calculateThread.besCalculateDataMapper.queryEnergySumData(GetType(type),
					GetLastTimeString(time, type), (String)energy.get("id"), parkId);
			//保存或更新数据
			saveEnergy((String)energy.get("id"), GetLastTimeString(time, type), GetType(type), String.valueOf(dataDto.get("fData")));
		}
		log.info("按" + type + "计算总能耗结束。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
	}

	/**
	 * 计算分项数据，小时，天，月，年
	 * @param time
	 * @param type
	 */
	public void CalculateGrade(Calendar time, TYPE type){
		log.info("开始按" + type + "计算分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		//查询根级分项，能源类型
		String parentid = "01"; 
		List<Map<String,Object>> lstRoot = calculateThread.besCalculateDataMapper.queryGrade(null,parentid);
		
		//遍历根级分项，根据能源类型遍历
		for (Map<String, Object> root : lstRoot){
			float rootData = 0.0f;
			//查询各大项
			parentid = (String) root.get("F_FXBH");
			List<Map<String,Object>> lstBig = calculateThread.besCalculateDataMapper.queryGrade(null,parentid);
			//遍历各大项
			for (Map<String, Object> big : lstBig){
				float bigData = 0.0f;
				//查询子项
				parentid = (String) big.get("F_FXBH");
				List<Map<String,Object>> lstChild = calculateThread.besCalculateDataMapper.queryGrade(null,parentid);
				
				//遍历子项
				for (Map<String, Object> child : lstChild){
					float childData = 0.0f;
					//查询该子项的支路统计数据之和
					Map<String, Object> d = calculateThread.besCalculateDataMapper.queryGradeSumData(GetType(type),
							GetLastTimeString(time, type), (String)child.get("F_FXBH")/*, parkId*/);
					if (d != null){
						childData = mapToFloat(d, "fData");
					}
					bigData += childData;
					//保存数据
					saveGrade((String) child.get("F_NYBH"), (String) child.get("F_FXBH"), GetLastTimeString(time, type),
							GetType(type), floatToString(childData));
				}
				rootData += bigData;
				//保存数据
				saveGrade((String) big.get("F_NYBH"), (String) big.get("F_FXBH"), GetLastTimeString(time, type),
						GetType(type), floatToString(bigData));
			}
			//保存数据
			saveGrade((String) root.get("F_NYBH"), (String) root.get("F_FXBH"), GetLastTimeString(time, type),
					GetType(type), floatToString(rootData));
		}
		log.info("按" + type + "计算分项结束。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
	}
	
	/**
	 * 计算分户数据，小时，天，月，年
	 * @param time
	 * @param type
	 */
	public void CalculateHousehold(Calendar time, TYPE type){
		log.info("开始按" + type + "计算分户。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		//查询一级分户
		List<Map<String,Object>> lstChild = calculateThread.besCalculateDataMapper.queryHouseHold("");
		//遍历子分户
		for (Map<String, Object> child : lstChild){
			CalculateHousehold(child, time, type);
		}
		log.info("按" + type + "计算分户结束。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
	}

	/**
	 * 分户数据按能源分项计算，小时，天，月，年
	 * @param time
	 * @param type
	 */
	public void CalculateHouseholdGrade(Calendar time, TYPE type){
		log.info("开始按" + type + "计算分户分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		//查询一级分户
		List<Map<String,Object>> lstChild = calculateThread.besCalculateDataMapper.queryHouseHold("");

		//遍历子分户
		for (Map<String, Object> child : lstChild){
			CalculateHouseholdGrade(child, time, type);
		}		
		log.info("按" + type + "计算分户分项结束。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
	}
	
	/**
	 * 计算分户(递归)
	 * @param id
	 * @param time
	 * @param type
	 * @return
	 */
	protected float CalculateHousehold(Map<String, Object> household, Calendar time, TYPE type){
		//查询该分户下的所有子分户
		String id = (String) household.get("F_FHBH");
		List<Map<String,Object>> lstChild = calculateThread.besCalculateDataMapper.queryHouseHold(id);
		//查询该分户下子支路个数
		Map<String,Object> count = calculateThread.besCalculateDataMapper.queryRelBranchCount(id);
		//查询该子项的支路统计数据之和
		float data = 0.0f;
		Map<String,Object> d = calculateThread.besCalculateDataMapper.queryHouseholdSumData(GetType(type),GetLastTimeString(time, type),id);
		if (d != null){
			data = mapToFloat(d, "F_DATA");
		}
		if (count.get("count").equals("0")){
			data = 0.0f;
		}
		//遍历子分户,并递归
		for (Map<String, Object> child : lstChild){
			float childData = CalculateHousehold(child, time, type);
			//如果没有子支路，等于子分户之和
			if (count.get("count").equals("0")){
				data += childData;
			}
		}
		//保存数据
		saveHousehold((String) household.get("F_NYBH"), id, GetLastTimeString(time, type), GetType(type), floatToString(data));		
		return data;
	}
	
	/**
	 * 分项数据的合并
	 * @param dst
	 * @param src
	 */
	@SuppressWarnings("unchecked")
	protected void AddToGrageData(Dto dst, Dto src){
		for (Object k : src.keySet()){
			//分离每一项数据，累加再写入
			String key = (String) k;
			float d1 = dtoToFloat(src, key);
			float d2 = dtoToFloat(dst, key);
			dst.put(key, String.valueOf(d1 + d2));
		}		
	}
	
	/**
	 * 计算支路按能源分项的数据集合
	 * @param id
	 * @param time
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Dto CalculateBranchGrade(Map<String, Object> branch, Calendar time, TYPE type){
//		log.info("CalculateBranchGrade开始按" + type + "计算分户分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		Dto data = new BaseDto();
		//如果有设备集，直接取当前数据；否则取子支路数据之和
		String F_EQUIPMENT_SET = (String) branch.get("F_EQUIPMENT_SET");
		if (F_EQUIPMENT_SET != null && F_EQUIPMENT_SET != "" && F_EQUIPMENT_SET.length() > 2){
			List<Map<String,String>> lsDto = calculateThread.besCalculateDataMapper.queryBranchGradeData((String) branch.get("F_ZLBH"),
					GetLastTimeString(time, type), GetType(type));
			Map<String,String> singleData = new HashMap<>();
			if (G4Utils.isNotEmpty(lsDto)) {
				singleData = lsDto.get(0);
			}
			
			if (singleData != null){
				data.put(singleData.get("F_FXBH"), singleData.get("F_DATA"));
			}
			return data;
		}
		//查询子支路数据
		List<Map<String,Object>> lstBranch = calculateThread.besCalculateDataMapper.queryBranch(null,(String) branch.get("F_ZLBH"),null);
		for (Map<String, Object> child : lstBranch){
			//查询子支路数据，并合并
			Dto childData = CalculateBranchGrade(child, time, type);
			AddToGrageData(data, childData);
		}
//		log.info("CalculateBranchGrade结束按" + type + "计算分户分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		return data;
	}
	
	/**
	 * 计算分户按分级计算(递归)
	 * @param id
	 * @param time
	 * @param type
	 * @return
	 */
	protected Dto CalculateHouseholdGrade(Map<String, Object> household, Calendar time, TYPE type){
//		log.info("CalculateHouseholdGrade开始按" + type + "计算分户分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		//查询该分户下的所有子分户
		String id = (String) household.get("F_FHBH");
		List<Map<String,Object>> lstChild = calculateThread.besCalculateDataMapper.queryHouseHold(id);
		
		Dto data = new BaseDto();
		//查询该子项的支路列表
		List<Map<String,Object>> lstBranch = calculateThread.besCalculateDataMapper.queryHouseBranch(id);
		int count = lstBranch.size();

		//查询子支路的数据
		for (Map<String, Object> branch : lstBranch){
			Dto branchData = CalculateBranchGrade(branch, time, type);			
			//取子支路的数据累加
			AddToGrageData(data, branchData);
		}
		
		//遍历子分户,并递归
		for (Map<String, Object> child : lstChild){
			Dto childData = CalculateHouseholdGrade(child, time, type);
			//如果没有子支路，等于子分户之和,把数据分离出来，再累加
			if (count == 0){
				AddToGrageData(data, childData);
			}
		}
		//保存当前分级数据
		for (Object k : data.keySet()){
			String gradeid = (String) k;
			String value = data.getAsString(gradeid);
			value = floatToString(value);
			
			saveHouseholdGrade((String)household.get("F_NYBH"), id, GetLastTimeString(time, type), GetType(type), value, gradeid);
		}	
//		log.info("CalculateHouseholdGrade结束按" + type + "计算分户分项。" + "园区=【"+parkName+"】，园区id=【" + parkId+"】");
		return data;
	}
		
	/**
	 * type对应的数据库值
	 * @param type
	 * @return
	 */
	public String GetType(TYPE type){
		return String.valueOf(type.ordinal());
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
	 * 获取该时间上次开始时间的字符串
	 * @param time
	 * @param type
	 * @return
	 */
	public String GetLastTimeString(Calendar time, TYPE type){
		SimpleDateFormat fmt = null;
		Calendar timeEnd = Calendar.getInstance();
		timeEnd.setTimeInMillis(time.getTimeInMillis());
		
		switch (type){
		case HOUR:
			timeEnd.add(Calendar.HOUR_OF_DAY, -1);
			fmt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			break;
		case DAY:
			timeEnd.add(Calendar.DAY_OF_MONTH, -1);
			fmt = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			break;
		case MONTH:
			timeEnd.add(Calendar.MONTH, -1);
			fmt = new SimpleDateFormat("yyyy-MM-01 00:00:00");
			break;
		case YEAR:
			timeEnd.add(Calendar.YEAR, -1);
			fmt = new SimpleDateFormat("yyyy-01-01 00:00:00");
			break;
		}
		
		return fmt.format(timeEnd.getTime());
	}
	
	/**
	 * string转float，含异常处理
	 * @param data2
	 * @param key
	 * @return
	 */
	public float stringToFloat(String data2){
		if (data2 == null){
			return 0.0f;
		}
		try{
			float data = Float.parseFloat(data2);
			return data;
		}
		catch(Exception e){
			return 0.0f;
		}
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
	 * @param max
	 * @param key
	 * @return
	 */
	public float mapToFloat(Map<String, Object> max, String key){
		if (max == null){
			return 0.0f;
		}
		try{
			float data = Float.parseFloat(String.valueOf(max.get(key)) );
			return data;
		}catch(Exception e){
			return 0.0f;
		}
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
	 * 字符串形式的float精确小数点
	 * @param data
	 * @return
	 */
	public String floatToString(String data){
		float value = 0.0f;
		try{
			value = Float.parseFloat(data);
		}
		catch(Exception e){
		}
		return String.format("%.02f", value);
	}
	
	/**
	 * 保存数据，如果存在则更新数据，不存在则创建记录
	 * @param sqlQuery
	 * @param sqlUpdate
	 * @param sqlInsert
	 * @param id
	 * @param time
	 * @param type
	 * @param data
	 * @param gradeid
	 */
	public void saveData(String sqlQuery, String sqlUpdate, String sqlInsert, Dto dto){
		//追加园区id，根据园区id、时间、能源id、时间类型查询是否存在
//		int count = (int) g4Dao.queryForObject(sqlQuery, dto);
//		
//		if (count == 0){
//			//新建记录并写入
//			dto.put("newid", IDHelper.getUUID());
//			g4Dao.insert(sqlInsert, dto);
//		} else {
//			//更新记录
//			g4Dao.update(sqlUpdate, dto);
//		}
	}
	
	/**
	 * 保存支路数据
	 * @param id
	 * @param time
	 * @param type
	 * @param data
	 */
	public void saveBranch(String energyid, String id, String time, String type, String data){
		try{
			if (Float.parseFloat(data) < 0){
				data = "0";
			}			
		}
		catch(Exception e){
			data = "0";
		}
		
		BesBranchData besBranchData = new BesBranchData();
		besBranchData.setfZlbh(id);
		besBranchData.setfCjsj(time);
		besBranchData.setfType(type);
		besBranchData.setfData(Double.parseDouble(data));
		//计算单位数据，顺序:价钱,耗煤量,二氧化碳,人数,面积
		Float[] param = getParams(energyid);
		
		float energy = stringToFloat(data);
		float allmoney = energy*param[0];

		besBranchData.setfAllMoney(floatToString(allmoney));
		besBranchData.setfCoalAmount(floatToString(energy*param[1]));
		besBranchData.setfCo2(floatToString(energy*param[2]));
		besBranchData.setfPermanData(floatToString(energy/param[3]));
		besBranchData.setfPermanMoney(floatToString(allmoney/param[3]));
		besBranchData.setfUnitareData(floatToString(energy/param[4]));
		besBranchData.setfUnitareMoney(floatToString(allmoney/param[4]));

		//根据园区id、时间、能源id、时间类型查询是否存在
		BesBranchData queryres = calculateThread.besBranchDataMapper.queryBranchExists(besBranchData);
		if (queryres.getRows().equals("0")){
			//新建记录并写入
			besBranchData.setfId(UUIDUtil.getRandom32BeginTimePK());
			calculateThread.besBranchDataMapper.saveBranchData(besBranchData);
		} else {
			//更新记录
			calculateThread.besBranchDataMapper.updateBranch(besBranchData);
		}
	}
	
	/**
	 * 保存分项数据
	 * @param id
	 * @param time
	 * @param type
	 * @param data
	 */
	public void saveGrade(String energyid, String id, String time, String type, String data){
		BESSubitemData besSubitemData = new BESSubitemData();
		besSubitemData.setfFxbh(id);
		besSubitemData.setfCjsj(time);
		besSubitemData.setfType(type);
		besSubitemData.setfData(Double.parseDouble(data));
		besSubitemData.setfYqbh(parkId);
		//计算单位数据
		Float[] param = getParams(energyid);
		
		float energy = stringToFloat(data);
		float allmoney = energy*param[0];
		
		besSubitemData.setfAllMoney(floatToString(allmoney));
		besSubitemData.setfCoalAmount(floatToString(energy*param[1]));
		besSubitemData.setfCo2(floatToString(energy*param[2]));
		besSubitemData.setfPercapitaEnergy(floatToString(energy/param[3]));
		besSubitemData.setfPercapitaMoney(floatToString(allmoney/param[3]));
		besSubitemData.setfUnitareaData(floatToString(energy/param[4]));
		besSubitemData.setfUnitareaMoney(floatToString(allmoney/param[4]));

		//根据园区id、时间、能源id、时间类型查询是否存在
		BESSubitemData queryres = calculateThread.besSubitemDataMapper.queryGradeExists(besSubitemData);
		if (queryres.getRows().equals("0")){
			//新建记录并写入
			besSubitemData.setfId(UUIDUtil.getRandom32BeginTimePK());
			calculateThread.besSubitemDataMapper.saveGradeData(besSubitemData);
		} else {
			//更新记录
			calculateThread.besSubitemDataMapper.updateGrade(besSubitemData);
		}
	}
	
	/**
	 * 保存分户数据
	 * @param id
	 * @param time
	 * @param type
	 * @param data
	 */
	@SuppressWarnings("unchecked")
	public void saveHousehold(String energyid, String id, String time, String type, String data){		
		BesHouseholdData besHouseholdData = new BesHouseholdData();
		besHouseholdData.setfFhbh(id);
		besHouseholdData.setfCjsj(time);
		besHouseholdData.setfType(type);
		besHouseholdData.setfData(Double.parseDouble(data));
		besHouseholdData.setfYqbh(parkId);
		Float[] param = getParams(energyid);

		if (id != null && id.length() > 0){
			Map<String, Object> houseParam = (Map<String, Object>) params.get("houseParams");
			
			if (houseParam != null){
				Map<String, Object> config =  (Map<String, Object>) houseParam.get(id);
				
				if (config != null){
					param[3] = mapToFloat(config, "F_PERSON_NUMS");
					param[4] = mapToFloat(config, "F_AREA");
				}
			}
		}		
		float energy = stringToFloat(data);
		float allmoney = energy*param[0];
		
		besHouseholdData.setfAllMoney(Double.valueOf(String.valueOf(allmoney)));

		besHouseholdData.setfCoalAmount(Double.valueOf(floatToString(energy*param[1])));
		besHouseholdData.setfCo2(Double.valueOf(floatToString(energy*param[2])));
		besHouseholdData.setfPermanData(Double.valueOf(floatToString(energy/param[3])));
		besHouseholdData.setfPermanMoney(Double.valueOf(floatToString(allmoney/param[3])));
		besHouseholdData.setfUnitareaData(Double.valueOf(floatToString(energy/param[4])));
		besHouseholdData.setfUnitareaMoney(Double.valueOf(floatToString(allmoney/param[4])));

		//根据园区id、时间、能源id、时间类型查询是否存在
		BesHouseholdData queryres = calculateThread.besHouseholdDataMapper.queryHouseholdExists(besHouseholdData);
		if (queryres.getRows().equals("0")){
			//新建记录并写入
			besHouseholdData.setfId(UUIDUtil.getRandom32BeginTimePK());
			calculateThread.besHouseholdDataMapper.saveHouseholdData(besHouseholdData);
		} else {
			//更新记录
			calculateThread.besHouseholdDataMapper.updateHousehold(besHouseholdData);
		}
	}
	
	/**
	 * 保存分户按能源数据
	 * @param id
	 * @param time
	 * @param type
	 * @param data
	 * @param gradeid
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void saveHouseholdGrade(String energyid, String id, String time, String type, String data, String gradeid){
//		log.info("saveHouseholdGrade开始" + id );
		Float[] param = getParams(energyid);

		if (id != null && id.length() > 0){
			Map<String,Object> houseParam =  (Map<String, Object>) params.get("houseParams");
			
			if (houseParam != null){
				Map<String,Object> config =  (Map<String, Object>) houseParam.get(id);
				
				if (config != null){
					param[3] = mapToFloat(config, "F_PERSON_NUMS");
					param[4] = mapToFloat(config, "F_AREA");
				}
			}
		}
		
		float energy = stringToFloat(data);
		float allmoney = energy*param[0];

		BesHouseholdSubitemData besHouseholdSubitemData = new BesHouseholdSubitemData();
		besHouseholdSubitemData.setfFhbh(id);
		besHouseholdSubitemData.setfCjsj(time);
		besHouseholdSubitemData.setfType(type);
		besHouseholdSubitemData.setfData(Double.parseDouble(data));
		besHouseholdSubitemData.setfFxbh(gradeid);
		besHouseholdSubitemData.setfAllMoney(floatToString(allmoney));
		besHouseholdSubitemData.setfCoalAmount(floatToString(energy*param[1]));
		besHouseholdSubitemData.setfCo2(floatToString(energy*param[2]));
		besHouseholdSubitemData.setfPermanData(floatToString(energy/param[3]));
		besHouseholdSubitemData.setfPermanMoney(floatToString(allmoney/param[3]));
		besHouseholdSubitemData.setfUnitareaData(floatToString(energy/param[4]));
		besHouseholdSubitemData.setfUnitareaMoney(floatToString(allmoney/param[4]));

		//根据园区id、时间、能源id、时间类型查询是否存在
//		BesHouseholdSubitemData queryres = calculateThread.besHouseholdSubitemDataMapper
//				.queryHouseholdGradeExists(besHouseholdSubitemData);
		List<Map<String,String>> queryList = calculateThread.besHouseholdSubitemDataMapper
				.queryHouseholdGradeExists(besHouseholdSubitemData.getfFhbh(),
						besHouseholdSubitemData.getfCjsj(),besHouseholdSubitemData.getfType(),besHouseholdSubitemData.getfFxbh());
//		if (queryres.getRows().equals("0")){
		if(queryList.size() == 0) {
			//新建记录并写入
			besHouseholdSubitemData.setfId(UUIDUtil.getRandom32BeginTimePK());
			try {
				calculateThread.besHouseholdSubitemDataMapper.saveHouseholdGradeData(besHouseholdSubitemData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//更新记录
			try {
				calculateThread.besHouseholdSubitemDataMapper.updateHouseholdGrade(besHouseholdSubitemData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		log.info("saveHouseholdGrade结束" + id );
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
		BesEnergyData queryres = calculateThread.besEnergyDataMapper.queryEnergyExists(besEnergyData);
		if (queryres.getRows().equals("0")){
			//新建记录并写入
			besEnergyData.setfId(UUIDUtil.getRandom32BeginTimePK());
			calculateThread.besEnergyDataMapper.saveEnergyData(besEnergyData);
		} else {
			//更新记录
			calculateThread.besEnergyDataMapper.updateEnergy(besEnergyData);
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