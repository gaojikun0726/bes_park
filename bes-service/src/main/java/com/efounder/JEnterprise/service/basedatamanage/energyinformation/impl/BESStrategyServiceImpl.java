package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESStrategyMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeSceneInfo;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTaskSyncSb;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESStrategyService;
import com.efounder.JEnterprise.util.CronUtil;
import com.efounder.JEnterprise.util.TaskScheduleModel;
import com.efounder.constants.ScheduleConstants;
import com.efounder.exception.TaskException;
import com.efounder.util.ScheduleUtils;
import com.framework.common.utils.ScopeDataUtil;
import org.apache.shiro.SecurityUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.efounder.util.ScheduleUtils.getJobKey;


@Service("BESStrategyService")
public class BESStrategyServiceImpl implements BESStrategyService {
	private static final Logger log = LoggerFactory.getLogger(BESStrategyServiceImpl.class);

	@Autowired
	private BESStrategyMapper besStrategyMapper;

	@Autowired
	private Scheduler scheduler;

	/**
	 * 获取树的数据
	 * @return
	 */
	@Override
	public ISSPReturnObject getStrategyTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		String userCode = principal.getUser().getF_yhbh();//获取当前登录用户

		List<BESStrategy> returnList = besStrategyMapper.getStrategyTree(userCode);
		returnObject.setData(returnList);
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 * 查询该策略的配置信息
	 * @param id
	 * @return
	 */
	@Override
	public ISSPReturnObject queryTableParam(String id) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		Map<String,Object> map = besStrategyMapper.queryTableParam(id); //查询策略信息

		//策略与支路关系
		List<String> strategyBranch = besStrategyMapper.queryStrategyBranch(id);
		if (strategyBranch != null && strategyBranch.size() > 0){
			map.put("strategyBranch",strategyBranch);
		} else {
//			map.put("branchList","");
			map.put("strategyBranch","");
		}


		//策略与部门关系
		List<String> strategyDepartment = besStrategyMapper.queryStrategyDepartment(id);
		if (strategyDepartment != null && strategyDepartment.size() > 0){

			map.put("strategyDepartment",strategyDepartment);
		} else {
			map.put("strategyDepartment","");
		}

