package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.PointwarnEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 点位报警维护
 *
 * @author YangChao
 * @date 2020-09-16 10:20:05
 */
public interface PointwarnService {

    PageInfo<PointwarnEntity> getSearch(String pointid,String warnid, Integer pageNum);

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(PointwarnEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(PointwarnEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public PointwarnEntity getSearchById(String id);

    List<PointwarnEntity> selectSebPointwarnList(PointwarnEntity pointwarnEntity);
}

