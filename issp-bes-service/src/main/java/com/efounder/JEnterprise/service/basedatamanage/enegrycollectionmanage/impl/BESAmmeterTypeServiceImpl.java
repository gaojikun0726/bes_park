package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.AmmeterTypeDeleteMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESAmmeterTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAmmeterType;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESAmmeterTypeService;
import com.framework.common.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 电表类型接口实现类
 * @author LvSihan
 * @modify suhang	
 */
@Service("BESAmmeterTypeService")
public class BESAmmeterTypeServiceImpl implements BESAmmeterTypeService,ESBaseService{

	@Autowired
	private BESAmmeterTypeMapper besAmmeterTypeMapper;


	@Resource
	private AmmeterTypeDeleteMapper ammeterTypeDeleteMapper;

	@Autowired
	private Pzlj pzlj;// 获取配置文件路径
	@Override
	public PageInfo<BESAmmeterType> getAmmeterTypeList(String keywords, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BESAmmeterType> list = besAmmeterTypeMapper.getAmmeterTypeList(keywords);
		PageInfo<BESAmmeterType> page = new PageInfo<BESAmmeterType>(list);		
		return page;
	}


	@Override
	public ISSPReturnObject add_AmmeterType(BESAmmeterType besAmmeterType) {
	ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		boolean flag = besAmmeterTypeMapper.add_AmmeterType(besAmmeterType);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setData(besAmmeterType);
		} else {
			returnObject.setStatus("0");
		}
	} catch (Exception e) {
		e.printStackTrace();
		returnObject.setStatus("0");
		returnObject.setMsg("类型编号已存在！请重新输入");
	}
	return returnObject;
}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public ISSPReturnObject del_AmmeterType(String fLxbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//删除之前保存到软删除表
		Map<String,Object> map = new HashMap<>();
		map.put("fLxbh",fLxbh);
		Integer num = ammeterTypeDeleteMapper.insertAmmeterTypeDelete(map);
		boolean flag = besAmmeterTypeMapper.del_AmmeterType(fLxbh);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功！");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败！");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getAmmeterType(String fLxbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESAmmeterType selmo = besAmmeterTypeMapper.getAmmeterType(fLxbh);
			returnObject.setData(selmo);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject edit_AmmeterType(BESAmmeterType besAmmeterType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besAmmeterTypeMapper.edit_AmmeterType(besAmmeterType);
			returnObject.setData(flag);
			returnObject.setMsg("编辑成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("编辑失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	/**
	 * 文件上传后台接收方法
	 */
	@Override
	public ISSPReturnObject impExcel(HttpServletRequest request,
									 @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fold = request.getParameter("fold");
		String dayFold = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String realPath = pzlj.getUploadPath();// 文件保存时候的根路径
		// 转换为文件类型的request
		// 获取对应file对象
		if (multipartFile != null) {
			if (multipartFile.getSize() != 0L) {
				// 原始文件名称
				String pictureFile_name = multipartFile.getOriginalFilename();
				// uuid
				String UUID = UUIDUtil.getRandom32BeginTimePK();
				// 新文件名称
				String wjmc_url = UUID + pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
				// 文件路径
				String fileUrl = realPath + "/" + fold + "/" + dayFold + "/" + wjmc_url;
				// 上传文件
				File uploadPic = new File(fileUrl);
				if (!uploadPic.getParentFile().exists()) {
					uploadPic.getParentFile().mkdirs();// 创建父级目录
					uploadPic.createNewFile();// 创建文件
				}
				// 向磁盘写文件
				multipartFile.transferTo(uploadPic);
				// 初始化导入工具类
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(fileUrl);
					ExcelUtil<BESAmmeterType> util = new ExcelUtil<>(BESAmmeterType.class);
					List<BESAmmeterType> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
					// 获取要导出的数据
					List<ExcelError> excelErrors = new ArrayList<>();

					for(int i = 0 ;i < list.size(); i++)
					{
						boolean flag = true; //插入数据成功的标志
						BESAmmeterType besAmmeterType = list.get(i);
						String date = DateUtil.getCurrTime();
						String fCrdate = date;
						String fChdate = date;
						besAmmeterType.setfCrdate(fCrdate);
						besAmmeterType.setfChdate(fChdate);
						String errMsg = "";
						if(besAmmeterType.getfLxbh() == null || besAmmeterType.getfLxbh().equals(""))
						{
							errMsg = "电表类型编号为空";
						}
						else if(besAmmeterType.getfLxmc() == null || besAmmeterType.getfLxmc().equals(""))
						{
							if("".equals(errMsg))
							{
								errMsg = "电表类型名称为空";
								flag = false;
							}
							else
							{
								errMsg = errMsg+",电表类型名称为空";
								flag = false;
							}

						}

						if (!flag) {
							ExcelError excelError = new ExcelError();
							excelError.setRow((i+2)+"");
							excelError.setErrorMsg(errMsg);
							excelErrors.add(excelError);
						}
					}
					if(excelErrors.size() > 0)
					{
						returnObject.setMsg("导入数据过程中出现失误，请查看excel错误报告！");
						returnObject.setStatus("2");
						returnObject.setList(excelErrors);
						return returnObject;
					}
					else
					{
						boolean inportflag = false;
						for (BESAmmeterType besAmmeterType :list) {
							try {
								inportflag = besAmmeterTypeMapper.add_AmmeterType(besAmmeterType);
							} catch (Exception e) {
								returnObject.setMsg("导入失败！");
								returnObject.setStatus("0");
								e.printStackTrace();
							}
						}
						if(inportflag){
							returnObject.setMsg("导入成功！");
							returnObject.setStatus("1");
						}else{
							returnObject.setMsg("导入失败！");
							returnObject.setStatus("0");
						}
					}
					// 如果不需要验证 则可直接将list插入到数据库
				}  catch (FileNotFoundException e) {
					returnObject.setStatus("0");
					returnObject.setMsg("模板错误！");
					e.printStackTrace();
				} catch (NullPointerException e) {
					returnObject.setStatus("0");
					returnObject.setMsg("模板错误！");
					e.printStackTrace();
				}
			}
		} else {
			returnObject.setMsg("导入数据失败！");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 查询全部或新增的仪表类型数据列表
	 *
	 * @param map 上次请求时间（可选）
	 * @return
	 */
	@Override
	public List<Map> queryAmmeterTypeList(Map<String, Object> map) {

		return besAmmeterTypeMapper.queryAmmeterTypeList(map);
	}

}
