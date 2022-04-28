package com.core.common.util;

import com.core.common.constants.Constants;

/**
 * 获取当前用户ID。
 * @author yangqichao
 */
public abstract class CurrentUserUtils {
    
    /**
     * 当前用户ID。
     */
    private static final ThreadLocal<Long> CURR_USER_ID = new InheritableThreadLocal<>();
    
    
    /**
     * 获取当前用户ID。
     * @return 当前用户ID，不存在时return -1
     */
    public static Long getUserId() {
        Long userId = CURR_USER_ID.get();
        return userId == null ? Constants.ANONYMOUS_ID : userId;
    }
    
    /**
     * 保存当前用户ID。
     * @param userId 当前用户ID
     */
    public static void setUserId(Long userId) {
        CURR_USER_ID.set(userId);
    }
    
    /**
     * 清除当前用户ID。
     */
    public static void clear() {
        CURR_USER_ID.remove();
    }
    
}

