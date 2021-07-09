package pro.haichuang.framework.base.exception;

/**
 * 枚举非法论证异常
 *
 * <p>主要用于自定义枚举转换时发生的异常</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class EnumIllegalArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = -6738594844611626333L;

    public EnumIllegalArgumentException() {
    }

    public EnumIllegalArgumentException(String s) {
        super(s);
    }

    public EnumIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
