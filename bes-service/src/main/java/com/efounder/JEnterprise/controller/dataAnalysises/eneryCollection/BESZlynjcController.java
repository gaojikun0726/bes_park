package com.efounder.JEnterprise.controller.dataAnalysises.eneryCollection;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesOriginalData;
import com.efounder.JEnterprise.service.dataAnalysises.BESZlynjcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨超
 * @version 创建时间：2018年11月1日 上午9:27:47
 * @parameter 能源数据集抄--支路用能--支路用能集抄
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/view/dataAnalysis/eneryCollection")
public class BESZlynjcController {
    private static final Logger log = LoggerFactory.getLogger(BESZlynjcController.class);

    @Autowired
    private BESZlynjcService beszlynjcservice;

    /**
     * 能源数据集抄--支路用能--支路用能集抄首页
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_zlynjc", method = RequestMethod.GET)
    public String zl_zlynjc() {
        log.info("BESZlynjcController能源数据集抄--支路用能--支路用能集抄");
        return "view/dataAnalysis/nysjjc/zl_zlynjc";
    }

    /**
     * 生成pin装table 根据条件查询
     * 
     * @return
     */
    @RequestMapping(value = "/pin_table", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject pin_table(BesOriginalData dto) {
        ISSPReturnObject returnObject = beszlynjcservice.pin_table(dto);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--参数查询
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_cscx", method = RequestMethod.GET)
    public String zl_cscx() {
        log.info("BESZlynjcController能源数据集抄--支路用能--参数查询");
        return "view/dataAnalysis/nysjjc/zl_cscx";
    }

    /**
     * 能源数据集抄--支路用能--参数查询--选择电表下拉框查询
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_cscx_Select", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject zl_cscx_Select(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--支路用能--参数查询--选择电表下拉框查询");
        ISSPReturnObject returnObject = beszlynjcservice.zl_cscx_Select(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--参数查询--电能参数查询
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_cscx_Select_dncs", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject zl_cscx_Select_dncs(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--支路用能--参数查询--电能参数查询");
        ISSPReturnObject returnObject = beszlynjcservice.zl_cscx_Select_dncs(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--参数查询--统计分析
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_cscx_dncs_tjfx", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject zl_cscx_dncs_tjfx(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--支路用能--参数查询--统计分析");
        ISSPReturnObject returnObject = beszlynjcservice.zl_cscx_dncs_tjfx(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--实时参数查询
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_sscscx", method = RequestMethod.GET)
    public String zl_sscscx() {
        log.info("BESZlynjcController能源数据集抄--支路用能--实时参数查询");
        return "view/dataAnalysis/nysjjc/zl_sscscx";
    }

    /**
     * 能源数据集抄--支路用能--实时参数查询 动态查询展示数据
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_sscscx_sssj", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject zl_sscscx_sssj(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--支路用能--实时参数查询--动态查询展示数据");
        ISSPReturnObject returnObject = beszlynjcservice.zl_sscscx_sssj(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--支路用能--分时段统计
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_fsdtj", method = RequestMethod.GET)
    public String zl_fsdtj() {
        log.info("BESZlynjcController能源数据集抄--支路用能--分时段统计");
        return "view/dataAnalysis/nysjjc/zl_fsdtj";
    }

    /**
     * 能源数据集抄--支路用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/zl_fsdtj_jcsj", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject zl_fsdtj_jcsj(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--支路用能--分时段统计--动态查询展示数据");
        ISSPReturnObject returnObject = beszlynjcservice.zl_fsdtj_jcsj(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--分户用能--分时段统计
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/fh_fsdtj", method = RequestMethod.GET)
    public String fh_fsdtj() {
        log.info("BESZlynjcController能源数据集抄--分户用能--分时段统计");
        return "view/dataAnalysis/nysjjc/fh_fsdtj";
    }

    /**
     * 能源数据集抄--分户用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/fh_fsdtj_jcsj", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject fh_fsdtj_jcsj(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--分户用能--分时段统计--动态查询展示数据");
        ISSPReturnObject returnObject = beszlynjcservice.fh_fsdtj_jcsj(request);
        return returnObject;
    }

    /**
     * 能源数据集抄--分项用能--分时段统计
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/fx_fsdtj", method = RequestMethod.GET)
    public String fx_fsdtj() {
        log.info("BESZlynjcController能源数据集抄--分户用能--分时段统计");
        return "view/dataAnalysis/nysjjc/fx_fsdtj";
    }

    /**
     * 能源数据集抄--分项用能--分时段统计 动态查询展示数据
     * 
     * @param
     * @return
     */
    @RequestMapping(value = "/fx_fsdtj_jcsj", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject fx_fsdtj_jcsj(HttpServletRequest request) {
        log.info("BESZlynjcController能源数据集抄--分户用能--分时段统计--动态查询展示数据");
        ISSPReturnObject returnObject = beszlynjcservice.fx_fsdtj_jcsj(request);
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年2月27日 下午2:46:39
     * @Description:数据分析导出功能
     * @param request
     * @return ISSPReturnObject
     */
    @RequestMapping(value = "/expExcel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject expExcel(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = beszlynjcservice.expExcel(request);
        return returnObject;
    }

    /**
     *
     * @Description: 根据能源类型查询能源单位
     *
     * @auther: wanghongjie
     * @date: 9:52 2021/1/11
     * @param: [energyType]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/getUnitByEnergyType",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getUnitByEnergyType(String energyType) {
        ISSPReturnObject returnObject = beszlynjcservice.getUnitByEnergyType(energyType);
        return returnObject;
    }

}
