package com.efounder.JEnterprise.zhdg.service.impl;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.zhdg.dao.DynamicagreeDao;
import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.efounder.JEnterprise.zhdg.entity.DynamicagreeEntity;
import com.efounder.JEnterprise.zhdg.service.DynamicagreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("dynamicagreeService")
public class DynamicagreeServiceImpl implements DynamicagreeService {
    @Autowired
    private DynamicagreeDao dao;
    @Autowired
    private ISebDynamicAgreeHandleService sebDynamicAgreeHandleService;

    @Override
    public PageInfo<DynamicagreeEntity> getSearch(String keywords, Integer pageNum) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DynamicagreeEntity> list = dao.getSearch(keywords);
        PageInfo<DynamicagreeEntity> page = new PageInfo<DynamicagreeEntity>(list);
        return page;
    }

    /**
    * 新增
    */
    @Override
    public ISSPReturnObject add(DynamicagreeEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            boolean flag = dao.add(dto);
            if (flag) {
                returnObject.setStatus("1");//1.成功
                returnObject.setMsg("添加成功");
                sebDynamicAgreeHandleService.reload();
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
    public ISSPReturnObject update(DynamicagreeEntity dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            int count = dao.update(dto);
            if (count > 0) {
                returnObject.setMsg("编辑成功");
                sebDynamicAgreeHandleService.reload();
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
                //重新加载动态协议配置
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
    public DynamicagreeEntity getSearchById(String id) {
        return dao.getSearchById(id);
    }
}