		if (map.get("f_type") != null && "0".equals(map.get("f_type"))){
			//String f_id =  besceneConfigMapper.selectSceneModeMaxId(id);
			returnObject.setMap(map);
			returnObject.setStatus("0");
		}else {
			returnObject.setMap(map);
			returnObject.setStatus("1");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject updateStrategyName(String id,String name){
		ISSPReturnObject returnObject = new ISSPReturnObject();

		boolean flag = besStrategyMapper.updateStrategyName(id,name);

		if (flag){
			returnObject.setStatus("1");
			returnObject.setMsg("修改策略名称成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("修改策略名称失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject deleteStrategy(String id){
		ISSPReturnObject returnObject = new ISSPReturnObject();

		boolean flag = besStrategyMapper.deleteStrategy(id);

		if (flag){

			returnObject.setStatus("1");
			returnObject.setMsg("删除策略成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除策略失败");
		}
		return returnObject;
	}


	/**
	 * 添加策略配置
	 */
	@Override
	public ISSPReturnObject add(BESStrategy besStrategy) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//获取当前登录用户
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		String userCode = principal.getUser().getF_yhbh();
		besStrategy.setF_user_code(userCode);
		besStrategy.setF_user_name(principal.toString());

		//生成cron表达式
		TaskScheduleModel scheduleModel = new TaskScheduleModel();
		scheduleModel.setJobType(besStrategy.getF_cron_job_type());
		scheduleModel.setStartDate(besStrategy.getF_cron_start_date());
		try {
			String cronExpression = CronUtil.createCronExpression(scheduleModel);
			besStrategy.setF_cron(cronExpression);
		} catch (Exception e) {
			e.printStackTrace();
		}

		besStrategyMapper.insertStrategy(besStrategy);

		if (besStrategy.getF_id() != null) {
			returnObject.setStatus("1");
			returnObject.setData(besStrategy.getF_id());

		} else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 修改策略配置
	 */
	@Override
	public ISSPReturnObject update(JSONObject object) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		String f_id = (String) object.get("f_id");
		String f_pId = (String) object.get("f_pId");

		BESStrategy besStrategy = new BESStrategy();
		besStrategy.setF_id(f_id);
		besStrategy.setF_pId(f_pId);
		besStrategy.setF_name((String) object.get("f_name"));
		besStrategy.setF_type((String) object.get("f_type"));
		besStrategy.setF_cron((String) object.get("f_cron"));
		besStrategy.setF_status((String) object.get("f_status"));
		besStrategy.setF_range((String) object.get("f_range"));
		besStrategy.setF_email((String) object.get("f_email"));
		besStrategy.setF_cron_job_type(object.getInteger("f_cron_job_type"));
		besStrategy.setF_cron_start_date((String) object.get("f_cron_start_date"));

		//生成cron表达式
		TaskScheduleModel scheduleModel = new TaskScheduleModel();
		scheduleModel.setJobType(object.getInteger("f_cron_job_type"));
		scheduleModel.setStartDate((String) object.get("f_cron_start_date"));
		try {
			String cronExpression = CronUtil.createCronExpression(scheduleModel);
			besStrategy.setF_cron(cronExpression);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//修改策略配置表bes_strategy信息
		boolean flag = besStrategyMapper.updateStrategy(besStrategy);


		List<Map<String,Object>> branchList = (List<Map<String,Object>>) object.get("branchList");
		List<Map<String,Object>> departmentList = (List<Map<String,Object>>) object.get("departmentList");

		if (flag){

			//判断父节点(1默认,2层级,3部门),修改相应关系表数据
			if (f_pId != null && "1".equals(f_pId)){
				//默认,层级和部门都有
				this.strategyBranch(f_id,branchList);
				this.strategyDepartment(f_id,departmentList);
			} else if (f_pId != null && "2".equals(f_pId)){
				//层级
				this.strategyBranch(f_id,branchList);
			} else {
				//部门
				this.strategyDepartment(f_id,departmentList);
			}


			//从策略配置表查询有无定时任务id,有则删除
			String f_job_id = besStrategyMapper.queryStrategyJobId(f_id);
			if (f_job_id != null && !"".equals(f_job_id)){
				this.deleteStrategyJob(f_job_id,f_id);
			}

			String f_status = besStrategy.getF_status();
			//状态为启用时,创建新的定时任务
			if ("1".equals(f_status)){
				try{
					ISSPReturnObject returnObject1 = this.insertStrategyJob(besStrategy);
					if ("1".equals(returnObject1.getStatus())){
						//创建成功,把定时任务Id存到配置策略表中
						String fJobId = returnObject1.getData().toString();
						besStrategyMapper.updateStrategyJobId(fJobId,f_id);
					} else {
						returnObject.setStatus("0");
						returnObject.setMsg("修改策略失败");
						return returnObject;
					}
				} catch (SchedulerException e){
					returnObject.setStatus("0");
					e.printStackTrace();
				} catch (TaskException e){
					returnObject.setStatus("0");
					e.printStackTrace();
				}
			}

			returnObject.setStatus("1");
			returnObject.setMsg("修改策略成功");

		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("修改策略失败");
		}
		return returnObject;

	}

	//策略与支路关系
	private void strategyBranch(String fStrategyId,List<Map<String,Object>> branchList){
		//先删除该策略之前所有的支路
		besStrategyMapper.deleteBranch(fStrategyId);
		//再重新添加
		if (branchList != null && branchList.size() > 0){
			String f_zlbh = "";
			String f_level = "";
			String f_zlmc = "";
			for (Map<String,Object> branch:branchList){

				f_zlbh = branch.get("f_zlbh").toString();
				f_level = branch.get("f_level").toString();
				f_zlmc = branch.get("f_zlmc").toString();

				besStrategyMapper.insertBranch(fStrategyId,f_zlbh,f_level,f_zlmc);
			}
		}
	}

	//策略与部门关系
	private void strategyDepartment(String fStrategyId,List<Map<String,Object>> departmentList){
		//先删除该策略之前所有的支路
		besStrategyMapper.deleteDepartment(fStrategyId);
		//再重新添加
		if (departmentList != null &&departmentList.size() > 0){
			String f_department_id = "";
			String f_level = "";
			String f_zlmc = "";
			for (Map<String,Object> department:departmentList){
				f_department_id = department.get("f_department_id").toString();
				f_level = department.get("f_level").toString();
				f_zlmc = department.get("f_zlmc").toString();
				besStrategyMapper.insertDepartment(fStrategyId,f_department_id,f_level,f_zlmc);
			}
		}
	}

	//添加定时任务
	private ISSPReturnObject insertStrategyJob(BESStrategy besStrategy) throws SchedulerException, TaskException {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		String strategyId = besStrategy.getF_id();

		SysJob sysJob = new SysJob();

		String f_id = UUIDUtil.getRandom32BeginTimePK();

		sysJob.setJobId(f_id);

		sysJob.setJobName(besStrategy.getF_name());

		sysJob.setJobGroup("sendReportToEmail");

		String invokeTarget = "besTask.executeStrategy('" + strategyId +"')";

		sysJob.setInvokeTarget(invokeTarget);

		sysJob.setCronExpression(besStrategy.getF_cron());

		sysJob.setMisfirePolicy("3");

		sysJob.setConcurrent("0");

		sysJob.setStatus(besStrategy.getF_status());

		sysJob.setCreateBy("admin");

		sysJob.setUpdateBy("admin");

		int rows = besStrategyMapper.insertSysJobSyncInfo(sysJob);

		if (rows > 0){
			ScheduleUtils.createScheduleJob(scheduler,sysJob);
			returnObject.setStatus("1");
			returnObject.setData(f_id);
		}else {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	//删除定时任务
	public ISSPReturnObject deleteStrategyJob(String jobId,String f_id){

		ISSPReturnObject returnObject = new ISSPReturnObject();

		String jobGroup = "sendReportToEmail";

		//删除定时任务
		try {
			scheduler.deleteJob(getJobKey(jobId, jobGroup));
			besStrategyMapper.deleteJobInfo(jobId);
			besStrategyMapper.updateStrategyJobId("",f_id);
			returnObject.setStatus("1");
		} catch (SchedulerException e) {
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

}
