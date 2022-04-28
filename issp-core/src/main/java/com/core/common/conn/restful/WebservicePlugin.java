package com.core.common.conn.restful;

import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * WebservicePlugin 采用SOAP协议获取XML形式参数
 *  根据WSDL文件说明请求接口
 * @author huangxb
 * @datetime 2018年10月15日
 *
 */
public class WebservicePlugin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357917448791470304L;
	
	/**
	 * 
	 * @param jsonObj
	 * @return
	 * @throws RemoteException 
	 * @throws Exception
	 */
	public static Object executeProcessService(JSONObject jsonObj) throws RemoteException{
		String url = jsonObj.getString("url"); //请求服务URL
		String nameSpace = jsonObj.getString("nameSpace"); //服务命名空间
		String methodName = jsonObj.getString("methodName"); //服务方法名
		String paramNames = jsonObj.getString("paramNames");//服务参数名(多个参数逗号隔开)
		String params = jsonObj.getString("params");//服务参数值(多个参数逗号隔开)
		Service s = new Service();
		Call call = null;
		try {
			call = (Call) s.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		call.setOperationName(new QName(nameSpace, methodName));
		String[] paramList = null;
		if (params != null && params != "") {
			if (params.contains(",")) {
				paramList = params.split(",");
			} else {
				paramList = new String[1];
				paramList[0] = params;
			}
		}
		String[] paramNameList = null;
		if (paramNames != null && paramNames != "") {
			if (paramNames.contains(",")) {
				paramNameList = paramNames.split(",");
			} else {
				paramNameList = new String[1];
				paramNameList[0] = paramNames;
			}
		}
		if (paramList != null) { // 有参数时,设置请求参数名称
			for (int i = 0; i < paramList.length; i++) {
//				call.addParameter(new QName(nameSpace, paramNameList[i]), XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter(paramNameList[i], XMLType.XSD_STRING, ParameterMode.IN);
			}
		}
		call.setUseSOAPAction(true);
//		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_VECTOR); // 返回SOAP_VECTOR为数组类型(不能用Array，否则报错)
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回SOAP_STRING为字符串类型
		call.setSOAPActionURI(nameSpace + methodName);
		Object[] paramObj = new Object[] {}; // 参数初始化
		if(paramNameList!=null){
			paramObj = new Object[paramNameList.length];
		}
		if(paramList!=null){// 有参数时,设置请求参数值
			for (int j = 0; j < paramList.length; j++) {
				paramObj[j] = paramList[j];
			}
		}
		// 将执行结果返回
		return call.invoke(paramObj);
	}

}
