package com.efounder.JEnterprise.service.monitorConsole;

import com.core.common.ISSPReturnObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @CkassName: vidiconService
 * @Author: YangChao
 * @Date: 2019/3/28 15:23
 * @Descruotuib: 监控台摄像机接口
 * @Version: 1.0
 **/
public interface vidiconService {

    ISSPReturnObject getLeftTree();

    ISSPReturnObject getData(HttpServletRequest request);

    ISSPReturnObject getVidiconList(HttpServletRequest request);

}
