package pro.haichuang.framework.base.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Async异步配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 设置线程池组名
        taskExecutor.setThreadGroupName("AsyncConfig");
        // 核心线程数, 默认为 [1]
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 核心线程是否允许超时, 默认为 [false]
        taskExecutor.setAllowCoreThreadTimeOut(false);
        // 线程池最大线程数, 默认为 [Integer.MAX_VALUE]
        taskExecutor.setMaxPoolSize(100);
        // 线程池中线程最大空闲时间, 默认为 [60]
        taskExecutor.setKeepAliveSeconds(60);
        // 线程队列最大线程数, 默认为 [Integer.MAX_VALUE]
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setTaskDecorator(runnable -> {
            log.info("========================= 进入 =========================");
            return runnable;
        });
        taskExecutor.setThreadNamePrefix("Async-Thread-%d");
        // 线程池拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // IOC容器关闭时是否阻塞等待剩余的任务执行完成, 默认为 [false]
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 阻塞IOC容器关闭的时间, 单位s, 默认为 [0]
        taskExecutor.setAwaitTerminationSeconds(60);
        // 设置线程优先级, 默认为 [Thread.NORM_PRIORITY]
        taskExecutor.setThreadPriority(Thread.NORM_PRIORITY);
        return taskExecutor;
    }
}
