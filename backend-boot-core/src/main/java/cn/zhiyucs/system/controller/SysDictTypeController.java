package cn.zhiyucs.system.controller;

import cn.zhiyucs.system.convert.SysDictTypeConvert;
import cn.zhiyucs.system.entity.SysDictTypeEntity;
import cn.zhiyucs.system.query.SysDictTypeQuery;
import cn.zhiyucs.system.service.SysDictTypeService;
import cn.zhiyucs.system.vo.SysDictTypeVO;
import cn.zhiyucs.system.vo.SysDictVO;
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
 * 字典类型
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/dict/type")
@Tag(name = "字典类型")
public class SysDictTypeController {

    @Resource
    private SysDictTypeService sysDictTypeService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public R<PageResult<SysDictTypeVO>> page(@Valid SysDictTypeQuery query) {
        PageResult<SysDictTypeVO> page = sysDictTypeService.page(query);

        return R.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:dict:info')")
    public R<SysDictTypeVO> get(@PathVariable("id") Long id) {
        SysDictTypeEntity entity = sysDictTypeService.getById(id);

        return R.ok(SysDictTypeConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:dict:save')")
    public R<String> save(@RequestBody @Valid SysDictTypeVO vo) {
        sysDictTypeService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public R<String> update(@RequestBody @Valid SysDictTypeVO vo) {
        sysDictTypeService.update(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        sysDictTypeService.delete(idList);

        return R.ok();
    }

    @GetMapping("all")
    @Operation(summary = "全部字典数据")
    public R<List<SysDictVO>> all() {
        List<SysDictVO> dictList = sysDictTypeService.getDictList();

        return R.ok(dictList);
    }

}