package pro.haichuang.framework.mybatis.generate.config;

import org.springframework.stereotype.Component;

/**
 * MyBatisPlus基本配置文件
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService
 * @since 1.0.0
 */
@Component
public class CodeBasicConfig {

    /**
     * 作者
     */
    private String author = "JiYinchuan";

    /**
     * 版本
     */
    private String version = "1.0";

    /**
     * 输出包类型
     */
    private OutputType outputType = OutputType.ALL;

    /**
     * 是否开启Swagger注解支持
     */
    private Boolean enableSwagger = true;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public Boolean getEnableSwagger() {
        return enableSwagger;
    }

    public void setEnableSwagger(Boolean enableSwagger) {
        this.enableSwagger = enableSwagger;
    }

    /**
     * 输出包类型
     */
    public enum OutputType {

        /**
         * 输出所有包
         */
        ALL,

        /**
         * 仅输出实体包
         */
        ONLY_DOMAIN,

        /**
         * 输出除实体外所有包
         */
        ALL_EXCLUDE_DOMAIN;

    }
}
