package com.efounder.JEnterprise.controller.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;
import com.efounder.JEnterprise.service.dataAnalysises.BESZlqsfxService;
import com.efounder.JEnterprise.service.dataAnalysises.BesZlthbfxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨超
 * @version 创建时间：2018年10月16日 上午8:47:27
 * @parameter 支路用能-趋势统计分析
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/view/dataAnalysis")
public class BESZlqsfxController {
	private static final Logger log = LoggerFactory.getLogger(BESZlqsfxController.class);

	@Autowired
	/**
	 * 趋势统计分析
	 */
	private BESZlqsfxService beszlqsfxservice;

	@Autowired
	/**
	 * 同比环比分析
	 */
	private BesZlthbfxService beszlthbfxservice;

	/**
	 * 支路用能-趋势统计分析首页
	 * 
	 * @param
	 * @return
	 */
    @RequestMapping(value = "/zl_qstjfx_dw", method = RequestMethod.GET)
	public String getZl_qstjfx() {
        log.info("BESZlqsfxController支路用能---趋势统计分析(单位)");
        return "view/dataAnalysis/zlyn/zl_qstjfx_dw";
	}

    /**
     * 支路用能-趋势统计分析首页
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_qstjfx", method = RequestMethod.GET)
    public String getZl_qstjfx_dw() {
        log.info("BESZlqsfxController支路用能---趋势统计分析(时间)");
        return "view/dataAnalysis/zlyn/zl_qstjfx";
    }

	/**
	 * 支路用能-同比环比分析首页
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/zl_tbhbfx", method = RequestMethod.GET)
	public String getZl_thbfx() {
		log.info("BESZlqsfxController支路用能---同环比分析");
		return "view/dataAnalysis/zlyn/zl_tbhbtjfx";
	}

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月27日 下午8:16:36
     * @Description:单位
     * @return String
     */
    @RequestMapping(value = "/zl_tbhbfx_dw", method = RequestMethod.GET)
    public String getZl_thbfx_dw() {
        log.info("BESZlqsfxController支路用能---同环比分析");
        return "view/dataAnalysis/zlyn/zl_tbhbtjfx_dw";
    }

	/**
	 * 调取tab页面数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zl_tablist", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getZl_tablist() {
		ISSPReturnObject returnObject = beszlqsfxservice.getZl_tablist();
		return returnObject;
	}

	/**
	 * 生成支路配置树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/branch_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String fZzjgbh, String fnybh) {
		ISSPReturnObject returnObject = beszlqsfxservice.getTree(fZzjgbh, fnybh);
		return returnObject;
	}

	@RequestMapping(value = "/branch_tree_dep", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeDep(String fZzjgbh, String fnybh) {
		ISSPReturnObject returnObject = beszlqsfxservice.getTreeDep(fZzjgbh, fnybh);
		return returnObject;
	}

	/**
     * 生成pin装table 根据条件查询 时间
     * 
     * @return
     */
	@RequestMapping(value = "/pin_table", method = RequestMethod.POST)
	@ResponseBody
    public ISSPReturnObject pin_table(BesQstjfxData dto) {
        ISSPReturnObject returnObject = beszlqsfxservice.pinTable(dto);// 重写
		return returnObject;
	}

	/**
     * 生成pin装table 根据条件查询 单位
     * 
     * @return
     */
    @RequestMapping(value = "/pin_table_dw", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject pin_table_dw(BesQstjfxData dto) {
        ISSPReturnObject returnObject = beszlqsfxservice.pinTabledw(dto);// 重写
        return returnObject;
    }

    /**
     * 同比环比 生成pin装table 根据条件查询
     * 
     * @return
     */
	@RequestMapping(value = "/thb_pin_table", method = RequestMethod.POST)
	@ResponseBody
    public ISSPReturnObject thb_pin_table(BesQstjfxData dto) {
        ISSPReturnObject returnObject = beszlthbfxservice.thbPinTable(dto);// 重写
		return returnObject;
	}

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月27日 下午8:20:36
     * @Description:dw
     * @param dto
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/thb_pin_table_dw", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject thb_pin_table_dw(BesQstjfxData dto) {
        ISSPReturnObject returnObject = beszlthbfxservice.thbPinTabledw(dto);// 重写
        return returnObject;
    }
    
    /**
     *
     * @Description: 加载能源统计分析--支路用能--用能统计分析以及趋势统计分析
     * 
     * @auther: wanghongjie
     * @date: 11:43 2021/5/11 
     * @param: 
     * @return: 
     *
     */
    @RequestMapping(value = "/statisAnalyOfEnergyConsumption",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject statisAnalyOfEnergyConsumption(BesQstjfxData besQstjfxData) {

    	return beszlthbfxservice.statisAnalyOfEnergyConsumption(besQstjfxData);
	}

	/**
	 *
	 * @Description: 加载能源统计分析--支路用能--部门用能统计分析以及趋势统计分析
	 *
	 * @auther: wanghongjie
	 * @date: 11:43 2021/5/11
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/statisAnalyOfEnergyConsumptionDep",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject statisAnalyOfEnergyConsumptionDep(BesQstjfxData besQstjfxData) {

		return beszlthbfxservice.statisAnalyOfEnergyConsumptionDep(besQstjfxData);
	}

	/**
	 *
	 * @Description: 加载能源统计分析--支路用能--同比环比分析
	 *
	 * @auther: wanghongjie
	 * @date: 16:16 2021/5/12
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/yoyAndMoMAnalysis",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject yoyAndMoMAnalysis(BesQstjfxData besQstjfxData) {

		return beszlthbfxservice.yoyAndMoMAnalysis(besQstjfxData);
	}
}
