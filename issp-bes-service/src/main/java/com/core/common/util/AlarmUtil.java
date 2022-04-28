package com.core.common.util;

import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AlarmUtil {
	private static AlarmUtil alarmUtil;
	@Autowired
	private  BesWainingRealInfoMapper besWainingRealInfoMapper;
	@PostConstruct
	public void init() {
		alarmUtil = this;
		alarmUtil.besWainingRealInfoMapper = this.besWainingRealInfoMapper;
	}
	/**
	 * 判断是否插入报警信息
	 * @param besWaringInfo
	 */
	public static void insertWarningReal(BesWainingInfo besWaringInfo) {
		//查询所有报警位置相同，且未处理的信息
		List<BesWainingInfo> warningRealList = alarmUtil.besWainingRealInfoMapper.queryWarningRealList(besWaringInfo.getFLoction(),besWaringInfo.getFTipInfo());
		//已存在相同的未处理报警时，不插入
		if(warningRealList.size()>0) {
			
		}else {
			alarmUtil.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);	
		}
	}

	/**
	 *
	 * @Description: 能耗的报警信息判断
	 *
	 * @auther: wanghongjie
	 * @date: 15:49 2020/6/3
	 * @param:
	 * @return:
	 *
	 */
	public static void insertWarningRealAmmeter(BesWainingInfo besWaringInfo){
		//查询所有报警位置相同，且未处理的信息
		List<BesWainingInfo> warningRealList = alarmUtil.besWainingRealInfoMapper.queryWarningRealList(besWaringInfo.getFLoction(),besWaringInfo.getFTipInfo());
		//当有报警消息时,插入到报警记录表bes_waring_real
//		alarmUtil.besWainingRealInfoMapper.insertWarningInfo(besWaring Info);
		if(warningRealList.size()>0) {//已存在相同的未处理报警时，跟新
			alarmUtil.besWainingRealInfoMapper.updateWarningInfoData(besWaringInfo);
		}else {//插入实时报警表bes_waring_real_data

			alarmUtil.besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
		}
	}
}
