package pro.haichuang.framework.base.config.mvc.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 重复请求包装器
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class RepeatRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public RepeatRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IOUtils.toByteArray(request.getInputStream());
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body)));
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStream() {
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
                }else {
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
        };
    }
}
