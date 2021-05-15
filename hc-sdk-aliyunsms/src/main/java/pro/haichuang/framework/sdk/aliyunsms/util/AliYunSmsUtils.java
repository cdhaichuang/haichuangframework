package pro.haichuang.framework.sdk.aliyunsms.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.exception.ThirdPartyException;

import java.util.List;

/**
 * 阿里云短信工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
public class AliYunSmsUtils {

    private static final Logger logger = LoggerFactory.getLogger(AliYunSmsUtils.class);
    private static final String LOG_TAG = "AliYunSms工具类";

    /**
     * 发送短信验证码
     *
     * @param regionId        RegionId
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param signName        短信签名
     * @param templateCode    短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     * @param phoneNumbers    短信接收号码, 支持以逗号分隔的形式进行批量调用, 批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式; 发送国际/港澳台消息时, 接收号码格式为: 国际区号+号码, 如"85200000000"。
     * @param templateParam   短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     */
    public static boolean send(@NonNull String regionId, @NonNull String accessKeyId, @NonNull String accessKeySecret,
                               @NonNull String signName, @NonNull String templateCode, @NonNull String phoneNumbers, @NonNull JSONObject templateParam) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumbers);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam.toJSONString());
        try {
            SendSmsResponse response = getClient(regionId, accessKeyId, accessKeySecret).getAcsResponse(request);
            if (!response.getCode().equals(HttpStatus.OK.name())) {
                logger.error("[{}] 发送验证码异常 [errorCode: {}, errorMessage: {}]", LOG_TAG, response.getCode(), response.getMessage());
                throw new ThirdPartyException(response.getCode(), response.getMessage());
            }
            return true;
        } catch (ClientException e) {
            logger.error("[{}] 发送验证码异常 [errorCode: {}, errorMessage: {}]", LOG_TAG, e.getErrCode(), e.getErrMsg());
            throw new ThirdPartyException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 批量发送短信验证码
     *
     * @param regionId        RegionId
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param signNames       短信签名, JSON格式
     * @param templateCode    短信模板ID
     * @param phones          短信接收号码, JSON格式, 批量上限为100个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     * @param templateParam   短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     */
    public static boolean sendBatch(@NonNull String regionId, @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                    @NonNull List<String> signNames, @NonNull String templateCode, @NonNull List<String> phones, @NonNull JSONArray templateParam) {
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        request.setPhoneNumberJson(JSONObject.toJSONString(phones));
        request.setSignNameJson(JSONObject.toJSONString(signNames));
        request.setTemplateCode(templateCode);
        request.setTemplateParamJson(templateParam.toJSONString());
        try {
            SendBatchSmsResponse response = getClient(regionId, accessKeyId, accessKeySecret).getAcsResponse(request);
            if (!response.getCode().equals(HttpStatus.OK.name())) {
                logger.error("[{}] 批量发送验证码异常 [errorCode: {}, errorMessage: {}]", LOG_TAG, response.getCode(), response.getMessage());
                throw new ThirdPartyException(response.getCode(), response.getMessage());
            }
            return true;
        } catch (ClientException e) {
            logger.error("[{}] 批量发送验证码异常 [errorCode: {}, errorMessage: {}]", LOG_TAG, e.getErrCode(), e.getErrMsg());
            throw new ThirdPartyException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 获取IAcsClient
     *
     * @param regionId        RegionId
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @return IAcsClient
     */
    @NonNull
    private static IAcsClient getClient(@NonNull String regionId, @NonNull String accessKeyId, @NonNull String accessKeySecret) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(regionId, "Dysmsapi", "dysmsapi.aliyuncs.com");
        return new DefaultAcsClient(profile);
    }
}
