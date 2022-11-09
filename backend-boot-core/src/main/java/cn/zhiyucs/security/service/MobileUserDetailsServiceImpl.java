package cn.zhiyucs.security.service;

import cn.zhiyucs.security.mobile.MobileUserDetailsService;
import cn.zhiyucs.system.dao.SysUserDao;
import cn.zhiyucs.system.entity.SysUserEntity;
import cn.zhiyucs.system.service.SysUserDetailsService;
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
