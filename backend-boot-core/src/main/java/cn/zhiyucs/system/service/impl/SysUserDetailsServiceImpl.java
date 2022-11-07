package cn.zhiyucs.system.service.impl;

import cn.zhiyucs.common.user.UserDetail;
import cn.zhiyucs.system.convert.SysUserConvert;
import cn.zhiyucs.system.dao.SysRoleDao;
import cn.zhiyucs.system.dao.SysRoleDataScopeDao;
import cn.zhiyucs.system.entity.SysUserEntity;
import cn.zhiyucs.system.enums.DataScopeEnum;
import cn.zhiyucs.system.enums.UserStatusEnum;
import cn.zhiyucs.system.service.SysMenuService;
import cn.zhiyucs.system.service.SysOrgService;
import cn.zhiyucs.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
            return null;
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
