package pro.haichuang.framework.base.util.jwt;

import org.springframework.util.Assert;
import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.exception.client.AuthorityException;

/**
 * Security工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class SecurityUtils {
    private static final ThreadLocal<JwtPayload> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    public static JwtPayload getContextValidate() {
        JwtPayload jwtPayload = CONTEXT_HOLDER.get();
        if (jwtPayload == null) {
            throw new AuthorityException(AuthorityErrorEnum.ACCESS_BLOCKED, "获取用户信息失败");
        }
        return jwtPayload;
    }

    public static JwtPayload getContext() {
        JwtPayload jwtPayload = CONTEXT_HOLDER.get();
        return jwtPayload != null ? jwtPayload : createEmptyContext();
    }

    public static void setContext(JwtPayload context) {
        Assert.notNull(context, "Only non-null JwtPayload instances are permitted");
        CONTEXT_HOLDER.set(context);
    }

    public static JwtPayload createEmptyContext() {
        return new JwtPayload();
    }
}
