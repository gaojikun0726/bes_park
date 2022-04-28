package com.core.common.util;

import net.sf.json.xml.XMLSerializer;
import org.dom4j.*;

import java.util.*;

/**
 * WebService 服务工具类(WSDL形式)
 * @author huangxianbo
 *
 */
public class WebservicePluginUtil {

	/**
	 * 将xml格式(返回值)解析为JSONObject
	 * 
	 * @param xmlStr
	 * @return (注意:返回类型是net.sf.json.JSONObject,
	 * 				不 是     com.alibaba.fastjson.JSONObject)
	 */
	
	public static net.sf.json.JSONObject parseXmlToJSONObj(String xmlStr) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		net.sf.json.JSON jObj = xmlSerializer.read(xmlStr);
		return net.sf.json.JSONObject.fromObject(jObj);
	}
	
	/**
	 * 将xml格式返回值解析为Map
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Map<String, Object> parseXmlToMap(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = doc.getRootElement();
		Map<String, Object> mapXml = new HashMap<String, Object>();
		element2Map(mapXml, rootElement);
		return mapXml;
	}
	
	/**
	 * 递归调用将多层级xml转map
	 * 
	 * @param map
	 * @param rootElement
	 */
	private static void element2Map(Map<String, Object> map, Element rootElement) {
		// 获得当前节点的子节点
		@SuppressWarnings("unchecked")
		List<Element> elements = rootElement.elements();
		if (elements.size() == 0) {
			// 没有子节点说明当前节点是叶子节点，直接取值
			map.put(rootElement.getName(), rootElement.getText());
		} else if (elements.size() == 1) {
			// 只有一个子节点说明不用考虑list的情况，继续递归
			Map<String, Object> tempMap = new HashMap<String, Object>();
			element2Map(tempMap, elements.get(0));
			map.put(rootElement.getName(), tempMap);
		} else {
			// 多个子节点的话就要考虑list的情况了，特别是当多个子节点有名称相同的字段时
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Element element : elements) {
				tempMap.put(element.getName(), null);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				@SuppressWarnings("unchecked")
				List<Element> sameElements = rootElement.elements(new QName(string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (sameElements.size() > 1) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					for (Element element : sameElements) {
						Map<String, Object> sameTempMap = new HashMap<String, Object>();
						element2Map(sameTempMap, element);
						list.add(sameTempMap);
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1直接递归
					Map<String, Object> sameTempMap = new HashMap<String, Object>();
					element2Map(sameTempMap, sameElements.get(0));
					map.put(string, sameTempMap);
				}
			}
		}
	}
}
