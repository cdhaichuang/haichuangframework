package pro.haichuang.framework.sdk.huaweicloudsms.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.sdk.huaweicloudsms.response.SendResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

/**
 * 华为云SMS工具类
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
     * 成功状态码
     */
    private static final String SUCCESS_CODE = "000000";

    public static void main(String[] args) {
        List<SendResponse.Result> data = send("https://rtcsms.cn-north-1.myhuaweicloud.com:10743",
                "2d60t967IL3c6EU6bm3gTzI4z478",
                "6j7F1Yx4843LsUXnvcufU1K40Wwn",
                "中鸿商业保理",
                "8821081732829",
                "15181743604",
                "9f39c42b030e403faabbf30f25fdd1ab",
                new JSONArray().fluentAdd(String.valueOf(RandomUtils.nextInt(100000, 1000000))));
        System.out.println(data);
        // String data = "{\n" +
        //         "    \"result\": [{\n" +
        //         "            \"originTo\": \"15181743604\",\n" +
        //         "            \"createTime\": \"2021-10-19T03:24:22Z\",\n" +
        //         "            \"from\": \"8821081732829\",\n" +
        //         "            \"smsMsgId\": \"c0a75b77-c080-45ef-9378-a36d183c006a_5056438028\",\n" +
        //         "            \"status\": \"000000\"\n" +
        //         "        }\n" +
        //         "    ],\n" +
        //         "    \"code\": \"000000\",\n" +
        //         "    \"description\": \"Success\"\n" +
        //         "}";
        // SendResponse sendResultResponse = JSONObject.parseObject(data, SendResponse.class);
        // System.out.println(sendResultResponse);
    }

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
     */
    public static List<SendResponse.Result> send(String url, String appKey, String appSecret,
                                                 String signName, String channelNumber,
                                                 String phoneNumbers, String templateId, @Nullable JSONArray templateParams)
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

        if (!SUCCESS_CODE.equals(response.getErrorCode())) {
            LOGGER.error("[{}] 发送验证码失败 [errorCode: {}, errorMessage: {}, phoneNumbers: {}, templateId: {}, templateParams: {}]",
                    LOG_TAG, response.getErrorCode(), response.getErrorMessage(), phoneNumbers, templateId, templateParams);
            throw new ThirdPartyException(response.getErrorCode(), response.getErrorMessage());
        }
        for (SendResponse.Result result : response.getContent()) {
            if (!SUCCESS_CODE.equals(result.getStatus())) {
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
    public static String buildWsseHeader(String appKey, String appSecret) {
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
