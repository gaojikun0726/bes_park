package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesRepertory;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZone;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZoneinfo;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZonemode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesZoneinfoMapper extends BaseMapper<String, BesZoneinfo> {
	
	//场景配置树
	List<BesZoneinfo> cjpz_tree();
	
	//新增文件夹
	boolean	addFolder(BesZoneinfo obj);
	
    // 修改
    boolean updateFolder(BesZoneinfo obj);

	//获取最大id
	String queryMaxId();
	
	//单独删除子节点
	boolean delAlone(String nodeId);
	
	//删除节点下所有节点
	boolean delAll(String nodeId);
	
	//删除zone表信息 nodeid相同
	boolean delZone(String nodeId);
	
	//获取子节点 
	List<Map<String, String>> getnodeId(String nodeId);
	
	//批量删除
	boolean batchDelete(@Param("nodeIds") List<String> nodeIds);
	
	//场景模式下拉查询
	List<Map<String,Object>> select_cjms();
	
	//场景模式根绝ID查询详情
	List<Map<String,Object>> select_cjmsxq(String id);
	
	//新增场景保存
	Boolean addScene(BesZone obj);
	
    // 修改场景保存
    Boolean updateScene(BesZone obj);

	//获取bes_zone最大id
	String queryzoneMaxId();
	
	//根据id查询详情
	List<Map<String,Object>> select_zonexq(String id);
	
	//根据节点id查询ddc信息
	Map<String,String> getddcxx(String id);
	
	//获取场景id信息
	Map<String,String> getcjidxx(String cjid);
	
	//获取bes_zonemode最大数值
	String queryMoMaxId();
	
	//保存bes_zoneMode信息 模式
	Boolean addZoneMode(BesZonemode mo);
	
    // 修改bes_zoneMode
    Boolean updateZoneMode(BesZonemode mo);

	//获取bes_repertory最大id
	String queryReMaxId();
	
	//模式内容保存
	Boolean addRepertory(BesRepertory re);
	
	//删除zonemode
	Boolean delzonemode(String nodeId);
	
	//删除repertory
	Boolean delrepertory(String nodeId);
	
	//场景内容
	List<Map<String,Object>> select_cjnrxq(String zondid);
	
	//根据场景id查询数据 bes_zone
	Map<String,String> interface_zoneMap(String cjid);
	
	//根据cjid查询bes_zonemode
	Map<String,String> interface_modeMap(String cjid);
	
	//根据cjid查询bes_repertory
	List<Map<String,String>> interface_reMap(String cjid);
	
	//根据关联id查询bes_ddc表信息
	Map<String,String> interface_ddcMap(String ddc_id);

    boolean delbes_repertory(@Param("fzonid") String fzonid);

	boolean Delcj(@Param("Table") String table,@Param("col") String col,@Param("nodeId") String nodeId);

    String getSbid(String nodeId);
}