package pro.haichuang.framework.mybatis.generate.config;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatisPlus基本配置文件
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService
 * @since 1.1.0.211021
 */
@Component
public class CodeBasicConfig {

    /**
     * 作者
     */
    private String author = "JiYinchuan";

    /**
     * 从xxx版本开始
     */
    private String since;

    /**
     * 输出包类型
     */
    private OutputType outputType = OutputType.ALL;

    /**
     * 是否开启Swagger注解支持
     */
    private Boolean enableSwagger = true;

    public CodeBasicConfig() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear() % 100);
        String month = now.getMonthValue() < 10 ? "0" + now.getMonthValue() : String.valueOf(now.getMonthValue());
        String day = now.getDayOfMonth() < 10 ? "0" + now.getDayOfMonth() : String.valueOf(now.getDayOfMonth());
        since = "1.0.0." + year + month + day;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
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
        ALL_EXCLUDE_DOMAIN

    }
}
