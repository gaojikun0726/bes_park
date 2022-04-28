package com.efounder.isspServlet;

import com.core.common.conn.db.MySQLDBObject;
import com.efounder.JEnterprise.timer.TimerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * servlet
 * 注解形式
 * @author LvSihan
 *
 */
@WebServlet(loadOnStartup=0,urlPatterns = "/servlet/monitorservlet")
public class MonitorServlet extends HttpServlet{
	private static final Logger log = LoggerFactory.getLogger(MonitorServlet.class);

	private static final long serialVersionUID = -3068172299073908297L;
	
	@Autowired
	private MySQLDBObject mysqlDBObject;
	
	@Override
	public void init() throws ServletException {
		log.info("[ init() ]*****************************************************************");
		log.info("[ init() ]*                                                               *");
		log.info("[ init() ]*                                                               *");
		log.info("[ init() ]*          ISSP应用使能平台   （数据监控端）   V1.0            *");
		log.info("[ init() ]*                                                               *");
		log.info("[ init() ]*                                                               *");
		log.info("[ init() ]*****************************************************************");
		//加载所有可运行的定时任务
		TimerManager.getInstance().loadTimerTask();
		//启动定时器
	    TimerManager.getInstance().startTimer();
		/*
		 * 1.启动线程池
		 */
		// ExecutorServerPool pool = new ExecutorServerPool();
		 //pool.addTask(new InformationBoardStatusMonitorTask());
		 //pool.addTask(new VorxVideoStatusMonitorTask());
		 //pool.addTask(new PushingDeviceStatus());
		// pool.start();
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        this.doPost(req,resp);  
    }  
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.getWriter().write("Hello User.");    
    }


//	@Override
//	public void destroy() {
//		//关闭数据库连接
//		Connection conn = TimerManager.getConn();
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		super.destroy();
//	}
	
}
