package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESProtocol;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 通信协议接口
 * @author LvSihan
 * @modify suhang
 */
public interface BESProtocolService {

	PageInfo<BESProtocol> getProtocolList(String keywords, Integer pageNum);
	
	/**
	 * 添加通信协议信息
	 * @param besProtocol
	 * @return
	 */
	public ISSPReturnObject insProtocol (BESProtocol besProtocol);
	
	/**
	 * 删除通信协议信息
	 * @param fTxxybh
	 * @return
	 */
	public ISSPReturnObject delProtocol(String fTxxybh);
	
	/**
	 * 查询需要编辑的通信协议信息
	 * @param fTxxybh
	 * @return
	 */
	public ISSPReturnObject getProtocol(String fTxxybh);
	
	/**
	 * 编辑通信协议信息
	 * @param besProtocol
	 * @return
	 */
	public ISSPReturnObject updProtocol(BESProtocol besProtocol);

	/**
	 * 导出通信协议信息
	 * @param keyWords
	 * @return
	 */
	public ISSPReturnObject exportProtocol(HttpServletRequest request, String keyWords);

	/**
	 * 导入excel数据
	 * @param request
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public ISSPReturnObject impExcel(HttpServletRequest request,
									 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException;

	/**
	 * 导出含有错误信息的excel
	 * @param request
	 * @param errorString 错误信息
	 * @return
	 */
	public ISSPReturnObject exportErrorExcel(HttpServletRequest request, String errorString);

}
