package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BesRate;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 通信波特率Service 
 * @author suhang
 *	@date 2018-7-27
 */
public interface BesRateService {

	/**
	 * 分页
	 * @param keywords
	 * @param pageNum
	 * @return
	 */
	PageInfo<BesRate> besRatePage(String keywords, Integer pageNum);

	/**
	 * 添加
	 * @param besRate
	 * @return
	 */
	public ISSPReturnObject insBesRate(BesRate besRate);

	/**
	 * 删除
	 * @param fRateBh
	 * @return
	 */
	public ISSPReturnObject delBesRate(String fRateBh);

	/**
	 * 查询
	 * @param fRateBh
	 * @return
	 */
	public ISSPReturnObject selBesRate(String fRateBh);

	/**
	 * 更新
	 * @param besRate
	 * @return
	 */
	public ISSPReturnObject updBesRate(BesRate besRate);

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
	 * 查重
	 * @param fRateBh
	 * @param fCommRate
	 * @return
	 */
	public ISSPReturnObject checkRepeat(String fRateBh,String fCommRate);

	/**
	 * 查重
	 * @param fCommRate
	 * @return
	 */
	public ISSPReturnObject checkEditRepeat(String fCommRate);

}
