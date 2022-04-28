package com.efounder.JEnterprise.service.basedatamanage.zzjg.impl;

import com.core.common.tree.IBaseTree;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.GroupCache;
import com.efounder.JEnterprise.mapper.basedatamanage.zzjg.ESZzjgMapper;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.usercenter.impl.ESUserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 组织机构接口实现类
 * @author gongfanfei
 */
@Service("esZzjgService")
public class ESZzjgServiceImpl implements ESZzjgService,ESBaseService{

	private static final Logger log = LoggerFactory.getLogger(ESUserServiceImpl.class);

	@Autowired
	private ESZzjgMapper eszzjgMapper;

	@Resource
	private GroupCache groupCache;

	/**
	 * 查询数据
	 */
	@Override
	public List<ESZzjg> getZzjgList(String zzjg_in) {

		// 紧跟着的第一个select方法会被分页
        List<ESZzjg> list = eszzjgMapper.findZzjgByPage(zzjg_in);

        log.info("# 查询默认数据库 page.toString()={}", list.toString());
		return list;
	}
	/**
	 * 增加组织机构
	 */
	@Override
	public boolean addZzjg(ESZzjg zzjg) {
		if (zzjg != null ){
			log.info("#ESZzjgMapper新增组织机构");
			int flag = eszzjgMapper.insert(zzjg);
			if(flag == 1)
			{
				// 添加到缓存
				groupCache.addOneGroupCache(eszzjgMapper.getZzjgIdBybh(zzjg.getF_zzjgbh()));
				return true;
			}
			else
			{
				return false;
			}
		}else
		return false;
	}

	/**
	 * ajax加载编辑对象
	 */
	@Override
	public ESZzjg findById(String f_id) {
		if(StringUtils.isBlank(f_id))
			return null;
		else
		    return eszzjgMapper.findById(f_id);
	}
	/**
	 *根据zzjgbh查询对象
	 */
	@Override
	public ESZzjg findBybh(String f_zzjgbh) {
		    return eszzjgMapper.getZzjgIdBybh(f_zzjgbh);
	}

	/**
	 * 编辑组织机构信息
	 */
	@Override
	public boolean editZzjg(ESZzjg zzjg) {
		if (zzjg != null && StringUtils.isNotBlank(zzjg.getF_id())){
			int flag = eszzjgMapper.update(zzjg);
			if(flag == 1)
			{
				// 更新缓存
				groupCache.updateOneGroupCache(eszzjgMapper.getZzjgIdBybh(zzjg.getF_zzjgbh()));
				return true;
			}
			else
			{
				return false;
			}
		}else
			return false;

	}
	@Override
	public boolean delZzjg(ESZzjg zzjg) {
		log.info("#ESZzjgMapper删除组织机构信息");
		ESZzjg esZzjg= eszzjgMapper.findById(zzjg.getF_id());

		// 查询是否被数据权限关联
		Integer roleGroup = eszzjgMapper.roleGroupIbfk(esZzjg.getF_zzjgbh());

		if (roleGroup != null && roleGroup > 0)
		{
			return false;
		}

		boolean deleted  = eszzjgMapper.deleteByBH(esZzjg.getF_id());
		if (deleted)
		{
			// 删除缓存
			groupCache.deleteOneGroupCache(esZzjg.getF_zzjgbh());
		}
		return deleted;
	}
	/**
	 * 组织机构树
	 */
	@Override
	public List<IBaseTree> loadAll(String checkDataLimit){
		log.info("#ESZzjgMapper加载组织机构树");
		return eszzjgMapper.loadAll(checkDataLimit);

	}

	/**
	 * 正晨部门树
	 */
	@Override
	public List<IBaseTree> loadAllZC(String checkDataLimit){
		log.info("#ESZzjgMapper加载组织机构树");
		return eszzjgMapper.loadAllZC(checkDataLimit);

	}

