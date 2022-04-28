package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesModule;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPointStruct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 18:33 2020/7/13
 * @Modified By:
 */
@Mapper
public interface BesPointStructMapper extends BaseMapper<String, BesPointStruct> {

    /**
     *
     * @Description: 修改模块的同步状态
     *
     * @auther: wanghongjie
     * @date: 16:14 2020/7/13
     * @param: [besModule]
     * @return: void
     *
     */
    void updateByPrimaryKeySelectModule(BesModule besModule);
    /**
     *
     * @Description: 修改虚点的同步状态
     *
     * @auther: wanghongjie
     * @date: 18:41 2020/7/13
     * @param: [besPointStruct]
     * @return: void
     *
     */
    void updateByPrimaryKeySelectVpoint(BesPointStruct besPointStruct);

    /**
     *
     * @Description: 查询相应的点位表中是否存在这个点位
     *
     * @auther: wanghongjie
     * @date: 9:48 2020/7/14
     * @param: [f_sys_name_old]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectPointMap(@Param("tabName") String tabName,@Param("f_sys_name_old") String f_sys_name_old);

    /**
     *
     * @Description: 修改不同的点位表的同步状态
     *
     * @auther: wanghongjie
     * @date: 10:02 2020/7/14
     * @param: [besPointStruct]
     * @return: void
     *
     */
    void updateByPrimaryKeySelectDOPoint(BesPointStruct besPointStruct);
    void updateByPrimaryKeySelectAOPoint(BesPointStruct besPointStruct);
    void updateByPrimaryKeySelectDIPoint(BesPointStruct besPointStruct);
    void updateByPrimaryKeySelectAIPoint(BesPointStruct besPointStruct);

    /**
     *
     * @Description: 修改相应的点位表的实时数据
     *
     * @auther: wanghongjie
     * @date: 9:56 2020/7/15
     * @param: [valueOf, valueOf1]
     * @return: int
     *
     */
    Integer updatePointValue(@Param("tabName") String tabName,@Param("id") String id,@Param("value") String value);

    /**
     *
     * @Description: 修改相应的虚点位表的实时数据
     *
     * @auther: wanghongjie
     * @date: 10:45 2020/7/15
     * @param: [valueOf, valueOf1]
     * @return: java.lang.Integer
     *
     */
    Integer updateVPointValue(@Param("id") String id,@Param("value") String value);

}
