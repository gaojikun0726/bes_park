package com.efounder.JEnterprise.controller.isspplugins;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.isspplugins.ISSPComboboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 类名称：ISSPComboboxController
 * 类描述：帮助组件加载数据
 * 创建人：huangxianbo
 * 创建时间：2018年7月10日
 * @version 1.0.0 
 */
@Controller
@RequestMapping(value = "/view/isspplugins")
public class ISSPComboboxController {
	@Autowired
    private ISSPComboboxService besCommonService;
	
	/**
	 * 根据所传参数获取数据
	 * @return
	 */
	@RequestMapping(value = "/helpcomboxData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getData(String tabName, String disCol, String disColId, String conColumn, String conValue) {
		ISSPReturnObject returnObject = besCommonService.getData(tabName, disCol, disColId, conColumn, conValue);

		return returnObject;
	}
}
