package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.WarnRecordEntity;
import com.github.pagehelper.PageInfo;

/**
 * 报警历史记录表
 *
 * @author YangChao
 * @date 2020-09-14 11:54:54
 */
public interface WarnRecordService {

    PageInfo<WarnRecordEntity> getSearch(String warnid,String beginTime,String endTime, Integer pageNum);

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(WarnRecordEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(WarnRecordEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
    * 根据id查询具体参数
    */
    public WarnRecordEntity getSearchById(String id);

    /**
     * 清空历史数据
     */
    public ISSPReturnObject clean();

    /**
     * @Description: 保存实时报警数据
     *
     */
    void saveRealWarn() throws Exception;
}

