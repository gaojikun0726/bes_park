package com.efounder.JEnterprise.controller.scopedata;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.scopedata.ScopeDataModel;
import com.efounder.JEnterprise.service.scopedata.ScopeDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据权限
 * @author xiepufeng
 * @date 2021/1/5 14:10
 */
@RestController
@RequestMapping(value = "/view/scopeData")
public class ScopeDataController
{

    @Resource
    private ScopeDataService scopeDataService;

    /**
     * 查询数据权限
     * @param request
     * @return
     */
    @GetMapping("/query")
    public ISSPReturnObject query(ScopeDataModel request)
    {
        return scopeDataService.query(request);
    }

    /**
     * 更新数据权限
     * @param request
     * @return
     */
    @PostMapping("/update")
    public ISSPReturnObject update(@RequestBody ScopeDataModel request)
    {
        return scopeDataService.update(request);
    }
}
