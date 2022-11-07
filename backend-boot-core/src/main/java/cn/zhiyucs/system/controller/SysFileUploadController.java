package cn.zhiyucs.system.controller;

import cn.zhiyucs.storage.service.StorageService;
import cn.zhiyucs.system.vo.SysFileUploadVO;
import cn.zhiyucs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传
 *
 * @author zhiyu1998
 */
@RestController
@RequestMapping("sys/file")
@Tag(name = "文件上传")
public class SysFileUploadController {

    @Resource
    private StorageService storageService;

    @PostMapping("upload")
    @Operation(summary = "上传")
    public R<SysFileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error("请选择需要上传的文件");
        }

        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传文件
        String url = storageService.upload(file.getBytes(), path);

        SysFileUploadVO vo = new SysFileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());
        vo.setPlatform(storageService.properties.getConfig().getType().name());

        return R.ok(vo);
    }
}
