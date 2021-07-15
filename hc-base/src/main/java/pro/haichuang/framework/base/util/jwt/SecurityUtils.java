package pro.haichuang.framework.base.util.jwt;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.exception.client.AuthorityException;

/**
 * Security工具类
 *
 * <p>该类为辅助JWT工具类, 用于在全局便捷获取JWT载荷功能</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see ThreadLocal
 * @see JwtPayload
 */
public class SecurityUtils {
    private static final ThreadLocal<JwtPayload> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置Context
     *
     * @param jwtPayload JWT载荷
     */
    public static void setJwtPayload(@NonNull JwtPayload jwtPayload) {
        Assert.notNull(jwtPayload, "Only non-null JwtPayload instances are permitted");
        CONTEXT_HOLDER.set(jwtPayload);
    }

    /**
     * 获取JWT载荷
     *
     * @return JWT载荷
     */
    @Nullable
    public static JwtPayload getJwtPayload() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 获取JWT载荷
     *
     * @return JWT载荷
     */
    @NonNull
    public static JwtPayload getJwtPayloadAndValidate() {
        JwtPayload jwtPayload = CONTEXT_HOLDER.get();
        if (jwtPayload == null) {
            throw new AuthorityException(AuthorityErrorEnum.ACCESS_BLOCKED, "获取用户信息失败");
        }
        return jwtPayload;
    }

    /**
     * 移除当前线程存储的JWT载荷
     *
     * <p>因为 {@link ThreadLocal} 底层使用的内部类 {@code ThreadLocalMap} 实现的, 生命周期为当前线程,
     * 所以不执行此方法当线程终止后 {@code ThreadLocalMap} 中的值会被JVM垃圾回收,
     * 但推荐在不需要使用的时候显性的执行此方法, 便于理解</p>
     */
    public static void remove() {
        CONTEXT_HOLDER.remove();
    }
}
