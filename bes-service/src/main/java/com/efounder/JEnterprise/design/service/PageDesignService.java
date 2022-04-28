package com.efounder.JEnterprise.design.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.design.model.DesignArea;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2020/05/21
 */
public interface PageDesignService {

    /**
     * 查询区域树
     * @return
     */
    List<Map> queryAreaTree();


    /**
     *  新增区域信息
     * @param designArea 区域信息
     * @return
     */
    ISSPReturnObject addDesignArea(DesignArea designArea);

    /**
     *  新增区域信息
     * @param map 区域信息
     * @return
     */
     ISSPReturnObject addArea(Map map);

    /**
     *  修改区域信息
     * @param map 区域信息
     * @return
     */
    ISSPReturnObject editArea(Map map);


    /**
     *  隐藏区域信息
     * @param map 区域信息
     * @return
     */
    ISSPReturnObject hideArea(Map map);


    /**
     *  隐藏当前节点及其所有的子节点
     * @param map 区域信息
     * @return
     */
    ISSPReturnObject hideChildrenArea(Map map);

    /**
     *  新增设计页面
     * @param map 设计页面信息
     * @return
     */
    ISSPReturnObject addPage(Map map);


    /**
     *  修改设计页面
     * @param map 设计页面信息
     * @return
     */
    ISSPReturnObject editPage(Map map);


    /**
     *  查询设计页面
     * @param map 设计页面信息
     * @return
     */
    ISSPReturnObject queryPageInfo(Map map);

    /**
     *  保存设计页面
     * @param map 设计页面信息
     * @return
     */
    ISSPReturnObject savePageInfo(Map map);

    /**
     * 保存页面
     * @param map
     * @return
     */
    boolean insert(Map map);


    /**
     * 查询页面
     * @param map
     * @return
     */
    Map queryOne(Map map);

    /**
     * 复制页面
     * @param copyId 复制页面id
     * @param targetId 目标页面id
     * @return
     */
    ISSPReturnObject copyHtml(String copyId,String targetId);
}
