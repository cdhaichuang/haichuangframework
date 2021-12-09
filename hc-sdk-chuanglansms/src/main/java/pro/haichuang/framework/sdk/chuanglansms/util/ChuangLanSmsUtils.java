package pro.haichuang.framework.sdk.chuanglansms.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.sdk.chuanglansms.enums.success.ChuangLanSmsSendSuccessEnum;
import pro.haichuang.framework.sdk.chuanglansms.exception.ChuangLanSmsSendException;
import pro.haichuang.framework.sdk.chuanglansms.response.SendResponse;

import java.text.MessageFormat;
import java.util.StringJoiner;

/**
 * 创蓝短信工具类
 *
 * <p>该类为 {@code chuanglansms} 相关操作工具类, 提供了对 {@code chuanglansms} 相关操作的封装
 * <p><a href="https://www.chuanglan.com/document/6110e57909fd9600010209de/617264ea40ec34000109bcb2">点击查看官方文档</a>
 *
 * @author JiYinchuan
 * @since 1.2.0.211209
 */
public class ChuangLanSmsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChuangLanSmsUtils.class);
    private static final String LOG_TAG = "ChuangLanSms工具类";

    /**
     * 发送手机短信
     *
     * @param apiAccount   API账号, 管理后台获取
     * @param apiPassword  API密码, 管理后台获取 [8-16位]
     * @param host         请求HOST域名
     * @param signName     签名名称
     * @param message      最终发送的消息, 长度不超过 [536] 个字符
     * @param phoneNumbers 接收的手机号; 多个手机号使用英文逗号间隔, 一次不要超过 [1000] 个
     * @return 发送结果
     * @throws ChuangLanSmsSendException 创蓝短信发送异常
     * @since 1.2.0.211209
     */
    public static SendResponse send(@NonNull String apiAccount, @NonNull String apiPassword, @NonNull String host,
                                    @NonNull String signName, @NonNull String message,
                                    @NonNull String phoneNumbers) throws ChuangLanSmsSendException {
        String baseUrl = new StringJoiner("", "https://", "/msg/v1/send/json").add(host).toString();

        JSONObject params = new JSONObject();
        params.put("account", apiAccount);
        params.put("password", apiPassword);
        params.put("msg", MessageFormat.format("【{0}】{1}", signName, message));
        params.put("phone", phoneNumbers);

        String responseJson = HttpUtil.post(baseUrl, params.toJSONString());
        SendResponse response = JSONObject.parseObject(responseJson, SendResponse.class);

        if (!ChuangLanSmsSendSuccessEnum.SEND_SUCCESS.value().equals(response.getErrorCode())) {
            LOGGER.error("[{}] 发送验证码失败 [errorCode: {}, errorMessage: {}, message: {}, phoneNumbers: {}]",
                    LOG_TAG, response.getErrorCode(), response.getErrorMessage(), message, phoneNumbers);
            throw new ChuangLanSmsSendException(response.getErrorCode(), response.getErrorMessage());
        }
        return response;
    }
}
