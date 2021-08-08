package pro.haichuang.framework.base.exception;

/**
 * 堆栈自定义异常
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public class StackTraceException extends RuntimeException {
    private static final long serialVersionUID = -2151212491487794180L;

    public StackTraceException(String message) {
        super(message);
    }
}
