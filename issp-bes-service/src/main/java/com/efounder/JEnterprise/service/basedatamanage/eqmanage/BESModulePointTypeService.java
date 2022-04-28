package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModulePointType;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BESModulePointTypeService {
	

	/**
	 * 模块点类型分页信息
	 * 
	 * @param pageNum , keywords
	 * @return
	 */
	PageInfo<BESModulePointType> selModulepointTypepage(Integer pageNum,Integer bars, String keywords);
	
	/**
	 * 删除模块点类型信息
	 * 
	 * @param fId
	 * @return
	 */
	ISSPReturnObject delModulepointType(String fId);
	
	/**
	 * 添加模块点类型信息
	 * 
	 * @param besModulePointType
	 * @return
	 */
	ISSPReturnObject insModulepointType(BESModulePointType besModulePointType); 
	
	/**
	 * 查询单行列表用于回显
	 * 
	 * @param fId
	 * @return
	 */
	ISSPReturnObject selectModulepointType(String fId);
	
	/**
	 * 更新模块点类型信息
	 * 
	 * @param besModulePointType
	 * @return
	 */
	ISSPReturnObject updfModulepointType(BESModulePointType besModulePointType);

	/**
	* @Author:         YangChao
	* @CreateDate:     2019/1/23 16:58
	* @Description:    文件上传后台接收方法
	*/
    ISSPReturnObject impExcel(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception;
}
