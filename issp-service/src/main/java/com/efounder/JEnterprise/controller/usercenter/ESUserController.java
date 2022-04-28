package com.efounder.JEnterprise.controller.usercenter;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;
import com.core.common.security.ISSPSecurityObject;
import com.core.common.security.LimitParamObject;
import com.core.common.tree.IBaseTree;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.framework.common.utils.OperationLog;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 用户定义
 * @author gongfanfei
 * @date 2018年4月17日
 *
 */
@RequestMapping(value = "/view/user")
@Controller
public class ESUserController {

	private static final Logger log = LoggerFactory.getLogger(ESUserController.class);

	@Autowired
	private ESUserService esUserService;

	@Autowired
	private ESZzjgService esZzjgService;
	@Autowired
	private OperationConfig operationConfig;
	/**
	 * @description 初始化到用户自定义主界面
	 * @param map
	 * @return user.ftl
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(ModelMap map) {
		log.info("# ESUserController跳转用户页面");
		return "/isspview/usermanage/user";
	}

	@RequestMapping(value = "/loadAlluser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList() {
		log.info("#ESUserControllers加载全部用户信息列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> list = new ArrayList<ESUser>();
		list = esUserService.findUsers();
		returnObject.setList(list);
		return returnObject;
	}

	@RequestMapping(value = "/zzjg_user", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String zzjgbh) {
		log.info("#ESUserControllers根据组织机构编号加载对应用户信息列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> list = new ArrayList<ESUser>();
		list = esUserService.findUserByzzjgId(zzjgbh);
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 根据组织机构查询用户列表
	 * 递归查询出该组织机构的所有子节点，再根据子节点查询用户
	 * @param zzjgbh 组织机构
	 * @return
	 */
	@RequestMapping(value = "/zzjg_userNew")
	@ResponseBody
	public ISSPReturnObject getChildListNew(String zzjgbh) {
		log.info("#ESUserControllers根据组织机构编号加载对应用户信息列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
//		List<String> childList = esZzjgService.getChildList(zzjgbh);
		JSONArray jsonArray = (JSONArray) JSONArray.parse(zzjgbh);
		Map<String,Object> map = new HashMap<>();
		map.put("array",jsonArray);
		//根据节点及其所有子节点查询用户信息
		List<ESUser> list = esUserService.getUserByZzjg(map);
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 搜索用户信息
	 * @param euserkeywords
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_bykey", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getEuserList(@RequestParam(value = "euserkeywords", required = false) String euserkeywords) {
		log.info("#ESUserControllers搜索用户信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> list = new ArrayList<ESUser>();
		list = esUserService.findUserBykeywords(euserkeywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_yhbh() == null
					|| "".equals(list.get(i).getF_yhbh())) {
				list.get(i).setF_yhbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 添加用户信息
	 * @param user
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUser(@RequestBody ESUser user) {
		log.info("#ESUserControllers添加用户信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			boolean isSucc = esUserService.addUser(user);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(user.getF_yhbh(), "es_user");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("添加用户成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setF_chdate(sdf.format(new Date()));
				user.setF_crdate(sdf.format(new Date()));
				returnObject.setData(user);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加用户失败");
			}
		} catch (Exception e) {
			returnObject.setMsg("登录账号或用户姓名已存在");
			e.printStackTrace();
		}
		return returnObject;

	}

	/**
	 * 删除用户信息
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUser(@RequestBody ESUser user, ModelMap model) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESUserControllers删除用户信息");
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(user.getF_yhbh(), "es_user");
			}
			boolean isSucc = esUserService.deleteUser(user);

			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除用户成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * ajax加载编辑对象
	 * @param usereditdata
	 * @return euser
	 */
	@RequestMapping(value = "/loadeditobj", method = RequestMethod.POST)
	@ResponseBody
	public ESUser loadedit(@RequestBody ESUser usereditdata) {
		log.info("#ESUserControllers ajax加载编辑用户对象");
		ESUser euser = esUserService.findUserById(usereditdata.getF_yhbh());
		return euser;
	}

	/**
	 * 更新修改用户信息
	 * @param user
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upUser(ESUser user) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESUserControllers更新修改用户信息");
		Map<String, Object> startMap = null;
		boolean isSucc = true;
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(user.getF_yhbh(), "es_user");
				isSucc = esUserService.upUser(user);
				OperationLog.updateEnd(user.getF_yhbh(), "es_user", startMap);
			}else{
				isSucc = esUserService.upUser(user);
			}
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setF_chdate(sdf.format(new Date()));
				returnObject.setStatus("1");
				returnObject.setMsg("更新修改用户成功");
				returnObject.setData(user);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新修改用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 更新修改用户信息
	 *
	 * @param user
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_updatepwd", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upUserPass(ESUser user) {
		log.info("#ESUserControllers重置用户密码");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean isSucc = esUserService.upUserPass(user);
		if (isSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("重置密码成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("重置密码失败");
		}
		return returnObject;
	}

    /**
     * 超级狗更新修改用户信息
     *
     * @param user
     * @return returnObject
     */
    @RequestMapping(value = "/SuperDogUptPwd", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject SuperDogUptPwd(ESUser user) {
        log.info("#ESUserControllers超级狗重置用户密码");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        try {
            if ("1".equals(operationConfig.getSysDataBaseUseable())) {
                Map<String, Object> startMap = OperationLog.updateStart(user.getF_yhbh(), "es_user");
                esUserService.updatePersonalCenterPassword(user);
                OperationLog.updateEnd(user.getF_yhbh(), "es_user", startMap);
            } else {
                esUserService.updatePersonalCenterPassword(user);
            }
            returnObject.setStatus("1");
            returnObject.setMsg("密码修改成功！");
        } catch (Exception e) {
            returnObject.setStatus("0");
            returnObject.setMsg("密码修改失败！");
        }
        return returnObject;
    }

	/**
	 * 生成组织机构树
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjg_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserControllers获取组织机构树");

		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
		String qxbh = "0005";
		String pscol = "";
		String yhbh = username;
		String pibzw = "1";
		LimitParamObject paramObject = new LimitParamObject();
		paramObject.setQxbh(qxbh);
		paramObject.setPscol(pscol);
		paramObject.setYhbh(yhbh);
		paramObject.setPibzw(pibzw);
		String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);

		try {
			List<IBaseTree> zzjglist = esZzjgService.loadAll(checkDataLimit);// 根据数据权限配置查询组织机构
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(zzjglist);
			returnObject.setList(ISSPTreeBuilder.buildTree(treeJson));
			returnObject.setMsg("获取组织机构树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取组织机构树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
}
