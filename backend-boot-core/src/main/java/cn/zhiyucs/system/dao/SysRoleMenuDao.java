package cn.zhiyucs.system.dao;

import cn.zhiyucs.common.dao.BaseDao;
import cn.zhiyucs.system.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> getMenuIdList(@Param("roleId") Long roleId);
}
