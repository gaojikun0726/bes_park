package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDiPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DI基础信息mapper接口
 *
 * @author
 */
@Mapper
public interface BesDiPointMapper extends BaseMapper<String, BESAmmeter> {

    /**
     * 获取所有AI数据
     *
     * @return
     */
    List<BesDiPoint> loadAll();

    BesDiPoint queryDiPoint(String f_sys_name);
}