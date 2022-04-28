package com.efounder.JEnterprise.hikvision;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
@Component
public class ArtemisPostTest {
	/**
	 * 请根据自己的appKey和appSecret更换static静态块中的三个参数. [1 host]
	 * 如果你选择的是和现场环境对接,host要修改为现场环境的ip,https端口默认为443，http端口默认为80.例如10.33.25.22:443 或者10.33.25.22:80
	 * appKey和appSecret请按照或得到的appKey和appSecret更改.
	 * TODO 调用前先要清楚接口传入的是什么，是传入json就用doPostStringArtemis方法，下载图片doPostStringImgArtemis方法
	 */
//	@Value("${ArtemisConfig.host}")
	private static String host;
//	@Value("${ArtemisConfig.appKey}")
	private static String appKey;
//	@Value("${ArtemisConfig.appSecret}")
	private static String appSecret;
	@Value("${ArtemisConfig.host}")
	public void setHost(String host){
		ArtemisConfig.host = host;
	}
	@Value("${ArtemisConfig.appKey}")
	public void setAppKey(String appKey){
		ArtemisConfig.appKey = appKey;
	}
	@Value("${ArtemisConfig.appSecret}")
	public void setAppSecret(String appSecret){
		ArtemisConfig.appSecret = appSecret;
	}

//	static {
//		ArtemisConfig.host = host;//"119.164.216.90:1443";// 代理API网关nginx服务器ip端口
//		ArtemisConfig.appKey = appKey;//"28399386";// 秘钥appkey
//		ArtemisConfig.appSecret = appSecret;//"DO38aCiUplCIO8WOKekJ";// 秘钥appSecret
//		ArtemisConfig.host = "119.164.216.90:1443";// 代理API网关nginx服务器ip端口
//		ArtemisConfig.appKey = "28399386";// 秘钥appkey
//		ArtemisConfig.appSecret = "DO38aCiUplCIO8WOKekJ";// 秘钥appSecret
//	}
	/**
	 * 能力开放平台的网站路径
	 * TODO 路径不用修改，就是/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";


	/**
	 * 调用POST请求类型(application/json)接口，这里以入侵报警事件日志为例
	 * https://open.hikvision.com/docs/918519baf9904844a2b608e558b21bb6#e6798840
	 *
	 * @return
	 */
	public static String callPostStringApi(){
		/**
		 * http://10.33.47.50/artemis/api/scpms/v1/eventLogs/searches
		 * 根据API文档可以看出来，这是一个POST请求的Rest接口，而且传入的参数值为一个json
		 * ArtemisHttpUtil工具类提供了doPostStringArtemis这个函数，一共六个参数在文档里写明其中的意思，因为接口是https，
		 * 所以第一个参数path是一个hashmap类型，请put一个key-value，query为传入的参数，body为传入的json数据
		 * 传入的contentType为application/json，accept不指定为null
		 * header没有额外参数可不传,指定为null
		 *
		 */
		final String getCamsApi = ARTEMIS_PATH +"/api/scpms/v1/eventLogs/searches";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", getCamsApi);//根据现场环境部署确认是http还是https
			}
		};

		JSONObject jsonBody = new JSONObject();

		jsonBody.put("pageNo", 1);
		jsonBody.put("pageSize", 3);
		String body = jsonBody.toJSONString();

		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);// post请求application/json类型参数
		return result;
	}

	/**
	 * 调用POST请求下载图片类型接口，这里以获取访客记录中的图片接口为例
	 * https://open.hikvision.com/docs/a0a1a0a24701a00aa904f7b151f97410#f11f3208
	 *
	 * @return
	 */
	public static void callPostImgStringApi(){
		/**
		 * http://10.33.47.50/api/visitor/v1/record/pictures
		 * 根据API文档可以看出来，这是一个POST请求的Rest接口，而且传入的参数值为一个json
		 * ArtemisHttpUtil工具类提供了doPostStringImgArtemis这个函数，一共六个参数在文档里写明其中的意思，因为接口是https，
		 * 所以第一个参数path是一个hashmap类型，请put一个key-value，query为传入的参数，body为传入的json数据
		 * 传入的contentType为application/json，accept不指定为null
		 * header没有额外参数可不传,指定为null
		 *
		 */
		final String VechicleDataApi = ARTEMIS_PATH +"/api/visitor/v1/record/pictures";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",VechicleDataApi);
			}
		};

		JSONObject jsonBody = new JSONObject();
		jsonBody.put("svrIndexCode", "8907fd9d-d090-43d3-bb3a-3a4b10dd7219");
		jsonBody.put("picUri", "/pic?0dd453i3c-e*046456451175m6ep=t=i2p*i=d1s*i3d0d*=*1b8i81f4747059503--bdf90a-855s5721z3b9i=1=");
		String body = jsonBody.toJSONString();
		System.out.println("body: "+body);
		HttpResponse result =ArtemisHttpUtil.doPostStringImgArtemis(path,body,null,null,"application/json",null);
		try {
			HttpResponse resp = result;
			if (200==resp.getStatusLine().getStatusCode()) {
				HttpEntity entity = resp.getEntity();
				InputStream in = entity.getContent();
				Tools.savePicToDisk(in, "d:/", "test4.jpg");
				System.out.println("下载成功");
			}else{
				System.out.println("下载出错");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 获取监控视频链接
     * @param id 监控器唯一标识  cameraIndexCode
     * @return
     */
	public static String rtmpPostImgStringApi(String id){
		final String VechicleDataApi = ARTEMIS_PATH +"/api/video/v2/cameras/previewURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",VechicleDataApi);
			}
		};

		JSONObject jsonBody = new JSONObject();
		jsonBody.put("cameraIndexCode", id);
		jsonBody.put("streamType", "0");
		jsonBody.put("protocol", "rtsp");
		jsonBody.put("transmode", "1");
		String body = jsonBody.toJSONString();
		System.out.println("body: "+body);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
		return result;
	}

	/**
	 * 获取报警回放视频链接
	 * @param id
	 * @return
	 */
	public static String getMp4(String id ,String beginTime, String endTime){
		final String VechicleDataApi = ARTEMIS_PATH +"/api/video/v2/cameras/previewURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",VechicleDataApi);
			}
		};

		JSONObject jsonBody = new JSONObject();
		jsonBody.put("cameraIndexCode", id);
		jsonBody.put("protocol", "rtmp");//播放格式
		jsonBody.put("beginTime", beginTime);//开始查询时间（IOS8601格式 ） 示例：2017-06-14T00:00:00.000+08:00
		jsonBody.put("endTime", endTime);//结束查询时间，开始时间和结束时间相差不超过三天  格式要求同上
		String body = jsonBody.toJSONString();
		System.out.println("body: "+body);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
		return result;
	}

    /**
     * 获取监控点资源
     * @return
     */
	public static String getid(){
		final String VechicleDataApi = ARTEMIS_PATH +"/api/resource/v1/cameras";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",VechicleDataApi);
			}
		};
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("pageNo", "1");
		jsonBody.put("pageSize", "20");
		String body = jsonBody.toJSONString();
		System.out.println("body: "+body);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
		return result;
	}

    /**
     * 按事件类型订阅事件
     * @return
     */
    public static String getsubscribe(){
        final String VechicleDataApi = ARTEMIS_PATH +"/api/eventService/v1/eventSubscriptionByEventTypes";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",VechicleDataApi);
            }
        };
        JSONObject jsonBody = new JSONObject();
        int data[] = new int[1];
        data[0] = 192517;
        int eventLvl[] = new int[1];
        eventLvl[0] = 2;
        jsonBody.put("eventTypes", data);//事件类型，数组形式
        jsonBody.put("eventDest", "https://ip:port/eventRcv");//运行环境后 应修改为项目运行地址
