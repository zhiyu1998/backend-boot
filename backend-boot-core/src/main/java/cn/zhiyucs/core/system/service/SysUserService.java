package cn.zhiyucs.core.system.service;

import cn.zhiyucs.common.basic.service.BaseService;
import cn.zhiyucs.core.system.entity.SysUserEntity;
import cn.zhiyucs.core.system.query.SysRoleUserQuery;
import cn.zhiyucs.core.system.query.SysUserQuery;
import cn.zhiyucs.core.system.vo.SysUserVO;
import cn.zhiyucs.common.utils.PageResult;

import java.util.List;

/**
 * 用户管理
 *
 * @author zhiyu1998
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    PageResult<SysUserVO> page(SysUserQuery query);

    void save(SysUserVO vo);

    void update(SysUserVO vo);

    void delete(List<Long> idList);

    SysUserVO getByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 分配角色，用户列表
     */
    PageResult<SysUserVO> roleUserPage(SysRoleUserQuery query);

}
