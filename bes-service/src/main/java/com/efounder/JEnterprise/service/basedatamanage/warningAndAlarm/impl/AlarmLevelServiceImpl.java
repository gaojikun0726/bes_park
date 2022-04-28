package com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.warningAndAlarm.AlarmLevelMapper;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmLevel;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.AlarmLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 警报等级
 * @author liuzhen
 */
@Service("alarmLevelService")
public class AlarmLevelServiceImpl implements AlarmLevelService,ESBaseService{

	@Autowired
	private AlarmLevelMapper alarmLevelMapper;

	@Override
	public ISSPReturnObject getAlarmLevelList(AlarmLevel alarmLevel) {
	ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		List<AlarmLevel> alarmLevels = alarmLevelMapper.findAlarmLevel(alarmLevel);
		for(int i =0;i<alarmLevels.size();i++)
		{
			alarmLevels.get(i).setId((i+1)+"");//序号赋值
			alarmLevels.get(i).setFChdate(alarmLevels.get(i).getFChdate().replace(".0",""));
			alarmLevels.get(i).setFCrdate(alarmLevels.get(i).getFCrdate().replace(".0",""));

		}
		returnObject.setList(alarmLevels);
		returnObject.setStatus("1");
		returnObject.setMsg("获取警报等级成功");
	} catch (Exception e) {
		e.printStackTrace();
		returnObject.setStatus("0");
		returnObject.setMsg("获取警报等级失败");
	}
	return returnObject;
}


	@Override
	public ISSPReturnObject getAlarmLevelListBySearch(AlarmLevel alarmLevel) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<AlarmLevel> alarmLevels = alarmLevelMapper.findAlarmLevelBySearch(alarmLevel);
			for(int i =0;i<alarmLevels.size();i++)
			{
				alarmLevels.get(i).setId((i+1)+"");//序号赋值
				alarmLevels.get(i).setFChdate(alarmLevels.get(i).getFChdate().replace(".0",""));
				alarmLevels.get(i).setFCrdate(alarmLevels.get(i).getFCrdate().replace(".0",""));

			}
			returnObject.setList(alarmLevels);
			returnObject.setStatus("1");
			returnObject.setMsg("获取警报等级成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("获取警报等级失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject insertAlarmLevel(AlarmLevel alarmLevel) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String date = DateUtil.getCurrTime();
		alarmLevel.setFChdate(date);
		alarmLevel.setFCrdate(date);
		try {
			boolean flag = alarmLevelMapper.insertAlarmLevel(alarmLevel);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("新增警报等级成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("新增警报等级失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("新增警报类型失败");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject deleteAlarmLevel(AlarmLevel alarmLevel) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {
			boolean flag = alarmLevelMapper.deleteAlarmLevel(alarmLevel);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("删除报警等级成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("删除报警等级失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("删除警报类型失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject updateAlarmLevel(AlarmLevel alarmLevel) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String time =DateUtil.getCurrTime();
		alarmLevel.setFChdate(time);
		try {
			boolean flag = alarmLevelMapper.updateAlarmLevel(alarmLevel);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("更新报警等级成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("更新报警等级失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("更新警报等级失败");
		}
		return returnObject;
	}

}
