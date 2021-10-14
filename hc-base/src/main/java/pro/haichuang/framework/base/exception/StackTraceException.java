package pro.haichuang.framework.base.exception;

/**
 * 堆栈自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
public class StackTraceException extends RuntimeException {
    private static final long serialVersionUID = -101811903662329126L;

    public StackTraceException(String message) {
        super(message);
    }
}
