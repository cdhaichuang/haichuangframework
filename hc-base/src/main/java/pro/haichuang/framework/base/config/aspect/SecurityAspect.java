package pro.haichuang.framework.base.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import pro.haichuang.framework.base.annotation.security.PrePermission;
import pro.haichuang.framework.base.annotation.security.PreRole;
import pro.haichuang.framework.base.config.interceptor.AbstractSecurityValidate;
import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.exception.client.AuthorityException;

import java.lang.reflect.Method;

/**
 * 权限切面
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Aspect
@Order(2)
@ConditionalOnBean(AbstractSecurityValidate.class)
public class SecurityAspect {

    @Autowired
    private AbstractSecurityValidate securityValidate;

    @Pointcut("@annotation(pro.haichuang.framework.base.annotation.security.PreRole)")
    public void preRolePointCut() {
    }

    @Pointcut("@annotation(pro.haichuang.framework.base.annotation.security.PrePermission)")
    public void prePermissionPointCut() {
    }

    @Around("preRolePointCut()")
    public Object preRoleAround(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        PreRole preRoleAnnotation = method.getAnnotation(PreRole.class);

        if (!securityValidate.preRole(preRoleAnnotation.value())) {
            throw new AuthorityException(AuthorityErrorEnum.UNAUTHORIZED);
        }
        return point.proceed();
    }

    @Around("prePermissionPointCut()")
    public Object prePermissionAround(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        PrePermission prePermissionAnnotation = method.getAnnotation(PrePermission.class);

        if (!securityValidate.prePermission(prePermissionAnnotation.value())) {
            throw new AuthorityException(AuthorityErrorEnum.UNAUTHORIZED);
        }
        return point.proceed();
    }
}
