package com.efounder.JEnterprise.design.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.design.mapper.GraphDesignMapper;
import com.efounder.JEnterprise.design.mapper.PageDesignMapper;
import com.efounder.JEnterprise.design.model.DesignArea;
import com.efounder.JEnterprise.design.service.PageDesignService;
import com.efounder.JEnterprise.design.util.ConstantValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * describe:
 *
 * @author zs
 * @date 2020/05/21
 */
@Service
public class PageDesignServiceImpl implements PageDesignService {


    @Resource
    private PageDesignMapper pageDesignMapper;

    @Resource
    private GraphDesignMapper graphDesignMapper;

    /**
     * 查询区域树
     *
     * @return
     */
    @Override
    public List<Map> queryAreaTree() {
        return pageDesignMapper.queryAreaTree();
    }

    /**
     * 新增区域信息
     *
     * @param designArea 区域信息
     * @return
     */
    @Override
    public ISSPReturnObject addDesignArea(DesignArea designArea) {
        designArea.setHideState(ConstantValue.HIDE_STATE_FALSE.getValue());
        Map map = new HashMap();
        map.put("designArea",designArea);
        Integer num = pageDesignMapper.addDesignArea(map);
        ISSPReturnObject object = new ISSPReturnObject();
        if(num == 1){
            object.setStatus("1");
        }else{
            object.setStatus("0");
        }

        return object;
    }

    /**
     * 新增区域信息
     *
     * @param map 区域信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ISSPReturnObject addArea(Map map) {
        map.put("hideState",ConstantValue.HIDE_STATE_FALSE.getValue());
        String areaId = UUID.randomUUID().toString();
        map.put("areaId",areaId);
        Integer num = pageDesignMapper.addArea(map);
        ISSPReturnObject object = new ISSPReturnObject();
        if(num == 1){
            String pageId = UUID.randomUUID().toString();
            map.put("pageId",pageId);
            num = pageDesignMapper.addPage(map);
            if(num == 1){
                object.setStatus("1");
            }
        }else{
            object.setStatus("0");
        }

        return object;
    }

    /**
     * 修改区域信息
     *
     * @param map 区域信息
     * @return
     */
    @Override
    public ISSPReturnObject editArea(Map map) {
        Integer num = pageDesignMapper.editArea(map);
        ISSPReturnObject object = new ISSPReturnObject();
        if(num == 1){
            object.setStatus("1");
        }else{
            object.setStatus("0");
        }

        return object;
    }

    /**
     * 隐藏区域信息
     *
     * @param map 区域信息
     * @return
     */
    @Override
    public ISSPReturnObject hideArea(Map map) {
        map.put("hideState",ConstantValue.HIDE_STATE_TRUE.getValue());
        ISSPReturnObject object = new ISSPReturnObject();
        Integer num = pageDesignMapper.hideArea(map);
        if(num == 1){
          num = pageDesignMapper.hidePage(map);
          object.setStatus("1");
        }else{
            object.setStatus("0");
        }
        return object;
    }


    /**
     * 隐藏当前节点及其所有的子节点
     *
     * @param map 区域信息
     * @return
     */
    @Override
    public ISSPReturnObject hideChildrenArea(Map map) {
        map.put("hideState",ConstantValue.HIDE_STATE_TRUE.getValue());
        ISSPReturnObject object = new ISSPReturnObject();
        List<String> idList = pageDesignMapper.queryAreaChildren(map);
        map.put("idList",idList);
        if(idList != null && idList.size() > 0){
            Integer num = pageDesignMapper.hideAreaList(map);
//        if(num == 1){
            num = pageDesignMapper.hidePageByAreaList(map);
            object.setStatus("1");
       /* }else{
            object.setStatus("0");
        }*/
        }

        return object;
    }

    /**
     * 新增设计页面
     *
     * @param map 设计页面信息
     * @return
     */
    @Override
    public ISSPReturnObject addPage(Map map) {
        ISSPReturnObject object = new ISSPReturnObject();
        String pageId = UUID.randomUUID().toString();
        map.put("pageId",pageId);
        Integer num = pageDesignMapper.addPage(map);
        if(num == 1){
            object.setStatus("1");
        }else{
            object.setStatus("0");
        }
        return object;
    }

    /**
     * 修改设计页面
     *
     * @param map 设计页面信息
     * @return
     */
    @Override
    public ISSPReturnObject editPage(Map map) {
        ISSPReturnObject object = new ISSPReturnObject();
        Integer num = pageDesignMapper.editPage(map);
        if(num == 1){
            object.setStatus("1");
        }else{
            object.setStatus("0");
        }
        return object;
    }

