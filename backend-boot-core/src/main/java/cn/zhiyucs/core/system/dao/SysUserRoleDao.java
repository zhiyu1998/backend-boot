package cn.zhiyucs.core.system.dao;

import cn.zhiyucs.common.basic.dao.BaseDao;
import cn.zhiyucs.core.system.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

    /**
     * 角色ID列表
     *
     * @param userId 用户ID
     * @return 返回角色ID列表
     */
    List<Long> getRoleIdList(@Param("userId") Long userId);
}