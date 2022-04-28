package com.efounder.JEnterprise.service.basedatamanage.energydataReport.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.energydataReport.BesBudingInformationMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energydataReport.BESBudingService;
import com.framework.common.utils.ExcelUtil;
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
import java.util.*;

/**
* @author  杨超
* @version 创建时间：2018年8月29日 下午3:51:22
* @parameter 
* @version 1.0
*/
@Service("BESBudingService")
public class BESBudingServiceimpl implements BESBudingService,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESBudingServiceimpl.class);
	@Autowired
	private BesBudingInformationMapper besbudinginformationmapper;
	@Autowired
	private Pzlj pzlj;
	
	@Override
	public PageInfo<BesBudingInformation> getBudingList(String keywords, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
//		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		PageHelper.startPage(pageNum, 15);
		List<BesBudingInformation> list = besbudinginformationmapper.getBudingList(keywords);
		PageInfo<BesBudingInformation> page = new PageInfo<BesBudingInformation>(list);
		return page;
	}
	
	@Override
	public ISSPReturnObject add_BudingType(BesBudingInformation besbudinginformation) {
	ISSPReturnObject returnObject = new ISSPReturnObject();
	try {
		String fBudingId=UUIDUtil.getRandom32BeginTimePK();
		besbudinginformation.setfBudingId(fBudingId);
		boolean flag = besbudinginformationmapper.add_BudingType(besbudinginformation);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setData(besbudinginformation);
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
	public ISSPReturnObject del_BudingType(String id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besbudinginformationmapper.del_BudingType(id);
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
	public ISSPReturnObject getBudingType(String bh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BesBudingInformation selmo = besbudinginformationmapper.getBudingType(bh);
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
	public ISSPReturnObject edit_BudingType(BesBudingInformation besbudinginformation) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean flag = besbudinginformationmapper.edit_BudingType(besbudinginformation);
			returnObject.setMsg("编辑成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("编辑失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 获取数据中心下拉框编号
	 */
	@Override
	public ISSPReturnObject selectsjzxList() {
			ISSPReturnObject returnObject = new ISSPReturnObject();	
			try {
				List<Map<String,Object>> list = besbudinginformationmapper.getSjzxTypeList();
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
	 * 查询所有建筑物信息
	 */
	@Override
	public ISSPReturnObject queryBuildingList() {
			ISSPReturnObject returnObject = new ISSPReturnObject();
			try {
				List<Map<String,Object>> list = besbudinginformationmapper.queryBuildingList();
				returnObject.setList(list);
				returnObject.setMsg("查询成功");
				returnObject.setStatus("1");
			} catch (Exception e) {
				returnObject.setMsg("查询失败");
				returnObject.setStatus("0");
			}
			return returnObject;
	}

	/**
	 * 刷新报警信息条数
	 */
	@Override
	public ISSPReturnObject getBjxxCount() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, Object> map= besbudinginformationmapper.getBjxxCount();
			returnObject.setData(map.get("BjxxCount"));
			returnObject.setStatus("1");
		} catch (Exception e) {
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
        ExcelUtil<BesBudingInformation> util = new ExcelUtil<BesBudingInformation>(BesBudingInformation.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
//      实体类导出
        List<BesBudingInformation> buildingInfoList = new ArrayList<BesBudingInformation>();
        BesBudingInformation buildingInfo = new BesBudingInformation();
        buildingInfo.setfBudingCode("370103B016");//建筑代码
        buildingInfo.setfCenterId("1");//数据中心id
        buildingInfo.setfBudingName("联合财富广场");//建筑名称
        buildingInfo.setfBudingNode("");//节点编码
        buildingInfo.setfBudingLetter("370103B016");//建筑字母别名
        buildingInfo.setfBudingOwner("张三");//建筑业主
        buildingInfo.setfBudingOwnerCompany("齐鲁交通信息集团");//业主单位
        buildingInfo.setfCountyArea("历下区");//所属县区
        buildingInfo.setfBudingContact("李四");//建筑 联系人
        buildingInfo.setfContactInformation("15112341234");//联系方式
        buildingInfo.setfBudingDetection("启用检测");//建筑检测状态
        buildingInfo.setfBudingAddress("舜华路");//建筑地址
        buildingInfo.setfBudingState("建成");//建筑状态
        buildingInfo.setfDateCompletion("2018年");//竣工年份
        buildingInfo.setfBudingDate("2018年");//建筑年代
        buildingInfo.setfBudingLongitude("10.32");//建筑坐标 经度
        buildingInfo.setfBudingLatitude("52.36");//建筑坐标 纬度
        buildingInfo.setfBudingLayerson("26");//地上建筑层数
        buildingInfo.setfBudingLayersunder("2");//地下建筑层数
        buildingInfo.setfBudingHeight("630m");//建筑高度
        buildingInfo.setfBudingFunction("办公建筑");//建筑功能
        buildingInfo.setfBudingArea("10000");//建筑面积
        buildingInfo.setfAirconditionerArea("9000");//空调面积
        buildingInfo.setfHeatingArea("9000");//采暖面积
        buildingInfo.setfBudingYnrs("1000");//用能人数
        buildingInfo.setfHeatingMode("集中供热");//供热方式
        buildingInfo.setfCoolingMode("集中供冷");//供冷方式
        buildingInfo.setfAirconditionerSystem("集中式全空气系统");//空调系统形式
        buildingInfo.setfHeatingSystem("空调系统集中供暖");//采暖系统形式
        buildingInfo.setfBudingCoefficient("5");//建筑体形系数
        buildingInfo.setfArchitecturalForm("框架结构");//建筑结构形式
        buildingInfo.setfExteriorwallForm("砖");//外墙材料形式
        buildingInfo.setfExteriorwallInsulationForm("内保温");//外墙保温形式
        buildingInfo.setfBudingExteriorType("中空双层玻璃窗");//建筑外窗类型
        buildingInfo.setfBudingGlassType("镀膜玻璃");//建筑玻璃类型
        buildingInfo.setfMaterialType("钢窗");//材料类型
        buildingInfo.setfSavingStandard("节能50%标准");//执行节能标准
        buildingInfo.setfSavingTransformation("是");//是否节能改造
        buildingInfo.setfBudingGovernment("否");//是否政府办公楼
        buildingInfo.setfBenchmarking("否");//是否标杆建筑
        buildingInfo.setfSchemeDesign("ASD");//方案设计方案
        buildingInfo.setfOperatorName("王五");//创建操作人名称
        buildingInfo.setfEngineeringConstruction("正晨科技");//工程施工单位
        buildingInfo.setfEngineeringContacts("小红");//工程联系人
        buildingInfo.setfEngineeringContactsBgdh("926385");//工程联系人 办公电话
        buildingInfo.setfEngineeringContactsYddh("15198769876");//工程联系人 移动电话
        buildingInfo.setfBudingPopulation("1000");//建筑总人口
        buildingInfo.setfMonitoringArea("10000");//监测面积
        buildingInfo.setfSystemSoftware("BES");//系统软件
        buildingInfo.setfCollectorType("BES200");//采集器型号
        buildingInfo.setfEntryTime("2019-01-25 09:44:00");//录入时间
        buildingInfo.setfDetectionTime("2019-01-25 09:44:00");//开始检测日期
        buildingInfo.setfAcceptanceTime("2018-01-25 09:44:00");//工程验收日期
        buildingInfo.setfUpdateTime("2019-01-25 09:44:00");//修改时间
        buildingInfo.setJCZTMC("合格");//检测状态名称

        buildingInfoList.add(buildingInfo);
        ExcelReturn res = util.resList(FileName, FilePath, buildingInfoList);//实体类导出
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
	public ISSPReturnObject impExcel(HttpServletRequest request, MultipartFile multipartFile) throws IOException {
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
                    ExcelUtil<BesBudingInformation> util = new ExcelUtil<BesBudingInformation>(BesBudingInformation.class);
                    List<BesBudingInformation> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
                    log.debug("\r 打印导入数据:" + list);
                    List<ExcelError> errorList = new ArrayList<>();
                    int i =0;
                    //生成主键+校验
                    for(BesBudingInformation buildingInfo : list) {   
                    	// 初始化存放错误信息实体类
						ExcelError err = new ExcelError();
                    	buildingInfo.setfBudingId(UUIDUtil.getRandom32BeginTimePK());  
                    	//文本转code
                    	text2code(list);
                    	if(Validate_y.isNull(buildingInfo.getfBudingCode())||Validate_y.isNull(buildingInfo.getfCenterId())
								||Validate_y.isNull(buildingInfo.getfBudingName())||Validate_y.isNull(buildingInfo.getfBudingLetter())
								||Validate_y.isNull(buildingInfo.getfBudingOwner())||Validate_y.isNull(buildingInfo.getfBudingDetection())
								||Validate_y.isNull(buildingInfo.getfBudingNode())||Validate_y.isNull(buildingInfo.getfBudingAddress())
								||Validate_y.isNull(buildingInfo.getfBudingLongitude())||Validate_y.isNull(buildingInfo.getfBudingLatitude())
								||Validate_y.isNull(buildingInfo.getfBudingLayerson())||Validate_y.isNull(buildingInfo.getfBudingLayersunder())
                    			||Validate_y.isNull(buildingInfo.getfBudingFunction())||Validate_y.isNull(buildingInfo.getfBudingArea())
								||Validate_y.isNull(buildingInfo.getfAirconditionerArea())||Validate_y.isNull(buildingInfo.getfHeatingArea())
								||Validate_y.isNull(buildingInfo.getfAirconditionerSystem())||Validate_y.isNull(buildingInfo.getfHeatingSystem())
								||Validate_y.isNull(buildingInfo.getfBudingCoefficient())||Validate_y.isNull(buildingInfo.getfArchitecturalForm())
								||Validate_y.isNull(buildingInfo.getfExteriorwallForm())||Validate_y.isNull(buildingInfo.getfExteriorwallInsulationForm())
								||Validate_y.isNull(buildingInfo.getfBudingExteriorType())||Validate_y.isNull(buildingInfo.getfBudingGlassType())
								||Validate_y.isNull(buildingInfo.getfMaterialType())||Validate_y.isNull(buildingInfo.getfBenchmarking())
								||Validate_y.isNull(buildingInfo.getfSchemeDesign())||Validate_y.isNull(buildingInfo.getfEngineeringConstruction())
								||Validate_y.isNull(buildingInfo.getfEntryTime())||Validate_y.isNull(buildingInfo.getfDetectionTime())){
							err.setRow((i+2)+"");
							err.setErrorMsg("必填项不可为空！");
							errorList.add(err);
						}
                    }
                    if(errorList.isEmpty()){
                    	 boolean flag = besbudinginformationmapper.addBudingInfo(list);
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
        ExcelUtil<BesBudingInformation> util = new ExcelUtil<BesBudingInformation>(BesBudingInformation.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        String FileName = "sheet";// sheet页名称
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
//      实体类导出
        List<BesBudingInformation> buildingInfoList = besbudinginformationmapper.getBuildingList(); 
        //code转关联的文本
        code2text(buildingInfoList);
        ExcelReturn res = util.resList(FileName, FilePath, buildingInfoList);//实体类导出
        Map<String, String> map = new HashMap<>();
        map.put("status", res.getStatus());// 1.成功 0.失败
        map.put("file", file);
        returnObject.setMap(map);
		return returnObject;
	}
	
	private void code2text(List<BesBudingInformation> buildingInfoList) {
		 for(BesBudingInformation buildingInfo : buildingInfoList) {
			 	//数据中心类型
			 if(!Validate_y.isNull(buildingInfo.getfCenterId())) {
				 switch(buildingInfo.getfCenterId()){
		            case "1":
		            	buildingInfo.setfCenterId("数据中心");break;
		            case "2":
		            	buildingInfo.setfCenterId("数据中转站");break;	            
		            default:
		            	buildingInfo.setfCenterId("");break;	
		            } 
			 }
	        	
	        	//所属区县
			 if(!Validate_y.isNull(buildingInfo.getfCountyArea())) {
	        	switch(buildingInfo.getfCountyArea()){
	            case "1":
	            	buildingInfo.setfCountyArea("历下区");break;
	            case "2":
	            	buildingInfo.setfCountyArea("市中区");break;
	            case "3":
	            	buildingInfo.setfCountyArea("槐荫区");break;
	            case "4":
	            	buildingInfo.setfCountyArea("天桥区");break;
	            case "5":
	            	buildingInfo.setfCountyArea("历城区");break;
	            case "6":
	            	buildingInfo.setfCountyArea("长清区");break;
	            case "7":
	            	buildingInfo.setfCountyArea("平阴县");break;
	            case "8":
	            	buildingInfo.setfCountyArea("济阳县");break;
	            case "9":
	            	buildingInfo.setfCountyArea("商河县");break;
	            case "10":
	            	buildingInfo.setfCountyArea("章丘区");break;
	            default:
	            	buildingInfo.setfCountyArea("");break;
	            }
			 }
	        	//建筑监测状态
			 if(!Validate_y.isNull(buildingInfo.getfBudingDetection())) {
	        	switch(buildingInfo.getfBudingDetection()){
	            case "1":
	            	buildingInfo.setfBudingDetection("启用检测");break;
	            case "2":
	            	buildingInfo.setfBudingDetection("停用检测");break;	            
	            default:
	            }
			 }
	        	//建筑状态
			 if(!Validate_y.isNull(buildingInfo.getfBudingState())) {
	        	switch(buildingInfo.getfBudingState()){
	            case "1":
	            	buildingInfo.setfBudingState("建成");break;
	            case "2":
	            	buildingInfo.setfBudingState("封顶");break;	
	            case "3":
	            	buildingInfo.setfBudingState("主体结构施工");break;
	            case "4":
	            	buildingInfo.setfBudingState("基础施工");break;
	            case "5":
	            	buildingInfo.setfBudingState("基坑施工");break;
	            default:
	            }
			 }
	        	//建筑功能
			 if(!Validate_y.isNull(buildingInfo.getfBudingFunction())) {
	        	switch(buildingInfo.getfBudingFunction()){
	            case "1":
	            	buildingInfo.setfBudingFunction("办公建筑");break;
	            case "2":
	            	buildingInfo.setfBudingFunction("商场建筑");break;
	            case "3":
	            	buildingInfo.setfBudingFunction("宾馆饭店建筑");break;
	            case "4":
	            	buildingInfo.setfBudingFunction("文化教育建筑");break;
	            case "5":
	            	buildingInfo.setfBudingFunction("医疗卫生建筑");break;
	            case "6":
	            	buildingInfo.setfBudingFunction("体育建筑");break;
	            case "7":
	            	buildingInfo.setfBudingFunction("综合建筑");break;
	            case "8":
	            	buildingInfo.setfBudingFunction("其他建筑");break;
	            default:
	            }
			 }
	        	//供热方式
			 if(!Validate_y.isNull(buildingInfo.getfHeatingMode())) {
	        	switch(buildingInfo.getfHeatingMode()){
	            case "1":
	            	buildingInfo.setfHeatingMode("集中供热");break;
	            case "2":
	            	buildingInfo.setfHeatingMode("分户采暖");break;	
	            case "3":
	            	buildingInfo.setfHeatingMode("无供热");break;	        
	            default:
	            	buildingInfo.setfHeatingMode("");break;	
	            }
			 }
	        	//供冷方式
			 if(!Validate_y.isNull(buildingInfo.getfCoolingMode())) {
	        	switch(buildingInfo.getfCoolingMode()){
	            case "1":
	            	buildingInfo.setfCoolingMode("集中供冷");break;
	            case "2":
	            	buildingInfo.setfCoolingMode("分户采冷");break;	
	            case "3":
	            	buildingInfo.setfCoolingMode("无供冷");break;	        
	            default:
	            	buildingInfo.setfCoolingMode("");break;	
	            }
			 }
	        	//空调系统形式
			 if(!Validate_y.isNull(buildingInfo.getfAirconditionerSystem())) {
	        	switch(buildingInfo.getfAirconditionerSystem()){
	            case "1":
	            	buildingInfo.setfAirconditionerSystem("集中式全空气系统");break;
	            case "2":
	            	buildingInfo.setfAirconditionerSystem("风机盘管＋新风系统");break;	
	            case "3":
	            	buildingInfo.setfAirconditionerSystem("分体式空调或VRV的局部式机组系统");break;
	            case "4":
	            	buildingInfo.setfAirconditionerSystem("其他");break;	            
	            default:
	            	buildingInfo.setfAirconditionerSystem("");break;
	            }
			 }
	        	//采暖系统形式
			 if(!Validate_y.isNull(buildingInfo.getfHeatingSystem())) {
	        	switch(buildingInfo.getfHeatingSystem()){
	            case "1":
	            	buildingInfo.setfHeatingSystem("散热器采暖");break;
	            case "2":
	            	buildingInfo.setfHeatingSystem("地板辐射采暖");break;	
	            case "3":
	            	buildingInfo.setfHeatingSystem("电辐射采暖");break;
	            case "4":
	            	buildingInfo.setfHeatingSystem("空调系统集中供暖");break;	
	            case "5":
	            	buildingInfo.setfAirconditionerSystem("其他");break;
	            default:
	            	buildingInfo.setfAirconditionerSystem("其他");break;
	            }
			 }
	        	//建筑结构形式
			 if(!Validate_y.isNull(buildingInfo.getfArchitecturalForm())) {
	        	switch(buildingInfo.getfArchitecturalForm()){
	            case "1":
	            	buildingInfo.setfArchitecturalForm("框架结构");break;
	            case "2":
	            	buildingInfo.setfArchitecturalForm("框-剪结构");break;
	            case "3":
	            	buildingInfo.setfArchitecturalForm("剪力墙结构");break;
	            case "4":
	            	buildingInfo.setfArchitecturalForm("砖混结构");break;
	            case "5":
	            	buildingInfo.setfArchitecturalForm("钢结构");break;
	            case "6":
	            	buildingInfo.setfArchitecturalForm("筒体结构");break;
	            case "7":
	            	buildingInfo.setfArchitecturalForm("木结构");break;
	            case "8":
	            	buildingInfo.setfArchitecturalForm("其他");break;
	            default:
	            	buildingInfo.setfArchitecturalForm("");break;
	            }
			 }
	        	//外墙材料形式
			 if(!Validate_y.isNull(buildingInfo.getfExteriorwallForm())) {
	        	switch(buildingInfo.getfExteriorwallForm()){
	            case "1":
	            	buildingInfo.setfExteriorwallForm("砖");break;
	            case "2":
	            	buildingInfo.setfExteriorwallForm("建筑砌块");break;
	            case "3":
	            	buildingInfo.setfExteriorwallForm("板材墙体");break;
	            case "4":
	            	buildingInfo.setfExteriorwallForm("复合墙板和墙体");break;
	            case "5":
	            	buildingInfo.setfExteriorwallForm("玻璃幕墙");break;
	            case "6":
	            	buildingInfo.setfExteriorwallForm("其它");break;	            
	            default:
	            	buildingInfo.setfExteriorwallForm("");break;	
	            }
			 }
	        	//外墙保温形式
			 if(!Validate_y.isNull(buildingInfo.getfExteriorwallInsulationForm())) {
	        	switch(buildingInfo.getfExteriorwallInsulationForm()){
	            case "1":
	            	buildingInfo.setfExteriorwallInsulationForm("内保温");break;
	            case "2":
	            	buildingInfo.setfExteriorwallInsulationForm("外保温");break;
	            case "3":
	            	buildingInfo.setfExteriorwallInsulationForm("夹芯保温");break;
	            case "4":
	            	buildingInfo.setfExteriorwallInsulationForm("其它");break;            
	            default:
	            }
			 }
	        	//建筑外窗类型
			 if(!Validate_y.isNull(buildingInfo.getfBudingExteriorType())) {
	        	switch(buildingInfo.getfBudingExteriorType()){
	            case "1":
	            	buildingInfo.setfBudingExteriorType("单玻单层窗");break;
	            case "2":
	            	buildingInfo.setfBudingExteriorType("单玻双层窗");break;
	            case "3":
	            	buildingInfo.setfBudingExteriorType("单玻单层窗+单玻双层窗");break;
	            case "4":
	            	buildingInfo.setfBudingExteriorType("中空双层玻璃窗");break;
	            case "5":
	            	buildingInfo.setfBudingExteriorType("中空三层玻璃窗");break;
	            case "6":
	            	buildingInfo.setfBudingExteriorType("中空充惰性气体");break;
	            case "7":
	            	buildingInfo.setfBudingExteriorType("其它");break;
	            default:
	            	buildingInfo.setfBudingExteriorType("");break;
	            }
			 }
	        	//建筑玻璃类型
			 if(!Validate_y.isNull(buildingInfo.getfBudingGlassType())) {
	        	switch(buildingInfo.getfBudingGlassType()){
	            case "1":
	            	buildingInfo.setfBudingGlassType("普通玻璃");break;
	            case "2":
	            	buildingInfo.setfBudingGlassType("镀膜玻璃");break;
	            case "3":
	            	buildingInfo.setfBudingGlassType("Low-e玻璃");break;
	            case "4":
	            	buildingInfo.setfBudingGlassType("其它");break;
	            default:
	            	buildingInfo.setfBudingGlassType("");break;
	            }
			 }
	        	//窗框材料类型
			 if(!Validate_y.isNull(buildingInfo.getfMaterialType())) {
	        	switch(buildingInfo.getfMaterialType()){
	            case "1":
	            	buildingInfo.setfMaterialType("钢窗");break;
	            case "2":
	            	buildingInfo.setfMaterialType("铝合金");break;
	            case "3":
	            	buildingInfo.setfMaterialType("木窗");break;
	            case "4":
	            	buildingInfo.setfMaterialType("断热窗框");break;
	            case "5":
	            	buildingInfo.setfMaterialType("塑钢");break;
	            case "6":
	            	buildingInfo.setfMaterialType("其它");break;
	            default:
	            	buildingInfo.setfMaterialType("");break;
	            }
			 }
	        	//执行节能标准
			 if(!Validate_y.isNull(buildingInfo.getfSavingStandard())) {
	        	switch(buildingInfo.getfSavingStandard()){
	            case "1":
	            	buildingInfo.setfSavingStandard("未执行节能标准");break;
	            case "2":
	            	buildingInfo.setfSavingStandard("节能50%标准");break;
	            case "3":
	            	buildingInfo.setfSavingStandard("节能60%标准");break;
	            case "4":
	            	buildingInfo.setfSavingStandard("节能65%以上标准");break;
	            default:
	            	buildingInfo.setfSavingStandard("");break;
	            }
			 }
	        	//是否节能改造
			 if(!Validate_y.isNull(buildingInfo.getfSavingTransformation())) {
	        	switch(buildingInfo.getfSavingTransformation()){
	            case "1":
	            	buildingInfo.setfSavingTransformation("是");break;
	            case "2":
	            	buildingInfo.setfSavingTransformation("否");break;
	            default:
	            	buildingInfo.setfSavingTransformation("");break;
	            }
			 }
	        	//是否政府办公楼
			 if(!Validate_y.isNull(buildingInfo.getfBudingGovernment())) {
	        	switch(buildingInfo.getfBudingGovernment()){
	            case "1":
	            	buildingInfo.setfBudingGovernment("是");break;
	            case "2":
	            	buildingInfo.setfBudingGovernment("否");break;
	            default:
	            	buildingInfo.setfBudingGovernment("");break;
	            }
			 }
	        	//是否标杆建筑
			 if(!Validate_y.isNull(buildingInfo.getfBenchmarking())) {
	        	switch(buildingInfo.getfBenchmarking()){
	            case "1":
	            	buildingInfo.setfBenchmarking("是");break;
	            case "2":
	            	buildingInfo.setfBenchmarking("否");break;
	            default:
	            	buildingInfo.setfBenchmarking("");break;
	            }
			 }
	        }
	}
	private void text2code(List<BesBudingInformation> buildingInfoList) {
		 for(BesBudingInformation buildingInfo : buildingInfoList) {
			 	//数据中心类型
			 if(!Validate_y.isNull(buildingInfo.getfCenterId())) {
	        	switch(buildingInfo.getfCenterId()){
	            case "数据中心":
	            	buildingInfo.setfCenterId("1");break;
	            case "数据中转站":
	            	buildingInfo.setfCenterId("2");break;	            
	            default:
	            	buildingInfo.setfCenterId("");break;
	            }
			 }
	        	//所属区县
			 if(!Validate_y.isNull(buildingInfo.getfCountyArea())) {
	        	switch(buildingInfo.getfCountyArea()){
	            case "历下区":
	            	buildingInfo.setfCountyArea("1");break;
	            case "市中区":
	            	buildingInfo.setfCountyArea("2");break;
	            case "槐荫区":
	            	buildingInfo.setfCountyArea("3");break;
	            case "天桥区":
	            	buildingInfo.setfCountyArea("4");break;
	            case "历城区":
	            	buildingInfo.setfCountyArea("5");break;
	            case "长清区":
	            	buildingInfo.setfCountyArea("6");break;
	            case "平阴县":
	            	buildingInfo.setfCountyArea("7");break;
	            case "济阳县":
	            	buildingInfo.setfCountyArea("8");break;
	            case "商河县":
	            	buildingInfo.setfCountyArea("9");break;
	            case "章丘区":
	            	buildingInfo.setfCountyArea("10");break;
	            default:
	            	buildingInfo.setfCountyArea("");break;
	            }
			 }
	        	//建筑监测状态
			 if(!Validate_y.isNull(buildingInfo.getfBudingDetection())) {
	        	switch(buildingInfo.getfBudingDetection()){
	            case "启用检测":
	            	buildingInfo.setfBudingDetection("1");break;
	            case "停用检测":
	            	buildingInfo.setfBudingDetection("2");break;	            
	            default:
	            	buildingInfo.setfBudingDetection("");break;
	            }
			 }
	        	//建筑状态
			 if(!Validate_y.isNull(buildingInfo.getfBudingState())) {
	        	switch(buildingInfo.getfBudingState()){
	            case "建成":
	            	buildingInfo.setfBudingState("1");break;
	            case "封顶":
	            	buildingInfo.setfBudingState("2");break;	
	            case "主体结构施工":
	            	buildingInfo.setfBudingState("3");break;
	            case "基础施工":
	            	buildingInfo.setfBudingState("4");break;
	            case "基坑施工":
	            	buildingInfo.setfBudingState("5");break;
	            default:
	            	buildingInfo.setfBudingState("");break;
	            }
			 }
	        	//建筑功能
			 if(!Validate_y.isNull(buildingInfo.getfBudingFunction())) {
	        	switch(buildingInfo.getfBudingFunction()){
	            case "办公建筑":
	            	buildingInfo.setfBudingFunction("1");break;
	            case "商场建筑":
	            	buildingInfo.setfBudingFunction("2");break;
	            case "宾馆饭店建筑":
	            	buildingInfo.setfBudingFunction("3");break;
	            case "文化教育建筑":
	            	buildingInfo.setfBudingFunction("4");break;
	            case "医疗卫生建筑":
	            	buildingInfo.setfBudingFunction("5");break;
	            case "体育建筑":
	            	buildingInfo.setfBudingFunction("6");break;
	            case "综合建筑":
	            	buildingInfo.setfBudingFunction("7");break;
	            case "其他建筑":
	            	buildingInfo.setfBudingFunction("8");break;
	            default:
	            	buildingInfo.setfBudingFunction("");break;
	            }
			 }
	        	//供热方式
			 if(!Validate_y.isNull(buildingInfo.getfHeatingMode())) {
	        	switch(buildingInfo.getfHeatingMode()){
	            case "集中供热":
	            	buildingInfo.setfHeatingMode("1");break;
	            case "分户采暖":
	            	buildingInfo.setfHeatingMode("2");break;	
	            case "无供热":
	            	buildingInfo.setfHeatingMode("3");break;	        
	            default:
	            	buildingInfo.setfHeatingMode("");break;
	            }
			 }
	        	//供冷方式
			 if(!Validate_y.isNull(buildingInfo.getfCoolingMode())) {
	        	switch(buildingInfo.getfCoolingMode()){
	            case "集中供冷":
	            	buildingInfo.setfCoolingMode("1");break;
	            case "分户采冷":
	            	buildingInfo.setfCoolingMode("2");break;	
	            case "无供冷":
	            	buildingInfo.setfCoolingMode("3");break;	        
	            default:
	            	buildingInfo.setfCoolingMode("");break;
	            }
			 }
	        	//空调系统形式
			 if(!Validate_y.isNull(buildingInfo.getfAirconditionerSystem())) {
	        	switch(buildingInfo.getfAirconditionerSystem()){
	            case "集中式全空气系统":
	            	buildingInfo.setfAirconditionerSystem("1");break;
	            case "风机盘管＋新风系统":
	            	buildingInfo.setfAirconditionerSystem("2");break;	
	            case "分体式空调或VRV的局部式机组系统":
	            	buildingInfo.setfAirconditionerSystem("3");break;
	            case "其他":
	            	buildingInfo.setfAirconditionerSystem("4");break;	            
	            default:
	            	buildingInfo.setfAirconditionerSystem("");break;
	            }
			 }
	        	//采暖系统形式
			 if(!Validate_y.isNull(buildingInfo.getfHeatingSystem())) {
	        	switch(buildingInfo.getfHeatingSystem()){
	            case "散热器采暖":
	            	buildingInfo.setfHeatingSystem("1");break;
	            case "地板辐射采暖":
	            	buildingInfo.setfHeatingSystem("2");break;	
	            case "电辐射采暖":
	            	buildingInfo.setfHeatingSystem("3");break;
	            case "空调系统集中供暖":
	            	buildingInfo.setfHeatingSystem("4");break;	
	            case "其他":
	            	buildingInfo.setfHeatingSystem("5");break;
	            default:
	            	buildingInfo.setfHeatingSystem("");break;
	            }
			 }
	        	//建筑结构形式
			 if(!Validate_y.isNull(buildingInfo.getfArchitecturalForm())) {
	        	switch(buildingInfo.getfArchitecturalForm()){
	            case "框架结构":
	            	buildingInfo.setfArchitecturalForm("1");break;
	            case "框-剪结构":
	            	buildingInfo.setfArchitecturalForm("2");break;
	            case "剪力墙结构":
	            	buildingInfo.setfArchitecturalForm("3");break;
	            case "砖混结构":
	            	buildingInfo.setfArchitecturalForm("4");break;
	            case "钢结构":
	            	buildingInfo.setfArchitecturalForm("5");break;
	            case "筒体结构":
	            	buildingInfo.setfArchitecturalForm("6");break;
	            case "木结构":
	            	buildingInfo.setfArchitecturalForm("7");break;
	            case "其他":
	            	buildingInfo.setfArchitecturalForm("8");break;
	            default:
	            }
			 }
	        	//外墙材料形式
			 if(!Validate_y.isNull(buildingInfo.getfExteriorwallForm())) {
	        	switch(buildingInfo.getfExteriorwallForm()){
	            case "砖":
	            	buildingInfo.setfExteriorwallForm("1");break;
	            case "建筑砌块":
	            	buildingInfo.setfExteriorwallForm("2");break;
	            case "板材墙体":
	            	buildingInfo.setfExteriorwallForm("3");break;
	            case "复合墙板和墙体":
	            	buildingInfo.setfExteriorwallForm("4");break;
	            case "玻璃幕墙":
	            	buildingInfo.setfExteriorwallForm("5");break;
	            case "其它":
	            	buildingInfo.setfExteriorwallForm("6");break;	            
	            default:
	            	buildingInfo.setfExteriorwallForm("");break;	
	            }
			 }
	        	//外墙保温形式
			 if(!Validate_y.isNull(buildingInfo.getfExteriorwallInsulationForm())) {
	        	switch(buildingInfo.getfExteriorwallInsulationForm()){
	            case "内保温":
	            	buildingInfo.setfExteriorwallInsulationForm("1");break;
	            case "外保温":
	            	buildingInfo.setfExteriorwallInsulationForm("2");break;
	            case "夹芯保温":
	            	buildingInfo.setfExteriorwallInsulationForm("3");break;
	            case "其它":
	            	buildingInfo.setfExteriorwallInsulationForm("4");break;            
	            default:
	            	buildingInfo.setfExteriorwallInsulationForm("");break;
	            }
			 }
	        	//建筑外窗类型
			 if(!Validate_y.isNull(buildingInfo.getfBudingExteriorType())) {
	        	switch(buildingInfo.getfBudingExteriorType()){
	            case "单玻单层窗":
	            	buildingInfo.setfBudingExteriorType("1");break;
	            case "单玻双层窗":
	            	buildingInfo.setfBudingExteriorType("2");break;
	            case "单玻单层窗+单玻双层窗":
	            	buildingInfo.setfBudingExteriorType("3");break;
	            case "中空双层玻璃窗":
	            	buildingInfo.setfBudingExteriorType("4");break;
	            case "中空三层玻璃窗":
	            	buildingInfo.setfBudingExteriorType("5");break;
	            case "中空充惰性气体":
	            	buildingInfo.setfBudingExteriorType("6");break;
	            case "其它":
	            	buildingInfo.setfBudingExteriorType("7");break;
	            default:
	            	buildingInfo.setfBudingExteriorType("");break;
	            }
			 }
	        	//建筑玻璃类型
			 if(!Validate_y.isNull(buildingInfo.getfBudingGlassType())) {
	        	switch(buildingInfo.getfBudingGlassType()){
	            case "普通玻璃":
	            	buildingInfo.setfBudingGlassType("1");break;
	            case "镀膜玻璃":
	            	buildingInfo.setfBudingGlassType("2");break;
	            case "Low-e玻璃":
	            	buildingInfo.setfBudingGlassType("3");break;
	            case "其它":
	            	buildingInfo.setfBudingGlassType("4");break;
	            default:
	            	buildingInfo.setfBudingGlassType("");break;
	            }
			 }
	        	//窗框材料类型
			 if(!Validate_y.isNull(buildingInfo.getfMaterialType())) {
	        	switch(buildingInfo.getfMaterialType()){
	            case "钢窗":
	            	buildingInfo.setfMaterialType("1");break;
	            case "铝合金":
	            	buildingInfo.setfMaterialType("2");break;
	            case "木窗":
	            	buildingInfo.setfMaterialType("3");break;
	            case "断热窗框":
	            	buildingInfo.setfMaterialType("4");break;
	            case "塑钢":
	            	buildingInfo.setfMaterialType("5");break;
	            case "其它":
	            	buildingInfo.setfMaterialType("6");break;
	            default:
	            	buildingInfo.setfMaterialType("");break;
	            }
			 }
	        	//执行节能标准
			 if(!Validate_y.isNull(buildingInfo.getfSavingStandard())) {
	        	switch(buildingInfo.getfSavingStandard()){
	            case "未执行节能标准":
	            	buildingInfo.setfSavingStandard("1");break;
	            case "节能50%标准":
	            	buildingInfo.setfSavingStandard("2");break;
	            case "节能60%标准":
	            	buildingInfo.setfSavingStandard("3");break;
	            case "节能65%以上标准":
	            	buildingInfo.setfSavingStandard("4");break;
	            default:
	            	buildingInfo.setfSavingStandard("");break;
	            }
			 }
	        	//是否节能改造
			 if(!Validate_y.isNull(buildingInfo.getfSavingTransformation())) {
	        	switch(buildingInfo.getfSavingTransformation()){
	            case "是":
	            	buildingInfo.setfSavingTransformation("1");break;
	            case "否":
	            	buildingInfo.setfSavingTransformation("2");break;
	            default:
	            	buildingInfo.setfSavingTransformation("");break;
	            }
			 }
	        	//是否政府办公楼
			 if(!Validate_y.isNull(buildingInfo.getfBudingGovernment())) {
	        	switch(buildingInfo.getfBudingGovernment()){
	            case "是":
	            	buildingInfo.setfBudingGovernment("1");break;
	            case "否":
	            	buildingInfo.setfBudingGovernment("2");break;
	            default:
	            	buildingInfo.setfBudingGovernment("");break;
	            }
			 }
	        	//是否标杆建筑
			 if(!Validate_y.isNull(buildingInfo.getfBenchmarking())) {
	        	switch(buildingInfo.getfBenchmarking()){
	            case "是":
	            	buildingInfo.setfBenchmarking("1");break;
	            case "否":
	            	buildingInfo.setfBenchmarking("2");break;
	            default:
	            	buildingInfo.setfBenchmarking("");break;
	            }
			 }
	        }
	}
}
