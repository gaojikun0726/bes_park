package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESProducerManage;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESProducerManageService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生产商管理
 * 
 * @author yujieying
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESProducerManageController {
	private static final Logger log = LoggerFactory.getLogger(BESProducerManageController.class);

	@Autowired
	private BESProducerManageService besproducermanageservice;

	/**
	 * @description 跳转生产商管理页面
	 * @return
	 */
	@RequestMapping(value = "/producermanagepage", method = RequestMethod.GET)
	public String getRecord() {
		log.info("#BESProducermanageController跳转生产商管理信息");

		return "view/basedatamanage/eqmanage/besProducterlistManage";
	}

	/**
	 * @description 获取生产商列表
	 * @author yujieying
	 */
	@RequestMapping(value = "/loadProducerManagelist", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getBESProducerManageList() {
		log.info("#BESProducerManageController 获取生产商列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESProducerManage> list = besproducermanageservice.getBESProducerManageList();
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * 分页查询
	 * 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/producerManage_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
   @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map,Integer bars) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESProducerManage> page = besproducermanageservice.findProducerByPage(bars,pageNum, keywords);
		map.put("page", page);
		//将数据转化为json字符串 add by wujf at 20180623
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		 map.put("pageSize", page.getPageSize()+"");
		return "view/basedatamanage/eqmanage/besProducterlistManage_page";//_page.ftl文件的路径
	}
	/**
	 * @description 搜索生产商列表
	 * @author 于洁英
	 */
	@RequestMapping(value = "/findProducerManagelist", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findBESProducerManageList(String keywords) {
		log.info("#BESProducerManageController 搜索生产商列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESProducerManage> list = besproducermanageservice.findProducterlist(keywords);
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * @Description 添加生产商信息
	 * @author yujieying
	 * @return
	 */
	@RequestMapping(value = "/Producer_insertproducer", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insertProducer(@RequestBody BESProducerManage besproducermanage) {
		log.info("#BESProducerManageController添加生产商信息");
		//String uuid = UUIDUtil.getRandom32BeginTimePK();
		String date = DateUtil.getCurrTime();
		//String fCrdate = date;
		//String fChdate = date;
		besproducermanage.setfId(UUIDUtil.getRandom32BeginTimePK());
		besproducermanage.setfCrdate(date);
		besproducermanage.setfChdate(date);
		
		return besproducermanageservice.addProducer(besproducermanage);
	}
	
	
	/**
	 * @Description 删除生产商信息
	 * @author yujieying
	 * @return
	 */
	@RequestMapping(value = "/Producer_delproducer", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delProducer(@RequestBody BESProducerManage besproducermanage) {
		log.info("#BESProducerManageController删除生产商信息");
		return besproducermanageservice.delProducer(besproducermanage);
	}
	/**
	 * @Description 编辑前获取生产商信息
	 * @author yujieying
	 * @return
	 */
	@RequestMapping(value = "/Producer_getObj", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getProducerObj(String fId) {
		log.info("#BESProducerManageController获取生产商对象");
		BESProducerManage promanageObj = besproducermanageservice.getBESProducerManageObj(fId);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject.setData(promanageObj);
		return returnObject;
	}
	
	/**
	 * @Description 编辑生产商信息
	 * @author yujieying
	 * @return
	 */
	@RequestMapping(value = "/updateproducer", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upProducer(@RequestBody BESProducerManage besproducermanage) {
		log.info("#BESProducerManageController编辑生产商信息");
		String date = DateUtil.getCurrTime();
		besproducermanage.setfChdate(date);
		return besproducermanageservice.updateProducer(besproducermanage);
	}
	
}
