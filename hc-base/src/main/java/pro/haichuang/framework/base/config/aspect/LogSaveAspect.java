package pro.haichuang.framework.base.config.aspect;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pro.haichuang.framework.base.annotation.LogSave;
import pro.haichuang.framework.base.config.interceptor.AbstractLogSave;
import pro.haichuang.framework.base.util.common.IpUtils;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.base.util.jwt.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志切面
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Aspect
@Order(3)
@ConditionalOnBean(AbstractLogSave.class)
public class LogSaveAspect {

    private final ExecutorService executorService = new ThreadPoolExecutor(5, 100, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024),
            new ThreadFactoryBuilder().setNamePrefix("SLogSaveAspect-Thread-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private AbstractLogSave abstractLogSave;

    @Pointcut("@annotation(pro.haichuang.framework.base.annotation.LogSave)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // UUID
        String uuid = UUIDUtils.random();
        // 开始执行时间
        long beginTime = System.currentTimeMillis();
        // 请求
        HttpServletRequest request;
        // 注解信息
        LogSave logSave = null;
        // 类型信息
        Api api = null;
        // 接口信息
        ApiOperation apiOperation = null;
        // 客户端IP真实地址
        String clientIp = null;
        // 完整方法名
        String fullMethodName = null;
        // 用户ID
        Long userId = null;

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();

            clientIp = IpUtils.getIpAddress(request);

            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            logSave = method.getAnnotation(LogSave.class);
            api = method.getDeclaringClass().getAnnotation(Api.class);
            apiOperation = method.getAnnotation(ApiOperation.class);

            fullMethodName = point.getTarget().getClass().getName() + "." + method.getName() + "()";

            userId = SecurityUtils.getContext().getUserId();
        }

        Object result = point.proceed();
        long executionTime = System.currentTimeMillis() - beginTime;

        LogSave finalLogSave = logSave;
        Api finalApi = api;
        ApiOperation finalApiOperation = apiOperation;
        String finalClientIp = clientIp;
        String finalFullMethodName = fullMethodName;
        Long finalUserId = userId;
        executorService.execute(() -> abstractLogSave.saveLog(finalLogSave, finalApi, finalApiOperation, finalClientIp, finalFullMethodName, finalUserId, executionTime));

        return result;
    }
}
