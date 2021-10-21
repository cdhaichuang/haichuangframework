package pro.haichuang.framework.base.config.mvc.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import pro.haichuang.framework.base.key.ProjectKey;
import pro.haichuang.framework.base.util.common.ThreadLocalUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 线程UUID切面
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@WebFilter(urlPatterns = "/**")
@Order(0)
public class RepeatAndMdcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            MDC.put(ProjectKey.loggerThreadTtl(), ThreadLocalUtils.id());
            chain.doFilter(request instanceof HttpServletRequest
                    ? new RepeatRequestWrapper((HttpServletRequest) request) : request, response);
        } finally {
            // 为避免业务处理中异常导致 [ThreadLocal] 没有执行 [ThreadLocal#remove()] 方法, 从而OOM
            // OOM的原因为 [ThreadLocal] 一直持有对象地址, 根据JVM可达性分析算法会被认为可达, 导致一直不会自动回收本线程中的 [ThreadLocal]
            MDC.remove(ProjectKey.loggerThreadTtl());
        }
    }
}
