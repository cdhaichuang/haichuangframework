package pro.haichuang.framework.base.dto;

import java.io.Serializable;

/**
 * HttpServletRequestDTO
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpServletRequestDTO implements Serializable {
    private static final long serialVersionUID = 771686212576678477L;

    /**
     * 当前请求UUID
     */
    private String uuid;

    /**
     * 客户端真实请求IP地址
     */
    private String clientIp;

    /**
     * 完整请求方法
     */
    private String fullMethodName;

    /**
     * 请求方法描述
     */
    private String methodDescription;

    /**
     * 请求用户ID
     */
    private Long userId;

    public String getUuid() {
        return uuid;
    }

    public HttpServletRequestDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public HttpServletRequestDTO setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getFullMethodName() {
        return fullMethodName;
    }

    public HttpServletRequestDTO setFullMethodName(String fullMethodName) {
        this.fullMethodName = fullMethodName;
        return this;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public HttpServletRequestDTO setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public HttpServletRequestDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "HttpServletRequestDTO{" +
                "uuid='" + uuid + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", userId=" + userId +
                ", fullMethodName='" + fullMethodName + '\'' +
                ", apiDescription='" + methodDescription + '\'' +
                '}';
    }
}
