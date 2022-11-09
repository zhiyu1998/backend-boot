package cn.zhiyucs.security.service;

import cn.zhiyucs.api.message.SmsApi;
import cn.zhiyucs.security.mobile.MobileVerifyCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信验证码效验
 *
 * @author zhiyu1998
 */
@Service
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    @Resource
    private SmsApi smsApi;

    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsApi.verifyCode(mobile, code);
    }
}
