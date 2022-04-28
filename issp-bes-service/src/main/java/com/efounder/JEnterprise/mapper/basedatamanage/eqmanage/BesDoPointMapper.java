package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDoPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DO基础信息mapper接口
 *
 * @author
 */
@Mapper
public interface BesDoPointMapper extends BaseMapper<String, BESAmmeter> {

    /**
     * 获取所有AI数据
     *
     * @return
     */
    List<BesDoPoint> loadAll();

    BesDoPoint queryDoPoint(String f_sys_name);
}