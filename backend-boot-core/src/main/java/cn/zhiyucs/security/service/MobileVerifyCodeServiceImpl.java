package cn.zhiyucs.security.service;

import cn.zhiyucs.api.message.SmsApi;
import cn.zhiyucs.common.mobile.MobileVerifyCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 短信验证码效验
 *
 * @author zhiyu1998
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    private final SmsApi smsApi;

    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsApi.verifyCode(mobile, code);
    }
}
