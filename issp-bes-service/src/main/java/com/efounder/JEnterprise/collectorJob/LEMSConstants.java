package com.efounder.JEnterprise.collectorJob;

public interface LEMSConstants {

	/**
	 * 调取服务，无返回值时
	 */
	public static final String Service_Err = "下位机无返回数据，请联系系统管理员！";
	/**
	 * 新增成功
	 */
	public static final String Add_Success = "新增成功";
	/**
	 * 修改成功
	 */
	public static final String Update_Success = "修改成功";
	/**
	 * 删除成功
	 */
	public static final String Delete_Success = "删除成功";
	
	/**
	 * 调取服务，返回成功
	 */
	public static final String Service_Success = "操作成功";
	
	/**
	 * 增加电表，有相同电表id时返回
	 */
	public static final String Ammeter_Same = "存在相同ID的电表，请修改电表ID！";
	
	/**
	 * 新增电能参数时，有相同的电能参数名称
	 */
	public static final String ElectricParams_Same = "已经存在此名称的电能参数！";
	/**
	 * 新增建筑信息时，有相同的建筑名称
	 */
	public static final String buildName_Same = "已经存在此名称的建筑！";
	/**
	 * 新增园区信息时，有相同的建筑名称
	 */
	public static final String parkName_Same = "已经存在此名称的园区！";
	/**
	 * 新增建筑信息时，只允许有一条数据
	 */
	public static final String buildCount_Same = "建筑信息不允许新增多条！";
	/**
	 * 新增园区信息时，只允许有一条数据
	 */
	public static final String parkCount_Same = "园区信息不允许新增多条！";
	/**
	 * 新增建筑信息时，有相同的建筑代码
	 */
	public static final String buildCode_Same = "已经存在此代码的建筑！";
	/**
	 * 刪除電能參數時候，查询是否在采集方案中被使用
	 */
	public static final String Electric_Used = "此电能参数正在被采集方案使用，不能进行操作！";
	/**
	 * 刪除電能參數時候，查询是否被设为统计参数
	 */
	public static final String Electric_TongJiCanShu = "此电能参数被设为统计参数，不能进行操作！";
	/**
	 * 修改電能參數時候，查询是否在采集方案中被使用
	 */
	public static final String downElectric_Used = "此电能参数正在被采集方案使用，请及时下发！！";
	/**
	 * 新增采集方案时，有相同的采集方案名称
	 */
	public static final String CollectorScheme_Same = "已经存在此名称的采集方案！";
	/**
	 * 新增数据中心时，有相同的数据中心名称
	 */
	public static final String DataCentreName_Same = "数据中心名称已存在！";
	/**
	 * 修改采集方案时，没有可选择的电能参数
	 */
	public static final String NullElectric_Used = "没有可以选择的电能参数！";
	/**
	 * 删除电能参数时，剩余最后一条不允许删除
	 */
	public static final String deleteLastElectric = "电能参数不允许全部删除！";
	/**
	 * 新增能源类型时，有相同的能源名称/编码
	 */
	public static final String EnergyType_Same = "此能源已经存在！";
	/**
	 * 修改分项时，有相同的分项名称/编码
	 */
	public static final String GradeName_Same = "此分项名称已经存在！";
	/**
	 * 修改分项时，有相同的分项名称/编码
	 */
	public static final String GradeName_SameAll = "此分项名称和编码都已存在！";
	/**
	 * 修改分项时，有相同的分项名称/编码
	 */
	public static final String GradeName_Id = "此分项编码已经存在！";
	/**
	 * 新增分项信息时，有相同的分项编码
	 */
	public static final String GradeCode_Same = "此分项信息内容已存在！";
	/**
	 * 新增分项只能到一级子项
	 */
	public static final String AddGradeLast = "新增分项的数据只允许到一级子项！";
	
	/**
	 * 通信服务中断提示
	 */
	public static final String ServiceException = "通信服务中断，请联系管理员！";
	
	/**
	 * 调取接口，无数据返回
	 */
	public static final String returnNullData = "无数据返回！";
	
	public static final String sameIPAddMsg = "系统已存在相同ip地址和端口号的采集器！";
	/**
	 * 删除采集方案时，判断是否被其他使用
	 */
	public static final String CollectorScheme_Used = "采集方案正在被使用，不能进行操作！";
	/**
	 * 删除电表时，判断是否被支路使用
	 */
	public static final String meter_branch = "该电表已配置到支路上面，不能删除！";
	/**
	 * 导出时候，如果没有查询
	 */
	public static final String DaoChuError = "未查询到需要导出的内容！";
	
	public static final String SaveCollectorInfo = "采集器信息已保存，请及时同步！";
	
	public static final String SaveAmmeterInfo = "电表信息已保存，请及时同步！";
	
	public static final String DelBusMsg = "该总线下有电表已配置到支路上面，请先删除电表！";
	
	public static final String DelCollectorMsg = "采集器下有电表已配置到支路上面，请先删除电表！";

