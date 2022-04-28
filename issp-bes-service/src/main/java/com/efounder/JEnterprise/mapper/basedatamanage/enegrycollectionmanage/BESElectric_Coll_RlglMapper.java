package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 电能参数采集方案关系mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESElectric_Coll_RlglMapper extends BaseMapper<String, BESElectric_Coll_Rlgl>{

    /**
     * 查询采集方案下的能耗参数
     * @param fCjfabh
     * @return
     */
    List<BESElectric_Coll_Rlgl> queryECRList(String fCjfabh);
    /**
     *
     * @param fDnbh
     * @return
     */
    BESElectric_Coll_Rlgl queryByDnbh(String fDnbh);
    /**
     * 新增
     * @param besECR
     * @return
     */
    boolean CollMethod_insertElectricP(BESElectric_Coll_Rlgl besECR);
    /**
     * 删除一条
     * @param besECR
     * @return
     */
    boolean CollMethod_deleteElectricP(BESElectric_Coll_Rlgl besECR);
    /**
     * 删除全部
     * @param besECR
     * @return
     */
    boolean CollMethod_leftmoveAll(BESElectric_Coll_Rlgl besECR);
	boolean update_inclu_fStatisticalParam(BESElectric_Coll_Rlgl besECR);

    /**
     * 更新变比
     * @param besECR
     * @return
     */
	boolean update_inclu_fIsRate(BESElectric_Coll_Rlgl besECR);

	List<BESElectric_Coll_Rlgl> queryrlglList(BESElectric_Coll_Rlgl besECR);

	BESElectric_Coll_Rlgl queryByDnbhAndCjfabh(BESElectric_Coll_Rlgl besECR);

    /**
     * 获取全部数据
     * @return
     */
    List<BESElectric_Coll_Rlgl> loadAll();
}