    /**
     * 查询设计页面
     *
     * @param map 设计页面信息
     * @return
     */
    @Override
    public ISSPReturnObject queryPageInfo(Map map) {
        ISSPReturnObject object = new ISSPReturnObject();
        List<Map> list = pageDesignMapper.queryPageInfo(map);
        if(list != null && list.size() > 0 && list.get(0) != null){
             String pageId = (String) list.get(0).get("id");
             map.put("pageId",pageId);
             List<Map> graphList = graphDesignMapper.queryGraphsByPageId(map);
             object.setMap(list.get(0));
             object.setList(graphList);
             object.setStatus("1");
        }else{
            object.setStatus("0");
        }
        return object;
    }

    /**
     * 保存设计页面
     *
     * @param map 设计页面信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ISSPReturnObject savePageInfo(Map map) {
       if(!map.get("graphs").equals("")){
           map = this.handlePageData(map);
           map.put("lineType",ConstantValue.GRAPH_TYPE_LINE.getValue());
           map.put("circleType",ConstantValue.GRAPH_TYPE_CIRCLE.getValue());
           map.put("rectType",ConstantValue.GRAPH_TYPE_RECT.getValue());

           List<Map> list = pageDesignMapper.queryPageInfo(map);
           if(list != null && list.size() > 0 && list.get(0) != null){
               //修改
               Integer num = pageDesignMapper.editPage(map);
               String pageId = String.valueOf(list.get(0).get("id"));
               if(num > 0){
                   List<String> pageIdList = new ArrayList<>();
                   pageIdList.add(pageId);
                   map.put("pageIdList",pageIdList);
                   map.put("pageId",pageId);
                   graphDesignMapper.deleteGraphsByPageIds(map);
                   this.insertGraphs(map);
               }
//            return this.editPage(map);
           }else{
               //新增
               /*if(!map.get("areaId").equals("")){
                   map.put("pageId",map.get("areaId"));
               }else{*/
                   String pageId = UUID.randomUUID().toString();
                   map.put("pageId",pageId);
            //   }
               map.put("hideState",ConstantValue.HIDE_STATE_TRUE.getValue());
               Integer num = pageDesignMapper.addPage(map);
               if(num > 0){
                   this.insertGraphs(map);
               }
//            return this.addPage(map);
           }
       }else{
           List<Map> list = pageDesignMapper.queryPageInfo(map);
           if(list != null && list.size() > 0 && list.get(0) != null){
               //修改
               Integer num = pageDesignMapper.editPage(map);
               String pageId = String.valueOf(list.get(0).get("id"));
           }else{
               //新增
               /*if(!map.get("areaId").equals("")){
                   map.put("pageId",map.get("areaId"));
               }else{*/
                   String pageId = UUID.randomUUID().toString();
                   map.put("pageId",pageId);
             /*  }*/
               map.put("hideState","11");
               Integer num = pageDesignMapper.addPage(map);
           }
       }

        ISSPReturnObject returnObject = new ISSPReturnObject();
        returnObject.setStatus("1");
        return returnObject;
    }


    /**
     * 保存图形数据
     * @param map
     */
    public void insertGraphs(Map map){
        List lineList = (List) map.get("lineList");
        List circleList = (List) map.get("circleList");
        List rectList = (List)map.get("rectList");
        if(circleList != null && circleList.size() > 0){
            graphDesignMapper.insertCircles(map);
        }
        if(lineList != null && lineList.size() > 0){
            graphDesignMapper.insertLines(map);
        }
       if(rectList != null && rectList.size() > 0){
           graphDesignMapper.insertRects(map);
       }

    }

    /**
     * 保存页面之前，解析页面数据
     * @param map 页面数据
     * @return
     */
    public Map handlePageData(Map map){
       Map graphs = (Map) map.get("graphs");
       List lines = (List) graphs.get("lines");
       List circles = (List) graphs.get("circles");
       List rects = (List) graphs.get("rects");
       map.put("lineList",lines);
       map.put("circleList",circles);
       map.put("rectList",rects);
        return map;
    }

    /**
     * 保存页面
     *
     * @param map
     * @return
     */
    @Override
    public boolean insert(Map map) {
        Integer num = pageDesignMapper.insert(map);
        return num == 1;
    }

    /**
     * 查询页面
     *
     * @param map
     * @return
     */
    @Override
    public Map queryOne(Map map) {
        List<Map> list = pageDesignMapper.queryOne(map);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 复制页面
     *
     * @param copyId   复制页面id
     * @param targetId 目标页面id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ISSPReturnObject copyHtml(String copyId, String targetId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map map = new HashMap();
        map.put("targetId",targetId);
        map.put("copyId",copyId);
        //复制html
        Integer num = pageDesignMapper.copyHtmlByAreaId(map);
        if(num == 1){
            //复制图形
            graphDesignMapper.copyGraphByAreaId(map);
            returnObject.setStatus("1");
        }else{
            returnObject.setStatus("0");
        }
        return returnObject;
    }
}
