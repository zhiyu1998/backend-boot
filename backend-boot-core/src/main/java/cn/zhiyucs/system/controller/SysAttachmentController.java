package cn.zhiyucs.system.controller;

import cn.zhiyucs.system.query.SysAttachmentQuery;
import cn.zhiyucs.system.service.SysAttachmentService;
import cn.zhiyucs.system.vo.SysAttachmentVO;
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
 * 附件管理
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/attachment")
@Tag(name = "附件管理")
public class SysAttachmentController {

    @Resource
    private SysAttachmentService sysAttachmentService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:attachment:page')")
    public R<PageResult<SysAttachmentVO>> page(@Valid SysAttachmentQuery query) {
        PageResult<SysAttachmentVO> page = sysAttachmentService.page(query);

        return R.ok(page);
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:attachment:save')")
    public R<String> save(@RequestBody SysAttachmentVO vo) {
        sysAttachmentService.save(vo);

        return R.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:attachment:delete')")
    public R<String> delete(@RequestBody List<Long> idList) {
        sysAttachmentService.delete(idList);

        return R.ok();
    }
}