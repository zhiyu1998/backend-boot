package cn.zhiyucs.cache;

/**
 * Redis Key管理
 *
 * @author zhiyu1998
 */
public class RedisKeys {

    private RedisKeys() {}

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "sys:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }

}
