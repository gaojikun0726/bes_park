package com.efounder.JEnterprise.controller.isspplugins;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.isspplugins.ISSPSpinnerBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 类名称：ISSPSpinnerBoxController
 * 类描述：下拉列表组件加载数据
 * 创建人：huangxianbo
 * 创建时间：2018年8月10日
 * @version 1.0.0 
 */
@Controller
@RequestMapping(value = "/view/isspplugins")
public class ISSPSpinnerBoxController {
	@Autowired
    private ISSPSpinnerBoxService isspSpinnerBoxService;
	
	/**
	 * 根据所传参数获取数据
	 * @return
	 */
	@RequestMapping(value = "/spinnerboxData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getData(String currId, String dataId, String dataTxt, String dataTabName, String conColumn, String conValue) {
		ISSPReturnObject returnObject = isspSpinnerBoxService.getData(currId, dataId, dataTxt, dataTabName, conColumn, conValue);

		return returnObject;
	}
}
