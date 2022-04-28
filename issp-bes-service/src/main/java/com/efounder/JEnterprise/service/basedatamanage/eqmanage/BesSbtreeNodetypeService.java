package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 设备树节点类型service
 * @author suhang
 * @datetime 2018-7-11
 */
public interface BesSbtreeNodetypeService {
	
	PageInfo<BESSbTreeNodeType> selBesSbtreeNodetypepage(Integer pageNum,Integer bars, String keywords);

	ISSPReturnObject insBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType);

	ISSPReturnObject delBesSbtreeNodetype(String f_node_type);

	ISSPReturnObject selectBesSbtreeNodetype(String f_node_type);

	ISSPReturnObject updateBesSbtreeNodetype(BESSbTreeNodeType besSbTreeNodeType);

	ISSPReturnObject impExcel(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception;


}
