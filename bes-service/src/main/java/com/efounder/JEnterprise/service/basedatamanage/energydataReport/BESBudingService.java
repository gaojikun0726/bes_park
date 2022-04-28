package com.efounder.JEnterprise.service.basedatamanage.energydataReport;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @author  杨超
* @version 创建时间：2018年8月29日 下午3:49:43
* @parameter 
* @version 1.0
*/
public interface BESBudingService {

	PageInfo<BesBudingInformation> getBudingList(String keywords, Integer pageNum);
	
	/**
	 * 添加信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject add_BudingType (BesBudingInformation besbudinginformation);
	
	/**
	 * 删除信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject del_BudingType(String id);
	
	/**
	 * 查询需要编辑的信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject getBudingType(String bh);
	
	/**
	 * 编辑信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject edit_BudingType(BesBudingInformation besbudinginformation);
	
	/**
	 * 数据中心下拉列表
	 * @return
	 */
	public ISSPReturnObject selectsjzxList();
	/**
	 * 查询所有建筑物信息
	 * @return
	 */
	public ISSPReturnObject queryBuildingList();

	/**
	 * 刷新报警信息条数
	 * @return
	 */
	public ISSPReturnObject getBjxxCount();
	/**
	 * 生成模板
	 * @param request
	 * @return
	 */
	public ISSPReturnObject exportExcelModel(HttpServletRequest request);
	/**
	 * 导入
	 * @param request
	 * @param multipartFile
	 * @return
	 * @throws IOException 
	 */
	public ISSPReturnObject impExcel(HttpServletRequest request, MultipartFile multipartFile) throws IOException;
	/**
	 * 导出
	 * @param request
	 * @param dto
	 * @return
	 */
	public ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto);
}
