package pro.haichuang.framework.sdk.chuanglansms.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创蓝短信基础响应
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class SendResponse implements Serializable {
    private static final long serialVersionUID = -8417816479642697185L;

    /**
     * 消息ID
     */
    @JSONField(name = "msgId")
    @JsonProperty("msgId")
    private String smsMessageId;

    /**
     * 创建时间
     */
    @JSONField(name = "time")
    @JsonProperty("time")
    private LocalDateTime responseTime;

    /**
     * 错误码
     *
     * <p><a href="https://www.chuanglan.com/document/6110e57909fd9600010209de/616f7d3440ec34000109bca3">点击查看完整错误码</a>
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    private String errorCode;

    /**
     * 错误信息
     */
    @JSONField(name = "errorMsg")
    @JsonProperty("errorMsg")
    private String errorMessage;

    public String getSmsMessageId() {
        return smsMessageId;
    }

    public void setSmsMessageId(String smsMessageId) {
        this.smsMessageId = smsMessageId;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "SendResponse{" +
                "smsMessageId='" + smsMessageId + '\'' +
                ", createTime=" + responseTime +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
