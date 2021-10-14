package pro.haichuang.framework.base.util.common;

import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.error.client.*;
import pro.haichuang.framework.base.enums.error.server.*;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.base.exception.client.*;
import pro.haichuang.framework.base.exception.server.*;

/**
 * 验证工具类
 *
 * <p>该类主要用于验证参数是否符合要求, 如果不符合则抛出指定异常, 避免重复性写大量 {@code if/else} 语句
 * <hr>
 * <ul>
 *     <li>客户端异常枚举包: {@link pro.haichuang.framework.base.enums.error.client}</li>
 *     <li>客户端自定义异常包: {@link pro.haichuang.framework.base.exception.client}</li>
 *     <li>服务端异常枚举包: {@link pro.haichuang.framework.base.enums.error.server}</li>
 *     <li>服务端自定义异常包: {@link pro.haichuang.framework.base.exception.server}</li>
 * </ul>
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see pro.haichuang.framework.base.enums.error.client 客户端异常枚举包
 * @see pro.haichuang.framework.base.exception.client 客户端自定义异常包
 * @see pro.haichuang.framework.base.enums.error.server 服务端异常枚举包
 * @see pro.haichuang.framework.base.exception.server 服务端自定义异常包
 * @since 1.0.0.211009
 */
public class ValidateUtils {

    // ============================= Base ============================

