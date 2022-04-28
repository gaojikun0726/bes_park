package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.GljlMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.GljlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("GljlService")
public class GljlServiceImpl implements GljlService, ESBaseService {

    private static final Logger log = LoggerFactory.getLogger(GljlServiceImpl.class);

    @Autowired
    private GljlMapper gljlMapper;

    //根据条件加载管理记录日志信息
    public PageInfo<GljlModel> loadGljlxx(Integer bars, Integer pageNum, String keywords){
        if (pageNum == null)
            pageNum = 1;
        if (bars == null) {
            bars = Constants.PAGE_SIZE;
        }
        PageHelper.startPage(pageNum,bars );
        List<GljlModel> list = gljlMapper.loadGljlxx(keywords);
        // 用PageInfo对结果进行包装
        PageInfo<GljlModel> page = new PageInfo<GljlModel>(list);
        return page;
    }

    //操作后添加管理记录信息到管理记录表中
    @Override
    public boolean addGljlxx(GljlModel gljlxx) {
        log.info("#esSysLoginLogMapper添加日志信息");
        return gljlMapper.addGljlxx(gljlxx);
    }
}
