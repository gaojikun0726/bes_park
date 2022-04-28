package com.efounder.JEnterprise.zhdg.controller;

import com.efounder.JEnterprise.platform.util.SwitchControlObject;
import com.efounder.JEnterprise.platform.websocket.config.PduStatusData;
import com.efounder.JEnterprise.timer.manage.SwitchStatusType;
import com.efounder.JEnterprise.zhdg.service.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * describe: PDU管理-控制器
 *
 * @author zs
 * @date 2021/12/2
 */
@Controller
@RequestMapping("/zhdg/pole/pdu")
public class PduManageController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PduManageController.class);


    @Resource
    private PointService pointService;


    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/zhdg/pdu";


    /**
     * PDU管理页面
     * @return
     */
    @RequestMapping("/pageView")
    public String view(){
        return prefix + "/pduManage";
    }


    /**
     * 查询PDU列表
     * @param request
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Map queryList(HttpServletRequest request){
        //组装数据，从point表中查询出所有的PDU IP，展示各路的开关状态，和pdu本身的在线状态，这样页面上状态是否正常，一目了然

        List<String> pduIpList = pointService.queryAllPduIp();
        List<Map<String,String>> list = new ArrayList<>();
        pduIpList.forEach(pduIp -> {
            Map<String,String> itemMap = new HashMap<>();
            itemMap.put("pduIp",pduIp);
            Map dataMap = PduStatusData.switchStatusMap.get(pduIp);

            if(dataMap != null && dataMap.size() > 0){
                dataMap.forEach((name,num) -> itemMap.put(String.valueOf(name),String.valueOf(num)));
            }else{
//                itemMap.put(SwitchControlObject.big_light.name(),null);
                for (SwitchControlObject ele : SwitchControlObject.values()) {
                    itemMap.put(ele.name(), SwitchStatusType.switchOFF.getCode());
                }
            }
            //PDU在线状态
            String onlineStatus = PduStatusData.pduOnlineStatusMap.get(pduIp);
            itemMap.put("online_status",onlineStatus);
//            itemMap.putAll(dataMap);
            list.add(itemMap);
        });

        Map<String,Object> result = new HashMap<>();
        result.put("rows",list);
        result.put("total",list.size());
        result.put("code",0);
        result.put("data",list);
        result.put("count",list.size());
        return result;
    }

}
