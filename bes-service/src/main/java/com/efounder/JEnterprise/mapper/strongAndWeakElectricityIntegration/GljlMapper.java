package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GljlMapper {
    //分页显示管理记录日志信息
    List<GljlModel> loadGljlxx(@Param("keywords") String keywords);
    //操作时插入日志信息
    public boolean addGljlxx(GljlModel gljlModel);
}
