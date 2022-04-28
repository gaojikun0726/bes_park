package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.common.util.G4Utils;
import com.efounder.JEnterprise.collectorJob.LEMSConstants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesZoneinfoMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.CrossEquipmentMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesRepertory;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZone;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZoneinfo;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZonemode;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESCjpzService;
import com.framework.common.utils.Validate_y;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author  杨超
* @version 创建时间：2018年9月26日 下午2:54:29
* @parameter 
* @version 1.0
*/
@Service("BESCjpzService")
public class BESCjpzServiceimpl implements BESCjpzService{

	@Autowired
	private BesZoneinfoMapper beszoneinfomapper;
	
	@Autowired
	private CrossEquipmentServiceImpl crossequipmentservice;
	
	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;
	@Autowired
	private BESSbdyMapper besSbdyMapper;

	
	
	/**
	 * 场景配置树查询
	 */
	@Override
	public ISSPReturnObject cjpz_tree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取场景配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取场景配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	private List<ISSPTreeNode> getTreeJson() {
		List<BesZoneinfo> list = beszoneinfomapper.cjpz_tree();// 从数据库获取所有场景配置
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (BesZoneinfo map : list) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(map.getfType());
			node.setId(map.getfId());
			node.setPid(map.getfParentid());//父级ID
			node.setText(map.getfName());
			nodes.add(node);// 添加到节点容器
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

	//新增文件夹
	@Override
	public ISSPReturnObject addFolder(BesZoneinfo obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String maxId = beszoneinfomapper.queryMaxId();
		obj.setfId(getAutoIncreaseCol(maxId));//主键	改为自增主键 每次加1
		obj.setfType("1");// 1 代表文件夹
		obj.setfZoneid("0");//文件夹存0  场景存1
		boolean flag = beszoneinfomapper.addFolder(obj);
		if(flag) {
			returnObject.setMsg("添加文件夹成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("添加文件夹失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	
	/**
	 * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
	 * @param col 该列当前最大值
	 * @return
	 */
	private String getAutoIncreaseCol(String col) {
		if (col == null || "".equals(col)) {
			return "1";
		}
		String regex = "^([0]+)([\\d]*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(new StringBuffer(col));
		if (matcher.find()) {
			return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
		} else {
			return String.valueOf(Integer.parseInt(col) + 1);
		}
	}

	/**
	 * 删除操作
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException
	 * -- nodeId 场景id bes_zone表
	 */
	@SuppressWarnings("static-access")
	@Override
	@Transactional
	public ISSPReturnObject del_tree(String nodeId,String f_type) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            // 下位机返回状态
            int reStr = 0 ;
            boolean flag = false;
            // nodeId 场景id -- 1.0 先删除下位机场景模块 再删除上位机场景模块
            Map<String,String> map = beszoneinfomapper.getddcxx(nodeId);
            Map<String,Object> pointMap = new HashMap<>();
            if(map != null){
                pointMap.put("IPAddr", map.get("IPAddr"));
                pointMap.put("Port", map.get("Port"));
                pointMap.put("Cjid", Integer.parseInt(nodeId));
            }else {

            }
            //打开通讯则删除上位机删除
            flag = beszoneinfomapper.Delcj("bes_zone","f_id",nodeId);
            flag = beszoneinfomapper.Delcj("bes_zonemode","f_zoneid",nodeId);
            flag = beszoneinfomapper.Delcj("bes_zoneinfo","f_id",nodeId);
            flag = beszoneinfomapper.Delcj("bes_repertory","f_zoneid",nodeId);
            if(flag){
                Dto retDto = crossequipmentservice.operCrossController(pointMap, 24);//同步逻辑点信息，index设置为16
                reStr = retDto.getAsInteger("code");
                //下位机删除场景成功
                if(reStr == 0) {
                    if(flag){
                        returnObject.setMsg("删除场景成功");
                        returnObject.setStatus("1");
                    }
                    else{
                        returnObject.setMsg("下位机删除场景失败");
                        returnObject.setStatus("1");
                    }
                }else {
                    returnObject.setMsg("下位机删除场景失败");
                    returnObject.setStatus("0");
                }
            }else{
                returnObject.setMsg("上位机删除场景失败");
                returnObject.setStatus("0");
            }
		}catch(Exception e){
			e.printStackTrace();

		}
		return returnObject;
	}
	
	/**
	 * 下位机数据格式转换
	 * @param reStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Dto parseReturnStr(String reStr) {
		Dto pDto = new BaseDto();
		if (G4Utils.isNotEmpty(reStr)) {
			pDto = getOjectFromJson(reStr);
		} else {
			pDto.put("errmsg", LEMSConstants.Service_Err);
		}
		return pDto;
	}
	
	/**
	 * Oject2son
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public Dto getOjectFromJson(String jsonStr) {
		Dto pDto = new BaseDto();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			if ("tm".equals(key) || "CollectControllerParameter".equals(key) || "MeterParameter".equals(key)
					|| "MeterElectricData".equals(key) || "ElectricDataCollectMethod".equals(key)
					|| "MeterHisElectricData".equals(key)) {
				JSONObject jsb = jsonObject.fromObject(jsonObject.get(key));
				Dto tDto = new BaseDto();
				for (Iterator titer = jsb.keys(); titer.hasNext();) {
					String tkey = (String) titer.next();
					tDto.put(tkey, jsb.get(tkey));
				}
				pDto.put(key, tDto);
			} else if ("CollectControllerInfo".equals(key) || "ElectricDataInfo".equals(key)) {
				List<Dto> ls = new ArrayList<Dto>();
				JSONObject jsb = jsonObject.fromObject(jsonObject.get(key));
				for (Iterator titer = jsb.keys(); titer.hasNext();) {
					String tkey = (String) titer.next();
					Dto collDto = new BaseDto();
					JSONObject jsonCollector = jsonObject.fromObject(jsb.get(tkey));
					for (Iterator titColl = jsonCollector.keys(); titColl.hasNext();) {
						String collKey = (String) titColl.next();
						collDto.put(collKey, jsonCollector.get(collKey));
					}
					ls.add(collDto);
				}
				pDto.put(key, ls);
			} else {
				pDto.put(key, jsonObject.get(key));
			}
		}
		return pDto;
	}
	
	/**
	 * 循环获取所有子节点	nodeId 节点id
	 * @param nodeId
	 * @param
	 * @return
	 */
	public void findAllnodeId(String nodeId,List<String> nodeIds){
		List<Map<String, String>> nodelist=beszoneinfomapper.getnodeId(nodeId);//根据节点id查询下级
		for(Map<String, String> map:nodelist) {
			nodeIds.add(map.get("F_ID"));
			findAllnodeId(map.get("F_ID"), nodeIds);
		}
	}

	//场景模式下拉框
	@Override
	public ISSPReturnObject select_cjms() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> list = beszoneinfomapper.select_cjms();
			returnObject.setList(list);
			returnObject.setMsg("查询场景模式成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询场景模式失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	//场景模式根绝id查询详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ISSPReturnObject select_cjmsxq(String id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> list = beszoneinfomapper.select_cjmsxq(id);//查询list
			List<Map<String,Object>> paramlist=new ArrayList<>();
			if(list.size()>0) {
				Map map = new HashMap<>();
				String param=(String) list.get(0).get("F_PARAM");
				String zondid=(String) list.get(0).get("F_ZONEID");
				List<Map<String,Object>> relist = beszoneinfomapper.select_cjnrxq(zondid);//查询内容list
				String arr [] =param.split("#");
				StringBuilder tr=new StringBuilder();
				StringBuilder theadtr=new StringBuilder();//table内容拼装
				tr.append("<tr>");
				for(int i=0;i<arr.length;i++) {
					tr.append("<th>"+arr[i]+"</th>");
				}
				tr.append("</tr>");
				map.put("tr",tr);
				//开始pin内容 判断几个tr
				if(relist.size()>0) {
					for(int j=0;j<relist.size();j++) {//循环几次 有几个tr
						String dname=(String) relist.get(j).get("NAME");
						String nr=(String)relist.get(j).get("NR");
						String nrarr []=nr.split("#");
						theadtr.append("<tr>");
						theadtr.append("<th>"+dname+"</th>");
							for(int i=0;i<nrarr.length;i++) {
								theadtr.append("<th>"+nrarr[i]+"</th>");
							}
						theadtr.append("</tr>");
						map.put("theadtr", theadtr);
					}
				}
				paramlist.add(map);
			}
			returnObject.setList(paramlist);
			returnObject.setMsg("查询场景模式成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询场景模式失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 新增场景保存
	 */
	@SuppressWarnings("unused")
	@Override
    @Transactional
	public ISSPReturnObject addScene(BesZone obj,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, RemoteException, ServiceException{
		String array=request.getParameter("pin_tabletrtd");//table内 tr内容拼装数组
		String tabletd=request.getParameter("pin_tabletd");//table表头拼装
		String cjms_name=request.getParameter("cjms_name");//场景模式名称
		String mslx=request.getParameter("mode_mslx");//bes_zonemode 模式类型
		ISSPReturnObject returnObject = new ISSPReturnObject();
        String operateType = obj.getOperateType();// 操作类型 C//新增 U//修改
        String maxId = beszoneinfomapper.queryMaxId();
        String momaxId = beszoneinfomapper.queryMoMaxId();
        obj.setfId(getAutoIncreaseCol(maxId));// 主键 改为自增主键 每次加1
        if ("C".equals(operateType)) {// 新增
            // 先保存bes_zoneinfo 1.
            BesZoneinfo dto = new BesZoneinfo();
            dto.setfId(getAutoIncreaseCol(maxId));
            dto.setfName(obj.getfSysName());
            dto.setfParentid(obj.getfParentid());
            dto.setfType(obj.getfType());// bes_zoneinfo 2. 控制场景 3.采集场景 bes_zone 0.控制场景 1.采集场景 需要转换
            dto.setfZoneid("1");
            boolean zoneinfo_flag = beszoneinfomapper.addFolder(dto);
            // 保存到两个表 bes_zoneinfo树 bes_zone 场景信息表 2.
            String type = obj.getfType();//
            if ("2".equals(type)) {// 控制场景
                obj.setfType("0");
            } else if ("3".equals(type)) {
                obj.setfType("1");
            } else {
            }
            if (Validate_y.isNull(obj.getfZonemodeId())) {
                obj.setfZonemodeId(momaxId);
            }
            obj.setfZonemodeId(getAutoIncreaseCol(momaxId));
            boolean bes_zone_flag = beszoneinfomapper.addScene(obj);
            // 保存模式信息到bes_zonemode 3.
            BesZonemode mo = new BesZonemode();
            mo.setfId(getAutoIncreaseCol(momaxId));// id
            mo.setfName(cjms_name);
            mo.setfType(mslx);// 模式类型
            mo.setfSize("2");// 场景模式的模式个数
            mo.setfDescription("");// 场景模式的描述
            if (Validate_y.noNull(tabletd)) {
                tabletd = tabletd.substring(0, tabletd.length() - 1);
            } else {
                tabletd = "";
            }
            mo.setfParam(tabletd);// 场景对应的内容 #隔开
            mo.setfZoneid(getAutoIncreaseCol(maxId));// 场景id
            mo.setfUsedNum("1");// 被使用次数
            boolean bes_zonemode_flag = beszoneinfomapper.addZoneMode(mo);
            // 保存模式详细信息 bes_repertory
            BesRepertory re = new BesRepertory();
            // 验证非空
            if (Validate_y.noNull(array)) {
                // 循环保存
                String arr[] = array.split(",");
                for (int i = 0; i < arr.length; i++) {
                    String remaxId = beszoneinfomapper.queryReMaxId();
					String NodeId = arr[i].substring(0, arr[i].indexOf("#"));// 获取点的id
                    String NodeName = arr[i].substring(arr[i].indexOf("#")+1, arr[i].indexOf("#",arr[i].indexOf("#")+1));// 获取点的名字
                    String param = arr[i].substring(arr[i].indexOf("#",arr[i].indexOf("#")+1)+1, arr[i].length());// 配置内容
                    re.setfId(getAutoIncreaseCol(remaxId));// id
                    re.setfNodeName(NodeName);// 点的名称
                    re.setfUnit("");// 点的单位
					String fSbid = beszoneinfomapper.getSbid(NodeId);
                    re.setfNodeId(fSbid);// 点的sbid
                    re.setfSize("");// 场景模式的模式个数
                    re.setfParam(param);// 每个指令表对应配置的内容(#隔开)
                    re.setfZoneid(getAutoIncreaseCol(maxId));// '采集方式ID集合（数字0-9）和param一一对应 0 变化量采集 1 时间间隔'
                    boolean bes_repertory = beszoneinfomapper.addRepertory(re);
                }
            }
            //通用新增同步接口
            Map<String,Object> map = CommonInterface(obj.getfId(),22);
            Dto retDto = crossequipmentservice.operCrossController(map, 22);//设置一个场景的所有参数
            int reStr = retDto.getAsInteger("code");
            //下位机返回成功
            if(reStr == 0){
                if (zoneinfo_flag && bes_zone_flag && bes_zonemode_flag) {
                    returnObject.setMsg("添加场景成功");
                    returnObject.setStatus("1");
                } else {
                    returnObject.setMsg("下位机添加场景失败");
                    returnObject.setStatus("0");
                }
            }
        }else{
            returnObject.setMsg("上位机添加场景失败");
            returnObject.setStatus("0");
        }
		return returnObject;
	}

	/**
	 * 根绝id查询bes_zone详情
	 */
	@Override
	public ISSPReturnObject select_zonexq(String id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			list = beszoneinfomapper.select_zonexq(id);//查询list
			returnObject.setList(list);
			returnObject.setMsg("查询场景模式成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询场景模式失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getipaddr(String f_sys_name, String f_psys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, Object> ddcinfo = new HashMap<>();//所属DDC控制器信息
			//查询该节点的实体表名
			String tabName = besSbdyMapper.findNodeTable(f_sys_name);
			if (tabName.equals("BES_VPOINT")) {
				// 查询逻辑虚点所属DDC控制器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_sys_name);
			} else {
				//查询逻辑点所属DDC控制器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint(f_sys_name);
			}
			returnObject.setMap(ddcinfo);
			returnObject.setMsg("查询ip成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询ip失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getlampipaddr(String f_sys_name, String f_psys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, Object> ddcinfo = new HashMap<>();//所属DDC控制器信息
			//查询该节点的实体表名
			String tabName = besSbdyMapper.findNodeTable(f_sys_name);
			if (tabName.equals("BES_VPOINT")) {
				// 查询逻辑虚点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_sys_name);
			} else {
				//查询逻辑点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			}
			List<Map<String,Object>> list = new ArrayList<>();
			list.add(ddcinfo);
			returnObject.setList(list);
			returnObject.setMsg("查询ip成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询ip失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 根据场景id查询信息返回接口	同步数据
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unused")
	@Override
	public ISSPReturnObject CjTbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
            //通用新增同步接口
            Map<String,Object> map = CommonInterface(cjid,23);
			Dto retDto = crossequipmentservice.operCrossController(map, 23);//设置一个场景的所有参数	index=23
			int reStr = retDto.getAsInteger("code");
			if(reStr == 0) {//下位机同步数据成功
				returnObject.setMsg("同步数据成功");
				returnObject.setStatus("1");
			}else {
				returnObject.setMsg("同步数据失败");
				returnObject.setStatus("0");
			}
		return returnObject;
	}


	/**
	* @Description: 新增同步场景共用
	* @param cjid 场景id index
	* @return pointMap
	* @author YangChao
	* @date 2019/8/21 10:43
	*/
	private Map<String,Object> CommonInterface(String cjid,int index){
		Map<String,Object> pointMap = new HashMap<>();

		try {
			//根据cjid查询bes_zone
			Map<String,String> zone_map = beszoneinfomapper.interface_zoneMap(cjid);
			//根据cjid查询bes_zonemode
			Map<String,String> mode_map = beszoneinfomapper.interface_modeMap(cjid);
			//根据cjid查询bes_repertory
			List<Map<String,String>> re_list = beszoneinfomapper.interface_reMap(cjid);
			//根据关联id查询bes_ddc表信息
			Map<String,String> ddc_map = beszoneinfomapper.interface_ddcMap(zone_map.get("F_ParentID"));

			pointMap.put("IPAddr", ddc_map.get("F_IP_ADDR"));
			pointMap.put("Port", ddc_map.get("F_PORT"));
			pointMap.put("item", "cross");
			pointMap.put("index", index);
			Map<String,Object> data=new HashMap<>();
			data.put("SceneType", Integer.parseInt(zone_map.get("F_TYPE")));//控制场景
			Map<String,Object> data2=new HashMap<>();
			data2.put("SceneType", Integer.parseInt(zone_map.get("F_TYPE")));//控制场景
			data2.put("ID",Integer.parseInt(zone_map.get("ID")));//控制场景ID
			data2.put("Active",Integer.parseInt(zone_map.get("F_ENABLED")));//是否启用
			data2.put("Name", zone_map.get("F_SYS_NAME"));//场景模式名称
			data2.put("Alias", zone_map.get("F_NICK_NAME"));//场景模式别名
			List<Map<String,Object>> CollectMode=new ArrayList<>();//场景模式

			/*
			 *	重新编写硬件所需接口逻辑
			 *	1.0 10个模式
			 * 模式下边套 点位信息
			 * 一个模式对应一列点位
			 */
			// 1.0 循环场景模式 放多个模式
			String F_PARAM = mode_map.get("F_PARAM");
			if(Validate_y.noNull(F_PARAM)){
				F_PARAM = F_PARAM.substring(F_PARAM.indexOf("#")+1,F_PARAM.length());
				String ModeArray [] = F_PARAM.split("#");
				for(int i=0;i<ModeArray.length;i++){
					Map<String,Object> CollectModeMap=new HashMap<>();
					CollectModeMap.put("ID", (i+1));//场景模式id--模式id从1开始
					CollectModeMap.put("Active", 1);//使能状态
					CollectModeMap.put("Name", ModeArray[i]);//场景模式名称
					List<Map<String,Object>> CollectPoint=new ArrayList<>();//点list
					//获取模式里面点位信息
					if(re_list.size()>0){
						// 循环点位的list
						for(int j=0;j<re_list.size();j++){
							String PointArray [] =re_list.get(j).get("F_PARAM").split("#");
							int ModeArrayLenght = ModeArray.length;
							int length = PointArray.length;
							Map<String,Object> CollectPointMap1=new HashMap<>();
							CollectPointMap1.put("Active", 1);//使能状态
							CollectPointMap1.put("PointID", Integer.parseInt(re_list.get(j).get("F_NODE_ID")));//计划所对应点的ID号
							CollectPointMap1.put("RunValue", Integer.parseInt(PointArray[i]));//运行值,数字量0或255，模拟量是个数值
							CollectPoint.add(CollectPointMap1);
						}
					}
					CollectModeMap.put("CollectPoint",CollectPoint);
					CollectMode.add(CollectModeMap);
				}
			}
			data2.put("CollectMode", CollectMode);
			data.put("data", data2);
			pointMap.put("data",data);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pointMap;
	}

	public static void main(String[] args) {
		String F_PARAM = "0#255#1#";
		//F_PARAM = F_PARAM.substring(F_PARAM.indexOf("#"),F_PARAM.length());
		String ModeArray [] = F_PARAM.split("#");
		for(int i=0;i<ModeArray.length;i++){
			System.err.println(ModeArray[i]);
		}
		System.err.println(ModeArray.length);
	}

	/**
	 * 根据场景id查询信息返回接口	对比数据
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject CjDbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
			try {
                //根据cjid查询bes_zone
                Map<String,String> zone_map = beszoneinfomapper.interface_zoneMap(cjid);
                //根据关联id查询bes_ddc表信息
                Map<String,String> ddc_map = beszoneinfomapper.interface_ddcMap(zone_map.get("F_ParentID"));
                Map<String,Object> pointMap = new HashMap<>();
                pointMap.put("IPAddr", ddc_map.get("F_IP_ADDR"));
                pointMap.put("Port", ddc_map.get("F_PORT"));
                pointMap.put("item", "lamp");
                pointMap.put("index", 25);
                Map<String,Object> data=new HashMap<>();
                data.put("ID", Integer.parseInt(zone_map.get("ID")));//场景ID
                pointMap.put("data",data);
                Dto retDto = crossequipmentservice.operCrossController(pointMap, 25);//设置一个场景的所有参数	index=23
				int reStr = retDto.getAsInteger("code");
				//下位机同步数据成功
				if(reStr == 0) {
					Map<String,Object> xDto = (Map<String,Object>) retDto.get("data");
					xDto.get("SceneType");//控制场景
					Map<String,Object> dDto = (Map<String,Object>) xDto.get("data");
					dDto.get("SceneType");//控制场景
					dDto.get("ID");//控制场景的ID
					dDto.get("Active");//是否启用
					Map<String,Object> cDto = (Map<String,Object>) dDto.get("CollectModeDDC");
					cDto.get("Active");//使能状态
					cDto.get("Name");//使能状态
					cDto.get("ID");//场景模式ID
					List<Map<String,Object>> clist = (List<Map<String,Object>>) cDto.get("CollectPointDDC");
					if("0".equals(xDto.get("SceneType"))) {//控制场景
						for(Map<String,Object> clist_map : clist) {
							clist_map.get("Active");//使能状态
							clist_map.get("PointID");//计划所对应点的ID号
							clist_map.get("RunValue");//运行值,数字量0或255，模拟量是个数值
							//clist_map.get("DelayTime");//延时执行时间
						}
					}
					returnObject.setMsg("获取场景信息成功");
					returnObject.setStatus("1");
				}else {
					returnObject.setMsg("获取场景信息失败");
					returnObject.setStatus("0");
				}
			}catch (Exception e){
				e.printStackTrace();
				e.getMessage();
				returnObject.setMsg("获取场景信息失败");
				returnObject.setStatus("0");
			}
		return returnObject;
	}

}
