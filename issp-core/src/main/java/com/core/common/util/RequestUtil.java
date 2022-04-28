package com.core.common.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * String操作Util类
 *
 * @author yangqichao
 */
public class RequestUtil {

    /**
     * 判断是否ajax请求。
     *
     * @param request
     * @return isAjax
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 获取request内的参数,并封装为Map返回(不支持数组形式的参数).
     *
     * @param request request
     * @return map
     * @author yangqichao
     * @since V1.0.0
     */
    public static Map<String, String> getRequestParamMap(HttpServletRequest request) {
        //获取request内所有的参数名称
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, String> paramMap = new HashMap<String, String>();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            //参数名获取单个参数,封装入Map
            paramMap.put(paramName, request.getParameter(paramName));
        }
        return paramMap;
    }

    /**
     * getMultipartRequestMap:(获取request内附件类信息,并封装为可序列化结构). <br/>
     *
     * @param request request
     * @return List<ZcMultipartFile>
     * @author yangqichao
     * @since V1.0.0
     */
    public static List<ZcMultipartFile> getMultipartFileList(
            HttpServletRequest request) {

        ArrayList<ZcMultipartFile> multipartList = new ArrayList<>();
        //获取附件类请求
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext(); ) {
            String key = it.next();
            //获取附件信息
            MultipartFile file = multipartRequest.getFile(key);

            ZcMultipartFile multipartFile = parseMultipartFile(file);

            multipartFile.setKey(key);

            multipartList.add(multipartFile);
        }
        return multipartList;
    }


    /**
     * 把MultipartFile转换为ZcMultipartFile
     *
     * @param file MultipartFile
     * @return ZcMultipartFile
     */
    public static ZcMultipartFile parseMultipartFile(MultipartFile file) {
        ZcMultipartFile multipartFile = new ZcMultipartFile();

        multipartFile.setOriginalFilename(file.getOriginalFilename());
        multipartFile.setSize(file.getSize());
        try {
            multipartFile.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("获取文件内容失败！", e);
        }

        return multipartFile;
    }
}
