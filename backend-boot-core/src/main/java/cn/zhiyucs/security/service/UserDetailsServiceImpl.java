package cn.zhiyucs.security.service;

import cn.zhiyucs.system.dao.SysUserDao;
import cn.zhiyucs.system.entity.SysUserEntity;
import cn.zhiyucs.system.service.SysUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 账号登录 UserDetailsService
 *
 * @author zhiyu1998
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return sysUserDetailsService.getUserDetails(userEntity);
    }

}
