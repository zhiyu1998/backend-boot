package cn.zhiyucs.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.PrimitiveArrayUtil;
import cn.zhiyucs.exception.ServerException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author zhiyu1998
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (CharSequenceUtil.isEmpty(text)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (PrimitiveArrayUtil.isEmpty(bytes)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(text, typeReference);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (CharSequenceUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return OBJECT_MAPPER.readValue(text, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

}
