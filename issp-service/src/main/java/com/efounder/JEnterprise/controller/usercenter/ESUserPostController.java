package com.efounder.JEnterprise.controller.usercenter;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.model.usercenter.UserPostRlgl;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.usercenter.ESPostService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.efounder.JEnterprise.service.usercenter.UserPostRlglService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 岗位自定义
 * @author gongfanfei
 */
@RequestMapping(value = "/view/userpost/")
@Controller
public class ESUserPostController {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostController.class);

	@Autowired
	private ESPostService esPostService;
	@Autowired
	private UserPostRlglService userPostRlglService;
	@Autowired
	private ESUserService esUserService;
	@Autowired
	private ESZzjgService esZzjgService;
	@Autowired
	private OperationConfig operationConfig;
	/**
	 * 获取所有用户岗位
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user_post", method = RequestMethod.GET)
	public String getUserPosts(ModelMap model) {
		log.info("#ESUserPostController获取用户岗位信息");
		//PageInfo<ESPost> page = esPostService.findUserPostByPage(null, null);
		//model.addAttribute("page", page);
		return "/isspview/usermanage/user_post";
	}

	/**
	 * 加载所有岗位信息
	 * @return
	 */
	@RequestMapping(value = "/loadAllPost", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getPostList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESPost> postList = new ArrayList<ESPost>();
		postList = esPostService.findPosts();
		for (int i = 0; i < postList.size(); i++) {
			ESZzjg zzjg = 	esZzjgService.findBybh(postList.get(i).getF_zzjgbh());
			if(zzjg != null){
				postList.get(i).setF_zzjgmc(zzjg.getF_zzjgmc());
			}
		}
		returnObject.setList(postList);
		return returnObject;
	}
	/**
	 * 搜索
	 */
	@RequestMapping(value = "/user_post_search", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserGroupList(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESPost> list = new ArrayList<ESPost>();
		list = esPostService.getUserPostList(keywords);
		for (int i = 0; i < list.size(); i++) {
			ESZzjg zzjg = 	esZzjgService.findBybh(list.get(i).getF_zzjgbh());
			list.get(i).setF_zzjgmc(zzjg.getF_zzjgmc());
			}
		returnObject.setList(list);
		return returnObject;
    }
	 /**
		 * ajax加载编辑对象
		 * @author gongfanfei
		 * @param f_zbh
		 * @param
		 * @return
		 */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESPost loadedit(@RequestBody ESPost espost) {
		log.info("#ESUserPostController ajax加载编辑岗位对象");
		ESPost espostdata = esPostService.findPostById(espost.getF_guid());
		return espostdata;
		}

	@RequestMapping(value = "/zzjg_post")
	@ResponseBody
	public ISSPReturnObject getChildPostList(String zzjgbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(zzjgbh != null){
//			String[] array = zzjgbh.split(",");
			JSONArray jsonArray = (JSONArray) JSONArray.parse(zzjgbh);
			List<ESPost> postList = esPostService.findPostByZzjgbhArray(jsonArray);
			returnObject.setList(postList);
		}
//		List<ESPost> postList = new ArrayList<ESPost>();
		//postList = esPostService.findPostByZzjgId(zzjgbh);
//		postList = esPostService.findPostByZzjgbh(zzjgbh);

	/*	for (int i = 0; i < postList.size(); i++) {

			ESZzjg zzjg = esZzjgService.findBybh(postList.get(i).getF_zzjgbh());

			postList.get(i).setF_zzjgmc(zzjg.getF_zzjgmc());
		}*/
//		returnObject.setList(postList);
		return returnObject;
	}
	/**
	 * 添加用户岗位
	 *
	 * @return
	 */
	@RequestMapping(value = "user_post_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUserPost(@RequestBody ESPost post, ModelMap model) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESUserPostController添加岗位信息");
		try {
			//设定四位数字，不够位数，自动补“0”
			DecimalFormat df=new DecimalFormat("0000");
			String f_guid =  UUIDUtil.getRandom32BeginTimePK();
			post.setF_guid(f_guid);
			List<ESPost> postlist = esPostService.findPosts();
			int postLen = postlist.size();
			if(postLen==0){
				post.setF_gwbh("0000");
			}else{
			String pgwbh = postlist.get(postLen-1).getF_gwbh();
			Integer cuPostbh = (Integer.parseInt(pgwbh)+1);
			String  currentPostbh = df.format(cuPostbh);
			post.setF_gwbh(currentPostbh);
			}
			boolean isSucc = esPostService.addPost(post);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(post.getF_guid(), "es_post");
			}
			ESZzjg zzjg = 	esZzjgService.findBybh(post.getF_zzjgbh());
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				returnObject.setStatus("1");
				returnObject.setMsg("添加岗位成功");
				post.setF_chdate(sdf.format(new Date()));
				post.setF_crdate(sdf.format(new Date()));
				HashMap<Object, Object> map = new HashMap<>();
				map.put("0", post);
				map.put("1", zzjg);
				returnObject.setMap(map);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加岗位失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 删除岗位信息
	 *
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user_post_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUserPosts(@RequestBody ESPost post) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESUserPostController删除岗位信息");
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(post.getF_guid(), "es_post");
			}
			boolean isSucc = esPostService.delPost(post);
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除岗位成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除岗位失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
     * @Description ajax更新岗位信息
     * @author gongfanfei
     * @param userpost
     * @return
     */
    @RequestMapping(value = "/userpost_edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESPost espost, ModelMap model) {
    	 ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#ESUserPostController修改岗位信息");
    	Map<String, Object> startMap;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(espost.getF_guid(), "es_post");
				flag = esPostService.upPost(espost);
				OperationLog.updateEnd(espost.getF_guid(), "es_post", startMap);
			}else{
				flag = esPostService.upPost(espost);
			}
	        ESPost espostdata = esPostService.findPostById(espost.getF_guid());
	        if (flag) {
	            returnObject.setStatus("1");
				returnObject.setMsg("岗位修改成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				espostdata.setF_chdate(sdf.format(new Date()));
				returnObject.setData(espostdata);
	        } else {
	        	returnObject.setStatus("0");
				returnObject.setMsg("岗位修改失败");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnObject;
    }

    //包含用户/////////////////////
    /**
	 * 加载岗位关系对应的用户信息
	 * @author gongfanfei
	 * @param f_zbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadPostRlglUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserPostRlglList(String f_gwguid,String keywords) {
		log.info("#ESUserPostController ajax加载编辑岗位包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		List<ESUser> EUserResult = new ArrayList<ESUser>();
		List<ESUser> newEuserL = new ArrayList<ESUser>();
		List<UserPostRlgl> rlglList = new ArrayList<UserPostRlgl>();
		rlglList = userPostRlglService.findChildRlglByPostbh(f_gwguid);
		for (int i = 0; i < rlglList.size(); i++) {
			ESUser user = esUserService.findUserById(rlglList.get(i).getF_yhbh());
			EUserlist.add(i, user);
		}
		if(keywords != null){
			EUserResult = esUserService.findUserBykeywords(keywords);//通过关键字查询到用户列表
			int index = 0;
			for (int i = 0; i < EUserlist.size(); i++) {
				for (int j = 0; j < EUserResult.size(); j++) {
					if(EUserResult.get(j).getF_yhbh().equals(EUserlist.get(i).getF_yhbh())){
						newEuserL.add(index++, EUserResult.get(j));
					}
				}
			}
		}else{
			newEuserL = EUserlist;
		}
		returnObject.setList(newEuserL);
		return returnObject;
	}

	/**
	 * 加载岗位关系未包含的用户
	 * @author gongfanfei
	 * @param f_gwguid
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getNoUserPostRlglList(String f_gwguid,String keywords) {
		log.info("#ESUserPostController ajax加载编辑岗位包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<UserPostRlgl> rlglList = new ArrayList<UserPostRlgl>();
		//得到所有用户信息EUserlist
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		EUserlist = esUserService.findUsersBykey(keywords);
		//通过f_zbh得到岗位关系中用户rlglList
		rlglList = userPostRlglService.findChildRlglByPostbh(f_gwguid);
		for (int i = 0; i < rlglList.size(); i++) {
			for (int j = 0; j < EUserlist.size(); j++) {
				if(EUserlist.get(j).getF_yhbh().equals(rlglList.get(i).getF_yhbh())){
					EUserlist.remove(j);
				}

			}
		}
		returnObject.setList(EUserlist);
		return returnObject;
	}

	 /**
     * @Description ajax添加岗位成员信息
     * @author gongfanfei
     * @param userpostrlgl
     * @return
     */
    @RequestMapping(value = "/userpost_insertuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertUserToPost(@RequestBody UserPostRlgl userpostrlgl, ModelMap model) {
    	log.info("#ESUserPostController添加岗位成员信息");
    	boolean flag = userPostRlglService.addUserPostRlgl(userpostrlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(userpostrlgl.getF_yhbh());
    	if (flag) {
    		returnObject.setStatus("1");
    		returnObject.setMsg("添加成功");
    		returnObject.setData(userdata);
    	} else {
    		returnObject.setStatus("0");
    		returnObject.setMsg("添加失败");
    	}
    	return returnObject;
    }
    /**
     * @Description ajax删除岗位成员信息
     * @author gongfanfei
     * @param userpostrlgl
     * @return
     */
    @RequestMapping(value = "/userpost_deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteUserToPost(@RequestBody UserPostRlgl userpostrlgl, ModelMap model) {
    	log.info("#UserGroupController移除岗位信息");
    	boolean flag = userPostRlglService.delUserPostRlgl(userpostrlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(userpostrlgl.getF_yhbh());
    	if (flag) {
    		returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
			returnObject.setData(userdata);
    	} else {
    		returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
    	}
    	return returnObject;
    }
    /**
     * @Description 清空岗位成员
     * @Description ajax删除对应岗位所有成员信息
     * @author gongfanfei
     * @param userpostrlgl
     * @return
     */
    @RequestMapping(value = "/userpost_leftmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAllUserToPost(@RequestBody UserPostRlgl userpostrlgl, ModelMap model) {

    	log.info("#ESUserGroupController清空岗位信息");
    	boolean flag = userPostRlglService.leftMoveAllUsers(userpostrlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	if (flag) {
    		returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
    	} else {
    		returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
    	}
    	return returnObject;
    }
    /**
     * @Description 添加全部岗位成员
     * @Description ajax添加至岗位所有成员信息
     * @author gongfanfei
     * @param userpostrlgl
     * @return
     */
    @RequestMapping(value = "/userpost_rightmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addAllUserToPost(@RequestBody UserPostRlgl userpostrlgl, ModelMap model) {
    	log.info("#ESUserPostController添加全部岗位信息");
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESUser> EUserlist = esUserService.findUsers();//查找所有用户
    	List<UserPostRlgl> rlglList = userPostRlglService.findChildRlglByPostbh(userpostrlgl.getF_gwguid());//查找岗位编号对应所有用户编码信息
    	for (int i = 0; i < rlglList.size(); i++) {
			for (int j = 0; j < EUserlist.size(); j++) {
				if(EUserlist.get(j).getF_yhbh().equals(rlglList.get(i).getF_yhbh())){
					EUserlist.remove(j);
				}
			}
		}
    	for (int i = 0; i < EUserlist.size(); i++) {
    		userpostrlgl.setF_yhbh(EUserlist.get(i).getF_yhbh());
    		boolean flag = userPostRlglService.addUserPostRlgl(userpostrlgl);
    		if(flag == false){
    			log.info("#ESUserPostController添加全部岗位信息失败");
    		}
		}
    	returnObject.setList(EUserlist);
    	return returnObject;
    }




}
