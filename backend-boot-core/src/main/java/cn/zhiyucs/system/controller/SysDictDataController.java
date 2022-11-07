package cn.zhiyucs.system.controller;

import cn.zhiyucs.system.convert.SysDictDataConvert;
import cn.zhiyucs.system.entity.SysDictDataEntity;
import cn.zhiyucs.system.query.SysDictDataQuery;
import cn.zhiyucs.system.service.SysDictDataService;
import cn.zhiyucs.system.vo.SysDictDataVO;
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
 * 字典数据
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "字典数据")
public class SysDictDataController {

    @Resource
    private SysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public R<PageResult<SysDictDataVO>> page(@Valid SysDictDataQuery query) {
        PageResult<SysDictDataVO> page = sysDictDataService.page(query);

        return R.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:dict:info')")
    public R<SysDictDataVO> get(@PathVariable("id") Long id) {
        SysDictDataEntity entity = sysDictDataService.getById(id);

        return R.ok(SysDictDataConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:dict:save')")
    public R<String> save(@RequestBody @Valid SysDictDataVO vo) {
        sysDictDataService.save(vo);

        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public R<String> update(@RequestBody @Valid SysDictDataVO vo) {
        sysDictDataService.update(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        sysDictDataService.delete(idList);

        return R.ok();
    }

}