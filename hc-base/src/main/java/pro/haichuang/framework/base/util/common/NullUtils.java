package pro.haichuang.framework.base.util.common;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ApplicationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 空指针工具类
 *
 * <p>该类用于处理项目开发过程的各类空指针问题, 采用JDK8新特性 {@link Optional} 类进行封装, 更加方便的处理项目中空指针问题
 *
 * @author JiYinchuan
 * @see Optional
 * @since 1.1.0.211021
 */
public class NullUtils {

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> T ofNullable(@Nullable T value, @NonNull T other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> T ofNullable(@Nullable T value, @NonNull Supplier<? extends T> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> List<T> ofNullable(@Nullable List<T> value, @NonNull List<T> other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> List<T> ofNullable(@Nullable List<T> value, @NonNull Supplier<? extends List<T>> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> Set<T> ofNullable(@Nullable Set<T> value, @NonNull Set<T> other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <T>   原始值类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T> Set<T> ofNullable(@Nullable Set<T> value, @NonNull Supplier<? extends Set<T>> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <K>   原始Key类型
     * @param <V>   原始Value类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <K, V> Map<K, V> ofNullable(@Nullable Map<K, V> value, @NonNull Map<K, V> other) {
        return Optional.ofNullable(value).orElse(other);
    }

    /**
     * 空指针处理
     *
     * @param value 原始值
     * @param other 空指针时的值
     * @param <K>   原始Key类型
     * @param <V>   原始Value类型
     * @return 处理后结果
     * @since 1.1.0.211021
     */
    @NonNull
    public static <K, V> Map<K, V> ofNullable(@Nullable Map<K, V> value, @NonNull Supplier<? extends Map<K, V>> other) {
        return Optional.ofNullable(value).orElseGet(other);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               抛出异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T, X extends ApplicationException> T ofNullableThrow(@Nullable T value, @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               抛出异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T, X extends ApplicationException> List<T> ofNullableThrow(@Nullable List<T> value, @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <T>               原始值类型
     * @param <X>               抛出异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static <T, X extends ApplicationException> Set<T> ofNullableThrow(@Nullable Set<T> value, @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }

    /**
     * 空指针处理
     *
     * @param value             原始值
     * @param exceptionSupplier 抛出异常
     * @param <K>               原始Key类型
     * @param <V>               原始Value类型
     * @param <X>               抛出异常类型
     * @return 处理后结果
     * @throws X 抛出异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static <K, V, X extends ApplicationException> Map<K, V> ofNullableThrow(@Nullable Map<K, V> value, @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(value).orElseThrow(exceptionSupplier);
    }
}
