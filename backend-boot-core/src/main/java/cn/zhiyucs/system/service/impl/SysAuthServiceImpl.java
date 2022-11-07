package cn.zhiyucs.system.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.zhiyucs.api.message.SmsApi;
import cn.zhiyucs.cache.TokenStoreCache;
import cn.zhiyucs.common.mobile.MobileAuthenticationToken;
import cn.zhiyucs.common.user.UserDetail;
import cn.zhiyucs.constant.Constant;
import cn.zhiyucs.exception.ServerException;
import cn.zhiyucs.system.enums.LoginOperationEnum;
import cn.zhiyucs.system.service.SysAuthService;
import cn.zhiyucs.system.service.SysCaptchaService;
import cn.zhiyucs.system.service.SysLogLoginService;
import cn.zhiyucs.system.service.SysUserService;
import cn.zhiyucs.system.vo.SysAccountLoginVO;
import cn.zhiyucs.system.vo.SysMobileLoginVO;
import cn.zhiyucs.system.vo.SysTokenVO;
import cn.zhiyucs.system.vo.SysUserVO;
import cn.zhiyucs.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 权限认证服务
 *
 * @author zhiyu1998
 */
@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private SysCaptchaService sysCaptchaService;

    @Resource
    private TokenStoreCache tokenStoreCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SysLogLoginService sysLogLoginService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SmsApi smsApi;

    @Override
    public SysTokenVO loginByAccount(SysAccountLoginVO login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 保存登录日志
            sysLogLoginService.save(login.getUsername(), Constant.FAIL, LoginOperationEnum.CAPTCHA_FAIL.getValue());

            throw new ServerException("验证码错误");
        }

        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }

    @Override
    public SysTokenVO loginByMobile(SysMobileLoginVO login) {
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new MobileAuthenticationToken(login.getMobile(), login.getCode()));
        } catch (BadCredentialsException e) {
            throw new ServerException("手机号或验证码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }

    @Override
    public boolean sendCode(String mobile) {
        // 生成6位验证码
        String code = RandomUtil.randomNumbers(6);

        SysUserVO user = sysUserService.getByMobile(mobile);
        if (user == null) {
            throw new ServerException("手机号未注册");
        }

        // 发送短信
        return smsApi.sendCode(mobile, "code", code);
    }

    @Override
    public void logout(String accessToken) {
        // 用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

        // 保存登录日志
        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGOUT_SUCCESS.getValue());
    }
}
