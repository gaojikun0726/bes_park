package com.efounder.JEnterprise.controller.systemcenter.personalcenter;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @Description 个人中心
 * @author liuhoujun
 * @date 2018/11/26
 *
 */
@SuppressWarnings("restriction")
@RequestMapping(value = "/view/personalcenter")
@Controller
public class ESPersonalCenterController {

	private static final Logger log = LoggerFactory.getLogger(ESPersonalCenterController.class);
	@Autowired
	private ESUserService esUserService;
	@Autowired
	private OperationConfig operationConfig;

	/**
	 * ajax加载编辑对象
	 * @param usereditdata
	 * @return euser
	 */
	@RequestMapping(value = "/loadUsereditobj", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadedit() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESPersonalCenterController ajax加载编辑用户对象");
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		// 获取当前用户的用户名
		ESUser euser = esUserService.findUserById(principal.getUser().getF_yhbh());
		if (euser == null) {
			returnObject.setStatus("0");
			return returnObject;
		}
		returnObject.setData(euser);
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 * 更新修改个人中心用户信息
	 * @param user
	 * @return returnObject
	 */
	@RequestMapping(value = "/user_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upUser(ESUser user) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESPersonalCenterController更新修改个人中心用户信息");
		boolean isSucc = true;
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				Map<String, Object> startMap = OperationLog.updateStart(user.getF_yhbh(), "es_user");
				isSucc = esUserService.updatePersonalCenterUserInfo(user);
				OperationLog.updateEnd(user.getF_yhbh(), "es_user", startMap);
			}else{
				isSucc = esUserService.updatePersonalCenterUserInfo(user);
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
			returnObject.setStatus("0");
			returnObject.setMsg("更新修改用户失败");
		}
		return returnObject;
	}

	/**
	 * 用户更换头像
	 * @param user
	 * @return returnObject
	 */
		@RequestMapping(value="/user_updateHeadPortrait", method = RequestMethod.POST)
	    @ResponseBody
	    public ISSPReturnObject uploadImg(@RequestParam(value = "headUploadFileBase64", required = false)String headUploadFileBase64,
	                                      HttpServletRequest request){
	        log.info("个人头像上传...");
	        ISSPReturnObject returnObject = new ISSPReturnObject();
	        try{
	        	Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
	        	ESUser user = new ESUser();
	        	String yhbh = principal.getUser().getF_yhbh();
	        	user.setF_yhbh(yhbh);
	            String imgName = getImgName(yhbh, "png");
	            String root = request.getSession().getServletContext().getRealPath("/personalcenter/");
	            uploadFile(base64StrToByteAttr(headUploadFileBase64), imgName,root);
				user.setF_headimg(imgName);
	            Boolean res = esUserService.updatePersonalCenterUserhead(user);
	            if (res == true){
	                returnObject.setStatus("1");
	                returnObject.setData(imgName);
	                returnObject.setMsg("头像上传成功");
	                return returnObject;
	            }
	            returnObject.setStatus("0");
	            returnObject.setMsg("头像上传失败");
	            return returnObject;
	        }catch (Exception e){
	            log.error("#ESPersonalCenterController.uploadImg Exception: ", e);
	            returnObject.setStatus("0");
	            returnObject.setMsg("头像上传失败");
	            return returnObject;
	        }
	    }
	/**
	 * 动态获取图片路径
	 */
	@RequestMapping(value = "/getimgurl", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getImgUrl() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESPersonalCenterController ajax加载获取图片路径");
		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		// 获取当前用户的图片路径
		ESUser euser = esUserService.findUserByBH(principa.getUser().getF_yhbh());
		if(euser == null){
			returnObject.setMsg("加载头像图片失败");
			returnObject.setStatus("0");
			return returnObject;
		}
		returnObject.setData(euser.getF_headimg());
		returnObject.setStatus("1");
		return returnObject;
	}

	/**
	 * 更新密码
	 * 
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return 
	 */
	@RequestMapping(value = "/user_updatepwd", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject exUserPass(String oldPass, String newPass) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESUserControllers重置用户密码");
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		String yhbh = principal.getUser().getF_yhbh();//用户编号
		UsernamePasswordToken token = new UsernamePasswordToken(yhbh, oldPass);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			returnObject.setStatus("0");
			returnObject.setMsg("原密码不正确，未知账户！");
			return returnObject;
		} catch (IncorrectCredentialsException ice) {
			returnObject.setStatus("0");
			returnObject.setMsg("原密码不正确！");
			return returnObject;
		} catch (LockedAccountException lae) {
			returnObject.setStatus("0");
			returnObject.setMsg("该账户已锁定！");
			return returnObject;
		} catch (ExcessiveAttemptsException eae) {
			returnObject.setStatus("0");
			returnObject.setMsg("原密码输入错误次数过多！");
			return returnObject;
		} catch (AuthenticationException ae) {
			returnObject.setStatus("0");
			returnObject.setMsg("原密码不正确！");
			return returnObject;
		}
		// 验证通过则修改密码
		if (currentUser.isAuthenticated()) {
			try {
				ESUser user = new ESUser();
				user.setF_yhbh(yhbh);
				user.setF_pass(newPass);
				if ("1".equals(operationConfig.getSysDataBaseUseable())) {
					Map<String, Object> startMap = OperationLog.updateStart(yhbh, "es_user");
					esUserService.updatePersonalCenterPassword(user);
					OperationLog.updateEnd(yhbh, "es_user", startMap);
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
		returnObject.setStatus("0");
		returnObject.setMsg("密码修改失败！");
		return returnObject;
	}
	
	
	
	
	
	
	
	
	
	
	/**
     * 头像上传工具类
     * @param file
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String fileName, String imageUrl) throws Exception
    {
        File targetFile = new File(imageUrl);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(imageUrl + fileName);
        out.write(file);
        out.flush();
        out.close();
    }


    /**
     * 返回图片名字
     * @param roomId
     * @param imgType
     * @param format
     * @return
     */
    public static String getImgName(String userId, String format)
    {
        return userId + "." + format;
    }

    /**
     * 获取图片的格式
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName)
    {
        String[] fileNames = fileName.split("\\.");
        return fileNames[fileNames.length - 1];
    }

    /**
     * base64字符串转图片字节数组
     * @param base64Str
     * @return
     */
    public static byte[] base64StrToByteAttr(String base64Str) throws IOException {
        if (StringUtil.isEmpty(base64Str)) // 图像数据为空
            return new byte[0];
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] fileByte =decoder.decodeBuffer(base64Str);
        for (int i = 0; i < fileByte.length; ++i) {
            if (fileByte[i] < 0) {// 调整异常数据
                fileByte[i] += 256;
            }
        }
        return fileByte;
    }
}
