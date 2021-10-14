package pro.haichuang.framework.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MybatisPlus配置
 *
 * <p>该类为 [MybatisPlus] 核心配置
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
        this.fillStrategy(metaObject, "modifyTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
        this.fillStrategy(metaObject, "modifyTime", LocalDateTime.now());
    }
}
