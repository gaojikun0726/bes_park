package com.efounder.JEnterprise.model.schedule;

import lombok.Data;

@Data
public class BuildBaseInfo {
	/**
	 * 建筑代码  对应生成文件:项目节点id   	F_DataCenterID
	 */
	private String f_buding_code;
	/**
	 * 建筑名称  对应生成文件:建筑物名称   	F_BuildName
	 */
	private String f_buding_name;
	/**
	 * 建筑字母别名 对应生成文件:建筑字母别名 F_AliasName
	 */
	private String f_buding_letter;
	/**
	 * 建筑业主 对应生成文件:建筑业主    	F_BuildOwner
	 */
	private String f_buding_owner;
	/**
	 * 建筑检测状态 对应生成文件: 建筑检测状态	demo:1,0  F_State
	 */
	private String f_state;
	/**
	 * 行政区号 对应生成文件:行政区号 F_DistrictCode
	 */
	private String f_districtcode;
	/**
	 * 建筑地址 对应生成文件: 建筑地址  	F_BuildAddr
	 */
	private String f_buding_address;
	/**
	 * 建筑坐标 经度  对应生成文件: 经度    	F_BuildLong
	 */
	private String f_buding_longitude;
	/**
	 * 建筑坐标 纬度  对应生成文件: 纬度	F_BuildLat
	 */
	private String f_buding_latitude;
	/**
	 * 建筑年代  对应生成文件: 建筑年代	 F_BuildYear
	 */
	private String f_buding_date;
	/**
	 * 地上建筑层数  对应生成文件:地上建筑层数		 F_UpFloor
	 */
	private String f_buding_layerson;
	/**
	 * 地下建筑层数  对应生成文件:地下建筑层数	 F_DownFloor
	 */
	private String f_buding_layersunder;
	/**
	 * 建筑功能  对应生成文件:建筑功能	  F_BuildFunc
	 */
	private String f_buding_function;
	/**
	 * 建筑面积  对应生成文件: 建筑面积 (总面积) 		 F_TotalArea
	 */
	private String f_buding_area;
	/**
	 * 空调面积  对应生成文件: 空调面积	 F_AirArea
	 */
	private String f_airconditioner_area;
	
	/**
	 * F_HEATING_AREA,#采暖面积			 F_HeatArea
	 */
	
	private String f_heating_area;
	/**
	 * 空调系统形式  对应生成文件: 空调系统形式 #demo：A|B|C|Z		 F_AirType
	 */
	private String f_airtype;
	/**
	 * 采暖系统形式  对应生成文件: 建筑采暖系统形式  demo: A|B|C|Z  F_HeatType
	 */
	private String f_heattype;
	/**
	 * 建筑体形系数  对应生成文件:建筑体型系数   #demo:XXX.XXXX eg:0.1000 	 F_BodyCoef
	 */
	private String f_bodycoef;
	/**
	 * 建筑结构形式  对应生成文件: 建筑结构形式 demo:A|B|C|D|E|F|G|Z F_StruType
	 */
	private String f_strutype;
	/**
	 * 外墙材料形式  对应生成文件: 外墙材料形式 demo:A|B|C|D|E|Z	 F_WallMatType
	 */
	private String f_wallmattype;
	/**
	 * 外墙保温形式  对应生成文件:外墙保温形式  demo:A|B|C|Z		 F_WallWarmType
	 */
	private String f_wallwarmtype;
	/**
	 * 建筑外窗类型  对应生成文件:建筑外窗类型  demo:A|B|C|D|E|F|Z F_WallWinType
	 */
	private String f_wallwintype;
	/**
	 * 建筑玻璃类型  对应生成文件:建筑玻璃类型 demo:A|B|C|Z				 F_GlassType
	 */
	private String f_glasstype;
	/**
	 * 窗框材料类型  对应生成文件:窗框材料类型 demo:A|B|C|D|Z   F_WinFrameType
	 */
	private String f_winframetype;
	/**
	 * 是否标杆建筑  对应生成文件:是否标杆建筑 demo:true|false F_IsStandard
	 */
	private String f_isstandard;
	/**
	 * 设计单位  对应生成文件: 方案设计方案(监测方案设计单位) F_DesignDept  
	 */
	private String f_design_organization;
	/**
	 * 工程施工单位  对应生成文件:工程施工单位(监测工程实施单位) F_WorkDept
	 */
	private String f_engineering_construction;
	/**
	 * 录入时间  对应生成文件: 录入时间      F_CreateTime
	 */
	private String f_entry_time;
	/**
	 * 工程验收日期  对应生成文件:F_ACCEPTANCE_TIME
	 */
	private String f_acceptance_time;
	
}
