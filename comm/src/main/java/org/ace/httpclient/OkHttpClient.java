package org.ace.httpclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.ace.httpclient.constant.HttpMediaType;
import org.ace.httpclient.exception.HttpException;
import org.ace.httpclient.request.RequestParams;

import java.io.File;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @author xiepufeng
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class OkHttpClient
{
    private static final int TIME_OUT = 5; // 超时时间 5 秒

    private static okhttp3.OkHttpClient okHttpClient;

    private String url;

    private RequestParams requestParams;

    public OkHttpClient()
    {
        if (okHttpClient == null)
        {
            okhttp3.OkHttpClient.Builder okHttpClientBuilder = new okhttp3.OkHttpClient.Builder();
            okHttpClientBuilder.hostnameVerifier((hostname, session) -> true);

            okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS); // 连接超时时间
            okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
            okHttpClient = okHttpClientBuilder.build();
        }
    }


    public OkHttpClient url(String url) throws HttpException
    {
        if (url == null)
        {
            throw new HttpException("url 不存在！");
        }

        this.url = url;

        return this;
    }

    public OkHttpClient data(RequestParams requestParams)
    {
        this.requestParams = requestParams;
        return this;
    }

    public OkHttpClient post(Callback callback) throws HttpException
    {

        if (url == null)
        {
            throw new HttpException("url 不存在！");
        }


        RequestBody body = RequestBody.create(HttpMediaType.JSON_TYPE, toJson(requestParams.params));

        Call call = okHttpClient.newCall(new Request.Builder().url(url).post(body).build());
        call.enqueue(callback);

        return this;
    }

    public OkHttpClient get(Callback callback) throws HttpException
    {

        if (url == null)
        {
            throw new HttpException("url 不存在！");
        }

        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (requestParams != null)
        {
            requestParams.params.forEach((key, value) ->
            {
                urlBuilder.append(key).append("=").append(toJson(value)).append("&");

            });
        }

        Call call = okHttpClient.newCall(new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build());
        call.enqueue(callback);

        return this;
    }

    public OkHttpClient filePost(Callback callback) throws HttpException
    {

        if (url == null)
        {
            throw new HttpException("url 不存在！");
        }

        MultipartBody.Builder requestBody = new MultipartBody.Builder();

        requestBody.setType(MultipartBody.FORM);

        if (requestParams != null)
        {
            requestParams.params.forEach((key, value) ->
            {
                if (value instanceof File)
                {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                            RequestBody.create(HttpMediaType.FILE_TYPE, (File) value));
                } else if (value instanceof String)
                {

                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                            RequestBody.create(null, toJson(value)));
                }
            });
        }


        Call call = okHttpClient.newCall(new Request.Builder().url(url).post(requestBody.build()).build());
        call.enqueue(callback);

        return this;
    }

    public String toJson(Object object)
    {
        if (object == null)
        {
            return "";
        }

        if (object instanceof String)
        {
            return (String) object;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Object>()
        {
        }.getType();

        return gson.toJson(object, type);
    }

}