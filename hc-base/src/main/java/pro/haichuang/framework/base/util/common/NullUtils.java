package pro.haichuang.framework.base.util.common;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 空指针工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class NullUtils {

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> T ofNullable(T value, T other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> T ofNullable(T value, Supplier<? extends T> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> List<T> ofNullable(List<T> value, List<T> other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> List<T> ofNullable(List<T> value, Supplier<? extends List<T>> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> Set<T> ofNullable(Set<T> value, Set<T> other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     */
    public static <T> Set<T> ofNullable(Set<T> value, Supplier<? extends Set<T>> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               排除异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     */
    public static <T, X extends Throwable> T ofNullableThrow(T value, Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               排除异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     */
    public static <T, X extends Throwable> List<T> ofNullableThrow(List<T> value, Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               排除异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     */
    public static <T, X extends Throwable> Set<T> ofNullableThrow(Set<T> value, Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }
}
