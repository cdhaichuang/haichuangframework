package pro.haichuang.framework.base.config.mvc.filter;

import org.apache.catalina.connector.Request;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import pro.haichuang.framework.base.util.common.RequestUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 重复获取请求流包装器
 *
 * <p>该类主要为解决 {@link HttpServletRequest} 中 {@code inputStream} 流只能被读取一次问题
 * <p>在处理表单请求时需要注意, 如果带有文件将会调用 {@link HttpServletRequest#getParts()} 拿到文件域,
 * 最终会调用 {@link Request#getParts()} 方法, 核心为 {@code getParts()} 方法中的 {@code parseParts(boolean)} 方法,
 * 在 {@code parseParts(boolean)} 方法中将会使用 {@link ServletFileUpload#parseRequest(RequestContext)} 方法进行解析文件,
 * 在 {@code parseRequest(RequestContext)} 方法中取出输入流, 所以如果为非 {@code json} 请求时不进行任何操作
 * <p>Warning: 在提前使用 {@link #getInputStream()} 方法时依然需要谨慎使用
 * (建议提前使用 {@link RequestUtils#isJsonRequest(HttpServletRequest)}) 判断该请求是否为 {@code json} 请求
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public class RepeatRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body = new byte[0];

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IOException If the request is null
     * @since 1.0.0.211009
     */
    public RepeatRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (RequestUtils.isJsonRequest(this)) {
            this.body = IOUtils.toByteArray(request.getInputStream());
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return RequestUtils.isJsonRequest(this) ? new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body))) : super.getReader();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return RequestUtils.isJsonRequest(this) ? new ServletInputStream() {
            private int lastIndexRetrieved = -1;
            private ReadListener readListener = null;

            @Override
            public boolean isFinished() {
                return lastIndexRetrieved == body.length - 1;
            }

            @Override
            public boolean isReady() {
                return isFinished();
            }

            @Override
            public void setReadListener(ReadListener listener) {
                this.readListener = listener;
                if (!isFinished()) {
                    try {
                        readListener.onDataAvailable();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                } else {
                    try {
                        readListener.onAllDataRead();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                }
            }

            @Override
            public int read() throws IOException {
                int index = -1;
                if (!isFinished()) {
                    index = body[lastIndexRetrieved + 1];
                    lastIndexRetrieved++;
                    if (isFinished() && (readListener != null)) {
                        try {
                            readListener.onAllDataRead();
                        } catch (IOException e) {
                            readListener.onError(e);
                            throw e;
                        }
                    }
                }
                return index;
            }
        } : super.getInputStream();
    }
}
