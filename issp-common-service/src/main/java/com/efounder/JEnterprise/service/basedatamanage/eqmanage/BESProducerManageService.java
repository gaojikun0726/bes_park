package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESProducerManage;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BESProducerManageService {//接口体由常量定义和方法定义两部分构成

	/**
	 * 获取生产商对象
	 * @return
	 */
	public BESProducerManage getBESProducerManageObj(String fId);
	/**
	 * 生产商管理列表
	 * @return
	 */
	public List<BESProducerManage> getBESProducerManageList();
	/**
	 * 搜索生产商
	 * @return
	 */
	public List<BESProducerManage> findProducterlist(String keywords);
	/**
	 * 添加生产商管理
	 * @param besproducermanage
	 * @return
	 */
	ISSPReturnObject addProducer(BESProducerManage besproducermanage);
	/**
	 * 删除生产商管理
	 * @param besproducermanage
	 * @return
	 */
	public ISSPReturnObject delProducer(BESProducerManage besproducermanage);
	/**
	 * 编辑生产商管理
	 * @param besproducermanage
	 * @return
	 */
	public ISSPReturnObject updateProducer(BESProducerManage besproducermanage);
	/**
	 * 分页查询
	 * @param besproducermanage
	 * @return
	 */

	public PageInfo<BESProducerManage> findProducerByPage(Integer bars,Integer pageNum, String keywords);


	
}
