package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类名称：GljlController
 *  类描述：管理记录页面
 *   修改人：wzx 2020-8-26 10:35:38
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/gljl")
public class GljlController {
	private static final Logger log = LoggerFactory.getLogger(GljlController.class);

    @Autowired
    private GljlService gljlService;

//   @GetMapping("/showInitPage")
//    public String showInitPage() {
//        log.info("#ManagementRecordController 进入管理记录页面");
//        return "view/strongAndWeakElectricityIntegration/managementRecord";
//    }

    @RequestMapping(value = "showInitPage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#GljlController 进入管理记录页面");
        return "view/strongAndWeakElectricityIntegration/gljl";
    }
    /**
     * 根据搜索条件分页显示信息
     * @return
     */
    @RequestMapping(value = "/loadGljlxx", method = RequestMethod.POST)
    public String loadGljlxx(ModelMap map, Integer pageNum, String keywords, Integer bars) {
        log.info("#GljlController管理登录日志分页加载");

        //进行分页查询
        try {
            PageInfo<GljlModel> page = gljlService.loadGljlxx(bars,pageNum,keywords);
            map.put("page", page);
            map.put("pageSize", page.getPageSize()+"");
            map.put("dataset", JsonMapper.toJsonString(page.getList()));
            String pageN =null;
            if(String.valueOf(page.getPageNum()).indexOf(",")!=-1)
                pageN = String.valueOf(page.getPageNum()).replaceAll(",","");
            else
                pageN = String.valueOf(page.getPageNum());
            map.put("pageNum", pageN);
            map.put("keywords", keywords);
        } catch (Exception e) {
        }
        return "view/strongAndWeakElectricityIntegration/gljl_page";
    }



}
