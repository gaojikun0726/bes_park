package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BesRate;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BesRateService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 通信波特率Controller
 * @author suhang
 * @date 2018-7-27
 */
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
@Controller
public class BesRateController {
	
	private static final Logger log = LoggerFactory.getLogger(BesRateController.class);
	
	@Autowired
	private BesRateService besRateService;
	
	/**
	 * 初始化主界面
	 * @return
	 */
	@RequestMapping(value = "/getBesRate", method = RequestMethod.GET)
	public String getBesRate() {
		log.info("# BesRateController #初始化通信波特率界面");
		return "view/basedatamanage/enegrycollectionmanage/besrate";
	}
	
	/**
	 * 分页信息
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/besRatePage" , method = RequestMethod.POST)
	public String besRatePage (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BesRateController #分页查询");
		PageInfo<BesRate> page = besRateService.besRatePage(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "view/basedatamanage/enegrycollectionmanage/besrate_page";
	}
	
	/**
	 * 添加通信波特率信息
	 * @param besRate
	 * @return
	 */
	@RequestMapping(value = "/insBesRate" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insBesRate(@RequestBody BesRate besRate) {
		log.info("BesRateController #添加");
		String date = DateUtil.getCurrTime();
		String fCrdate = date;
		String fChdate = date;
		besRate.setfCrdate(fCrdate);
		besRate.setfChdate(fChdate);
		return besRateService.insBesRate(besRate);
	}
	
	/**
	 * 删除通信波特率信息
	 * 
	 * @param fRateBh
	 * @return
	 */
	@RequestMapping(value = "/delBesRate", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delBesRate(String fRateBh) {
		log.info("BesRateController #删除");
		return besRateService.delBesRate(fRateBh);
	}
	
	/**
	 * 单行查询
	 * 
	 * @param fRateBh
	 * @return
	 */
	@RequestMapping(value = "/selBesRate", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selBesRate(String fRateBh) {
		log.info("BesRateController #查询");
		return besRateService.selBesRate(fRateBh);
	}
	
	/**
	 * 更新
	 * 
	 * @param besRate
	 * @return
	 */
	@RequestMapping(value = "/updBesRate", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updBesRate(BesRate besRate) {
		log.info("BesRateController #更新");
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besRate.setfChdate(fChdate);
		return besRateService.updBesRate(besRate);
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
	@RequestMapping(value = "/besRateFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUpload(HttpServletRequest request,
							 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = besRateService.impExcel(request, multipartFile);
		return returnObject;
	}


	/**
	 * fDnbh 通信波特率查重
	 * @param fRateBh
	 * @return
	 */
	@RequestMapping(value = "/checkRateRepeat" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject checkRepeat(String fRateBh,String fCommRate) {
		log.info("# BESElectricParamsController 通过能耗编号获取采集参数" );
		ISSPReturnObject returnObject = besRateService.checkRepeat(fRateBh,fCommRate);
		return returnObject;
	}

	/**
	 * fDnbh 编辑通信波特率查重
	 * @param fCommRate
	 * @return
	 */
	@RequestMapping(value = "/checkEditRepeat" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject checkEditRepeat(String fCommRate) {
		log.info("# BESElectricParamsController 通过能耗编号获取采集参数" );
		ISSPReturnObject returnObject = besRateService.checkEditRepeat(fCommRate);
		return returnObject;
	}



}
