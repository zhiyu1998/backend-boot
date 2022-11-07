package cn.zhiyucs.system.controller;

import cn.zhiyucs.common.user.SecurityUser;
import cn.zhiyucs.common.user.UserDetail;
import cn.zhiyucs.constant.Constant;
import cn.zhiyucs.system.convert.SysMenuConvert;
import cn.zhiyucs.system.entity.SysMenuEntity;
import cn.zhiyucs.system.enums.MenuTypeEnum;
import cn.zhiyucs.system.service.SysMenuService;
import cn.zhiyucs.system.vo.SysMenuVO;
import cn.zhiyucs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/menu")
@Tag(name = "菜单管理")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("nav")
    @Operation(summary = "菜单导航")
    public R<List<SysMenuVO>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuVO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.getValue());

        return R.ok(list);
    }

    @GetMapping("authority")
    @Operation(summary = "用户权限标识")
    public R<Set<String>> authority() {
        UserDetail user = SecurityUser.getUser();
        Set<String> set = sysMenuService.getUserAuthority(user);

        return R.ok(set);
    }

    @GetMapping("list")
    @Operation(summary = "菜单列表")
    @Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  2：接口  null：全部")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public R<List<SysMenuVO>> list(Integer type) {
        List<SysMenuVO> list = sysMenuService.getMenuList(type);

        return R.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:menu:info')")
    public R<SysMenuVO> get(@PathVariable("id") Long id) {
        SysMenuEntity entity = sysMenuService.getById(id);
        SysMenuVO vo = SysMenuConvert.INSTANCE.convert(entity);

        // 获取上级菜单名称
        if (!Constant.ROOT.equals(entity.getPid())) {
            SysMenuEntity parentEntity = sysMenuService.getById(entity.getPid());
            vo.setParentName(parentEntity.getName());
        }

        return R.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public R<String> save(@RequestBody @Valid SysMenuVO vo) {
        sysMenuService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public R<String> update(@RequestBody @Valid SysMenuVO vo) {
        sysMenuService.update(vo);

        return R.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public R<String> delete(@PathVariable("id") Long id) {
        // 判断是否有子菜单或按钮
        Long count = sysMenuService.getSubMenuCount(id);
        if (count > 0) {
            return R.error("请先删除子菜单");
        }

        sysMenuService.delete(id);

        return R.ok();
    }
}