//        jsonBody.put("subType", "1");//订阅类型 0-订阅原始事件，1-联动事件，2-原始事件和联动事件，不填使用默认值0
        jsonBody.put("eventLvl", eventLvl);//事件等级，0-未配置，1-低，2-中，3-高
        String body = jsonBody.toJSONString();
        System.out.println("body: "+body);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
        return result;
    }


    /**
     * 查询事件订阅信息
     * @return
     */
    public static String getAllsubscribe(){
        final String VechicleDataApi = ARTEMIS_PATH +"/api/eventService/v1/eventSubscriptionView";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",VechicleDataApi);
            }
        };
        String result =ArtemisHttpUtil.doPostStringArtemis(path,null,null,null,"application/json",null);
        return result;
    }

    /**
     * 按事件类型取消订阅
     * @param id 取消类型
     * @return
     */
    public static String deletesubscribe(String id){
        final String VechicleDataApi = ARTEMIS_PATH +"/api/eventService/v1/eventUnSubscriptionByEventTypes";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",VechicleDataApi);
            }
        };
        Integer ii =new Integer(id);
        int x=ii.intValue();
        int y = Integer.parseInt(id);
        JSONObject jsonBody = new JSONObject();
        int data[] = new int[1];
        data[0] = y;
        jsonBody.put("eventTypes", data);//取消事件类型编号，前端传入
        String body = jsonBody.toJSONString();
        System.out.println("body: "+body);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
        return result;
    }


    /**
     * 获取联动事件列表
     * @return
     */
    public static String getsubscribelinkage(){
        final String VechicleDataApi = ARTEMIS_PATH +"/api/els/v1/events/search";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",VechicleDataApi);
            }
        };
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("startTime", "2019-03-18T12:11:38.154+08:00");//开始时间,ISO8601时间
        jsonBody.put("endTime", "2019-03-19T12:11:38.154+08:00");//结束时间,ISO8601时间
        jsonBody.put("pageSize", "20");//分页大小，可前端传入
        jsonBody.put("pageNo", "1");//页码大小
        String body = jsonBody.toJSONString();
        System.out.println("body: "+body);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json",null);
        return result;
    }




//	public static void main(String[] args) {
//
//		String StringeResult = callPostStringApi();
//		System.out.println("StringeResult结果示例: "+StringeResult);
//		callPostImgStringApi();
//	}
}
