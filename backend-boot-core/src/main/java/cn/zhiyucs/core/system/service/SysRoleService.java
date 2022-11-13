package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysRoleEntity;
import cn.zhiyucs.core.system.query.SysRoleQuery;
import cn.zhiyucs.core.system.vo.SysRoleDataScopeVO;
import cn.zhiyucs.core.system.vo.SysRoleVO;
import cn.zhiyucs.common.utils.PageResult;

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
