package com.efounder.JEnterprise.service.basedatamanage.energydataRecord.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.energydataRecord.BesMeterAmmeterMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataRecord.BesMeterAmmeter;
import com.efounder.JEnterprise.model.excelres.ExcelError;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.efounder.JEnterprise.model.excelres.Pzlj;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energydataRecord.BESMeterService;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.Validate_y;
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
import java.util.regex.Pattern;

/**
* @author  杨超
* @version 创建时间：2018年8月31日 上午10:09:41
* @parameter 
* @version 1.0
*/
@Service("BESMeterService")
public class BESMeterServiceimpl implements BESMeterService ,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESMeterServiceimpl.class);
	
	@Autowired
	private BesMeterAmmeterMapper besammetermapper;
	@Autowired
	private Pzlj bespzlj;// 获取配置文件路径

	/**
	 * 生成静态电表树
	 */
	@Override
	public ISSPReturnObject meter_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取静态电表树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取静态电表树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	private List<ISSPTreeNode> getTreeJson() {
		List<BesMeterAmmeter> list = besammetermapper.getMeterTypeList();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BesMeterAmmeter map : list) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(map.getFguid());
			node.setId(map.getFguid());
			node.setPid("");
			node.setText(map.getfNickName());
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}
	
	/**
	 * 新增静态电表 保存方法
	 */
	@Override
	public ISSPReturnObject add_MeterInformation(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		dto.setfBlxbh("5");//5  静态电表
		//主键
		String fGuid=UUIDUtil.getRandom32BeginTimePK();
		dto.setFguid(fGuid);
		boolean flag = besammetermapper.add_MeterInformation(dto);
		BesMeterAmmeter list = null;
		if(flag){
			list = besammetermapper.editSelect_MeterInformation(fGuid);
		}
		if(flag) {
			returnObject.setMsg("添加静态电表成功");
			returnObject.setData(list);
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("添加静态电表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 修改回显 查询
	 */
	@Override
	public ISSPReturnObject editSelect_MeterInformation(String fGuid) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		BesMeterAmmeter list = besammetermapper.editSelect_MeterInformation(fGuid);
		try {
			returnObject.setData(list);
			returnObject.setMsg("修改回显成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("修改回显失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 修改保存
	 */
	@Override
	public ISSPReturnObject edit_MeterInformation(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		dto.setfChdate(fChdate);
		dto.setfBlxbh("5");//5  静态电表
		//主键
		boolean flag = besammetermapper.edit_MeterInformation(dto);
		if(flag) {
			returnObject.setMsg("修改静态电表成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("修改静态电表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 删除
	 */
	@Override
	public ISSPReturnObject del_MeterInformation(String fGuid,String fJtguid,String lxqf) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		//lxqf  类型区分  sj 代表删除数据  	fJtguid 静态电表数据id		fGuid 静态电表id
		//仅删除静态电表数据
		if("sj".equals(lxqf)) {
			boolean sjflag=besammetermapper.del_MeterInfo_one(fJtguid);
			if(sjflag) {
				returnObject.setMsg("删除静态电表数据成功");
				returnObject.setStatus("1");
			}else {
				returnObject.setMsg("删除静态电表数据失败");
				returnObject.setStatus("0");
			}
		}else {
			boolean flag = besammetermapper.del_MeterInformation(fGuid);
			boolean sjflag=besammetermapper.del_MeterInfo(fGuid);
			if(flag) {
				returnObject.setMsg("删除静态电表成功");
				returnObject.setStatus("1");
			}else {
				returnObject.setMsg("删除静态电表失败");
				returnObject.setStatus("0");
			}
		}
		return returnObject;
	}
	
	/**
	 * 新增静态电表数据 保存方法
	 */
	@Override
	public ISSPReturnObject add_MeterInfo(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		dto.setfType("1");//1 静态电表数据
		//主键
		String fJtguid=UUIDUtil.getRandom32BeginTimePK();
		dto.setfJtguid(fJtguid);
		boolean flag = besammetermapper.add_MeterInfo(dto);
		if(flag) {
			returnObject.setMsg("添加静态电表数据成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("添加静态电表数据失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	//点击树 查询右侧table
	@Override
	public ISSPReturnObject TreeTable(String treeId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BesMeterAmmeter> list = besammetermapper.TreeTable(treeId);
			returnObject.setList(list);
			returnObject.setMsg("查找子类成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查找子类失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	
	/**
	 * 修改数据回显 查询
	 */
	@Override
	public ISSPReturnObject editSelect_MeterInfo(String fJtguid) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		BesMeterAmmeter list = besammetermapper.editSelect_MeterInfo(fJtguid);
		try {
			returnObject.setData(list);
			returnObject.setMsg("修改数据回显成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("修改数据回显失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 修改数据保存
	 */
	@Override
	public ISSPReturnObject edit_MeterInfo(BesMeterAmmeter dto) {
		ISSPReturnObject returnObject=new ISSPReturnObject();
		String date=DateUtil.getCurrTime();//当前时间
		dto.setfChdate(date);
		//主键
		boolean flag = besammetermapper.edit_MeterInfo(dto);
		if(flag) {
			returnObject.setMsg("修改静态电表数据成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("修改静态电表数据失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 获取电表类型
	 */
	@Override
	public ISSPReturnObject selectMeterType() {
		ISSPReturnObject returnObject = new ISSPReturnObject();	
		try {
			List<Map<String,Object>> list = besammetermapper.selectMeterType();
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
	 *
	 * @param treeId
	 * @return
	 */
	@Override
	public ISSPReturnObject exportBesMeter(HttpServletRequest request, String treeId) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		// 创建工具类.
		ExcelUtil<BesMeterAmmeter> util = new ExcelUtil<>(BesMeterAmmeter.class);
		// 临时文件名
		String file = System.currentTimeMillis() + "";
		String FileName = "sheet";// sheet页名称
		String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
		// 获取要导出的数据
		List<BesMeterAmmeter> besMeterAmmeters = besammetermapper.TreeTable(treeId);
		for(BesMeterAmmeter b:besMeterAmmeters)
		{
			b.setfBlxbh(b.getfBlxmc());
			if("0".equals(b.getfEnabled()))
			{
				b.setfEnabled("否");
			}
			else
			{
				b.setfEnabled("是");
			}
		}
		ExcelReturn res = util.resList(FileName, FilePath, besMeterAmmeters);
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
		String realPath = bespzlj.getUploadPath();// 文件保存时候的根路径
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
				try {
					fis = new FileInputStream(fileUrl);
					ExcelUtil<BesMeterAmmeter> util = new ExcelUtil<>(BesMeterAmmeter.class);
					List<BesMeterAmmeter> list = util.importExcel("sheet1", fis);// 导入excel,处理后生成list
					// 获取要导出的数据
					List<ExcelError> excelErrors = new ArrayList<>();

					for(int i = 0 ;i < list.size(); i++)
					{
						BesMeterAmmeter besMeterAmmeter = list.get(i);
						besMeterAmmeter.setfBlxbh("5");//静态电表数据
						besMeterAmmeter.setfBlxmc("静态电表");//静态电表数据
						besMeterAmmeter.setfEnabled(besMeterAmmeter.getfEnabled().split(":")[0]);
						//主键
						String fJtguid=UUIDUtil.getRandom32BeginTimePK();
						besMeterAmmeter.setFguid(fJtguid);
						String date = DateUtil.getCurrTime();
						String fCrdate = date;
						String fChdate = date;
						besMeterAmmeter.setfCrdate(fCrdate);
						besMeterAmmeter.setfChdate(fChdate);
						boolean flag =false;
						String errMsg ="";
						if(Validate_y.isNull(besMeterAmmeter.getfSysName())|| Validate_y.isNull(besMeterAmmeter.getfNickName())
								|| Validate_y.isNull(besMeterAmmeter.getfAzwz())|| Validate_y.isNull(besMeterAmmeter.getfDescription())
								|| Validate_y.isNull(besMeterAmmeter.getfData()))
						{
							errMsg ="必填项不可为空！";
							flag =false;
						}
						if(!isInteger(besMeterAmmeter.getfData()))
						{
							flag =false;
							if("".equals(errMsg))
							{
								errMsg ="数据必须为数字";
							}
							else
							{
								errMsg ="，数据必须为数字";
							}
						}

						if(!flag)
						{
							ExcelError excelError = new ExcelError();
							excelError.setRow((i+2)+"");
							excelError.setErrorMsg(errMsg);
							excelErrors.add(excelError);

						}

					}
					if(excelErrors.size() > 0)
					{
						returnObject.setMsg("导入数据过程中出现错误，请查看excel错误报告！");
						returnObject.setStatus("2");
						returnObject.setList(excelErrors);
						return returnObject;
					}
					else
					{
						boolean flag = false;
						for (BesMeterAmmeter besMeterAmmeter :list) {
							flag = besammetermapper.add_MeterInformation(besMeterAmmeter);
							besMeterAmmeter.setfType("1");//1 静态电表数据
							String guid=UUIDUtil.getRandom32BeginTimePK();
							besMeterAmmeter.setfJtguid(guid);
							besammetermapper.add_MeterInfo(besMeterAmmeter);
						}
						if(flag){
							returnObject.setMsg("导入成功！");
							returnObject.setStatus("1");
						}else{
							returnObject.setMsg("导入失败！");
							returnObject.setStatus("0");
						}
					}
					// 如果不需要验证 则可直接将list插入到数据库
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
			returnObject.setMsg("导入数据失败！");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
