package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author  杨超--YangChao
* @version 创建时间：2018年12月12日 下午6:36:35
* @parameter 
* @version 1.0
*/
@Mapper
public interface BESExportReportMapper extends BaseMapper<String, BESExportReport> {

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月12日 下午7:59:08
     * @history:查询支路分页数据
     * @param dto
     * @return List<BESExportReport>
     */
    List<Map<String, Object>> getZlExportReport(BESExportReport dto);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月12日 下午8:01:10
     * @Description:查询分项分页数据
     * @param dto
     * @return List<BESExportReport>
     */
    List<Map<String, Object>> getFxExportReport(BESExportReport dto);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月13日 下午3:45:05
     * @Description:查询所有能源下的支路
     * @param dto
     * @return List<Map<String,String>>
     */
    List<Map<String, String>> AllZlList(BESExportReport dto);

    /**
     * 
     * @author: YangChao
     * @createTime: 2018年12月18日 上午10:04:32
     * @Description:查询所有能源下的分项
     * @param dto
     * @return List<Map<String,String>>
     */
    List<Map<String, String>> AllFxList(BESExportReport dto);

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月2日 下午4:41:31
     * @Description:根据支路id向下查询count
     * @return String
     */
    int getDownCount(@Param("ZLID") String ZLID);

}
