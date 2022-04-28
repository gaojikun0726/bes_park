package com.efounder.JEnterprise.service.fileDownload;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.filedownload.expExcelMapper;
import com.efounder.JEnterprise.model.excelres.ExcelReturn;
import com.framework.common.utils.ExcelUtil;
import com.framework.common.utils.Validate_y;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CkassName: expExcelServiceimpl
 * @Author: YangChao
 * @Date: 2019/1/21 10:18
 * @Descruotuib:通用导出接口
 * @Version: 1.0
 **/
@Service("/expExcelService")
public class expExcelServiceimpl implements expExcelService {
    private static final Logger log = LoggerFactory.getLogger(expExcelServiceimpl.class);

    @Autowired
    private expExcelMapper expExcelMapper;

    /**
     * @Author: YangChao
     * @CreateDate: 2019/1/21 10:25
     * @Description: 通用导出接口(实体类版)--数据和表名同时传,默认按照数据导出
     */
    @SuppressWarnings("unchecked")
    @Override
    public ISSPReturnObject exportExcel(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // excel的列头
        List<Object> alias = jsonToList(request.getParameter("alias"));
        // 数据List中的Map的key值.
        List<Object> names = jsonToList(request.getParameter("names"));
        // 表名
        String tablename = request.getParameter("tablename");
        // 数据list
        List<Object> jsonList = jsonToList(request.getParameter("jsonList"));

        //新建list
        List<Map<String, Object>> list = new ArrayList<>();

        if(Validate_y.noNull(tablename)){
            //传表名
            list = expExcelMapper.expExcel(listToString(names), tablename);
        }else{
            //传数据
            for (int i = 0; i < jsonList.size(); i++) {
                Map<String,Object> map = new HashMap<>();
                for(int j = 0;j<names.size();j++) {
                    String key = (String) names.get(j);
                    Map<String, Object> newMap = (Map<String, Object>) jsonList.get(i);
                    Object val = newMap.get(key);
                    map.put(key, val);
                }
                list.add(map);
            }
        }

        // 创建工具类.
        ExcelUtil<Object> util = new ExcelUtil<Object>(Object.class);
        // 临时文件名
        String file = System.currentTimeMillis() + "";
        // sheet页名称
        String FileName = "sheet";
        // 导出excel地址
        String FilePath = request.getServletContext().getRealPath("/") + "WEB-INF\\file\\excel\\" + file + ".xls";
        // 导出方法
        ExcelReturn res = util.resListDynamic(FileName, FilePath, list, alias, names);
        Map<String, String> map = new HashMap<>();
        // 1.成功 0.失败
        map.put("status", res.getStatus());
        map.put("file", file);
        returnObject.setMap(map);
        return returnObject;
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 16:33
    * @Description:    通用删除接口
    */
    @Override
    public ISSPReturnObject delete(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 主键id
        String id = request.getParameter("id");
        // 主键列名
        String col = request.getParameter("col");
        // 表名
        String tablename = request.getParameter("TableName");
        // 执行sql
        boolean flag = expExcelMapper.Delete(id,col,tablename);
        if(flag){
            returnObject.setStatus("1");
            returnObject.setMsg("删除成功！");
        }else{
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败！");
        }
        return returnObject;
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 17:52
    * @Description:    通用查询接口
    */
    @Override
    public ISSPReturnObject selectVlaues(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 主键id
        String id = request.getParameter("id");
        // 主键列名
        String col = request.getParameter("col");
        // 表名
        String TableName = request.getParameter("TableName");
        // 数据
        List<Map<String,Object>> list = expExcelMapper.selectVlaues(id,col,TableName);
        returnObject.setList(list);
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月22日 上午11:15:28
     * @Description:字符串转换
     * @param s
     * @return List<Object>
     */
    public static List<Object> jsonToList(String s){
        List<Object> list =new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.fromObject(s);
            for (int i=0;i<jsonArray.size();i++){
                list.add(jsonArray.get(i));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月23日 上午10:04:19
     * @Description:处理list数组转String
     * @param list
     * @return String
     */
    public static String listToString(List<Object> list) {
        String a = "";
        if (!list.isEmpty() && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                a += list.get(i) + ",";
            }
            a = a.substring(0, a.length() - 1);
        }
        return a;
    }

}
