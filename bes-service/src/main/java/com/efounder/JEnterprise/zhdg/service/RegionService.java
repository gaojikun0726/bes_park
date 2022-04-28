package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.RegionEntity;
import com.github.pagehelper.PageInfo;

/**
 * 区域表
 *
 * @author YangChao
 * @date 2020-08-28 11:48:44
 */
public interface RegionService {

    PageInfo<RegionEntity> getSearch(String keywords, Integer pageNum);

    /**
     * 下拉列表查询
     */
    public ISSPReturnObject getRegionSearch();

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(RegionEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(RegionEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public RegionEntity getSearchById(String id);

}

