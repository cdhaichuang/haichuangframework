package pro.haichuang.framework.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;

/**
 * 全局枚举父接口
 *
 * <p>该类为全局枚举父接口, 为避免部分功能失效, 项目中所有的自定义枚举原则上都必须实现该接口
 * 目前该类主要关联说明:
 * <ul>
 *     <li>请求参数枚举转换: {@link pro.haichuang.framework.base.config.mvc.enums.ParamEnumConverterFactory}</li>
 *     <li>FastJson枚举序列化: {@link pro.haichuang.framework.base.config.mvc.FastJsonConfig}</li>
 *     <li>自定义异常封装: {@link pro.haichuang.framework.base.exception.ApplicationException}</li>
 *     <li>全局统一响应: {@link pro.haichuang.framework.base.response.ResultVO}</li>
 * </ul>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
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
     * @param <E>       继承 {@link BaseEnum} 的枚举类型
     * @return 解析后的枚举对象
     * @throws EnumIllegalArgumentException 解析异常
     */
    static <T,E extends Enum<E> & BaseEnum> E resolve(String value, Class<E> enumClass) throws EnumIllegalArgumentException {
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
