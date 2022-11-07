package cn.zhiyucs.exception;

import cn.zhiyucs.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 *
 * @author zhiyu1998
 */
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public R<String> handleException(ServerException ex) {

        return R.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public R<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return R.error(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R<String> handleAccessDeniedException(Exception ex) {

        return R.error(ErrorCode.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return R.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}