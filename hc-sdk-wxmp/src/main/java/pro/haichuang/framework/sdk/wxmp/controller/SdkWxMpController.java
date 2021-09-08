package pro.haichuang.framework.sdk.wxmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.haichuang.framework.sdk.wxmp.service.WxMpService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * 该类默认提供微信服务器验证模版接口, 用于自动验证服务器
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@RequestMapping("/framework/sdk/wxmp")
@RestController
@Validated
public class SdkWxMpController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 验证微信消息
     *
     * @param signature 微信加密签名, signature结合了开发者填写的token参数和请求中的timestamp参数/nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   请求响应值
     * @param response  HttpServletResponse
     * @since 1.0.0
     */
    @GetMapping("/authorization")
    @SuppressWarnings("SpellCheckingInspection")
    public void verifyWxMessage(@NotBlank String signature, @NotBlank String timestamp, @NotBlank String nonce,
                                @NotBlank String echostr, HttpServletResponse response) {
        wxMpService.verifyWxMessage(signature, timestamp, nonce, echostr, response);
    }
}
