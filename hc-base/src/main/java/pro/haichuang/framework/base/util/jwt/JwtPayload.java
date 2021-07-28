package pro.haichuang.framework.base.util.jwt;

import java.io.Serializable;
import java.util.Date;

/**
 * JWT载荷
 *
 * <p>该类为JWT标准载荷+自定义载荷, 其中包含了两个自定义参数, 对JWT相关操作请参考 {@link JwtUtils}
 * <ul>
 *     <li>userId: 用户ID</li>
 *     <li>internal: 是否为系统内置</li>
 * </ul>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see JwtUtils
 */
public class JwtPayload implements Serializable {
    private static final long serialVersionUID = -3034724392253728198L;

    public static final String USER_ID = "userId";
    public static final String INTERNAL = "internal";

    // ========================= JWT标准字段 =========================

    /**
     * JWT ID 唯一标识（UUID）
     */
    private String jti;

    /**
     * Issuer 签发者
     */
    private String iss;

    /**
     * Issuer at 签发时间（时间戳）
     */
    private Date iat;

    /**
     * Expire 过期时间（时间戳）
     */
    private Date exp;

    /**
     * Not Before 如果当前时间在此时间之前则token不被接受
     */
    private Date nbf;

    /**
     * Audience 接收方
     */
    private String aud;

    /**
     * Subject 面向的用户
     */
    private String sub;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 是否为内置
     */
    private boolean internal;

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Date getNbf() {
        return nbf;
    }

    public void setNbf(Date nbf) {
        this.nbf = nbf;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    @Override
    public String toString() {
        return "JwtPayload{" +
                "jti='" + jti + '\'' +
                ", iss='" + iss + '\'' +
                ", iat=" + iat +
                ", exp=" + exp +
                ", nbf=" + nbf +
                ", aud='" + aud + '\'' +
                ", sub='" + sub + '\'' +
                ", userId=" + userId +
                ", internal=" + internal +
                '}';
    }
}
