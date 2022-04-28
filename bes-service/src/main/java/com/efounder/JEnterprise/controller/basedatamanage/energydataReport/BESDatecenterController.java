package com.efounder.JEnterprise.controller.basedatamanage.energydataReport;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.service.basedatamanage.energydataReport.BESDatecenterService;
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
 * 数据中心
 * @author 杨超
 * @modify ycc
 */
@Controller
@RequestMapping(value = "/view/energydataReport/basedatecentermanager")
public class BESDatecenterController {
	private static final Logger log = LoggerFactory.getLogger(BESDatecenterController.class);
	@Autowired
	private BESDatecenterService besdatecenterservice;
	
	
	/**
	 * 数据中心初始化页面
	 * @return
	 */
	@RequestMapping(value = "/getDatecenter" ,method = RequestMethod.GET)
	public String getDatecenter (ModelMap model) {
		log.info("# BESDatecenterController #初始化‘数据中心’界面");
		//查询类型list
		
		return "/view/basedatamanage/energydataReport/basedatecentermanager/datecenterType";
	}
	/**
	 * 搜索
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getDatecenterList" ,method = RequestMethod.POST)
	public String getDatecenterList (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BESDatecenterController #分页查询，关键字查询");
		PageInfo<BESDatecenterType> page = besdatecenterservice.getDatecenterList(keywords,pageNum);
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "/view/basedatamanage/energydataReport/basedatecentermanager/datecenterType_page";		
	}
	
	/**
	 * 添加
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/add_Datecenter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_Datecenter(BESDatecenterType besdatecentertype) {
		log.info("#添加");
		return besdatecenterservice.add_Datecenter(besdatecentertype);
	}
	
	/**
	 * 删除
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/del_Datecenter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_Datecenter(String id) {
		log.info("#删除");
		return besdatecenterservice.del_Datecenter(id);
	}
	
	/**
	 * 单行查询
	 * 
	 * @param fLxbh
	 * @return
	 */
	@RequestMapping(value = "/getDatecenter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getDatecenter(String bh) {
		log.info("查询");
		return besdatecenterservice.getDatecenter(bh);
	}
	
	/**
	 * 更新
	 * 
	 * @param besAmmeterType
	 * @return
	 */
	@RequestMapping(value = "/edit_Datecenter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_Datecenter(BESDatecenterType besdatecentertype) {
		log.info("更新");
		return besdatecenterservice.edit_Datecenter(besdatecentertype);
	}
	
	/**
	 * 查询下拉框列表
	 * @return
	 */
	@RequestMapping(value = "/selectfNybhList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectfNybhList() {
		log.info("# BESDatecenterController 获取下拉框列表");
		ISSPReturnObject returnObject = besdatecenterservice.selectfNybhList();	
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
		ISSPReturnObject returnObject = besdatecenterservice.exportExcelModel(request);	
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
        ISSPReturnObject returnObject = besdatecenterservice.impExcel(request, multipartFile);
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
        ISSPReturnObject returnObject = besdatecenterservice.exportExcel(request, dto);
        return returnObject;
    }
}
