package pro.haichuang.framework.base.config.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pro.haichuang.framework.base.util.common.IpUtils;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.base.util.jwt.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * <p>该类具体实现了记录请求日志的功能, 切点为标注了 {@link ApiOperation @ApiOperation} 注解的方法, 记录相关请求与响应数据</p>
 * <p>注意: 该类启用的前提条件为标注了
 * {@link pro.haichuang.framework.base.annotation.EnableControllerLogAspect @EnableControllerLogAspect} 注解</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Aspect
@Order(1)
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        UUIDUtils.Local.init();
        // UUID
        String uuid = UUIDUtils.Local.get();
        // 开始执行时间
        long beginTime = System.currentTimeMillis();
        // 请求
        HttpServletRequest request = null;
        // 客户端IP真实地址
        String clientIp = null;
        // 完整方法名
        String fullMethodName = null;
        // 描述信息
        String message = null;
        // 用户ID
        Long userId = null;

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();

            clientIp = IpUtils.getIpv4Address(request);

            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            Api apiAnnotation = method.getDeclaringClass().getAnnotation(Api.class);
            ApiOperation apiOperationAnnotation = method.getAnnotation(ApiOperation.class);

            fullMethodName = point.getTarget().getClass().getName() + "." + method.getName() + "()";

            message = String.join(",", apiAnnotation.tags()) + "-" + apiOperationAnnotation.value();

            userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();

            LOGGER.info("[AOP] [Begin] 检测到请求 [uuid: {}, apiMessage: {}, requestUri: {}, method: {}," +
                            "clientIp: {}, userId: {}, params: {}]",
                    uuid, message, request.getRequestURI(), fullMethodName,
                    clientIp, userId, point.getArgs());
        }

        Object result = point.proceed();
        long executionTime = System.currentTimeMillis() - beginTime;

        LOGGER.debug("[AOP] [Details-End] 检测到请求 [uuid: {}, apiMessage: {}, requestUri: {}, method: {}," +
                        "clientIp: {}, userId: {}, params: {}, executionTime: {}, result: {}]",
                uuid, message, request != null ? request.getRequestURI() : "null", fullMethodName,
                clientIp, userId, point.getArgs(), executionTime, new ObjectMapper().writeValueAsString(result));
        LOGGER.info("[AOP] [ End ] 检测到请求 [uuid: {}, apiMessage: {}, requestUri: {}, method: {}," +
                        "clientIp: {}, userId: {}, executionTime: {}]",
                uuid, message, request != null ? request.getRequestURI() : "null", fullMethodName,
                clientIp, userId, executionTime);

        UUIDUtils.Local.remove();
        return result;
    }
}
