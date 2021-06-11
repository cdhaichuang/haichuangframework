package pro.haichuang.framework.base.util.common;

import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.abnormal.client.*;
import pro.haichuang.framework.base.exception.*;
import pro.haichuang.framework.base.exception.client.*;

/**
 * 验证工具类
 *
 * @author JiYinchuan
 * @version 1.0
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
     * @param isThrow               是否抛出异常
     * @param authorityAbnormalEnum 访问权限异常枚举
     * @throws AuthorityException 访问权限异常
     */
    public static void validate(boolean isThrow, AuthorityAbnormalEnum authorityAbnormalEnum) throws AuthorityException {
        if (isThrow) {
            throw new AuthorityException(authorityAbnormalEnum);
        }
    }

    /**
     * 验证访问权限异常
     *
     * @param isThrow               是否抛出异常
     * @param authorityAbnormalEnum 访问权限异常枚举
     * @param userTip               用户提示
     * @throws AuthorityException 访问权限异常
     */
    public static void validate(boolean isThrow, AuthorityAbnormalEnum authorityAbnormalEnum, String userTip) throws AuthorityException {
        if (isThrow) {
            throw new AuthorityException(authorityAbnormalEnum, userTip);
        }
    }

    /**
     * 验证用户登录异常
     *
     * @param isThrow           是否抛出异常
     * @param loginAbnormalEnum 用户登录异常枚举
     * @throws LoginException 用户登录异常
     */
    public static void validate(boolean isThrow, LoginAbnormalEnum loginAbnormalEnum) throws LoginException {
        if (isThrow) {
            throw new LoginException(loginAbnormalEnum);
        }
    }

    /**
     * 验证用户登录异常
     *
     * @param isThrow           是否抛出异常
     * @param loginAbnormalEnum 用户登录异常枚举
     * @param userTip           用户提示
     * @throws LoginException 用户登录异常
     */
    public static void validate(boolean isThrow, LoginAbnormalEnum loginAbnormalEnum, String userTip) throws LoginException {
        if (isThrow) {
            throw new LoginException(loginAbnormalEnum, userTip);
        }
    }

    /**
     * 验证注册异常
     *
     * @param isThrow              是否抛出异常
     * @param registerAbnormalEnum 注册异常枚举
     * @throws LoginException 注册异常
     */
    public static void validate(boolean isThrow, RegisterAbnormalEnum registerAbnormalEnum) throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerAbnormalEnum);
        }
    }

    /**
     * 验证注册异常
     *
     * @param isThrow              是否抛出异常
     * @param registerAbnormalEnum 注册异常枚举
     * @param userTip              用户提示
     * @throws LoginException 注册异常
     */
    public static void validate(boolean isThrow, RegisterAbnormalEnum registerAbnormalEnum, String userTip) throws RegisterException {
        if (isThrow) {
            throw new RegisterException(registerAbnormalEnum, userTip);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow                  是否抛出异常
     * @param requestParamAbnormalEnum 请求参数异常
     * @throws RequestParamException 请求参数异常
     */
    public static void validate(boolean isThrow, RequestParamAbnormalEnum requestParamAbnormalEnum) throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamAbnormalEnum);
        }
    }

    /**
     * 验证请求参数异常
     *
     * @param isThrow                  是否抛出异常
     * @param requestParamAbnormalEnum 请求参数异常
     * @param userTip                  用户提示
     * @throws RequestParamException 请求参数异常
     */
    public static void validate(boolean isThrow, RequestParamAbnormalEnum requestParamAbnormalEnum, String userTip) throws RequestParamException {
        if (isThrow) {
            throw new RequestParamException(requestParamAbnormalEnum, userTip);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                   是否抛出异常
     * @param requestServerAbnormalEnum 请求服务异常
     * @throws RequestServerException 请求服务异常
     */
    public static void validate(boolean isThrow, RequestServerAbnormalEnum requestServerAbnormalEnum) throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerAbnormalEnum);
        }
    }

    /**
     * 验证请求服务异常
     *
     * @param isThrow                   是否抛出异常
     * @param requestServerAbnormalEnum 请求服务异常
     * @param userTip                   用户提示
     * @throws RequestServerException 请求服务异常
     */
    public static void validate(boolean isThrow, RequestServerAbnormalEnum requestServerAbnormalEnum, String userTip) throws RequestServerException {
        if (isThrow) {
            throw new RequestServerException(requestServerAbnormalEnum, userTip);
        }
    }
}
