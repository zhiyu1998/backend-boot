package cn.zhiyucs.common.basic.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户
 *
 * @author zhiyu1998
 */
public class SecurityUser {

    private SecurityUser(){}
    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return new UserDetail();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

}