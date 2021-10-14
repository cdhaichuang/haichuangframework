package pro.haichuang.framework.base.util.common;

import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP工具类
 *
 * <p>该类提供了获取客户端IP的方法
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 */
public class IpUtils {

    private static final String UNKNOWN = "unknown";

    private static final Pattern ACCESS_IPV4_PATTERN = Pattern.compile("((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})" +
            "(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}");

    /**
     * 获取客户端真实IP地址
     *
     * <p>使用Nginx等反向代理软件， 则不能通过{@link HttpServletRequest#getRemoteAddr()} 获取IP地址,
     * 否则拿到的是Nginx等反向代理软件所在的IP地址, 并非真实的客户端IP
     * <p>当设置了 {@code X-Forwarded-For} 时, 如果使用了多级反向代理, {@code X-Forwarded-For} 的值并不止一个(),
     * 而是一串IP地址(逗号分割), {@code X-Forwarded-For} 中第一个非 {@code unknown} 的有效IP字符串, 则为真实IP地址
     * (客户端可以伪造 {@code X-Forwarded-For} 请求头, 需要验证IP正确性)
     * <hr>
     * <ul>
     *     <li>X-Forwarded-For: 该字段为行业统一请求头, 并非标准请求头, 用于Nginx等反向代理软件转发请求来源的IP地址</li>
     * </ul>
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     * @since 1.0.0.211014
     */
    @NonNull
    public static String getIpv4Address(@NonNull HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            // noinspection AlibabaUndefineMagicConstant
            if (ip != null && ip.contains(",") && ip.split(",").length > 1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            Matcher matcher = ACCESS_IPV4_PATTERN.matcher(ip);
            if (matcher.find()) {
                ip = matcher.group();
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
