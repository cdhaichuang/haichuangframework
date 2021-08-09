package pro.haichuang.framework.base.config.swagger.factory;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import pro.haichuang.framework.base.config.swagger.SwaggerInfo;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * swagger工厂类
 *
 * <p>该类为 [Swagger] 工厂类, 主要用于根据 {@link SwaggerInfo} 创建 {@link Docket} 对象
 * <p>该类指定了部分默认参数, 如需自定义在参数 {@link SwaggerInfo} 中进行赋值相关属性即可
 * <p>注意: 请求地址中带有 [common] 字段时 {@link SecurityContext} 上下文将不生效
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see SwaggerInfo
 * @since 1.0.0
 */
public class SwaggerFactory {

    /**
     * 默认分组名
     */
    public static final String DEFAULT_GROUP_NAME = "default";

    /**
     * 默认标题
     */
    public static final String DEFAULT_TITLE = "HaiChuang Restful Apis";

    /**
     * 默认描述
     */
    public static final String DEFAULT_DESCRIPTION = "HaiChuang Restful Apis Description";

    /**
     * 默认服务条款URL
     */
    public static final String DEFAULT_TERMS_OF_SERVICE_URL = "https://www.haichuang.pro";

    /**
     * 默认作者信息
     */
    public static final Contact DEFAULT_CONTACT = new Contact("JiYinchuan", "https://www.haichuang.pro", "jiyinchuan@haichuang.pro");

    /**
     * 默认版本号
     */
    public static final String DEFAULT_VERSION = "1.0.0";

    /**
     * 忽略权限的正则表达式
     * 主要用于接口文档中默认对匹配的路径不加上请求头, 方便操作
     */
    public static final Pattern IGNORE_PATTERN = Pattern.compile("^((?!common).)*$");

    /**
     * 初始化API文档
     *
     * @param infoDTO Swagger信息
     * @return API文档
     */
    public static Docket createRestApi(SwaggerInfo infoDTO) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        if (infoDTO.getParameters() != null && !infoDTO.getParameters().isEmpty()) {
            docket.securitySchemes(new ArrayList<>(infoDTO.getParameters()))
                    .securityContexts(Collections.singletonList(securityContext(infoDTO.getParameters())));

        }
        String groupName = (infoDTO.getGroupName() != null) && (!infoDTO.getGroupName().isEmpty())
                ? infoDTO.getGroupName() : DEFAULT_GROUP_NAME;
        Predicate<RequestHandler> predicate = (infoDTO.getBasePackage() != null) && (!infoDTO.getBasePackage().isEmpty())
                ? RequestHandlerSelectors.basePackage(infoDTO.getBasePackage()) : RequestHandlerSelectors.withClassAnnotation(Api.class);
        return docket.apiInfo(apiInfo(infoDTO))
                .groupName(groupName)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .extensions(infoDTO.getOpenApiExtensionResolver().buildExtensions(groupName));
    }

    /**
     * 设置API信息
     *
     * @param infoDTO Swagger信息
     * @return API信息
     */
    private static ApiInfo apiInfo(SwaggerInfo infoDTO) {
        return new ApiInfoBuilder()
                .title(StringUtils.isNotBlank(infoDTO.getTitle()) ? infoDTO.getTitle() : DEFAULT_TITLE)
                .description(StringUtils.isNotBlank(infoDTO.getDescription()) ? infoDTO.getDescription() : DEFAULT_DESCRIPTION)
                .termsOfServiceUrl(StringUtils.isNotBlank(infoDTO.getTermsOfServiceUrl()) ? infoDTO.getTermsOfServiceUrl() : DEFAULT_TERMS_OF_SERVICE_URL)
                .contact(infoDTO.getContact() != null ? infoDTO.getContact() : DEFAULT_CONTACT)
                .version(StringUtils.isNotBlank(infoDTO.getVersion()) ? infoDTO.getVersion() : DEFAULT_VERSION).build();
    }

    /**
     * 创建安全上下文
     *
     * @param parameters 授权参数
     * @return 返回权限上下文
     */
    private static SecurityContext securityContext(List<SecurityScheme> parameters) {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("web", "access_token");
        return SecurityContext.builder()
                .securityReferences(parameters.stream().map(parameter -> new SecurityReference(parameter.getName(), authorizationScopes)).collect(Collectors.toList()))
                .forPaths(path -> IGNORE_PATTERN.matcher(path).matches())
                .build();
    }
}
