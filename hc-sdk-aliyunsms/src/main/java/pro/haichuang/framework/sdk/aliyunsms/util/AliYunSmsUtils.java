package pro.haichuang.framework.sdk.aliyunsms.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsSendErrorEnum;
import pro.haichuang.framework.sdk.aliyunsms.exception.AliYunSmsSendException;

import java.util.List;

/**
 * 阿里云短信工具类
 *
 * <p>该类为 {@code aliyunsms} 相关操作工具类, 提供了对 {@code aliyunsms} 相关操作的封装
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
@SuppressWarnings("SpellCheckingInspection")
public class AliYunSmsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliYunSmsUtils.class);
    private static final String LOG_TAG = "AliYunSms工具类";

    /**
     * DefaultRegionId
     */
    private static final String REGION_ID = "cn-hangzhou";

    /**
     * DefaultSysDomain
     */
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * DefaultSysVersion
     */
    private static final String SYS_VERSION = "2017-05-25";

    /**
     * DefaultSysAction
     */
    private static final String SYS_ACTION = "SendSms";

    /**
     * 发送短信验证码
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param signName        短信签名
     * @param templateCode    短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     * @param phoneNumbers    短信接收号码, 支持以逗号分隔的形式进行批量调用
     *                        批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     *                        发送国际/港澳台消息时, 接收号码格式为: 国际区号+号码, 如"85200000000"
     * @param templateParam   短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     * @throws AliYunSmsSendException 阿里云短信发送异常
     * @since 1.0.0.211014
     */
    public static boolean send(String accessKeyId, String accessKeySecret,
                               String signName, String templateCode,
                               String phoneNumbers, JSONObject templateParam)
            throws AliYunSmsSendException {
        CommonRequest request = createRequest();
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam.toJSONString());
        return baseSend(accessKeyId, accessKeySecret, request);
    }

    /**
     * 批量发送短信验证码
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param signNames       短信签名, JSON格式
     * @param templateCode    短信模板ID
     * @param phones          短信接收号码, JSON格式, 批量上限为100个手机号码
     *                        批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     * @param templateParam   短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     * @throws AliYunSmsSendException 阿里云短信发送异常
     * @since 1.0.0.211014
     */
    public static boolean sendBatch(String accessKeyId, String accessKeySecret,
                                    List<String> signNames, String templateCode,
                                    List<String> phones, JSONArray templateParam)
            throws AliYunSmsSendException {
        CommonRequest request = createRequest();
        request.putQueryParameter("PhoneNumberJson", JSONObject.toJSONString(phones));
        request.putQueryParameter("SignNameJson", JSONObject.toJSONString(signNames));
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParamJson", templateParam.toJSONString());
        return baseSend(accessKeyId, accessKeySecret, request);
    }

    /**
     * 获取IAcsClient
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @return IAcsClient
     * @since 1.0.0.211014
     */
    private static IAcsClient getClient(String accessKeyId, String accessKeySecret) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 创建公共请求
     *
     * @return 公共请求
     * @since 1.0.0.211014
     */
    private static CommonRequest createRequest() {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SYS_ACTION);
        return request;
    }

    /**
     * 发送短信
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param request         CommonRequest
     * @return 执行结果
     * @throws AliYunSmsSendException 阿里云短信发送异常
     * @since 1.0.0.211014
     */
    private static boolean baseSend(String accessKeyId, String accessKeySecret, CommonRequest request)
            throws AliYunSmsSendException {
        try {
            getClient(accessKeyId, accessKeySecret).getCommonResponse(request);
            return true;
        } catch (ClientException e) {
            try {
                LOGGER.error("[{}] 发送验证码异常 [requestId: {}, errorCode: {}, errorMessage: {}, errorType: {}, errorDescription: {}]",
                        LOG_TAG, e.getRequestId(), e.getErrCode(), e.getErrMsg(), e.getErrorType(), e.getErrorDescription());
                throw new AliYunSmsSendException(AliYunSmsSendErrorEnum.parseCode(e.getErrCode()));
            } catch (EnumIllegalArgumentException e1) {
                throw new AliYunSmsSendException(AliYunSmsSendErrorEnum.UNKONE_ERROR);
            }
        }
    }
}
