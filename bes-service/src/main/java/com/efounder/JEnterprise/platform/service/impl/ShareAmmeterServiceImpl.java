package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.platform.mapper.ShareAmmeterMapper;
import com.efounder.JEnterprise.platform.service.ShareAmmeterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 公摊仪表
 *
 * @author zs
 * @date 2020/11/9
 */
@Service
public class ShareAmmeterServiceImpl implements ShareAmmeterService {

    @Resource
    private ShareAmmeterMapper shareAmmeterMapper;

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
        List<Map> list = shareAmmeterMapper.queryList(map);
        PageInfo<Map> page = new PageInfo<>(list);

        result.put("rows",list);
        result.put("total",page.getTotal());
        result.put("code",0);
        result.put("data",list);
        result.put("count",page.getTotal());
        return result;
    }

    /**
     * 配置公摊仪表
     *
     * @param meterIds 仪表id
     * @return
     */
    @Override
    public ISSPReturnObject shareMeterConfig(String[] meterIds) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        if(meterIds != null && meterIds.length > 0){
            Map<String,Object> map = new HashMap<>();
            map.put("array",meterIds);
            //查询电表是否已关联房间
            List<Map> list = shareAmmeterMapper.queryRelativeAmmeter(map);
            if(list != null && list.size() > 0){
                isspReturnObject.setMsg("存在"+list.size() + "条数据已关联房间，请重新选择");
            }else{
                Integer num = shareAmmeterMapper.shareMeterConfig(map);
                if(num > 0){
                    isspReturnObject.setStatus("1");
                }
            }
        }
        return isspReturnObject;
    }

    /**
     * 配置普通仪表
     *
     * @param meterIds 仪表id
     * @return
     */
    @Override
    public ISSPReturnObject plainMeterConfig(String[] meterIds) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        if(meterIds != null && meterIds.length > 0){
            Map<String,Object> map = new HashMap<>();
            map.put("array",meterIds);
            Integer num = shareAmmeterMapper.plainMeterConfig(map);
            if(num > 0){
                isspReturnObject.setStatus("1");
            }
        }
        return isspReturnObject;
    }

    /**
     * 查询是否全部是公摊仪表或普通仪表
     *
     * @param meterIds   仪表id
     * @param meterState 仪表状态
     * @return
     */
    @Override
    public ISSPReturnObject queryMeterState(JSONArray meterIds, String meterState) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        isspReturnObject.setStatus("0");
        if(meterIds != null && meterIds.size() > 0){
            Map<String,Object> map = new HashMap<>();
            map.put("array",meterIds);
            map.put("meterState",meterState);
            Integer num = shareAmmeterMapper.queryMeterState(map);
            if(num == meterIds.size()){
                //全部为公摊仪表或普通仪表
                isspReturnObject.setStatus("1");
            }else{
                isspReturnObject.setNum(meterIds.size() - num);
            }
        }
        return isspReturnObject;
    }

    /**
     * 查询仪表类型数据
     *
     * @return
     */
    @Override
    public List<Map> queryAmmeterType() {
        return shareAmmeterMapper.queryAmmeterType();
    }
}
