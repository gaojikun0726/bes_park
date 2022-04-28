package com.efounder.JEnterprise.collectorJob;

import com.efounder.JEnterprise.mapper.collectorJob.BESHouseAlarmMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HouseAlarmJob implements Job {
	
	private static final Logger log = LoggerFactory.getLogger(CollectorThread.class);
	@Autowired
	private BESHouseAlarmMapper besHouseAlarmMapper;

	@SuppressWarnings("unchecked")
	private static HouseAlarmJob houseAlarmJob;

	@PostConstruct
	public void init() {
		houseAlarmJob = this;
		houseAlarmJob.besHouseAlarmMapper = this.besHouseAlarmMapper;
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("开始轮训分户报警!");
		List<Map<String,Object>> parkList = houseAlarmJob.besHouseAlarmMapper.queryAllPark();
		List<HouseAlarmThread> houThreads = new ArrayList<>();
		for (Map<String,Object> park : parkList) {
			try {
				HouseAlarmThread houseThread = new HouseAlarmThread();
				houseThread.setParkId((String) park.get("F_YQBH"));
				houseThread.setParkName((String) park.get("F_YQMC"));
				houseThread.start();
				houThreads.add(houseThread);
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		for (HouseAlarmThread ht : houThreads) {
			try {
				ht.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("结束轮训分户报警!");
	}
}
