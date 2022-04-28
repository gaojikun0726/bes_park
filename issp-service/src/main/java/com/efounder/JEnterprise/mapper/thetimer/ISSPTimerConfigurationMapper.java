package com.efounder.JEnterprise.mapper.thetimer;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISSPTimerConfigurationMapper extends BaseMapper<String, Object>{

	List<Object> getTimerConfigurationList(@Param(value = "keywords") String keywords,@Param(value = "F_TIMER_BH")String F_TIMER_BH);

	List<Object> getEquipmentadapter();

	List<Object> getIntefaceadapter(@Param(value = "F_ADAPTERGUID") String F_ADAPTERGUID);

	List<Object> getTimertype();

	boolean addTimerList(@Param(value = "F_TIMER_BH") String F_TIMER_BH,
			@Param(value = "F_TIMER_NAME") String F_TIMER_NAME, @Param(value = "F_TIMER_TYPEBH") String F_TIMER_TYPEBH,
			@Param(value = "F_LOOP_TIME") String F_LOOP_TIME, @Param(value = "F_FIXED_TIME") String F_FIXED_TIME,
			@Param(value = "F_VAR_TIME") String F_VAR_TIME);

	boolean addTimerManage(@Param(value="F_TIMER_BH") String F_TIMER_BH,
			@Param(value="F_TIMER_NAME") String F_TIMER_NAME, @Param(value="F_TIMER_STATE") String F_TIMER_STATE);

	boolean addEqJoinTask(@Param(value = "F_TASK_BH") String F_TASK_BH,
			@Param(value = "F_TIMER_BH") String F_TIMER_BH, @Param(value = "F_TIMER_NAME") String F_TIMER_NAME,
			@Param(value = "F_EQADAPTER_GUID") String F_EQADAPTER_GUID, @Param(value = "F_INTERFACE_GUID") String F_INTERFACE_GUID);

	boolean delTimerList(String f_TIMER_BH);

	boolean delTimerManage(String f_TIMER_BH);

	boolean delEqJoinTask(String f_TIMER_BH);

	boolean updateTimerList(@Param(value = "f_TIMER_BH") String f_TIMER_BH,
			@Param(value = "f_TIMER_NAME") String f_TIMER_NAME, @Param(value = "f_TIMER_TYPEBH") String f_TIMER_TYPEBH,
			@Param(value = "f_LOOP_TIME") String f_LOOP_TIME, @Param(value = "f_FIXED_TIME") String f_FIXED_TIME,
			@Param(value = "f_VAR_TIME") String f_VAR_TIME);

	boolean updateTimerManage(@Param(value="f_TIMER_BH")String f_TIMER_BH,@Param(value="f_TIMER_NAME") String f_TIMER_NAME);

	boolean updateEqJoinTask(@Param(value = "f_TIMER_BH") String f_TIMER_BH,
			@Param(value = "f_TIMER_NAME") String f_TIMER_NAME,
			@Param(value = "f_EQADAPTER_GUID") String f_EQADAPTER_GUID,
			@Param(value = "f_INTERFACE_GUID") String f_INTERFACE_GUID);

}
