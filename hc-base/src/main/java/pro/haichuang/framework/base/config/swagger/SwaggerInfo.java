package pro.haichuang.framework.base.config.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;

import java.util.List;

/**
 * Swagger信息
 *
 * <p>该类为 {@code Swagger} 相关信息封装,
 * 配合 {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory} 工厂类生成
 * {@link springfox.documentation.spring.web.plugins.Docket} 对象, 创建相关接口文档数据
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory
 */
public class SwaggerInfo {

    /**
     * 分组名称
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_GROUP_NAME}
     */
    private final String groupName;

    /**
     * 标题
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_TITLE}
     */
    private final String title;

    /**
     * 描述
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_DESCRIPTION}
     */
    private final String description;

    /**
     * 服务条款URL
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_TERMS_OF_SERVICE_URL}
     */
    private final String termsOfServiceUrl;

    /**
     * 作者信息
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_CONTACT}
     */
    private final Contact contact;

    /**
     * 版本号
     * 默认值 = {@link pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory#DEFAULT_VERSION}
     */
    private final String version;

    /**
     * 扫描的包路径
     */
    private final String basePackage;

    /**
     * 扩展解析器对象(必传, 将自动注入的对象赋值过来即可)
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * 授权参数
     * <ul>
     *     <li>KeyValue: new ApiKey("参数说明(Token)", "参数名(Authorization)", "参数位置(header|query)")</li>
     *     <li>TODO BasicAuth Not Finish</li>
     *     <li>TODO OAuth</li>
     * </ul>
     */
    private final List<SecurityScheme> parameters;

    public SwaggerInfo(String groupName, String title, String description, String termsOfServiceUrl,
                       Contact contact, String version, String basePackage,
                       OpenApiExtensionResolver openApiExtensionResolver, List<SecurityScheme> parameters) {
        this.groupName = groupName;
        this.title = title;
        this.description = description;
        this.termsOfServiceUrl = termsOfServiceUrl;
        this.contact = contact;
        this.version = version;
        this.basePackage = basePackage;
        this.openApiExtensionResolver = openApiExtensionResolver;
        this.parameters = parameters;
    }

    public static SwaggerInfoDtoBuilder builder() {
        return new SwaggerInfoDtoBuilder();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public Contact getContact() {
        return contact;
    }

    public String getVersion() {
        return version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public OpenApiExtensionResolver getOpenApiExtensionResolver() {
        return openApiExtensionResolver;
    }

    public List<SecurityScheme> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "SwaggerInfoDTO{" +
                "groupName='" + groupName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contact=" + contact +
                ", version='" + version + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", openApiExtensionResolver=" + openApiExtensionResolver +
                ", parameters=" + parameters +
                '}';
    }

    public static class SwaggerInfoDtoBuilder {

        private String groupName;
        private String title;
        private String description;
        private String termsOfServiceUrl;
        private Contact contact;
        private String version;
        private String basePackage;
        private OpenApiExtensionResolver openApiExtensionResolver;
        private List<SecurityScheme> parameters;

        public SwaggerInfo build() {
            return new SwaggerInfo(this.groupName, this.title, this.description, this.termsOfServiceUrl, this.contact, this.version, this.basePackage, this.openApiExtensionResolver, this.parameters);
        }

        public SwaggerInfoDtoBuilder groupName(final String groupName) {
            this.groupName = groupName;
            return this;
        }

        public SwaggerInfoDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public SwaggerInfoDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public SwaggerInfoDtoBuilder termsOfServiceUrl(final String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
            return this;
        }

        public SwaggerInfoDtoBuilder contact(final Contact contact) {
            this.contact = contact;
            return this;
        }

        public SwaggerInfoDtoBuilder version(final String version) {
            this.version = version;
            return this;
        }

        public SwaggerInfoDtoBuilder basePackage(final String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public SwaggerInfoDtoBuilder openApiExtensionResolver(final OpenApiExtensionResolver openApiExtensionResolver) {
            this.openApiExtensionResolver = openApiExtensionResolver;
            return this;
        }

        public SwaggerInfoDtoBuilder parameters(final List<SecurityScheme> parameters) {
            this.parameters = parameters;
            return this;
        }

        @Override
        public String toString() {
            return "SwaggerInfoDtoBuilder{" +
                    "groupName='" + groupName + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                    ", contact=" + contact +
                    ", version='" + version + '\'' +
                    ", basePackage='" + basePackage + '\'' +
                    ", openApiExtensionResolver=" + openApiExtensionResolver +
                    ", parameters=" + parameters +
                    '}';
        }
    }
}
