package cn.zhiyucs.core.system.dao;

import cn.zhiyucs.common.basic.dao.BaseDao;
import cn.zhiyucs.core.system.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理
 *
 * @author zhiyu1998
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

    /**
     * 查询所有菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenuEntity> getMenuList(@Param("type") Integer type);

    /**
     * 查询用户菜单列表
     *
     * @param userId 用户ID
     * @param type   菜单类型
     */
    List<SysMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("type") Integer type);

    /**
     * 查询用户权限列表
     *
     * @param userId 用户ID
     */
    List<String> getUserAuthorityList(@Param("userId") Long userId);

    /**
     * 查询所有权限列表
     */
    List<String> getAuthorityList();

}
