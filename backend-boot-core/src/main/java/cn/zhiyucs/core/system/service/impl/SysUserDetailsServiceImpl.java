package cn.zhiyucs.core.system.service.impl;

import cn.zhiyucs.common.basic.user.UserDetail;
import cn.zhiyucs.core.system.convert.SysUserConvert;
import cn.zhiyucs.core.system.dao.SysRoleDao;
import cn.zhiyucs.core.system.dao.SysRoleDataScopeDao;
import cn.zhiyucs.core.system.entity.SysUserEntity;
import cn.zhiyucs.core.system.service.SysMenuService;
import cn.zhiyucs.core.system.service.SysOrgService;
import cn.zhiyucs.core.system.service.SysUserDetailsService;
import cn.zhiyucs.common.enums.system.DataScopeEnum;
import cn.zhiyucs.common.enums.system.UserStatusEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 用户 UserDetails 信息
 *
 * @author zhiyu1998
 */
@Service
public class SysUserDetailsServiceImpl implements SysUserDetailsService {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysOrgService sysOrgService;

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public UserDetails getUserDetails(SysUserEntity userEntity) {
        // 转换成UserDetail对象
        UserDetail userDetail = SysUserConvert.INSTANCE.convertDetail(userEntity);

        // 账号不可用
        if (userEntity.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }

        // 数据权限范围
        List<Long> dataScopeList = getDataScope(userDetail);
        userDetail.setDataScopeList(dataScopeList);

        // 用户权限列表
        Set<String> authoritySet = sysMenuService.getUserAuthority(userDetail);
        userDetail.setAuthoritySet(authoritySet);

        return userDetail;
    }

    private List<Long> getDataScope(UserDetail userDetail) {
        Integer dataScope = sysRoleDao.getDataScopeByUserId(userDetail.getId());
        if (dataScope == null) {
            return new ArrayList<>();
        }

        if (dataScope.equals(DataScopeEnum.ALL.getValue())) {
            // 全部数据权限，则返回null
            return Collections.emptyList();
        } else if (dataScope.equals(DataScopeEnum.ORG_AND_CHILD.getValue())) {
            // 本机构及子机构数据
            List<Long> dataScopeList = sysOrgService.getSubOrgIdList(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        } else if (dataScope.equals(DataScopeEnum.ORG_ONLY.getValue())) {
            // 本机构数据
            List<Long> dataScopeList = new ArrayList<>();
            dataScopeList.add(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        } else if (dataScope.equals(DataScopeEnum.CUSTOM.getValue())) {
            // 自定义数据权限范围
            return sysRoleDataScopeDao.getDataScopeList(userDetail.getId());
        }

        return new ArrayList<>();
    }
}
