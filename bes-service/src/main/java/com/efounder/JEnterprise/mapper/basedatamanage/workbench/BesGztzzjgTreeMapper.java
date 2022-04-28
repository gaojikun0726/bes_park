package com.efounder.JEnterprise.mapper.basedatamanage.workbench;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.BesSubyearData;
import com.efounder.JEnterprise.model.BesWorkbench.BESGztfhzzjg;
import com.efounder.JEnterprise.model.BesWorkbench.BesGztzzjgTree;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface BesGztzzjgTreeMapper extends BaseMapper<String, BesGztzzjgTree> {
	
	List<Map<String, String>> getzzjg_tree();

    // 刷新table
    List<Map<String, String>> RefreshTable(String nodeId);
    
    //删除数据
    boolean del_WorkbenchTree(String id);
    
    //添加数据
    boolean add_workbenchTree(BesGztzzjgTree besgztzjgtree);
    
    //编辑数据
    boolean edit_workbenchTree(BesGztzzjgTree besgztzjgtree);
    
    //查询数据
    List<BesGztzzjgTree> serch_workdenchTree(String bh);

    // 环比查询数据 同期
    List<BesHouseholdData> tqData(BesHouseholdData besHouseholdData);

    // 本期
    List<BesHouseholdData> bqData(BesHouseholdData besHouseholdData);

    // 环比查询数据 同期
    List<BesHouseholdData> monthtqData(BesHouseholdData besHouseholdData);

    // 本期
    List<BesHouseholdData> monthbqData(BesHouseholdData besHouseholdData);

    // 今天 总能耗
    List<BesHouseholdData> TodayAllData(BesHouseholdData besHouseholdData);
    
    //查询所有子ID
	List<Map<String, String>> getnodId(String id);
	
	//删除所有子ID元素
	boolean deleteSon(List<String> nodeIds);

	//删除ID自身
	boolean deleteSelf(String id);
	
	//查询img的url
	List<BESGztfhzzjg> changeImage(String id);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月18日 下午2:54:44
     * @Description:分户能耗数据
     * @param besHouseholdData
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> getFhEnergyData(BesHouseholdData besHouseholdData);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月19日 上午10:53:44
     * @Description:分户能耗数据总耗能
     * @param besHouseholdData
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> getAllFhEnergyData(BesHouseholdData besHouseholdData);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月19日 下午2:23:09
     * @Description:获取去年能耗
     * @param requset
     * @return List<Map<String,String>>
     */
    List<BesHouseholdData> getLastYear(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月19日 下午2:49:28
     * @Description:获取上月能耗
     * @param requset
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> getLastMonth(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月19日 下午2:52:21
     * @Description:获取本月能耗
     * @param requset
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> getMonth(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月19日 下午3:11:16
     * @Description:获取昨天能耗
     * @param requset
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> getYesterday(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月21日 下午3:45:52
     * @Description:能耗峰值
     * @param besHouseholdData
     * @return String
     */
    String getpeakEnergy(BesHouseholdData besHouseholdData);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月24日 上午10:02:15
     * @Description:获取所有电表
     * @param DtoList
     * @return List<BesHouseholdData>
     */
    List<Map<String, String>> Getdbsysname(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月24日 上午10:07:54
     * @Description:电表瞬时功率
     * @param DtoList
     * @return String
     */
    String Getpower(Map<String, Object> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月25日 下午2:36:51
     * @Description:本日能耗排行
     * @param DtoList
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> GetTodayRankEnergy(Map<String, String> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月25日 下午2:36:56
     * @Description:本月能耗排行
     * @param DtoList
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> GetMonthRankEnergy(Map<String, String> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年5月13日 下午2:16:37
     * @Description:园区排行
     * @param DtoList
     * @return List<BesHouseholdData>
     */
    List<BesHouseholdData> GetYqRankEnergy(Map<String, String> DtoList);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月26日 下午2:21:16
     * @Description:工作台报警信息获取
     * @param beswaininginfo
     * @return List<BesWainingInfo>
     */
    List<Map<String, String>> GetWaringReal(@Param("array") String[] levelArray, @Param("limit") String limit);

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月7日 上午9:44:24
     * @Description:先查询最近时间
     * @param dtoMap
     * @return List<Object>
     */
    String GetTimeList(@Param("currentTime") String currentTime, @Param("nhbh") String nhbh,
            @Param("dbsysnameList") List<String> dbsysnameList);

	Map<String, String> getleftInfo(String yqbh);

    /**
     *
     * @Description: 查询含有相关能源的园区
     *
     * @auther: wanghongjie
     * @date: 11:41 2020/7/17
     * @param: [nybh]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     *
     */
    List<Map<String, String>> energyList(String nybh);


    /**
     * 根据能源编号和园区编号查询特定数据
     * @param nybh
     * @param yqbh
     * @return
     */
    List<Map<String, String>> getEnergyListByYq(@Param("nybh")String nybh,@Param("yqbh")String yqbh);
    /**
     *
     * @Description: 查询当前园区的今日能耗
     *
     * @auther: wanghongjie
     * @date: 13:49 2020/7/17
     * @param: [f_fhbh, fCjsj_start]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     *
     */
    List<BesHouseholdData> parkEnergyMap(@Param("f_fhbh") String f_fhbh,@Param("fCjsj_start") String fCjsj_start);


    /**
     * sunzhiyuan
     * 查询当前能源的月数据
     * @param
     * @return
     */
    Map<String,Object> MounthParkEnergyMap(Map<String, String> DtoList);

    /**
     * 获取该分户的人数
     * @param fhbh
     * @return
     */
    Double getHouseConfPerson(@Param("fhbh") String fhbh);
    /**
     *
     * @Description: 查询当前点击的分户下的子节点
     *
     * @auther: wanghongjie
     * @date: 10:04 2020/7/23
     * @param: [dtoMap]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> selectBesHouseholdChild(String fFhbh);

    /**
     *
     * @Description: 根据能源编号和园区编号查询当前分户园区的信息
     *
     * @auther: wanghongjie
     * @date: 14:58 2020/7/23
     * @param: [yqbh]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     *
     */
    List<Map<String, Object>> selectBesHouseholdByYqbh(@Param("yqbh") String yqbh,@Param("nybh") String nybh);

    /**
     * @Description: 根据能源编号园区编号分户编号查询子节点数据
     * @auther:sunzhiyuan
     * @param yqbh
     * @param
     * @param
     * @return
     */
    List<Map<String,Object>> getHouseHoldInfo(@Param("fNybh") String fNybh,@Param("yqbh") String yqbh,@Param("fFhbh") String fFhbh);


    /**
     * sunzhiyuan 查询子节点下单个节点的数据
     * @param fhbh
     * @param yqbh
     * @return
     */
    Map<String,Object> getHouseHoldPersonData(@Param("fhbh") String fhbh,@Param("yqbh") String yqbh,@Param("month_start") String month_start,@Param("month_end") String month_end);



    /**
     *根据获取年数据
     * @param nybh
     * @param type
     * @return
     */
    List<Map<String,Object>> selectEnergyDataByYear(@Param("nybh") String nybh,@Param("type") String type,@Param("firstDay") String firstDay);

    /**
     * 获取
     * @param nybh
     * @param endYearData
     * @return
     */
    String selectEnergyDataMaxByMonth(@Param("nybh") String nybh,@Param("firstDay") String firstDay,@Param("endYearData") String endYearData);

    /**
     * sunzhiyuan
     * 查询当前能源的月数据
     * @param
     * @return
     */
    Boolean insertSubyearData(List<BesSubyearData> list);

    /**
     * 根据分项编号查询该分项编号下的年数据
     * @param fxbh
     * @return
     */
    String selectSubitemData(@Param("fxbh") String fxbh,@Param("firstDay") String firstDay,@Param("endDay") String endDay);

    /**
     * 插入分项年数据之前先删除表内所有数据
     * @return
     */
    Integer deleteSubyearData();

    /**
     *查询分项年数据
     * @return
     */
    List<Map<String,Object>> selectSubyearData();

    /**
     *
     * @Description: 如果当前分户编号下没有子节点,则显示父节点下的子节点
     *
     * @auther: wanghongjie
     * @date: 14:10 2020/9/18
     * @param: [fFhbh]
     * @return: java.lang.String
     *
     */
    String selectPfhbh(String fFhbh);

    /**
     * 没子节点就查父节点
     * @param fFhbh
     * @return
     */
    String getHouseHoldFhbh(String fFhbh);

    /**
     * 更新subyeardate数据
     * @param fxbh
     * @param f_ydata
     * @return
     */
    Boolean updateSubyearDataYdata(@Param("fxbh")String fxbh,@Param("f_ydata")String f_ydata);

    /**
     *
     * @Description: 查询第一个分户的单位
     *
     * @auther: wanghongjie
     * @date: 15:30 2021/4/26
     * @param: [fFhbh]
     * @return: java.lang.String
     *
     */
    String selectUnitByFHBH(String fFhbh);
}