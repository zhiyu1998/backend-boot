package cn.zhiyucs.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.zhiyucs.service.impl.BaseServiceImpl;
import cn.zhiyucs.system.dao.SysUserRoleDao;
import cn.zhiyucs.system.entity.SysUserRoleEntity;
import cn.zhiyucs.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关系
 *
 * @author zhiyu1998
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        // 数据库角色ID列表
        List<Long> dbRoleIdList = getRoleIdList(userId);

        // 需要新增的角色ID
        Collection<Long> insertRoleIdList = CollUtil.subtract(roleIdList, dbRoleIdList);
        if (CollUtil.isNotEmpty(insertRoleIdList)) {
            List<SysUserRoleEntity> roleList = insertRoleIdList.stream().map(roleId -> {
                SysUserRoleEntity entity = new SysUserRoleEntity();
                entity.setUserId(userId);
                entity.setRoleId(roleId);
                return entity;
            }).collect(Collectors.toList());

            // 批量新增
            saveBatch(roleList);
        }

        // 需要删除的角色ID
        Collection<Long> deleteRoleIdList = CollUtil.subtract(dbRoleIdList, roleIdList);
        if (CollUtil.isNotEmpty(deleteRoleIdList)) {
            LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
            remove(queryWrapper.eq(SysUserRoleEntity::getUserId, userId).in(SysUserRoleEntity::getRoleId, deleteRoleIdList));
        }
    }

    @Override
    public void saveUserList(Long roleId, List<Long> userIdList) {
        List<SysUserRoleEntity> list = userIdList.stream().map(userId -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            return entity;
        }).collect(Collectors.toList());

        // 批量新增
        saveBatch(list);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        remove(new LambdaQueryWrapper<SysUserRoleEntity>().in(SysUserRoleEntity::getRoleId, roleIdList));
    }

    @Override
    public void deleteByUserIdList(List<Long> userIdList) {
        remove(new LambdaQueryWrapper<SysUserRoleEntity>().in(SysUserRoleEntity::getUserId, userIdList));
    }

    @Override
    public void deleteByUserIdList(Long roleId, List<Long> userIdList) {
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        remove(queryWrapper.eq(SysUserRoleEntity::getRoleId, roleId).in(SysUserRoleEntity::getUserId, userIdList));
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {
        return baseMapper.getRoleIdList(userId);
    }
}