package pro.haichuang.framework.base.config.aspect;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.annotation.Order;
import pro.haichuang.framework.base.annotation.EnableRequestTimeoutAspect;
import pro.haichuang.framework.base.config.properties.BaseConfigProperties;
import pro.haichuang.framework.base.enums.error.server.ExecutionErrorEnum;
import pro.haichuang.framework.base.exception.server.ExecutionException;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.concurrent.*;

/**
 * 请求超时切面
 *
 * <p>该类具体实现了记录请求日志的功能, 切点为标注了 {@link ApiOperation @ApiOperation} 注解的方法开启请求超时切面监听
 * <p>注意: 该类启用的前提条件为标注了 {@link EnableRequestTimeoutAspect @EnableControllerTimeoutAspect} 注解
 *
 * @author JiYinchuan
 * @see EnableRequestTimeoutAspect
 * @since 1.1.0.211021
 */
@Aspect
@Order(2)
public class RequestTimeoutAspect {

    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private BaseConfigProperties baseConfigProperties;

    private static ExecutorService executorService;

    @PostConstruct
    public void init() {
        // 设置线程池的大小为 [Tomcat] 线程的大小, 队列大小最小为 [Tomcat] 最大线程数, 最大为 [1024]
        executorService = new ThreadPoolExecutor(
                serverProperties.getTomcat().getThreads().getMinSpare(),
                serverProperties.getTomcat().getThreads().getMax(),
                30, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(serverProperties.getTomcat().getThreads().getMax()),
                new ThreadFactoryBuilder().setNamePrefix("RequestTimeoutAspect-Thread-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 开始执行时间
        long beginTime = System.currentTimeMillis();

        Future<Object> future = executorService.submit(() -> {
            try {
                return point.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        while (true) {
            if (future.isDone()) {
                return future.get();
            }
            Duration requestTimeoutTime = baseConfigProperties.getRequestTimeoutTime();
            if (System.currentTimeMillis() - beginTime > requestTimeoutTime.toMillis()) {
                future.cancel(true);
                throw new ExecutionException(ExecutionErrorEnum.EXECUTION_TIMEOUT);
            }
        }
    }
}
