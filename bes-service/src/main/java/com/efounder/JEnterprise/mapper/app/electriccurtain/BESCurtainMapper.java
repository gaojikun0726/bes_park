package com.efounder.JEnterprise.mapper.app.electriccurtain;

import com.efounder.JEnterprise.model.app.electriccurtain.BESCurain;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCurtain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wzx
 * @date 2020-5-12 14:11:49
 */
@Mapper
public interface BESCurtainMapper {

    void insert(BESCurain bescurain);

    List<Object> findList(BESCurain bescurain);

    void update(BESCurain bescurain);

    void delete(Integer id);

    Boolean insertCurtain(BESCurtain besCurtain);
}
