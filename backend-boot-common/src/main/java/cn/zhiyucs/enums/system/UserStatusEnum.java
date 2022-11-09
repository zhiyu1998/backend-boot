package cn.zhiyucs.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author zhiyu1998
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    /**
     * 停用
     */
    DISABLE(0),
    /**
     * 正常
     */
    ENABLED(1);

    private final int value;
}
