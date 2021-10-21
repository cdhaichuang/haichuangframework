package pro.haichuang.framework.sdk.huaweicloudsms.response;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.success.HuaWeiCloudSmsSendSuccessEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 华为云短信基础响应
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class SendResponse implements Serializable {
    private static final long serialVersionUID = -3910888723895098822L;

    /**
     * 错误码
     */
    @JSONField(name = "code")
    @JsonProperty("code")
    private String errorCode;

    /**
     * 错误信息
     */
    @JSONField(name = "description")
    @JsonProperty("description")
    private String errorMessage;

    /**
     * 响应数据
     */
    @JSONField(name = "result")
    @JsonProperty("result")
    private List<Result> content = new ArrayList<>();

    /**
     * 华为云短信发送结果响应
     *
     * @author JiYinchuan
     * @since 1.1.0.211021
     */
    public static class Result implements Serializable {
        private static final long serialVersionUID = -6452712762780474877L;

        /**
         * 短信的唯一标识
         */
        @JSONField(name = "smsMsgId")
        @JsonProperty("smsMsgId")
        private String smsMessageId;

        /**
         * 短信资源的创建时间, 即短信平台接收到客户发送短信请求的时间
         */
        @JSONField(name = "createTime")
        @JsonProperty("createTime")
        @DateTimeFormat(pattern = DatePattern.UTC_PATTERN)
        @JsonFormat(pattern = DatePattern.UTC_PATTERN)
        private LocalDateTime createTime;

        /**
         * 短信发送方的号码
         */
        @JSONField(name = "from")
        @JsonProperty("from")
        private String channelNumber;

        /**
         * 短信接收方的号码
         */
        @JSONField(name = "originTo")
        @JsonProperty("originTo")
        private String phoneNumber;

        /**
         * 短信状态码
         */
        @JSONField(name = "status")
        @JsonProperty("status")
        private HuaWeiCloudSmsSendSuccessEnum status;

        public String getSmsMessageId() {
            return smsMessageId;
        }

        public void setSmsMessageId(String smsMessageId) {
            this.smsMessageId = smsMessageId;
        }

        public LocalDateTime getCreateTime() {
            return createTime;
        }

        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        public String getChannelNumber() {
            return channelNumber;
        }

        public void setChannelNumber(String channelNumber) {
            this.channelNumber = channelNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public HuaWeiCloudSmsSendSuccessEnum getStatus() {
            return status;
        }

        public void setStatus(HuaWeiCloudSmsSendSuccessEnum status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "SendResultResponse{" +
                    "smsMessageId='" + smsMessageId + '\'' +
                    ", createTime=" + createTime +
                    ", channelNumber='" + channelNumber + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", status=" + status +
                    '}';
        }
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

    public List<Result> getContent() {
        return content;
    }

    public void setContent(List<Result> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SendResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", content=" + content +
                '}';
    }
}
