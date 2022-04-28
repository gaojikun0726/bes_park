package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.AirconditioningUnitConfigModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 14:11:49
 */
@Mapper
public interface AirconditioningUnitConfigMapper {

    void insert(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    List<Object> findList(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    void update(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    void delete(Integer id);
}
