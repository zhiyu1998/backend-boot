package cn.zhiyucs.system.controller;

import cn.zhiyucs.system.service.SysAuthService;
import cn.zhiyucs.system.service.SysCaptchaService;
import cn.zhiyucs.system.vo.SysAccountLoginVO;
import cn.zhiyucs.system.vo.SysCaptchaVO;
import cn.zhiyucs.system.vo.SysMobileLoginVO;
import cn.zhiyucs.system.vo.SysTokenVO;
import cn.zhiyucs.utils.R;
import cn.zhiyucs.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 认证管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/auth")
@Tag(name = "认证管理")
public class SysAuthController {
    @Resource
    private SysCaptchaService sysCaptchaService;

    @Resource
    private SysAuthService sysAuthService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    public R<SysCaptchaVO> captcha() {
        SysCaptchaVO captchaVO = sysCaptchaService.generate();

        return R.ok(captchaVO);
    }

    @PostMapping("login")
    @Operation(summary = "账号密码登录")
    public R<SysTokenVO> login(@RequestBody SysAccountLoginVO login) {
        SysTokenVO token = sysAuthService.loginByAccount(login);

        return R.ok(token);
    }

    @PostMapping("send/code")
    @Operation(summary = "发送短信验证码")
    public R<String> sendCode(String mobile) {
        boolean flag = sysAuthService.sendCode(mobile);
        if (!flag) {
            return R.error("短信发送失败！");
        }

        return R.ok();
    }

    @PostMapping("mobile")
    @Operation(summary = "手机号登录")
    public R<SysTokenVO> mobile(@RequestBody SysMobileLoginVO login) {
        SysTokenVO token = sysAuthService.loginByMobile(login);

        return R.ok(token);
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public R<String> logout(HttpServletRequest request) {
        sysAuthService.logout(TokenUtils.getAccessToken(request));

        return R.ok();
    }
}
