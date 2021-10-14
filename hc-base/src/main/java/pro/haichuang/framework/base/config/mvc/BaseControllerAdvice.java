package pro.haichuang.framework.base.config.mvc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.enums.error.client.RequestServerErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;
import pro.haichuang.framework.base.exception.StackTraceException;
import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.response.vo.BaseVO;
import pro.haichuang.framework.base.util.common.IpUtils;
import pro.haichuang.framework.base.util.jwt.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局Controller异常处理
 *
 * <p>该类具体实现了全局请求异常的拦截, 对于不同的异常有不同的拦截并返回, 无论是自定义异常/校验异常/未知异常等都有具体的实现,
 * 拦截后给予客户端友好的返回, 同时将异常规范的写出到日志文件中, 便于项目运行中通过日志排查问题
 * <p>注意: 该类启用的前置条件为标注了
 * {@link pro.haichuang.framework.base.annotation.EnableControllerAdvice @EnableControllerAdvice} 注解
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
@RestControllerAdvice
public class BaseControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerAdvice.class);
    private static final String LOG_TAG = "全局控制器异常捕获";
    private static final Pattern ENUM_ILLEGAL_ARGUMENT_PRINT_PATTERN = Pattern.compile("(\\[.*])\\s");

    // ========================= 基础验证 =========================

    /**
     * 自定义异常全局捕获
     *
     * @param e       {@link pro.haichuang.framework.base.exception.ApplicationException}
     * @param request {@link javax.servlet.http.HttpServletRequest}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(ApplicationException.class)
    public BaseVO generalErrorHandle(ApplicationException e, HttpServletRequest request) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 自定义异常信息 ------------------------- [Begin]", LOG_TAG);
        printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), "自定义异常信息", clientIp, userId, null);
        LOGGER.warn("[{}] ------------------------- 自定义异常信息 ------------------------- [ End ]", LOG_TAG);
        return ResultVO.other(e.getBaseEnum(), e.getUserTip());
    }

    /**
     * 堆栈异常全局捕获
     *
     * @param e        {@link pro.haichuang.framework.base.exception.StackTraceException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(StackTraceException.class)
    public BaseVO stackTracerErrorHandler(StackTraceException e, HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.error("[{}] ------------------------- 堆栈异常信息 ------------------------- [Begin]", LOG_TAG);
        LOGGER.error("[{}] 堆栈异常信息 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getLocalizedMessage(), e);
        LOGGER.error("[{}] ------------------------- 堆栈异常信息 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultVO.other(RequestServerErrorEnum.SERVICE_ERROR, ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    /**
     * 第三方异常全局捕获
     *
     * @param e        {@link pro.haichuang.framework.base.exception.ThirdPartyException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(ThirdPartyException.class)
    public JSONObject thirdPartyErrorHandler(ThirdPartyException e, HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.error("[{}] ------------------------- 第三方异常信息 ------------------------- [Begin]", LOG_TAG);
        LOGGER.error("[{}] 第三方异常信息 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorCode: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getErrorCode(), e.getErrorMessage(), e);
        LOGGER.error("[{}] ------------------------- 第三方异常信息 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestServerErrorEnum.SERVICE_ERROR.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    /**
     * 系统异常全局捕获
     *
     * @param e        {@link java.lang.Exception}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(Exception.class)
    public BaseVO serverErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.error("[{}] ------------------------- 系统异常信息 ------------------------- [Begin]", LOG_TAG);
        LOGGER.error("[{}] 系统异常信息 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getLocalizedMessage(), e);
        LOGGER.error("[{}] ------------------------- 系统异常信息 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultVO.other(RequestServerErrorEnum.SERVICE_ERROR, ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    // ========================= 请求参数验证 =========================

    /**
     * 参数为空全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestParam}</b>
     * <p>捕获来自 {@link org.springframework.web.bind.annotation.RequestParam}
     *
     * @param e        {@link org.springframework.web.bind.MissingServletRequestParameterException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JSONObject validationExceptionHandler(MissingServletRequestParameterException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 参数为空 ------------------------- [Begin]", LOG_TAG);
        LOGGER.warn("[{}] 参数为空 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getLocalizedMessage());
        LOGGER.warn("[{}] ------------------------- 参数为空 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.PARAMETER_EMPTY.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, e.getLocalizedMessage());
    }

    /**
     * 参数转换异常全局捕获
     *
     * @param e        {@link org.springframework.web.method.annotation.MethodArgumentTypeMismatchException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JSONObject validationExceptionHandler(MethodArgumentTypeMismatchException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 参数转换异常 ------------------------- [Begin]", LOG_TAG);
        String errorMessage = this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), "参数转换异常", clientIp, userId, e.getLocalizedMessage());
        LOGGER.warn("[{}] ------------------------- 参数转换异常 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.PARAMETER_EMPTY.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, errorMessage)
                .fluentPut(BaseVO.USER_TIP, e.getLocalizedMessage().equals(errorMessage)
                        ? errorMessage : ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    /**
     * 验证异常全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestParam}</b>
     * <p>捕获来自 {@link javax.validation.constraints}
     *
     * @param e        {@link javax.validation.ValidationException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(ValidationException.class)
    public JSONObject validationExceptionHandler(ValidationException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 验证异常 ------------------------- [Begin]", LOG_TAG);
        LOGGER.warn("[{}] 验证异常 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getLocalizedMessage());
        List<String> userTipMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            userTipMessages.add(constraintViolation.getMessage());
        }
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(),
                "验证异常", clientIp, userId, null);
        LOGGER.warn("[{}] ------------------------- 验证异常 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, String.join(",", userTipMessages));
    }

    /**
     * 请求体异常全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestBody}</b>
     * <p>Also throw to {@link InvalidFormatException}
     *
     * @param e        {@link org.springframework.http.converter.HttpMessageNotReadableException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @see com.fasterxml.jackson.databind.DeserializationContext#weirdStringException(String, Class, String)
     * <p>Enum deserialize failed exception is unsolved
     * @since 1.0.0.211009
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JSONObject validationExceptionHandler(HttpMessageNotReadableException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 请求体异常 ------------------------- [Begin]", LOG_TAG);
        String errorMessage = this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(),
                "请求体异常", clientIp, userId, e.getLocalizedMessage());
        LOGGER.warn("[{}] 请求体异常 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, errorMessage);
        LOGGER.warn("[{}] ------------------------- 请求体异常 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.PARAMETER_EMPTY.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, errorMessage)
                .fluentPut(BaseVO.USER_TIP, e.getLocalizedMessage().equals(errorMessage)
                        ? errorMessage : ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    /**
     * 请求体验证全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestBody}</b>
     * <p>捕获来自 {@link javax.validation.constraints}
     *
     * @param e        {@link org.springframework.web.bind.MethodArgumentNotValidException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONObject validationExceptionHandler(MethodArgumentNotValidException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 请求体验证异常 ------------------------- [Begin]", LOG_TAG);
        List<String> errorMessages = new ArrayList<>();
        List<String> userTipMessages = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errorMessages.add(String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()));
            userTipMessages.add(fieldError.getDefaultMessage());
            LOGGER.warn("[{}] 请求体验证异常 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorType: @{}, errorMessage: {}, field: {}#{}={}]",
                    LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, fieldError.getCode(), fieldError.getDefaultMessage(),
                    e.getParameter().getParameterType().getName(), fieldError.getField(), fieldError.getRejectedValue());
        }
        LOGGER.warn("[{}] ------------------------- 请求体验证异常 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, String.join(", ", errorMessages))
                .fluentPut(BaseVO.USER_TIP, String.join(", ", userTipMessages));
    }

    /**
     * 绑定异常全局捕获
     *
     * @param e        {@link org.springframework.validation.BindException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(BindException.class)
    public JSONObject validationExceptionHandler(BindException e, HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 绑定异常 ------------------------- [Begin]", LOG_TAG);
        LOGGER.warn("[{}] 绑定异常 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getAllErrors().get(0).getDefaultMessage());
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(),
                "绑定异常", clientIp, userId, null);
        LOGGER.warn("[{}] ------------------------- 绑定异常 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getAllErrors().get(0).getDefaultMessage())
                .fluentPut(BaseVO.USER_TIP, e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 方法不被允许全局捕获
     *
     * @param e        HttpRequestMethodNotSupportedException
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @since 1.0.0.211009
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject validationExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                 HttpServletRequest request, HttpServletResponse response) {
        String clientIp = IpUtils.getIpv4Address(request);
        Long userId = SecurityUtils.getJwtPayloadOrNewInstance().getUserId();
        LOGGER.warn("[{}] ------------------------- 方法不被允许 ------------------------- [Begin]", LOG_TAG);
        LOGGER.warn("[{}] 方法不被允许 [requestUri: {}, requestMethod: {}, clientIp: {}, userId: {}, errorMessage: {}]",
                LOG_TAG, request.getRequestURI(), request.getMethod(), clientIp, userId, e.getLocalizedMessage());
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(),
                "方法不被允许", clientIp, userId, null);
        LOGGER.warn("[{}] ------------------------- 方法不被允许 ------------------------- [ End ]", LOG_TAG);
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamErrorEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, ApplicationException.DEFAULT_ERROR_USER_TIP);
    }

    /**
     * 格式化异常信息|堆栈异常打印
     *
     * <p>为了保证输出安全, 如果为 {@link EnumIllegalArgumentException} 异常,
     * 则采用正则表达式 {@code "(\\[.*])\\s"} 来匹配 {@link pro.haichuang.framework.base.enums.BaseEnum#resolve(String, Class)} 中
     * 解析异常所抛出的 {@link EnumIllegalArgumentException} 异常信息, 将异常信息的全限定名进行删除后输出
     *
     * @param requestUri           请求地址
     * @param requestMethod        请求方法
     * @param tp                   {@code t} 父节点
     * @param t                    Throwable
     * @param tag                  输出标签
     * @param clientIp             客户端IP
     * @param userId               用户ID
     * @param originalErrorMessage 原始错误信息
     * @return 格式化后的错误信息
     * @since 1.0.0.211009
     */
    @Nullable
    private String printStackTraceFormat(String requestUri, String requestMethod, Throwable tp, @Nullable Throwable t,
                                         String tag, String clientIp, @Nullable Long userId,
                                         @Nullable String originalErrorMessage) {
        if (t == null) {
            if (tp instanceof EnumIllegalArgumentException) {
                Matcher matcher = ENUM_ILLEGAL_ARGUMENT_PRINT_PATTERN.matcher(tp.getLocalizedMessage());
                originalErrorMessage = tp.getLocalizedMessage();
                if (matcher.find()) {
                    originalErrorMessage = originalErrorMessage.replace(matcher.group(), "");
                }
                LOGGER.warn("[{}] {} [requestUri: {}, requestMethod: {}, clientIp, {}, userId: {}, errorMessage: {}]",
                        LOG_TAG, tag, requestUri, requestMethod, clientIp, userId, tp.getLocalizedMessage());
            } else {
                boolean isPrintStack = false;
                for (StackTraceElement stackTraceElement : tp.getStackTrace()) {
                    if (stackTraceElement.getClassName().contains("pro.haichuang")) {
                        LOGGER.warn("[{}] {} [requestUri: {}, requestMethod: {}, clientIp, {}, userId: {}, stackTrace: {}]",
                                LOG_TAG, tag, requestUri, requestMethod, clientIp, userId, stackTraceElement);
                        isPrintStack = true;
                    }
                }
                if (!isPrintStack && originalErrorMessage != null) {
                    originalErrorMessage = originalErrorMessage.split(":")[0];
                }
            }
            return originalErrorMessage;
        } else {
            return this.printStackTraceFormat(requestUri, requestMethod, t, t.getCause(), tag, clientIp, userId, originalErrorMessage);
        }
    }
}
