package pro.haichuang.framework.mybatis.config.druid.advert.filter;

import com.alibaba.druid.util.Utils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Druid广告过滤器
 *
 * <p>该过滤器主要用于去掉 [druid] 网页中的底部广告内容
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DruidAdvertFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        // 重置缓冲区
        response.resetBuffer();
        String filePath = "support/http/resources/js/common.js";
        // 获取common.js
        String text = Utils.readFromResource(filePath);
        // 正则替换banner, 除去底部广告信息
        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
        text = text.replaceAll("powered.*?shrek.wang</a>", "");
        response.getWriter().write(text);
    }
}
