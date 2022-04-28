package com.efounder.JEnterprise.mapper.collectorJob;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.collectorJob.BesNoteAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BesNoteAlarmMapper extends BaseMapper<String, BesNoteAlarm>{

	/**
	 * 插入短信报警信息
	 * @param besNoteAlarm
	 */
	public void insertDuanXinInfo(BesNoteAlarm besNoteAlarm);
}