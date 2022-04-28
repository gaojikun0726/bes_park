package com.efounder.JEnterprise.platform.mapper;

import com.efounder.JEnterprise.platform.model.OpmPositionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 区域位置信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-29
 */
@Mapper
public interface OpmPositionInfoMapper
{
    /**
     * 查询区域位置信息
     *
     * @param id 区域位置信息ID
     * @return 区域位置信息
     */
    public OpmPositionInfo selectOpmPositionInfoById(String id);

    /**
     * 查询区域位置信息列表
     *
     * @param opmPositionInfo 区域位置信息
     * @return 区域位置信息集合
     */
    public List<OpmPositionInfo> selectOpmPositionInfoList(OpmPositionInfo opmPositionInfo);

    /**
     * 新增区域位置信息
     *
     * @param opmPositionInfo 区域位置信息
     * @return 结果
     */
    public int insertOpmPositionInfo(OpmPositionInfo opmPositionInfo);

    /**
     * 修改区域位置信息
     *
     * @param opmPositionInfo 区域位置信息
     * @return 结果
     */
    public int updateOpmPositionInfo(OpmPositionInfo opmPositionInfo);

    /**
     * 删除区域位置信息
     *
     * @param id 区域位置信息ID
     * @return 结果
     */
    public int deleteOpmPositionInfoById(String id);

    /**
     * 批量删除区域位置信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOpmPositionInfoByIds(String[] ids);


    /**
     * 获取全部或新增的房间仪表抄表数据
     * @param map 上次请求时间（可选）
     * @return
     */
    List<Map> getRoomAmmeterData(Map<String,Object> map);


    /**
     * 查询所有的房间与仪表的关联关系数据
     * @param map
     * @return
     */
    List<Map> getRoomAmmeterConfigData(Map<String,Object> map);

    /**
     * 查询区域位置树
     * @return
     */
    List<Map> queryPositionTree();


    /**
     * 查询区域位置类型数据
     * @return
     */
    List<Map> queryPositionType();


    /**
     * 查询列表
     *
     * @param map 参数
     * @return
     */
    List<Map> queryList(Map<String, Object> map);


    /**
     * 查询已关联电表数据
     * @param map 参数
     * @return
     */
    List<Map> queryContainAmmeter(Map<String,Object> map);

    /**
     * 查询未关联电表数据
     * @param map 参数
     * @return
     */
    List<Map> queryRemainAmmeter(Map<String,Object> map);



    /**
     * 添加电表房间关联数据
     * @param map 参数
     * @return
     */
    Integer addPositionAmmeterConfig(Map<String,Object> map);

    /**
     * 删除电表房间关联数据
     * @param map 参数
     * @return
     */
    Integer deletePositionAmmeterConfig(Map<String,Object> map);


//    /**
//     * 按条件查询未关联电表并关联到房间
//     * @param map 参数
//     * @return
//     */
//    Integer addRelativeByCondition(Map<String,Object> map);
//
//    /**
//     * 按条件查询已关联电表并移除关联
//     * @param map 参数
//     * @return
//     */
//    Integer deleteRelativeByCondition(Map<String,Object> map);

    /**
     * 求采集时间从上次请求时间往后的每个电表每天的最后一条记录
     * @param map
     * @return
     */
    List<Map> getDayDataFromTime(Map<String,Object> map);


    /**
     * 查询上次请求的数据中每个电表读取时间最大的那条记录
     * @param map
     * @return
     */
    List<Map> getReadDataTillTime(Map<String,Object> map);



    /**
     * 获取全部或新增的房间仪表抄表数据
     * @param map 上次请求时间（可选）
     * @return
     */
    List<Map> getReadDataList(Map<String,Object> map);


    /**
     * 查询全部仪表或单个仪表某天的抄表数据
     * @param map 时间戳，仪表主键（可选）
     * @return
     */
    List<Map> queryMeterDayData(Map<String,Object> map);


    /**
     * 新增房间列表数据--保存接口获取的数据
     * @param map 房间列表数据
     * @return
     */
    Integer insertPositionList(Map<String,Object> map);

    /**
     * 保存接口数据前，删除所有的原有数据
     * @return
     */
    Integer deleteAllPosition();


    /**
     * 获取全部或新增的房间仪表抄表数据
     * @param map 上次请求时间（可选）
     * @return
     */
    List<Map> getReadDataListNew(Map<String,Object> map);

    /**
     * 查询全部仪表或单个仪表某天的抄表数据
     * @param map 时间戳，仪表主键（可选）
     * @return
     */
    List<Map> queryMeterDayDataNew(Map<String,Object> map);

}
