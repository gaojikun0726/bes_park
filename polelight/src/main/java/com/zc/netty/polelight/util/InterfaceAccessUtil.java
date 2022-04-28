package com.zc.netty.polelight.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;


/**
 * describe: 接口访问工具类
 *
 * @author zs
 * @date 2020/11/12
 */
public class InterfaceAccessUtil {

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
    public static String doGetWithHeader(String path,String headerName,String headerValue) {
        return doGetWithParam(path,"",headerName,headerValue);
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
     * 使用unirest请求：post,参数放到请求体
     * @param path 路径
     * @param username 用户名
     * @param password  密码
     * @param captcha 验证码
     * @param uuid uuid
     * @return
     */
    public static String doHttpBody(String path,String username,String password,String captcha,String uuid){
        String result = null;
//        Unirest.setTimeouts(0, 0);
        try {
//            HttpRequestWithBody httpRequest =  Unirest.post(path);
//            httpRequest.header("Content-Type","application/json");
//            httpRequest.body(bodyJson);
//            com.mashape.unirest.http.HttpResponse<String> httpResponse = httpRequest.asString();
//            result = httpResponse.getBody();
//            com.mashape.unirest.http.HttpResponse response = Unirest.post(path)
//                    .header("Content-Type", "application/json")
//                    .body(bodyJson).asString();
            com.mashape.unirest.http.HttpResponse response = Unirest.post(path)
                    .header("Content-Type", "application/json")
                    .body("{\"username\":\""+username+"\",\"password\":\""+password+"\",\"captcha\":\""+captcha+"\",\"uuid\":\""+uuid+"\"}").asString();
            result = String.valueOf(response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
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
     * 使用Bearer Token格式访问
     * @return
     */
    public static String doPostBearerToken(String path,String token){
        return doPostBearerTokenWithParam(path,token,"");
    }

    /**
     * 使用Bearer Token格式访问
     * @return
     */
    public static String doPostBearerTokenWithParam(String path,String token,String params){
//        String result = null;
////        Unirest.setTimeouts(0, 0);
//        try {
//            HttpResponse<String> response = Unirest.post(path)
//                    .header("Authorization", "Bearer " + token)
//                    .asString();
//            result = String.valueOf(response.getBody());
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
//
//     return result;

        String urlParams = "";
        if(params != null && !"".equals(params)){
            urlParams = "?" + params;
        }
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(path + urlParams);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Authorization", "Bearer " + token);

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
     * 使用Bearer Token格式访问
     * @return
     */
    public static String doUnirestGetBearerToken(String path,String token){
        String result = null;
//        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.get(path)
                    .header("Authorization", "Bearer " + token)
                    .asString();
            result = String.valueOf(response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return result;
    }





    /**
     * 获取token
     * 使用application/x-www-form-urlencoded Post方式访问
     * @param path 访问路径
     * @param appId 参数
     * @return
     */
    public static String getToken(String path,String appId,String clientSecret) {
        String result = "";
//        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post(path)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("appId", "park_energy")
                    .field("clientSecret", "qz_opm")
                    .asString();
            result = String.valueOf(response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return result;
    }




}
