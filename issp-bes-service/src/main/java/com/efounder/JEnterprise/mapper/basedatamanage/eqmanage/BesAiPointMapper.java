package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI基础信息mapper接口
 *
 * @author
 */
@Mapper
public interface BesAiPointMapper extends BaseMapper<String, BESAmmeter> {

    /**
     * 获取所有AI数据
     *
     * @return
     */
    List<BesAiPoint> loadAll();

    BesAiPoint queryAiPoint(String f_sys_name);
}