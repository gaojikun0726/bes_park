package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESModulePointTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModulePointType;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESModulePointTypeService;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.OperationLog;
import com.framework.common.utils.Validate_y;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("besModulePointTypeService")
public class BESModulePointTypeServiceImpl implements BESModulePointTypeService {

	private static final Logger log = LoggerFactory.getLogger(BESModulePointTypeServiceImpl.class);

	@Autowired
	private BESModulePointTypeMapper besModulePointTypeMapper;
	@Autowired
	private OperationConfig operationConfig;
	@Autowired
	private Pzlj pzlj;


	@Override
	public PageInfo<BESModulePointType> selModulepointTypepage(Integer pageNum,Integer bars, String keywords) {
		log.info("模块点类型分页查询");

		if (pageNum == null)
			pageNum = 1;
		if (bars == null)
			bars = Constants.PAGE_SIZE;
		PageHelper.startPage(pageNum,bars);
		// 紧跟着的第一个select方法会被分页
		List<BESModulePointType> pageList = besModulePointTypeMapper.selModulepointTypepage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<BESModulePointType> page = new PageInfo<BESModulePointType>(pageList);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	@Override
	public ISSPReturnObject delModulepointType(String fId) {
		log.info("#删除模块点类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//删除之前查询是否有外键约束，关联的外键数据是否存在
			Integer num = besModulePointTypeMapper.queryRelationType(fId);
			if(num > 0){
				returnObject.setStatus("0");
				returnObject.setMsg("该模块点类型与设备树节点类型存在关联，无法删除！");
			}else{
				if("1".equals(operationConfig.getSysDataBaseUseable())){
					OperationLog.delete(fId, "bes_modulepoint_type");
				}
				boolean flag = besModulePointTypeMapper.delModulepointType(fId);
				if (flag) {
					returnObject.setStatus("1");
					returnObject.setMsg("删除成功！");
				} else {
					returnObject.setStatus("0");
					returnObject.setMsg("删除失败！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject insModulepointType(BESModulePointType besModulePointType) {
		log.info("添加模块点类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean isSucc = besModulePointTypeMapper.insModulepointType(besModulePointType);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(besModulePointType.getfId(), "bes_modulepoint_type");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setData(besModulePointType);
			} else {
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg(e.getMessage());
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject selectModulepointType(String fId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESModulePointType selmo = besModulePointTypeMapper.selectModulepointType(fId);
			returnObject.setData(selmo);
			returnObject.setMsg("查询模块点类型成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询模块点类型失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject updfModulepointType(BESModulePointType besModulePointType) {
		log.info("更新模块点类型信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = true;
			Map<String, Object> startMap = null;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(besModulePointType.getfId(), "bes_modulepoint_type");
				flag = besModulePointTypeMapper.updfModulepointType(besModulePointType);
				OperationLog.updateEnd(besModulePointType.getfId(), "bes_modulepoint_type", startMap);
			}
			flag = besModulePointTypeMapper.updfModulepointType(besModulePointType);
			if (flag) {
				returnObject.setData(besModulePointType);
				returnObject.setStatus("1");
				returnObject.setMsg("更新成功！");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	* @Author:         YangChao
	* @CreateDate:     2019/1/25 10:59
	* @Description:    导入后台验证
	*/
    @Override
    public ISSPReturnObject impExcel(HttpServletRequest request, MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fold = request.getParameter("fold");
		String dayFold = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 文件保存时候的根路径
		String realPath = pzlj.getUploadPath();
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
//				multipartFile.transferTo(uploadPic);
				//写入指定文件夹
				OutputStream out = new FileOutputStream(uploadPic);
				out.write(multipartFile.getBytes());
				// 初始化导入工具类
				FileInputStream fis = null;
				// 初始化存放数据list
				List<BESModulePointType> list = new ArrayList<>();
				List<ExcelError> errorList = new ArrayList<>();
				try {
					fis = new FileInputStream(fileUrl);
					ExcelUtil<BESModulePointType> util = new ExcelUtil<BESModulePointType>(BESModulePointType.class);
					// 导入excel,处理后生成list
					list = util.importExcel("sheet1", fis);
					// 处理导入数据 判断验证
					log.debug("\r 打印导入数据:" + list);
					//实例化实体类list
					List<BESModulePointType> besmodulepointtypeList = new ArrayList<>();
					// 如果不需要验证 则可直接将list插入到数据库
					for(int i =0;i<list.size();i++){
						// 初始化实体类
						BESModulePointType besmodulepointtype = new BESModulePointType();
						// 初始化存放错误信息实体类
						ExcelError err = new ExcelError();
						//模块点类型
						String fModulepointType = list.get(i).getfModulepointType();
						//模块点描述
						String fDescription = list.get(i).getfDescription();
						// 时间
						String date = DateUtil.getCurrTime();
						String fCrdate = date;
						String fChdate = date;
						String uuid = UUIDUtil.getRandom32BeginTimePK();
						besmodulepointtype.setfId(uuid);
						besmodulepointtype.setfDescription(fDescription);
						besmodulepointtype.setfModulepointType(fModulepointType);
						besmodulepointtype.setfCrdate(fCrdate);
						besmodulepointtype.setfChdate(fChdate);
						besmodulepointtypeList.add(besmodulepointtype);
						if(Validate_y.isNull(fModulepointType)||Validate_y.isNull(fDescription)){
							err.setRow((i+2)+"");
							err.setErrorMsg("必填项不可为空！");
							errorList.add(err);
						}
					}
					log.debug("newlist:"+besmodulepointtypeList);
					log.debug("错误信息打印:"+errorList);
					//判断错误信息
					boolean flag = false;
					if(errorList.isEmpty()){
						for (BESModulePointType map :besmodulepointtypeList) {
							flag = besModulePointTypeMapper.insModulepointType(map);
						}
						if(flag){
							returnObject.setMsg("导入成功！");
							returnObject.setStatus("1");
						}else{
							returnObject.setMsg("导入失败！");
							returnObject.setStatus("0");
						}
					}else{
						//将错误信息放到list返回到前台--只有status==2 才导出错误报告
						returnObject.setStatus("2");
						returnObject.setMsg("导入数据过程中出现错误，请查看excel错误报告！");
						returnObject.setList(errorList);
					}

				} catch (FileNotFoundException e) {
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
			returnObject.setMsg("文件上传失败！");
			returnObject.setStatus("0");
		}
		return returnObject;
    }


}
