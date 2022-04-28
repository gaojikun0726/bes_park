package com.efounder.JEnterprise.util;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.*;

@Component
public class DDCrelevant {

    @Autowired
    private BESSbdyService besSbdyService;

    @Autowired
    private BESSbdyMapper besSbdyMapper;

    @Autowired
    private SbPzStructCache sbPzStructCache;

    String id ;

    List<Map<String,Object>> newlist = new ArrayList<>();


    /**
     * 判断控制添加DDC有关
     * @param obj
     * @param returnObject
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
    public ISSPReturnObject judgeDDC(JSONObject obj,ISSPReturnObject returnObject) throws UnsupportedEncodingException, RemoteException, ServiceException {
        if (returnObject.getStatus().equals("1")) {
            String msg = returnObject.getMsg();
            return this.addxudian(obj, msg);
        } else return returnObject;
    }

        public ISSPReturnObject judgeDDCLamp(JSONObject obj,ISSPReturnObject returnObject) throws UnsupportedEncodingException, RemoteException, ServiceException {
            if (returnObject.getStatus().equals("1")){
                String msg = returnObject.getMsg();
                return   this.addxudianLamp(obj,msg);
            }else return returnObject;


    }

    /**
     * 默认添加虚点
     * @param obj
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
    public ISSPReturnObject addxudian(JSONObject obj,String msg) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        JSONObject objxudian = new JSONObject();
        objxudian.put("f_psys_name",obj.get("f_sys_name").toString());
        objxudian.put("tabName",obj.get("tabName").toString());
        String f_sys_name = besSbdyService.selectSYS_NAME(obj.get("f_sys_name").toString());
        objxudian.put("f_sys_name",f_sys_name);
        id = f_sys_name;
        objxudian.put("f_type","24");
        objxudian.put("f_nick_name","虚点");
        objxudian.put("f_allpath",obj.get("f_allpath").toString()+">虚点");
        returnObject = besSbdyService.addSbdyInfoByTreeBtn(objxudian);
        if (returnObject.getStatus().equals("1")){

            /*添加到缓存设备结构树 start*/

            BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(f_sys_name);

            if(sbPzStruct != null)
            {
                sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
            }

            /*添加到缓存设备结构树 end*/

            return this.addzongxian(obj,f_sys_name,msg);
        }else return returnObject;
    }
    public ISSPReturnObject addxudianLamp(JSONObject obj,String msg) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        JSONObject objxudian = new JSONObject();
        objxudian.put("f_psys_name",obj.get("f_sys_name").toString());
        objxudian.put("tabName",obj.get("tabName").toString());
        String f_sys_name = besSbdyService.selectSYS_NAME(obj.get("f_sys_name").toString());
        objxudian.put("f_sys_name",f_sys_name);
        id = f_sys_name;
        objxudian.put("f_type","24");
        objxudian.put("f_nick_name","虚点");
        objxudian.put("f_allpath",obj.get("f_allpath").toString()+">虚点");
        returnObject = besSbdyService.addSbdyInfoByTreeBtn(objxudian);
        newlist=besSbdyService.judge(id);
        returnObject.setMsg(msg);
        returnObject.setList(newlist);

        /*添加到缓存设备结构树 start*/

        BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(f_sys_name);

        if(sbPzStruct != null)
        {
            sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
        }

        /*添加到缓存设备结构树 end*/

        /*if (returnObject.getStatus().equals("1")){
            return this.addzongxian(obj,f_sys_name,msg);
        }else */return returnObject;
    }


    /**
     * 默认添加总线
     * @param obj
     * @param f_sys_name
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
    public ISSPReturnObject addzongxian(JSONObject obj,String f_sys_name,String msg) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        JSONObject objxudian = new JSONObject();
        objxudian.put("f_psys_name",obj.get("f_sys_name").toString());
        objxudian.put("tabName",obj.get("tabName").toString());
        String f_sys_name1 = besSbdyService.selectSYS_NAME(f_sys_name);
        objxudian.put("f_sys_name",f_sys_name1);
        objxudian.put("f_type","8");
        objxudian.put("f_nick_name","总线");
        objxudian.put("f_allpath",obj.get("f_allpath").toString()+">总线");
        returnObject = besSbdyService.addSbdyInfoByTreeBtn(objxudian);
        newlist=besSbdyService.judge(id);
        returnObject.setMsg(msg);
        returnObject.setList(newlist);

        /*添加到缓存设备结构树 start*/

        BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(f_sys_name1);

        if(sbPzStruct != null)
        {
            sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
        }

        /*添加到缓存设备结构树 end*/

        /*if (returnObject.getStatus().equals("1")){
            obj.put("f_allpath",objxudian.get("f_allpath"));
            return this.addzongxia(obj,f_sys_name1);
        }else*/ return returnObject;
    }


    /**
     * 默认总线下添加5种模式
     * @param obj
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
	 //wanghongjie 修改
    public ISSPReturnObject addzongxia(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        JSONObject objxudian = new JSONObject();
        Map map  = new HashMap();
        map.put("1","PNP");map.put("2","FLN1");map.put("3","FLN2");map.put("4","FLN3");map.put("5","FLN4");
        objxudian.put("f_psys_name",obj.get("f_sys_name").toString());
        objxudian.put("tabName",obj.get("tabName").toString());
        objxudian.put("f_type","23");
        objxudian.put("f_allpath",obj.get("f_allpath").toString());
        for(Object key:map.keySet()){
            objxudian.put("f_nick_name",map.get(key).toString());
            String f_sys_name1 = besSbdyService.selectSYS_NAME(obj.get("f_sys_name").toString());
            objxudian.put("f_sys_name",f_sys_name1);
            returnObject = besSbdyService.addSbdyInfoByTreeBtn(objxudian);
            if (returnObject.getStatus().equals("0")){
                return returnObject;
            }
        }
        newlist = besSbdyService.judge(id);
        for (int i = 0; i < newlist.size(); i++){//24是虚点的类型,将其去掉,这块代码有空了再优化
            if (newlist.get(i).get("F_TYPE").equals(24)){
                newlist.remove(i);
            }
        }
        for (int j = 0; j < newlist.size(); j++){//8是虚点的类型,将其去掉,这块代码有空了再优化
            if (newlist.get(j).get("F_TYPE").equals(8)){
                newlist.remove(j);
            }
        }
        returnObject.setList(newlist);
        return returnObject;
    }


    /**
     * 根据ID查个数
     * @param obj
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
    public ISSPReturnObject judge(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> list = besSbdyService.judge(obj.get("f_psys_name").toString()+"1");

        Iterator<Map<String,Object>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Map<String,Object> map = iterator.next();
            Integer F_TYPE = (Integer) map.get("F_TYPE");
            if (F_TYPE != 23) {
                iterator.remove();
            }
        }

        if (list.size()<5){
            returnObject = besSbdyService.addSbdyInfoByTreeBtn(obj);
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("超过新增线路数量限制");
        }
        return returnObject;
    }





}
