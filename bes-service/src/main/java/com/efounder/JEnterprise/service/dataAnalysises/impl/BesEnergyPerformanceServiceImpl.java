package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesEnergyPerformanceMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyPerformance;
import com.efounder.JEnterprise.service.dataAnalysises.BesEnergyPerformanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 能源绩效考核
 * @author liuzhen
 *
 */
@Service("getEnergyPerformanceData")
public class BesEnergyPerformanceServiceImpl implements BesEnergyPerformanceService{
	private static final Logger log = LoggerFactory.getLogger(BesEnergyPerformanceServiceImpl.class);

	@Autowired
	private BesEnergyPerformanceMapper besEnergyPerformanceMapper;
	


	
	@Override
	public ISSPReturnObject getEnergyPerformance(BesEnergyPerformance besEnergyPerformance) {
		// TODO Auto-generated method stub
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesEnergyPerformance> besEnergyPerformances = besEnergyPerformanceMapper.searchEnergyPerformance(besEnergyPerformance);
			besEnergyPerformance.setFCjsj_start_year(Integer.parseInt(besEnergyPerformance.getFCjsj_start().split("-")[0]));
			besEnergyPerformance.setFCjsj_start_month(Integer.parseInt(besEnergyPerformance.getFCjsj_start().split("-")[1]));
			besEnergyPerformance.setFCjsj_end_year(Integer.parseInt(besEnergyPerformance.getFCjsj_end().split("-")[0]));
			besEnergyPerformance.setFCjsj_end_month(Integer.parseInt(besEnergyPerformance.getFCjsj_end().split("-")[1]));
			List<BesEnergyPerformance> besEnergyPlanPerformances = besEnergyPerformanceMapper.searchEnergyPlanPerformance(besEnergyPerformance);
			for(BesEnergyPerformance besEnergyPerformance1:besEnergyPerformances)
			{
				for(BesEnergyPerformance besEnergyPerformance2:besEnergyPlanPerformances )
				{
				 	if(besEnergyPerformance1.getFPlanFhbh().equals(besEnergyPerformance2.getFPlanFhbh()))
				 	{
						besEnergyPerformance1.setFAllEnegry(besEnergyPerformance1.getFAllEnegry().replace(",",""));
						besEnergyPerformance1.setFPlanAllEnegry(besEnergyPerformance2.getFPlanAllEnegry());
						besEnergyPerformance1.setFPlanAllMoney(besEnergyPerformance2.getFPlanAllMoney());
						besEnergyPerformance1.setFPlanCo2(besEnergyPerformance2.getFPlanCo2());
						besEnergyPerformance1.setFPlanCoalAmount(besEnergyPerformance2.getFPlanCoalAmount());
						besEnergyPerformance1.setFPlanPermanData(besEnergyPerformance2.getFPlanPermanData());
						besEnergyPerformance1.setFPlanPermanMoney(besEnergyPerformance2.getFPlanPermanMoney());
						besEnergyPerformance1.setFPlanUnitareaData(besEnergyPerformance2.getFPlanUnitareaData());
						besEnergyPerformance1.setFPlanUnitareaMoney(besEnergyPerformance2.getFPlanUnitareaMoney());

					}
				}
			}
			for(BesEnergyPerformance besEnergyPerformanceData:besEnergyPerformances)
			{
				if(besEnergyPerformanceData.getFAllEnegry() !=null && !besEnergyPerformanceData.getFAllEnegry() .equals("") &&
						besEnergyPerformanceData.getFPlanAllEnegry() !=null && !besEnergyPerformanceData.getFPlanAllEnegry() .equals(""))
				{
					if("0" .equals(besEnergyPerformanceData.getFPlanAllEnegry()) ||"0.00" .equals(besEnergyPerformanceData.getFPlanAllEnegry()) )
					{
						besEnergyPerformanceData.setEnergyPercentage("0%");
					}
					else
					{
						BigDecimal bignum1 = new BigDecimal(besEnergyPerformanceData.getFAllEnegry().trim().replace(",",""));
						BigDecimal bignum2 = new BigDecimal(besEnergyPerformanceData.getFPlanAllEnegry().trim().replace(",",""));
						BigDecimal bignum3 = null; 
						BigDecimal bignum4 = null;
						BigDecimal bignum5 = new BigDecimal("100");
						BigDecimal bignum6 = null;

						bignum3 = bignum2.subtract(bignum1); 
						bignum4 = bignum3.divide(bignum2,2,BigDecimal.ROUND_DOWN); 
						bignum6 = bignum4.multiply(bignum5);
						besEnergyPerformanceData.setEnergyPercentage(bignum6.setScale(2).doubleValue()+"%");
					}
					
				}
				
			}
			returnObject.setList(besEnergyPerformances);
			returnObject.setMsg("获取能源绩效考核数据成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取能源绩效考核数据失败");
			returnObject.setStatus("0");
			e.printStackTrace();
		}
		return returnObject;
	}


	 
	

	

}
