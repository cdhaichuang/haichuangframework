package pro.haichuang.framework.mybatis.exception;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.mybatis.enums.error.MybatisServiceErrorEnum;

/**
 * MybatisService自定义异常
 *
 * @author JiYinchuan
 * @see MybatisServiceErrorEnum
 * @since 1.1.0.211021
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
