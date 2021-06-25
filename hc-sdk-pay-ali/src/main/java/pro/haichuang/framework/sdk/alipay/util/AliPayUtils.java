package pro.haichuang.framework.sdk.alipay.util;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支付宝支付工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class AliPayUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayUtils.class);
    private static final String LOG_TAG = "AliPayUtils工具类";

    public static void appCreate(String subject, String outTradeNo, String totalAmount) {
        try {
            AlipayTradeAppPayResponse response = Factory.Payment.App().pay(subject, outTradeNo, totalAmount);
            if (ResponseChecker.success(response)) {
                LOGGER.info("[{}] APP创建交易成功 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                        subject, outTradeNo, totalAmount);
            }else {
                LOGGER.error("[{}] APP创建交易失败 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                        subject, outTradeNo, totalAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("[{}] APP创建交易未知异常 [subject: {}, outTradeNo: {}, totalAmount: {}]", LOG_TAG,
                    subject, outTradeNo, totalAmount);
        }
    }
}
