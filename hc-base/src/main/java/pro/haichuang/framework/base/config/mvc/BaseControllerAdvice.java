package pro.haichuang.framework.base.config.mvc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pro.haichuang.framework.base.enums.abnormal.client.RequestParamAbnormalEnum;
import pro.haichuang.framework.base.enums.abnormal.client.RequestServerAbnormalEnum;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;
import pro.haichuang.framework.base.exception.StackTraceException;
import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.response.vo.BaseVO;
import pro.haichuang.framework.base.util.common.UUIDUtils;

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
 * @author JiYinchuan
 * @version 1.0
 */
@RestControllerAdvice
public class BaseControllerAdvice {

    public static final String BASE_ERROR_MESSAGE = "网络开小差了, 请稍后再试 (╯﹏╰)";
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
     */
    @ExceptionHandler(ApplicationException.class)
    @NonNull
    public BaseVO generalErrorHandle(@NonNull ApplicationException e,
                                     @NonNull HttpServletRequest request) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 自定义异常信息 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "自定义异常信息", null);
        LOGGER.warn("[{}] ------------------------- 自定义异常信息 ------------------------- [ End - {} ]", LOG_TAG, uuid);
        return ResultVO.other(e.getBaseEnum(), e.getUserTip());
    }

    /**
     * 堆栈异常全局捕获
     *
     * @param e        {@link pro.haichuang.framework.base.exception.StackTraceException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(StackTraceException.class)
    @NonNull
    public BaseVO stackTracerErrorHandler(@NonNull StackTraceException e,
                                          @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.error("[{}] ------------------------- 堆栈异常信息 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.error("[{}] 堆栈异常信息 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]",
                LOG_TAG, uuid, request.getRequestURI(), request.getMethod(), e.getLocalizedMessage(), e);
        LOGGER.error("[{}] ------------------------- 堆栈异常信息 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultVO.other(RequestServerAbnormalEnum.SERVICE_ABNORMAL, BASE_ERROR_MESSAGE);
    }

    /**
     * 第三方异常全局捕获
     *
     * @param e        {@link pro.haichuang.framework.base.exception.ThirdPartyException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(ThirdPartyException.class)
    @NonNull
    public JSONObject thirdPartyErrorHandler(@NonNull ThirdPartyException e,
                                             @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.error("[{}] ------------------------- 第三方异常信息 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.error("[{}] 第三方异常信息 [uuid: {}, requestUri: {}, requestMethod: {}, errorCode: {}, errorMessage: {}]",
                LOG_TAG, uuid, request.getRequestURI(), request.getMethod(), e.getErrorCode(), e.getErrorMessage(), e);
        LOGGER.error("[{}] ------------------------- 第三方异常信息 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestServerAbnormalEnum.SERVICE_ABNORMAL.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, BASE_ERROR_MESSAGE);
    }

    /**
     * 系统异常全局捕获
     *
     * @param e        {@link java.lang.Exception}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(Exception.class)
    @NonNull
    public BaseVO serverErrorHandler(@NonNull Exception e,
                                     @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.error("[{}] ------------------------- 系统异常信息 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.error("[{}] 系统异常信息 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]",
                LOG_TAG, uuid, request.getRequestURI(), request.getMethod(), e.getLocalizedMessage(), e);
        LOGGER.error("[{}] ------------------------- 系统异常信息 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultVO.other(RequestServerAbnormalEnum.SERVICE_ABNORMAL, BASE_ERROR_MESSAGE);
    }

    // ========================= 请求参数验证 =========================

    /**
     * 参数为空全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestParam}</b></p>
     * <p>捕获来自 {@link org.springframework.web.bind.annotation.RequestParam}</p>
     *
     * @param e        {@link org.springframework.web.bind.MissingServletRequestParameterException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull MissingServletRequestParameterException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 参数为空 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.warn("[{}] 参数为空 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]", LOG_TAG, uuid,
                request.getRequestURI(), request.getMethod(), e.getLocalizedMessage());
        LOGGER.warn("[{}] ------------------------- 参数为空 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.PARAMETER_EMPTY.value())
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
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull MethodArgumentTypeMismatchException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 参数转换异常 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        String errorMessage = this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "参数转换异常", e.getLocalizedMessage());
        LOGGER.warn("[{}] ------------------------- 参数转换异常 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.PARAMETER_EMPTY.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, errorMessage)
                .fluentPut(BaseVO.USER_TIP, e.getLocalizedMessage().equals(errorMessage) ? errorMessage : BASE_ERROR_MESSAGE);
    }

    /**
     * 验证异常全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestParam}</b></p>
     * <p>捕获来自 {@link javax.validation.constraints}</p>
     *
     * @param e        {@link javax.validation.ValidationException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(ValidationException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull ValidationException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 验证异常 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.warn("[{}] 验证异常 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]",
                LOG_TAG, uuid, request.getRequestURI(), request.getMethod(), e.getLocalizedMessage());
        List<String> userTipMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            userTipMessages.add(constraintViolation.getMessage());
        }
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "验证异常", null);
        LOGGER.warn("[{}] ------------------------- 验证异常 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, String.join(",", userTipMessages));
    }

    /**
     * 请求体异常全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestBody}</b></p>
     * <p>Also throw to {@link InvalidFormatException}</p>
     *
     * @param e        {@link org.springframework.http.converter.HttpMessageNotReadableException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     * @see com.fasterxml.jackson.databind.DeserializationContext#weirdStringException(String, Class, String)
     * <p>Enum deserialize failed exception is unsolved</p>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull HttpMessageNotReadableException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 请求体异常 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        String errorMessage = this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "请求体异常", e.getLocalizedMessage());
        LOGGER.warn("[{}] ------------------------- 请求体异常 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.PARAMETER_EMPTY.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, errorMessage)
                .fluentPut(BaseVO.USER_TIP, e.getLocalizedMessage().equals(errorMessage) ? errorMessage : BASE_ERROR_MESSAGE);
    }

    /**
     * 请求体验证全局捕获
     * <p>RequestType = <b>{@link org.springframework.web.bind.annotation.RequestBody}</b></p>
     * <p>捕获来自 {@link javax.validation.constraints}</p>
     *
     * @param e        {@link org.springframework.web.bind.MethodArgumentNotValidException}
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return 结果响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull MethodArgumentNotValidException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 请求体验证异常 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        List<String> errorMessages = new ArrayList<>();
        List<String> userTipMessages = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errorMessages.add(String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()));
            userTipMessages.add(fieldError.getDefaultMessage());
            LOGGER.warn("[{}] 请求体验证异常 [uuid: {}, requestUri: {}, requestMethod: {}, errorType: @{}, errorMessage: {}, field: {}#{}={}]",
                    LOG_TAG, uuid, request.getRequestURI(), request.getMethod(), fieldError.getCode(), fieldError.getDefaultMessage(),
                    e.getParameter().getParameterType().getName(), fieldError.getField(), fieldError.getRejectedValue());
        }
        LOGGER.warn("[{}] ------------------------- 请求体验证异常 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.INVALID_INPUT.value())
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
     */
    @ExceptionHandler(BindException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull BindException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 绑定异常 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.warn("[{}] 绑定异常 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]", LOG_TAG, uuid,
                request.getRequestURI(), request.getMethod(), e.getAllErrors().get(0).getDefaultMessage());
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "绑定异常", null);
        LOGGER.warn("[{}] ------------------------- 绑定异常 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.INVALID_INPUT.value())
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
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @NonNull
    public JSONObject validationExceptionHandler(@NonNull HttpRequestMethodNotSupportedException e,
                                                 @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        String uuid = UUIDUtils.Local.get();
        LOGGER.warn("[{}] ------------------------- 方法不被允许 ------------------------- [Begin - {}]", LOG_TAG, uuid);
        LOGGER.warn("[{}] 方法不被允许 [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]", LOG_TAG, uuid,
                request.getRequestURI(), request.getMethod(), e.getLocalizedMessage());
        this.printStackTraceFormat(request.getRequestURI(), request.getMethod(), e, e.getCause(), uuid, "方法不被允许", null);
        LOGGER.warn("[{}] ------------------------- 方法不被允许 ------------------------- [ End - {}]", LOG_TAG, uuid);
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return new JSONObject()
                .fluentPut(BaseVO.ERROR_CODE, RequestParamAbnormalEnum.INVALID_INPUT.value())
                .fluentPut(BaseVO.ERROR_MESSAGE, e.getLocalizedMessage())
                .fluentPut(BaseVO.USER_TIP, BASE_ERROR_MESSAGE);
    }

    /**
     * 格式化异常信息|堆栈异常打印
     * <p>
     * 为了保证输出安全, 如果为 {@link EnumIllegalArgumentException} 异常,
     * 则采用正则表达式 {@code "(\\[.*])\\s"} 来匹配 {@link pro.haichuang.framework.base.enums.BaseEnum#resolve(String, Class)} 中
     * 解析异常所抛出的 {@link EnumIllegalArgumentException} 异常信息, 将异常信息的全限定名进行删除后输出
     * </p>
     *
     * @param requestUri           请求地址
     * @param requestMethod        请求方法
     * @param tp                   {@param t} 父节点
     * @param t                    Throwable
     * @param uuid                 唯一标识
     * @param tag                  输出标签
     * @param originalErrorMessage 原始错误信息
     * @return 格式化后的错误信息
     */
    private String printStackTraceFormat(@NonNull String requestUri, @NonNull String requestMethod, @NonNull Throwable tp,
                                         @Nullable Throwable t, @NonNull String uuid, @NonNull String tag, @Nullable String originalErrorMessage) {
        if (t == null) {
            if (tp instanceof EnumIllegalArgumentException) {
                Matcher matcher = ENUM_ILLEGAL_ARGUMENT_PRINT_PATTERN.matcher(tp.getLocalizedMessage());
                originalErrorMessage = tp.getLocalizedMessage();
                if (matcher.find()) {
                    originalErrorMessage = originalErrorMessage.replace(matcher.group(), "");
                }
                LOGGER.warn("[{}] {} [uuid: {}, requestUri: {}, requestMethod: {}, errorMessage: {}]", LOG_TAG, tag, uuid,
                        requestUri, requestMethod, tp.getLocalizedMessage());
            } else {
                boolean isPrintStack = false;
                for (StackTraceElement stackTraceElement : tp.getStackTrace()) {
                    if (stackTraceElement.getClassName().contains("pro.haichuang")) {
                        LOGGER.warn("[{}] {} [uuid: {}, requestUri: {}, requestMethod: {}, stackTrace: {}]", LOG_TAG, tag, uuid,
                                requestUri, requestMethod, stackTraceElement);
                        isPrintStack = true;
                    }
                }
                if (!isPrintStack && originalErrorMessage != null) {
                    originalErrorMessage = originalErrorMessage.split(":")[0];
                }
            }
            return originalErrorMessage;
        } else {
            return this.printStackTraceFormat(requestUri, requestMethod, t, t.getCause(), uuid, tag, originalErrorMessage);
        }
    }
}
