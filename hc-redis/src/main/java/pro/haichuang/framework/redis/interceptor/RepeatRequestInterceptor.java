package pro.haichuang.framework.redis.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pro.haichuang.framework.base.enums.error.client.RequestServerErrorEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.IpUtils;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.base.util.jwt.SecurityUtils;
import pro.haichuang.framework.redis.annotation.RepeatRequestValid;
import pro.haichuang.framework.redis.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 重复请求拦截器
 *
 * <p>基于 [Redis] 实现, 使用时请先引入 {@code hc-redis} 依赖, 否则不生效
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.redis.annotation.EnableRequestRepeatValidate
 * @see RepeatRequestValid
 * @since 1.0.0
 */
public class RepeatRequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatRequestInterceptor.class);

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatRequestValid repeatRequestValidAnnotation = method.getAnnotation(RepeatRequestValid.class);
            if (repeatRequestValidAnnotation != null) {
                String clientIp = IpUtils.getIpv4Address(request);

                Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();

                String repeatRedisKey = repeatRequestValidAnnotation.preKey()
                        .concat("#").concat(clientIp)
                        .concat("#").concat(request.getRequestURI());

                Map<String, String[]> parameterMap = request.getParameterMap();

                String parameterMd5Hex = parameterMap == null || parameterMap.isEmpty()
                        ? DigestUtils.md5Hex(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8))
                        : DigestUtils.md5Hex(JSONObject.toJSONString(parameterMap));

                // 判断同一用户
                String repeatValue = redisService.get(repeatRedisKey);
                if (StringUtils.equals(repeatValue, parameterMd5Hex)) {
                    ApiOperation apiOperationAnnotation = method.getAnnotation(ApiOperation.class);
                    String apiMessage = ObjectUtils.isNotEmpty(apiOperationAnnotation)
                            ? apiOperationAnnotation.value() : null;
                    LOGGER.warn("[重复请求拦截器] 拦截请求 [apiMessage: {}, requestUri: {}, clientIp: {}, userId: {}, params: {}]",
                            apiMessage, request.getRequestURI(), clientIp, userId, parameterMd5Hex);
                    ResponseUtils.writeOfJson(response, ResultVO.other(RequestServerErrorEnum.REPEAT_REQUEST,
                            "请求速度过快, 请稍后重试"));
                    return false;
                } else {
                    redisService.set(repeatRedisKey, parameterMd5Hex, repeatRequestValidAnnotation.value());
                }
            }
        }
        return true;
    }
}
