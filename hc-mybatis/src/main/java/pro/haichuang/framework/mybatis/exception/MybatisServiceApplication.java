package pro.haichuang.framework.mybatis.exception;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.mybatis.enums.error.MybatisServiceErrorEnum;

/**
 * MybatisService自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see MybatisServiceErrorEnum
 */
public class MybatisServiceApplication extends ApplicationException {
    private static final long serialVersionUID = 8447830148015276778L;

    public MybatisServiceApplication(MybatisServiceErrorEnum mybatisServiceErrorEnum) {
        super(mybatisServiceErrorEnum);
    }

    public MybatisServiceApplication(MybatisServiceErrorEnum mybatisServiceErrorEnum, @Nullable String userTip) {
        super(mybatisServiceErrorEnum, userTip);
    }
}
