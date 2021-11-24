package pro.haichuang.framework.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.haichuang.framework.base.annotation.*;
import pro.haichuang.framework.redis.annotation.EnableRequestRepeatValidate;

/**
 * ServiceApplication
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@SpringBootApplication
@EnableGlobalCorsConfig
@EnableControllerAdvice
@EnableControllerLogAspect
@EnableLogSave
@EnableRequestRepeatValidate
@EnableRequestTimeoutAspect
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
