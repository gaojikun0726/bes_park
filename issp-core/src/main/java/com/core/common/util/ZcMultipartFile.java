package com.core.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 附件序列化_辅助工具类.
 * ClassName: ZcMultipartFile <br/>
 * @author yangqichao
 * @since V1.0.0
 */
public class ZcMultipartFile implements Serializable{
    /**
     */
    private static final long serialVersionUID = -7567466037230780608L;
    /**
     * 附件key值.
     */
    private String key;
    /**
     * 附件名称.
     */
    private String originalFilename;
    /**
     * 附件大小.
     */
    private long size;
    /**
     * 附件字节列.
     */
    private byte[] bytes;

    /**
     * getInputStream:(由字节数组获取input流). <br/>
     * @author yinyanzhen
     * @return InputStream 文件流
     * @since V1.0.0
     */
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.getBytes());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
