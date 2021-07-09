package pro.haichuang.framework.sdk.alipay.util;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.util.common.ValidateUtils;

/**
 * 支付宝支付工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class AliPayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayUtils.class);
    private static final String LOG_TAG = "AliPayUtils工具类";

    /**
     * 默认通信协议
     */
    private static final String DEFAULT_PROTOCOL = "https";

    /**
     * 默认网关域名
     */
    private static final String DEFAULT_GATEWAY_HOST = "openapi.alipay.com";

    /**
     * 默认沙箱网关域名
     */
    private static final String DEFAULT_SAND_BOX_GATEWAY_HOST = "openapi.alipaydev.com";

    /**
     * 默认签名类型
     */
    private static final String DEFAULT_SIGN_TYPE = "RSA2";

    /**
     * 是否初始化支付配置
     */
    private static boolean isInitOptions = false;

    public static void appCreate(String subject, String outTradeNo, String totalAmount) {
        ValidateUtils.validate(!isInitOptions, RequestParamErrorEnum.OPERATION_ABNORMAL, "");
        try {
            AlipayTradeAppPayResponse response = Factory.Payment.App().pay(subject, outTradeNo, totalAmount);
            if (ResponseChecker.success(response)) {
                LOGGER.info("[{}] APP创建交易成功 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                        subject, outTradeNo, totalAmount);
            } else {
                LOGGER.error("[{}] APP创建交易失败 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                        subject, outTradeNo, totalAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("[{}] APP创建交易未知异常 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                    subject, outTradeNo, totalAmount);
        }
    }

    /**
     * 设置客户端参数, 只需设置一次
     *
     * @param isSandBox          是否为沙箱环境
     * @param appid              AppId
     * @param alipayPublicKey    支付宝公钥
     * @param merchantPrivateKey 应用私钥
     * @param notifyUrl          异步通知回调地址
     * @param encryptKey         AES密钥
     */
    public static void setOptions(boolean isSandBox, String appid,
                                  String alipayPublicKey, String merchantPrivateKey,
                                  @Nullable String notifyUrl, @Nullable String encryptKey) {
        Config config = new Config();
        config.protocol = DEFAULT_PROTOCOL;
        config.appId = appid;
        config.gatewayHost = isSandBox ? DEFAULT_SAND_BOX_GATEWAY_HOST : DEFAULT_GATEWAY_HOST;
        // 设置客户端签名参数
        setSignParams(config, alipayPublicKey, merchantPrivateKey,
                null, null, null);
        config.notifyUrl = notifyUrl;
        config.encryptKey = encryptKey;
        Factory.setOptions(config);
        isInitOptions = true;
    }

    /**
     * 设置客户端参数, 只需设置一次
     *
     * @param isSandBox          是否为沙箱环境
     * @param appid              AppId
     * @param merchantPrivateKey 应用私钥
     * @param merchantCertPath   应用公钥证书文件路径
     * @param alipayCertPath     支付宝公钥证书文件路径
     * @param alipayRootCertPath 支付宝根证书文件路径
     * @param notifyUrl          异步通知回调地址
     * @param encryptKey         AES密钥
     */
    public static void setOptionsByCert(boolean isSandBox, String appid,
                                        String merchantPrivateKey, String merchantCertPath,
                                        String alipayCertPath, String alipayRootCertPath,
                                        @Nullable String notifyUrl, @Nullable String encryptKey) {
        Config config = new Config();
        config.protocol = DEFAULT_PROTOCOL;
        config.appId = appid;
        config.gatewayHost = isSandBox ? DEFAULT_SAND_BOX_GATEWAY_HOST : DEFAULT_GATEWAY_HOST;
        // 设置客户端签名参数
        setSignParams(config, null, merchantPrivateKey,
                merchantCertPath, alipayCertPath, alipayRootCertPath);
        config.notifyUrl = notifyUrl;
        config.encryptKey = encryptKey;
        Factory.setOptions(config);
        isInitOptions = true;
    }

    /**
     * 设置客户端签名参数
     *
     * @param config             Config配置
     * @param alipayPublicKey    支付宝公钥
     * @param merchantPrivateKey 应用私钥
     * @param merchantCertPath   应用公钥证书文件路径
     * @param alipayCertPath     支付宝公钥证书文件路径
     * @param alipayRootCertPath 支付宝根证书文件路径
     */
    private static void setSignParams(Config config, @Nullable String alipayPublicKey,
                                      String merchantPrivateKey, @Nullable String merchantCertPath,
                                      @Nullable String alipayCertPath, @Nullable String alipayRootCertPath) {
        // 签名类型, Alipay Easy SDK只推荐使用RSA2
        config.signType = DEFAULT_SIGN_TYPE;
        // 支付宝公钥
        config.alipayPublicKey = alipayPublicKey;
        // 应用私钥
        config.merchantPrivateKey = merchantPrivateKey;
        // 应用公钥证书文件路径
        config.merchantCertPath = merchantCertPath;
        // 支付宝公钥证书文件路径
        config.alipayCertPath = alipayCertPath;
        // 支付宝根证书文件路径
        config.alipayRootCertPath = alipayRootCertPath;
    }
}
