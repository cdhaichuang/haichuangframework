package pro.haichuang.framework.base.config.mvc.filter;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class RepeatRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body = new byte[0];

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IOException If the request is null
     */
    public RepeatRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (isJsonRequest()) {
            this.body = IOUtils.toByteArray(request.getInputStream());
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return isJsonRequest() ? new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body))) : super.getReader();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return isJsonRequest() ? new ServletInputStream() {
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

    /**
     * 判断是否为JSON请求
     *
     * @return {false: 否, true: 是}
     */
    private boolean isJsonRequest() {
        return MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(super.getHeader(HttpHeaders.CONTENT_TYPE));
    }
}
