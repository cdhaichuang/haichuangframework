package pro.haichuang.framework.base.util.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.dto.HttpServletRequestDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * <p>该类主要用于处理请求相关数据
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpServletRequestUtils {

    @NonNull
    public static HttpServletRequestDTO parseDTO(@NonNull HttpServletRequest request, @Nullable Api apiAnnotation, @Nullable ApiOperation apiOperationAnnotation) {
        String uuid = UUIDUtils.Local.get();
        String clientIp = IpUtils.getIpv4Address(request);
        String fullMethodName = apiAnnotation != null ? String.join(",", apiAnnotation.tags()) : "";
        if (apiOperationAnnotation != null) {
            fullMethodName += !apiOperationAnnotation.value().isEmpty() ? "-".concat(apiOperationAnnotation.value()) : "";
        }
        return new HttpServletRequestDTO()
                .setUuid(uuid)
                .setClientIp(clientIp);
    }

}
