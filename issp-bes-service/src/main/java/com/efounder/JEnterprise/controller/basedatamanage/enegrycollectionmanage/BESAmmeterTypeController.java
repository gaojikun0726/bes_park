package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAmmeterType;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESAmmeterTypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 电表类型定义
 * @author LvSihan
 * @modify suhang
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
public class BESAmmeterTypeController {
	private static final Logger log = LoggerFactory.getLogger(BESAmmeterTypeController.class);
	@Autowired
	private BESAmmeterTypeService besAmmeterTypeService;
	
	/**
	 * 初始化电表类型定义界面
	 * @return
	 */
	@RequestMapping(value = "/getAmmeterType" ,method = RequestMethod.GET)
	public String getAmmeterType () {
		log.info("# BESAmmeterTypeController #初始化‘电表类型定义’界面");
		return "view/basedatamanage/enegrycollectionmanage/ammeterType";
	}
	/**
	 * 搜索
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getAmmeterTypeList" ,method = RequestMethod.POST)
	public String getAmmeterTypeList (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BESAmmeterTypeController #分页查询，关键字查询");
		PageInfo<BESAmmeterType> page = besAmmeterTypeService.getAmmeterTypeList(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "view/basedatamanage/enegrycollectionmanage/ammeterType_page";		
	}
	
	/**
	 * 添加
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/add_AmmeterType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_AmmeterType(BESAmmeterType besAmmeterType) {
		log.info("#添加");
		String date = DateUtil.getCurrTime();
		String fCrdate = date;
		String fChdate = date;
		besAmmeterType.setfCrdate(fCrdate);
		besAmmeterType.setfChdate(fChdate);
		return besAmmeterTypeService.add_AmmeterType(besAmmeterType);
	}
	
	/**
	 * 删除
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/del_AmmeterType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_AmmeterType(String fLxbh) {
		log.info("#删除");
		return besAmmeterTypeService.del_AmmeterType(fLxbh);
	}
	
	/**
	 * 单行查询
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/getAmmeterType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAmmeterType(String fLxbh) {
		log.info("查询");
		return besAmmeterTypeService.getAmmeterType(fLxbh);
	}
	
	/**
	 * 更新
	 * 
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/edit_AmmeterType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_AmmeterType(BESAmmeterType besAmmeterType) {
		log.info("更新");
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besAmmeterType.setfChdate(fChdate);
		return besAmmeterTypeService.edit_AmmeterType(besAmmeterType);
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
	@RequestMapping(value = "/besAmmeterTypeFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUpload(HttpServletRequest request,
							 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = besAmmeterTypeService.impExcel(request, multipartFile);
		return returnObject;
	}


}
