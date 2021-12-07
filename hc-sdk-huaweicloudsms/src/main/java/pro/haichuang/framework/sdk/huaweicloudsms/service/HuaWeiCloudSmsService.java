package pro.haichuang.framework.sdk.huaweicloudsms.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.sdk.huaweicloudsms.config.properties.HuaWeiCloudSmsProperties;
import pro.haichuang.framework.sdk.huaweicloudsms.response.SendResponse;

import java.util.List;

/**
 * 华为云短信 Service
 *
 * <p>该类为 {@code huaweicloudsms} 第三方操作核心接口, 项目中所有 {@code huaweicloudsms} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultHuaWeiCloudSmsServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @see DefaultHuaWeiCloudSmsServiceImpl
 * @since 1.1.0.211021
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface HuaWeiCloudSmsService {

    /**
     * 发送短信
     *
     * @param signName       短信签名
     * @param channelNumber  通道号
     * @param templateId     短信模板ID
     * @param phoneNumbers   短信接收号码, 标准号码格式为: {@code +{国家码}{地区码}{终端号码}}
     *                       <ul>
     *                           <li>
     *                               <p>发送国内短信, 如果 {@code +国家码} 不存在, 则默认为 [+86], 如果接收方号码为手机号码, 则 [地区码] 可选
     *                               <p>如: +8613112345678
     *                           </li>
     *                           <li>
     *                               <p>发送国际/港澳台短信: 不区分接收号码类型, 所填号码都必须符合标准号码格式
     *                               <p>示例: +2412000000 (加蓬号码)
     *                           </li>
     *                       </ul>
     *                       <p>如果携带多个接收方号码, 则以英文逗号分隔。每个号码最大长度为21位, 最多允许携带1000个号码
     * @param templateParams 短信模板的变量值列表, 用于依次填充 {@code templateId} 参数指定的模板内容中的变量
     *                       <p>列表中变量值的个数及长度必须和 {@code templateId} 对应模板内容中定义的变量个数及长度保持一致,
     *                       例如 {@code templateId} 对应的模板内容有 [2] 个变量且变量长度分别为 [5] 和 [6],
     *                       则此处需要设置 [2] 个变量值且内容长度分别小于等于 [5] 和 [6]
     * @return 执行结果
     * @since 1.1.0.211021
     */
    List<SendResponse.Result> send(String signName, String channelNumber, String templateId,
                                   String phoneNumbers, @Nullable JSONArray templateParams);

    /**
     * 发送短信
     *
     * <p>{@code signName} / {@code channelNumber} 参数将使用 {@link HuaWeiCloudSmsProperties} 中的值
     *
     * @param templateId     短信模板ID
     * @param phoneNumbers   短信接收号码, 标准号码格式为: {@code +{国家码}{地区码}{终端号码}}
     *                       <ul>
     *                           <li>
     *                               <p>发送国内短信, 如果 {@code +国家码} 不存在, 则默认为 [+86], 如果接收方号码为手机号码, 则 [地区码] 可选
     *                               <p>如: +8613112345678
     *                           </li>
     *                           <li>
     *                               <p>发送国际/港澳台短信: 不区分接收号码类型, 所填号码都必须符合标准号码格式
     *                               <p>示例: +2412000000 (加蓬号码)
     *                           </li>
     *                       </ul>
     *                       <p>如果携带多个接收方号码, 则以英文逗号分隔。每个号码最大长度为21位, 最多允许携带1000个号码
     * @param templateParams 短信模板的变量值列表, 用于依次填充 {@code templateId} 参数指定的模板内容中的变量
     *                       <p>列表中变量值的个数及长度必须和 {@code templateId} 对应模板内容中定义的变量个数及长度保持一致,
     *                       例如 {@code templateId} 对应的模板内容有 [2] 个变量且变量长度分别为 [5] 和 [6],
     *                       则此处需要设置 [2] 个变量值且内容长度分别小于等于 [5] 和 [6]
     * @return 执行结果
     * @since 1.1.0.211021
     */
    List<SendResponse.Result> send(String templateId,
                                   String phoneNumbers, @Nullable JSONArray templateParams);

    /**
     * 发送短信
     *
     * <p>{@code signName} / {@code channelNumber} / {@code templateId} 参数将使用 {@link HuaWeiCloudSmsProperties} 中的值
     *
     * @param phoneNumbers   短信接收号码, 标准号码格式为: {@code +{国家码}{地区码}{终端号码}}
     *                       <ul>
     *                           <li>
     *                               <p>发送国内短信, 如果 {@code +国家码} 不存在, 则默认为 [+86], 如果接收方号码为手机号码, 则 [地区码] 可选
     *                               <p>如: +8613112345678
     *                           </li>
     *                           <li>
     *                               <p>发送国际/港澳台短信: 不区分接收号码类型, 所填号码都必须符合标准号码格式
     *                               <p>示例: +2412000000 (加蓬号码)
     *                           </li>
     *                       </ul>
     *                       <p>如果携带多个接收方号码, 则以英文逗号分隔。每个号码最大长度为21位, 最多允许携带1000个号码
     * @param templateParams 短信模板的变量值列表, 用于依次填充 {@code templateId} 参数指定的模板内容中的变量
     *                       <p>列表中变量值的个数及长度必须和 {@code templateId} 对应模板内容中定义的变量个数及长度保持一致,
     *                       例如 {@code templateId} 对应的模板内容有 [2] 个变量且变量长度分别为 [5] 和 [6],
     *                       则此处需要设置 [2] 个变量值且内容长度分别小于等于 [5] 和 [6]
     * @return 执行结果
     * @since 1.1.0.211021
     */
    List<SendResponse.Result> send(String phoneNumbers, @Nullable JSONArray templateParams);

}
