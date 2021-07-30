package pro.haichuang.framework.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.haichuang.framework.base.annotation.EnableControllerAdvice;
import pro.haichuang.framework.base.annotation.EnableControllerLogAspect;
import pro.haichuang.framework.base.annotation.EnableGlobalCorsConfig;
import pro.haichuang.framework.base.annotation.EnableLogSave;
import pro.haichuang.framework.redis.annotation.EnableRequestRepeatValidate;

/**
 * ServiceApplication
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@SpringBootApplication
@EnableGlobalCorsConfig
@EnableControllerAdvice
@EnableControllerLogAspect
@EnableLogSave
@EnableRequestRepeatValidate
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
