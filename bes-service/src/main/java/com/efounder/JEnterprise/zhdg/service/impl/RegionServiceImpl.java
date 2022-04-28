package com.efounder.JEnterprise.zhdg.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.zhdg.dao.RegionDao;
import com.efounder.JEnterprise.zhdg.entity.RegionEntity;
import com.efounder.JEnterprise.zhdg.service.RegionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("regionService")
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao dao;

    @Override
    public PageInfo<RegionEntity> getSearch(String keywords, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<RegionEntity> list = dao.getSearch(keywords);
        PageInfo<RegionEntity> page = new PageInfo<RegionEntity>(list);
        return page;
    }

    /**
     * 下拉列表查询
     */
    @Override
    public ISSPReturnObject getRegionSearch() {
        ISSPReturnObject res = new ISSPReturnObject();
        List<RegionEntity> list = dao.getSearch("");
        res.setList(list);
        return res;
    }

    /**
    * 新增
    */
    @Override
    public ISSPReturnObject add(RegionEntity dto) {
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
    public ISSPReturnObject update(RegionEntity dto) {
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
    * 根据id查询具体参数
    */
    @Override
    public RegionEntity getSearchById(String id) {
        return dao.getSearchById(id);
    }
}