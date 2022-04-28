package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESProducerManageMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESProducerManage;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESProducerManageService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 类描述：生产商管理 修改备注：
 * 
 * @version 1.0.0
 *
 */
@Service("BESProducerManageService")
public class BESProducerManageServiceImpl implements BESProducerManageService {

	private static final Logger log = LoggerFactory.getLogger(BESProducerManageServiceImpl.class);

	@Autowired
	private BESProducerManageMapper BESProducermanagemapper;
	@Autowired
	private OperationConfig operationConfig;
	
	// 生产商管理列表实现方法

	@Override
	public List<BESProducerManage> getBESProducerManageList() {
		// TODO Auto-generated method stubO
		return BESProducermanagemapper.getBESProducerManageList();
	}
	//添加生产商信息
	@Override
	public ISSPReturnObject addProducer(BESProducerManage besproducermanage) {
		ISSPReturnObject isspReturnObject = new ISSPReturnObject();
		try {
			boolean addProducer = BESProducermanagemapper.addProducer(besproducermanage);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(besproducermanage.getfId(), "bes_eq_producer");
			}
			if (addProducer) {
				isspReturnObject.setData(besproducermanage);
				isspReturnObject.setMsg("添加成功！");
				isspReturnObject.setStatus("1");
			} else {
				isspReturnObject.setStatus("0");
				isspReturnObject.setMsg("添加失败！");
			}
		} catch (Exception e) {
			isspReturnObject.setStatus("0");
		}
		return isspReturnObject;
	}
	//分页
	@Override
	public PageInfo<BESProducerManage> findProducerByPage(Integer bars,Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		//PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<BESProducerManage> besproducermanage = BESProducermanagemapper.findProducerByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<BESProducerManage> page = new PageInfo<BESProducerManage>(besproducermanage);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}
	//删除
	@Override
	public ISSPReturnObject delProducer(BESProducerManage besproducermanage) {
		ISSPReturnObject resultData = new ISSPReturnObject();
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(besproducermanage.getfId(), "bes_eq_producer");
			}
			boolean flag = BESProducermanagemapper.delProducer(besproducermanage);
			if(flag){
				resultData.setStatus("1");
				resultData.setMsg("删除成功！");
			}else{
				resultData.setStatus("0");
				resultData.setMsg("删除失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}
	//编辑
	@Override
	public ISSPReturnObject updateProducer(BESProducerManage besproducermanage) {
		// TODO Auto-generated method stub
		ISSPReturnObject resultData = new ISSPReturnObject();
		try {
			boolean flag = true;
			Map<String, Object> startMap = null;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap=OperationLog.updateStart(besproducermanage.getfId(), "bes_eq_producer");
				flag = BESProducermanagemapper.updateProducer(besproducermanage);
				OperationLog.updateEnd(besproducermanage.getfId(), "bes_eq_producer", startMap);
			}else{
				flag = BESProducermanagemapper.updateProducer(besproducermanage);
			}
			if(flag){
				resultData.setData(besproducermanage);
				resultData.setStatus("1");
				resultData.setMsg("编辑成功！");
			}else{
				resultData.setStatus("0");
				resultData.setMsg("编辑失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}
	//获取生产商对象
	@Override
	public BESProducerManage getBESProducerManageObj(String fId) {
		// TODO Auto-generated method stub
		return BESProducermanagemapper.getBESProducerManageObj(fId);
	}
	//搜索生产商列表
	@Override
	public List<BESProducerManage> findProducterlist(String keywords) {
		// TODO Auto-generated method stub
		return BESProducermanagemapper.findproducter(keywords);
	}

	
}