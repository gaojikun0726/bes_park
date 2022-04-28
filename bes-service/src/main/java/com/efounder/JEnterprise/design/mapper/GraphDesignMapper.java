package com.efounder.JEnterprise.design.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 设计器图形接口
 */
@Mapper
public interface GraphDesignMapper {

    /**
     * 新增线段
     * @param map 线段列表数据
     * @return
     */
    Integer insertLines(Map map);

    /**
     * 新增矩形
     * @param map 矩形列表数据
     * @return
     */
    Integer insertRects(Map map);

    /**
     * 新增圆形
     * @param map 圆形列表数据
     * @return
     */
    Integer insertCircles(Map map);

    /**
     * 根据页面id查询所有图形
     * @param map 页面id
     * @return
     */
    List<Map> queryGraphsByPageId(Map map);

    /**
     * 删除页面关联的图形记录
     * @param map 页面id列表
     * @return
     */
    Integer deleteGraphsByPageIds(Map map);

    /**
     * 复制页面的图形
     * @param map 拷贝页面areaId,目标页面areaId
     * @return
     */
    Integer copyGraphByAreaId(Map map);
}
