package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.platform.mapper.OpmPositionInfoMapper;
import com.efounder.JEnterprise.platform.model.OpmPositionInfo;
import com.efounder.JEnterprise.platform.service.IOpmPositionInfoService;
import com.efounder.JEnterprise.platform.util.List2Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 区域位置信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-29
 */
@Service
public class OpmPositionInfoServiceImpl implements IOpmPositionInfoService
{
    @Resource
    private OpmPositionInfoMapper opmPositionInfoMapper;

    /**
     * 电表信息数据接口
     */
    @Resource
    private BESAmmeterMapper besAmmeterMapper;

    /**
     * 查询区域位置树
     *
     * @return
     */
    @Override
    public List<Map> queryPositionTree() {
        return opmPositionInfoMapper.queryPositionTree();
    }

    /**
     * 查询区域位置类型数据
     *
     * @return
     */
    @Override
    public List<Map> queryPositionType() {
        return opmPositionInfoMapper.queryPositionType();
    }

    /**
     * 查询已关联电表数据
     * @param map 参数
     * @return
     */
    @Override
    public List<Map> queryContainAmmeter(Map<String,Object> map) {
        return opmPositionInfoMapper.queryContainAmmeter(map);
    }


    /**
     * 查询已关联电表数据
     * @param map 参数
     * @return
     */
    @Override
    public List<Map> queryRemainAmmeter(Map<String,Object> map) {
        return opmPositionInfoMapper.queryRemainAmmeter(map);
    }

