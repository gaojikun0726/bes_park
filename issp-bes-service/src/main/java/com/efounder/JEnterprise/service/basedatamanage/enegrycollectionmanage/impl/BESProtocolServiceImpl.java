package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESProtocolMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESProtocol;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESProtocolService;
import com.framework.common.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通信协议接口实现类
 * @author LvSihan
 * @modify suhang
 */
@Service("BESProtocolService")
public class BESProtocolServiceImpl implements BESProtocolService,ESBaseService{
	@Autowired
	private BESProtocolMapper besProtocolMapper;
	@Autowired
	private Pzlj pzlj;// 获取配置文件路径

	private static final Logger log = LoggerFactory.getLogger(BESProtocolServiceImpl.class);

	@Override
	public PageInfo<BESProtocol> getProtocolList(String keywords, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BESProtocol> list = besProtocolMapper.getProtocolList(keywords);
		PageInfo<BESProtocol> page = new PageInfo<BESProtocol>(list);	
		return page;
	}

	@Override
	public ISSPReturnObject insProtocol(BESProtocol besProtocol) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besProtocolMapper.insProtocol(besProtocol);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setData(besProtocol);
			} else {
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("编号已存在！请重新输入");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject delProtocol(String fTxxybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besProtocolMapper.delProtocol(fTxxybh);
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
	public ISSPReturnObject getProtocol(String fTxxybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESProtocol selmo = besProtocolMapper.getProtocol(fTxxybh);
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
	public ISSPReturnObject updProtocol(BESProtocol besProtocol) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besProtocolMapper.updProtocol(besProtocol);
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
	 *
	 * @param keyWords 关键字
	 * @return
	 */
	@Override
	public ISSPReturnObject exportProtocol(HttpServletRequest request,String keyWords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		// 创建工具类.
		ExcelUtil<BESProtocol> util = new ExcelUtil<>(BESProtocol.class);
        // 临时文件名
		String file = System.currentTimeMillis() + "";
		String FileName = "sheet";// sheet页名称
		String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
		// 获取要导出的数据
		List<BESProtocol> protocols = besProtocolMapper.getProtocolList(keyWords);
		ExcelReturn res = util.resList(FileName, FilePath, protocols);
		Map<String, String> map = new HashMap<>();
		map.put("status", res.getStatus());// 1.成功 0.失败
		map.put("file", file);
		returnObject.setMap(map);

		return returnObject;
	}



	/**
	 *
	 * @param errorString 错误信息
	 * @return
	 */
	@Override
	public ISSPReturnObject exportErrorExcel(HttpServletRequest request,String errorString) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		// 创建工具类.
		ExcelUtil<ExcelError> util = new ExcelUtil<>(ExcelError.class);
		// 临时文件名
		String file = System.currentTimeMillis() + "";
		String FileName = "sheet";// sheet页名称
		String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
		// 获取要导出的数据
		List<ExcelError> errors = JSONObject.parseArray(errorString, ExcelError.class);
		ExcelReturn res = util.resList(FileName, FilePath, errors);
		Map<String, String> map = new HashMap<>();
		map.put("status", res.getStatus());// 1.成功 0.失败
		map.put("file", file);
		returnObject.setMap(map);

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
					ExcelUtil<BESProtocol> util = new ExcelUtil<>(BESProtocol.class);
					List<BESProtocol> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
					// 获取要导出的数据
					List<ExcelError> excelErrors = new ArrayList<>();

					for(int i = 0 ;i < list.size(); i++)
					{
						boolean flag = true; //插入数据成功的标志
						BESProtocol besProtocol = list.get(i);
						String date = DateUtil.getCurrTime();
						String fCrdate = date;
						String fChdate = date;
						besProtocol.setfCrdate(fCrdate);
						besProtocol.setfChdate(fChdate);
						String errMsg = "";
						if(besProtocol.getfTxxybh() == null || besProtocol.getfTxxybh().equals(""))
						{
							errMsg = "通讯协议编号为空";
						}
						else if(besProtocol.getfType() == null || besProtocol.getfType().equals(""))
						{
							if("".equals(errMsg))
							{
								errMsg = "通讯协议类型为空";
								flag = false;
							}
							else
							{
								errMsg = errMsg+",通讯协议类型为空";
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
						for (BESProtocol besProtocol1 :list) {
							try {
								inportflag = besProtocolMapper.insProtocol(besProtocol1);
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
				}catch (FileNotFoundException e) {
					log.debug("模板错误！");
					returnObject.setStatus("0");
					returnObject.setMsg("模板错误！");
					e.printStackTrace();
				} catch (NullPointerException e) {
					log.debug("模板错误！");
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

}
