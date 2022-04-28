package com.efounder.JEnterprise.service.opm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * describe: 请求工具类
 *
 * @author zs
 * @date 2020/12/1
 */
public class InterfaceUtil {

    /**
     * json格式的数据作为请求体
     * @param path 请求路径
     * @param stringEntity 请求体
     * @return
     */
    public static String doHttpEntity(String path, StringEntity stringEntity){
        return doHttpEntityWithHeader(path,"","",stringEntity);
    }

    /**
     * json格式的数据作为请求体
     * @param path 请求路径
     * @param stringEntity 请求体
     * @return
     * @throws IOException
     */
    public static String doHttpEntityWithHeader(String path,String headerKey,String headerValue,StringEntity stringEntity) {
        String content = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(path);
        CloseableHttpResponse response = null;
        try {
            post.setEntity(stringEntity);
            post.setHeader("Content-type", "application/json");
            if(headerKey != null && !"".equals(headerKey)){
                post.setHeader(headerKey,headerValue);
            }
            response = httpClient.execute(post);
            content = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    /**
     * get请求
     * @param path
     * @return
     */
    public static String doGet(String path){
        return doGetWithParam(path,"","","");
    }

    /**
     * GET方式请求---(手动在url后面加上参数)
     */
    public static String doGetWithParam(String path,String params,String headerName,String headerValue) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String urlParams = "";
        if(params != null && !"".equals(params)){
            urlParams = "?" + params;
        }

        // 创建Get请求
        HttpGet httpGet = new HttpGet(path + urlParams);

        if(headerName != null && !"".equals(headerName)){
            httpGet.setHeader(headerName,headerValue);
        }
//        httpGet.setHeader("token",token);
//        HttpGet httpGet = new HttpGet(path + "?" + params);
//        httpGet.setHeader("Cookie","JSESSIONID="+token);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                InputStream inputStream =  responseEntity.getContent();
                result = getInputStream2String(inputStream);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * InputStream转为String
     * @param inputStream
     * @return
     */
    public static String getInputStream2String(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String str = sb.toString();
        return str;
    }


    /**
     * POST方式请求---(普通参数)
     *
     * @date 2018年7月13日 下午4:18:50
     */
    public static String doPostParam(String path,String params) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(path + "?" + params);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

//            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                InputStream inputStream =  responseEntity.getContent();
                result = getInputStream2String(inputStream);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 使用Bearer Token格式访问
     * @return
     */
    public static String doGetBearerToken(String path,String token){
        return doGetBearerTokenWithParam(path,token,"");
    }

    /**
     * 使用Bearer Token格式访问
     * @return
     */
    public static String doGetBearerTokenWithParam(String path,String token,String params){

        String urlParams = "";
        if(params != null && !"".equals(params)){
            urlParams = "?" + params;
        }
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Get请求
        HttpGet httpGet = new HttpGet(path + urlParams);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpGet.setHeader("Authorization", "Bearer " + token);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                InputStream inputStream =  responseEntity.getContent();
                result = getInputStream2String(inputStream);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * json格式的数据作为请求体
     * @param path 请求路径
     * @param stringEntity 请求体
     * @return
     * @throws IOException
     */
    public static String doHttpEntityWithBearerToken(String path,String token,StringEntity stringEntity) {
        String content = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(path);
        CloseableHttpResponse response = null;
        try {
            post.setEntity(stringEntity);
            post.setHeader("Content-type", "application/json");
            post.setHeader("Authorization", "Bearer " + token);
            response = httpClient.execute(post);
            content = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    /**
     *
     * 使用application/x-www-form-urlencoded Post方式访问
     * @param url 请求地址
     * @param parms 参数
     * @return
     */
    public static JSONObject postForForm(String url, Map<String, String> parms) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        parms.forEach((key, value) -> list.add(new BasicNameValuePair(key, value)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            if (Objects.nonNull(parms) && parms.size() >0) {
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity, "UTF-8"));
            return jsonObject;
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (Objects.nonNull(httpClient)){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
