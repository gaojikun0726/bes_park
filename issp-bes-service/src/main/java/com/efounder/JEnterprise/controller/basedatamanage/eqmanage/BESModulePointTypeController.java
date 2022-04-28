package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModulePointType;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESModulePointTypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/view/basedatamanage/eqmanage")
public class BESModulePointTypeController {
	
	private static final Logger log = LoggerFactory.getLogger(BESModulePointTypeController.class);
	@Autowired
	private BESModulePointTypeService besModulePointTypeService;
	
	/**
	 * 初始化模块点类型列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allbesModulePointType", method = RequestMethod.GET)
	public String allintefaceAdapter() {
		log.info("#初始化模块点类型");
		return "besview/basedatamanage/eqmanage/modulepointType";
	}
	
	/**
	 * 获取模块点类型分页信息
	 * 
	 * @return
	 */

	@RequestMapping(value = "/modulepointType_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "bars", required = false) Integer bars,ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESModulePointType> page = besModulePointTypeService.selModulepointTypepage(pageNum,bars, keywords);
		map.put("pageSize", page.getPageSize());
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "besview/basedatamanage/eqmanage/modulepointType_page";
	}
	
	/**
	 * 添加模块点类型列表信息
	 * 
	 * @param isspEquipmentadapter
	 * @return
	 */
	@RequestMapping(value = "/addbesModulePointType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addSPI(@RequestBody BESModulePointType besModulePointType) {
		log.info("#添加模块点类型信息");
		String uuid = UUIDUtil.getRandom32BeginTimePK();
		String date = DateUtil.getCurrTime();
		String fCrdate = date;
		String fChdate = date;
		besModulePointType.setfId(uuid);
		besModulePointType.setfCrdate(fCrdate);
		besModulePointType.setfChdate(fChdate);
		return besModulePointTypeService.insModulepointType(besModulePointType);
	}
	
	/**
	 * 删除模块点类型列表信息
	 * 
	 * @param fId
	 * @return
	 */
	@RequestMapping(value = "/deletebesModulePointType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delFID(String fId) {
		log.info("#删除模块点类型信息");
		return besModulePointTypeService.delModulepointType(fId);
	}
	
	/**
	 * 模块点类型单行查询
	 * 
	 * @param fGuid
	 * @return
	 */
	@RequestMapping(value = "/querybesModulePointType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryBES(String fId) {
		log.info("查询单行模块点类型信息");
		return besModulePointTypeService.selectModulepointType(fId);
	}

	/**
	 * 模块点类型信息
	 * 
	 * @param isspEquipmentadapter
	 * @return
	 */
	@RequestMapping(value = "/updatebesModulePointType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updISSPEquipmentadapter(BESModulePointType besModulePointType) {
		log.info("更新模块点类型信息");
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besModulePointType.setfChdate(fChdate);
		return besModulePointTypeService.updfModulepointType(besModulePointType);
	}

	/**
	* @Author:         YangChao
	* @CreateDate:     2019/1/23 16:57
	* @Description:    文件上传后台接收方法
	*/
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = besModulePointTypeService.impExcel(request, multipartFile);
        return returnObject;
    }
	
}
