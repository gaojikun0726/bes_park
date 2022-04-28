package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.WarntypeEntity;
import com.github.pagehelper.PageInfo;

/**
 * 报警类型维护表
 *
 * @author YangChao
 * @date 2020-09-14 11:20:24
 */
public interface WarntypeService {

    PageInfo<WarntypeEntity> getSearch(String keywords, Integer pageNum);

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(WarntypeEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(WarntypeEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public WarntypeEntity getSearchById(String id);

    /**
     * 下拉列表查询所有报警类型
     */
    public ISSPReturnObject getAllWarnType();
}

