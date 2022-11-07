package cn.zhiyucs.system.controller;

import cn.zhiyucs.system.convert.SysPostConvert;
import cn.zhiyucs.system.entity.SysPostEntity;
import cn.zhiyucs.system.query.SysPostQuery;
import cn.zhiyucs.system.service.SysPostService;
import cn.zhiyucs.system.vo.SysPostVO;
import cn.zhiyucs.utils.PageResult;
import cn.zhiyucs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 岗位管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/post")
@Tag(name = "岗位管理")
public class SysPostController {

    @Resource
    private SysPostService sysPostService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:post:page')")
    public R<PageResult<SysPostVO>> page(@Valid SysPostQuery query) {
        PageResult<SysPostVO> page = sysPostService.page(query);

        return R.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    public R<List<SysPostVO>> list() {
        List<SysPostVO> list = sysPostService.getList();

        return R.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:post:info')")
    public R<SysPostVO> get(@PathVariable("id") Long id) {
        SysPostEntity entity = sysPostService.getById(id);

        return R.ok(SysPostConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:post:save')")
    public R<String> save(@RequestBody SysPostVO vo) {
        sysPostService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:post:update')")
    public R<String> update(@RequestBody @Valid SysPostVO vo) {
        sysPostService.update(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:post:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        sysPostService.delete(idList);

        return R.ok();
    }
}