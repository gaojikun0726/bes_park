package com.efounder.JEnterprise.mapper.datacenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.datacenter.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/** 
 * 类名称：NewsMapper
 * 类描述：新闻mapper接口
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:24:15
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

    List<News> findNewsByPage(@Param("keywords") String keywords);

}
