package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BesRateMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BesRate;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BesRateService;
import com.framework.common.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 通信波特率ServiceImpl
 * @author suhang
 * @date 2018-7-27	
 */
@Service("besRateService")
public class BesRateServiceImpl implements BesRateService {

	@Autowired
	private BesRateMapper besrateMapper;

	@Autowired
	private Pzlj pzlj;// 获取配置文件路径
	@Override
	public PageInfo<BesRate> besRatePage(String keywords, Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BesRate> list = besrateMapper.besRatePage(keywords);
		PageInfo<BesRate> page = new PageInfo<BesRate>(list);
		return page;
	}

	@Override
	public ISSPReturnObject insBesRate(BesRate besRate) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besrateMapper.insBesRate(besRate);
			if (flag) {
				returnObject.setData(besRate);
				returnObject.setStatus("1");
			} else {
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("添加失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject delBesRate(String fRateBh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besrateMapper.delBesRate(fRateBh);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject selBesRate(String fRateBh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		BesRate selbesrate = besrateMapper.selBesRate(fRateBh);
		try {
			returnObject.setData(selbesrate);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("查询失败");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject updBesRate(BesRate besRate) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besrateMapper.updBesRate(besRate);
		try {
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("编辑成功");
				returnObject.setData(besRate);
			} else {
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("编辑失败");
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
					ExcelUtil<BesRate> util = new ExcelUtil<>(BesRate.class);
					List<BesRate> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
					// 获取要导出的数据
					List<ExcelError> excelErrors = new ArrayList<>();

					for(int i = 0 ;i < list.size(); i++)
					{
						boolean flag = true; //插入数据成功的标志
						BesRate besRate = list.get(i);
						String date = DateUtil.getCurrTime();
						String fCrdate = date;
						String fChdate = date;
						besRate.setfCrdate(fCrdate);
						besRate.setfChdate(fChdate);
						String errMsg = "";
						if(besRate.getfRateBh() == null || besRate.getfCommRate().equals(""))
						{
							errMsg = "通讯波特率编号为空";
						}
						else if(besRate.getfCommRate() == null || besRate.getfCommRate().equals(""))
						{
							if("".equals(errMsg))
							{
								errMsg = "通讯波特率为空";
								flag = false;
							}
							else
							{
								errMsg = errMsg+",通讯波特率为空";
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
						for (BesRate besRate :list) {
							try {
								inportflag = besrateMapper.insBesRate(besRate);
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
				} catch (FileNotFoundException e) {
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
	 *通信波特率查重
	 * @param fRateBh
	 * @param fCommRate
	 * @return
	 */
	@Override
	public ISSPReturnObject checkRepeat(String fRateBh, String fCommRate) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		int fRateBhType = besrateMapper.checkRepeatFRateBh(fRateBh);

		int fCommRateType = besrateMapper.checkRepeatFCommRate(fCommRate);

		if (fRateBhType>0){

			returnObject.setData("1");//错误有重复字段

		}else {
			returnObject.setData("0");
		}


		if (fCommRateType>0){
			returnObject.setMsg("1");//错误有重复字段
		}else {
			returnObject.setMsg("0");
		}

		return returnObject;
	}
	/**
	 *通信波特率查重
	 * @param fRateBh
	 * @param fCommRate
	 * @return
	 */
	@Override
	public ISSPReturnObject checkEditRepeat(String fCommRate) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		int editeFCommRateType = besrateMapper.checkRepeatFCommRate(fCommRate);

		if (editeFCommRateType>0){

			returnObject.setData("1");//错误有重复字段

		}else {
			returnObject.setData("0");
		}
		return returnObject;
	}
}
