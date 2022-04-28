package com.efounder.JEnterprise.zhdg.service.impl;

import com.efounder.JEnterprise.zhdg.service.HttpConnect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @CkassName: HttpConnectImpl
 * @Author: YangChao
 * @Date: 2020/4/7 15:54
 * @Descruotuib:
 * @Version: 1.0
 **/
@Service
public class HttpConnectImpl implements HttpConnect {

    @Value("${neetServerSendMsg.address}")
    private String address ;


    @Override
    public void sendMsg(String DeviceId, String data) {

        String url = address;
        String param="data="+data+"&DeviceId="+DeviceId;
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            //connection.setRequestProperty("contentType", "utf8");
            connection.setReadTimeout(5000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            /** 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个 */
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);

            // 建立实际的连接
            connection.connect();

            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();

            // 获取所有响应头字段
            // Map<String, List<String>> map = connection.getHeaderFields();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));//防止乱码
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            //System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            result="";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
