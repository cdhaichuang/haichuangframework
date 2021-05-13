package pro.haichuang.framework.base.enums;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.fasterxml.jackson.annotation.JsonValue;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;

/**
 * 全局枚举基类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public interface BaseEnum {

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    @JsonValue
    String value();

    /**
     * 获取枚举信息
     *
     * @return 枚举信息
     */
    String getReasonPhrase();

    /**
     * 解析枚举参数值
     *
     * @param value     参数值
     * @param enumClass 需要解析的枚举类
     * @param <E>       集成 {@link BaseEnum}
     * @return 解析后的枚举对象
     * @throws EnumIllegalArgumentException 解析异常
     */
    static <E extends BaseEnum> E resolve(String value, Class<E> enumClass) throws EnumIllegalArgumentException {
        E enumValue = null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E enumConstant : enumConstants) {
            if (enumConstant.value().equals(value)) {
                enumValue = enumConstant;
                break;
            }
        }
        if (enumValue == null) {
            throw new EnumIllegalArgumentException("No matching [" + enumClass.getPackage().getName() + "] for [" + value + "]");
        }
        return enumValue;
    }
}
