package cn.zhiyucs.security.event;

import cn.zhiyucs.common.user.UserDetail;
import cn.zhiyucs.constant.Constant;
import cn.zhiyucs.system.enums.LoginOperationEnum;
import cn.zhiyucs.system.service.SysLogLoginService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 认证事件处理
 *
 * @author zhiyu1998
 */
@Component
@AllArgsConstructor
public class AuthenticationEvents {
    private final SysLogLoginService sysLogLoginService;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        // 用户信息
        UserDetail user = (UserDetail) event.getAuthentication().getPrincipal();

        // 保存登录日志
        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGIN_SUCCESS.getValue());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        // 用户名
        String username = (String) event.getAuthentication().getPrincipal();

        // 保存登录日志
        sysLogLoginService.save(username, Constant.FAIL, LoginOperationEnum.ACCOUNT_FAIL.getValue());
    }

}