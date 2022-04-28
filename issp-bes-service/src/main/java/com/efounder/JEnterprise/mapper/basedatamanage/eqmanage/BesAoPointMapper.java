package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAoPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AO基础信息mapper接口
 *
 * @author
 */
@Mapper
public interface BesAoPointMapper extends BaseMapper<String, BESAmmeter> {

    /**
     * 获取所有AO数据
     *
     * @return
     */
    List<BesAoPoint> loadAll();

    BesAoPoint queryAoPoint(String f_sys_name);
}