package org.ace.httpclient.constant;

import okhttp3.MediaType;

/**
 * @author xiepufeng
 * @date 2020/12/29 16:05
 */
public class HttpMediaType
{

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
}
