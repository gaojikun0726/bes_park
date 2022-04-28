package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util.CssParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * 
 * @author yangjx
 * 模块定义 :  此处为了防止动公用类 issp-service.EsHomeModuleController ,暂时放到这里查看效果。
 */
@Controller
@RequestMapping(value = "/view/esHomeModule/icon")
public class EsHomeModuleController1 {

	private static final Logger log = LoggerFactory.getLogger(EsHomeModuleController1.class);
	
	 /**
	  * Start add by yangjx at 20191022 for 图标显示图形 
	  * @return
	  */
	 @PostMapping("/getFonts")
	 @ResponseBody
	 public ISSPReturnObject getFonts(){
		 ISSPReturnObject isspReturnObject=new ISSPReturnObject();
		 //css文件路径
		 //String path = System.getProperty("user.dir") + "\\ARWebapp\\src\\main\\resources\\static\\fonts\\font-awesome\\css\\font-awesome.min.css";
		 String classpath = this.getClass().getResource("/").toString();
		 String filePath = "static\\fonts\\font-awesome\\css\\font-awesome.min.css";
		 //使用File.separator替换文件路径分隔符，避免linux下报错
		 filePath = filePath.replace("\\", File.separator);
		 String path = classpath + filePath;
//		 String path = System.getProperty("user.dir") + "\\BESWebapp\\src\\main\\resources\\static\\fonts\\font-awesome\\css\\font-awesome.min.css";
//		 String URIPath = "file:///" + path;
		 //获取图标名称
		 try {
			 List<String> list= CssParserUtil.getFonts(path);
			 isspReturnObject.setStatus("1");
			 isspReturnObject.setList(list);
		 } catch (Exception e) {
			 e.printStackTrace();
			 isspReturnObject.setStatus("0");
		 }
		 return isspReturnObject;
	 }
	 //End add by yangjx at 20191022
	 
}
