package com.efounder.JEnterprise.common.constants;

/**
 *   
 * 类名称：Constants
 * 类描述：常量类
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:26:42
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class Constants {

    /* 分页操作时，每页只显示10条 */
    public static final Integer PAGE_SIZE = 20;

    /* 状态,1=有效，0=失效 */
    public static final Integer STATUS_VALID = 1;
    public static final Integer STATUS_INVALID = 0;

    /* session & session key */
    public static final String PERMISSION_MENU_SESSION = "permission_menu_session";
    public static final String PERMISSION_MODULE_ESSION = "permission_module_session";
    public static final String SESSION_KEY = "session_key";

    /* 用户名 */
    public static final String USERNAME = "username";

    // url & roleName
    public static final String ROLE_CODE = "role_code";
    public static final String GROUP_CODE = "group_code";
    public static final String PERMISSION_URL = "permission_url";

    public static final String ROLE_BOSS_CODE = "boss_role";
    public static final String ROLE_MANAGER_CODE = "manager_role";// 管理员
    public static final String COMMON_ROLE_CODE = "common_role";// 普通用户

    public static final String ROLE_BOSS_NAME = "总经理";
    public static final String ROLE_MANAGER_NAME = "管理员";
    public static final String ROLE_COMMON_NAME = "普通用户";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
