package cn.zhiyucs.security.mobile;

/**
 * 手机短信登录，验证码效验
 *
 * @author zhiyu1998
 */
public interface MobileVerifyCodeService {

    boolean verifyCode(String mobile, String code);
}
