package com.efounder.JEnterprise.design.mapper;

import com.efounder.JEnterprise.design.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * describe: 文件操作类
 *
 * @author zs
 * @date 2020/05/25
 */
@Mapper
public interface FileInfoMapper {

    /**
     * 新增
     * @param fileInfo
     * @return
     */
    Integer insertFileInfo(FileInfo fileInfo);
}
