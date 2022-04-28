package com.efounder.JEnterprise.service.auth.impl;

import com.core.common.util.RedisStorageManager;
import com.efounder.JEnterprise.common.exception.BusinessException;
import com.efounder.JEnterprise.config.shiro.vo.PermissionMenuVo;
import com.efounder.JEnterprise.mapper.auth.PermissionMapper;
import com.efounder.JEnterprise.model.auth.PermissionMenu;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.auth.PermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("all")
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService, ESBaseService {

	/**
	 * ApplicationName:应用ID
	 *
	 * @since 1.0.0
	 */
	@Value("${JEnterprise.ApplicationId}")
	private String ApplicationId;

	public String getApplicationId() {
		return ApplicationId;
	}

	public void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}

	@Autowired
	private PermissionMapper permissionMapper;

	private PermissionMenuVo convertToVo(PermissionMenu per) {
		PermissionMenuVo pvo = new PermissionMenuVo();
		pvo.setId(per.getId());
		pvo.setName(per.getName());
		pvo.setCssClass(per.getCssClass());
		pvo.setUrl(per.getUrl());
		pvo.setKey(per.getKey());
		pvo.setParentKey(per.getParentKey());
		pvo.setHide(per.getHide());
		pvo.setLev(per.getLev());
		pvo.setSort(per.getSort());
		pvo.setFullScreen(per.getFullScreen());
		pvo.setFormParam(per.getFormParam());
		pvo.setOpenType(per.getOpenType());
		pvo.setTabCloseable(per.getTabCloseable());
		return pvo;
	}

	@Autowired
	private RedisStorageManager redisStorageService;

	public List<PermissionModule> getPermissionModules(String userId) {
		Map mapParam = new HashMap();
		mapParam.put("userId", userId);
		mapParam.put("xtbh", ApplicationId);
		return permissionMapper.findPermissionModuleByUserId(mapParam);
	}
	public List<PermissionModule> getHomeModules() {
		Map mapParam = new HashMap();
		mapParam.put("xtbh", ApplicationId);
		return permissionMapper.findPermissionModuleByUserId(mapParam);
	}
	public List<PermissionModule> getHomeModulesByXtbh(String xtbh) {
		Map mapParam = new HashMap();
		mapParam.put("xtbh", xtbh);
		return permissionMapper.findPermissionModuleByXtbh(mapParam);
	}
	public PermissionModule getPermissionMenusByMkbh(String mkbh) {
		return permissionMapper.findModuleObjByMkbh(mkbh);
	}

	@Override
	// @Cacheable(value="Permission")
	public Map getPermissionMenus(String userId, List<PermissionModule> moduleList, String limitSQL) {
		Map resultMap = new HashMap();
		List<PermissionModule> delModelList = new ArrayList<PermissionModule>();
		String mkGuid, mkbh;
		for (int i = 0; i < moduleList.size(); i++) {
			mkGuid = moduleList.get(i).getGuid();
			mkbh = moduleList.get(i).getMkbh();
			List<PermissionMenuVo> pmv = getPermissionMenusByModuleGUID(userId, mkGuid, limitSQL);
			if (pmv != null) {
				resultMap.put(mkbh, pmv);
			}else{
				delModelList.add(moduleList.get(i));
			}
		}
		resultMap.put("delModelList", delModelList);
		return resultMap;
	}

	public List<PermissionMenuVo> getPermissionMenusByModuleGUID(String userId, String mkGuid, String limitSQL) {
		Map mapParam = new HashMap();
		mapParam.put("userId", userId);
		mapParam.put("guid", mkGuid);
		mapParam.put("limitSQL", limitSQL);

		List<PermissionMenu> permissions = permissionMapper.findPermissionMenuByUserId(mapParam);

		if (CollectionUtils.isEmpty(permissions)) {
			return null;
		}

		if (CollectionUtils.isNotEmpty(permissions)) {
			Map<String, PermissionMenuVo> oneMap = new LinkedHashMap<String, PermissionMenuVo>();// 一级菜单
			Map<String, PermissionMenuVo> twoMap = new LinkedHashMap<String, PermissionMenuVo>();// 二级菜单
			Map<String, PermissionMenuVo> threeMap = new LinkedHashMap<String, PermissionMenuVo>();// 三级菜单

			String parentKey = null, key = null;
			Integer lev = null;
			PermissionMenuVo child = null, parent = null, permissionVo = null;
			for (PermissionMenu p : permissions) {
				key = p.getKey();
				lev = p.getLev();
				permissionVo = convertToVo(p);
				if (1 == lev) {// 判断是不是模块
					oneMap.put(key, permissionVo);
				}
				if (2 == lev) {// 判断是不是菜单分类
					twoMap.put(key, permissionVo);
				}
				if (3 == lev) {// 判断是不是菜单
					threeMap.put(key, permissionVo);
				}
			}

			List<PermissionMenuVo> vos = null;

			// 迭代所有3级菜单， 把3级菜单挂在2级菜单分类下面去
			for (Entry<String, PermissionMenuVo> vo : threeMap.entrySet()) {
				child = vo.getValue();// 3级菜单
				parentKey = child.getParentKey();// 获取3级菜单对应的2级菜单KEY，即父节点KEY
				if (twoMap.containsKey(parentKey)) {// 校验当前拿到的2级菜单KEY在twoMap集合中有没有
					parent = twoMap.get(parentKey);// 获取对应的2级菜单

					vos = parent.getChildren();// 获取2级菜单下3级菜单集合
					if (CollectionUtils.isEmpty(vos)) {
						vos = new ArrayList<>();
					}
					vos.add(child);// 将3级菜单挂在2级菜单下去
					parent.setChildren(vos);
					twoMap.put(parentKey, parent);
				}
			}

			// 迭代所有2级菜单， 把2级菜单挂在1级菜单分类下面去
			for (Entry<String, PermissionMenuVo> vo : twoMap.entrySet()) {
				child = vo.getValue();// 2级菜单
				parentKey = child.getParentKey();// 获取2级菜单对应的1级菜单KEY，即父节点KEY
				if (oneMap.containsKey(parentKey)) {// 校验当前拿到的1级菜单KEY在oneMap集合中有没有
					parent = oneMap.get(parentKey);// 获取对应的1级菜单

					vos = parent.getChildren();// 获取1级菜单下2级菜单集合
					if (CollectionUtils.isEmpty(vos)) {
						vos = new ArrayList<PermissionMenuVo>();
					}
					vos.add(child);// 将2级菜单挂在1级菜单下去
					parent.setChildren(vos);
					oneMap.put(parentKey, parent);
				}
			}

			List<PermissionMenuVo> permissionVos = new ArrayList<PermissionMenuVo>();
			permissionVos.addAll(oneMap.values());

			redisStorageService.setJson("permissionVos", permissionVos, 0);
			return permissionVos;
		} else {
			return null;
		}
	}


	/**
	 * 查询用户所能访问的所有模块+菜单
	 *
	 * @param userId     用户ID
	 * @param moduleList
	 * @param limitSQL
	 * @return permissions 菜单
	 */
	@Override
	public List<PermissionMenuVo> findModuleMenuByUserId(String userId, String limitSQL) {
		Map mapParam = new HashMap();
		mapParam.put("userId", userId);
//		mapParam.put("guid", mkGuid);
		mapParam.put("limitSQL", limitSQL);
		mapParam.put("xtbh", ApplicationId);

		List<PermissionMenu> permissions = permissionMapper.findModuleMenuByUserId(mapParam);

		if (CollectionUtils.isEmpty(permissions)) {
			return null;
		}

		List<PermissionMenu> moduleList = new ArrayList<>();
		//查询首页信息,将首页信息放到顶级菜单中
		List<PermissionMenu> firstList = permissionMapper.findFirstPageInfo(mapParam);
		if(firstList.size() > 0 && firstList.get(0) != null){
			moduleList.add(firstList.get(0));
		}
		List<PermissionMenu> mList = permissionMapper.findModuleByUserId(mapParam);
		moduleList.addAll(mList);

		Map<String, PermissionMenuVo> topMap = new LinkedHashMap<String, PermissionMenuVo>();//模块
		PermissionMenuVo menuVo = null;
		for(PermissionMenu p : moduleList){
			String key = p.getKey();
			menuVo = convertToVo(p);
			topMap.put(key,menuVo);
		}

		if (CollectionUtils.isNotEmpty(permissions)) {
			Map<String, PermissionMenuVo> oneMap = new LinkedHashMap<String, PermissionMenuVo>();// 一级菜单
			Map<String, PermissionMenuVo> twoMap = new LinkedHashMap<String, PermissionMenuVo>();// 二级菜单
			Map<String, PermissionMenuVo> threeMap = new LinkedHashMap<String, PermissionMenuVo>();// 三级菜单

			String parentKey = null, key = null;
			Integer lev = null;
			PermissionMenuVo child = null, parent = null, permissionVo = null;
			for (PermissionMenu p : permissions) {
				key = p.getKey();
				lev = p.getLev();
				permissionVo = convertToVo(p);
//				if(lev == 0){//模块
//					topMap.put(key,permissionVo);
//				}
				if (1 == lev) {// 判断是不是模块
					oneMap.put(key, permissionVo);
				}
				if (2 == lev) {// 判断是不是菜单分类
					twoMap.put(key, permissionVo);
				}
				if (3 == lev) {// 判断是不是菜单
					threeMap.put(key, permissionVo);
				}
			}

			List<PermissionMenuVo> vos = null;

			// 迭代所有3级菜单， 把3级菜单挂在2级菜单分类下面去
			for (Entry<String, PermissionMenuVo> vo : threeMap.entrySet()) {
				child = vo.getValue();// 3级菜单
				parentKey = child.getParentKey();// 获取3级菜单对应的2级菜单KEY，即父节点KEY
				if (twoMap.containsKey(parentKey)) {// 校验当前拿到的2级菜单KEY在twoMap集合中有没有
					parent = twoMap.get(parentKey);// 获取对应的2级菜单

					vos = parent.getChildren();// 获取2级菜单下3级菜单集合
					if (CollectionUtils.isEmpty(vos)) {
						vos = new ArrayList<>();
					}
					vos.add(child);// 将3级菜单挂在2级菜单下去
					parent.setChildren(vos);
					twoMap.put(parentKey, parent);
				}
			}

			// 迭代所有2级菜单， 把2级菜单挂在1级菜单分类下面去
			for (Entry<String, PermissionMenuVo> vo : twoMap.entrySet()) {
				child = vo.getValue();// 2级菜单
				parentKey = child.getParentKey();// 获取2级菜单对应的1级菜单KEY，即父节点KEY
				if (oneMap.containsKey(parentKey)) {// 校验当前拿到的1级菜单KEY在oneMap集合中有没有
					parent = oneMap.get(parentKey);// 获取对应的1级菜单

					vos = parent.getChildren();// 获取1级菜单下2级菜单集合
					if (CollectionUtils.isEmpty(vos)) {
						vos = new ArrayList<PermissionMenuVo>();
					}
					vos.add(child);// 将2级菜单挂在1级菜单下去
					parent.setChildren(vos);
					oneMap.put(parentKey, parent);
				}
			}

			//把模块下的所有菜单挂到模块上去
//			for(Entry<String, PermissionMenuVo> vo : oneMap.entrySet()){
//				child = vo.getValue();// 2级菜单
//				parentKey = child.getParentKey();// 获取2级菜单对应的1级菜单KEY，即父节点KEY
//				if (topMap.containsKey(parentKey)) {// 校验当前拿到的1级菜单KEY在oneMap集合中有没有
//					parent = topMap.get(parentKey);// 获取对应的1级菜单
//
//					vos = parent.getChildren();// 获取1级菜单下2级菜单集合
//					if (CollectionUtils.isEmpty(vos)) {
//						vos = new ArrayList<PermissionMenuVo>();
//					}
//					vos.add(child);// 将2级菜单挂在1级菜单下去
//					parent.setChildren(vos);
//					topMap.put(parentKey, parent);
//				}
//			}

			for(Entry<String, PermissionMenuVo> entry : oneMap.entrySet()){
				String k = entry.getKey();
				PermissionMenuVo entity = entry.getValue();
				parentKey = entity.getParentKey();
				if(topMap.containsKey(parentKey)){
					parent = topMap.get(parentKey);
					List<PermissionMenuVo> children = parent.getChildren();
					if (CollectionUtils.isEmpty(children)) {
						children = new ArrayList<PermissionMenuVo>();
					}
					children.add(entity);
					parent.setChildren(children);
				}
			}

			List<PermissionMenuVo> permissionVos = new ArrayList<PermissionMenuVo>();
			permissionVos.addAll(topMap.values());

			redisStorageService.setJson("permissionVos", permissionVos, 0);
			Iterator<PermissionMenuVo> iterator = permissionVos.iterator();
			while(iterator.hasNext()){
				PermissionMenuVo permissionMenuVo = iterator.next();
				String url = permissionMenuVo.getUrl();
				List children = permissionMenuVo.getChildren();
				if( (url == null || "".equals(url)) && (children == null || children.isEmpty())){
					//如果url为空，并且没有子菜单，这个模块就隐藏
					iterator.remove();
				}
			}
			return permissionVos;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public void addPermission(PermissionMenu permission) {
		if (permission == null || StringUtils.isBlank(permission.getKey())
				|| StringUtils.isBlank(permission.getName())) {
			throw new BusinessException("permission-fail", "## 创建菜单出错；菜单项数据不完整，无法进行创建。");
		}
		PermissionMenu p = permissionMapper.findPermissionMenuByKey(permission.getKey());
		if (p != null)
			throw new BusinessException("permission-fail", "#创建菜单出错;菜单Key已经存在,key=" + permission.getKey());
		//permission.setId(FactoryAboutKey.getPK(TablesPKEnum.T_SYS_PERMISSION));
		permissionMapper.insert(permission);
	}

}
