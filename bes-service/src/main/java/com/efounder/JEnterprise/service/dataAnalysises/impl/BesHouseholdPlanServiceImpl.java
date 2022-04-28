package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesHouseholdPlanDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdPlanData;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdPlanDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类绩效考核->能源计划分配
 * @author liuzhen
 *
 */
@Service("getHouseholdPlanData")
public class BesHouseholdPlanServiceImpl implements BesHouseholdPlanDataService{
	private static final Logger log = LoggerFactory.getLogger(BesHouseholdPlanServiceImpl.class);

	@Autowired
	private BesHouseholdPlanDataMapper besHouseholdPlanDataMapper;

	/**
	 *  获取以及插入能源计划
	 * @param besHouseholdplanData
	 * @return
	 */
	@Override
	public ISSPReturnObject getHouseholdPlanData(BesHouseholdPlanData besHouseholdplanData) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesHouseholdPlanData> besHouseholdPlanDatas = besHouseholdPlanDataMapper.getHouseholdPlanData(besHouseholdplanData);

			/**
			 * 给每个分户添加每年12个月的能源计划
			 */
			if(besHouseholdPlanDatas.size()==0){
					for (int i =1;i<13;i++)
					{
						String F_DATACENTER_ID= UUIDUtil.getRandom32BeginTimePK();
						BesHouseholdPlanData addBesHousehold =new BesHouseholdPlanData();
						addBesHousehold.setFGuid(F_DATACENTER_ID);
						addBesHousehold.setFFhbh(besHouseholdplanData.getFFhbh());
						addBesHousehold.setFPyear(besHouseholdplanData.getFPyear());
						addBesHousehold.setFPmonth(i+"");
						addBesHousehold.setFEfficientName(besHouseholdplanData.getFPyear()+"年"+i+"月耗能计划");
						besHouseholdPlanDataMapper.insert(addBesHousehold);
						if(i==12){
							besHouseholdPlanDatas = besHouseholdPlanDataMapper.getHouseholdPlanData(besHouseholdplanData);
						}
					}

			}
			for(BesHouseholdPlanData besHouseholdData:besHouseholdPlanDatas)
			{
				if(("0").equals(besHouseholdData.getFEnable()))
				{
					besHouseholdData.setFEnable("使能");
				}
				else
				{
					besHouseholdData.setFEnable("否");
				}
			}

			returnObject.setList(besHouseholdPlanDatas);
			returnObject.setMsg("能源计划分配获取数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("能源计划分配获取数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 *
	 * @param besHouseholdplanData
	 * @return
	 * 更新分户数据
	 */
	@Override
	public ISSPReturnObject updateHouseholdPlan(BesHouseholdPlanData besHouseholdplanData) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besHouseholdPlanDataMapper.updateHouseholdPlan(besHouseholdplanData);
			returnObject.setMsg("更新分户数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("更新分户数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}
	
	
	/**
	 *
	 * @param besHouseholdplanData
	 * @return
	 * 更新能源计划配置数据
	 */
	@Override
	public ISSPReturnObject updateHouseholdPlanData(BesHouseholdPlanData besHouseholdplanData) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besHouseholdPlanDataMapper.updateHouseholdPlanData(besHouseholdplanData);
			returnObject.setMsg("更新能源计划配置数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("更新能源计划配置数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}


	 
	

	

}
