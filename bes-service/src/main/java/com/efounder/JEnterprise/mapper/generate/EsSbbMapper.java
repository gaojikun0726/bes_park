package com.efounder.JEnterprise.mapper.generate;

import com.efounder.JEnterprise.model.generator.EsSbb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface EsSbbMapper {
    Boolean deleteByPrimaryKey(EsSbb essbb);

    int insert(EsSbb record);

    Boolean insertSelective(EsSbb record);

    EsSbb findsbblId(String id);

    List<EsSbb> selectByPrimarykeywords(String zzjgbh);

    int updateByPrimaryKeySelective(EsSbb record);

    Boolean updateByPrimaryKey(EsSbb record);

    List<EsSbb> selectbuyjiji(@Param("sbbkeywords")String sbbkeywords);


}
