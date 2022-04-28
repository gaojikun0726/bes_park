package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Date:2020/8/31 15:10
 */
@Mapper
public interface BesceneConfigMapper {

    /**
     * 获取场景配置树机构
     * @return
     */
    List<BeSceneInfo> getSceneTree();

    /**
     * 获取最大Id
     * @return
     */
    String querySceneInfoMaxId();

    /**
     * 新增一个场景
     * @param beSceneInfo
     * @return
     */
    Boolean insertSceneInfo(BeSceneInfo beSceneInfo);

    /**
     * 修改场景
     * @param beSceneInfo
     * @return
     */
    Boolean editSceneInfo(BeSceneInfo beSceneInfo);

    /**
     * 删除一个场景
     * @param id
     * @return
     */
    Boolean deleteSceneInfo(String id);

    /**
     * 查询table内指令信息
     * @param id
     * @return
     */
    Map<String,Object> queryTableParam(String id);

    List<Map<String,Object>> queryModeParamById(String id);


    /**
     * 获取场景最大Id
     * @return
     */
    String querySceneModeMaxId();

    /**
     * 添加zone数据
     * @param beSceneMode
     * @return
     */
    Boolean insertSceneMode(BeSceneMode beSceneMode);

    Boolean updateSceneMode(BeSceneMode beSceneMode);

    List<Map<String,Object>> selectAllModePoint(@Param("sceneId") String sceneId,@Param("modeId") String modeId);

    List<Object> selectBeSceneModeByInfoId(String f_sceneInfoId);

    Boolean insertSceneValue(BeScenePoint beScenePoint);

    List<Map<String,Object>> selectPublicScenePoint(String f_sceneInfoId);

    String selectModeIdByModeName(String modename);

    String selectRepeatModeName(String modename);

    Map<String,Object> selectRepeatModeId(String fId);

    Boolean insertBeScenePoint(BeScenePoint scenePoint);

    Boolean saveSceneInfomation(JSONObject obj);

    Boolean insertDefaultMode(BeSceneMode beSceneMode);

    List<Map<String,Object>> selectModeInfoBySceneId(String sceneId);

    Boolean deleteScenemodeInfomation(String modeId);

    Boolean deleteScenepointInfomation(String modeId);

    Boolean deleteSceneModeBySceneId(String sceneId);

    Boolean deleteScenePointBySceneId(String sceneId);

    List<Map<String,Object>> selectScenePointBySceneId(String id);

    List<Map<String,Object>> selectScenePointBySceneAndPointId(@Param("modeId") String modeId,@Param("sceneId") String sceneId);

    List<Map<String,Object>> selectModeIdBySceneId(String sceneId);

    /**
     *
     * @Description: 获取照明点位的ip地址
     *
     * @auther: wanghongjie
     * @date: 16:03 2021/8/14
     * @param: [f_id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String,Object> queryChannelByIdLDC(String f_id);

    /**
     *
     * @Description: 获取楼控点位的ip地址
     *
     * @auther: wanghongjie
     * @date: 16:03 2021/8/14
     * @param: [f_id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String,Object> queryChannelByIdDDC(String f_id);

    List<Map<String,Object>> selectModeListById(String id);

    Map<String,Object> selectSceneInfoBySceneId(String sceneId);

    Map<String,Object> selectModeInfoByModeId(String modeId);

    Boolean insertIntoSynchroState(@Param("f_synchro") String f_synchro,@Param("modeId") String modeId);

    Map<String,Object> selectSchroStateById(String modeId);

    /**
     *
     * @Description: 从数据库中查询出全部”场景模式定义“数据
     *
     * @auther: wanghongjie
     * @date: 14:36 2021/8/9
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDiPoint>
     *
     */
    List<BeSceneMode> loadSceneModeAll();

    /**
     *
     * @Description: 点位关联模式定义
     *
     * @auther: wanghongjie
     * @date: 15:09 2021/8/9
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint>
     *
     */
    List<BeScenePoint> loadScenePointAll();

    /**
     *
     * @Description: 首先删除 bes_scenepoint 点位关联模式表中的相关点位信息
     *
     * @auther: wanghongjie
     * @date: 9:33 2021/8/10
     * @param: [modeId]
     * @return: boolean
     *
     */
    boolean deleteScenepointByScenemodeId(String modeId);

    /**
     *
     * @Description: 获取别的场景下所有的点位
     *
     * @auther: wanghongjie
     * @date: 16:13 2021/8/12
     * @param: [f_sceneInfoId, f_pointType]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> selectPointMap(@Param("f_sceneInfoId") String f_sceneInfoId,@Param("f_pointType") String f_pointType);

    /**
     *
     * @Description: 根据模式点位表的id修改点位的值和单位
     *
     * @auther: wanghongjie
     * @date: 10:21 2021/8/19
     * @param: [stringObjectMap]
     * @return: boolean
     *
     */
    boolean updateScenepointValue(Map<String, Object> stringObjectMap);

    /**
     *
     * @Description: 根据模式id查询模式点位表中的数据
     *
     * @auther: wanghongjie
     * @date: 10:49 2021/8/19
     * @param: [modeId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> scenePointList(String modeId);
}
