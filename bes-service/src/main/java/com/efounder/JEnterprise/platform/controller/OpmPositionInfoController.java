package com.efounder.JEnterprise.platform.controller;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.service.IOpmPositionInfoService;
import com.efounder.JEnterprise.platform.util.ConstantValue;
import com.efounder.JEnterprise.platform.util.List2Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域位置信息Controller
 * 
 * @author zs
 * @date 2020-10-29
 */
@Controller
@RequestMapping("/platform/position")
public class OpmPositionInfoController
{
    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/platform/position";

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(OpmPositionInfoController.class);

    @Resource
    private IOpmPositionInfoService opmPositionInfoService;


    /**
     * 页面
     * @return
     */
    @RequestMapping("/view")
    public String view(){
        return prefix + "/positionInfo";
    }

    /**
     * 查询区域位置树
     * @return
     */
    @RequestMapping("/queryPositionTree")
    @ResponseBody
    public List<Map> queryPositionTree(){
        return opmPositionInfoService.queryPositionTree();
    }


    /**
     * 查询区域位置类型数据
     * @return
     */
    @RequestMapping("/queryPositionType")
    @ResponseBody
    public ISSPReturnObject queryPositionType(){
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        List<Map> list = opmPositionInfoService.queryPositionType();
        Map map = List2Map.list2Map(list,"value","name");
        isspReturnObject.setStatus("1");
        isspReturnObject.setList(list);
        isspReturnObject.setMap(map);
        return isspReturnObject;
    }


    /**
     * 查询区域位置列表
     * @param request
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Map queryList(HttpServletRequest request){
        //区域位置id
        String parentId = request.getParameter("parent_id");
        //区域编号
        String positionCode = request.getParameter("position_code");
        //区域名称
        String positionName = request.getParameter("position_name");
        //区域类型
        String positionType = request.getParameter("position_type");

        //排序字段
        String field = request.getParameter("field");
        //排序顺序
        String order = request.getParameter("order");

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isEmpty(pageNumber)){
            pageNumber = String.valueOf(ConstantValue.PAGE_NUM);
        }
        if(StringUtils.isEmpty(pageSize)){
            pageSize = String.valueOf(ConstantValue.PAGE_SIZE);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",parentId);
        map.put("positionName",positionName);
        map.put("positionCode",positionCode);
        map.put("positionType",positionType);
        map.put("field",field);
        map.put("order",order);
        return opmPositionInfoService.queryList(map,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
    }

    /**
     * 查询已关联电表数据
     * @return
     */
    @RequestMapping("/queryContainAmmeter")
    @ResponseBody
    public List<Map> queryContainAmmeter(String positionId,String keywords){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);
        //电表编号或名称
        map.put("keywords",keywords);
        return opmPositionInfoService.queryContainAmmeter(map);
    }


    /**
     * 查询未关联电表数据
     * @return
     */
    @RequestMapping("/queryRemainAmmeter")
    @ResponseBody
    public List<Map> queryRemainAmmeter(String positionId,String keywords){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);
        //电表编号或名称
        map.put("keywords",keywords);
        return opmPositionInfoService.queryRemainAmmeter(map);
    }

//

    /**
     * 添加电表房间关联数据
     * @return
     */
    @RequestMapping("/addPositionAmmeterConfig")
    @ResponseBody
    public ISSPReturnObject addPositionAmmeterConfig(String positionId, String ammeterId){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);
        List<Map> list = new ArrayList<>();
        Map item = new HashMap();
        //电表id
        item.put("ammeterId",ammeterId);
        list.add(item);
        map.put("list",list);
        return opmPositionInfoService.addPositionAmmeterConfig(map);
    }

    /**
     * 删除电表房间关联数据
     * @return
     */
    @RequestMapping("/deletePositionAmmeterConfig")
    @ResponseBody
    public ISSPReturnObject deletePositionAmmeterConfig(String positionId, String ammeterId){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);

        List<Map> list = new ArrayList<>();
        Map item = new HashMap();
        //电表id
        item.put("ammeterId",ammeterId);
        list.add(item);
        map.put("list",list);
        return opmPositionInfoService.deletePositionAmmeterConfig(map);
    }

    /**
     * 按条件查询未关联电表并关联到房间
     * @return
     */
    @RequestMapping("/addRelativeByCondition")
    @ResponseBody
    public ISSPReturnObject addRelativeByCondition(String positionId, String keywords){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);
        //电表编号或名称
        map.put("keywords",keywords);
        return opmPositionInfoService.addRelativeByCondition(map);
    }


    /**
     * 按条件查询已关联电表并移除关联
     * @return
     */
    @RequestMapping("/deleteRelativeByCondition")
    @ResponseBody
    public ISSPReturnObject deleteRelativeByCondition(String positionId, String keywords){
        Map<String,Object> map = new HashMap<>();
        //区域位置id
        map.put("positionId",positionId);
        //电表编号或名称
        map.put("keywords",keywords);
        return opmPositionInfoService.deleteRelativeByCondition(map);
    }


}
