package pro.haichuang.framework.mybatis.generate.config;

import org.springframework.stereotype.Component;

/**
 * MyBatisPlus基本配置文件
 *
 * @author JiYinchuan
 * @version 1.0
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
     * 实体包输出配置 [-1: 只输出实体包(公共模块), 0: 输出所有包(聚合模块), 1: 输出除实体以外的包(分离模块)]
     */
    private Integer outputType = 0;

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

    public Integer getOutputType() {
        return outputType;
    }

    public void setOutputType(Integer outputType) {
        this.outputType = outputType;
    }

    public Boolean getEnableSwagger() {
        return enableSwagger;
    }

    public void setEnableSwagger(Boolean enableSwagger) {
        this.enableSwagger = enableSwagger;
    }
}
