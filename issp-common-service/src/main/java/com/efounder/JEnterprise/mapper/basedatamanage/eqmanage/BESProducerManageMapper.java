package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESProducerManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/** 
 * 类名称：BESProducerManageMapper
 * 类描述  生产商管理mapper接口
 * 创建人：yujieying
 * 修改人：yujieying
 * @version 1.0.0 
 *
 */
@Mapper
public interface BESProducerManageMapper extends BaseMapper<String ,BESProducerManage>{
	
	/**
	 * 查询生产商对象
	 * @return
	 */
	BESProducerManage getBESProducerManageObj(String fId) ;
	/**
	 * 查询生产商列表
	 * @return
	 */
	List<BESProducerManage> getBESProducerManageList() ;
	
	/**
	 * 增加生产商
	 * @param BESProducerManage
	 * @return
	 */
	
	boolean addProducer(BESProducerManage besproducermanage);
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<BESProducerManage> findProducerByPage(@Param("keywords") String keywords);
	/**
	 * 删除生产商
	 * 
	 * @param BESProducerManage
	 * @return 
	 */
	boolean delProducer(BESProducerManage besproducermanage);
	/**
	 * 编辑生产商
	 * 
	 * @param BESProducerManage
	 * @return 
	 */
	boolean updateProducer(BESProducerManage besproducermanage);
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	List<BESProducerManage> findproducter(@Param("keywords") String keywords);
	
	
}
