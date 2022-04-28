package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 主机联动（主机）
 * @author xiepufeng
 * @date 2020/2/11 15:54
 */
@Mapper
public interface HostLinkageMapper {

    void insert(HostLinkageModel hostLinkageModel);

    List<HostLinkageModel> findList(HostLinkageModel hostLinkageModel);

    void update(HostLinkageModel hostLinkageModel);

    void delete(Integer id);
}
