package pro.haichuang.framework.base.config.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pro.haichuang.framework.base.dto.HttpServletRequestDTO;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.base.util.common.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * <p>该类具体实现了记录请求日志的功能, 切点为标注了 {@link ApiOperation @ApiOperation} 注解的方法, 记录相关请求与响应数据
 * <p>注意: 该类启用的前提条件为标注了
 * {@link pro.haichuang.framework.base.annotation.EnableControllerLogAspect @EnableControllerLogAspect} 注解
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
@Aspect
@Order(1)
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    private static final String LOG_TAG = "Request";

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 开始执行时间
        long beginTime = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, ApplicationException.DEFAULT_ERROR_USER_TIP);
        HttpServletRequest request = requestAttributes.getRequest();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        HttpServletRequestDTO httpServletRequestDTO = RequestUtils.parseInfo(request, method);
        // 客户端真实请求IP地址
        String clientIp = httpServletRequestDTO.getClientIp();
        // 请求信息
        String apiMessage = httpServletRequestDTO.getApiMessage();
        // 请求用户ID
        Long userId = httpServletRequestDTO.getUserId();
        // 完整请求方法
        String fullMethodName = httpServletRequestDTO.getFullMethodName();

        LOGGER.info("[{}] [Begin] 检测到请求 [apiMessage: {}, requestUri: {}, method: {}, " +
                        "clientIp: {}, userId: {}, params: {}]",
                LOG_TAG, apiMessage, request.getRequestURI(), fullMethodName,
                clientIp, userId, point.getArgs());

        Object result = point.proceed();
        long executionTime = System.currentTimeMillis() - beginTime;

        LOGGER.info("[{}] [ End ] 检测到请求 [apiMessage: {}, requestUri: {}, method: {}, " +
                        "clientIp: {}, userId: {}, executionTime: {}]",
                LOG_TAG, apiMessage, request.getRequestURI(), fullMethodName, clientIp, userId, executionTime);
        LOGGER.debug("[{}] 检测到请求 [apiMessage: {}, requestUri: {}, method: {}, " +
                        "clientIp: {}, userId: {}, executionTime: {}, params: {}, result: {}]",
                LOG_TAG, apiMessage, request.getRequestURI(), fullMethodName, clientIp, userId, executionTime,
                point.getArgs(), objectMapper.writeValueAsString(result));

        return result;
    }
}
