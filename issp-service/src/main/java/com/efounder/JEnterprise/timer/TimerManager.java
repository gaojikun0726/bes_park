package com.efounder.JEnterprise.timer;

import com.alibaba.fastjson.JSONObject;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.conn.db.MySQLDBUtil;
import com.core.common.data.ISSPDataSet;
import com.core.common.data.ISSPRowSet;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerList;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerType;
import com.efounder.JEnterprise.timer.state.TimerStubStateRUNNING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * 定时器
 * @author LvSihan
 *
 */
@Component
public class TimerManager implements Runnable{
	private static TimerManager timerManager;
	
	public static TimerManager getInstance(){
		if (timerManager == null || flag ) {
			timerManager = new TimerManager();  
			if (flag){
				flag = false;
			}
        } 		
		return timerManager;
	}
	@Autowired
	private MySQLDBObject mysqlDBObject;
	
	@PostConstruct
	public void init() {
		timerManager = this;
		timerManager.mysqlDBObject = this.mysqlDBObject;

	}
	/**
	 * 立即启动是否成功
	 */
	private static boolean startflag = false;

	private static boolean flag = false;
	/**
	 * 定时器状态，初始为false
	 */
	private static boolean timerState = false;
	/**
	 * 定时器循环时间，默认为1
	 */
	protected int loopTimer = 1;
	
	RequestProcessor.Task openNodeObjectTask = null;

