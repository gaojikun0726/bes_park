package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wzx
 * @date 2020-8-10 17:46:52
 */
@Mapper
public interface CgqConfigMapper {

    void insert(CgqConfigModel cgqConfigModel);

    List<Object> findList(CgqConfigModel cgqConfigModel);

    List<Object> findListByType(@Param("cgqType")String cgqType, CgqConfigModel cgqConfigModel);

    void update(CgqConfigModel cgqConfigModel);

    void delete(Integer id);
}
