package com.efounder.JEnterprise.controller.fileDownload;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.BESEquipmentMoldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 杨超
 * @version 创建时间：2018年11月19日 下午5:03:08
 * @parameter File工具类
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private BESEquipmentMoldService besEquipmentMoldService;
	/**
	 * 文件下载类
	 *
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	@RequestMapping("/fileDownload")
	@ResponseBody
	public void fileDownload(HttpServletResponse response, HttpServletRequest request, String filePath, String fileName) {
		filePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + filePath + ".xls";
		byte[] data;
		try {
			log.debug("\r" + "开始下载文件：" + fileName);
			log.debug("\r" + "文件路径：" + filePath);
			data = toByteArray2(filePath);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			response.flushBuffer();
			delFile(filePath);
		} catch (IOException e) {
			log.error("\r" + "下载文件过程中，文件流异常！！");
			e.printStackTrace();
		}
	}

	/**
	 * 下载模板文件，用于部署后的路径下载
	 * @param response
	 * @param request
	 * @param filePath
	 * @param fileName
	 */
	@RequestMapping("/newFileDownload")
	@ResponseBody
	public void newFileDownload(HttpServletResponse response, HttpServletRequest request, String filePath,
								String fileName) {
		String path = this.getClass().getResource("/").getPath();
		filePath = path + filePath;
		filePath = filePath.replace("\\",File.separator);
		byte[] data;
		try {
			log.debug("\r" + "开始下载文件：" + fileName);
			log.debug("\r" + "文件路径：" + filePath);
			data = toByteArray2(filePath);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			response.flushBuffer();
		} catch (IOException e) {
			log.error("\r" + "下载文件过程中，文件流异常！！");
			e.printStackTrace();
		}
	}

    /**
     *
     * @author: YangChao
     * @createTime: 2019年1月10日 下午5:00:09
     * @Description:模板存放路径下载
     * @param response
     * @param request
     * @param filePath
     * @param fileName void
     */
    @RequestMapping("/ExpfileDownload")
    @ResponseBody
    public void ExpfileDownload(HttpServletResponse response, HttpServletRequest request, String filePath,
            String fileName) {
		//wanghongjie 修改下载本地模板的路径
		//idea或eclipse起来的本地项目路径
//		String path = System.getProperty("user.dir");
//		filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\expExcel\\"+fileName;
		//tomcat起来的war项目路径
		filePath = request.getServletContext().getRealPath("/") + "WEB-INF\\" + filePath;
        byte[] data;
        try {
            log.debug("\r" + "开始下载文件：" + fileName);
            log.debug("\r" + "文件路径：" + filePath);
            data = toByteArray2(filePath);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            response.flushBuffer();
        } catch (IOException e) {
            log.error("\r" + "下载文件过程中，文件流异常！！");
            e.printStackTrace();
        }
    }

    /**
     *
     * @Description: idea或eclipse起来的本地项目路径
     *
     * @auther: wanghongjie
     * @date: 9:11 2021/5/21
     * @param: [response, request, filePath, fileName]
     * @return: void
     *
     */
	@RequestMapping("/ideaExpfileDownload")
	@ResponseBody
	public void ideaExpfileDownload(HttpServletResponse response, HttpServletRequest request, String filePath,
								String fileName) {
		//使用项目实际运行时的路径
		String path = this.getClass().getResource("/").getPath();
		filePath = path + filePath;
		filePath = filePath.replace("\\",File.separator);

		//wanghongjie 修改下载本地模板的路径
		//idea或eclipse起来的本地项目路径
//		String path = System.getProperty("user.dir");
//		filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\expExcel\\"+fileName;
		//tomcat起来的war项目路径
//		filePath = request.getServletContext().getRealPath("/") + "WEB-INF\\" + filePath;
		byte[] data;
		try {
			log.debug("\r" + "开始下载文件：" + fileName);
			log.debug("\r" + "文件路径：" + filePath);
			data = toByteArray2(filePath);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			response.flushBuffer();
		} catch (IOException e) {
			log.error("\r" + "下载文件过程中，文件流异常！！");
			e.printStackTrace();
		}
	}

    /**
     *
     * @Description: 可编程页面的程序下载
     *
     * @auther: wanghongjie
     * @date: 17:20 2020/4/29
     * @param: [response, request, filePath, fileName, f_id]
     * @return: void
     *
     */
	@RequestMapping("/ExpfileDownloadbc")
	@ResponseBody
	public void ExpfileDownloadbc(HttpServletResponse response, HttpServletRequest request, String filePath,
								  String fileName,Integer f_id) {
		ISSPReturnObject txtNum = besEquipmentMoldService.selectProgrammInfo(f_id);//查询bes_txt_end表里面有没有当前id关联的txt文本
		if (txtNum != null) {

			String ssss= txtNum.getStatus();
			byte[] data;
			data = ssss.getBytes();
			//wanghongjie 修改下载本地模板的路径
			//idea或eclipse起来的本地项目路径
//		String path = System.getProperty("user.dir");
//		filePath = path+"\\BESWebapp\\src\\main\\webapp\\WEB-INF\\file\\DDCprgram\\"+fileName;
			//tomcat起来的war项目路径
//		filePath = request.getServletContext().getRealPath("/") + filePath;
//		byte[] data;
			try {
				log.debug("\r" + "开始下载文件：" + fileName);
				log.debug("\r" + "文件路径：" + filePath);
//			data = toByteArray2(filePath);
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.reset();
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.addHeader("Content-Length", "" + data.length);
				response.setContentType("application/octet-stream;charset=UTF-8");
				OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
				outputStream.write(data);
				outputStream.flush();
				outputStream.close();
				response.flushBuffer();
			} catch (IOException e) {
				log.error("\r" + "下载文件过程中，文件流异常！！");
				e.printStackTrace();
			}
		}

	}


	// 文件验证
	public byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件
	 *
	 * String 文件路径及名称 如c:/fqf.txt
	 * String
	 * @return boolean
	 */
	@RequestMapping("/filedel")
	@ResponseBody
	public void delFile(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
                    log.error("\r" + "删除文件成功");
				}
			}
		} catch (Exception e) {
			log.error("\r" + "删除文件出错");
		}
	}

}
