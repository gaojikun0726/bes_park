package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAmmeterType;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 电表类型接口
 * @author LvSihan
 * @modify suhang
 */
public interface BESAmmeterTypeService {

	PageInfo<BESAmmeterType> getAmmeterTypeList(String keywords, Integer pageNum);
	
	
	/**
	 * 添加电表信息
	 * @param besAmmeterType
	 * @return
	 */
	public ISSPReturnObject add_AmmeterType (BESAmmeterType besAmmeterType);
	
	/**
	 * 删除电表信息
	 * @param fLxbh
	 * @return
	 */
	public ISSPReturnObject del_AmmeterType(String fLxbh);
	
	/**
	 * 查询需要编辑的电表信息
	 * @param fLxbh
	 * @return
	 */
	public ISSPReturnObject getAmmeterType(String fLxbh);
	
	/**
	 * 编辑电表信息
	 * @param besAmmeterType
	 * @return
	 */
	public ISSPReturnObject edit_AmmeterType(BESAmmeterType besAmmeterType);

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
	 * 查询全部或新增的仪表类型数据列表
	 * @param map 上次请求时间（可选）
	 * @return
	 */
	List<Map> queryAmmeterTypeList(Map<String,Object> map);
}
