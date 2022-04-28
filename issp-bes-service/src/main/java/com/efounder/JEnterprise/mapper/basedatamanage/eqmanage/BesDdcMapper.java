package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesDdcMapper extends BaseMapper<String, BesDdc>{
    int deleteByPrimaryKey(String fSysName);

    int insert(BesDdc record);

    int insertSelective(BesDdc record);

    int updateByPrimaryKeySelective(BesDdc record);

    int updateByGuidSelective(BesDdc record);

    int updateByPrimaryKey(BesDdc record);

	Map<String, Object> queryDDCInfo(String fSysName);

    Map<String, Object> queryDDCInfoByIp(String ipAdress);

    Map<String, Object> queryDDCInfoByChannelId(String channelId);

    Map<String, Object> queryDDCMap(String f_sys_name);

    void updateModule(BesModule besModule);

    /**
     *
     * @Description: 修改bes_ddc表的通道id
     *
     * @auther: wanghongjie
     * @date: 15:39 2020/7/13 
     * @param: [besDdc]
     * @return: void
     *
     */
    void updateDDCChannelId(BesDdc besDdc);

    /**
     *
     * @Description: 根据ip查询当前DDC控制器点是否存在
     *
     * @auther: wanghongjie
     * @date: 9:11 2020/7/15
     * @param: [ip]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectDDCPointMap(String ip);

    /**
     *
     * @Description: 根据id查询点位所属的点位表名
     *
     * @auther: wanghongjie
     * @date: 9:45 2020/7/15
     * @param: [id]
     * @return: java.lang.String
     *
     */
    String selectDDCPointTabNameMap(Integer id);

    /**
     *
     * @Description: 根据id和点位表名查询相应的点位表信息是否存在
     *
     * @auther: wanghongjie
     * @date: 9:48 2020/7/15
     * @param: [tabName, id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectPointByIdMap(@Param("tabName") String tabName,@Param("id") String id);

    /**
     *
     * @Description: 根据id和点位表名查询相应的虚点位表信息是否存在
     *
     * @auther: wanghongjie
     * @date: 10:31 2020/7/15
     * @param: [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectVPointByIdMap(@Param("id") String id);

    /**
     * 获取全部数据
     * @return
     */
    List<BesDdc> loadAll();

    /**
     * 根据系统名称获取控制器信息
     * @return
     */
    BesDdc selectBySysName(String sysName);
}