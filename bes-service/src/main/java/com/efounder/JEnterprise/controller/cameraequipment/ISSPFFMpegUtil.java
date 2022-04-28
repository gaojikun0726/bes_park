package com.efounder.JEnterprise.controller.cameraequipment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FFMpeg转码
 * 
 * @author Alvin
 * @datetime 2019年3月14日
 *
 */
public class ISSPFFMpegUtil {
//	private static String ffmpegEXE = "D:/ffMpeg/bin/ffmpeg";
	private static String ffmpegEXE = "/opt/lmt/ffmpeg/ffmpeg";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map convetorStream(String videoInputPath, String videoOutPath) {
//		String ffmpegEXE = request.getSession().getServletContext().getRealPath("/zcvdo/ffMpeg/bin/ffmpeg");
		Map map = new HashMap();
		List<String> command = new ArrayList<String>();

		/*
		* 第一种格式
		* ffmpeg -i "rtmp://live.hkstv.hk.lxdns.com/live/hks2" -vcodec libx264 -preset:v ultrafast -tune:v zerolatency -an -f flv "rtmp://10.200.1.138:1935/live/t1"
		**/
//		command.add(ffmpegEXE);
//		command.add("-i");
//		command.add(videoInputPath);
//		command.add("-vcodec");
//		command.add("libx264");
//		command.add("-preset:v");
//		command.add("ultrafast");
//		command.add("-tune:v");
//		command.add("zerolatency");
//		command.add("-acodec");
//		command.add("copy");
//		command.add("-f");
//		command.add("flv");
//		command.add(videoOutPath);


		/*
		* 第二种格式
		* ffmpeg -f rtsp -rtsp_transport tcp -i "rtsp://10.50.13.247:554/pag://10.50.13.248:7302:18082810021310006299:0:MAIN:TCP?streamform=
		* rtp" -vcodec libx264 -preset:v ultrafast -tune:v zerolatency -acodec copy -f flv "rtmp://10.200.1.138:1935/hls/t16"
		 */
		command.add(ffmpegEXE);
		command.add("-f");
		command.add("rtsp");
		command.add("-rtsp_transport");
		command.add("tcp");
		command.add("-i");
		command.add(videoInputPath);
		command.add("-vcodec");
		command.add("libx264");
		command.add("-preset:v");
		command.add("ultrafast");
		command.add("-tune:v");
		command.add("zerolatency");
		command.add("-acodec");
		command.add("copy");
		command.add("-f");
		command.add("flv");
		command.add(videoOutPath);



		/*
		* 第三种格式
		*
		* ffmpeg -i "rtsp://admin:admin123@10.2.19.2:554/cam/realmonitor?channel=15&subtype=0" -vcodec copy -acodec copy -f flv "rtmp://10.200.10.63:1935/hls/t1"
		*
		* */
//		command.add(ffmpegEXE);
//		command.add("-i");
//		command.add(videoInputPath);
//		command.add("-vcodec");
//		command.add("copy");
//		command.add("-acodec");
//		command.add("copy");
//		command.add("-f");
//		command.add("flv");
//		command.add(videoOutPath);
		try {
			Process videoProcess = new ProcessBuilder(command).start();
			new ISSPPrintStream(videoProcess.getErrorStream()).start();
			new ISSPPrintStream(videoProcess.getInputStream()).start();
			int waitFor = videoProcess.waitFor();
			map.put("Process", videoProcess);
			map.put("WaitFor", waitFor);
			return map;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return map;
	}
}
