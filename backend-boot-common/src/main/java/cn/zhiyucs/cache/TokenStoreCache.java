package cn.zhiyucs.cache;

import cn.zhiyucs.user.UserDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 认证 Cache
 *
 * @author zhiyu1998
 */
@Component
public class TokenStoreCache {

    @Resource
    private RedisCache redisCache;

    public void saveUser(String accessToken, UserDetail user) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.set(key, user);
    }

    public UserDetail getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return (UserDetail) redisCache.get(key);
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }
}
