package com.efounder.JEnterprise.service.basedatamanage.dwxxpz.impl;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.dwxxpz.ESDwxxpzMapper;
import com.efounder.JEnterprise.model.basedatamanage.dwxxpz.ESDwxxpz;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.dwxxpz.ESDwxxpzService;
import com.efounder.JEnterprise.service.usercenter.impl.ESUserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 单位信息接口实现类
 * @author gongfanfei
 */
@Service("esDwxxpzService")
public class ESDwxxpzServiceImpl implements ESDwxxpzService,ESBaseService{

	private static final Logger log = LoggerFactory.getLogger(ESUserServiceImpl.class);

	@Autowired
	private ESDwxxpzMapper esdwxxpzMapper;

	/**
	 * 查询数据
	 */
	@Override
	public List<ESDwxxpz> getDwxxList(String dwxx_in) {

		// 紧跟着的第一个select方法会被分页
        List<ESDwxxpz> list = esdwxxpzMapper.findDwxxByPage(dwxx_in);

        log.info("# 查询默认数据库 page.toString()={}", list.toString());
		return list;
	}
	/**
	 * 增加单位信息
	 */
	@Override
	public boolean addDwxx(ESDwxxpz dwxx) {
		if (dwxx != null ){
			log.info("#ESDwxxpzMapper新增单位信息");
			int flag = esdwxxpzMapper.insert(dwxx);
			if(flag == 1)
				return true;
			else
				return false;
		}else
		return false;
	}

	/**
	 * ajax加载编辑对象
	 */
	@Override
	public ESDwxxpz findById(String f_id) {
		if(StringUtils.isBlank(f_id))
			return null;
		else
		    return esdwxxpzMapper.findById(f_id);
	}
	/**
	 *根据dwxxbh查询对象
	 */
	@Override
	public ESDwxxpz findBybh(String f_dwxxbh) {
		    return esdwxxpzMapper.getDwxxIdBybh(f_dwxxbh);
	}

	/**
	 * 编辑单位信息信息
	 */
	@Override
	public boolean editDwxx(ESDwxxpz dwxx) {
		if (dwxx != null && StringUtils.isNotBlank(dwxx.getF_id())){
			int flag = esdwxxpzMapper.update(dwxx);
			if(flag == 1)
				return true;
			else
				return false;
		}else
			return false;

	}
	@Override
	public boolean delDwxx(ESDwxxpz dwxx) {
		log.info("#ESDwxxpzMapper删除单位信息");
		return esdwxxpzMapper.deleteByBH(dwxx.getF_id());
	}
	/**
	 * 单位信息树
	 */
	@Override
	public List<IBaseTree> loadAll(String checkDataLimit){
		log.info("#ESDwxxpzMapper加载单位信息树");
		return esdwxxpzMapper.loadAll(checkDataLimit);

	}
	/**
	 * 根据单位信息编号模糊查询本级和子级id
	 */
	@Override
	public List<ESDwxxpz> findDwxxListByLikeBh(String f_dwxxbh) {
		log.info("#ESDwxxpzMapper模糊查询本级和子级ID");
		return esdwxxpzMapper.findDwxxListByLikeBh(f_dwxxbh);
	}
	/**
	 * 根据编号获取单位信息所有子节点
	 */
	@Override
	public List<ESDwxxpz> findChildByBh(String f_dwxxbh) {
		log.info("#ESDwxxpzMapper获取子节点");
		return esdwxxpzMapper.findChildByBh(f_dwxxbh);
	}
	@Override
	public boolean updateMxByBh(String f_dwxxbh,String f_mx) {
		log.info("#ESDwxxpzMapper根据编号更新明细");
		return esdwxxpzMapper.updateMxByBh(f_dwxxbh,f_mx);
	}
	@Override
	public PageInfo<ESDwxxpz> findDwxxByPage(Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
				// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
				if (pageNum == null)
					pageNum = 1;
				PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
				// 紧跟着的第一个select方法会被分页
				List<ESDwxxpz> dwxx = esdwxxpzMapper.findDwxxByPage(keywords);
				// 用PageInfo对结果进行包装
				PageInfo<ESDwxxpz> page = new PageInfo<ESDwxxpz>(dwxx);
				// 测试PageInfo全部属性
				// PageInfo包含了非常全面的分页属性
				log.info("# 查询默认数据库 page.toString()={}", page.toString());

				return page;
	}
	//子节点分页查询
	@Override
	public PageInfo<ESDwxxpz> findSonDwxxByPage(Integer pageNum, String f_dwxxbh) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESDwxxpz> dwxx = esdwxxpzMapper.findSonDwxxByPage(f_dwxxbh);
		// 用PageInfo对结果进行包装
		PageInfo<ESDwxxpz> page = new PageInfo<ESDwxxpz>(dwxx);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}

	@Override
	public ESDwxxpz getDwxxIdBybh(String f_dwxxbh) {
		return esdwxxpzMapper.getDwxxIdBybh(f_dwxxbh);
	}

	@Override
	public List<ESDwxxpz> getId(String f_dwxxbh) {
		return esdwxxpzMapper.getId(f_dwxxbh);
	}
	@Override
	public List<ESDwxxpz> findDwxxidByBh(String f_dwxxbh) {
		return esdwxxpzMapper.getId(f_dwxxbh);
	}
	@Override
	public List<ESDwxxpz> getbh(String id) {
		return esdwxxpzMapper.getbh(id);
	}
	@Override
	public List<ESDwxxpz> findDwxxListByLikeBhAndCheckDataLimit(String f_dwxxbh, String checkDataLimit) {
		// TODO Auto-generated method stub
		return esdwxxpzMapper.findDwxxListByLikeBhAndCheckDataLimit(f_dwxxbh, checkDataLimit);
	}
	@Override
	public List<ESDwxxpz> getBranchOffice() {
		// TODO Auto-generated method stub
		return esdwxxpzMapper.getBranchOffice();
	}
	@Override
	public List<ESDwxxpz> loadDwxxTreeJsTwo() {
		// TODO Auto-generated method stub
		return esdwxxpzMapper.loadDwxxTreeJsTwo();
	}


}