	private static java.util.List<TimerStub> timerStubList = new java.util.Vector<TimerStub>();
	

	
	/**
	 * 启动定时器
	 */
	public final void startTimer() {
		if (openNodeObjectTask == null) {
			openNodeObjectTask = RequestProcessor.getDefault().create(this);
			// 多长时间后执行
			openNodeObjectTask.schedule(loopTimer * 1000 * 10);// 以秒为单位-10秒后执行
			TimerManager.setTimerState(true);//设置定时器状态
		}
	}
	/**
	 * 关闭定时器
	 */
	public final void stopTimer() {
		if (openNodeObjectTask != null) {
			openNodeObjectTask.cancel();
			openNodeObjectTask = null;
			TimerManager.setTimerState(false);
		}
	}
	/**
	 * 轮询定时任务
	 */
	public final void runTimerService() {		
		TimerStub timerStub = null;
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < timerStubList.size(); i++) {
			// 获取一个TimerStub
			timerStub = timerStubList.get(i);
			// 检查是否能运行
			if (canTimerRunService(timerStub, c)) {
				// 执行具体的服务
				timerThreadRunService(timerStub);				
			}
		}
	}
	/**
	 * 判断是否可执行
	 * @param timerStub
	 * @param c
	 * @return
	 */
	protected boolean canTimerRunService(TimerStub timerStub, Calendar c) {
		// 如果正在运行，则返回
		if (timerStub.isRunning())
			return false;
		// 如果不是处在可运行状态，则返回,通过管理控制台可以切换状态
		if (!(timerStub.getState() instanceof TimerStubStateRUNNING))
			return false;
		timerStub.setLoopCount(timerStub.getLoopCount() + 1);
//		System.out.println(timerStub.getTimerCaption()+"任务的第"+timerStub.getLoopCount()+"次轮询");
		return TimerUtil.isTimeFit(timerStub, c);
	}
	/**
	 * 新建线程，调用具体的服务
	 * @param timerStub
	 */
	protected void timerThreadRunService(TimerStub timerStub) {
	    TimerThread tt = TimerThread.getInstance(this,timerStub);
	    RequestProcessor.Task rptt = RequestProcessor.getDefault().create(tt);;
	    rptt.schedule(0);
	    // 执行次数加一
	    timerStub.setExecuteCount(timerStub.getExecuteCount() + 1);
	}
	
	/**
	 * 连接数据库，获取可运行的定时任务
	 */
	public void loadTimerTask() {		
		// 先清除
		timerStubList.clear();		
		String queryTimerclass = "select * from issp_timer_class";
		ISSPDataSet dataSetTimerclass;
		Connection conn = timerManager.mysqlDBObject.getConnection();
		try {
			dataSetTimerclass = MySQLDBUtil.executeQuerySQL(conn, queryTimerclass, null);
			int rowCountTimerclass = dataSetTimerclass.getRowCount();
			if(rowCountTimerclass > 0) {
				for (int i = 0; i < rowCountTimerclass; i++) {
//					ISSPRowSet rowset = dataSetTimerclass.getRowSet(i);
//			String time = rowset.getString("job_time", "");
//			String name = rowset.getString("F_CLASS_NAME", "");
//			String className = rowset.getString("F_CLASS_PATH", "");
//			/**
//			 * 动态获取类对象,强制转接口
//			 */
//			//如果有异常忽略
//			try {
//				Job job = (Job) Class.forName(className).newInstance();
//				//QuartzManager.addJob(name, job, time);				
//				System.out.print("启动任务: " + name + "\n");
//			} catch (InstantiationException | IllegalAccessException
//					| ClassNotFoundException e) {
//			}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查询状态为‘0’(可运行状态)的定时任务，包括定时对象，定时类型等
		String sql = "select a.F_TIMER_BH,a.F_TIMER_NAME,a.F_TIMER_STATE,a.F_TIMER_PARAMS," + 
				"b.F_TIMER_CLASSBH,b.F_TIMER_TYPEBH,d.F_NAME,"+ 
				"b.F_LOOP_TIME,b.F_FIXED_TIME,b.F_VAR_TIME,a.F_START_TIME,a.F_STOP_TIME " +				
				"from issp_timer_manage a "+ 
				"LEFT JOIN issp_timer_list b on a.F_TIMER_BH = b.F_TIMER_BH " + 
				"LEFT JOIN issp_timer_type d on b.F_TIMER_TYPEBH = d.F_TYPE "+ 				
				"where a.F_TIMER_STATE = '1'";
		ISSPDataSet dataSet;
		try {
			dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
			int rowCount = dataSet.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				ISSPRowSet rowset = dataSet.getRowSet(i);		
				//解析后放入timerStubList中
				loadScheduleTask(null,null,rowset);			
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 解析数据，放入TimerStub
	 * @param reTimer
	 * @param reTimerClass
	 * @param reTimerType
	 * @param rowset
	 */
	@SuppressWarnings("static-access")
	public void loadScheduleTask(ISSPTimerList reTimer, ISSPTimerType reTimerType, ISSPRowSet rowset) {
		TimerStub ts = null;
		//rowset为null，是前台‘立即启动’调用的
		if(rowset == null) {
			ts = new TimerStub();
			// ID
			ts.setTimerID(reTimer.getfTimerBh());
			// caption
			ts.setTimerCaption(reTimer.getfTimerName());
			// 循环时间
			ts.setLoopTimer(Integer.parseInt(reTimer.getfLoopTime()));
			// 初始状态是运行
			ts.setState(new TimerStubStateRUNNING());
			// 定时类型:loop循环执行(默认方式),date固定时间执行...,day每天执行,week每周执行,month每月执行
			String timerType = reTimerType.getfType();
			if (timerType == null || timerType.trim().length() == 0)
				timerType = ts._TIMER_TYPE_LOOP_;
			ts.setTimerType(timerType);
			// 开始时间，一般是时分秒
			ts.setFixedTime(reTimer.getfFixedTime());
			// 变量时间
			ts.setVarTime(reTimer.getfVarTime());
			// 固定时间，计算出固定时间来，兼容
			if (ts._TIMER_TYPE_DATE_.equals(ts.getTimerType())) {
				String fixedTimer = ts.getVarTime() + " " + ts.getFixedTime();
				ts.setFixedTimer(TimerUtil.paraseDate(fixedTimer));
			}
			timerThreadRunService(ts);
		}else {
			ts = new TimerStub();
			// ID
			ts.setTimerID(rowset.getString("F_TIMER_BH", ""));
			// caption
			ts.setTimerCaption(rowset.getString("F_TIMER_NAME", ""));
			// 循环时间
			ts.setLoopTimer(Integer.parseInt(rowset.getString("F_LOOP_TIME", "")));
			// 初始状态是运行
			ts.setState(new TimerStubStateRUNNING());
			// 定时类型:loop循环执行(默认方式),date固定时间执行...,day每天执行,week每周执行,month每月执行
			String timerType = rowset.getString("F_TIMER_TYPEBH", "");
			if (timerType == null || timerType.trim().length() == 0)
				timerType = ts._TIMER_TYPE_LOOP_;
			ts.setTimerType(timerType);
			// 开始时间，一般是时分秒
			ts.setFixedTime(rowset.getString("F_FIXED_TIME", ""));
			// 变量时间
			ts.setVarTime(rowset.getString("F_VAR_TIME", ""));
			// 固定时间，计算出固定时间来，兼容
			if (ts._TIMER_TYPE_DATE_.equals(ts.getTimerType())) {
				String fixedTimer = ts.getVarTime() + " " + ts.getFixedTime();
				ts.setFixedTimer(TimerUtil.paraseDate(fixedTimer));
			}
		}
			insertTimerStub(ts);
		}
	/**
	 * 将TimerStub中的数据放入timerStubList
	 * @param timerStub
	 */
	protected void insertTimerStub(TimerStub timerStub) {
		timerStubList.add(timerStub);
	}
	

	/**
	 * 动态调用方法
	 * @param timerStub
	 */
	public static void callTaskProcess(TimerStub timerStub) {
		//判断数据库连接是否有效

		Connection conn = timerManager.mysqlDBObject.getConnection();

		JSONObject jsonObject = new JSONObject();

		//获取接口服务需要的ID,
		String sql = "select c.F_GUID,b.*,c.F_METHOD,c.F_SERVICE_MC,c.F_PRO_FUN_ID  "
				+ "FROM issp_eq_join_task a " 
				+ "LEFT JOIN issp_equipmentadapter b on a.F_EQADAPTER_GUID = b.F_GUID "
				+ "LEFT JOIN issp_intefaceadapter c on a.F_INTERFACE_GUID = c.F_GUID " 
				+ "where  a.F_TIMER_BH = '"+timerStub.getTimerID() + "'";
		ISSPDataSet dataSet;
		try {
			dataSet = MySQLDBUtil.executeQuerySQL(conn, sql, null);
			int rowCount = dataSet.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				ISSPRowSet rowset = dataSet.getRowSet(i);	
				// 类路径
				timerStub.setF_CLASS_PATH(rowset.getString("F_CLASSPATH", ""));
				// 方法名
				timerStub.setF_METHOD(rowset.getString("F_METHOD", ""));
				//协议函数ID
				jsonObject.put("F_PRO_FUN_ID", rowset.getString("F_PRO_FUN_ID", ""));
				//设备适配器信息
				jsonObject.put("F_GUID", rowset.getString("F_GUID", ""));//适配器F_GUID
				jsonObject.put("F_ADAPTERMC", rowset.getString("F_ADAPTERMC", ""));//适配器名称
				jsonObject.put("F_SBLX", rowset.getString("F_SBLX", ""));//设备类型
				jsonObject.put("F_SBMC", rowset.getString("F_SBMC", ""));//设备名称
				jsonObject.put("F_MANUFACTURER", rowset.getString("F_MANUFACTURER", ""));//服务提供厂商
				jsonObject.put("F_IP", rowset.getString("F_IP", ""));//服务IP地址
				jsonObject.put("F_PORT", rowset.getString("F_PORT", ""));//服务端口
				jsonObject.put("F_CLASSPATH", rowset.getString("F_CLASSPATH", ""));//适配器处理类

				
//				String inputString = rowset.getString("F_PARAMS", "");
//				Map<String, Object> eDto = new HashMap<String, Object>();
//				eDto = StringFunction.String2HashMap(inputString, eDto, ";");
//				jsonObject.putAll(eDto);			
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
		//获得这个类
		Object myClass = null;
		try {
			myClass = Class.forName(timerStub.getF_CLASS_PATH()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//获得这个类中名叫**的参数为JSONObject的方法
		Method method = null;
		try {
			method = myClass.getClass().getMethod(timerStub.getF_METHOD(),JSONObject.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		//调用这个方法		
		try {
			 method.invoke(myClass,jsonObject);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		// 运行定时服务
		runTimerService();	   
		//定时器的轮询时间间隔
		openNodeObjectTask.schedule(loopTimer*1000*60);
	}

	public static boolean isTimerState() {
		return timerState;
	}


	public static void setTimerState(boolean timerState) {
		TimerManager.timerState = timerState;
	}
	
	public static java.util.List<TimerStub> getTimerStubList() {
		return timerStubList;
	}


	public static void setTimerStubList(java.util.List<TimerStub> timerStubList) {
		TimerManager.timerStubList = timerStubList;
	}

	public static boolean isStartflag() {
		return startflag;
	}


	public static void setStartflag(boolean startflag) {
		TimerManager.startflag = startflag;
	}

}
