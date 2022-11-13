package cn.zhiyucs.core.system.controller;

import cn.zhiyucs.common.basic.user.SecurityUser;
import cn.zhiyucs.common.basic.user.UserDetail;
import cn.zhiyucs.core.system.convert.SysRoleConvert;
import cn.zhiyucs.core.system.entity.SysRoleEntity;
import cn.zhiyucs.core.system.query.SysRoleQuery;
import cn.zhiyucs.core.system.query.SysRoleUserQuery;
import cn.zhiyucs.core.system.service.*;
import cn.zhiyucs.core.system.vo.SysMenuVO;
import cn.zhiyucs.core.system.vo.SysRoleDataScopeVO;
import cn.zhiyucs.core.system.vo.SysRoleVO;
import cn.zhiyucs.core.system.vo.SysUserVO;
import cn.zhiyucs.common.utils.PageResult;
import cn.zhiyucs.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/role")
@Tag(name = "角色管理")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysRoleDataScopeService sysRoleDataScopeService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:role:page')")
    public R<PageResult<SysRoleVO>> page(@Valid SysRoleQuery query) {
        PageResult<SysRoleVO> page = sysRoleService.page(query);

        return R.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public R<List<SysRoleVO>> list() {
        List<SysRoleVO> list = sysRoleService.getList(new SysRoleQuery());

        return R.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:role:info')")
    public R<SysRoleVO> get(@PathVariable("id") Long id) {
        SysRoleEntity entity = sysRoleService.getById(id);

        // 转换对象
        SysRoleVO role = SysRoleConvert.INSTANCE.convert(entity);

        // 查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
        role.setMenuIdList(menuIdList);

        // 查询角色对应的数据权限
        List<Long> orgIdList = sysRoleDataScopeService.getOrgIdList(id);
        role.setOrgIdList(orgIdList);

        return R.ok(role);
    }

    @PostMapping
    @Operation(summary = "保存", hidden = true)
    @PreAuthorize("hasAuthority('sys:role:save')")
    public R<String> save(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<String> update(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.update(vo);

        return R.ok();
    }

    @PutMapping("data-scope")
    @Operation(summary = "数据权限")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<String> dataScope(@RequestBody @Valid SysRoleDataScopeVO vo) {
        sysRoleService.dataScope(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        sysRoleService.delete(idList);

        return R.ok();
    }

    @GetMapping("menu")
    @Operation(summary = "角色菜单")
    @PreAuthorize("hasAuthority('sys:role:menu')")
    public R<List<SysMenuVO>> menu() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuVO> list = sysMenuService.getUserMenuList(user, null);

        return R.ok(list);
    }

    @GetMapping("user/page")
    @Operation(summary = "角色用户-分页")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<PageResult<SysUserVO>> userPage(@Valid SysRoleUserQuery query) {
        PageResult<SysUserVO> page = sysUserService.roleUserPage(query);

        return R.ok(page);
    }

    @DeleteMapping("user/{roleId}")
    @Operation(summary = "删除角色用户")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<String> userDelete(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
        sysUserRoleService.deleteByUserIdList(roleId, userIdList);

        return R.ok();
    }

    @PostMapping("user/{roleId}")
    @Operation(summary = "分配角色给用户列表")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R<String> userSave(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
        sysUserRoleService.saveUserList(roleId, userIdList);

        return R.ok();
    }
}