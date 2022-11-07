package cn.zhiyucs.utils;

import cn.zhiyucs.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 响应数据
 *
 * @author zhiyu1998
 */
@Data
@Schema(description = "响应")
public class R<T> {
    @Schema(description = "编码 0表示成功，其他值表示失败")
    private int code = 0;

    @Schema(description = "消息内容")
    private String msg = "success";

    @Schema(description = "响应数据")
    private T data;

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        R<T> R = new R<>();
        R.setData(data);
        return R;
    }

    public static <T> R<T> error() {
        return error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> R<T> error(String msg) {
        return error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> R<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> R = new R<>();
        R.setCode(code);
        R.setMsg(msg);
        return R;
    }
}
