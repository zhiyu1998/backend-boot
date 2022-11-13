package cn.zhiyucs.core.system.service;


import cn.zhiyucs.core.system.entity.SysUserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserDetailsService {

    /**
     * 获取 UserDetails 对象
     */
    UserDetails getUserDetails(SysUserEntity userEntity);
}
