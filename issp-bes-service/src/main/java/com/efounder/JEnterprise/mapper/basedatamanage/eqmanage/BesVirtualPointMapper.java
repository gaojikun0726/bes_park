package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesVirtualPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 虚点基础信息mapper接口
 *
 * @author
 */
@Mapper
public interface BesVirtualPointMapper extends BaseMapper<String, BESAmmeter> {

    /**
     * 获取所有虚点数据
     *
     * @return
     */
    List<BesVirtualPoint> loadAll();

    BesVirtualPoint queryVirtualPoint(String f_sys_name);
}