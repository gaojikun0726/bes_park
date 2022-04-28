package com.efounder.JEnterprise.design.controller;

import com.efounder.JEnterprise.design.service.FileInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * describe: 文件操作类 --控制器
 *
 * @author zs
 * @date 2019/12/13
 */
@RequestMapping("/fileInfo")
@Controller
public class FileInfoController {

    @Resource
    private FileInfoService fileInfoService;

    /**
     * 上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 F:/file/
     */
    @Value("${uploadFile.location}")
    private String uploadPath;

    /**
     * 映射的虚拟地址，如 localhost:8080/file/
     */
    @Value("${uploadFile.urlPrefix}")
    private String urlPrefix;

    private final static Logger logger = Logger.getLogger(FileInfoController.class);
    /**
     * 多文件上传
     * @return 返回json
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploadFile")
    @ResponseBody
    public Map<String, String> uploadFile(MultipartHttpServletRequest multipartRequest) throws Exception {
        multipartRequest.setCharacterEncoding("UTF-8");
        Map<String, String> result = new HashMap<String, String>();
        // 业务id
        String fkId = multipartRequest.getParameter("fkId");

        // 多文件上传实现
        MultipartFile multipartFile = null;
        Map<String, MultipartFile> map = multipartRequest.getFileMap();
        for (MultipartFile value : map.values()) {
            multipartFile = value;
        }

        if (null != multipartFile) {
            String fileName = multipartFile.getOriginalFilename();
            // 获取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // 随机生成上传目录中的文件名称
            String id = UUID.randomUUID().toString();

            try {
                String datePath = getDateString();
                // 上传路径
                String fileSavePath = uploadPath + datePath;
                //配置目录+当前日期目录
                File directory = new File(fileSavePath);
                if(!directory.exists()){
                    directory.mkdirs();
                }
                String saveName = id + suffix;
                String realPath = fileSavePath +"/"+ saveName;
                File file = new File(realPath);
                multipartFile.transferTo(file);

                String fileUrl = urlPrefix + datePath + "/" + saveName;

//                // 文件/图片信息写入数据库
//                FileInfo fileInfo = new FileInfo();
//                fileInfo.setId(id);
//                fileInfo.setFileName(fileName);
//                fileInfo.setUploadTime(new Timestamp(System.currentTimeMillis()));
//                fileInfo.setSaveDirectory(realPath);
//                fileInfo.setFileUrl(fileUrl);
//                fileInfo.setSaveName(saveName);

                result.put("id", id);
                result.put("fileName", fileName);
                result.put("fileUrl",fileUrl);
            } catch (IOException e) {
                logger.error("图片/文件上传报错：" + e.getMessage(),e);
            }
        }

        return result;
    }

    /**
     * 获取日期字符串
     * @return
     */
    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String str = sdf.format(new Date());
        return "/" + str;
    }

}
