package pro.haichuang.framework.sdk.wxmp.dto;

import java.io.Serializable;
import java.time.Duration;

/**
 * WxMpJsApiTicketDTO
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class WxMpJsApiTicketDTO implements Serializable {
    private static final long serialVersionUID = 8067673354928705952L;

    /**
     * WebAccessToken
     */
    private String ticket;

    /**
     * 有效时间
     */
    private Duration effectiveTime;


    public String getTicket() {
        return ticket;
    }

    public WxMpJsApiTicketDTO setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public Duration getEffectiveTime() {
        return effectiveTime;
    }

    public WxMpJsApiTicketDTO setEffectiveTime(Duration effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    @Override
    public String toString() {
        return "WxMpJsApiTicketDTO{" +
                "ticket='" + ticket + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                '}';
    }
}