    /**
     * 添加电表房间关联数据
     *
     * @param map 参数
     * @return
     */
    @Override
    public ISSPReturnObject addPositionAmmeterConfig(Map<String, Object> map) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        Integer num = opmPositionInfoMapper.addPositionAmmeterConfig(map);
        if(num > 0){
            isspReturnObject.setStatus("1");
        }
        return isspReturnObject;
    }

    /**
     * 删除电表房间关联数据
     *
     * @param map 参数
     * @return
     */
    @Override
    public ISSPReturnObject deletePositionAmmeterConfig(Map<String, Object> map) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        Integer num = opmPositionInfoMapper.deletePositionAmmeterConfig(map);
        if(num > 0){
            isspReturnObject.setStatus("1");
        }
        return isspReturnObject;
    }

    /**
     * 按条件查询未关联电表并关联到房间
     *
     * @param map 参数
     * @return
     */
    @Override
    public ISSPReturnObject addRelativeByCondition(Map<String, Object> map) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        List<Map> list = opmPositionInfoMapper.queryRemainAmmeter(map);
        map.put("list",list);
        Integer num = opmPositionInfoMapper.addPositionAmmeterConfig(map);
        if(num > 0){
            isspReturnObject.setStatus("1");
        }
        return isspReturnObject;
    }

    /**
     * 按条件查询已关联电表并移除关联
     *
     * @param map 参数
     * @return
     */
    @Override
    public ISSPReturnObject deleteRelativeByCondition(Map<String, Object> map) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        List<Map> list = opmPositionInfoMapper.queryContainAmmeter(map);
        map.put("list",list);
        Integer num = opmPositionInfoMapper.deletePositionAmmeterConfig(map);
        if(num > 0){
            isspReturnObject.setStatus("1");
        }
        return isspReturnObject;
    }

    /**
     * 查询列表
     *
     * @param map        参数
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @return
     */
    @Override
    public Map queryList(Map<String, Object> map, Integer pageNumber, Integer pageSize) {
        Map<String,Object> result = new HashMap<>(2);

        PageHelper.startPage(pageNumber,pageSize);
        List<Map> list = opmPositionInfoMapper.queryList(map);
        PageInfo<Map> page = new PageInfo<>(list);

        result.put("rows",list);
        result.put("total",page.getTotal());
        result.put("code",0);
        result.put("data",list);
        result.put("count",page.getTotal());
        return result;
    }

    /**
     * 查询区域位置信息
     *
     * @param id 区域位置信息ID
     * @return 区域位置信息
     */
    @Override
    public OpmPositionInfo selectOpmPositionInfoById(String id)
    {
        return opmPositionInfoMapper.selectOpmPositionInfoById(id);
    }

    /**
     * 查询区域位置信息列表
     *
     * @param opmPositionInfo 区域位置信息
     * @return 区域位置信息
     */
    @Override
    public List<OpmPositionInfo> selectOpmPositionInfoList(OpmPositionInfo opmPositionInfo)
    {
        return opmPositionInfoMapper.selectOpmPositionInfoList(opmPositionInfo);
    }

    /**
     * 新增区域位置信息
     *
     * @param opmPositionInfo 区域位置信息
     * @return 结果
     */
    @Override
    public int insertOpmPositionInfo(OpmPositionInfo opmPositionInfo)
    {
        return opmPositionInfoMapper.insertOpmPositionInfo(opmPositionInfo);
    }

    /**
     * 修改区域位置信息
     *
     * @param opmPositionInfo 区域位置信息
     * @return 结果
     */
    @Override
    public int updateOpmPositionInfo(OpmPositionInfo opmPositionInfo)
    {
        return opmPositionInfoMapper.updateOpmPositionInfo(opmPositionInfo);
    }

    /**
     * 删除区域位置信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOpmPositionInfoByIds(String ids)
    {
        return opmPositionInfoMapper.deleteOpmPositionInfoByIds(ids.split(","));
    }

    /**
     * 删除区域位置信息信息
     *
     * @param id 区域位置信息ID
     * @return 结果
     */
    @Override
    public int deleteOpmPositionInfoById(String id)
    {
        return opmPositionInfoMapper.deleteOpmPositionInfoById(id);
    }

    /**
     * 查询全部或新增的仪表数据列表
     *
     * @param map 上次请求时间（可选）
     * @return
     */
    @Override
    public List<Map<String,Object>> queryAmmeterList(Map<String, Object> map) {
        List<Map<String,Object>> list = besAmmeterMapper.getAmmeterList(map);
        List<Map> configList = opmPositionInfoMapper.getRoomAmmeterConfigData(map);
        Map<String,List<String>> configMap = getRoomAmmeterConfigMap(configList);
        for(Map itemMap :list){
            String guid = itemMap.get("guid") == null ? "" : itemMap.get("guid").toString();
            List<String> roomIdList = configMap.get(guid);
            itemMap.put("positionIdList",roomIdList);
        }
        return list;
    }

    /**
     * 获取全部或新增的房间仪表抄表数据
     *
     * @param lastTime 上次请求时间（可选）
     * @return
     */
    @Override
    public List<Map> getRoomAmmeterData(Long lastTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("lastTime",lastTime);
        List<Map> dayList = opmPositionInfoMapper.getDayDataFromTime(map);
        if(lastTime != null){
            List<Map> tillList = opmPositionInfoMapper.getReadDataTillTime(map);
            Map<String,Object> tillMap = List2Map.list2Map(tillList,"meterGuid","readNum");
            for(Map itemMap : dayList){
                String meterGuid = itemMap.get("meterGuid") == null ? "" : itemMap.get("meterGuid").toString();
                String readNumStr = itemMap.get("readNum") == null ? "0" : itemMap.get("readNum").toString();
                String tillNumStr = tillMap.get(meterGuid) == null ? "0" : tillMap.get(meterGuid).toString();

                BigDecimal readNum = new BigDecimal(readNumStr);
                BigDecimal tillNum = new BigDecimal(tillNumStr);
                BigDecimal useNum = readNum.subtract(tillNum);
                //四舍五入，2位小数
                double consumption = useNum.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                itemMap.put("consumption",consumption);
            }
        }

        return dayList;
    }

    /**
     * 获取全部或新增的房间仪表抄表数据
     *
     * @param map 上次请求时间（可选）
     * @return
     */
    @Override
    public List<Map> getReadDataList(Map<String, Object> map) {
        return opmPositionInfoMapper.getReadDataList(map);
    }

    /**
     * 查询全部仪表或单个仪表某天的抄表数据
     *
     * @param map 时间戳，仪表主键（可选）
     * @return
     */
    @Override
    public List<Map> queryMeterDayData(Map<String, Object> map) {
        return opmPositionInfoMapper.queryMeterDayData(map);
    }

    /**
     * 查询所有的房间与仪表的关联关系数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getRoomAmmeterConfigData(Map<String, Object> map) {
        return opmPositionInfoMapper.getRoomAmmeterConfigData(map);
    }

    /**
     * 转换房间与仪表的关联关系数据格式
     *
     * @param list 房间与仪表的关联关系数据
     * @return
     */
    @Override
    public Map<String,List<String>> getRoomAmmeterConfigMap(List<Map> list) {
        Map<String,List<String>> resultMap = new HashMap<>();
        for(Map map : list){
            String roomId = map.get("room_id") == null ? "" : map.get("room_id").toString();
            String ammeterId = map.get("ammeter_id") == null ? "" : map.get("ammeter_id").toString();
            List<String> stringList = resultMap.get(ammeterId);
            if(stringList == null){
                stringList = new ArrayList<>();
            }
            stringList.add(roomId);
            resultMap.put(ammeterId,stringList);
        }
        return resultMap;
    }

    /**
     * 处理接口获取的数据
     *
     * @param jsonArray 房间列表数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer handleAccessData(JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        map.put("list",jsonArray);
        //删除数据
        deleteAllPosition();
        //保存数据
        Integer num = insertPositionList(map);
        return num;
    }

    /**
     * 新增房间列表数据--保存接口获取的数据
     *
     * @param map 房间列表数据
     * @return
     */
    @Override
    public Integer insertPositionList(Map<String, Object> map) {
        return opmPositionInfoMapper.insertPositionList(map);
    }

    /**
     * 保存接口数据前，删除所有的原有数据
     *
     * @return
     */
    @Override
    public Integer deleteAllPosition() {
        return opmPositionInfoMapper.deleteAllPosition();
    }



    /**
     * 获取全部或新增的房间仪表抄表数据
     *
     * @param map 上次请求时间（可选）
     * @return
     */
    @Override
    public List<Map> getReadDataListNew(Map<String, Object> map) {
        return opmPositionInfoMapper.getReadDataListNew(map);
    }


    /**
     * 查询全部仪表或单个仪表某天的抄表数据
     *
     * @param map 时间戳，仪表主键（可选）
     * @return
     */
    @Override
    public List<Map> queryMeterDayDataNew(Map<String, Object> map) {
        return opmPositionInfoMapper.queryMeterDayDataNew(map);
    }
}
