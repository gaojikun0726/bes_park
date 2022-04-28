package com.efounder.JEnterprise;

import com.alibaba.fastjson.JSONObject;
import com.core.common.config.SocketServerConfigImpl;
import com.core.common.config.WebSocketConfigImpl;
import com.efounder.JEnterprise.commhandler.MsgSubPubHandler;
import com.efounder.JEnterprise.commhandler.SyncTimeBatchHandler;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesCollectorMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDdcMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import com.efounder.JEnterprise.service.collectorJob.InitJobService;
import com.efounder.JEnterprise.timer.manage.PduStatusManage;
import com.efounder.datareported.service.DataReportActuator;
import org.ace.TftpServer.TFtpServer;
import org.ace.socketserver.handler.SocketServerRunner;
import org.ace.websocket.handler.WebSocketRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class BESApplicationRunner implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(BESApplicationRunner.class);

	/*@Value("${system.parameter.collector}")
	private String collectorEnable;*/
	@Value("${system.parameter.bessbtreethread}")
	private String bessbtreethread;
	@Autowired
	private InitJobService initJobService;

	@Autowired
	private WebSocketConfigImpl webSocketConfig;

	@Autowired
	private SocketServerConfigImpl socketServerConfig;

	@Autowired
	private BesCollectorMapper besCollectorMapper;

	@Autowired
	private BesDdcMapper besDdcMapper;

	@Autowired
	private BESSbdyMapper besSbdyMapper;


	@Autowired
	private DataReportActuator dataReportActuator;

	@Value("${tftpServerSendMsg.address}")
	private String tftpServerAddress;

	@Autowired
	private PduStatusManage pduStatusManage;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// 启动时时设置能耗采集器在线状态为离线（确保能耗采集器在线状态和下位机一致）
		BesCollector besCollector = new BesCollector();
		besCollector.setfOnlineState("0");
		besCollectorMapper.updateCollector(besCollector);

		// 启动时时设置ddc控制器在线状态为离线（确保ddc控制器在线状态和下位机一致）
		BesDdc record = new BesDdc();
		record.setfDdcOnlinestate("0");
		besDdcMapper.updateByGuidSelective(record);

		// 启动时时设置设备树节点在线状态为离线（确保设备树节点在线状态和下位机一致）
		JSONObject obj = new JSONObject();
		obj.put("f_status", "0");
		besSbdyMapper.updatesbdyByPoint(obj);

		// 启动 webSocket
		WebSocketRunner ws = new WebSocketRunner(webSocketConfig);
		Thread wsRunThread = new Thread(ws);
		wsRunThread.start();

		// 启动 socket 与下位机建立连接
		SocketServerRunner ss = new SocketServerRunner(socketServerConfig);
		Thread ssRunThread = new Thread(ss);
		ssRunThread.start();

		Timer timer = new Timer(true);

		// 一小时执行一次清理订阅消息
		MsgSubPubHandler msgSubPubHandler = new MsgSubPubHandler();
		timer.schedule(msgSubPubHandler, MsgSubPubHandler.INTERVAL, MsgSubPubHandler.INTERVAL);

		// 一天同步一次所有控制器时间
		SyncTimeBatchHandler syncTimeBatchHandler = new SyncTimeBatchHandler();
		timer.schedule(syncTimeBatchHandler, SyncTimeBatchHandler.INTERVAL, SyncTimeBatchHandler.INTERVAL);

		new TFtpServer(tftpServerAddress).start();

		// 数据上报执行器启动
		dataReportActuator.start();

		//智慧灯杆初始化
		pduStatusManage.init();
	}

}
