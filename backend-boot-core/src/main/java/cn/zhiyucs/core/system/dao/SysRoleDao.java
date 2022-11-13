package cn.zhiyucs.core.system.dao;

import cn.zhiyucs.common.basic.dao.BaseDao;
import cn.zhiyucs.core.system.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色管理
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 根据用户ID，获取用户最大的数据范围
     */
    Integer getDataScopeByUserId(@Param("userId") Long userId);

}
