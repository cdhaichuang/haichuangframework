package pro.haichuang.framework.sdk.chuanglansms.service;

import org.springframework.lang.NonNull;
import pro.haichuang.framework.sdk.chuanglansms.exception.ChuangLanSmsSendException;
import pro.haichuang.framework.sdk.chuanglansms.response.SendResponse;

/**
 * ChuangLanSmsService
 *
 * <p>该类为 {@code chuanglansms} 第三方操作核心接口, 项目中所有 {@code chuanglansms} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultChuangLanSmsServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @since 1.2.0.211209
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface ChuangLanSmsService {

    /**
     * 发送短信验证码
     *
     * @param signName     短信签名
     * @param message      最终发送的消息, 长度不超过 [536] 个字符
     * @param phoneNumbers 接收的手机号; 多个手机号使用英文逗号间隔, 一次不要超过 [1000] 个
     * @return 执行结果
     * @throws ChuangLanSmsSendException 创蓝短信发送异常
     * @since 1.2.0.211209
     */
    SendResponse send(@NonNull String signName, @NonNull String message, @NonNull String phoneNumbers)
            throws ChuangLanSmsSendException
    ;

    /**
     * 发送短信验证码
     *
     * @param message      最终发送的消息, 长度不超过 [536] 个字符
     * @param phoneNumbers 接收的手机号; 多个手机号使用英文逗号间隔, 一次不要超过 [1000] 个
     * @return 执行结果
     * @throws ChuangLanSmsSendException 创蓝短信发送异常
     * @since 1.2.0.211209
     */
    SendResponse send(@NonNull String message, @NonNull String phoneNumbers) throws ChuangLanSmsSendException;

    /**
     * 发送短信验证码
     *
     * @param phoneNumbers 接收的手机号; 多个手机号使用英文逗号间隔, 一次不要超过 [1000] 个
     * @return 执行结果
     * @throws ChuangLanSmsSendException 创蓝短信发送异常
     * @since 1.2.0.211209
     */
    SendResponse send(@NonNull String phoneNumbers) throws ChuangLanSmsSendException;

}
