package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util;

/**
 * @NAME: ObjectUtil
 * @USER: LiuGuoqing
 * @DATE: 2019/06/24
 **/
public class ObjectUtil {
	
	
	/**
	 * 判断一个对象是否不为空
	 * @param object
	 * @return
	 */
	public static boolean notNull(Object object) {
		boolean mark = false;
		if(null != object) //对象不为空
			mark = true;
		return mark;
	}
}
