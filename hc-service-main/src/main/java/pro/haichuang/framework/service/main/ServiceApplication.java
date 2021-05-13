package pro.haichuang.framework.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.haichuang.framework.base.config.annotation.EnableControllerAdvice;
import pro.haichuang.framework.base.config.annotation.EnableControllerLogAspect;
import pro.haichuang.framework.base.config.annotation.EnableLogSave;
import pro.haichuang.framework.base.config.annotation.EnableSecurityAspect;
import pro.haichuang.framework.redis.config.annotation.EnableRequestRepeatValidate;

/**
 * ServiceApplication
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SpringBootApplication
@EnableControllerAdvice
@EnableControllerLogAspect
@EnableSecurityAspect
@EnableLogSave
@EnableRequestRepeatValidate
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
