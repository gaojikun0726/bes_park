package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common;
/**  
 * All rights Reserved, Designed By www.sdzckj.com/
 * @Description: 
 * @author: gongfanfei     
 * @date:   2019年6月23日 上午12:05:01   
 * @version V1.0.0
 * 注意：本内容仅限于山东正晨技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class JsonResult {

	/**
	 * JSON结果响应
	 *
	 */
	    private static final String SUCCESS = "成功";
	    private static final String ERROR = "失败";
	    
	    /**
	     * 响应状态code，因为前台layui默认0为响应成功，所以此处默认为0
	     */
	    private String code = "0";

	    /**
	     * 数据总条数
	     */
	    private Long count = 0L;

	 	/**
	     * 返回是否成功
	     */
	    private Boolean result = false;
	    
	    /**
	     * 返回提示信息
	     */
	    private String msg = "";

	    /**
	     * 返回数据信息
	     */
	    private Object data;

	    public JsonResult() {
	    }

	    public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		public Boolean getResult() {
			return result;
		}

		public void setResult(Boolean result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public static String getSuccess() {
			return SUCCESS;
		}

		public static String getError() {
			return ERROR;
		}

		/**
	     * 成功的响应
	     * 
	     * @return
	     */
	    public static JsonResult success() {
	        return result(true, SUCCESS, null,null);
	    }

	    public static JsonResult success(String msg) {
	        return result(true, msg, null,null);
	    }

	    public static JsonResult success(Object data) {
	        return result(true, SUCCESS, data,null);
	    }
	    public static JsonResult success(Object data,Long count) {
	        return result(true, SUCCESS, data,count);
	    }


	    public static JsonResult success(String msg, Object data) {
	        return result(true, msg, data,null);
	    }

	    public static JsonResult success(String msg, Object data,Long count) {
	        return result(true, msg, data,count);
	    }

	    /**
	     * 失败的响应
	     * 
	     * @return
	     */
	    public static JsonResult error() {
	        return result(false, ERROR, null,null);
	    }

	    public static JsonResult error(String msg) {
	        return result(false, msg, null,null);
	    }

	    public static JsonResult error(Object data) {
	        return result(false, ERROR, data,null);
	    }

	    public static JsonResult error(Object data,Long count) {
	        return result(false, ERROR, data,count);
	    }

	    public static JsonResult error(String msg, Object data) {
	        return result(false, msg, data,null);
	    }

	    public static JsonResult error(String msg, Object data,Long count) {
	        return result(false, msg, data,count);
	    }

	    public static JsonResult result(Boolean result, String msg, Object data,Long count) {
	        JsonResult jsonResult = new JsonResult();
	        jsonResult.setResult(result);
	        jsonResult.setMsg(msg);
	        jsonResult.setData(data);
	        jsonResult.setCount(count);
	        return jsonResult;
	    }
	    
	}
