package cn.zhiyucs.system.controller;

import cn.hutool.core.text.CharSequenceUtil;
import cn.zhiyucs.system.convert.SysUserConvert;
import cn.zhiyucs.system.entity.SysUserEntity;
import cn.zhiyucs.system.query.SysUserQuery;
import cn.zhiyucs.system.service.SysUserPostService;
import cn.zhiyucs.system.service.SysUserRoleService;
import cn.zhiyucs.system.service.SysUserService;
import cn.zhiyucs.system.vo.SysUserPasswordVO;
import cn.zhiyucs.system.vo.SysUserVO;
import cn.zhiyucs.basic.user.SecurityUser;
import cn.zhiyucs.basic.user.UserDetail;
import cn.zhiyucs.utils.PageResult;
import cn.zhiyucs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 用户管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/user")
@Tag(name = "用户管理")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserPostService sysUserPostService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:user:page')")
    public R<PageResult<SysUserVO>> page(@Valid SysUserQuery query) {
        PageResult<SysUserVO> page = sysUserService.page(query);

        return R.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:user:info')")
    public R<SysUserVO> get(@PathVariable("id") Long id) {
        SysUserEntity entity = sysUserService.getById(id);

        SysUserVO vo = SysUserConvert.INSTANCE.convert(entity);

        // 用户角色列表
        List<Long> roleIdList = sysUserRoleService.getRoleIdList(id);
        vo.setRoleIdList(roleIdList);

        // 用户岗位列表
        List<Long> postIdList = sysUserPostService.getPostIdList(id);
        vo.setPostIdList(postIdList);

        return R.ok(vo);
    }

    @GetMapping("info")
    @Operation(summary = "登录用户")
    public R<SysUserVO> info() {
        SysUserVO user = SysUserConvert.INSTANCE.convert(SecurityUser.getUser());

        return R.ok(user);
    }

    @PutMapping("password")
    @Operation(summary = "修改密码")
    public R<String> password(@RequestBody @Valid SysUserPasswordVO vo) {
        // 原密码不正确
        UserDetail user = SecurityUser.getUser();
        if (!passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            return R.error("原密码不正确");
        }

        // 修改密码
        sysUserService.updatePassword(user.getId(), passwordEncoder.encode(vo.getNewPassword()));

        return R.ok();
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public R<String> save(@RequestBody @Valid SysUserVO vo) {
        // 新增密码不能为空
        if (CharSequenceUtil.isBlank(vo.getPassword())) {
            R.error("密码不能为空");
        }

        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        // 保存
        sysUserService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R<String> update(@RequestBody @Valid SysUserVO vo) {
        // 如果密码不为空，则进行加密处理
        if (CharSequenceUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        sysUserService.update(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        Long userId = SecurityUser.getUserId();
        if (idList.contains(userId)) {
            return R.error("不能删除当前登录用户");
        }

        sysUserService.delete(idList);

        return R.ok();
    }
}
