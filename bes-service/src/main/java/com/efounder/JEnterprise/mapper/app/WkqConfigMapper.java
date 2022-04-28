package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.WkqConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESWkq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 14:11:49
 */
@Mapper
public interface WkqConfigMapper {

    void insert(WkqConfigModel wkqConfigModel);

    List<Object> findList(WkqConfigModel wkqConfigModel);

    void update(WkqConfigModel wkqConfigModel);

    void delete(Integer id);

    Boolean insertWkq(BESWkq besWkq);
}
