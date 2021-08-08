package pro.haichuang.framework.base.config.mvc.filter;

import org.springframework.core.annotation.Order;
import pro.haichuang.framework.base.util.common.UUIDUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 线程UUID切面
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
@WebFilter(urlPatterns = "/**")
@Order(0)
public class UUIDOfLocalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UUIDUtils.Local.init();
        chain.doFilter(request instanceof HttpServletRequest
                ? new RepeatRequestWrapper((HttpServletRequest) request) : request, response);
        UUIDUtils.Local.remove();
    }
}
