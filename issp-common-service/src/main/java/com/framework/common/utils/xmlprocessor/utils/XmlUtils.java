package com.framework.common.utils.xmlprocessor.utils;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;
import com.framework.common.utils.xmlprocessor.annotation.RootXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author xiepufeng
 * xml 工具类
 * @date 2020/11/7 11:35
 */
public class XmlUtils
{
    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder db;

    static
    {
        try
        {
            db = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个DOM的新实例
     *
     * @return
     */
    public static Document getDocument()
    {
        return db.newDocument();
    }

    /**
     * 创建xml 文件并存放到指定目录
     *
     * @param obj 构建xml 文件的信息对象
     * @param path 文件生成存放路径
     * @param path xml 文件名称（不需要携带后缀名）
     */
    public static <T> boolean createXmlFile(T obj, String path, String fileName)
    {
        if (obj == null || path == null || fileName == null || "".equals(fileName))
        {
            return false;
        }

        //判断目录是否存在，不存在则创建
        if (!makeWritableDirectoryIfNotExist(path))
        {
            return false;
        }

        Document document = getDocument();

        // 创建根元素
        Element rootElement = createRootElement(obj, document);

        if (rootElement == null)
        {
            return false;
        }

        // 创建子元素
        createChildrenElement(obj, document, rootElement);

        // 创建TransformerFactory对象
        TransformerFactory tff = TransformerFactory.newInstance();

        // 创建 Transformer对象
        Transformer tf;
        try
        {
            tf = tff.newTransformer();
        } catch (TransformerConfigurationException e)
        {
            e.printStackTrace();
            return false;
        }

        document.appendChild(rootElement);

        // 输出内容是否使用换行
        tf.setOutputProperty(OutputKeys.INDENT, "yes");

        try
        {
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(path + "/" + fileName + ".xml"));
        } catch (TransformerException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * 创建根元素
     *
     * @param obj
     * @param document
     * @return
     */
    public static<T> Element createRootElement(T obj, Document document)
    {
        if (obj == null || document == null)
        {
            return null;
        }

        Class<?> c = obj.getClass();

        RootXml rootXml = c.getAnnotation(RootXml.class);

        String rootXmlName;

        if (rootXml == null)
        {
            rootXmlName = c.getSimpleName();
        }else
        {
            rootXmlName = rootXml.value();

            if ("".equals(rootXmlName))
            {
                rootXmlName = c.getSimpleName();
            }
        }

        // 不显示standalone="no"
        document.setXmlStandalone(true);

        return document.createElement(rootXmlName);

    }


    /**
     * 创建子元素
     *
     * @param obj
     * @param document
     * @param parentElement
     * @return
     */
    public static<T> void createChildrenElement(T obj, Document document, Element parentElement)
    {

        if (obj == null || document == null || parentElement == null)
        {
            return;
        }

        Class<?> c = obj.getClass();

        Field[] objField = c.getDeclaredFields();

        try
        {

            for (Field field : objField)
            {

                String fieldName = field.getName();

                AttrXml attrXml = field.getAnnotation(AttrXml.class);

                // 是否忽略此属性
                boolean ignore = false;
                // 是否为标签参数
                boolean isProperty = false;
                // 是否为内部属性
                boolean isInnerAttr = false;
                // dom 节点名字
                String domName = fieldName;

                if (null != attrXml)
                {

                    if (!"".equals(attrXml.name()))
                    {
                        domName = attrXml.name();
                    }

                    ignore = attrXml.ignore();
                    isProperty = attrXml.isProperty();
                    isInnerAttr = attrXml.isInnerAttr();
                }

                // 如果该属性是被忽略的，则不参与构建xml
                if (ignore)
                {
                    continue;
                }

                // 创建一个属性描述器
                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, c);

                // 获取该属性的 get 方法
                Method method = descriptor.getReadMethod();

                // 调用 get 方法
                Object methodObj = method.invoke(obj);

                if (methodObj == null)
                {
                    methodObj = "";
                    isInnerAttr = false;
                }

                if (isProperty)
                {
                    parentElement.setAttribute(domName, String.valueOf(methodObj));

                } else
                {
                    // 判断是否为内部属性
                    if (isInnerAttr)
                    {
                        if (methodObj instanceof List)
                        {
                            List listObj = (List) methodObj;

                            String finalDomName = domName;

                            listObj.forEach((item) ->
                            {
                                Element element = document.createElement(finalDomName);
                                parentElement.appendChild(element);
                                createChildrenElement(item, document, element);
                            });

                        }else
                        {
                            Element element = document.createElement(domName);
                            parentElement.appendChild(element);
                            createChildrenElement(methodObj, document, element);
                        }

                    }else
                    {
                        if (methodObj instanceof List)
                        {
                            List listObj = (List) methodObj;

                            String finalDomName = domName;
                            listObj.forEach((item) ->
                            {
                                Element element = document.createElement(finalDomName);
                                parentElement.appendChild(element);
                                element.setTextContent(String.valueOf(item));
                            });

                        }else
                        {
                            Element element = document.createElement(domName);
                            parentElement.appendChild(element);
                            element.setTextContent(String.valueOf(methodObj));
                        }
                    }
                }
            }

        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 判断目录是否存在，不存在则创建
     *
     * @param path
     * @return
     */
    public static boolean makeWritableDirectoryIfNotExist(String path)
    {
        if (path == null)
        {
            return false;
        }

        //判断目录是否存在，不存在则创建
        try
        {
            File filePath = new File(path);
            if (!filePath.exists())
            {
                if (!filePath.mkdirs())
                {
                    return false;
                }
            }

            if (!filePath.canWrite())
            {
                return false;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
