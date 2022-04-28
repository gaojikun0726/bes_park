package com.efounder.JEnterprise.controller.basedatamanage.energydataRecord;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataRecord.BesMeterAmmeter;
import com.efounder.JEnterprise.service.basedatamanage.energydataRecord.BESMeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author  杨超
* @version 创建时间：2018年8月31日 上午10:04:28
* @parameter 静态电表信息
* @version 1.0
*/
@Controller
@RequestMapping(value = "/view/basedatamanage/energydataRecord/entrymanual")
public class BESMeterController {
	private static final Logger log = LoggerFactory.getLogger(BESMeterController.class);
	
	@Autowired
	private BESMeterService besmeterservice;
	
	/**
	 * 初始化主界面
	 * @return
	 */
	@RequestMapping(value = "/getMeterList", method = RequestMethod.GET)
	public String getMeterList() {
		return "/view/basedatamanage/energydataRecord/entrymanual/meterinformation";
	}
	
	/**
	 * 生成静态电表树
	 * @return
	 */
	
	@RequestMapping(value = "/meter_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject meter_tree() {
		log.info("# BESMeterController 生成静态电表树树" );
		ISSPReturnObject returnObject = besmeterservice.meter_tree();
		return returnObject;
	}
	
	/**
	 * 新增 静态电表
	 * @return
	 */
	@RequestMapping(value = "/add_MeterInformation", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_MeterInformation(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=besmeterservice.add_MeterInformation(dto);
		return returnObject;
	}
	/**
	 * 修改查询 回显
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/editSelect_MeterInformation", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editSelect_MeterInformation(String fGuid) {
		return besmeterservice.editSelect_MeterInformation(fGuid);
	}
	
	/**
	 * 修改保存
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/edit_MeterInformation", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_MeterInformation(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=besmeterservice.edit_MeterInformation(dto);
		return returnObject;
	}
	
	/**
	 * 删除静态电表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/del_MeterInformation", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_MeterInformation(String fGuid,String fJtguid,String lxqf) {
		ISSPReturnObject returnObject=besmeterservice.del_MeterInformation(fGuid,fJtguid,lxqf);
		return returnObject;
	}
	
	/**
	 * 新增 静态电表 数据
	 * @return
	 */
	@RequestMapping(value = "/add_MeterInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_MeterInfo(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=besmeterservice.add_MeterInfo(dto);
		return returnObject;
	}
	
	/**
	 *点击树 查询右侧table 
	 * @param treeId
	 * @return
	 */
	@RequestMapping(value = "/TreeTable" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject TreeTable(String treeId) {
		ISSPReturnObject returnObject = besmeterservice.TreeTable(treeId);
		return returnObject;
	}
	
	/**
	 * 静态电表数据修改回显
	 * 
	 */
	@RequestMapping(value = "/editSelect_MeterInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editSelect_MeterInfo(String fJtguid) {
		return besmeterservice.editSelect_MeterInfo(fJtguid);
	}
	
	/**
	 * 修改数据保存
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/edit_MeterInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_MeterInfo(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=besmeterservice.edit_MeterInfo(dto);
		return returnObject;
	}
	
	/**
	 * 查询下拉框列表
	 * @return
	 */
	@RequestMapping(value = "/selectMeterType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectMeterType() {
		ISSPReturnObject returnObject = besmeterservice.selectMeterType();
		return returnObject;
	}

	/**
	 * 导出数据
	 *
	 * @param treeId
	 * @return
	 */
	@RequestMapping(value = "/exportBesMeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject exportBesMeter(HttpServletRequest request, String treeId) {
		log.info("#BESMeterController导出数据");
		return besmeterservice.exportBesMeter(request,treeId);
	}


	/**
	 *
	 * @author: lizuhen
	 * @createTime: 2019年1月21日 16:13
	 * @Description:文件上传 后台接收方法
	 * @param request
	 * @param multipartFile
	 * @return
	 * @throws Exception ISSPReturnObject
	 */
	@RequestMapping(value = "/meterFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUpload(HttpServletRequest request,
							 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = besmeterservice.impExcel(request, multipartFile);
		return returnObject;
	}
}

