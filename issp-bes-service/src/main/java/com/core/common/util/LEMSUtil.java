package com.core.common.util;

import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.efounder.JEnterprise.collectorJob.LEMSConstants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.collectorJob.BESSubAlarmMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BesSysConfMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class LEMSUtil {
	protected static Logger logger = Logger.getLogger(LEMSUtil.class.getName());
	private static final String LEMSERVICE_ADDRESS = "lemService_address";
	private static final String ZC_OPC_SERVER = "ZCOpcServer";//OPC服务
	private static final String HTTP_URI = "HTTP_URI";
	private static final String LEMSERVICE_HOST = "lemService_host";//邮箱账号
	private static final String LEMSERVICE_PASS = "lemService_pass";//邮箱密码
	private static final String LEMRERVICE_HOST = "lemRervice_host";//接收邮箱
	private static final String LEMPHONE = "lemPhone";//接收邮箱
	private static final String SERVICE_TIMEOUT = "serviceTimeOut";
	private static final String SYS_MENU_ID = "sysmenuid";
	private static final boolean IS_DEBUG = false; // 增加日志输出控制判断 add by wujf at 20171212
	
	private static LEMSUtil lemsUtil;
	@Autowired
	private BesSysConfMapper besSysConfMapper;
	@Autowired
	private BESSubAlarmMapper besSubAlarmMapper;
	
	/**
	 * opc数据开关
	 */
	@Value("${system.parameter.opcuseable}")
    private int opcuseable;

	/**
	 * 轮询周期
	 */
	@Value("${system.parameter.bespolltime}")
	private int polltime;

	@PostConstruct
	public void init() {
		lemsUtil = this;
		lemsUtil.besSysConfMapper = this.besSysConfMapper;
		lemsUtil.besSubAlarmMapper = this.besSubAlarmMapper;
	}
	/**
	 * 读取项目配置文件，根据key获取value值
	 * 
	 * @param path
	 * @return
	 */
	public static String GetFilePath(String path) {
		String filePath = null;
		ResourceBundle bundle = ResourceBundle.getBundle("lems");
		try {
			filePath = bundle.getString(path);
		} catch (Exception e) {
			logger.error("认证模块异常：" + e.getMessage(), e);
		}
		return filePath;
	}
	
	public static String GetJDBCProperties(String pro) {
		String filePath = null;
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		try {
			filePath = bundle.getString(pro);
		} catch (Exception e) {
			logger.error("认证模块异常：" + e.getMessage(), e);
		}
		return filePath;
	}
	
	public static String GetFusionChartsFilePath(String http_uri) {
		String filePath = null;
		ResourceBundle bundle = ResourceBundle.getBundle("fusioncharts_export");
		try {
			filePath = bundle.getString(http_uri);
		} catch (Exception e) {
			logger.error("认证模块异常：" + e.getMessage(), e);
		}
		return filePath;
	}
	
	public static String getFusionChartsHttp_uri() {
		String flashpath = LEMSUtil.GetFusionChartsFilePath(HTTP_URI);
		return insureSlash(flashpath);
	}
	
	/**
	 * 获取服务配置的ip地址
	 * @return
	 */
	public static String getLEMService_address() {
		return LEMSUtil.GetFilePath(LEMSERVICE_ADDRESS);
	}
	
	/**
	 * 获取OPC服务的ip地址
	 * @return
	 */
	public static String getZCOpcServer() {
		return LEMSUtil.GetFilePath(ZC_OPC_SERVER);
	}
	/**
	 * 获取邮箱账号
	 * @return
	 */
	public static String lemService_host() {
		return LEMSUtil.GetFilePath(LEMSERVICE_HOST);
	}
	/**
	 * 获取邮箱密码
	 * @return
	 */
	public static String lemService_pass() {
		return LEMSUtil.GetFilePath(LEMSERVICE_PASS);
	}
	/**
	 * 接收邮箱
	 * @return
	 */
	public static String lemRervice_host() {
		return LEMSUtil.GetFilePath(LEMRERVICE_HOST);
	}
	/**
	 * 电话号
	 * @return
	 */
	public static String lemPhone() {
		return LEMSUtil.GetFilePath(LEMPHONE);
	}
	
	public static String getJDBCUser() {
		return LEMSUtil.GetJDBCProperties("g4.jdbc.username");
	}
	
	public static String getJDBCPassword() {
		return LEMSUtil.GetJDBCProperties("g4.jdbc.password");
	}
	
	public static String getJDBCPort() {
		return LEMSUtil.GetFilePath("jdbc.port");
	}
	
	public static String getJDBCDataBaseName() {
		return LEMSUtil.GetFilePath("jdbc.exportDatabaseName");
	}
	
	public static String getMysqlBinPath() {
		return LEMSUtil.GetFilePath("export.mysqlBinPath");
	}
	
	public static Calendar getSysRunTime() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
		String runTime = LEMSUtil.GetFilePath("runingTime");
		if (G4Utils.isEmpty(runTime)) {
			Date date = dateFormat.parse("2016-6-1 00:00:00");
			calendar.setTime(date);
		} else {
			Date date = dateFormat.parse(runTime);
			calendar.setTime(date);
		}
		return calendar;
	}
	
	/**
	 * 与服务器通讯超时时间
	 * @return
	 */
	public static int getServiceTimeOut() {
		String timeout = LEMSUtil.GetFilePath(SERVICE_TIMEOUT);
		return Integer.valueOf(timeout);
	}
	
	public static String getSysMenuids() {
		return LEMSUtil.GetFilePath(SYS_MENU_ID);
	}
	
	/**
	 * 确保目录名带有"/"
	 * @param path	原始目录名
	 * @return	确保带有"/"或"\\"的目录名
	 */
	public static String insureSlash(String path){
		String pathWithSlash = path;
		if (!path.endsWith("/") && !path.endsWith("\\")){
			pathWithSlash += "/";
		}
		return pathWithSlash;
	}
	
	/**
	 * 推送报警信息
	 * @param info
	 */
	public static void pushAlarm(String parkid) {
	}
	/**
	 * 输出日志
	 * @param log
	 */
	public static void log(String str){
		if(IS_DEBUG){ // 增加日志输出控制判断 add by wujf at 20171212
			logger.info(str);
		}
	}
	/**
	 * 写入报警接口
	 * @param location
	 * @param alarmname
	 * @param actualvalue
	 * @param planvalue
	 * @param tipinfo
	 * @param type
	 */
	public static void pushAlarm(String parkid,String location, String alarmname, String actualvalue, String planvalue, String tipinfo, String type){

		BesWainingInfo besWaringInfo = new BesWainingInfo();
		besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
		besWaringInfo.setFLoction(location);//报警位置
		besWaringInfo.setFTipInfo(tipinfo);//提示信息
		besWaringInfo.setFAlarmName(alarmname);//报警名称
		besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
		besWaringInfo.setFType(type);//信息类型
		besWaringInfo.setFYqbh(parkid);//园区编号
		besWaringInfo.setFLevel(LEMSConstants.LEVELIMPORTENT);
		//判断是否存在相同的未处理报警时
		AlarmUtil.insertWarningReal(besWaringInfo);
		//pushAlarm(parkid);
	}
	
	@SuppressWarnings("unchecked")
	public static Dto getMailAddByParkid(String parkid) {
		Dto pDto = new BaseDto();
		if (G4Utils.isNotEmpty(parkid)) {
			String mail = "";
			String phone = "";
			List<Map<String,Object>> ls = lemsUtil.besSubAlarmMapper.queryMailByPark(parkid);
			for (Map<String, Object> dto : ls) {
				String m = (String) dto.get("F_EMAIL");
				if (G4Utils.isNotEmpty(m)) {
					mail = mail + m + "$$$$";
				}
				String p = (String) dto.get("F_PHONE");
				if (G4Utils.isNotEmpty(p)) {
					phone = phone + p + "$$$$";
				}
			}
			pDto.put("mail", mail);
			pDto.put("phone", phone);
		}
		return pDto;
	}

	public int getOpcuseable() {
		return opcuseable;
	}
	public void setOpcuseable(int opcuseable) {
		this.opcuseable = opcuseable;
	}
	public int getPolltime() {
		return polltime;
	}
	public void setPolltime(int polltime) {
		this.polltime = polltime;
	}

}
