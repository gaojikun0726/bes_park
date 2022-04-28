package com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.warningAndAlarm.AlarmTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.AlarmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 警报类型
 * @author liuzhen
 */
@Service("alarmTypeService")
public class AlarmTypeServiceImpl implements AlarmTypeService,ESBaseService{

	@Autowired
	private AlarmTypeMapper alarmTypeMapper;

	@Override
	public ISSPReturnObject getAlarmTypeList(AlarmType alarmType) {
	ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		List<AlarmType> alarmTypes = alarmTypeMapper.findAlarmType(alarmType);
		for(int i =0;i<alarmTypes.size();i++)
		{
			alarmTypes.get(i).setId((i+1)+"");//序号赋值
			alarmTypes.get(i).setFChdate(alarmTypes.get(i).getFChdate().replace(".0",""));
			alarmTypes.get(i).setFCrdate(alarmTypes.get(i).getFCrdate().replace(".0",""));

		}
		returnObject.setList(alarmTypes);
		returnObject.setStatus("1");
		returnObject.setMsg("获取警报类型成功");
	} catch (Exception e) {
		e.printStackTrace();
		returnObject.setStatus("0");
		returnObject.setMsg("获取警报类型失败");
	}
	return returnObject;
}


	@Override
	public ISSPReturnObject getAlarmTypeListBySearch(AlarmType alarmType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<AlarmType> alarmTypes = alarmTypeMapper.findAlarmTypeBySearch(alarmType);
			for(int i =0;i<alarmTypes.size();i++)
			{
				alarmTypes.get(i).setFChdate(alarmTypes.get(i).getFChdate().replace(".0",""));
				alarmTypes.get(i).setFCrdate(alarmTypes.get(i).getFCrdate().replace(".0",""));

			}
			returnObject.setList(alarmTypes);
			returnObject.setStatus("1");
			returnObject.setMsg("获取警报类型成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("获取警报类型失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject insertAlarmType(AlarmType alarmType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String date = DateUtil.getCurrTime();
		alarmType.setFChdate(date);
		alarmType.setFCrdate(date);
		try {
			boolean flag = alarmTypeMapper.insertAlarmType(alarmType);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("新增警报类型成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("新增警报类型失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("新增警报类型失败");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject deleteAlarmType(AlarmType alarmType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		try {
			boolean flag = alarmTypeMapper.deleteAlarmType(alarmType);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("删除报警类型成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("删除报警类型失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("删除警报类型失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject updateAlarmType(AlarmType alarmType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String time =DateUtil.getCurrTime();
		alarmType.setFChdate(time);
		try {
			boolean flag = alarmTypeMapper.updateAlarmType(alarmType);
			if(flag)
			{
				returnObject.setStatus("1");
				returnObject.setMsg("更新报警类型成功");
			}
			else
			{
				returnObject.setStatus("0");
				returnObject.setMsg("更新报警类型失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("更新警报类型失败");
		}
		return returnObject;
	}

}
