package com.efounder.JEnterprise.service.collectorJob.impl;

import com.efounder.JEnterprise.collectorJob.QuartzManager;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.model.collectorJob.BESSysConf;
import com.efounder.JEnterprise.service.collectorJob.InitJobService;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 采集器任务service实现类
 * @author LvSihan
 *
 */
@Component
@Service("InitJobService")
public class InitJobServiceImpl implements InitJobService {
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static InitJobServiceImpl InitJobServiceImpl;

	/*@Value("${system.parameter.collectortime}")
	private String time;*/

	@Autowired
	private BESJobManagerMapper besJobManagerMapper;

	@PostConstruct
	public void init() {
		InitJobServiceImpl = this;
		InitJobServiceImpl.besJobManagerMapper = this.besJobManagerMapper;
	}


	public void InitService() {
		//查询job列表
		List<Map<String,Object>> lstJob = InitJobServiceImpl.besJobManagerMapper.queryJob();		
		//遍历，启动所有任务
		for (int i=0;i<lstJob.size();i++){
			String time = (String) lstJob.get(i).get("F_FIXED_TIME");
			String name = (String) lstJob.get(i).get("F_CLASS_NAME");
			String className = (String) lstJob.get(i).get("F_CLASS_PATH");

			// 如果有异常忽略
			try {
				Job job = (Job) Class.forName(className).newInstance();
				QuartzManager.addJob(name, job, time);
				System.out.print("启动任务: " + name + "\n");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SchedulerException
					| ParseException e) {
			}							
		}
		
		//监测上次运行时间
		Map<String,Object> lastRunTime = InitJobServiceImpl.besJobManagerMapper.querySysConf();
		
		if (lastRunTime == null || !lastRunTime.containsKey("F_LASTRUNTIME")){
			return ;
		}
		
		Date lastTime = null;
		Date now = new Date();
		
		try {
			lastTime = fmt.parse((String) lastRunTime.get("F_LASTRUNTIME"));
		} catch (ParseException e) {
		}
		
		if (lastTime == null){
			return ;
		}
		
		//写入数据库中，等待空闲时间执行
		BESSysConf besSysConf = new BESSysConf(); 
		besSysConf.setfStarttime(fmt.format(lastTime));
		besSysConf.setfEndtime(fmt.format(now));
		InitJobServiceImpl.besJobManagerMapper.updateCalculateTime(besSysConf);
	}
}
