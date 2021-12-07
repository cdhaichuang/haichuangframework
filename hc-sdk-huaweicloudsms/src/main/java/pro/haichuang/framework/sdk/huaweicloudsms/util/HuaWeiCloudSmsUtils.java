package pro.haichuang.framework.sdk.huaweicloudsms.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.success.HuaWeiCloudSmsSendSuccessEnum;
import pro.haichuang.framework.sdk.huaweicloudsms.response.SendResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

/**
 * 华为云短信工具类
 *
 * <p>该类为 {@code huaweicloudsms} 相关操作工具类, 提供了对 {@code huaweicloudsms} 相关操作的封装
 * <p><a href="https://support.huaweicloud.com/api-msgsms/sms_05_0005.html">点击查看官方文档</a>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class HuaWeiCloudSmsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HuaWeiCloudSmsUtils.class);
    private static final String LOG_TAG = "sdk-huaweicloudsms-util";

    /**
     * 发送手机短信
     *
     * @param url            APP接入地址
     * @param appKey         AppKey
     * @param appSecret      AppSecret
     * @param signName       签名名称
     * @param channelNumber  通道号
     * @param phoneNumbers   发送的手机号
     * @param templateId     模版ID
     * @param templateParams 模版参数
     * @return 发送结果
     * @throws ThirdPartyException 发送验证码失败|发送验证码状态错误
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<SendResponse.Result> send(@NonNull String url, @NonNull String appKey, @NonNull String appSecret,
                                                 @NonNull String signName, @NonNull String channelNumber,
                                                 @NonNull String phoneNumbers, @NonNull String templateId, @Nullable JSONArray templateParams)
            throws ThirdPartyException {
        String baseUrl = url + "/sms/batchSendSms/v1";

        String contentTypeHeader = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String authorizationHeader = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";
        String xWsseHeader = buildWsseHeader(appKey, appSecret);

        JSONObject params = new JSONObject();
        params.put("from", channelNumber);
        params.put("to", phoneNumbers);
        params.put("templateId", templateId);
        if (templateParams != null && templateParams.size() != 0) {
            params.put("templateParas", templateParams.toJSONString());
        }
        params.put("signature", signName);

        String fullUrl = HttpUtil.urlWithForm(baseUrl, params.getInnerMap(), StandardCharsets.UTF_8, true);
        String responseJson = HttpUtil.createPost(fullUrl)
                .header(HttpHeaders.CONTENT_TYPE, contentTypeHeader)
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .header("X-WSSE", xWsseHeader)
                .execute().body();
        SendResponse response = JSONObject.parseObject(responseJson, SendResponse.class);

        if (!HuaWeiCloudSmsSendSuccessEnum.SEND_SUCCESS.value().equals(response.getErrorCode())) {
            LOGGER.error("[{}] 发送验证码失败 [errorCode: {}, errorMessage: {}, phoneNumbers: {}, templateId: {}, templateParams: {}]",
                    LOG_TAG, response.getErrorCode(), response.getErrorMessage(), phoneNumbers, templateId, templateParams);
            throw new ThirdPartyException(response.getErrorCode(), response.getErrorMessage());
        }
        for (SendResponse.Result result : response.getContent()) {
            if (null != result.getStatus() && !HuaWeiCloudSmsSendSuccessEnum.SEND_SUCCESS.value().equals(result.getStatus())) {
                LOGGER.error("[{}] 发送验证码状态错误 [errorStatus: {}, phoneNumbers: {}, templateId: {}, templateParams: {}]",
                        LOG_TAG, result.getStatus(), phoneNumbers, templateId, templateParams);
                throw new ThirdPartyException(result.getStatus(), "发送验证码状态错误");
            }
        }

        return response.getContent();
    }

    /**
     * 构造X-WSSE参数值
     *
     * @param appKey    AppKey
     * @param appSecret AppSecret
     * @return X-WSSE
     * @since 1.1.0.211021
     */
    @SneakyThrows
    @NonNull
    public static String buildWsseHeader(@NonNull String appKey, @NonNull String appSecret) {
        String time = DateTimeFormatter.ofPattern(DatePattern.UTC_PATTERN).format(LocalDateTime.now());
        String nonce = UUIDUtils.random();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((nonce + time + appSecret).getBytes());

        byte[] passwordDigest = md.digest();
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(passwordDigest);

        return String.format("UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"",
                appKey, passwordDigestBase64Str, nonce, time);
    }
}