    /**
     * 验证自定义异常
     *
     * @param isThrow  是否抛出异常
     * @param baseEnum 自定义异常枚举
     * @throws ApplicationException 自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow, @NonNull BaseEnum baseEnum)
            throws ApplicationException {
        if (isThrow) {
            throw new ApplicationException(baseEnum);
        }
    }

    /**
     * 验证自定义异常
     *
     * @param isThrow  是否抛出异常
     * @param baseEnum 自定义异常枚举
     * @param userTip  用户提示
     * @throws ApplicationException 自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull BaseEnum baseEnum, @NonNull String userTip)
            throws ApplicationException {
        if (isThrow) {
            throw new ApplicationException(baseEnum, userTip);
        }
    }

    // ============================= Client ============================

    /**
     * 验证访问权限异常
     *
     * @param isThrow            是否抛出异常
     * @param authorityErrorEnum 访问权限异常枚举
     * @throws AuthorityException 访问权限自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull AuthorityErrorEnum authorityErrorEnum)
            throws AuthorityException {
        if (isThrow) {
            throw new AuthorityException(authorityErrorEnum);
        }
    }

    /**
     * 验证访问权限异常
     *
     * @param isThrow            是否抛出异常
     * @param authorityErrorEnum 访问权限异常枚举
     * @param userTip            用户提示
     * @throws AuthorityException 访问权限自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull AuthorityErrorEnum authorityErrorEnum, @NonNull String userTip)
            throws AuthorityException {
        if (isThrow) {
            throw new AuthorityException(authorityErrorEnum, userTip);
        }
    }

    /**
     * 验证用户登录异常
     *
     * @param isThrow        是否抛出异常
     * @param loginErrorEnum 用户登录异常枚举
     * @throws LoginException 用户登录自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull LoginErrorEnum loginErrorEnum)
            throws LoginException {
        if (isThrow) {
            throw new LoginException(loginErrorEnum);
        }
    }

    /**
     * 验证用户登录异常
     *
     * @param isThrow        是否抛出异常
     * @param loginErrorEnum 用户登录异常枚举
     * @param userTip        用户提示
     * @throws LoginException 用户登录自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull LoginErrorEnum loginErrorEnum, @NonNull String userTip)
            throws LoginException {
        if (isThrow) {
            throw new LoginException(loginErrorEnum, userTip);
        }
    }

    /**
     * 验证用户注册异常
     *
     * @param isThrow           是否抛出异常
     * @param registerErrorEnum 用户注册异常枚举
     * @throws RegisterException 用户注册自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RegisterErrorEnum registerErrorEnum)
            throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerErrorEnum);
        }
    }

    /**
     * 验证用户注册异常
     *
     * @param isThrow           是否抛出异常
     * @param registerErrorEnum 用户注册异常枚举
     * @param userTip           用户提示
     * @throws RegisterException 用户注册自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RegisterErrorEnum registerErrorEnum, @NonNull String userTip)
            throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerErrorEnum, userTip);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow               是否抛出异常
     * @param requestParamErrorEnum 请求参数异常枚举
     * @throws RequestParamException 请求参数自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RequestParamErrorEnum requestParamErrorEnum)
            throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamErrorEnum);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow               是否抛出异常
     * @param requestParamErrorEnum 请求参数异常枚举
     * @param userTip               用户提示
     * @throws RequestParamException 请求参数自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RequestParamErrorEnum requestParamErrorEnum, @NonNull String userTip)
            throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamErrorEnum, userTip);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                是否抛出异常
     * @param requestServerErrorEnum 请求服务异常枚举
     * @throws RequestServerException 请求服务自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RequestServerErrorEnum requestServerErrorEnum)
            throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerErrorEnum);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                是否抛出异常
     * @param requestServerErrorEnum 请求服务异常枚举
     * @param userTip                用户提示
     * @throws RequestServerException 请求服务自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull RequestServerErrorEnum requestServerErrorEnum, @NonNull String userTip)
            throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerErrorEnum, userTip);
        }
    }

    /**
     * 验证用户资源异常
     *
     * @param isThrow           是否抛出异常
     * @param resourceErrorEnum 用户资源异常枚举
     * @throws pro.haichuang.framework.base.exception.client.ResourceException 用户资源自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull pro.haichuang.framework.base.enums.error.client.ResourceErrorEnum resourceErrorEnum)
            throws pro.haichuang.framework.base.exception.client.ResourceException {
        if (isThrow) {
            throw new pro.haichuang.framework.base.exception.client.ResourceException(resourceErrorEnum);
        }
    }

    /**
     * 验证用户资源异常
     *
     * @param isThrow           是否抛出异常
     * @param resourceErrorEnum 用户资源异常枚举
     * @param userTip           用户提示
     * @throws pro.haichuang.framework.base.exception.client.ResourceException 用户资源自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull pro.haichuang.framework.base.enums.error.client.ResourceErrorEnum resourceErrorEnum,
                                @NonNull String userTip) throws pro.haichuang.framework.base.exception.client.ResourceException {
        if (isThrow) {
            throw new pro.haichuang.framework.base.exception.client.ResourceException(resourceErrorEnum, userTip);
        }
    }

    /**
     * 验证用户上传文件异常
     *
     * @param isThrow             是否抛出异常
     * @param uploadFileErrorEnum 用户上传文件异常枚举
     * @throws UploadFileException 用户上传文件自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull UploadFileErrorEnum uploadFileErrorEnum)
            throws UploadFileException {
        if (isThrow) {
            throw new UploadFileException(uploadFileErrorEnum);
        }
    }

    /**
     * 验证用户上传文件异常
     *
     * @param isThrow             是否抛出异常
     * @param uploadFileErrorEnum 用户上传文件异常枚举
     * @param userTip             用户提示
     * @throws UploadFileException 用户上传文件自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull UploadFileErrorEnum uploadFileErrorEnum, @NonNull String userTip)
            throws UploadFileException {
        if (isThrow) {
            throw new UploadFileException(uploadFileErrorEnum, userTip);
        }
    }

    /**
     * 验证用户当前版本异常
     *
     * @param isThrow          是否抛出异常
     * @param versionErrorEnum 用户当前版本异常枚举
     * @throws VersionException 用户当前版本自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull VersionErrorEnum versionErrorEnum)
            throws VersionException {
        if (isThrow) {
            throw new VersionException(versionErrorEnum);
        }
    }

    /**
     * 验证用户当前版本异常
     *
     * @param isThrow          是否抛出异常
     * @param versionErrorEnum 用户当前版本异常枚举
     * @param userTip          用户提示
     * @throws VersionException 用户当前版本自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull VersionErrorEnum versionErrorEnum, @NonNull String userTip)
            throws VersionException {
        if (isThrow) {
            throw new VersionException(versionErrorEnum, userTip);
        }
    }

    /**
     * 验证用户隐私未授权异常
     *
     * @param isThrow          是否抛出异常
     * @param privacyErrorEnum 用户隐私未授权异常枚举
     * @throws PrivacyException 用户隐私未授权自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull PrivacyErrorEnum privacyErrorEnum)
            throws PrivacyException {
        if (isThrow) {
            throw new PrivacyException(privacyErrorEnum);
        }
    }

    /**
     * 验证用户隐私未授权异常
     *
     * @param isThrow          是否抛出异常
     * @param privacyErrorEnum 用户隐私未授权异常枚举
     * @param userTip          用户提示
     * @throws PrivacyException 用户隐私未授权自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull PrivacyErrorEnum privacyErrorEnum, @NonNull String userTip)
            throws PrivacyException {
        if (isThrow) {
            throw new PrivacyException(privacyErrorEnum, userTip);
        }
    }

    /**
     * 验证用户设备异常
     *
     * @param isThrow         是否抛出异常
     * @param deviceErrorEnum 用户设备异常枚举
     * @throws DeviceException 用户设备自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull DeviceErrorEnum deviceErrorEnum)
            throws DeviceException {
        if (isThrow) {
            throw new DeviceException(deviceErrorEnum);
        }
    }

    /**
     * 验证用户设备异常
     *
     * @param isThrow         是否抛出异常
     * @param deviceErrorEnum 用户设备异常枚举
     * @param userTip         用户提示
     * @throws DeviceException 用户设备自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull DeviceErrorEnum deviceErrorEnum, @NonNull String userTip)
            throws DeviceException {
        if (isThrow) {
            throw new DeviceException(deviceErrorEnum, userTip);
        }
    }

    // ============================= Server ============================

    /**
     * 验证系统执行超时异常
     *
     * @param isThrow            是否抛出异常
     * @param executionErrorEnum 系统执行超时异常枚举
     * @throws ExecutionException 系统执行超时自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull ExecutionErrorEnum executionErrorEnum)
            throws ExecutionException {
        if (isThrow) {
            throw new ExecutionException(executionErrorEnum);
        }
    }

    /**
     * 验证系统执行超时异常
     *
     * @param isThrow            是否抛出异常
     * @param executionErrorEnum 系统执行超时异常枚举
     * @param userTip            用户提示
     * @throws ExecutionException 系统执行超时自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull ExecutionErrorEnum executionErrorEnum, @NonNull String userTip)
            throws ExecutionException {
        if (isThrow) {
            throw new ExecutionException(executionErrorEnum, userTip);
        }
    }

    /**
     * 验证系统容灾功能被触发异常
     *
     * @param isThrow                   是否抛出异常
     * @param disasterRecoveryErrorEnum 系统容灾功能被触发异常枚举
     * @throws DisasterRecoveryException 系统容灾功能被触发自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull DisasterRecoveryErrorEnum disasterRecoveryErrorEnum)
            throws DisasterRecoveryException {
        if (isThrow) {
            throw new DisasterRecoveryException(disasterRecoveryErrorEnum);
        }
    }

    /**
     * 验证系统容灾功能被触发异常
     *
     * @param isThrow                   是否抛出异常
     * @param disasterRecoveryErrorEnum 系统容灾功能被触发异常枚举
     * @param userTip                   用户提示
     * @throws DisasterRecoveryException 系统容灾功能被触发自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull DisasterRecoveryErrorEnum disasterRecoveryErrorEnum, @NonNull String userTip)
            throws DisasterRecoveryException {
        if (isThrow) {
            throw new DisasterRecoveryException(disasterRecoveryErrorEnum, userTip);
        }
    }

    /**
     * 验证系统资源异常
     *
     * @param isThrow           是否抛出异常
     * @param resourceErrorEnum 系统资源异常枚举
     * @throws pro.haichuang.framework.base.exception.server.ResourceException 系统资源自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull pro.haichuang.framework.base.enums.error.server.ResourceErrorEnum resourceErrorEnum)
            throws pro.haichuang.framework.base.exception.server.ResourceException {
        if (isThrow) {
            throw new pro.haichuang.framework.base.exception.server.ResourceException(resourceErrorEnum);
        }
    }

    /**
     * 验证系统资源异常
     *
     * @param isThrow           是否抛出异常
     * @param resourceErrorEnum 系统资源异常枚举
     * @param userTip           用户提示
     * @throws pro.haichuang.framework.base.exception.server.ResourceException 系统资源自定义异常
     * @since 1.0.0.211009
     */
    public static void validate(boolean isThrow,
                                @NonNull pro.haichuang.framework.base.enums.error.server.ResourceErrorEnum resourceErrorEnum,
                                @NonNull String userTip) throws pro.haichuang.framework.base.exception.server.ResourceException {
        if (isThrow) {
            throw new pro.haichuang.framework.base.exception.server.ResourceException(resourceErrorEnum, userTip);
        }
    }
}
