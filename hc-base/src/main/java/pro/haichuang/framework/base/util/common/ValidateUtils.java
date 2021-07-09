package pro.haichuang.framework.base.util.common;

import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.error.client.*;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.base.exception.client.*;

/**
 * 验证工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class ValidateUtils {

    /**
     * 验证自定义异常
     *
     * @param isThrow  是否抛出异常
     * @param baseEnum 自定义异常枚举
     * @throws ApplicationException 自定义异常
     */
    public static void validate(boolean isThrow, BaseEnum baseEnum) throws ApplicationException {
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
     */
    public static void validate(boolean isThrow, BaseEnum baseEnum, String userTip) throws ApplicationException {
        if (isThrow) {
            throw new ApplicationException(baseEnum, userTip);
        }
    }


    /**
     * 验证访问权限异常
     *
     * @param isThrow            是否抛出异常
     * @param authorityErrorEnum 访问权限异常枚举
     * @throws AuthorityException 访问权限异常
     */
    public static void validate(boolean isThrow, AuthorityErrorEnum authorityErrorEnum) throws AuthorityException {
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
     * @throws AuthorityException 访问权限异常
     */
    public static void validate(boolean isThrow, AuthorityErrorEnum authorityErrorEnum, String userTip) throws AuthorityException {
        if (isThrow) {
            throw new AuthorityException(authorityErrorEnum, userTip);
        }
    }

    /**
     * 验证用户登录异常
     *
     * @param isThrow        是否抛出异常
     * @param loginErrorEnum 用户登录异常枚举
     * @throws LoginException 用户登录异常
     */
    public static void validate(boolean isThrow, LoginErrorEnum loginErrorEnum) throws LoginException {
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
     * @throws LoginException 用户登录异常
     */
    public static void validate(boolean isThrow, LoginErrorEnum loginErrorEnum, String userTip) throws LoginException {
        if (isThrow) {
            throw new LoginException(loginErrorEnum, userTip);
        }
    }

    /**
     * 验证注册异常
     *
     * @param isThrow           是否抛出异常
     * @param registerErrorEnum 注册异常枚举
     * @throws LoginException 注册异常
     */
    public static void validate(boolean isThrow, RegisterErrorEnum registerErrorEnum) throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerErrorEnum);
        }
    }

    /**
     * 验证注册异常
     *
     * @param isThrow           是否抛出异常
     * @param registerErrorEnum 注册异常枚举
     * @param userTip           用户提示
     * @throws LoginException 注册异常
     */
    public static void validate(boolean isThrow, RegisterErrorEnum registerErrorEnum, String userTip) throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerErrorEnum, userTip);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow               是否抛出异常
     * @param requestParamErrorEnum 请求参数异常
     * @throws RequestParamException 请求参数异常
     */
    public static void validate(boolean isThrow, RequestParamErrorEnum requestParamErrorEnum) throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamErrorEnum);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow               是否抛出异常
     * @param requestParamErrorEnum 请求参数异常
     * @param userTip               用户提示
     * @throws RequestParamException 请求参数异常
     */
    public static void validate(boolean isThrow, RequestParamErrorEnum requestParamErrorEnum, String userTip) throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamErrorEnum, userTip);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                是否抛出异常
     * @param requestServerErrorEnum 请求服务异常
     * @throws RequestServerException 请求服务异常
     */
    public static void validate(boolean isThrow, RequestServerErrorEnum requestServerErrorEnum) throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerErrorEnum);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                是否抛出异常
     * @param requestServerErrorEnum 请求服务异常
     * @param userTip                用户提示
     * @throws RequestServerException 请求服务异常
     */
    public static void validate(boolean isThrow, RequestServerErrorEnum requestServerErrorEnum, String userTip) throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerErrorEnum, userTip);
        }
    }
}
