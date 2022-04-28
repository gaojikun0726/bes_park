package com.efounder.JEnterprise.collectorJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;


/**
 * Quartz管理类
 * @author LvSihan
 *
 */
public class QuartzManager {
	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, Job job, String time) throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		// 任务名，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobName, JOB_GROUP_NAME).build();
		// 触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)// 触发器名,触发器组
				.withSchedule(cronSchedule(time))
				.build();
		sched.scheduleJob(jobDetail, trigger);
		// 启动
		if (!sched.isShutdown())
			sched.start();
	}

	/**
	 * 获取任务时间
	 * 
	 * @param jobName
	 * @return
	 * @throws SchedulerException
	 */
	// public static String getJobTime(String jobName) throws SchedulerException{
	// Scheduler sched = sf.getScheduler();
	// CronTrigger trigger = (CronTrigger)
	// sched.getTrigger(jobName,TRIGGER_GROUP_NAME);
	// if (trigger == null) {
	// return "";
	// }
	// return trigger.getCronExpression();
	// }

	/** *//**
			 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
			 * 
			 * @param jobName
			 * @throws SchedulerException
			 */
	// public static void removeJob(String jobName)
	// throws SchedulerException{
	// Scheduler sched = sf.getScheduler();
	// sched.pauseTrigger(jobName,TRIGGER_GROUP_NAME);//停止触发器
	// sched.unscheduleJob(jobName,TRIGGER_GROUP_NAME);//移除触发器
	// sched.deleteJob(jobName,JOB_GROUP_NAME);//删除任务
	// }
}