	public static final String DelAreaMsg = "区域下有点位已配置到支路上面，请先删除电表！";

	public static final String DelModuleMsg = "模块下有点位已配置到支路上面，请先删除电表！";

	public static final String DelLineMsg = "线路下有点位已配置到支路上面，请先删除电表！";

	public static final String DelIPRouterMsg = "照明IP路由器节点下有点位已配置到支路上面，请先删除电表！";

	public static final String DelLdcMsg = "照明控制节点下有点位已配置到支路上面，请先删除电表！";

	public static final String DelLdcAreaMsg = "照明区域节点下有点位已配置到支路上面，请先删除电表！";

	public static final String DelTrunkCouplerMsg = "干线耦合器节点下有点位已配置到支路上面，请先删除电表！";

	public static final String DelBranchCouplerMsg = "支线耦合器节点下有点位已配置到支路上面，请先删除电表！";

	public static final String DelParkMsg = "根节点下有点位已配置到支路上面，请先删除电表！";
	/**
	 * 查询人数和面积的时候，判断是否有值
	 */
	public static final String NoneHouseInfo = "分户的基本信息没配置完整！配置完成请刷新重试";
	
	public static final String OperSqlErroe = "操作失败，请联系系统管理员！";
	
	public static final String BranchEnergy = "支路用能";
	
	public static final String IetmEnergy = "分项用能";
	
	public static final String LEAF_TRUE = "1";
	
	public static final String LEAF_FALSE = "0";
	
	/**
	 * 支路用能集抄
	 */
	public static final String BranchSumEnergy = "BranchEnergyAmr.do?reqCode=branchEnergyAmrServiceInit";
	
	/**
	 * 分时段统计
	 */
	public static final String DaypartingStatistics = "SlotCount.do?reqCode=slotCountServiceInit";
	
	/**
	 * 逐时统计
	 */
	public static final String HourlyStatistics = "SubStatistics.do?reqCode=SubStatisticsInit";
	
	/**
	 * 参数查询
	 */
	public static final String ParameterQuery = "ParameterQuery.do?reqCode=ParameterQueryInit";
	
	/**
	 * 同比环比分析
	 */
	public static final String ComparedAnnulusStatistics = "YoyStatistics.do?reqCode=yoyStatisticsInit";
	
	/**
	 * 能耗统计分析
	 */
	public static final String StatisticalAnalysisOfEnergy = "houseEnergyStatistics.do?reqCode=houseEnergyStatisticsInit";
	
	public static final String updateStaticDataMsg = "计算服务处于计算模式，不允许修改数据！";
	
	/**
	 * 能源计划分配
	 */
	public static final String AllocationEnergyResources = "alarmInfo.do?reqCode=alarmInfoServiceInit";
	
	/**
	 * 能源绩效考核
	 */
	public static final String PerformanceAppraisal = "houseEnergyStatistics.do?reqCode=houseEnergyStatisticsInit";
	
	/**
	 * 实时参数查询
	 */
	public static final String RealTimeParam = "realtimeParam.do?reqCode=realtimeParamInit";

	public static final String  LEVELURGENT = "1";//报警等级紧急
	public static final String  LEVELIMPORTENT = "2";//报警等级重要
	public static final String  LEVELCOMMON = "3";//报警等级一般
	/**
	 * 根节点
	 */
	public static final String NodeType_ROOT = "0";
	/**
	 * 区域节点
	 */
	public static final String NodeType_Region = "1";
	/**
	 * 能耗采集节点
	 */
	public static final String NodeType_sys = "31";
	/**
	 * DDC节点
	 */
	public static final String NodeType_DDC = "2";
	/**
	 * IP路由器节点
	 */
	public static final String NodeType_IPRouter = "3";
	/**
	 * 网络控制器节点
	 */
	public static final String NodeType_NetController = "4";
	/**
	 * 干线耦合器节点
	 */
	public static final String NodeType_TrunkCoupler = "5";
	/**
	 * 支线耦合器节点
	 */
	public static final String NodeType_BranchCoupler = "6";
	/**
	 * 总线节点
	 */
	public static final String NodeType_Bus = "8";
	/**
	 * 模块节点
	 */
	public static final String NodeType_Module = "9";
	/**
	 * AI节点
	 */
	public static final String NodeType_AI = "10";
	/**
	 * AO节点
	 */
	public static final String NodeType_AO = "11";
	/**
	 * 采集器节点
	 */
	public static final String NodeType_Collector = "26";
	/**
	 * 采集器总线节点
	 */
	public static final String NodeType_CollectorBus = "27";
	/**
	 * 电表节点
	 */
	public static final String NodeType_Ammeter = "28";
	/**
	 * 虚点能耗电表
	 */
	public static final String NodeType_VpointEne = "16";

	/**
	 * 线路节点
	 */
	public static final String NodeType_Line = "23";

	/**
	 * 照明控制节点
	 */
	public static final String NodeType_Ldc = "21";

	/**
	 * 照明区域节点
	 */
	public static final String NodeType_LdcArea = "34";
	
}
