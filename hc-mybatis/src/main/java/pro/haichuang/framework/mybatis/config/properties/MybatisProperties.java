package pro.haichuang.framework.mybatis.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import pro.haichuang.framework.mybatis.generate.properties.GenerateConfigProperties;

/**
 * Mybatis参数配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "haichuang.mybatis")
public class MybatisProperties {

    /**
     * Mybatis配置
     */
    private Config config;

    /**
     * Mybatis代码生成器配置
     */
    @NestedConfigurationProperty
    private GenerateConfigProperties generate = new GenerateConfigProperties();
    /**
     * Mybatis配置
     */
    public static class Config {
        /**
         * 是否启用
         */
        private Boolean enable;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public GenerateConfigProperties getGenerate() {
        return generate;
    }

    public void setGenerate(GenerateConfigProperties generate) {
        this.generate = generate;
    }
}
