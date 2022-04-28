package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.GljlModel;
import com.github.pagehelper.PageInfo;

public interface GljlService {

    //根据条件加载管理记录日志信息
    PageInfo<GljlModel> loadGljlxx(Integer bars, Integer pageNum, String keywords);

    //操作后添加管理记录信息到管理记录表中
    boolean addGljlxx(GljlModel gljlxx);
}
