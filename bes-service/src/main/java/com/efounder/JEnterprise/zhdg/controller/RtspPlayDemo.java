package com.efounder.JEnterprise.zhdg.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RtspPlayDemo {
    public static void main(String[] args){
        RtspPlayDemo convertVideoPakcet = new RtspPlayDemo();

        // rtsp地址 rtsp://admin:jdrx1234567@192.168.60.30:554/h264/ch1/sub/av_stream
        String rtspUrl = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
        // Nginx rtmp地址
        // 1935 对应Nginx配置文件中rtmp所监听的端口
        // live 对应Nginx配置文件中rtmp下application的值
        // rtmpStream 对应播放页面中16行参数stream的值
        String nginxRtmpUrl = "rtmp://localhost:1935/live/rtmpStream";

        int a=convertVideoPakcet.pushVideoAsRTSP(rtspUrl, nginxRtmpUrl);
        System.out.println("结束推流："+a);
    }

    public Integer pushVideoAsRTSP(String rtspUrl, String nginxRtmpUrl){
        int flag = -1;
        try {
            // ffmpeg 已经在系统环境变量中配置好了
            String command = "ffmpeg";
            command += " -i \"" + rtspUrl + "\"";
            command += " -vcodec copy -acodec copy -f flv -s 800x600 " + nginxRtmpUrl;
            System.out.println("ffmpeg推流命令：" + command);

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br= new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("视频推流信息[" + line + "]");
            }
            flag = process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
