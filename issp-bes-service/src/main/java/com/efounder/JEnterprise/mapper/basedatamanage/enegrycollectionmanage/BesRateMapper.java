package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;


import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BesRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 通信波特率mapper
 * @author suhang
 * @date 2018-7-27
 */
@Mapper
public interface BesRateMapper {
	
	List<BesRate> besRatePage(@Param(value = "keywords") String keywords);
   
	boolean delBesRate(String fRateBh);

    boolean insBesRate(BesRate besRate);

    BesRate selBesRate(String fRateBh);

    boolean updBesRate(BesRate besRate);

    /**
     * 波特率编号查重
     * @param fRateBh
     * @return
     */
    int checkRepeatFRateBh(@Param("fRateBh") String fRateBh);

    /**
     * 波特率查重
     * @param fCommRate
     * @return
     */
    int checkRepeatFCommRate(@Param("fCommRate") String fCommRate);


}