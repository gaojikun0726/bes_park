package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.util.Map;

/**
* @author  杨超
* @version 创建时间：2018年9月30日 上午10:44:29
* @parameter 
* @version 1.0
*/
public class ReturnScene implements BaseEntity<String>{
	private String	IPAddr;	// IP 地址
	private String	Port ;	// 端口号
	private String	item ;  // 项目标识	
	private int	index;  // 命令指令
	private Map<String,Object> data; //返回集合
	public String getIPAddr() {
		return IPAddr;
	}
	public void setIPAddr(String iPAddr) {
		IPAddr = iPAddr;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
    
} 