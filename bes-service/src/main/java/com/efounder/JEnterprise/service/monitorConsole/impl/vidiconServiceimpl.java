package com.efounder.JEnterprise.service.monitorConsole.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.mapper.monitorConsole.vidiconMapper;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.monitorConsole.vidiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CkassName: vidiconServiceimpl
 * @Author: YangChao
 * @Date: 2019/3/28 15:24
 * @Descruotuib: 监控台摄像机接口实现类
 * @Version: 1.0
 **/
@Service("vidiconService")
public class vidiconServiceimpl  implements vidiconService {

	@Autowired
	private ESZzjgService esZzjgService;
    @Autowired
    private vidiconMapper mapper;


    @Override
    public ISSPReturnObject getLeftTree() {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            // 组织结构业务逻辑编写-- 线路-->路段-->分公司-->摄像机
            // 1.0 查询所有线路
//            List<BESRoute> bESRoute = new ArrayList<>();
            /*道路监控(云台摄像机,固定摄像机,高清一体化摄像机,摄像机,监控室摄像机)
            (00000000014,00000000016,00000000204,00000000206,00000000212,00000000100,00000000178)
            广场(广场摄像机)(00000000061)
            收费亭(收费亭内摄像机)(00000000060)
            车道(ETC车道摄像机)(00000000185)*/
            // 获得一棵树模型的数据
            List<ISSPTreeNode> tree = getTreeJson();
            returnObject.setList(tree);
            returnObject.setMsg("获取组织机构树成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setMsg("获取组织机构树失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    private List<ISSPTreeNode> getTreeJson() {
        // 写死树级头
        List<Map<String, String>> list = getList();
        try {
            // 道路监控
            getdljkList(list);
            // 广场
//            getGcList(list);
            // 收费亭
//            getSftList(list);
            // 车道
//            getCdList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
        List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
        for (Map<String, String> map : list) {
            ISSPTreeNode node = new ISSPTreeNode();
            node.setId(map.get("CId"));
            node.setPid(map.get("PId"));
            node.setText(map.get("CMc"));
            node.setNodeTreeId(map.get("CJs"));
            nodes.add(node);
        }
        List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
        return buildTree;
    }


    // 写死树头
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        // 道路监控传输数组
        List<String> dljk = new ArrayList<>();
       /* dljk.add("00000000014");
        dljk.add("00000000016");
        dljk.add("00000000204");
        dljk.add("00000000206");
        dljk.add("00000000212");
        dljk.add("00000000100");
        dljk.add("00000000178");*/
        dljk.add("00000000014");
        dljk.add("00000000015");
        dljk.add("00000000016");
        dljk.add("00000000059");
        dljk.add("00000000060");
        dljk.add("00000000061");
        dljk.add("00000000100");
        dljk.add("00000000178");
        dljk.add("00000000185");
        dljk.add("00000000200");
        dljk.add("00000000204");
        dljk.add("00000000206");
        dljk.add("00000000212");
        dljk.add("00000012406");
        // 收费站数组
        List<String> sfz = new ArrayList<>();
        sfz.add("00000000061");
        sfz.add("00000000060");
        sfz.add("00000000185");
        // 广场传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000061");
        // 收费亭传输数组
        List<String> sft = new ArrayList<>();
        sft.add("00000000060");
        // 车道传输数组
        List<String> cd = new ArrayList<>();
        cd.add("00000000185");
        Map<String, String> map = new HashMap<>();
        map.put("PId", "");
        map.put("CId", "00");
        map.put("CMc", "齐鲁交通");
        map.put("CJs", "1");
        list.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("PId", "00");
        map1.put("CId", "0010");
        map1.put("CMc", "道路监控" + getSxjSl(dljk));
        map1.put("CJs", "2");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("PId", "00");
        map2.put("CId", "0011");
        map2.put("CMc", "收费站");
        map2.put("CJs", "2");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>();
        map3.put("PId", "0011");
        map3.put("CId", "001111");
        map3.put("CMc", "广场");
        map3.put("CJs", "3");
        list.add(map3);
        Map<String, String> map4 = new HashMap<>();
        map4.put("PId", "0011");
        map4.put("CId", "001112");
        map4.put("CMc", "收费亭");
        map4.put("CJs", "3");
        list.add(map4);
        Map<String, String> map5 = new HashMap<>();
        map5.put("PId", "0011");
        map5.put("CId", "001113");
        map5.put("CMc", "车道");
        map5.put("CJs", "3");
        list.add(map5);
        return list;
    }


    // 道路监控
    public List<Map<String, String>> getdljkList(List<Map<String, String>> list) {
        // 道路监控传输数组
        List<String> dljk = new ArrayList<>();
        dljk.add("00000000014");
        dljk.add("00000000015");
        dljk.add("00000000016");
        dljk.add("00000000059");
        dljk.add("00000000060");
        dljk.add("00000000061");
        dljk.add("00000000100");
        dljk.add("00000000178");
        dljk.add("00000000185");
        dljk.add("00000000200");
        dljk.add("00000000204");
        dljk.add("00000000206");
        dljk.add("00000000212");
        dljk.add("00000012406");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = mapper.getsxtsb_list(dljk);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        List<String> xlid_list = mapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = mapper.GetAllxlList(xlid_list, "3", "0010");
        // 分解List 重新组合
        xlList = comList(xlList);
        // 1.3 线路下边挂组织机构
		String qxbh = "0005";
//		List<ESZzjg> BranchOfficeList =(List<ESZzjg>) isJurisdictionDetermine(qxbh);
		List<String> zzId = new ArrayList<>();
        List<Map<String, String>> xlZzjgList = mapper.GetxlZzjgList(zzId, "4", "0010", "0010");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = mapper.GetzzjgsxtList(dljk, "5", "0010");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 广场
    public List<Map<String, String>> getGcList(List<Map<String, String>> list) {
        // 广场传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000061");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = mapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        List<String> xlid_list = mapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = mapper.GetAllxlList(xlid_list, "4", "001111");
        // 1.3 线路下边挂组织机构
        List<Map<String, String>> xlZzjgList = mapper.GetxlZzjgList(sxtsbList, "5", "001111", "001111");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = mapper.GetzzjgsxtList(gc, "6", "001111");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 收费亭
    public List<Map<String, String>> getSftList(List<Map<String, String>> list) throws Exception {
        // 收费亭传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000060");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = mapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        if (sxtsbList.size() > 0) {
            List<String> xlid_list = mapper.GetxlidList(sxtsbList);

            // 1.2 根据所有线路id查询所有道路监控的线路
            List<Map<String, String>> xlList = mapper.GetAllxlList(xlid_list, "4", "001112");
            // 1.3 线路下边挂组织机构
            List<Map<String, String>> xlZzjgList = mapper.GetxlZzjgList(sxtsbList, "5", "001112", "001112");
            // 1.4 组织机构下边放具体摄像头
            List<Map<String, String>> zzjgsxt_list = mapper.GetzzjgsxtList(gc, "6", "001112");
            //将道路监控数据保存到list中
            list.addAll(xlList);
            list.addAll(xlZzjgList);
            list.addAll(zzjgsxt_list);
        }
        return list;
    }

    // 车道
    public List<Map<String, String>> getCdList(List<Map<String, String>> list) throws Exception {
        // 车道传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000185");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = mapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        if (sxtsbList.size() > 0) {
            List<String> xlid_list = mapper.GetxlidList(sxtsbList);
            // 1.2 根据所有线路id查询所有道路监控的线路
            List<Map<String, String>> xlList = mapper.GetAllxlList(xlid_list, "4", "001113");
            // 1.3 线路下边挂组织机构
            List<Map<String, String>> xlZzjgList = mapper.GetxlZzjgList(sxtsbList, "5", "001113", "001113");
            // 1.4 组织机构下边放具体摄像头
            List<Map<String, String>> zzjgsxt_list = mapper.GetzzjgsxtList(gc, "6", "001113");
            //将道路监控数据保存到list中
            list.addAll(xlList);
            list.addAll(xlZzjgList);
            list.addAll(zzjgsxt_list);
        }
        return list;
    }

    // 摄像机数量
    public String getSxjSl(List<String> list) {
        String zc = mapper.GetSxtzcSum(list);
        String bj = mapper.GetSxtbjSum(list);
        String yc = mapper.GetSxtycSum(list);
        return "(" + zc + "/" + bj + "/" + yc + ")";
    }

    // 线路id查询摄像机数量
    public String getSxjXlSl(List<String> list) {
        String zc = mapper.GetSxtXlzcSum(list, "0");
        String bj = mapper.GetSxtXlzcSum(list, "1");
        String yc = mapper.GetSxtXlzcSum(list, "2");
        return "(" + zc + "/" + bj + "/" + yc + ")";
    }

    // 分解List添加()数量
    public List<Map<String, String>> comList(List<Map<String, String>> list) {
        List<Map<String, String>> retutnList = new ArrayList<>();
        for (Map<String, String> map : list) {
            // 查询
            List<String> list1 = new ArrayList<>();
            list1.add(map.get("F_XLBH"));
            Map<String, String> map1 = new HashMap<>();
            map1.put("PId", map.get("PId"));
            map1.put("CId", map.get("CId"));
            map1.put("CMc", map.get("CMc") + getSxjXlSl(list1));
            map1.put("CJs", map.get("CJs"));
            retutnList.add(map1);
        }
        return retutnList;
    }

    @Override
    public ISSPReturnObject getData(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String nodeId = request.getParameter("nodeId");
        List<Map<String, String>> list = mapper.getData(nodeId);
        returnObject.setList(list);
        return returnObject;
    }

    @Override
    public ISSPReturnObject getVidiconList(HttpServletRequest request) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String, String>> list = mapper.getVidiconList();
        returnObject.setList(list);
        return returnObject;
    }
}

