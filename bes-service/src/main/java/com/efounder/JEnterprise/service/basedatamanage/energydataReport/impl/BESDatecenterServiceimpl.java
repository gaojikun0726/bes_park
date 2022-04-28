package com.efounder.JEnterprise.service.basedatamanage.energydataReport.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.energydataReport.BESDatecenterMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BESDatecenterType;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energydataReport.BESDatecenterService;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.Validate_y;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("BESDatecenterService")
public class BESDatecenterServiceimpl implements BESDatecenterService,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESDatecenterServiceimpl.class);
	@Autowired
	private BESDatecenterMapper besdatecentermapper;
	
	@Autowired
    private Pzlj pzlj;// 获取配置文件路径
	
	@Override
	public PageInfo<BESDatecenterType> getDatecenterList(String keywords, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BESDatecenterType> list = besdatecentermapper.getDatecenterList(keywords);
		PageInfo<BESDatecenterType> page = new PageInfo<BESDatecenterType>(list);
		return page;
	}

	@Override
	public ISSPReturnObject add_Datecenter(BESDatecenterType BESDatecenterType) {
	ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		String F_DATACENTER_ID=UUIDUtil.getRandom32BeginTimePK();
		BESDatecenterType.setF_DATACENTER_ID(F_DATACENTER_ID);
		boolean flag = besdatecentermapper.add_Datecenter(BESDatecenterType);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setData(BESDatecenterType);
		} else {
			returnObject.setStatus("0");
		}
	} catch (Exception e) {
		e.printStackTrace();
		returnObject.setStatus("0");
		returnObject.setMsg("数据异常！请重新输入");
	}
	return returnObject;
}

	@Override
	public ISSPReturnObject del_Datecenter(String id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besdatecentermapper.del_Datecenter(id);
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
	public ISSPReturnObject getDatecenter(String bh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESDatecenterType selmo = besdatecentermapper.getDatecenter(bh);
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
	public ISSPReturnObject edit_Datecenter(BESDatecenterType BESDatecenterType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besdatecentermapper.edit_Datecenter(BESDatecenterType);
			PageHelper.startPage(1, Constants.PAGE_SIZE);
			List<BESDatecenterType> list = besdatecentermapper.getDatecenterList("");
			PageInfo<BESDatecenterType> page = new PageInfo<BESDatecenterType>(list);
			String jsonString = JSONObject.toJSONString(page.getList());
			returnObject.setData(jsonString);
			returnObject.setMsg("编辑成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("编辑失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 获取类型编号
	 */
	@Override
	public ISSPReturnObject selectfNybhList() {
			ISSPReturnObject returnObject = new ISSPReturnObject();	
			try {
				List<Map<String,Object>> list = besdatecentermapper.getDatecenterTypeList(null);
				returnObject.setList(list);
				returnObject.setMsg("查询类型成功");
				returnObject.setStatus("1");
			} catch (Exception e) {
				returnObject.setMsg("查询类型失败");
				returnObject.setStatus("0");
			}
			return returnObject;
	}
	/**
	 * 生成模板
	 */
	@Override
	public ISSPReturnObject exportExcelModel(HttpServletRequest request) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		// 创建工具类.
        ExcelUtil<BESDatecenterType> util = new ExcelUtil<BESDatecenterType>(BESDatecenterType.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
//      实体类导出
        List<BESDatecenterType> datecenterType = new ArrayList<BESDatecenterType>();
        BESDatecenterType dt = new BESDatecenterType();
        dt.setF_DATACENTER_CODE("125");//
        dt.setF_DATACENTER_NAME("济南中心");
        dt.setF_DATACENTER_IP("192.168.0.1");
        dt.setF_DATACENTER_PORT("1204");
        dt.setLXMC("数据中转站");
        dt.setF_GOVERNING_BODY("正晨科技");
        dt.setF_CONTACTPERSON("张三");
        dt.setF_PHONE("18812345555");
        dt.setF_DATACENTER_DESCRIBE("描述内容123");
        datecenterType.add(dt);
//        ExcelReturn res = util.resListDynamic(FileName, FilePath, null, alias, null, null);
      ExcelReturn res = util.resList(FileName, FilePath, datecenterType);//实体类导出
        Map<String, String> map = new HashMap<>();
        map.put("status", res.getStatus());// 1.成功 0.失败
        map.put("file", file);
        returnObject.setMap(map);
		return returnObject;
	}
	/**
	 * 导入
	 * @throws IOException 
	 */
	@Override
	public ISSPReturnObject impExcel(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException {
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
//                multipartFile.transferTo(uploadPic);
				//写入指定文件夹
				OutputStream out = new FileOutputStream(uploadPic);
				out.write(multipartFile.getBytes());
                // 初始化导入工具类
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(fileUrl);
                    ExcelUtil<BESDatecenterType> util = new ExcelUtil<BESDatecenterType>(BESDatecenterType.class);
                    List<BESDatecenterType> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
                    log.debug("\r 打印导入数据:" + list);
                    List<ExcelError> errorList = new ArrayList<>();
                    int i =0;
                    //生成主键+校验
                    for(BESDatecenterType datecenter : list) {                   	
                    	// 初始化存放错误信息实体类
						ExcelError err = new ExcelError();
						
                    	datecenter.setF_DATACENTER_ID(UUIDUtil.getRandom32BeginTimePK());  
                    	if(Validate_y.isNull(datecenter.getF_DATACENTER_CODE())||Validate_y.isNull(datecenter.getF_DATACENTER_NAME())){
							err.setRow((i+2)+"");
							err.setErrorMsg("必填项不可为空！");
							errorList.add(err);
						}
                    	switch(datecenter.getLXMC()){
                        case "数据中心":
                        	datecenter.setF_DATACENTER_TYPE("1");break;
                        case "数据中转站":
                        	datecenter.setF_DATACENTER_TYPE("2");break;  
                        default:
                        	break; 
                        }    
                    	i++;
                    }
                    if(errorList.isEmpty()){
                    	boolean flag = besdatecentermapper.addDatecenterList(list);
                        if(flag) {
                        	returnObject.setMsg("文件上传成功！");
                            returnObject.setStatus("1");
                        }else {
                        	returnObject.setMsg("文件上传失败！");
                            returnObject.setStatus("0");
                        }
					}else{
						//将错误信息放到list返回到前台--只有status==2 才导出错误报告
						returnObject.setStatus("2");
						returnObject.setMsg("导入数据过程中出现错误，请查看excel错误报告！");
						returnObject.setList(errorList);
					}                                      
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }                               
            }
        } else {
            returnObject.setMsg("文件上传失败！");
            returnObject.setStatus("0");
        }
        return returnObject;
	}
	/**
	 * 导出
	 */
	@Override
	public ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto) {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		// 创建工具类.
        ExcelUtil<BESDatecenterType> util = new ExcelUtil<BESDatecenterType>(BESDatecenterType.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
//      实体类导出
        List<BESDatecenterType> datecenterType = besdatecentermapper.getDatecenterList("");       
        ExcelReturn res = util.resList(FileName, FilePath, datecenterType);//实体类导出
        Map<String, String> map = new HashMap<>();
        map.put("status", res.getStatus());// 1.成功 0.失败
        map.put("file", file);
        returnObject.setMap(map);
		return returnObject;
	}
	
}
