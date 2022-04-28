package com.efounder.JEnterprise.zhdg.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.zhdg.entity.PointEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-08-27 18:36:45
 */
public interface PointService {

    PageInfo<PointEntity> getSearch(String keywords, Integer pageNum);

    /**
     * 获取灯杆信息+气象状态实时数据
     * @param keywords 关键字
     * @param pageNum 分页信息
     * @return
     */
    PageInfo<PointEntity> getSearchWithRealStatus(String keywords, Integer pageNum);

    /**
     * 查询所有点位
     */
    public List<PointEntity>  selectSebPointList();

    /**
	 * 添加信息
	 */
    public ISSPReturnObject add(PointEntity dto);

    /**
	 * 更新信息
	 */
    public ISSPReturnObject update(PointEntity dto);

    /**
	 * 删除
	 */
    public ISSPReturnObject delete(String id);

    /**
     * 查询唯一编码是否重复
     * @param pointEntity
     * @return
     */
    Integer querySameCode(PointEntity pointEntity);


    /**
     * 查询IP地址是否重复
     * @param pointEntity
     * @return
     */
    Integer querySameIp(PointEntity pointEntity);

    /**
    * 根据id查询具体参数
    */
    public PointEntity getSearchById(String id);

    /**
     * 查询离线设备
     */
    public List<PointEntity> GetofflinePointList();

    /**
     * 修改数据库点位状态--离线
     */
    public void updatePointStatus(List<String> offlineList);

    /**
     * 修改点位报警状态
     */
    public void updatePointsWarnStatus(List<String> newWarnList);

    /**
     * 修改点位在线状态
     */
    public void updatePointsStatus(List<String> fCode);

    /**
     * 修改所有点位状态-离线
     */
    public void updateAllPointsStatus();

    /**
     * 根据fCode查询具体参数
     */
    public PointEntity getSearchByFcode(String id);


    /**
     * 根据大屏编码查询灯杆开关编码
     * @return
     */
    String queryCodeByScreenCode(String screenCode);

    /**
     * 根据显示屏IP地址查询关联的PDU IP地址
     * @param screenIp 显示屏IP地址
     * @return
     */
    String queryPduIpByScreenIp(String screenIp);


    /**
     * 根据PDU IP地址查询关联的显示屏IP地址
     * @param pduIp PDU IP地址
     * @return
     */
    String queryScreenIpByPduIp(String pduIp);

    /**
     * 查询所有的PDU_IP
     * @return
     */
    List<String> queryAllPduIp();


    /**
     * 查询所有气象的ip、状态数据
     * @return
     */
    Map<String,String> getWeatherData();

    /**
     * 保存所有气象的ip、状态数据
     * @param map
     * @return
     */
    void saveWeatherStatus(Map<String,String> map);
}

