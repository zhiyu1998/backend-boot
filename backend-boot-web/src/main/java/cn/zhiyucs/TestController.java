package cn.zhiyucs;

import cn.zhiyucs.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiyu
 * @date 2022/10/29 15:16
 */
@RestController
@RequestMapping("new/test")
@Tag(name = "新模块测试")
@AllArgsConstructor
public class TestController {

    @GetMapping()
    @Operation(summary = "测试接口")
    public R<String> test() {
        return R.ok("测试数据");
    }
}

