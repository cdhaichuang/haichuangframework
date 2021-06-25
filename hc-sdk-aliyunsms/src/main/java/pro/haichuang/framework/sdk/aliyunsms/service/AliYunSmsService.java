package pro.haichuang.framework.sdk.aliyunsms.service;

import com.alibaba.fastjson.JSONObject;

/**
 * AliYunSmsService
 *
 * @author JiYinchuan
 * @version 1.0
 */
public interface AliYunSmsService {

    /**
     * 发送短信验证码
     *
     * @param signName      短信签名
     * @param templateCode  短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     * @param phoneNumbers  短信接收号码, 支持以逗号分隔的形式进行批量调用
     *                      批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     *                      发送国际/港澳台消息时, 接收号码格式为: 国际区号+号码, 如"85200000000"。
     * @param templateParam 短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     */
    boolean send(String signName, String templateCode,
                 String phoneNumbers, JSONObject templateParam);

    /**
     * 发送短信验证码
     *
     * @param templateCode  短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     * @param phoneNumbers  短信接收号码, 支持以逗号分隔的形式进行批量调用
     *                      批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     *                      发送国际/港澳台消息时, 接收号码格式为: 国际区号+号码, 如"85200000000"。
     * @param templateParam 短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     */
    boolean send(String templateCode, String phoneNumbers, JSONObject templateParam);

    /**
     * 发送短信验证码
     *
     * @param phoneNumbers  短信接收号码, 支持以逗号分隔的形式进行批量调用
     *                      批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
     *                      发送国际/港澳台消息时, 接收号码格式为: 国际区号+号码, 如"85200000000"。
     * @param templateParam 短信模板变量替换JSON串, 友情提示: 如果JSON中需要带换行符, 请参照标准的JSON协议
     * @return 执行结果
     */
    boolean send(String phoneNumbers, JSONObject templateParam);

}
