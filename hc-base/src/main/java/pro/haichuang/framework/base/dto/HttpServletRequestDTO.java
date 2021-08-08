package pro.haichuang.framework.base.dto;

import java.io.Serializable;

/**
 * HttpServletRequestDTO
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public class HttpServletRequestDTO implements Serializable {
    private static final long serialVersionUID = -8724659073656593786L;

    /**
     * 客户端真实请求IP地址
     */
    private String clientIp;

    /**
     * 请求信息
     */
    private String apiMessage;

    /**
     * 请求用户ID
     */
    private Long userId;

    /**
     * 完整请求方法
     */
    private String fullMethodName;

    public String getClientIp() {
        return clientIp;
    }

    public HttpServletRequestDTO setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public HttpServletRequestDTO setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public HttpServletRequestDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFullMethodName() {
        return fullMethodName;
    }

    public HttpServletRequestDTO setFullMethodName(String fullMethodName) {
        this.fullMethodName = fullMethodName;
        return this;
    }

    @Override
    public String toString() {
        return "HttpServletRequestDTO{" +
                "clientIp='" + clientIp + '\'' +
                ", apiMessage='" + apiMessage + '\'' +
                ", userId=" + userId +
                ", fullMethodName='" + fullMethodName + '\'' +
                '}';
    }
}
