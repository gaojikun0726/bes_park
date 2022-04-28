package com.efounder.JEnterprise.design.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2020/05/21
 */
@Mapper
public interface PageDesignMapper {

    /**
     * 查询区域树
     * @return
     */
    List<Map> queryAreaTree();

    /**
     * 新增区域信息
     * @param map 实体类
     * @return
     */
    Integer addDesignArea(Map map);

    /**
     *  新增区域信息
     * @param map 区域信息
     * @return
     */
    Integer addArea(Map map);

    /**
     *  修改区域信息
     * @param map 区域信息
     * @return
     */
    Integer editArea(Map map);


    /**
     *  隐藏区域信息
     * @param map 区域信息
     * @return
     */
    Integer hideArea(Map map);

    /**
     *  隐藏区域信息
     * @param map 区域信息
     * @return
     */
    Integer hideAreaList(Map map);


    /**
     * 查询当前节点及其所有子节点id
     * @param map 区域id
     * @return
     */
    List<String> queryAreaChildren(Map map);


    /**
     *  隐藏当前节点及其所有的子节点
     * @param map 区域信息
     * @return
     */
    Integer hideChildrenArea(Map map);

    /**
     * 隐藏设计页面
     * @param map 区域idList
     * @return
     */
    Integer hidePageByAreaList(Map map);

    /**
     *  新增设计页面
     * @param map 设计页面信息
     * @return
     */
    Integer addPage(Map map);

    /**
     *  修改设计页面
     * @param map 设计页面信息
     * @return
     */
    Integer editPage(Map map);


    /**
     *  隐藏设计页面
     * @param map 设计页面信息
     * @return
     */
    Integer hidePage(Map map);


    /**
     *  查询设计页面
     * @param map 设计页面信息
     * @return
     */
    List<Map> queryPageInfo(Map map);

    /**
     * 保存设计页面
     * @param map
     * @return
     */
    Integer insert(Map map);


    /**
     * 查询页面
     * @param map
     * @return
     */
    List<Map> queryOne(Map map);

    /**
     * 复制html
     * @param map 拷贝页面areaId,目标页面areaId
     * @return
     */
    Integer copyHtmlByAreaId(Map map);
}
