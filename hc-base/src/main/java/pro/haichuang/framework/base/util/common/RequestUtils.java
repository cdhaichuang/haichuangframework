package pro.haichuang.framework.base.util.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.dto.HttpServletRequestDTO;
import pro.haichuang.framework.base.util.jwt.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 请求工具类
 *
 * <p>该类主要用于处理请求相关数据
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class RequestUtils {

    /**
     * 解析HttpServletRequest请求相关信息
     *
     * <p>该类一般用于AOP或Intercept中, 传入 {@link HttpServletRequest} 与 {@link Method} 参数, 返回该请求相关的信息
     *
     * @param request HttpServletRequest
     * @param method  method
     * @return HttpServletRequestDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static HttpServletRequestDTO parseInfo(@NonNull HttpServletRequest request, @NotNull Method method) {
        String clientIp = IpUtils.getIpv4Address(request);

        List<String> parameterFullNameList = Arrays.stream(method.getParameters()).collect(ArrayList::new,
                (result, item) -> result.add(item.getType().getName().concat(" ").concat(item.getName())), List::addAll);
        String fullMethodName = String.format("%s.%s(%s)", method.getDeclaringClass().getName(), method.getName(),
                String.join(", ", parameterFullNameList));

        Api apiAnnotation = method.getDeclaringClass().getAnnotation(Api.class);
        ApiOperation apiOperationAnnotation = method.getAnnotation(ApiOperation.class);
        String methodDescription = apiAnnotation != null && apiAnnotation.tags().length > 0
                ? String.join(",", apiAnnotation.tags()) : "";
        methodDescription += apiOperationAnnotation != null && !apiOperationAnnotation.value().isEmpty()
                ? "-".concat(apiOperationAnnotation.value()) : "";

        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();

        return new HttpServletRequestDTO()
                .setClientIp(clientIp)
                .setApiMessage(methodDescription)
                .setUserId(userId)
                .setFullMethodName(fullMethodName);
    }

    /**
     * 判断是否为JSON请求
     *
     * @param request HttpServletRequest
     * @return 是否为JSON请求 [true: 是, false: 否]
     * @since 1.1.0.211021
     */
    public static boolean isJsonRequest(@NonNull HttpServletRequest request) {
        String contentTypeInHeaderValue = request.getHeader(HttpHeaders.CONTENT_TYPE);
        return contentTypeInHeaderValue != null && contentTypeInHeaderValue.contains(MediaType.APPLICATION_JSON_VALUE);
    }
}
