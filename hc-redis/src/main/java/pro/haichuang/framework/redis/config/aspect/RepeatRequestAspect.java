// package pro.haichuang.framework.redis.config.aspect;
//
// import com.alibaba.fastjson.JSONObject;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import org.apache.commons.lang3.StringUtils;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
// import pro.haichuang.framework.base.enums.error.client.RequestServerErrorEnum;
// import pro.haichuang.framework.base.response.ResultVO;
// import pro.haichuang.framework.base.util.common.IpUtils;
// import pro.haichuang.framework.base.util.common.UUIDUtils;
// import pro.haichuang.framework.redis.annotation.RepeatRequestValid;
// import pro.haichuang.framework.redis.service.RedisService;
//
// import javax.servlet.http.HttpServletRequest;
// import java.lang.reflect.Method;
// import java.util.Map;
//
// /**
//  * 重复请求拦截器
//  *
//  * <p>基于 [Redis] 实现, 使用时请先引入 {@code hc-redis} 依赖, 否则不生效
//  *
//  * @author JiYinchuan
//  * @version 1.0.0
//  * @since 1.0.0
//  * @see pro.haichuang.framework.redis.annotation.RepeatRequestValid
//  */
// @Aspect
// public class RepeatRequestAspect {
//
//     private static final Logger LOGGER = LoggerFactory.getLogger(RepeatRequestAspect.class);
//
//     @Autowired
//     private RedisService redisService;
//
//     @Pointcut("@annotation(pro.haichuang.framework.redis.annotation.RepeatRequestValid)")
//     public void logPointCut() {
//     }
//
//     @Around("logPointCut()")
//     public Object around(ProceedingJoinPoint point) throws Throwable {
//         // UUID
//         String uuid = UUIDUtils.Local.get();
//         // 请求
//         HttpServletRequest request;
//         // 客户端IP真实地址
//         String clientIp;
//         // 完整方法名
//         String fullMethodName;
//         // 描述信息
//         String message;
//         // 用户ID
//         Long userId = null;
//
//         ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//         if (requestAttributes != null) {
//             request = requestAttributes.getRequest();
//
//             clientIp = IpUtils.getIpv4Address(request);
//
//             MethodSignature signature = (MethodSignature) point.getSignature();
//             Method method = signature.getMethod();
//             fullMethodName = point.getTarget().getClass().getName() + "." + method.getName() + "()";
//             Api apiAnnotation = method.getDeclaringClass().getAnnotation(Api.class);
//             ApiOperation apiOperationAnnotation = method.getAnnotation(ApiOperation.class);
//
//             message = apiAnnotation != null && apiOperationAnnotation != null
//                     ? String.join(",", apiAnnotation.tags()) + "-" + apiOperationAnnotation.value() : null;
//
//             RepeatRequestValid repeatRequestValidAnnotation = method.getAnnotation(RepeatRequestValid.class);
//             if (repeatRequestValidAnnotation != null) {
//                 String repeatRedisKey = repeatRequestValidAnnotation.preKey()
//                         .concat("#").concat(clientIp)
//                         .concat("#").concat(request.getRequestURI());
//
//                 String parameterMd5Hex;
//
//                 Map<String, String[]> parameterMap = request.getParameterMap();
//
//                 if (parameterMap == null || parameterMap.isEmpty()) {
//                     parameterMd5Hex = JSONObject.toJSONString(point.getArgs());
//                 }else {
//                     parameterMd5Hex = JSONObject.toJSONString(parameterMap);
//                 }
//
//                 // 判断同一用户
//                 String repeatValue = redisService.get(repeatRedisKey);
//                 if (StringUtils.equals(repeatValue, parameterMd5Hex)) {
//                     LOGGER.warn("[重复请求拦截器] 拦截请求 [uuid: {}, apiMessage: {}, requestUri: {}, method: {}, clientIp: {}, userId: {}, params: {}]",
//                             uuid, message, request.getRequestURI(), fullMethodName, clientIp, userId, parameterMd5Hex);
//                     return ResultVO.other(RequestServerErrorEnum.REPEAT_REQUEST,
//                             "请求速度过快, 请稍后重试");
//                 }else {
//                     redisService.set(repeatRedisKey, parameterMd5Hex, repeatRequestValidAnnotation.value());
//                 }
//             }
//         }
//         return point.proceed();
//     }
// }
