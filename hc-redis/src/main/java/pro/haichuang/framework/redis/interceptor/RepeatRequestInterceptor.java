package pro.haichuang.framework.redis.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pro.haichuang.framework.base.enums.abnormal.RequestServerAbnormalEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.base.util.common.IpUtils;
import pro.haichuang.framework.redis.config.annotation.RepeatRequestValid;
import pro.haichuang.framework.redis.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 重复请求拦截器
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Slf4j
public class RepeatRequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatRequestValid repeatRequestValidAnnotation = method.getAnnotation(RepeatRequestValid.class);
            if (repeatRequestValidAnnotation != null) {
                String repeatRedisKey = repeatRequestValidAnnotation.preKey() + "#" + request.getRequestURI();

                Map<String, String[]> parameterMap = request.getParameterMap();

                String parameterMapString = JSONObject.toJSONString(parameterMap);

                // 判断同一用户
                String repeatValue = redisService.get(repeatRedisKey);
                if (StringUtils.equals(repeatValue, parameterMapString)) {
                    ApiOperation apiOperationAnnotation = method.getAnnotation(ApiOperation.class);
                    String apiMessage = ObjectUtils.isNotEmpty(apiOperationAnnotation) ? apiOperationAnnotation.value() : null;
                    log.info("[重复请求拦截器] 拦截请求 [apiMessage: {}, requestUri: {}, clientIp: {}, params: {}]",
                            apiMessage,
                            request.getRequestURI(),
                            IpUtils.getIpAddr(request),
                            parameterMapString);
                    ResponseUtils.write(response, ResultVO.other(RequestServerAbnormalEnum.REPEAT_REQUEST, "请求速度过快, 请稍后重试"));
                    return false;
                }else {
                    redisService.set(repeatRedisKey, parameterMapString, repeatRequestValidAnnotation.value());
                }
            }
        }
        return true;
    }
}
