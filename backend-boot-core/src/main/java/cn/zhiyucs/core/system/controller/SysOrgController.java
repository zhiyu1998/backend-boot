package cn.zhiyucs.core.system.controller;

import cn.zhiyucs.common.constant.Constant;
import cn.zhiyucs.core.system.convert.SysOrgConvert;
import cn.zhiyucs.core.system.entity.SysOrgEntity;
import cn.zhiyucs.core.system.service.SysOrgService;
import cn.zhiyucs.core.system.vo.SysOrgVO;
import cn.zhiyucs.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 机构管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/org")
@Tag(name = "机构管理")
public class SysOrgController {

    @Resource
    private SysOrgService sysOrgService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('sys:org:list')")
    public R<List<SysOrgVO>> list() {
        List<SysOrgVO> list = sysOrgService.getList();

        return R.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:org:info')")
    public R<SysOrgVO> get(@PathVariable("id") Long id) {
        SysOrgEntity entity = sysOrgService.getById(id);
        SysOrgVO vo = SysOrgConvert.INSTANCE.convert(entity);

        // 获取上级机构名称
        if (!Constant.ROOT.equals(entity.getPid())) {
            SysOrgEntity parentEntity = sysOrgService.getById(entity.getPid());
            vo.setParentName(parentEntity.getName());
        }

        return R.ok(vo);
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:org:save')")
    public R<String> save(@RequestBody @Valid SysOrgVO vo) {
        sysOrgService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:org:update')")
    public R<String> update(@RequestBody @Valid SysOrgVO vo) {
        sysOrgService.update(vo);

        return R.ok();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:org:delete')")
    public R<String> delete(@PathVariable("id") Long id) {
        sysOrgService.delete(id);

        return R.ok();
    }

}