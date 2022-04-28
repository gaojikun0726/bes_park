package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESProtocol;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESProtocolService;
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
 * 通信协议定义
 * @author LvSihan
 * @modify suhang
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
public class BESProtocolController {
	private static final Logger log = LoggerFactory.getLogger(BESProtocolController.class);

	@Autowired
	private BESProtocolService besProtocolService;
	/**
	 * 初始化主界面
	 * @return
	 */
	@RequestMapping(value = "/getProtocol", method = RequestMethod.GET)
	public String getProtocol() {
		log.info("# BESProtocolController #初始化‘通信协议’界面");
		return "view/basedatamanage/enegrycollectionmanage/protocol";
	}
	/**
	 * 分页查询，关键字查询
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getProtocolList", method = RequestMethod.POST)
	public String getProtocolList(@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BESProtocolController #分页查询‘通信协议’");
		PageInfo<BESProtocol> page = besProtocolService.getProtocolList(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "view/basedatamanage/enegrycollectionmanage/protocol_page";
		
	}
	
	/**
	 * 添加
	 * @param besProtocol
	 * @return
	 */
	@RequestMapping(value = "/insProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insProtocol(@RequestBody BESProtocol besProtocol) {
		log.info("#添加");
		String date = DateUtil.getCurrTime();
		String fCrdate = date;
		String fChdate = date;
		besProtocol.setfCrdate(fCrdate);
		besProtocol.setfChdate(fChdate);
		return besProtocolService.insProtocol(besProtocol);
	}
	
	/**
	 * 删除
	 * 
	 * @param fTxxybh
	 * @return
	 */
	@RequestMapping(value = "/delProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delProtocol(String fTxxybh) {
		log.info("#删除");
		return besProtocolService.delProtocol(fTxxybh);
	}
	
	/**
	 * 单行查询
	 * 
	 * @param fTxxybh
	 * @return
	 */
	@RequestMapping(value = "/getProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getProtocol(String fTxxybh) {
		log.info("查询");
		return besProtocolService.getProtocol(fTxxybh);
	}
	
	/**
	 * 更新
	 * 
	 * @param besProtocol
	 * @return
	 */
	@RequestMapping(value = "/updProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updProtocol(BESProtocol besProtocol) {
		log.info("更新");
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besProtocol.setfChdate(fChdate);
		return besProtocolService.updProtocol(besProtocol);
	}

	/**
	 * 导出数据
	 *
	 * @param keywords 关键字
	 * @return
	 */
	@RequestMapping(value = "/exportProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject exportProtocol(HttpServletRequest request,String keywords) {
		log.info("#BESProtocolController导出数据");
		return besProtocolService.exportProtocol(request,keywords);
	}

	/**
	 * 导出excel错误数据
	 *
	 * @param errorString 关键字
	 * @return
	 */
	@RequestMapping(value = "/exportErrorExcel", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject exportErrorExcel(HttpServletRequest request,String errorString) {
		log.info("#BESProtocolController导出数据");
		return besProtocolService.exportErrorExcel(request,errorString);
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
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUpload(HttpServletRequest request,
							 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = besProtocolService.impExcel(request, multipartFile);
		return returnObject;
	}


}
