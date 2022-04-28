package com.efounder.JEnterprise.design.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * describe: 文件信息类
 *
 * @author zs
 * @date 2020/05/25
 */
@Data
public class FileInfo {

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名（包括拓展名）
     */
    private String fileName;

    /**
     * 硬盘上的文件保存名称（包括拓展名）
     */
    private String saveName;

    /**
     * 文件在硬盘上的保存目录
     */
    private String saveDirectory;

    /**
     * 文件访问链接
     */
    private String fileUrl;

    /**
     * 上传时间
     */
    private Timestamp uploadTime;
}
