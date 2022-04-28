package com.efounder.JEnterprise.service.basedatamanage.energydataRecord;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energydataRecord.BesMeterAmmeter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @author  杨超
* @version 创建时间：2018年8月31日 上午10:08:06
* @parameter 
* @version 1.0
*/
public interface BESMeterService {
	
	
	public ISSPReturnObject meter_tree();
	
	//新增 静态电表信息
	public ISSPReturnObject add_MeterInformation(BesMeterAmmeter dto);
	
	//新增 静态电表数据
	public ISSPReturnObject add_MeterInfo(BesMeterAmmeter dto);
	
	//修改 保存
	public ISSPReturnObject edit_MeterInformation(BesMeterAmmeter dto);
	
	//修改 保存
	public ISSPReturnObject edit_MeterInfo(BesMeterAmmeter dto);
	
	//删除
	public ISSPReturnObject del_MeterInformation(String fGuid,String fJtguid,String lxqf);
	
	//修改回显
	public ISSPReturnObject editSelect_MeterInformation(String fGuid);
	
	//修改数据回显
	public ISSPReturnObject editSelect_MeterInfo(String fJtguid);
	
	//点击树 查询右侧table
	public ISSPReturnObject TreeTable(String treeId);
	
	//电表类型查询
	public ISSPReturnObject selectMeterType();
	/**
	 * 导出电表信息
	 * @param treeId
	 * @return
	 */
	public ISSPReturnObject exportBesMeter(HttpServletRequest request, String treeId);

	/**
	 * 导入excel数据
	 * @param request
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public ISSPReturnObject impExcel(HttpServletRequest request,
									 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException;

}
