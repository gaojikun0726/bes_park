package com.efounder.JEnterprise.controller.basedatamanage.energydataReport;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.service.basedatamanage.energydataReport.BESBudingService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
* @author  杨超
* @version 创建时间：2018年8月29日 下午3:45:30
* @parameter 
* @version 1.0
*/
@Controller
@RequestMapping(value = "/view/energydataReport/basebudingmanager")
public class BESBudingController {
	private static final Logger log = LoggerFactory.getLogger(BESBudingController.class);
	
	@Autowired
	private BESBudingService  besbudingservice;
	
	/**
	 * 初始化 建筑基本信息 界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getBuding" ,method = RequestMethod.GET)
	public String getBuding (ModelMap mpdel) {
		log.info("# BESBudingController #初始化‘建筑基本信息’界面");
		Subject currentUser = SecurityUtils.getSubject();
		Object loginUser = currentUser.getPrincipal();
		String user=loginUser.toString();
		//当前登录人
		mpdel.addAttribute("username",user);
		return "/view/basedatamanage/energydataReport/basebudingmanager/budingType";
	}
	
	/**
	 * 搜索
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getBudingList" ,method = RequestMethod.POST)
	public String getDatecenterList (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BESBudingController #分页查询，关键字查询");
		PageInfo<BesBudingInformation> page = besbudingservice.getBudingList(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "view/basedatamanage/energydataReport/basebudingmanager/budingType_page";		
	}
	
	/**
	 * 添加
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/add_BudingType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_BudingType(BesBudingInformation besbudinginformation) {
		log.info("#添加");
		return besbudingservice.add_BudingType(besbudinginformation);
	}
	
	/**
	 * 删除
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/del_BudingType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_BudingType(String id) {
		log.info("#删除");
		return besbudingservice.del_BudingType(id);
	}
	
	/**
	 * 单行查询
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/getBudingType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getBudingType(String bh) {
		log.info("查询");
		return besbudingservice.getBudingType(bh);
	}
	
	/**
	 * 更新
	 * 
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/edit_BudingType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_BudingType(BesBudingInformation besbudinginformation) {
		log.info("更新");
		return besbudingservice.edit_BudingType(besbudinginformation);
	}
	

	/**
	 * 查询下拉框列表
	 * @return
	 */
	@RequestMapping(value = "/selectSjzxList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectSjzxList() {
		ISSPReturnObject returnObject = besbudingservice.selectsjzxList();
		return returnObject;
	}

	/**
	 * 查询所有建筑物信息
	 * @return
	 */
	@RequestMapping(value = "/queryBuildingList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryBuildingList() {
		ISSPReturnObject returnObject = besbudingservice.queryBuildingList();
		return returnObject;
	}

	//首页 报警信息弹窗 table
	@RequestMapping(value = "/getRightTable" ,method = RequestMethod.POST)
	public String getRightTable (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		PageInfo<BesBudingInformation> page = besbudingservice.getBudingList(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "/view/main-index-page";		
	}
	
	//报警信息弹窗count查询
	@RequestMapping(value = "/getBjxxCount", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getBjxxCount() {
		log.info("# BESBudingController 刷新报警信息条数");
		ISSPReturnObject returnObject = besbudingservice.getBjxxCount();	
		return returnObject;
	}
	/**
	 * 生成模板
	 * @return
	 */
	@RequestMapping(value = "/exportExcelModel", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject exportExcelModel(HttpServletRequest request) {
		log.info("# BESDatecenterController 生成模板");
		ISSPReturnObject returnObject = besbudingservice.exportExcelModel(request);	
		return returnObject;
	}
	/**
	 * 导入
	 * @param request
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
            @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = besbudingservice.impExcel(request, multipartFile);
        return returnObject;
    }
    /**
     * 导出
     * @param request
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto) throws Exception {
        ISSPReturnObject returnObject = besbudingservice.exportExcel(request, dto);
        return returnObject;
    }
	
}
