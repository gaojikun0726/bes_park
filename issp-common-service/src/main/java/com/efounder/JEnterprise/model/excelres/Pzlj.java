package com.efounder.JEnterprise.model.excelres;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
* @author  杨超
* @version 创建时间：2018年9月6日 上午9:12:55
* @parameter 
* @version 1.0
*/
@Component
public class Pzlj {

    // 文件上传路径
    @Value("${system.parameter.uploadPath}")
    private String uploadPath;

    public String getUploadPath() {

        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {

        this.uploadPath = uploadPath;
    }
	
}