	/**
	 * 根据组织机构编号模糊查询本级和子级id
	 */
	@Override
	public List<ESZzjg> findZzjgListByLikeBh(String f_zzjgbh) {
		log.info("#ESZzjgMapper模糊查询本级和子级ID");
		return eszzjgMapper.findZzjgListByLikeBh(f_zzjgbh);
	}
	/**
	 * 根据编号获取组织机构所有子节点
	 */
	@Override
	public List<ESZzjg> findChildByBh(String f_zzjgbh) {
		log.info("#ESZzjgMapper获取子节点");
		return eszzjgMapper.findChildByBh(f_zzjgbh);
	}
	@Override
	public boolean updateMxByBh(String f_zzjgbh,String f_mx) {
		log.info("#ESZzjgMapper根据编号更新明细");
		boolean updated = eszzjgMapper.updateMxByBh(f_zzjgbh,f_mx);
		if (updated)
		{
			// 更新缓存
			groupCache.updateOneGroupCache(eszzjgMapper.getZzjgIdBybh(f_zzjgbh));
		}
		return updated;
	}
	@Override
	public PageInfo<ESZzjg> findZzjgByPage(Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
				// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
				if (pageNum == null)
					pageNum = 1;
				PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
				// 紧跟着的第一个select方法会被分页
				List<ESZzjg> zzjg = eszzjgMapper.findZzjgByPage(keywords);
				// 用PageInfo对结果进行包装
				PageInfo<ESZzjg> page = new PageInfo<ESZzjg>(zzjg);
				// 测试PageInfo全部属性
				// PageInfo包含了非常全面的分页属性
				log.info("# 查询默认数据库 page.toString()={}", page.toString());

				return page;
	}
	//子节点分页查询
	@Override
	public PageInfo<ESZzjg> findSonZzjgByPage(Integer pageNum, String f_zzjgbh) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESZzjg> zzjg = eszzjgMapper.findSonZzjgByPage(f_zzjgbh);
		// 用PageInfo对结果进行包装
		PageInfo<ESZzjg> page = new PageInfo<ESZzjg>(zzjg);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());

		return page;
	}

	@Override
	public ESZzjg getZzjgIdBybh(String f_zzjgbh) {
		return eszzjgMapper.getZzjgIdBybh(f_zzjgbh);
	}

	@Override
	public List<ESZzjg> getId(String f_zzjgbh) {
		return eszzjgMapper.getId(f_zzjgbh);
	}
	@Override
	public List<ESZzjg> findZzjgidByBh(String f_zzjgbh) {
		return eszzjgMapper.getId(f_zzjgbh);
	}
	@Override
	public List<ESZzjg> getbh(String id) {
		return eszzjgMapper.getbh(id);
	}
	@Override
	public List<ESZzjg> findZzjgListByLikeBhAndCheckDataLimit(String f_zzjgbh, String checkDataLimit) {
		// TODO Auto-generated method stub
		return eszzjgMapper.findZzjgListByLikeBhAndCheckDataLimit(f_zzjgbh, checkDataLimit);
	}
	@Override
	public List<ESZzjg> getBranchOffice() {
		// TODO Auto-generated method stub
		return eszzjgMapper.getBranchOffice();
	}
	@Override
	public List<ESZzjg> loadZzjgTreeJsTwo() {
		// TODO Auto-generated method stub
		return eszzjgMapper.loadZzjgTreeJsTwo();
	}

	@Override
	public List<ESZzjg> findAll()
	{
		return eszzjgMapper.findAll();
	}

	/**
	 * 查询所有子节点
	 *
	 * @param zzjgbh 组织机构
	 * @return
	 */
	@Override
	public List<String> getChildList(String zzjgbh) {
        List<ESZzjg> list = eszzjgMapper.findAll();
        List<String> childList = findNodeChildren(zzjgbh,list);
        childList.add(zzjgbh);
		return childList;
	}
	/**
	 * 递归实现获得所有子节点
	 * @param id 待查询节点
	 * @param nodeList 节点集合
	 * @return
	 */
	List<String> findNodeChildren(String id,List<ESZzjg> nodeList){
		List<String> list = new ArrayList<>();

		for(ESZzjg node : nodeList){
			String nodeId = node.getF_zzjgbh();
			String parent = node.getF_pzzjgbh();
			//如果该节点为子节点，就收集子节点id
			if(parent != null && parent.equals(id)){
				list.add(nodeId);
				list.addAll(findNodeChildren(nodeId,nodeList));
			}
		}
		return list;
	}

}
