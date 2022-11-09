package cn.zhiyucs.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.zhiyucs.exception.ServerException;

/**
 * 校验工具类
 *
 * @author zhiyu1998
 */
public class AssertUtils {

    private AssertUtils() {
    }

    public static void isBlank(String str, String variable) {
        if (CharSequenceUtil.isBlank(str)) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isNull(Object object, String variable) {
        if (object == null) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isArrayEmpty(Object[] array, String variable) {
        if (ArrayUtil.isEmpty(array)) {
            throw new ServerException(variable + "不能为空");
        }
    }

}