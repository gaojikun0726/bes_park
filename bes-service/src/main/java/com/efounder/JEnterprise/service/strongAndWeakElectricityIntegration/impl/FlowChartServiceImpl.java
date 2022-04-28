package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.FlowChartMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.BESFlowType;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.FlowChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("FlowChartService")
public class FlowChartServiceImpl implements FlowChartService {
	private static final Logger log = LoggerFactory.getLogger(FlowChartServiceImpl.class);
	@Autowired
	private FlowChartMapper flowChartMapper;


	@Override
	public ISSPReturnObject addFlowType(BESFlowType besFlowType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#FlowChartMapper新增流程图信息");
		String pageId  = UUID.randomUUID().toString();
		String fId =  pageId.substring(4)+"_lct";
		besFlowType.setF_id(fId);
		try{
			boolean flag = flowChartMapper.addFlowType(besFlowType);
			if (flag) {
				returnObject.setData(besFlowType);
				returnObject.setStatus("1");
				returnObject.setMsg("添加流程图信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加流程图信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getFlowTypeInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#FlowChartMapper查询流程图信息");
		try{
			List<BESFlowType> list  = flowChartMapper.getFlowTypeInfo();
			if (list.size()!=0) {
				returnObject.setList(list);
				returnObject.setStatus("1");
				returnObject.setMsg("查询流程图信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("查询流程图信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 修改流程图信息
	 * @param besFlowType
	 * @return
	 */
	@Override
	public ISSPReturnObject editFlowType(BESFlowType besFlowType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#FlowChartMapper修改流程图信息");
		try{
			boolean flag = flowChartMapper.editFlowType(besFlowType);
			if (flag) {
				List<BESFlowType> list  = flowChartMapper.getFlowTypeInfo();
				if (list.size()!=0) {
					returnObject.setList(list);
					returnObject.setStatus("1");
					returnObject.setMsg("修改流程图信息成功");
				} else {
					returnObject.setStatus("0");
					returnObject.setMsg("修改流程图信息失败");
				}

				/*returnObject.setData(besFlowType);
				returnObject.setStatus("1");
				returnObject.setMsg("修改流程图信息成功");*/
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("修改流程图信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 删除流程图
	 * @param besFlowType
	 * @return
	 */
	@Override
	public ISSPReturnObject deleteFlowType(BESFlowType besFlowType) {
		// TODO Auto-generated method stub
		log.info("#FlowChartMapper删除流程图信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag1 = flowChartMapper.deleteFlowType(besFlowType);
			boolean flag2 = flowChartMapper.deleteFlowChart(besFlowType);
			if (flag1&&flag2) {
				List<BESFlowType> list  = flowChartMapper.getFlowTypeInfo();
				returnObject.setStatus("1");
				returnObject.setList(list);
				returnObject.setMsg("删除流程图信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除流程图信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

}


