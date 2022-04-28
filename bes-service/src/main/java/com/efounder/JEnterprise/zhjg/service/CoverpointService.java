package com.efounder.JEnterprise.zhjg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhjg.entity.CoverpointEntity;
import com.github.pagehelper.PageInfo;

/**
 * 设备维护
 *
 * @author YangChao
 * @date 2020-10-09 14:28:58
 */
public interface CoverpointService {

    PageInfo<CoverpointEntity> getSearch(String keywords, Integer pageNum);

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(CoverpointEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(CoverpointEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public CoverpointEntity getSearchById(String id);

}

