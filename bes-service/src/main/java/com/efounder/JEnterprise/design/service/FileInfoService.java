package com.efounder.JEnterprise.design.service;

import com.efounder.JEnterprise.design.model.FileInfo;

/**
 * describe: 文件操作类--业务层接口
 *
 * @author zs
 * @date 2020/05/25
 */
public interface FileInfoService {

    /**
     * 新增
     * @param fileInfo
     * @return
     */
    boolean insertFileInfo(FileInfo fileInfo);

}
