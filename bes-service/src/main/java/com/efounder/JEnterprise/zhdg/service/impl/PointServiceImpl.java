package com.efounder.JEnterprise.zhdg.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.zhdg.config.WeatherStatusEnum;
import com.efounder.JEnterprise.zhdg.dao.PointDao;
import com.efounder.JEnterprise.zhdg.entity.PointEntity;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Service("pointService")
public class PointServiceImpl implements PointService {
    @Autowired
    private PointDao dao;

    @Override
    public PageInfo<PointEntity> getSearch(String keywords, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<PointEntity> list = dao.getSearch(keywords);
        PageInfo<PointEntity> page = new PageInfo<PointEntity>(list);
        return page;
    }

    /**
     * 获取灯杆信息+气象状态实时数据
     *
     * @param keywords 关键字
     * @param pageNum  分页信息
     * @return
     */
    @Override
    public PageInfo<PointEntity> getSearchWithRealStatus(String keywords, Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<PointEntity> list = dao.getSearch(keywords);
        list.forEach(pointEntity -> {
            //添加实时气象状态数据
            //气象IP
            String ip = pointEntity.getMainboardIp();
            //气象状态
            String status = SebDynamicAgreeHandleServiceImpl.weatherStatusMap.get(ip);
            if(status == null){
                //默认离线
                status = WeatherStatusEnum.offline.getCode();
            }
            pointEntity.setFStatus(Integer.valueOf(status));
        });
        PageInfo<PointEntity> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 查询所有点位
     */
    @Override
    public List<PointEntity> selectSebPointList() {
        return dao.getSearch("");
    }

    /**
    * 新增
    */
    @Override
    public ISSPReturnObject add(PointEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            boolean flag = dao.add(dto);
            if (flag) {
                returnObject.setStatus("1");//1.成功
                returnObject.setMsg("添加成功");
            } else {
                returnObject.setStatus("0");//0.失败
                returnObject.setMsg("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus("0");
            returnObject.setMsg("数据异常！请重新输入");
        }
        return returnObject;
    }

    /**
    * 修改
    */
    @Override
    public ISSPReturnObject update(PointEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            int count = dao.update(dto);
            if (count > 0) {
                returnObject.setMsg("编辑成功");
                returnObject.setStatus("1");
            } else {
                returnObject.setMsg("编辑失败");
                returnObject.setStatus("0");
            }
        } catch (Exception e) {
            returnObject.setMsg("编辑失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
    * 删除
    */
    @Override
    public ISSPReturnObject delete(String id) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            int count = dao.delete(id);
            if (count > 0) {
                returnObject.setStatus("1");
                returnObject.setMsg("删除成功");
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("删除失败");
            }
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }

    /**
     * 查询唯一编码是否重复
     *
     * @param pointEntity
     * @return
     */
    @Override
    public Integer querySameCode(PointEntity pointEntity) {
        return dao.querySameCode(pointEntity);
    }

    /**
     * 查询IP地址是否重复
     *
     * @param pointEntity
     * @return
     */
    @Override
    public Integer querySameIp(PointEntity pointEntity) {
        return dao.querySameIp(pointEntity);
    }

    /**
    * 根据id查询具体参数
    */
    @Override
    public PointEntity getSearchById(String id) {
        return dao.getSearchById(id);
    }

    /**
     * 查询离线设备
     */
    @Override
    public List<PointEntity> GetofflinePointList() {
        return dao.GetofflinePointList();
    }

    /**
     * 修改数据库点位状态
     */
    @Override
    public void updatePointStatus(List<String> offlineList) {
        dao.updatePointStatus(offlineList);
    }

    /**
     * 修改点位报警状态
     */
    @Override
    public void updatePointsWarnStatus(List<String> newWarnList) {
        dao.updatePointsWarnStatus(newWarnList);
    }

    /**
     * 修改点位在线状态
     */
    @Override
    public void updatePointsStatus(List<String> fCode) {
        dao.updatePointsStatus(fCode);
    }

    /**
     * 修改所有点位状态-离线
     */
    @Override
    public void updateAllPointsStatus() {
        dao.updateAllPointsStatus();
    }

    /**
     * 根据fCode查询具体参数
     */
    @Override
    public PointEntity getSearchByFcode(String id) {
        return dao.getSearchByFcode(id);
    }

    /**
     * 根据大屏编码查询灯杆开关编码
     *
     * @param screenCode
     * @return
     */
    @Override
    public String queryCodeByScreenCode(String screenCode) {
        String code = "";
        List<String> list =  dao.queryCodeByScreenCode(screenCode);
        if(list != null && list.size() > 0){
            code = list.get(0);
        }
        return code;
    }

    /**
     * 根据显示屏IP地址查询关联的PDU IP地址
     *
     * @param screenIp 显示屏IP地址
     * @return
     */
    @Override
    public String queryPduIpByScreenIp(String screenIp) {
        String ip = "";
        List<String> list =  dao.queryPduIpByScreenIp(screenIp);
        if(list != null && list.size() > 0){
            ip = list.get(0);
        }
        return ip;
    }

    /**
     * 根据PDU IP地址查询关联的显示屏IP地址
     *
     * @param pduIp PDU IP地址
     * @return
     */
    @Override
    public String queryScreenIpByPduIp(String pduIp) {
        String ip = "";
        List<String> list =  dao.queryScreenIpByPduIp(pduIp);
        if(list != null && list.size() > 0){
            ip = list.get(0);
        }
        return ip;
    }

    /**
     * 查询所有的PDU_IP
     *
     * @return
     */
    @Override
    public List<String> queryAllPduIp() {
        return dao.queryAllPduIp();
    }

    /**
     * 查询所有气象的ip、状态数据
     *
     * @return
     */
    @Override
    public Map<String, String> getWeatherData() {
        Map<String, String> map = new ConcurrentHashMap<>();
        List<Map> list = dao.getWeatherData();
        list.forEach(itemMap -> {
            String ip = Optional.ofNullable(itemMap.get("MAINBOARD_IP")).orElse("").toString();
//            String status = Optional.ofNullable(itemMap.get("F_STATUS")).orElse("").toString();
            //默认初始状态全部为离线
            String status = WeatherStatusEnum.offline.getCode();
            map.put(ip,status);
        });
        return map;
    }

    /**
     * 保存所有气象的ip、状态数据
     *
     * @param map
     * @return
     */
    @Override
    public void saveWeatherStatus(Map<String, String> map) {
//        if(!ObjectUtils.isEmpty(map)){
        if(map != null && map.size() > 0){
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("map",map);
            dao.saveWeatherStatus(objectMap);
        }
    }
}
