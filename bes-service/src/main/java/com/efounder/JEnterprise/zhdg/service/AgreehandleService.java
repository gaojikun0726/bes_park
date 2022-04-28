package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.AgreehandleEntity;
import com.github.pagehelper.PageInfo;

/**
 * 协议处理类型维护
 *
 * @author YangChao
 * @date 2020-08-31 17:41:28
 */
public interface AgreehandleService {

    PageInfo<AgreehandleEntity> getSearch(String keywords, Integer pageNum);

    /**
     * 协议处理类型查询
     */
    public ISSPReturnObject getHandleSearch();

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(AgreehandleEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(AgreehandleEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public AgreehandleEntity getSearchById(String id);

}

