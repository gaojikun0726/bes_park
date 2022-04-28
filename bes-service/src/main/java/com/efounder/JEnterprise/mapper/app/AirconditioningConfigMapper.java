package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.AirconditioningConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAirconditioning;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 14:11:49
 */
@Mapper
public interface AirconditioningConfigMapper {

    void insert(AirconditioningConfigModel airconditioningConfigModel);

    List<Object> findList(AirconditioningConfigModel airconditioningConfigModel);

    void update(AirconditioningConfigModel airconditioningConfigModel);

    void delete(Integer id);

    Boolean insertAirconditioning(BESAirconditioning besAirconditioning);
}
