package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.DynamicagreeEntity;
import com.github.pagehelper.PageInfo;

/**
 * 动态协议配置
 *
 * @author YangChao
 * @date 2020-09-03 09:40:12
 */
public interface DynamicagreeService {

    PageInfo<DynamicagreeEntity> getSearch(String keywords, Integer pageNum);

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(DynamicagreeEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(DynamicagreeEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public DynamicagreeEntity getSearchById(String id);

}

