package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESProtocol;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 通信协议mapper接口
 * @author LvSihan
 * @modify suhang
 */
@Mapper
public interface BESProtocolMapper extends BaseMapper<String, BESProtocol>{
	boolean delProtocol(String fTxxybh);

    boolean insProtocol(BESProtocol besProtocol);

    BESProtocol getProtocol(String fTxxybh);

    boolean updProtocol(BESProtocol besProtocol);

	List<BESProtocol> getProtocolList(@Param(value = "keywords") String keywords);
}