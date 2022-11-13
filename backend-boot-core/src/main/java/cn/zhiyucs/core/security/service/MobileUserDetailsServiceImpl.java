package cn.zhiyucs.core.security.service;

import cn.zhiyucs.core.system.dao.SysUserDao;
import cn.zhiyucs.core.system.entity.SysUserEntity;
import cn.zhiyucs.core.system.service.SysUserDetailsService;
import cn.zhiyucs.core.security.mobile.MobileUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 手机验证码登录 MobileUserDetailsService
 *
 * @author zhiyu1998
 */
@Service
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    @Resource
    private SysUserDetailsService sysUserDetailsService;

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByMobile(mobile);
        if (userEntity == null) {
            throw new UsernameNotFoundException("手机号或验证码错误");
        }

        return sysUserDetailsService.getUserDetails(userEntity);
    }

}
