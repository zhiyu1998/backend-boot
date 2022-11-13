package cn.zhiyucs.core.system.controller;

import cn.zhiyucs.core.system.query.SysLogLoginQuery;
import cn.zhiyucs.core.system.service.SysLogLoginService;
import cn.zhiyucs.core.system.vo.SysLogLoginVO;
import cn.zhiyucs.common.utils.PageResult;
import cn.zhiyucs.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录日志
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/log/login")
@Tag(name = "登录日志")
public class SysLogLoginController {

    @Resource
    private SysLogLoginService sysLogLoginService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:log:login')")
    public R<PageResult<SysLogLoginVO>> page(@Valid SysLogLoginQuery query) {
        PageResult<SysLogLoginVO> page = sysLogLoginService.page(query);

        return R.ok(page);
    }

}