package com.efounder.JEnterprise.zhdg.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.zhdg.dao.PointwarnDao;
import com.efounder.JEnterprise.zhdg.entity.PointwarnEntity;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.service.PointwarnService;
import com.efounder.JEnterprise.zhdg.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pointwarnService")
public class PointwarnServiceImpl implements PointwarnService {
    @Autowired
    private PointwarnDao dao;

    @Autowired
    private ISebDynamicAgreeHandleService sebDynamicAgreeHandleService;

    @Override
    public PageInfo<PointwarnEntity> getSearch(String pointid,String warnid, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<PointwarnEntity> list = dao.getSearch(pointid,warnid);
        PageInfo<PointwarnEntity> page = new PageInfo<PointwarnEntity>(list);
        return page;
    }

    /**
    * 新增
    */
    @Override
    public ISSPReturnObject add(PointwarnEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        /** 编写逻辑--批量新增 */
        /**1.0--查询当前报警类型是否存在 */
        long warnid = dto.getWarnid();
        String [] pointIdList = dto.getPointid().split(",");
        List<String> warnTypeList = dao.getWarnTypeConunt(warnid,pointIdList);
        if (warnTypeList.size() > 0) {//
            StringBuilder sb = new StringBuilder();
            for(String str : warnTypeList){
                sb.append(str).append(",");
            }
            returnObject.setStatus("0");//0.失败
            returnObject.setMsg("设备id为"+sb.toString().substring(0,sb.toString().length()-1)+"的当前报警类型已存在");
        } else {
            /** 拼接插入sql */
            StringBuilder sb = new StringBuilder();
            for (String pointId : pointIdList) {
                if (StringUtils.isNotEmpty(pointId)) {
                    sb.append("(")
                            .append(pointId)
                            .append(",")
                            .append(dto.getWarnid())
                            .append(",")
                            .append(dto.getVtype())
                            .append(",")
                            .append(dto.getVtype0())
                            .append(",")
                            .append(dto.getVtype1Min())
                            .append(",")
                            .append(dto.getVtype1Max())
                            .append("),");
                }
            }
            String pinSql = sb.toString().substring(0,sb.toString().length()-1);
            int warnint = dao.insertSebPointwarn(pinSql);
            if (warnint > 0) {
                returnObject.setStatus("1");//1.成功
                returnObject.setMsg("添加成功");
                //重新加载点位报警配置数据
                sebDynamicAgreeHandleService.reload();
            } else {
                returnObject.setStatus("0");//0.失败
                returnObject.setMsg("添加失败");
            }
        }
        return returnObject;
    }

    /**
    * 修改
    */
    @Override
    public ISSPReturnObject update(PointwarnEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            int count = dao.update(dto);
            if (count > 0) {
                returnObject.setMsg("编辑成功");
                returnObject.setStatus("1");
                //重新加载点位报警配置数据
                sebDynamicAgreeHandleService.reload();
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
                //重新加载点位报警配置数据
                sebDynamicAgreeHandleService.reload();
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
    * 根据id查询具体参数
    */
    @Override
    public PointwarnEntity getSearchById(String id) {
        return dao.getSearchById(id);
    }

    @Override
    public List<PointwarnEntity> selectSebPointwarnList(PointwarnEntity pointwarnEntity) {
        return dao.selectSebPointwarnList(pointwarnEntity);
    }
}
