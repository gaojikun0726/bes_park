package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConfTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分项配置模板mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESSubitemConfTemplateMapper extends BaseMapper<String , BESSubitemConfTemplate>{

    /**
     *
     * @Description: 获取分项配置模板的数据
     *
     * @auther: wanghongjie
     * @date: 15:39 2021/5/26
     * @param: [fNybh, fYqbh, buildingbh]
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConfTemplate>
     *
     */
    List<BESSubitemConfTemplate> besSubitemConfTemplates(@Param("fNybh") String fNybh,@Param("fYqbh") String fYqbh,@Param("buildingbh") String buildingbh);

    /**
     *
     * @Description: 查询父节点的分项编号
     *
     * @auther: wanghongjie
     * @date: 17:24 2021/5/26
     * @param: [fNybh, fYqbh, buildingbh, fSubitemCode]
     * @return: java.lang.String
     *
     */
    String selectFxbh(@Param("fNybh") String fNybh,@Param("fYqbh") String fYqbh,@Param("buildingbh") String buildingbh,@Param("fSubitemCode") String fSubitemCode);
}