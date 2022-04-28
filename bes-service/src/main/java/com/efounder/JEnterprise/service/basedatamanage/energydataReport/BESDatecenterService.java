package com.efounder.JEnterprise.service.basedatamanage.energydataReport;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 数据中心接口
 * @author 杨超
 * @modify ycc
 */
public interface BESDatecenterService {
	
PageInfo<BESDatecenterType> getDatecenterList(String keywords, Integer pageNum);
	
	
	/**
	 * 添加信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject add_Datecenter (BESDatecenterType besdatecentertype);
	
	/**
	 * 删除信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject del_Datecenter(String id);
	
	/**
	 * 查询需要编辑的信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject getDatecenter(String bh);
	
	/**
	 * 编辑信息
	 * @param 
	 * @return
	 */
	public ISSPReturnObject edit_Datecenter(BESDatecenterType besdatecentertype);
	
	public ISSPReturnObject selectfNybhList();

	/**
	 * 生成模板
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
