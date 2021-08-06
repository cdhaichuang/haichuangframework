package pro.haichuang.framework.redis.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pro.haichuang.framework.base.dto.HttpServletRequestDTO;
import pro.haichuang.framework.base.enums.error.client.RequestServerErrorEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.RequestUtils;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.redis.annotation.RepeatRequestValid;
import pro.haichuang.framework.redis.constant.RedisKeyOfFramework;
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
    private static final String LOG_TAG = "重复请求拦截器";

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
                String uuid = UUIDUtils.Local.get();
                HttpServletRequestDTO httpServletRequestDTO = RequestUtils.parseInfo(request, method);
                // 客户端真实请求IP地址
                String clientIp = httpServletRequestDTO.getClientIp();
                // 请求信息
                String apiMessage = httpServletRequestDTO.getApiMessage();
                // 请求用户ID
                Long userId = httpServletRequestDTO.getUserId();
                // 完整请求方法
                String fullMethodName = httpServletRequestDTO.getFullMethodName();

                // RequestParams
                Map<String, String[]> parameterMap = request.getParameterMap();
                String parameterString = "";
                if (parameterMap != null && !parameterMap.isEmpty()) {
                    // Params
                    parameterString = JSONObject.toJSONString(parameterMap);
                } else if (RequestUtils.isJsonRequest(request)) {
                    // Body
                    parameterString = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
                }

                // RedisKey
                String repeatRedisKey = RedisKeyOfFramework.repeatRequest(repeatRequestValidAnnotation.preKey(),
                        clientIp, String.valueOf(userId), request.getRequestURI());
                String rdbParameterString = redisService.get(repeatRedisKey);

                if (rdbParameterString == null) {
                    redisService.set(repeatRedisKey, parameterString, repeatRequestValidAnnotation.value());
                } else {
                    LOGGER.warn("[{}] 拦截请求 [uuid: {}, apiMessage: {}, requestUri: {}, method: {}, " +
                                    "clientIp: {}, userId: {}, params: {}]",
                            LOG_TAG, uuid, apiMessage, request.getRequestURI(), fullMethodName, clientIp, userId, parameterString);
                    ResponseUtils.writeOfJson(response, ResultVO.other(RequestServerErrorEnum.REPEAT_REQUEST,
                            "请求速度过快, 请稍后重试"));
                    return false;
                }
            }
        }
        return true;
    }
}
