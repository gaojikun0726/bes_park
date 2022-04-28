package com.efounder.JEnterprise.service.datacenter.impl;

import com.efounder.JEnterprise.mapper.datacenter.ESGnzdMapper;
import com.efounder.JEnterprise.mapper.usercenter.ESUserGnqxManageMapper;
import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import com.efounder.JEnterprise.service.datacenter.ESGnzdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：功能字典接口实现类 
 * 创建人：pql 
 * 修改时间：2018年2月2日
 *
 */
@Service("ESGnzdService")
public class ESGnzdServiceImpl implements ESGnzdService {

	private static final Logger log = LoggerFactory.getLogger(ESGnzdServiceImpl.class);

	@Autowired
	private ESGnzdMapper esGnzdMapper;

	@Resource
	private ESUserGnqxManageMapper esUserGnqxManageMapper;

	/**
	 * 查询所有
	 */
	@Override
	public List<ESGnzd> loadGnzdList() {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdList();
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 根据功能编号查询
	 */
	@Override
	public List<ESGnzd> loadGnzdListByGnbh(String gnbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByGnbh(gnbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 根据父功能编号查询
	 */
	@Override
	public List<ESGnzd> loadGnzdListByPgnbh(String gnbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByPgnbh(gnbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 查询所有
	 */
	@Override
	public List<ESGnzd> loadGnzdListByMkuuid(String mkbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByMkuuid(mkbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 根据模块和对应表查询所有
	 */
	@Override
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndSh(String mkbh,String tablename,String fYhbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByMkuuidAndTableAndSh(mkbh,tablename,fYhbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	@Override
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndRole(String mkbh,String tablename,String fRolebh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByMkuuidAndTableAndShAndRole(mkbh,tablename,fRolebh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 根据模块编号和父节点为空作为条件查询功能字典列表
	 */
	@Override
	public List<ESGnzd> getGnzdByMkbhAndNull(String mkbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.getGnzdByMkbhAndNull(mkbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 通过功能名称查询
	 */
	@Override
	public List<ESGnzd> getGnzdList(String gnmc) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.findGnzdByMC(gnmc);

		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	/**
	 * 根据编号获取所有子节点
	 */
	@Override
	public List<ESGnzd> findChildrenByBH(String pgnbh, String mkbh){
		List<ESGnzd> list = esGnzdMapper.findChildrenByBH(pgnbh, mkbh);
		log.info("#esGnzdMapper 获取获取子节点 ", list.toString());
		return list;
	}
	
	/**
	 * 新增功能菜单
	 */
	@Override
	public boolean addGnzd(ESGnzd gnzd)
	{
		if(gnzd != null)
		{
			log.info("#esGnzdMapper新增功能菜单");
			int flag = esGnzdMapper.insertGnzd(gnzd);
			if (flag == 1)
				return true;
		}
		return false;
	}
	/**
	 * 修改功能菜单(全部)
	 */
	@Override
	public boolean editGnzdByGnbh(ESGnzd gnzd)
	{
		log.info("#esGnzdMapper修改功能菜单");
		int flag = esGnzdMapper.updateBygnbh(gnzd);
		if(flag == 1)
		{
			return true;
		}
		return false;
	}
	/**
	 * 修改功能菜单（url、gnmc）
	 */
	@Override
	public boolean editGnzd(ESGnzd gnzd)
	{
		log.info("#esGnzdMapper修改功能菜单");
		int flag = esGnzdMapper.update(gnzd);
		if(flag == 1)
		{
			return true;
		}
		return false;
	}
	/**
	 * 删除功能菜单
	 * @param gnbh
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteGnzd(String gnbh, String mkbh)
	{
		//删除该菜单关联的用户功能权限
		log.info("#esGnzdMapper删除功能菜单");
		Map map = new HashMap();
		map.put("gnbh",gnbh);
		map.put("mkbh",mkbh);
		List<String> list = esGnzdMapper.selectGnzdId(map);
		map.put("list",list);
		esUserGnqxManageMapper.delRelativeGnqx(map);
		esGnzdMapper.delGnzdList(map);
		return true;
	}
	/**
	 * 根据功能编号更新明细
	 * @param gnbh
	 * @return
	 */
	@Override
	public boolean updateMxByBh(String gnbh, String mkbh, String mx)
	{
		log.info("#esGnzdMapper更新明细列");
		return esGnzdMapper.updateMx(gnbh, mkbh, mx);
	}
	@Override
	public List<ESGnzd> getGnzdListBygnzdlist(List<String> gnzds) {
		// TODO Auto-generated method stub
		return esGnzdMapper.getGnzdListBygnzdlist(gnzds);
	}
	@Override
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndGroup(String guid, String classifygnqxTb, String f_zbh) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByMkuuidAndTableAndShAndGroup(guid,classifygnqxTb,f_zbh);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	@Override
	public List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndPost(String guid, String classifygnqxTb, String f_gwguid) {
		// TODO Auto-generated method stub
		List<ESGnzd> list = esGnzdMapper.loadGnzdListByMkuuidAndTableAndShAndPost(guid,classifygnqxTb,f_gwguid);
		log.info("#esGnzdMapper 查询默认数据库 ", list.toString());
		return list;
	}
	
}
