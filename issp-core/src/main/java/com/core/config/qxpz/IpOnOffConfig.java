package com.core.config.qxpz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * IP拦截配置
 * 
 * @author gongfanfei
 * @datetime 2019年05月14日
 *
 */
@Component
public class IpOnOffConfig {
	

	//是否启用IP拦截
	@Value("${system.parameter.ipable}")
	private String sysParameterIpable;

	public String getSysParameterIpable() {
		return sysParameterIpable;
	}

	public void setSysParameterIpable(String sysParameterIpable) {
		this.sysParameterIpable = sysParameterIpable;
	}

	
	
}