package com.efounder.JEnterprise.mapper.monitorConsole;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface vidiconMapper extends BaseMapper<String, String> {

    List<String> getsxtsb_list(List<String> Id);

    List<String> GetxlidList(List<String> Id);

    List<Map<String,String>> GetAllxlList(@Param("Id") List<String> Id, @Param("CJs") String CJs,@Param("PId") String PId);

    List<Map<String,String>> GetxlZzjgList(@Param("Id") List<String> Id,@Param("CJs") String CJs,@Param("XLid") String XLid,@Param("SJid") String SJid);

    List<Map<String,String>> GetzzjgsxtList(@Param("Id") List<String> Id,@Param("CJs") String CJs,@Param("SJid") String SJid);

    List<Map<String,String>> getData(@Param("nodeId")String nodeId);

    String GetSxtzcSum(List<String> Id);

    String GetSxtbjSum(List<String> Id);

    String GetSxtycSum(List<String> Id);

    String GetSxtXlzcSum(@Param("Id") List<String> Id,@Param("fStatus") String fStatus);

    List<Map<String, String>> getVidiconList();


}