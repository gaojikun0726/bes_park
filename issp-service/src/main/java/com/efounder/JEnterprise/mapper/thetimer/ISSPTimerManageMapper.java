package com.efounder.JEnterprise.mapper.thetimer;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerClass;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerList;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerManage;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ISSPTimerManageMapper extends BaseMapper<String, ISSPTimerManage>{

	public List<ISSPTimerManage> getTimerManageList(@Param(value = "keywords") String keywords);

	public ISSPTimerList getTimerList(@Param(value = "fTimerBh") String fTimerBh);

	public ISSPTimerClass getTimerClass(@Param(value = "fTimerClassbh")String fTimerClassbh);

	public ISSPTimerType getTimerType(@Param(value = "fTimerTypebh")String fTimerTypebh);

	public ISSPTimerManage getTimerManage(ISSPTimerManage isspTimerManage);

	public boolean upTimerManage(ISSPTimerManage isspTimerManage);

}
