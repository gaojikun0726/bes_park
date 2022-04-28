package com.efounder.JEnterprise.service.dataAnalysises.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesBranchDataMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;
import com.efounder.JEnterprise.service.dataAnalysises.BESZlqsfxService;
import com.framework.common.utils.ScopeDataUtil;
import com.framework.common.utils.Validate_y;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author 杨超
 * @version 创建时间：2018年10月17日 上午11:03:25
 * @parameter
 * @version 1.0
 */
@Service("BESZlqsfxService")
public class BESZlqsfxServiceimpl implements BESZlqsfxService {
	private static final Logger log = LoggerFactory.getLogger(BESZlqsfxServiceimpl.class);

	@Autowired
	private BesBranchDataMapper besbranchdatamapper;

	/**
	 * 查询tab-list信息
	 */
	@Override
	public ISSPReturnObject getZl_tablist() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Map<String, String>> list = besbranchdatamapper.getZl_tablist();
			returnObject.setList(list);
			returnObject.setMsg("success");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("fail");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 获取支路配置树
	 */
	@Override
	public ISSPReturnObject getTree(String fZzjgbh, String fnybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<ISSPTreeNode> tree = getTreeJson(fZzjgbh, fnybh);// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取支路配置树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取支路配置树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	private List<ISSPTreeNode> getTreeJson(String fZzjgbh, String fnybh) {
        //获取用户和组织编号
        List<String> groupbh = ScopeDataUtil.getGroupIdScope();
        String userId = ScopeDataUtil.getUserIdScope();
		List<Map<String, String>> list = besbranchdatamapper.loadAll(fZzjgbh, fnybh,groupbh,userId);// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		if (list.size() > 0) {
			for (Map<String, String> map : list) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId(map.get("ID"));
				node.setId(map.get("ID"));
				node.setPid(map.get("PID"));
				node.setText(map.get("NAME"));
				node.setNodeStatus(map.get("JS"));
                node.setNodeType(map.get("PNAME"));// 暂时存的PID的name
				nodes.add(node);// 添加到节点容器
			}
		}
		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
		return buildTree;
	}

    @Override
    public ISSPReturnObject getTreeDep(String fZzjgbh, String fnybh) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            List<ISSPTreeNode> tree = getTreeJsonDep(fZzjgbh, fnybh);// 获得一棵树模型的数据
            returnObject.setList(tree);
            returnObject.setMsg("获取支路配置树成功");
            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setMsg("获取支路配置树失败");
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    private List<ISSPTreeNode> getTreeJsonDep(String fZzjgbh, String fnybh) {
        //获取用户和组织编号
        List<String> groupbh = ScopeDataUtil.getGroupIdScope();
        String userId = ScopeDataUtil.getUserIdScope();
        List<Map<String, String>> list = besbranchdatamapper.loadAllDep(fZzjgbh, fnybh,groupbh,userId);// 从数据库获取所有资源
        List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
        if (list.size() > 0) {
            for (Map<String, String> map : list) {
                ISSPTreeNode node = new ISSPTreeNode();
                node.setNodeTreeId(map.get("ID"));
                node.setId(map.get("ID"));
                node.setPid(map.get("PID"));
                node.setText(map.get("NAME"));
                node.setNodeStatus(map.get("JS"));
                node.setNodeType(map.get("PNAME"));// 暂时存的PID的name
                nodes.add(node);// 添加到节点容器
            }
        }
        List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
        return buildTree;
    }

	// 根据查询条件拼装table
	@Override
    public ISSPReturnObject pin_table(BesQstjfxData dto) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		// 获取查询条件
        String time_start = dto.getTime_start();// 起始时间
        String time_end = dto.getTime_end();// 终止时间
        String sjkld = dto.getSjkld();// 时间颗粒度
        String dwhs = dto.getDwhs();// 单位换算 起始就是查询的动态列
        String nhlx = dto.getNhlx();// 能耗类型
        String zlid = dto.getZlid();// 支路id 逗号隔开
        String tblx = dto.getTblx();// 图表类型
		StringBuilder str = new StringBuilder();// 拼装table
		str.append("<thead>");
		str.append(
                "<tr class='header_color'><th class='czjz' rowspan='2' style='white-space: nowrap;width:20%; '>支路名称</th>");
		// 判断时 天 月 年 1.表头
		StringBuilder a = sjkld_s(zlid, dwhs, time_start, time_end, sjkld, nhlx, dto);
		str.append(a);
		str.append("</thead>");
		str.append("<tbody>");
		// 循环遍历查询 2.内容 加 echars
		if (Validate_y.noNull(zlid)) {
			StringBuilder b = sjkld_s_nr(zlid, time_start, time_end, sjkld, dwhs, nhlx, tblx, dto);
			str.append(b);
		}
		str.append("</tbody>");
		Map<String, Object> map = new HashMap<>();
		map.put("str", str);
		returnObject.setList(dto.getBtlist());
		returnObject.setData(dto.getNrlist());
		returnObject.setMap(map);
		return returnObject;
	}

	// 时 str pin

	// 1. 时 重新编写表头 逻辑拼装
	public StringBuilder sjkld_s(String zlid, String dwhs, String time_start, String time_end, String sjkld, String nhlx, BesQstjfxData dto) {
		StringBuilder str = new StringBuilder();
		// 循环遍历查询 先判断
		if (Validate_y.noNull(zlid)) {
			String arr[] = zlid.split(",");
			// 1. 查询表头数据 根据条件
			String sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
			if ("0".equals(sjkld)) {// 时
				sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
			} else if ("1".equals(sjkld)) {// 天
				sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m')";
			} else if ("2".equals(sjkld)) {// 月
				sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
			} else if ("3".equals(sjkld)) {// 年
				sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
			}
			List<Map<String, Object>> sjkld_s_bt = besbranchdatamapper.sjkld_s_bt(sjgs, arr, time_start, time_end, sjkld, nhlx);
			// 循环所有的天
			for (Map<String, Object> btmap : sjkld_s_bt) {
				String btsj = btmap.get("sj").toString();
				// 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
				str.append("<th class='jzxs' colspan=" + sjkld_s_bt_s.size()
                        + " style='white-space: nowrap;text-align:center; '>" + btsj
                        + "</th>");
			}
			str.append("</tr>");
			str.append("<tr class='header_color'>");
			List<String> btlist = new ArrayList<>();
			for (Map<String, Object> btmap : sjkld_s_bt) {
				String btsj = btmap.get("sj").toString();
				// 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
				for (Map<String, Object> ssmap : sjkld_s_bt_s) {
					String sj = ssmap.get("sj").toString();
					if (Validate_y.noNull(sj)) {
						if ("0".equals(sjkld)) {// 时
							sj = sj.substring(10, 13);
							str.append(
                                    "<th style='white-space: nowrap;text-align:center; '>"
                                            + sj + "时</th>");
							btlist.add(sj);
						} else if ("1".equals(sjkld)) {// 天
							sj = sj.substring(8, 11);
							str.append(
                                    "<th style='white-space: nowrap;text-align:center; '>"
                                            + sj + "日</th>");
							btlist.add(sj);
						} else if ("2".equals(sjkld)) {// 月
							sj = sj.substring(5, 7);
							str.append(
                                    "<th style='white-space: nowrap;text-align:center; '>"
                                            + sj + "月</th>");
							btlist.add(sj);
						} else if ("3".equals(sjkld)) {// 年
							sj = sj.substring(0, 4);
							str.append(
                                    "<th style='white-space: nowrap;text-align:center; '>"
                                            + sj + "年</th>");
							btlist.add(sj);
						}
					}
				}
			}
			dto.setBtlist(btlist);
			str.append("</tr>");
		} else {
			str.append("</tr>");
		}
		return str;
	}

	// 1.1 时 重新编写内容 逻辑拼装 zlid支路id 为每一次循环的id zlids为所有的支路id
	public StringBuilder sjkld_s_nr(String zlids, String time_start, String time_end, String sjkld, String dwhs, String nhlx, String tblx, BesQstjfxData dto) {
		StringBuilder str = new StringBuilder();
		String arr[] = zlids.split(",");
		List<Map<String, Object>> list = new ArrayList<>();// 拼echars
		String sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
		if ("0".equals(sjkld)) {// 时
			sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m-%d')";
		} else if ("1".equals(sjkld)) {// 天
			sjgs = "DATE_FORMAT(F_CJSJ, '%Y-%m')";
		} else if ("2".equals(sjkld)) {// 月
			sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
		} else if ("3".equals(sjkld)) {// 年
			sjgs = "DATE_FORMAT(F_CJSJ, '%Y')";
		}
		for (int i = 0; i < arr.length; i++) {
			// 根据zlid查询支路名称
			String zlmc = besbranchdatamapper.getzlmc(arr[i]);
			List<String> sjlist = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			Map<String, String> table_xmap = besbranchdatamapper.pin_xmap(arr[i]);
			str.append("<tr>");
            str.append("<th id=" + table_xmap.get("ID")
                    + " style='white-space: nowrap;text-align:center; '>"
					+ table_xmap.get("NAME") + "</th>");
			// 1. 查询表头数据 根据条件
			List<Map<String, Object>> sjkld_s_bt = besbranchdatamapper.sjkld_s_bt(sjgs, arr, time_start, time_end, sjkld, nhlx);
			for (Map<String, Object> btmap : sjkld_s_bt) {
				String btsj = btmap.get("sj").toString();
				// 查询天 下属小时
                List<Map<String, Object>> sjkld_s_bt_s = besbranchdatamapper.sjkld_s_bt_s(sjgs, arr, btsj, sjkld, nhlx,
                        time_start, time_end);
				for (Map<String, Object> ssmap : sjkld_s_bt_s) {
					String sj = ssmap.get("sj").toString();
					List<String> lista = new ArrayList<>();
					// 查询数据 根据zlid和循环查询的时间
					Map<String, String> datamap = besbranchdatamapper.sjkld_s_bt_nr(arr[i], sj, sjkld, nhlx, dwhs);
					if (Validate_y.isNull(datamap)) {
                        str.append(
                                "<th style='white-space: nowrap;text-align:center; '>"
                                        + 0.00 + "</th>");
						lista.add("0.00");
					} else {
						String data = datamap.get("data");
						String id = datamap.get("id");
						lista.add(data);
                        str.append("<th id=" + id
                                + " style='white-space: nowrap;text-align:center; '>"
								+ data + "</th>");
					}
					sjlist.addAll(lista);
				}
			}
			map.put("name", zlmc);
			map.put("type", tblx);
			map.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
			map.put("data", sjlist);
			list.add(map);
			str.append("</tr>");
		}
		dto.setNrlist(list);
		return str;
	}

	/**
	 * 
	 * Description:重写趋势统计分析拼装table  
	 * @param dto
	 * @return   
	 * @see com.efounder.JEnterprise.service.dataAnalysises.BESZlqsfxService#pinTable(com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData)
	 */
    @Override
    public ISSPReturnObject pinTable(BesQstjfxData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 1.0 先根据条件将所有数据都查询出来
        dto.setZlbh(dto.getZlid().split(","));// 将对应格式
        dto.setZlmc(dto.getZlname().split(","));
        try {
            List<Map<String, Object>> bqAllData = besbranchdatamapper.bqAllData(dto);// 本期 按时间正序排列
            List<String> AlltimeList = new ArrayList<>();// 时间list 去重前
            List<String> timeList = new ArrayList<>();// 时间list 去重后
            // 对本期数据进行处理
            for (int i = 0; i < bqAllData.size(); i++) {
                AlltimeList.add(bqAllData.get(i).get("f_cjsj").toString());// 将本期时间存入timeList中
            }
            Set<String> set = new HashSet<>();
            set.addAll(AlltimeList);
            timeList.addAll(set);// 去重后的list
            Collections.sort(timeList);// 重新排序
            // 拼table 先拼表头(thead) 再拼内容(tbody)
            // 1.0 拼表头
            StringBuilder str = new StringBuilder();
            str.append("<thead>");
            str.append(
                    "<tr class='header_color'><th class='czjz' rowspan='2' style='white-space: nowrap;width:20%; '>支路名称</th>");
            // 判断时 天 月 年
            StringBuilder title = pin_thead(dto, timeList);
            str.append(title);
            str.append("</thead>");

            // 2.0 拼内容
            str.append("<tbody>");
            StringBuilder content = pin_tbody(dto, timeList, bqAllData);
            str.append(content);
            str.append("</tbody>");
            Map<String, Object> map = new HashMap<>();
            map.put("str", str);
            if (bqAllData.size() > 0) {
                returnObject.setStatus("1");
                returnObject.setList(dto.getBtlist());
                returnObject.setData(dto.getNrlist());
                returnObject.setMap(map);
            } else {
                returnObject.setStatus("0");// 返回失敗
            }
        } catch (Exception e) {
            returnObject.setStatus("0");// 返回失敗
        }
        return returnObject;
    }

    /**
     * 
     * @Title: pin_thead @Description:拼装表头 @return: StringBuilder @throws
     */
    public StringBuilder pin_thead(BesQstjfxData dto, List<String> timeList) {
        StringBuilder str = new StringBuilder();
        // 先判断时间颗粒度是什么类型 来确定表头如何展示
        String timeGranularity = dto.getSjkld();// 时间颗粒度
        // 时间截取格式
        int subStart = 0, subEnd = 11;// table one-th
        int echarsStart = 5, echarsEnd = 13;// echars
        String unit = "时";
        if ("0".equals(timeGranularity)) {// 0 时 11-24 00
            subStart = 0;
            subEnd = 11;
            echarsStart = 11;
            echarsEnd = 13;
            unit = "时";
        } else if ("1".equals(timeGranularity)) {// 1 天
            subStart = 0;
            subEnd = 7;
            echarsStart = 8;
            echarsEnd = 10;
            unit = "日";
        } else if ("2".equals(timeGranularity)) {// 2 月
            subStart = 0;
            subEnd = 4;
            echarsStart = 5;
            echarsEnd = 7;
            unit = "月";
        } else if ("3".equals(timeGranularity)) {// 3 年
            subStart = 0;
            subEnd = 4;
            echarsStart = 0;
            echarsEnd = 4;
            unit = "年";
        }
        // 循环时间
        List<String> echarsBt = new ArrayList<>();
        String time = "";
        StringBuilder timeBt = new StringBuilder();
        List<String> ArrayTime = new ArrayList<>();// 创建数组存放不同时间
        if (timeList.size() > 0) {
            for (int i = 0; i < timeList.size(); i++) {
                time = timeList.get(i);
                ArrayTime.add(time.substring(subStart, subEnd));
                timeBt.append("<th style='white-space: nowrap;text-align:center;'>"
                        + time.substring(echarsStart, echarsEnd) + unit + "</th>");
                echarsBt.add(time.substring(echarsStart, echarsEnd));
            }
            // 将截取的时间数组放到set里面去重
            List<String> ArrayList = new ArrayList<>();
            Set<String> ArraySet = new HashSet<>();
            ArraySet.addAll(ArrayTime);
            ArrayList.addAll(ArraySet);
            Collections.sort(ArrayList);
            List<Map<String, Object>> mergeList = new ArrayList<>();
            for (String st : ArrayList) {
                int eqsum = 0;
                Map<String, Object> mergeMap = new HashMap<>();
                for (int j = 0; j < ArrayTime.size(); j++) {
                    if (st.equals(ArrayTime.get(j))) {// 如果时间相同的话,累计计算个数
                        eqsum++;
                    }
                }
                // 将相同的数据放到新list中
                mergeMap.put("name", st);
                mergeMap.put("num", eqsum);
                mergeList.add(mergeMap);
            }
            for (Map<String, Object> newmap : mergeList) {
                str.append("<th colspan=" + newmap.get("num") + " style='white-space: nowrap;text-align:center;'>"
                        + newmap.get("name") + "</th>");
            }
            str.append("</tr>");
            str.append("<tr class='header_color' >");
            str.append(timeBt);
            str.append("</tr>");
        } else {
            str.append("</tr>");
        }
        dto.setBtlist(echarsBt);
        return str;
    }

    /**
     * 
     * @Title: pin_tbody 
     * @Description:拼内容 
     * @return: StringBuilder 
     * @throws
     */
    public StringBuilder pin_tbody(BesQstjfxData dto, List<String> timeList, List<Map<String, Object>> bqAllData) {
        StringBuilder str = new StringBuilder(); // 本期str
        String[] zlbh = dto.getZlbh(); // 所有的支路编号
        List<Map<String, Object>> echarsContentList = new ArrayList<>();

        String f_type = dto.getSjkld();


        for (int d = 0; d < zlbh.length; d++) {
            for (int a = 0; a < timeList.size(); a++) {

                Boolean data = false;
                sign :
                for (Map<String,Object> map : bqAllData) {

                    if (zlbh[d].equals(map.get("F_ZLBH")) && Timestamp.valueOf(timeList.get(a)).equals(map.get("f_cjsj"))) {


                        data = true;
                        break sign;

                    }
                }
                if (!data) {

                    Map<String, Object> map1 = new HashMap();
                    map1.put("f_cjsj", Timestamp.valueOf(timeList.get(a)));
                    map1.put("data", 0.0);
                    map1.put("F_ZLBH", zlbh[d]);
                    bqAllData.add(map1);
                }
            }
        }
        /*for (int d = 0; d < zlbh.length; d++) {
            for (int a = 0; a < timeList.size(); a++) {
                //根据支路编号和时间查询数据库bes_branch_data表中是否有数据
                List<Map<String,Object>> whetherFdata = besbranchdatamapper.selectWhetherFdata(zlbh[d],timeList.get(a).replace(".0",""),f_type);

                Timestamp ts = new Timestamp(System.currentTimeMillis());
                if (whetherFdata.size() == 0) {
                    Map<String,Object> map = new HashMap();
                    map.put("f_cjsj", Timestamp.valueOf(timeList.get(a)));
                    map.put("data",0.0);
                    map.put("F_ZLBH",zlbh[d]);
                    bqAllData.add(map);
                }

            }
        }*/

        for (int i = 0; i < zlbh.length; i++) {
            List<String> echarsBqData = new ArrayList<>();// echars数据 本期
            String zlmc = dto.getZlmc()[i]; // 支路名称
            str.append("<tr>");
            str.append("<th  class='czjz' style='white-space: nowrap;'>" + zlmc + "</th>");
            // 然后循环时间 将本期数据拼装
            for (int j = 0; j < timeList.size(); j++) {
                // 循环同期数据 将时间相同的数据取出来
                for (int d = 0; d < bqAllData.size(); d++) {
                    String time = timeList.get(j); // 循环时间
                    String dataTime = bqAllData.get(d).get("f_cjsj").toString(); // 本期数据中的时间
                    String dataZlbh = bqAllData.get(d).get("F_ZLBH").toString(); // 本期数据中的支路编号
                    if (time.equals(dataTime) && zlbh[i].equals(dataZlbh)) { // 时间相同并且支路编号相同才拼装 以确定唯一
                        String data = bqAllData.get(d).get("data").toString(); // 本期数据
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        echarsBqData.add(data);
                    }
                }
            }
            // 拼装echars 本期
            Map<String, Object> echarsBqContent = new HashMap<>();
            echarsBqContent.put("name", zlmc);
            echarsBqContent.put("type", dto.getTblx());
            echarsBqContent.put("smooth", "true"); // 平滑曲线
            echarsBqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
            echarsBqContent.put("data", echarsBqData);
            echarsBqContent.put("symbolClip", true);
            echarsBqContent.put("symbol", "roundRect");
            echarsContentList.add(echarsBqContent);
            str.append("</tr>");
        }
        dto.setNrlist(echarsContentList);
        return str;
    }

    /**
     * --以下重写单位table
     */
    @Override
    public ISSPReturnObject pinTabledw(BesQstjfxData dto) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 1.0 先根据条件将所有数据都查询出来
        dto.setZlbh(dto.getZlid().split(","));// 将对应格式
        dto.setZlmc(dto.getZlname().split(","));
        String[] zlmc = dto.getZlname().split(",");
        List<String> dwmc = new ArrayList<>();
        for (int z = 0; z < zlmc.length; z++) {
            dwmc.add(zlmc[z]);
        }
        dto.setBtlist(dwmc);
        try {
            List<Map<String, Object>> bqAllData = besbranchdatamapper.bqAllData(dto);// 本期 按时间正序排列
            List<String> AlltimeList = new ArrayList<>();// 时间list 去重前
            List<String> timeList = new ArrayList<>();// 时间list 去重后
            // 对本期数据进行处理
            for (int i = 0; i < bqAllData.size(); i++) {
                AlltimeList.add(bqAllData.get(i).get("f_cjsj").toString());// 将本期时间存入timeList中
            }
            Set<String> set = new HashSet<>();
            set.addAll(AlltimeList);
            timeList.addAll(set);// 去重后的list
            Collections.sort(timeList);// 重新排序
            // 拼table 先拼表头(thead) 再拼内容(tbody)
            // 1.0 拼表头
            StringBuilder str = new StringBuilder();
            str.append("<thead>");
            str.append(
                    "<tr class='header_color'><th class='czjz' rowspan='2' style='white-space: nowrap;width:20%; '>支路名称</th>");
            // 判断时 天 月 年
            StringBuilder title = pin_theaddw(dto, timeList);
            str.append(title);
            str.append("</thead>");

            // 2.0 拼内容
            str.append("<tbody>");
            StringBuilder content = pin_tbodydw(dto, timeList, bqAllData);
            str.append(content);
            str.append("</tbody>");
            Map<String, Object> map = new HashMap<>();
            map.put("str", str);
            if (bqAllData.size() > 0) {
                returnObject.setStatus("1");
                returnObject.setList(dto.getBtlist());
                returnObject.setData(dto.getNrlist());
                returnObject.setMap(map);
            } else {
                returnObject.setStatus("0");// 返回失敗
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus("0");// 返回失敗
        }
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月27日 下午7:09:59
     * @Description:
     * @param dto
     * @param timeList
     * @return StringBuilder
     */
    public StringBuilder pin_theaddw(BesQstjfxData dto, List<String> timeList) {
        StringBuilder str = new StringBuilder();
        // 先判断时间颗粒度是什么类型 来确定表头如何展示
        String timeGranularity = dto.getSjkld();// 时间颗粒度
        // 时间截取格式
        int subStart = 0, subEnd = 11;// table one-th
        int echarsStart = 5, echarsEnd = 13;// echars
        String unit = "时";
        if ("0".equals(timeGranularity)) {// 0 时 11-24 00
            subStart = 0;
            subEnd = 11;
            echarsStart = 11;
            echarsEnd = 13;
            unit = "时";
        } else if ("1".equals(timeGranularity)) {// 1 天
            subStart = 0;
            subEnd = 7;
            echarsStart = 8;
            echarsEnd = 10;
            unit = "日";
        } else if ("2".equals(timeGranularity)) {// 2 月
            subStart = 0;
            subEnd = 4;
            echarsStart = 5;
            echarsEnd = 7;
            unit = "月";
        } else if ("3".equals(timeGranularity)) {// 3 年
            subStart = 0;
            subEnd = 4;
            echarsStart = 0;
            echarsEnd = 4;
            unit = "年";
        }
        // 循环时间
        List<String> echarsBt = new ArrayList<>();
        String time = "";
        StringBuilder timeBt = new StringBuilder();
        List<String> ArrayTime = new ArrayList<>();// 创建数组存放不同时间
        if (timeList.size() > 0) {
            for (int i = 0; i < timeList.size(); i++) {
                time = timeList.get(i);
                ArrayTime.add(time.substring(subStart, subEnd));
                timeBt.append("<th style='white-space: nowrap;text-align:center;'>"
                        + time.substring(echarsStart, echarsEnd) + unit + "</th>");
                echarsBt.add(time.substring(echarsStart, echarsEnd));
            }
            // 将截取的时间数组放到set里面去重
            List<String> ArrayList = new ArrayList<>();
            Set<String> ArraySet = new HashSet<>();
            ArraySet.addAll(ArrayTime);
            ArrayList.addAll(ArraySet);
            Collections.sort(ArrayList);
            List<Map<String, Object>> mergeList = new ArrayList<>();
            for (String st : ArrayList) {
                int eqsum = 0;
                Map<String, Object> mergeMap = new HashMap<>();
                for (int j = 0; j < ArrayTime.size(); j++) {
                    if (st.equals(ArrayTime.get(j))) {// 如果时间相同的话,累计计算个数
                        eqsum++;
                    }
                }
                // 将相同的数据放到新list中
                mergeMap.put("name", st);
                mergeMap.put("num", eqsum);
                mergeList.add(mergeMap);
            }
            for (Map<String, Object> newmap : mergeList) {
                str.append("<th colspan=" + newmap.get("num") + " style='white-space: nowrap;text-align:center;'>"
                        + newmap.get("name") + "</th>");
            }
            str.append("</tr>");
            str.append("<tr class='header_color' >");
            str.append(timeBt);
            str.append("</tr>");
        } else {
            str.append("</tr>");
        }
//        dto.setBtlist(echarsBt);
        return str;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月27日 下午6:56:32
     * @Description:
     * @param dto
     * @param timeList
     * @param bqAllData
     * @return StringBuilder
     */
    public StringBuilder pin_tbodydw(BesQstjfxData dto, List<String> timeList, List<Map<String, Object>> bqAllData) {
        StringBuilder str = new StringBuilder(); // 本期str
        String[] zlbh = dto.getZlbh(); // 所有的支路编号
        List<Map<String, Object>> echarsContentList = new ArrayList<>();
        List<Object> echarsBqDataDw = new ArrayList<>();// echars数据 本期
        String f_type = dto.getSjkld();

            for (int d = 0; d < zlbh.length; d++) {
                for (int a = 0; a < timeList.size(); a++) {

                    Boolean data = false;

                    sign :
                    for (Map<String,Object> map : bqAllData) {

                        if (zlbh[d].equals(map.get("F_ZLBH")) && Timestamp.valueOf(timeList.get(a)).equals(map.get("f_cjsj"))) {
                            data = true;
                            break sign;
                        }
                    }
                    if (!data) {

                        Map<String, Object> map1 = new HashMap();
                        map1.put("f_cjsj", Timestamp.valueOf(timeList.get(a)));
                        map1.put("data", 0.0);
                        map1.put("F_ZLBH", zlbh[d]);
                        bqAllData.add(map1);
                    }
                }
            }
       /* for (int d = 0; d < zlbh.length; d++) {
            for (int a = 0; a < timeList.size(); a++) {
               //根据支路编号和时间查询数据库bes_branch_data表中是否有数据
                List<Map<String,Object>> whetherFdata = besbranchdatamapper.selectWhetherFdata(zlbh[d],timeList.get(a).replace(".0",""),f_type);

                if (whetherFdata.size() == 0) {
                    Map<String,Object> map = new HashMap();
                    map.put("f_cjsj", Timestamp.valueOf(timeList.get(a)));
                    map.put("data",0.0);
                    map.put("F_ZLBH",zlbh[d]);
                    bqAllData.add(map);
                }

            }
        }*/

        for (int i = 0; i < zlbh.length; i++) {
            String zlmc = dto.getZlmc()[i]; // 支路名称
            str.append("<tr>");
            str.append("<th  class='czjz' style='white-space: nowrap;'>" + zlmc + "</th>");
            // 然后循环时间 将本期数据拼装
            // 计算单位数据
            double sum = 0.00;
            for (int j = 0; j < timeList.size(); j++) {
                // 循环同期数据 将时间相同的数据取出来
                for (int d = 0; d < bqAllData.size(); d++) {
                    String time = timeList.get(j); // 循环时间
                    String dataTime = bqAllData.get(d).get("f_cjsj").toString(); // 本期数据中的时间
                    String dataZlbh = bqAllData.get(d).get("F_ZLBH").toString(); // 本期数据中的支路编号
                    if (time.equals(dataTime) && zlbh[i].equals(dataZlbh)) { // 时间相同并且支路编号相同才拼装 以确定唯一
                        String data = bqAllData.get(d).get("data").toString(); // 本期数据
                        str.append("<th style='white-space: nowrap;text-align:center;'>" + data + "</th>");
                        sum += parse(data);
                    }
                }
            }
            echarsBqDataDw.add(String.format("%.2f", sum));// 截取小数点后两位
            str.append("</tr>");
        }
        // 拼装echars 本期
        Map<String, Object> echarsBqContent = new HashMap<>();
        echarsBqContent.put("name", "数据");
        echarsBqContent.put("type", dto.getTblx());
        echarsBqContent.put("smooth", "true"); // 平滑曲线
        echarsBqContent.put("itemStyle", "{ normal: {label : {show: true,position: 'top'}}}");
        echarsBqContent.put("data", echarsBqDataDw);
        echarsBqContent.put("symbolClip", true);
        echarsBqContent.put("symbol", "roundRect");
        echarsContentList.add(echarsBqContent);
        dto.setNrlist(echarsContentList);
        return str;
    }

    // 字符串转换成double
    public double parse(String str) {
        double b = Double.parseDouble(str);
        return b;
    }

}
