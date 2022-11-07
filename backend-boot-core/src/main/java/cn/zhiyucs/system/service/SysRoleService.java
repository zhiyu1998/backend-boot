package cn.zhiyucs.system.service;

import cn.zhiyucs.common.service.BaseService;
import cn.zhiyucs.system.entity.SysRoleEntity;
import cn.zhiyucs.system.query.SysRoleQuery;
import cn.zhiyucs.system.vo.SysRoleDataScopeVO;
import cn.zhiyucs.system.vo.SysRoleVO;
import cn.zhiyucs.utils.PageResult;

import java.util.List;

/**
 * 角色
 *
 * @author zhiyu1998
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    PageResult<SysRoleVO> page(SysRoleQuery query);

    List<SysRoleVO> getList(SysRoleQuery query);

    void save(SysRoleVO vo);

    void update(SysRoleVO vo);

    void dataScope(SysRoleDataScopeVO vo);

    void delete(List<Long> idList);
}
