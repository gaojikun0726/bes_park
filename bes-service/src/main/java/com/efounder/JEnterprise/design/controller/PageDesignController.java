package com.efounder.JEnterprise.design.controller;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.design.model.DesignArea;
import com.efounder.JEnterprise.design.service.PageDesignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 页面设计器
 *
 * @author zs
 * @date 2020/05/21
 */
@Controller
@RequestMapping("/view/pageDesign")
public class PageDesignController {

    @Resource
    private PageDesignService pageDesignService;

    /**
     * 区域管理--外层iframe页面
     * @return
     */
    @RequestMapping("/areaFrame")
    public String areaFrame(){
        return "/view/app/pageDesign/areaFrame";
    }

    /**
     * 区域管理页面
     * @return
     */
    @RequestMapping("/areaView")
    public String areaView(){
        return "/view/app/pageDesign/designArea";
    }

    /**
     * 查询区域树
     * @return
     */
    @RequestMapping("/queryAreaTree")
    @ResponseBody
    public List<Map> queryAreaTree(){
        return pageDesignService.queryAreaTree();
    }


    /**
     *  新增区域信息
     * @param designArea 区域信息
     * @return
     */
    @RequestMapping(value = "/addDesignArea",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addDesignArea(@RequestBody DesignArea designArea){
        return pageDesignService.addDesignArea(designArea);
    }

    /**
     *  新增区域信息
     * @param map 区域信息
     * @return
     */
    @RequestMapping(value = "/addArea",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addArea(@RequestBody Map map){

        return pageDesignService.addArea(map);
    }

    /**
     *  修改区域信息
     * @param map 区域信息
     * @return
     */
    @RequestMapping(value = "/editArea",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject editArea(@RequestBody Map map){

        return pageDesignService.editArea(map);
    }

    /**
     *  隐藏区域信息
     * @param map 区域信息
     * @return
     */
    @RequestMapping(value = "/hideChildrenArea",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject hideChildrenArea(@RequestBody Map map){

        return pageDesignService.hideChildrenArea(map);
    }

    /**
     *  新增设计页面
     * @param map 设计页面信息
     * @return
     */
    @RequestMapping(value = "/addPage",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addPage(@RequestBody Map map){

        return pageDesignService.addPage(map);
    }


    /**
     *  修改设计页面
     * @param map 设计页面信息
     * @return
     */
    @RequestMapping(value = "/editPage",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject editPage(@RequestBody Map map){

        return pageDesignService.editPage(map);
    }

    /**
     *  查询设计页面
     * @param map 设计页面信息
     * @return
     */
    @RequestMapping(value = "/queryPageInfo",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryPageInfo(@RequestBody Map map){

        return pageDesignService.queryPageInfo(map);
    }


    /**
     *  保存设计页面
     * @param map 设计页面信息
     * @return
     */
    @RequestMapping(value = "/savePageInfo",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject savePageInfo(@RequestBody Map map){

        return pageDesignService.savePageInfo(map);
    }

    /**
     * 设计器页面
     * @return
     */
    @RequestMapping("/view")
    public String view(String areaId, Model model){
        model.addAttribute("areaId",areaId);
        return "/view/app/pageDesign/pageDesign";
    }


    /**
     * 模板设计页面
     * @return
     */
    @RequestMapping("/moduleView")
    public String moduleView(Model model){
       // model.addAttribute("areaId",areaId);
        return "/view/app/pageDesign/designWin/template";
    }

    /**
     * 页面切换
     * @return
     */
    @RequestMapping("/frameView")
    public String frameView(String areaId, Model model){
        model.addAttribute("areaId",areaId);
        return "/view/app/pageDesign/designFrame";
    }

    /**
     * 保存设计页面
     * @param content
     * @return
     */
    @RequestMapping("/saveContent")
    @ResponseBody
    public String saveContent(String content){
        Map map = new HashMap();
        map.put("content",content);
        boolean result = pageDesignService.insert(map);
        return String.valueOf(result);
    }

    /**
     * 展示页面
     * @return
     */
    @RequestMapping("/displayView")
    public String displayView(String areaId, Model model){
        model.addAttribute("areaId",areaId);
        return "/view/app/pageDesign/displayPage";
    }

    /**
     * 查询页面html
     * @return
     */
    @RequestMapping("/getContent")
    @ResponseBody
    public Map getContent(){
        Map map = new HashMap();
        Map result = pageDesignService.queryOne(map);
        return result;
    }

    /**
     * 保存skinColor的值到session中
     * @return
     */
    @RequestMapping(value = "/setSkinColor",method = RequestMethod.POST)
    @ResponseBody
    public Map setSkinColor(String skinColor, HttpSession httpSession){
        httpSession.setAttribute("skinColor",skinColor);
        Map map = new HashMap();
        return map;
    }


    /**
     * 从session中取skinColor值
     * @return
     */
    @RequestMapping("/getSkinColor")
    @ResponseBody
    public Map getSkinColor(HttpSession httpSession){
        Object skinColor = httpSession.getAttribute("skinColor");
        Map map = new HashMap();
        map.put("skinColor",skinColor);
        return map;
    }

    /**
     * 复制页面
     * @return
     */
    @RequestMapping(value = "/copyHtml",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject copyHtml(String copyId,String targetId){

        return pageDesignService.copyHtml(copyId,targetId);
    }

}
