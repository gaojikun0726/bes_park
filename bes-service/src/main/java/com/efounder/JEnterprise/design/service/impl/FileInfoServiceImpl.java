package com.efounder.JEnterprise.design.service.impl;

import com.efounder.JEnterprise.design.mapper.FileInfoMapper;
import com.efounder.JEnterprise.design.model.FileInfo;
import com.efounder.JEnterprise.design.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe: 文件操作类--实现类
 *
 * @author zs
 * @date 2020/05/25
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private FileInfoMapper fileInfoMapper;

    /**
     * 新增
     *
     * @param fileInfo
     * @return
     */
    @Override
    public boolean insertFileInfo(FileInfo fileInfo) {
        Integer num = fileInfoMapper.insertFileInfo(fileInfo);
        return num == 1;
    }
}
